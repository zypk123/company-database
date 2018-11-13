package com.huntkey.rx.sceo.formula.common.model.vo;

import java.util.List;

/**
 * @author zhouyou on 2017/10/28.
 */
public class DeptTree {
    private String moniLvlCode;
    private List<DeptTree> childDept;
    private String moniNodeName;
    private String deptId;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getMoniNodeName() {
        return moniNodeName;
    }

    public void setMoniNodeName(String moniNodeName) {
        this.moniNodeName = moniNodeName;
    }

    public String getMoniLvlCode() {
        return moniLvlCode;
    }

    public void setMoniLvlCode(String moniLvlCode) {
        this.moniLvlCode = moniLvlCode;
    }

    public List<DeptTree> getChildDept() {
        return childDept;
    }

    public void setChildDept(List<DeptTree> childDept) {
        this.childDept = childDept;
    }
}
