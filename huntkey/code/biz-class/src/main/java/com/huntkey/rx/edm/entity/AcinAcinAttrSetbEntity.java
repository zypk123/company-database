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
public class AcinAcinAttrSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 4240872272961413L;
    
    /**属性名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="acin_aname", className="")
    private String acin_aname;
    /**限定方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="acin_alimit_type", className="")
    private String acin_alimit_type;
    /**限定条件*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="acin_alimit_con", className="")
    private String acin_alimit_con;

    public String getAcin_aname() {
        return acin_aname;
    }

    public void setAcin_aname(String acin_aname) {
        this.acin_aname = acin_aname;
    }

    public String getAcin_alimit_type() {
        return acin_alimit_type;
    }

    public void setAcin_alimit_type(String acin_alimit_type) {
        this.acin_alimit_type = acin_alimit_type;
    }

    public String getAcin_alimit_con() {
        return acin_alimit_con;
    }

    public void setAcin_alimit_con(String acin_alimit_con) {
        this.acin_alimit_con = acin_alimit_con;
    }



}