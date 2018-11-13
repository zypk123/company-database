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
public class PrfhPrfhEvalSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5511203321688371L;
    
    /**序号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prfh_esort", className="")
    private Integer prfh_esort;
    /**评价内容*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prfh_econt", className="")
    private String prfh_econt;
    /**评价标准*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prfh_estand", className="")
    private String prfh_estand;
    /**标准分*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prfh_esvalue", className="")
    private BigDecimal prfh_esvalue;
    /**参评人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prfh_eemp", className="EmployeeEntity")
    private String prfh_eemp;
    /**审查得分*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prfh_ervalue", className="")
    private BigDecimal prfh_ervalue;
    /**审查意见*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prfh_esugg", className="")
    private String prfh_esugg;

    public Integer getPrfh_esort() {
        return prfh_esort;
    }

    public void setPrfh_esort(Integer prfh_esort) {
        this.prfh_esort = prfh_esort;
    }

    public String getPrfh_econt() {
        return prfh_econt;
    }

    public void setPrfh_econt(String prfh_econt) {
        this.prfh_econt = prfh_econt;
    }

    public String getPrfh_estand() {
        return prfh_estand;
    }

    public void setPrfh_estand(String prfh_estand) {
        this.prfh_estand = prfh_estand;
    }

    public BigDecimal getPrfh_esvalue() {
        return prfh_esvalue;
    }

    public void setPrfh_esvalue(BigDecimal prfh_esvalue) {
        this.prfh_esvalue = prfh_esvalue;
    }

    public String getPrfh_eemp() {
        return prfh_eemp;
    }

    public void setPrfh_eemp(String prfh_eemp) {
        this.prfh_eemp = prfh_eemp;
    }

    public EmployeeEntity loadPrfh_eemp() {
        String propertyName = "prfh_eemp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public BigDecimal getPrfh_ervalue() {
        return prfh_ervalue;
    }

    public void setPrfh_ervalue(BigDecimal prfh_ervalue) {
        this.prfh_ervalue = prfh_ervalue;
    }

    public String getPrfh_esugg() {
        return prfh_esugg;
    }

    public void setPrfh_esugg(String prfh_esugg) {
        this.prfh_esugg = prfh_esugg;
    }



}