package com.huntkey.rx.purchase.common.model.currency;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 币别类DTO
 *
 * @author zhoucj
 * @date 2018/1/19
 */
public class CurrencyDTO implements Serializable{
    //币别类id
    @JSONField(name = "id")
    private String id;

    //币别代码
    @JSONField(name = "curr_code")
    private String currCode;

    //币别名称
    @JSONField(name = "curr_name")
    private String currName;

    //币别描述
    @JSONField(name = "curr_desc")
    private String currDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public String getCurrName() {
        return currName;
    }

    public void setCurrName(String currName) {
        this.currName = currName;
    }

    public String getCurrDesc() {
        return currDesc;
    }

    public void setCurrDesc(String currDesc) {
        this.currDesc = currDesc;
    }
}
