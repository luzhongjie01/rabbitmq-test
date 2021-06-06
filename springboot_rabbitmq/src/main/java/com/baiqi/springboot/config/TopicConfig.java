package com.baiqi.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
Topics模式  交换机类型 topic
* */
@Configuration
public class TopicConfig {

	//声明队列
	@Bean
	public Queue topicQ1() {
		return new Queue("topic_sb_mq_q1");
	}
	@Bean
	public Queue topicQ2() {
		return new Queue("topic_sb_mq_q2");
	}


	//声明exchange
	@Bean
	public TopicExchange setTopicExchange() {
		return new TopicExchange("topicExchange");
	}

	//声明binding，需要声明一个roytingKey
	@Bean
	public Binding bindTopicHebei1() {
		return BindingBuilder.bind(topicQ1()).to(setTopicExchange()).with("changsha.*");
	}
	@Bean
	public Binding bindTopicHebei2() {
		return BindingBuilder.bind(topicQ2()).to(setTopicExchange()).with("#.beijing");
	}

}
