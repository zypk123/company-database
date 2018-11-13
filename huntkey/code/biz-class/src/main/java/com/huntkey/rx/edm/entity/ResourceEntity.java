package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 资源类实体
 * 
 */
public class ResourceEntity extends EdmEntity implements Serializable {
    private static final long serialVersionUID = 8582510193516127L;
    
    /**维护单集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="reso_order_set", className="ResoResoOrderSetaEntity")
    private List<ResoResoOrderSetaEntity> reso_order_set;

    public List<ResoResoOrderSetaEntity> loadReso_order_set() {
        String propertyName = "reso_order_set";
        String rootClass = "";
        return (List<ResoResoOrderSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setReso_order_set(List<ResoResoOrderSetaEntity> reso_order_set) {
        this.reso_order_set = reso_order_set;
    }

    public List<ResoResoOrderSetaEntity> getReso_order_set() {
        return reso_order_set;
    }



}