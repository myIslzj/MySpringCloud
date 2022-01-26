package com.lzj.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /************************************ 服务降级 *********************************/

    /**
     * 服务异常调用回滚方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "hystrixTimeout_fallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")}) //等待3秒
    public String paymentTimeout(int id) {
        int timeout = 5;
//        int i = 10/0;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "，hystrixTimeout  id=" + id + " 耗死（秒）：";
    }

    public String hystrixTimeout_fallback(int id){
        return "线程池：" + Thread.currentThread().getName() + "，hystrixTimeout_fallback  id=" + id +" 运行报错回滚。。。。";
    }

    /************************************ 服务熔断 *********************************/

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  //是否开启熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),  //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),  //窗口时间
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")  //失败率为多少，熔断
    })
    public String paymentCircuitBreaker(int id){
        if (id < 0){
            throw new RuntimeException("***** id不能为负数");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + " 调用成功，流水号：" + uuid;
    }

    private String paymentCircuitBreaker_fallback(int id){
        return "id不能为负数。。。。。。。。。。。。。";
    }

}
