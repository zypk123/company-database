package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;


/**
 * Created by zhouyou on 2017/11/23.
 */
public class EmpContSetDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;//合同记录主键
    private String pid;//员工类id
    //签订类型
    @JSONField(name = EmpContSetConstants.REMP_SIGN_TYPE)
    private String rempSignType;

    //合同开始日期
    @JSONField(name = EmpContSetConstants.REMP_CONT_BEG)
    private String rempContBeg;

    //合同结束日期
    @JSONField(name = EmpContSetConstants.REMP_CONT_END)
    private String rempContEnd;

    //签订日期
    @JSONField(name = EmpContSetConstants.REMP_CONT_SDATE)
    private String rempContSdate;

    //法人公司
    @JSONField(name = EmpContSetConstants.REMP_CONT_MCOP)
    private String rempContMcop;

    //合同实际结束日期
    @JSONField(name = EmpContSetConstants.REMP_CONT_REND)
    private String rempContRend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRempSignType() {
        return rempSignType;
    }

    public void setRempSignType(String rempSignType) {
        this.rempSignType = rempSignType;
    }

    public String getRempContBeg() {
        return rempContBeg;
    }

    public void setRempContBeg(String rempContBeg) {
        this.rempContBeg = rempContBeg;
    }

    public String getRempContEnd() {
        return rempContEnd;
    }

    public void setRempContEnd(String rempContEnd) {
        this.rempContEnd = rempContEnd;
    }

    public String getRempContSdate() {
        return rempContSdate;
    }

    public void setRempContSdate(String rempContSdate) {
        this.rempContSdate = rempContSdate;
    }

    public String getRempContMcop() {
        return rempContMcop;
    }

    public void setRempContMcop(String rempContMcop) {
        this.rempContMcop = rempContMcop;
    }

    public String getRempContRend() {
        return rempContRend;
    }

    public void setRempContRend(String rempContRend) {
        this.rempContRend = rempContRend;
    }
}
