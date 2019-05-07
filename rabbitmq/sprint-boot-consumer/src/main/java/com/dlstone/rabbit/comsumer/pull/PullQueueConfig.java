package com.dlstone.rabbit.comsumer.pull;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PullQueueConfig {

    public static final String SPRING_BOOT_PULL_QUEUE = "spring-boot-pull-queue";

    @Bean
    public Queue pullQueue() {
        return new Queue(SPRING_BOOT_PULL_QUEUE);
    }

    @Bean
    public TopicExchange pullExchange() {
        return new TopicExchange("spring-boot-pull-exchange");
    }

    @Bean
    public Binding pullBinding(Queue pullQueue, TopicExchange pullExchange) {
        return BindingBuilder
                .bind(pullQueue)
                .to(pullExchange)
                .with("spring.boot.pull.*");
    }
}
