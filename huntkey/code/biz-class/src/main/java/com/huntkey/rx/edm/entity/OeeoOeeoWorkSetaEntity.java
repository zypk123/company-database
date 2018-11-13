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
public class OeeoOeeoWorkSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 441588378944296L;
    
    /**工作单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_work_comp", className="")
    private String oeeo_work_comp;
    /**工作开始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_work_beg", className="")
    private Date oeeo_work_beg;
    /**工作结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_work_end", className="")
    private Date oeeo_work_end;
    /**职位名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_work_post", className="")
    private String oeeo_work_post;
    /**咨询人姓名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_work_cons", className="")
    private String oeeo_work_cons;
    /**咨询人关系*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_cons_rela", className="")
    private String oeeo_cons_rela;
    /**咨询人职位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_cons_post", className="")
    private String oeeo_cons_post;
    /**咨询人电话*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_cons_tel", className="")
    private String oeeo_cons_tel;
    /**工作描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_work_desc", className="")
    private String oeeo_work_desc;

    public String getOeeo_work_comp() {
        return oeeo_work_comp;
    }

    public void setOeeo_work_comp(String oeeo_work_comp) {
        this.oeeo_work_comp = oeeo_work_comp;
    }

    public Date getOeeo_work_beg() {
        return oeeo_work_beg;
    }

    public void setOeeo_work_beg(Date oeeo_work_beg) {
        this.oeeo_work_beg = oeeo_work_beg;
    }

    public Date getOeeo_work_end() {
        return oeeo_work_end;
    }

    public void setOeeo_work_end(Date oeeo_work_end) {
        this.oeeo_work_end = oeeo_work_end;
    }

    public String getOeeo_work_post() {
        return oeeo_work_post;
    }

    public void setOeeo_work_post(String oeeo_work_post) {
        this.oeeo_work_post = oeeo_work_post;
    }

    public String getOeeo_work_cons() {
        return oeeo_work_cons;
    }

    public void setOeeo_work_cons(String oeeo_work_cons) {
        this.oeeo_work_cons = oeeo_work_cons;
    }

    public String getOeeo_cons_rela() {
        return oeeo_cons_rela;
    }

    public void setOeeo_cons_rela(String oeeo_cons_rela) {
        this.oeeo_cons_rela = oeeo_cons_rela;
    }

    public String getOeeo_cons_post() {
        return oeeo_cons_post;
    }

    public void setOeeo_cons_post(String oeeo_cons_post) {
        this.oeeo_cons_post = oeeo_cons_post;
    }

    public String getOeeo_cons_tel() {
        return oeeo_cons_tel;
    }

    public void setOeeo_cons_tel(String oeeo_cons_tel) {
        this.oeeo_cons_tel = oeeo_cons_tel;
    }

    public String getOeeo_work_desc() {
        return oeeo_work_desc;
    }

    public void setOeeo_work_desc(String oeeo_work_desc) {
        this.oeeo_work_desc = oeeo_work_desc;
    }



}