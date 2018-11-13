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
public class ActpActpRightSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 7646672802201425L;
    
    /**右邻模板*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_right_pattern", className="ActivitytemplateEntity")
    private String actp_right_pattern;

    public String getActp_right_pattern() {
        return actp_right_pattern;
    }

    public void setActp_right_pattern(String actp_right_pattern) {
        this.actp_right_pattern = actp_right_pattern;
    }

    public ActivitytemplateEntity loadActp_right_pattern() {
        String propertyName = "actp_right_pattern";
        return (ActivitytemplateEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}