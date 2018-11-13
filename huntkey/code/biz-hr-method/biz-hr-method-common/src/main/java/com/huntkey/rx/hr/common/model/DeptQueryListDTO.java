package com.huntkey.rx.hr.common.model;

/**
 * Created by xuyf on 2017/11/27 0027.
 */
public class DeptQueryListDTO {

    private String[] deptIds;

    private String date;

    private Integer complement = 0;

    public String[] getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String[] deptIds) {
        this.deptIds = deptIds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getComplement() {
        return complement;
    }

    public void setComplement(Integer complement) {
        this.complement = complement;
    }
}
