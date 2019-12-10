package com.wpg.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户控制器
 * */

@RestController
public class ConsumerController {
    /**
     *监听和读取消息
     */

    @JmsListener(destination = "active.queue")
    public void readActiveQueue(String text){
        System.out.println("queue模式接受消息："+text);
    }
    @JmsListener(destination = "active.topic")
    public void readActiveTopic(String text){
        System.out.println("topic模式接受消息："+text);
    }
}
