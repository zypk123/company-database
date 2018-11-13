package com.huntkey.rx.hr.common.model;

/**
 * Created by xuyf on 2017/11/14 0014.
 */
public interface OdscChagSetConstants {

    String ODSC_FLAG_NONE = "0";
    String ODSC_FLAG_ADD = "1";
    String ODSC_FLAG_MODIFY = "2";
    String ODSC_FLAG_MODIFY_STAFFING = "3";
    String ODSC_FLAG_MOVE = "4";
    String ODSC_FLAG_CANCEL = "5";

    /**
     * EDM类名：部门结构异动单.异动列表
     */
    String EDM_ODSC_CHAG_SET = "deptstuchangeorder.odsc_chag_set";

    /**
     * 异动列表属性：标志
     */
    String ODSC_FLAG = "odsc_flag";
    /**
     * 异动列表属性：部门编码
     */
    String ODSC_DEPT_CODE = "odsc_dept_code";

    /**
     * 异动列表属性：部门对象ID
     */
    String ODSC_DEPT_ID = "odsc_dept_id";
    /**
     * 异动列表属性：上级部门对象Id
     */
    String ODSC_PDEPT = "odsc_pdept";

    /**
     * 异动列表属性：上级部门名称
     */
    String ODSC_PDEPT_NAME = "odsc_pdept_name";

    /**
     * 异动列表属性：层级码
     */
    String ODSC_LVL = "odsc_lvl";
    /**
     * 异动列表属性：部门名称
     */
    String ODSC_NAME = "odsc_name";
    /**
     * 异动列表属性：部门简称
     */
    String ODSC_SNAME = "odsc_sname";
    /**
     * 异动列表属性：部门级别
     */
    String ODSC_DGRADE = "odsc_dgrade";
    /**
     * 异动列表属性：办公园区
     */
    String ODSC_RPAK = "odsc_rpak";
    /**
     * 异动列表属性：法人公司
     */
    String ODSC_MCOP = "odsc_mcop";
    /**
     * 异动列表属性：部门职责
     */
    String ODSC_FUNCTION = "odsc_function";
    /**
     * 异动列表属性：本层编制
     */
    String ODSC_TL_PNUM = "odsc_tl_pnum";
    /**
     * 异动列表属性：下层编制
     */
    String ODSC_LL_PNUM = "odsc_ll_pnum";

    /**
     * 部门岗位数
     */
    String MDEP_POST_NUM = "mdepPostNum";

    /**
     * 异动列表属性：上级部门_旧
     */
    String ODSC_PDEPT_OLD = "odsc_pdept_old";
    String ODSC_PDEPT_NAME_OLD = "odsc_pdept_name_old";
    /**
     * 异动列表属性：部门名称_旧
     */
    String ODSC_NAME_OLD = "odsc_name_old";
    /**
     * 异动列表属性：部门简称_旧
     */
    String ODSC_SNAME_OLD = "odsc_sname_old";
    /**
     * 异动列表属性：部门级别_旧
     */
    String ODSC_DGRADE_OLD = "odsc_dgrade_old";
    /**
     * 异动列表属性：办公园区_旧
     */
    String ODSC_RPAK_OLD = "odsc_rpak_old";
    /**
     * 异动列表属性：法人公司_旧
     */
    String ODSC_MCOP_OLD = "odsc_mcop_old";
    /**
     * 异动列表属性：部门职责_旧
     */
    String ODSC_FUNC_OLD = "odsc_func_old";
    /**
     * 异动列表属性：本层编制_旧
     */
    String ODSC_TL_OLD = "odsc_tl_old";
    /**
     * 异动列表属性：下层编制_旧
     */
    String ODSC_LL_OLD = "odsc_ll_old";

}
