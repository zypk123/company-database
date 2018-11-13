package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengchen on 2017/11/20.
 */
public class EmpPostChangeApplyDTO {

    @JSONField(name = "id")
    String id;

    /**
     * 备注
     */
    @JSONField(name="oepc_remark")
    String oepcRemark;

    /**
     * 生效日期 2017-11-12 13:11:13
     */
    @JSONField(name="oepc_beg")
    String oepcBeg;

    @JSONField(name = "oepc_chang_set")
    List<OepcChangSetDTO> oepcChangSet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OepcChangSetDTO> getOepcChangSet() {
        return oepcChangSet;
    }

    public void setOepcChangSet(List<OepcChangSetDTO> oepcChangSet) {
        this.oepcChangSet = oepcChangSet;
    }

    public String getOepcRemark() {
        return oepcRemark;
    }

    public void setOepcRemark(String oepcRemark) {
        this.oepcRemark = oepcRemark;
    }

    public String getOepcBeg() {
        return oepcBeg;
    }

    public void setOepcBeg(String oepcBeg) {
        this.oepcBeg = oepcBeg;
    }
}
