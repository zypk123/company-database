package com.huntkey.rx.sceo.formula.common.model;

import com.huntkey.rx.sceo.formula.common.base.BaseEntity;

import java.util.Date;

/**
 * @author chenfei on 2017/5/15.
 */
public class TfdFormula extends BaseEntity {
    /** 编号 */
    private String frmuId;

    /**  */
    private String frmuFormulaContent;

    /**  */
    private String frmuFormulaStyle;

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

    /** 其他系统展示公式内容 */
    private String frmuFormulaText;

    public String getFrmuId() {
        return frmuId;
    }

    public void setFrmuId(String frmuId) {
        this.frmuId = frmuId == null ? null : frmuId.trim();
    }

    public String getFrmuFormulaContent() {
        return frmuFormulaContent;
    }

    public void setFrmuFormulaContent(String frmuFormulaContent) {
        this.frmuFormulaContent = frmuFormulaContent == null ? null : frmuFormulaContent.trim();
    }

    public String getFrmuFormulaStyle() {
        return frmuFormulaStyle;
    }

    public void setFrmuFormulaStyle(String frmuFormulaStyle) {
        this.frmuFormulaStyle = frmuFormulaStyle == null ? null : frmuFormulaStyle.trim();
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

    public String getFrmuFormulaText() {
        return frmuFormulaText;
    }

    public void setFrmuFormulaText(String frmuFormulaText) {
        this.frmuFormulaText = frmuFormulaText == null ? null : frmuFormulaText.trim();
    }
}