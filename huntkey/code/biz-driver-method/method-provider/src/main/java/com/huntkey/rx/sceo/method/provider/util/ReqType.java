package com.huntkey.rx.sceo.method.provider.util;

/**
 * Created by lulx on 2017/10/16 0016 下午 4:13
 */
public enum ReqType {
    GET("get", "查询", "0"),
    POST("post", "增加", "1"),
    PUT("put", "更新", "2"),
    PATCH("patch", "局部更新", "3"),
    DELETE("delete", "删除", "4")
    ;

    private String reqType;
    private String dbTyep;
    private String typeCode;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getDbTyep() {
        return dbTyep;
    }

    public void setDbTyep(String dbTyep) {
        this.dbTyep = dbTyep;
    }

    ReqType(String reqType, String dbTyep, String typeCode) {
        this.reqType = reqType;
        this.dbTyep = dbTyep;
        this.typeCode = typeCode;
    }
}
