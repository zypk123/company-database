package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.BaseEntity;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 企业模型类实体
 * 
 */
public class EdmEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 975100938569435L;
    
    /**来源类*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="edmd_src_class", className="")
    private String edmd_src_class;
    /**对象编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="edmd_code", className="")
    private String edmd_code;
    /**企业对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="edmd_ente", className="EnterpriseEntity")
    private String edmd_ente;
    /**所属类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="edmd_class", className="")
    private String edmd_class;
    /**来源对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="edmd_srcobj", className="")
    private String edmd_srcobj;

    public String getEdmd_src_class() {
        return edmd_src_class;
    }

    public void setEdmd_src_class(String edmd_src_class) {
        this.edmd_src_class = edmd_src_class;
    }

    public String getEdmd_code() {
        return edmd_code;
    }

    public void setEdmd_code(String edmd_code) {
        this.edmd_code = edmd_code;
    }

    public String getEdmd_ente() {
        return edmd_ente;
    }

    public void setEdmd_ente(String edmd_ente) {
        this.edmd_ente = edmd_ente;
    }

    public EnterpriseEntity loadEdmd_ente() {
        String propertyName = "edmd_ente";
        return (EnterpriseEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEdmd_class() {
        return edmd_class;
    }

    public void setEdmd_class(String edmd_class) {
        this.edmd_class = edmd_class;
    }

    public String getEdmd_srcobj() {
        return edmd_srcobj;
    }

    public void setEdmd_srcobj(String edmd_srcobj) {
        this.edmd_srcobj = edmd_srcobj;
    }



}