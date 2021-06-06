package com.baiqi.springboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HelloWorld rabbitmq课上讲解的第一个工作模式
 * 直连模式只需要声明队列，所有消息都通过队列转发。
 * 无需设置交换机
 */
@Configuration
public class HelloWorldConfig {

	@Bean
	public Queue setQueue() {
		return new Queue("helloWorldqueue");
	}
}
