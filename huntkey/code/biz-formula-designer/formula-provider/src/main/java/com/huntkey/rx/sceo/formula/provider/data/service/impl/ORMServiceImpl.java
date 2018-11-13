package com.huntkey.rx.sceo.formula.provider.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.vo.EsHbaseCriteriaVo;
import com.huntkey.rx.sceo.formula.provider.data.service.ORMService;
import com.huntkey.rx.sceo.formula.provider.engine.feign.EsAndHbaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author chenfei on 2017/7/10.
 */
@Service
public class ORMServiceImpl implements ORMService {

    private Logger logger = LoggerFactory.getLogger(ORMServiceImpl.class);

    @Autowired
    private EsAndHbaseService esAndHbaseService;

    @Override
    public List<Map<String, Object>> queryDataFromORM(EsHbaseCriteriaVo esHbaseCriteriaVo) {

        String params = JSONObject.toJSONString(esHbaseCriteriaVo);
        logger.info("orm query params: {}", params);

        Result result = esAndHbaseService.queryFromEsAndHbase(params);
        logger.info("result: {}", result);
        logger.info("origin: {}", result.getData().toString());

        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) result.getData();
        logger.info("map: {}", map);

        for (String key : map.keySet()) {
            logger.info("(key: {} -> className: {})", key, map.get(key).getClass().getName());
        }

        List<Map<String, Object>> dataset = (List<Map<String, Object>>) map.get("dataset");

        logger.info("dataset: {}", dataset);

        return dataset;
    }

}
