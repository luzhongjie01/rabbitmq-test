package com.zhongjie.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhongjie.util.RabbitmqConnectUtil;
import com.zhongjie.util.RabbitmqConstant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * 气象局类     生产者
 */
public class weatherBureau {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitmqConnectUtil.getConnection();

        Channel channel = connection.createChannel();

//        Scanner scanner = new Scanner(System.in);
//        String message = scanner.next();

        Map weatherMap = new HashMap<String,String>();
        weatherMap.put("changsha","2021-4-7 15度");
        weatherMap.put("guangzhou","2021-4-7 16度");
        weatherMap.put("huizhou","2021-4-7 17度");
        weatherMap.put("wuhan","2021-4-7 18度");
        //1.交换机名 2. 路由key 3. 基础的属性 4.消息实体
        Iterator<Map.Entry<String,String>> iterator = weatherMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            channel.basicPublish(RabbitmqConstant.EXCHANGE_WEATHER_ROUTING,entry.getKey() , null, entry.getValue().getBytes());
        }


        channel.close();

        connection.close();

    }
}
