package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;


/**
 * Created by zhouyou on 2017/11/21.
 */
public class RempContSetDTO {

    //员工合同记录类ID
    @JSONField(name = NodeConstant.ID)
    private String id;

    //员工类外键
    @JSONField(name = NodeConstant.PID)
    private String pid;

    //签订类型
    @JSONField(name = RempContSetConstants.REMP_SING_TYPE)
    private String rempSignType;

    //合同开始日期
    @JSONField(name=RempContSetConstants.REMP_CONT_BEG)
    private String rempContBeg;

    //合同结束日期
    @JSONField(name=RempContSetConstants.REMP_CONT_END)
    private String rempContEnd;

    //签订日期
    @JSONField(name=RempContSetConstants.REMP_CONT_SDATE)
    private String rempContSdate;

    //法人公司
    @JSONField(name=RempContSetConstants.REMP_CONT_MCOP)
    private String rempContMcop;

    //合同实际结束日期
    @JSONField(name=RempContSetConstants.REMP_CONT_REND)
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
