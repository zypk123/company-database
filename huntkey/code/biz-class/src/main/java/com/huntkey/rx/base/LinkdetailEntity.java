package com.huntkey.rx.base;

import com.huntkey.rx.edm.entity.OrderEntity;
import com.huntkey.rx.util.EntityUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by linziy on 2017/11/24.
 */
public class LinkdetailEntity extends BaseEntity implements Serializable {
    @PropertyAnnotation(
            fomula = "",
            limitFomula = "",
            fieldName = "lind_status",
            className = ""
    )
    private Integer lind_status;
    @PropertyAnnotation(
            fomula = "",
            limitFomula = "",
            fieldName = "lind_time",
            className = ""
    )
    private Date lind_time;
    @PropertyAnnotation(
            fomula = "",
            limitFomula = "",
            fieldName = "lind_objt_orde",
            className = "OrderEntity"
    )
    private String lind_objt_orde;
    @PropertyAnnotation(
            fomula = "",
            limitFomula = "",
            fieldName = "lind_attr",
            className = ""
    )
    private String lind_attr;
    @PropertyAnnotation(
            fomula = "",
            limitFomula = "",
            fieldName = "lind_attr_value",
            className = ""
    )
    private String lind_attr_value;
    @PropertyAnnotation(
            fomula = "",
            limitFomula = "",
            fieldName = "lind_rele_attr",
            className = ""
    )
    private String lind_rele_attr;
    @PropertyAnnotation(
            fomula = "null",
            limitFomula = "null",
            fieldName = "lind_class",
            className = ""
    )
    private String lind_class;
    @PropertyAnnotation(
            fomula = "",
            limitFomula = "",
            fieldName = "lind_modtime",
            className = ""
    )
    private Date lind_modtime;
    @PropertyAnnotation(
            fomula = "",
            limitFomula = "",
            fieldName = "lind_releobj",
            className = ""
    )
    private String lind_releobj;
    @PropertyAnnotation(
            fomula = "",
            limitFomula = "",
            fieldName = "lind_respondtype",
            className = ""
    )
    private String lind_respondtype;

    public LinkdetailEntity() {

    }

    public Integer getLind_status() {
        return this.lind_status;
    }

    public void setLind_status(Integer lind_status) {
        this.lind_status = lind_status;
    }

    public Date getLind_time() {
        return this.lind_time;
    }

    public void setLind_time(Date lind_time) {
        this.lind_time = lind_time;
    }

    public String getLind_objt_orde() {
        return this.lind_objt_orde;
    }

    public void setLind_objt_orde(String lind_objt_orde) {
        this.lind_objt_orde = lind_objt_orde;
    }

    public OrderEntity loadLind_objt_orde() {
        String propertyName = "lind_objt_orde";
        return (OrderEntity) EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getLind_attr() {
        return this.lind_attr;
    }

    public void setLind_attr(String lind_attr) {
        this.lind_attr = lind_attr;
    }

    public String getLind_attr_value() {
        return this.lind_attr_value;
    }

    public void setLind_attr_value(String lind_attr_value) {
        this.lind_attr_value = lind_attr_value;
    }

    public String getLind_rele_attr() {
        return this.lind_rele_attr;
    }

    public void setLind_rele_attr(String lind_rele_attr) {
        this.lind_rele_attr = lind_rele_attr;
    }

    public String getLind_class() {
        return this.lind_class;
    }

    public void setLind_class(String lind_class) {
        this.lind_class = lind_class;
    }

    public Date getLind_modtime() {
        return this.lind_modtime;
    }

    public void setLind_modtime(Date lind_modtime) {
        this.lind_modtime = lind_modtime;
    }

    public String getLind_releobj() {
        return this.lind_releobj;
    }

    public void setLind_releobj(String lind_releobj) {
        this.lind_releobj = lind_releobj;
    }

    public String getLind_respondtype() {
        return this.lind_respondtype;
    }

    public void setLind_respondtype(String lind_respondtype) {
        this.lind_respondtype = lind_respondtype;
    }

}
