package com.dlstone.rabbit.pull;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)
                .build();

        byte[] content = "hello world".getBytes();
        channel.basicPublish("pull-exchange", "pull.hello",
                false, properties, content);

        channel.close();
        connection.close();
    }
}
