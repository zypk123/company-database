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
public class ActpActpLeftSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6886374371225512L;
    
    /**左邻模板*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_left_pattern", className="ActivitytemplateEntity")
    private String actp_left_pattern;

    public String getActp_left_pattern() {
        return actp_left_pattern;
    }

    public void setActp_left_pattern(String actp_left_pattern) {
        this.actp_left_pattern = actp_left_pattern;
    }

    public ActivitytemplateEntity loadActp_left_pattern() {
        String propertyName = "actp_left_pattern";
        return (ActivitytemplateEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}