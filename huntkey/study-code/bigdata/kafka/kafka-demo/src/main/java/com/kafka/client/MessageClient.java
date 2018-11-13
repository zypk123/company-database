package com.kafka.client;

import com.kafka.config.ConfigureAPI;
import com.kafka.consumer.MessageConsumer;
import com.kafka.producer.MessageProducer;

/**
 * Created by zhangyu1 on 2017/5/23.
 * 客户端 client
 */
public class MessageClient {

    public static void main(String[] args) {
        MessageProducer producer = new MessageProducer(ConfigureAPI.TOPIC);
        producer.start(); // 启动生产者

        MessageConsumer consumer = new MessageConsumer(ConfigureAPI.TOPIC);
        consumer.start(); // 启动消费者
    }
}
