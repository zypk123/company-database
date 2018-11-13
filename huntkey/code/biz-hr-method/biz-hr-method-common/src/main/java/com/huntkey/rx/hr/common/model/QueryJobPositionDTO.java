package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by weijian on 2017/12/1.
 */
public class QueryJobPositionDTO  implements Serializable{
    private static final long serialVersionUID = 1L;
    @JSONField(name="id")
    private String rposId;//岗位ID
    @JSONField(name="rpos_emp")
    private String rposEmp;//岗位员工
    @JSONField(name="rpos_code")
    private String rposCode;//岗位编码
    @JSONField(name="rpos_name")
    private String rposName;//岗位名称
    @JSONField(name="rpos_grade")
    private String rposGrade;//岗级
    @JSONField(name="rpos_dept")
    private String rposDept;//所属部门
    @JSONField(name="rpos_duty_type")
    private String rposDutyType;//任职方式 0：任职 1：兼职 2：代职

    private String inThisDept;//在本部门任职 1：在 0：不在

    public String getRposId() {
        return rposId;
    }

    public void setRposId(String rposId) {
        this.rposId = rposId;
    }

    public String getRposEmp() {
        return rposEmp;
    }

    public void setRposEmp(String rposEmp) {
        this.rposEmp = rposEmp;
    }

    public String getRposCode() {
        return rposCode;
    }

    public void setRposCode(String rposCode) {
        this.rposCode = rposCode;
    }

    public String getRposName() {
        return rposName;
    }

    public void setRposName(String rposName) {
        this.rposName = rposName;
    }

    public String getRposGrade() {
        return rposGrade;
    }

    public void setRposGrade(String rposGrade) {
        this.rposGrade = rposGrade;
    }

    public String getRposDept() {
        return rposDept;
    }

    public void setRposDept(String rposDept) {
        this.rposDept = rposDept;
    }

    public String getRposDutyType() {
        return rposDutyType;
    }

    public void setRposDutyType(String rposDutyType) {
        this.rposDutyType = rposDutyType;
    }

    public String getInThisDept() {
        return inThisDept;
    }

    public void setInThisDept(String inThisDept) {
        this.inThisDept = inThisDept;
    }
}
