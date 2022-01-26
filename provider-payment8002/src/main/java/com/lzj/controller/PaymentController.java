package com.lzj.controller;

import com.lzj.entity.Payment;
import com.lzj.service.PaymentService;
import com.lzj.utils.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 生产者，支付模块
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/add")
    private Map<String, Object> add(@RequestBody Payment payment){
        try {
            paymentService.add(payment);
            return ResultMap.writeResultMap(1, "添加成功，serverPort=" + serverPort);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加失败：" + e);
        }
        return ResultMap.writeResultMap(-1, "添加失败");
    }

    @GetMapping("/getPaymentById")
    private Map<String, Object> getPaymentById(@RequestParam int id){
        try {
            Payment payment = paymentService.getPaymentById(id);
            return ResultMap.readResultMap(1, "查询成功，serverPort=" + serverPort, payment);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询失败：" + e);
        }
        return ResultMap.readResultMap(-1, "查询失败", null);
    }

    @GetMapping("/myRibbon")
    private String myRibbon(){
        return serverPort;
    }

    @GetMapping("/feign/timeout")
    private String feignTimeout(){
        try {
            //等待3秒钟
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
