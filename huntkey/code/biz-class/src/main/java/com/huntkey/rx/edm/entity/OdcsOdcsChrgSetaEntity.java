package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class OdcsOdcsChrgSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9120897895735387L;
    
    /**部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odcs_dept", className="DepttreeEntity")
    private String odcs_dept;
    /**责任人类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odcs_chrg_type", className="WordlistEntity")
    private String odcs_chrg_type;
    /**责任人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odcs_emp", className="EmployeeEntity")
    private String odcs_emp;
    /**岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odcs_post", className="JobpositionEntity")
    private String odcs_post;
    /**任职方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odcs_duty_type", className="WordlistEntity")
    private String odcs_duty_type;
    /**负责人_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odcs_emp_old", className="EmployeeEntity")
    private String odcs_emp_old;
    /**岗位_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odcs_post_old", className="JobpositionEntity")
    private String odcs_post_old;
    /**任职方式_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odcs_dtyp_old", className="WordlistEntity")
    private String odcs_dtyp_old;

    public String getOdcs_dept() {
        return odcs_dept;
    }

    public void setOdcs_dept(String odcs_dept) {
        this.odcs_dept = odcs_dept;
    }

    public DepttreeEntity loadOdcs_dept() {
        String propertyName = "odcs_dept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdcs_chrg_type() {
        return odcs_chrg_type;
    }

    public void setOdcs_chrg_type(String odcs_chrg_type) {
        this.odcs_chrg_type = odcs_chrg_type;
    }

    public WordlistEntity loadOdcs_chrg_type() {
        String propertyName = "odcs_chrg_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdcs_emp() {
        return odcs_emp;
    }

    public void setOdcs_emp(String odcs_emp) {
        this.odcs_emp = odcs_emp;
    }

    public EmployeeEntity loadOdcs_emp() {
        String propertyName = "odcs_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdcs_post() {
        return odcs_post;
    }

    public void setOdcs_post(String odcs_post) {
        this.odcs_post = odcs_post;
    }

    public JobpositionEntity loadOdcs_post() {
        String propertyName = "odcs_post";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdcs_duty_type() {
        return odcs_duty_type;
    }

    public void setOdcs_duty_type(String odcs_duty_type) {
        this.odcs_duty_type = odcs_duty_type;
    }

    public WordlistEntity loadOdcs_duty_type() {
        String propertyName = "odcs_duty_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdcs_emp_old() {
        return odcs_emp_old;
    }

    public void setOdcs_emp_old(String odcs_emp_old) {
        this.odcs_emp_old = odcs_emp_old;
    }

    public EmployeeEntity loadOdcs_emp_old() {
        String propertyName = "odcs_emp_old";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdcs_post_old() {
        return odcs_post_old;
    }

    public void setOdcs_post_old(String odcs_post_old) {
        this.odcs_post_old = odcs_post_old;
    }

    public JobpositionEntity loadOdcs_post_old() {
        String propertyName = "odcs_post_old";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdcs_dtyp_old() {
        return odcs_dtyp_old;
    }

    public void setOdcs_dtyp_old(String odcs_dtyp_old) {
        this.odcs_dtyp_old = odcs_dtyp_old;
    }

    public WordlistEntity loadOdcs_dtyp_old() {
        String propertyName = "odcs_dtyp_old";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}