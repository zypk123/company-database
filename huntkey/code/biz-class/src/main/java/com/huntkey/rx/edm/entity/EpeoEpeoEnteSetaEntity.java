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
public class EpeoEpeoEnteSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5927405234800959L;
    
    /**企业对象*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="epeo_ente_obj", className="EnterpriseEntity")
    private String epeo_ente_obj;

    public String getEpeo_ente_obj() {
        return epeo_ente_obj;
    }

    public void setEpeo_ente_obj(String epeo_ente_obj) {
        this.epeo_ente_obj = epeo_ente_obj;
    }

    public EnterpriseEntity loadEpeo_ente_obj() {
        String propertyName = "epeo_ente_obj";
        return (EnterpriseEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}