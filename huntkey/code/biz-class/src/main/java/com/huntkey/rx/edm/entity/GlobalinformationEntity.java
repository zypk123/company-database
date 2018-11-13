package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 全球信息类实体
 * 
 */
public class GlobalinformationEntity extends EcosystemEntity implements Serializable {
    private static final long serialVersionUID = 5546604135927274L;
    
    /**编号*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="ginf_code", className="")
    private String ginf_code;
    /**名称*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="ginf_name", className="")
    private String ginf_name;
    /**描述*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="ginf_desc", className="")
    private String ginf_desc;

    public String getGinf_code() {
        return ginf_code;
    }

    public void setGinf_code(String ginf_code) {
        this.ginf_code = ginf_code;
    }

    public String getGinf_name() {
        return ginf_name;
    }

    public void setGinf_name(String ginf_name) {
        this.ginf_name = ginf_name;
    }

    public String getGinf_desc() {
        return ginf_desc;
    }

    public void setGinf_desc(String ginf_desc) {
        this.ginf_desc = ginf_desc;
    }



}