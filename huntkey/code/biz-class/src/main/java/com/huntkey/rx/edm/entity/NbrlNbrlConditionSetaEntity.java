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
public class NbrlNbrlConditionSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8646846665761593L;
    
    /**规则条件公式集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_condition_formula_set", className="NbrlNbrlConditionFormulaSetbEntity")
    private List<NbrlNbrlConditionFormulaSetbEntity> nbrl_condition_formula_set;
    /**规则描述*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_desc", className="")
    private String nbrl_desc;
    /**当前流水号*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_current_serial", className="")
    private String nbrl_current_serial;
    /**是否手工编号*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_is_manual_number", className="")
    private String nbrl_is_manual_number;
    /**手工编号集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_manual_number_set", className="NbrlNbrlManualNumberSetbEntity")
    private List<NbrlNbrlManualNumberSetbEntity> nbrl_manual_number_set;
    /**规则项集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_item_set", className="NbrlNbrlItemSetbEntity")
    private List<NbrlNbrlItemSetbEntity> nbrl_item_set;
    /**启用状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="nbrl_is_used", className="")
    private String nbrl_is_used;

    public List<NbrlNbrlConditionFormulaSetbEntity> loadNbrl_condition_formula_set() {
        String propertyName = "nbrl_condition_formula_set";
        String rootClass = "NumberrulesEntity";
        return (List<NbrlNbrlConditionFormulaSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setNbrl_condition_formula_set(List<NbrlNbrlConditionFormulaSetbEntity> nbrl_condition_formula_set) {
        this.nbrl_condition_formula_set = nbrl_condition_formula_set;
    }

    public List<NbrlNbrlConditionFormulaSetbEntity> getNbrl_condition_formula_set() {
        return nbrl_condition_formula_set;
    }

    public String getNbrl_desc() {
        return nbrl_desc;
    }

    public void setNbrl_desc(String nbrl_desc) {
        this.nbrl_desc = nbrl_desc;
    }

    public String getNbrl_current_serial() {
        return nbrl_current_serial;
    }

    public void setNbrl_current_serial(String nbrl_current_serial) {
        this.nbrl_current_serial = nbrl_current_serial;
    }

    public String getNbrl_is_manual_number() {
        return nbrl_is_manual_number;
    }

    public void setNbrl_is_manual_number(String nbrl_is_manual_number) {
        this.nbrl_is_manual_number = nbrl_is_manual_number;
    }

    public List<NbrlNbrlManualNumberSetbEntity> loadNbrl_manual_number_set() {
        String propertyName = "nbrl_manual_number_set";
        String rootClass = "NumberrulesEntity";
        return (List<NbrlNbrlManualNumberSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setNbrl_manual_number_set(List<NbrlNbrlManualNumberSetbEntity> nbrl_manual_number_set) {
        this.nbrl_manual_number_set = nbrl_manual_number_set;
    }

    public List<NbrlNbrlManualNumberSetbEntity> getNbrl_manual_number_set() {
        return nbrl_manual_number_set;
    }

    public List<NbrlNbrlItemSetbEntity> loadNbrl_item_set() {
        String propertyName = "nbrl_item_set";
        String rootClass = "NumberrulesEntity";
        return (List<NbrlNbrlItemSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setNbrl_item_set(List<NbrlNbrlItemSetbEntity> nbrl_item_set) {
        this.nbrl_item_set = nbrl_item_set;
    }

    public List<NbrlNbrlItemSetbEntity> getNbrl_item_set() {
        return nbrl_item_set;
    }

    public String getNbrl_is_used() {
        return nbrl_is_used;
    }

    public void setNbrl_is_used(String nbrl_is_used) {
        this.nbrl_is_used = nbrl_is_used;
    }



}