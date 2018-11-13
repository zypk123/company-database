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
public class OdpsOdpsDpostSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 4023500232650918L;
    
    /**是否含下级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_sub", className="")
    private String odps_sub;
    /**岗位名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_name", className="")
    private String odps_name;
    /**岗位名称_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_name_old", className="")
    private String odps_name_old;
    /**职位*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="odps_rpof", className="PositiondefinitionEntity")
    private String odps_rpof;
    /**标识*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_flag", className="")
    private String odps_flag;
    /**部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_dept", className="DepttreeEntity")
    private String odps_dept;
    /**岗位编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_post", className="")
    private String odps_post;
    /**岗级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_pgrade", className="WordlistEntity")
    private String odps_pgrade;
    /**汇报岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_ppost", className="JobpositionEntity")
    private String odps_ppost;
    /**岗位职责*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_duty", className="")
    private String odps_duty;
    /**任职资格*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_qual", className="")
    private String odps_qual;
    /**岗级_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_pgrade_old", className="WordlistEntity")
    private String odps_pgrade_old;
    /**汇报岗位_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_ppost_old", className="JobpositionEntity")
    private String odps_ppost_old;
    /**岗位职责_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_duty_old", className="")
    private String odps_duty_old;
    /**任职资格_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_qual_old", className="")
    private String odps_qual_old;
    /**岗位层级码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_lvl", className="")
    private String odps_lvl;

    public String getOdps_sub() {
        return odps_sub;
    }

    public void setOdps_sub(String odps_sub) {
        this.odps_sub = odps_sub;
    }

    public String getOdps_name() {
        return odps_name;
    }

    public void setOdps_name(String odps_name) {
        this.odps_name = odps_name;
    }

    public String getOdps_name_old() {
        return odps_name_old;
    }

    public void setOdps_name_old(String odps_name_old) {
        this.odps_name_old = odps_name_old;
    }

    public String getOdps_rpof() {
        return odps_rpof;
    }

    public void setOdps_rpof(String odps_rpof) {
        this.odps_rpof = odps_rpof;
    }

    public PositiondefinitionEntity loadOdps_rpof() {
        String propertyName = "odps_rpof";
        return (PositiondefinitionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdps_flag() {
        return odps_flag;
    }

    public void setOdps_flag(String odps_flag) {
        this.odps_flag = odps_flag;
    }

    public String getOdps_dept() {
        return odps_dept;
    }

    public void setOdps_dept(String odps_dept) {
        this.odps_dept = odps_dept;
    }

    public DepttreeEntity loadOdps_dept() {
        String propertyName = "odps_dept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdps_post() {
        return odps_post;
    }

    public void setOdps_post(String odps_post) {
        this.odps_post = odps_post;
    }

    public String getOdps_pgrade() {
        return odps_pgrade;
    }

    public void setOdps_pgrade(String odps_pgrade) {
        this.odps_pgrade = odps_pgrade;
    }

    public WordlistEntity loadOdps_pgrade() {
        String propertyName = "odps_pgrade";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdps_ppost() {
        return odps_ppost;
    }

    public void setOdps_ppost(String odps_ppost) {
        this.odps_ppost = odps_ppost;
    }

    public JobpositionEntity loadOdps_ppost() {
        String propertyName = "odps_ppost";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdps_duty() {
        return odps_duty;
    }

    public void setOdps_duty(String odps_duty) {
        this.odps_duty = odps_duty;
    }

    public String getOdps_qual() {
        return odps_qual;
    }

    public void setOdps_qual(String odps_qual) {
        this.odps_qual = odps_qual;
    }

    public String getOdps_pgrade_old() {
        return odps_pgrade_old;
    }

    public void setOdps_pgrade_old(String odps_pgrade_old) {
        this.odps_pgrade_old = odps_pgrade_old;
    }

    public WordlistEntity loadOdps_pgrade_old() {
        String propertyName = "odps_pgrade_old";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdps_ppost_old() {
        return odps_ppost_old;
    }

    public void setOdps_ppost_old(String odps_ppost_old) {
        this.odps_ppost_old = odps_ppost_old;
    }

    public JobpositionEntity loadOdps_ppost_old() {
        String propertyName = "odps_ppost_old";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdps_duty_old() {
        return odps_duty_old;
    }

    public void setOdps_duty_old(String odps_duty_old) {
        this.odps_duty_old = odps_duty_old;
    }

    public String getOdps_qual_old() {
        return odps_qual_old;
    }

    public void setOdps_qual_old(String odps_qual_old) {
        this.odps_qual_old = odps_qual_old;
    }

    public String getOdps_lvl() {
        return odps_lvl;
    }

    public void setOdps_lvl(String odps_lvl) {
        this.odps_lvl = odps_lvl;
    }



}