package com.huntkey.rx.hr.common.constants;

/**
 * errMsg 常量
 */
public interface MsgConstants {

    /**
     * errMsg: 部门名称不能为空
     */
    String MSG_HR_NOT_BLANK_DEPT_ID = "部门ID不能为空";
    /**
     * errMsg: 部门名称不能为空
     */
    String MSG_HR_NOT_BLANK_DEPT_NAME = "部门名称不能为空";
    /**
     * validate: 当前页码不能小于1
     */
    String MSG_HR_PAGE_START_MIN = "当前页码不能小于1";

    /**
     * validate: 分页大小不能小于1
     */
    String MSG_HR_PAGE_ROWS_MIN = "分页大小不能小于1";
    /**
     * errMsg: 员工ID不能为空
     */
    String MSG_HR_NOT_BLANK_EMPLYEE_ID = "员工ID不能为空";

    //离职申请单类
    /**
     * errMsg: 离职申请单类不能为空
     */
    String MSG_HR_NOT_BLANK_OERA_ID = "离职申请单类ID不能为空";

    String MSG_HR_NOT_FOUND_OERA_ID = "离职申请单类ID不存在";

    /**
     * errMsg: 合同签订单类id不能为空
     */
    String MSG_HR_NOT_BLANK_CS_ID = "合同签订单类ID不能为空";

    /**
     * errMsg: 对应合同签订单不存在
     */
    String MSG_HR_NOT_EXIST_CS_DATA = "对应合同签订单不存在";

    /**
     * errMsg: 对应合同签订单-签约人员属性集不存在
     */
    String MSG_HR_NOT_EXIST_CS_OES = "对应合同签订单-签约人员属性集不存在";

    String MSG_HR_NOT_BLANK_POST_ID = "岗位ID不能为空";

    String MSG_HR_NOT_BLANK_POST_ORDER_ID = "岗位单据ID不能为空";

    String MSG_ORDER_NOT_BLANK_TYPE = "单据类型不能为空";

    String MSG_ORDER_NOT_BLANK_STATUS = "单据状态不能为空";

    String MSG_HR_NOT_BLANK_DEPT_BEGIN_DATE = "生效日期不能为空";

    String MSG_HR_PATTERN_DEPT_BEGIN_DATE = "生效日期格式错误，应为yyyy-MM-dd";

    String MSG_HR_NOT_NULL_DEPT_LEVEL = "展开层数不能为空";

    String MSG_HR_MIN_DEPT_LEVEL = "展开层数不能小于1";

    String MSG_HR_RANG_DEPT_COMPLEMENT = "是否补全关联属性名,0否1是";

    String MSG_HR_NOT_BLANK_HANDLER_TYPE = "流程处理类型不能为空";

    String MSG_HR_ORM_CALL_EXCEPTION = "调用ORM查询接口出现异常";

    String MSG_HR_SAVE_DEPT_ODSC_ERR = "保存部门调动单异常";

    String MSG_HR_UPDATE_DEPT_ODSC_ERR = "修改部门调动单异常";

    String MSG_HR_POSTION_LIST_NOT_FOUND = "没有对应的职位列表";


}

