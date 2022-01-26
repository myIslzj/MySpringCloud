package com.lzj.service.impl;

import com.lzj.dao.PaymentDao;
import com.lzj.entity.Payment;
import com.lzj.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int add(Payment payment) {
        return paymentDao.add(payment);
    }

    @Override
    public Payment getPaymentById(int id) {
        return paymentDao.getPaymentById(id);
    }
}
