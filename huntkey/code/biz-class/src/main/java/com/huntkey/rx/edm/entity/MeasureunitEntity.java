package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 计量单位类实体
 * 
 */
public class MeasureunitEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 5537357095037739L;
    
    /**状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_enable", className="")
    private Integer meas_enable;
    /**计量对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_obj", className="")
    private String meas_obj;
    /**基准计量名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_name", className="")
    private String meas_name;
    /**计量单位定义列表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_define_set", className="MeasMeasDefineSetaEntity")
    private List<MeasMeasDefineSetaEntity> meas_define_set;
    /**是否标准计量*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_isstand", className="")
    private Integer meas_isstand;

    public Integer getMeas_enable() {
        return meas_enable;
    }

    public void setMeas_enable(Integer meas_enable) {
        this.meas_enable = meas_enable;
    }

    public String getMeas_obj() {
        return meas_obj;
    }

    public void setMeas_obj(String meas_obj) {
        this.meas_obj = meas_obj;
    }

    public String getMeas_name() {
        return meas_name;
    }

    public void setMeas_name(String meas_name) {
        this.meas_name = meas_name;
    }

    public List<MeasMeasDefineSetaEntity> loadMeas_define_set() {
        String propertyName = "meas_define_set";
        String rootClass = "";
        return (List<MeasMeasDefineSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setMeas_define_set(List<MeasMeasDefineSetaEntity> meas_define_set) {
        this.meas_define_set = meas_define_set;
    }

    public List<MeasMeasDefineSetaEntity> getMeas_define_set() {
        return meas_define_set;
    }

    public Integer getMeas_isstand() {
        return meas_isstand;
    }

    public void setMeas_isstand(Integer meas_isstand) {
        this.meas_isstand = meas_isstand;
    }



}