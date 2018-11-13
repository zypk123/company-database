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
public class EaorEaorOattrSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 3537678522189749L;
    
    /**属性名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_oattrname", className="")
    private String eaor_oattrname;
    /**限制方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_oalimit", className="")
    private String eaor_oalimit;
    /**限定公式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_oacond", className="")
    private String eaor_oacond;

    public String getEaor_oattrname() {
        return eaor_oattrname;
    }

    public void setEaor_oattrname(String eaor_oattrname) {
        this.eaor_oattrname = eaor_oattrname;
    }

    public String getEaor_oalimit() {
        return eaor_oalimit;
    }

    public void setEaor_oalimit(String eaor_oalimit) {
        this.eaor_oalimit = eaor_oalimit;
    }

    public String getEaor_oacond() {
        return eaor_oacond;
    }

    public void setEaor_oacond(String eaor_oacond) {
        this.eaor_oacond = eaor_oacond;
    }



}