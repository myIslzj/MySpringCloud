package com.lzj.dao;

import com.lzj.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int add(Payment payment);

    Payment getPaymentById(@Param("id") int id);

}
