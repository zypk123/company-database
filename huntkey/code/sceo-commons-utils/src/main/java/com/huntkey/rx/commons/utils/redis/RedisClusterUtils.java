package com.huntkey.rx.commons.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.*;

public class RedisClusterUtils {

    private static final Logger logger = LoggerFactory.getLogger(RedisClusterUtils.class);

    /**
     * 连接超时时间
     */
    private final static int DEFAULT_TIMEOUT = 5000;

    /**
     * 出现异常最大重试次数
     */
    private final static int DEFAULT_MAX_REDIRECTIONS = 5;

    /**
     * JedisPool的最大连接数
     */
    private final static int MAX_TOTAL = 20;

    /**
     * JedisPool的最大空闲数
     */
    private final static int MAX_IDLE = 20;

    /**
     * JedisPool的最小空闲数
     */
    private final static int MIN_IDLE = 5;

    /**
     * JedisPool的最大等待时间
     */
    private final static long MAX_WAIT_MILLIS = 6000;

    /**
     * JedisPoolConfig对象
     */
    private static JedisPoolConfig config;

    /**
     * JedisCluster对象
     */
    private static JedisCluster jedisCluster;


    /**
     * 初始化JedisCluster对象
     *
     * @param nodesString
     */
    public RedisClusterUtils(String nodesString) {
        genJedisConfig();
        // 逗号分隔集群的redis的IP
        String[] serverArray = nodesString.split(",");
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        RedisClusterUtils.jedisCluster = new JedisCluster(nodes, DEFAULT_TIMEOUT, DEFAULT_MAX_REDIRECTIONS, config);
    }

    /**
     * 配置JedisPoolConfig相关参数
     */
    private void genJedisConfig() {
        config = new JedisPoolConfig();
        config.setMaxTotal(MAX_TOTAL);
        config.setMaxIdle(MAX_IDLE);
        config.setMinIdle(MIN_IDLE);
        config.setMaxWaitMillis(MAX_WAIT_MILLIS);
        config.setTestOnBorrow(true);
    }

