package com.huntkey.rx.sceo.orm.common.type;

/**
 * Created by linziy on 2017/11/20.
 */
public enum TableEnum {
    Main(1, "", "主表"),
    Link(2, "_linkdetail", "联动表"),
    AttributeSet(3, "", "属性集"),
    Others(4, "", "别的扩展表");

    private int num;
    private String suffix;
    private String desc;

    /**
     * @param num    序号
     * @param suffix 表名后缀填充字符串
     * @param desc   描述
     */
    private TableEnum(int num, String suffix, String desc) {
        this.num = num;
        this.suffix = suffix;
        this.desc = desc;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public int getNum() {
        return num;
    }

    public String getDesc() {
        return desc;
    }


}
