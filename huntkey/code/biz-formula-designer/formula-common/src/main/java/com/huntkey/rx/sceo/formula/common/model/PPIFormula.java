package com.huntkey.rx.sceo.formula.common.model;

import java.util.Date;

/**
 * @author chengchen on 2017/11/29.
 */
public class PPIFormula {
    /**ppiId*/
    private String ppiId;
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


    public String getPpiId() {
        return ppiId;
    }

    public void setPpiId(String ppiId) {
        this.ppiId = ppiId;
    }

    public String getFrmuId() {
        return frmuId;
    }

    public void setFrmuId(String frmuId) {
        this.frmuId = frmuId;
    }

    public String getFrmuFormulaContent() {
        return frmuFormulaContent;
    }

    public void setFrmuFormulaContent(String frmuFormulaContent) {
        this.frmuFormulaContent = frmuFormulaContent;
    }

    public String getFrmuFormulaStyle() {
        return frmuFormulaStyle;
    }

    public void setFrmuFormulaStyle(String frmuFormulaStyle) {
        this.frmuFormulaStyle = frmuFormulaStyle;
    }

    public Byte getIsenable() {
        return isenable;
    }

    public void setIsenable(Byte isenable) {
        this.isenable = isenable;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser;
    }

    public Date getModtime() {
        return modtime;
    }

    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }

    public String getModuser() {
        return moduser;
    }

    public void setModuser(String moduser) {
        this.moduser = moduser;
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
        this.frmuFormulaText = frmuFormulaText;
    }
}
