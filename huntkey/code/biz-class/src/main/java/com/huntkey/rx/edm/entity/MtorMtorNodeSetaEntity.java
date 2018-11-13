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
public class MtorMtorNodeSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 2144447553490861L;
    
    /**节点编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_node_no", className="")
    private String mtor_node_no;
    /**节点名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_node_name", className="")
    private String mtor_node_name;
    /**节点定义*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_node_def", className="")
    private String mtor_node_def;
    /**主管人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_major", className="EmployeeEntity")
    private String mtor_major;
    /**协管人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_assit", className="EmployeeEntity")
    private String mtor_assit;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_beg", className="")
    private Date mtor_beg;
    /**失效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_end", className="")
    private Date mtor_end;
    /**节点排序*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_seq", className="")
    private BigDecimal mtor_seq;
    /**指标配置*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_index_conf", className="ApplicableppiEntity")
    private String mtor_index_conf;
    /**层级编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_lvl_code", className="")
    private String mtor_lvl_code;
    /**节点所在层级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_lvl", className="")
    private Integer mtor_lvl;
    /**是否枚举*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_enum", className="")
    private String mtor_enum;
    /**关联资源对象集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_res_set", className="MtorMtorResSetbEntity")
    private List<MtorMtorResSetbEntity> mtor_res_set;
    /**监管更新标记*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_type", className="")
    private Integer mtor_type;
    /**直属关联对象条件*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_relate_cnd", className="")
    private String mtor_relate_cnd;
    /**节点id*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="mtor_relate_id", className="MonitorEntity")
    private String mtor_relate_id;

    public String getMtor_node_no() {
        return mtor_node_no;
    }

    public void setMtor_node_no(String mtor_node_no) {
        this.mtor_node_no = mtor_node_no;
    }

    public String getMtor_node_name() {
        return mtor_node_name;
    }

    public void setMtor_node_name(String mtor_node_name) {
        this.mtor_node_name = mtor_node_name;
    }

    public String getMtor_node_def() {
        return mtor_node_def;
    }

    public void setMtor_node_def(String mtor_node_def) {
        this.mtor_node_def = mtor_node_def;
    }

    public String getMtor_major() {
        return mtor_major;
    }

    public void setMtor_major(String mtor_major) {
        this.mtor_major = mtor_major;
    }

    public EmployeeEntity loadMtor_major() {
        String propertyName = "mtor_major";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMtor_assit() {
        return mtor_assit;
    }

    public void setMtor_assit(String mtor_assit) {
        this.mtor_assit = mtor_assit;
    }

    public EmployeeEntity loadMtor_assit() {
        String propertyName = "mtor_assit";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getMtor_beg() {
        return mtor_beg;
    }

    public void setMtor_beg(Date mtor_beg) {
        this.mtor_beg = mtor_beg;
    }

    public Date getMtor_end() {
        return mtor_end;
    }

    public void setMtor_end(Date mtor_end) {
        this.mtor_end = mtor_end;
    }

    public BigDecimal getMtor_seq() {
        return mtor_seq;
    }

    public void setMtor_seq(BigDecimal mtor_seq) {
        this.mtor_seq = mtor_seq;
    }

    public String getMtor_index_conf() {
        return mtor_index_conf;
    }

    public void setMtor_index_conf(String mtor_index_conf) {
        this.mtor_index_conf = mtor_index_conf;
    }

    public ApplicableppiEntity loadMtor_index_conf() {
        String propertyName = "mtor_index_conf";
        return (ApplicableppiEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getMtor_lvl_code() {
        return mtor_lvl_code;
    }

    public void setMtor_lvl_code(String mtor_lvl_code) {
        this.mtor_lvl_code = mtor_lvl_code;
    }

    public Integer getMtor_lvl() {
        return mtor_lvl;
    }

    public void setMtor_lvl(Integer mtor_lvl) {
        this.mtor_lvl = mtor_lvl;
    }

    public String getMtor_enum() {
        return mtor_enum;
    }

    public void setMtor_enum(String mtor_enum) {
        this.mtor_enum = mtor_enum;
    }

    public List<MtorMtorResSetbEntity> loadMtor_res_set() {
        String propertyName = "mtor_res_set";
        String rootClass = "MonitortreeorderEntity";
        return (List<MtorMtorResSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setMtor_res_set(List<MtorMtorResSetbEntity> mtor_res_set) {
        this.mtor_res_set = mtor_res_set;
    }

    public List<MtorMtorResSetbEntity> getMtor_res_set() {
        return mtor_res_set;
    }

    public Integer getMtor_type() {
        return mtor_type;
    }

    public void setMtor_type(Integer mtor_type) {
        this.mtor_type = mtor_type;
    }

    public String getMtor_relate_cnd() {
        return mtor_relate_cnd;
    }

    public void setMtor_relate_cnd(String mtor_relate_cnd) {
        this.mtor_relate_cnd = mtor_relate_cnd;
    }

    public String getMtor_relate_id() {
        return mtor_relate_id;
    }

    public void setMtor_relate_id(String mtor_relate_id) {
        this.mtor_relate_id = mtor_relate_id;
    }

    public MonitorEntity loadMtor_relate_id() {
        String propertyName = "mtor_relate_id";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}