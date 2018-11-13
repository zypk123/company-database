package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 提示信息类实体
 * 
 */
public class TipmessageEntity extends ShowEntity implements Serializable {
    private static final long serialVersionUID = 4070698293298200L;
    
    /**信息编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="tipm_msg_code", className="")
    private String tipm_msg_code;
    /**页面编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="tipm_page_code", className="")
    private String tipm_page_code;
    /**显示时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="tipm_show_time", className="")
    private Integer tipm_show_time;
    /**信息内容*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="tipm_base_msg", className="")
    private String tipm_base_msg;
    /**自定义信息内容*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="tipm_show_msg", className="")
    private String tipm_show_msg;
    /**信息类别*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="tipm_msg_type", className="WordlistEntity")
    private String tipm_msg_type;

    public String getTipm_msg_code() {
        return tipm_msg_code;
    }

    public void setTipm_msg_code(String tipm_msg_code) {
        this.tipm_msg_code = tipm_msg_code;
    }

    public String getTipm_page_code() {
        return tipm_page_code;
    }

    public void setTipm_page_code(String tipm_page_code) {
        this.tipm_page_code = tipm_page_code;
    }

    public Integer getTipm_show_time() {
        return tipm_show_time;
    }

    public void setTipm_show_time(Integer tipm_show_time) {
        this.tipm_show_time = tipm_show_time;
    }

    public String getTipm_base_msg() {
        return tipm_base_msg;
    }

    public void setTipm_base_msg(String tipm_base_msg) {
        this.tipm_base_msg = tipm_base_msg;
    }

    public String getTipm_show_msg() {
        return tipm_show_msg;
    }

    public void setTipm_show_msg(String tipm_show_msg) {
        this.tipm_show_msg = tipm_show_msg;
    }

    public String getTipm_msg_type() {
        return tipm_msg_type;
    }

    public void setTipm_msg_type(String tipm_msg_type) {
        this.tipm_msg_type = tipm_msg_type;
    }

    public WordlistEntity loadTipm_msg_type() {
        String propertyName = "tipm_msg_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}