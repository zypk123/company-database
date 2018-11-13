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
public class RempRempFamSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 1906259515964080L;
    
    /**姓名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_fam_name", className="")
    private String remp_fam_name;
    /**关系*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_fam_rela", className="")
    private String remp_fam_rela;
    /**工作单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_fam_com", className="")
    private String remp_fam_com;
    /**职位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_fam_post", className="")
    private String remp_fam_post;
    /**联系电话*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_fam_tel", className="")
    private String remp_fam_tel;
    /**家庭地址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_fam_addr", className="")
    private String remp_fam_addr;

    public String getRemp_fam_name() {
        return remp_fam_name;
    }

    public void setRemp_fam_name(String remp_fam_name) {
        this.remp_fam_name = remp_fam_name;
    }

    public String getRemp_fam_rela() {
        return remp_fam_rela;
    }

    public void setRemp_fam_rela(String remp_fam_rela) {
        this.remp_fam_rela = remp_fam_rela;
    }

    public String getRemp_fam_com() {
        return remp_fam_com;
    }

    public void setRemp_fam_com(String remp_fam_com) {
        this.remp_fam_com = remp_fam_com;
    }

    public String getRemp_fam_post() {
        return remp_fam_post;
    }

    public void setRemp_fam_post(String remp_fam_post) {
        this.remp_fam_post = remp_fam_post;
    }

    public String getRemp_fam_tel() {
        return remp_fam_tel;
    }

    public void setRemp_fam_tel(String remp_fam_tel) {
        this.remp_fam_tel = remp_fam_tel;
    }

    public String getRemp_fam_addr() {
        return remp_fam_addr;
    }

    public void setRemp_fam_addr(String remp_fam_addr) {
        this.remp_fam_addr = remp_fam_addr;
    }



}