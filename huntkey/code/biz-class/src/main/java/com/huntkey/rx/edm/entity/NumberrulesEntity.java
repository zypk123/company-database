package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 编号规则类实体
 * 
 */
public class NumberrulesEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 2238322262491499L;
    
    /**规则代码*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_code", className="")
    private String nbrl_code;
    /**规则名称*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_name", className="")
    private String nbrl_name;
    /**流水是否按条件递增*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_serial_increase", className="")
    private String nbrl_serial_increase;
    /**规则使用属性集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_use_set", className="NbrlNbrlUseSetaEntity")
    private List<NbrlNbrlUseSetaEntity> nbrl_use_set;
    /**规则条件集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_condition_set", className="NbrlNbrlConditionSetaEntity")
    private List<NbrlNbrlConditionSetaEntity> nbrl_condition_set;

    public String getNbrl_code() {
        return nbrl_code;
    }

    public void setNbrl_code(String nbrl_code) {
        this.nbrl_code = nbrl_code;
    }

    public String getNbrl_name() {
        return nbrl_name;
    }

    public void setNbrl_name(String nbrl_name) {
        this.nbrl_name = nbrl_name;
    }

    public String getNbrl_serial_increase() {
        return nbrl_serial_increase;
    }

    public void setNbrl_serial_increase(String nbrl_serial_increase) {
        this.nbrl_serial_increase = nbrl_serial_increase;
    }

    public List<NbrlNbrlUseSetaEntity> loadNbrl_use_set() {
        String propertyName = "nbrl_use_set";
        String rootClass = "";
        return (List<NbrlNbrlUseSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setNbrl_use_set(List<NbrlNbrlUseSetaEntity> nbrl_use_set) {
        this.nbrl_use_set = nbrl_use_set;
    }

    public List<NbrlNbrlUseSetaEntity> getNbrl_use_set() {
        return nbrl_use_set;
    }

    public List<NbrlNbrlConditionSetaEntity> loadNbrl_condition_set() {
        String propertyName = "nbrl_condition_set";
        String rootClass = "";
        return (List<NbrlNbrlConditionSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setNbrl_condition_set(List<NbrlNbrlConditionSetaEntity> nbrl_condition_set) {
        this.nbrl_condition_set = nbrl_condition_set;
    }

    public List<NbrlNbrlConditionSetaEntity> getNbrl_condition_set() {
        return nbrl_condition_set;
    }



}