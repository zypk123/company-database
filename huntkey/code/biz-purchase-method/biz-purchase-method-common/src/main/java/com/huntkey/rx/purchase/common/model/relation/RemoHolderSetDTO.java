package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RemoRemoHolderSetaProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

import java.math.BigDecimal;

/**
 * Created by xuyf on 2018/1/22 0022.
 */
public class RemoHolderSetDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RemoRemoHolderSetaProperty.REMO_HNAME)
    private String remoHname;

    @JSONField(name = RemoRemoHolderSetaProperty.REMO_HRATE)
    private BigDecimal remoHrate;

    @JSONField(name = RemoRemoHolderSetaProperty.REMO_HNAME_OLD)
    private String remoHnameOld;

    @JSONField(name = RemoRemoHolderSetaProperty.REMO_HRATE_OLD)
    private BigDecimal remoHrateOld;

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

    public String getRemoHname() {
        return remoHname;
    }

    public void setRemoHname(String remoHname) {
        this.remoHname = remoHname;
    }

    public BigDecimal getRemoHrate() {
        return remoHrate;
    }

    public void setRemoHrate(BigDecimal remoHrate) {
        this.remoHrate = remoHrate;
    }

    public String getRemoHnameOld() {
        return remoHnameOld;
    }

    public void setRemoHnameOld(String remoHnameOld) {
        this.remoHnameOld = remoHnameOld;
    }

    public BigDecimal getRemoHrateOld() {
        return remoHrateOld;
    }

    public void setRemoHrateOld(BigDecimal remoHrateOld) {
        this.remoHrateOld = remoHrateOld;
    }
}
