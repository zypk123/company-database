package com.huntkey.rx.sceo.formula.common.model.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


/**
 * @author zhouyou on 2017/9/30.
 */
public class RequestParamVo {
    //具体表单类 formclass
    @JSONField(name = "formclass")
    private String formclass;

    //方式 method
    @JSONField(name = "method")
    private String method;

    //方式 mark
    @JSONField(name = "mark")
    private String mark;

    //方式 01所有岗位  02主管岗位
    @JSONField(name = "allorOne")
    private String allorOne;

    //岗位id
    @JSONField(name = "postid")
    private String postid;

    //部门id
    @JSONField(name = "deptid")
    private String deptid;

    //级别
    @JSONField(name = "level")
    private String level;

    //日期
    @JSONField(name = "date")
    private Date date;

    //岗位级别
    @JSONField(name = "pGrade")
    private String pGrade;

    //岗位操作符
    @JSONField(name = "poperater")
    private String poperater;

    //部门级别
    @JSONField(name = "dGrade")
    private String  dGrade;

    //部门操作符
    @JSONField(name = "doperater")
    private String doperater;


    public String getAllorOne() {
        return allorOne;
    }

    public void setAllorOne(String allorOne) {
        this.allorOne = allorOne;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getFormclass() {
        return formclass;
    }

    public void setFormclass(String formclass) {
        this.formclass = formclass;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getpGrade() {
        return pGrade;
    }

    public void setpGrade(String pGrade) {
        this.pGrade = pGrade;
    }

    public String getPoperater() {
        return poperater;
    }

    public void setPoperater(String poperater) {
        this.poperater = poperater;
    }

    public String getdGrade() {
        return dGrade;
    }

    public void setdGrade(String dGrade) {
        this.dGrade = dGrade;
    }

    public String getDoperater() {
        return doperater;
    }

    public void setDoperater(String doperater) {
        this.doperater = doperater;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    @Override
    public String toString() {
        return "RequestParamVo{" +
                "formclass='" + formclass + '\'' +
                ", method='" + method + '\'' +
                ", mark='" + mark + '\'' +
                ", allorOne='" + allorOne + '\'' +
                ", postid='" + postid + '\'' +
                ", deptid='" + deptid + '\'' +
                ", level='" + level + '\'' +
                ", date=" + date +
                ", pGrade='" + pGrade + '\'' +
                ", poperater='" + poperater + '\'' +
                ", dGrade='" + dGrade + '\'' +
                ", doperater='" + doperater + '\'' +
                '}';
    }
}
