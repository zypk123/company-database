package com.huntkey.rx.purchase.common.constants;

/**
 * Created by fangyou on 2018年1月18日 0018.
 */
public interface PuorderConstants {

    String PUORDER = "puorder";

    String PUODORDERSET = "puodorderset";

    String ORDER_ID = "orderId";

    String PUOD_ORDER_SET_ID = "puodOrderSetId";

    String PUOD_CURR_CODE = "puodCurrCode";

    String PUOD_CORP_NAME = "puodCorpName";

    String PUOD_PARK_NAME = "puodParkName";

    String PUOD_EMP_NAME = "puodEmpName";

    String PUOD_DEPT_NAME = "puodDeptName";

    String PUOD_SETTLE_NAME = "puodSettleName";

    String PUOD_T_MONEY = "tMoney";

    String PUOD_SUMO_CODE = "puodSumoCode";

    String ORDE_ADDUSER_NAME = "ordeAdduserName";

    String TOTAL_AMOUNT = "totalAmount";

    String TOTAL_MONEY = "totalMoney";

    String TOTAL_TAX = "totalTax";

    String PUOD_ISTATUS = "puodIstatus";

    /**
     * 订单明细状态（行状态）：
     * 0-拒绝
     * 1-正常
     * 2-终止
     * 3-完成
     */
    String PUOD_ISTATUS_0 = "0";
    String PUOD_ISTATUS_1 = "1";
    String PUOD_ISTATUS_2 = "2";
    String PUOD_ISTATUS_3 = "3";

    /**
     * 订单状态：
     * 1-临时
     * 2-待审
     * 3-待核
     * 4-待批
     * 5-待交货
     * 6-退回
     * 10-部分交货
     * 11-完成
     * 12-终止
     */
    String PUOD_STATUS_1 = "1";
    String PUOD_STATUS_2 = "2";
    String PUOD_STATUS_3 = "3";
    String PUOD_STATUS_4 = "4";
    String PUOD_STATUS_5 = "5";
    String PUOD_STATUS_6 = "6";
    String PUOD_STATUS_10 = "10";
    String PUOD_STATUS_11 = "11";
    String PUOD_STATUS_12 = "12";

    /**
     * 是否补货项：
     * 1-是
     * 0-否
     */
    int PUOD_ISREPL_0 = 0;
    int PUOD_ISREPL_1 = 1;

    /**
     * 价格类型:
     * 默认写入0
     * 单价为阶梯报价的写入1
     * 为修改的单价写入2
     */
    int PUOD_PRICE_TYPE_0 = 0;
    int PUOD_PRICE_TYPE_1 = 1;
    int PUOD_PRICE_TYPE_2 = 2;



}
