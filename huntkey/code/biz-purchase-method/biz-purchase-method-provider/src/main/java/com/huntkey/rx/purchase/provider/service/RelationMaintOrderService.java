package com.huntkey.rx.purchase.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.model.relation.RelationMaintOrderDTO;

/**
 * @author xuyf on 2018/1/22 0022.
 */
public interface RelationMaintOrderService {

    /**
     * 根据伙伴维护单据id查询伙伴维护单详情
     * @param orderId
     * @return
     */
    RelationMaintOrderDTO getRelationMaintOrderByOrderId(String orderId);

    /**
     * 伙伴维护单唯一性校验
     * @param exceptRelaId
     * @param exceptRemoId
     * @param remoUscc
     * @param remoCode
     * @param remoShortName
     * @return
     */
    JSONObject checkRelationMaintOrderUnique(String exceptRelaId, String exceptRemoId, String remoUscc, String remoCode, String remoShortName);

    /**
     * 伙伴维护单 保存
     *
     * @param relationMaintOrderDTO
     * @return
     */
    JSONObject saveRelationMaintOrder(RelationMaintOrderDTO relationMaintOrderDTO);

    JSONObject submitRelationMaintOrder(RelationMaintOrderDTO relationMaintOrderDTO);

    /**
     * 伙伴维护单 删除
     *
     * @param id
     * @return
     */
    int deleteRelation(String id);

    /**
     * 伙伴维护单 批准通过
     *
     * @param orderInstanceId
     * @param handlerType
     */
    void approve(String orderInstanceId, String handlerType);

    Result audit(JSONObject auditSet);

    /**
     * 更新客户维护单单据状态
     *
     * @param orderInstanceId
     * @param orderStatus
     */
    void updateRelationMaintOrderStatus(String orderInstanceId, String orderStatus);

}
