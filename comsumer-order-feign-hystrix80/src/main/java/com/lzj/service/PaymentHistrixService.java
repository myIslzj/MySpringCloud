package com.lzj.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "hystrix-payment-service", fallback = PaymentFallbackService.class)
public interface PaymentHistrixService {

    @GetMapping("/payment/hystrixOk")
    String paymentOk(@RequestParam("id") int id);

    @GetMapping("/payment/hystrixTimeout")
    String paymentTimeout(@RequestParam("id") int id);

}
