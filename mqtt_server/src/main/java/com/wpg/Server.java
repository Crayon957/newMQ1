package com.wpg;

import com.wpg.beans.MqttConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;
@Component
public class Server implements ApplicationRunner {

    private static Logger logger = getLogger(Server.class);

    private MqttClient client;

    private MqttTopic topic;

    private MqttMessage message;

    @Autowired
    public MqttConfig mqttConfig;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("初始化启动");
        connect();
    }
    /**
     * 连接服务器
     * */
    void connect() throws Exception{
        //配置服务器

        client = new MqttClient(mqttConfig.getHostUrl(),mqttConfig.getClientId(),new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(mqttConfig.getUsername());
        options.setPassword(mqttConfig.getPassword().toCharArray());
        //设置超时时间
        options.setConnectionTimeout(60);
        //设置会话心跳时间
        options.setKeepAliveInterval(20);

        try {
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println(1);
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    System.out.println(2);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println(3);
                }
            });
            client.connect(options);
            topic = client.getTopic(mqttConfig.getMsgTopic()[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
        message = new MqttMessage();
        message.setQos(0);
        message.setRetained(true);
        message.setPayload("123456".getBytes());
        topic.publish(message);
    }

}
