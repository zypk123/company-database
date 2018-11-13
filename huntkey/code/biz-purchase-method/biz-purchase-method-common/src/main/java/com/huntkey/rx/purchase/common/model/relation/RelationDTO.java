package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelationProperty;
import com.huntkey.rx.purchase.common.model.base.EdmDTO;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by xuyf on 2018/1/17 0017.
 */
public class RelationDTO extends EdmDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = RelationProperty.RELA_CODE)
    private String relaCode;

    @JSONField(name = RelationProperty.RELA_NAME)
    private String relaName;

    @JSONField(name = RelationProperty.RELA_SHORT_NAME)
    private String relaShortName;

    @JSONField(name = RelationProperty.RELA_REMARK)
    private String relaRemark;

    @JSONField(name = RelationProperty.RELA_TYPES_SET)
    private List<RelaRelaTypesSetaDTO> relaTypesSet;

    @JSONField(name = RelationProperty.RELA_SUPPLIER_SET)
    private List<RelaRelaSupplierSetaDTO> relaSupplierSet;

    @JSONField(name = RelationProperty.RELA_CUST_SET)
    private List<RelaRelaCustSetaDTO> relaCustSet;

    @JSONField(name = RelationProperty.RELA_HOLDER_SET)
    private List<RelaRelaHolderSetaDTO> relaHolderSet;

    @JSONField(name = RelationProperty.RELA_ADDRP)
    private String relaAddrp;

    @JSONField(name = RelationProperty.RELA_ADDRC)
    private String relaAddrc;

    @JSONField(name = RelationProperty.RELA_ADDRL)
    private String relaAddrl;

    @JSONField(name = RelationProperty.RELA_DADDR)
    private String relaDaddr;

    @JSONField(name = RelationProperty.RELA_PCODE)
    private String relaPcode;

    @JSONField(name = RelationProperty.RELA_TEL)
    private String relaTel;

    @JSONField(name = RelationProperty.RELA_FAX)
    private String relaFax;

    @JSONField(name = RelationProperty.RELA_WEB)
    private String relaWeb;

    @JSONField(name = RelationProperty.RELA_MPROD)
    private String relaMprod;

    @JSONField(name = RelationProperty.RELA_CHARACTER)
    private String relaCharacter;

    @JSONField(name = RelationProperty.RELA_LISTEXCH)
    private String relaListexch;

    @JSONField(name = RelationProperty.RELA_LISTNAME)
    private String relaListname;

    @JSONField(name = RelationProperty.RELA_LISTCODE)
    private String relaListcode;

    @JSONField(name = RelationProperty.RELA_USCC)
    private String relaUscc;

    @JSONField(name = RelationProperty.RELA_RADDR)
    private String relaRaddr;

    @JSONField(name = RelationProperty.RELA_RDATE)
    private Date relaRdate;

    @JSONField(name = RelationProperty.RELA_LREP)
    private String relaLrep;

    @JSONField(name = RelationProperty.RELA_LADDR)
    private String relaLaddr;

    @JSONField(name = RelationProperty.RELA_RCURR)
    private String relaRcurr;

    @JSONField(name = RelationProperty.RELA_RCAPITAL)
    private BigDecimal relaRcapital;

    @JSONField(name = RelationProperty.RELA_SYSTEM_SET)
    private List<RelaRelaSystemSetaDTO> relaSystemSet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelaCode() {
        return relaCode;
    }

    public void setRelaCode(String relaCode) {
        this.relaCode = relaCode;
    }

    public String getRelaName() {
        return relaName;
    }

    public void setRelaName(String relaName) {
        this.relaName = relaName;
    }

    public String getRelaShortName() {
        return relaShortName;
    }

    public void setRelaShortName(String relaShortName) {
        this.relaShortName = relaShortName;
    }

    public String getRelaRemark() {
        return relaRemark;
    }

    public void setRelaRemark(String relaRemark) {
        this.relaRemark = relaRemark;
    }

    public List<RelaRelaTypesSetaDTO> getRelaTypesSet() {
        return relaTypesSet;
    }

    public void setRelaTypesSet(List<RelaRelaTypesSetaDTO> relaTypesSet) {
        this.relaTypesSet = relaTypesSet;
    }

    public List<RelaRelaSupplierSetaDTO> getRelaSupplierSet() {
        return relaSupplierSet;
    }

    public void setRelaSupplierSet(List<RelaRelaSupplierSetaDTO> relaSupplierSet) {
        this.relaSupplierSet = relaSupplierSet;
    }

    public List<RelaRelaCustSetaDTO> getRelaCustSet() {
        return relaCustSet;
    }

    public void setRelaCustSet(List<RelaRelaCustSetaDTO> relaCustSet) {
        this.relaCustSet = relaCustSet;
    }

    public List<RelaRelaHolderSetaDTO> getRelaHolderSet() {
        return relaHolderSet;
    }

    public void setRelaHolderSet(List<RelaRelaHolderSetaDTO> relaHolderSet) {
        this.relaHolderSet = relaHolderSet;
    }

    public String getRelaAddrp() {
        return relaAddrp;
    }

    public void setRelaAddrp(String relaAddrp) {
        this.relaAddrp = relaAddrp;
    }

    public String getRelaAddrc() {
        return relaAddrc;
    }

    public void setRelaAddrc(String relaAddrc) {
        this.relaAddrc = relaAddrc;
    }

    public String getRelaAddrl() {
        return relaAddrl;
    }

    public void setRelaAddrl(String relaAddrl) {
        this.relaAddrl = relaAddrl;
    }

    public String getRelaDaddr() {
        return relaDaddr;
    }

    public void setRelaDaddr(String relaDaddr) {
        this.relaDaddr = relaDaddr;
    }

    public String getRelaPcode() {
        return relaPcode;
    }

    public void setRelaPcode(String relaPcode) {
        this.relaPcode = relaPcode;
    }

    public String getRelaTel() {
        return relaTel;
    }

    public void setRelaTel(String relaTel) {
        this.relaTel = relaTel;
    }

    public String getRelaFax() {
        return relaFax;
    }

    public void setRelaFax(String relaFax) {
        this.relaFax = relaFax;
    }

    public String getRelaWeb() {
        return relaWeb;
    }

    public void setRelaWeb(String relaWeb) {
        this.relaWeb = relaWeb;
    }

    public String getRelaMprod() {
        return relaMprod;
    }

    public void setRelaMprod(String relaMprod) {
        this.relaMprod = relaMprod;
    }

    public String getRelaCharacter() {
        return relaCharacter;
    }

    public void setRelaCharacter(String relaCharacter) {
        this.relaCharacter = relaCharacter;
    }

    public String getRelaListexch() {
        return relaListexch;
    }

    public void setRelaListexch(String relaListexch) {
        this.relaListexch = relaListexch;
    }

    public String getRelaListname() {
        return relaListname;
    }

    public void setRelaListname(String relaListname) {
        this.relaListname = relaListname;
    }

    public String getRelaListcode() {
        return relaListcode;
    }

    public void setRelaListcode(String relaListcode) {
        this.relaListcode = relaListcode;
    }

    public String getRelaUscc() {
        return relaUscc;
    }

    public void setRelaUscc(String relaUscc) {
        this.relaUscc = relaUscc;
    }

    public String getRelaRaddr() {
        return relaRaddr;
    }

    public void setRelaRaddr(String relaRaddr) {
        this.relaRaddr = relaRaddr;
    }

    public Date getRelaRdate() {
        return relaRdate;
    }

    public void setRelaRdate(Date relaRdate) {
        this.relaRdate = relaRdate;
    }

    public String getRelaLrep() {
        return relaLrep;
    }

    public void setRelaLrep(String relaLrep) {
        this.relaLrep = relaLrep;
    }

    public String getRelaLaddr() {
        return relaLaddr;
    }

    public void setRelaLaddr(String relaLaddr) {
        this.relaLaddr = relaLaddr;
    }

    public String getRelaRcurr() {
        return relaRcurr;
    }

    public void setRelaRcurr(String relaRcurr) {
        this.relaRcurr = relaRcurr;
    }

    public BigDecimal getRelaRcapital() {
        return relaRcapital;
    }

    public void setRelaRcapital(BigDecimal relaRcapital) {
        this.relaRcapital = relaRcapital;
    }

    public List<RelaRelaSystemSetaDTO> getRelaSystemSet() {
        return relaSystemSet;
    }

    public void setRelaSystemSet(List<RelaRelaSystemSetaDTO> relaSystemSet) {
        this.relaSystemSet = relaSystemSet;
    }
}
