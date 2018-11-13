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
public class ApppApppIdashboardSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 2955744360700415L;
    
    /**靶向*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_idash", className="DashboardEntity")
    private String appp_idash;
    /**授权岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_idduty", className="JobpositionEntity")
    private String appp_idduty;
    /**授权人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_idauthorizer", className="EmployeeEntity")
    private String appp_idauthorizer;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_idisempower", className="")
    private String appp_idisempower;
    /**授权时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_iddate", className="")
    private Date appp_iddate;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_idbeg", className="")
    private Date appp_idbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_idend", className="")
    private Date appp_idend;

    public String getAppp_idash() {
        return appp_idash;
    }

    public void setAppp_idash(String appp_idash) {
        this.appp_idash = appp_idash;
    }

    public DashboardEntity loadAppp_idash() {
        String propertyName = "appp_idash";
        return (DashboardEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAppp_idduty() {
        return appp_idduty;
    }

    public void setAppp_idduty(String appp_idduty) {
        this.appp_idduty = appp_idduty;
    }

    public JobpositionEntity loadAppp_idduty() {
        String propertyName = "appp_idduty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAppp_idauthorizer() {
        return appp_idauthorizer;
    }

    public void setAppp_idauthorizer(String appp_idauthorizer) {
        this.appp_idauthorizer = appp_idauthorizer;
    }

    public EmployeeEntity loadAppp_idauthorizer() {
        String propertyName = "appp_idauthorizer";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAppp_idisempower() {
        return appp_idisempower;
    }

    public void setAppp_idisempower(String appp_idisempower) {
        this.appp_idisempower = appp_idisempower;
    }

    public Date getAppp_iddate() {
        return appp_iddate;
    }

    public void setAppp_iddate(Date appp_iddate) {
        this.appp_iddate = appp_iddate;
    }

    public Date getAppp_idbeg() {
        return appp_idbeg;
    }

    public void setAppp_idbeg(Date appp_idbeg) {
        this.appp_idbeg = appp_idbeg;
    }

    public Date getAppp_idend() {
        return appp_idend;
    }

    public void setAppp_idend(Date appp_idend) {
        this.appp_idend = appp_idend;
    }



}