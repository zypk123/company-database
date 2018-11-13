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
public class EpeoEpeoSkillSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 4192672240839514L;
    
    /**技能领域*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_skill_field", className="WordlistEntity")
    private String epeo_skill_field;
    /**专业技能*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_skill_pro", className="WordlistEntity")
    private String epeo_skill_pro;
    /**掌握水平*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_mast_level", className="WordlistEntity")
    private String epeo_mast_level;
    /**应用时长*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epep_util_mon", className="")
    private Integer epep_util_mon;

    public String getEpeo_skill_field() {
        return epeo_skill_field;
    }

    public void setEpeo_skill_field(String epeo_skill_field) {
        this.epeo_skill_field = epeo_skill_field;
    }

    public WordlistEntity loadEpeo_skill_field() {
        String propertyName = "epeo_skill_field";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_skill_pro() {
        return epeo_skill_pro;
    }

    public void setEpeo_skill_pro(String epeo_skill_pro) {
        this.epeo_skill_pro = epeo_skill_pro;
    }

    public WordlistEntity loadEpeo_skill_pro() {
        String propertyName = "epeo_skill_pro";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_mast_level() {
        return epeo_mast_level;
    }

    public void setEpeo_mast_level(String epeo_mast_level) {
        this.epeo_mast_level = epeo_mast_level;
    }

    public WordlistEntity loadEpeo_mast_level() {
        String propertyName = "epeo_mast_level";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getEpep_util_mon() {
        return epep_util_mon;
    }

    public void setEpep_util_mon(Integer epep_util_mon) {
        this.epep_util_mon = epep_util_mon;
    }



}