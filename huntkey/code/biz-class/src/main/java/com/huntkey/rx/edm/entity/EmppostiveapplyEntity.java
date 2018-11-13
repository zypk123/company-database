package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 转正申请单类实体
 * 
 */
public class EmppostiveapplyEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 6032130842118398L;
    
    /**转正人数*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepa_num", className="")
    private Integer oepa_num;
    /**所属部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepa_dept", className="DepttreeEntity")
    private String oepa_dept;
    /**附件名称*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="oepa_file_name", className="")
    private String oepa_file_name;
    /**转正人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepa_emp", className="EmployeeEntity")
    private String oepa_emp;
    /**转正日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepa_date", className="")
    private Date oepa_date;
    /**备注*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepa_remark", className="")
    private String oepa_remark;
    /**指引人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepa_guild", className="EmployeeEntity")
    private String oepa_guild;
    /**转正报告*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepa_report", className="")
    private String oepa_report;
    /**报告路径*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepa_report_url", className="")
    private String oepa_report_url;
    /**转正意见*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepa_audit_set", className="OepaOepaAuditSetaEntity")
    private List<OepaOepaAuditSetaEntity> oepa_audit_set;

    public Integer getOepa_num() {
        return oepa_num;
    }

    public void setOepa_num(Integer oepa_num) {
        this.oepa_num = oepa_num;
    }

    public String getOepa_dept() {
        return oepa_dept;
    }

    public void setOepa_dept(String oepa_dept) {
        this.oepa_dept = oepa_dept;
    }

    public DepttreeEntity loadOepa_dept() {
        String propertyName = "oepa_dept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOepa_file_name() {
        return oepa_file_name;
    }

    public void setOepa_file_name(String oepa_file_name) {
        this.oepa_file_name = oepa_file_name;
    }

    public String getOepa_emp() {
        return oepa_emp;
    }

    public void setOepa_emp(String oepa_emp) {
        this.oepa_emp = oepa_emp;
    }

    public EmployeeEntity loadOepa_emp() {
        String propertyName = "oepa_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getOepa_date() {
        return oepa_date;
    }

    public void setOepa_date(Date oepa_date) {
        this.oepa_date = oepa_date;
    }

    public String getOepa_remark() {
        return oepa_remark;
    }

    public void setOepa_remark(String oepa_remark) {
        this.oepa_remark = oepa_remark;
    }

    public String getOepa_guild() {
        return oepa_guild;
    }

    public void setOepa_guild(String oepa_guild) {
        this.oepa_guild = oepa_guild;
    }

    public EmployeeEntity loadOepa_guild() {
        String propertyName = "oepa_guild";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOepa_report() {
        return oepa_report;
    }

    public void setOepa_report(String oepa_report) {
        this.oepa_report = oepa_report;
    }

    public String getOepa_report_url() {
        return oepa_report_url;
    }

    public void setOepa_report_url(String oepa_report_url) {
        this.oepa_report_url = oepa_report_url;
    }

    public List<OepaOepaAuditSetaEntity> loadOepa_audit_set() {
        String propertyName = "oepa_audit_set";
        String rootClass = "";
        return (List<OepaOepaAuditSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOepa_audit_set(List<OepaOepaAuditSetaEntity> oepa_audit_set) {
        this.oepa_audit_set = oepa_audit_set;
    }

    public List<OepaOepaAuditSetaEntity> getOepa_audit_set() {
        return oepa_audit_set;
    }



}