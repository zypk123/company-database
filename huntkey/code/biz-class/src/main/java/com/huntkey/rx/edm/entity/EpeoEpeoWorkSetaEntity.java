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
public class EpeoEpeoWorkSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 7189578385611559L;
    
    /**公司名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_work_comp", className="")
    private String epeo_work_comp;
    /**职位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_work_post", className="")
    private String epeo_work_post;
    /**起始年月*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_work_beg", className="")
    private Date epeo_work_beg;
    /**结束年月*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_work_end", className="")
    private Date epeo_work_end;
    /**工作描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_work_desc", className="")
    private String epeo_work_desc;
    /**咨询人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_attn_set", className="")
    private String epeo_attn_set;
    /**咨询人关系*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_attn_rela", className="")
    private String epeo_attn_rela;
    /**咨询人职位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_attn_post", className="")
    private String epeo_attn_post;
    /**咨询人电话*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_attn_tel", className="")
    private String epeo_attn_tel;

    public String getEpeo_work_comp() {
        return epeo_work_comp;
    }

    public void setEpeo_work_comp(String epeo_work_comp) {
        this.epeo_work_comp = epeo_work_comp;
    }

    public String getEpeo_work_post() {
        return epeo_work_post;
    }

    public void setEpeo_work_post(String epeo_work_post) {
        this.epeo_work_post = epeo_work_post;
    }

    public Date getEpeo_work_beg() {
        return epeo_work_beg;
    }

    public void setEpeo_work_beg(Date epeo_work_beg) {
        this.epeo_work_beg = epeo_work_beg;
    }

    public Date getEpeo_work_end() {
        return epeo_work_end;
    }

    public void setEpeo_work_end(Date epeo_work_end) {
        this.epeo_work_end = epeo_work_end;
    }

    public String getEpeo_work_desc() {
        return epeo_work_desc;
    }

    public void setEpeo_work_desc(String epeo_work_desc) {
        this.epeo_work_desc = epeo_work_desc;
    }

    public String getEpeo_attn_set() {
        return epeo_attn_set;
    }

    public void setEpeo_attn_set(String epeo_attn_set) {
        this.epeo_attn_set = epeo_attn_set;
    }

    public String getEpeo_attn_rela() {
        return epeo_attn_rela;
    }

    public void setEpeo_attn_rela(String epeo_attn_rela) {
        this.epeo_attn_rela = epeo_attn_rela;
    }

    public String getEpeo_attn_post() {
        return epeo_attn_post;
    }

    public void setEpeo_attn_post(String epeo_attn_post) {
        this.epeo_attn_post = epeo_attn_post;
    }

    public String getEpeo_attn_tel() {
        return epeo_attn_tel;
    }

    public void setEpeo_attn_tel(String epeo_attn_tel) {
        this.epeo_attn_tel = epeo_attn_tel;
    }



}