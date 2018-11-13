package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class OdscOdscChagSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5678176404204065L;
    
    /**下层编制_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_ll_old", className="")
    private Integer odsc_ll_old;
    /**标识*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_flag", className="")
    private String odsc_flag;
    /**部门编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_dept_code", className="")
    private String odsc_dept_code;
    /**上级部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_pdept", className="DepttreeEntity")
    private String odsc_pdept;
    /**部门名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_name", className="")
    private String odsc_name;
    /**部门简称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_sname", className="")
    private String odsc_sname;
    /**部门级别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_dgrade", className="WordlistEntity")
    private String odsc_dgrade;
    /**办公园区*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_rpak", className="ParkEntity")
    private String odsc_rpak;
    /**法人公司*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_mcop", className="RelationEntity")
    private String odsc_mcop;
    /**部门职责*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_function", className="")
    private String odsc_function;
    /**本层编制*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_tl_pnum", className="")
    private Integer odsc_tl_pnum;
    /**下层编制*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_ll_pnum", className="")
    private Integer odsc_ll_pnum;
    /**上级部门_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_pdept_old", className="DepttreeEntity")
    private String odsc_pdept_old;
    /**部门名称_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_name_old", className="")
    private String odsc_name_old;
    /**部门简称_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_sname_old", className="")
    private String odsc_sname_old;
    /**部门级别_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_dgrade_old", className="WordlistEntity")
    private String odsc_dgrade_old;
    /**办公园区_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_rpak_old", className="ParkEntity")
    private String odsc_rpak_old;
    /**法人公司_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_mcop_old", className="RelationEntity")
    private String odsc_mcop_old;
    /**部门职责_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_func_old", className="")
    private String odsc_func_old;
    /**本层编制_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_tl_old", className="")
    private Integer odsc_tl_old;
    /**层级码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_lvl", className="")
    private String odsc_lvl;

    public Integer getOdsc_ll_old() {
        return odsc_ll_old;
    }

    public void setOdsc_ll_old(Integer odsc_ll_old) {
        this.odsc_ll_old = odsc_ll_old;
    }

    public String getOdsc_flag() {
        return odsc_flag;
    }

    public void setOdsc_flag(String odsc_flag) {
        this.odsc_flag = odsc_flag;
    }

    public String getOdsc_dept_code() {
        return odsc_dept_code;
    }

    public void setOdsc_dept_code(String odsc_dept_code) {
        this.odsc_dept_code = odsc_dept_code;
    }

    public String getOdsc_pdept() {
        return odsc_pdept;
    }

    public void setOdsc_pdept(String odsc_pdept) {
        this.odsc_pdept = odsc_pdept;
    }

    public DepttreeEntity loadOdsc_pdept() {
        String propertyName = "odsc_pdept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdsc_name() {
        return odsc_name;
    }

    public void setOdsc_name(String odsc_name) {
        this.odsc_name = odsc_name;
    }

    public String getOdsc_sname() {
        return odsc_sname;
    }

    public void setOdsc_sname(String odsc_sname) {
        this.odsc_sname = odsc_sname;
    }

    public String getOdsc_dgrade() {
        return odsc_dgrade;
    }

    public void setOdsc_dgrade(String odsc_dgrade) {
        this.odsc_dgrade = odsc_dgrade;
    }

    public WordlistEntity loadOdsc_dgrade() {
        String propertyName = "odsc_dgrade";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdsc_rpak() {
        return odsc_rpak;
    }

    public void setOdsc_rpak(String odsc_rpak) {
        this.odsc_rpak = odsc_rpak;
    }

    public ParkEntity loadOdsc_rpak() {
        String propertyName = "odsc_rpak";
        return (ParkEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdsc_mcop() {
        return odsc_mcop;
    }

    public void setOdsc_mcop(String odsc_mcop) {
        this.odsc_mcop = odsc_mcop;
    }

    public RelationEntity loadOdsc_mcop() {
        String propertyName = "odsc_mcop";
        return (RelationEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdsc_function() {
        return odsc_function;
    }

    public void setOdsc_function(String odsc_function) {
        this.odsc_function = odsc_function;
    }

    public Integer getOdsc_tl_pnum() {
        return odsc_tl_pnum;
    }

    public void setOdsc_tl_pnum(Integer odsc_tl_pnum) {
        this.odsc_tl_pnum = odsc_tl_pnum;
    }

    public Integer getOdsc_ll_pnum() {
        return odsc_ll_pnum;
    }

    public void setOdsc_ll_pnum(Integer odsc_ll_pnum) {
        this.odsc_ll_pnum = odsc_ll_pnum;
    }

    public String getOdsc_pdept_old() {
        return odsc_pdept_old;
    }

    public void setOdsc_pdept_old(String odsc_pdept_old) {
        this.odsc_pdept_old = odsc_pdept_old;
    }

    public DepttreeEntity loadOdsc_pdept_old() {
        String propertyName = "odsc_pdept_old";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdsc_name_old() {
        return odsc_name_old;
    }

    public void setOdsc_name_old(String odsc_name_old) {
        this.odsc_name_old = odsc_name_old;
    }

    public String getOdsc_sname_old() {
        return odsc_sname_old;
    }

    public void setOdsc_sname_old(String odsc_sname_old) {
        this.odsc_sname_old = odsc_sname_old;
    }

    public String getOdsc_dgrade_old() {
        return odsc_dgrade_old;
    }

    public void setOdsc_dgrade_old(String odsc_dgrade_old) {
        this.odsc_dgrade_old = odsc_dgrade_old;
    }

    public WordlistEntity loadOdsc_dgrade_old() {
        String propertyName = "odsc_dgrade_old";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdsc_rpak_old() {
        return odsc_rpak_old;
    }

    public void setOdsc_rpak_old(String odsc_rpak_old) {
        this.odsc_rpak_old = odsc_rpak_old;
    }

    public ParkEntity loadOdsc_rpak_old() {
        String propertyName = "odsc_rpak_old";
        return (ParkEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdsc_mcop_old() {
        return odsc_mcop_old;
    }

    public void setOdsc_mcop_old(String odsc_mcop_old) {
        this.odsc_mcop_old = odsc_mcop_old;
    }

    public RelationEntity loadOdsc_mcop_old() {
        String propertyName = "odsc_mcop_old";
        return (RelationEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdsc_func_old() {
        return odsc_func_old;
    }

    public void setOdsc_func_old(String odsc_func_old) {
        this.odsc_func_old = odsc_func_old;
    }

    public Integer getOdsc_tl_old() {
        return odsc_tl_old;
    }

    public void setOdsc_tl_old(Integer odsc_tl_old) {
        this.odsc_tl_old = odsc_tl_old;
    }

    public String getOdsc_lvl() {
        return odsc_lvl;
    }

    public void setOdsc_lvl(String odsc_lvl) {
        this.odsc_lvl = odsc_lvl;
    }



}