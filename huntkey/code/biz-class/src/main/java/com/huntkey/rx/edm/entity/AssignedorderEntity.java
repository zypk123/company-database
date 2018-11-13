package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 可用表单类实体
 * 
 */
public class AssignedorderEntity extends EmpowerEntity implements Serializable {
    private static final long serialVersionUID = 9251219505046271L;
    
    /**表单集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="asor_orde_set", className="AsorAsorOrdeSetaEntity")
    private List<AsorAsorOrdeSetaEntity> asor_orde_set;

    public List<AsorAsorOrdeSetaEntity> loadAsor_orde_set() {
        String propertyName = "asor_orde_set";
        String rootClass = "";
        return (List<AsorAsorOrdeSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setAsor_orde_set(List<AsorAsorOrdeSetaEntity> asor_orde_set) {
        this.asor_orde_set = asor_orde_set;
    }

    public List<AsorAsorOrdeSetaEntity> getAsor_orde_set() {
        return asor_orde_set;
    }



}