package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 部门责任人任免单类实体
 * 
 */
public class DeptchargerapplyorderEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 139626498553087L;
    
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odcs_beg", className="")
    private Date odcs_beg;
    /**备注*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odcs_remark", className="")
    private String odcs_remark;
    /**责任人信息*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odcs_chrg_set", className="OdcsOdcsChrgSetaEntity")
    private List<OdcsOdcsChrgSetaEntity> odcs_chrg_set;
    /**起始部门*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="odcs_dept_beg", className="DepttreeEntity")
    private String odcs_dept_beg;

    public Date getOdcs_beg() {
        return odcs_beg;
    }

    public void setOdcs_beg(Date odcs_beg) {
        this.odcs_beg = odcs_beg;
    }

    public String getOdcs_remark() {
        return odcs_remark;
    }

    public void setOdcs_remark(String odcs_remark) {
        this.odcs_remark = odcs_remark;
    }

    public List<OdcsOdcsChrgSetaEntity> loadOdcs_chrg_set() {
        String propertyName = "odcs_chrg_set";
        String rootClass = "";
        return (List<OdcsOdcsChrgSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOdcs_chrg_set(List<OdcsOdcsChrgSetaEntity> odcs_chrg_set) {
        this.odcs_chrg_set = odcs_chrg_set;
    }

    public List<OdcsOdcsChrgSetaEntity> getOdcs_chrg_set() {
        return odcs_chrg_set;
    }

    public String getOdcs_dept_beg() {
        return odcs_dept_beg;
    }

    public void setOdcs_dept_beg(String odcs_dept_beg) {
        this.odcs_dept_beg = odcs_dept_beg;
    }

    public DepttreeEntity loadOdcs_dept_beg() {
        String propertyName = "odcs_dept_beg";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}