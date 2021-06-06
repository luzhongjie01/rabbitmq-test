package com.zhongjie;

import com.zhongjie.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public  class ProductTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void send() {
        rabbitTemplate.convertAndSend(RabbitmqConfig.springboot_exchange,"boot.bootqueue", "我来了宝贝");
    }

}
