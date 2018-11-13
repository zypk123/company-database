package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelaRelaSupplierSetaProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

import java.util.List;

/**
 * Created by xuyf on 2018/1/17 0017.
 */
public class RelaRelaSupplierSetaDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RelaRelaSupplierSetaProperty.RELA_SUPPTYPE)
    private String relaSupptype;

    @JSONField(name = RelaRelaSupplierSetaProperty.RELA_STAT_SUPP)
    private String relaStatSupp;

    @JSONField(name = RelaRelaSupplierSetaProperty.RELA_SERVDEPT_SUPP)
    private String relaServdeptSupp;

    @JSONField(name = RelaRelaSupplierSetaProperty.RELA_TAXRATE)
    private String relaTaxrate;

    @JSONField(name = RelaRelaSupplierSetaProperty.RELA_SETT_WAYS)
    private String relaSettWays;

    @JSONField(name = RelaRelaSupplierSetaProperty.RELA_CONTACT_SUPP_SET)
    private List<RelaRelaContactSuppSetbDTO> relaContactSuppSet;

    @JSONField(name = RelaRelaSupplierSetaProperty.RELA_ATTACH_SUPP_SET)
    private List<RelaRelaAttachSuppSetbDTO> relaAttachSuppSet;

    @JSONField(name = RelaRelaSupplierSetaProperty.RELA_SERVT_SUPP_SET)
    private List<RelaRelaServtSuppSetbDTO> relaServtSuppSet;

    @JSONField(name = RelaRelaSupplierSetaProperty.RELA_ACCOUNT_SUPP_SET)
    private List<RelaRelaAccountSuppSetbDTO> relaAccountSuppSet;

    @JSONField(name = RelaRelaSupplierSetaProperty.RELA_DELI_SUPP_SET)
    private List<RelaRelaDeliSuppSetbDTO> relaDeliSuppSet;

    private String relaServdeptCode;

    private String relaServdeptName;

    private String relaSettWaycStr;

    private String relaTaxrateStr;

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

    public String getRelaSupptype() {
        return relaSupptype;
    }

    public void setRelaSupptype(String relaSupptype) {
        this.relaSupptype = relaSupptype;
    }

    public String getRelaStatSupp() {
        return relaStatSupp;
    }

    public void setRelaStatSupp(String relaStatSupp) {
        this.relaStatSupp = relaStatSupp;
    }

    public String getRelaServdeptSupp() {
        return relaServdeptSupp;
    }

    public void setRelaServdeptSupp(String relaServdeptSupp) {
        this.relaServdeptSupp = relaServdeptSupp;
    }

    public String getRelaTaxrate() {
        return relaTaxrate;
    }

    public void setRelaTaxrate(String relaTaxrate) {
        this.relaTaxrate = relaTaxrate;
    }

    public String getRelaSettWays() {
        return relaSettWays;
    }

    public void setRelaSettWays(String relaSettWays) {
        this.relaSettWays = relaSettWays;
    }

    public List<RelaRelaContactSuppSetbDTO> getRelaContactSuppSet() {
        return relaContactSuppSet;
    }

    public void setRelaContactSuppSet(List<RelaRelaContactSuppSetbDTO> relaContactSuppSet) {
        this.relaContactSuppSet = relaContactSuppSet;
    }

    public List<RelaRelaAttachSuppSetbDTO> getRelaAttachSuppSet() {
        return relaAttachSuppSet;
    }

    public void setRelaAttachSuppSet(List<RelaRelaAttachSuppSetbDTO> relaAttachSuppSet) {
        this.relaAttachSuppSet = relaAttachSuppSet;
    }

    public List<RelaRelaServtSuppSetbDTO> getRelaServtSuppSet() {
        return relaServtSuppSet;
    }

    public void setRelaServtSuppSet(List<RelaRelaServtSuppSetbDTO> relaServtSuppSet) {
        this.relaServtSuppSet = relaServtSuppSet;
    }

    public List<RelaRelaAccountSuppSetbDTO> getRelaAccountSuppSet() {
        return relaAccountSuppSet;
    }

    public void setRelaAccountSuppSet(List<RelaRelaAccountSuppSetbDTO> relaAccountSuppSet) {
        this.relaAccountSuppSet = relaAccountSuppSet;
    }

    public List<RelaRelaDeliSuppSetbDTO> getRelaDeliSuppSet() {
        return relaDeliSuppSet;
    }

    public void setRelaDeliSuppSet(List<RelaRelaDeliSuppSetbDTO> relaDeliSuppSet) {
        this.relaDeliSuppSet = relaDeliSuppSet;
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

    public String getRelaTaxrateStr() {
        return relaTaxrateStr;
    }

    public void setRelaTaxrateStr(String relaTaxrateStr) {
        this.relaTaxrateStr = relaTaxrateStr;
    }
}
