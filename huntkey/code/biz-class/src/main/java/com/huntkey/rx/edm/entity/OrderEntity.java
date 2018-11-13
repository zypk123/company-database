package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 单据类实体
 * 
 */
public class OrderEntity extends EdmEntity implements Serializable {
    private static final long serialVersionUID = 9465514559632830L;
    
    /**版本号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_version", className="")
    private String orde_version;
    /**单据定义对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_rode_obj", className="OperationdocumentdefinitionEntity")
    private String orde_rode_obj;
    /**审批流程实例*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_procobj", className="ProcessEntity")
    private String orde_procobj;
    /**资源对象集合*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_res_set", className="OrdeOrdeResSetaEntity")
    private List<OrdeOrdeResSetaEntity> orde_res_set;
    /**关闭时需通知到的岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_closenotify_set", className="OrdeOrdeClosenotifySetaEntity")
    private List<OrdeOrdeClosenotifySetaEntity> orde_closenotify_set;
    /**上一版单据对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_last_version_obj", className="OrderEntity")
    private String orde_last_version_obj;
    /**更新活动岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_update_pos", className="JobpositionEntity")
    private String orde_update_pos;
    /**更新员工*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_emp", className="EmployeeEntity")
    private String orde_emp;
    /**制单岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_duty", className="JobpositionEntity")
    private String orde_duty;
    /**制单时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_date", className="")
    private Date orde_date;
    /**单据生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_effdate", className="")
    private Date orde_effdate;
    /**制单人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_adduser", className="EmployeeEntity")
    private String orde_adduser;
    /**制单代理人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_proxyuser", className="EmployeeEntity")
    private String orde_proxyuser;
    /**单据状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_status", className="WordlistEntity")
    private String orde_status;
    /**表单对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_form_obj", className="FormEntity")
    private String orde_form_obj;
    /**单据单号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="orde_nbr", className="")
    private String orde_nbr;
    /**联名提交岗位集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_jointlypost_set", className="OrdeOrdeJointlypostSetaEntity")
    private List<OrdeOrdeJointlypostSetaEntity> orde_jointlypost_set;
    /**关闭时需提交的表单*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_closecommit_set", className="OrdeOrdeClosecommitSetaEntity")
    private List<OrdeOrdeClosecommitSetaEntity> orde_closecommit_set;
    /**业务流程集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_workflow_set", className="OrdeOrdeWorkflowSetaEntity")
    private List<OrdeOrdeWorkflowSetaEntity> orde_workflow_set;
    /**制单代理岗位*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_proxyuser_pos", className="JobpositionEntity")
    private String orde_proxyuser_pos;
    /**参考单据*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_hisorder_set", className="OrdeOrdeHisorderSetaEntity")
    private List<OrdeOrdeHisorderSetaEntity> orde_hisorder_set;
    /**联名提交员工集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_jointlystaff_set", className="OrdeOrdeJointlystaffSetaEntity")
    private List<OrdeOrdeJointlystaffSetaEntity> orde_jointlystaff_set;

    public String getOrde_version() {
        return orde_version;
    }

    public void setOrde_version(String orde_version) {
        this.orde_version = orde_version;
    }

    public String getOrde_rode_obj() {
        return orde_rode_obj;
    }

    public void setOrde_rode_obj(String orde_rode_obj) {
        this.orde_rode_obj = orde_rode_obj;
    }

    public OperationdocumentdefinitionEntity loadOrde_rode_obj() {
        String propertyName = "orde_rode_obj";
        return (OperationdocumentdefinitionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOrde_procobj() {
        return orde_procobj;
    }

    public void setOrde_procobj(String orde_procobj) {
        this.orde_procobj = orde_procobj;
    }

    public ProcessEntity loadOrde_procobj() {
        String propertyName = "orde_procobj";
        return (ProcessEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<OrdeOrdeResSetaEntity> loadOrde_res_set() {
        String propertyName = "orde_res_set";
        String rootClass = "";
        return (List<OrdeOrdeResSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOrde_res_set(List<OrdeOrdeResSetaEntity> orde_res_set) {
        this.orde_res_set = orde_res_set;
    }

    public List<OrdeOrdeResSetaEntity> getOrde_res_set() {
        return orde_res_set;
    }

    public List<OrdeOrdeClosenotifySetaEntity> loadOrde_closenotify_set() {
        String propertyName = "orde_closenotify_set";
        String rootClass = "";
        return (List<OrdeOrdeClosenotifySetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOrde_closenotify_set(List<OrdeOrdeClosenotifySetaEntity> orde_closenotify_set) {
        this.orde_closenotify_set = orde_closenotify_set;
    }

    public List<OrdeOrdeClosenotifySetaEntity> getOrde_closenotify_set() {
        return orde_closenotify_set;
    }

    public String getOrde_last_version_obj() {
        return orde_last_version_obj;
    }

    public void setOrde_last_version_obj(String orde_last_version_obj) {
        this.orde_last_version_obj = orde_last_version_obj;
    }

    public OrderEntity loadOrde_last_version_obj() {
        String propertyName = "orde_last_version_obj";
        return (OrderEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOrde_update_pos() {
        return orde_update_pos;
    }

    public void setOrde_update_pos(String orde_update_pos) {
        this.orde_update_pos = orde_update_pos;
    }

    public JobpositionEntity loadOrde_update_pos() {
        String propertyName = "orde_update_pos";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOrde_emp() {
        return orde_emp;
    }

    public void setOrde_emp(String orde_emp) {
        this.orde_emp = orde_emp;
    }

    public EmployeeEntity loadOrde_emp() {
        String propertyName = "orde_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOrde_duty() {
        return orde_duty;
    }

    public void setOrde_duty(String orde_duty) {
        this.orde_duty = orde_duty;
    }

    public JobpositionEntity loadOrde_duty() {
        String propertyName = "orde_duty";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getOrde_date() {
        return orde_date;
    }

    public void setOrde_date(Date orde_date) {
        this.orde_date = orde_date;
    }

    public Date getOrde_effdate() {
        return orde_effdate;
    }

    public void setOrde_effdate(Date orde_effdate) {
        this.orde_effdate = orde_effdate;
    }

    public String getOrde_adduser() {
        return orde_adduser;
    }

    public void setOrde_adduser(String orde_adduser) {
        this.orde_adduser = orde_adduser;
    }

    public EmployeeEntity loadOrde_adduser() {
        String propertyName = "orde_adduser";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOrde_proxyuser() {
        return orde_proxyuser;
    }

    public void setOrde_proxyuser(String orde_proxyuser) {
        this.orde_proxyuser = orde_proxyuser;
    }

    public EmployeeEntity loadOrde_proxyuser() {
        String propertyName = "orde_proxyuser";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOrde_status() {
        return orde_status;
    }

    public void setOrde_status(String orde_status) {
        this.orde_status = orde_status;
    }

    public WordlistEntity loadOrde_status() {
        String propertyName = "orde_status";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOrde_form_obj() {
        return orde_form_obj;
    }

    public void setOrde_form_obj(String orde_form_obj) {
        this.orde_form_obj = orde_form_obj;
    }

    public FormEntity loadOrde_form_obj() {
        String propertyName = "orde_form_obj";
        return (FormEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOrde_nbr() {
        return orde_nbr;
    }

    public void setOrde_nbr(String orde_nbr) {
        this.orde_nbr = orde_nbr;
    }

    public List<OrdeOrdeJointlypostSetaEntity> loadOrde_jointlypost_set() {
        String propertyName = "orde_jointlypost_set";
        String rootClass = "";
        return (List<OrdeOrdeJointlypostSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOrde_jointlypost_set(List<OrdeOrdeJointlypostSetaEntity> orde_jointlypost_set) {
        this.orde_jointlypost_set = orde_jointlypost_set;
    }

    public List<OrdeOrdeJointlypostSetaEntity> getOrde_jointlypost_set() {
        return orde_jointlypost_set;
    }

    public List<OrdeOrdeClosecommitSetaEntity> loadOrde_closecommit_set() {
        String propertyName = "orde_closecommit_set";
        String rootClass = "";
        return (List<OrdeOrdeClosecommitSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOrde_closecommit_set(List<OrdeOrdeClosecommitSetaEntity> orde_closecommit_set) {
        this.orde_closecommit_set = orde_closecommit_set;
    }

    public List<OrdeOrdeClosecommitSetaEntity> getOrde_closecommit_set() {
        return orde_closecommit_set;
    }

    public List<OrdeOrdeWorkflowSetaEntity> loadOrde_workflow_set() {
        String propertyName = "orde_workflow_set";
        String rootClass = "";
        return (List<OrdeOrdeWorkflowSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOrde_workflow_set(List<OrdeOrdeWorkflowSetaEntity> orde_workflow_set) {
        this.orde_workflow_set = orde_workflow_set;
    }

    public List<OrdeOrdeWorkflowSetaEntity> getOrde_workflow_set() {
        return orde_workflow_set;
    }

    public String getOrde_proxyuser_pos() {
        return orde_proxyuser_pos;
    }

    public void setOrde_proxyuser_pos(String orde_proxyuser_pos) {
        this.orde_proxyuser_pos = orde_proxyuser_pos;
    }

    public JobpositionEntity loadOrde_proxyuser_pos() {
        String propertyName = "orde_proxyuser_pos";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<OrdeOrdeHisorderSetaEntity> loadOrde_hisorder_set() {
        String propertyName = "orde_hisorder_set";
        String rootClass = "";
        return (List<OrdeOrdeHisorderSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOrde_hisorder_set(List<OrdeOrdeHisorderSetaEntity> orde_hisorder_set) {
        this.orde_hisorder_set = orde_hisorder_set;
    }

    public List<OrdeOrdeHisorderSetaEntity> getOrde_hisorder_set() {
        return orde_hisorder_set;
    }

    public List<OrdeOrdeJointlystaffSetaEntity> loadOrde_jointlystaff_set() {
        String propertyName = "orde_jointlystaff_set";
        String rootClass = "";
        return (List<OrdeOrdeJointlystaffSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOrde_jointlystaff_set(List<OrdeOrdeJointlystaffSetaEntity> orde_jointlystaff_set) {
        this.orde_jointlystaff_set = orde_jointlystaff_set;
    }

    public List<OrdeOrdeJointlystaffSetaEntity> getOrde_jointlystaff_set() {
        return orde_jointlystaff_set;
    }



}