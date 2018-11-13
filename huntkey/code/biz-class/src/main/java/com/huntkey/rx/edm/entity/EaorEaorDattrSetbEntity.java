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
public class EaorEaorDattrSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6966325637619452L;
    
    /**属性名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_dattrname", className="")
    private String eaor_dattrname;
    /**限定方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_dalimit", className="")
    private String eaor_dalimit;
    /**限定条件*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_dacond", className="")
    private String eaor_dacond;

    public String getEaor_dattrname() {
        return eaor_dattrname;
    }

    public void setEaor_dattrname(String eaor_dattrname) {
        this.eaor_dattrname = eaor_dattrname;
    }

    public String getEaor_dalimit() {
        return eaor_dalimit;
    }

    public void setEaor_dalimit(String eaor_dalimit) {
        this.eaor_dalimit = eaor_dalimit;
    }

    public String getEaor_dacond() {
        return eaor_dacond;
    }

    public void setEaor_dacond(String eaor_dacond) {
        this.eaor_dacond = eaor_dacond;
    }



}