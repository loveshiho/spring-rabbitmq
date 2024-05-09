package com.akai.confirm;

import com.akai.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
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
    public void publishWithConfirms() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            // b代表是否 ack 布尔值 已经帮我们判断好了
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println(b == true ? "nice~" : "fuck!");
            }
        });
        // 指定：1 交换机 2 routingKey 3 message
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "big.black.dog", "hello shiho~");
    }
}
