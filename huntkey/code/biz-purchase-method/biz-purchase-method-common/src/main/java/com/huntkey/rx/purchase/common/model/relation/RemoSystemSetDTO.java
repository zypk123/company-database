package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RemoRemoSystemSetaProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

/**
 * Created by xuyf on 2018/1/22 0022.
 */
public class RemoSystemSetDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RemoRemoSystemSetaProperty.REMO_SNAME)
    private String remoSname;

    @JSONField(name = RemoRemoSystemSetaProperty.REMO_SNAME_OLD)
    private String remoSnameOld;

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

    public String getRemoSname() {
        return remoSname;
    }

    public void setRemoSname(String remoSname) {
        this.remoSname = remoSname;
    }

    public String getRemoSnameOld() {
        return remoSnameOld;
    }

    public void setRemoSnameOld(String remoSnameOld) {
        this.remoSnameOld = remoSnameOld;
    }
}
