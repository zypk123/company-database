package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.io.Serializable;

/**
 * Created by weijian on 2017/12/2.
 */
public class OdcsChrgSetDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "pid")
    private String pid;
    @JSONField(name = "classname")
    private String classname;
    @JSONField(name = "odcs_chrg_type")
    private String odcsChrgType;//责任人类型
    @JSONField(name = "odcs_dept")
    private String odcsDept;//部门
    @JSONField(name = "odcs_dtyp_old")
    private String odcsDtypOld;//任职方式_旧
    @JSONField(name = "odcs_duty_type")
    private String odcsDutyType;//任职方式
    @JSONField(name = "odcs_emp")
    private String odcsEmp;//责任人
    @JSONField(name = "odcs_emp_old")
    private String odcsEmpOld;//负责人_旧
    @JSONField(name = "odcs_post")
    private String odcsPost;//岗位
    @JSONField(name = "odcs_post_old")
    private String odcsPostOld;//岗位_旧

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getOdcsChrgType() {
        return odcsChrgType;
    }

    public void setOdcsChrgType(String odcsChrgType) {
        this.odcsChrgType = odcsChrgType;
    }

    public String getOdcsDept() {
        return odcsDept;
    }

    public void setOdcsDept(String odcsDept) {
        this.odcsDept = odcsDept;
    }

    public String getOdcsDtypOld() {
        return odcsDtypOld;
    }

    public void setOdcsDtypOld(String odcsDtypOld) {
        this.odcsDtypOld = odcsDtypOld;
    }

    public String getOdcsDutyType() {
        return odcsDutyType;
    }

    public void setOdcsDutyType(String odcsDutyType) {
        this.odcsDutyType = odcsDutyType;
    }

    public String getOdcsEmp() {
        return odcsEmp;
    }

    public void setOdcsEmp(String odcsEmp) {
        this.odcsEmp = odcsEmp;
    }

    public String getOdcsEmpOld() {
        return odcsEmpOld;
    }

    public void setOdcsEmpOld(String odcsEmpOld) {
        this.odcsEmpOld = odcsEmpOld;
    }

    public String getOdcsPost() {
        return odcsPost;
    }

    public void setOdcsPost(String odcsPost) {
        this.odcsPost = odcsPost;
    }

    public String getOdcsPostOld() {
        return odcsPostOld;
    }

    public void setOdcsPostOld(String odcsPostOld) {
        this.odcsPostOld = odcsPostOld;
    }
}
