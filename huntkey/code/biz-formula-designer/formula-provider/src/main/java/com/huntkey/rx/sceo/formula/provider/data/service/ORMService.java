package com.huntkey.rx.sceo.formula.provider.data.service;

import com.huntkey.rx.sceo.formula.common.model.vo.EsHbaseCriteriaVo;

import java.util.List;
import java.util.Map;

/**
 * @author chenfei on 2017/7/10.
 */
public interface ORMService {

    /**
     * query data from orm service.
     *
     * @param esHbaseCriteriaVo query param
     * @return data list
     */
    List<Map<String, Object>> queryDataFromORM(EsHbaseCriteriaVo esHbaseCriteriaVo);
}
