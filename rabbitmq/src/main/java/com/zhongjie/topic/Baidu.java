package com.zhongjie.topic;

import com.rabbitmq.client.*;
import com.zhongjie.util.RabbitmqConnectUtil;
import com.zhongjie.util.RabbitmqConstant;

import java.io.IOException;

public class Baidu {
    public static void main(String[] args) throws IOException {

        Connection connection = RabbitmqConnectUtil.getConnection();

        final Channel channel = connection.createChannel();

        channel.queueDeclare(RabbitmqConstant.QUEUE_BAIDU, false, false, false, null);

        channel.queueBind(RabbitmqConstant.QUEUE_BAIDU, RabbitmqConstant.EXCHANGE_WEATHER_TOPIC, "#.changsha");

        channel.basicConsume(RabbitmqConstant.QUEUE_BAIDU, false, new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接受到消息:" + new String(body));
                //false只确认签收当前的消息，设置为true的时候则代表签收该消费者所有未签收的消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
