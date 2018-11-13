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
public class PrptPrptProcSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 1180226725560109L;
    
    /**模板对象*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_resource_id", className="ActivitytemplateEntity")
    private String prpt_resource_id;

    public String getPrpt_resource_id() {
        return prpt_resource_id;
    }

    public void setPrpt_resource_id(String prpt_resource_id) {
        this.prpt_resource_id = prpt_resource_id;
    }

    public ActivitytemplateEntity loadPrpt_resource_id() {
        String propertyName = "prpt_resource_id";
        return (ActivitytemplateEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}