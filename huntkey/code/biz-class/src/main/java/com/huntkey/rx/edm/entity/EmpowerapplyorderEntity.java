package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 权限申请单实体
 * 
 */
public class EmpowerapplyorderEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 9053994679766580L;
    
    /**申请人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_emp", className="")
    private String eaor_emp;
    /**申请时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_date", className="")
    private Date eaor_date;
    /**申请人岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_duty", className="")
    private String eaor_duty;
    /**申请人岗位名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_dutyname", className="")
    private String eaor_dutyname;
    /**申请人上级岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_pduty", className="")
    private String eaor_pduty;
    /**申请可用表单集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_order_set", className="EaorEaorOrderSetaEntity")
    private List<EaorEaorOrderSetaEntity> eaor_order_set;
    /**申请可见数据集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_data_set", className="EaorEaorDataSetaEntity")
    private List<EaorEaorDataSetaEntity> eaor_data_set;
    /**申请可见ppi集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="eaor_ppi_set", className="EaorEaorPpiSetaEntity")
    private List<EaorEaorPpiSetaEntity> eaor_ppi_set;

    public String getEaor_emp() {
        return eaor_emp;
    }

    public void setEaor_emp(String eaor_emp) {
        this.eaor_emp = eaor_emp;
    }

    public Date getEaor_date() {
        return eaor_date;
    }

    public void setEaor_date(Date eaor_date) {
        this.eaor_date = eaor_date;
    }

    public String getEaor_duty() {
        return eaor_duty;
    }

    public void setEaor_duty(String eaor_duty) {
        this.eaor_duty = eaor_duty;
    }

    public String getEaor_dutyname() {
        return eaor_dutyname;
    }

    public void setEaor_dutyname(String eaor_dutyname) {
        this.eaor_dutyname = eaor_dutyname;
    }

    public String getEaor_pduty() {
        return eaor_pduty;
    }

    public void setEaor_pduty(String eaor_pduty) {
        this.eaor_pduty = eaor_pduty;
    }

    public List<EaorEaorOrderSetaEntity> loadEaor_order_set() {
        String propertyName = "eaor_order_set";
        String rootClass = "";
        return (List<EaorEaorOrderSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEaor_order_set(List<EaorEaorOrderSetaEntity> eaor_order_set) {
        this.eaor_order_set = eaor_order_set;
    }

    public List<EaorEaorOrderSetaEntity> getEaor_order_set() {
        return eaor_order_set;
    }

    public List<EaorEaorDataSetaEntity> loadEaor_data_set() {
        String propertyName = "eaor_data_set";
        String rootClass = "";
        return (List<EaorEaorDataSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEaor_data_set(List<EaorEaorDataSetaEntity> eaor_data_set) {
        this.eaor_data_set = eaor_data_set;
    }

    public List<EaorEaorDataSetaEntity> getEaor_data_set() {
        return eaor_data_set;
    }

    public List<EaorEaorPpiSetaEntity> loadEaor_ppi_set() {
        String propertyName = "eaor_ppi_set";
        String rootClass = "";
        return (List<EaorEaorPpiSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEaor_ppi_set(List<EaorEaorPpiSetaEntity> eaor_ppi_set) {
        this.eaor_ppi_set = eaor_ppi_set;
    }

    public List<EaorEaorPpiSetaEntity> getEaor_ppi_set() {
        return eaor_ppi_set;
    }



}