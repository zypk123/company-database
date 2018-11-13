package com.huntkey.rx.purchase.common.model.custom;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.huntkey.rx.purchase.common.model.base.OrderDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 客户维护单DTO
 *
 * @author zhangyu
 * @create 2018-01-03 16:45
 **/
public class CustomMaintOrderDTO extends OrderDTO implements Serializable {

    /**
     * 伙伴id，用于编辑时提供
     */
    private String relaId;

    /**
     * id
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 统一社会信用码
     */
    @JSONField(name = "cumo_uscc")
    private String cumoUscc;

    /**
     * 伙伴编号
     */
    @JSONField(name = "cumo_code")
    private String cumoCode;

    /**
     * 伙伴简称
     */
    @JSONField(name = "cumo_short_name")
    private String cumoShortName;

    /**
     * 伙伴简称(旧)
     */
    @JSONField(name = "cumo_short_name_old")
    private String cumoShortNameOld;

    /**
     * 伙伴名称
     */
    @JSONField(name = "cumo_name")
    private String cumoName;

    /**
     * 伙伴名称(旧)
     */
    @JSONField(name = "cumo_name_old")
    private String cumoNameOld;

    /**
     * 备注
     */
    @JSONField(name = "cumo_remark")
    private String cumoRemark;

    /**
     * 备注(旧)
     */
    @JSONField(name = "cumo_remark_old")
    private String cumoRemarkOld;

    /**
     * 伙伴省地址
     */
    @JSONField(name = "cumo_addrp")
    private String cumoAddrp;

    /**
     * 伙伴省地址(旧)
     */
    @JSONField(name = "cumo_addrp_old")
    private String cumoAddrpOld;

    /**
     * 伙伴市地址
     */
    @JSONField(name = "cumo_addrc")
    private String cumoAddrc;

    /**
     * 伙伴市地址(旧)
     */
    @JSONField(name = "cumo_addrc_old")
    private String cumoAddrcOld;

    /**
     * 伙伴区地址
     */
    @JSONField(name = "cumo_addrl")
    private String cumoAddrl;

    /**
     * 伙伴区地址(旧)
     */
    @JSONField(name = "cumo_addrl_old")
    private String cumoAddrlOld;

    /**
     * 伙伴详细地址
     */
    @JSONField(name = "cumo_daddr")
    private String cumoDaddr;

    /**
     * 伙伴详细地址(旧)
     */
    @JSONField(name = "cumo_daddr_old")
    private String cumoDaddrOld;

    /**
     * 邮编
     */
    @JSONField(name = "cumo_pcode")
    private String cumoPcode;

    /**
     * 邮编(旧)
     */
    @JSONField(name = "cumo_pcode_old")
    private String cumoPcodeOld;

    /**
     * 网站
     */
    @JSONField(name = "cumo_web")
    private String cumoWeb;

    /**
     * 网站(旧)
     */
    @JSONField(name = "cumo_web_old")
    private String cumoWebOld;

    /**
     * 联系电话
     */
    @JSONField(name = "cumo_tel")
    private String cumoTel;

    /**
     * 联系电话(旧)
     */
    @JSONField(name = "cumo_tel_old")
    private String cumoTelOld;

    /**
     * 传真
     */
    @JSONField(name = "cumo_fax")
    private String cumoFax;

    /**
     * 传真(旧)
     */
    @JSONField(name = "cumo_fax_old")
    private String cumoFaxOld;

    /**
     * 企业性质
     */
    @JSONField(name = "cumo_character")
    private String cumoCharacter;

    /**
     * 企业性质(旧)
     */
    @JSONField(name = "cumo_character_old")
    private String cumoCharacterOld;

    /**
     * 主营产品
     */
    @JSONField(name = "cumo_mprod")
    private String cumoMprod;

    /**
     * 主营产品(旧)
     */
    @JSONField(name = "cumo_mprod_old")
    private String cumoMprodOld;

    /**
     * 注册地
     */
    @JSONField(name = "cumo_raddr")
    private String cumoRaddr;

