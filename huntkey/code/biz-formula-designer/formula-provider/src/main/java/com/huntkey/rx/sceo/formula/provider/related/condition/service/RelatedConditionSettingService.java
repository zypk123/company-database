package com.huntkey.rx.sceo.formula.provider.related.condition.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.TacConditionRelated;
import com.huntkey.rx.sceo.formula.common.model.TacPropertyRelated;
import com.huntkey.rx.sceo.formula.common.model.vo.PropertyRelatedVo;

import java.util.List;
import java.util.Map;

/**
 * 关联条件设置Service接口
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
public interface RelatedConditionSettingService {

    /**
     * 新增关联条件的条件
     *
     * @param tacConditionRelated
     * @return
     */
    int saveConditionRelated(TacConditionRelated tacConditionRelated);

    /**
     * 更新关联条件的条件
     *
     * @param tacConditionRelated
     * @return
     */
    int updateConditionRelated(TacConditionRelated tacConditionRelated);

    /**
     * 查询关联条件的条件列表
     *
     * @return
     */
    List<TacConditionRelated> queryAllConditionsRelated();

    /**
     * 通过id查询关联条件的条件
     *
     * @param cndrId
     * @return
     */
    TacConditionRelated queryConditionsRelatedByCndrId(String cndrId);

    /**
     * 删除关联条件的条件接口(逻辑删除)
     *
     * @param cndrId 条件id
     * @return
     */
    int removeConditionRelated(String cndrId);

    /**
     * 保存关联条件
     *
     * @param tacPropertyRelated
     * @return
     */
    int savePropRelated(TacPropertyRelated tacPropertyRelated);


    /**
     * 更新关联条件
     *
     * @param tacPropertyRelated
     * @return
     */
    int updatePropRelated(TacPropertyRelated tacPropertyRelated);

    /**
     * 通过属性关联条件ID查询属性关联及其条件列表
     *
     * @param prplId
     * @return
     */
    PropertyRelatedVo getPropertyRelatedCondition(String prplId);

    /**
     * 通过id查找属性关联
     *
     * @param prplId
     * @return
     */
    TacPropertyRelated getPropertyRelatedById(String prplId);

    /**
     * 保存条件
     *
     * @param tacConditionRelated
     * @return
     */
    int saveOrUpdateConditionRelated(TacConditionRelated tacConditionRelated);

    /**
     * 保存关联属性
     *
     * @param tacPropertyRelated
     * @return
     */
    int saveOrUpdatePropRelated(TacPropertyRelated tacPropertyRelated);

    /**
     * 保存关联属性及其条件
     *
     * @param propertyRelatedVo
     * @return
     */
    int saveOrUpdatePropRelatedAndConditions(PropertyRelatedVo propertyRelatedVo);


    /**
     * 根据关联属性ID查找公式
     *
     * @param prplId
     * @return
     */
    String getPrplConditionDescByPrplId(String prplId);

    /**
     * 根据关联属性ID数组查找公式
     *
     * @param prplIdArr1
     * @param prplIdArr2
     * @return
     */
    Map<String, Object> getPrplConditionDescByPrplIdArr(List<String> prplIdArr1, List<String> prplIdArr2);

    /**
     * relCondDataByPro
     * @param prplId
     * @param dataId
     * @return
     */
    Result relCondDataByPro(String prplId, String dataId);

    /**
     * relCondConfDataByPro
     * @param prplId
     * @param dataId
     * @return
     */
    Result relCondConfDataByPro(String prplId, String dataId);

    /**
     * relCondConfDataByPrplIdForClass
     * @param prplId
     * @param dataId
     * @return
     */
    Result relCondConfDataByPrplIdForClass(String prplId,String dataId);

    /**
     * getObjectRelCondConfDataByPro
     * @param prplId
     * @return
     */
    Result getObjectRelCondConfDataByPro(String prplId);
}
