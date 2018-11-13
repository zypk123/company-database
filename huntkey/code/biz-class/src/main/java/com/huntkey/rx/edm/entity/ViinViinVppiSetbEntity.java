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
public class ViinViinVppiSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6216640115473539L;
    
    /**监管类对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vpmonitor", className="MonitorEntity")
    private String viin_vpmonitor;
    /**可见ppi*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vppi", className="ProcessperformanceindexEntity")
    private String viin_vppi;
    /**授权岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vpduty", className="JobpositionEntity")
    private String viin_vpduty;
    /**授权人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vpauthorizer", className="EmployeeEntity")
    private String viin_vpauthorizer;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vpisempower", className="")
    private String viin_vpisempower;
    /**授权时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vpdate", className="")
    private Date viin_vpdate;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vpbeg", className="")
    private Date viin_vpbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vpend", className="")
    private Date viin_vpend;
    /**向下可溯*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vpistrace", className="")
    private String viin_vpistrace;

    public String getViin_vpmonitor() {
        return viin_vpmonitor;
    }

    public void setViin_vpmonitor(String viin_vpmonitor) {
        this.viin_vpmonitor = viin_vpmonitor;
    }

    public MonitorEntity loadViin_vpmonitor() {
        String propertyName = "viin_vpmonitor";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vppi() {
        return viin_vppi;
    }

    public void setViin_vppi(String viin_vppi) {
        this.viin_vppi = viin_vppi;
    }

    public ProcessperformanceindexEntity loadViin_vppi() {
        String propertyName = "viin_vppi";
        return (ProcessperformanceindexEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vpduty() {
        return viin_vpduty;
    }

    public void setViin_vpduty(String viin_vpduty) {
        this.viin_vpduty = viin_vpduty;
    }

    public JobpositionEntity loadViin_vpduty() {
        String propertyName = "viin_vpduty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vpauthorizer() {
        return viin_vpauthorizer;
    }

    public void setViin_vpauthorizer(String viin_vpauthorizer) {
        this.viin_vpauthorizer = viin_vpauthorizer;
    }

    public EmployeeEntity loadViin_vpauthorizer() {
        String propertyName = "viin_vpauthorizer";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vpisempower() {
        return viin_vpisempower;
    }

    public void setViin_vpisempower(String viin_vpisempower) {
        this.viin_vpisempower = viin_vpisempower;
    }

    public Date getViin_vpdate() {
        return viin_vpdate;
    }

    public void setViin_vpdate(Date viin_vpdate) {
        this.viin_vpdate = viin_vpdate;
    }

    public Date getViin_vpbeg() {
        return viin_vpbeg;
    }

    public void setViin_vpbeg(Date viin_vpbeg) {
        this.viin_vpbeg = viin_vpbeg;
    }

    public Date getViin_vpend() {
        return viin_vpend;
    }

    public void setViin_vpend(Date viin_vpend) {
        this.viin_vpend = viin_vpend;
    }

    public String getViin_vpistrace() {
        return viin_vpistrace;
    }

    public void setViin_vpistrace(String viin_vpistrace) {
        this.viin_vpistrace = viin_vpistrace;
    }



}