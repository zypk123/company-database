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
public class RpifRpifCalSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 237394351460575L;
    
    /**对应资源属性集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_resources_props_set", className="RpifRpifResourcesPropsSetbEntity")
    private List<RpifRpifResourcesPropsSetbEntity> rpif_resources_props_set;
    /**变量名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_var_name", className="")
    private String rpif_var_name;
    /**统计适用值*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_val", className="")
    private String rpif_val;

    public List<RpifRpifResourcesPropsSetbEntity> loadRpif_resources_props_set() {
        String propertyName = "rpif_resources_props_set";
        String rootClass = "PpiformulaEntity";
        return (List<RpifRpifResourcesPropsSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRpif_resources_props_set(List<RpifRpifResourcesPropsSetbEntity> rpif_resources_props_set) {
        this.rpif_resources_props_set = rpif_resources_props_set;
    }

    public List<RpifRpifResourcesPropsSetbEntity> getRpif_resources_props_set() {
        return rpif_resources_props_set;
    }

    public String getRpif_var_name() {
        return rpif_var_name;
    }

    public void setRpif_var_name(String rpif_var_name) {
        this.rpif_var_name = rpif_var_name;
    }

    public String getRpif_val() {
        return rpif_val;
    }

    public void setRpif_val(String rpif_val) {
        this.rpif_val = rpif_val;
    }



}