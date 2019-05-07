package com.dlstone.rabbit.comsumer.pull;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitPullModel {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitPullModel(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void pullMessage() {
        Object message = rabbitTemplate.receiveAndConvert(PullQueueConfig.SPRING_BOOT_PULL_QUEUE);
        System.err.println(new String((byte[]) message));
    }
}
