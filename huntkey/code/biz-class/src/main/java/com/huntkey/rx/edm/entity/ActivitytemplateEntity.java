package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 活动模板类实体
 * 
 */
public class ActivitytemplateEntity extends ProcessEntity implements Serializable {
    private static final long serialVersionUID = 3738452911230880L;
    
    /**模板编码*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_code", className="")
    private String actp_code;
    /**模板名称*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_name", className="")
    private String actp_name;
    /**模板类型*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_type", className="")
    private String actp_type;
    /**模板描述*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_desc", className="")
    private String actp_desc;
    /**审批人定位公式*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_owner", className="")
    private String actp_owner;
    /**所属阶段*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_stage", className="")
    private String actp_stage;
    /**流转条件*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_flow_condition", className="")
    private String actp_flow_condition;
    /**流转表达式公式*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_formula_id", className="")
    private String actp_formula_id;
    /**是否是默认节点*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_is_default", className="")
    private String actp_is_default;
    /**激活条件*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_active_condition", className="")
    private String actp_active_condition;
    /**左邻模板集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_left_set", className="ActpActpLeftSetaEntity")
    private List<ActpActpLeftSetaEntity> actp_left_set;
    /**右邻模板集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_right_set", className="ActpActpRightSetaEntity")
    private List<ActpActpRightSetaEntity> actp_right_set;
    /**流程定义*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_processpattern", className="ProcesspatternEntity")
    private String actp_processpattern;
    /**分组*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_group", className="")
    private String actp_group;
    /**节点类型*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_node_type", className="WordlistEntity")
    private String actp_node_type;
    /**多处理人类型*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_configactp_users_type", className="WordlistEntity")
    private String actp_configactp_users_type;
    /**配置信息*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="actp_config", className="")
    private String actp_config;

    public String getActp_code() {
        return actp_code;
    }

    public void setActp_code(String actp_code) {
        this.actp_code = actp_code;
    }

    public String getActp_name() {
        return actp_name;
    }

    public void setActp_name(String actp_name) {
        this.actp_name = actp_name;
    }

    public String getActp_type() {
        return actp_type;
    }

    public void setActp_type(String actp_type) {
        this.actp_type = actp_type;
    }

    public String getActp_desc() {
        return actp_desc;
    }

    public void setActp_desc(String actp_desc) {
        this.actp_desc = actp_desc;
    }

    public String getActp_owner() {
        return actp_owner;
    }

    public void setActp_owner(String actp_owner) {
        this.actp_owner = actp_owner;
    }

    public String getActp_stage() {
        return actp_stage;
    }

    public void setActp_stage(String actp_stage) {
        this.actp_stage = actp_stage;
    }

    public String getActp_flow_condition() {
        return actp_flow_condition;
    }

    public void setActp_flow_condition(String actp_flow_condition) {
        this.actp_flow_condition = actp_flow_condition;
    }

    public String getActp_formula_id() {
        return actp_formula_id;
    }

    public void setActp_formula_id(String actp_formula_id) {
        this.actp_formula_id = actp_formula_id;
    }

    public String getActp_is_default() {
        return actp_is_default;
    }

    public void setActp_is_default(String actp_is_default) {
        this.actp_is_default = actp_is_default;
    }

    public String getActp_active_condition() {
        return actp_active_condition;
    }

    public void setActp_active_condition(String actp_active_condition) {
        this.actp_active_condition = actp_active_condition;
    }

    public List<ActpActpLeftSetaEntity> loadActp_left_set() {
        String propertyName = "actp_left_set";
        String rootClass = "";
        return (List<ActpActpLeftSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setActp_left_set(List<ActpActpLeftSetaEntity> actp_left_set) {
        this.actp_left_set = actp_left_set;
    }

    public List<ActpActpLeftSetaEntity> getActp_left_set() {
        return actp_left_set;
    }

    public List<ActpActpRightSetaEntity> loadActp_right_set() {
        String propertyName = "actp_right_set";
        String rootClass = "";
        return (List<ActpActpRightSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setActp_right_set(List<ActpActpRightSetaEntity> actp_right_set) {
        this.actp_right_set = actp_right_set;
    }

    public List<ActpActpRightSetaEntity> getActp_right_set() {
        return actp_right_set;
    }

    public String getActp_processpattern() {
        return actp_processpattern;
    }

    public void setActp_processpattern(String actp_processpattern) {
        this.actp_processpattern = actp_processpattern;
    }

    public ProcesspatternEntity loadActp_processpattern() {
        String propertyName = "actp_processpattern";
        return (ProcesspatternEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getActp_group() {
        return actp_group;
    }

    public void setActp_group(String actp_group) {
        this.actp_group = actp_group;
    }

    public String getActp_node_type() {
        return actp_node_type;
    }

    public void setActp_node_type(String actp_node_type) {
        this.actp_node_type = actp_node_type;
    }

    public WordlistEntity loadActp_node_type() {
        String propertyName = "actp_node_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getActp_configactp_users_type() {
        return actp_configactp_users_type;
    }

    public void setActp_configactp_users_type(String actp_configactp_users_type) {
        this.actp_configactp_users_type = actp_configactp_users_type;
    }

    public WordlistEntity loadActp_configactp_users_type() {
        String propertyName = "actp_configactp_users_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getActp_config() {
        return actp_config;
    }

    public void setActp_config(String actp_config) {
        this.actp_config = actp_config;
    }



}