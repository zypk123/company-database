package com.huntkey.rx.hr.common.model;

/**
 * Created by xuyf on 2017/11/10 0010.
 */
public interface MDepChgrSetConstants {

    String MDEP_CHGR_TYPE_LEADER = "1";

    String MDEP_CHGR_TYPE_ASSISTANT = "2";

    String MDEP_CHGR_SEPARATOR_A = "/";

    String MDEP_CHGR_SEPARATOR_B = ";";

    /**
     *  EDM类名： 部门负责人集合
     */
    String EDM_MDEP_CHGR_SET = "depttree.mdep_chgr_set";

    /**
     * 部门负责人集合类属性：负责人类型
     */
    String MDEP_CHGR_TYPE = "mdep_chgr_type";
    /**
     * 部门负责人集合类属性：负责人
     */
    String MDEP_CHGR_EMP = "mdep_chgr_emp";
    /**
     * 部门负责人集合类属性：负责人岗位
     */
    String MDEP_CHGR_POST = "mdep_chgr_post";
    /**
     * 部门负责人集合类属性：生效日期
     */
    String MDEP_CHAR_BEG = "mdep_chgr_beg";
    /**
     * 部门负责人集合类属性：失效日期
     */
    String MDEP_CHGR_END = "mdep_chgr_end";

}
