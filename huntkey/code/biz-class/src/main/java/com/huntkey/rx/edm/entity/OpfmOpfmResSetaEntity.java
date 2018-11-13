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
public class OpfmOpfmResSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8048810857275791L;
    
    /**资源名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_res_class", className="")
    private String opfm_res_class;

    public String getOpfm_res_class() {
        return opfm_res_class;
    }

    public void setOpfm_res_class(String opfm_res_class) {
        this.opfm_res_class = opfm_res_class;
    }



}