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
public class OepcOepcChangSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 2068280744520678L;
    
    /**是否保留原岗位*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="oepc_iskeep", className="")
    private String oepc_iskeep;
    /**员工*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepc_emp", className="EmployeeEntity")
    private String oepc_emp;
    /**部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepc_dept", className="DepttreeEntity")
    private String oepc_dept;
    /**岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepc_post", className="JobpositionEntity")
    private String oepc_post;
    /**任职方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepc_dtyp_type", className="WordlistEntity")
    private String oepc_dtyp_type;
    /**所属部门_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepc_dept_old", className="DepttreeEntity")
    private String oepc_dept_old;
    /**部门岗位_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepc_post_old", className="JobpositionEntity")
    private String oepc_post_old;
    /**任职方式_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepc_dtyp_old", className="WordlistEntity")
    private String oepc_dtyp_old;

    public String getOepc_iskeep() {
        return oepc_iskeep;
    }

    public void setOepc_iskeep(String oepc_iskeep) {
        this.oepc_iskeep = oepc_iskeep;
    }

    public String getOepc_emp() {
        return oepc_emp;
    }

    public void setOepc_emp(String oepc_emp) {
        this.oepc_emp = oepc_emp;
    }

    public EmployeeEntity loadOepc_emp() {
        String propertyName = "oepc_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOepc_dept() {
        return oepc_dept;
    }

    public void setOepc_dept(String oepc_dept) {
        this.oepc_dept = oepc_dept;
    }

    public DepttreeEntity loadOepc_dept() {
        String propertyName = "oepc_dept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOepc_post() {
        return oepc_post;
    }

    public void setOepc_post(String oepc_post) {
        this.oepc_post = oepc_post;
    }

    public JobpositionEntity loadOepc_post() {
        String propertyName = "oepc_post";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOepc_dtyp_type() {
        return oepc_dtyp_type;
    }

    public void setOepc_dtyp_type(String oepc_dtyp_type) {
        this.oepc_dtyp_type = oepc_dtyp_type;
    }

    public WordlistEntity loadOepc_dtyp_type() {
        String propertyName = "oepc_dtyp_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOepc_dept_old() {
        return oepc_dept_old;
    }

    public void setOepc_dept_old(String oepc_dept_old) {
        this.oepc_dept_old = oepc_dept_old;
    }

    public DepttreeEntity loadOepc_dept_old() {
        String propertyName = "oepc_dept_old";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOepc_post_old() {
        return oepc_post_old;
    }

    public void setOepc_post_old(String oepc_post_old) {
        this.oepc_post_old = oepc_post_old;
    }

    public JobpositionEntity loadOepc_post_old() {
        String propertyName = "oepc_post_old";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOepc_dtyp_old() {
        return oepc_dtyp_old;
    }

    public void setOepc_dtyp_old(String oepc_dtyp_old) {
        this.oepc_dtyp_old = oepc_dtyp_old;
    }

    public WordlistEntity loadOepc_dtyp_old() {
        String propertyName = "oepc_dtyp_old";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}