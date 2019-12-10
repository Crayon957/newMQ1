package com.wpg.conf;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class Config {
    @Bean
    public Queue getQueue(){
        return new ActiveMQQueue("active.queue");
    }
    @Bean
    public Topic getTopic(){
        return new ActiveMQTopic("active.topic");
    }
}
