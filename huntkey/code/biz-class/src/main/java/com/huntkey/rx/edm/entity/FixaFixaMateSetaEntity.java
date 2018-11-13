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
public class FixaFixaMateSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5220278717812028L;
    
    /**维护方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_mate_way", className="WordlistEntity")
    private String fixa_mate_way;
    /**维护类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_mate_type", className="WordlistEntity")
    private String fixa_mate_type;
    /**间隔期(天)*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_mate_days", className="")
    private Integer fixa_mate_days;
    /**上次维护日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_last_date", className="")
    private Date fixa_last_date;
    /**下次维护日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_next_date", className="")
    private Date fixa_next_date;
    /**维护描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_mate_desc", className="")
    private String fixa_mate_desc;
    /**维护部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_mate_dept", className="")
    private String fixa_mate_dept;
    /**维护单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_mate_unit", className="")
    private String fixa_mate_unit;

    public String getFixa_mate_way() {
        return fixa_mate_way;
    }

    public void setFixa_mate_way(String fixa_mate_way) {
        this.fixa_mate_way = fixa_mate_way;
    }

    public WordlistEntity loadFixa_mate_way() {
        String propertyName = "fixa_mate_way";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getFixa_mate_type() {
        return fixa_mate_type;
    }

    public void setFixa_mate_type(String fixa_mate_type) {
        this.fixa_mate_type = fixa_mate_type;
    }

    public WordlistEntity loadFixa_mate_type() {
        String propertyName = "fixa_mate_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getFixa_mate_days() {
        return fixa_mate_days;
    }

    public void setFixa_mate_days(Integer fixa_mate_days) {
        this.fixa_mate_days = fixa_mate_days;
    }

    public Date getFixa_last_date() {
        return fixa_last_date;
    }

    public void setFixa_last_date(Date fixa_last_date) {
        this.fixa_last_date = fixa_last_date;
    }

    public Date getFixa_next_date() {
        return fixa_next_date;
    }

    public void setFixa_next_date(Date fixa_next_date) {
        this.fixa_next_date = fixa_next_date;
    }

    public String getFixa_mate_desc() {
        return fixa_mate_desc;
    }

    public void setFixa_mate_desc(String fixa_mate_desc) {
        this.fixa_mate_desc = fixa_mate_desc;
    }

    public String getFixa_mate_dept() {
        return fixa_mate_dept;
    }

    public void setFixa_mate_dept(String fixa_mate_dept) {
        this.fixa_mate_dept = fixa_mate_dept;
    }

    public String getFixa_mate_unit() {
        return fixa_mate_unit;
    }

    public void setFixa_mate_unit(String fixa_mate_unit) {
        this.fixa_mate_unit = fixa_mate_unit;
    }



}