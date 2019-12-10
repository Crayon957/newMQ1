package com.wpg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqttApplication {
    private static Logger logger= LoggerFactory.getLogger(MqttApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MqttApplication.class, args);
        logger.info("Msmq消息服务启动成功");
    }

}
