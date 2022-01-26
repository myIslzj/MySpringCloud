package com.lzj.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHistrixService {

    @Override
    public String paymentOk(int id) {
        return "PaymentFallbackService, fallback-paymentOk.....";
    }

    @Override
    public String paymentTimeout(int id) {
        return "PaymentFallbackService, fallback-paymentTimeout.....";
    }
}
