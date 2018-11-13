package com.huntkey.rx.purchase.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.model.goodsmaintorder.GoodsMaintOrderDTO;

/**
 * ClassName:GoodsMaintOrderService 物品特征类操作
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 * Date:     2017年12月27日 上午11:55:29
 * @author   liangh
 * @version
 * @see
 */
public interface GoodsMaintOrderService {

    /**
     * 查询物品维护单方法
     * @Param id
     */
    Result load(String id);

    /**
     * 新增、更新物品维护单方法
     * 注意更新物品维护单时，存old数据
     * @Param
     */
    Result saveGoodsMaintOrder(GoodsMaintOrderDTO goodsMaintOrderDTO);

    void saveGoods(String id);

    /**
     * 物品维护单删除方法
     * 注意这个方法只是删除维护单，不是删除物品信息
     * @Param
     */
    Result deleteGoodsMaintOrder(String id);

    /**
     * 物品维护单提交方法
     * @Param
     */
    Result submitGoodsMaintOrder(GoodsMaintOrderDTO goodsMaintOrderDTO);

    /**
     * 物品维护单批准通过方法
     * @Param
     */
    Result approveGoodsMaintOrder(String orderInstanceId, String handlerType);

    Result audit(JSONObject auditSet);

    /**
     * 查物品表和物品维护单表，物品编码是否有重复
     * 如果有，则返回状态码为0,给出错误信息
     * 如果没有，则返回状态码为1
     * @param godsCode 物品编码godsCode
     */
    Result check(String godsCode);

}
