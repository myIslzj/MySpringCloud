package com.lzj.controller;

import com.lzj.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 消费者
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
public class OrderConsulController {

    public static final String PAYMENT_URL = "http://consul-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/paymentConsul")
    private String createPayment(Payment payment){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/consulService", String.class);
    }

}
