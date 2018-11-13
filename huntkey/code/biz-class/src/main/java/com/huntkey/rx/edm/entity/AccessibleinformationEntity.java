package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 数据可见类实体
 * 
 */
public class AccessibleinformationEntity extends EmpowerEntity implements Serializable {
    private static final long serialVersionUID = 3831816727008400L;
    
    /**可见资源集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="acin_resource_set", className="AcinAcinResourceSetaEntity")
    private List<AcinAcinResourceSetaEntity> acin_resource_set;

    public List<AcinAcinResourceSetaEntity> loadAcin_resource_set() {
        String propertyName = "acin_resource_set";
        String rootClass = "";
        return (List<AcinAcinResourceSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setAcin_resource_set(List<AcinAcinResourceSetaEntity> acin_resource_set) {
        this.acin_resource_set = acin_resource_set;
    }

    public List<AcinAcinResourceSetaEntity> getAcin_resource_set() {
        return acin_resource_set;
    }



}