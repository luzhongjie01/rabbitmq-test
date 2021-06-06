package com.zhongjie.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RabbitmqListener {

    @RabbitListener(queues = "springboot_queue")
    public void bootQueue(Message message){
        System.out.println(new String(message.getBody()));
    }


}
