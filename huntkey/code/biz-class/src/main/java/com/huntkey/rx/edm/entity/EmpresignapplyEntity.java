package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 离职申请单类实体
 * 
 */
public class EmpresignapplyEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 2023476201093766L;
    
    /**备注*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oera_remark", className="")
    private String oera_remark;
    /**离职明细*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oera_emp_set", className="OeraOeraEmpSetaEntity")
    private List<OeraOeraEmpSetaEntity> oera_emp_set;

    public String getOera_remark() {
        return oera_remark;
    }

    public void setOera_remark(String oera_remark) {
        this.oera_remark = oera_remark;
    }

    public List<OeraOeraEmpSetaEntity> loadOera_emp_set() {
        String propertyName = "oera_emp_set";
        String rootClass = "";
        return (List<OeraOeraEmpSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOera_emp_set(List<OeraOeraEmpSetaEntity> oera_emp_set) {
        this.oera_emp_set = oera_emp_set;
    }

    public List<OeraOeraEmpSetaEntity> getOera_emp_set() {
        return oera_emp_set;
    }



}