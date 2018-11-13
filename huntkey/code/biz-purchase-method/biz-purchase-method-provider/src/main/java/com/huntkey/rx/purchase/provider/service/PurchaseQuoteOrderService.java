package com.huntkey.rx.purchase.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuqoOrderQueryDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuquGodsQueryDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuquoteOrderDTO;

/**
 * 采购报价单类接口
 *
 * @author zhoucj
 * @date 2018/1/19
 */
public interface PurchaseQuoteOrderService {


    /**
     * 保存 采购报价单
     *
     * @author yaoss
     */
    Result savePuquoteOrder(PuquoteOrderDTO params);

    /**
     * 提交 采购报价单
     *
     * @author yaoss
     */
    Result submitPuquoteOrder(PuquoteOrderDTO params);

    /**
     * 加载 采购报价单
     *
     * @param id          单据id
     * @param isLoadParks 是否需要加载 物品可选园区。因为编辑界面可以修改园区，而审批界面只是显示园区，不能修改园区
     *                    所以，为了数据库效率，设定可加载可选园区和不加载园区
     * @return
     * @author yaoss
     */
    Result loadOrder(String id, boolean isLoadParks);

    /**
     * /**
     * 变更历史；报价单查询页面
     *
     * @param queryDTO
     * @return
     * @author yaoss
     */
    Result queryPuquOrders(PuqoOrderQueryDTO queryDTO);

    /**
     * pass通过
     *
     * @param orderInstanceId 单据id
     * @param handlerType     审批类型
     * @return
     * @author yaoss
     */
    Result passOrder(String orderInstanceId, String handlerType);

    /**
     * audit审核接口
     *
     * @param auditSet
     * @return
     * @author yaoss
     */
    Result auditOrder(JSONObject auditSet);

    /**
     * 采购价格管理列表查询
     *
     * @param queryDTO
     * @return
     * @author yaoss
     */
    Result queryPuquPriceByGoods(PuquGodsQueryDTO queryDTO);


}
