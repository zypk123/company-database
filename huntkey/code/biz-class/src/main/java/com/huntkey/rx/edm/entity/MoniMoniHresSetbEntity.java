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
public class MoniMoniHresSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8429766175658166L;
    
    /**资源对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hres_id", className="ResourceEntity")
    private String moni_hres_id;

    public String getMoni_hres_id() {
        return moni_hres_id;
    }

    public void setMoni_hres_id(String moni_hres_id) {
        this.moni_hres_id = moni_hres_id;
    }

    public ResourceEntity loadMoni_hres_id() {
        String propertyName = "moni_hres_id";
        return (ResourceEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}