package com.huntkey.rx.sceo.serviceCenter.provider.orm.config;

import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.HbaseUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Map;

/**
 * @author yangcong
 * @create 2017-05-23
 * @description 返回ElasticSearch客户端对象TransportClient
 **/
@Component
@Configuration
@ConfigurationProperties(prefix = "es_hbase")
public class EsAndHbaseConfig {
    private String clusterName;                 //ElasticSearch集群名称
    private Map<String, String> clusterNodes;    //ElasticSearch集群ip:port  eg: 192.168.24.110:9300

    private String clientPort;                  //hbase客户端连接端口
    private String quorum;                       //zookpper集群
    private String znodeParent;
    private String writeBuffer;                 //hbase批量写缓存

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Map<String, String> getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(Map<String, String> clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    public void setQuorum(String quorum) {
        this.quorum = quorum;
    }

    public void setZnodeParent(String znodeParent) {
        this.znodeParent = znodeParent;
    }

    public void setWriteBuffer(String writeBuffer) {
        this.writeBuffer = writeBuffer;
    }

    /*
     * 返回TransportClient对象，用来操作ElasticSearch
     * @return  TransportClient
    */
    @Bean
    public TransportClient getTransportClient() {
        TransportClient client = null;
        try {
            //设置集群名称
            Settings settings = Settings.builder().put("cluster.name", this.clusterName).build();
            //创建client
            client = new PreBuiltTransportClient(settings);

            //将集群的ip和port初始化到client中
            for (Map.Entry<String, String> s : clusterNodes.entrySet()) {
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(s.getKey()), Integer.valueOf(s.getValue())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return client;
    }

    /*
     * 返回HbaseUtil对象，用来操作Hbase
     * @return  HbaseUtil
    */
    @Bean
    public HbaseUtil getHbaseConnection() throws Exception {
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.property.clientPort", clientPort);
        conf.set("hbase.zookeeper.quorum", quorum);
        conf.set("zookeeper.znode.parent", znodeParent);
        conf.set("hbase.client.write.buffer", writeBuffer);
        Connection conn = ConnectionFactory.createConnection(conf);
        HbaseUtil hbaseUtil = new HbaseUtil(conn);
        return hbaseUtil;
    }
}