package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 员工岗位调整单类实体
 * 
 */
public class EmppostchangeapplyEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 6048234205460054L;
    
    /**生效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepc_beg", className="")
    private Date oepc_beg;
    /**备注*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepc_remark", className="")
    private String oepc_remark;
    /**单据类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepc_type", className="WordlistEntity")
    private String oepc_type;
    /**调整清单*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oepc_chang_set", className="OepcOepcChangSetaEntity")
    private List<OepcOepcChangSetaEntity> oepc_chang_set;

    public Date getOepc_beg() {
        return oepc_beg;
    }

    public void setOepc_beg(Date oepc_beg) {
        this.oepc_beg = oepc_beg;
    }

    public String getOepc_remark() {
        return oepc_remark;
    }

    public void setOepc_remark(String oepc_remark) {
        this.oepc_remark = oepc_remark;
    }

    public String getOepc_type() {
        return oepc_type;
    }

    public void setOepc_type(String oepc_type) {
        this.oepc_type = oepc_type;
    }

    public WordlistEntity loadOepc_type() {
        String propertyName = "oepc_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<OepcOepcChangSetaEntity> loadOepc_chang_set() {
        String propertyName = "oepc_chang_set";
        String rootClass = "";
        return (List<OepcOepcChangSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOepc_chang_set(List<OepcOepcChangSetaEntity> oepc_chang_set) {
        this.oepc_chang_set = oepc_chang_set;
    }

    public List<OepcOepcChangSetaEntity> getOepc_chang_set() {
        return oepc_chang_set;
    }



}