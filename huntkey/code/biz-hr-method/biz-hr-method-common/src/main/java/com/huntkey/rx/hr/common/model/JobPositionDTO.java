package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by weijian on 2017/11/18.
 */
public class JobPositionDTO {
    @JSONField(name="id")
    String id;
    /**
     * 岗位类属性：所属部门
     */
    @JSONField(name="rpos_dept")
    String rposDept;
    /**
     * 岗位类属性：职位
     */
    @JSONField(name="rpos_rpof")
    String rposRpof;
    /**
     * 岗位类属性：岗位编码
     */
    @JSONField(name="rpos_code")
    String rposCode;
    /**
     * 岗位类属性：岗位名称
     */
    @JSONField(name="rpos_name")
    String rposName;
    /**
     * 岗位类属性：岗级
     */
    @JSONField(name="rpos_grade")
    String rposGrade;
    /**
     * 岗位类属性：汇报岗位
     */
    @JSONField(name="rpos_ppost")
    String rposPpost;
    /**
     * 岗位类属性：职责
     */
    @JSONField(name="rpos_duty")
    String rposDuty;
    /**
     * 岗位类属性：任职资格
     */
    @JSONField(name="rpos_qual")
    String rposQual;
    /**
     * 岗位类属性：排序
     */
    @JSONField(name="rpos_seq")
    String rposSeq;
    /**
     * 岗位类属性：任职人
     */
    @JSONField(name="rpos_emp")
    String rposEmp;
    /**
     * 岗位类属性：任职方式
     */
    @JSONField(name="rpos_duty_type")
    String rposDutyType;
    /**
     * 岗位类属性：生效日期
     */
    @JSONField(name="rpos_beg")
    String rposBeg;
    /**
     * 岗位类属性：失效日期
     */
    @JSONField(name="rpos_end")
    String rposEnd;
    /**
     * 岗位类：职员年龄
     */
    @JSONField(name="rpos_emp_age")
    String rposEmpAge;

    /**
     * 岗位类：职员工龄
     */
    @JSONField(name="rpos_wrk_age")
    String rposWrkAge;
    /**
     * 岗位类：职员在职月数
     */
    @JSONField(name="rpos_on_month")
    String rposOnMonth;

    /**
     * 岗位类：变更历史
     */
    @JSONField(name="rpos_charge_set")
    List<JobPositonChagSetDTO> rposChargeSet;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRposDept() {
        return rposDept;
    }

    public void setRposDept(String rposDept) {
        this.rposDept = rposDept;
    }

    public String getRposRpof() {
        return rposRpof;
    }

    public void setRposRpof(String rposRpof) {
        this.rposRpof = rposRpof;
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

    public String getRposPpost() {
        return rposPpost;
    }

    public void setRposPpost(String rposPpost) {
        this.rposPpost = rposPpost;
    }

    public String getRposDuty() {
        return rposDuty;
    }

    public void setRposDuty(String rposDuty) {
        this.rposDuty = rposDuty;
    }

    public String getRposQual() {
        return rposQual;
    }

    public void setRposQual(String rposQual) {
        this.rposQual = rposQual;
    }

    public String getRposSeq() {
        return rposSeq;
    }

    public void setRposSeq(String rposSeq) {
        this.rposSeq = rposSeq;
    }

    public String getRposEmp() {
        return rposEmp;
    }

    public void setRposEmp(String rposEmp) {
        this.rposEmp = rposEmp;
    }

    public String getRposDutyType() {
        return rposDutyType;
    }

    public void setRposDutyType(String rposDutyType) {
        this.rposDutyType = rposDutyType;
    }

    public String getRposBeg() {
        return rposBeg;
    }

    public void setRposBeg(String rposBeg) {
        this.rposBeg = rposBeg;
    }

    public String getRposEnd() {
        return rposEnd;
    }

    public void setRposEnd(String rposEnd) {
        this.rposEnd = rposEnd;
    }

    public String getRposEmpAge() {
        return rposEmpAge;
    }

    public void setRposEmpAge(String rposEmpAge) {
        this.rposEmpAge = rposEmpAge;
    }

    public String getRposWrkAge() {
        return rposWrkAge;
    }

    public void setRposWrkAge(String rposWrkAge) {
        this.rposWrkAge = rposWrkAge;
    }

    public String getRposOnMonth() {
        return rposOnMonth;
    }

    public void setRposOnMonth(String rposOnMonth) {
        this.rposOnMonth = rposOnMonth;
    }

    public List<JobPositonChagSetDTO> getRposChargeSet() {
        return rposChargeSet;
    }

    public void setRposChargeSet(List<JobPositonChagSetDTO> rposChargeSet) {
        this.rposChargeSet = rposChargeSet;
    }
}
