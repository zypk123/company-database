package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class PpixIpinTimeSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 3109200779684870L;
    
    /**规划值*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ipin_pln_val", className="")
    private String ipin_pln_val;
    /**实现值*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ipin_ful_val", className="")
    private BigDecimal ipin_ful_val;
    /**完成率*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ipin_ful_rate", className="")
    private BigDecimal ipin_ful_rate;
    /**周期对象*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="ipin_peid_id", className="PeriodEntity")
    private String ipin_peid_id;

    public String getIpin_pln_val() {
        return ipin_pln_val;
    }

    public void setIpin_pln_val(String ipin_pln_val) {
        this.ipin_pln_val = ipin_pln_val;
    }

    public BigDecimal getIpin_ful_val() {
        return ipin_ful_val;
    }

    public void setIpin_ful_val(BigDecimal ipin_ful_val) {
        this.ipin_ful_val = ipin_ful_val;
    }

    public BigDecimal getIpin_ful_rate() {
        return ipin_ful_rate;
    }

    public void setIpin_ful_rate(BigDecimal ipin_ful_rate) {
        this.ipin_ful_rate = ipin_ful_rate;
    }

    public String getIpin_peid_id() {
        return ipin_peid_id;
    }

    public void setIpin_peid_id(String ipin_peid_id) {
        this.ipin_peid_id = ipin_peid_id;
    }

    public PeriodEntity loadIpin_peid_id() {
        String propertyName = "ipin_peid_id";
        return (PeriodEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}