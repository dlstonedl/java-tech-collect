package com.dlstone.rabbit.comsumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AEConfig {

    @Bean
    public TopicExchange aeExchange() {
        return new TopicExchange("ae.exchange");
    }

    @Bean
    public Queue aeQueue() {
        return new Queue("ae.queue");
    }

    @Bean
    public Binding aeBinding(TopicExchange aeExchange, Queue aeQueue) {
        return BindingBuilder
                .bind(aeQueue)
                .to(aeExchange)
                .with("#");
    }
}
