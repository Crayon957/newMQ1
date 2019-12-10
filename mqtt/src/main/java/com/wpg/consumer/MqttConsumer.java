package com.wpg.consumer;

import com.wpg.config.MqttConfigBean;
import com.wpg.producer.TopMsgCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class MqttConsumer implements ApplicationRunner {

    private static Logger logger = getLogger(MqttConsumer.class);

    private static MqttClient client;

    private static MqttTopic mqttTopic;

    /**
     * MQTT连接属性配置对象
     */
    @Autowired
    public MqttConfigBean mqttConfigBean;

    /**
     * 初始化参数配置
     * */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("初始化启动MQTT连接");
        this.connect();
    }

    /**
     * 用来连接服务器
     * */
    private void connect() throws  Exception{
        client = new MqttClient(mqttConfigBean.getHostUrl(),mqttConfigBean.getClientId(),new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(mqttConfigBean.getUsername());
        options.setPassword(mqttConfigBean.getPassword().toCharArray());
        options.setCleanSession(false);//是否清除session
        //设置超时时间
        options.setConnectionTimeout(30);
        //设置会话心跳时间
        options.setKeepAliveInterval(20);
        try{
            String[] msgtopic = mqttConfigBean.getMsgTopic();
            //订阅消息
            int[] qos = new int[msgtopic.length];
            for (int i = 0; i < msgtopic.length; i++) {
                qos[i] = 0;
            }
            client.setCallback(new TopMsgCallback(client,options,msgtopic,qos));
            client.connect(options);
            client.subscribe(msgtopic,qos);
            logger.info("MQTT连接成功："+mqttConfigBean.getClientId()+":"+client);
        }catch (Exception e){
            logger.error("MQTT连接异常："+e);
        }
    }

    /**
     * 订阅某个主题
     * @Param topic
     * @Param qos
     * */
    public void subscribe(String topic,int qos){
        try {
            logger.info("topic:"+topic);
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public MqttClient getClient() {
        return client;
    }

    public void setClient(MqttClient client) {
        this.client = client;
    }

    public MqttTopic getMqttTopic() {
        return mqttTopic;
    }

    public void setMqttTopic(MqttTopic mqttTopic) {
        this.mqttTopic = mqttTopic;
    }

}
