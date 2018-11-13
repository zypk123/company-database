package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 控件定义类实体
 * 
 */
public class ControldefinitionEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 2661341544096479L;
    
    /**控件分类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ctdf_class", className="WordlistEntity")
    private String ctdf_class;
    /**排序值*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ctdf_sort", className="")
    private Integer ctdf_sort;
    /**控件参数集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ctdf_para_set", className="CtdfCtdfParaSetaEntity")
    private List<CtdfCtdfParaSetaEntity> ctdf_para_set;

    public String getCtdf_class() {
        return ctdf_class;
    }

    public void setCtdf_class(String ctdf_class) {
        this.ctdf_class = ctdf_class;
    }

    public WordlistEntity loadCtdf_class() {
        String propertyName = "ctdf_class";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getCtdf_sort() {
        return ctdf_sort;
    }

    public void setCtdf_sort(Integer ctdf_sort) {
        this.ctdf_sort = ctdf_sort;
    }

    public List<CtdfCtdfParaSetaEntity> loadCtdf_para_set() {
        String propertyName = "ctdf_para_set";
        String rootClass = "";
        return (List<CtdfCtdfParaSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setCtdf_para_set(List<CtdfCtdfParaSetaEntity> ctdf_para_set) {
        this.ctdf_para_set = ctdf_para_set;
    }

    public List<CtdfCtdfParaSetaEntity> getCtdf_para_set() {
        return ctdf_para_set;
    }



}