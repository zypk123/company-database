package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 全球物流索引类实体
 * 
 */
public class GimEntity extends EcosystemEntity implements Serializable {
    private static final long serialVersionUID = 3480378777177948L;
    
    /**物料识别码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_code", className="")
    private String goim_code;
    /**物料名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_name", className="")
    private String goim_name;
    /**物料描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_desc", className="")
    private String goim_desc;
    /**首登记时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_register_time", className="")
    private Date goim_register_time;
    /**计量单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_meas", className="MeasureunitEntity")
    private String goim_meas;
    /**首登记自然人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_register_peop", className="")
    private String goim_register_peop;
    /**首登记企业*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_register_ente", className="EnterpriseEntity")
    private String goim_register_ente;
    /**事务特性列表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_char_set", className="GoimGoimCharSetaEntity")
    private List<GoimGoimCharSetaEntity> goim_char_set;
    /**视图特性表修订历史*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_hist_set", className="GoimGoimHistSetaEntity")
    private List<GoimGoimHistSetaEntity> goim_hist_set;
    /**企业需求登记列表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_requ_set", className="GoimGoimRequSetaEntity")
    private List<GoimGoimRequSetaEntity> goim_requ_set;
    /**企业可供登记列表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_appr_set", className="GoimGoimApprSetaEntity")
    private List<GoimGoimApprSetaEntity> goim_appr_set;

    public String getGoim_code() {
        return goim_code;
    }

    public void setGoim_code(String goim_code) {
        this.goim_code = goim_code;
    }

    public String getGoim_name() {
        return goim_name;
    }

    public void setGoim_name(String goim_name) {
        this.goim_name = goim_name;
    }

    public String getGoim_desc() {
        return goim_desc;
    }

    public void setGoim_desc(String goim_desc) {
        this.goim_desc = goim_desc;
    }

    public Date getGoim_register_time() {
        return goim_register_time;
    }

    public void setGoim_register_time(Date goim_register_time) {
        this.goim_register_time = goim_register_time;
    }

    public String getGoim_meas() {
        return goim_meas;
    }

    public void setGoim_meas(String goim_meas) {
        this.goim_meas = goim_meas;
    }

    public MeasureunitEntity loadGoim_meas() {
        String propertyName = "goim_meas";
        return (MeasureunitEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getGoim_register_peop() {
        return goim_register_peop;
    }

    public void setGoim_register_peop(String goim_register_peop) {
        this.goim_register_peop = goim_register_peop;
    }

    public String getGoim_register_ente() {
        return goim_register_ente;
    }

    public void setGoim_register_ente(String goim_register_ente) {
        this.goim_register_ente = goim_register_ente;
    }

    public EnterpriseEntity loadGoim_register_ente() {
        String propertyName = "goim_register_ente";
        return (EnterpriseEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<GoimGoimCharSetaEntity> loadGoim_char_set() {
        String propertyName = "goim_char_set";
        String rootClass = "";
        return (List<GoimGoimCharSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setGoim_char_set(List<GoimGoimCharSetaEntity> goim_char_set) {
        this.goim_char_set = goim_char_set;
    }

    public List<GoimGoimCharSetaEntity> getGoim_char_set() {
        return goim_char_set;
    }

    public List<GoimGoimHistSetaEntity> loadGoim_hist_set() {
        String propertyName = "goim_hist_set";
        String rootClass = "";
        return (List<GoimGoimHistSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setGoim_hist_set(List<GoimGoimHistSetaEntity> goim_hist_set) {
        this.goim_hist_set = goim_hist_set;
    }

    public List<GoimGoimHistSetaEntity> getGoim_hist_set() {
        return goim_hist_set;
    }

    public List<GoimGoimRequSetaEntity> loadGoim_requ_set() {
        String propertyName = "goim_requ_set";
        String rootClass = "";
        return (List<GoimGoimRequSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setGoim_requ_set(List<GoimGoimRequSetaEntity> goim_requ_set) {
        this.goim_requ_set = goim_requ_set;
    }

    public List<GoimGoimRequSetaEntity> getGoim_requ_set() {
        return goim_requ_set;
    }

    public List<GoimGoimApprSetaEntity> loadGoim_appr_set() {
        String propertyName = "goim_appr_set";
        String rootClass = "";
        return (List<GoimGoimApprSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setGoim_appr_set(List<GoimGoimApprSetaEntity> goim_appr_set) {
        this.goim_appr_set = goim_appr_set;
    }

    public List<GoimGoimApprSetaEntity> getGoim_appr_set() {
        return goim_appr_set;
    }



}