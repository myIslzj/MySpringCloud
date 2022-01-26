package com.lzj.service;

import com.lzj.entity.Payment;

public interface PaymentService {

    int add(Payment payment);

    Payment getPaymentById(int id);

}
