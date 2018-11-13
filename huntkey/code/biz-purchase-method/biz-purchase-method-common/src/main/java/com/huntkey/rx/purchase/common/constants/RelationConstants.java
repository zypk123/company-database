package com.huntkey.rx.purchase.common.constants;

/**
 * Created by xuyf on 2018/1/8 0008.
 */
public interface RelationConstants {

    String className = "relation";

    /**
     * 伙伴类型枚举名称
     */
    String RELA_TYPE_ENUM_NAME = "relation_type";

    /**
     * 伙伴类型：客户
     */
    String RELA_TYPE_CUST = "1";

    /**
     * 伙伴类型：供应商
     */
    String RELA_TYPE_SUPPLIER = "2";

    /**
     * 伙伴类型：股东
     */
    String RELA_TYPE_HOLDER = "3";

    /**
     * 伙伴类型：银行
     */
    String RELA_TYPE_BANK = "4";

    /**
     * 伙伴类型：法人
     */
    String RELA_TYPE_CORP = "5";

    /**
     * 伙伴状态枚举名称
     */
    String RELA_STAT_ENUM_NAME = "rela_stat";

    /**
     * 伙伴状态：正常
     */
    String RELA_STAT_NORMAL = "1";

    /**
     * 伙伴状态：观察
     */
    String RELA_STAT_OBSERVE = "2";

    /**
     * 伙伴状态：关闭
     */
    String RELA_STAT_CLOSE = "3";

    /**
     * 团队类型枚举名称
     */
    String TEAM_TYPE_ENUM_NAME = "team_type";

    /**
     * 团队类型：质量负责人
     */
    String TEAM_TYPE_QUALITY = "1";

    /**
     * 团队类型：采购负责人
     */
    String TEAM_TYPE_BUY = "2";

    /**
     * 团队类型：销售负责人
     */
    String TEAM_TYPE_SALE = "3";

    /**
     * 团队类型：研发负责人
     */
    String TEAM_TYPE_RESEARCH = "4";

    /**
     * 团队类型：财务负责人
     */
    String TEAM_TYPE_FINANCE = "5";

    /**
     * 表名
     */
    String RELATION = "relation";
    /**
     * 伙伴编号
     */
    String RELA_CODE = "rela_code";
    /**
     * 伙伴简称
     */
    String RELA_SHORT_NAME = "rela_short_name";
    /**
     * 统一社会信用代码
     */
    String RELA_USCC = "rela_uscc";


    /**
     * 伙伴类唯一性校验的三个字段名称 —— 统一社会信用代码
     */
    String FIELD_NAME_RELA_USCC = "统一社会信用代码";

    /**
     * 伙伴类唯一性校验的三个字段名称 —— 伙伴编号
     */
    String FIELD_NAME_RELA_CODE = "伙伴编号";

    /**
     * 伙伴类唯一性校验的三个字段名称 —— 伙伴简称
     */
    String FIELD_NAME_RELA_SHORT_NAME = "伙伴简称";


}
