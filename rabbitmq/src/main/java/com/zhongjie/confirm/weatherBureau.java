package com.zhongjie.confirm;

import com.rabbitmq.client.*;
import com.zhongjie.util.RabbitmqConnectUtil;
import com.zhongjie.util.RabbitmqConstant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 气象局类     生产者
 */
public class weatherBureau {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitmqConnectUtil.getConnection();

        Channel channel = connection.createChannel();


        //开启消息确认机制
        channel.confirmSelect();


        channel.addReturnListener(new ReturnListener() {
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("监听到有消息未入队列");
                System.err.println("=============================================");
            }
        });


        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("broker已经接受了消息,tag:" + deliveryTag);
            }

            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("broker拒绝了消息,tag:" + deliveryTag);
            }
        });



        Map weatherMap = new HashMap<String, String>();
        weatherMap.put("china.hunan.changsha","2021-4-7 15度");
        weatherMap.put("china.hubei.wuhan","2021-4-8 16度");
        weatherMap.put("us.jianada","2021-4-9 16度");
        weatherMap.put("us.wujiang","2021-4-10 15度");
        weatherMap.put("hubei.wuhan","2021-4-11 16度");
        //1.交换机名 2. 路由key 3. 基础的属性 4.消息实体
        Iterator<Map.Entry<String, String>> iterator = weatherMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            channel.basicPublish(RabbitmqConstant.EXCHANGE_WEATHER_TOPIC, entry.getKey(), null, entry.getValue().getBytes());
        }


        //不能关闭连接,因为需要监听生产者消息是否发送到了broker
//        channel.close();
//
//        connection.close();

    }
}
