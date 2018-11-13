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
public class EpeoEpeoAjobSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9174003585829458L;
    
    /**希望工作地*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_epro", className="")
    private String epeo_epro;
    /**期望试用年薪*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_tysal", className="")
    private BigDecimal epeo_tysal;
    /**期望转正年薪*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_ysal", className="")
    private BigDecimal epeo_ysal;
    /**期望试用月薪*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_tmsal", className="")
    private BigDecimal epeo_tmsal;
    /**期望转正月薪*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_msal", className="")
    private BigDecimal epeo_msal;
    /**可上岗日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_en_date", className="")
    private String epeo_en_date;
    /**申请职位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_apost", className="")
    private String epeo_apost;
    /**是否保密*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_issecret", className="WordlistEntity")
    private String epeo_issecret;

    public String getEpeo_epro() {
        return epeo_epro;
    }

    public void setEpeo_epro(String epeo_epro) {
        this.epeo_epro = epeo_epro;
    }

    public BigDecimal getEpeo_tysal() {
        return epeo_tysal;
    }

    public void setEpeo_tysal(BigDecimal epeo_tysal) {
        this.epeo_tysal = epeo_tysal;
    }

    public BigDecimal getEpeo_ysal() {
        return epeo_ysal;
    }

    public void setEpeo_ysal(BigDecimal epeo_ysal) {
        this.epeo_ysal = epeo_ysal;
    }

    public BigDecimal getEpeo_tmsal() {
        return epeo_tmsal;
    }

    public void setEpeo_tmsal(BigDecimal epeo_tmsal) {
        this.epeo_tmsal = epeo_tmsal;
    }

    public BigDecimal getEpeo_msal() {
        return epeo_msal;
    }

    public void setEpeo_msal(BigDecimal epeo_msal) {
        this.epeo_msal = epeo_msal;
    }

    public String getEpeo_en_date() {
        return epeo_en_date;
    }

    public void setEpeo_en_date(String epeo_en_date) {
        this.epeo_en_date = epeo_en_date;
    }

    public String getEpeo_apost() {
        return epeo_apost;
    }

    public void setEpeo_apost(String epeo_apost) {
        this.epeo_apost = epeo_apost;
    }

    public String getEpeo_issecret() {
        return epeo_issecret;
    }

    public void setEpeo_issecret(String epeo_issecret) {
        this.epeo_issecret = epeo_issecret;
    }

    public WordlistEntity loadEpeo_issecret() {
        String propertyName = "epeo_issecret";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}