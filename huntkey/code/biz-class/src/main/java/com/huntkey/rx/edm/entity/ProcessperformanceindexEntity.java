package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 绩效指标类实体
 * 
 */
public class ProcessperformanceindexEntity extends IndexEntity implements Serializable {
    private static final long serialVersionUID = 7130130151265406L;
    
    /**指标定义对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ipin_rpif", className="PpiformulaEntity")
    private String ipin_rpif;
    /**适用周期集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ipin_time_set", className="PpixIpinTimeSetaEntity")
    private List<PpixIpinTimeSetaEntity> ipin_time_set;
    /**统计范围集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ipin_rang_set", className="PpixIpinRangSetaEntity")
    private List<PpixIpinRangSetaEntity> ipin_rang_set;

    public String getIpin_rpif() {
        return ipin_rpif;
    }

    public void setIpin_rpif(String ipin_rpif) {
        this.ipin_rpif = ipin_rpif;
    }

    public PpiformulaEntity loadIpin_rpif() {
        String propertyName = "ipin_rpif";
        return (PpiformulaEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<PpixIpinTimeSetaEntity> loadIpin_time_set() {
        String propertyName = "ipin_time_set";
        String rootClass = "";
        return (List<PpixIpinTimeSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setIpin_time_set(List<PpixIpinTimeSetaEntity> ipin_time_set) {
        this.ipin_time_set = ipin_time_set;
    }

    public List<PpixIpinTimeSetaEntity> getIpin_time_set() {
        return ipin_time_set;
    }

    public List<PpixIpinRangSetaEntity> loadIpin_rang_set() {
        String propertyName = "ipin_rang_set";
        String rootClass = "";
        return (List<PpixIpinRangSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setIpin_rang_set(List<PpixIpinRangSetaEntity> ipin_rang_set) {
        this.ipin_rang_set = ipin_rang_set;
    }

    public List<PpixIpinRangSetaEntity> getIpin_rang_set() {
        return ipin_rang_set;
    }



}