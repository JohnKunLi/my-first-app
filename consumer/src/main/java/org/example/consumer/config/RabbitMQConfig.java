package org.example.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //交换机
    public static final String EXCHANGE_NAME = "demo.exchange";
    //队列
    public static final String QUEUE_NAME = "demo.order.queue";
    //路由
    public static final String ROUTING_KEY = "demo.order";

    @Bean
    public Exchange demoExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    public Queue demoQueue() {
        return QueueBuilder.durable(QUEUE_NAME)
                .build();
    }

    @Bean
    public Binding demoBinding(Queue demoQueue, Exchange demoExchange) {
        return BindingBuilder.bind(demoQueue)
                .to(demoExchange)
                .with(ROUTING_KEY)
                .noargs();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}