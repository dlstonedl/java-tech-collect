package com.dlstone.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = {"test"}, groupId = "group_id")
    public void consumer(String message) {
        System.out.println("message is " + message);
    }

}
