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
public class OwreOwreResourceSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 2268503123653702L;
    
    /**资源对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="owre_robj", className="ResourceEntity")
    private String owre_robj;
    /**授权岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="owre_rduty", className="JobpositionEntity")
    private String owre_rduty;
    /**授权人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="owre_rauthorizer", className="EmployeeEntity")
    private String owre_rauthorizer;
    /**授权范围*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="owre_rauth_range", className="")
    private String owre_rauth_range;
    /**授权时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="owre_rdate", className="")
    private Date owre_rdate;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="owre_rbeg", className="")
    private Date owre_rbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="owre_rend", className="")
    private Date owre_rend;

    public String getOwre_robj() {
        return owre_robj;
    }

    public void setOwre_robj(String owre_robj) {
        this.owre_robj = owre_robj;
    }

    public ResourceEntity loadOwre_robj() {
        String propertyName = "owre_robj";
        return (ResourceEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOwre_rduty() {
        return owre_rduty;
    }

    public void setOwre_rduty(String owre_rduty) {
        this.owre_rduty = owre_rduty;
    }

    public JobpositionEntity loadOwre_rduty() {
        String propertyName = "owre_rduty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOwre_rauthorizer() {
        return owre_rauthorizer;
    }

    public void setOwre_rauthorizer(String owre_rauthorizer) {
        this.owre_rauthorizer = owre_rauthorizer;
    }

    public EmployeeEntity loadOwre_rauthorizer() {
        String propertyName = "owre_rauthorizer";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOwre_rauth_range() {
        return owre_rauth_range;
    }

    public void setOwre_rauth_range(String owre_rauth_range) {
        this.owre_rauth_range = owre_rauth_range;
    }

    public Date getOwre_rdate() {
        return owre_rdate;
    }

    public void setOwre_rdate(Date owre_rdate) {
        this.owre_rdate = owre_rdate;
    }

    public Date getOwre_rbeg() {
        return owre_rbeg;
    }

    public void setOwre_rbeg(Date owre_rbeg) {
        this.owre_rbeg = owre_rbeg;
    }

    public Date getOwre_rend() {
        return owre_rend;
    }

    public void setOwre_rend(Date owre_rend) {
        this.owre_rend = owre_rend;
    }



}