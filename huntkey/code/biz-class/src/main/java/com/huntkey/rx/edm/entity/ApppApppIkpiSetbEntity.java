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
public class ApppApppIkpiSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6442496645250051L;
    
    /**配置kpi*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ikpi", className="KeyperformanceindexEntity")
    private String appp_ikpi;
    /**授权岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ikduty", className="JobpositionEntity")
    private String appp_ikduty;
    /**授权人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ikauthorizer", className="EmployeeEntity")
    private String appp_ikauthorizer;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ikisempower", className="")
    private String appp_ikisempower;
    /**授权时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ikdate", className="")
    private Date appp_ikdate;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ikbeg", className="")
    private Date appp_ikbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ikend", className="")
    private Date appp_ikend;

    public String getAppp_ikpi() {
        return appp_ikpi;
    }

    public void setAppp_ikpi(String appp_ikpi) {
        this.appp_ikpi = appp_ikpi;
    }

    public KeyperformanceindexEntity loadAppp_ikpi() {
        String propertyName = "appp_ikpi";
        return (KeyperformanceindexEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAppp_ikduty() {
        return appp_ikduty;
    }

    public void setAppp_ikduty(String appp_ikduty) {
        this.appp_ikduty = appp_ikduty;
    }

    public JobpositionEntity loadAppp_ikduty() {
        String propertyName = "appp_ikduty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAppp_ikauthorizer() {
        return appp_ikauthorizer;
    }

    public void setAppp_ikauthorizer(String appp_ikauthorizer) {
        this.appp_ikauthorizer = appp_ikauthorizer;
    }

    public EmployeeEntity loadAppp_ikauthorizer() {
        String propertyName = "appp_ikauthorizer";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAppp_ikisempower() {
        return appp_ikisempower;
    }

    public void setAppp_ikisempower(String appp_ikisempower) {
        this.appp_ikisempower = appp_ikisempower;
    }

    public Date getAppp_ikdate() {
        return appp_ikdate;
    }

    public void setAppp_ikdate(Date appp_ikdate) {
        this.appp_ikdate = appp_ikdate;
    }

    public Date getAppp_ikbeg() {
        return appp_ikbeg;
    }

    public void setAppp_ikbeg(Date appp_ikbeg) {
        this.appp_ikbeg = appp_ikbeg;
    }

    public Date getAppp_ikend() {
        return appp_ikend;
    }

    public void setAppp_ikend(Date appp_ikend) {
        this.appp_ikend = appp_ikend;
    }



}