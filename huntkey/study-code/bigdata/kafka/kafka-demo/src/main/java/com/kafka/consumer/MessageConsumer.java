package com.kafka.consumer;

import com.kafka.config.ConfigureAPI;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by zhangyu1 on 2017/5/23.
 * 消费者 Consumer
 */
public class MessageConsumer extends Thread {
    private KafkaConsumer consumer; // 创建consumer对象
    private String topic;

    public MessageConsumer(String topic) {
        consumer = this.consumerConfig();
        this.topic = topic;
    }

    private KafkaConsumer consumerConfig() {
        Properties props = new Properties();

        /**
         * 用于客户端向服务器建立初始连接的kafka broker ip及端口集,与producer中配置相同。
         */
        props.put("bootstrap.servers", "192.168.13.33:6667");

        /**
         * 实现了接口Deserializer 的类，用于反序列化key.与producer中的key.serialzer对应。
         */
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        /**
         * 实现了接口Deserializer 的类，用于反序列化value.与producer中的value.serialzer对应。
         */
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        /**
         *
         * 偏移量是由consumer维护，并不代表是存储在本客户端（客户端无处可存，且对于多个group消费者无法实现互斥消费），实际上是存储在zookeeper。
         * 因此offset可以由服务器代为维护
         */
        props.put("enable.auto.commit", "false");

        /**
         * session超时时间
         */
        props.put("session.timeout.ms", "30000");

        /**
         * 配置消费者group的id
         */
        props.put("group.id", ConfigureAPI.GROUP_ID);

        return new KafkaConsumer(props);
    }

    @Override
    public void run() {
        consumer.subscribe(Arrays.asList(topic)); // 订阅TOPIC
        try {
            while (true) {
                // 轮询,当调用poll(long）时，消费者将自动加入到组中。只要持续的调用poll，消费者将一直保持可用，并继续从分配的分区中接收消息
                ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
                for (TopicPartition partition : records.partitions()) {
                    List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                    for (ConsumerRecord<String, String> record : partitionRecords) {
                        //可以自定义Handler,处理对应的TOPIC消息(partitionRecords.key())
                        System.out.println("Receive->[" + record.value() + "]");
                    }
                    consumer.commitSync(); // 同步
                }
            }
        } finally {
            consumer.close();
        }
    }

}
