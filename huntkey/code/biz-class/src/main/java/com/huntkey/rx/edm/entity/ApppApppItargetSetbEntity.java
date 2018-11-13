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
public class ApppApppItargetSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6807135116205792L;
    
    /**任务目标*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_itarget", className="TargetEntity")
    private String appp_itarget;
    /**授权岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_itduty", className="JobpositionEntity")
    private String appp_itduty;
    /**授权人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_itauthorizer", className="EmployeeEntity")
    private String appp_itauthorizer;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_itisempower", className="")
    private String appp_itisempower;
    /**授权时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_itdate", className="")
    private Date appp_itdate;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_itbeg", className="")
    private Date appp_itbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_itend", className="")
    private Date appp_itend;

    public String getAppp_itarget() {
        return appp_itarget;
    }

    public void setAppp_itarget(String appp_itarget) {
        this.appp_itarget = appp_itarget;
    }

    public TargetEntity loadAppp_itarget() {
        String propertyName = "appp_itarget";
        return (TargetEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAppp_itduty() {
        return appp_itduty;
    }

    public void setAppp_itduty(String appp_itduty) {
        this.appp_itduty = appp_itduty;
    }

    public JobpositionEntity loadAppp_itduty() {
        String propertyName = "appp_itduty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAppp_itauthorizer() {
        return appp_itauthorizer;
    }

    public void setAppp_itauthorizer(String appp_itauthorizer) {
        this.appp_itauthorizer = appp_itauthorizer;
    }

    public EmployeeEntity loadAppp_itauthorizer() {
        String propertyName = "appp_itauthorizer";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAppp_itisempower() {
        return appp_itisempower;
    }

    public void setAppp_itisempower(String appp_itisempower) {
        this.appp_itisempower = appp_itisempower;
    }

    public Date getAppp_itdate() {
        return appp_itdate;
    }

    public void setAppp_itdate(Date appp_itdate) {
        this.appp_itdate = appp_itdate;
    }

    public Date getAppp_itbeg() {
        return appp_itbeg;
    }

    public void setAppp_itbeg(Date appp_itbeg) {
        this.appp_itbeg = appp_itbeg;
    }

    public Date getAppp_itend() {
        return appp_itend;
    }

    public void setAppp_itend(Date appp_itend) {
        this.appp_itend = appp_itend;
    }



}