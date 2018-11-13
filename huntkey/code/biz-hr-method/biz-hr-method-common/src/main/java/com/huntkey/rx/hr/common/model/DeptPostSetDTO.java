package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author zhanggj
 * @createTime 2017/11/14
 * @desc
 */
public class DeptPostSetDTO {

    @JSONField(name = "id")
    private String id;
    @JSONField(name = "pid")
    private String pid;
    @JSONField(name = "odps_name")
    private String odpsName;
    @JSONField(name = "odps_dept")
    private String odpsDept;
    @JSONField(name = "odps_rpof")
    private String odpsRpof;
    @JSONField(name = "odps_pgrade")
    private String odpsPgrade;
    @JSONField(name = "odps_post")
    private String odpsPost;
    @JSONField(name = "odps_ppost")
    private String odpsPpost;
    @JSONField(name = "odps_duty")
    private String odpsDuty;
    @JSONField(name = "odps_qual")
    private String odpsQual;
    @JSONField(name = "odps_sub")
    private String odpsSub;
    @JSONField(name = "odps_name_old")
    private String odpsNameOld;
    @JSONField(name = "odps_pgrade_old")
    private String odpsPgradeOld;
    @JSONField(name = "odps_ppost_old")
    private String odpsPpostOld;
    @JSONField(name = "odps_duty_old")
    private String odpsDutyOld;
    @JSONField(name = "odps_qual_old")
    private String odpsQualOld;
    @JSONField(name = "odps_lvl")
    private String odpsLvl;
    @JSONField(name = "odps_flag")
    private String odps_flag;

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

    public String getOdpsName() {
        return odpsName;
    }

    public void setOdpsName(String odpsName) {
        this.odpsName = odpsName;
    }

    public String getOdpsDept() {
        return odpsDept;
    }

    public void setOdpsDept(String odpsDept) {
        this.odpsDept = odpsDept;
    }

    public String getOdpsPost() {
        return odpsPost;
    }

    public void setOdpsPost(String odpsPost) {
        this.odpsPost = odpsPost;
    }

    public String getOdpsRpof() {
        return odpsRpof;
    }

    public void setOdpsRpof(String odpsRpof) {
        this.odpsRpof = odpsRpof;
    }

    public String getOdpsPgrade() {
        return odpsPgrade;
    }

    public void setOdpsPgrade(String odpsPgrade) {
        this.odpsPgrade = odpsPgrade;
    }

    public String getOdpsDuty() {
        return odpsDuty;
    }

    public void setOdpsDuty(String odpsDuty) {
        this.odpsDuty = odpsDuty;
    }

    public String getOdpsQual() {
        return odpsQual;
    }

    public void setOdpsQual(String odpsQual) {
        this.odpsQual = odpsQual;
    }

    public String getOdpsPpost() {
        return odpsPpost;
    }

    public void setOdpsPpost(String odpsPpost) {
        this.odpsPpost = odpsPpost;
    }

    public String getOdpsSub() {
        return odpsSub;
    }

    public void setOdpsSub(String odpsSub) {
        this.odpsSub = odpsSub;
    }

    public String getOdpsNameOld() {
        return odpsNameOld;
    }

    public void setOdpsNameOld(String odpsNameOld) {
        this.odpsNameOld = odpsNameOld;
    }

    public String getOdpsPgradeOld() {
        return odpsPgradeOld;
    }

    public void setOdpsPgradeOld(String odpsPgradeOld) {
        this.odpsPgradeOld = odpsPgradeOld;
    }

    public String getOdpsPpostOld() {
        return odpsPpostOld;
    }

    public void setOdpsPpostOld(String odpsPpostOld) {
        this.odpsPpostOld = odpsPpostOld;
    }

    public String getOdpsDutyOld() {
        return odpsDutyOld;
    }

    public void setOdpsDutyOld(String odpsDutyOld) {
        this.odpsDutyOld = odpsDutyOld;
    }

    public String getOdpsQualOld() {
        return odpsQualOld;
    }

    public void setOdpsQualOld(String odpsQualOld) {
        this.odpsQualOld = odpsQualOld;
    }

    public String getOdpsLvl() {
        return odpsLvl;
    }

    public void setOdpsLvl(String odpsLvl) {
        this.odpsLvl = odpsLvl;
    }

    public String getOdps_flag() {
        return odps_flag;
    }

    public void setOdps_flag(String odps_flag) {
        this.odps_flag = odps_flag;
    }

}
