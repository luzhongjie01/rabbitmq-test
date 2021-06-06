package com.baiqi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 白起老师
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerTest {

     //注入 RabbitTemplate
    @Autowired
     private RabbitTemplate  rabbitTemplate;

     @Test
     public void send(){
         rabbitTemplate.convertAndSend("boot_topic_exchange","boot.haha","boot mq...");
     }
}
