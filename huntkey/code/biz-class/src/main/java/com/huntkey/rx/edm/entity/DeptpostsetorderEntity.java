package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 部门岗位设置单类实体
 * 
 */
public class DeptpostsetorderEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 3154788370975838L;
    
    /**起始部门*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="odps_dept_beg", className="DepttreeEntity")
    private String odps_dept_beg;
    /**单据类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_type", className="WordlistEntity")
    private String odps_type;
    /**备注*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_remark", className="")
    private String odps_remark;
    /**岗位信息*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_dpost_set", className="OdpsOdpsDpostSetaEntity")
    private List<OdpsOdpsDpostSetaEntity> odps_dpost_set;
    /**生效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="odps_beg", className="")
    private Date odps_beg;

    public String getOdps_dept_beg() {
        return odps_dept_beg;
    }

    public void setOdps_dept_beg(String odps_dept_beg) {
        this.odps_dept_beg = odps_dept_beg;
    }

    public DepttreeEntity loadOdps_dept_beg() {
        String propertyName = "odps_dept_beg";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdps_type() {
        return odps_type;
    }

    public void setOdps_type(String odps_type) {
        this.odps_type = odps_type;
    }

    public WordlistEntity loadOdps_type() {
        String propertyName = "odps_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOdps_remark() {
        return odps_remark;
    }

    public void setOdps_remark(String odps_remark) {
        this.odps_remark = odps_remark;
    }

    public List<OdpsOdpsDpostSetaEntity> loadOdps_dpost_set() {
        String propertyName = "odps_dpost_set";
        String rootClass = "";
        return (List<OdpsOdpsDpostSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOdps_dpost_set(List<OdpsOdpsDpostSetaEntity> odps_dpost_set) {
        this.odps_dpost_set = odps_dpost_set;
    }

    public List<OdpsOdpsDpostSetaEntity> getOdps_dpost_set() {
        return odps_dpost_set;
    }

    public Date getOdps_beg() {
        return odps_beg;
    }

    public void setOdps_beg(Date odps_beg) {
        this.odps_beg = odps_beg;
    }



}