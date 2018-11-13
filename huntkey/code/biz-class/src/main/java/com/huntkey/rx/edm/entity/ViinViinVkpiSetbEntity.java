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
public class ViinViinVkpiSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9765053240652881L;
    
    /**监管类对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vkmonitor", className="MonitorEntity")
    private String viin_vkmonitor;
    /**可见kpi*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vkpi", className="KeyperformanceindexEntity")
    private String viin_vkpi;
    /**授权岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vkduty", className="JobpositionEntity")
    private String viin_vkduty;
    /**授权人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vkauthorizer", className="EmployeeEntity")
    private String viin_vkauthorizer;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vkisempower", className="")
    private String viin_vkisempower;
    /**授权时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vkdate", className="")
    private Date viin_vkdate;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vkbeg", className="")
    private Date viin_vkbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vkend", className="")
    private Date viin_vkend;
    /**向下可溯*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vkistrace", className="")
    private String viin_vkistrace;

    public String getViin_vkmonitor() {
        return viin_vkmonitor;
    }

    public void setViin_vkmonitor(String viin_vkmonitor) {
        this.viin_vkmonitor = viin_vkmonitor;
    }

    public MonitorEntity loadViin_vkmonitor() {
        String propertyName = "viin_vkmonitor";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vkpi() {
        return viin_vkpi;
    }

    public void setViin_vkpi(String viin_vkpi) {
        this.viin_vkpi = viin_vkpi;
    }

    public KeyperformanceindexEntity loadViin_vkpi() {
        String propertyName = "viin_vkpi";
        return (KeyperformanceindexEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vkduty() {
        return viin_vkduty;
    }

    public void setViin_vkduty(String viin_vkduty) {
        this.viin_vkduty = viin_vkduty;
    }

    public JobpositionEntity loadViin_vkduty() {
        String propertyName = "viin_vkduty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vkauthorizer() {
        return viin_vkauthorizer;
    }

    public void setViin_vkauthorizer(String viin_vkauthorizer) {
        this.viin_vkauthorizer = viin_vkauthorizer;
    }

    public EmployeeEntity loadViin_vkauthorizer() {
        String propertyName = "viin_vkauthorizer";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getViin_vkisempower() {
        return viin_vkisempower;
    }

    public void setViin_vkisempower(String viin_vkisempower) {
        this.viin_vkisempower = viin_vkisempower;
    }

    public Date getViin_vkdate() {
        return viin_vkdate;
    }

    public void setViin_vkdate(Date viin_vkdate) {
        this.viin_vkdate = viin_vkdate;
    }

    public Date getViin_vkbeg() {
        return viin_vkbeg;
    }

    public void setViin_vkbeg(Date viin_vkbeg) {
        this.viin_vkbeg = viin_vkbeg;
    }

    public Date getViin_vkend() {
        return viin_vkend;
    }

    public void setViin_vkend(Date viin_vkend) {
        this.viin_vkend = viin_vkend;
    }

    public String getViin_vkistrace() {
        return viin_vkistrace;
    }

    public void setViin_vkistrace(String viin_vkistrace) {
        this.viin_vkistrace = viin_vkistrace;
    }



}