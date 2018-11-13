package com.huntkey.rx.sceo.formula.common.constant;

/**
 * this constant for formula inside and outside communicating.
 * <p>
 * section: [4000 ~ 4999]
 * <p>
 * @author chenfei on 2017/7/6.
 */
public enum FormulaCode {

    /**
     * 公式预计算错误
     */
    FORMULA_PRE_CALC_ERR(4000, "公式预计算错误"),
    /**
     * 公式校验未通过
     */
    FORMULA_VALIDATE_ERR(4002, "公式校验未通过"),
    /**
     * ORM查询错误
     */
    ORM_QUERY_DATA_ERR(4001, "ORM查询错误"),
    /**
     * 自定义函数错误
     */
    FUNCTION_DEFINE_ERR(4003, "自定义函数错误"),
    /**
     * EDM查询数据类型错误
     */
    EDM_QUERY_DATA_TYPE_ERR(4004, "EDM查询数据类型错误"),
    /**
     * EDM查询属性类型错误
     */
    EDM_QUERY_PROPERTY_ERR(4005, "EDM查询属性类型错误"),;

    /**
     * code of some state.
     */
    private int stateCode;
    /**
     * description of some state.
     */
    private String stateDesc;

    public int getStateCode() {
        return stateCode;
    }

    FormulaCode(int stateCode, String stateDesc) {
        this.stateCode = stateCode;
        this.stateDesc = stateDesc;
    }
}
