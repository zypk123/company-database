package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 知识类实体
 * 
 */
public class KnowledgeEntity extends AssetEntity implements Serializable {
    private static final long serialVersionUID = 5876943992291396L;
    
    /**定义*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="know_defi", className="")
    private String know_defi;
    /**适用范围*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="know_scope", className="")
    private String know_scope;
    /**密级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="know_secret_degree", className="")
    private Integer know_secret_degree;
    /**是否计次*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="know_iscount", className="")
    private String know_iscount;
    /**知识来源*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="know_origin", className="KnowledgeEntity")
    private String know_origin;

    public String getKnow_defi() {
        return know_defi;
    }

    public void setKnow_defi(String know_defi) {
        this.know_defi = know_defi;
    }

    public String getKnow_scope() {
        return know_scope;
    }

    public void setKnow_scope(String know_scope) {
        this.know_scope = know_scope;
    }

    public Integer getKnow_secret_degree() {
        return know_secret_degree;
    }

    public void setKnow_secret_degree(Integer know_secret_degree) {
        this.know_secret_degree = know_secret_degree;
    }

    public String getKnow_iscount() {
        return know_iscount;
    }

    public void setKnow_iscount(String know_iscount) {
        this.know_iscount = know_iscount;
    }

    public String getKnow_origin() {
        return know_origin;
    }

    public void setKnow_origin(String know_origin) {
        this.know_origin = know_origin;
    }

    public KnowledgeEntity loadKnow_origin() {
        String propertyName = "know_origin";
        return (KnowledgeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}