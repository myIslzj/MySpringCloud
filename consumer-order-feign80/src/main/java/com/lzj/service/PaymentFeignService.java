package com.lzj.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Component
@FeignClient(value = "PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/getPaymentById")
    Map<String, Object> getPaymentById(@RequestParam("id") int id);

    @GetMapping("/payment/feign/timeout")
    String feignTimeout();

}
