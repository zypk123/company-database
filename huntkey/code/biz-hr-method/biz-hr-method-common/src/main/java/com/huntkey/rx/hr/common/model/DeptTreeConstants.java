package com.huntkey.rx.hr.common.model;

/**
 * Created by xuyf on 2017/11/10 0010.
 */
public interface DeptTreeConstants {

    int COMPLEMENT_NO = 0;

    int COMPLEMENT_YES = 1;

    /**
     *  EDM类名： 部门
     */
    String EDM_DEPTTREE = "depttree";
    /**
     * 部门类属性：编码
     */
    String MDEP_CODE = "mdep_code";
    /**
     * 部门类属性：名称
     */
    String MDEP_NAME = "mdep_name";

    /**
     * 上级部门
     */
    String MDEP_PAR = "mdep_par";

    /**
     * 上级部门名称
     */
    String MDEP_PAR_NAME = "mdep_par_name";
    
    /**
     * 层级编码
     */
    String MDEP_LVL_CODE = "mdep_lvl_code";

    /**
     * 部门类属性：简称
     */
    String MDEP_SNAME ="mdep_sname";
    
    /**
     * 部门全称
     */
    String MDEP_LNAME = "mdep_lname";

    /**
     * 部门类属性：法人公司
     */
    String MDEP_MCOP = "mdep_mcop";

    /**
     * 部门类属性：部门级别
     */
    String MDEP_GRADE = "mdep_grade";
    
    /**
     * 排序
     */
    String MDEP_SEQ = "mdep_seq";

    /**
     * 部门类属性：办公园区
     */
    String MDEP_RPAK = "mdep_rpak";

    /**
     * 办公园区名字 key
     */
    String MDEP_RPAK_NAME = "oepc_park";

    /**
     * 部门类属性：部门职责
     */
    String MDEP_DUTY = "mdep_duty";

    /**
     * 部门类属性：部门编制
     */
    String MDEP_TL_NUM = "mdep_tl_num";

    /**
     * 部门类属性：下层编制
     */
    String MDEP_LL_NUM = "mdep_ll_num";

    /**
     * 部门类属性：主责岗位
     */
    String MDEP_LEADER_POST = "mdep_leader_post";

    /**
     * 部门类属性：负责人
     */
    String MDEP_LEADER = "mdep_leader";

    /**
     * 职员人数
     */
    String MDEP_NUM_TOT = "mdep_num_tot";

    /**
     * 部门岗位数
     */
    String MDEP_POST_NUM = "mdepPostNum";
    
    /**
     * 生效时间
     */
    String MDEP_BEG = "mdep_beg";
    
    /**
     * 失效时间
     */
    String MDEP_END = "mdep_end";
    
    /**
     * 逻辑删除
     */
    String IS_DEL = "is_del";
    
    /**
     * 变更集合
     */
    String MDEP_CHAG_SET = "mdep_chag_set";

    /**
     * 部门类id
     */
    String MDEP_ID = "id";
}
