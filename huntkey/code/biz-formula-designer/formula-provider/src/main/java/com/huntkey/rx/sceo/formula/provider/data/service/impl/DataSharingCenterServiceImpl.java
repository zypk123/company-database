package com.huntkey.rx.sceo.formula.provider.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.provider.data.entity.DataSharingCenterParam;
import com.huntkey.rx.sceo.formula.provider.data.service.DataSharingCenterService;
import com.huntkey.rx.sceo.formula.provider.engine.feign.DataSharingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenfei on 2017/8/1.
 */
@Service
public class DataSharingCenterServiceImpl implements DataSharingCenterService {

    private Logger logger = LoggerFactory.getLogger(DataSharingCenterServiceImpl.class);

    @Autowired
    private DataSharingService dataSharingService;

    @Override
    public Object search(DataSharingCenterParam param) {

        String params = JSONObject.toJSONString(param);
        logger.info("orm query params: {}", params);

        long startTime = System.currentTimeMillis();

        Result result = dataSharingService.find(params);

        long endTime = System.currentTimeMillis();

        logger.info("query cost: {}", (endTime - startTime));
        logger.info("result: {}", result);

        if (null == result.getData()) {
            throw new RuntimeException("Data is empty, please check your formula.");
        }

        logger.info("origin: {}", result.getData().toString());

        return result.getData();
    }

    @Override
    public Object load(DataSharingCenterParam param) {

        String params = JSONObject.toJSONString(param);

        // hack for poor ORM interface.
        params = params.replace("\"conditions\":[]", "");
        logger.info("orm query params: {}", params);

        long startTime = System.currentTimeMillis();

        Result result = dataSharingService.load(params);

        long endTime = System.currentTimeMillis();

        logger.info("query cost: {}", (endTime - startTime));
        logger.info("result: {}", result);

        if (null == result.getData()) {
            throw new RuntimeException("Data is empty, please check your formula.");
        }

        logger.info("origin: {}", result.getData().toString());

        return result.getData();
    }
}
