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
public class PpixIpinRangSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 7092646933310912L;
    
    /**统计监管对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ipin_rang_id", className="MonitorEntity")
    private String ipin_rang_id;
    /**变量名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ipin_var_name", className="")
    private String ipin_var_name;
    /**监管对象集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ipin_val_set", className="PpixIpinValSetbEntity")
    private List<PpixIpinValSetbEntity> ipin_val_set;
    /**所属监管类*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="ipin_rang_clss", className="MonitorEntity")
    private String ipin_rang_clss;

    public String getIpin_rang_id() {
        return ipin_rang_id;
    }

    public void setIpin_rang_id(String ipin_rang_id) {
        this.ipin_rang_id = ipin_rang_id;
    }

    public MonitorEntity loadIpin_rang_id() {
        String propertyName = "ipin_rang_id";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getIpin_var_name() {
        return ipin_var_name;
    }

    public void setIpin_var_name(String ipin_var_name) {
        this.ipin_var_name = ipin_var_name;
    }

    public List<PpixIpinValSetbEntity> loadIpin_val_set() {
        String propertyName = "ipin_val_set";
        String rootClass = "ProcessperformanceindexEntity";
        return (List<PpixIpinValSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setIpin_val_set(List<PpixIpinValSetbEntity> ipin_val_set) {
        this.ipin_val_set = ipin_val_set;
    }

    public List<PpixIpinValSetbEntity> getIpin_val_set() {
        return ipin_val_set;
    }

    public String getIpin_rang_clss() {
        return ipin_rang_clss;
    }

    public void setIpin_rang_clss(String ipin_rang_clss) {
        this.ipin_rang_clss = ipin_rang_clss;
    }

    public MonitorEntity loadIpin_rang_clss() {
        String propertyName = "ipin_rang_clss";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}