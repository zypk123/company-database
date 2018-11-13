package com.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by zhangyu1 on 2017/5/23.
 * 生产者  Producer
 */
public class MessageProducer extends Thread {

    private Producer<Integer, String> producer; // 创建producer对象
    private String topic;
    private Properties props = new Properties();
    private final int SLEEP = 1000 * 3;

    public MessageProducer(String topic) {


        /**
         * 一个主机/端口对的集合列表。目的是为了初始与kafka集群建立连接。注意：客户端不需要把的集群中所有的主机都配置在这里。
         * 此处的目的仅仅时初始的时候建立与集群的连接，一旦建立，客户端可以使用使用集群中的任何一台机器
         *
         */
        props.put("bootstrap.servers", "192.168.13.33:6667");

        /**
         * 实现了Serializer接口的序列化类。用于告诉kafka如何序列化key.
         *
         */
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        /**
         * 实现了Serializer接口的序列化类。用于告诉kafka如何序列化value
         *
         */
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        /**
         * leader收到的来自所有follower复制成功的确认数量(比如：有10个follower，只要2个确认成功就认为整个发送成功[还有一个是leader自己]，那么acks=3)。只有达到这个数量，生产者才认为消息成功发送。
         * acks=all 这表示leader会等待所有“in-sync”台follower的复制确认。只要一台“in-sync” follower处于激活状态，数据就保证不会丢失。 这是最强的可用性保证
         */
        props.put("acks", "all");

        /**
         * 生产者可以用来缓冲消息记录的总内存大小
         */
        props.put("buffer.memory", 33554432);

        /**
         * 如果此值大于0,那么客户端在接收到事务error时，会重新发送
         */
        props.put("retries ", 1);

        // 泛型参数分别表示，第一个是Partition的类型, 第二个是message的类型
        // Partition中一般存的是消息的位置，即为offset的值，一般为Integer类型
        producer = new KafkaProducer<Integer, String>(props);

        this.topic = topic;
    }

    @Override
    public void run() {
        int offsetNo = 1;
        while (true) {
            String msg = "Message_" + offsetNo;
            System.out.println("Send->[" + msg + "]");
            producer.send(new ProducerRecord<Integer, String>(topic, msg)); // 发送消息
            offsetNo++;
            try {
                sleep(SLEEP);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
