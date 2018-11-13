package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 资源配置类实体
 * 
 */
public class OwnerofresourceEntity extends EmpowerEntity implements Serializable {
    private static final long serialVersionUID = 4726944505489817L;
    
    /**配置资源集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="owre_resource_set", className="OwreOwreResourceSetaEntity")
    private List<OwreOwreResourceSetaEntity> owre_resource_set;

    public List<OwreOwreResourceSetaEntity> loadOwre_resource_set() {
        String propertyName = "owre_resource_set";
        String rootClass = "";
        return (List<OwreOwreResourceSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOwre_resource_set(List<OwreOwreResourceSetaEntity> owre_resource_set) {
        this.owre_resource_set = owre_resource_set;
    }

    public List<OwreOwreResourceSetaEntity> getOwre_resource_set() {
        return owre_resource_set;
    }



}