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
public class ViinViinVdashboardSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9278858053153440L;
    
    /**监管类对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vdmonitor", className="MonitorEntity")
    private String viin_vdmonitor;
    /**靶向*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vdash", className="TargetEntity")
    private String viin_vdash;
    /**授权岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vdduty", className="JobpositionEntity")
    private String viin_vdduty;
    /**授权人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vdauthorizer", className="EmployeeEntity")
    private String viin_vdauthorizer;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vdisempower", className="")
    private String viin_vdisempower;
    /**授权时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vddate", className="")
    private Date viin_vddate;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vdbeg", className="")
    private Date viin_vdbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vdend", className="")
    private Date viin_vdend;
    /**向下可溯*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vdistrace", className="")
    private String viin_vdistrace;

    public String getViin_vdmonitor() {
        return viin_vdmonitor;
    }

    public void setViin_vdmonitor(String viin_vdmonitor) {
        this.viin_vdmonitor = viin_vdmonitor;
    }

    public MonitorEntity loadViin_vdmonitor() {
        String propertyName = "viin_vdmonitor";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vdash() {
        return viin_vdash;
    }

    public void setViin_vdash(String viin_vdash) {
        this.viin_vdash = viin_vdash;
    }

    public TargetEntity loadViin_vdash() {
        String propertyName = "viin_vdash";
        return (TargetEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vdduty() {
        return viin_vdduty;
    }

    public void setViin_vdduty(String viin_vdduty) {
        this.viin_vdduty = viin_vdduty;
    }

    public JobpositionEntity loadViin_vdduty() {
        String propertyName = "viin_vdduty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vdauthorizer() {
        return viin_vdauthorizer;
    }

    public void setViin_vdauthorizer(String viin_vdauthorizer) {
        this.viin_vdauthorizer = viin_vdauthorizer;
    }

    public EmployeeEntity loadViin_vdauthorizer() {
        String propertyName = "viin_vdauthorizer";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vdisempower() {
        return viin_vdisempower;
    }

    public void setViin_vdisempower(String viin_vdisempower) {
        this.viin_vdisempower = viin_vdisempower;
    }

    public Date getViin_vddate() {
        return viin_vddate;
    }

    public void setViin_vddate(Date viin_vddate) {
        this.viin_vddate = viin_vddate;
    }

    public Date getViin_vdbeg() {
        return viin_vdbeg;
    }

    public void setViin_vdbeg(Date viin_vdbeg) {
        this.viin_vdbeg = viin_vdbeg;
    }

    public Date getViin_vdend() {
        return viin_vdend;
    }

    public void setViin_vdend(Date viin_vdend) {
        this.viin_vdend = viin_vdend;
    }

    public String getViin_vdistrace() {
        return viin_vdistrace;
    }

    public void setViin_vdistrace(String viin_vdistrace) {
        this.viin_vdistrace = viin_vdistrace;
    }



}