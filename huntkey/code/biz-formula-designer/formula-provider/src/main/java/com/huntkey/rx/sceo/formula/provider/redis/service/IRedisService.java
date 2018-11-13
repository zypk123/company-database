package com.huntkey.rx.sceo.formula.provider.redis.service;

import redis.clients.jedis.Jedis;

/**
 * @author lulx on 2017/6/19 0019.
 */
public interface IRedisService {

    /**
     * getResource
     * @return
     */
    Jedis getResource();

    /**
     * returnResource
     * @param jedis
     */
    void returnResource(Jedis jedis);

    /**
     * set
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * set
     * @param key
     * @param value
     */
    void set(byte[] key, byte[] value);

    /**
     * get
     * @param key
     * @return
     */
    String get(String key);

    /**
     * get
     * @param key
     * @return
     */
    byte[] get(byte[] key);

    /**
     * delete
     * @param key
     * @return
     */
    Long delete(String key);

    /**
     * delete
     * @param key
     * @return
     */
    Long delete(byte[] key);
}
