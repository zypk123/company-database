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
public class MoniMoniResSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5904327129021631L;
    
    /**资源对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_res_id", className="ResourceEntity")
    private String moni_res_id;

    public String getMoni_res_id() {
        return moni_res_id;
    }

    public void setMoni_res_id(String moni_res_id) {
        this.moni_res_id = moni_res_id;
    }

    public ResourceEntity loadMoni_res_id() {
        String propertyName = "moni_res_id";
        return (ResourceEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}