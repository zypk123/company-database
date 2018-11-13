package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 关联明细类实体
 * 
 */
public class RelevancedetailEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 1661945199839448L;
    
    /**所属类对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="reld_class_edmd", className="EdmEntity")
    private String reld_class_edmd;
    /**属性名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="reld_name", className="")
    private String reld_name;
    /**触发条件*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="reld_trig_cond", className="")
    private String reld_trig_cond;
    /**关联明细*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="reld_list_set", className="ReldReldListSetaEntity")
    private List<ReldReldListSetaEntity> reld_list_set;

    public String getReld_class_edmd() {
        return reld_class_edmd;
    }

    public void setReld_class_edmd(String reld_class_edmd) {
        this.reld_class_edmd = reld_class_edmd;
    }

    public EdmEntity loadReld_class_edmd() {
        String propertyName = "reld_class_edmd";
        return (EdmEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getReld_name() {
        return reld_name;
    }

    public void setReld_name(String reld_name) {
        this.reld_name = reld_name;
    }

    public String getReld_trig_cond() {
        return reld_trig_cond;
    }

    public void setReld_trig_cond(String reld_trig_cond) {
        this.reld_trig_cond = reld_trig_cond;
    }

    public List<ReldReldListSetaEntity> loadReld_list_set() {
        String propertyName = "reld_list_set";
        String rootClass = "";
        return (List<ReldReldListSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setReld_list_set(List<ReldReldListSetaEntity> reld_list_set) {
        this.reld_list_set = reld_list_set;
    }

    public List<ReldReldListSetaEntity> getReld_list_set() {
        return reld_list_set;
    }



}