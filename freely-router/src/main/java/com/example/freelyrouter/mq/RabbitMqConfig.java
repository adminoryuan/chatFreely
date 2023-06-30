package com.example.freelyrouter.mq;

import org.freely.commom.contans.RabbitMqConstants;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Configuration
public class RabbitMqConfig {
    @Bean
    public void directExchange() {
        new DirectExchange(RabbitMqConstants.ImClusterExchangeName,true,true);
    }
}
