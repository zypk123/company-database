package com.huntkey.rx.purchase.common.constants;

/**
 * 采购项目-编码规则常量
 *
 * @author zhangyu
 * @create 2018-01-15 16:40
 **/
public interface NumberConstants {

    //--------------接口所需字段名----------------

    String EDMN_ENCODE = "edmnEncode";

    String EDMN_TYPE = "edmnType";

    //--------------临时编号前缀----------------
    /**
     * 编号规则前缀：临时维护单
     */
    String PREFIX_PU_TEMP_ORDER = "TMP";

    //--------------正式编号前缀----------------

    /**
     * 编号规则前缀：伙伴维护单
     */
    String PREFIX_RELATION_MAINT_ORDER = "BA22";

    /**
     * 编号规则前缀：供应商维护单
     */
    String PREFIX_SUPPLIER_MAINT_ORDER = "PU01";

    /**
     * 编号规则前缀：客户维护单
     */
    String PREFIX_CUSTOM_MAINT_ORDER = "SA01";

    /**
     * 编号规则前缀：采购报价单
     */
    String PREFIX_PUQUOTE_ORDER = "PU02";

    /**
     * 编号规则前缀：采购订单
     */
    String PREFIX_PUORDER = "PU03";

    /**
     * 编号规则前缀：采购收货单
     */
    String PREFIX_PU_RECEIVE_ORDER = "PU04";

    /**
     * 编号规则前缀：采购退货单
     */
    String PREFIX_PU_RETURN_ORDER = "PU05";

    /**
     * 编号规则前缀：采购对账单
     */
    String PREFIX_PUACCOUNT_ORDER = "PU06";

    /**
     * 编号规则前缀：物品维护单
     */
    String PREFIX_PU_GOODS_ORDER = "BA21";


    //--------------规则类型----------------
    //规则类型（edmnType）所对应的前缀（edmnEncode）参照注释
    /**
     * 1：字符串+‘-’+2位年+2位月+4位流水 (HR01-18)
     */
    int TYPE_1 = 1;
    /**
     * 2：字符串+五位流水 (D)
     */
    int TYPE_2 = 2;
    /**
     * 3：字符串+四位流水 (P、null、"")
     */
    int TYPE_3 = 3;
    /**
     * 4：字符串+2位年+2位月+4为流水 (null、“”)
     */
    int TYPE_4 = 4;

}
