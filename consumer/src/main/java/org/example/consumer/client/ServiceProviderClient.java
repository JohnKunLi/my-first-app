package org.example.consumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "service-provider", path = "/api")
public interface ServiceProviderClient {

    @GetMapping("/user/{id}")
    Map<String, Object> getUser(@PathVariable("id") Long id);

    @PostMapping("/user")
    Map<String, Object> saveUser(@RequestBody Map<String, Object> user);

    @GetMapping("/header-test")
    String headerTest(@RequestHeader("X-Request-Id") String requestId);
}