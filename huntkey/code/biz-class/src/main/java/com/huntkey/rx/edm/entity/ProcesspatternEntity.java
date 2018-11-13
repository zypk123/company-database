package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 流程定义类实体
 * 
 */
public class ProcesspatternEntity extends KnowledgeEntity implements Serializable {
    private static final long serialVersionUID = 8896669788630279L;
    
    /**流程定义名称*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_name", className="")
    private String prpt_name;
    /**流程定义标识*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_code", className="")
    private String prpt_code;
    /**流程定义描述*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_desc", className="")
    private String prpt_desc;
    /**流程首节点*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_fflownode", className="ActivitytemplateEntity")
    private String prpt_fflownode;
    /**适用部门*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_applydept", className="DepttreeEntity")
    private String prpt_applydept;
    /**活动模板集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_proc_set", className="PrptPrptProcSetaEntity")
    private List<PrptPrptProcSetaEntity> prpt_proc_set;
    /**关联单据定义*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_order", className="OperationdocumentdefinitionEntity")
    private String prpt_order;
    /**关联表单*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_form", className="FormEntity")
    private String prpt_form;
    /**状态*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_status", className="WordlistEntity")
    private String prpt_status;
    /**流程状态*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_procstatus", className="WordlistEntity")
    private String prpt_procstatus;
    /**版本*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_version", className="")
    private String prpt_version;
    /**流程设计资源文件*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_resources", className="")
    private String prpt_resources;
    /**流程图资源文件*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_svg_data", className="")
    private String prpt_svg_data;
    /**阶段配置信息*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_stage_config", className="")
    private String prpt_stage_config;
    /**生效时间*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prpt_effect_time", className="")
    private Date prpt_effect_time;

    public String getPrpt_name() {
        return prpt_name;
    }

    public void setPrpt_name(String prpt_name) {
        this.prpt_name = prpt_name;
    }

    public String getPrpt_code() {
        return prpt_code;
    }

    public void setPrpt_code(String prpt_code) {
        this.prpt_code = prpt_code;
    }

    public String getPrpt_desc() {
        return prpt_desc;
    }

    public void setPrpt_desc(String prpt_desc) {
        this.prpt_desc = prpt_desc;
    }

    public String getPrpt_fflownode() {
        return prpt_fflownode;
    }

    public void setPrpt_fflownode(String prpt_fflownode) {
        this.prpt_fflownode = prpt_fflownode;
    }

    public ActivitytemplateEntity loadPrpt_fflownode() {
        String propertyName = "prpt_fflownode";
        return (ActivitytemplateEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrpt_applydept() {
        return prpt_applydept;
    }

    public void setPrpt_applydept(String prpt_applydept) {
        this.prpt_applydept = prpt_applydept;
    }

    public DepttreeEntity loadPrpt_applydept() {
        String propertyName = "prpt_applydept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<PrptPrptProcSetaEntity> loadPrpt_proc_set() {
        String propertyName = "prpt_proc_set";
        String rootClass = "";
        return (List<PrptPrptProcSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPrpt_proc_set(List<PrptPrptProcSetaEntity> prpt_proc_set) {
        this.prpt_proc_set = prpt_proc_set;
    }

    public List<PrptPrptProcSetaEntity> getPrpt_proc_set() {
        return prpt_proc_set;
    }

    public String getPrpt_order() {
        return prpt_order;
    }

    public void setPrpt_order(String prpt_order) {
        this.prpt_order = prpt_order;
    }

    public OperationdocumentdefinitionEntity loadPrpt_order() {
        String propertyName = "prpt_order";
        return (OperationdocumentdefinitionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrpt_form() {
        return prpt_form;
    }

    public void setPrpt_form(String prpt_form) {
        this.prpt_form = prpt_form;
    }

    public FormEntity loadPrpt_form() {
        String propertyName = "prpt_form";
        return (FormEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrpt_status() {
        return prpt_status;
    }

    public void setPrpt_status(String prpt_status) {
        this.prpt_status = prpt_status;
    }

    public WordlistEntity loadPrpt_status() {
        String propertyName = "prpt_status";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrpt_procstatus() {
        return prpt_procstatus;
    }

    public void setPrpt_procstatus(String prpt_procstatus) {
        this.prpt_procstatus = prpt_procstatus;
    }

    public WordlistEntity loadPrpt_procstatus() {
        String propertyName = "prpt_procstatus";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrpt_version() {
        return prpt_version;
    }

    public void setPrpt_version(String prpt_version) {
        this.prpt_version = prpt_version;
    }

    public String getPrpt_resources() {
        return prpt_resources;
    }

    public void setPrpt_resources(String prpt_resources) {
        this.prpt_resources = prpt_resources;
    }

    public String getPrpt_svg_data() {
        return prpt_svg_data;
    }

    public void setPrpt_svg_data(String prpt_svg_data) {
        this.prpt_svg_data = prpt_svg_data;
    }

    public String getPrpt_stage_config() {
        return prpt_stage_config;
    }

    public void setPrpt_stage_config(String prpt_stage_config) {
        this.prpt_stage_config = prpt_stage_config;
    }

    public Date getPrpt_effect_time() {
        return prpt_effect_time;
    }

    public void setPrpt_effect_time(Date prpt_effect_time) {
        this.prpt_effect_time = prpt_effect_time;
    }



}