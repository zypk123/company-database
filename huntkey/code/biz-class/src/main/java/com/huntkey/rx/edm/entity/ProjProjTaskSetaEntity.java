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
public class ProjProjTaskSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 317820607231383L;
    
    /**任务名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tname", className="")
    private String proj_tname;
    /**任务描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tdesc", className="")
    private String proj_tdesc;
    /**计划开始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tpbeg", className="")
    private Date proj_tpbeg;
    /**计划结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tbend", className="")
    private Date proj_tbend;
    /**任务层级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tlvlnum", className="")
    private Integer proj_tlvlnum;
    /**任务层码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tlvlcode", className="")
    private String proj_tlvlcode;
    /**负责人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_temp", className="EmployeeEntity")
    private String proj_temp;
    /**是否里程碑*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tismilestone", className="")
    private String proj_tismilestone;
    /**是否叶子*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tisleaf", className="")
    private String proj_tisleaf;
    /**任务日志集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_tlog_set", className="ProjProjTlogSetbEntity")
    private List<ProjProjTlogSetbEntity> proj_tlog_set;

    public String getProj_tname() {
        return proj_tname;
    }

    public void setProj_tname(String proj_tname) {
        this.proj_tname = proj_tname;
    }

    public String getProj_tdesc() {
        return proj_tdesc;
    }

    public void setProj_tdesc(String proj_tdesc) {
        this.proj_tdesc = proj_tdesc;
    }

    public Date getProj_tpbeg() {
        return proj_tpbeg;
    }

    public void setProj_tpbeg(Date proj_tpbeg) {
        this.proj_tpbeg = proj_tpbeg;
    }

    public Date getProj_tbend() {
        return proj_tbend;
    }

    public void setProj_tbend(Date proj_tbend) {
        this.proj_tbend = proj_tbend;
    }

    public Integer getProj_tlvlnum() {
        return proj_tlvlnum;
    }

    public void setProj_tlvlnum(Integer proj_tlvlnum) {
        this.proj_tlvlnum = proj_tlvlnum;
    }

    public String getProj_tlvlcode() {
        return proj_tlvlcode;
    }

    public void setProj_tlvlcode(String proj_tlvlcode) {
        this.proj_tlvlcode = proj_tlvlcode;
    }

    public String getProj_temp() {
        return proj_temp;
    }

    public void setProj_temp(String proj_temp) {
        this.proj_temp = proj_temp;
    }

    public EmployeeEntity loadProj_temp() {
        String propertyName = "proj_temp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProj_tismilestone() {
        return proj_tismilestone;
    }

    public void setProj_tismilestone(String proj_tismilestone) {
        this.proj_tismilestone = proj_tismilestone;
    }

    public String getProj_tisleaf() {
        return proj_tisleaf;
    }

    public void setProj_tisleaf(String proj_tisleaf) {
        this.proj_tisleaf = proj_tisleaf;
    }

    public List<ProjProjTlogSetbEntity> loadProj_tlog_set() {
        String propertyName = "proj_tlog_set";
        String rootClass = "ProjectEntity";
        return (List<ProjProjTlogSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setProj_tlog_set(List<ProjProjTlogSetbEntity> proj_tlog_set) {
        this.proj_tlog_set = proj_tlog_set;
    }

    public List<ProjProjTlogSetbEntity> getProj_tlog_set() {
        return proj_tlog_set;
    }



}