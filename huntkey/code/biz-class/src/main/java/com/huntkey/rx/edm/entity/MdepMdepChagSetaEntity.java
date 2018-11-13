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
public class MdepMdepChagSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 2652328213890277L;
    
    /**部门编制*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="mdep_tnum_his", className="")
    private Integer mdep_tnum_his;
    /**部门名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_name_his", className="")
    private String mdep_name_his;
    /**部门简称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_sname_his", className="")
    private String mdep_sname_his;
    /**部门级别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_grade_his", className="WordlistEntity")
    private String mdep_grade_his;
    /**排序*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_seq_his", className="")
    private Integer mdep_seq_his;
    /**办公园区*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_rpak_his", className="ParkEntity")
    private String mdep_rpak_his;
    /**法人公司*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_mcop_his", className="RelationEntity")
    private String mdep_mcop_his;
    /**部门职责*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_duty_his", className="")
    private String mdep_duty_his;
    /**生效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_beg_his", className="")
    private Date mdep_beg_his;
    /**失效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_end_his", className="")
    private Date mdep_end_his;
    /**部门层码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_lvl_his", className="")
    private String mdep_lvl_his;
    /**上级部门*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="mdep_par_his", className="DepttreeEntity")
    private String mdep_par_his;

    public Integer getMdep_tnum_his() {
        return mdep_tnum_his;
    }

    public void setMdep_tnum_his(Integer mdep_tnum_his) {
        this.mdep_tnum_his = mdep_tnum_his;
    }

    public String getMdep_name_his() {
        return mdep_name_his;
    }

    public void setMdep_name_his(String mdep_name_his) {
        this.mdep_name_his = mdep_name_his;
    }

    public String getMdep_sname_his() {
        return mdep_sname_his;
    }

    public void setMdep_sname_his(String mdep_sname_his) {
        this.mdep_sname_his = mdep_sname_his;
    }

    public String getMdep_grade_his() {
        return mdep_grade_his;
    }

    public void setMdep_grade_his(String mdep_grade_his) {
        this.mdep_grade_his = mdep_grade_his;
    }

    public WordlistEntity loadMdep_grade_his() {
        String propertyName = "mdep_grade_his";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getMdep_seq_his() {
        return mdep_seq_his;
    }

    public void setMdep_seq_his(Integer mdep_seq_his) {
        this.mdep_seq_his = mdep_seq_his;
    }

    public String getMdep_rpak_his() {
        return mdep_rpak_his;
    }

    public void setMdep_rpak_his(String mdep_rpak_his) {
        this.mdep_rpak_his = mdep_rpak_his;
    }

    public ParkEntity loadMdep_rpak_his() {
        String propertyName = "mdep_rpak_his";
        return (ParkEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMdep_mcop_his() {
        return mdep_mcop_his;
    }

    public void setMdep_mcop_his(String mdep_mcop_his) {
        this.mdep_mcop_his = mdep_mcop_his;
    }

    public RelationEntity loadMdep_mcop_his() {
        String propertyName = "mdep_mcop_his";
        return (RelationEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMdep_duty_his() {
        return mdep_duty_his;
    }

    public void setMdep_duty_his(String mdep_duty_his) {
        this.mdep_duty_his = mdep_duty_his;
    }

    public Date getMdep_beg_his() {
        return mdep_beg_his;
    }

    public void setMdep_beg_his(Date mdep_beg_his) {
        this.mdep_beg_his = mdep_beg_his;
    }

    public Date getMdep_end_his() {
        return mdep_end_his;
    }

    public void setMdep_end_his(Date mdep_end_his) {
        this.mdep_end_his = mdep_end_his;
    }

    public String getMdep_lvl_his() {
        return mdep_lvl_his;
    }

    public void setMdep_lvl_his(String mdep_lvl_his) {
        this.mdep_lvl_his = mdep_lvl_his;
    }

    public String getMdep_par_his() {
        return mdep_par_his;
    }

    public void setMdep_par_his(String mdep_par_his) {
        this.mdep_par_his = mdep_par_his;
    }

    public DepttreeEntity loadMdep_par_his() {
        String propertyName = "mdep_par_his";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}