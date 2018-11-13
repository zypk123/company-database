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
public class ServiceData extends JSONObject {

    private static final long serialVersionUID = -1647111229957622373L;

    public ServiceData(String edmName) {
        this.put(NodeConstant.EDMNAME, edmName);
        this.put(NodeConstant.DATASET, new JSONArray());
    }

    public JSONArray getDataset() {
        return this.getJSONArray(NodeConstant.DATASET);
    }

    public String getEdmName() {
        return this.getString(NodeConstant.EDMNAME);
    }

    public JSONArray getES() {
        if (!this.containsKey(NodeConstant.ES_FIELDS)) {
            this.put(NodeConstant.ES_FIELDS, new JSONArray());
        }

        return this.getJSONArray(NodeConstant.ES_FIELDS);
    }

    public ServiceData addData(Object data) {
        this.getJSONArray(NodeConstant.DATASET).add(data);
        return this;
    }
}
