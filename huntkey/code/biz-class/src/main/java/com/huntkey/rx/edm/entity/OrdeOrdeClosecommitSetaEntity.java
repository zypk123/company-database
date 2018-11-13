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
public class OrdeOrdeClosecommitSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5088029772890246L;
    
    /**表单对象*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_cobj", className="OrderEntity")
    private String orde_cobj;

    public String getOrde_cobj() {
        return orde_cobj;
    }

    public void setOrde_cobj(String orde_cobj) {
        this.orde_cobj = orde_cobj;
    }

    public OrderEntity loadOrde_cobj() {
        String propertyName = "orde_cobj";
        return (OrderEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}