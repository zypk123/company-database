package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class EnteEnteFinaSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9491361177732322L;
    
    /**企业*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_fina_ente", className="EnterpriseEntity")
    private String ente_fina_ente;
    /**股份比例*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_fina_rate", className="")
    private BigDecimal ente_fina_rate;
    /**授权代表列表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_auth_set", className="EnteEnteAuthSetbEntity")
    private List<EnteEnteAuthSetbEntity> ente_auth_set;

    public String getEnte_fina_ente() {
        return ente_fina_ente;
    }

    public void setEnte_fina_ente(String ente_fina_ente) {
        this.ente_fina_ente = ente_fina_ente;
    }

    public EnterpriseEntity loadEnte_fina_ente() {
        String propertyName = "ente_fina_ente";
        return (EnterpriseEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public BigDecimal getEnte_fina_rate() {
        return ente_fina_rate;
    }

    public void setEnte_fina_rate(BigDecimal ente_fina_rate) {
        this.ente_fina_rate = ente_fina_rate;
    }

    public List<EnteEnteAuthSetbEntity> loadEnte_auth_set() {
        String propertyName = "ente_auth_set";
        String rootClass = "EnterpriseEntity";
        return (List<EnteEnteAuthSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEnte_auth_set(List<EnteEnteAuthSetbEntity> ente_auth_set) {
        this.ente_auth_set = ente_auth_set;
    }

    public List<EnteEnteAuthSetbEntity> getEnte_auth_set() {
        return ente_auth_set;
    }



}