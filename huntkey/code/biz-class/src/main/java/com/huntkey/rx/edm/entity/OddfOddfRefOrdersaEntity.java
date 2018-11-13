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
public class OddfOddfRefOrdersaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 2302105944159814L;
    
    /**单据对象定位公式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oddf_ref_order_formula", className="")
    private String oddf_ref_order_formula;

    public String getOddf_ref_order_formula() {
        return oddf_ref_order_formula;
    }

    public void setOddf_ref_order_formula(String oddf_ref_order_formula) {
        this.oddf_ref_order_formula = oddf_ref_order_formula;
    }



}