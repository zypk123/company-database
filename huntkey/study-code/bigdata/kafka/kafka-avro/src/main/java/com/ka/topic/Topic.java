package com.ka.topic;

import com.ka.entity.User;
import org.apache.avro.specific.SpecificRecordBase;

import java.util.EnumSet;


/**
 * Created by zhangyu1 on 2017/5/27.
 */
public enum Topic {
    USER("test", new User());

    public final String topicName;
    public final SpecificRecordBase topicType;

    Topic(String topicName, SpecificRecordBase topicType) {
        this.topicName = topicName;
        this.topicType = topicType;
    }

    public static Topic matchFor(String topicName) {
        return EnumSet.allOf(Topic.class).stream()
                .filter(topic -> topic.topicName.equals(topicName))
                .findFirst()
                .orElse(null);
    }
}