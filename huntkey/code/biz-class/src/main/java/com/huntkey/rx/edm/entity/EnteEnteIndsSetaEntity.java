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
public class EnteEnteIndsSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 1906600304226655L;
    
    /**行业*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_inds", className="WordlistEntity")
    private String ente_inds;

    public String getEnte_inds() {
        return ente_inds;
    }

    public void setEnte_inds(String ente_inds) {
        this.ente_inds = ente_inds;
    }

    public WordlistEntity loadEnte_inds() {
        String propertyName = "ente_inds";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}