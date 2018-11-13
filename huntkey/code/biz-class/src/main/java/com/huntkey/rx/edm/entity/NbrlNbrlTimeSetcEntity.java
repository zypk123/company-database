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
public class NbrlNbrlTimeSetcEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8983728763849943L;
    
    /**时间类型*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_time_type", className="WordlistEntity")
    private String nbrl_time_type;
    /**时间长度*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_time_length", className="")
    private Integer nbrl_time_length;

    public String getNbrl_time_type() {
        return nbrl_time_type;
    }

    public void setNbrl_time_type(String nbrl_time_type) {
        this.nbrl_time_type = nbrl_time_type;
    }

    public WordlistEntity loadNbrl_time_type() {
        String propertyName = "nbrl_time_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getNbrl_time_length() {
        return nbrl_time_length;
    }

    public void setNbrl_time_length(Integer nbrl_time_length) {
        this.nbrl_time_length = nbrl_time_length;
    }



}