package com.zhongjie.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class OrderAckListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("---订单延迟队列开始执行---");

        //获取消息id
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        System.out.println("message:" + new String(message.getBody()));

        System.out.println("业务处理");

        channel.basicAck(deliveryTag, false);
    }
}
