package com.huntkey.rx.sceo.orm.common.type;

import com.huntkey.rx.edm.entity.EmployeeEntity;
import com.huntkey.rx.sceo.orm.common.util.OrmAccessUtil;

/**
 * Created by linziy on 2017/11/29.
 * SQL 函数封装
 */
public enum SQLFunctionEnum {
    AVG(0, "AVG", "返回某列的平均值"),
    COUNT(1, "COUNT", "返回某列的行数（不包括 NULL 值)"),
    MAX(4, "MAX", "返回某列的最高值"),
    MIN(5, "MIN", "返回某列的最低值"),
    SUM(1, "SUM", "求和函数");
    //    FIRST(2, "FIRST", "返回在指定的域中第一个记录的值"),
//    LAST(3, "LAST", "返回在指定的域中最后一个记录的值"),
//    EXIST(3, "EXIST", "是否存在");

    private int type;
    private String functionName;
    private String desc;

    private SQLFunctionEnum(int type, String functionName, String desc) {
        this.type = type;
        this.functionName = functionName;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getDesc() {
        return desc;
    }

}
