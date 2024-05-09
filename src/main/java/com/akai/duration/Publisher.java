package com.akai.duration;

import com.akai.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
@SpringBootTest
@Component
public class Publisher {
    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Test
    // 发送普通消息
    public void publishWithDuration() {
        // 指定：1 交换机 2 routingKey 3 message 4 自定义属性
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "big.black.dog", "hello shiho~", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // message.getMessageProperties(): 拿到 message的所有属性
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            }
        });
    }
}
