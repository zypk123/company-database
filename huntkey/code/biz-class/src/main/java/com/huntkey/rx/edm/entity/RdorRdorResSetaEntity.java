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
public class RdorRdorResSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8191968736400767L;
    
    /**资源类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rdor_resclass", className="ResourceEntity")
    private String rdor_resclass;
    /**限定条件*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rdor_rcondition", className="")
    private String rdor_rcondition;
    /**使用范围*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rdor_ruserange", className="")
    private Integer rdor_ruserange;
    /**可再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rdor_raempower", className="")
    private Integer rdor_raempower;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rdor_beg", className="")
    private Date rdor_beg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rdor_end", className="")
    private Date rdor_end;
    /**资源对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rdor_robj", className="")
    private String rdor_robj;
    /**资源对象名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rdor_robjname", className="")
    private String rdor_robjname;

    public String getRdor_resclass() {
        return rdor_resclass;
    }

    public void setRdor_resclass(String rdor_resclass) {
        this.rdor_resclass = rdor_resclass;
    }

    public ResourceEntity loadRdor_resclass() {
        String propertyName = "rdor_resclass";
        return (ResourceEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRdor_rcondition() {
        return rdor_rcondition;
    }

    public void setRdor_rcondition(String rdor_rcondition) {
        this.rdor_rcondition = rdor_rcondition;
    }

    public Integer getRdor_ruserange() {
        return rdor_ruserange;
    }

    public void setRdor_ruserange(Integer rdor_ruserange) {
        this.rdor_ruserange = rdor_ruserange;
    }

    public Integer getRdor_raempower() {
        return rdor_raempower;
    }

    public void setRdor_raempower(Integer rdor_raempower) {
        this.rdor_raempower = rdor_raempower;
    }

    public Date getRdor_beg() {
        return rdor_beg;
    }

    public void setRdor_beg(Date rdor_beg) {
        this.rdor_beg = rdor_beg;
    }

    public Date getRdor_end() {
        return rdor_end;
    }

    public void setRdor_end(Date rdor_end) {
        this.rdor_end = rdor_end;
    }

    public String getRdor_robj() {
        return rdor_robj;
    }

    public void setRdor_robj(String rdor_robj) {
        this.rdor_robj = rdor_robj;
    }

    public String getRdor_robjname() {
        return rdor_robjname;
    }

    public void setRdor_robjname(String rdor_robjname) {
        this.rdor_robjname = rdor_robjname;
    }



}