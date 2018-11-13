package com.huntkey.rx.purchase.common.model.park;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.purchase.common.model.base.EdmDTO;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.io.Serializable;

/**
 * Created by liangh on 2017/12/27 0027.
 *
 */
public class ParkDTO extends EdmDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name="rpak_name")
    private String rpakName;//园区名称

    @JSONField(name="rpak_code")
    private String rpakCode;//园区编码

    @JSONField(name="rpak_addr")
    private String rpakAddr;//园区地址

    @JSONField(name="rpak_isdefault")
    private String rpakIsdefault;//是否是默认园区

    private boolean haveGoods;//已经存在物品信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRpakName() {
        return rpakName;
    }

    public void setRpakName(String rpakName) {
        this.rpakName = rpakName;
    }

    public String getRpakCode() {
        return rpakCode;
    }

    public void setRpakCode(String rpakCode) {
        this.rpakCode = rpakCode;
    }

    public String getRpakAddr() {
        return rpakAddr;
    }

    public void setRpakAddr(String rpakAddr) {
        this.rpakAddr = rpakAddr;
    }

    public String getRpakIsdefault() {
        return rpakIsdefault;
    }

    public void setRpakIsdefault(String rpakIsdefault) {
        this.rpakIsdefault = rpakIsdefault;
    }

    public boolean isHaveGoods() {
        return haveGoods;
    }

    public void setHaveGoods(boolean haveGoods) {
        this.haveGoods = haveGoods;
    }

    @Override
    public String toString() {
        return "ParkDTO{" +
                "id='" + id + '\'' +
                ", rpakName='" + rpakName + '\'' +
                ", rpakCode='" + rpakCode + '\'' +
                ", rpakAddr='" + rpakAddr + '\'' +
                ", rpakIsdefault='" + rpakIsdefault + '\'' +
                ", haveGoods=" + haveGoods +
                '}';
    }
}
