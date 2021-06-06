package com.baiqi.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
   路由模式|Routing模式   交换机类型：direct
*/
@Configuration
public class DirectConfig {

	//声明队列
	@Bean
	public Queue directQ1() {
		return new Queue("direct_sb_mq_q1");
	}
	@Bean
	public Queue directQ2() {
		return new Queue("direct_sb_mq_q2");
	}


	//声明exchange
	@Bean
	public DirectExchange setDirectExchange() {
		return new DirectExchange("directExchange");
	}

	//声明binding，需要声明一个routingKey
	@Bean
	public Binding bindDirectBind1() {
		return BindingBuilder.bind(directQ1()).to(setDirectExchange()).with("china.changsha");
	}
	@Bean
	public Binding bindDirectBind2() {
			return BindingBuilder.bind(directQ2()).to(setDirectExchange()).with("china.beijing");
	}

}
