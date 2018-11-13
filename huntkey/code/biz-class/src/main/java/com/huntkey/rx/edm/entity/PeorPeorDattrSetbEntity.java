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
public class PeorPeorDattrSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8001378144197109L;
    
    /**属性名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_daname", className="")
    private String peor_daname;
    /**限定方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_dalimit", className="")
    private String peor_dalimit;
    /**限定条件*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_dacond", className="")
    private String peor_dacond;

    public String getPeor_daname() {
        return peor_daname;
    }

    public void setPeor_daname(String peor_daname) {
        this.peor_daname = peor_daname;
    }

    public String getPeor_dalimit() {
        return peor_dalimit;
    }

    public void setPeor_dalimit(String peor_dalimit) {
        this.peor_dalimit = peor_dalimit;
    }

    public String getPeor_dacond() {
        return peor_dacond;
    }

    public void setPeor_dacond(String peor_dacond) {
        this.peor_dacond = peor_dacond;
    }



}