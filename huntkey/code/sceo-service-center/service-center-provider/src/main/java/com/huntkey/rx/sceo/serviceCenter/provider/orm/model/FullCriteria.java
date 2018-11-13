package com.huntkey.rx.sceo.serviceCenter.provider.orm.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.config.PersistanceConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.DBUtil;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年6月26日 下午3:13:59
 * 
 **********************************************************************/

// 查询的条件参数对象
public class FullCriteria extends AbstractCriteria {

	public FullCriteria() {

	}

	public FullCriteria(JSONObject data) {
		setData(data);
		this.initData();
	}

	public FullCriteria(String data) {
		this(JSONObject.parseObject(data));
	}

	public FullCriteria(JSONObject data, String shortName) {
		this.setShortName(shortName);
		setData(data);
		initData();
	}

	private void initData() {
		if (getData().containsKey(NODE_EDMNAME)) {
			String _edmName = getData().getString(NODE_EDMNAME);
			setEdmName(_edmName);

			// 如果是_link_records 结尾的edmName参数，不需要根据简称获取物理表名称
			String _tableName = null;
			if (_edmName.endsWith(PersistanceConstant.LINK_SUFFIX)) {
				_tableName = _edmName;
			} else {
				// 调用工具类，获取对于的物理表名称
				_tableName = DBUtil.getTableName(_edmName, getShortName());
			}

			setTableName(_tableName);
		}

		if (getData().containsKey(NODE_COLUMNS)) {
			this.setColumns(getData().getJSONArray(NODE_COLUMNS));
		}

		if (getData().containsKey(NODE_ESFILEDS)) {
			this.setEsFileds(getData().getJSONArray(NODE_ESFILEDS));
		}

		if (getData().containsKey(NODE_CONDITIONS)) {
			JSONArray _conditions = getData().getJSONArray(NODE_CONDITIONS);
			this.setConditions(_conditions);
		}

		if (getData().containsKey(NODE_PAGENATION)) {
			JSONObject _pagenation = getData().getJSONObject(NODE_PAGENATION);
			this.setPagenation(_pagenation);
		}

		if (getData().containsKey(NODE_DATASET)) {
			this.setDataset(getData().getJSONArray(NODE_DATASET));
		}

		if (getData().containsKey(NODE_ORDERBY)) {
			this.setOrderBy(getData().getJSONArray(NODE_ORDERBY));
		}

		if (getData().containsKey(NODE_IDS)) {
			this.setIDs(getData().getJSONArray(NODE_IDS));
		}

		if (getData().containsKey(NODE_WHERE)){
			this.setConditionsInWhere(getData().getString(NODE_WHERE));
		}
	}

}
