package com.lzj.controller;

import com.lzj.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 消费者
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    /**
     * openfeign实现负载均衡（自带Ribbon）
     * @param id
     * @return
     */
    @GetMapping("/feign/getPayment")
    private Map<String, Object> getPayment(@RequestParam int id){
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * openfeign-ribbon 客户端默认等待1秒钟
     * @return
     */
    @GetMapping("/feign/timeout")
    private String feignTimeout(){
        return paymentFeignService.feignTimeout();
    }

}
