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
public class EnteEnteAuthSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 204942796270936L;
    
    /**代表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_auth_peop", className="")
    private String ente_auth_peop;
    /**企业中职务*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_auth_duty", className="")
    private String ente_auth_duty;

    public String getEnte_auth_peop() {
        return ente_auth_peop;
    }

    public void setEnte_auth_peop(String ente_auth_peop) {
        this.ente_auth_peop = ente_auth_peop;
    }

    public String getEnte_auth_duty() {
        return ente_auth_duty;
    }

    public void setEnte_auth_duty(String ente_auth_duty) {
        this.ente_auth_duty = ente_auth_duty;
    }



}