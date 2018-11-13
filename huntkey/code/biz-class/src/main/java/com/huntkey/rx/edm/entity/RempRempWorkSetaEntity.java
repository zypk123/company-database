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
public class RempRempWorkSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9718979047675851L;
    
    /**公司名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_work_comp", className="")
    private String remp_work_comp;
    /**职位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_work_post", className="")
    private String remp_work_post;
    /**起始年月*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_work_beg", className="")
    private Date remp_work_beg;
    /**结束年月*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_work_end", className="")
    private Date remp_work_end;
    /**工作描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_work_desc", className="")
    private String remp_work_desc;
    /**咨询人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_cons", className="")
    private String remp_cons;
    /**咨询人关系*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_cons_rela", className="")
    private String remp_cons_rela;
    /**咨询人职位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_cons_post", className="")
    private String remp_cons_post;
    /**咨询人电话*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_cons_tel", className="")
    private String remp_cons_tel;

    public String getRemp_work_comp() {
        return remp_work_comp;
    }

    public void setRemp_work_comp(String remp_work_comp) {
        this.remp_work_comp = remp_work_comp;
    }

    public String getRemp_work_post() {
        return remp_work_post;
    }

    public void setRemp_work_post(String remp_work_post) {
        this.remp_work_post = remp_work_post;
    }

    public Date getRemp_work_beg() {
        return remp_work_beg;
    }

    public void setRemp_work_beg(Date remp_work_beg) {
        this.remp_work_beg = remp_work_beg;
    }

    public Date getRemp_work_end() {
        return remp_work_end;
    }

    public void setRemp_work_end(Date remp_work_end) {
        this.remp_work_end = remp_work_end;
    }

    public String getRemp_work_desc() {
        return remp_work_desc;
    }

    public void setRemp_work_desc(String remp_work_desc) {
        this.remp_work_desc = remp_work_desc;
    }

    public String getRemp_cons() {
        return remp_cons;
    }

    public void setRemp_cons(String remp_cons) {
        this.remp_cons = remp_cons;
    }

    public String getRemp_cons_rela() {
        return remp_cons_rela;
    }

    public void setRemp_cons_rela(String remp_cons_rela) {
        this.remp_cons_rela = remp_cons_rela;
    }

    public String getRemp_cons_post() {
        return remp_cons_post;
    }

    public void setRemp_cons_post(String remp_cons_post) {
        this.remp_cons_post = remp_cons_post;
    }

    public String getRemp_cons_tel() {
        return remp_cons_tel;
    }

    public void setRemp_cons_tel(String remp_cons_tel) {
        this.remp_cons_tel = remp_cons_tel;
    }



}