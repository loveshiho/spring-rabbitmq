package com.akai.delayed;

import com.akai.config.DelayedMQConfig;
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
public class DelayedPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testPublish() {
        String msg = "hello shiho~";
        rabbitTemplate.convertAndSend(DelayedMQConfig.DELAYED_EXCHANGE, "delayed.shiho", msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("30000");
                return message;
            }
        });
    }
}
