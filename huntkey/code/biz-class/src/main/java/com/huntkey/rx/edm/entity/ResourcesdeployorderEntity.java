package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 资源配置单实体
 * 
 */
public class ResourcesdeployorderEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 3372632107179454L;
    
    /**授权人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rdor_authorizer", className="")
    private String rdor_authorizer;
    /**授权人岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rdor_auth_duty", className="")
    private String rdor_auth_duty;
    /**授权岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rdor_emduty", className="")
    private String rdor_emduty;
    /**授权时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rdor_emdate", className="")
    private Date rdor_emdate;
    /**授权资源配置集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rdor_res_set", className="RdorRdorResSetaEntity")
    private List<RdorRdorResSetaEntity> rdor_res_set;

    public String getRdor_authorizer() {
        return rdor_authorizer;
    }

    public void setRdor_authorizer(String rdor_authorizer) {
        this.rdor_authorizer = rdor_authorizer;
    }

    public String getRdor_auth_duty() {
        return rdor_auth_duty;
    }

    public void setRdor_auth_duty(String rdor_auth_duty) {
        this.rdor_auth_duty = rdor_auth_duty;
    }

    public String getRdor_emduty() {
        return rdor_emduty;
    }

    public void setRdor_emduty(String rdor_emduty) {
        this.rdor_emduty = rdor_emduty;
    }

    public Date getRdor_emdate() {
        return rdor_emdate;
    }

    public void setRdor_emdate(Date rdor_emdate) {
        this.rdor_emdate = rdor_emdate;
    }

    public List<RdorRdorResSetaEntity> loadRdor_res_set() {
        String propertyName = "rdor_res_set";
        String rootClass = "";
        return (List<RdorRdorResSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRdor_res_set(List<RdorRdorResSetaEntity> rdor_res_set) {
        this.rdor_res_set = rdor_res_set;
    }

    public List<RdorRdorResSetaEntity> getRdor_res_set() {
        return rdor_res_set;
    }



}