package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class GoimGoimHistSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8662846700805585L;
    
    /**修订自然人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_edit_peop", className="")
    private String goim_edit_peop;
    /**修订企业*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_edit_ente", className="EnterpriseEntity")
    private String goim_edit_ente;
    /**修订时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_edit_time", className="")
    private Date goim_edit_time;
    /**事务特性修订表单*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_edit_ecos", className="EcosystemEntity")
    private String goim_edit_ecos;

    public String getGoim_edit_peop() {
        return goim_edit_peop;
    }

    public void setGoim_edit_peop(String goim_edit_peop) {
        this.goim_edit_peop = goim_edit_peop;
    }

    public String getGoim_edit_ente() {
        return goim_edit_ente;
    }

    public void setGoim_edit_ente(String goim_edit_ente) {
        this.goim_edit_ente = goim_edit_ente;
    }

    public EnterpriseEntity loadGoim_edit_ente() {
        String propertyName = "goim_edit_ente";
        return (EnterpriseEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getGoim_edit_time() {
        return goim_edit_time;
    }

    public void setGoim_edit_time(Date goim_edit_time) {
        this.goim_edit_time = goim_edit_time;
    }

    public String getGoim_edit_ecos() {
        return goim_edit_ecos;
    }

    public void setGoim_edit_ecos(String goim_edit_ecos) {
        this.goim_edit_ecos = goim_edit_ecos;
    }

    public EcosystemEntity loadGoim_edit_ecos() {
        String propertyName = "goim_edit_ecos";
        return (EcosystemEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}