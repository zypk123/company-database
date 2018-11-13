package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by weijian on 2017/11/20.
 */
public class PositionDefinitionDTO {
    @JSONField(name = "id")
    String id;
    @JSONField(name = "rpof_code")
    String rpofCode;//职位编码
    @JSONField(name = "rpof_duty")
    String rpofDuty;//职责
    @JSONField(name = "rpof_grade")
    String rpofGrade;//岗级
    @JSONField(name = "rpof_name")
    String  rpofName;//职位名称
    @JSONField(name = "rpof_prop")
    String  rpofProp;//职位属性
    @JSONField(name = "rpof_qual")
    String  rpofQual;//任职资格
    @JSONField(name = "rpof_seq")
    String  rpofSeq;//排序
    @JSONField(name = "rpof_status")
    String  rpofStatus;//状态
    @JSONField(name = "rpof_type")
    String  rpofType;//职位类别

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRpofCode() {
        return rpofCode;
    }

    public void setRpofCode(String rpofCode) {
        this.rpofCode = rpofCode;
    }

    public String getRpofDuty() {
        return rpofDuty;
    }

    public void setRpofDuty(String rpofDuty) {
        this.rpofDuty = rpofDuty;
    }

    public String getRpofGrade() {
        return rpofGrade;
    }

    public void setRpofGrade(String rpofGrade) {
        this.rpofGrade = rpofGrade;
    }

    public String getRpofName() {
        return rpofName;
    }

    public void setRpofName(String rpofName) {
        this.rpofName = rpofName;
    }

    public String getRpofProp() {
        return rpofProp;
    }

    public void setRpofProp(String rpofProp) {
        this.rpofProp = rpofProp;
    }

    public String getRpofQual() {
        return rpofQual;
    }

    public void setRpofQual(String rpofQual) {
        this.rpofQual = rpofQual;
    }

    public String getRpofSeq() {
        return rpofSeq;
    }

    public void setRpofSeq(String rpofSeq) {
        this.rpofSeq = rpofSeq;
    }

    public String getRpofStatus() {
        return rpofStatus;
    }

    public void setRpofStatus(String rpofStatus) {
        this.rpofStatus = rpofStatus;
    }

    public String getRpofType() {
        return rpofType;
    }

    public void setRpofType(String rpofType) {
        this.rpofType = rpofType;
    }
}
