package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 部门结构异动单类实体
 * 
 */
public class DeptstuchangeorderEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 573777535754769L;
    
    /**单据类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_type", className="WordlistEntity")
    private String odsc_type;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_beg", className="")
    private Date odsc_beg;
    /**备注*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_remark", className="")
    private String odsc_remark;
    /**异动列表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odsc_chag_set", className="OdscOdscChagSetaEntity")
    private List<OdscOdscChagSetaEntity> odsc_chag_set;
    /**起始部门*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="odsc_dept_beg", className="DepttreeEntity")
    private String odsc_dept_beg;

    public String getOdsc_type() {
        return odsc_type;
    }

    public void setOdsc_type(String odsc_type) {
        this.odsc_type = odsc_type;
    }

    public WordlistEntity loadOdsc_type() {
        String propertyName = "odsc_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getOdsc_beg() {
        return odsc_beg;
    }

    public void setOdsc_beg(Date odsc_beg) {
        this.odsc_beg = odsc_beg;
    }

    public String getOdsc_remark() {
        return odsc_remark;
    }

    public void setOdsc_remark(String odsc_remark) {
        this.odsc_remark = odsc_remark;
    }

    public List<OdscOdscChagSetaEntity> loadOdsc_chag_set() {
        String propertyName = "odsc_chag_set";
        String rootClass = "";
        return (List<OdscOdscChagSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOdsc_chag_set(List<OdscOdscChagSetaEntity> odsc_chag_set) {
        this.odsc_chag_set = odsc_chag_set;
    }

    public List<OdscOdscChagSetaEntity> getOdsc_chag_set() {
        return odsc_chag_set;
    }

    public String getOdsc_dept_beg() {
        return odsc_dept_beg;
    }

    public void setOdsc_dept_beg(String odsc_dept_beg) {
        this.odsc_dept_beg = odsc_dept_beg;
    }

    public DepttreeEntity loadOdsc_dept_beg() {
        String propertyName = "odsc_dept_beg";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}