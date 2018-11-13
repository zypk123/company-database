package com.huntkey.rx.sceo.orm.common.type;

import com.huntkey.rx.sceo.orm.common.constant.ConstantRegex;

/**
 * Created by linziy on 2017/11/23.
 */
public enum SQLCurdEnum {

    INSERT(1, "INSERT", "插入"),
    UPDATE(2, "UPDATE", "更新"),
    SELECT(3, "SELECT", "查询"),
    DELETE(4, "DELETE", "删除"),
    EXEC(5,"EXEC", "执行存储过程");

    private int sequence; //序列
    private String sqlOp;//sql 的操作方式
    private String desc;// 操作符

    private SQLCurdEnum(int sequence, String sqlOp, String desc) {
        this.sequence = sequence;
        this.sqlOp = sqlOp;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getSequence() {
        return sequence;
    }

    public String getSqlOp() {
        return sqlOp;
    }

    public String getSqlOpWithSpace() {
        return sqlOp + " ";
    }

    public String getSqlOpRegex() {
        return sqlOp + ConstantRegex.AT_LEAST_ONE_SPACE;
    }


}
