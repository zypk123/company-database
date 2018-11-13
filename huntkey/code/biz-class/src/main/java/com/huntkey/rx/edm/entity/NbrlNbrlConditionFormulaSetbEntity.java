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
public class NbrlNbrlConditionFormulaSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 1020328164127389L;
    
    /**规则条件公式*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_condition_formula", className="")
    private String nbrl_condition_formula;
    /**规则条件类型*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_cformula_type", className="")
    private String nbrl_cformula_type;

    public String getNbrl_condition_formula() {
        return nbrl_condition_formula;
    }

    public void setNbrl_condition_formula(String nbrl_condition_formula) {
        this.nbrl_condition_formula = nbrl_condition_formula;
    }

    public String getNbrl_cformula_type() {
        return nbrl_cformula_type;
    }

    public void setNbrl_cformula_type(String nbrl_cformula_type) {
        this.nbrl_cformula_type = nbrl_cformula_type;
    }



}