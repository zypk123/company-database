package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by chengchen on 2017/11/22.
 */
public class EmpPostEditApplyDTO {
    /**
     * 单据id
     */
    @JSONField(name="id")
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

    public String getOepcBeg() {
        return oepcBeg;
    }

    public void setOepcBeg(String oepcBeg) {
        this.oepcBeg = oepcBeg;
    }

    public String getOepcRemark() {
        return oepcRemark;
    }

    public void setOepcRemark(String oepcRemark) {
        this.oepcRemark = oepcRemark;
    }

    /**
     * 属性集
     */
    @JSONField(name="oepc_chang_set")
    List<OepcEditSetDTO> oepcChangSet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OepcEditSetDTO> getOepcChangSet() {
        return oepcChangSet;
    }

    public void setOepcChangSet(List<OepcEditSetDTO> oepcChangSet) {
        this.oepcChangSet = oepcChangSet;
    }
}
