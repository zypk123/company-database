package com.huntkey.rx.purchase.common.constants;

/**
 * errMsg 常量
 */
public interface ErrorMsgConstants {

    /**
     * goods
     */
    String ERROR_MSG_PU_GOODS_QUERY = "查询物品列表信息接口报错";

    String ERROR_MSG_PU_GOODS_LOAD = "根据物品类id加载物品信息接口报错";

    String ERROR_MSG_PU_GOODS_PARK = "查询所有的园区接口报错";

    String ERROR_MSG_PU_UNIT_TREE = "查询单位树接口报错";

    /**
     * goodsfeature
     */

    String ERROR_MSG_PU_GOODSFEA_QUERY = "查询物品特征类列表接口报错";

    String ERROR_MSG_PU_GOODSFEA_LOAD = "根据物品特性类id加载物品特征信息接口报错";

    String ERROR_MSG_PU_GOODSFEA_SAVE = "保存物品特征信息接口报错";

    String ERROR_MSG_PU_GOODSFEA_DELETE = "根据物品特征id集合删除物品特征信息接口报错";

    String ERROR_MSG_PU_GOODSFEA_CHECK = "验证物品特征名称唯一性接口报错";

    String ERROR_MSG_PU_GOODSFEA_MOVE = "物品特征上移下移接口报错";

    String ERROR_MSG_PU_GOODSFEA_WORDNAME = "根据枚举名称查询枚举列表接口报错";

    /**
     * goodsmaintorder
     */
    String ERROR_MSG_PU_GOODSMAINT_LOAD = "根据物品维护单id加载物品维护单信息接口报错";

    String ERROR_MSG_PU_GOODSMAINT_SAVE = "保存物品维护单信息接口报错";

    String ERROR_MSG_PU_GOODSMAINT_DELETE = "根据物品维护单id集合删除物品维护单信息接口报错";

    String ERROR_MSG_PU_GOODSMAINT_SUBMIT = "物品维护单提交接口报错";

    String ERROR_MSG_PU_GOODSMAINT_PASS = "物品维护单批准通过接口接口报错";

    String ERROR_MSG_PU_GOODSMAINT_CHECK = "校验物品编码是否有重复接口报错";
}

