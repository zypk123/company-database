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
public class RposRposChagSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 245693113296419L;
    
    /**职位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_post_his", className="PositiondefinitionEntity")
    private String rpos_post_his;
    /**汇报岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_prep_his", className="JobpositionEntity")
    private String rpos_prep_his;
    /**岗级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_grade_his", className="WordlistEntity")
    private String rpos_grade_his;
    /**职责*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_duty_his", className="")
    private String rpos_duty_his;
    /**任职资格*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_qual_his", className="")
    private String rpos_qual_his;
    /**生效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_beg_his", className="")
    private Date rpos_beg_his;
    /**失效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_end_his", className="")
    private Date rpos_end_his;
    /**岗位名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpos_name_his", className="")
    private String rpos_name_his;

    public String getRpos_post_his() {
        return rpos_post_his;
    }

    public void setRpos_post_his(String rpos_post_his) {
        this.rpos_post_his = rpos_post_his;
    }

    public PositiondefinitionEntity loadRpos_post_his() {
        String propertyName = "rpos_post_his";
        return (PositiondefinitionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpos_prep_his() {
        return rpos_prep_his;
    }

    public void setRpos_prep_his(String rpos_prep_his) {
        this.rpos_prep_his = rpos_prep_his;
    }

    public JobpositionEntity loadRpos_prep_his() {
        String propertyName = "rpos_prep_his";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpos_grade_his() {
        return rpos_grade_his;
    }

    public void setRpos_grade_his(String rpos_grade_his) {
        this.rpos_grade_his = rpos_grade_his;
    }

    public WordlistEntity loadRpos_grade_his() {
        String propertyName = "rpos_grade_his";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpos_duty_his() {
        return rpos_duty_his;
    }

    public void setRpos_duty_his(String rpos_duty_his) {
        this.rpos_duty_his = rpos_duty_his;
    }

    public String getRpos_qual_his() {
        return rpos_qual_his;
    }

    public void setRpos_qual_his(String rpos_qual_his) {
        this.rpos_qual_his = rpos_qual_his;
    }

    public Date getRpos_beg_his() {
        return rpos_beg_his;
    }

    public void setRpos_beg_his(Date rpos_beg_his) {
        this.rpos_beg_his = rpos_beg_his;
    }

    public Date getRpos_end_his() {
        return rpos_end_his;
    }

    public void setRpos_end_his(Date rpos_end_his) {
        this.rpos_end_his = rpos_end_his;
    }

    public String getRpos_name_his() {
        return rpos_name_his;
    }

    public void setRpos_name_his(String rpos_name_his) {
        this.rpos_name_his = rpos_name_his;
    }



}