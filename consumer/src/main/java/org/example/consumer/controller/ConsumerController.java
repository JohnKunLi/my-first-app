package org.example.consumer.controller;

import org.example.consumer.client.ServiceProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ServiceProviderClient serviceProviderClient;

    // 测试 GET 请求
    @GetMapping("/test-get/{id}")
    public Map<String, Object> testGet(@PathVariable Long id) {
        return serviceProviderClient.getUser(id);
    }

    // 测试 POST 请求
    @PostMapping("/test-post")
    public Map<String, Object> testPost(@RequestBody Map<String, Object> user) {
        return serviceProviderClient.saveUser(user);
    }

    // 测试 Header 传递
    @GetMapping("/test-header")
    public String testHeader(@RequestParam String requestId) {
        return serviceProviderClient.headerTest(requestId);
    }
}