    /**
     * 关闭连接
     */
    public static void clusterClose() {
        try {
            if (jedisCluster != null) {
                jedisCluster.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute clusterClose function:", e);
            throw new RuntimeException("some error occur when execute clusterClose function:", e);
        }
    }

    /**
     * 设置数据 String类型
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setValue(String key, String value) {
        boolean OK = false;
        try {
            String ret = jedisCluster.set(key, value);
            OK = "OK".equals(ret);
        } catch (Exception e) {
            OK = false;
            e.printStackTrace();
            logger.error("some error occur when execute setValue function:", e);
            throw new RuntimeException("some error occur when execute setValue function:", e);
        }
        return OK;
    }

    /**
     * 设置数据 byte[]类型
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setByteValue(byte[] key, byte[] value) {
        boolean OK = false;
        try {
            String ret = jedisCluster.set(key, value);
            OK = "OK".equals(ret);
        } catch (Exception e) {
            OK = false;
            e.printStackTrace();
            logger.error("some error occur when execute setByteValue function:", e);
            throw new RuntimeException("some error occur when execute setByteValue function:", e);
        }
        return OK;
    }

    /**
     * 获取数据 String类型
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        String value = null;
        try {
            value = jedisCluster.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute getValue function:", e);
            throw new RuntimeException("some error occur when execute getValue function:", e);
        }
        return value;
    }

    /**
     * 获取数据 byte[]数组
     *
     * @param key
     * @return
     */
    public byte[] getByteValue(byte[] key) {
        byte[] value = null;
        try {
            value = jedisCluster.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute getByteValue function:", e);
            throw new RuntimeException("some error occur when execute getByteValue function:", e);
        }
        return value;
    }

    /**
     * 设置数据，并设置过期时间
     *
     * @param key
     * @param value
     * @param expireSec
     * @return
     */
    public boolean setValue(String key, String value, int expireSec) {
        boolean OK = false;
        try {
            String ret = jedisCluster.setex(key, expireSec, value);
            OK = "OK".equals(ret);
        } catch (Exception e) {
            OK = false;
            e.printStackTrace();
            logger.error("some error occur when execute setValue function:", e);
            throw new RuntimeException("some error occur when execute setValue function:", e);
        }
        return OK;
    }

    /**
     * 设置数据，并设置过期时间
     *
     * @param key
     * @param value
     * @param expireSec
     * @return
     */
    public boolean setValue(String key, byte[] value, int expireSec) {
        boolean OK = false;
        try {
            String ret = jedisCluster.setex(key, expireSec, value.toString());
            OK = "OK".equals(ret);
        } catch (Exception e) {
            OK = false;
            e.printStackTrace();
            logger.error("some error occur when execute setValue function:", e);
            throw new RuntimeException("some error occur when execute setValue function:", e);
        }
        return OK;
    }

    /**
     * 设置数据，并设置过期时间
     *
     * @param key
     * @param value
     * @param expireSec
     * @return
     */
    public boolean setValue(byte[] key, byte[] value, int expireSec) {
        boolean OK = false;
        try {
            String ret = jedisCluster.setex(key, expireSec, value);
            OK = "OK".equals(ret);
        } catch (Exception e) {
            OK = false;
            e.printStackTrace();
            logger.error("some error occur when execute setValue function:", e);
            throw new RuntimeException("some error occur when execute setValue function:", e);
        }
        return OK;
    }

    /**
     * 向set集合中插入String类型的数据
     *
     * @param key
     * @param values
     * @return
     */
    public long add2set(String key, String... values) {
        long ret = 0L;
        try {
            ret = jedisCluster.sadd(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute add2set function:", e);
            throw new RuntimeException("some error occur when execute add2set function:", e);
        }
        return ret;
    }

    /**
     * 向set集合中插入byte[]类型的数据
     *
     * @param key
     * @param values
     * @return
     */
    public long add2set(String key, byte[]... values) {
        long ret = 0L;
        try {
            ret = jedisCluster.sadd(key.getBytes(), values);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute add2set function:", e);
            throw new RuntimeException("some error occur when execute add2set function:", e);
        }
        return ret;
    }

    /**
     * 判断 member 元素是否是集合 key 的成员
     *
     * @param key
     * @param member
     * @return
     */
    public boolean sismember(String key, String member) {
        boolean b = false;
        try {
            b = jedisCluster.sismember(key, member);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute sismember function:", e);
            throw new RuntimeException("some error occur when execute sismember function:", e);
        }
        return b;
    }

    /**
     * 移除集合中一个或多个成员
     *
     * @param key
     * @param member
     */
    public void srem(String key, String... member) {
        try {
            jedisCluster.srem(key, member);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute srem function:", e);
            throw new RuntimeException("some error occur when execute srem function:", e);
        }
    }

    /**
     * 返回set集合中的所有元素
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key) {
        Set<String> ret = null;
        try {
            ret = jedisCluster.smembers(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute smembers function:", e);
            throw new RuntimeException("some error occur when execute smembers function:", e);
        }
        return ret;
    }

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
     * 当 key 存在但不是字符串类型时，返回一个错误
     *
     * @param key
     * @param value
     * @return
     */
    public String getSet(String key, String value) {
        String str = null;
        try {
            str = jedisCluster.getSet(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute getSet function:", e);
            throw new RuntimeException("some error occur when execute getSet function:", e);
        }
        return str;
    }

    /**
     * 返回set集合中的二进制成员
     *
     * @param key
     * @return
     */
    public Set<byte[]> getBinarySet(String key) {
        Set<byte[]> ret = null;
        try {
            ret = jedisCluster.smembers(key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute getBinarySet function:", e);
            throw new RuntimeException("some error occur when execute getBinarySet function:", e);
        }
        return ret;
    }

    /**
     * 重命名key
     *
     * @param oldKey
     * @param newKey
     * @return
     */
    public String renameKey(String oldKey, String newKey) {
        String ret = "";
        try {
            jedisCluster.set(newKey, jedisCluster.get(oldKey));
            jedisCluster.del(oldKey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute renameKey function:", e);
            throw new RuntimeException("some error occur when execute renameKey function:", e);
        }
        return ret;
    }

    /**
     * 获取所有满足pattern的key
     *
     * @param pattern
     * @return
     */
    public Set<String> getKeys(String pattern) {
        TreeSet<String> keys = null;
        try {
            // 随机选取其中一个pool
            keys = new TreeSet<String>();
            Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
            for (String k : clusterNodes.keySet()) {
                JedisPool jp = clusterNodes.get(k);
                Jedis connection = jp.getResource();
                try {
                    keys.addAll(connection.keys(pattern));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    connection.close(); // 用完一定要close这个链接！！！
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute getKeys function:", e);
            throw new RuntimeException("some error occur when execute getKeys function:", e);
        }
        return keys;
    }

    /**
     * 删除key
     *
     * @param key
     * @return
     */
    public boolean delKey(String key) {
        boolean OK = false;
        try {
            long ret = jedisCluster.del(key);
            OK = (ret == 1);
        } catch (Exception e) {
            OK = false;
            e.printStackTrace();
            logger.error("some error occur when execute delKey function:", e);
            throw new RuntimeException("some error occur when execute delKey function:", e);
        }
        return OK;
    }

    /**
     * 设置计数器
     *
     * @param topic
     * @param key
     * @param value
     * @return
     */
    public String setCounter(final String topic, final String key, final long value) {
        String ret = null;
        try {
            ret = jedisCluster.set(topic + "_" + key, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute setCounter function:", e);
            throw new RuntimeException("some error occur when execute setCounter function:", e);
        }
        return ret;
    }

    /**
     * 计数器加1
     *
     * @param topic
     * @param key
     * @return
     */
    public long incrCounter(final String topic, final String key) {
        long ret = 0;
        try {
            ret = jedisCluster.incr(topic + "_" + key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute incrCounter function:", e);
            throw new RuntimeException("some error occur when execute incrCounter function:", e);
        }
        return ret;
    }

    /**
     * 计数器加num
     *
     * @param topic
     * @param key
     * @param num
     * @return
     */
    public long incrCounter(final String topic, final String key, long num) {
        long ret = 0;
        try {
            ret = jedisCluster.incrBy(topic + "_" + key, num);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute incrCounter function:", e);
            throw new RuntimeException("some error occur when execute incrCounter function:", e);
        }
        return ret;
    }

    /**
     * 删除计数器
     *
     * @param topic
     * @param keys
     */
    public void resetCounter(final String topic, String... keys) {
        try {
            for (String key : keys) {
                // 删除
                jedisCluster.del(topic + "_" + key);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute resetCounter function:", e);
            throw new RuntimeException("some error occur when execute resetCounter function:", e);
        }
    }

    /**
     * 删除计数器
     *
     * @param keyPrefix
     */
    public void resetCounter(String keyPrefix) {
        try {
            Set<String> keys = jedisCluster.hkeys(keyPrefix);
            for (String key : keys) {
                // 删除
                jedisCluster.del(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute resetCounter function:", e);
            throw new RuntimeException("some error occur when execute resetCounter function:", e);
        }
    }

    /**
     * 删除key (key是String)
     *
     * @param keys
     */
    public void delete(String... keys) {
        try {
            for (String key : keys) {
                // 删除
                jedisCluster.del(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute delete function:", e);
            throw new RuntimeException("some error occur when execute delete function:", e);
        }
    }


    /**
     * 删除key (key是byte[])
     *
     * @param keys
     */
    public void delete(byte[]... keys) {
        try {
            for (byte[] key : keys) {
                // 删除
                jedisCluster.del(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute delete function:", e);
            throw new RuntimeException("some error occur when execute delete function:", e);
        }
    }

    /**
     * 将 key 的值设为 value ，当且仅当 key 不存在
     * 若给定的 key 已经存在，则不做任何动作。
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setValueIfNotExist(final String key, final String value) {
        // not set
        boolean ret = false;
        try {
            // 不存在key时才赋值，否则不覆盖
            long flag = jedisCluster.setnx(key, value);
            ret = (flag == 0) ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute setValueIfNotExist function:", e);
            throw new RuntimeException("some error occur when execute setValueIfNotExist function:", e);
        }
        return ret;
    }

    /**
     * 设置hashmap
     *
     * @param key
     * @param map
     */
    public void setHashMap(String key, Map<String, String> map) {
        try {
            for (String name : map.keySet()) {
                jedisCluster.hset(key, name, String.valueOf(map.get(name)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute setHashMap function:", e);
            throw new RuntimeException("some error occur when execute setHashMap function:", e);
        }
    }

    /**
     * 获得保存的hashmap
     *
     * @param key
     * @return
     */
    public Map<String, String> getHashMap(String key) {
        Map<String, String> map = null;
        try {
            map = jedisCluster.hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute getHashMap function:", e);
            throw new RuntimeException("some error occur when execute getHashMap function:", e);
        }
        return map;
    }

    /**
     * 从头部插入一个value元素到key as list
     *
     * @param key
     * @param value
     */
    public void pushList(String key, String value) {
        try {
            jedisCluster.lpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute pushList function:", e);
            throw new RuntimeException("some error occur when execute pushList function:", e);
        }
    }

    /**
     * 移除并获取list中最后一个元素
     *
     * @param key
     * @return
     */
    public String rpop(String key) {
        String ret = null;
        try {
            ret = jedisCluster.rpop(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute rpop function:", e);
            throw new RuntimeException("some error occur when execute rpop function:", e);
        }
        return ret;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        try {
            jedisCluster.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute expire function:", e);
            throw new RuntimeException("some error occur when execute expire function:", e);
        }
    }

    /**
     * 保留list中前n个数据，其他的移除
     *
     * @param key
     * @param size
     */
    public void trimList(String key, int size) {
        try {
            jedisCluster.ltrim(key, 0, size - 1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute trimList function:", e);
            throw new RuntimeException("some error occur when execute trimList function:", e);
        }
    }

    /**
     * 获得List中指定元素列表
     *
     * @param key
     * @param start 从0开始
     * @param end   -1为结尾
     * @return
     */
    public List<String> rangeList(String key, int start, int end) {
        List<String> ret = null;
        try {
            ret = jedisCluster.lrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute rangeList function:", e);
            throw new RuntimeException("some error occur when execute rangeList function:", e);
        }
        return ret;
    }

    /**
     * 移除list中所有与value相等的值
     *
     * @param key
     * @param value
     * @return
     */
    public long removeInList(String key, String value) {
        long ret = 0;
        try {
            ret = jedisCluster.lrem(key, 0, value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute removeInList function:", e);
            throw new RuntimeException("some error occur when execute removeInList function:", e);
        }
        return ret;
    }

    /**
     * 发布消息
     *
     * @param channel
     * @param message
     */
    public void publish(String channel, String message) {
        try {
            // 发布
            jedisCluster.publish(channel, message);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute publish function:", e);
            throw new RuntimeException("some error occur when execute publish function:", e);
        }
    }

    /**
     * 订阅消息
     *
     * @param channel
     */
    public void subscribe(String channel) {
        try {
            JedisPubSubListener pubsub = new JedisPubSubListener();
            // 发布
            jedisCluster.subscribe(pubsub, channel);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute subscribe function:", e);
            throw new RuntimeException("some error occur when execute subscribe function:", e);
        }
    }

    public static void main(String[] args) {
        String nodesString = "10.3.98.153:7000,10.3.98.153:7001,10.3.98.154:7002,10.3.98.154:7003,10.3.98.155:7004,10.3.98.155:7005";
        System.out.println("nodesString:" + nodesString);
        long time1 = System.currentTimeMillis();
        RedisClusterUtils rcu = new RedisClusterUtils(nodesString);
        System.out.println("======" + rcu.toString());
        rcu.setValue("li", "chunjie");
        System.out.println(rcu.setValueIfNotExist("li2", "chunjie123"));
        System.out.println(rcu.getValue("li"));
//        rcu.publish("test", "123456");
//        rcu.setValue("li", "chunjie");
//        System.out.println(rcu.getValue("li"));
//        long time2 = System.currentTimeMillis();
//        System.out.println("耗时：" + (time2 - time1) + "毫秒");

//        rcu.publish("test", "123456");
//        rcu.setValue("li", "chunjie");
//        String value = rcu.getValue("staff_shortname");
//        System.out.println(value);
//        setCountertem.out.println(rcu.getSet("testset"));
//        rcu.setCounter("testCounter", "test1", 10);
//        rcu.incrCounter("testCounter", "test1", 15);
//        System.out.println(rcu.getValue("testCounter_test1"));
//        HashMap<String, Integer> map = new HashMap<String, Integer>();
//        map.put("name1", 1);
//        rcu.setHashMap("mapTest", map);
//        System.out.println(rcu.getHashMap("mapTest"));
    }
}
