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
public class RempRempContSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 2381718641918959L;
    
    /**签订类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_sign_type", className="WordlistEntity")
    private String remp_sign_type;
    /**合同开始日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_cont_beg", className="")
    private Date remp_cont_beg;
    /**合同结束日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_cont_end", className="")
    private Date remp_cont_end;
    /**签订日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_cont_sdate", className="")
    private Date remp_cont_sdate;
    /**法人公司*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_cont_mcop", className="RelationEntity")
    private String remp_cont_mcop;
    /**合同实际结束日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_cont_rend", className="")
    private Date remp_cont_rend;

    public String getRemp_sign_type() {
        return remp_sign_type;
    }

    public void setRemp_sign_type(String remp_sign_type) {
        this.remp_sign_type = remp_sign_type;
    }

    public WordlistEntity loadRemp_sign_type() {
        String propertyName = "remp_sign_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getRemp_cont_beg() {
        return remp_cont_beg;
    }

    public void setRemp_cont_beg(Date remp_cont_beg) {
        this.remp_cont_beg = remp_cont_beg;
    }

    public Date getRemp_cont_end() {
        return remp_cont_end;
    }

    public void setRemp_cont_end(Date remp_cont_end) {
        this.remp_cont_end = remp_cont_end;
    }

    public Date getRemp_cont_sdate() {
        return remp_cont_sdate;
    }

    public void setRemp_cont_sdate(Date remp_cont_sdate) {
        this.remp_cont_sdate = remp_cont_sdate;
    }

    public String getRemp_cont_mcop() {
        return remp_cont_mcop;
    }

    public void setRemp_cont_mcop(String remp_cont_mcop) {
        this.remp_cont_mcop = remp_cont_mcop;
    }

    public RelationEntity loadRemp_cont_mcop() {
        String propertyName = "remp_cont_mcop";
        return (RelationEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getRemp_cont_rend() {
        return remp_cont_rend;
    }

    public void setRemp_cont_rend(Date remp_cont_rend) {
        this.remp_cont_rend = remp_cont_rend;
    }



}