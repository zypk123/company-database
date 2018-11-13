package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

public class OepaPositiveApplyDto {
    @JSONField(name = "oepa_audit_idea")
    String oepaAuditIdea;

    @JSONField(name = "oepa_ext_date")
    String oepaExtDate;

    @JSONField(name = "oepa_emp")
    private String oepaEmp;

    @JSONField(name = "oepa_date")
    private String oepaDate;

    public String getOepaAuditIdea() {
        return oepaAuditIdea;
    }

    public void setOepaAuditIdea(String oepaAuditIdea) {
        this.oepaAuditIdea = oepaAuditIdea;
    }

    public String getOepaExtDate() {
        return oepaExtDate;
    }

    public void setOepaExtDate(String oepaExtDate) {
        this.oepaExtDate = oepaExtDate;
    }

    public String getOepaEmp() {
        return oepaEmp;
    }

    public void setOepaEmp(String oepaEmp) {
        this.oepaEmp = oepaEmp;
    }

    public String getOepaDate() {
        return oepaDate;
    }

    public void setOepaDate(String oepaDate) {
        this.oepaDate = oepaDate;
    }
}
