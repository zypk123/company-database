package com.kafka.config;

/**
 * Created by zhangyu1 on 2017/5/23.
 * kafka的相关配置
 */
public interface ConfigureAPI {

    // zookeeper地址(集群)
    public final static String ZK = "192.168.13.31:2181,192.168.13.32:2181,192.168.13.33:2181";

    // 消费者group的id
    public final static String GROUP_ID = "test";

    // topic
    public final static String TOPIC = "test";

    // kafka地址(集群)
    public final static String BROKER_LIST = "192.168.13.33:6667,192.168.13.34:6667,192.168.13.35:6667";

    //
    public final static int BUFFER_SIZE = 64 * 1024;

    //
    public final static int TIMEOUT = 40000;

    //
    public final static int INTERVAL = 10000;


}
