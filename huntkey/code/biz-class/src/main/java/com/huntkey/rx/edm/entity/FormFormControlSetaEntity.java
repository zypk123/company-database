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
public class FormFormControlSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 1661187771478315L;
    
    /**控件对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="form_cobj", className="ControlEntity")
    private String form_cobj;
    /**序号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="form_csort", className="")
    private Integer form_csort;
    /**可见否*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="form_cvisible", className="")
    private String form_cvisible;
    /**可编辑否*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="form_cedit", className="")
    private String form_cedit;

    public String getForm_cobj() {
        return form_cobj;
    }

    public void setForm_cobj(String form_cobj) {
        this.form_cobj = form_cobj;
    }

    public ControlEntity loadForm_cobj() {
        String propertyName = "form_cobj";
        return (ControlEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getForm_csort() {
        return form_csort;
    }

    public void setForm_csort(Integer form_csort) {
        this.form_csort = form_csort;
    }

    public String getForm_cvisible() {
        return form_cvisible;
    }

    public void setForm_cvisible(String form_cvisible) {
        this.form_cvisible = form_cvisible;
    }

    public String getForm_cedit() {
        return form_cedit;
    }

    public void setForm_cedit(String form_cedit) {
        this.form_cedit = form_cedit;
    }



}