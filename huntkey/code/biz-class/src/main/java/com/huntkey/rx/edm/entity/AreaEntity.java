package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 区域类实体
 * 
 */
public class AreaEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 2339171031859539L;
    
    /**区域识别码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="area_idcode", className="")
    private String area_idcode;
    /**区域代码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="area_code", className="")
    private String area_code;
    /**区域名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="area_name", className="")
    private String area_name;
    /**区域上级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="area_parent_area", className="AreaEntity")
    private String area_parent_area;
    /**区域层级码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="area_level", className="")
    private String area_level;
    /**区域排序*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="area_order", className="")
    private Integer area_order;
    /**区域描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="area_desc", className="")
    private String area_desc;

    public String getArea_idcode() {
        return area_idcode;
    }

    public void setArea_idcode(String area_idcode) {
        this.area_idcode = area_idcode;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getArea_parent_area() {
        return area_parent_area;
    }

    public void setArea_parent_area(String area_parent_area) {
        this.area_parent_area = area_parent_area;
    }

    public AreaEntity loadArea_parent_area() {
        String propertyName = "area_parent_area";
        return (AreaEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getArea_level() {
        return area_level;
    }

    public void setArea_level(String area_level) {
        this.area_level = area_level;
    }

    public Integer getArea_order() {
        return area_order;
    }

    public void setArea_order(Integer area_order) {
        this.area_order = area_order;
    }

    public String getArea_desc() {
        return area_desc;
    }

    public void setArea_desc(String area_desc) {
        this.area_desc = area_desc;
    }



}