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
public class OeeoOeeoFamSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 7305026449911414L;
    
    /**姓名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_fam_name", className="")
    private String oeeo_fam_name;
    /**关系*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_fam_rela", className="")
    private String oeeo_fam_rela;
    /**工作单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_fam_comp", className="")
    private String oeeo_fam_comp;
    /**职位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_fam_post", className="")
    private String oeeo_fam_post;
    /**联系电话*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_fam_tel", className="")
    private String oeeo_fam_tel;
    /**家庭地址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_fam_addr", className="")
    private String oeeo_fam_addr;

    public String getOeeo_fam_name() {
        return oeeo_fam_name;
    }

    public void setOeeo_fam_name(String oeeo_fam_name) {
        this.oeeo_fam_name = oeeo_fam_name;
    }

    public String getOeeo_fam_rela() {
        return oeeo_fam_rela;
    }

    public void setOeeo_fam_rela(String oeeo_fam_rela) {
        this.oeeo_fam_rela = oeeo_fam_rela;
    }

    public String getOeeo_fam_comp() {
        return oeeo_fam_comp;
    }

    public void setOeeo_fam_comp(String oeeo_fam_comp) {
        this.oeeo_fam_comp = oeeo_fam_comp;
    }

    public String getOeeo_fam_post() {
        return oeeo_fam_post;
    }

    public void setOeeo_fam_post(String oeeo_fam_post) {
        this.oeeo_fam_post = oeeo_fam_post;
    }

    public String getOeeo_fam_tel() {
        return oeeo_fam_tel;
    }

    public void setOeeo_fam_tel(String oeeo_fam_tel) {
        this.oeeo_fam_tel = oeeo_fam_tel;
    }

    public String getOeeo_fam_addr() {
        return oeeo_fam_addr;
    }

    public void setOeeo_fam_addr(String oeeo_fam_addr) {
        this.oeeo_fam_addr = oeeo_fam_addr;
    }



}