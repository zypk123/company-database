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
public class NbrlNbrlPropertyTypecEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 4478085830734196L;
    
    /**属性*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_property", className="")
    private String nbrl_property;
    /**属性截取类型*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_property_intercept_type", className="WordlistEntity")
    private String nbrl_property_intercept_type;
    /**属性截取开始位数*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_property_intercept_start", className="")
    private Integer nbrl_property_intercept_start;
    /**属性截取字符数*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_property_intercept_count", className="")
    private Integer nbrl_property_intercept_count;

    public String getNbrl_property() {
        return nbrl_property;
    }

    public void setNbrl_property(String nbrl_property) {
        this.nbrl_property = nbrl_property;
    }

    public String getNbrl_property_intercept_type() {
        return nbrl_property_intercept_type;
    }

    public void setNbrl_property_intercept_type(String nbrl_property_intercept_type) {
        this.nbrl_property_intercept_type = nbrl_property_intercept_type;
    }

    public WordlistEntity loadNbrl_property_intercept_type() {
        String propertyName = "nbrl_property_intercept_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getNbrl_property_intercept_start() {
        return nbrl_property_intercept_start;
    }

    public void setNbrl_property_intercept_start(Integer nbrl_property_intercept_start) {
        this.nbrl_property_intercept_start = nbrl_property_intercept_start;
    }

    public Integer getNbrl_property_intercept_count() {
        return nbrl_property_intercept_count;
    }

    public void setNbrl_property_intercept_count(Integer nbrl_property_intercept_count) {
        this.nbrl_property_intercept_count = nbrl_property_intercept_count;
    }



}