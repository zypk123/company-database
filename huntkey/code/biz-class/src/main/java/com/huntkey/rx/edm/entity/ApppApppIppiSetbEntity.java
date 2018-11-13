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
public class ApppApppIppiSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 3099870672239805L;
    
    /**配置ppi*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ippi", className="ProcessperformanceindexEntity")
    private String appp_ippi;
    /**授权岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ipduty", className="JobpositionEntity")
    private String appp_ipduty;
    /**授权人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ipauthorizer", className="EmployeeEntity")
    private String appp_ipauthorizer;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ipisempower", className="")
    private String appp_ipisempower;
    /**授权时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ipdate", className="")
    private Date appp_ipdate;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ipbeg", className="")
    private Date appp_ipbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ipend", className="")
    private Date appp_ipend;

    public String getAppp_ippi() {
        return appp_ippi;
    }

    public void setAppp_ippi(String appp_ippi) {
        this.appp_ippi = appp_ippi;
    }

    public ProcessperformanceindexEntity loadAppp_ippi() {
        String propertyName = "appp_ippi";
        return (ProcessperformanceindexEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAppp_ipduty() {
        return appp_ipduty;
    }

    public void setAppp_ipduty(String appp_ipduty) {
        this.appp_ipduty = appp_ipduty;
    }

    public JobpositionEntity loadAppp_ipduty() {
        String propertyName = "appp_ipduty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAppp_ipauthorizer() {
        return appp_ipauthorizer;
    }

    public void setAppp_ipauthorizer(String appp_ipauthorizer) {
        this.appp_ipauthorizer = appp_ipauthorizer;
    }

    public EmployeeEntity loadAppp_ipauthorizer() {
        String propertyName = "appp_ipauthorizer";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAppp_ipisempower() {
        return appp_ipisempower;
    }

    public void setAppp_ipisempower(String appp_ipisempower) {
        this.appp_ipisempower = appp_ipisempower;
    }

    public Date getAppp_ipdate() {
        return appp_ipdate;
    }

    public void setAppp_ipdate(Date appp_ipdate) {
        this.appp_ipdate = appp_ipdate;
    }

    public Date getAppp_ipbeg() {
        return appp_ipbeg;
    }

    public void setAppp_ipbeg(Date appp_ipbeg) {
        this.appp_ipbeg = appp_ipbeg;
    }

    public Date getAppp_ipend() {
        return appp_ipend;
    }

    public void setAppp_ipend(Date appp_ipend) {
        this.appp_ipend = appp_ipend;
    }



}