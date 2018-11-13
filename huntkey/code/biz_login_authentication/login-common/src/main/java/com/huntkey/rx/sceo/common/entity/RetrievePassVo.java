package com.huntkey.rx.sceo.common.entity;

/**
 * 找回密码VO
 *
 * @author zhangyu
 * @create 2018-03-15 16:58
 **/
public class RetrievePassVo {

    /**
     * 身份证号
     */
    private String epeoCardNo;

    /**
     * 手机号
     */
    private String epeoTel;

    /**
     * 验证码
     */
    private String verificationCode;

    /**
     * 新密码
     */
    private String epeoPasswordNew;

    /**
     * 确认新密码
     */
    private String epeoPasswordNewAgain;

    public String getEpeoCardNo() {
        return epeoCardNo;
    }

    public void setEpeoCardNo(String epeoCardNo) {
        this.epeoCardNo = epeoCardNo;
    }

    public String getEpeoTel() {
        return epeoTel;
    }

    public void setEpeoTel(String epeoTel) {
        this.epeoTel = epeoTel;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getEpeoPasswordNew() {
        return epeoPasswordNew;
    }

    public void setEpeoPasswordNew(String epeoPasswordNew) {
        this.epeoPasswordNew = epeoPasswordNew;
    }

    public String getEpeoPasswordNewAgain() {
        return epeoPasswordNewAgain;
    }

    public void setEpeoPasswordNewAgain(String epeoPasswordNewAgain) {
        this.epeoPasswordNewAgain = epeoPasswordNewAgain;
    }

    @Override
    public String toString() {
        return "RetrievePassVo{" +
                "epeoCardNo='" + epeoCardNo + '\'' +
                ", epeoTel='" + epeoTel + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", epeoPasswordNew='" + epeoPasswordNew + '\'' +
                ", epeoPasswordNewAgain='" + epeoPasswordNewAgain + '\'' +
                '}';
    }
}
