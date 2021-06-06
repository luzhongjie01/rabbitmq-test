package com.zhongjie.workqueues;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhongjie.util.RabbitmqConnectUtil;
import com.zhongjie.util.RabbitmqConstant;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Order {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqConnectUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(RabbitmqConstant.QUEUE_HELLOWORK, false, false, false, null);


        for (int i = 1;i<=100;i++){
            Sms sms = new Sms();
            sms.setName("乘客" + i);
            sms.setPhone("手机号" + "123" + i);
            sms.setSmsContent("出票成功");
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            channel.basicPublish("", RabbitmqConstant.QUEUE_HELLOWORK, null, gson.toJson(sms).getBytes());
        }

        channel.close();

        connection.close();

        System.out.println("发送成功");
    }
}
