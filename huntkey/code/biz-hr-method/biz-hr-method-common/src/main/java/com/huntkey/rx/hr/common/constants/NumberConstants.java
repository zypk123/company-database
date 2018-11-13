package com.huntkey.rx.hr.common.constants;

/**
 * Created by xuyf on 2017/11/21 0021.
 */
public interface NumberConstants {

    //--------------接口所需字段名----------------

    String EDMN_ENCODE = "edmnEncode";

    String EDMN_TYPE = "edmnType";


    //--------------编号前缀----------------
    /**
     * 编号规则前缀：部门结构异动单-新增
     */
    String PREFIX_DEPT_STU_CHANGE_ORDER_ADD = "HR01";

    /**
     * 编号规则前缀：部门结构异动单-修改
     */
    String PREFIX_DEPT_STU_CHANGE_ORDER_MODIFY = "HR02";

    /**
     * 编号规则前缀：部门结构异动单-调动
     */
    String PREFIX_DEPT_STU_CHANGE_ORDER_MOVE = "HR03";

    /**
     * 编号规则前缀：部门结构异动单-注销
     */
    String PREFIX_DEPT_STU_CHANGE_ORDER_CANCEL = "HR04";

    /**
     * 编号规则前缀：部门责任人任免单
     */
    String PREFIX_DEPT_CHARGER_APPLY_ORDER = "HR05";

    /**
     * 编号规则前缀：职位定义维护单
     */
    String PREFIX_POST_DEFINITION_ORDER = "HR06";

    /**
     * 编号规则前缀：部门岗位设置单
     */
    String PREFIX_DEPT_POST_SET_ORDER_ADD = "HR07";

    /**
     * 编号规则前缀：部门岗位修改单
     */
    String PREFIX_DEPT_POST_SET_ORDER_MODIFY = "HR08";

    /**
     * 编号规则前缀：部门岗位注销单
     */
    String PREFIX_DEPT_POST_SET_ORDER_CANCEL = "HR09";

    /**
     * 编号规则前缀：职员入职单
     */
    String PREFIX_EMPLOYEE_ENTRY_APPLY = "HR10";

    /**
     * 编号规则前缀：职员档案变更单
     */
    String PREFIX_EMPLOYEE_FILES_MODIFY = "HR11";

    /**
     * 编号规则前缀：员工岗位新增单
     */
    String PREFIX_EMP_POST_CHANGE_APPLY_ADD = "HR12";

    /**
     * 编号规则前缀：员工岗位调整单
     */
    String PREFIX_EMP_POST_CHANGE_APPLY_MODIFY = "HR13";

    /**
     * 编号规则前缀：员工岗位注销单
     */
    String PREFIX_EMP_POST_CHANGE_APPLY_CANCEL = "HR14";

    /**
     * 编号规则前缀：转正申请单
     */
    String PREFIX_EMP_POSTIVE_APPLY = "HR15";

    /**
     * 编号规则前缀：离职申请单
     */
    String PREFIX_EMP_RESIGN_APPLY = "HR16";

    /**
     * 编号规则前缀：离职手续单
     */
    String PREFIX_EMP_RESIGN_PROCEDURE = "HR17";

    /**
     * 编号规则前缀：合同签订单
     */
    String PREFIX_CONTRACT_SIGN_APPLY = "HR18";

    /**
     * 编号规则前缀：职类新增单
     */
    String PREFIX_POST_ORDER_ADD = "HR19";

    /**
     * 编号规则前缀：部门编码
     */
    String PREFIX_DEPT_CODE = "Department";
    /**
     * 编号规则前缀：职位编码
     */
    String PREFIX_POSITION_CODE = "Position";
    /**
     * 编号规则前缀：岗位编码
     */
    String PREFIX_QUARTERS_CODE = "Quarters";
    /**
     * 编号规则前缀：工号
     */
    String PREFIX_EMP_NO = "";


    //--------------规则类型----------------
    //规则类型（edmnType）所对应的前缀（edmnEncode）参照注释
    /**
     * 1：字符串+‘-’+2位年+2位月+4位流水 (HR01-18)
     */
    int TYPE_1 = 1;
    /**
     * 2：字符串+五位流水 (D)
     */
    int TYPE_2 = 2;
    /**
     * 3：字符串+四位流水 (P、null、"")
     */
    int TYPE_3 = 3;
    /**
     * 4：字符串+2位年+2位月+4为流水 (null、“”)
     */
    int TYPE_4 = 4;


}
