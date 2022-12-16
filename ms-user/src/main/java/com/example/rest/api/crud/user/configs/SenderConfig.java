package com.example.rest.api.crud.user.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SenderConfig {
    @Value("${spring.queue.name}")
    private String userqueue;

    @Bean
    public Queue queue() {
        return new Queue(userqueue, true);
    }
}
