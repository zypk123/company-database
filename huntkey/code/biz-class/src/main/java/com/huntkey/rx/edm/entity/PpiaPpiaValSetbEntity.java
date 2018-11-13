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
public class PpiaPpiaValSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 349723007285671L;
    
    /**被统计监管类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ppia_val_class", className="")
    private String ppia_val_class;
    /**监管对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ppia_range_val", className="MonitorEntity")
    private String ppia_range_val;

    public String getPpia_val_class() {
        return ppia_val_class;
    }

    public void setPpia_val_class(String ppia_val_class) {
        this.ppia_val_class = ppia_val_class;
    }

    public String getPpia_range_val() {
        return ppia_range_val;
    }

    public void setPpia_range_val(String ppia_range_val) {
        this.ppia_range_val = ppia_range_val;
    }

    public MonitorEntity loadPpia_range_val() {
        String propertyName = "ppia_range_val";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}