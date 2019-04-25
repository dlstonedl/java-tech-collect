package com.dlstone.rabbit.comsumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "spring_boot_queue",
                    durable = "true",
                    exclusive = "false",
                    autoDelete = "false"),
            exchange = @Exchange(name = "spring_boot_producer_exchange",
                    type = ExchangeTypes.TOPIC),
            key = "spring.boot.*"
    ))
    public void handler(Message message, Channel channel) throws Exception {
        System.err.println(new String(message.getBody()));
    }
}
