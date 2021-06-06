package com.zhongjie.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitmqConnectUtil {

    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("192.168.94.133");
        connectionFactory.setVirtualHost("zhongjie");
        connectionFactory.setUsername("luzhongjie");
        connectionFactory.setPassword("luzhongjie");
        connectionFactory.setPort(5672);
    }

    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
