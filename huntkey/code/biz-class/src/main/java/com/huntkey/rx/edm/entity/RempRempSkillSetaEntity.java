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
public class RempRempSkillSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 869143675066164L;
    
    /**技能领域*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_skill_field", className="WordlistEntity")
    private String remp_skill_field;
    /**技能专业*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_skill_pro", className="WordlistEntity")
    private String remp_skill_pro;
    /**应用时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_util_mon", className="")
    private Integer remp_util_mon;
    /**掌握程度*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_mast_level", className="WordlistEntity")
    private String remp_mast_level;

    public String getRemp_skill_field() {
        return remp_skill_field;
    }

    public void setRemp_skill_field(String remp_skill_field) {
        this.remp_skill_field = remp_skill_field;
    }

    public WordlistEntity loadRemp_skill_field() {
        String propertyName = "remp_skill_field";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_skill_pro() {
        return remp_skill_pro;
    }

    public void setRemp_skill_pro(String remp_skill_pro) {
        this.remp_skill_pro = remp_skill_pro;
    }

    public WordlistEntity loadRemp_skill_pro() {
        String propertyName = "remp_skill_pro";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getRemp_util_mon() {
        return remp_util_mon;
    }

    public void setRemp_util_mon(Integer remp_util_mon) {
        this.remp_util_mon = remp_util_mon;
    }

    public String getRemp_mast_level() {
        return remp_mast_level;
    }

    public void setRemp_mast_level(String remp_mast_level) {
        this.remp_mast_level = remp_mast_level;
    }

    public WordlistEntity loadRemp_mast_level() {
        String propertyName = "remp_mast_level";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}