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
public class PrplPrplTeamSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5587154247338990L;
    
    /**序号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_tsort", className="")
    private Integer prpl_tsort;
    /**成员*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_tmember", className="EmployeeEntity")
    private String prpl_tmember;
    /**项目角色*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_trole", className="WordlistEntity")
    private String prpl_trole;
    /**工作职责*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_tobligation", className="")
    private String prpl_tobligation;
    /**加入日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_pjoin", className="")
    private Date prpl_pjoin;
    /**离开日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_pleave", className="")
    private Date prpl_pleave;

    public Integer getPrpl_tsort() {
        return prpl_tsort;
    }

    public void setPrpl_tsort(Integer prpl_tsort) {
        this.prpl_tsort = prpl_tsort;
    }

    public String getPrpl_tmember() {
        return prpl_tmember;
    }

    public void setPrpl_tmember(String prpl_tmember) {
        this.prpl_tmember = prpl_tmember;
    }

    public EmployeeEntity loadPrpl_tmember() {
        String propertyName = "prpl_tmember";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrpl_trole() {
        return prpl_trole;
    }

    public void setPrpl_trole(String prpl_trole) {
        this.prpl_trole = prpl_trole;
    }

    public WordlistEntity loadPrpl_trole() {
        String propertyName = "prpl_trole";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrpl_tobligation() {
        return prpl_tobligation;
    }

    public void setPrpl_tobligation(String prpl_tobligation) {
        this.prpl_tobligation = prpl_tobligation;
    }

    public Date getPrpl_pjoin() {
        return prpl_pjoin;
    }

    public void setPrpl_pjoin(Date prpl_pjoin) {
        this.prpl_pjoin = prpl_pjoin;
    }

    public Date getPrpl_pleave() {
        return prpl_pleave;
    }

    public void setPrpl_pleave(Date prpl_pleave) {
        this.prpl_pleave = prpl_pleave;
    }



}