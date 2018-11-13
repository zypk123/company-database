package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 活动流程类实体
 * 
 */
public class ProcessEntity extends EdmEntity implements Serializable {
    private static final long serialVersionUID = 1212073013404300L;
    
    /**创建维护表单*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_orderobj", className="OrderEntity")
    private String proc_orderobj;
    /**步骤*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_step", className="")
    private String proc_step;
    /**编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_code", className="")
    private String proc_code;
    /**名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_name", className="")
    private String proc_name;
    /**创建岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_duty", className="JobpositionEntity")
    private String proc_duty;
    /**创建人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_emp", className="EmployeeEntity")
    private String proc_emp;
    /**创建时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_adddate", className="")
    private Date proc_adddate;
    /**执行岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_execduty", className="JobpositionEntity")
    private String proc_execduty;
    /**执行岗位限定条件*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_exec_con", className="")
    private String proc_exec_con;
    /**活动关闭条件公式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_close_con", className="")
    private String proc_close_con;
    /**是否允许编辑*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_isedit", className="")
    private String proc_isedit;
    /**子流程*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_child_set", className="ProcProcChildSetaEntity")
    private List<ProcProcChildSetaEntity> proc_child_set;

    public String getProc_orderobj() {
        return proc_orderobj;
    }

    public void setProc_orderobj(String proc_orderobj) {
        this.proc_orderobj = proc_orderobj;
    }

    public OrderEntity loadProc_orderobj() {
        String propertyName = "proc_orderobj";
        return (OrderEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProc_step() {
        return proc_step;
    }

    public void setProc_step(String proc_step) {
        this.proc_step = proc_step;
    }

    public String getProc_code() {
        return proc_code;
    }

    public void setProc_code(String proc_code) {
        this.proc_code = proc_code;
    }

    public String getProc_name() {
        return proc_name;
    }

    public void setProc_name(String proc_name) {
        this.proc_name = proc_name;
    }

    public String getProc_duty() {
        return proc_duty;
    }

    public void setProc_duty(String proc_duty) {
        this.proc_duty = proc_duty;
    }

    public JobpositionEntity loadProc_duty() {
        String propertyName = "proc_duty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProc_emp() {
        return proc_emp;
    }

    public void setProc_emp(String proc_emp) {
        this.proc_emp = proc_emp;
    }

    public EmployeeEntity loadProc_emp() {
        String propertyName = "proc_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getProc_adddate() {
        return proc_adddate;
    }

    public void setProc_adddate(Date proc_adddate) {
        this.proc_adddate = proc_adddate;
    }

    public String getProc_execduty() {
        return proc_execduty;
    }

    public void setProc_execduty(String proc_execduty) {
        this.proc_execduty = proc_execduty;
    }

    public JobpositionEntity loadProc_execduty() {
        String propertyName = "proc_execduty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProc_exec_con() {
        return proc_exec_con;
    }

    public void setProc_exec_con(String proc_exec_con) {
        this.proc_exec_con = proc_exec_con;
    }

    public String getProc_close_con() {
        return proc_close_con;
    }

    public void setProc_close_con(String proc_close_con) {
        this.proc_close_con = proc_close_con;
    }

    public String getProc_isedit() {
        return proc_isedit;
    }

    public void setProc_isedit(String proc_isedit) {
        this.proc_isedit = proc_isedit;
    }

    public List<ProcProcChildSetaEntity> loadProc_child_set() {
        String propertyName = "proc_child_set";
        String rootClass = "";
        return (List<ProcProcChildSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setProc_child_set(List<ProcProcChildSetaEntity> proc_child_set) {
        this.proc_child_set = proc_child_set;
    }

    public List<ProcProcChildSetaEntity> getProc_child_set() {
        return proc_child_set;
    }



}