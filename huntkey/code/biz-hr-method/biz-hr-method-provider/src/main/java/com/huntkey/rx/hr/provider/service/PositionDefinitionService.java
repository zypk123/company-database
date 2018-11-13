package com.huntkey.rx.hr.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.PositiondefinitionEntity;
import com.huntkey.rx.hr.common.model.PostDefinitionOrderDTO;
import java.util.List;


/**
 * @author Created by weijian on 2017/11/13.
 */
public interface PositionDefinitionService
{
    /**
     * 查询职位列表
     * @param positionAttributeType 职位属性类别，0:直接职位; 1:间接职位
     * @param positionTypeCodeValue 职类代码区间，用,分割的字符串
     * @param postGradeValue 岗级代码区间
     * @return
     */
    Result queryPositionListService(String positionAttributeType, String positionTypeCodeValue, String postGradeValue);

    /**
     * 保存职位设置列表
     * @param positionList
     * @return
     */
    Result savePositionService(List<PositiondefinitionEntity> positionList);

    /**
     * 删除职位定义
     * @param positionCodeList
     * @return
     */
    Result deletePositionService(List<String> positionCodeList);

    /**
     * 职位维护单加载方法
     * @param rpofType
     * @return
     */
    Result load(String rpofType);

    /**
     * 职位维护单保存方法
     * @param postDefinitionOrderDTO
     * @return
     */
    Result save(PostDefinitionOrderDTO postDefinitionOrderDTO) throws Exception;


    /**
     * 职位维护单提交
     * @param postDefinitionOrderDTO
     * @return
     */
    Result submit(PostDefinitionOrderDTO postDefinitionOrderDTO) throws Exception;

    /**
     *职位维护单提交加载方法（加载单据数据）
     * @param id
     * @return
     */
    Result loadPostDefinitionOrder(String id);

    /**
     * 职位维护单通过
     * @param orderInstanceId 单据实例ID
     * @param handlerType 处理类型
     * @return
     */
    Result pass(String orderInstanceId,String handlerType);


    /**
     * 审核职位维护单
     *  @param auditSet
     * @author yaoss
     */
    Result audit(JSONObject auditSet);
}
