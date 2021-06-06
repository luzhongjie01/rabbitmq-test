package com.zhongjie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        //初始化IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-rabbitmq-consumer.xml");
    }
}
