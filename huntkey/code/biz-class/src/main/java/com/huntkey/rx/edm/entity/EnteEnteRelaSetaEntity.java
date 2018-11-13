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
public class EnteEnteRelaSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 3183043891506198L;
    
    /**关系企业*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_ente_set", className="EnteEnteEnteSetbEntity")
    private List<EnteEnteEnteSetbEntity> ente_ente_set;
    /**关系人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_peop_set", className="EnteEntePeopSetbEntity")
    private List<EnteEntePeopSetbEntity> ente_peop_set;
    /**关联政府*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_gove_set", className="EnteEnteGoveSetbEntity")
    private List<EnteEnteGoveSetbEntity> ente_gove_set;

    public List<EnteEnteEnteSetbEntity> loadEnte_ente_set() {
        String propertyName = "ente_ente_set";
        String rootClass = "EnterpriseEntity";
        return (List<EnteEnteEnteSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEnte_ente_set(List<EnteEnteEnteSetbEntity> ente_ente_set) {
        this.ente_ente_set = ente_ente_set;
    }

    public List<EnteEnteEnteSetbEntity> getEnte_ente_set() {
        return ente_ente_set;
    }

    public List<EnteEntePeopSetbEntity> loadEnte_peop_set() {
        String propertyName = "ente_peop_set";
        String rootClass = "EnterpriseEntity";
        return (List<EnteEntePeopSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEnte_peop_set(List<EnteEntePeopSetbEntity> ente_peop_set) {
        this.ente_peop_set = ente_peop_set;
    }

    public List<EnteEntePeopSetbEntity> getEnte_peop_set() {
        return ente_peop_set;
    }

    public List<EnteEnteGoveSetbEntity> loadEnte_gove_set() {
        String propertyName = "ente_gove_set";
        String rootClass = "EnterpriseEntity";
        return (List<EnteEnteGoveSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEnte_gove_set(List<EnteEnteGoveSetbEntity> ente_gove_set) {
        this.ente_gove_set = ente_gove_set;
    }

    public List<EnteEnteGoveSetbEntity> getEnte_gove_set() {
        return ente_gove_set;
    }



}