package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class ApplyPositiveDTO {
    @JSONField(name = "id")
    private String id;

    @JSONField(name = "oepa_dept")
    private String oepaDept;

    @JSONField(name = "oepa_emp")
    private String oepaEmp;

    @JSONField(name = "oepa_date")
    private String oepaDate;

    @JSONField(name = "oepa_remark")
    private String oepaRemark;

    @JSONField(name = "oepa_guild")
    private String oepaGuild;

    @JSONField(name = "oepa_report")
    private String oepaReport;

    @JSONField(name = "oepa_report_url")
    private String oepaReportUrl;

    @JSONField(name = "oepa_audit_set")
    private List<OepaAuditSetDTO> oepaAuditSet;

    @JSONField(name = "oepa_file_name")
    private String oepaFileName;

    public String getOepaFileName() {
        return oepaFileName;
    }

    public void setOepaFileName(String oepaFileName) {
        this.oepaFileName = oepaFileName;
    }
    @JSONField(name = "id")
    private String oepaId;

    public String getOepaId() {
        return oepaId;
    }

    public void setOepaId(String oepaId) {
        this.oepaId = oepaId;
    }

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

    @JSONField(name = "oepa_ext_date")
    private String oepaExtDate;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOepaDept() {
        return oepaDept;
    }

    public void setOepaDept(String oepaDept) {
        this.oepaDept = oepaDept;
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

    public String getOepaRemark() {
        return oepaRemark;
    }

    public void setOepaRemark(String oepaRemark) {
        this.oepaRemark = oepaRemark;
    }

    public String getOepaGuild() {
        return oepaGuild;
    }

    public void setOepaGuild(String oepaGuild) {
        this.oepaGuild = oepaGuild;
    }

    public String getOepaReport() {
        return oepaReport;
    }

    public void setOepaReport(String oepaReport) {
        this.oepaReport = oepaReport;
    }

    public String getOepaReportUrl() {
        return oepaReportUrl;
    }

    public void setOepaReportUrl(String oepaReportUrl) {
        this.oepaReportUrl = oepaReportUrl;
    }

    public List<OepaAuditSetDTO> getOepaAuditSet() {
        return oepaAuditSet;
    }

    public void setOepaAuditSet(List<OepaAuditSetDTO> oepaAuditSet) {
        this.oepaAuditSet = oepaAuditSet;
    }

}
