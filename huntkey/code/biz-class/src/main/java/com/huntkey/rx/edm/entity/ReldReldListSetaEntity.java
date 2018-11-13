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
public class ReldReldListSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 1269082654334046L;
    
    /**条件*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="reld_condition", className="")
    private String reld_condition;
    /**关联对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="reld_objt", className="OrderEntity")
    private String reld_objt;
    /**关联属性*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="reld_attr", className="")
    private String reld_attr;

    public String getReld_condition() {
        return reld_condition;
    }

    public void setReld_condition(String reld_condition) {
        this.reld_condition = reld_condition;
    }

    public String getReld_objt() {
        return reld_objt;
    }

    public void setReld_objt(String reld_objt) {
        this.reld_objt = reld_objt;
    }

    public OrderEntity loadReld_objt() {
        String propertyName = "reld_objt";
        return (OrderEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getReld_attr() {
        return reld_attr;
    }

    public void setReld_attr(String reld_attr) {
        this.reld_attr = reld_attr;
    }



}