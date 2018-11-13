package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class AcinAcinResourceSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 858779249620069L;
    
    /**可见数据类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="acin_rdataclass", className="ResourceEntity")
    private String acin_rdataclass;
    /**授权岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="acin_rduty", className="JobpositionEntity")
    private String acin_rduty;
    /**授权人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="acin_rauthorizer", className="EmployeeEntity")
    private String acin_rauthorizer;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="acin_risempower", className="")
    private String acin_risempower;
    /**授权时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="acin_rdate", className="")
    private Date acin_rdate;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="acin_rbeg", className="")
    private Date acin_rbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="acin_rend", className="")
    private Date acin_rend;
    /**检索限定条件*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="acin_rrow_con", className="")
    private String acin_rrow_con;
    /**属性可阅性控制*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="acin_attr_set", className="AcinAcinAttrSetbEntity")
    private List<AcinAcinAttrSetbEntity> acin_attr_set;

    public String getAcin_rdataclass() {
        return acin_rdataclass;
    }

    public void setAcin_rdataclass(String acin_rdataclass) {
        this.acin_rdataclass = acin_rdataclass;
    }

    public ResourceEntity loadAcin_rdataclass() {
        String propertyName = "acin_rdataclass";
        return (ResourceEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAcin_rduty() {
        return acin_rduty;
    }

    public void setAcin_rduty(String acin_rduty) {
        this.acin_rduty = acin_rduty;
    }

    public JobpositionEntity loadAcin_rduty() {
        String propertyName = "acin_rduty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAcin_rauthorizer() {
        return acin_rauthorizer;
    }

    public void setAcin_rauthorizer(String acin_rauthorizer) {
        this.acin_rauthorizer = acin_rauthorizer;
    }

    public EmployeeEntity loadAcin_rauthorizer() {
        String propertyName = "acin_rauthorizer";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getAcin_risempower() {
        return acin_risempower;
    }

    public void setAcin_risempower(String acin_risempower) {
        this.acin_risempower = acin_risempower;
    }

    public Date getAcin_rdate() {
        return acin_rdate;
    }

    public void setAcin_rdate(Date acin_rdate) {
        this.acin_rdate = acin_rdate;
    }

    public Date getAcin_rbeg() {
        return acin_rbeg;
    }

    public void setAcin_rbeg(Date acin_rbeg) {
        this.acin_rbeg = acin_rbeg;
    }

    public Date getAcin_rend() {
        return acin_rend;
    }

    public void setAcin_rend(Date acin_rend) {
        this.acin_rend = acin_rend;
    }

    public String getAcin_rrow_con() {
        return acin_rrow_con;
    }

    public void setAcin_rrow_con(String acin_rrow_con) {
        this.acin_rrow_con = acin_rrow_con;
    }

    public List<AcinAcinAttrSetbEntity> loadAcin_attr_set() {
        String propertyName = "acin_attr_set";
        String rootClass = "AccessibleinformationEntity";
        return (List<AcinAcinAttrSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setAcin_attr_set(List<AcinAcinAttrSetbEntity> acin_attr_set) {
        this.acin_attr_set = acin_attr_set;
    }

    public List<AcinAcinAttrSetbEntity> getAcin_attr_set() {
        return acin_attr_set;
    }



}