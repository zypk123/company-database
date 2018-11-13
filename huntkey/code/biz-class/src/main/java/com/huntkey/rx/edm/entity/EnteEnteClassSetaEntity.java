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
public class EnteEnteClassSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 1070158902398140L;
    
    /**行业类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_class", className="WordlistEntity")
    private String ente_class;

    public String getEnte_class() {
        return ente_class;
    }

    public void setEnte_class(String ente_class) {
        this.ente_class = ente_class;
    }

    public WordlistEntity loadEnte_class() {
        String propertyName = "ente_class";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}