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
public class CufmCufmControlSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 2139472814871184L;
    
    /**控件对象模板*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="cufm_cmodel_obj", className="ControlEntity")
    private String cufm_cmodel_obj;
    /**控件对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="cufm_cobj", className="ControlEntity")
    private String cufm_cobj;
    /**序号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="cufm_csort", className="")
    private Integer cufm_csort;
    /**可见否*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="cufm_cvisible", className="")
    private String cufm_cvisible;
    /**可编辑否*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="cufm_edit", className="")
    private String cufm_edit;

    public String getCufm_cmodel_obj() {
        return cufm_cmodel_obj;
    }

    public void setCufm_cmodel_obj(String cufm_cmodel_obj) {
        this.cufm_cmodel_obj = cufm_cmodel_obj;
    }

    public ControlEntity loadCufm_cmodel_obj() {
        String propertyName = "cufm_cmodel_obj";
        return (ControlEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getCufm_cobj() {
        return cufm_cobj;
    }

    public void setCufm_cobj(String cufm_cobj) {
        this.cufm_cobj = cufm_cobj;
    }

    public ControlEntity loadCufm_cobj() {
        String propertyName = "cufm_cobj";
        return (ControlEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getCufm_csort() {
        return cufm_csort;
    }

    public void setCufm_csort(Integer cufm_csort) {
        this.cufm_csort = cufm_csort;
    }

    public String getCufm_cvisible() {
        return cufm_cvisible;
    }

    public void setCufm_cvisible(String cufm_cvisible) {
        this.cufm_cvisible = cufm_cvisible;
    }

    public String getCufm_edit() {
        return cufm_edit;
    }

    public void setCufm_edit(String cufm_edit) {
        this.cufm_edit = cufm_edit;
    }



}