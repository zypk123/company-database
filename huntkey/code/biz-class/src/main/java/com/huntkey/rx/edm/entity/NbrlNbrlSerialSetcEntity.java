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
public class NbrlNbrlSerialSetcEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9164344106084881L;
    
    /**流水号长度*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_serial_length", className="")
    private Integer nbrl_serial_length;
    /**流水号重置条件*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_serial_reset_condition", className="WordlistEntity")
    private String nbrl_serial_reset_condition;
    /**流水号步长*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_serial_step", className="")
    private Integer nbrl_serial_step;
    /**流水号重置开始值*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_serial_reset_number", className="")
    private String nbrl_serial_reset_number;
    /**流水号进位规则*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_serial_rule", className="")
    private String nbrl_serial_rule;

    public Integer getNbrl_serial_length() {
        return nbrl_serial_length;
    }

    public void setNbrl_serial_length(Integer nbrl_serial_length) {
        this.nbrl_serial_length = nbrl_serial_length;
    }

    public String getNbrl_serial_reset_condition() {
        return nbrl_serial_reset_condition;
    }

    public void setNbrl_serial_reset_condition(String nbrl_serial_reset_condition) {
        this.nbrl_serial_reset_condition = nbrl_serial_reset_condition;
    }

    public WordlistEntity loadNbrl_serial_reset_condition() {
        String propertyName = "nbrl_serial_reset_condition";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getNbrl_serial_step() {
        return nbrl_serial_step;
    }

    public void setNbrl_serial_step(Integer nbrl_serial_step) {
        this.nbrl_serial_step = nbrl_serial_step;
    }

    public String getNbrl_serial_reset_number() {
        return nbrl_serial_reset_number;
    }

    public void setNbrl_serial_reset_number(String nbrl_serial_reset_number) {
        this.nbrl_serial_reset_number = nbrl_serial_reset_number;
    }

    public String getNbrl_serial_rule() {
        return nbrl_serial_rule;
    }

    public void setNbrl_serial_rule(String nbrl_serial_rule) {
        this.nbrl_serial_rule = nbrl_serial_rule;
    }



}