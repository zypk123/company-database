package com.huntkey.rx.purchase.common.model.supplier;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.huntkey.rx.purchase.common.model.base.OrderDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 供应商维护单DTO
 *
 * @author zhangyu
 * @create 2018-01-03 16:45
 **/
public class SupplierMaintOrderDTO extends OrderDTO implements Serializable {

    /**
     * 伙伴id，编辑时传入
     */
    private String relaId;

    /**
     * id
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 申请人
     */
    @JSONField(name = "creuser")
    private String creuser;

    /**
     * 申请部门
     */
    @JSONField(name = "orde_dept")
    private String ordeDept;

    /**
     * 统一社会信用码
     */
    @JSONField(name = "sumo_uscc")
    private String sumoUscc;

    /**
     * 伙伴编号
     */
    @JSONField(name = "sumo_code")
    private String sumoCode;

    /**
     * 伙伴简称
     */
    @JSONField(name = "sumo_short_name")
    private String sumoShortName;

    /**
     * 伙伴简称(旧)
     */
    @JSONField(name = "sumo_short_name_old")
    private String sumoShortNameOld;

    /**
     * 伙伴名称
     */
    @JSONField(name = "sumo_name")
    private String sumoName;

    /**
     * 伙伴名称(旧)
     */
    @JSONField(name = "sumo_name_old")
    private String sumoNameOld;

    /**
     * 备注
     */
    @JSONField(name = "sumo_remark")
    private String sumoRemark;

    /**
     * 备注(旧)
     */
    @JSONField(name = "sumo_remark_old")
    private String sumoRemarkOld;

    /**
     * 伙伴省地址
     */
    @JSONField(name = "sumo_addrp")
    private String sumoAddrp;

    /**
     * 伙伴省地址(旧)
     */
    @JSONField(name = "sumo_addrp_old")
    private String sumoAddrpOld;

    /**
     * 伙伴市地址
     */
    @JSONField(name = "sumo_addrc")
    private String sumoAddrc;

    /**
     * 伙伴市地址(旧)
     */
    @JSONField(name = "sumo_addrc_old")
    private String sumoAddrcOld;

    /**
     * 伙伴区地址
     */
    @JSONField(name = "sumo_addrl")
    private String sumoAddrl;

    /**
     * 伙伴区地址(旧)
     */
    @JSONField(name = "sumo_addrl_old")
    private String sumoAddrlOld;

    /**
     * 伙伴详细地址
     */
    @JSONField(name = "sumo_daddr")
    private String sumoDaddr;

    /**
     * 伙伴详细地址(旧)
     */
    @JSONField(name = "sumo_daddr_old")
    private String sumoDaddrOld;

    /**
     * 邮编
     */
    @JSONField(name = "sumo_pcode")
    private String sumoPcode;

    /**
     * 邮编(旧)
     */
    @JSONField(name = "sumo_pcode_old")
    private String sumoPcodeOld;

    /**
     * 网站
     */
    @JSONField(name = "sumo_web")
    private String sumoWeb;

    /**
     * 网站(旧)
     */
    @JSONField(name = "sumo_web_old")
    private String sumoWebOld;

    /**
     * 联系电话
     */
    @JSONField(name = "sumo_tel")
    private String sumoTel;

    /**
     * 联系电话(旧)
     */
    @JSONField(name = "sumo_tel_old")
    private String sumoTelOld;

    /**
     * 传真
     */
    @JSONField(name = "sumo_fax")
    private String sumoFax;

    /**
     * 传真(旧)
     */
    @JSONField(name = "sumo_fax_old")
    private String sumoFaxOld;

    /**
     * 企业性质
     */
    @JSONField(name = "sumo_character")
    private String sumoCharacter;

    /**
     * 企业性质(旧)
     */
    @JSONField(name = "sumo_character_old")
    private String sumoCharacterOld;

    /**
     * 主营产品
     */
    @JSONField(name = "sumo_mprod")
    private String sumoMprod;

    /**
     * 主营产品(旧)
     */
    @JSONField(name = "sumo_mprod_old")
    private String sumoMprodOld;

    /**
     * 注册地
     */
    @JSONField(name = "sumo_raddr")
    private String sumoRaddr;

