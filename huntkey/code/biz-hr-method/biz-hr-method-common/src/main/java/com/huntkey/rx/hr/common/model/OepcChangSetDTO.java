package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by chengchen on 2017/11/20.
 */
public class OepcChangSetDTO {
    /**
     * id
     */
    @JSONField(name="id")
    String id;

    /**
     * pid
     */

    @JSONField(name="pid")
    String pid;

    /**
     * 员工id
     */
    @JSONField(name="oepc_emp")
    String oepcEmp;

    /**
     * 岗位id
     */
    @JSONField(name="oepc_post")
    String oepcPost;

    /**
     * 部门id
     */
    @JSONField(name="oepc_dept")
    String oepcDept;
    /**
     * 工号
     */
    @JSONField(name="remp_no")
    String rempNo;

    /**
     * 姓名
     */
    @JSONField(name="remp_name")
    String rempName;

    /**
     * 性别
     */
    @JSONField(name="remp_gender")
    String rempGender;

    /**
     * 年龄
     */
    @JSONField(name="rempAge")
    String rempAge;
    /**
     * 岗级
     */
    @JSONField(name="remp_pgrade")
    String rempPgrade;

    /**
     * 法人公司
     */
    @JSONField(name="remp_mcop")
    String rempMcop;

    /**
     * 在职月数
     */
    @JSONField(name="workMonths")
    String workMonths;

    /**
     * 在岗月数
     */
    @JSONField(name="onJobMonths")
    String onJobMonths;

    /**
     * 工龄
     */
    @JSONField(name="workYears")
    String workYears;

    /**
     * 任职方式
     */
    @JSONField(name="rpos_duty_type")
    String rposDutyType;


    /**
     * 岗位名称
     */
    @JSONField(name="rpos_name")
    String rposName;

    /**
     * 直属上级
     */
    @JSONField(name="rpos_emp")
    String rposEmp;

    /**
     * 学位
     */
    @JSONField(name="remp_rsch")
    String rempRsch;

    /**
     * 学历
     */
    @JSONField(name="remp_degree")
    String rempDegree;

    /**
     * 专业
     */
    @JSONField(name="remp_major")
    String rempMajor;

    public String getOepcEmp() {
        return oepcEmp;
    }

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

    public void setOepcEmp(String oepcEmp) {
        this.oepcEmp = oepcEmp;
    }

    public String getOepcPost() {
        return oepcPost;
    }

    public void setOepcPost(String oepcPost) {
        this.oepcPost = oepcPost;
    }

    public String getOepcDept() {
        return oepcDept;
    }

    public void setOepcDept(String oepcDept) {
        this.oepcDept = oepcDept;
    }

    public String getRempNo() {
        return rempNo;
    }

    public void setRempNo(String rempNo) {
        this.rempNo = rempNo;
    }

    public String getRempName() {
        return rempName;
    }

    public void setRempName(String rempName) {
        this.rempName = rempName;
    }

    public String getRempGender() {
        return rempGender;
    }

    public void setRempGender(String rempGender) {
        this.rempGender = rempGender;
    }

    public String getRempAge() {
        return rempAge;
    }

    public void setRempAge(String rempAge) {
        this.rempAge = rempAge;
    }

    public String getRempPgrade() {
        return rempPgrade;
    }

    public void setRempPgrade(String rempPgrade) {
        this.rempPgrade = rempPgrade;
    }

    public String getRempMcop() {
        return rempMcop;
    }

    public void setRempMcop(String rempMcop) {
        this.rempMcop = rempMcop;
    }

    public String getWorkMonths() {
        return workMonths;
    }

    public void setWorkMonths(String workMonths) {
        this.workMonths = workMonths;
    }

    public String getOnJobMonths() {
        return onJobMonths;
    }

    public void setOnJobMonths(String onJobMonths) {
        this.onJobMonths = onJobMonths;
    }

    public String getWorkYears() {
        return workYears;
    }

    public void setWorkYears(String workYears) {
        this.workYears = workYears;
    }

    public String getRposDutyType() {
        return rposDutyType;
    }

    public void setRposDutyType(String rposDutyType) {
        this.rposDutyType = rposDutyType;
    }

    public String getRposName() {
        return rposName;
    }

    public void setRposName(String rposName) {
        this.rposName = rposName;
    }

    public String getRposEmp() {
        return rposEmp;
    }

    public void setRposEmp(String rposEmp) {
        this.rposEmp = rposEmp;
    }

    public String getRempRsch() {
        return rempRsch;
    }

    public void setRempRsch(String rempRsch) {
        this.rempRsch = rempRsch;
    }

    public String getRempDegree() {
        return rempDegree;
    }

    public void setRempDegree(String rempDegree) {
        this.rempDegree = rempDegree;
    }

    public String getRempMajor() {
        return rempMajor;
    }

    public void setRempMajor(String rempMajor) {
        this.rempMajor = rempMajor;
    }
}
