package com.huntkey.rx.sceo.formula.common.params;

/**
 * @author lulx on 2017/8/18 0018 下午 3:24
 */
public enum OrmOperator {
    /**
     * 大于
     */
    GT(">","大于","<="),

    /**
     * 大于
     */
    LT("<","小于",">="),

    /**
     * 大于
     */
    EG("=","等于","="),

    /**
     * 大于
     */
    LE("<=","小于等于",">"),

    /**
     * 大于
     */
    GE(">=","大于等于","<"),

    /**
     * 大于
     */
    NEG("!=","不等于","!="),

    /**
     * 大于
     */
    INC("like","包含","like"),

    /**
     * 大于
     */
    NINC("not like","不包含","not like")
    ;
    private String operator;
    private String name;
    private String reversOperator;

    OrmOperator(String operator, String name, String reversOperator) {
        this.operator = operator;
        this.name = name;
        this.reversOperator = reversOperator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReversOperator() {
        return reversOperator;
    }

    public static OrmOperator getOrmOperator(String operator){
        for (OrmOperator ormOperator : OrmOperator.values()) {
            if (ormOperator.getOperator().equalsIgnoreCase(operator.trim())) {
                return ormOperator;
            }
        }
        throw new RuntimeException("暂不支持 " + operator + " 运算符");
    }
}
