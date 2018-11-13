package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 汇率类实体
 * 
 */
public class CurrencyrateEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 2120573702620447L;
    
    /**汇率识别码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="curt_code", className="")
    private Integer curt_code;
    /**换算币别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="curt_conv_curr", className="CurrencyEntity")
    private String curt_conv_curr;
    /**本位币别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="curt_local_curr", className="CurrencyEntity")
    private String curt_local_curr;
    /**换算单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="curt_conv_unit", className="")
    private BigDecimal curt_conv_unit;
    /**汇率*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="curt_rate", className="")
    private BigDecimal curt_rate;
    /**生效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="curt_beg", className="")
    private Date curt_beg;
    /**失效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="curt_end", className="")
    private Date curt_end;
    /**备注*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="curt_remark", className="")
    private String curt_remark;

    public Integer getCurt_code() {
        return curt_code;
    }

    public void setCurt_code(Integer curt_code) {
        this.curt_code = curt_code;
    }

    public String getCurt_conv_curr() {
        return curt_conv_curr;
    }

    public void setCurt_conv_curr(String curt_conv_curr) {
        this.curt_conv_curr = curt_conv_curr;
    }

    public CurrencyEntity loadCurt_conv_curr() {
        String propertyName = "curt_conv_curr";
        return (CurrencyEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getCurt_local_curr() {
        return curt_local_curr;
    }

    public void setCurt_local_curr(String curt_local_curr) {
        this.curt_local_curr = curt_local_curr;
    }

    public CurrencyEntity loadCurt_local_curr() {
        String propertyName = "curt_local_curr";
        return (CurrencyEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public BigDecimal getCurt_conv_unit() {
        return curt_conv_unit;
    }

    public void setCurt_conv_unit(BigDecimal curt_conv_unit) {
        this.curt_conv_unit = curt_conv_unit;
    }

    public BigDecimal getCurt_rate() {
        return curt_rate;
    }

    public void setCurt_rate(BigDecimal curt_rate) {
        this.curt_rate = curt_rate;
    }

    public Date getCurt_beg() {
        return curt_beg;
    }

    public void setCurt_beg(Date curt_beg) {
        this.curt_beg = curt_beg;
    }

    public Date getCurt_end() {
        return curt_end;
    }

    public void setCurt_end(Date curt_end) {
        this.curt_end = curt_end;
    }

    public String getCurt_remark() {
        return curt_remark;
    }

    public void setCurt_remark(String curt_remark) {
        this.curt_remark = curt_remark;
    }



}