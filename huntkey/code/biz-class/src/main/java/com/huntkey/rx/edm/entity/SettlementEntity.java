package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 结算方式类实体
 * 
 */
public class SettlementEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 4637479779803352L;
    
    /**结算识别码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="sett_idcode", className="")
    private String sett_idcode;
    /**结算方式摘要*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="sett_set_sumr", className="")
    private String sett_set_sumr;
    /**付款方式摘要*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="sett_pay_sumr", className="")
    private String sett_pay_sumr;
    /**结算方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="sett_way", className="WordlistEntity")
    private String sett_way;
    /**预收比例(不含单位)*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="sett_prepay_rate", className="")
    private BigDecimal sett_prepay_rate;
    /**预付提前天数*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="sett_prepay_day", className="")
    private Integer sett_prepay_day;
    /**结算天数*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="sett_day_num", className="")
    private Integer sett_day_num;
    /**结算日*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="sett_day", className="")
    private Integer sett_day;
    /**付款方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="sett_pay_way", className="WordlistEntity")
    private String sett_pay_way;

    public String getSett_idcode() {
        return sett_idcode;
    }

    public void setSett_idcode(String sett_idcode) {
        this.sett_idcode = sett_idcode;
    }

    public String getSett_set_sumr() {
        return sett_set_sumr;
    }

    public void setSett_set_sumr(String sett_set_sumr) {
        this.sett_set_sumr = sett_set_sumr;
    }

    public String getSett_pay_sumr() {
        return sett_pay_sumr;
    }

    public void setSett_pay_sumr(String sett_pay_sumr) {
        this.sett_pay_sumr = sett_pay_sumr;
    }

    public String getSett_way() {
        return sett_way;
    }

    public void setSett_way(String sett_way) {
        this.sett_way = sett_way;
    }

    public WordlistEntity loadSett_way() {
        String propertyName = "sett_way";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public BigDecimal getSett_prepay_rate() {
        return sett_prepay_rate;
    }

    public void setSett_prepay_rate(BigDecimal sett_prepay_rate) {
        this.sett_prepay_rate = sett_prepay_rate;
    }

    public Integer getSett_prepay_day() {
        return sett_prepay_day;
    }

    public void setSett_prepay_day(Integer sett_prepay_day) {
        this.sett_prepay_day = sett_prepay_day;
    }

    public Integer getSett_day_num() {
        return sett_day_num;
    }

    public void setSett_day_num(Integer sett_day_num) {
        this.sett_day_num = sett_day_num;
    }

    public Integer getSett_day() {
        return sett_day;
    }

    public void setSett_day(Integer sett_day) {
        this.sett_day = sett_day;
    }

    public String getSett_pay_way() {
        return sett_pay_way;
    }

    public void setSett_pay_way(String sett_pay_way) {
        this.sett_pay_way = sett_pay_way;
    }

    public WordlistEntity loadSett_pay_way() {
        String propertyName = "sett_pay_way";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}