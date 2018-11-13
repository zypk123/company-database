package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 联动明细类实体
 * 
 */
public class LinkdetailEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 7704602104448156L;
    
    /**状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="lind_status", className="")
    private Integer lind_status;
    /**时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="lind_time", className="")
    private Date lind_time;
    /**联动对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="lind_objt_orde", className="OrderEntity")
    private String lind_objt_orde;
    /**联动属性*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="lind_attr", className="")
    private String lind_attr;
    /**联动属性值*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="lind_attr_value", className="")
    private String lind_attr_value;
    /**关联属性*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="lind_rele_attr", className="")
    private String lind_rele_attr;
    /**联动类*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="lind_class", className="")
    private String lind_class;
    /**更新时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="lind_modtime", className="")
    private Date lind_modtime;
    /**关联对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="lind_releobj", className="")
    private String lind_releobj;
    /**联动响应类别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="lind_respondtype", className="")
    private String lind_respondtype;

    public Integer getLind_status() {
        return lind_status;
    }

    public void setLind_status(Integer lind_status) {
        this.lind_status = lind_status;
    }

    public Date getLind_time() {
        return lind_time;
    }

    public void setLind_time(Date lind_time) {
        this.lind_time = lind_time;
    }

    public String getLind_objt_orde() {
        return lind_objt_orde;
    }

    public void setLind_objt_orde(String lind_objt_orde) {
        this.lind_objt_orde = lind_objt_orde;
    }

    public OrderEntity loadLind_objt_orde() {
        String propertyName = "lind_objt_orde";
        return (OrderEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getLind_attr() {
        return lind_attr;
    }

    public void setLind_attr(String lind_attr) {
        this.lind_attr = lind_attr;
    }

    public String getLind_attr_value() {
        return lind_attr_value;
    }

    public void setLind_attr_value(String lind_attr_value) {
        this.lind_attr_value = lind_attr_value;
    }

    public String getLind_rele_attr() {
        return lind_rele_attr;
    }

    public void setLind_rele_attr(String lind_rele_attr) {
        this.lind_rele_attr = lind_rele_attr;
    }

    public String getLind_class() {
        return lind_class;
    }

    public void setLind_class(String lind_class) {
        this.lind_class = lind_class;
    }

    public Date getLind_modtime() {
        return lind_modtime;
    }

    public void setLind_modtime(Date lind_modtime) {
        this.lind_modtime = lind_modtime;
    }

    public String getLind_releobj() {
        return lind_releobj;
    }

    public void setLind_releobj(String lind_releobj) {
        this.lind_releobj = lind_releobj;
    }

    public String getLind_respondtype() {
        return lind_respondtype;
    }

    public void setLind_respondtype(String lind_respondtype) {
        this.lind_respondtype = lind_respondtype;
    }



}