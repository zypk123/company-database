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
public class PeorPeorDutySetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8753722832523922L;
    
    /**授权岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_dduty", className="")
    private String peor_dduty;

    public String getPeor_dduty() {
        return peor_dduty;
    }

    public void setPeor_dduty(String peor_dduty) {
        this.peor_dduty = peor_dduty;
    }



}