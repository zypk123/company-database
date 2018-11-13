package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 控件类实体
 * 
 */
public class ControlEntity extends ShowEntity implements Serializable {
    private static final long serialVersionUID = 3236203583212666L;
    
    /**控件定义*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_define", className="ControldefinitionEntity")
    private String conl_define;
    /**序号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_sort", className="")
    private Integer conl_sort;
    /**控件值*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_value", className="")
    private String conl_value;
    /**可见否*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_visible", className="")
    private String conl_visible;
    /**控件参数集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_para_set", className="ConlConlParaSetaEntity")
    private List<ConlConlParaSetaEntity> conl_para_set;
    /**子控件集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_child_set", className="ConlConlChildSetaEntity")
    private List<ConlConlChildSetaEntity> conl_child_set;

    public String getConl_define() {
        return conl_define;
    }

    public void setConl_define(String conl_define) {
        this.conl_define = conl_define;
    }

    public ControldefinitionEntity loadConl_define() {
        String propertyName = "conl_define";
        return (ControldefinitionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getConl_sort() {
        return conl_sort;
    }

    public void setConl_sort(Integer conl_sort) {
        this.conl_sort = conl_sort;
    }

    public String getConl_value() {
        return conl_value;
    }

    public void setConl_value(String conl_value) {
        this.conl_value = conl_value;
    }

    public String getConl_visible() {
        return conl_visible;
    }

    public void setConl_visible(String conl_visible) {
        this.conl_visible = conl_visible;
    }

    public List<ConlConlParaSetaEntity> loadConl_para_set() {
        String propertyName = "conl_para_set";
        String rootClass = "";
        return (List<ConlConlParaSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setConl_para_set(List<ConlConlParaSetaEntity> conl_para_set) {
        this.conl_para_set = conl_para_set;
    }

    public List<ConlConlParaSetaEntity> getConl_para_set() {
        return conl_para_set;
    }

    public List<ConlConlChildSetaEntity> loadConl_child_set() {
        String propertyName = "conl_child_set";
        String rootClass = "";
        return (List<ConlConlChildSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setConl_child_set(List<ConlConlChildSetaEntity> conl_child_set) {
        this.conl_child_set = conl_child_set;
    }

    public List<ConlConlChildSetaEntity> getConl_child_set() {
        return conl_child_set;
    }



}