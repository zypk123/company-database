package com.ka.producer;

import com.ka.serializer.AvroSerializer;
import com.ka.topic.Topic;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Created by zhangyu1 on 2017/5/27.
 */
public class Producer<T extends SpecificRecordBase> {

    private KafkaProducer<String, T> producer = new KafkaProducer<>(getProperties());

    public void sendData(Topic topic, T data) {
        producer.send(new ProducerRecord<>(topic.topicName, data),
                (metadata, exception) -> {
                    if (exception == null) {
                        System.out.printf("Sent user: %s \n", data);
                    } else {
                        System.out.println("data sent failed: " + exception.getMessage());
                    }
                });
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.13.33:6667");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "test");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                AvroSerializer.class.getName());
        return props;
    }
}
