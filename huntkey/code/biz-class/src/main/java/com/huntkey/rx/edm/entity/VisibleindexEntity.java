package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 指标可见类实体
 * 
 */
public class VisibleindexEntity extends EmpowerEntity implements Serializable {
    private static final long serialVersionUID = 6016512968907719L;
    
    /**指标可见集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_visible_set", className="ViinViinVisibleSetaEntity")
    private List<ViinViinVisibleSetaEntity> viin_visible_set;

    public List<ViinViinVisibleSetaEntity> loadViin_visible_set() {
        String propertyName = "viin_visible_set";
        String rootClass = "";
        return (List<ViinViinVisibleSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setViin_visible_set(List<ViinViinVisibleSetaEntity> viin_visible_set) {
        this.viin_visible_set = viin_visible_set;
    }

    public List<ViinViinVisibleSetaEntity> getViin_visible_set() {
        return viin_visible_set;
    }



}