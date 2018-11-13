package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 岗位授权单实体
 * 
 */
public class PositionempowerorderEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 2621986562263914L;
    
    /**授权人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_authorizer", className="")
    private String peor_authorizer;
    /**授权人岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_auduty", className="")
    private String peor_auduty;
    /**授权时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_date", className="")
    private Date peor_date;
    /**授权人岗位名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_auduty_name", className="")
    private String peor_auduty_name;
    /**授权岗位集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_duty_set", className="PeorPeorDutySetaEntity")
    private List<PeorPeorDutySetaEntity> peor_duty_set;
    /**授权可用表单集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_asor_set", className="PeorPeorAsorSetaEntity")
    private List<PeorPeorAsorSetaEntity> peor_asor_set;
    /**授权可见数据集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_data_set", className="PeorPeorDataSetaEntity")
    private List<PeorPeorDataSetaEntity> peor_data_set;
    /**授权可见ppi集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_ppi_set", className="PeorPeorPpiSetaEntity")
    private List<PeorPeorPpiSetaEntity> peor_ppi_set;

    public String getPeor_authorizer() {
        return peor_authorizer;
    }

    public void setPeor_authorizer(String peor_authorizer) {
        this.peor_authorizer = peor_authorizer;
    }

    public String getPeor_auduty() {
        return peor_auduty;
    }

    public void setPeor_auduty(String peor_auduty) {
        this.peor_auduty = peor_auduty;
    }

    public Date getPeor_date() {
        return peor_date;
    }

    public void setPeor_date(Date peor_date) {
        this.peor_date = peor_date;
    }

    public String getPeor_auduty_name() {
        return peor_auduty_name;
    }

    public void setPeor_auduty_name(String peor_auduty_name) {
        this.peor_auduty_name = peor_auduty_name;
    }

    public List<PeorPeorDutySetaEntity> loadPeor_duty_set() {
        String propertyName = "peor_duty_set";
        String rootClass = "";
        return (List<PeorPeorDutySetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPeor_duty_set(List<PeorPeorDutySetaEntity> peor_duty_set) {
        this.peor_duty_set = peor_duty_set;
    }

    public List<PeorPeorDutySetaEntity> getPeor_duty_set() {
        return peor_duty_set;
    }

    public List<PeorPeorAsorSetaEntity> loadPeor_asor_set() {
        String propertyName = "peor_asor_set";
        String rootClass = "";
        return (List<PeorPeorAsorSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPeor_asor_set(List<PeorPeorAsorSetaEntity> peor_asor_set) {
        this.peor_asor_set = peor_asor_set;
    }

    public List<PeorPeorAsorSetaEntity> getPeor_asor_set() {
        return peor_asor_set;
    }

    public List<PeorPeorDataSetaEntity> loadPeor_data_set() {
        String propertyName = "peor_data_set";
        String rootClass = "";
        return (List<PeorPeorDataSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPeor_data_set(List<PeorPeorDataSetaEntity> peor_data_set) {
        this.peor_data_set = peor_data_set;
    }

    public List<PeorPeorDataSetaEntity> getPeor_data_set() {
        return peor_data_set;
    }

    public List<PeorPeorPpiSetaEntity> loadPeor_ppi_set() {
        String propertyName = "peor_ppi_set";
        String rootClass = "";
        return (List<PeorPeorPpiSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPeor_ppi_set(List<PeorPeorPpiSetaEntity> peor_ppi_set) {
        this.peor_ppi_set = peor_ppi_set;
    }

    public List<PeorPeorPpiSetaEntity> getPeor_ppi_set() {
        return peor_ppi_set;
    }



}