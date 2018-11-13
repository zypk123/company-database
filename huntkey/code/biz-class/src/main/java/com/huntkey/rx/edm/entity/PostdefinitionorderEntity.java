package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 职位定义维护单类实体
 * 
 */
public class PostdefinitionorderEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 21653476501583L;
    
    /**职位类别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opde_type", className="WordlistEntity")
    private String opde_type;
    /**职位属性*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opde_prop", className="WordlistEntity")
    private String opde_prop;
    /**备注*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opde_remark", className="")
    private String opde_remark;
    /**职位列表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opde_post_set", className="OpdeOpdePostSetaEntity")
    private List<OpdeOpdePostSetaEntity> opde_post_set;
    /**单据类型*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="opde_edit_type", className="")
    private String opde_edit_type;

    public String getOpde_type() {
        return opde_type;
    }

    public void setOpde_type(String opde_type) {
        this.opde_type = opde_type;
    }

    public WordlistEntity loadOpde_type() {
        String propertyName = "opde_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOpde_prop() {
        return opde_prop;
    }

    public void setOpde_prop(String opde_prop) {
        this.opde_prop = opde_prop;
    }

    public WordlistEntity loadOpde_prop() {
        String propertyName = "opde_prop";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOpde_remark() {
        return opde_remark;
    }

    public void setOpde_remark(String opde_remark) {
        this.opde_remark = opde_remark;
    }

    public List<OpdeOpdePostSetaEntity> loadOpde_post_set() {
        String propertyName = "opde_post_set";
        String rootClass = "";
        return (List<OpdeOpdePostSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOpde_post_set(List<OpdeOpdePostSetaEntity> opde_post_set) {
        this.opde_post_set = opde_post_set;
    }

    public List<OpdeOpdePostSetaEntity> getOpde_post_set() {
        return opde_post_set;
    }

    public String getOpde_edit_type() {
        return opde_edit_type;
    }

    public void setOpde_edit_type(String opde_edit_type) {
        this.opde_edit_type = opde_edit_type;
    }



}