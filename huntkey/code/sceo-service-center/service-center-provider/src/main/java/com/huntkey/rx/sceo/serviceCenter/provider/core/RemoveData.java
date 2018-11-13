package com.huntkey.rx.sceo.serviceCenter.provider.core;

import com.alibaba.fastjson.JSONArray;
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
public class RemoveData extends JSONObject {

    private static final long serialVersionUID = -1647111229957622373L;


    public RemoveData(String edmName) {
        this.put(NodeConstant.EDMNAME, edmName);
        this.put(NodeConstant.CONDITIONS, new JSONArray());
    }

    public JSONArray getDataset() {
        return this.getJSONArray(NodeConstant.CONDITIONS);
    }

    public String getEdmName() {
        return this.getString(NodeConstant.EDMNAME);
    }

    public RemoveData addWhereData(Object data) {
        this.getJSONArray(NodeConstant.CONDITIONS).add(data);
        return this;
    }

    public RemoveData addColumnsData(Object data) {
        this.getJSONArray(NodeConstant.COLUMNS).add(data);
        return this;
    }
}
