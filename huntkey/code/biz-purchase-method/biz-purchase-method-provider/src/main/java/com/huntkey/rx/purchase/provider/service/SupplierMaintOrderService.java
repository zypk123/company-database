package com.huntkey.rx.purchase.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.model.supplier.SupplierMaintOrderDTO;

import java.util.HashMap;

/**
 * 供应商维护单Service接口
 *
 * @author zhangyu
 * @create 2018-01-02 17:08
 **/
public interface SupplierMaintOrderService {

    /**
     * 供应商维护单 加载
     *
     * @param id
     * @return
     */
    Result load(String id);

    /**
     * 供应商维护单 保存 (新增、修改)
     *
     * @param supplierMaintOrderDTO
     * @return
     */
    Result save(SupplierMaintOrderDTO supplierMaintOrderDTO);

    /**
     * 供应商维护单 删除
     *
     * @param id 供应商维护单id
     * @return
     */
    Result delete(String id);

    /**
     * 供应商维护单 提交
     *
     * @param supplierMaintOrderDTO
     * @return
     */
    Result submit(SupplierMaintOrderDTO supplierMaintOrderDTO);

    /**
     * 通过方法
     *
     * @param auditSet
     * @return
     */
    Result audit(JSONObject auditSet);

    /**
     * 供应商维护单 批准通过
     *
     * @param orderInstanceId 单据id
     * @param handlerType     处理方式
     * @return
     */
    Result approve(String orderInstanceId, String handlerType);

    /**
     * 供应商维护单 唯一性校验
     *
     * @param checkField
     * @param value
     * @param relaId
     * @param supplierMaintOrderId
     * @return
     */
    Result checkSupplierUnique(String checkField, String value, String relaId, String supplierMaintOrderId);

    /**
     * 通过伙伴编码查询供应商维护单
     *
     * @param sumoCode
     * @return
     */
    Result getSupplierMaintOrderByCode(String sumoCode);

    /**
     * 通过伙伴编码查询伙伴类
     *
     * @param sumoCode
     * @return
     */
    Result getRelationByCode(String sumoCode);

    /**
     * 供应商列表查询接口
     * @param relaCode
     * @param relaShortName
     * @param relaStatus
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result query(String relaCode, String relaShortName, String relaStatus, int pageNum, int pageSize);

    /**
     * 根据条件查询想要的结果
     * @param c
     * @param conditions
     * @param columnName
     * @return
     */
    String queryResultByConditions(Class c , HashMap<String , Object> conditions , String columnName);

    Result getResult(int retCode,String errMsg);

    Result emptyPageQueryResult(int pageNum , int pageSize);
}
