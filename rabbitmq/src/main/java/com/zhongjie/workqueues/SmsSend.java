package com.zhongjie.workqueues;

import com.rabbitmq.client.*;
import com.zhongjie.util.RabbitmqConnectUtil;
import com.zhongjie.util.RabbitmqConstant;

import java.io.IOException;

public class SmsSend {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqConnectUtil.getConnection();

        final Channel channel = connection.createChannel();

        //创建一个队列,如果队列存在则使用这个队列
        channel.queueDeclare(RabbitmqConstant.QUEUE_HELLOWORK, false, false, false, null);

        channel.basicQos(1);

        channel.basicConsume(RabbitmqConstant.QUEUE_HELLOWORK, false, new DefaultConsumer(channel) {


            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("接受到消息:" + new String(body));
                //false只确认签收当前的消息，设置为true的时候则代表签收该消费者所有未签收的消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