    /**
     * 注册地(旧)
     */
    @JSONField(name = "sumo_raddr_old")
    private String sumoRaddrOld;

    /**
     * 注册日期
     */
    @JSONField(name = "sumo_rdate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sumoRdate;

    /**
     * 注册日期(旧)
     */
    @JSONField(name = "sumo_rdate_old")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sumoRdateOld;

    /**
     * 法人代表
     */
    @JSONField(name = "sumo_lrep")
    private String sumoLrep;

    /**
     * 法人代表(旧)
     */
    @JSONField(name = "sumo_lrep_old")
    private String sumoLrepOld;

    /**
     * 注册资本币别
     */
    @JSONField(name = "sumo_rcurrCurr")
    private String sumoRcurrCurr;

    /**
     * 注册资本币别(旧)
     */
    @JSONField(name = "sumo_rcurr_old")
    private String sumoRcurrOld;

    /**
     * 注册资本(万)
     */
    @JSONField(name = "sumo_rcapital")
    private BigDecimal sumoRcapital;

    /**
     * 注册资本(万)(旧)
     */
    @JSONField(name = "sumo_rcapital_old")
    private BigDecimal sumoRcapitalold;

    /**
     * 法定地址
     */
    @JSONField(name = "sumo_laddr")
    private String sumoLaddr;

    /**
     * 法定地址(旧)
     */
    @JSONField(name = "sumo_laddr_old")
    private String sumoLaddrOld;

    /**
     * 上市交易所
     */
    @JSONField(name = "sumo_listexch")
    private String sumoListexch;

    /**
     * 上市交易所(旧)
     */
    @JSONField(name = "sumo_listexch_old")
    private String sumoListexchOld;

    /**
     * 上市简称
     */
    @JSONField(name = "sumo_listname")
    private String sumoListname;

    /**
     * 上市简称(旧)
     */
    @JSONField(name = "sumo_listname_old")
    private String sumoListnameOld;

    /**
     * 上市交易代码
     */
    @JSONField(name = "sumo_listcode")
    private String sumoListcode;

    /**
     * 上市交易代码(旧)
     */
    @JSONField(name = "sumo_listcode_old")
    private String sumoListcodeOld;

    /**
     * 体系认证
     */
    @JSONField(name = "sumo_system_set")
    private List<SumoSystemSetDTO> sumoSystemSet;

    /**
     * 股东数据集
     */
    @JSONField(name = "sumo_holder_set")
    private List<SumoHolderSetDTO> sumoHolderSet;

    /**
     * 供应商数据集
     */
    @JSONField(name = "sumo_supplier_set")
    private SumoSupplierSetDTO sumoSupplierSet;

    /**
     * 是否是提交情况的状态位
     */
    private boolean isSubmit;

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

    @Override
    public String getCreuser() {
        return creuser;
    }

    @Override
    public void setCreuser(String creuser) {
        this.creuser = creuser;
    }

    @Override
    public String getOrdeDept() {
        return ordeDept;
    }

    @Override
    public void setOrdeDept(String ordeDept) {
        this.ordeDept = ordeDept;
    }

    public String getSumoUscc() {
        return sumoUscc;
    }

    public void setSumoUscc(String sumoUscc) {
        this.sumoUscc = sumoUscc;
    }

    public String getSumoCode() {
        return sumoCode;
    }

    public void setSumoCode(String sumoCode) {
        this.sumoCode = sumoCode;
    }

    public String getSumoShortName() {
        return sumoShortName;
    }

    public void setSumoShortName(String sumoShortName) {
        this.sumoShortName = sumoShortName;
    }

    public String getSumoShortNameOld() {
        return sumoShortNameOld;
    }

    public void setSumoShortNameOld(String sumoShortNameOld) {
        this.sumoShortNameOld = sumoShortNameOld;
    }

    public String getSumoName() {
        return sumoName;
    }

    public void setSumoName(String sumoName) {
        this.sumoName = sumoName;
    }

    public String getSumoNameOld() {
        return sumoNameOld;
    }

    public void setSumoNameOld(String sumoNameOld) {
        this.sumoNameOld = sumoNameOld;
    }

    public String getSumoRemark() {
        return sumoRemark;
    }

    public void setSumoRemark(String sumoRemark) {
        this.sumoRemark = sumoRemark;
    }

    public String getSumoRemarkOld() {
        return sumoRemarkOld;
    }

    public void setSumoRemarkOld(String sumoRemarkOld) {
        this.sumoRemarkOld = sumoRemarkOld;
    }

    public String getSumoAddrp() {
        return sumoAddrp;
    }

    public void setSumoAddrp(String sumoAddrp) {
        this.sumoAddrp = sumoAddrp;
    }

    public String getSumoAddrpOld() {
        return sumoAddrpOld;
    }

    public void setSumoAddrpOld(String sumoAddrpOld) {
        this.sumoAddrpOld = sumoAddrpOld;
    }

    public String getSumoAddrc() {
        return sumoAddrc;
    }

    public void setSumoAddrc(String sumoAddrc) {
        this.sumoAddrc = sumoAddrc;
    }

    public String getSumoAddrcOld() {
        return sumoAddrcOld;
    }

    public void setSumoAddrcOld(String sumoAddrcOld) {
        this.sumoAddrcOld = sumoAddrcOld;
    }

    public String getSumoAddrl() {
        return sumoAddrl;
    }

    public void setSumoAddrl(String sumoAddrl) {
        this.sumoAddrl = sumoAddrl;
    }

    public String getSumoAddrlOld() {
        return sumoAddrlOld;
    }

    public void setSumoAddrlOld(String sumoAddrlOld) {
        this.sumoAddrlOld = sumoAddrlOld;
    }

    public String getSumoDaddr() {
        return sumoDaddr;
    }

    public void setSumoDaddr(String sumoDaddr) {
        this.sumoDaddr = sumoDaddr;
    }

    public String getSumoDaddrOld() {
        return sumoDaddrOld;
    }

    public void setSumoDaddrOld(String sumoDaddrOld) {
        this.sumoDaddrOld = sumoDaddrOld;
    }

    public String getSumoPcode() {
        return sumoPcode;
    }

    public void setSumoPcode(String sumoPcode) {
        this.sumoPcode = sumoPcode;
    }

    public String getSumoPcodeOld() {
        return sumoPcodeOld;
    }

    public void setSumoPcodeOld(String sumoPcodeOld) {
        this.sumoPcodeOld = sumoPcodeOld;
    }

    public String getSumoWeb() {
        return sumoWeb;
    }

    public void setSumoWeb(String sumoWeb) {
        this.sumoWeb = sumoWeb;
    }

    public String getSumoWebOld() {
        return sumoWebOld;
    }

    public void setSumoWebOld(String sumoWebOld) {
        this.sumoWebOld = sumoWebOld;
    }

    public String getSumoTel() {
        return sumoTel;
    }

    public void setSumoTel(String sumoTel) {
        this.sumoTel = sumoTel;
    }

    public String getSumoTelOld() {
        return sumoTelOld;
    }

    public void setSumoTelOld(String sumoTelOld) {
        this.sumoTelOld = sumoTelOld;
    }

    public String getSumoFax() {
        return sumoFax;
    }

    public void setSumoFax(String sumoFax) {
        this.sumoFax = sumoFax;
    }

    public String getSumoFaxOld() {
        return sumoFaxOld;
    }

    public void setSumoFaxOld(String sumoFaxOld) {
        this.sumoFaxOld = sumoFaxOld;
    }

    public String getSumoCharacter() {
        return sumoCharacter;
    }

    public void setSumoCharacter(String sumoCharacter) {
        this.sumoCharacter = sumoCharacter;
    }

    public String getSumoCharacterOld() {
        return sumoCharacterOld;
    }

    public void setSumoCharacterOld(String sumoCharacterOld) {
        this.sumoCharacterOld = sumoCharacterOld;
    }

    public String getSumoMprod() {
        return sumoMprod;
    }

    public void setSumoMprod(String sumoMprod) {
        this.sumoMprod = sumoMprod;
    }

    public String getSumoMprodOld() {
        return sumoMprodOld;
    }

    public void setSumoMprodOld(String sumoMprodOld) {
        this.sumoMprodOld = sumoMprodOld;
    }

    public String getSumoRaddr() {
        return sumoRaddr;
    }

    public void setSumoRaddr(String sumoRaddr) {
        this.sumoRaddr = sumoRaddr;
    }

    public String getSumoRaddrOld() {
        return sumoRaddrOld;
    }

    public void setSumoRaddrOld(String sumoRaddrOld) {
        this.sumoRaddrOld = sumoRaddrOld;
    }

    public Date getSumoRdate() {
        return sumoRdate;
    }

    public void setSumoRdate(Date sumoRdate) {
        this.sumoRdate = sumoRdate;
    }

    public Date getSumoRdateOld() {
        return sumoRdateOld;
    }

    public void setSumoRdateOld(Date sumoRdateOld) {
        this.sumoRdateOld = sumoRdateOld;
    }

    public String getSumoLrep() {
        return sumoLrep;
    }

    public void setSumoLrep(String sumoLrep) {
        this.sumoLrep = sumoLrep;
    }

    public String getSumoLrepOld() {
        return sumoLrepOld;
    }

    public void setSumoLrepOld(String sumoLrepOld) {
        this.sumoLrepOld = sumoLrepOld;
    }

    public String getSumoRcurrCurr() {
        return sumoRcurrCurr;
    }

    public void setSumoRcurrCurr(String sumoRcurrCurr) {
        this.sumoRcurrCurr = sumoRcurrCurr;
    }

    public String getSumoRcurrOld() {
        return sumoRcurrOld;
    }

    public void setSumoRcurrOld(String sumoRcurrOld) {
        this.sumoRcurrOld = sumoRcurrOld;
    }

    public BigDecimal getSumoRcapital() {
        return sumoRcapital;
    }

    public void setSumoRcapital(BigDecimal sumoRcapital) {
        this.sumoRcapital = sumoRcapital;
    }

    public BigDecimal getSumoRcapitalold() {
        return sumoRcapitalold;
    }

    public void setSumoRcapitalold(BigDecimal sumoRcapitalold) {
        this.sumoRcapitalold = sumoRcapitalold;
    }

    public String getSumoLaddr() {
        return sumoLaddr;
    }

    public void setSumoLaddr(String sumoLaddr) {
        this.sumoLaddr = sumoLaddr;
    }

    public String getSumoLaddrOld() {
        return sumoLaddrOld;
    }

    public void setSumoLaddrOld(String sumoLaddrOld) {
        this.sumoLaddrOld = sumoLaddrOld;
    }

    public String getSumoListexch() {
        return sumoListexch;
    }

    public void setSumoListexch(String sumoListexch) {
        this.sumoListexch = sumoListexch;
    }

    public String getSumoListexchOld() {
        return sumoListexchOld;
    }

    public void setSumoListexchOld(String sumoListexchOld) {
        this.sumoListexchOld = sumoListexchOld;
    }

    public String getSumoListname() {
        return sumoListname;
    }

    public void setSumoListname(String sumoListname) {
        this.sumoListname = sumoListname;
    }

    public String getSumoListnameOld() {
        return sumoListnameOld;
    }

    public void setSumoListnameOld(String sumoListnameOld) {
        this.sumoListnameOld = sumoListnameOld;
    }

    public String getSumoListcode() {
        return sumoListcode;
    }

    public void setSumoListcode(String sumoListcode) {
        this.sumoListcode = sumoListcode;
    }

    public String getSumoListcodeOld() {
        return sumoListcodeOld;
    }

    public void setSumoListcodeOld(String sumoListcodeOld) {
        this.sumoListcodeOld = sumoListcodeOld;
    }

    public List<SumoSystemSetDTO> getSumoSystemSet() {
        return sumoSystemSet;
    }

    public void setSumoSystemSet(List<SumoSystemSetDTO> sumoSystemSet) {
        this.sumoSystemSet = sumoSystemSet;
    }

    public List<SumoHolderSetDTO> getSumoHolderSet() {
        return sumoHolderSet;
    }

    public void setSumoHolderSet(List<SumoHolderSetDTO> sumoHolderSet) {
        this.sumoHolderSet = sumoHolderSet;
    }

    public SumoSupplierSetDTO getSumoSupplierSet() {
        return sumoSupplierSet;
    }

    public void setSumoSupplierSet(SumoSupplierSetDTO sumoSupplierSet) {
        this.sumoSupplierSet = sumoSupplierSet;
    }

    public boolean isSubmit() {
        return isSubmit;
    }

    public void setSubmit(boolean submit) {
        isSubmit = submit;
    }

    @Override
    public String toString() {
        return "SupplierMaintOrderDTO{" +
                "relaId='" + relaId + '\'' +
                ", id='" + id + '\'' +
                ", creuser='" + creuser + '\'' +
                ", ordeDept='" + ordeDept + '\'' +
                ", sumoUscc='" + sumoUscc + '\'' +
                ", sumoCode='" + sumoCode + '\'' +
                ", sumoShortName='" + sumoShortName + '\'' +
                ", sumoShortNameOld='" + sumoShortNameOld + '\'' +
                ", sumoName='" + sumoName + '\'' +
                ", sumoNameOld='" + sumoNameOld + '\'' +
                ", sumoRemark='" + sumoRemark + '\'' +
                ", sumoRemarkOld='" + sumoRemarkOld + '\'' +
                ", sumoAddrp='" + sumoAddrp + '\'' +
                ", sumoAddrpOld='" + sumoAddrpOld + '\'' +
                ", sumoAddrc='" + sumoAddrc + '\'' +
                ", sumoAddrcOld='" + sumoAddrcOld + '\'' +
                ", sumoAddrl='" + sumoAddrl + '\'' +
                ", sumoAddrlOld='" + sumoAddrlOld + '\'' +
                ", sumoDaddr='" + sumoDaddr + '\'' +
                ", sumoDaddrOld='" + sumoDaddrOld + '\'' +
                ", sumoPcode='" + sumoPcode + '\'' +
                ", sumoPcodeOld='" + sumoPcodeOld + '\'' +
                ", sumoWeb='" + sumoWeb + '\'' +
                ", sumoWebOld='" + sumoWebOld + '\'' +
                ", sumoTel='" + sumoTel + '\'' +
                ", sumoTelOld='" + sumoTelOld + '\'' +
                ", sumoFax='" + sumoFax + '\'' +
                ", sumoFaxOld='" + sumoFaxOld + '\'' +
                ", sumoCharacter='" + sumoCharacter + '\'' +
                ", sumoCharacterOld='" + sumoCharacterOld + '\'' +
                ", sumoMprod='" + sumoMprod + '\'' +
                ", sumoMprodOld='" + sumoMprodOld + '\'' +
                ", sumoRaddr='" + sumoRaddr + '\'' +
                ", sumoRaddrOld='" + sumoRaddrOld + '\'' +
                ", sumoRdate=" + sumoRdate +
                ", sumoRdateOld=" + sumoRdateOld +
                ", sumoLrep='" + sumoLrep + '\'' +
                ", sumoLrepOld='" + sumoLrepOld + '\'' +
                ", sumoRcurrCurr='" + sumoRcurrCurr + '\'' +
                ", sumoRcurrOld='" + sumoRcurrOld + '\'' +
                ", sumoRcapital=" + sumoRcapital +
                ", sumoRcapitalold=" + sumoRcapitalold +
                ", sumoLaddr='" + sumoLaddr + '\'' +
                ", sumoLaddrOld='" + sumoLaddrOld + '\'' +
                ", sumoListexch='" + sumoListexch + '\'' +
                ", sumoListexchOld='" + sumoListexchOld + '\'' +
                ", sumoListname='" + sumoListname + '\'' +
                ", sumoListnameOld='" + sumoListnameOld + '\'' +
                ", sumoListcode='" + sumoListcode + '\'' +
                ", sumoListcodeOld='" + sumoListcodeOld + '\'' +
                ", sumoSystemSet=" + sumoSystemSet +
                ", sumoHolderSet=" + sumoHolderSet +
                ", sumoSupplierSet=" + sumoSupplierSet +
                ", isSubmit=" + isSubmit +
                '}';
    }
}
