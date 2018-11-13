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
public class PpiaPpiaRangSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 4921304343087969L;
    
    /**所属监管类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ppia_rang_class", className="")
    private String ppia_rang_class;
    /**统计监管对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ppia_rang_id", className="")
    private String ppia_rang_id;
    /**变量名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ppia_var_name", className="")
    private String ppia_var_name;
    /**监管对象集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ppia_val_set", className="PpiaPpiaValSetbEntity")
    private List<PpiaPpiaValSetbEntity> ppia_val_set;
    /**数据状态*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="ppia_data_stat", className="")
    private Integer ppia_data_stat;

    public String getPpia_rang_class() {
        return ppia_rang_class;
    }

    public void setPpia_rang_class(String ppia_rang_class) {
        this.ppia_rang_class = ppia_rang_class;
    }

    public String getPpia_rang_id() {
        return ppia_rang_id;
    }

    public void setPpia_rang_id(String ppia_rang_id) {
        this.ppia_rang_id = ppia_rang_id;
    }

    public String getPpia_var_name() {
        return ppia_var_name;
    }

    public void setPpia_var_name(String ppia_var_name) {
        this.ppia_var_name = ppia_var_name;
    }

    public List<PpiaPpiaValSetbEntity> loadPpia_val_set() {
        String propertyName = "ppia_val_set";
        String rootClass = "PpiassignmentEntity";
        return (List<PpiaPpiaValSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPpia_val_set(List<PpiaPpiaValSetbEntity> ppia_val_set) {
        this.ppia_val_set = ppia_val_set;
    }

    public List<PpiaPpiaValSetbEntity> getPpia_val_set() {
        return ppia_val_set;
    }

    public Integer getPpia_data_stat() {
        return ppia_data_stat;
    }

    public void setPpia_data_stat(Integer ppia_data_stat) {
        this.ppia_data_stat = ppia_data_stat;
    }



}