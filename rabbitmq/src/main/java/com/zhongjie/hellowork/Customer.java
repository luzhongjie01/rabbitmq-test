package com.zhongjie.hellowork;

import com.rabbitmq.client.*;
import com.zhongjie.util.RabbitmqConnectUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer {
    public static void main(String[] args) throws IOException, TimeoutException {


        Connection connection = RabbitmqConnectUtil.getConnection();

        final Channel channel = connection.createChannel();

        //创建一个队列,如果队列存在则使用这个队列
        channel.queueDeclare("helloword", false, false, false, null);

        channel.basicConsume("helloword", false, new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接受到消息:" + new String(body));
                System.out.println("消息的tagId:" + envelope.getDeliveryTag());
                //false只确认签收当前的消息，设置为true的时候则代表签收该消费者所有未签收的消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });


    }
}
