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
public class OepaOepaAuditSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 7781179094576661L;
    
    /**审核人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepa_auditor", className="EmployeeEntity")
    private String oepa_auditor;
    /**审批意见*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepa_audit_idea", className="WordlistEntity")
    private String oepa_audit_idea;
    /**评价理由*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepa_evaluation", className="")
    private String oepa_evaluation;
    /**延期月数*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepa_ext_month", className="")
    private Integer oepa_ext_month;
    /**延期转正日*/
    @PropertyAnnotation(fomula="fb5184cdbae1452cabdb33794de3b903", limitFomula="", fieldName="oepa_ext_date", className="")
    private Date oepa_ext_date;

    public String getOepa_auditor() {
        return oepa_auditor;
    }

    public void setOepa_auditor(String oepa_auditor) {
        this.oepa_auditor = oepa_auditor;
    }

    public EmployeeEntity loadOepa_auditor() {
        String propertyName = "oepa_auditor";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOepa_audit_idea() {
        return oepa_audit_idea;
    }

    public void setOepa_audit_idea(String oepa_audit_idea) {
        this.oepa_audit_idea = oepa_audit_idea;
    }

    public WordlistEntity loadOepa_audit_idea() {
        String propertyName = "oepa_audit_idea";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOepa_evaluation() {
        return oepa_evaluation;
    }

    public void setOepa_evaluation(String oepa_evaluation) {
        this.oepa_evaluation = oepa_evaluation;
    }

    public Integer getOepa_ext_month() {
        return oepa_ext_month;
    }

    public void setOepa_ext_month(Integer oepa_ext_month) {
        this.oepa_ext_month = oepa_ext_month;
    }

    public Date getOepa_ext_date() {
        return oepa_ext_date;
    }

    public void setOepa_ext_date(Date oepa_ext_date) {
        this.oepa_ext_date = oepa_ext_date;
    }



}