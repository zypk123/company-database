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
public class GoveGoveGoveSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6082270915704024L;
    
    /**政府*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="gove_gove", className="GovernmentEntity")
    private String gove_gove;

    public String getGove_gove() {
        return gove_gove;
    }

    public void setGove_gove(String gove_gove) {
        this.gove_gove = gove_gove;
    }

    public GovernmentEntity loadGove_gove() {
        String propertyName = "gove_gove";
        return (GovernmentEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}