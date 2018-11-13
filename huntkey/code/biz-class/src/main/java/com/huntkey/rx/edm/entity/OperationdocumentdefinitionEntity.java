package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 单据定义类实体
 * 
 */
public class OperationdocumentdefinitionEntity extends KnowledgeEntity implements Serializable {
    private static final long serialVersionUID = 2811804706127477L;
    
    /**单据描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oddf_desc", className="")
    private String oddf_desc;
    /**审批流程定义*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="oddf_appr_flow_def", className="ProcesspatternEntity")
    private String oddf_appr_flow_def;
    /**版本编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oddf_version_num", className="")
    private String oddf_version_num;
    /**自定义表单对象*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="oddf_def_form", className="FormEntity")
    private String oddf_def_form;
    /**单据分类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oddf_class", className="")
    private String oddf_class;
    /**单据类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oddf_orde_class", className="")
    private String oddf_orde_class;
    /**系统表单对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oddf_sys_form", className="SystemformEntity")
    private String oddf_sys_form;
    /**单据定义类名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oddf_name", className="")
    private String oddf_name;
    /**关闭时需要通知到的岗位定位公式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oddf_close_pos", className="")
    private String oddf_close_pos;
    /**单据定义编码*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="oddf_code", className="")
    private String oddf_code;
    /**供审核活动参考的历史单据*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="oddf_ref_orders", className="OddfOddfRefOrdersaEntity")
    private List<OddfOddfRefOrdersaEntity> oddf_ref_orders;
    /**单据定义状态*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="oddf_status", className="WordlistEntity")
    private String oddf_status;
    /**资源对象集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="oddf_res_objs", className="OddfOddfResObjsaEntity")
    private List<OddfOddfResObjsaEntity> oddf_res_objs;
    /**上一版本单据定义对象*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="oddf_prev_version_obj", className="OperationdocumentdefinitionEntity")
    private String oddf_prev_version_obj;

    public String getOddf_desc() {
        return oddf_desc;
    }

    public void setOddf_desc(String oddf_desc) {
        this.oddf_desc = oddf_desc;
    }

    public String getOddf_appr_flow_def() {
        return oddf_appr_flow_def;
    }

    public void setOddf_appr_flow_def(String oddf_appr_flow_def) {
        this.oddf_appr_flow_def = oddf_appr_flow_def;
    }

    public ProcesspatternEntity loadOddf_appr_flow_def() {
        String propertyName = "oddf_appr_flow_def";
        return (ProcesspatternEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOddf_version_num() {
        return oddf_version_num;
    }

    public void setOddf_version_num(String oddf_version_num) {
        this.oddf_version_num = oddf_version_num;
    }

    public String getOddf_def_form() {
        return oddf_def_form;
    }

    public void setOddf_def_form(String oddf_def_form) {
        this.oddf_def_form = oddf_def_form;
    }

    public FormEntity loadOddf_def_form() {
        String propertyName = "oddf_def_form";
        return (FormEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOddf_class() {
        return oddf_class;
    }

    public void setOddf_class(String oddf_class) {
        this.oddf_class = oddf_class;
    }

    public String getOddf_orde_class() {
        return oddf_orde_class;
    }

    public void setOddf_orde_class(String oddf_orde_class) {
        this.oddf_orde_class = oddf_orde_class;
    }

    public String getOddf_sys_form() {
        return oddf_sys_form;
    }

    public void setOddf_sys_form(String oddf_sys_form) {
        this.oddf_sys_form = oddf_sys_form;
    }

    public SystemformEntity loadOddf_sys_form() {
        String propertyName = "oddf_sys_form";
        return (SystemformEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOddf_name() {
        return oddf_name;
    }

    public void setOddf_name(String oddf_name) {
        this.oddf_name = oddf_name;
    }

    public String getOddf_close_pos() {
        return oddf_close_pos;
    }

    public void setOddf_close_pos(String oddf_close_pos) {
        this.oddf_close_pos = oddf_close_pos;
    }

    public String getOddf_code() {
        return oddf_code;
    }

    public void setOddf_code(String oddf_code) {
        this.oddf_code = oddf_code;
    }

    public List<OddfOddfRefOrdersaEntity> loadOddf_ref_orders() {
        String propertyName = "oddf_ref_orders";
        String rootClass = "";
        return (List<OddfOddfRefOrdersaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOddf_ref_orders(List<OddfOddfRefOrdersaEntity> oddf_ref_orders) {
        this.oddf_ref_orders = oddf_ref_orders;
    }

    public List<OddfOddfRefOrdersaEntity> getOddf_ref_orders() {
        return oddf_ref_orders;
    }

    public String getOddf_status() {
        return oddf_status;
    }

    public void setOddf_status(String oddf_status) {
        this.oddf_status = oddf_status;
    }

    public WordlistEntity loadOddf_status() {
        String propertyName = "oddf_status";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<OddfOddfResObjsaEntity> loadOddf_res_objs() {
        String propertyName = "oddf_res_objs";
        String rootClass = "";
        return (List<OddfOddfResObjsaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOddf_res_objs(List<OddfOddfResObjsaEntity> oddf_res_objs) {
        this.oddf_res_objs = oddf_res_objs;
    }

    public List<OddfOddfResObjsaEntity> getOddf_res_objs() {
        return oddf_res_objs;
    }

    public String getOddf_prev_version_obj() {
        return oddf_prev_version_obj;
    }

    public void setOddf_prev_version_obj(String oddf_prev_version_obj) {
        this.oddf_prev_version_obj = oddf_prev_version_obj;
    }

    public OperationdocumentdefinitionEntity loadOddf_prev_version_obj() {
        String propertyName = "oddf_prev_version_obj";
        return (OperationdocumentdefinitionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}