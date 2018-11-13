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
public class ResoResoOrderSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 3572994379857907L;
    
    /**单据对象*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="reso_orderobj", className="OrderEntity")
    private String reso_orderobj;
    /**所属单据类*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="reso_orderclass", className="")
    private String reso_orderclass;

    public String getReso_orderobj() {
        return reso_orderobj;
    }

    public void setReso_orderobj(String reso_orderobj) {
        this.reso_orderobj = reso_orderobj;
    }

    public OrderEntity loadReso_orderobj() {
        String propertyName = "reso_orderobj";
        return (OrderEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getReso_orderclass() {
        return reso_orderclass;
    }

    public void setReso_orderclass(String reso_orderclass) {
        this.reso_orderclass = reso_orderclass;
    }



}