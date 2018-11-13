package com.huntkey.rx.hr.common.constants;

/**
 * @author zhanggj
 * @createTime 2017/12/21
 * @desc
 */
public interface DeptPostSetOderConstants {

    //--------------------------------
    String EDM_JOBPOSITION = "jobposition";

    String EDM_DEPTPOSTSETORDER = "deptpostsetorder";
    //--------------------------------
    //单据状态 临时
    String ORDER_STATUS_1 = "1";

    //单据状态 待审
    String ORDER_STATUS_2 = "2";

    //单据状态 待核
    String ORDER_STATUS_3 = "3";

    //单据状态 待批
    String ORDER_STATUS_4 = "4";

    //单据状态 完成
    String ORDER_STATUS_5 = "5";

    //单据状态 退回
    String ORDER_STATUS_6 = "6";

    //--------------------------------
    //单据类型 新增
    String ODPS_TYPE_0 = "0";

    //单据类型 修改
    String ODPS_TYPE_1 = "1";

    //单据类型 注销
    String ODPS_TYPE_3 = "3";

    //--------------------------------
    //记录类型 原有记录
    String ODPS_FLAG_0 = "0";

    //记录类型 新增新增
    String ODPS_FLAG_1 = "1";

    //记录类型 根节点记录
    String ODPS_FLAG_3 = "3";

    //---------------------------------
    //是否含下级 不含
    String ODPS_SUB_0 = "0";

    //是否含下级 包含
    String ODPS_SUB_1 = "1";

    //--------------------------------
    // 流程实例id
    String PARAM_ACT_INSTANCE_ID = "actInstanceId";

    // 审批意见文本
    String PARAM_OPINION = "opinion";

    // 审批意见 pass，枚举
    String PARAM_AUDITKEY = "auditKey";

    // 单据编辑状态：0不可以编辑，1可以编辑
    String PARAM_FORMSTATE = "formState";


}
