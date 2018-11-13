package com.huntkey.rx.sceo.formula.common.model;

import com.huntkey.rx.sceo.formula.common.base.BaseEntity;

import java.util.Date;

/**
 * @author chenfei on 2017/5/15.
 */
public class TfmFunctionDefinition extends BaseEntity {
    /**
     * 编号
     */
    private String fundId;

    /**
     * 函数名称
     */
    private String fundFunName;

    /**
     * 函数分类
     */
    private String fundFunCatagoryId;

    private String fundFunClassifyDesc;

    private String fnccClassifyName;

    /**
     * enum[prohibit,    inusing]
     */
    private String fundState;

    /**
     * 函数描述
     */
    private String fundFunDesc;

    /**
     * 变更摘要
     */
    private String fundModifyRemarks;

    /**
     * 是否可用: 1   可用，     0   不可用
     */
    private Byte isenable;

    /**
     * 创建时间
     */
    private Date addtime;

    /**
     * 创建人
     */
    private String adduser;

    /**
     * 更新时间
     */
    private Date modtime;

    /**
     * 修改人
     */
    private String moduser;

    /**
     * 区域标识
     */
    private Integer acctid;

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId == null ? null : fundId.trim();
    }

    public String getFundFunName() {
        return fundFunName;
    }

    public void setFundFunName(String fundFunName) {
        this.fundFunName = fundFunName == null ? null : fundFunName.trim();
    }

    public String getFundFunCatagoryId() {
        return fundFunCatagoryId;
    }

    public void setFundFunCatagoryId(String fundFunCatagoryId) {
        this.fundFunCatagoryId = fundFunCatagoryId == null ? null : fundFunCatagoryId.trim();
    }

    public String getFundState() {
        return fundState;
    }

    public void setFundState(String fundState) {
        this.fundState = fundState == null ? null : fundState.trim();
    }

    public String getFundFunDesc() {
        return fundFunDesc;
    }

    public void setFundFunDesc(String fundFunDesc) {
        this.fundFunDesc = fundFunDesc == null ? null : fundFunDesc.trim();
    }

    public String getFundModifyRemarks() {
        return fundModifyRemarks;
    }

    public void setFundModifyRemarks(String fundModifyRemarks) {
        this.fundModifyRemarks = fundModifyRemarks == null ? null : fundModifyRemarks.trim();
    }
    @Override
    public Byte getIsenable() {
        return isenable;
    }
    @Override
    public void setIsenable(Byte isenable) {
        this.isenable = isenable;
    }
    @Override
    public Date getAddtime() {
        return addtime;
    }
    @Override
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
    @Override
    public String getAdduser() {
        return adduser;
    }
    @Override
    public void setAdduser(String adduser) {
        this.adduser = adduser == null ? null : adduser.trim();
    }
    @Override
    public Date getModtime() {
        return modtime;
    }
    @Override
    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }
    @Override
    public String getModuser() {
        return moduser;
    }
    @Override
    public void setModuser(String moduser) {
        this.moduser = moduser == null ? null : moduser.trim();
    }

    public Integer getAcctid() {
        return acctid;
    }

    public void setAcctid(Integer acctid) {
        this.acctid = acctid;
    }

    public String getFundFunClassifyDesc() {
        return fundFunClassifyDesc;
    }

    public void setFundFunClassifyDesc(String fundFunClassifyDesc) {
        this.fundFunClassifyDesc = fundFunClassifyDesc;
    }

    public String getFnccClassifyName() {
        return fnccClassifyName;
    }

    public void setFnccClassifyName(String fnccClassifyName) {
        this.fnccClassifyName = fnccClassifyName;
    }

    @Override
    public String toString() {
        return "TfmFunctionDefinition{" +
                "fundId='" + fundId + '\'' +
                ", fundFunName='" + fundFunName + '\'' +
                ", fundFunCatagoryId='" + fundFunCatagoryId + '\'' +
                ", fundFunClassifyDesc='" + fundFunClassifyDesc + '\'' +
                ", fnccClassifyName='" + fnccClassifyName + '\'' +
                ", fundState='" + fundState + '\'' +
                ", fundFunDesc='" + fundFunDesc + '\'' +
                ", fundModifyRemarks='" + fundModifyRemarks + '\'' +
                ", isenable=" + isenable +
                ", addtime=" + addtime +
                ", adduser='" + adduser + '\'' +
                ", modtime=" + modtime +
                ", moduser='" + moduser + '\'' +
                ", acctid=" + acctid +
                '}';
    }
}