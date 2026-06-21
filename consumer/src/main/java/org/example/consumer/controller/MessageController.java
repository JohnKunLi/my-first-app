package org.example.consumer.controller;

import org.example.consumer.dto.OrderMessage;
import org.example.consumer.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mq")
public class MessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String sendMessage() {
        OrderMessage message = new OrderMessage(
                "ORDER-" + System.currentTimeMillis(),
                "user_001",
                "笔记本电脑",
                1,
                5999.00
        );
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                message
        );
        return "消息已发送: " + message;
    }

    @PostMapping("/send-custom")
    public String sendCustomMessage(@RequestBody OrderMessage message) {
        message.setTimestamp(System.currentTimeMillis());
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                message
        );
        return "自定义消息已发送: " + message;
    }
}