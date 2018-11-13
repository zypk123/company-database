package com.huntkey.rx.sceo.formula.provider.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author chenfei on 2017/7/25.
 */
@Service
@Lazy(false)
public class RestUtils {

    private static Logger logger = LoggerFactory.getLogger(RestUtils.class);

    @Autowired
    RestTemplate restTemplate;

    private static RestTemplate templateHolder;

    @PostConstruct
    public void init() {
        templateHolder = restTemplate;
    }

    /**
     * Send Get request
     *
     * @param url like http://HELLO-SERVICE/hello, HELLO-SERVICE can be discovered in Eureka.
     * @param params
     * @return
     */
    public static String doGet(String url, Map<String, String> params) {

        long start = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        result.append(templateHolder.getForEntity(url, String.class, params).getBody());

        long end = System.currentTimeMillis();
        logger.info("Spend time : {}", (end - start));

        return result.toString();
    }

    /**
     * Send Post request
     *
     * @param url like http://HELLO-SERVICE/hello, HELLO-SERVICE can be discovered in Eureka.
     * @param obj
     * @return
     */
    public static String doPost(String url, Object obj) {

        long start = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        ResponseEntity<String> responseEntity = templateHolder.postForEntity(url, obj, String.class);
        result.append(responseEntity.getBody());

        long end = System.currentTimeMillis();
        logger.info("Spend time : {}", (end - start));

        return result.toString();
    }

}
