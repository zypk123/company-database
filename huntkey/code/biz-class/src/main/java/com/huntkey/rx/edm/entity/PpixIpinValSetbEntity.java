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
public class PpixIpinValSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9760488710472267L;
    
    /**监管对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ipin_range_val", className="MonitorEntity")
    private String ipin_range_val;
    /**被统计监管类*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="ipin_val_class", className="MonitorEntity")
    private String ipin_val_class;

    public String getIpin_range_val() {
        return ipin_range_val;
    }

    public void setIpin_range_val(String ipin_range_val) {
        this.ipin_range_val = ipin_range_val;
    }

    public MonitorEntity loadIpin_range_val() {
        String propertyName = "ipin_range_val";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getIpin_val_class() {
        return ipin_val_class;
    }

    public void setIpin_val_class(String ipin_val_class) {
        this.ipin_val_class = ipin_val_class;
    }

    public MonitorEntity loadIpin_val_class() {
        String propertyName = "ipin_val_class";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}