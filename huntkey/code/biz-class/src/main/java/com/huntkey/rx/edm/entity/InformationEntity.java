package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 信息类实体
 * 
 */
public class InformationEntity extends ResourceEntity implements Serializable {
    private static final long serialVersionUID = 2486983529680540L;
    
    /**编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="info_code", className="")
    private String info_code;
    /**名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="info_name", className="")
    private String info_name;
    /**描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="info_desc", className="")
    private String info_desc;

    public String getInfo_code() {
        return info_code;
    }

    public void setInfo_code(String info_code) {
        this.info_code = info_code;
    }

    public String getInfo_name() {
        return info_name;
    }

    public void setInfo_name(String info_name) {
        this.info_name = info_name;
    }

    public String getInfo_desc() {
        return info_desc;
    }

    public void setInfo_desc(String info_desc) {
        this.info_desc = info_desc;
    }



}