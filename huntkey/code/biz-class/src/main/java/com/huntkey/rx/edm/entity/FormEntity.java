package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 表单类实体
 * 
 */
public class FormEntity extends ShowEntity implements Serializable {
    private static final long serialVersionUID = 2202895900049396L;
    
    /**前版表单*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="form_upperform", className="ShowEntity")
    private String form_upperform;
    /**是否启用列表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="form_islist", className="")
    private String form_islist;
    /**状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="form_status", className="")
    private String form_status;
    /**创建时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="form_create", className="")
    private Date form_create;
    /**版本号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="form_version", className="")
    private String form_version;
    /**表单设计者*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="form_designer", className="EmployeeEntity")
    private String form_designer;
    /**控件集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="form_control_set", className="FormFormControlSetaEntity")
    private List<FormFormControlSetaEntity> form_control_set;
    /**定制表单集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="form_custom_set", className="FormFormCustomSetaEntity")
    private List<FormFormCustomSetaEntity> form_custom_set;

    public String getForm_upperform() {
        return form_upperform;
    }

    public void setForm_upperform(String form_upperform) {
        this.form_upperform = form_upperform;
    }

    public ShowEntity loadForm_upperform() {
        String propertyName = "form_upperform";
        return (ShowEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getForm_islist() {
        return form_islist;
    }

    public void setForm_islist(String form_islist) {
        this.form_islist = form_islist;
    }

    public String getForm_status() {
        return form_status;
    }

    public void setForm_status(String form_status) {
        this.form_status = form_status;
    }

    public Date getForm_create() {
        return form_create;
    }

    public void setForm_create(Date form_create) {
        this.form_create = form_create;
    }

    public String getForm_version() {
        return form_version;
    }

    public void setForm_version(String form_version) {
        this.form_version = form_version;
    }

    public String getForm_designer() {
        return form_designer;
    }

    public void setForm_designer(String form_designer) {
        this.form_designer = form_designer;
    }

    public EmployeeEntity loadForm_designer() {
        String propertyName = "form_designer";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<FormFormControlSetaEntity> loadForm_control_set() {
        String propertyName = "form_control_set";
        String rootClass = "";
        return (List<FormFormControlSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setForm_control_set(List<FormFormControlSetaEntity> form_control_set) {
        this.form_control_set = form_control_set;
    }

    public List<FormFormControlSetaEntity> getForm_control_set() {
        return form_control_set;
    }

    public List<FormFormCustomSetaEntity> loadForm_custom_set() {
        String propertyName = "form_custom_set";
        String rootClass = "";
        return (List<FormFormCustomSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setForm_custom_set(List<FormFormCustomSetaEntity> form_custom_set) {
        this.form_custom_set = form_custom_set;
    }

    public List<FormFormCustomSetaEntity> getForm_custom_set() {
        return form_custom_set;
    }



}