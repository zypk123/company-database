package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 指标定义类实体
 * 
 */
public class PpiformulaEntity extends KnowledgeEntity implements Serializable {
    private static final long serialVersionUID = 920626083618095L;
    
    /**数据来源及通用计算法*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_cal_desc", className="")
    private String rpif_cal_desc;
    /**申请说明*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_desc", className="")
    private String rpif_desc;
    /**指标状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_stat", className="WordlistEntity")
    private String rpif_stat;
    /**公式配置状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_cal_stat", className="WordlistEntity")
    private String rpif_cal_stat;
    /**绩效指标配置状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_ipin_stat", className="WordlistEntity")
    private String rpif_ipin_stat;
    /**指标编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_code", className="")
    private String rpif_code;
    /**指标名称 */
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_name", className="")
    private String rpif_name;
    /**指标定义单据id*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_opfm_id", className="")
    private String rpif_opfm_id;
    /**指标类别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_type", className="WordlistEntity")
    private String rpif_type;
    /**显示单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_unit_show", className="")
    private Object rpif_unit_show;
    /**基本单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_unit_cal", className="")
    private Object rpif_unit_cal;
    /**累计方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_plan_decp", className="WordlistEntity")
    private String rpif_plan_decp;
    /**判断趋势*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_dir", className="WordlistEntity")
    private String rpif_dir;
    /**系统定义*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_defn", className="")
    private String rpif_defn;
    /**用户定义及管理应用*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_defn_user", className="")
    private String rpif_defn_user;
    /**数据来源*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_src", className="WordlistEntity")
    private String rpif_src;
    /**责任人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_emp_duty", className="EmployeeEntity")
    private String rpif_emp_duty;
    /**管理部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_dept_duty", className="DepttreeEntity")
    private String rpif_dept_duty;
    /**保密密级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_encr", className="WordlistEntity")
    private String rpif_encr;
    /**显示系数*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_coef", className="")
    private BigDecimal rpif_coef;
    /**排序*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_ord", className="")
    private Integer rpif_ord;
    /**导入流程*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_wf_code", className="")
    private String rpif_wf_code;
    /**适用周期集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_time_set", className="RpifRpifTimeSetaEntity")
    private List<RpifRpifTimeSetaEntity> rpif_time_set;
    /**公式中涉及的变量集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_cal_set", className="RpifRpifCalSetaEntity")
    private List<RpifRpifCalSetaEntity> rpif_cal_set;
    /**关联资源集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="rpif_rel_res_set", className="RpifRpifRelResSetaEntity")
    private List<RpifRpifRelResSetaEntity> rpif_rel_res_set;

    public String getRpif_cal_desc() {
        return rpif_cal_desc;
    }

    public void setRpif_cal_desc(String rpif_cal_desc) {
        this.rpif_cal_desc = rpif_cal_desc;
    }

    public String getRpif_desc() {
        return rpif_desc;
    }

    public void setRpif_desc(String rpif_desc) {
        this.rpif_desc = rpif_desc;
    }

    public String getRpif_stat() {
        return rpif_stat;
    }

    public void setRpif_stat(String rpif_stat) {
        this.rpif_stat = rpif_stat;
    }

    public WordlistEntity loadRpif_stat() {
        String propertyName = "rpif_stat";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpif_cal_stat() {
        return rpif_cal_stat;
    }

    public void setRpif_cal_stat(String rpif_cal_stat) {
        this.rpif_cal_stat = rpif_cal_stat;
    }

    public WordlistEntity loadRpif_cal_stat() {
        String propertyName = "rpif_cal_stat";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpif_ipin_stat() {
        return rpif_ipin_stat;
    }

    public void setRpif_ipin_stat(String rpif_ipin_stat) {
        this.rpif_ipin_stat = rpif_ipin_stat;
    }

    public WordlistEntity loadRpif_ipin_stat() {
        String propertyName = "rpif_ipin_stat";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpif_code() {
        return rpif_code;
    }

    public void setRpif_code(String rpif_code) {
        this.rpif_code = rpif_code;
    }

    public String getRpif_name() {
        return rpif_name;
    }

    public void setRpif_name(String rpif_name) {
        this.rpif_name = rpif_name;
    }

    public String getRpif_opfm_id() {
        return rpif_opfm_id;
    }

    public void setRpif_opfm_id(String rpif_opfm_id) {
        this.rpif_opfm_id = rpif_opfm_id;
    }

    public String getRpif_type() {
        return rpif_type;
    }

    public void setRpif_type(String rpif_type) {
        this.rpif_type = rpif_type;
    }

    public WordlistEntity loadRpif_type() {
        String propertyName = "rpif_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Object getRpif_unit_show() {
        return rpif_unit_show;
    }

    public void setRpif_unit_show(Object rpif_unit_show) {
        this.rpif_unit_show = rpif_unit_show;
    }

    public Object getRpif_unit_cal() {
        return rpif_unit_cal;
    }

    public void setRpif_unit_cal(Object rpif_unit_cal) {
        this.rpif_unit_cal = rpif_unit_cal;
    }

    public String getRpif_plan_decp() {
        return rpif_plan_decp;
    }

    public void setRpif_plan_decp(String rpif_plan_decp) {
        this.rpif_plan_decp = rpif_plan_decp;
    }

    public WordlistEntity loadRpif_plan_decp() {
        String propertyName = "rpif_plan_decp";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpif_dir() {
        return rpif_dir;
    }

    public void setRpif_dir(String rpif_dir) {
        this.rpif_dir = rpif_dir;
    }

    public WordlistEntity loadRpif_dir() {
        String propertyName = "rpif_dir";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpif_defn() {
        return rpif_defn;
    }

    public void setRpif_defn(String rpif_defn) {
        this.rpif_defn = rpif_defn;
    }

    public String getRpif_defn_user() {
        return rpif_defn_user;
    }

    public void setRpif_defn_user(String rpif_defn_user) {
        this.rpif_defn_user = rpif_defn_user;
    }

    public String getRpif_src() {
        return rpif_src;
    }

    public void setRpif_src(String rpif_src) {
        this.rpif_src = rpif_src;
    }

    public WordlistEntity loadRpif_src() {
        String propertyName = "rpif_src";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpif_emp_duty() {
        return rpif_emp_duty;
    }

    public void setRpif_emp_duty(String rpif_emp_duty) {
        this.rpif_emp_duty = rpif_emp_duty;
    }

    public EmployeeEntity loadRpif_emp_duty() {
        String propertyName = "rpif_emp_duty";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpif_dept_duty() {
        return rpif_dept_duty;
    }

    public void setRpif_dept_duty(String rpif_dept_duty) {
        this.rpif_dept_duty = rpif_dept_duty;
    }

    public DepttreeEntity loadRpif_dept_duty() {
        String propertyName = "rpif_dept_duty";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRpif_encr() {
        return rpif_encr;
    }

    public void setRpif_encr(String rpif_encr) {
        this.rpif_encr = rpif_encr;
    }

    public WordlistEntity loadRpif_encr() {
        String propertyName = "rpif_encr";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public BigDecimal getRpif_coef() {
        return rpif_coef;
    }

    public void setRpif_coef(BigDecimal rpif_coef) {
        this.rpif_coef = rpif_coef;
    }

    public Integer getRpif_ord() {
        return rpif_ord;
    }

    public void setRpif_ord(Integer rpif_ord) {
        this.rpif_ord = rpif_ord;
    }

    public String getRpif_wf_code() {
        return rpif_wf_code;
    }

    public void setRpif_wf_code(String rpif_wf_code) {
        this.rpif_wf_code = rpif_wf_code;
    }

    public List<RpifRpifTimeSetaEntity> loadRpif_time_set() {
        String propertyName = "rpif_time_set";
        String rootClass = "";
        return (List<RpifRpifTimeSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRpif_time_set(List<RpifRpifTimeSetaEntity> rpif_time_set) {
        this.rpif_time_set = rpif_time_set;
    }

    public List<RpifRpifTimeSetaEntity> getRpif_time_set() {
        return rpif_time_set;
    }

    public List<RpifRpifCalSetaEntity> loadRpif_cal_set() {
        String propertyName = "rpif_cal_set";
        String rootClass = "";
        return (List<RpifRpifCalSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRpif_cal_set(List<RpifRpifCalSetaEntity> rpif_cal_set) {
        this.rpif_cal_set = rpif_cal_set;
    }

    public List<RpifRpifCalSetaEntity> getRpif_cal_set() {
        return rpif_cal_set;
    }

    public List<RpifRpifRelResSetaEntity> loadRpif_rel_res_set() {
        String propertyName = "rpif_rel_res_set";
        String rootClass = "";
        return (List<RpifRpifRelResSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRpif_rel_res_set(List<RpifRpifRelResSetaEntity> rpif_rel_res_set) {
        this.rpif_rel_res_set = rpif_rel_res_set;
    }

    public List<RpifRpifRelResSetaEntity> getRpif_rel_res_set() {
        return rpif_rel_res_set;
    }



}