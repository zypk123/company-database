package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 任务执行单类实体
 * 
 */
public class TaskexecuteEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 9800128670090001L;
    
    /**项目对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_proj", className="")
    private String taex_proj;
    /**任务名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_name", className="")
    private String taex_name;
    /**任务描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_desc", className="")
    private String taex_desc;
    /**任务安排*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_plan", className="")
    private String taex_plan;
    /**计划开始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_pbeg", className="")
    private Date taex_pbeg;
    /**计划结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_pend", className="")
    private Date taex_pend;
    /**承接人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_dutyuser", className="EmployeeEntity")
    private String taex_dutyuser;
    /**验收人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_checkuser", className="EmployeeEntity")
    private String taex_checkuser;
    /**任务资源集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_resource_set", className="TaexTaexResourceSetaEntity")
    private List<TaexTaexResourceSetaEntity> taex_resource_set;
    /**前置任务*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_pretask", className="")
    private String taex_pretask;
    /**下一任务*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_nextask", className="")
    private String taex_nextask;

    public String getTaex_proj() {
        return taex_proj;
    }

    public void setTaex_proj(String taex_proj) {
        this.taex_proj = taex_proj;
    }

    public String getTaex_name() {
        return taex_name;
    }

    public void setTaex_name(String taex_name) {
        this.taex_name = taex_name;
    }

    public String getTaex_desc() {
        return taex_desc;
    }

    public void setTaex_desc(String taex_desc) {
        this.taex_desc = taex_desc;
    }

    public String getTaex_plan() {
        return taex_plan;
    }

    public void setTaex_plan(String taex_plan) {
        this.taex_plan = taex_plan;
    }

    public Date getTaex_pbeg() {
        return taex_pbeg;
    }

    public void setTaex_pbeg(Date taex_pbeg) {
        this.taex_pbeg = taex_pbeg;
    }

    public Date getTaex_pend() {
        return taex_pend;
    }

    public void setTaex_pend(Date taex_pend) {
        this.taex_pend = taex_pend;
    }

    public String getTaex_dutyuser() {
        return taex_dutyuser;
    }

    public void setTaex_dutyuser(String taex_dutyuser) {
        this.taex_dutyuser = taex_dutyuser;
    }

    public EmployeeEntity loadTaex_dutyuser() {
        String propertyName = "taex_dutyuser";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getTaex_checkuser() {
        return taex_checkuser;
    }

    public void setTaex_checkuser(String taex_checkuser) {
        this.taex_checkuser = taex_checkuser;
    }

    public EmployeeEntity loadTaex_checkuser() {
        String propertyName = "taex_checkuser";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<TaexTaexResourceSetaEntity> loadTaex_resource_set() {
        String propertyName = "taex_resource_set";
        String rootClass = "";
        return (List<TaexTaexResourceSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setTaex_resource_set(List<TaexTaexResourceSetaEntity> taex_resource_set) {
        this.taex_resource_set = taex_resource_set;
    }

    public List<TaexTaexResourceSetaEntity> getTaex_resource_set() {
        return taex_resource_set;
    }

    public String getTaex_pretask() {
        return taex_pretask;
    }

    public void setTaex_pretask(String taex_pretask) {
        this.taex_pretask = taex_pretask;
    }

    public String getTaex_nextask() {
        return taex_nextask;
    }

    public void setTaex_nextask(String taex_nextask) {
        this.taex_nextask = taex_nextask;
    }



}