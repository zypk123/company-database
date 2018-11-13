package com.huntkey.rx.sceo.formula.common.constant;

/**
 * @author chenfei on 2017/7/18.
 */
public enum FunInOrOutType {
    /**
     * 文本
     */
    TEXT("text", "文本"),
    /**
     * 日期
     */
    DATE("date", "日期"),
    /**
     * 整形
     */
    INT("int", "整形"),
    /**
     * 字符串
     */
    VARCHAR("varchar", "字符串"),
    /**
     * 布尔
     */
    BOOLEAN("boolean", "布尔"),
    /**
     * 浮点数
     */
    DECIMAL("decimal", "浮点数"),
    /**
     * 数字型
     */
    NUMBER("number", "数字型"),
    /**
     * 对象
     */
    OBJECT("object", "对象"),
    /**
     * 属性集
     */
    ASSEMBLE("assemble", "属性集"),
    /**
     * 对象实例
     */
    INSTANTIATE("instantiate", "对象实例"),
    /**
     * 计量单位
     */
    MEASUREMENT("measurement", "计量单位" ),
    /**
     * 对象链接
     */
    OBJECTLINK("objectLink", "对象链接"),
    /**
     * 枚举
     */
    ENUM("enum", "枚举"),
    /**
     * 卷积属性
     */
    CONVOLUTION("convolution", "卷积属性"),
    /**
     * 数值数组型
     */
    NUMBERARRAY("numberArray", "数值数组型"),
    /**
     * 任何
     */
    ANY("any", "任何");

    private String desc;
    private String type;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    FunInOrOutType(String type, String desc) {
        this.desc = desc;
        this.type = type;
    }

    public static FunInOrOutType getTypeByName(String typeName) {

        for (FunInOrOutType fiot : FunInOrOutType.values()) {
            if (fiot.type.equalsIgnoreCase(typeName)) {
                return fiot;
            }
        }

        throw new RuntimeException("The typeName: " + typeName + " can not be supported.");
    }

    @Override
    public String toString() {
        return desc;
    }
}
