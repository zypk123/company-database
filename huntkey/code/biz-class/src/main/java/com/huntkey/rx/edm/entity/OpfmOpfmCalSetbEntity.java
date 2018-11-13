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
public class OpfmOpfmCalSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6391328581413097L;
    
    /**变量名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_name_var", className="")
    private String opfm_name_var;
    /**数据状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_data_stat", className="")
    private Integer opfm_data_stat;
    /**统计适用值*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_val", className="")
    private String opfm_val;
    /**资源对象属性集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_opti_set", className="OpfmOpfmOptiSetcEntity")
    private List<OpfmOpfmOptiSetcEntity> opfm_opti_set;

    public String getOpfm_name_var() {
        return opfm_name_var;
    }

    public void setOpfm_name_var(String opfm_name_var) {
        this.opfm_name_var = opfm_name_var;
    }

    public Integer getOpfm_data_stat() {
        return opfm_data_stat;
    }

    public void setOpfm_data_stat(Integer opfm_data_stat) {
        this.opfm_data_stat = opfm_data_stat;
    }

    public String getOpfm_val() {
        return opfm_val;
    }

    public void setOpfm_val(String opfm_val) {
        this.opfm_val = opfm_val;
    }

    public List<OpfmOpfmOptiSetcEntity> loadOpfm_opti_set() {
        String propertyName = "opfm_opti_set";
        String rootClass = "PpidefinitionmaintEntity";
        return (List<OpfmOpfmOptiSetcEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOpfm_opti_set(List<OpfmOpfmOptiSetcEntity> opfm_opti_set) {
        this.opfm_opti_set = opfm_opti_set;
    }

    public List<OpfmOpfmOptiSetcEntity> getOpfm_opti_set() {
        return opfm_opti_set;
    }



}