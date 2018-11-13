package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 定制表单类实体
 * 
 */
public class CustomizedformEntity extends ShowEntity implements Serializable {
    private static final long serialVersionUID = 2890166741059769L;
    
    /**前版表单*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="cufm_upperform", className="ShowEntity")
    private String cufm_upperform;
    /**标准表单更新状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="cufm_updatestatus", className="")
    private String cufm_updatestatus;
    /**标准表单对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="cufm_form", className="FormEntity")
    private String cufm_form;
    /**版本号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="cufm_version", className="")
    private String cufm_version;
    /**表单定制部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="cufm_dept", className="DepttreeEntity")
    private String cufm_dept;
    /**表单定制岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="cufm_duty", className="JobpositionEntity")
    private String cufm_duty;
    /**表单定制人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="cufm_emp", className="EmployeeEntity")
    private String cufm_emp;
    /**控件集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="cufm_control_set", className="CufmCufmControlSetaEntity")
    private List<CufmCufmControlSetaEntity> cufm_control_set;

    public String getCufm_upperform() {
        return cufm_upperform;
    }

    public void setCufm_upperform(String cufm_upperform) {
        this.cufm_upperform = cufm_upperform;
    }

    public ShowEntity loadCufm_upperform() {
        String propertyName = "cufm_upperform";
        return (ShowEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getCufm_updatestatus() {
        return cufm_updatestatus;
    }

    public void setCufm_updatestatus(String cufm_updatestatus) {
        this.cufm_updatestatus = cufm_updatestatus;
    }

    public String getCufm_form() {
        return cufm_form;
    }

    public void setCufm_form(String cufm_form) {
        this.cufm_form = cufm_form;
    }

    public FormEntity loadCufm_form() {
        String propertyName = "cufm_form";
        return (FormEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getCufm_version() {
        return cufm_version;
    }

    public void setCufm_version(String cufm_version) {
        this.cufm_version = cufm_version;
    }

    public String getCufm_dept() {
        return cufm_dept;
    }

    public void setCufm_dept(String cufm_dept) {
        this.cufm_dept = cufm_dept;
    }

    public DepttreeEntity loadCufm_dept() {
        String propertyName = "cufm_dept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getCufm_duty() {
        return cufm_duty;
    }

    public void setCufm_duty(String cufm_duty) {
        this.cufm_duty = cufm_duty;
    }

    public JobpositionEntity loadCufm_duty() {
        String propertyName = "cufm_duty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getCufm_emp() {
        return cufm_emp;
    }

    public void setCufm_emp(String cufm_emp) {
        this.cufm_emp = cufm_emp;
    }

    public EmployeeEntity loadCufm_emp() {
        String propertyName = "cufm_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<CufmCufmControlSetaEntity> loadCufm_control_set() {
        String propertyName = "cufm_control_set";
        String rootClass = "";
        return (List<CufmCufmControlSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setCufm_control_set(List<CufmCufmControlSetaEntity> cufm_control_set) {
        this.cufm_control_set = cufm_control_set;
    }

    public List<CufmCufmControlSetaEntity> getCufm_control_set() {
        return cufm_control_set;
    }



}