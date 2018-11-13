package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author zhanggj
 * @createTime 2017/11/15
 * @desc
 */
public class JobPositonChagSetDTO {
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "pid")
    private String pid;
    @JSONField(name = "rpos_post_his")
    private String rpoPostHis;
    @JSONField(name = "rpos_name_his")
    private String rposNameHis;
    @JSONField(name = "rpos_prep_his")
    private String rposPrepHis;
    @JSONField(name = "rpos_grade_his")
    private String rposGradeHis;
    @JSONField(name = "rpos_duty_his")
    private String rposDutyHis;
    @JSONField(name = "rpos_qual_his")
    private String rposQualHis;
    @JSONField(name = "rpos_beg_his")
    private String rposBegHis;
    @JSONField(name = "rpos_end_his")
    private String rposEndHis;

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

    public String getRpoPostHis() {
        return rpoPostHis;
    }

    public void setRpoPostHis(String rpoPostHis) {
        this.rpoPostHis = rpoPostHis;
    }

    public String getRposNameHis() {
        return rposNameHis;
    }

    public void setRposNameHis(String rposNameHis) {
        this.rposNameHis = rposNameHis;
    }

    public String getRposPrepHis() {
        return rposPrepHis;
    }

    public void setRposPrepHis(String rposPrepHis) {
        this.rposPrepHis = rposPrepHis;
    }

    public String getRposGradeHis() {
        return rposGradeHis;
    }

    public void setRposGradeHis(String rposGradeHis) {
        this.rposGradeHis = rposGradeHis;
    }

    public String getRposDutyHis() {
        return rposDutyHis;
    }

    public void setRposDutyHis(String rposDutyHis) {
        this.rposDutyHis = rposDutyHis;
    }

    public String getRposQualHis() {
        return rposQualHis;
    }

    public void setRposQualHis(String rposQualHis) {
        this.rposQualHis = rposQualHis;
    }

    public String getRposBegHis() {
        return rposBegHis;
    }

    public void setRposBegHis(String rposBegHis) {
        this.rposBegHis = rposBegHis;
    }

    public String getRposEndHis() {
        return rposEndHis;
    }

    public void setRposEndHis(String rposEndHis) {
        this.rposEndHis = rposEndHis;
    }
}
