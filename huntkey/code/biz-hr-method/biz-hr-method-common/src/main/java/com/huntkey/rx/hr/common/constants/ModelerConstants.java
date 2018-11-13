
package com.huntkey.rx.hr.common.constants;

public interface ModelerConstants {
    /**
     *  EDM类名： 部门
     */
    String EDM_DEPTTREE = "depttree";
    /**
     *  EDM类名： 伙伴
     */
    String EDM_RELATION = "relation";
    /**
     * 部门类属性：编码
     */
    String MDEP_CODE = "mdep_code";
    /**
     * 部门类属性：名称
     */
    String MDEP_NAME = "mdep_name";
    /**
     * 部门类属性：主责岗位
     */
    String MDEP_LEADER_POST = "mdep_leader_post";
    /**
     * 部门类属性：部门变更集
     */
    String EDM_DEPTTREE_CHAG_SET = "depttree.mdep_chag_set";
    /**
     * 部门名称
     */
    String MDEP_NAME_HIS="mdep_name_his";
    /**
     * 部门变更集属性：部门全称
     */
    String MDEP_FNAME_HIS = "mdep_fname_his";
    
    /**
     * 部门变更集属性：生效日期
     */
    String MDEP_BEG_HIS = "mdep_beg_his";
    
    /**
     * 部门变更集属性：失效日期
     */
    String MDEP_END_HIS = "mdep_end_his";
    
    /**
     * 部门类属性：部门负责人集合
     */
    String EDM_DEPTTREE_CHGR_SET = "depttree.mdep_chgr_set";
    /**
     * 部门负责人集合属性：负责人员工类
     */
    String MDEP_CHGR_EMP = "mdep_chgr_emp";
    /**
     * 部门负责人集合属性：负责人岗位
     */
    String MDEP_CHGR_POST = "mdep_chgr_post";
    
    /**
     * 部门负责人集合属性：负责人员工类
     */
    String MDEP_CHGR_BEG = "mdep_chgr_beg";
    /**
     * 部门负责人集合属性：负责人员工类
     */
    String MDEP_CHGR_END = "mdep_chgr_end";
    
    /**
     *  EDM类名： 岗位
     */
    String EDM_JOBPOSITION = "jobposition";
    /**
     *  岗位类： 所属部门
     */
    String RPOS_DEPT = "rpos_dept";
    /**
     *  岗位类： 岗位编码
     */
    String RPOS_CODE = "rpos_code";
    /**
     *  岗位类： 岗位名称
     */
    String RPOS_NAME = "rpos_name";
    /**
     *  岗位类： 汇报岗位
     */
    String RPOS_PPOST = "rpos_ppost";
    /**
     *  岗位类： 职位
     */
    String RPOS_RPOF = "rpos_rpof";
    
    /**
     * EDM类名： 离职申请单类
     */
    String EDM_EMPRESIGNAPPLY = "empresignapply";

    /**
     * EDM类名： 合同签订单类
     */
    String EDM_CONTRACTSIGNAPPLY = "contractsignapply";

    /**
     * EDM类名： 合同签订单类-签约人员属性集表
     */
    String EDM_OCSOEMPSET = "contractsignapply.ocso_emp_set";

    /**
     * EDM类名： 员工类-合同记录属性集表
     */
    String EDM_REMPCONTSET = "employee.remp_cont_set";
    /***
     * 职位类
     */
    String EDM_POSITIONDEFINITION="positiondefinition";
    
    
    
}

