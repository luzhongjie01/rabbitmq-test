package com.zhongjie.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhongjie.util.RabbitmqConnectUtil;
import com.zhongjie.util.RabbitmqConstant;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * 气象局类     生产者
 */
public class weatherBureau {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitmqConnectUtil.getConnection();

        Channel channel = connection.createChannel();

        Scanner scanner = new Scanner(System.in);
        String message = scanner.next();

        channel.basicPublish(RabbitmqConstant.EXCHANGE_WEATHER, "", null, message.getBytes());

        channel.close();

        connection.close();

    }
}
