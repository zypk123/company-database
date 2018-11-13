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
public class MdepMdepChgrSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 7346772336425036L;
    
    /**负责人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_chgr_emp", className="EmployeeEntity")
    private String mdep_chgr_emp;
    /**负责人岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_chgr_post", className="JobpositionEntity")
    private String mdep_chgr_post;
    /**负责人类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_chgr_type", className="WordlistEntity")
    private String mdep_chgr_type;
    /**生效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_chgr_beg", className="")
    private Date mdep_chgr_beg;
    /**失效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_chgr_end", className="")
    private Date mdep_chgr_end;

    public String getMdep_chgr_emp() {
        return mdep_chgr_emp;
    }

    public void setMdep_chgr_emp(String mdep_chgr_emp) {
        this.mdep_chgr_emp = mdep_chgr_emp;
    }

    public EmployeeEntity loadMdep_chgr_emp() {
        String propertyName = "mdep_chgr_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMdep_chgr_post() {
        return mdep_chgr_post;
    }

    public void setMdep_chgr_post(String mdep_chgr_post) {
        this.mdep_chgr_post = mdep_chgr_post;
    }

    public JobpositionEntity loadMdep_chgr_post() {
        String propertyName = "mdep_chgr_post";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMdep_chgr_type() {
        return mdep_chgr_type;
    }

    public void setMdep_chgr_type(String mdep_chgr_type) {
        this.mdep_chgr_type = mdep_chgr_type;
    }

    public WordlistEntity loadMdep_chgr_type() {
        String propertyName = "mdep_chgr_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getMdep_chgr_beg() {
        return mdep_chgr_beg;
    }

    public void setMdep_chgr_beg(Date mdep_chgr_beg) {
        this.mdep_chgr_beg = mdep_chgr_beg;
    }

    public Date getMdep_chgr_end() {
        return mdep_chgr_end;
    }

    public void setMdep_chgr_end(Date mdep_chgr_end) {
        this.mdep_chgr_end = mdep_chgr_end;
    }



}