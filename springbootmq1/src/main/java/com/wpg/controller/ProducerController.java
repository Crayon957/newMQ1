package com.wpg.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;
/**
 * 队列消息控制器
 * */
@RestController
@Api(tags = "生产者发布消息")
public class ProducerController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;
    /**
     * 消息生产者
     * */
    @ApiOperation("Queue模式发布")
    @PostMapping("/sendmsg")
    public void sendmsg(String msg){
        //指定消息发送的目的地，及内容
        this.jmsMessagingTemplate.convertAndSend(this.queue,"queue模式"+msg);
    }

    @ApiOperation("Topic模式下发布")
    @PostMapping("/send")
    public void send(String msg){
        //指定消息发送目的地，及内容
        this.jmsMessagingTemplate.convertAndSend(this.topic,"topic模式"+msg);
    }

}
