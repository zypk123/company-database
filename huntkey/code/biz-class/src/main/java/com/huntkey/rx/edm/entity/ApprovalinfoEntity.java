package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 审批信息类实体
 * 
 */
public class ApprovalinfoEntity extends ProcessEntity implements Serializable {
    private static final long serialVersionUID = 5466545465522596L;
    
    /**流程实例*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="proi_proinstance", className="ProcinstanceEntity")
    private String proi_proinstance;
    /**审批人*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="proi_approver", className="EmployeeEntity")
    private String proi_approver;
    /**审批结果*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="proi_result", className="")
    private String proi_result;
    /**审批意见*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="proi_opinion", className="")
    private String proi_opinion;
    /**活动模板*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="proi_actpattern", className="ActivitytemplateEntity")
    private String proi_actpattern;
    /**完成类型*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="proi_complete_type", className="WordlistEntity")
    private String proi_complete_type;
    /**系统自动通过类型*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="proi_sys_complete_type", className="WordlistEntity")
    private String proi_sys_complete_type;
    /**岗位*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="proi_job", className="JobpositionEntity")
    private String proi_job;
    /**开始时间*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="proi_start_time", className="")
    private Date proi_start_time;
    /**结束时间*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="proi_end_time", className="")
    private Date proi_end_time;
    /**活动类型*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="proi_type", className="WordlistEntity")
    private String proi_type;
    /**来源节点*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="proi_source", className="ApprovalinfoEntity")
    private String proi_source;

    public String getProi_proinstance() {
        return proi_proinstance;
    }

    public void setProi_proinstance(String proi_proinstance) {
        this.proi_proinstance = proi_proinstance;
    }

    public ProcinstanceEntity loadProi_proinstance() {
        String propertyName = "proi_proinstance";
        return (ProcinstanceEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProi_approver() {
        return proi_approver;
    }

    public void setProi_approver(String proi_approver) {
        this.proi_approver = proi_approver;
    }

    public EmployeeEntity loadProi_approver() {
        String propertyName = "proi_approver";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProi_result() {
        return proi_result;
    }

    public void setProi_result(String proi_result) {
        this.proi_result = proi_result;
    }

    public String getProi_opinion() {
        return proi_opinion;
    }

    public void setProi_opinion(String proi_opinion) {
        this.proi_opinion = proi_opinion;
    }

    public String getProi_actpattern() {
        return proi_actpattern;
    }

    public void setProi_actpattern(String proi_actpattern) {
        this.proi_actpattern = proi_actpattern;
    }

    public ActivitytemplateEntity loadProi_actpattern() {
        String propertyName = "proi_actpattern";
        return (ActivitytemplateEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProi_complete_type() {
        return proi_complete_type;
    }

    public void setProi_complete_type(String proi_complete_type) {
        this.proi_complete_type = proi_complete_type;
    }

    public WordlistEntity loadProi_complete_type() {
        String propertyName = "proi_complete_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProi_sys_complete_type() {
        return proi_sys_complete_type;
    }

    public void setProi_sys_complete_type(String proi_sys_complete_type) {
        this.proi_sys_complete_type = proi_sys_complete_type;
    }

    public WordlistEntity loadProi_sys_complete_type() {
        String propertyName = "proi_sys_complete_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProi_job() {
        return proi_job;
    }

    public void setProi_job(String proi_job) {
        this.proi_job = proi_job;
    }

    public JobpositionEntity loadProi_job() {
        String propertyName = "proi_job";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getProi_start_time() {
        return proi_start_time;
    }

    public void setProi_start_time(Date proi_start_time) {
        this.proi_start_time = proi_start_time;
    }

    public Date getProi_end_time() {
        return proi_end_time;
    }

    public void setProi_end_time(Date proi_end_time) {
        this.proi_end_time = proi_end_time;
    }

    public String getProi_type() {
        return proi_type;
    }

    public void setProi_type(String proi_type) {
        this.proi_type = proi_type;
    }

    public WordlistEntity loadProi_type() {
        String propertyName = "proi_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProi_source() {
        return proi_source;
    }

    public void setProi_source(String proi_source) {
        this.proi_source = proi_source;
    }

    public ApprovalinfoEntity loadProi_source() {
        String propertyName = "proi_source";
        return (ApprovalinfoEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}