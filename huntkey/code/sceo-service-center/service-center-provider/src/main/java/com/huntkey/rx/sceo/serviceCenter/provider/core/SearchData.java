package com.huntkey.rx.sceo.serviceCenter.provider.core;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

/***********************************************************************
 * @author chenxj
 *
 * @email: kaleson@163.com
 *
 * @date : 2017年7月15日 上午11:52:45
 *
 **********************************************************************/
public class SearchData extends JSONObject {

    private static final long serialVersionUID = -1647111229957622373L;

    public SearchData(String edmName) {
        this.put(NodeConstant.EDMNAME, edmName);
    }

    public String getEdmName() {
        return this.getString(NodeConstant.EDMNAME);
    }
}
