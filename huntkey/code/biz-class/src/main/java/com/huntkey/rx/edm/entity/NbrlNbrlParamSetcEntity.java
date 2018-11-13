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
public class NbrlNbrlParamSetcEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 4903068483968559L;
    
    /**参数*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_param", className="")
    private String nbrl_param;
    /**参数截取类型*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_param_type", className="WordlistEntity")
    private String nbrl_param_type;
    /**参数截取开始位置*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_param_start_position", className="")
    private Integer nbrl_param_start_position;
    /**参数截取字符数*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_param_intercept_count", className="")
    private Integer nbrl_param_intercept_count;

    public String getNbrl_param() {
        return nbrl_param;
    }

    public void setNbrl_param(String nbrl_param) {
        this.nbrl_param = nbrl_param;
    }

    public String getNbrl_param_type() {
        return nbrl_param_type;
    }

    public void setNbrl_param_type(String nbrl_param_type) {
        this.nbrl_param_type = nbrl_param_type;
    }

    public WordlistEntity loadNbrl_param_type() {
        String propertyName = "nbrl_param_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getNbrl_param_start_position() {
        return nbrl_param_start_position;
    }

    public void setNbrl_param_start_position(Integer nbrl_param_start_position) {
        this.nbrl_param_start_position = nbrl_param_start_position;
    }

    public Integer getNbrl_param_intercept_count() {
        return nbrl_param_intercept_count;
    }

    public void setNbrl_param_intercept_count(Integer nbrl_param_intercept_count) {
        this.nbrl_param_intercept_count = nbrl_param_intercept_count;
    }



}