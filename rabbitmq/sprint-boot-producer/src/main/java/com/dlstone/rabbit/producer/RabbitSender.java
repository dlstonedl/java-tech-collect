package com.dlstone.rabbit.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        System.err.println("correlationData=" + correlationData);
        System.err.println("ack=" + ack);
        if (!ack) {
            System.err.println("cause=" + cause);
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText, exchange, routingKey) -> {
        System.err.println("message=" + message);
        System.err.println("replyCode=" + replyCode);
        System.err.println("replyText=" + replyText);
        System.err.println("exchange=" + exchange);
        System.err.println("routingKey=" + routingKey);
    };


    public void send(String exchange, String routingKey, Object message, CorrelationData correlationData) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
    }
}
