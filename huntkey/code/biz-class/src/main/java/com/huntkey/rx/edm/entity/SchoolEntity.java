package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 学校类实体
 * 
 */
public class SchoolEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 2964113037177425L;
    
    /**学校编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rsch_code", className="")
    private String rsch_code;
    /**学校名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rsch_name", className="")
    private String rsch_name;
    /**学校所在地*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rsch_city", className="AreaEntity")
    private String rsch_city;
    /**学校层次*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rsch_ranking", className="WordlistEntity")
    private String rsch_ranking;
    /**学校网址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rsch_website", className="")
    private String rsch_website;
    /**是否有效*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rsch_enable", className="")
    private String rsch_enable;

    public String getRsch_code() {
        return rsch_code;
    }

    public void setRsch_code(String rsch_code) {
        this.rsch_code = rsch_code;
    }

    public String getRsch_name() {
        return rsch_name;
    }

    public void setRsch_name(String rsch_name) {
        this.rsch_name = rsch_name;
    }

    public String getRsch_city() {
        return rsch_city;
    }

    public void setRsch_city(String rsch_city) {
        this.rsch_city = rsch_city;
    }

    public AreaEntity loadRsch_city() {
        String propertyName = "rsch_city";
        return (AreaEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRsch_ranking() {
        return rsch_ranking;
    }

    public void setRsch_ranking(String rsch_ranking) {
        this.rsch_ranking = rsch_ranking;
    }

    public WordlistEntity loadRsch_ranking() {
        String propertyName = "rsch_ranking";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRsch_website() {
        return rsch_website;
    }

    public void setRsch_website(String rsch_website) {
        this.rsch_website = rsch_website;
    }

    public String getRsch_enable() {
        return rsch_enable;
    }

    public void setRsch_enable(String rsch_enable) {
        this.rsch_enable = rsch_enable;
    }



}