package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 授权类实体
 * 
 */
public class EmpowerEntity extends EdmEntity implements Serializable {
    private static final long serialVersionUID = 5315033488573649L;
    
    /**授权对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="empo_obj", className="MonitorEntity")
    private String empo_obj;

    public String getEmpo_obj() {
        return empo_obj;
    }

    public void setEmpo_obj(String empo_obj) {
        this.empo_obj = empo_obj;
    }

    public MonitorEntity loadEmpo_obj() {
        String propertyName = "empo_obj";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}