package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class AsorAsorOrdeSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 3538221840986573L;
    
    /**表单*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="asor_ordeclass", className="OrderEntity")
    private String asor_ordeclass;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="asor_oaisempower", className="")
    private String asor_oaisempower;
    /**授权时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="asor_opdate", className="")
    private Date asor_opdate;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="asor_obeg", className="")
    private Date asor_obeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="asor_oend", className="")
    private Date asor_oend;

    public String getAsor_ordeclass() {
        return asor_ordeclass;
    }

    public void setAsor_ordeclass(String asor_ordeclass) {
        this.asor_ordeclass = asor_ordeclass;
    }

    public OrderEntity loadAsor_ordeclass() {
        String propertyName = "asor_ordeclass";
        return (OrderEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAsor_oaisempower() {
        return asor_oaisempower;
    }

    public void setAsor_oaisempower(String asor_oaisempower) {
        this.asor_oaisempower = asor_oaisempower;
    }

    public Date getAsor_opdate() {
        return asor_opdate;
    }

    public void setAsor_opdate(Date asor_opdate) {
        this.asor_opdate = asor_opdate;
    }

    public Date getAsor_obeg() {
        return asor_obeg;
    }

    public void setAsor_obeg(Date asor_obeg) {
        this.asor_obeg = asor_obeg;
    }

    public Date getAsor_oend() {
        return asor_oend;
    }

    public void setAsor_oend(Date asor_oend) {
        this.asor_oend = asor_oend;
    }



}