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
public class NbrlNbrlItemSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8647463234950423L;
    
    /**规则项排序*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_item_order", className="")
    private Integer nbrl_item_order;
    /**规则项类型*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_item_type", className="WordlistEntity")
    private String nbrl_item_type;
    /**时间集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_time_set", className="NbrlNbrlTimeSetcEntity")
    private List<NbrlNbrlTimeSetcEntity> nbrl_time_set;
    /**文本集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_text_set", className="NbrlNbrlTextSetcEntity")
    private List<NbrlNbrlTextSetcEntity> nbrl_text_set;
    /**参数集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_param_set", className="NbrlNbrlParamSetcEntity")
    private List<NbrlNbrlParamSetcEntity> nbrl_param_set;
    /**流水号集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_serial_set", className="NbrlNbrlSerialSetcEntity")
    private List<NbrlNbrlSerialSetcEntity> nbrl_serial_set;
    /**属性类型集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="nbrl_property_type", className="NbrlNbrlPropertyTypecEntity")
    private List<NbrlNbrlPropertyTypecEntity> nbrl_property_type;

    public Integer getNbrl_item_order() {
        return nbrl_item_order;
    }

    public void setNbrl_item_order(Integer nbrl_item_order) {
        this.nbrl_item_order = nbrl_item_order;
    }

    public String getNbrl_item_type() {
        return nbrl_item_type;
    }

    public void setNbrl_item_type(String nbrl_item_type) {
        this.nbrl_item_type = nbrl_item_type;
    }

    public WordlistEntity loadNbrl_item_type() {
        String propertyName = "nbrl_item_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<NbrlNbrlTimeSetcEntity> loadNbrl_time_set() {
        String propertyName = "nbrl_time_set";
        String rootClass = "NumberrulesEntity";
        return (List<NbrlNbrlTimeSetcEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setNbrl_time_set(List<NbrlNbrlTimeSetcEntity> nbrl_time_set) {
        this.nbrl_time_set = nbrl_time_set;
    }

    public List<NbrlNbrlTimeSetcEntity> getNbrl_time_set() {
        return nbrl_time_set;
    }

    public List<NbrlNbrlTextSetcEntity> loadNbrl_text_set() {
        String propertyName = "nbrl_text_set";
        String rootClass = "NumberrulesEntity";
        return (List<NbrlNbrlTextSetcEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setNbrl_text_set(List<NbrlNbrlTextSetcEntity> nbrl_text_set) {
        this.nbrl_text_set = nbrl_text_set;
    }

    public List<NbrlNbrlTextSetcEntity> getNbrl_text_set() {
        return nbrl_text_set;
    }

    public List<NbrlNbrlParamSetcEntity> loadNbrl_param_set() {
        String propertyName = "nbrl_param_set";
        String rootClass = "NumberrulesEntity";
        return (List<NbrlNbrlParamSetcEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setNbrl_param_set(List<NbrlNbrlParamSetcEntity> nbrl_param_set) {
        this.nbrl_param_set = nbrl_param_set;
    }

    public List<NbrlNbrlParamSetcEntity> getNbrl_param_set() {
        return nbrl_param_set;
    }

    public List<NbrlNbrlSerialSetcEntity> loadNbrl_serial_set() {
        String propertyName = "nbrl_serial_set";
        String rootClass = "NumberrulesEntity";
        return (List<NbrlNbrlSerialSetcEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setNbrl_serial_set(List<NbrlNbrlSerialSetcEntity> nbrl_serial_set) {
        this.nbrl_serial_set = nbrl_serial_set;
    }

    public List<NbrlNbrlSerialSetcEntity> getNbrl_serial_set() {
        return nbrl_serial_set;
    }

    public List<NbrlNbrlPropertyTypecEntity> loadNbrl_property_type() {
        String propertyName = "nbrl_property_type";
        String rootClass = "NumberrulesEntity";
        return (List<NbrlNbrlPropertyTypecEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setNbrl_property_type(List<NbrlNbrlPropertyTypecEntity> nbrl_property_type) {
        this.nbrl_property_type = nbrl_property_type;
    }

    public List<NbrlNbrlPropertyTypecEntity> getNbrl_property_type() {
        return nbrl_property_type;
    }



}