package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 政府类实体
 * 
 */
public class GovernmentEntity extends EcosystemEntity implements Serializable {
    private static final long serialVersionUID = 9756042826144445L;
    
    /**政府识别码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="gove_code", className="")
    private String gove_code;
    /**机构名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="gove_name", className="")
    private String gove_name;
    /**网址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="gove_url", className="")
    private String gove_url;
    /**通讯地址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="gove_addr", className="")
    private String gove_addr;
    /**第一负责人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="gove_principal", className="")
    private String gove_principal;
    /**政府关系圈*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="gove_rela_set", className="GoveGoveRelaSetaEntity")
    private List<GoveGoveRelaSetaEntity> gove_rela_set;

    public String getGove_code() {
        return gove_code;
    }

    public void setGove_code(String gove_code) {
        this.gove_code = gove_code;
    }

    public String getGove_name() {
        return gove_name;
    }

    public void setGove_name(String gove_name) {
        this.gove_name = gove_name;
    }

    public String getGove_url() {
        return gove_url;
    }

    public void setGove_url(String gove_url) {
        this.gove_url = gove_url;
    }

    public String getGove_addr() {
        return gove_addr;
    }

    public void setGove_addr(String gove_addr) {
        this.gove_addr = gove_addr;
    }

    public String getGove_principal() {
        return gove_principal;
    }

    public void setGove_principal(String gove_principal) {
        this.gove_principal = gove_principal;
    }

    public List<GoveGoveRelaSetaEntity> loadGove_rela_set() {
        String propertyName = "gove_rela_set";
        String rootClass = "";
        return (List<GoveGoveRelaSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setGove_rela_set(List<GoveGoveRelaSetaEntity> gove_rela_set) {
        this.gove_rela_set = gove_rela_set;
    }

    public List<GoveGoveRelaSetaEntity> getGove_rela_set() {
        return gove_rela_set;
    }



}