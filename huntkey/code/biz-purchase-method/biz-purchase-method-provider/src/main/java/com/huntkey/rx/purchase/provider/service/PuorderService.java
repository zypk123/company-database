package com.huntkey.rx.purchase.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.model.puodorder.PuorderDTO;

/**
 * 采购订单接口Service
 *
 * Created by fangyou on 2018年1月18日 0018.
 */
public interface PuorderService {


    /**
     * 采购订单保存接口
     * @param puorderDTO
     * @param isSubmit
     * @return
     */
    Result saveOrderService(PuorderDTO puorderDTO, boolean isSubmit);

    /**
     * 采购订单管理列表查询接口
     * @param ordeNbr
     * @param sumoId
     * @param startTime
     * @param endTime
     * @param puodStatus
     * @param parkId
     * @param currId
     * @param empId
     * @param deptId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result queryOrderListService(String ordeNbr, String sumoId, String startTime, String endTime, String puodStatus, String parkId,String currId,String empId,String deptId, int pageNum, int pageSize);

    /**
     * 采购订单提交接口
     * @param puorderDTO
     * @return
     */
    Result submitOrder(PuorderDTO puorderDTO);

    /**
     * 采购订单删除接口
     * @param id
     * @return
     */
    Result deleteOrderService(String id);

    /**
     * 单据审核
     *
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    Result approve(String orderInstanceId,String handlerType);

    /**
     * 单据审批通过接口
     * @param id
     */
    void pass(String id);

    /**
     * 根据ID判断单据是否已经提交过了，如果提交过了不能再次提交
     * @param id
     * @return
     */
    Result checkIsSubmit(String id);

    /**
     * 根据单据id加载单据信息
     * @param id
     * @return
     */
    Result loadOrder(String id);

    /**
     * 采购订单审核方法
     * @param jsonObject
     * @return
     */
    Result auditOrder(JSONObject jsonObject);

    /**
     * 采购订单关闭方法
     * @param puorderDTO
     * @return
     */
    Result closeOrder(PuorderDTO puorderDTO);

    /**
     * 根据供应商id,园区id 查询物品编号接口
     * @param supplierId
     * @param parkId
     * @param godsCode
     * @return
     */
    Result queryGoodsCode(String supplierId,String parkId,String godsCode);

    /**
     * 根据物品编号 获取采购订单明细
     * @param godsCode     物品编号
     * @param supplierId   供应商
     * @param parkId       园区
     * @param currId       币别
     * @param goodsCodes   物品编号集[,,]
     * @return
     */
    Result getPurchaseOrderDetails(String godsCode,String supplierId,String parkId,String currId,String goodsCodes);

    /**
     * 根据供应商ID获取币别 税率 结算方式集合
     * @param supplierId
     * @return
     */
    Result queryBySupplier(String supplierId);

    /**
     * 根据园区载入交货地址
     * @param parkId
     * @return
     */
    Result queryByPark(String parkId);

    /**
     * 获取采购法人列表接口
     * @return
     */
    Result getPuorderLeagerPerson();

}
