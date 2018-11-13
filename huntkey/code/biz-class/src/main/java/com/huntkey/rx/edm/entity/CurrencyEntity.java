package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 币别类实体
 * 
 */
public class CurrencyEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 4547256129625475L;
    
    /**币别识别码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="curr_idcode", className="")
    private Integer curr_idcode;
    /**币别代码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="curr_code", className="")
    private String curr_code;
    /**币别名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="curr_name", className="")
    private String curr_name;
    /**币别描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="curr_desc", className="")
    private String curr_desc;
    /**是否为国际标准币别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="curr_isstad_itnt", className="WordlistEntity")
    private String curr_isstad_itnt;

    public Integer getCurr_idcode() {
        return curr_idcode;
    }

    public void setCurr_idcode(Integer curr_idcode) {
        this.curr_idcode = curr_idcode;
    }

    public String getCurr_code() {
        return curr_code;
    }

    public void setCurr_code(String curr_code) {
        this.curr_code = curr_code;
    }

    public String getCurr_name() {
        return curr_name;
    }

    public void setCurr_name(String curr_name) {
        this.curr_name = curr_name;
    }

    public String getCurr_desc() {
        return curr_desc;
    }

    public void setCurr_desc(String curr_desc) {
        this.curr_desc = curr_desc;
    }

    public String getCurr_isstad_itnt() {
        return curr_isstad_itnt;
    }

    public void setCurr_isstad_itnt(String curr_isstad_itnt) {
        this.curr_isstad_itnt = curr_isstad_itnt;
    }

    public WordlistEntity loadCurr_isstad_itnt() {
        String propertyName = "curr_isstad_itnt";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}