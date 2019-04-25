package com.dlstone.rabbit.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitSenderTest {

    @Autowired
    private RabbitSender rabbitSender;

    @Test
    public void confirm_callback_happy_path() throws Exception{
        CorrelationData correlationData = new CorrelationData("111");
        rabbitSender.send("spring_boot_producer_exchange", "spring.boot.hello","Hello SpringBoot RabbitMQ", correlationData);
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void should_return_false_when_exchange_not_exist() throws Exception{
        CorrelationData correlationData = new CorrelationData("111");
        rabbitSender.send("spring_boot_error_exchange", "spring.boot.hello","Hello SpringBoot RabbitMQ", correlationData);
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void should_return_callback_when_queue_not_exist() throws Exception {
        CorrelationData correlationData = new CorrelationData("111");
        rabbitSender.send("spring_boot_producer_exchange", "spring.error.hello","Hello SpringBoot RabbitMQ", correlationData);
        TimeUnit.SECONDS.sleep(1);
    }

}
