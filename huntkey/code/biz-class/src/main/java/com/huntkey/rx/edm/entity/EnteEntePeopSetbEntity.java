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
public class EnteEntePeopSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 1845605629076904L;
    
    /**人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_peop", className="")
    private String ente_peop;

    public String getEnte_peop() {
        return ente_peop;
    }

    public void setEnte_peop(String ente_peop) {
        this.ente_peop = ente_peop;
    }



}