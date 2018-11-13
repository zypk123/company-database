package com.huntkey.rx.modeler.provider.point;

import com.alibaba.fastjson.JSON;
import com.huntkey.rx.modeler.provider.annotation.RedisCache;
import com.huntkey.rx.modeler.provider.annotation.RedisEvict;
import com.huntkey.rx.modeler.provider.util.RedisCacheUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by yexin on 2017/10/14.
 */
//@Component
//@Aspect
public class RedisCacheAspect {

    private static Logger log = LoggerFactory.getLogger(RedisCacheAspect.class);
    private static final String DELIMITER = "_";
//    @Autowired
//    StringRedisTemplate rt;

    @Autowired
    RedisCacheUtil redisCacheUtil;

    /**
     * 方法调用前，先查询缓存。如果存在缓存，则返回缓存数据，阻止方法调用;
     * 如果没有缓存，则调用业务方法，然后将结果放到缓存中
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.huntkey.rx.modeler.provider.dao.*.*(..))")
    public Object cache(ProceedingJoinPoint jp) throws Throwable {

        // 得到类名、方法名和参数
        String clazzName = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        Object result = null;

        // 得到被代理的方法
        Method me = ((MethodSignature) jp.getSignature()).getMethod();
        Type type = me.getGenericReturnType();
        //RedisCache 注解方式
        if(me.isAnnotationPresent(RedisCache.class)){

            // 根据类名，方法名和参数生成key
            String key = genKey(clazzName, methodName, args);
            if (log.isDebugEnabled()) {
                log.debug("生成key:{}", key);
            }
            // 得到被代理的方法上的注解
            Class modelType = me.getAnnotation(RedisCache.class).type();

            // 检查redis中是否有缓存
            String value = (String)redisCacheUtil.getHashValue(modelType.getName(), key);

            // result是方法的最终返回结果

            if (null == value) {
                // 缓存未命中
                if (log.isDebugEnabled()) {
                    log.debug("缓存未命中");
                }
                // 调用数据库查询方法
                result = jp.proceed(args);
            // 序列化查询结果
                String json = serialize(result);
                System.out.println("json value: ===:"+json);
                // 序列化结果放入缓存
                redisCacheUtil.set(modelType.getName(), key, json);
            } else {
                // 缓存命中
                if (log.isDebugEnabled()) {
                    log.debug("缓存命中, value = {}", value);
                }
                // 得到被代理方法的返回值类型
                Class returnType = ((MethodSignature) jp.getSignature()).getReturnType();
                // 反序列化从缓存中拿到的json
                result = deserialize(value, returnType, type);
                if (log.isDebugEnabled()) {
                    log.debug("反序列化结果 = {}", result);
                }
            }
         //   RedisEvict 注解分支
        }else if(me.isAnnotationPresent(RedisEvict.class)){

            // 得到被代理的方法上的注解
            Class modelType = me.getAnnotation(RedisEvict.class).type();

            if (log.isDebugEnabled()) {
                log.debug("清空缓存:{}", modelType.getName());
            }

            // 清除对应缓存
            redisCacheUtil.delete(modelType.getName());

            result = jp.proceed(args);
        }else {
            result = jp.proceed(args);
        }

        return result;

    }

    /**
     * 根据类名、方法名和参数生成key
     * @param clazzName
     * @param methodName
     * @param args 方法参数
     * @return
     */
    protected String genKey(String clazzName, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder(clazzName);
        sb.append(DELIMITER);
        sb.append(methodName);
        sb.append(DELIMITER);

        for (Object obj : args) {
            if(obj != null){
                sb.append(obj.toString());
            }else{
                sb.append("null");
            }

            sb.append(DELIMITER);
        }
        return sb.toString();
    }

    private String serialize(Object target) {
        return JSON.toJSONString(target);
    }
//
    protected Object deserialize(String jsonString, Class clazz, Type type) {
        // 序列化结果应该是List对象
        if (clazz.isAssignableFrom(List.class)) {
            Type argType =  ((ParameterizedType)type).getActualTypeArguments()[0];
            Class cls = (Class) argType;
            return JSON.parseArray(jsonString, cls);
        }

        // 序列化结果是普通对象
        return JSON.parseObject(jsonString, clazz);
    }
}
