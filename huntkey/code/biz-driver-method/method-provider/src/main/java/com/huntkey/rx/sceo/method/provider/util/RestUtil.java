package com.huntkey.rx.sceo.method.provider.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huntkey.rx.commons.utils.rest.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lulx on 2017/10/14 0014 上午 10:37
 */
@Service
@Lazy(false)
public class RestUtil {
    private static Logger logger = LoggerFactory.getLogger(RestUtil.class);

    @Autowired
    RestTemplate restTemplate;

    private static RestTemplate templateHolder;

    @PostConstruct
    public void init() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(20000);
        //requestFactory.setConnectionRequestTimeout(50000);

        restTemplate.setRequestFactory(requestFactory);
        templateHolder = restTemplate;
    }

    /**
     * Send Get request
     *
     * @param url like http://HELLO-SERVICE/hello, HELLO-SERVICE can be discovered in Eureka.
     *            http://driver-service-demo/index/get?params={params}
     * @param params
     * @return
     */
    public static Result doGet(String url, String params) {

        Map<String, Object> map = new HashMap();
        map.put("params", params);
        // JSON.parseObject(params, Map.class);

        long start = System.currentTimeMillis();
        ResponseEntity<Result> responseEntity = templateHolder.getForEntity(url, Result.class, map);
        long end = System.currentTimeMillis();
        logger.info("doGet Spend time : {}", (end - start));

        return responseEntity.getBody();
    }

    /**
     * Send Post request
     *
     * @param url like http://HELLO-SERVICE/hello, HELLO-SERVICE can be discovered in Eureka.
     * @param obj
     * @return
     */
    public static Result doPost(String url, String obj) {

        long start = System.currentTimeMillis();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        HttpEntity<String> formEntity = new HttpEntity<String>(obj, headers);

        ResponseEntity<Result> responseEntity = templateHolder.postForEntity(url, formEntity, Result.class);

        long end = System.currentTimeMillis();
        logger.info("doPost Spend time : {}", (end - start));

        return responseEntity.getBody();
    }

    public static Result doPut(String url, String params){
        long start = System.currentTimeMillis();

        //templateHolder.put(url, obj, String.class);
        Result result = exchange(url, HttpMethod.PUT, Result.class, params);

        long end = System.currentTimeMillis();
        logger.info("doPut Spend time : {}", (end - start));
        return result;
    }

    public static Result doPatch(String url, String params){
        long start = System.currentTimeMillis();

        //templateHolder.put(url, obj, String.class);
        Result result = exchange(url, HttpMethod.PATCH, Result.class, params);

        long end = System.currentTimeMillis();
        logger.info("doPatch Spend time : {}", (end - start));
        return result;
    }

    public static Result doDelete(String url, String params){
        long start = System.currentTimeMillis();

        //templateHolder.delete(url, params);
        Result result = exchange(url, HttpMethod.DELETE, Result.class, params);

        long end = System.currentTimeMillis();
        logger.info("doDelete Spend time : {}", (end - start));
        return result;
    }

    /**
     * 发送/获取 服务端数据(主要用于解决发送put,delete方法无返回值问题).
     *
     * @param url      绝对地址
     * @param method   请求方式
     * @param bodyType 返回类型
     * @param <T>      返回类型
     * @return 返回结果(响应体)
     */
    public static <T> T exchange(String url, HttpMethod method, Class<T> bodyType, String params) {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
        // 请求体
        headers.setContentType(mediaType);
        // 发送请求
        HttpEntity<String> entity = new HttpEntity<String>(params, headers);
        ResponseEntity<T> resultEntity = templateHolder.exchange(url, method, entity, bodyType);
        return resultEntity.getBody();
    }

    /**
     *  立即执行
     * @param methodUrl  http://formula-provider/variantMgr/loadSystemVariants
     *                   formula-provider： eureka 中注册的服务名
     * @param reqType 请求类型 get post delete put patch
     * @param params 前端传入的参数直接转发
     */
    public static Result doExec(String methodUrl, String reqType, String params){
        Result result = new Result();
        try{
            if(ReqType.PUT.getTypeCode().equalsIgnoreCase(reqType)){
                result = RestUtil.doPut(methodUrl, params);
            }else if(ReqType.POST.getTypeCode().equalsIgnoreCase(reqType)){
                result = RestUtil.doPost(methodUrl, params);
            }else if(ReqType.GET.getTypeCode().equalsIgnoreCase(reqType)){
                result = RestUtil.doGet(methodUrl, params);
            }else if(ReqType.DELETE.getTypeCode().equalsIgnoreCase(reqType)){
                result = RestUtil.doDelete(methodUrl, params);
            }else if(ReqType.PATCH.getTypeCode().equalsIgnoreCase(reqType)){
                result = RestUtil.doPatch(methodUrl, params);
            }
        }catch (Exception e){
            logger.error("doExec方法:"+methodUrl+"执行出错：" + e.getMessage(), e);
        }
        return result;
    }
}
