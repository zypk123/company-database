package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by weijian on 2017/11/18.
 */
public class OeeoWorkSetDTO {
    String id;
    String pid;
    String classname;
    @JSONField(name="oeeo_cons_post")
    String oeeoConsPost;//咨询人姓名
    @JSONField(name="oeeo_cons_rela")
    String oeeoConsRela;//咨询人关系
    @JSONField(name="oeeo_cons_tel")
    String oeeoConsTel;//咨询人电话
    @JSONField(name="oeeo_work_beg")
    String oeeoWorkBeg;//工作开始时间
    @JSONField(name="oeeo_work_comp")
    String oeeoWorkComp;//工作单位
    @JSONField(name="oeeo_work_cons")
    String oeeoWorkCons;//咨询人姓名
    @JSONField(name="oeeo_work_desc")
    String oeeoWorkDesc;//工作描述
    @JSONField(name="oeeo_work_end")
    String oeeoWorkEnd;//工作结束时间
    @JSONField(name="oeeo_work_post")
    String oeeoWorkPost;//职位名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getOeeoConsPost() {
        return oeeoConsPost;
    }

    public void setOeeoConsPost(String oeeoConsPost) {
        this.oeeoConsPost = oeeoConsPost;
    }

    public String getOeeoConsRela() {
        return oeeoConsRela;
    }

    public void setOeeoConsRela(String oeeoConsRela) {
        this.oeeoConsRela = oeeoConsRela;
    }

    public String getOeeoConsTel() {
        return oeeoConsTel;
    }

    public void setOeeoConsTel(String oeeoConsTel) {
        this.oeeoConsTel = oeeoConsTel;
    }

    public String getOeeoWorkBeg() {
        return oeeoWorkBeg;
    }

    public void setOeeoWorkBeg(String oeeoWorkBeg) {
        this.oeeoWorkBeg = oeeoWorkBeg;
    }

    public String getOeeoWorkComp() {
        return oeeoWorkComp;
    }

    public void setOeeoWorkComp(String oeeoWorkComp) {
        this.oeeoWorkComp = oeeoWorkComp;
    }

    public String getOeeoWorkCons() {
        return oeeoWorkCons;
    }

    public void setOeeoWorkCons(String oeeoWorkCons) {
        this.oeeoWorkCons = oeeoWorkCons;
    }

    public String getOeeoWorkDesc() {
        return oeeoWorkDesc;
    }

    public void setOeeoWorkDesc(String oeeoWorkDesc) {
        this.oeeoWorkDesc = oeeoWorkDesc;
    }

    public String getOeeoWorkEnd() {
        return oeeoWorkEnd;
    }

    public void setOeeoWorkEnd(String oeeoWorkEnd) {
        this.oeeoWorkEnd = oeeoWorkEnd;
    }

    public String getOeeoWorkPost() {
        return oeeoWorkPost;
    }

    public void setOeeoWorkPost(String oeeoWorkPost) {
        this.oeeoWorkPost = oeeoWorkPost;
    }
}
