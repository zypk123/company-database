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
public class PeorPeorAsorSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6656994117997412L;
    
    /**表单*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_aorder", className="OrderEntity")
    private String peor_aorder;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_aisempower", className="")
    private Integer peor_aisempower;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_abeg", className="")
    private Date peor_abeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_aend", className="")
    private Date peor_aend;
    /**授权属性可见数据集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_aattr_set", className="PeorPeorAattrSetbEntity")
    private List<PeorPeorAattrSetbEntity> peor_aattr_set;

    public String getPeor_aorder() {
        return peor_aorder;
    }

    public void setPeor_aorder(String peor_aorder) {
        this.peor_aorder = peor_aorder;
    }

    public OrderEntity loadPeor_aorder() {
        String propertyName = "peor_aorder";
        return (OrderEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getPeor_aisempower() {
        return peor_aisempower;
    }

    public void setPeor_aisempower(Integer peor_aisempower) {
        this.peor_aisempower = peor_aisempower;
    }

    public Date getPeor_abeg() {
        return peor_abeg;
    }

    public void setPeor_abeg(Date peor_abeg) {
        this.peor_abeg = peor_abeg;
    }

    public Date getPeor_aend() {
        return peor_aend;
    }

    public void setPeor_aend(Date peor_aend) {
        this.peor_aend = peor_aend;
    }

    public List<PeorPeorAattrSetbEntity> loadPeor_aattr_set() {
        String propertyName = "peor_aattr_set";
        String rootClass = "PositionempowerorderEntity";
        return (List<PeorPeorAattrSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPeor_aattr_set(List<PeorPeorAattrSetbEntity> peor_aattr_set) {
        this.peor_aattr_set = peor_aattr_set;
    }

    public List<PeorPeorAattrSetbEntity> getPeor_aattr_set() {
        return peor_aattr_set;
    }



}