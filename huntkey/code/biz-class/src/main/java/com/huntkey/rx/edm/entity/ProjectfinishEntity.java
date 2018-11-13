package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 项目结项单类实体
 * 
 */
public class ProjectfinishEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 1893884766940131L;
    
    /**项目对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prfh_proj", className="ProjectEntity")
    private String prfh_proj;
    /**结项报告*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prfh_cont", className="")
    private String prfh_cont;
    /**结项评价*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prfh_eval", className="")
    private String prfh_eval;
    /**评审意见集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prfh_eval_set", className="PrfhPrfhEvalSetaEntity")
    private List<PrfhPrfhEvalSetaEntity> prfh_eval_set;

    public String getPrfh_proj() {
        return prfh_proj;
    }

    public void setPrfh_proj(String prfh_proj) {
        this.prfh_proj = prfh_proj;
    }

    public ProjectEntity loadPrfh_proj() {
        String propertyName = "prfh_proj";
        return (ProjectEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrfh_cont() {
        return prfh_cont;
    }

    public void setPrfh_cont(String prfh_cont) {
        this.prfh_cont = prfh_cont;
    }

    public String getPrfh_eval() {
        return prfh_eval;
    }

    public void setPrfh_eval(String prfh_eval) {
        this.prfh_eval = prfh_eval;
    }

    public List<PrfhPrfhEvalSetaEntity> loadPrfh_eval_set() {
        String propertyName = "prfh_eval_set";
        String rootClass = "";
        return (List<PrfhPrfhEvalSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPrfh_eval_set(List<PrfhPrfhEvalSetaEntity> prfh_eval_set) {
        this.prfh_eval_set = prfh_eval_set;
    }

    public List<PrfhPrfhEvalSetaEntity> getPrfh_eval_set() {
        return prfh_eval_set;
    }



}