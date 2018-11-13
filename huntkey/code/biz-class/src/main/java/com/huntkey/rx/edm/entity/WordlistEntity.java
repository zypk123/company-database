package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 枚举列表类实体
 * 
 */
public class WordlistEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 2481811582255423L;
    
    /**状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="word_enable", className="")
    private Integer word_enable;
    /**序号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="word_seq", className="")
    private Integer word_seq;
    /**词汇名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="word_name", className="")
    private String word_name;
    /**词汇用途说明*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="word_remark", className="")
    private String word_remark;
    /**是否标准*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="word_issta", className="")
    private Integer word_issta;
    /**上级词汇对象*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="word_par", className="WordlistEntity")
    private String word_par;

    public Integer getWord_enable() {
        return word_enable;
    }

    public void setWord_enable(Integer word_enable) {
        this.word_enable = word_enable;
    }

    public Integer getWord_seq() {
        return word_seq;
    }

    public void setWord_seq(Integer word_seq) {
        this.word_seq = word_seq;
    }

    public String getWord_name() {
        return word_name;
    }

    public void setWord_name(String word_name) {
        this.word_name = word_name;
    }

    public String getWord_remark() {
        return word_remark;
    }

    public void setWord_remark(String word_remark) {
        this.word_remark = word_remark;
    }

    public Integer getWord_issta() {
        return word_issta;
    }

    public void setWord_issta(Integer word_issta) {
        this.word_issta = word_issta;
    }

    public String getWord_par() {
        return word_par;
    }

    public void setWord_par(String word_par) {
        this.word_par = word_par;
    }

    public WordlistEntity loadWord_par() {
        String propertyName = "word_par";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}