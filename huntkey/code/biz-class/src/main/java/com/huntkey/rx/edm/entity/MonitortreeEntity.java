package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 监管树版本类实体
 * 
 */
public class MonitortreeEntity extends KnowledgeEntity implements Serializable {
    private static final long serialVersionUID = 6553499325666525L;
    
    /**监管树版本号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="motr_ver_code", className="")
    private String motr_ver_code;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="motr_beg", className="")
    private Date motr_beg;
    /**失效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="motr_end", className="")
    private Date motr_end;
    /**监管对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="motr_root_id", className="")
    private String motr_root_id;
    /**所属监管类id*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="motr_edm_id", className="")
    private String motr_edm_id;

    public String getMotr_ver_code() {
        return motr_ver_code;
    }

    public void setMotr_ver_code(String motr_ver_code) {
        this.motr_ver_code = motr_ver_code;
    }

    public Date getMotr_beg() {
        return motr_beg;
    }

    public void setMotr_beg(Date motr_beg) {
        this.motr_beg = motr_beg;
    }

    public Date getMotr_end() {
        return motr_end;
    }

    public void setMotr_end(Date motr_end) {
        this.motr_end = motr_end;
    }

    public String getMotr_root_id() {
        return motr_root_id;
    }

    public void setMotr_root_id(String motr_root_id) {
        this.motr_root_id = motr_root_id;
    }

    public String getMotr_edm_id() {
        return motr_edm_id;
    }

    public void setMotr_edm_id(String motr_edm_id) {
        this.motr_edm_id = motr_edm_id;
    }



}