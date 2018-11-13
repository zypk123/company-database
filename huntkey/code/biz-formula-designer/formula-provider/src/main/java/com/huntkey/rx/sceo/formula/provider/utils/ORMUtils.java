package com.huntkey.rx.sceo.formula.provider.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.model.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author lulx on 2017/8/16 0016 上午 11:15
 */
public final class ORMUtils {

    private static Logger logger = LoggerFactory.getLogger(ORMUtils.class);

    public static EsHbaseCriteriaVo getEsHbaseCriteria(String edmName, String[] columns, List<ConditionsVo> conditions, PagenationVo page) {
        SearchVo search = new SearchVo();
        if (!StringUtil.isNullOrEmpty(columns) && columns.length > 0) {
            search.setColumns(Arrays.asList(columns));
        }

        if (!StringUtil.isNullOrEmpty(conditions) && conditions.size() > 0) {
            search.setConditions(conditions);
        }

        if (StringUtil.isNullOrEmpty(page)) {
            page = new PagenationVo();
            page.setRows(50);
            page.setStartPage(1);
        }
        search.setPagenation(page);

        EsHbaseCriteriaVo params = new EsHbaseCriteriaVo();
        params.setEdmName(edmName);
        params.setSearch(search);
        return params;
    }

    public static SimpleCriteriaVo getSimpleCriteriaVo(String edmName, String conditionFormula) {

        SimpleSearchVo search = new SimpleSearchVo();
        if (!StringUtil.isNullOrEmpty(conditionFormula)) {
            search.setWhere(conditionFormula);
        }

        SimpleCriteriaVo params = new SimpleCriteriaVo();
        params.setEdmName(edmName);
        params.setSearch(search);
        return params;
    }


    public static Object getValByPara(List<Map<String, Object>> dataSet, String para) {
        for (Map<String, Object> item : dataSet) {
            if (item.containsKey(para)) {
                return item.get(para);
            }
        }
        return null;
    }

    public static Boolean isHasKey(List<Map<String, Object>> dataSet, String para) {
        for (Map<String, Object> item : dataSet) {
            if (item.containsKey(para)) {
                return true;
            }
        }
        return false;
    }


    public static List<Map<String, Object>> getDataSet(String restResult) {
        Result ormResult = JSON.parseObject(restResult, Result.class);
        logger.info("result: {}", ormResult);

        List<Map<String, Object>> dataset = new ArrayList<Map<String, Object>>();
        if (!Result.RECODE_SUCCESS.equals(ormResult.getRetCode())) {
            return dataset;
        }

        logger.info("origin: {}", ormResult.getData().toString());

        JSONObject map = (JSONObject) ormResult.getData();
        logger.info("map: {}", map);

        for (String key : map.keySet()) {
            logger.info("(key: {} -> className: {})", key, map.get(key).getClass().getName());
        }

        dataset = (List<Map<String, Object>>) map.get("dataset");
        logger.info("dataset: {}", dataset);
        return dataset;
    }

    public static Object[] getValsByPara(List<Map<String, Object>> dataSet, String para) {
        List<Object> lists = new ArrayList<Object>();
        for (Map<String, Object> item : dataSet) {
            if (item.containsKey(para)) {
                lists.add(item.get(para));
            }
        }
        return lists.toArray();
    }
}
