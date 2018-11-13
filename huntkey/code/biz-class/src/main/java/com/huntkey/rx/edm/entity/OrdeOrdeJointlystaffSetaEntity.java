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
public class OrdeOrdeJointlystaffSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5384964785334486L;
    
    /**员工对象*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_jointly_staff", className="EmployeeEntity")
    private String orde_jointly_staff;

    public String getOrde_jointly_staff() {
        return orde_jointly_staff;
    }

    public void setOrde_jointly_staff(String orde_jointly_staff) {
        this.orde_jointly_staff = orde_jointly_staff;
    }

    public EmployeeEntity loadOrde_jointly_staff() {
        String propertyName = "orde_jointly_staff";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}