package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by weijian on 2017/11/20.
 */
public class opdePostSetDTO {
    @JSONField(name = "id")
    String id;
    @JSONField(name = "pid")
    String pid;
    @JSONField(name = "classname")
    String classname;
    @JSONField(name = "opde_duty_old")
    String opdeDutyOld;//职位职责_旧
    @JSONField(name = "opde_grade")
    String opdeGrade;//岗级
    @JSONField(name = "opde_grade_old")
    String opdeGradeOld;//岗级_旧
    @JSONField(name = "opde_name_old")
    String opdeNameOld;//职位名称_旧
    @JSONField(name = "opde_post_code")
    String opdePostCode;//职位编码
    @JSONField(name = "opde_post_duty")
    String opdePostDuty;//职位职责
    @JSONField(name = "opde_post_name")
    String opdePostName;//职位名称
    @JSONField(name = "opde_post_qual")
    String opdePostQual;//任职资格
    @JSONField(name = "opde_post_status")
    String opdePostStatus;//状态
    @JSONField(name = "opde_qual_old")
    String opdeQualOld;//职位资格_旧
    @JSONField(name = "opde_status_old")
    String opdeStatusOld;//状态_旧
    @JSONField(name = "opde_seq")
    String opdeSeq;//排序


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

    public String getOpdeDutyOld() {
        return opdeDutyOld;
    }

    public void setOpdeDutyOld(String opdeDutyOld) {
        this.opdeDutyOld = opdeDutyOld;
    }

    public String getOpdeGrade() {
        return opdeGrade;
    }

    public void setOpdeGrade(String opdeGrade) {
        this.opdeGrade = opdeGrade;
    }

    public String getOpdeGradeOld() {
        return opdeGradeOld;
    }

    public void setOpdeGradeOld(String opdeGradeOld) {
        this.opdeGradeOld = opdeGradeOld;
    }

    public String getOpdeNameOld() {
        return opdeNameOld;
    }

    public void setOpdeNameOld(String opdeNameOld) {
        this.opdeNameOld = opdeNameOld;
    }

    public String getOpdePostCode() {
        return opdePostCode;
    }

    public void setOpdePostCode(String opdePostCode) {
        this.opdePostCode = opdePostCode;
    }

    public String getOpdePostDuty() {
        return opdePostDuty;
    }

    public void setOpdePostDuty(String opdePostDuty) {
        this.opdePostDuty = opdePostDuty;
    }

    public String getOpdePostName() {
        return opdePostName;
    }

    public void setOpdePostName(String opdePostName) {
        this.opdePostName = opdePostName;
    }

    public String getOpdePostQual() {
        return opdePostQual;
    }

    public void setOpdePostQual(String opdePostQual) {
        this.opdePostQual = opdePostQual;
    }

    public String getOpdePostStatus() {
        return opdePostStatus;
    }

    public void setOpdePostStatus(String opdePostStatus) {
        this.opdePostStatus = opdePostStatus;
    }

    public String getOpdeQualOld() {
        return opdeQualOld;
    }

    public void setOpdeQualOld(String opdeQualOld) {
        this.opdeQualOld = opdeQualOld;
    }

    public String getOpdeStatusOld() {
        return opdeStatusOld;
    }

    public void setOpdeStatusOld(String opdeStatusOld) {
        this.opdeStatusOld = opdeStatusOld;
    }

    public String getOpdeSeq() {
        return opdeSeq;
    }

    public void setOpdeSeq(String opdeSeq) {
        this.opdeSeq = opdeSeq;
    }
}
