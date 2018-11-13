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
public class EnteEnteInveSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 4514112118073702L;
    
    /**投资人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_inve_peop", className="")
    private String ente_inve_peop;
    /**股份比例*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_stock_rate", className="")
    private BigDecimal ente_stock_rate;
    /**企业中职务*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_duty", className="")
    private String ente_duty;

    public String getEnte_inve_peop() {
        return ente_inve_peop;
    }

    public void setEnte_inve_peop(String ente_inve_peop) {
        this.ente_inve_peop = ente_inve_peop;
    }

    public BigDecimal getEnte_stock_rate() {
        return ente_stock_rate;
    }

    public void setEnte_stock_rate(BigDecimal ente_stock_rate) {
        this.ente_stock_rate = ente_stock_rate;
    }

    public String getEnte_duty() {
        return ente_duty;
    }

    public void setEnte_duty(String ente_duty) {
        this.ente_duty = ente_duty;
    }



}