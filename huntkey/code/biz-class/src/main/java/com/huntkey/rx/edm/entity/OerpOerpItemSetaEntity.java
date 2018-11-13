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
public class OerpOerpItemSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 4043906257860014L;
    
    /**排序*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oerp_seq", className="")
    private Integer oerp_seq;
    /**离职事项*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oerp_item", className="WordlistEntity")
    private String oerp_item;
    /**经办人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oerp_handler", className="EmployeeEntity")
    private String oerp_handler;
    /**事项状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oerp_item_state", className="WordlistEntity")
    private String oerp_item_state;
    /**办理时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oerp_time", className="")
    private Date oerp_time;
    /**类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oerp_item_type", className="")
    private String oerp_item_type;
    /**金额*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oerp_money", className="")
    private BigDecimal oerp_money;
    /**备注*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oerp_remark", className="")
    private String oerp_remark;

    public Integer getOerp_seq() {
        return oerp_seq;
    }

    public void setOerp_seq(Integer oerp_seq) {
        this.oerp_seq = oerp_seq;
    }

    public String getOerp_item() {
        return oerp_item;
    }

    public void setOerp_item(String oerp_item) {
        this.oerp_item = oerp_item;
    }

    public WordlistEntity loadOerp_item() {
        String propertyName = "oerp_item";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOerp_handler() {
        return oerp_handler;
    }

    public void setOerp_handler(String oerp_handler) {
        this.oerp_handler = oerp_handler;
    }

    public EmployeeEntity loadOerp_handler() {
        String propertyName = "oerp_handler";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOerp_item_state() {
        return oerp_item_state;
    }

    public void setOerp_item_state(String oerp_item_state) {
        this.oerp_item_state = oerp_item_state;
    }

    public WordlistEntity loadOerp_item_state() {
        String propertyName = "oerp_item_state";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getOerp_time() {
        return oerp_time;
    }

    public void setOerp_time(Date oerp_time) {
        this.oerp_time = oerp_time;
    }

    public String getOerp_item_type() {
        return oerp_item_type;
    }

    public void setOerp_item_type(String oerp_item_type) {
        this.oerp_item_type = oerp_item_type;
    }

    public BigDecimal getOerp_money() {
        return oerp_money;
    }

    public void setOerp_money(BigDecimal oerp_money) {
        this.oerp_money = oerp_money;
    }

    public String getOerp_remark() {
        return oerp_remark;
    }

    public void setOerp_remark(String oerp_remark) {
        this.oerp_remark = oerp_remark;
    }



}