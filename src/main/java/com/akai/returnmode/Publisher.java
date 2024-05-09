package com.akai.returnmode;

import com.akai.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;


@SpringBootTest
@Component
public class Publisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void publishWithReturn() {
        // 新版本用 setReturnsCallback，老版本用 setReturnCallback
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                // returnedMessage 包含一条消息所含有的所有信息
                String msg = new String(returnedMessage.getMessage().getBody());
                System.out.println(msg + "执行失败!");
            }
        });
        // 指定：1 交换机 2 routingKey 3 message
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "big.black.dog", "hello shiho~");
    }
}
