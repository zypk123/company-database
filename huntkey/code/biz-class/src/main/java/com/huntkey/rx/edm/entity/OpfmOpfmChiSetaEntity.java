package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class OpfmOpfmChiSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 5870497004069093L;
    
    /**单据状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_order_stcd", className="WordlistEntity")
    private String opfm_order_stcd;
    /**单据单号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_order_num", className="")
    private String opfm_order_num;
    /**审批流程*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_approve_flow_obj", className="")
    private String opfm_approve_flow_obj;
    /**适用周期集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_time_set", className="OpfmOpfmTimeSetbEntity")
    private List<OpfmOpfmTimeSetbEntity> opfm_time_set;
    /**公式涉及的变量集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_cal_set", className="OpfmOpfmCalSetbEntity")
    private List<OpfmOpfmCalSetbEntity> opfm_cal_set;

    public String getOpfm_order_stcd() {
        return opfm_order_stcd;
    }

    public void setOpfm_order_stcd(String opfm_order_stcd) {
        this.opfm_order_stcd = opfm_order_stcd;
    }

    public WordlistEntity loadOpfm_order_stcd() {
        String propertyName = "opfm_order_stcd";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOpfm_order_num() {
        return opfm_order_num;
    }

    public void setOpfm_order_num(String opfm_order_num) {
        this.opfm_order_num = opfm_order_num;
    }

    public String getOpfm_approve_flow_obj() {
        return opfm_approve_flow_obj;
    }

    public void setOpfm_approve_flow_obj(String opfm_approve_flow_obj) {
        this.opfm_approve_flow_obj = opfm_approve_flow_obj;
    }

    public List<OpfmOpfmTimeSetbEntity> loadOpfm_time_set() {
        String propertyName = "opfm_time_set";
        String rootClass = "PpidefinitionmaintEntity";
        return (List<OpfmOpfmTimeSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOpfm_time_set(List<OpfmOpfmTimeSetbEntity> opfm_time_set) {
        this.opfm_time_set = opfm_time_set;
    }

    public List<OpfmOpfmTimeSetbEntity> getOpfm_time_set() {
        return opfm_time_set;
    }

    public List<OpfmOpfmCalSetbEntity> loadOpfm_cal_set() {
        String propertyName = "opfm_cal_set";
        String rootClass = "PpidefinitionmaintEntity";
        return (List<OpfmOpfmCalSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOpfm_cal_set(List<OpfmOpfmCalSetbEntity> opfm_cal_set) {
        this.opfm_cal_set = opfm_cal_set;
    }

    public List<OpfmOpfmCalSetbEntity> getOpfm_cal_set() {
        return opfm_cal_set;
    }



}