package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import com.huntkey.rx.base.PropertyBaseEntity;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class GoimGoimRequSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6907228582014331L;
    
    /**需求企业*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_requ_ente", className="EnterpriseEntity")
    private String goim_requ_ente;
    /**提交时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_requ_time", className="")
    private Date goim_requ_time;
    /**需求数量*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_requ_qty", className="")
    private BigDecimal goim_requ_qty;
    /**计量单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_requ_meas", className="MeasureunitEntity")
    private String goim_requ_meas;
    /**到货截止时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_requ_deadline", className="")
    private Date goim_requ_deadline;
    /**是否已经关闭*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_requ_closed", className="WordlistEntity")
    private String goim_requ_closed;

    public String getGoim_requ_ente() {
        return goim_requ_ente;
    }

    public void setGoim_requ_ente(String goim_requ_ente) {
        this.goim_requ_ente = goim_requ_ente;
    }

    public EnterpriseEntity loadGoim_requ_ente() {
        String propertyName = "goim_requ_ente";
        return (EnterpriseEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getGoim_requ_time() {
        return goim_requ_time;
    }

    public void setGoim_requ_time(Date goim_requ_time) {
        this.goim_requ_time = goim_requ_time;
    }

    public BigDecimal getGoim_requ_qty() {
        return goim_requ_qty;
    }

    public void setGoim_requ_qty(BigDecimal goim_requ_qty) {
        this.goim_requ_qty = goim_requ_qty;
    }

    public String getGoim_requ_meas() {
        return goim_requ_meas;
    }

    public void setGoim_requ_meas(String goim_requ_meas) {
        this.goim_requ_meas = goim_requ_meas;
    }

    public MeasureunitEntity loadGoim_requ_meas() {
        String propertyName = "goim_requ_meas";
        return (MeasureunitEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getGoim_requ_deadline() {
        return goim_requ_deadline;
    }

    public void setGoim_requ_deadline(Date goim_requ_deadline) {
        this.goim_requ_deadline = goim_requ_deadline;
    }

    public String getGoim_requ_closed() {
        return goim_requ_closed;
    }

    public void setGoim_requ_closed(String goim_requ_closed) {
        this.goim_requ_closed = goim_requ_closed;
    }

    public WordlistEntity loadGoim_requ_closed() {
        String propertyName = "goim_requ_closed";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}