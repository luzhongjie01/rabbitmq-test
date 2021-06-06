package com.zhongjie.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    public static final String springboot_exchange = "springboot_exchange";

    public static final String springboot_queue = "springboot_queue";

    @Bean("bootExchange")
    public Exchange bootExchange(){
        return ExchangeBuilder.topicExchange(springboot_exchange).durable(true).build();
    }

    @Bean("bootQueue")
    public Queue bootQueue(){
        return QueueBuilder.durable(springboot_queue).build();
    }

    @Bean
    public Binding bindQueueExchange(@Qualifier("bootQueue")Queue queue,@Qualifier("bootExchange")Exchange exchange){
       return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }
}
