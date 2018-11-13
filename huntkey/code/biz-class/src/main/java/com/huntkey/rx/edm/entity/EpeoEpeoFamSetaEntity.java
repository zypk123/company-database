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
public class EpeoEpeoFamSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 393444852673189L;
    
    /**姓名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_fam_name", className="")
    private String epeo_fam_name;
    /**关系*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_fam_rela", className="")
    private String epeo_fam_rela;
    /**工作单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_fam_comp", className="")
    private String epeo_fam_comp;
    /**职位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_fam_post", className="")
    private String epeo_fam_post;
    /**联系电话*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_fam_tel", className="")
    private String epeo_fam_tel;
    /**家庭地址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_fam_addr", className="")
    private String epeo_fam_addr;

    public String getEpeo_fam_name() {
        return epeo_fam_name;
    }

    public void setEpeo_fam_name(String epeo_fam_name) {
        this.epeo_fam_name = epeo_fam_name;
    }

    public String getEpeo_fam_rela() {
        return epeo_fam_rela;
    }

    public void setEpeo_fam_rela(String epeo_fam_rela) {
        this.epeo_fam_rela = epeo_fam_rela;
    }

    public String getEpeo_fam_comp() {
        return epeo_fam_comp;
    }

    public void setEpeo_fam_comp(String epeo_fam_comp) {
        this.epeo_fam_comp = epeo_fam_comp;
    }

    public String getEpeo_fam_post() {
        return epeo_fam_post;
    }

    public void setEpeo_fam_post(String epeo_fam_post) {
        this.epeo_fam_post = epeo_fam_post;
    }

    public String getEpeo_fam_tel() {
        return epeo_fam_tel;
    }

    public void setEpeo_fam_tel(String epeo_fam_tel) {
        this.epeo_fam_tel = epeo_fam_tel;
    }

    public String getEpeo_fam_addr() {
        return epeo_fam_addr;
    }

    public void setEpeo_fam_addr(String epeo_fam_addr) {
        this.epeo_fam_addr = epeo_fam_addr;
    }



}