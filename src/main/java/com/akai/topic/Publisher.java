package com.akai.topic;

import com.akai.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
    @Autowired
    public RabbitTemplate rabbitTemplate;

    // 发送普通消息
    public void publish() {
        // 指定：1 交换机 2 routingKey 3 message
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "big.black.dog", "hello shiho~");
    }

    public void publishWithProps() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "big.black.dog", "msg with props", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setCorrelationId("123");
                return message;
            }
        });
    }
}
