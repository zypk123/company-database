package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import com.huntkey.rx.base.PropertyBaseEntity;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class MoniMoniHisSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 994322915988663L;
    
    /**节点编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hnode_no", className="")
    private String moni_hnode_no;
    /**节点名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hnode_name", className="")
    private String moni_hnode_name;
    /**节点定义*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hnode_def", className="")
    private String moni_hnode_def;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hbeg", className="")
    private Date moni_hbeg;
    /**失效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hend", className="")
    private Date moni_hend;
    /**指标配置*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hindex_conf", className="ApplicableppiEntity")
    private String moni_hindex_conf;
    /**层级编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hlvl_code", className="")
    private String moni_hlvl_code;
    /**节点所在层级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hlvl", className="")
    private Integer moni_hlvl;
    /**关联历史资源对象集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hres_set", className="MoniMoniHresSetbEntity")
    private List<MoniMoniHresSetbEntity> moni_hres_set;
    /**直属关联对象条件*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hrelate_cnd", className="")
    private String moni_hrelate_cnd;
    /**是否枚举*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_henum", className="")
    private String moni_henum;
    /**主管人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hmajor", className="EmployeeEntity")
    private String moni_hmajor;
    /**协管人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hassit", className="EmployeeEntity")
    private String moni_hassit;
    /**节点排序*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_hseq", className="")
    private BigDecimal moni_hseq;

    public String getMoni_hnode_no() {
        return moni_hnode_no;
    }

    public void setMoni_hnode_no(String moni_hnode_no) {
        this.moni_hnode_no = moni_hnode_no;
    }

    public String getMoni_hnode_name() {
        return moni_hnode_name;
    }

    public void setMoni_hnode_name(String moni_hnode_name) {
        this.moni_hnode_name = moni_hnode_name;
    }

    public String getMoni_hnode_def() {
        return moni_hnode_def;
    }

    public void setMoni_hnode_def(String moni_hnode_def) {
        this.moni_hnode_def = moni_hnode_def;
    }

    public Date getMoni_hbeg() {
        return moni_hbeg;
    }

    public void setMoni_hbeg(Date moni_hbeg) {
        this.moni_hbeg = moni_hbeg;
    }

    public Date getMoni_hend() {
        return moni_hend;
    }

    public void setMoni_hend(Date moni_hend) {
        this.moni_hend = moni_hend;
    }

    public String getMoni_hindex_conf() {
        return moni_hindex_conf;
    }

    public void setMoni_hindex_conf(String moni_hindex_conf) {
        this.moni_hindex_conf = moni_hindex_conf;
    }

    public ApplicableppiEntity loadMoni_hindex_conf() {
        String propertyName = "moni_hindex_conf";
        return (ApplicableppiEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMoni_hlvl_code() {
        return moni_hlvl_code;
    }

    public void setMoni_hlvl_code(String moni_hlvl_code) {
        this.moni_hlvl_code = moni_hlvl_code;
    }

    public Integer getMoni_hlvl() {
        return moni_hlvl;
    }

    public void setMoni_hlvl(Integer moni_hlvl) {
        this.moni_hlvl = moni_hlvl;
    }

    public List<MoniMoniHresSetbEntity> loadMoni_hres_set() {
        String propertyName = "moni_hres_set";
        String rootClass = "MonitorEntity";
        return (List<MoniMoniHresSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setMoni_hres_set(List<MoniMoniHresSetbEntity> moni_hres_set) {
        this.moni_hres_set = moni_hres_set;
    }

    public List<MoniMoniHresSetbEntity> getMoni_hres_set() {
        return moni_hres_set;
    }

    public String getMoni_hrelate_cnd() {
        return moni_hrelate_cnd;
    }

    public void setMoni_hrelate_cnd(String moni_hrelate_cnd) {
        this.moni_hrelate_cnd = moni_hrelate_cnd;
    }

    public String getMoni_henum() {
        return moni_henum;
    }

    public void setMoni_henum(String moni_henum) {
        this.moni_henum = moni_henum;
    }

    public String getMoni_hmajor() {
        return moni_hmajor;
    }

    public void setMoni_hmajor(String moni_hmajor) {
        this.moni_hmajor = moni_hmajor;
    }

    public EmployeeEntity loadMoni_hmajor() {
        String propertyName = "moni_hmajor";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMoni_hassit() {
        return moni_hassit;
    }

    public void setMoni_hassit(String moni_hassit) {
        this.moni_hassit = moni_hassit;
    }

    public EmployeeEntity loadMoni_hassit() {
        String propertyName = "moni_hassit";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public BigDecimal getMoni_hseq() {
        return moni_hseq;
    }

    public void setMoni_hseq(BigDecimal moni_hseq) {
        this.moni_hseq = moni_hseq;
    }



}