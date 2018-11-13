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
public class PrplPrplPlantaskSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 3556114504339695L;
    
    /**任务名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_pname", className="")
    private String prpl_pname;
    /**任务描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_pdesc", className="")
    private String prpl_pdesc;
    /**计划开始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_ppbeg", className="")
    private Date prpl_ppbeg;
    /**计划结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_ppend", className="")
    private Date prpl_ppend;
    /**任务层级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_plvlnum", className="")
    private Integer prpl_plvlnum;
    /**任务层码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_plvlcode", className="")
    private String prpl_plvlcode;
    /**负责人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_pemp", className="EmployeeEntity")
    private String prpl_pemp;
    /**是否里程碑*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_pismilestone", className="")
    private String prpl_pismilestone;
    /**是否叶子*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_pisleaf", className="")
    private String prpl_pisleaf;

    public String getPrpl_pname() {
        return prpl_pname;
    }

    public void setPrpl_pname(String prpl_pname) {
        this.prpl_pname = prpl_pname;
    }

    public String getPrpl_pdesc() {
        return prpl_pdesc;
    }

    public void setPrpl_pdesc(String prpl_pdesc) {
        this.prpl_pdesc = prpl_pdesc;
    }

    public Date getPrpl_ppbeg() {
        return prpl_ppbeg;
    }

    public void setPrpl_ppbeg(Date prpl_ppbeg) {
        this.prpl_ppbeg = prpl_ppbeg;
    }

    public Date getPrpl_ppend() {
        return prpl_ppend;
    }

    public void setPrpl_ppend(Date prpl_ppend) {
        this.prpl_ppend = prpl_ppend;
    }

    public Integer getPrpl_plvlnum() {
        return prpl_plvlnum;
    }

    public void setPrpl_plvlnum(Integer prpl_plvlnum) {
        this.prpl_plvlnum = prpl_plvlnum;
    }

    public String getPrpl_plvlcode() {
        return prpl_plvlcode;
    }

    public void setPrpl_plvlcode(String prpl_plvlcode) {
        this.prpl_plvlcode = prpl_plvlcode;
    }

    public String getPrpl_pemp() {
        return prpl_pemp;
    }

    public void setPrpl_pemp(String prpl_pemp) {
        this.prpl_pemp = prpl_pemp;
    }

    public EmployeeEntity loadPrpl_pemp() {
        String propertyName = "prpl_pemp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrpl_pismilestone() {
        return prpl_pismilestone;
    }

    public void setPrpl_pismilestone(String prpl_pismilestone) {
        this.prpl_pismilestone = prpl_pismilestone;
    }

    public String getPrpl_pisleaf() {
        return prpl_pisleaf;
    }

    public void setPrpl_pisleaf(String prpl_pisleaf) {
        this.prpl_pisleaf = prpl_pisleaf;
    }



}