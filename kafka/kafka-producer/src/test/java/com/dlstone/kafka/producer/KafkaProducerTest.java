package com.dlstone.kafka.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaProducerTest {

    @Autowired
    public KafkaProducer kafkaProducer;

    @Test
    public void should_send_default_message() {
        kafkaProducer.sendMessage();
    }

}
