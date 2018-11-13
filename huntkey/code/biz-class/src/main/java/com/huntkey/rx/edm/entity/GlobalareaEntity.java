package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 全球区域类实体
 * 
 */
public class GlobalareaEntity extends GlobalinformationEntity implements Serializable {
    private static final long serialVersionUID = 4013255968623684L;
    
    /**区域识别码*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="gare_idcode", className="")
    private String gare_idcode;
    /**区域代码*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="gare_code", className="")
    private String gare_code;
    /**区域名称*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="gare_name", className="")
    private String gare_name;
    /**区域上级*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="gare_parent_area", className="AreaEntity")
    private String gare_parent_area;
    /**区域层级码*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="gare_level", className="")
    private String gare_level;
    /**区域排序*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="gare_order", className="")
    private Integer gare_order;
    /**区域描述*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="gare_desc", className="")
    private String gare_desc;

    public String getGare_idcode() {
        return gare_idcode;
    }

    public void setGare_idcode(String gare_idcode) {
        this.gare_idcode = gare_idcode;
    }

    public String getGare_code() {
        return gare_code;
    }

    public void setGare_code(String gare_code) {
        this.gare_code = gare_code;
    }

    public String getGare_name() {
        return gare_name;
    }

    public void setGare_name(String gare_name) {
        this.gare_name = gare_name;
    }

    public String getGare_parent_area() {
        return gare_parent_area;
    }

    public void setGare_parent_area(String gare_parent_area) {
        this.gare_parent_area = gare_parent_area;
    }

    public AreaEntity loadGare_parent_area() {
        String propertyName = "gare_parent_area";
        return (AreaEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getGare_level() {
        return gare_level;
    }

    public void setGare_level(String gare_level) {
        this.gare_level = gare_level;
    }

    public Integer getGare_order() {
        return gare_order;
    }

    public void setGare_order(Integer gare_order) {
        this.gare_order = gare_order;
    }

    public String getGare_desc() {
        return gare_desc;
    }

    public void setGare_desc(String gare_desc) {
        this.gare_desc = gare_desc;
    }



}