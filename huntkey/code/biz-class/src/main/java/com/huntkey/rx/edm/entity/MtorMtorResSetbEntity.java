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
public class MtorMtorResSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 2802365951501442L;
    
    /**资源对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_res_id", className="")
    private String mtor_res_id;

    public String getMtor_res_id() {
        return mtor_res_id;
    }

    public void setMtor_res_id(String mtor_res_id) {
        this.mtor_res_id = mtor_res_id;
    }



}