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
public class GoveGoveEnteSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8132853062236098L;
    
    /**企业*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="gove_ente", className="EnterpriseEntity")
    private String gove_ente;

    public String getGove_ente() {
        return gove_ente;
    }

    public void setGove_ente(String gove_ente) {
        this.gove_ente = gove_ente;
    }

    public EnterpriseEntity loadGove_ente() {
        String propertyName = "gove_ente";
        return (EnterpriseEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}