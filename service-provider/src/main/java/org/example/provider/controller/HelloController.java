package org.example.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RefreshScope
public class HelloController {

    @Value("${user.name:default-user}")
    private String userName;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Service Provider!";
    }

    @GetMapping("/config")
    public String getConfig() {
        return "配置中心读取: user.name=" + userName;
    }

    // ========== 以下是新增的 Feign 测试接口 ==========

    /**
     * 1. 简单 GET 请求：根据 id 获取用户信息
     */
    @GetMapping("/user/{id}")
    public Map<String, Object> getUser(@PathVariable("id") Long id) {

        Map<String, Object> user = new HashMap<>();
        user.put("id", id);
        user.put("name", "用户" + id);
        user.put("email", "user" + id + "@example.com");
        return user;
    }

    /**
     * 2. POST 请求：模拟保存用户，接收 JSON 参数
     */
    @PostMapping("/user")
    public Map<String, Object> saveUser(@RequestBody Map<String, Object> user) {
        // 模拟保存成功，返回保存后的数据（附带一个生成的时间戳）
        user.put("savedAt", System.currentTimeMillis());
        return user;
    }

    /**
     * 3. 带请求头的接口：模拟需要 Header 参数的场景
     */
    @GetMapping("/header-test")
    public String headerTest(@RequestHeader("X-Request-Id") String requestId) {
        return "收到请求头 X-Request-Id: " + requestId;
    }
}