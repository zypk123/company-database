package com.huntkey.rx.hr.common.model;


import java.io.Serializable;

/**
 * Created by xuyf on 2017/11/24 0024.
 */
public class DeptIsEditableDTO implements Serializable {

    private String type;

    private String date;

    private String[] deptCodes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[] getDeptCodes() {
        return deptCodes;
    }

    public void setDeptCodes(String[] deptCodes) {
        this.deptCodes = deptCodes;
    }
}
