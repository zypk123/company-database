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
public class EaorEaorPpiSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 4286006234386180L;
    
    /**监管类对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_pmoni", className="MonitorEntity")
    private String eaor_pmoni;
    /**可见ppi*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_ppi", className="ProcessperformanceindexEntity")
    private String eaor_ppi;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_pisempower", className="")
    private Integer eaor_pisempower;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_pbeg", className="")
    private Date eaor_pbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_pend", className="")
    private Date eaor_pend;
    /**向下可溯*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_pistrace", className="")
    private Integer eaor_pistrace;

    public String getEaor_pmoni() {
        return eaor_pmoni;
    }

    public void setEaor_pmoni(String eaor_pmoni) {
        this.eaor_pmoni = eaor_pmoni;
    }

    public MonitorEntity loadEaor_pmoni() {
        String propertyName = "eaor_pmoni";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEaor_ppi() {
        return eaor_ppi;
    }

    public void setEaor_ppi(String eaor_ppi) {
        this.eaor_ppi = eaor_ppi;
    }

    public ProcessperformanceindexEntity loadEaor_ppi() {
        String propertyName = "eaor_ppi";
        return (ProcessperformanceindexEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getEaor_pisempower() {
        return eaor_pisempower;
    }

    public void setEaor_pisempower(Integer eaor_pisempower) {
        this.eaor_pisempower = eaor_pisempower;
    }

    public Date getEaor_pbeg() {
        return eaor_pbeg;
    }

    public void setEaor_pbeg(Date eaor_pbeg) {
        this.eaor_pbeg = eaor_pbeg;
    }

    public Date getEaor_pend() {
        return eaor_pend;
    }

    public void setEaor_pend(Date eaor_pend) {
        this.eaor_pend = eaor_pend;
    }

    public Integer getEaor_pistrace() {
        return eaor_pistrace;
    }

    public void setEaor_pistrace(Integer eaor_pistrace) {
        this.eaor_pistrace = eaor_pistrace;
    }



}