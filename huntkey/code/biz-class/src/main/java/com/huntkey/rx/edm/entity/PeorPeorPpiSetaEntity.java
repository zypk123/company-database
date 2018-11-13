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
public class PeorPeorPpiSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 2854624460886178L;
    
    /**监管类对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_pmoni", className="MonitorEntity")
    private String peor_pmoni;
    /**可见ppi*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_ppi", className="ProcessperformanceindexEntity")
    private String peor_ppi;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_pisempower", className="")
    private Integer peor_pisempower;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_pbeg", className="")
    private Date peor_pbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_pend", className="")
    private Date peor_pend;
    /**向下可溯*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_pistrace", className="")
    private Integer peor_pistrace;

    public String getPeor_pmoni() {
        return peor_pmoni;
    }

    public void setPeor_pmoni(String peor_pmoni) {
        this.peor_pmoni = peor_pmoni;
    }

    public MonitorEntity loadPeor_pmoni() {
        String propertyName = "peor_pmoni";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPeor_ppi() {
        return peor_ppi;
    }

    public void setPeor_ppi(String peor_ppi) {
        this.peor_ppi = peor_ppi;
    }

    public ProcessperformanceindexEntity loadPeor_ppi() {
        String propertyName = "peor_ppi";
        return (ProcessperformanceindexEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getPeor_pisempower() {
        return peor_pisempower;
    }

    public void setPeor_pisempower(Integer peor_pisempower) {
        this.peor_pisempower = peor_pisempower;
    }

    public Date getPeor_pbeg() {
        return peor_pbeg;
    }

    public void setPeor_pbeg(Date peor_pbeg) {
        this.peor_pbeg = peor_pbeg;
    }

    public Date getPeor_pend() {
        return peor_pend;
    }

    public void setPeor_pend(Date peor_pend) {
        this.peor_pend = peor_pend;
    }

    public Integer getPeor_pistrace() {
        return peor_pistrace;
    }

    public void setPeor_pistrace(Integer peor_pistrace) {
        this.peor_pistrace = peor_pistrace;
    }



}