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
public class EnteEnteGoveSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5551864719073141L;
    
    /**政府*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_gove", className="GovernmentEntity")
    private String ente_gove;

    public String getEnte_gove() {
        return ente_gove;
    }

    public void setEnte_gove(String ente_gove) {
        this.ente_gove = ente_gove;
    }

    public GovernmentEntity loadEnte_gove() {
        String propertyName = "ente_gove";
        return (GovernmentEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}