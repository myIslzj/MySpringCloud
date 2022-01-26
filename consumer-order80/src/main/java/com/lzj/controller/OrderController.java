package com.lzj.controller;

import com.lzj.entity.Payment;
import com.lzj.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * 消费者
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
public class OrderController {

    public static final String PAYMENT_URL = "http://PAYMENT-SERVICE";

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/createPayment")
    private Map<String, Object> createPayment(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/add", payment, Map.class);
    }

    @GetMapping("/getPayment")
    private Map<String, Object> getPayment(@RequestParam int id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getPaymentById?id=" + id, Map.class);
    }

    /**
     * 自定义轮询负载均衡
     * @return
     */
    @GetMapping("/myRibbon")
    private String myRibbon(){

        //获取服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances("payment-service");
        if (null == instances || instances.size() < 1){
            return "无可用服务";
        }

        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri + "/payment/myRibbon", String.class);
    }

}
