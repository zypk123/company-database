package com.huntkey.rx.sceo.serviceCenter.common.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 *  新增、 修改、 删除参数对象
 *  
 * Created by chenxj on 2017/8/10.
 */
public class MergeParam extends JSONObject implements Param{

	private static final long serialVersionUID = -7944277172120159532L;

	public MergeParam(String edmName) {
		this.put(NodeConstant.EDMNAME, edmName);
		this.put(NodeConstant.PARAMS, new JSONArray());
	}
	
	/**
	 * 增加数据对象
	 * 
	 * @param data
	 * @return
	 */
	public MergeParam addData(Object data){
		this.getJSONArray(NodeConstant.PARAMS).add(data);
		
		return this;
	}
	
	 /**
     * 增加所有数据对象
     * 
     * @param data
     * @return
     */
    public MergeParam addAllData(JSONArray data){
        this.put(NodeConstant.PARAMS,data);
        return this;
    }
	
	@Override
	public String getEdmName() {
		return this.getString(NodeConstant.EDMNAME);
	}

	@Override
	public JSONObject getSearch() {
		return this.getJSONObject(NodeConstant.SEARCH);
	}

	@Override
	public InputArgument build() {
		return new FullInputArgument(this);
	}
}
