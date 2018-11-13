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
public class EaorEaorDataSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6675222327392153L;
    
    /**可见数据类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_dclass", className="ResourceEntity")
    private String eaor_dclass;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_disempower", className="")
    private Integer eaor_disempower;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_dbeg", className="")
    private Date eaor_dbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_dend", className="")
    private Date eaor_dend;
    /**检索限定条件*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_drow_cond", className="")
    private String eaor_drow_cond;
    /**申请属性可阅性控制集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_dattr_set", className="EaorEaorDattrSetbEntity")
    private List<EaorEaorDattrSetbEntity> eaor_dattr_set;

    public String getEaor_dclass() {
        return eaor_dclass;
    }

    public void setEaor_dclass(String eaor_dclass) {
        this.eaor_dclass = eaor_dclass;
    }

    public ResourceEntity loadEaor_dclass() {
        String propertyName = "eaor_dclass";
        return (ResourceEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getEaor_disempower() {
        return eaor_disempower;
    }

    public void setEaor_disempower(Integer eaor_disempower) {
        this.eaor_disempower = eaor_disempower;
    }

    public Date getEaor_dbeg() {
        return eaor_dbeg;
    }

    public void setEaor_dbeg(Date eaor_dbeg) {
        this.eaor_dbeg = eaor_dbeg;
    }

    public Date getEaor_dend() {
        return eaor_dend;
    }

    public void setEaor_dend(Date eaor_dend) {
        this.eaor_dend = eaor_dend;
    }

    public String getEaor_drow_cond() {
        return eaor_drow_cond;
    }

    public void setEaor_drow_cond(String eaor_drow_cond) {
        this.eaor_drow_cond = eaor_drow_cond;
    }

    public List<EaorEaorDattrSetbEntity> loadEaor_dattr_set() {
        String propertyName = "eaor_dattr_set";
        String rootClass = "EmpowerapplyorderEntity";
        return (List<EaorEaorDattrSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEaor_dattr_set(List<EaorEaorDattrSetbEntity> eaor_dattr_set) {
        this.eaor_dattr_set = eaor_dattr_set;
    }

    public List<EaorEaorDattrSetbEntity> getEaor_dattr_set() {
        return eaor_dattr_set;
    }



}