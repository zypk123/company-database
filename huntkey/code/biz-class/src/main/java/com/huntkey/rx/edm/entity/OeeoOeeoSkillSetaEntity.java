package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class OeeoOeeoSkillSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 1266392744130025L;
    
    /**技能领域*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_skill_field", className="WordlistEntity")
    private String oeeo_skill_field;
    /**专业技能*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_skill_pro", className="WordlistEntity")
    private String oeeo_skill_pro;
    /**掌握水平*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_mast_level", className="WordlistEntity")
    private String oeeo_mast_level;
    /**应用时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_util_mon", className="")
    private Integer oeeo_util_mon;

    public String getOeeo_skill_field() {
        return oeeo_skill_field;
    }

    public void setOeeo_skill_field(String oeeo_skill_field) {
        this.oeeo_skill_field = oeeo_skill_field;
    }

    public WordlistEntity loadOeeo_skill_field() {
        String propertyName = "oeeo_skill_field";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_skill_pro() {
        return oeeo_skill_pro;
    }

    public void setOeeo_skill_pro(String oeeo_skill_pro) {
        this.oeeo_skill_pro = oeeo_skill_pro;
    }

    public WordlistEntity loadOeeo_skill_pro() {
        String propertyName = "oeeo_skill_pro";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_mast_level() {
        return oeeo_mast_level;
    }

    public void setOeeo_mast_level(String oeeo_mast_level) {
        this.oeeo_mast_level = oeeo_mast_level;
    }

    public WordlistEntity loadOeeo_mast_level() {
        String propertyName = "oeeo_mast_level";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getOeeo_util_mon() {
        return oeeo_util_mon;
    }

    public void setOeeo_util_mon(Integer oeeo_util_mon) {
        this.oeeo_util_mon = oeeo_util_mon;
    }



}