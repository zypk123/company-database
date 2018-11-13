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
public class GoveGoveRelaSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5778203140652520L;
    
    /**关系企业集合*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="gove_ente_set", className="GoveGoveEnteSetbEntity")
    private List<GoveGoveEnteSetbEntity> gove_ente_set;
    /**关系人集合*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="gove_peop_set", className="GoveGovePeopSetbEntity")
    private List<GoveGovePeopSetbEntity> gove_peop_set;
    /**关联政府集合*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="gove_gove_set", className="GoveGoveGoveSetbEntity")
    private List<GoveGoveGoveSetbEntity> gove_gove_set;

    public List<GoveGoveEnteSetbEntity> loadGove_ente_set() {
        String propertyName = "gove_ente_set";
        String rootClass = "GovernmentEntity";
        return (List<GoveGoveEnteSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setGove_ente_set(List<GoveGoveEnteSetbEntity> gove_ente_set) {
        this.gove_ente_set = gove_ente_set;
    }

    public List<GoveGoveEnteSetbEntity> getGove_ente_set() {
        return gove_ente_set;
    }

    public List<GoveGovePeopSetbEntity> loadGove_peop_set() {
        String propertyName = "gove_peop_set";
        String rootClass = "GovernmentEntity";
        return (List<GoveGovePeopSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setGove_peop_set(List<GoveGovePeopSetbEntity> gove_peop_set) {
        this.gove_peop_set = gove_peop_set;
    }

    public List<GoveGovePeopSetbEntity> getGove_peop_set() {
        return gove_peop_set;
    }

    public List<GoveGoveGoveSetbEntity> loadGove_gove_set() {
        String propertyName = "gove_gove_set";
        String rootClass = "GovernmentEntity";
        return (List<GoveGoveGoveSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setGove_gove_set(List<GoveGoveGoveSetbEntity> gove_gove_set) {
        this.gove_gove_set = gove_gove_set;
    }

    public List<GoveGoveGoveSetbEntity> getGove_gove_set() {
        return gove_gove_set;
    }



}