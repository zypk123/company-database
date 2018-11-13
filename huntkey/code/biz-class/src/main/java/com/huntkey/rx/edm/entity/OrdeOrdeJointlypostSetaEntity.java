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
public class OrdeOrdeJointlypostSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 1503987152099120L;
    
    /**提交状态*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_jointly_status", className="")
    private String orde_jointly_status;
    /**岗位对象*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_jointly_pos", className="JobpositionEntity")
    private String orde_jointly_pos;

    public String getOrde_jointly_status() {
        return orde_jointly_status;
    }

    public void setOrde_jointly_status(String orde_jointly_status) {
        this.orde_jointly_status = orde_jointly_status;
    }

    public String getOrde_jointly_pos() {
        return orde_jointly_pos;
    }

    public void setOrde_jointly_pos(String orde_jointly_pos) {
        this.orde_jointly_pos = orde_jointly_pos;
    }

    public JobpositionEntity loadOrde_jointly_pos() {
        String propertyName = "orde_jointly_pos";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}