package com.dlstone.rabbit.comsumer.push;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadLetterConfig {

    @Bean
    public TopicExchange dlxExchange() {
        return new TopicExchange("dlx.exchange");
    }

    @Bean
    public Queue dlxQueue() {
        return new Queue("dlx.queue");
    }

    @Bean
    public Binding dlxBinding(TopicExchange dlxExchange, Queue dlxQueue) {
        return BindingBuilder
                .bind(dlxQueue)
                .to(dlxExchange)
                .with("#");
    }
}
