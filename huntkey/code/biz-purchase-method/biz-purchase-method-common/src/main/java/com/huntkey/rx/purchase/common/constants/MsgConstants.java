package com.huntkey.rx.purchase.common.constants;

/**
 * errMsg 常量
 */
public interface MsgConstants {

    String MSG_PU_PAGE_START_MIN = "当前页码不能小于1";

    String MSG_PU_PAGE_ROWS_MIN = "分页大小不能小于1";

    String MSG_ORDER_NOT_BLANK_TYPE = "单据类型不能为空";

    String MSG_ORDER_NOT_BLANK_STATUS = "单据状态不能为空";

    String MSG_PU_NOT_BLANK_DEPT_BEGIN_DATE = "生效日期不能为空";

    String MSG_PU_PATTERN_DEPT_BEGIN_DATE = "生效日期格式错误，应为yyyy-MM-dd";

    String MSG_PU_NOT_NULL_DEPT_LEVEL = "展开层数不能为空";

    String MSG_PU_MIN_DEPT_LEVEL = "展开层数不能小于1";

    String MSG_PU_RANG_DEPT_COMPLEMENT = "是否补全关联属性名,0否1是";

    String MSG_PU_NOT_BLANK_HANDLER_TYPE = "流程处理类型不能为空";

    //物品类

    String MSG_PU_NOT_BLANK_GOODS_ID = "物品类id不能为空";

    String MSG_PU_NOT_BLANK_GOODS_FEA_ID = "物品特征类id不能为空";

    String MSG_PU_NOT_BLANK_GOODS_MAINT_ID = "物品维护单id不能为空";
}

