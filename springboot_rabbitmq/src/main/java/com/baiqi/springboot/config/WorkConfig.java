package com.baiqi.springboot.config;



import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkConfig {


    //声明队列
    @Bean
    public Queue workQ1() {
        return new Queue("work_sb_mq_q");
    }

}
