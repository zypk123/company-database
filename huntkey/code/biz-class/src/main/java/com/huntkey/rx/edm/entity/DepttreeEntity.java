package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 部门类实体
 * 
 */
public class DepttreeEntity extends OrganizationEntity implements Serializable {
    private static final long serialVersionUID = 3323657079236936L;
    
    /**部门层码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_lvl_code", className="")
    private String mdep_lvl_code;
    /**失效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_end", className="")
    private Date mdep_end;
    /**生效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_beg", className="")
    private Date mdep_beg;
    /**上级部门*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="mdep_par", className="DepttreeEntity")
    private String mdep_par;
    /**部门编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_code", className="")
    private String mdep_code;
    /**部门名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_name", className="")
    private String mdep_name;
    /**部门简称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_sname", className="")
    private String mdep_sname;
    /**部门级别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_grade", className="WordlistEntity")
    private String mdep_grade;
    /**排序*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_seq", className="")
    private Integer mdep_seq;
    /**部门全称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_lname", className="")
    private String mdep_lname;
    /**办公园区*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_rpak", className="ParkEntity")
    private String mdep_rpak;
    /**法人公司*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_mcop", className="RelationEntity")
    private String mdep_mcop;
    /**部门职责*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_duty", className="")
    private String mdep_duty;
    /**部门编制*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_tl_num", className="")
    private Integer mdep_tl_num;
    /**下层编制*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_ll_num", className="")
    private Integer mdep_ll_num;
    /**主责岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_leader_post", className="JobpositionEntity")
    private String mdep_leader_post;
    /**负责人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_leader", className="EmployeeEntity")
    private String mdep_leader;
    /**变更记录*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_chag_set", className="MdepMdepChagSetaEntity")
    private List<MdepMdepChagSetaEntity> mdep_chag_set;
    /**负责人集合*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mdep_chgr_set", className="MdepMdepChgrSetaEntity")
    private List<MdepMdepChgrSetaEntity> mdep_chgr_set;

    public String getMdep_lvl_code() {
        return mdep_lvl_code;
    }

    public void setMdep_lvl_code(String mdep_lvl_code) {
        this.mdep_lvl_code = mdep_lvl_code;
    }

    public Date getMdep_end() {
        return mdep_end;
    }

    public void setMdep_end(Date mdep_end) {
        this.mdep_end = mdep_end;
    }

    public Date getMdep_beg() {
        return mdep_beg;
    }

    public void setMdep_beg(Date mdep_beg) {
        this.mdep_beg = mdep_beg;
    }

    public String getMdep_par() {
        return mdep_par;
    }

    public void setMdep_par(String mdep_par) {
        this.mdep_par = mdep_par;
    }

    public DepttreeEntity loadMdep_par() {
        String propertyName = "mdep_par";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMdep_code() {
        return mdep_code;
    }

    public void setMdep_code(String mdep_code) {
        this.mdep_code = mdep_code;
    }

    public String getMdep_name() {
        return mdep_name;
    }

    public void setMdep_name(String mdep_name) {
        this.mdep_name = mdep_name;
    }

    public String getMdep_sname() {
        return mdep_sname;
    }

    public void setMdep_sname(String mdep_sname) {
        this.mdep_sname = mdep_sname;
    }

    public String getMdep_grade() {
        return mdep_grade;
    }

    public void setMdep_grade(String mdep_grade) {
        this.mdep_grade = mdep_grade;
    }

    public WordlistEntity loadMdep_grade() {
        String propertyName = "mdep_grade";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getMdep_seq() {
        return mdep_seq;
    }

    public void setMdep_seq(Integer mdep_seq) {
        this.mdep_seq = mdep_seq;
    }

    public String getMdep_lname() {
        return mdep_lname;
    }

    public void setMdep_lname(String mdep_lname) {
        this.mdep_lname = mdep_lname;
    }

    public String getMdep_rpak() {
        return mdep_rpak;
    }

    public void setMdep_rpak(String mdep_rpak) {
        this.mdep_rpak = mdep_rpak;
    }

    public ParkEntity loadMdep_rpak() {
        String propertyName = "mdep_rpak";
        return (ParkEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMdep_mcop() {
        return mdep_mcop;
    }

    public void setMdep_mcop(String mdep_mcop) {
        this.mdep_mcop = mdep_mcop;
    }

    public RelationEntity loadMdep_mcop() {
        String propertyName = "mdep_mcop";
        return (RelationEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMdep_duty() {
        return mdep_duty;
    }

    public void setMdep_duty(String mdep_duty) {
        this.mdep_duty = mdep_duty;
    }

    public Integer getMdep_tl_num() {
        return mdep_tl_num;
    }

    public void setMdep_tl_num(Integer mdep_tl_num) {
        this.mdep_tl_num = mdep_tl_num;
    }

    public Integer getMdep_ll_num() {
        return mdep_ll_num;
    }

    public void setMdep_ll_num(Integer mdep_ll_num) {
        this.mdep_ll_num = mdep_ll_num;
    }

    public String getMdep_leader_post() {
        return mdep_leader_post;
    }

    public void setMdep_leader_post(String mdep_leader_post) {
        this.mdep_leader_post = mdep_leader_post;
    }

    public JobpositionEntity loadMdep_leader_post() {
        String propertyName = "mdep_leader_post";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMdep_leader() {
        return mdep_leader;
    }

    public void setMdep_leader(String mdep_leader) {
        this.mdep_leader = mdep_leader;
    }

    public EmployeeEntity loadMdep_leader() {
        String propertyName = "mdep_leader";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<MdepMdepChagSetaEntity> loadMdep_chag_set() {
        String propertyName = "mdep_chag_set";
        String rootClass = "";
        return (List<MdepMdepChagSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setMdep_chag_set(List<MdepMdepChagSetaEntity> mdep_chag_set) {
        this.mdep_chag_set = mdep_chag_set;
    }

    public List<MdepMdepChagSetaEntity> getMdep_chag_set() {
        return mdep_chag_set;
    }

    public List<MdepMdepChgrSetaEntity> loadMdep_chgr_set() {
        String propertyName = "mdep_chgr_set";
        String rootClass = "";
        return (List<MdepMdepChgrSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setMdep_chgr_set(List<MdepMdepChgrSetaEntity> mdep_chgr_set) {
        this.mdep_chgr_set = mdep_chgr_set;
    }

    public List<MdepMdepChgrSetaEntity> getMdep_chgr_set() {
        return mdep_chgr_set;
    }



}