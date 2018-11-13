package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class ConlConlChildSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8948155729161914L;
    
    /**控件对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_cobj", className="ControlEntity")
    private String conl_cobj;
    /**序号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_csort", className="")
    private Integer conl_csort;
    /**可见否*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_cvisible", className="")
    private String conl_cvisible;
    /**可编辑否*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_cedit", className="")
    private String conl_cedit;

    public String getConl_cobj() {
        return conl_cobj;
    }

    public void setConl_cobj(String conl_cobj) {
        this.conl_cobj = conl_cobj;
    }

    public ControlEntity loadConl_cobj() {
        String propertyName = "conl_cobj";
        return (ControlEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getConl_csort() {
        return conl_csort;
    }

    public void setConl_csort(Integer conl_csort) {
        this.conl_csort = conl_csort;
    }

    public String getConl_cvisible() {
        return conl_cvisible;
    }

    public void setConl_cvisible(String conl_cvisible) {
        this.conl_cvisible = conl_cvisible;
    }

    public String getConl_cedit() {
        return conl_cedit;
    }

    public void setConl_cedit(String conl_cedit) {
        this.conl_cedit = conl_cedit;
    }



}