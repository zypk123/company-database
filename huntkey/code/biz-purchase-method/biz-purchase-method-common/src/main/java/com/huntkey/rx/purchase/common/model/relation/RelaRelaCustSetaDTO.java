package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelaRelaCustSetaProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xuyf on 2018/1/17 0017.
 */
public class RelaRelaCustSetaDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RelaRelaCustSetaProperty.RELA_STAT_CUST)
    private String relaStatCust;

    @JSONField(name = RelaRelaCustSetaProperty.RELA_SERVDEPT_CUST)
    private String relaServdeptCust;

    @JSONField(name = RelaRelaCustSetaProperty.RELA_CREDLIMIT)
    private Integer relaCredlimit;

    @JSONField(name = RelaRelaCustSetaProperty.RELA_SETT_WAYC)
    private String relaSettWayc;

    @JSONField(name = RelaRelaCustSetaProperty.RELA_CONTACT_CUST_SET)
    private List<RelaRelaContactCustSetbDTO> relaContactCustSet;

    @JSONField(name = RelaRelaCustSetaProperty.RELA_ATTACH_CUST_SET)
    private List<RelaRelaAttachCustSetbDTO> relaAttachCustSet;

    @JSONField(name = RelaRelaCustSetaProperty.RELA_SERVT_CUST_SET)
    private List<RelaRelaServtCustSetbDTO> relaServtCustSet;

    @JSONField(name = RelaRelaCustSetaProperty.RELA_ACCOUNT_CUST_SET)
    private List<RelaRelaAccountCustSetbDTO> relaAccountCustSet;

    @JSONField(name = RelaRelaCustSetaProperty.RELA_DELI_CUST_SET)
    private List<RelaRelaDeliCustSetbDTO> relaDeliCustSet;

    @JSONField(name = RelaRelaCustSetaProperty.RELA_CRED_REMAIN)
    private BigDecimal relaCredRemain;

    private String relaServdeptCode;

    private String relaServdeptName;

    private String relaSettWaycStr;

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

    public String getRelaStatCust() {
        return relaStatCust;
    }

    public void setRelaStatCust(String relaStatCust) {
        this.relaStatCust = relaStatCust;
    }

    public String getRelaServdeptCust() {
        return relaServdeptCust;
    }

    public void setRelaServdeptCust(String relaServdeptCust) {
        this.relaServdeptCust = relaServdeptCust;
    }

    public Integer getRelaCredlimit() {
        return relaCredlimit;
    }

    public void setRelaCredlimit(Integer relaCredlimit) {
        this.relaCredlimit = relaCredlimit;
    }

    public String getRelaSettWayc() {
        return relaSettWayc;
    }

    public void setRelaSettWayc(String relaSettWayc) {
        this.relaSettWayc = relaSettWayc;
    }

    public List<RelaRelaContactCustSetbDTO> getRelaContactCustSet() {
        return relaContactCustSet;
    }

    public void setRelaContactCustSet(List<RelaRelaContactCustSetbDTO> relaContactCustSet) {
        this.relaContactCustSet = relaContactCustSet;
    }

    public List<RelaRelaAttachCustSetbDTO> getRelaAttachCustSet() {
        return relaAttachCustSet;
    }

    public void setRelaAttachCustSet(List<RelaRelaAttachCustSetbDTO> relaAttachCustSet) {
        this.relaAttachCustSet = relaAttachCustSet;
    }

    public List<RelaRelaServtCustSetbDTO> getRelaServtCustSet() {
        return relaServtCustSet;
    }

    public void setRelaServtCustSet(List<RelaRelaServtCustSetbDTO> relaServtCustSet) {
        this.relaServtCustSet = relaServtCustSet;
    }

    public List<RelaRelaAccountCustSetbDTO> getRelaAccountCustSet() {
        return relaAccountCustSet;
    }

    public void setRelaAccountCustSet(List<RelaRelaAccountCustSetbDTO> relaAccountCustSet) {
        this.relaAccountCustSet = relaAccountCustSet;
    }

    public List<RelaRelaDeliCustSetbDTO> getRelaDeliCustSet() {
        return relaDeliCustSet;
    }

    public void setRelaDeliCustSet(List<RelaRelaDeliCustSetbDTO> relaDeliCustSet) {
        this.relaDeliCustSet = relaDeliCustSet;
    }

    public BigDecimal getRelaCredRemain() {
        return relaCredRemain;
    }

    public void setRelaCredRemain(BigDecimal relaCredRemain) {
        this.relaCredRemain = relaCredRemain;
    }

    public String getRelaServdeptCode() {
        return relaServdeptCode;
    }

    public void setRelaServdeptCode(String relaServdeptCode) {
        this.relaServdeptCode = relaServdeptCode;
    }

    public String getRelaServdeptName() {
        return relaServdeptName;
    }

    public void setRelaServdeptName(String relaServdeptName) {
        this.relaServdeptName = relaServdeptName;
    }

    public String getRelaSettWaycStr() {
        return relaSettWaycStr;
    }

    public void setRelaSettWaycStr(String relaSettWaycStr) {
        this.relaSettWaycStr = relaSettWaycStr;
    }
}
