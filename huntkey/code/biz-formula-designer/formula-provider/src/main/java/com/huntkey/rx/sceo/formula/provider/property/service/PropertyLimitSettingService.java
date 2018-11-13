package com.huntkey.rx.sceo.formula.provider.property.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.TplPropertyLimit;
import com.huntkey.rx.sceo.formula.common.model.vo.PropLimitCndtVo;

import java.util.List;
import java.util.Map;

/**
 * 属性限值Service接口
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 * @modify  by nidongxu  on 2017-06-19
 *
 **/
public interface PropertyLimitSettingService {

    /**
     * 根据属性限值编号prprId 查询所有关联条件
     * 属性或者相关类编号cndt_prop_clss_id   是条件表的外键，故根据相关类id的查询方法，当传入prprId可获取所有属性的条件。
     * @param prprId
     * @return
     */
    List<TplCondition> queryAllConditions(String prprId);

    /**
     * 新增/更新属性限值关联条件
     * @param tplCondition
     * @return
     */
    int saveOrUpdateCondition(TplCondition tplCondition);

    /**
     * 新增属性限值关联条件
     * @param tplCondition
     * @return
     */
    int saveCondition(TplCondition tplCondition);

    /**
     * 更新属性限值关联条件
     * @param tplCondition
     * @return
     */
    int updateCondition(TplCondition tplCondition);

    /**
     * 删除一条关联条件
     * @param cndtId
     * @return
     */
    int removeCondition(String cndtId);

    /**
     * 新增/更新属性限值
     * @param tplPropertyLimit
     * @return
     */
    int saveOrUpdatePropLimit(TplPropertyLimit tplPropertyLimit);

    /**
     * 新增属性限值
     * @param tplPropertyLimit
     * @return
     */
    int savePropLimit(TplPropertyLimit tplPropertyLimit);

    /**
     * 更新属性限值
     * @param tplPropertyLimit
     * @return
     */
    int updatePropLimit(TplPropertyLimit tplPropertyLimit);

    /**
     * 根据属性限值标号prprId查询属性限值
     * @param prprId
     * @return
     */
    TplPropertyLimit getPropLimitById(String prprId);

    /**
     * 保存属性限值及关联条件
     * @param vo
     * @return
     */
    int saveOrUpdateLimitAndConditions(PropLimitCndtVo vo);

    /**
     * 根据属性限值标号prprId 查询属性限值及关联条件
     * @param prprId
     * @return
     */
    PropLimitCndtVo queryLimitAndConditions(String prprId);

    /**
     * queryFormulasAndPropertyLimits
     * @param prprIds
     * @param formulaIds
     * @return
     */
    Map<String, Object> queryFormulasAndPropertyLimits(List<String> prprIds, List<String> formulaIds);

    /**
     * deletePropLimit
     * @param prprId
     * @return
     */
    Result deletePropLimit(String prprId);
}
