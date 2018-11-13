package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 项目审查单类实体
 * 
 */
public class ProjectcheckEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 845050764080632L;
    
    /**项目对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_proj", className="ProjectEntity")
    private String prtk_proj;
    /**任务名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_name", className="")
    private String prtk_name;
    /**任务描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_desc", className="")
    private String prtk_desc;
    /**审查意见*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_suggestion", className="")
    private String prtk_suggestion;
    /**项目产出集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_output_set", className="PrtkPrtkOutputSetaEntity")
    private List<PrtkPrtkOutputSetaEntity> prtk_output_set;
    /**评审意见集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_sugg_set", className="PrtkPrtkSuggSetaEntity")
    private List<PrtkPrtkSuggSetaEntity> prtk_sugg_set;

    public String getPrtk_proj() {
        return prtk_proj;
    }

    public void setPrtk_proj(String prtk_proj) {
        this.prtk_proj = prtk_proj;
    }

    public ProjectEntity loadPrtk_proj() {
        String propertyName = "prtk_proj";
        return (ProjectEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrtk_name() {
        return prtk_name;
    }

    public void setPrtk_name(String prtk_name) {
        this.prtk_name = prtk_name;
    }

    public String getPrtk_desc() {
        return prtk_desc;
    }

    public void setPrtk_desc(String prtk_desc) {
        this.prtk_desc = prtk_desc;
    }

    public String getPrtk_suggestion() {
        return prtk_suggestion;
    }

    public void setPrtk_suggestion(String prtk_suggestion) {
        this.prtk_suggestion = prtk_suggestion;
    }

    public List<PrtkPrtkOutputSetaEntity> loadPrtk_output_set() {
        String propertyName = "prtk_output_set";
        String rootClass = "";
        return (List<PrtkPrtkOutputSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPrtk_output_set(List<PrtkPrtkOutputSetaEntity> prtk_output_set) {
        this.prtk_output_set = prtk_output_set;
    }

    public List<PrtkPrtkOutputSetaEntity> getPrtk_output_set() {
        return prtk_output_set;
    }

    public List<PrtkPrtkSuggSetaEntity> loadPrtk_sugg_set() {
        String propertyName = "prtk_sugg_set";
        String rootClass = "";
        return (List<PrtkPrtkSuggSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPrtk_sugg_set(List<PrtkPrtkSuggSetaEntity> prtk_sugg_set) {
        this.prtk_sugg_set = prtk_sugg_set;
    }

    public List<PrtkPrtkSuggSetaEntity> getPrtk_sugg_set() {
        return prtk_sugg_set;
    }



}