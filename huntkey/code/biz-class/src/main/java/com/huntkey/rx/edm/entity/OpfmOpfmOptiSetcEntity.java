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
public class OpfmOpfmOptiSetcEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 531846284941449L;
    
    /**资源类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_res_class", className="")
    private String opfm_res_class;
    /**统计属性*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_prop", className="")
    private String opfm_prop;

    public String getOpfm_res_class() {
        return opfm_res_class;
    }

    public void setOpfm_res_class(String opfm_res_class) {
        this.opfm_res_class = opfm_res_class;
    }

    public String getOpfm_prop() {
        return opfm_prop;
    }

    public void setOpfm_prop(String opfm_prop) {
        this.opfm_prop = opfm_prop;
    }



}