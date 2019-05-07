package com.dlstone.rabbit.comsumer.push;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RabbitPushModel {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "spring_boot_queue",
                    durable = "true",
                    exclusive = "false",
                    autoDelete = "false",
                    arguments= {@Argument(name = "x-dead-letter-exchange", value = "dlx.exchange")}),
            exchange = @Exchange(name = "spring_boot_producer_exchange",
                    type = ExchangeTypes.TOPIC,
                    arguments = {@Argument(name = "alternate-exchange", value = "ae.exchange")}),
            key = "spring.boot.*"
    ))
    public void handler(Message message, Channel channel) throws Exception {
        String ms = new String(message.getBody());

        System.err.println(ms);

        if (ms.contains("3")) {
            throw new RuntimeException();
        }

        TimeUnit.SECONDS.sleep(5);
    }
}
