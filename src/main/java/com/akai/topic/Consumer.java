package com.akai.topic;

import com.akai.config.RabbitMQConfig;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    // 1 msg 消息
    // 2 channel 用于确认消息
    // 3 message 获取自定义属性
    public void consume(String msg, Channel channel, Message message) throws IOException {
        System.out.println("msg is " + msg);
        // 通过 message对象获取自定义属性
        String correlationId = message.getMessageProperties().getCorrelationId();
        System.out.println("unique id is " + correlationId);
        // 传入消息 id ——> 通过 message对象获取
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
