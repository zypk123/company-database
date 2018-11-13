package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

public class OepaAuditSetDTO {
    @JSONField(name = "id")
    private String id;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @JSONField(name = "pid")
    private String pid;

    @JSONField(name = "oepa_auditor")
    private String oepaAuditor;

    @JSONField(name = "oepa_audit_idea")
    private String oepaAuditIdea;

    @JSONField(name = "oepa_evaluation")
    private String oepaEvaluation;

    @JSONField(name = "oepa_ext_month")
    private String oepaExtMonth;

    @JSONField(name = "oepa_ext_date")
    private String oepaExtDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOepaAuditor() {
        return oepaAuditor;
    }

    public void setOepaAuditor(String oepaAuditor) {
        this.oepaAuditor = oepaAuditor;
    }

    public String getOepaAuditIdea() {
        return oepaAuditIdea;
    }

    public void setOepaAuditIdea(String oepaAuditIdea) {
        this.oepaAuditIdea = oepaAuditIdea;
    }

    public String getOepaEvaluation() {
        return oepaEvaluation;
    }

    public void setOepaEvaluation(String oepaEvaluation) {
        this.oepaEvaluation = oepaEvaluation;
    }

    public String getOepaExtMonth() {
        return oepaExtMonth;
    }

    public void setOepaExtMonth(String oepaExtMonth) {
        this.oepaExtMonth = oepaExtMonth;
    }

    public String getOepaExtDate() {
        return oepaExtDate;
    }

    public void setOepaExtDate(String oepaExtDate) {
        this.oepaExtDate = oepaExtDate;
    }


}
