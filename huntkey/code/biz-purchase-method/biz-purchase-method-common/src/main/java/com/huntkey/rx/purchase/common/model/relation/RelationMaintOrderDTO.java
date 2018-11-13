package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelationmaintorderProperty;
import com.huntkey.rx.purchase.common.model.base.OrderDTO;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by xuyf on 2018/1/22 0022.
 */
public class RelationMaintOrderDTO extends OrderDTO {

    /**
     * 伙伴id，用于编辑时提供
     */
    private String relaId;

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = RelationmaintorderProperty.REMO_CODE)
    private String remoCode;

    @JSONField(name = RelationmaintorderProperty.REMO_SHORT_NAME)
    private String remoShortName;

    @JSONField(name = RelationmaintorderProperty.REMO_NAME)
    private String remoName;

    @JSONField(name = RelationmaintorderProperty.REMO_REMARK)
    private String remoRemark;

    @JSONField(name = RelationmaintorderProperty.REMO_ADDRP)
    private String remoAddrp;

    @JSONField(name = RelationmaintorderProperty.REMO_ADDRC)
    private String remoAddrc;

    @JSONField(name = RelationmaintorderProperty.REMO_ADDRL)
    private String remoAddrl;

    @JSONField(name = RelationmaintorderProperty.REMO_DADDR)
    private String remoDaddr;

    @JSONField(name = RelationmaintorderProperty.REMO_PCODE)
    private String remoPcode;

    @JSONField(name = RelationmaintorderProperty.REMO_TEL)
    private String remoTel;

    @JSONField(name = RelationmaintorderProperty.REMO_FAX)
    private String remoFax;

    @JSONField(name = RelationmaintorderProperty.REMO_WEB)
    private String remoWeb;

    @JSONField(name = RelationmaintorderProperty.REMO_MPROD)
    private String remoMprod;

    @JSONField(name = RelationmaintorderProperty.REMO_CHARACTER)
    private String remoCharacter;

    @JSONField(name = RelationmaintorderProperty.REMO_LISTEXCH)
    private String remoListexch;

    @JSONField(name = RelationmaintorderProperty.REMO_LISTNAME)
    private String remoListname;

    @JSONField(name = RelationmaintorderProperty.REMO_LISTCODE)
    private String remoListcode;

    @JSONField(name = RelationmaintorderProperty.REMO_USCC)
    private String remoUscc;

    @JSONField(name = RelationmaintorderProperty.REMO_RADDR)
    private String remoRaddr;

    @JSONField(name = RelationmaintorderProperty.REMO_RDATE)
    private Date remoRdate;

    @JSONField(name = RelationmaintorderProperty.REMO_LREP)
    private String remoLrep;

    @JSONField(name = RelationmaintorderProperty.REMO_LADDR)
    private String remoLaddr;

    @JSONField(name = RelationmaintorderProperty.REMO_RCURR)
    private String remoRcurr;

    @JSONField(name = RelationmaintorderProperty.REMO_RCAPITAL)
    private BigDecimal remoRcapital;

    @JSONField(name = RelationmaintorderProperty.REMO_SHORT_NAME_OLD)
    private String remoShortNameOld;

    @JSONField(name = RelationmaintorderProperty.REMO_NAME_OLD)
    private String remoNameOld;

    @JSONField(name = RelationmaintorderProperty.REMO_REMARK_OLD)
    private String remoRemarkOld;

    @JSONField(name = RelationmaintorderProperty.REMO_ADDRP_OLD)
    private String remoAddrpOld;

    @JSONField(name = RelationmaintorderProperty.REMO_ADDRC_OLD)
    private String remoAddrcOld;

    @JSONField(name = RelationmaintorderProperty.REMO_ADDRL_OLD)
    private String remoAddrlOld;

    @JSONField(name = RelationmaintorderProperty.REMO_DADDR_OLD)
    private String remoDaddrOld;

    @JSONField(name = RelationmaintorderProperty.REMO_PCODE_OLD)
    private String remoPcodeOld;

    @JSONField(name = RelationmaintorderProperty.REMO_TEL_OLD)
    private String remoTelOld;

    @JSONField(name = RelationmaintorderProperty.REMO_FAX_OLD)
    private String remoFaxOld;

    @JSONField(name = RelationmaintorderProperty.REMO_WEB_OLD)
    private String remoWebOld;

    @JSONField(name = RelationmaintorderProperty.REMO_MPROD_OLD)
    private String remoMprodOld;

    @JSONField(name = RelationmaintorderProperty.REMO_CHARACTER_OLD)
    private String remoCharacterOld;

    @JSONField(name = RelationmaintorderProperty.REMO_LISTEXCH_OLD)
    private String remoListexchOld;

    @JSONField(name = RelationmaintorderProperty.REMO_LISTNAME_OLD)
    private String remoListnameOld;

    @JSONField(name = RelationmaintorderProperty.REMO_LISTCODE_OLD)
    private String remoListcodeOld;

    @JSONField(name = RelationmaintorderProperty.REMO_RADDR_OLD)
    private String remoRaddrOld;

    @JSONField(name = RelationmaintorderProperty.REMO_RDATE_OLD)
    private Date remoRdateOld;

    @JSONField(name = RelationmaintorderProperty.REMO_LREP_OLD)
    private String remoLrepOld;

    @JSONField(name = RelationmaintorderProperty.REMO_LADDR_OLD)
    private String remoLaddrOld;

    @JSONField(name = RelationmaintorderProperty.REMO_RCURR_OLD)
    private String remoRcurrOld;

    @JSONField(name = RelationmaintorderProperty.REMO_RCAPITAL_OLD)
    private BigDecimal remoRcapitalOld;

    @JSONField(name = RelationmaintorderProperty.REMO_SYSTEM_SET)
    private List<RemoSystemSetDTO> remoSystemSet;

    @JSONField(name = RelationmaintorderProperty.REMO_HOLDER_SET)
    private List<RemoHolderSetDTO> remoHolderSet;

    private boolean isSubmit = false;

    public String getRelaId() {
        return relaId;
    }

    public void setRelaId(String relaId) {
        this.relaId = relaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemoCode() {
        return remoCode;
    }

    public void setRemoCode(String remoCode) {
        this.remoCode = remoCode;
    }

    public String getRemoShortName() {
        return remoShortName;
    }

    public void setRemoShortName(String remoShortName) {
        this.remoShortName = remoShortName;
    }

    public String getRemoName() {
        return remoName;
    }

    public void setRemoName(String remoName) {
        this.remoName = remoName;
    }

    public String getRemoRemark() {
        return remoRemark;
    }

    public void setRemoRemark(String remoRemark) {
        this.remoRemark = remoRemark;
    }

    public String getRemoAddrp() {
        return remoAddrp;
    }

    public void setRemoAddrp(String remoAddrp) {
        this.remoAddrp = remoAddrp;
    }

    public String getRemoAddrc() {
        return remoAddrc;
    }

    public void setRemoAddrc(String remoAddrc) {
        this.remoAddrc = remoAddrc;
    }

    public String getRemoAddrl() {
        return remoAddrl;
    }

    public void setRemoAddrl(String remoAddrl) {
        this.remoAddrl = remoAddrl;
    }

    public String getRemoDaddr() {
        return remoDaddr;
    }

    public void setRemoDaddr(String remoDaddr) {
        this.remoDaddr = remoDaddr;
    }

    public String getRemoPcode() {
        return remoPcode;
    }

    public void setRemoPcode(String remoPcode) {
        this.remoPcode = remoPcode;
    }

    public String getRemoTel() {
        return remoTel;
    }

    public void setRemoTel(String remoTel) {
        this.remoTel = remoTel;
    }

    public String getRemoFax() {
        return remoFax;
    }

    public void setRemoFax(String remoFax) {
        this.remoFax = remoFax;
    }

    public String getRemoWeb() {
        return remoWeb;
    }

    public void setRemoWeb(String remoWeb) {
        this.remoWeb = remoWeb;
    }

    public String getRemoMprod() {
        return remoMprod;
    }

    public void setRemoMprod(String remoMprod) {
        this.remoMprod = remoMprod;
    }

    public String getRemoCharacter() {
        return remoCharacter;
    }

    public void setRemoCharacter(String remoCharacter) {
        this.remoCharacter = remoCharacter;
    }

    public String getRemoListexch() {
        return remoListexch;
    }

    public void setRemoListexch(String remoListexch) {
        this.remoListexch = remoListexch;
    }

    public String getRemoListname() {
        return remoListname;
    }

    public void setRemoListname(String remoListname) {
        this.remoListname = remoListname;
    }

    public String getRemoListcode() {
        return remoListcode;
    }

    public void setRemoListcode(String remoListcode) {
        this.remoListcode = remoListcode;
    }

    public String getRemoUscc() {
        return remoUscc;
    }

    public void setRemoUscc(String remoUscc) {
        this.remoUscc = remoUscc;
    }

    public String getRemoRaddr() {
        return remoRaddr;
    }

    public void setRemoRaddr(String remoRaddr) {
        this.remoRaddr = remoRaddr;
    }

    public Date getRemoRdate() {
        return remoRdate;
    }

    public void setRemoRdate(Date remoRdate) {
        this.remoRdate = remoRdate;
    }

    public String getRemoLrep() {
        return remoLrep;
    }

    public void setRemoLrep(String remoLrep) {
        this.remoLrep = remoLrep;
    }

    public String getRemoLaddr() {
        return remoLaddr;
    }

    public void setRemoLaddr(String remoLaddr) {
        this.remoLaddr = remoLaddr;
    }

    public String getRemoRcurr() {
        return remoRcurr;
    }

    public void setRemoRcurr(String remoRcurr) {
        this.remoRcurr = remoRcurr;
    }

    public BigDecimal getRemoRcapital() {
        return remoRcapital;
    }

    public void setRemoRcapital(BigDecimal remoRcapital) {
        this.remoRcapital = remoRcapital;
    }

    public String getRemoShortNameOld() {
        return remoShortNameOld;
    }

    public void setRemoShortNameOld(String remoShortNameOld) {
        this.remoShortNameOld = remoShortNameOld;
    }

    public String getRemoNameOld() {
        return remoNameOld;
    }

    public void setRemoNameOld(String remoNameOld) {
        this.remoNameOld = remoNameOld;
    }

    public String getRemoRemarkOld() {
        return remoRemarkOld;
    }

    public void setRemoRemarkOld(String remoRemarkOld) {
        this.remoRemarkOld = remoRemarkOld;
    }

    public String getRemoAddrpOld() {
        return remoAddrpOld;
    }

    public void setRemoAddrpOld(String remoAddrpOld) {
        this.remoAddrpOld = remoAddrpOld;
    }

    public String getRemoAddrcOld() {
        return remoAddrcOld;
    }

    public void setRemoAddrcOld(String remoAddrcOld) {
        this.remoAddrcOld = remoAddrcOld;
    }

    public String getRemoAddrlOld() {
        return remoAddrlOld;
    }

    public void setRemoAddrlOld(String remoAddrlOld) {
        this.remoAddrlOld = remoAddrlOld;
    }

    public String getRemoDaddrOld() {
        return remoDaddrOld;
    }

    public void setRemoDaddrOld(String remoDaddrOld) {
        this.remoDaddrOld = remoDaddrOld;
    }

    public String getRemoPcodeOld() {
        return remoPcodeOld;
    }

    public void setRemoPcodeOld(String remoPcodeOld) {
        this.remoPcodeOld = remoPcodeOld;
    }

    public String getRemoTelOld() {
        return remoTelOld;
    }

    public void setRemoTelOld(String remoTelOld) {
        this.remoTelOld = remoTelOld;
    }

    public String getRemoFaxOld() {
        return remoFaxOld;
    }

    public void setRemoFaxOld(String remoFaxOld) {
        this.remoFaxOld = remoFaxOld;
    }

    public String getRemoWebOld() {
        return remoWebOld;
    }

    public void setRemoWebOld(String remoWebOld) {
        this.remoWebOld = remoWebOld;
    }

    public String getRemoMprodOld() {
        return remoMprodOld;
    }

    public void setRemoMprodOld(String remoMprodOld) {
        this.remoMprodOld = remoMprodOld;
    }

    public String getRemoCharacterOld() {
        return remoCharacterOld;
    }

    public void setRemoCharacterOld(String remoCharacterOld) {
        this.remoCharacterOld = remoCharacterOld;
    }

    public String getRemoListexchOld() {
        return remoListexchOld;
    }

    public void setRemoListexchOld(String remoListexchOld) {
        this.remoListexchOld = remoListexchOld;
    }

    public String getRemoListnameOld() {
        return remoListnameOld;
    }

    public void setRemoListnameOld(String remoListnameOld) {
        this.remoListnameOld = remoListnameOld;
    }

    public String getRemoListcodeOld() {
        return remoListcodeOld;
    }

    public void setRemoListcodeOld(String remoListcodeOld) {
        this.remoListcodeOld = remoListcodeOld;
    }

    public String getRemoRaddrOld() {
        return remoRaddrOld;
    }

    public void setRemoRaddrOld(String remoRaddrOld) {
        this.remoRaddrOld = remoRaddrOld;
    }

    public Date getRemoRdateOld() {
        return remoRdateOld;
    }

    public void setRemoRdateOld(Date remoRdateOld) {
        this.remoRdateOld = remoRdateOld;
    }

    public String getRemoLrepOld() {
        return remoLrepOld;
    }

    public void setRemoLrepOld(String remoLrepOld) {
        this.remoLrepOld = remoLrepOld;
    }

    public String getRemoLaddrOld() {
        return remoLaddrOld;
    }

    public void setRemoLaddrOld(String remoLaddrOld) {
        this.remoLaddrOld = remoLaddrOld;
    }

    public String getRemoRcurrOld() {
        return remoRcurrOld;
    }

    public void setRemoRcurrOld(String remoRcurrOld) {
        this.remoRcurrOld = remoRcurrOld;
    }

    public BigDecimal getRemoRcapitalOld() {
        return remoRcapitalOld;
    }

    public void setRemoRcapitalOld(BigDecimal remoRcapitalOld) {
        this.remoRcapitalOld = remoRcapitalOld;
    }

    public List<RemoSystemSetDTO> getRemoSystemSet() {
        return remoSystemSet;
    }

    public void setRemoSystemSet(List<RemoSystemSetDTO> remoSystemSet) {
        this.remoSystemSet = remoSystemSet;
    }

    public List<RemoHolderSetDTO> getRemoHolderSet() {
        return remoHolderSet;
    }

    public void setRemoHolderSet(List<RemoHolderSetDTO> remoHolderSet) {
        this.remoHolderSet = remoHolderSet;
    }

    public boolean isSubmit() {
        return isSubmit;
    }

    public void setSubmit(boolean submit) {
        isSubmit = submit;
    }
}
