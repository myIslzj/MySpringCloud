package com.lzj.controller;

import com.lzj.service.PaymentHistrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "global_fallback_method")
public class OrderController {

    @Resource
    private PaymentHistrixService paymentHistrixService;

    @GetMapping("/hystrixOk")
    public String hystrixOk(@RequestParam int id){
        return paymentHistrixService.paymentOk(id);
    }

    @GetMapping("/hystrixTimeout")
    /*@HystrixCommand(fallbackMethod = "paymentHystrixTimeoutFallback", commandProperties = { //该注解不能用在private方法上
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")}) //等待1.5秒*/
    @HystrixCommand  //调用默认回滚方法
    public String hystrixTimeout(@RequestParam int id){
        return paymentHistrixService.paymentTimeout(id);
    }

    private String paymentHystrixTimeoutFallback(int id){
        return "客户端调用服务端异常，进行回滚。。。。。。。";
    }

    private String global_fallback_method(){
        return "客户端调用服务端异常，全局异常回滚。。。。。。。";
    }

}
