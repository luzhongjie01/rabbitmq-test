package com.zhongjie.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class PrefetchAckListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("---ack监听器开始了---");

        //获取消息id
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            System.out.println("message:" + new String(message.getBody()));

            System.out.println("业务处理");

            channel.basicAck(deliveryTag, false);

        } catch (Exception e) {
            //有异常,拒绝签收消息
            //括号第三个参数(true表示消息重新放回队列)(false丢弃消息)
            channel.basicNack(deliveryTag,true,false);
        }

    }
}
