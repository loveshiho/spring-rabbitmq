package com.akai.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // 交换机名称
    public static final String EXCHANGE_NAME = "boot-exchange";
    // 队列名称
    public static final String QUEUE_NAME = "boot-queue";
    // routingKey
    public static final String ROUTING_KEY = "*.black.*";

    @Bean
    public Exchange bootExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).build();
    }

    @Bean
    public Queue bootQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public Binding bootBinding(Exchange bootExchange, Queue bootQueue) {
        return BindingBuilder.bind(bootQueue).to(bootExchange).with(ROUTING_KEY).noargs();
        // 断点调试
        // Exchange [name=boot-exchange, type=topic, durable=true, autoDelete=false, internal=false, arguments={}]
        // Queue [name=boot-queue, durable=true, autoDelete=false, exclusive=false, arguments={}, actualName=boot-queue]
    }
}
