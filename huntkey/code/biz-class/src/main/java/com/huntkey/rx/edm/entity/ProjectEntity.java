package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 项目类实体
 * 
 */
public class ProjectEntity extends ResourceEntity implements Serializable {
    private static final long serialVersionUID = 7984292134695053L;
    
    /**项目编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_code", className="")
    private String proj_code;
    /**项目名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_name", className="")
    private String proj_name;
    /**项目描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_desc", className="")
    private String proj_desc;
    /**负责人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_emp", className="EmployeeEntity")
    private String proj_emp;
    /**计划开始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_pbeg", className="")
    private Date proj_pbeg;
    /**计划结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_pend", className="")
    private Date proj_pend;
    /**实际开始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_rbeg", className="")
    private Date proj_rbeg;
    /**实际结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_rend", className="")
    private Date proj_rend;
    /**项目状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_status", className="WordlistEntity")
    private String proj_status;
    /**计划任务集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_task_set", className="ProjProjTaskSetaEntity")
    private List<ProjProjTaskSetaEntity> proj_task_set;
    /**项目成员集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_team_set", className="ProjProjTeamSetaEntity")
    private List<ProjProjTeamSetaEntity> proj_team_set;

    public String getProj_code() {
        return proj_code;
    }

    public void setProj_code(String proj_code) {
        this.proj_code = proj_code;
    }

    public String getProj_name() {
        return proj_name;
    }

    public void setProj_name(String proj_name) {
        this.proj_name = proj_name;
    }

    public String getProj_desc() {
        return proj_desc;
    }

    public void setProj_desc(String proj_desc) {
        this.proj_desc = proj_desc;
    }

    public String getProj_emp() {
        return proj_emp;
    }

    public void setProj_emp(String proj_emp) {
        this.proj_emp = proj_emp;
    }

    public EmployeeEntity loadProj_emp() {
        String propertyName = "proj_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getProj_pbeg() {
        return proj_pbeg;
    }

    public void setProj_pbeg(Date proj_pbeg) {
        this.proj_pbeg = proj_pbeg;
    }

    public Date getProj_pend() {
        return proj_pend;
    }

    public void setProj_pend(Date proj_pend) {
        this.proj_pend = proj_pend;
    }

    public Date getProj_rbeg() {
        return proj_rbeg;
    }

    public void setProj_rbeg(Date proj_rbeg) {
        this.proj_rbeg = proj_rbeg;
    }

    public Date getProj_rend() {
        return proj_rend;
    }

    public void setProj_rend(Date proj_rend) {
        this.proj_rend = proj_rend;
    }

    public String getProj_status() {
        return proj_status;
    }

    public void setProj_status(String proj_status) {
        this.proj_status = proj_status;
    }

    public WordlistEntity loadProj_status() {
        String propertyName = "proj_status";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<ProjProjTaskSetaEntity> loadProj_task_set() {
        String propertyName = "proj_task_set";
        String rootClass = "";
        return (List<ProjProjTaskSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setProj_task_set(List<ProjProjTaskSetaEntity> proj_task_set) {
        this.proj_task_set = proj_task_set;
    }

    public List<ProjProjTaskSetaEntity> getProj_task_set() {
        return proj_task_set;
    }

    public List<ProjProjTeamSetaEntity> loadProj_team_set() {
        String propertyName = "proj_team_set";
        String rootClass = "";
        return (List<ProjProjTeamSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setProj_team_set(List<ProjProjTeamSetaEntity> proj_team_set) {
        this.proj_team_set = proj_team_set;
    }

    public List<ProjProjTeamSetaEntity> getProj_team_set() {
        return proj_team_set;
    }



}