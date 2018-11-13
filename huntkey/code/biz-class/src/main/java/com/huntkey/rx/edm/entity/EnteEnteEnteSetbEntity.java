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
public class EnteEnteEnteSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9466916349983095L;
    
    /**企业*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_ente", className="EnterpriseEntity")
    private String ente_ente;

    public String getEnte_ente() {
        return ente_ente;
    }

    public void setEnte_ente(String ente_ente) {
        this.ente_ente = ente_ente;
    }

    public EnterpriseEntity loadEnte_ente() {
        String propertyName = "ente_ente";
        return (EnterpriseEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}