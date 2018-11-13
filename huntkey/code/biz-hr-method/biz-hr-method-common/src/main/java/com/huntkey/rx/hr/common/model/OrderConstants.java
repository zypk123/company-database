package com.huntkey.rx.hr.common.model;

/**
 * Created by xuyf on 2017/11/14 0014.
 */
public interface OrderConstants {

    //-----------以下“单据类型”用于校验时判断使用--------
    /**
     * 单据类型：部门结构异动单类型-新增直属
     */
    String ORDE_TYPE_ODSC_ADD = "odsc_01";
    /**
     * 单据类型：部门结构异动单类型-修改直属
     */
    String ORDE_TYPE_ODSC_MODIFY = "odsc_02";
    /**
     * 单据类型：部门结构异动单类型-剪切调出
     */
    String ORDE_TYPE_ODSC_MOVE = "odsc_03";
    /**
     * 单据类型：部门结构异动单类型-注销直属
     */
    String ORDE_TYPE_ODSC_CANCEL = "odsc_04";
    /**
     * 单据类型：部门责任人任免单类型
     */
    String ORDE_TYPE_ODCA = "odca_01";

    /**
     * 单据状态：临时
     */
    String ORDE_STATUS_1 = "1";
    /**
     * 单据状态：待审
     */
    String ORDE_STATUS_2 = "2";
    /**
     * 单据状态：待核
     */
    String ORDE_STATUS_3 = "3";
    /**
     * 单据状态：待批
     */
    String ORDE_STATUS_4 = "4";
    /**
     * 单据状态：完成
     */
    String ORDE_STATUS_5 = "5";
    /**
     * 单据状态：退回
     */
    String ORDE_STATUS_6 = "6";


    /**
     *  EDM类名： 单据类
     */
    String EDM_ORDER = "order";
    /**
     * 单据类属性：制单人
     */
    String ORDE_ADDUSER =  "orde_adduser";

    /**
     * 返回值 制单人名字
     */
    String ORDE_ADDUSER_NAME =  "orde_adduser_name";

    /**
     * 返回值 制单人工号
     */
    String ORDE_ADDUSER_NO =  "orde_adduser_no";

    /**
     * 单据类属性：制单时间
     */
    String ORDE_DATE =  "orde_date";
    /**
     * 单据类属性：制单岗位
     */
    String ORDE_DUTY =  "orde_duty";

    /**
     * 返回值：制单岗位名字
     */
    String ORDE_DUTY_NAME =  "orde_duty_name";

    /**
     * 单据类属性：制单代理岗位
     */
    String ORDE_PROXYUSER_POS =  "orde_proxyuser_pos";
    /**
     * 单据类属性：单据生效时间
     */
    String ORDE_EFFDATE =  "orde_effdate";
    /**
     * 单据类属性：更新员工
     */
    String ORDE_EMP =  "orde_emp";
    /**
     * 单据类属性：表单对象（表单类）
     */
    String ORDE_FORM_OBJ =  "orde_form_obj";
    /**
     * 单据类属性：上一版单据对象
     */
    String ORDE_LAST_VERSION_OBJ =  "orde_last_version_obj";
    /**
     * 单据类属性：单号
     */
    String ORDE_NBR =  "orde_nbr";
    /**
     * 单据类属性：审批流程实例
     */
    String ORDE_PROCOBJ =  "orde_procobj";
    /**
     * 单据类属性：代理制单人
     */
    String ORDE_PROXYUSER =  "orde_proxyuser";
    /**
     * 单据类属性：单据定义对象
     */
    String ORDE_RODE_OBJ =  "orde_rode_obj";
    /**
     * 单据类属性：单据状态
     */
    String ORDE_STATUS =  "orde_status";
    /**
     * 单据类属性：更新活动/岗位
     */
    String ORDE_UPDATE_POS =  "orde_update_pos";
    /**
     * 单据类属性：版本编号
     */
    String ORDE_VERSION =  "orde_version";

    /**
     * 单据类属性：制单部门
     */
    String ORDE_DEPT =  "orde_dept";

    /**
     * 返回值：制单部门名字
     */
    String ORDE_DEPT_NAME =  "orde_dept_name";
}
