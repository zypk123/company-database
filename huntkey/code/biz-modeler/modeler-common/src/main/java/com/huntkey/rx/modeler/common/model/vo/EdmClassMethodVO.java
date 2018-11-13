package com.huntkey.rx.modeler.common.model.vo;

import java.util.Arrays;

/**
 * Created by licj on 2017/7/17.
 */
public class EdmClassMethodVO {

    String classId;
    String[] methodIds;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String[] getMethodIds() {
        return methodIds;
    }

    public void setMethodIds(String[] methodIds) {
        this.methodIds = methodIds;
    }

    @Override
    public String toString() {
        return "EdmClassMethodVO{" +
                "classId='" + classId + '\'' +
                ", methodIds=" + Arrays.toString(methodIds) +
                '}';
    }
}
