package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.BaseEntity;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 生态系统类实体
 * 
 */
public class EcosystemEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 9898153763316639L;
    
    /**对象编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ecos_code", className="")
    private String ecos_code;
    /**所属类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ecos_class_ecos", className="EcosystemEntity")
    private String ecos_class_ecos;
    /**父对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ecos_parent_ecos", className="EcosystemEntity")
    private String ecos_parent_ecos;
    /**逻辑删除标识*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ecos_logic_del", className="")
    private String ecos_logic_del;
    /**id解析服务器*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ecos_idres_server", className="")
    private String ecos_idres_server;

    public String getEcos_code() {
        return ecos_code;
    }

    public void setEcos_code(String ecos_code) {
        this.ecos_code = ecos_code;
    }

    public String getEcos_class_ecos() {
        return ecos_class_ecos;
    }

    public void setEcos_class_ecos(String ecos_class_ecos) {
        this.ecos_class_ecos = ecos_class_ecos;
    }

    public EcosystemEntity loadEcos_class_ecos() {
        String propertyName = "ecos_class_ecos";
        return (EcosystemEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEcos_parent_ecos() {
        return ecos_parent_ecos;
    }

    public void setEcos_parent_ecos(String ecos_parent_ecos) {
        this.ecos_parent_ecos = ecos_parent_ecos;
    }

    public EcosystemEntity loadEcos_parent_ecos() {
        String propertyName = "ecos_parent_ecos";
        return (EcosystemEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEcos_logic_del() {
        return ecos_logic_del;
    }

    public void setEcos_logic_del(String ecos_logic_del) {
        this.ecos_logic_del = ecos_logic_del;
    }

    public String getEcos_idres_server() {
        return ecos_idres_server;
    }

    public void setEcos_idres_server(String ecos_idres_server) {
        this.ecos_idres_server = ecos_idres_server;
    }



}