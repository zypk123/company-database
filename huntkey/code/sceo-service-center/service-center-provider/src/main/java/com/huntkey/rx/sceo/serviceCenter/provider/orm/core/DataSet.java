package com.huntkey.rx.sceo.serviceCenter.provider.orm.core;

import com.alibaba.fastjson.JSONObject;

/***********************************************************************
 * @author chenxj
 *
 * @email: kaleson@163.com											  
 *
 * @date : 2017年6月26日 下午3:02:17											 
 *
 **********************************************************************/
public class DataSet extends JSONObject {

    private static final long serialVersionUID = -7039243815812380880L;

    private JSONObject jsonObject;

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

}
