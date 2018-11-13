package com.huntkey.rx.purchase.provider.base;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.OrderEntity;

/**
 * 最基础的base接口
 * 该接口只适用于 单据类。
 * 一般关于单据的接口继承该接口。 如果有新的接口需求，可以自行拓展。
 * @param <T>
 */
public interface IBaseOrderService<T extends OrderEntity> {

    /**
     * 保存单据
     * @param params
     * @return
     */
    Result saveOrder(JSONObject params);

    /**
     * 校验单据
     * @param entity
     * @return
     */
    Result vaildOrder(T entity);

    /**
     * 提交单据
     * @param params
     * @return
     */
    Result submitOrder(JSONObject params);

    /**
     * 加载单据详情信息
     * @param orderId
     * @return
     */
    Result loadOrder(String orderId);


    /**
     * pass通过 （单据的审批操作）
     * @param orderInstanceId 单据id
     * @param handlerType     审批类型
     * @return
     * @author yaoss
     */
    Result passOrder(String orderInstanceId, String handlerType);

    /**
     * audit审核接口 （单据的审核操作，这个是在pass之前调用，audit和pass是一起的。如果单据需要在审批过程中修改，
     * 那么 audit接口中就需要相应的更新单据一些信息 然后再调用pass，如果不需要，那么audit接口调用完毕之后，会直接调用pass。
     * ）
     * @param auditSet
     * @return
     * @author yaoss
     */
    Result auditOrder(JSONObject auditSet);

    /**
     * 查询单据列表（ 这个接口 非必须。 有的需求不需要，有的需要。那么该接口可实现，亦可不实现）
     * @param params
     * @return
     */
    Result queryOrderList(JSONObject params);


}
