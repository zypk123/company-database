package com.huntkey.rx.sceo.formula.provider.related.classes.service;

import com.huntkey.rx.sceo.formula.common.model.TplCondition;

/**
 * 相关类条件Service
 *
 * @author zhangyu
 * @create 2017-07-04 17:24
 **/
public interface ConditionService {

    /**
     * 保存条件
     *
     * @param tplCondition
     * @return
     */
    int saveCondition(TplCondition tplCondition);

    /**
     * 修改条件
     *
     * @param tplCondition
     * @return
     */
    int updateCondition(TplCondition tplCondition);
}
