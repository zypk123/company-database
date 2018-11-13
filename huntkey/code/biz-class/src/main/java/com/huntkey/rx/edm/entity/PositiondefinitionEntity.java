package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 职位定义类实体
 * 
 */
public class PositiondefinitionEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 2141538541878748L;
    
    /**职位类别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpof_type", className="WordlistEntity")
    private String rpof_type;
    /**职位属性*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpof_prop", className="WordlistEntity")
    private String rpof_prop;
    /**职位编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpof_code", className="")
    private String rpof_code;
    /**职位名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpof_name", className="")
    private String rpof_name;
    /**岗级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpof_grade", className="WordlistEntity")
    private String rpof_grade;
    /**职责*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpof_duty", className="")
    private String rpof_duty;
    /**任职资格*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpof_qual", className="")
    private String rpof_qual;
    /**职类内排序*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpof_seq", className="")
    private Integer rpof_seq;

    public String getRpof_type() {
        return rpof_type;
    }

    public void setRpof_type(String rpof_type) {
        this.rpof_type = rpof_type;
    }

    public WordlistEntity loadRpof_type() {
        String propertyName = "rpof_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpof_prop() {
        return rpof_prop;
    }

    public void setRpof_prop(String rpof_prop) {
        this.rpof_prop = rpof_prop;
    }

    public WordlistEntity loadRpof_prop() {
        String propertyName = "rpof_prop";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpof_code() {
        return rpof_code;
    }

    public void setRpof_code(String rpof_code) {
        this.rpof_code = rpof_code;
    }

    public String getRpof_name() {
        return rpof_name;
    }

    public void setRpof_name(String rpof_name) {
        this.rpof_name = rpof_name;
    }

    public String getRpof_grade() {
        return rpof_grade;
    }

    public void setRpof_grade(String rpof_grade) {
        this.rpof_grade = rpof_grade;
    }

    public WordlistEntity loadRpof_grade() {
        String propertyName = "rpof_grade";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpof_duty() {
        return rpof_duty;
    }

    public void setRpof_duty(String rpof_duty) {
        this.rpof_duty = rpof_duty;
    }

    public String getRpof_qual() {
        return rpof_qual;
    }

    public void setRpof_qual(String rpof_qual) {
        this.rpof_qual = rpof_qual;
    }

    public Integer getRpof_seq() {
        return rpof_seq;
    }

    public void setRpof_seq(Integer rpof_seq) {
        this.rpof_seq = rpof_seq;
    }



}