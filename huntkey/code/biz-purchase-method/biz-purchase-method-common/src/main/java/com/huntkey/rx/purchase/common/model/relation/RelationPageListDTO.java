package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelationProperty;
import com.huntkey.rx.purchase.common.model.base.EdmDTO;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author by xuyf on 2018/1/16 0016.
 */
public class RelationPageListDTO extends EdmDTO {

    /**
     * id
     */
    @JSONField(name = EdmSysColumn.ID)
    private String id;

    /**
     * 伙伴编号
     */
    @JSONField(name = RelationProperty.RELA_CODE)
    private String relaCode;

    /**
     * 伙伴名称
     */
    @JSONField(name = RelationProperty.RELA_NAME)
    private String relaName;

    /**
     * 伙伴简称
     */
    @JSONField(name = RelationProperty.RELA_SHORT_NAME)
    private String relaShortName;

    /**
     * 备注
     */
    @JSONField(name = RelationProperty.RELA_REMARK)
    private String relaRemark;

    /**
     * 伙伴类型集
     */
    @JSONField(name = RelationProperty.RELA_TYPES_SET)
    private List<RelaRelaTypesSetaDTO> relaTypesSet;

    /**
     * 伙伴省地址
     */
    @JSONField(name = RelationProperty.RELA_ADDRP)
    private String relaAddrp;

    /**
     * 伙伴市地址
     */
    @JSONField(name = RelationProperty.RELA_ADDRC)
    private String relaAddrc;

    /**
     * 伙伴区地址
     */
    @JSONField(name = RelationProperty.RELA_ADDRL)
    private String relaAddrl;

    /**
     * 伙伴详细地址
     */
    @JSONField(name = RelationProperty.RELA_DADDR)
    private String relaDaddr;

    /**
     * 邮编
     */
    @JSONField(name = RelationProperty.RELA_PCODE)
    private String relaPcode;

    /**
     * 电话
     */
    @JSONField(name = RelationProperty.RELA_TEL)
    private String relaTel;

    /**
     * 传真
     */
    @JSONField(name = RelationProperty.RELA_FAX)
    private String relaFax;

    /**
     * 网站
     */
    @JSONField(name = RelationProperty.RELA_WEB)
    private String relaWeb;

    /**
     * 主营产品
     */
    @JSONField(name = RelationProperty.RELA_MPROD)
    private String relaMprod;

    /**
     * 企业性质
     */
    @JSONField(name = RelationProperty.RELA_CHARACTER)
    private String relaCharacter;

    /**
     * 上市交易所
     */
    @JSONField(name = RelationProperty.RELA_LISTEXCH)
    private String relaListexch;

    /**
     * 上市简称
     */
    @JSONField(name = RelationProperty.RELA_LISTNAME)
    private String relaListname;

    /**
     * 上市交易代码
     */
    @JSONField(name = RelationProperty.RELA_LISTCODE)
    private String relaListcode;

    /**
     * 统一社会信用代码
     */
    @JSONField(name = RelationProperty.RELA_USCC)
    private String relaUscc;

    /**
     * 注册地
     */
    @JSONField(name = RelationProperty.RELA_RADDR)
    private String relaRaddr;

    /**
     * 注册日期
     */
    @JSONField(name = RelationProperty.RELA_RDATE)
    private Date relaRdate;

    /**
     * 法人代表
     */
    @JSONField(name = RelationProperty.RELA_LREP)
    private String relaLrep;

    /**
     * 法定地址
     */
    @JSONField(name = RelationProperty.RELA_LADDR)
    private String relaLaddr;

    /**
     * 注册资本币别
     */
    @JSONField(name = RelationProperty.RELA_RCURR)
    private String relaRcurr;

    /**
     * 注册资本(万)
     */
    @JSONField(name = RelationProperty.RELA_RCAPITAL)
    private BigDecimal relaRcapital;

    /**
     * 伙伴状态对应的相关信息集合
     */
    private List<RelationStatusDTO> relationStatusList;

    /**
     * 维护人名称
     */
    private String moduserName;

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

    public String getModuserName() {
        return moduserName;
    }

    public void setModuserName(String moduserName) {
        this.moduserName = moduserName;
    }

    public List<RelationStatusDTO> getRelationStatusList() {
        return relationStatusList;
    }

    public void setRelationStatusList(List<RelationStatusDTO> relationStatusList) {
        this.relationStatusList = relationStatusList;
    }

    @Override
    public String toString() {
        return "RelationPageListDTO{" +
                "id='" + id + '\'' +
                ", relaCode='" + relaCode + '\'' +
                ", relaName='" + relaName + '\'' +
                ", relaShortName='" + relaShortName + '\'' +
                ", relaRemark='" + relaRemark + '\'' +
                ", relaTypesSet=" + relaTypesSet +
                ", relaAddrp='" + relaAddrp + '\'' +
                ", relaAddrc='" + relaAddrc + '\'' +
                ", relaAddrl='" + relaAddrl + '\'' +
                ", relaDaddr='" + relaDaddr + '\'' +
                ", relaPcode='" + relaPcode + '\'' +
                ", relaTel='" + relaTel + '\'' +
                ", relaFax='" + relaFax + '\'' +
                ", relaWeb='" + relaWeb + '\'' +
                ", relaMprod='" + relaMprod + '\'' +
                ", relaCharacter='" + relaCharacter + '\'' +
                ", relaListexch='" + relaListexch + '\'' +
                ", relaListname='" + relaListname + '\'' +
                ", relaListcode='" + relaListcode + '\'' +
                ", relaUscc='" + relaUscc + '\'' +
                ", relaRaddr='" + relaRaddr + '\'' +
                ", relaRdate='" + relaRdate + '\'' +
                ", relaLrep='" + relaLrep + '\'' +
                ", relaLaddr='" + relaLaddr + '\'' +
                ", relaRcurr='" + relaRcurr + '\'' +
                ", relaRcapital='" + relaRcapital + '\'' +
                ", relationStatusList=" + relationStatusList +
                ", moduserName='" + moduserName + '\'' +
                '}';
    }
}
