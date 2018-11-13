package com.huntkey.rx.sceo.formula.common.model;

import com.huntkey.rx.sceo.formula.common.base.BaseEntity;

import java.util.Date;

/**
 * @author chenfei on 2017/5/15.
 */
public class FtmFunctionClassify extends BaseEntity {
    /** 编号 */
    private String fnccId;

    /** 分类名称 */
    private String fnccClassifyName;

    /** 分类编码 */
    private String fnccClassifyCode;

    /** 分类描述 */
    private String fnccClassifyDesc;

    /** 分类状态  1 无效  0 有效 */
    private String fnccState;

    /** 是否可用: 1 可用， 0 不可用 */
    private Byte isenable;

    /** 创建时间 */
    private Date addtime;

    /** 创建人 */
    private String adduser;

    /** 更新时间 */
    private Date modtime;

    /** 修改人 */
    private String moduser;

    /** 区域标识 */
    private Integer acctid;

    /** 编译后的class文件内容 */
    private byte[] fuccClassifyBinary;

    public String getFnccId() {
        return fnccId;
    }

    public void setFnccId(String fnccId) {
        this.fnccId = fnccId == null ? null : fnccId.trim();
    }

    public String getFnccClassifyName() {
        return fnccClassifyName;
    }

    public void setFnccClassifyName(String fnccClassifyName) {
        this.fnccClassifyName = fnccClassifyName == null ? null : fnccClassifyName.trim();
    }

    public String getFnccClassifyCode() {
        return fnccClassifyCode;
    }

    public void setFnccClassifyCode(String fnccClassifyCode) {
        this.fnccClassifyCode = fnccClassifyCode == null ? null : fnccClassifyCode.trim();
    }

    public String getFnccClassifyDesc() {
        return fnccClassifyDesc;
    }

    public void setFnccClassifyDesc(String fnccClassifyDesc) {
        this.fnccClassifyDesc = fnccClassifyDesc == null ? null : fnccClassifyDesc.trim();
    }

    public String getFnccState() {
        return fnccState;
    }

    public void setFnccState(String fnccState) {
        this.fnccState = fnccState == null ? null : fnccState.trim();
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

    public byte[] getFuccClassifyBinary() {
        return fuccClassifyBinary;
    }

    public void setFuccClassifyBinary(byte[] fuccClassifyBinary) {
        this.fuccClassifyBinary = fuccClassifyBinary;
    }
}