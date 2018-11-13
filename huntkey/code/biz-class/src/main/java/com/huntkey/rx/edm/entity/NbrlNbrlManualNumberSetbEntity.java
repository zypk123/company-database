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
public class NbrlNbrlManualNumberSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8922541399168153L;
    
    /**编号*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_manual_number", className="")
    private String nbrl_manual_number;
    /**排序值*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_manual_number_order", className="")
    private Integer nbrl_manual_number_order;
    /**是否已使用*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_is_use", className="")
    private String nbrl_is_use;

    public String getNbrl_manual_number() {
        return nbrl_manual_number;
    }

    public void setNbrl_manual_number(String nbrl_manual_number) {
        this.nbrl_manual_number = nbrl_manual_number;
    }

    public Integer getNbrl_manual_number_order() {
        return nbrl_manual_number_order;
    }

    public void setNbrl_manual_number_order(Integer nbrl_manual_number_order) {
        this.nbrl_manual_number_order = nbrl_manual_number_order;
    }

    public String getNbrl_is_use() {
        return nbrl_is_use;
    }

    public void setNbrl_is_use(String nbrl_is_use) {
        this.nbrl_is_use = nbrl_is_use;
    }



}