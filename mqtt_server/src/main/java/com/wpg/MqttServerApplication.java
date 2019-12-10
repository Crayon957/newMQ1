package com.wpg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqttServerApplication {
    private static Logger logger= LoggerFactory.getLogger(Server.class);
    public static void main(String[] args) {

        SpringApplication.run(MqttServerApplication.class, args);
        logger.info("Msmq消息服务启动成功");
    }

}
