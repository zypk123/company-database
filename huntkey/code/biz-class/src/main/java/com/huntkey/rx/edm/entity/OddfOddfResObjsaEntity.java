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
public class OddfOddfResObjsaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 7653424418521708L;
    
    /**资源对象定位公式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oddf_resource_formula", className="")
    private String oddf_resource_formula;

    public String getOddf_resource_formula() {
        return oddf_resource_formula;
    }

    public void setOddf_resource_formula(String oddf_resource_formula) {
        this.oddf_resource_formula = oddf_resource_formula;
    }



}