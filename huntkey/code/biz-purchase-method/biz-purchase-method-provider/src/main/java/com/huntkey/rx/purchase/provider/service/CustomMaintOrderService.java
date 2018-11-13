package com.huntkey.rx.purchase.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.RelationEntity;
import com.huntkey.rx.purchase.common.model.custom.CustomMaintOrderDTO;

import java.util.HashMap;

/**
 * 客户维护单Service接口
 *
 * @author zhangyu
 * @create 2018-01-02 17:08
 **/
public interface CustomMaintOrderService {

    /**
     * 客户管理—列表查询
     * @param relaCode
     * @param relaShortName
     * @param relaStatus
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result query(String relaCode, String relaShortName, String relaStatus, int pageNum, int pageSize);

    /**
     * 客户维护单 加载
     *
     * @param id
     * @return
     */
    Result load(String id);

    /**
     * 客户维护单 保存 (新增、修改)
     *
     * @param customMaintOrderDTO
     * @return
     */
    Result save(CustomMaintOrderDTO customMaintOrderDTO);

    /**
     * 客户维护单 删除
     *
     * @param id 客户维护单id
     * @return
     */
    Result delete(String id);

    /**
     * 客户维护单 提交
     *
     * @param customMaintOrderDTO
     * @return
     */
    Result submit(CustomMaintOrderDTO customMaintOrderDTO);

    /**
     * 客户维护单 批准通过
     *
     * @param orderInstanceId 单据id
     * @param handlerType     处理方式
     * @return
     */
    Result approve(String orderInstanceId, String handlerType);

    /**
     * 通过方法
     *
     * @param auditSet
     * @return
     */
    Result audit(JSONObject auditSet);

    /**
     * 客户维护单 唯一性校验
     *
     * @param checkField
     * @param value
     * @param relaId
     * @param customMaintOrderId
     * @return
     */
    Result checkCustomUnique(String checkField, String value, String relaId, String customMaintOrderId);

    /**
     * 通过伙伴编码查询客户维护单
     *
     * @param cumoCode
     * @return
     */
    Result getCustomMaintOrderByCode(String cumoCode);

    /**
     * 通过伙伴编码查询伙伴类
     *
     * @param cumoCode
     * @return
     */
    Result getRelationByCode(String cumoCode);


}
