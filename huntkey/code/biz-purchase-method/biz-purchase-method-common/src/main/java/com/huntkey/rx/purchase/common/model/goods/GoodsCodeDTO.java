package com.huntkey.rx.purchase.common.model.goods;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.io.Serializable;

/**
 * 物品编号DTO 用于返回模糊查询得到物品编号
 * @author zhoucj
 * @date 2018/1/20
 */
public class GoodsCodeDTO implements Serializable {
    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name="gods_code")
    private String godsCode;//物品编码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGodsCode() {
        return godsCode;
    }

    public void setGodsCode(String godsCode) {
        this.godsCode = godsCode;
    }
}
