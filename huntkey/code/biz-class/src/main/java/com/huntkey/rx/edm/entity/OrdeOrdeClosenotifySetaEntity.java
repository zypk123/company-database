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
public class OrdeOrdeClosenotifySetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 1662594115230730L;
    
    /**岗位对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_to_notice_jp_obj", className="JobpositionEntity")
    private String orde_to_notice_jp_obj;

    public String getOrde_to_notice_jp_obj() {
        return orde_to_notice_jp_obj;
    }

    public void setOrde_to_notice_jp_obj(String orde_to_notice_jp_obj) {
        this.orde_to_notice_jp_obj = orde_to_notice_jp_obj;
    }

    public JobpositionEntity loadOrde_to_notice_jp_obj() {
        String propertyName = "orde_to_notice_jp_obj";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}