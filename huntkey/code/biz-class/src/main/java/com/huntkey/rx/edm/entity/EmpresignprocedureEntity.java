package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 离职手续单类实体
 * 
 */
public class EmpresignprocedureEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 4619866940869409L;
    
    /**离职申请单*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="oerp_oera", className="EmpresignapplyEntity")
    private String oerp_oera;
    /**所属部门*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="oerp_dept", className="DepttreeEntity")
    private String oerp_dept;
    /**离职人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oerp_emp", className="EmployeeEntity")
    private String oerp_emp;
    /**离职日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oerp_date", className="")
    private Date oerp_date;
    /**离职类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oerp_type", className="WordlistEntity")
    private String oerp_type;
    /**事项明细*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oerp_item_set", className="OerpOerpItemSetaEntity")
    private List<OerpOerpItemSetaEntity> oerp_item_set;
    /**离职人数*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oerp_num", className="")
    private Integer oerp_num;

    public String getOerp_oera() {
        return oerp_oera;
    }

    public void setOerp_oera(String oerp_oera) {
        this.oerp_oera = oerp_oera;
    }

    public EmpresignapplyEntity loadOerp_oera() {
        String propertyName = "oerp_oera";
        return (EmpresignapplyEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOerp_dept() {
        return oerp_dept;
    }

    public void setOerp_dept(String oerp_dept) {
        this.oerp_dept = oerp_dept;
    }

    public DepttreeEntity loadOerp_dept() {
        String propertyName = "oerp_dept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOerp_emp() {
        return oerp_emp;
    }

    public void setOerp_emp(String oerp_emp) {
        this.oerp_emp = oerp_emp;
    }

    public EmployeeEntity loadOerp_emp() {
        String propertyName = "oerp_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getOerp_date() {
        return oerp_date;
    }

    public void setOerp_date(Date oerp_date) {
        this.oerp_date = oerp_date;
    }

    public String getOerp_type() {
        return oerp_type;
    }

    public void setOerp_type(String oerp_type) {
        this.oerp_type = oerp_type;
    }

    public WordlistEntity loadOerp_type() {
        String propertyName = "oerp_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<OerpOerpItemSetaEntity> loadOerp_item_set() {
        String propertyName = "oerp_item_set";
        String rootClass = "";
        return (List<OerpOerpItemSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOerp_item_set(List<OerpOerpItemSetaEntity> oerp_item_set) {
        this.oerp_item_set = oerp_item_set;
    }

    public List<OerpOerpItemSetaEntity> getOerp_item_set() {
        return oerp_item_set;
    }

    public Integer getOerp_num() {
        return oerp_num;
    }

    public void setOerp_num(Integer oerp_num) {
        this.oerp_num = oerp_num;
    }



}