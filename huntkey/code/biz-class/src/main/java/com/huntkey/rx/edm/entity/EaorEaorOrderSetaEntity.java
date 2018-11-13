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
public class EaorEaorOrderSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 3644426643797233L;
    
    /**表单*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_oorder", className="OrderEntity")
    private String eaor_oorder;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_oisempower", className="")
    private Integer eaor_oisempower;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_obeg", className="")
    private Date eaor_obeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_oend", className="")
    private Date eaor_oend;
    /**申请属性可见数据集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_oattr_set", className="EaorEaorOattrSetbEntity")
    private List<EaorEaorOattrSetbEntity> eaor_oattr_set;

    public String getEaor_oorder() {
        return eaor_oorder;
    }

    public void setEaor_oorder(String eaor_oorder) {
        this.eaor_oorder = eaor_oorder;
    }

    public OrderEntity loadEaor_oorder() {
        String propertyName = "eaor_oorder";
        return (OrderEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getEaor_oisempower() {
        return eaor_oisempower;
    }

    public void setEaor_oisempower(Integer eaor_oisempower) {
        this.eaor_oisempower = eaor_oisempower;
    }

    public Date getEaor_obeg() {
        return eaor_obeg;
    }

    public void setEaor_obeg(Date eaor_obeg) {
        this.eaor_obeg = eaor_obeg;
    }

    public Date getEaor_oend() {
        return eaor_oend;
    }

    public void setEaor_oend(Date eaor_oend) {
        this.eaor_oend = eaor_oend;
    }

    public List<EaorEaorOattrSetbEntity> loadEaor_oattr_set() {
        String propertyName = "eaor_oattr_set";
        String rootClass = "EmpowerapplyorderEntity";
        return (List<EaorEaorOattrSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEaor_oattr_set(List<EaorEaorOattrSetbEntity> eaor_oattr_set) {
        this.eaor_oattr_set = eaor_oattr_set;
    }

    public List<EaorEaorOattrSetbEntity> getEaor_oattr_set() {
        return eaor_oattr_set;
    }



}