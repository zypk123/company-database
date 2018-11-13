package com.huntkey.rx.sceo.serviceCenter.provider.orm.enums;

/**
 * Created by yangcong on 2017/6/9.
 */
public enum ResultEnum {
    INDEX_ISEXIST("索引已经存在"),TABLE_ISEXIST("表已经存在"),SAVE_SUCCESS("保存成功"),MODIFY_SUCCESS("修改成功"),
    INDEX_TABLE_CREATE_SUCCESS("索引和表创建成功"),DELETE_SUCCESS("删除成功");

    private final String value;

    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
    ResultEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
