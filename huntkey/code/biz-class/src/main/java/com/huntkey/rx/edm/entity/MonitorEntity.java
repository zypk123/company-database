package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 监管类实体
 * 
 */
public class MonitorEntity extends EdmEntity implements Serializable {
    private static final long serialVersionUID = 2031866650133541L;
    
    /**所属资源类*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="moni_resource_class", className="")
    private String moni_resource_class;
    /**节点排序*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_seq", className="")
    private BigDecimal moni_seq;
    /**节点编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_node_no", className="")
    private String moni_node_no;
    /**节点名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_node_name", className="")
    private String moni_node_name;
    /**节点定义*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_node_def", className="")
    private String moni_node_def;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_beg", className="")
    private Date moni_beg;
    /**失效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_end", className="")
    private Date moni_end;
    /**指标配置*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_index_conf", className="ApplicableppiEntity")
    private String moni_index_conf;
    /**层级编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_lvl_code", className="")
    private String moni_lvl_code;
    /**节点所在层级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_lvl", className="")
    private Integer moni_lvl;
    /**关联资源对象集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_res_set", className="MoniMoniResSetaEntity")
    private List<MoniMoniResSetaEntity> moni_res_set;
    /**直属关联对象条件*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_relate_cnd", className="")
    private String moni_relate_cnd;
    /**是否枚举*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_enum", className="")
    private String moni_enum;
    /**主管人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_major", className="EmployeeEntity")
    private String moni_major;
    /**协管人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_assit", className="EmployeeEntity")
    private String moni_assit;
    /**历史节点集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="moni_his_set", className="MoniMoniHisSetaEntity")
    private List<MoniMoniHisSetaEntity> moni_his_set;

    public String getMoni_resource_class() {
        return moni_resource_class;
    }

    public void setMoni_resource_class(String moni_resource_class) {
        this.moni_resource_class = moni_resource_class;
    }

    public BigDecimal getMoni_seq() {
        return moni_seq;
    }

    public void setMoni_seq(BigDecimal moni_seq) {
        this.moni_seq = moni_seq;
    }

    public String getMoni_node_no() {
        return moni_node_no;
    }

    public void setMoni_node_no(String moni_node_no) {
        this.moni_node_no = moni_node_no;
    }

    public String getMoni_node_name() {
        return moni_node_name;
    }

    public void setMoni_node_name(String moni_node_name) {
        this.moni_node_name = moni_node_name;
    }

    public String getMoni_node_def() {
        return moni_node_def;
    }

    public void setMoni_node_def(String moni_node_def) {
        this.moni_node_def = moni_node_def;
    }

    public Date getMoni_beg() {
        return moni_beg;
    }

    public void setMoni_beg(Date moni_beg) {
        this.moni_beg = moni_beg;
    }

    public Date getMoni_end() {
        return moni_end;
    }

    public void setMoni_end(Date moni_end) {
        this.moni_end = moni_end;
    }

    public String getMoni_index_conf() {
        return moni_index_conf;
    }

    public void setMoni_index_conf(String moni_index_conf) {
        this.moni_index_conf = moni_index_conf;
    }

    public ApplicableppiEntity loadMoni_index_conf() {
        String propertyName = "moni_index_conf";
        return (ApplicableppiEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMoni_lvl_code() {
        return moni_lvl_code;
    }

    public void setMoni_lvl_code(String moni_lvl_code) {
        this.moni_lvl_code = moni_lvl_code;
    }

    public Integer getMoni_lvl() {
        return moni_lvl;
    }

    public void setMoni_lvl(Integer moni_lvl) {
        this.moni_lvl = moni_lvl;
    }

    public List<MoniMoniResSetaEntity> loadMoni_res_set() {
        String propertyName = "moni_res_set";
        String rootClass = "";
        return (List<MoniMoniResSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setMoni_res_set(List<MoniMoniResSetaEntity> moni_res_set) {
        this.moni_res_set = moni_res_set;
    }

    public List<MoniMoniResSetaEntity> getMoni_res_set() {
        return moni_res_set;
    }

    public String getMoni_relate_cnd() {
        return moni_relate_cnd;
    }

    public void setMoni_relate_cnd(String moni_relate_cnd) {
        this.moni_relate_cnd = moni_relate_cnd;
    }

    public String getMoni_enum() {
        return moni_enum;
    }

    public void setMoni_enum(String moni_enum) {
        this.moni_enum = moni_enum;
    }

    public String getMoni_major() {
        return moni_major;
    }

    public void setMoni_major(String moni_major) {
        this.moni_major = moni_major;
    }

    public EmployeeEntity loadMoni_major() {
        String propertyName = "moni_major";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMoni_assit() {
        return moni_assit;
    }

    public void setMoni_assit(String moni_assit) {
        this.moni_assit = moni_assit;
    }

    public EmployeeEntity loadMoni_assit() {
        String propertyName = "moni_assit";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<MoniMoniHisSetaEntity> loadMoni_his_set() {
        String propertyName = "moni_his_set";
        String rootClass = "";
        return (List<MoniMoniHisSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setMoni_his_set(List<MoniMoniHisSetaEntity> moni_his_set) {
        this.moni_his_set = moni_his_set;
    }

    public List<MoniMoniHisSetaEntity> getMoni_his_set() {
        return moni_his_set;
    }



}