package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.util.Date;

/**
 *  合同记录
 * @author zhouyou
 * @date 2017/12/1
 */
public class ContractRecordDTO {
    //ID
    @JSONField(name = ContractRecordConsts.ID)
    private String id;

    //PID
    @JSONField(name = ContractRecordConsts.PID)
    private String pid;

    //签订类型
    @JSONField(name = ContractRecordConsts.REMP_SIGN_TYPE)
    private String rempSignType;

    //合同开始日期
    @JSONField(name = ContractRecordConsts.REMP_CONT_BEG)
    private String rempContBeg;

    //合同结束日期
    @JSONField(name = ContractRecordConsts.REMP_CONT_END)
    private String rempContEnd;

    //签订日期
    @JSONField(name = ContractRecordConsts.REMP_CONT_SDATE)
    private String rempContSdate;

    //法人公司
    @JSONField(name = ContractRecordConsts.REMP_CONT_MCOP)
    private String rempContMcop;

    //合同实际结束日期
    @JSONField(name = ContractRecordConsts.REMP_CONT_REND)
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

    public String getRempContEnd() {
        return rempContEnd;
    }

    public void setRempContEnd(String rempContEnd) {
        this.rempContEnd = rempContEnd;
    }

    public String getRempContRend() {
        return rempContRend;
    }

    public void setRempContRend(String rempContRend) {
        this.rempContRend = rempContRend;
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
}
