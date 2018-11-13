package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by weijian on 2017/12/14.
 */
public class SchoolDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;//学校id
    @JSONField(name = "rsch_city")
    private String rschCity;//学校所在地Id
    @JSONField(name = "rsch_website")
    private String rschWebsite;//网址
    @JSONField(name = "rsch_code")
    private String rschCode;//学校代码
    @JSONField(name = "rsch_name")
    private String rschName;//学校名称
    @JSONField(name = "rsch_ranking")
    private String rschRanking;//学校层次

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRschCity() {
        return rschCity;
    }

    public void setRschCity(String rschCity) {
        this.rschCity = rschCity;
    }

    public String getRschWebsite() {
        return rschWebsite;
    }

    public void setRschWebsite(String rschWebsite) {
        this.rschWebsite = rschWebsite;
    }

    public String getRschCode() {
        return rschCode;
    }

    public void setRschCode(String rschCode) {
        this.rschCode = rschCode;
    }

    public String getRschName() {
        return rschName;
    }

    public void setRschName(String rschName) {
        this.rschName = rschName;
    }

    public String getRschRanking() {
        return rschRanking;
    }

    public void setRschRanking(String rschRanking) {
        this.rschRanking = rschRanking;
    }
}
