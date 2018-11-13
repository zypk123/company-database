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
public class OpdeOpdePostSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 401406009815867L;
    
    /**职位编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opde_post_code", className="")
    private String opde_post_code;
    /**岗级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opde_grade", className="WordlistEntity")
    private String opde_grade;
    /**职位名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opde_post_name", className="")
    private String opde_post_name;
    /**职位职责*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opde_post_duty", className="")
    private String opde_post_duty;
    /**任职资格*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opde_post_qual", className="")
    private String opde_post_qual;
    /**岗级_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opde_grade_old", className="WordlistEntity")
    private String opde_grade_old;
    /**职位名称_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opde_name_old", className="")
    private String opde_name_old;
    /**职位职责_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opde_duty_old", className="")
    private String opde_duty_old;
    /**职位资格_旧*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opde_qual_old", className="")
    private String opde_qual_old;

    public String getOpde_post_code() {
        return opde_post_code;
    }

    public void setOpde_post_code(String opde_post_code) {
        this.opde_post_code = opde_post_code;
    }

    public String getOpde_grade() {
        return opde_grade;
    }

    public void setOpde_grade(String opde_grade) {
        this.opde_grade = opde_grade;
    }

    public WordlistEntity loadOpde_grade() {
        String propertyName = "opde_grade";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOpde_post_name() {
        return opde_post_name;
    }

    public void setOpde_post_name(String opde_post_name) {
        this.opde_post_name = opde_post_name;
    }

    public String getOpde_post_duty() {
        return opde_post_duty;
    }

    public void setOpde_post_duty(String opde_post_duty) {
        this.opde_post_duty = opde_post_duty;
    }

    public String getOpde_post_qual() {
        return opde_post_qual;
    }

    public void setOpde_post_qual(String opde_post_qual) {
        this.opde_post_qual = opde_post_qual;
    }

    public String getOpde_grade_old() {
        return opde_grade_old;
    }

    public void setOpde_grade_old(String opde_grade_old) {
        this.opde_grade_old = opde_grade_old;
    }

    public WordlistEntity loadOpde_grade_old() {
        String propertyName = "opde_grade_old";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOpde_name_old() {
        return opde_name_old;
    }

    public void setOpde_name_old(String opde_name_old) {
        this.opde_name_old = opde_name_old;
    }

    public String getOpde_duty_old() {
        return opde_duty_old;
    }

    public void setOpde_duty_old(String opde_duty_old) {
        this.opde_duty_old = opde_duty_old;
    }

    public String getOpde_qual_old() {
        return opde_qual_old;
    }

    public void setOpde_qual_old(String opde_qual_old) {
        this.opde_qual_old = opde_qual_old;
    }



}