package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 指标定义维护类实体
 * 
 */
public class PpidefinitionmaintEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 8024287001236133L;
    
    /**单号类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_nbr_type", className="WordlistEntity")
    private String opfm_nbr_type;
    /**变更指标定义对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_old_def", className="")
    private String opfm_old_def;
    /**单号状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_state", className="WordlistEntity")
    private String opfm_state;
    /**指标编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_code", className="")
    private String opfm_code;
    /**指标名称 */
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_name", className="")
    private String opfm_name;
    /**指标分类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_type", className="WordlistEntity")
    private String opfm_type;
    /**显示单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_unit_show", className="")
    private Object opfm_unit_show;
    /**统计单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_unit_cal", className="")
    private Object opfm_unit_cal;
    /**是否累计规划分解*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_plan_decp", className="WordlistEntity")
    private String opfm_plan_decp;
    /**是否做规划*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_plan_is", className="WordlistEntity")
    private String opfm_plan_is;
    /**判断趋势*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_dir", className="WordlistEntity")
    private String opfm_dir;
    /**定义*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_defn", className="")
    private String opfm_defn;
    /**用户定义*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_defn_user", className="")
    private String opfm_defn_user;
    /**数据来源*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_src", className="WordlistEntity")
    private String opfm_src;
    /**申请说明*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_desc", className="")
    private String opfm_desc;
    /**数据来源及通用计算方法*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_cal_desc", className="")
    private String opfm_cal_desc;
    /**责任人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_emp_duty", className="")
    private String opfm_emp_duty;
    /**责任部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_dept_duty", className="")
    private String opfm_dept_duty;
    /**协管人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_emp_asoc", className="")
    private String opfm_emp_asoc;
    /**加密*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_encr", className="WordlistEntity")
    private String opfm_encr;
    /**显示系数*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_coef", className="")
    private BigDecimal opfm_coef;
    /**排序*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_ord", className="")
    private BigDecimal opfm_ord;
    /**导入流程代码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_wf_code", className="")
    private String opfm_wf_code;
    /**相关资源类集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_res_set", className="OpfmOpfmResSetaEntity")
    private List<OpfmOpfmResSetaEntity> opfm_res_set;
    /**子流程集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_chi_set", className="OpfmOpfmChiSetaEntity")
    private List<OpfmOpfmChiSetaEntity> opfm_chi_set;

    public String getOpfm_nbr_type() {
        return opfm_nbr_type;
    }

    public void setOpfm_nbr_type(String opfm_nbr_type) {
        this.opfm_nbr_type = opfm_nbr_type;
    }

    public WordlistEntity loadOpfm_nbr_type() {
        String propertyName = "opfm_nbr_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOpfm_old_def() {
        return opfm_old_def;
    }

    public void setOpfm_old_def(String opfm_old_def) {
        this.opfm_old_def = opfm_old_def;
    }

    public String getOpfm_state() {
        return opfm_state;
    }

    public void setOpfm_state(String opfm_state) {
        this.opfm_state = opfm_state;
    }

    public WordlistEntity loadOpfm_state() {
        String propertyName = "opfm_state";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOpfm_code() {
        return opfm_code;
    }

    public void setOpfm_code(String opfm_code) {
        this.opfm_code = opfm_code;
    }

    public String getOpfm_name() {
        return opfm_name;
    }

    public void setOpfm_name(String opfm_name) {
        this.opfm_name = opfm_name;
    }

    public String getOpfm_type() {
        return opfm_type;
    }

    public void setOpfm_type(String opfm_type) {
        this.opfm_type = opfm_type;
    }

    public WordlistEntity loadOpfm_type() {
        String propertyName = "opfm_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Object getOpfm_unit_show() {
        return opfm_unit_show;
    }

    public void setOpfm_unit_show(Object opfm_unit_show) {
        this.opfm_unit_show = opfm_unit_show;
    }

    public Object getOpfm_unit_cal() {
        return opfm_unit_cal;
    }

    public void setOpfm_unit_cal(Object opfm_unit_cal) {
        this.opfm_unit_cal = opfm_unit_cal;
    }

    public String getOpfm_plan_decp() {
        return opfm_plan_decp;
    }

    public void setOpfm_plan_decp(String opfm_plan_decp) {
        this.opfm_plan_decp = opfm_plan_decp;
    }

    public WordlistEntity loadOpfm_plan_decp() {
        String propertyName = "opfm_plan_decp";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOpfm_plan_is() {
        return opfm_plan_is;
    }

    public void setOpfm_plan_is(String opfm_plan_is) {
        this.opfm_plan_is = opfm_plan_is;
    }

    public WordlistEntity loadOpfm_plan_is() {
        String propertyName = "opfm_plan_is";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOpfm_dir() {
        return opfm_dir;
    }

    public void setOpfm_dir(String opfm_dir) {
        this.opfm_dir = opfm_dir;
    }

    public WordlistEntity loadOpfm_dir() {
        String propertyName = "opfm_dir";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOpfm_defn() {
        return opfm_defn;
    }

    public void setOpfm_defn(String opfm_defn) {
        this.opfm_defn = opfm_defn;
    }

    public String getOpfm_defn_user() {
        return opfm_defn_user;
    }

    public void setOpfm_defn_user(String opfm_defn_user) {
        this.opfm_defn_user = opfm_defn_user;
    }

    public String getOpfm_src() {
        return opfm_src;
    }

    public void setOpfm_src(String opfm_src) {
        this.opfm_src = opfm_src;
    }

    public WordlistEntity loadOpfm_src() {
        String propertyName = "opfm_src";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOpfm_desc() {
        return opfm_desc;
    }

    public void setOpfm_desc(String opfm_desc) {
        this.opfm_desc = opfm_desc;
    }

    public String getOpfm_cal_desc() {
        return opfm_cal_desc;
    }

    public void setOpfm_cal_desc(String opfm_cal_desc) {
        this.opfm_cal_desc = opfm_cal_desc;
    }

    public String getOpfm_emp_duty() {
        return opfm_emp_duty;
    }

    public void setOpfm_emp_duty(String opfm_emp_duty) {
        this.opfm_emp_duty = opfm_emp_duty;
    }

    public String getOpfm_dept_duty() {
        return opfm_dept_duty;
    }

    public void setOpfm_dept_duty(String opfm_dept_duty) {
        this.opfm_dept_duty = opfm_dept_duty;
    }

    public String getOpfm_emp_asoc() {
        return opfm_emp_asoc;
    }

    public void setOpfm_emp_asoc(String opfm_emp_asoc) {
        this.opfm_emp_asoc = opfm_emp_asoc;
    }

    public String getOpfm_encr() {
        return opfm_encr;
    }

    public void setOpfm_encr(String opfm_encr) {
        this.opfm_encr = opfm_encr;
    }

    public WordlistEntity loadOpfm_encr() {
        String propertyName = "opfm_encr";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public BigDecimal getOpfm_coef() {
        return opfm_coef;
    }

    public void setOpfm_coef(BigDecimal opfm_coef) {
        this.opfm_coef = opfm_coef;
    }

    public BigDecimal getOpfm_ord() {
        return opfm_ord;
    }

    public void setOpfm_ord(BigDecimal opfm_ord) {
        this.opfm_ord = opfm_ord;
    }

    public String getOpfm_wf_code() {
        return opfm_wf_code;
    }

    public void setOpfm_wf_code(String opfm_wf_code) {
        this.opfm_wf_code = opfm_wf_code;
    }

    public List<OpfmOpfmResSetaEntity> loadOpfm_res_set() {
        String propertyName = "opfm_res_set";
        String rootClass = "";
        return (List<OpfmOpfmResSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOpfm_res_set(List<OpfmOpfmResSetaEntity> opfm_res_set) {
        this.opfm_res_set = opfm_res_set;
    }

    public List<OpfmOpfmResSetaEntity> getOpfm_res_set() {
        return opfm_res_set;
    }

    public List<OpfmOpfmChiSetaEntity> loadOpfm_chi_set() {
        String propertyName = "opfm_chi_set";
        String rootClass = "";
        return (List<OpfmOpfmChiSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOpfm_chi_set(List<OpfmOpfmChiSetaEntity> opfm_chi_set) {
        this.opfm_chi_set = opfm_chi_set;
    }

    public List<OpfmOpfmChiSetaEntity> getOpfm_chi_set() {
        return opfm_chi_set;
    }



}