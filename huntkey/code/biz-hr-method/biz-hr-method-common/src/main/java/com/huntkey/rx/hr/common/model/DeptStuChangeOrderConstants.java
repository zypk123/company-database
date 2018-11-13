package com.huntkey.rx.hr.common.model;

/**
 * Created by xuyf on 2017/11/14 0014.
 */
public interface DeptStuChangeOrderConstants {


    /**
     * 部门结构异动单类型：新增直属
     */
    String ODSC_TYPE_ADD_ID = "0";
    /**
     * 部门结构异动单类型：修改直属
     */
    String ODSC_TYPE_MODIFY_ID = "1";
    /**
     * 部门结构异动单类型：剪切调出
     */
    String ODSC_TYPE_MOVE_ID = "2";
    /**
     * 部门结构异动单类型：注销直属
     */
    String ODSC_TYPE_CANCEL_ID = "3";

    /**
     * EDM类名： 部门结构异动单
     */
    String EDM_DEPT_STU_CHANGE_ORDER = "deptstuchangeorder";

    /**
     * 父类属性-单据类型
     */
    String ORDE_STATUS = "orde_status";

    /**
     * 部门结构异动单属性：起始部门
     */
    String ODSC_DEPT_BEG = "odsc_dept_beg";
    /**
     * 部门结构异动单属性：单据类型
     */
    String ODSC_TYPE = "odsc_type";
    /**
     * 部门结构异动单属性：生效时间
     */
    String ODSC_BEG = "odsc_beg";
    /**
     * 部门结构异动单属性：备注
     */
    String ODSC_REMARK = "odsc_remark";


}
