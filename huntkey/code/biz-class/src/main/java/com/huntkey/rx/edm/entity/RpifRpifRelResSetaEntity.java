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
public class RpifRpifRelResSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6099278065457093L;
    
    /**关联资源类*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="rpif_res_class", className="")
    private String rpif_res_class;

    public String getRpif_res_class() {
        return rpif_res_class;
    }

    public void setRpif_res_class(String rpif_res_class) {
        this.rpif_res_class = rpif_res_class;
    }



}