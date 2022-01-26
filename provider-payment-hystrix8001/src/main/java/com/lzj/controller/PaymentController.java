package com.lzj.controller;

import com.lzj.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/hystrixOk")
    private String paymentOk(@RequestParam int id) {
        log.info("线程池：" + Thread.currentThread().getName() + "，paymentOk  id=" + id);
        return "线程池：" + Thread.currentThread().getName() + "，paymentOk  id=" + id;
    }

    @GetMapping("/hystrixTimeout")
    private String paymentTimeout(@RequestParam int id) {
        return paymentService.paymentTimeout(id);
    }

    /**
     * 服务熔断
     * @param id
     * @return
     */
    @GetMapping("/circuitBreaker")
    public String paymentCircuitBreaker(@RequestParam int id){
        return paymentService.paymentCircuitBreaker(id);
    }

}
