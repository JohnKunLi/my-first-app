package org.example.consumer.listener;

import com.rabbitmq.client.Channel;
import org.example.consumer.dto.OrderMessage;
import org.example.consumer.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageListener {

    private static final Logger log = LoggerFactory.getLogger(OrderMessageListener.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleOrderMessage(Message message, Channel channel,
                                   @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        try {
            String body = new String(message.getBody());
            String messageId = message.getMessageProperties().getMessageId();
            log.info("收到消息, messageId: {}, body: {}", messageId, body);

            // 模拟业务处理
            log.info("处理订单业务逻辑: 创建订单、扣减库存、生成物流单...");
            Thread.sleep(1000);

            // 手动确认消息（成功）
            channel.basicAck(deliveryTag, false);
            log.info("消息处理成功, deliveryTag: {}", deliveryTag);

        } catch (Exception e) {
            log.error("消息处理失败, deliveryTag: {}, error: {}", deliveryTag, e.getMessage(), e);
            try {
                // 拒绝消息并重新入队（失败重试）
                channel.basicNack(deliveryTag, false, true);
            } catch (Exception ex) {
                log.error("拒绝消息失败", ex);
            }
        }
    }
}