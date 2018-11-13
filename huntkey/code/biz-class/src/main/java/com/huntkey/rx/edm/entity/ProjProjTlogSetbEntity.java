package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class ProjProjTlogSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9022159832051638L;
    
    /**任务安排*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tlplan", className="")
    private String proj_tlplan;
    /**承接人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tldutyuser", className="EmployeeEntity")
    private String proj_tldutyuser;
    /**验收人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tlcheckuser", className="EmployeeEntity")
    private String proj_tlcheckuser;
    /**实际开始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tlrbeg", className="")
    private Date proj_tlrbeg;
    /**实际结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tlrend", className="")
    private Date proj_tlrend;
    /**内容*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tlcont", className="")
    private String proj_tlcont;
    /**产出物*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tloutput", className="")
    private String proj_tloutput;
    /**任务执行单对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tlexcut", className="TaskexecuteEntity")
    private String proj_tlexcut;

    public String getProj_tlplan() {
        return proj_tlplan;
    }

    public void setProj_tlplan(String proj_tlplan) {
        this.proj_tlplan = proj_tlplan;
    }

    public String getProj_tldutyuser() {
        return proj_tldutyuser;
    }

    public void setProj_tldutyuser(String proj_tldutyuser) {
        this.proj_tldutyuser = proj_tldutyuser;
    }

    public EmployeeEntity loadProj_tldutyuser() {
        String propertyName = "proj_tldutyuser";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProj_tlcheckuser() {
        return proj_tlcheckuser;
    }

    public void setProj_tlcheckuser(String proj_tlcheckuser) {
        this.proj_tlcheckuser = proj_tlcheckuser;
    }

    public EmployeeEntity loadProj_tlcheckuser() {
        String propertyName = "proj_tlcheckuser";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getProj_tlrbeg() {
        return proj_tlrbeg;
    }

    public void setProj_tlrbeg(Date proj_tlrbeg) {
        this.proj_tlrbeg = proj_tlrbeg;
    }

    public Date getProj_tlrend() {
        return proj_tlrend;
    }

    public void setProj_tlrend(Date proj_tlrend) {
        this.proj_tlrend = proj_tlrend;
    }

    public String getProj_tlcont() {
        return proj_tlcont;
    }

    public void setProj_tlcont(String proj_tlcont) {
        this.proj_tlcont = proj_tlcont;
    }

    public String getProj_tloutput() {
        return proj_tloutput;
    }

    public void setProj_tloutput(String proj_tloutput) {
        this.proj_tloutput = proj_tloutput;
    }

    public String getProj_tlexcut() {
        return proj_tlexcut;
    }

    public void setProj_tlexcut(String proj_tlexcut) {
        this.proj_tlexcut = proj_tlexcut;
    }

    public TaskexecuteEntity loadProj_tlexcut() {
        String propertyName = "proj_tlexcut";
        return (TaskexecuteEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}