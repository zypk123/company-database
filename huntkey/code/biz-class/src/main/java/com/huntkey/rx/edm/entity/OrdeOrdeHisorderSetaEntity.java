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
public class OrdeOrdeHisorderSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5950266021245799L;
    
    /**参考单据.单据对象定位公式*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_hobj", className="OrderEntity")
    private String orde_hobj;

    public String getOrde_hobj() {
        return orde_hobj;
    }

    public void setOrde_hobj(String orde_hobj) {
        this.orde_hobj = orde_hobj;
    }

    public OrderEntity loadOrde_hobj() {
        String propertyName = "orde_hobj";
        return (OrderEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}