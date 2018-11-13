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
public class GoveGovePeopSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9006815855336832L;
    
    /**人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="gove_peop", className="")
    private String gove_peop;

    public String getGove_peop() {
        return gove_peop;
    }

    public void setGove_peop(String gove_peop) {
        this.gove_peop = gove_peop;
    }



}