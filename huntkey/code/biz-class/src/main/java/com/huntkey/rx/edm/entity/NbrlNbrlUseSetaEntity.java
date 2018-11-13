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
public class NbrlNbrlUseSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8091002286553805L;
    
    /**属性*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_useproperty", className="")
    private String nbrl_useproperty;

    public String getNbrl_useproperty() {
        return nbrl_useproperty;
    }

    public void setNbrl_useproperty(String nbrl_useproperty) {
        this.nbrl_useproperty = nbrl_useproperty;
    }



}