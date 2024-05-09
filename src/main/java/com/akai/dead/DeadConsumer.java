package com.akai.dead;

import com.akai.config.DeadRabbitMQConfig;
import com.akai.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DeadConsumer {
    @RabbitListener(queues = DeadRabbitMQConfig.NORMAL_QUEUE)
    public void consume(String msg, Channel channel, Message message) throws IOException {
        System.out.println("接收到normal队列的消息：" + msg);
        // 参数列表：1 DeliveryTag 2 requeue 是否重新放回队列
        channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        // 参数列表：1 DeliveryTag 2 是否批操作 3 requeue 是否重新放回队列
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }
}
