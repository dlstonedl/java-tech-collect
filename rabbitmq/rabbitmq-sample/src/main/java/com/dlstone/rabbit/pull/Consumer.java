package com.dlstone.rabbit.pull;

import com.rabbitmq.client.*;

import java.util.Objects;

public class Consumer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明queue
        channel.queueDeclare("pull-queue", true, false, false, null);
        //声明exchange
        channel.exchangeDeclare("pull-exchange", "topic", true);
        //绑定
        channel.queueBind("pull-queue", "pull-exchange","pull.*");

        //pull模式
        GetResponse response = channel.basicGet("pull-queue", false);
        if (Objects.nonNull(response)) {
            byte[] body = response.getBody();
            System.err.println(new String(body));

            long deliveryTag = response.getEnvelope().getDeliveryTag();
            channel.basicAck(deliveryTag, false);
        }

        channel.close();
        connection.close();
    }
}
