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
public class RpifRpifResourcesPropsSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8801700910869843L;
    
    /**    资源类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_resources_class", className="")
    private String rpif_resources_class;
    /**    资源类属性*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_resources_prop", className="")
    private String rpif_resources_prop;

    public String getRpif_resources_class() {
        return rpif_resources_class;
    }

    public void setRpif_resources_class(String rpif_resources_class) {
        this.rpif_resources_class = rpif_resources_class;
    }

    public String getRpif_resources_prop() {
        return rpif_resources_prop;
    }

    public void setRpif_resources_prop(String rpif_resources_prop) {
        this.rpif_resources_prop = rpif_resources_prop;
    }



}