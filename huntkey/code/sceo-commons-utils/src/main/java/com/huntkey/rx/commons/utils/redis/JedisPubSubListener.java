package com.huntkey.rx.commons.utils.redis;

import redis.clients.jedis.JedisPubSub;

/**
 * 对订阅到消息后的处理
 *
 * @author zhangyu
 * @create 2017-10-09 15:39
 **/
public class JedisPubSubListener extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        // 取得订阅的消息后的处理
        System.out.println(channel + " " + message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        // 取得按表达式的方式订阅的消息后的处理
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        // 初始化按表达式的方式订阅时候的处理
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        // 取消按表达式的方式订阅时候的处理
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        // 初始化订阅时候的处理
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        // 取消订阅时候的处理
    }

}