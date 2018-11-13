package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class OeraOeraEmpSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5935591323049011L;
    
    /**所属部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oera_dept", className="DepttreeEntity")
    private String oera_dept;
    /**岗级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oera_pgrade", className="WordlistEntity")
    private String oera_pgrade;
    /**离职申请数*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oera_num_ap", className="")
    private Integer oera_num_ap;
    /**离职审核意见*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oera_audit_desc", className="")
    private String oera_audit_desc;
    /**离职人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oera_emp", className="EmployeeEntity")
    private String oera_emp;
    /**离职类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oera_type", className="WordlistEntity")
    private String oera_type;
    /**离职原因*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oera_reson", className="WordlistEntity")
    private String oera_reson;
    /**预离职日*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oera_app_date", className="")
    private Date oera_app_date;
    /**批准离职日*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oera_appr_date", className="")
    private Date oera_appr_date;
    /**实际离职日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oera_real_date", className="")
    private Date oera_real_date;

    public String getOera_dept() {
        return oera_dept;
    }

    public void setOera_dept(String oera_dept) {
        this.oera_dept = oera_dept;
    }

    public DepttreeEntity loadOera_dept() {
        String propertyName = "oera_dept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOera_pgrade() {
        return oera_pgrade;
    }

    public void setOera_pgrade(String oera_pgrade) {
        this.oera_pgrade = oera_pgrade;
    }

    public WordlistEntity loadOera_pgrade() {
        String propertyName = "oera_pgrade";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getOera_num_ap() {
        return oera_num_ap;
    }

    public void setOera_num_ap(Integer oera_num_ap) {
        this.oera_num_ap = oera_num_ap;
    }

    public String getOera_audit_desc() {
        return oera_audit_desc;
    }

    public void setOera_audit_desc(String oera_audit_desc) {
        this.oera_audit_desc = oera_audit_desc;
    }

    public String getOera_emp() {
        return oera_emp;
    }

    public void setOera_emp(String oera_emp) {
        this.oera_emp = oera_emp;
    }

    public EmployeeEntity loadOera_emp() {
        String propertyName = "oera_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOera_type() {
        return oera_type;
    }

    public void setOera_type(String oera_type) {
        this.oera_type = oera_type;
    }

    public WordlistEntity loadOera_type() {
        String propertyName = "oera_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOera_reson() {
        return oera_reson;
    }

    public void setOera_reson(String oera_reson) {
        this.oera_reson = oera_reson;
    }

    public WordlistEntity loadOera_reson() {
        String propertyName = "oera_reson";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getOera_app_date() {
        return oera_app_date;
    }

    public void setOera_app_date(Date oera_app_date) {
        this.oera_app_date = oera_app_date;
    }

    public Date getOera_appr_date() {
        return oera_appr_date;
    }

    public void setOera_appr_date(Date oera_appr_date) {
        this.oera_appr_date = oera_appr_date;
    }

    public Date getOera_real_date() {
        return oera_real_date;
    }

    public void setOera_real_date(Date oera_real_date) {
        this.oera_real_date = oera_real_date;
    }



}