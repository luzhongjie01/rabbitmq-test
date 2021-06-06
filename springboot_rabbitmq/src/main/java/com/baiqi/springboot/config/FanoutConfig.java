package com.baiqi.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fanout模式需要声明exchange，并绑定queue，由exchange负责转发到queue上。
 * 广播模式 交换机类型设置为：fanout
 */
@Configuration
public class FanoutConfig {

	//声明队列
	@Bean
	public Queue fanoutQ1() {
		return new Queue("fanout.q1");
	}
	@Bean
	public Queue fanoutQ2() {
		return new Queue("fanout.q2");
	}


	//声明exchange
	@Bean
	public FanoutExchange setFanoutExchange() {
		return new FanoutExchange("fanoutExchange");
	}


	//声明Binding,exchange与queue的绑定关系
	@Bean
	public Binding bindQ1() {
		return BindingBuilder.bind(fanoutQ1()).to(setFanoutExchange());
	}
	@Bean
	public Binding bindQ2() {
		return BindingBuilder.bind(fanoutQ2()).to(setFanoutExchange());
	}

}
