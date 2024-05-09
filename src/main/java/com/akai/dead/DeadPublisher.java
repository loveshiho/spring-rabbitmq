package com.akai.dead;

import com.akai.config.DeadRabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
@Component
public class DeadPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testDeadPublish() {
        String msg = "hello dead~";
        rabbitTemplate.convertAndSend(DeadRabbitMQConfig.NORMAL_EXCHANGE, "normal.shiho", msg);
    }
    @Test
    public void testDeadPublishWithTTL() {
        String msg = "hello shiho~ ex";
        rabbitTemplate.convertAndSend(DeadRabbitMQConfig.NORMAL_EXCHANGE, "normal.shiho", msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("1000");
                return message;
            }
        });
    }
}
