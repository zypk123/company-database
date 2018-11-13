package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 活动实例类实体
 * 
 */
public class ActinstanceEntity extends ProcessEntity implements Serializable {
    private static final long serialVersionUID = 645038511986748L;
    
    /**流程定义*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="atit_procpattern", className="ProcesspatternEntity")
    private String atit_procpattern;
    /**活动模板*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="atit_actpattern", className="ActivitytemplateEntity")
    private String atit_actpattern;
    /**流程实例*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="atit_procinstance", className="ProcinstanceEntity")
    private String atit_procinstance;
    /**状态*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="atit_status", className="WordlistEntity")
    private String atit_status;
    /**开始时间*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="atit_starttime", className="")
    private Date atit_starttime;
    /**结束时间*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="atit_endtime", className="")
    private Date atit_endtime;
    /**节点类型*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="atit_type", className="WordlistEntity")
    private String atit_type;
    /**岗位*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="atit_job", className="JobpositionEntity")
    private String atit_job;
    /**执行员工*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="atit_operator", className="EmployeeEntity")
    private String atit_operator;
    /**目标节点*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="atit_dest", className="ApprovalinfoEntity")
    private String atit_dest;

    public String getAtit_procpattern() {
        return atit_procpattern;
    }

    public void setAtit_procpattern(String atit_procpattern) {
        this.atit_procpattern = atit_procpattern;
    }

    public ProcesspatternEntity loadAtit_procpattern() {
        String propertyName = "atit_procpattern";
        return (ProcesspatternEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAtit_actpattern() {
        return atit_actpattern;
    }

    public void setAtit_actpattern(String atit_actpattern) {
        this.atit_actpattern = atit_actpattern;
    }

    public ActivitytemplateEntity loadAtit_actpattern() {
        String propertyName = "atit_actpattern";
        return (ActivitytemplateEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAtit_procinstance() {
        return atit_procinstance;
    }

    public void setAtit_procinstance(String atit_procinstance) {
        this.atit_procinstance = atit_procinstance;
    }

    public ProcinstanceEntity loadAtit_procinstance() {
        String propertyName = "atit_procinstance";
        return (ProcinstanceEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAtit_status() {
        return atit_status;
    }

    public void setAtit_status(String atit_status) {
        this.atit_status = atit_status;
    }

    public WordlistEntity loadAtit_status() {
        String propertyName = "atit_status";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getAtit_starttime() {
        return atit_starttime;
    }

    public void setAtit_starttime(Date atit_starttime) {
        this.atit_starttime = atit_starttime;
    }

    public Date getAtit_endtime() {
        return atit_endtime;
    }

    public void setAtit_endtime(Date atit_endtime) {
        this.atit_endtime = atit_endtime;
    }

    public String getAtit_type() {
        return atit_type;
    }

    public void setAtit_type(String atit_type) {
        this.atit_type = atit_type;
    }

    public WordlistEntity loadAtit_type() {
        String propertyName = "atit_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAtit_job() {
        return atit_job;
    }

    public void setAtit_job(String atit_job) {
        this.atit_job = atit_job;
    }

    public JobpositionEntity loadAtit_job() {
        String propertyName = "atit_job";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAtit_operator() {
        return atit_operator;
    }

    public void setAtit_operator(String atit_operator) {
        this.atit_operator = atit_operator;
    }

    public EmployeeEntity loadAtit_operator() {
        String propertyName = "atit_operator";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAtit_dest() {
        return atit_dest;
    }

    public void setAtit_dest(String atit_dest) {
        this.atit_dest = atit_dest;
    }

    public ApprovalinfoEntity loadAtit_dest() {
        String propertyName = "atit_dest";
        return (ApprovalinfoEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}