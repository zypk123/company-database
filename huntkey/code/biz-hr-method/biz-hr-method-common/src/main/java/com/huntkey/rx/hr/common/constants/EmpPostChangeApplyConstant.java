package com.huntkey.rx.hr.common.constants;

/**
 * Created by chengchen on 2017/11/20.
 */
public interface EmpPostChangeApplyConstant {

    String EDM_NAME = "emppostchangeapply";
    String OEPC_BEG = "oepc_beg";
    String EDM_OEPC_CHANG_SET = "emppostchangeapply.oepc_chang_set";
    String ID = "id";
    String PID = "pid";
    String OEPC_REMARK = "oepc_remark";
    String OEPC_TYPE = "oepc_type";
    String OEPC_DEPT = "oepc_dept";
    String OEPC_POST = "oepc_post";
    String OEPC_EMP = "oepc_emp";
    String ORDE_STATUS = "orde_status";
    String ORDE_NBR = "orde_nbr";
    String OEPC_DTYP_TYPE = "oepc_dtyp_type";
    String OEPC_DEPT_OLD = "oepc_dept_old";
    String OEPC_POST_OLD = "oepc_post_old";
    String OEPC_DTYP_OLD = "oepc_dtyp_old";
    /**
     * 是否保留原岗位 "0"不保留 "1"保留
     */
    String OEPC_IS_KEEP = "oepc_iskeep";
    String OEPC_CHGR_TYPE = "oepc_chgr_type";
    String EMP_NAME = "empName";
    String DEPT_NAME = "deptName";
    String POST_NAME = "postName";
    String OLD_DEPT_NAME = "oldDeptName";
    String OLD_POST_NAME = "oldPostName";
    String ADD_ORDER = "0";
    String EDIT_ORDER  = "1";
    String CANCEL_ORDER = "3";
    String SAVE ="1";
    String PENDING ="2";
    /**
     * 任职方式：任职
     */
    String DUTY_TYPE_0 = "0";

    /**
     * 任职方式：兼职
     */
    String DUTY_TYPE_1 = "1";

    /**
     * 任职方式:代职
     */
    String DUTY_TYPE_2 = "2";

    /**
     *查询员工类方法
     */
    int EMP_TYPE_2 = 2;
    // 返回值
    String OEPC_CHANG_SET = "oepcChangSet";
}
