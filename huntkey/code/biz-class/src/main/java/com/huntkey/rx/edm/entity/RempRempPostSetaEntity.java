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
public class RempRempPostSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5604002451995580L;
    
    /**岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_post_his", className="JobpositionEntity")
    private String remp_post_his;
    /**岗级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_pgrad_his", className="WordlistEntity")
    private String remp_pgrad_his;
    /**任职方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_dtyp_his", className="WordlistEntity")
    private String remp_dtyp_his;
    /**汇报上级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_pemp_his", className="EmployeeEntity")
    private String remp_pemp_his;
    /**生效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_post_beg", className="")
    private Date remp_post_beg;
    /**失效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_post_end", className="")
    private Date remp_post_end;

    public String getRemp_post_his() {
        return remp_post_his;
    }

    public void setRemp_post_his(String remp_post_his) {
        this.remp_post_his = remp_post_his;
    }

    public JobpositionEntity loadRemp_post_his() {
        String propertyName = "remp_post_his";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_pgrad_his() {
        return remp_pgrad_his;
    }

    public void setRemp_pgrad_his(String remp_pgrad_his) {
        this.remp_pgrad_his = remp_pgrad_his;
    }

    public WordlistEntity loadRemp_pgrad_his() {
        String propertyName = "remp_pgrad_his";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_dtyp_his() {
        return remp_dtyp_his;
    }

    public void setRemp_dtyp_his(String remp_dtyp_his) {
        this.remp_dtyp_his = remp_dtyp_his;
    }

    public WordlistEntity loadRemp_dtyp_his() {
        String propertyName = "remp_dtyp_his";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_pemp_his() {
        return remp_pemp_his;
    }

    public void setRemp_pemp_his(String remp_pemp_his) {
        this.remp_pemp_his = remp_pemp_his;
    }

    public EmployeeEntity loadRemp_pemp_his() {
        String propertyName = "remp_pemp_his";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getRemp_post_beg() {
        return remp_post_beg;
    }

    public void setRemp_post_beg(Date remp_post_beg) {
        this.remp_post_beg = remp_post_beg;
    }

    public Date getRemp_post_end() {
        return remp_post_end;
    }

    public void setRemp_post_end(Date remp_post_end) {
        this.remp_post_end = remp_post_end;
    }



}