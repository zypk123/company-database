package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by weijian on 2017/12/4.
 */
public class QueryDeptEmployeesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @JSONField(name = "rpos_emp")
    private String rposEmp;//任职人
    @JSONField(name = "remp_id")
    private String rempId;//员工ID
    @JSONField(name = "remp_name")
    private String rempName;//员工姓名
    @JSONField(name = "rpos_name")
    private String rposName;//职位名称
    @JSONField(name = "id")
    private String rposId;//岗位ID
    @JSONField(name = "rpos_duty_type")
    private String rposDutyType;//任职方式
    @JSONField(name = "rpos_code")
    private String rposCode;//职位代码
    @JSONField(name = "remp_no")
    private String rempNo;//工号

    public String getRposEmp() {
        return rposEmp;
    }

    public void setRposEmp(String rposEmp) {
        this.rposEmp = rposEmp;
    }

    public String getRempId() {
        return rempId;
    }

    public void setRempId(String rempId) {
        this.rempId = rempId;
    }

    public String getRempName() {
        return rempName;
    }

    public void setRempName(String rempName) {
        this.rempName = rempName;
    }

    public String getRposName() {
        return rposName;
    }

    public void setRposName(String rposName) {
        this.rposName = rposName;
    }

    public String getRposId() {
        return rposId;
    }

    public void setRposId(String rposId) {
        this.rposId = rposId;
    }

    public String getRposDutyType() {
        return rposDutyType;
    }

    public void setRposDutyType(String rposDutyType) {
        this.rposDutyType = rposDutyType;
    }

    public String getRposCode() {
        return rposCode;
    }

    public void setRposCode(String rposCode) {
        this.rposCode = rposCode;
    }

    public String getRempNo() {
        return rempNo;
    }

    public void setRempNo(String rempNo) {
        this.rempNo = rempNo;
    }
}
