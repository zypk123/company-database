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
public class ViinViinVtargetSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 690749369930089L;
    
    /**监管类对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vtmonitor", className="MonitorEntity")
    private String viin_vtmonitor;
    /**任务目标*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vtarget", className="TargetEntity")
    private String viin_vtarget;
    /**授权岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vtduty", className="JobpositionEntity")
    private String viin_vtduty;
    /**授权人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vtauthorizer", className="EmployeeEntity")
    private String viin_vtauthorizer;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vtisempower", className="")
    private String viin_vtisempower;
    /**授权时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vtdate", className="")
    private Date viin_vtdate;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vtbeg", className="")
    private Date viin_vtbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vtend", className="")
    private Date viin_vtend;
    /**向下可溯*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vtistrace", className="")
    private String viin_vtistrace;

    public String getViin_vtmonitor() {
        return viin_vtmonitor;
    }

    public void setViin_vtmonitor(String viin_vtmonitor) {
        this.viin_vtmonitor = viin_vtmonitor;
    }

    public MonitorEntity loadViin_vtmonitor() {
        String propertyName = "viin_vtmonitor";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vtarget() {
        return viin_vtarget;
    }

    public void setViin_vtarget(String viin_vtarget) {
        this.viin_vtarget = viin_vtarget;
    }

    public TargetEntity loadViin_vtarget() {
        String propertyName = "viin_vtarget";
        return (TargetEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vtduty() {
        return viin_vtduty;
    }

    public void setViin_vtduty(String viin_vtduty) {
        this.viin_vtduty = viin_vtduty;
    }

    public JobpositionEntity loadViin_vtduty() {
        String propertyName = "viin_vtduty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vtauthorizer() {
        return viin_vtauthorizer;
    }

    public void setViin_vtauthorizer(String viin_vtauthorizer) {
        this.viin_vtauthorizer = viin_vtauthorizer;
    }

    public EmployeeEntity loadViin_vtauthorizer() {
        String propertyName = "viin_vtauthorizer";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vtisempower() {
        return viin_vtisempower;
    }

    public void setViin_vtisempower(String viin_vtisempower) {
        this.viin_vtisempower = viin_vtisempower;
    }

    public Date getViin_vtdate() {
        return viin_vtdate;
    }

    public void setViin_vtdate(Date viin_vtdate) {
        this.viin_vtdate = viin_vtdate;
    }

    public Date getViin_vtbeg() {
        return viin_vtbeg;
    }

    public void setViin_vtbeg(Date viin_vtbeg) {
        this.viin_vtbeg = viin_vtbeg;
    }

    public Date getViin_vtend() {
        return viin_vtend;
    }

    public void setViin_vtend(Date viin_vtend) {
        this.viin_vtend = viin_vtend;
    }

    public String getViin_vtistrace() {
        return viin_vtistrace;
    }

    public void setViin_vtistrace(String viin_vtistrace) {
        this.viin_vtistrace = viin_vtistrace;
    }



}