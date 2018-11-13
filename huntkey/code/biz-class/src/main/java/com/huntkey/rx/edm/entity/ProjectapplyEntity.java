package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 项目立项单类实体
 * 
 */
public class ProjectapplyEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 568958696833732L;
    
    /**项目编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prty_code", className="")
    private String prty_code;
    /**项目名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prty_name", className="")
    private String prty_name;
    /**项目描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prty_desc", className="")
    private String prty_desc;
    /**负责人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prty_emp", className="EmployeeEntity")
    private String prty_emp;
    /**计划开始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prty_pbeg", className="")
    private Date prty_pbeg;
    /**计划结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prty_pend", className="")
    private Date prty_pend;
    /**实际开始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prty_rbeg", className="")
    private Date prty_rbeg;
    /**实际结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prty_rend", className="")
    private Date prty_rend;
    /**预计工时*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prty_time", className="")
    private BigDecimal prty_time;
    /**项目类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prty_type", className="WordlistEntity")
    private String prty_type;

    public String getPrty_code() {
        return prty_code;
    }

    public void setPrty_code(String prty_code) {
        this.prty_code = prty_code;
    }

    public String getPrty_name() {
        return prty_name;
    }

    public void setPrty_name(String prty_name) {
        this.prty_name = prty_name;
    }

    public String getPrty_desc() {
        return prty_desc;
    }

    public void setPrty_desc(String prty_desc) {
        this.prty_desc = prty_desc;
    }

    public String getPrty_emp() {
        return prty_emp;
    }

    public void setPrty_emp(String prty_emp) {
        this.prty_emp = prty_emp;
    }

    public EmployeeEntity loadPrty_emp() {
        String propertyName = "prty_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getPrty_pbeg() {
        return prty_pbeg;
    }

    public void setPrty_pbeg(Date prty_pbeg) {
        this.prty_pbeg = prty_pbeg;
    }

    public Date getPrty_pend() {
        return prty_pend;
    }

    public void setPrty_pend(Date prty_pend) {
        this.prty_pend = prty_pend;
    }

    public Date getPrty_rbeg() {
        return prty_rbeg;
    }

    public void setPrty_rbeg(Date prty_rbeg) {
        this.prty_rbeg = prty_rbeg;
    }

    public Date getPrty_rend() {
        return prty_rend;
    }

    public void setPrty_rend(Date prty_rend) {
        this.prty_rend = prty_rend;
    }

    public BigDecimal getPrty_time() {
        return prty_time;
    }

    public void setPrty_time(BigDecimal prty_time) {
        this.prty_time = prty_time;
    }

    public String getPrty_type() {
        return prty_type;
    }

    public void setPrty_type(String prty_type) {
        this.prty_type = prty_type;
    }

    public WordlistEntity loadPrty_type() {
        String propertyName = "prty_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}