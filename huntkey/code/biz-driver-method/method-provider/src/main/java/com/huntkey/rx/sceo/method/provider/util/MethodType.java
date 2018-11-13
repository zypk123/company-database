package com.huntkey.rx.sceo.method.provider.util;

/**
 * Created by lulx on 2017/10/14 0014 下午 3:54
 */
public enum MethodType {

    EXECSYNC("0","同步"),
    EXECASYNC("1","异步"),
    EXECAUTO("2","自动方法"),
    EXECTIMING("3","定时执行")
    ;

    private String methodType;
    private String typeName;

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    MethodType(String methodType, String typeName) {
        this.methodType = methodType;
        this.typeName = typeName;
    }
}
