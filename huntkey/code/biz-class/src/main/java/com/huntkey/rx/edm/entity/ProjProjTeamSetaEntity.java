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
public class ProjProjTeamSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 3444115429158491L;
    
    /**序号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_msort", className="")
    private Integer proj_msort;
    /**成员*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_member", className="EmployeeEntity")
    private String proj_member;
    /**项目角色*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_mrole", className="WordlistEntity")
    private String proj_mrole;
    /**工作职责*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_mobligation", className="")
    private String proj_mobligation;
    /**加入日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_mjoin", className="")
    private Date proj_mjoin;
    /**离开日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proj_mleave", className="")
    private Date proj_mleave;

    public Integer getProj_msort() {
        return proj_msort;
    }

    public void setProj_msort(Integer proj_msort) {
        this.proj_msort = proj_msort;
    }

    public String getProj_member() {
        return proj_member;
    }

    public void setProj_member(String proj_member) {
        this.proj_member = proj_member;
    }

    public EmployeeEntity loadProj_member() {
        String propertyName = "proj_member";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProj_mrole() {
        return proj_mrole;
    }

    public void setProj_mrole(String proj_mrole) {
        this.proj_mrole = proj_mrole;
    }

    public WordlistEntity loadProj_mrole() {
        String propertyName = "proj_mrole";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProj_mobligation() {
        return proj_mobligation;
    }

    public void setProj_mobligation(String proj_mobligation) {
        this.proj_mobligation = proj_mobligation;
    }

    public Date getProj_mjoin() {
        return proj_mjoin;
    }

    public void setProj_mjoin(Date proj_mjoin) {
        this.proj_mjoin = proj_mjoin;
    }

    public Date getProj_mleave() {
        return proj_mleave;
    }

    public void setProj_mleave(Date proj_mleave) {
        this.proj_mleave = proj_mleave;
    }



}