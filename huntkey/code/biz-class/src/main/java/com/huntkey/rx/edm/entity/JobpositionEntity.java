package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 岗位类实体
 * 
 */
public class JobpositionEntity extends OrganizationEntity implements Serializable {
    private static final long serialVersionUID = 6094585523003734L;
    
    /**岗位名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_name", className="")
    private String rpos_name;
    /**岗位编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_code", className="")
    private String rpos_code;
    /**所属部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_dept", className="DepttreeEntity")
    private String rpos_dept;
    /**职位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_rpof", className="PositiondefinitionEntity")
    private String rpos_rpof;
    /**汇报岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_ppost", className="JobpositionEntity")
    private String rpos_ppost;
    /**岗级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_grade", className="WordlistEntity")
    private String rpos_grade;
    /**职责*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_duty", className="")
    private String rpos_duty;
    /**任职资格*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_qual", className="")
    private String rpos_qual;
    /**排序*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_seq", className="")
    private Integer rpos_seq;
    /**任职人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_emp", className="EmployeeEntity")
    private String rpos_emp;
    /**任职方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_duty_type", className="WordlistEntity")
    private String rpos_duty_type;
    /**生效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_beg", className="")
    private Date rpos_beg;
    /**失效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_end", className="")
    private Date rpos_end;
    /**变更历史*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_chag_set", className="RposRposChagSetaEntity")
    private List<RposRposChagSetaEntity> rpos_chag_set;

    public String getRpos_name() {
        return rpos_name;
    }

    public void setRpos_name(String rpos_name) {
        this.rpos_name = rpos_name;
    }

    public String getRpos_code() {
        return rpos_code;
    }

    public void setRpos_code(String rpos_code) {
        this.rpos_code = rpos_code;
    }

    public String getRpos_dept() {
        return rpos_dept;
    }

    public void setRpos_dept(String rpos_dept) {
        this.rpos_dept = rpos_dept;
    }

    public DepttreeEntity loadRpos_dept() {
        String propertyName = "rpos_dept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpos_rpof() {
        return rpos_rpof;
    }

    public void setRpos_rpof(String rpos_rpof) {
        this.rpos_rpof = rpos_rpof;
    }

    public PositiondefinitionEntity loadRpos_rpof() {
        String propertyName = "rpos_rpof";
        return (PositiondefinitionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpos_ppost() {
        return rpos_ppost;
    }

    public void setRpos_ppost(String rpos_ppost) {
        this.rpos_ppost = rpos_ppost;
    }

    public JobpositionEntity loadRpos_ppost() {
        String propertyName = "rpos_ppost";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpos_grade() {
        return rpos_grade;
    }

    public void setRpos_grade(String rpos_grade) {
        this.rpos_grade = rpos_grade;
    }

    public WordlistEntity loadRpos_grade() {
        String propertyName = "rpos_grade";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpos_duty() {
        return rpos_duty;
    }

    public void setRpos_duty(String rpos_duty) {
        this.rpos_duty = rpos_duty;
    }

    public String getRpos_qual() {
        return rpos_qual;
    }

    public void setRpos_qual(String rpos_qual) {
        this.rpos_qual = rpos_qual;
    }

    public Integer getRpos_seq() {
        return rpos_seq;
    }

    public void setRpos_seq(Integer rpos_seq) {
        this.rpos_seq = rpos_seq;
    }

    public String getRpos_emp() {
        return rpos_emp;
    }

    public void setRpos_emp(String rpos_emp) {
        this.rpos_emp = rpos_emp;
    }

    public EmployeeEntity loadRpos_emp() {
        String propertyName = "rpos_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpos_duty_type() {
        return rpos_duty_type;
    }

    public void setRpos_duty_type(String rpos_duty_type) {
        this.rpos_duty_type = rpos_duty_type;
    }

    public WordlistEntity loadRpos_duty_type() {
        String propertyName = "rpos_duty_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getRpos_beg() {
        return rpos_beg;
    }

    public void setRpos_beg(Date rpos_beg) {
        this.rpos_beg = rpos_beg;
    }

    public Date getRpos_end() {
        return rpos_end;
    }

    public void setRpos_end(Date rpos_end) {
        this.rpos_end = rpos_end;
    }

    public List<RposRposChagSetaEntity> loadRpos_chag_set() {
        String propertyName = "rpos_chag_set";
        String rootClass = "";
        return (List<RposRposChagSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRpos_chag_set(List<RposRposChagSetaEntity> rpos_chag_set) {
        this.rpos_chag_set = rpos_chag_set;
    }

    public List<RposRposChagSetaEntity> getRpos_chag_set() {
        return rpos_chag_set;
    }



}