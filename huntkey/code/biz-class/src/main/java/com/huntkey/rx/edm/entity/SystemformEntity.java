package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 系统表单类实体
 * 
 */
public class SystemformEntity extends ShowEntity implements Serializable {
    private static final long serialVersionUID = 7053674725621789L;
    
    /**页面编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="syfm_view_code", className="")
    private String syfm_view_code;
    /**页面名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="syfm_view_name", className="")
    private String syfm_view_name;
    /**系统表单分类*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="syfm_view_classify", className="WordlistEntity")
    private String syfm_view_classify;
    /**表单列表url*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="syfm_view_url", className="")
    private String syfm_view_url;
    /**表单描述*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="syfm_view_desc", className="")
    private String syfm_view_desc;
    /**表单审核url*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="syfm_audit_url", className="")
    private String syfm_audit_url;
    /**表单编辑url*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="syfm_edit_url", className="")
    private String syfm_edit_url;

    public String getSyfm_view_code() {
        return syfm_view_code;
    }

    public void setSyfm_view_code(String syfm_view_code) {
        this.syfm_view_code = syfm_view_code;
    }

    public String getSyfm_view_name() {
        return syfm_view_name;
    }

    public void setSyfm_view_name(String syfm_view_name) {
        this.syfm_view_name = syfm_view_name;
    }

    public String getSyfm_view_classify() {
        return syfm_view_classify;
    }

    public void setSyfm_view_classify(String syfm_view_classify) {
        this.syfm_view_classify = syfm_view_classify;
    }

    public WordlistEntity loadSyfm_view_classify() {
        String propertyName = "syfm_view_classify";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getSyfm_view_url() {
        return syfm_view_url;
    }

    public void setSyfm_view_url(String syfm_view_url) {
        this.syfm_view_url = syfm_view_url;
    }

    public String getSyfm_view_desc() {
        return syfm_view_desc;
    }

    public void setSyfm_view_desc(String syfm_view_desc) {
        this.syfm_view_desc = syfm_view_desc;
    }

    public String getSyfm_audit_url() {
        return syfm_audit_url;
    }

    public void setSyfm_audit_url(String syfm_audit_url) {
        this.syfm_audit_url = syfm_audit_url;
    }

    public String getSyfm_edit_url() {
        return syfm_edit_url;
    }

    public void setSyfm_edit_url(String syfm_edit_url) {
        this.syfm_edit_url = syfm_edit_url;
    }



}