    /**
     * 注册地(旧)
     */
    @JSONField(name = "cumo_raddr_old")
    private String cumoRaddrOld;

    /**
     * 注册日期
     */
    @JSONField(name = "cumo_rdate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cumoRdate;

    /**
     * 注册日期(旧)
     */
    @JSONField(name = "cumo_rdate_old")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cumoRdateOld;

    /**
     * 法人代表
     */
    @JSONField(name = "cumo_lrep")
    private String cumoLrep;

    /**
     * 法人代表(旧)
     */
    @JSONField(name = "cumo_lrep_old")
    private String cumoLrepOld;

    /**
     * 注册资本币别
     */
    @JSONField(name = "cumo_rcurrCurr")
    private String cumoRcurrCurr;

    /**
     * 注册资本币别(旧)
     */
    @JSONField(name = "cumo_rcurr_old")
    private String cumoRcurrOld;

    /**
     * 注册资本(万)
     */
    @JSONField(name = "cumo_rcapital")
    private BigDecimal cumoRcapital;

    /**
     * 注册资本(万)(旧)
     */
    @JSONField(name = "cumo_rcapital_old")
    private BigDecimal cumoRcapitalOld;

    /**
     * 法定地址
     */
    @JSONField(name = "cumo_laddr")
    private String cumoLaddr;

    /**
     * 法定地址(旧)
     */
    @JSONField(name = "cumo_laddr_old")
    private String cumoLaddrOld;

    /**
     * 上市交易所
     */
    @JSONField(name = "cumo_listexch")
    private String cumoListexch;

    /**
     * 上市交易所(旧)
     */
    @JSONField(name = "cumo_listexch_old")
    private String cumoListexchOld;

    /**
     * 上市简称
     */
    @JSONField(name = "cumo_listname")
    private String cumoListname;

    /**
     * 上市简称(旧)
     */
    @JSONField(name = "cumo_listname_old")
    private String cumoListnameOld;

    /**
     * 上市交易代码
     */
    @JSONField(name = "cumo_listcode")
    private String cumoListcode;

    /**
     * 上市交易代码(旧)
     */
    @JSONField(name = "cumo_listcode_old")
    private String cumoListcodeOld;

    /**
     * 体系认证
     */
    @JSONField(name = "cumo_system_set")
    private List<CumoSystemSetDTO> cumoSystemSet;

    /**
     * 股东数据集
     */
    @JSONField(name = "cumo_holder_set")
    private List<CumoHolderSetDTO> cumoHolderSet;

    /**
     * 客户数据集
     */
    @JSONField(name = "cumo_cust_set")
    private CumoCustSetDTO cumoCustSet;

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

    public String getCumoUscc() {
        return cumoUscc;
    }

    public void setCumoUscc(String cumoUscc) {
        this.cumoUscc = cumoUscc;
    }

    public String getCumoCode() {
        return cumoCode;
    }

    public void setCumoCode(String cumoCode) {
        this.cumoCode = cumoCode;
    }

    public String getCumoShortName() {
        return cumoShortName;
    }

    public void setCumoShortName(String cumoShortName) {
        this.cumoShortName = cumoShortName;
    }

    public String getCumoShortNameOld() {
        return cumoShortNameOld;
    }

    public void setCumoShortNameOld(String cumoShortNameOld) {
        this.cumoShortNameOld = cumoShortNameOld;
    }

    public String getCumoName() {
        return cumoName;
    }

    public void setCumoName(String cumoName) {
        this.cumoName = cumoName;
    }

    public String getCumoNameOld() {
        return cumoNameOld;
    }

    public void setCumoNameOld(String cumoNameOld) {
        this.cumoNameOld = cumoNameOld;
    }

    public String getCumoRemark() {
        return cumoRemark;
    }

    public void setCumoRemark(String cumoRemark) {
        this.cumoRemark = cumoRemark;
    }

    public String getCumoRemarkOld() {
        return cumoRemarkOld;
    }

    public void setCumoRemarkOld(String cumoRemarkOld) {
        this.cumoRemarkOld = cumoRemarkOld;
    }

    public String getCumoAddrp() {
        return cumoAddrp;
    }

    public void setCumoAddrp(String cumoAddrp) {
        this.cumoAddrp = cumoAddrp;
    }

    public String getCumoAddrpOld() {
        return cumoAddrpOld;
    }

    public void setCumoAddrpOld(String cumoAddrpOld) {
        this.cumoAddrpOld = cumoAddrpOld;
    }

    public String getCumoAddrc() {
        return cumoAddrc;
    }

    public void setCumoAddrc(String cumoAddrc) {
        this.cumoAddrc = cumoAddrc;
    }

    public String getCumoAddrcOld() {
        return cumoAddrcOld;
    }

    public void setCumoAddrcOld(String cumoAddrcOld) {
        this.cumoAddrcOld = cumoAddrcOld;
    }

    public String getCumoAddrl() {
        return cumoAddrl;
    }

    public void setCumoAddrl(String cumoAddrl) {
        this.cumoAddrl = cumoAddrl;
    }

    public String getCumoAddrlOld() {
        return cumoAddrlOld;
    }

    public void setCumoAddrlOld(String cumoAddrlOld) {
        this.cumoAddrlOld = cumoAddrlOld;
    }

    public String getCumoDaddr() {
        return cumoDaddr;
    }

    public void setCumoDaddr(String cumoDaddr) {
        this.cumoDaddr = cumoDaddr;
    }

    public String getCumoDaddrOld() {
        return cumoDaddrOld;
    }

    public void setCumoDaddrOld(String cumoDaddrOld) {
        this.cumoDaddrOld = cumoDaddrOld;
    }

    public String getCumoPcode() {
        return cumoPcode;
    }

    public void setCumoPcode(String cumoPcode) {
        this.cumoPcode = cumoPcode;
    }

    public String getCumoPcodeOld() {
        return cumoPcodeOld;
    }

    public void setCumoPcodeOld(String cumoPcodeOld) {
        this.cumoPcodeOld = cumoPcodeOld;
    }

    public String getCumoWeb() {
        return cumoWeb;
    }

    public void setCumoWeb(String cumoWeb) {
        this.cumoWeb = cumoWeb;
    }

    public String getCumoWebOld() {
        return cumoWebOld;
    }

    public void setCumoWebOld(String cumoWebOld) {
        this.cumoWebOld = cumoWebOld;
    }

    public String getCumoTel() {
        return cumoTel;
    }

    public void setCumoTel(String cumoTel) {
        this.cumoTel = cumoTel;
    }

    public String getCumoTelOld() {
        return cumoTelOld;
    }

    public void setCumoTelOld(String cumoTelOld) {
        this.cumoTelOld = cumoTelOld;
    }

    public String getCumoFax() {
        return cumoFax;
    }

    public void setCumoFax(String cumoFax) {
        this.cumoFax = cumoFax;
    }

    public String getCumoFaxOld() {
        return cumoFaxOld;
    }

    public void setCumoFaxOld(String cumoFaxOld) {
        this.cumoFaxOld = cumoFaxOld;
    }

    public String getCumoCharacter() {
        return cumoCharacter;
    }

    public void setCumoCharacter(String cumoCharacter) {
        this.cumoCharacter = cumoCharacter;
    }

    public String getCumoCharacterOld() {
        return cumoCharacterOld;
    }

    public void setCumoCharacterOld(String cumoCharacterOld) {
        this.cumoCharacterOld = cumoCharacterOld;
    }

    public String getCumoMprod() {
        return cumoMprod;
    }

    public void setCumoMprod(String cumoMprod) {
        this.cumoMprod = cumoMprod;
    }

    public String getCumoMprodOld() {
        return cumoMprodOld;
    }

    public void setCumoMprodOld(String cumoMprodOld) {
        this.cumoMprodOld = cumoMprodOld;
    }

    public String getCumoRaddr() {
        return cumoRaddr;
    }

    public void setCumoRaddr(String cumoRaddr) {
        this.cumoRaddr = cumoRaddr;
    }

    public String getCumoRaddrOld() {
        return cumoRaddrOld;
    }

    public void setCumoRaddrOld(String cumoRaddrOld) {
        this.cumoRaddrOld = cumoRaddrOld;
    }

    public Date getCumoRdate() {
        return cumoRdate;
    }

    public void setCumoRdate(Date cumoRdate) {
        this.cumoRdate = cumoRdate;
    }

    public Date getCumoRdateOld() {
        return cumoRdateOld;
    }

    public void setCumoRdateOld(Date cumoRdateOld) {
        this.cumoRdateOld = cumoRdateOld;
    }

    public String getCumoLrep() {
        return cumoLrep;
    }

    public void setCumoLrep(String cumoLrep) {
        this.cumoLrep = cumoLrep;
    }

    public String getCumoLrepOld() {
        return cumoLrepOld;
    }

    public void setCumoLrepOld(String cumoLrepOld) {
        this.cumoLrepOld = cumoLrepOld;
    }

    public String getCumoRcurrCurr() {
        return cumoRcurrCurr;
    }

    public void setCumoRcurrCurr(String cumoRcurrCurr) {
        this.cumoRcurrCurr = cumoRcurrCurr;
    }

    public String getCumoRcurrOld() {
        return cumoRcurrOld;
    }

    public void setCumoRcurrOld(String cumoRcurrOld) {
        this.cumoRcurrOld = cumoRcurrOld;
    }

    public BigDecimal getCumoRcapital() {
        return cumoRcapital;
    }

    public void setCumoRcapital(BigDecimal cumoRcapital) {
        this.cumoRcapital = cumoRcapital;
    }

    public BigDecimal getCumoRcapitalOld() {
        return cumoRcapitalOld;
    }

    public void setCumoRcapitalOld(BigDecimal cumoRcapitalOld) {
        this.cumoRcapitalOld = cumoRcapitalOld;
    }

    public String getCumoLaddr() {
        return cumoLaddr;
    }

    public void setCumoLaddr(String cumoLaddr) {
        this.cumoLaddr = cumoLaddr;
    }

    public String getCumoLaddrOld() {
        return cumoLaddrOld;
    }

    public void setCumoLaddrOld(String cumoLaddrOld) {
        this.cumoLaddrOld = cumoLaddrOld;
    }

    public String getCumoListexch() {
        return cumoListexch;
    }

    public void setCumoListexch(String cumoListexch) {
        this.cumoListexch = cumoListexch;
    }

    public String getCumoListexchOld() {
        return cumoListexchOld;
    }

    public void setCumoListexchOld(String cumoListexchOld) {
        this.cumoListexchOld = cumoListexchOld;
    }

    public String getCumoListname() {
        return cumoListname;
    }

    public void setCumoListname(String cumoListname) {
        this.cumoListname = cumoListname;
    }

    public String getCumoListnameOld() {
        return cumoListnameOld;
    }

    public void setCumoListnameOld(String cumoListnameOld) {
        this.cumoListnameOld = cumoListnameOld;
    }

    public String getCumoListcode() {
        return cumoListcode;
    }

    public void setCumoListcode(String cumoListcode) {
        this.cumoListcode = cumoListcode;
    }

    public String getCumoListcodeOld() {
        return cumoListcodeOld;
    }

    public void setCumoListcodeOld(String cumoListcodeOld) {
        this.cumoListcodeOld = cumoListcodeOld;
    }

    public List<CumoSystemSetDTO> getCumoSystemSet() {
        return cumoSystemSet;
    }

    public void setCumoSystemSet(List<CumoSystemSetDTO> cumoSystemSet) {
        this.cumoSystemSet = cumoSystemSet;
    }

    public List<CumoHolderSetDTO> getCumoHolderSet() {
        return cumoHolderSet;
    }

    public void setCumoHolderSet(List<CumoHolderSetDTO> cumoHolderSet) {
        this.cumoHolderSet = cumoHolderSet;
    }

    public CumoCustSetDTO getCumoCustSet() {
        return cumoCustSet;
    }

    public void setCumoCustSet(CumoCustSetDTO cumoCustSet) {
        this.cumoCustSet = cumoCustSet;
    }

    public boolean isSubmit() {
        return isSubmit;
    }

    public void setSubmit(boolean submit) {
        isSubmit = submit;
    }

    @Override
    public String toString() {
        return "CustomMaintOrderDTO{" +
                "relaId='" + relaId + '\'' +
                ", id='" + id + '\'' +
                ", cumoUscc='" + cumoUscc + '\'' +
                ", cumoCode='" + cumoCode + '\'' +
                ", cumoShortName='" + cumoShortName + '\'' +
                ", cumoShortNameOld='" + cumoShortNameOld + '\'' +
                ", cumoName='" + cumoName + '\'' +
                ", cumoNameOld='" + cumoNameOld + '\'' +
                ", cumoRemark='" + cumoRemark + '\'' +
                ", cumoRemarkOld='" + cumoRemarkOld + '\'' +
                ", cumoAddrp='" + cumoAddrp + '\'' +
                ", cumoAddrpOld='" + cumoAddrpOld + '\'' +
                ", cumoAddrc='" + cumoAddrc + '\'' +
                ", cumoAddrcOld='" + cumoAddrcOld + '\'' +
                ", cumoAddrl='" + cumoAddrl + '\'' +
                ", cumoAddrlOld='" + cumoAddrlOld + '\'' +
                ", cumoDaddr='" + cumoDaddr + '\'' +
                ", cumoDaddrOld='" + cumoDaddrOld + '\'' +
                ", cumoPcode='" + cumoPcode + '\'' +
                ", cumoPcodeOld='" + cumoPcodeOld + '\'' +
                ", cumoWeb='" + cumoWeb + '\'' +
                ", cumoWebOld='" + cumoWebOld + '\'' +
                ", cumoTel='" + cumoTel + '\'' +
                ", cumoTelOld='" + cumoTelOld + '\'' +
                ", cumoFax='" + cumoFax + '\'' +
                ", cumoFaxOld='" + cumoFaxOld + '\'' +
                ", cumoCharacter='" + cumoCharacter + '\'' +
                ", cumoCharacterOld='" + cumoCharacterOld + '\'' +
                ", cumoMprod='" + cumoMprod + '\'' +
                ", cumoMprodOld='" + cumoMprodOld + '\'' +
                ", cumoRaddr='" + cumoRaddr + '\'' +
                ", cumoRaddrOld='" + cumoRaddrOld + '\'' +
                ", cumoRdate=" + cumoRdate +
                ", cumoRdateOld=" + cumoRdateOld +
                ", cumoLrep='" + cumoLrep + '\'' +
                ", cumoLrepOld='" + cumoLrepOld + '\'' +
                ", cumoRcurrCurr='" + cumoRcurrCurr + '\'' +
                ", cumoRcurrOld='" + cumoRcurrOld + '\'' +
                ", cumoRcapital=" + cumoRcapital +
                ", cumoRcapitalOld=" + cumoRcapitalOld +
                ", cumoLaddr='" + cumoLaddr + '\'' +
                ", cumoLaddrOld='" + cumoLaddrOld + '\'' +
                ", cumoListexch='" + cumoListexch + '\'' +
                ", cumoListexchOld='" + cumoListexchOld + '\'' +
                ", cumoListname='" + cumoListname + '\'' +
                ", cumoListnameOld='" + cumoListnameOld + '\'' +
                ", cumoListcode='" + cumoListcode + '\'' +
                ", cumoListcodeOld='" + cumoListcodeOld + '\'' +
                ", cumoSystemSet=" + cumoSystemSet +
                ", cumoHolderSet=" + cumoHolderSet +
                ", cumoCustSet=" + cumoCustSet +
                ", isSubmit=" + isSubmit +
                '}';
    }
}
