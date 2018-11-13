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
public class OrdeOrdeResSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 1050908795790679L;
    
    /**资源对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_robj", className="ResourceEntity")
    private String orde_robj;

    public String getOrde_robj() {
        return orde_robj;
    }

    public void setOrde_robj(String orde_robj) {
        this.orde_robj = orde_robj;
    }

    public ResourceEntity loadOrde_robj() {
        String propertyName = "orde_robj";
        return (ResourceEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}