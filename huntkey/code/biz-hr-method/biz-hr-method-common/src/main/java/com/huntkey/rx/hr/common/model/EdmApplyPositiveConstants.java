package com.huntkey.rx.hr.common.model;

public interface EdmApplyPositiveConstants {

    /**
     * EDM类：转正申请单
     */
    String OEPA_EMPPOSITIVEAPPLY = "emppostiveapply";
    /**
     * 转正单类：转正人
     */
    String OEPA_EMP = "oepa_emp";

    /**
     * 转正单类：所属部门
     */
    String OEPA_DEPT = "oepa_dept";

    /**
     * 转正单类：转正日期
     */
    String OEPA_DATE = "oepa_date";

    /**
     * 转正单类：备注
     */
    String OEPA_REMARK = "oepa_remark";

    /**
     * 转正单类：指引人
     */
    String OEPA_GUILD = "oepa_guild";

    /**
     * 转正单类：转正报告
     */
    String OEPA_REPORT = "oepa_report";

    /**
     * 转正单类：报告路径
     */
    String OEPA_REPORT_URL = "oepa_report_url";

    /**
     * 转正单类：附件名称
     */
    String OEPA_FILE_NAME = "oepa_file_name";

    /**
     * 转正意见单：id
     */
    String OEPA_ID = "id";


    String OEPA_AUDIT_SET = "oepa_audit_set";

    /**
     * 转正意见：审批意见
     */
    String OEPA_AUDIT_IDEA = "oepa_audit_idea";

    /**
     * 转正意见：延期转正日
     */
    String OEPA_OEPA_EXT_DATE = "oepa_ext_date";

    String OEPA_AUDIT_PID = "pid";

    String OEPA_OEPA_CLASSNAME = "classname";
    /**
     *转正意见：延期月数
     */
    String OEPA_EXT_MONTH = "oepa_ext_month";

    /**
     * 转正意见：评价理由
     */
    String OEPA_EVALUATION = "oepa_evaluation";

    String ORDE_STATUS = "orde_status";
    String ORDE_NBR = "orde_nbr";

    // 返回值
    String OEPA_ID_RETURN = "oepaId";
    String CREATE_TIME = "cretime";
    String MOD_TIME = "modtime";
    String OEPA_GUILD_NAME = "oepa_guild_name";
    String OEPA_GUILD_NO = "oepa_guild_no";

    // 驼峰参数
    String PARAM_ORDER_OBJ = "auditMsg";
    String PARAM_OEPA_AUDITOR = "oepaAuditor";
    // 转正意见
    String PARAM_OEPA_AUDIT_IDEA = "oepaAuditIdea";
    // 评价理由
    String PARAM_OEPA_EVALUATION = "oepaEvaluation";
    String PARAM_OEPA_EXT_MONTH = "oepaExtMonth";
    String PARAM_OEPA_EXT_DATE = "oepaExtDate";
//    // 流程实例id
//    String PARAM_ACT_INSTANCE_ID = "actInstanceId";
//    // 审批意见文本
//    String PARAM_OPINION = "opinion";
//    // 审批意见 pass，枚举
//    String PARAM_AUDITKEY = "auditKey";
//    // 单据编辑状态：0不可以编辑，1可以编辑
//    String PARAM_FORMSTATE = "formState";

    // 数据库查询别名
    String OEPA_EMPPOSITIVEAPPLY_ID = "emppostiveapplyId";
    String OEPA_EMPPOSITIVEAPPLY_IS_DEL = "orde_is_del";
    String NOT_DEL = "0";
    // 分页
    String PAGE_PAGENUM = "pageNum";
    String PAGE_PAGESIZE = "pageSize";
    String PAGE_TOTAL = "total";
    String PAGE_LIST = "list";
}
