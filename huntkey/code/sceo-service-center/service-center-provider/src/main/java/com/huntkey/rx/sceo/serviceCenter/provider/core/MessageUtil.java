package com.huntkey.rx.sceo.serviceCenter.provider.core;

import com.huntkey.rx.sceo.mpf.common.MsgClient;
import com.huntkey.rx.sceo.mpf.common.MsgClientFactory;
import com.huntkey.rx.sceo.mpf.common.MsgConsumerInterface;
import com.huntkey.rx.sceo.mpf.exception.MsgException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MessageUtil {

    private static Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    @Autowired
    private MsgClient msgClient;

    public void sendMessageToKafka(String message) {
        try {
            String topic = "messageData";//随便写一个测试
//            Properties prop = new Properties();
//            MsgClient client = MsgClientFactory.createClient(prop);//建立连接
            msgClient.sendMessage(topic, message);//发送消息
//            client.closeProducer();
        } catch (Exception e) {
            System.out.print("kafka:" + e.getMessage());
        }
    }

    public void consumeMesssage() throws MsgException {
        try {
            Properties prop = new Properties();
            String topic = "messageData";//消费的主题名称
            prop.put("group.id", "myGroup");//组的名称,可随意设置
            MsgClient client = MsgClientFactory.createClient(prop); //建立连接
            client.receiveMessage(topic, new MsgConsumerInterface() {
                @Override
                public void consume(String topic, String message) {// topic主题名称，message消息内容
                    //消息处理模块
                    System.out.println("消费kafka消息：" + message);
                }
            });
        } catch (Exception e) {
            System.out.print("kafka:" + e.getMessage());
        }
    }
}
