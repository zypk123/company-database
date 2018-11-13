package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 合同签订单类实体
 * 
 */
public class ContractsignapplyEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 5862515171343624L;
    
    /**部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ocso_dept", className="DepttreeEntity")
    private String ocso_dept;
    /**签约法人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ocso_mcop", className="RelationEntity")
    private String ocso_mcop;
    /**签约类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ocso_type", className="WordlistEntity")
    private String ocso_type;
    /**签订日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ocso_sign_date", className="")
    private Date ocso_sign_date;
    /**签约人员*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ocso_emp_set", className="OcsoOcsoEmpSetaEntity")
    private List<OcsoOcsoEmpSetaEntity> ocso_emp_set;

    public String getOcso_dept() {
        return ocso_dept;
    }

    public void setOcso_dept(String ocso_dept) {
        this.ocso_dept = ocso_dept;
    }

    public DepttreeEntity loadOcso_dept() {
        String propertyName = "ocso_dept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOcso_mcop() {
        return ocso_mcop;
    }

    public void setOcso_mcop(String ocso_mcop) {
        this.ocso_mcop = ocso_mcop;
    }

    public RelationEntity loadOcso_mcop() {
        String propertyName = "ocso_mcop";
        return (RelationEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOcso_type() {
        return ocso_type;
    }

    public void setOcso_type(String ocso_type) {
        this.ocso_type = ocso_type;
    }

    public WordlistEntity loadOcso_type() {
        String propertyName = "ocso_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getOcso_sign_date() {
        return ocso_sign_date;
    }

    public void setOcso_sign_date(Date ocso_sign_date) {
        this.ocso_sign_date = ocso_sign_date;
    }

    public List<OcsoOcsoEmpSetaEntity> loadOcso_emp_set() {
        String propertyName = "ocso_emp_set";
        String rootClass = "";
        return (List<OcsoOcsoEmpSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOcso_emp_set(List<OcsoOcsoEmpSetaEntity> ocso_emp_set) {
        this.ocso_emp_set = ocso_emp_set;
    }

    public List<OcsoOcsoEmpSetaEntity> getOcso_emp_set() {
        return ocso_emp_set;
    }



}