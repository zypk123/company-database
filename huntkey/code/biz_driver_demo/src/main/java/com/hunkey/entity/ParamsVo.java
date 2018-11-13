package com.hunkey.entity;

import java.util.Map;

/**
 * Created by lulx on 2017/10/17 0017 上午 9:42
 */
public class ParamsVo {
    private String className;
    private String methodName;
    /*
        id
     */
    private Map<String, Object> paramObj;

    public ParamsVo() {
    }

    public ParamsVo(String className, String methodName, Map<String, Object> paramObj) {
        this.className = className;
        this.methodName = methodName;
        this.paramObj = paramObj;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Map<String, Object> getParamObj() {
        return paramObj;
    }

    public void setParamObj(Map<String, Object> paramObj) {
        this.paramObj = paramObj;
    }
}
