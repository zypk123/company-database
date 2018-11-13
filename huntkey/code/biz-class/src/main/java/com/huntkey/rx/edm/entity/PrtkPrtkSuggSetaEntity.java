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
public class PrtkPrtkSuggSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8659870135357166L;
    
    /**序号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_ssort", className="")
    private Integer prtk_ssort;
    /**评价内容*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_scont", className="")
    private String prtk_scont;
    /**评价标准*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_sstand", className="")
    private String prtk_sstand;
    /**标准分*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_ssvalue", className="")
    private BigDecimal prtk_ssvalue;
    /**参评人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_semp", className="EmployeeEntity")
    private String prtk_semp;
    /**审查得分*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_srvalue", className="")
    private BigDecimal prtk_srvalue;
    /**审查意见*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_ssugg", className="")
    private String prtk_ssugg;

    public Integer getPrtk_ssort() {
        return prtk_ssort;
    }

    public void setPrtk_ssort(Integer prtk_ssort) {
        this.prtk_ssort = prtk_ssort;
    }

    public String getPrtk_scont() {
        return prtk_scont;
    }

    public void setPrtk_scont(String prtk_scont) {
        this.prtk_scont = prtk_scont;
    }

    public String getPrtk_sstand() {
        return prtk_sstand;
    }

    public void setPrtk_sstand(String prtk_sstand) {
        this.prtk_sstand = prtk_sstand;
    }

    public BigDecimal getPrtk_ssvalue() {
        return prtk_ssvalue;
    }

    public void setPrtk_ssvalue(BigDecimal prtk_ssvalue) {
        this.prtk_ssvalue = prtk_ssvalue;
    }

    public String getPrtk_semp() {
        return prtk_semp;
    }

    public void setPrtk_semp(String prtk_semp) {
        this.prtk_semp = prtk_semp;
    }

    public EmployeeEntity loadPrtk_semp() {
        String propertyName = "prtk_semp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public BigDecimal getPrtk_srvalue() {
        return prtk_srvalue;
    }

    public void setPrtk_srvalue(BigDecimal prtk_srvalue) {
        this.prtk_srvalue = prtk_srvalue;
    }

    public String getPrtk_ssugg() {
        return prtk_ssugg;
    }

    public void setPrtk_ssugg(String prtk_ssugg) {
        this.prtk_ssugg = prtk_ssugg;
    }



}