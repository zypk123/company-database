package com.huntkey.rx.sceo.serviceCenter.provider.orm.db.hbase;

import java.text.SimpleDateFormat;
import java.util.*;

import com.huntkey.rx.sceo.serviceCenter.common.emun.TableType;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchResult;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.util.Bytes;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.IndexNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.config.PersistanceConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DBHandler;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DataSet;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.HBaseException;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.model.Criteria;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.EsUtil;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.HbaseUtil;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年6月22日 下午7:41:12
 * 
 **********************************************************************/
@Service
public class HBaseHandler implements DBHandler {
	private static Logger logger = LoggerFactory.getLogger(HBaseHandler.class);

	@Autowired
	private HbaseUtil hbaseUtil; // hbase 工具类

	@Autowired
	private EsUtil esUtil; // es 工具类

	@Autowired
	private TransportClient client; // es 客户端

	/**
	 * 获取当前时间的
	 * 
	 * @return
	 */
	String getDateString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date());
	}

	// 根据id来查询
	@Override
	public DataSet load(Criteria criteria) throws HBaseException {
		String name = criteria.getTableName();
		if (name == null || name.isEmpty()) {
			String errMsg ="hbase table is null";
			logger.error(errMsg);
			throw new HBaseException(errMsg);
		}
		////////////////////////////////////////////////////////////////
		JSONArray jsonArray = criteria.getIDs();
		if (jsonArray == null) {
			return null;
		}

		List<String> listId = jsonArray.toJavaList(String.class);
		List<Get> listGet = new ArrayList<Get>();
		for (String rowKey : listId) {
			Get getRecord = new Get(Bytes.toBytes(rowKey));
			listGet.add(getRecord);
		}
		DataSet dataSet = hbaseUtil.getByGetList(name, listGet, criteria.getColumns());
		JSONObject jsonObject = dataSet.getJsonObject();
		jsonObject.put(NodeConstant.TABLENAME, name); // 表名称
		// 组装数据
		// long currenSize = listGet.size();
		long currenSize = jsonObject.getJSONArray(NodeConstant.DATASET).size();
		jsonObject.put(NodeConstant.TOTALSIZE, currenSize);
		return dataSet;
	}

	@Override
	public String merge(Criteria criteria) throws HBaseException {
		// es 索引名和hbase 表名
		String name = criteria.getTableName();
		if (name == null || name.isEmpty()) {
			String errMsg = "hbase table is null.";
			logger.error(errMsg);
			throw new HBaseException(errMsg);
		}
		Set<String> rowkeyHashSet = new HashSet<String>();
		name = name.toLowerCase();
		// 获得表的类型
		TableType tableType = TableType.getTableType(name);
		// 新增时间
		String addtime = getDateString();
		// 数据集
		JSONArray dataset = criteria.getDataset();
		// 添加的属性为空
		if (dataset == null) {
			String errMsg = "dataset is null";
			logger.error(errMsg);
			throw new HBaseException(errMsg);
		}

		JSONArray esFileds = criteria.getEsFileds();
		List<JSONObject> hbaseList = new ArrayList<JSONObject>();
		List<Map<String, Object>> esList = new ArrayList<Map<String, Object>>();
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Iterator<Object> iter = dataset.iterator();
		while (iter.hasNext()) {
			JSONObject _hbaseJson = (JSONObject) iter.next();
			String rowkey = _hbaseJson.getString(NodeConstant.ID);
			// 对rowkey 进行处理
			if ((null == rowkey || rowkey.isEmpty())) {
				// 判断hbase 是否存在这个id 如果存在继续生成新的id
				do {
					rowkey = UUID.randomUUID().toString().replaceAll("-", "");
				} while (hbaseUtil.isRowExists(name, rowkey));
			} else {
				if (hbaseUtil.isRowExists(name, rowkey)) {
					String errMsg = "hbase row is exists";
					logger.error(errMsg);
					throw new HBaseException(errMsg);
				}
			}
			// 设置hbase 的id
			_hbaseJson.put(NodeConstant.ID, rowkey);
			rowkeyHashSet.add(rowkey);
			// 添加addtime
			if (!_hbaseJson.containsKey(PersistanceConstant.P_ADDTIME)) {
				_hbaseJson.put(PersistanceConstant.P_ADDTIME, addtime);
			}
			// 添加adduser
			if (!_hbaseJson.containsKey(PersistanceConstant.P_ADDUSER)) {
				_hbaseJson.put(PersistanceConstant.P_ADDUSER, "");
			}
			hbaseList.add(_hbaseJson);
			/////////////////处理es 数据///////////////////////////////////////////////////////
			JSONObject _esJson = new JSONObject();
			if (TableType.LINK_TABLE == tableType){
				//link 表采取全表更新,筛选非null字符串
				Set<String> keySet  = _hbaseJson.keySet();
				for (String key: keySet){
					String _value = _hbaseJson.getString(key);
					if ("null".equals(_value)){
						continue;
					}
					_esJson.put(key, _value);
				}
			}else {
				_esJson.put(NodeConstant.ID, rowkey);
				if(0 < esFileds.size()){
					Iterator<Object> esFiledsIter = esFileds.iterator();
					while (esFiledsIter.hasNext()) {
						String _esFiled = (String) esFiledsIter.next();
						String _value = _hbaseJson.getString(_esFiled);
						if (!"null".equals(_value)){
							_esJson.put(_esFiled,_value);
						}
					}
				}
				// 添加addtime
				_esJson.put(PersistanceConstant.P_ADDTIME, _hbaseJson.getString(PersistanceConstant.P_ADDTIME));
				// 添加adduser
				_esJson.put(PersistanceConstant.P_ADDUSER, _hbaseJson.getString(PersistanceConstant.P_ADDUSER));
			}
			Map<String, Object> map = (Map<String, Object>) _esJson;
			esList.add(map);
		}
		// hbase表中新增数据
		hbaseUtil.putData(name, HbaseUtil.COLUMNFAMILY, hbaseList);
		// es索引中新增数据
		esUtil.bulkIndex(client, name, name, NodeConstant.ID, esList);

		String rowKeyLine = "";
		for (String str : rowkeyHashSet){
			rowKeyLine = rowKeyLine + "," +str;
		}

		rowKeyLine = rowKeyLine.replaceFirst(",","");
		return rowKeyLine;
	}

	@Override
	public void delete(Criteria criteria) throws HBaseException {
		// es 索引名和hbase 表名
		String tableName = criteria.getTableName();
		HashSet<String> idsSet = isNeedIntoEs(criteria);
		if (null != idsSet) {
			// es索引删除
			for (String id : idsSet) {
				esUtil.deleteDoc(client, tableName, tableName, id);
				// hbase删除
				hbaseUtil.deleteRow(tableName, id);
			}
		} else {
			// 需要进入 es 查询一次
			try {
				// 如果索引不存在，说明二边都没有数据，直接返回;
				JSONObject esResult = esUtil.query(client, tableName, criteria);
				JSONArray array = esResult.getJSONArray("list");
				List<String> listData = array.toJavaList(String.class);
				for (String rowkey : listData) {
					// es索引删除
					esUtil.deleteDoc(client, tableName, tableName, rowkey);
					// hbase删除
					hbaseUtil.deleteRow(tableName, rowkey);
				}
			} catch (IndexNotFoundException e) {
				return;
			}
		}
	}

	/**
	 * 判断是否需要先进入 es 查询
	 * 
	 * @param criteria
	 * @return
	 */
	public HashSet<String> isNeedIntoEs(Criteria criteria) {
		JSONArray conditions = criteria.getConditions(); // 条件
		if (conditions != null && conditions.size() == 1) {
			JSONObject conditionsJSONObject = conditions.getJSONObject(0);
			String attr = conditionsJSONObject.getString(NodeConstant.ATTR);
			if (NodeConstant.ID.equals(attr)) {
				String operator = conditionsJSONObject.getString(NodeConstant.OPERATOR);
				if ("=".equals(operator) || "in".equals(operator)) {
					String value = conditionsJSONObject.getString(NodeConstant.VALUE);
					if (value.isEmpty()){
						String errMsg = "The array of id by in is not allow to be empty!";
						logger.error(errMsg);
						throw new IllegalArgumentException(errMsg);
					}
					String[] esIdArray = value.split(",");
					List<String> list = Arrays.asList(esIdArray);
					if (0 < list.size()) {
						HashSet<String> esIdSet = new HashSet<String>();
						esIdSet.addAll(list);
						return esIdSet;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 只有and
	 * @param criteria: 条件对象
	 * @return
	 * @throws HBaseException
	 */
	@Override
	public DataSet find(Criteria criteria) throws HBaseException {
		// es 索引名和hbase 表名
		String name = criteria.getTableName();
		HashSet<String> idSet = isNeedIntoEs(criteria);
		List<String> listData = new ArrayList<String>();
		// 根据id查询
		JSONObject esResult = null;
		if (idSet != null) {
			listData.addAll(idSet);
		} else {
			esResult = esUtil.query(client, name, criteria); // es 查询
			System.out.println(esResult.toJSONString());
			JSONArray array = esResult.getJSONArray("list");
			listData = array.toJavaList(String.class);
		}

		// hbase 查询
		List<Get> listGet = new ArrayList<Get>();
		for (String rowKey : listData) {
			Get getRecord = new Get(Bytes.toBytes(rowKey));
			listGet.add(getRecord);
		}

		DataSet dataSet = hbaseUtil.getByGetList(name, listGet, criteria.getColumns());
		JSONObject jsonObject = dataSet.getJsonObject();
		jsonObject.put(NodeConstant.TABLENAME , criteria.getTableName()); // 表名称
		// 组装数据
		// long currenSize = listGet.size();
		long currenSize = jsonObject.getJSONArray(NodeConstant.DATASET).size();
		jsonObject.put(NodeConstant.SIZE, currenSize); // 当前页条目数
		if (idSet != null) {
			jsonObject.put(NodeConstant.TOTALSIZE, currenSize); // 根据id来查
		} else {
			Long totalSize = esResult.getLong("total");
			jsonObject.put(NodeConstant.TOTALSIZE, totalSize); // 总记录
		}

		if (null != criteria.getPagenation()) {
			jsonObject.put(NodeConstant.PAGENATION, criteria.getPagenation()); // 分页信息
		}
		return dataSet;
	}


	@Override
	public void update(Criteria criteria) throws DBException {
		// es 索引名和hbase 表名
		String name = criteria.getTableName();
		if (name == null || name.isEmpty()) {
			throw new HBaseException("hbase table is null");
		}
		// 判断表的类型
		TableType tableType = TableType.getTableType(name);

		String modtime = getDateString();
		// 数据集
		JSONArray dataset = criteria.getDataset();
		if (dataset == null || dataset.size() == 0) {
			String errMsg = "dataset is null";
			logger.error(errMsg);
			throw new HBaseException(errMsg);
		}

		// 处理hbase数据
		List<JSONObject> hbaseList = new ArrayList<JSONObject>();
		// 存放更新的List id
		List<String> hbaseIdList = new ArrayList<String>();
		for (Iterator<Object> iterator = dataset.iterator(); iterator.hasNext();) {
			JSONObject _hbaseJson = (JSONObject) iterator.next();
			if (!_hbaseJson.containsKey(NodeConstant.ID)) {
				String errMsg = "hbase id is null";
				logger.error(errMsg);
				throw new HBaseException(errMsg);
			}
			String _id = _hbaseJson.getString(NodeConstant.ID);
			if (!hbaseUtil.isRowExists(name, _id)) {
				String errMsg = "hbase row is not exists";
				logger.error(errMsg);
				throw new HBaseException(errMsg);
			}

			_hbaseJson.put(NodeConstant.ID, _id);
			hbaseIdList.add(_id);
			if (!_hbaseJson.containsKey(PersistanceConstant.P_MODTIME)) {
				_hbaseJson.put(PersistanceConstant.P_MODTIME, modtime);
			}
			if (!_hbaseJson.containsKey(PersistanceConstant.P_MODUSER)) {
				_hbaseJson.put(PersistanceConstant.P_MODUSER, "");
			}
			hbaseList.add(_hbaseJson);
		}

		hbaseUtil.putData(name, HbaseUtil.COLUMNFAMILY, hbaseList);
		////////////////////// 处理es数据//////////////////////////////////////////////
		JSONArray esFileds = criteria.getEsFileds();
		if (TableType.SON_TABLE == tableType) {
			esFileds.add(NodeConstant.PID);
		}
		// 如果没有索引属性，不处理es,且不是link 表,直接返回
		if (esFileds.size() == 0 && tableType != TableType.LINK_TABLE) {
			return;
		}
		// es索引中新增数据
		List<Map<String, Object>> _esList = new ArrayList<Map<String, Object>>();
		for (String _rowkey : hbaseIdList) {
			JSONObject _esJson = new JSONObject();
			// 查询hbaseUitl数据然后更新
			JSONObject _hbaseJson = hbaseUtil.getByRowKey(name, _rowkey);
			if (tableType == TableType.LINK_TABLE){
				//link 表采取全表更新,筛选非null字符串
				Set<String> keySet  = _hbaseJson.keySet();
				for (String key: keySet){
					 String _value = _hbaseJson.getString(key);
					 if ("null".equals(_value)){
					 	continue;
					 }
					_esJson.put(key, _value);
				}
			}else{
				_esJson.put(NodeConstant.ID, _rowkey);
				// 循环组装es 数据
				Iterator<Object> _esFiledsIter = esFileds.iterator();
				while (_esFiledsIter.hasNext()) {
					String _esFiled = (String) _esFiledsIter.next();
					String _value = _hbaseJson.getString(_esFiled);
					if (!"null".equals(_value)) {
						// 非null字符串
						_esJson.put(_esFiled, _value);
					}
				}
				// 添加时间
				if (_hbaseJson.containsKey(PersistanceConstant.P_ADDTIME)) {
					_esJson.put(PersistanceConstant.P_ADDTIME, _hbaseJson.getString(PersistanceConstant.P_ADDTIME));
				}
				// 添加的人
				if (_hbaseJson.containsKey(PersistanceConstant.P_ADDUSER)) {
					_esJson.put(PersistanceConstant.P_ADDUSER, _hbaseJson.getString(PersistanceConstant.P_ADDUSER));
				}
				// 提取修改时间
				_esJson.put(PersistanceConstant.P_MODTIME, _hbaseJson.getString(PersistanceConstant.P_MODTIME));
				// 修改人
				_esJson.put(PersistanceConstant.P_MODUSER, _hbaseJson.getString(PersistanceConstant.P_MODUSER));
			}
			_esList.add((Map<String, Object>) _esJson);
		}
		esUtil.bulkIndex(client, name, name, NodeConstant.ID, _esList);
		return;
	}

	@Override
	public long count(Criteria criteria) throws DBException {
		String name = criteria.getTableName();
		long count = esUtil.count(client, name, criteria); // es 查询
		return count;
	}

	/**
	 * and or not 关系的查
	 * @param criteria:
	 * @return
	 * @throws HBaseException
	 */
	@Override
	public DataSet richfind(Criteria criteria) throws HBaseException {
		String tableName = criteria.getTableName();
	//	String conditions = criteria.getConditionsInWhere();
		//1.根据where 条件到es 中查数据
		JSONObject esResult = esUtil.richfind(client, tableName, criteria);
	//	System.out.println(esResult.toJSONString());
		//2.获取rowkey 队列
		List<String> listData = new ArrayList<String>();
		JSONArray array = esResult.getJSONArray("list");
		listData = array.toJavaList(String.class);
		//3.根据rowkey 到hbase 中取数据
		List<Get> listGet = new ArrayList<Get>();
		for (String rowKey : listData) {
			Get getRecord = new Get(Bytes.toBytes(rowKey));
			listGet.add(getRecord);
		}
		//4.组装数据
		DataSet dataSet = hbaseUtil.getByGetList(tableName, listGet, criteria.getColumns());
		JSONObject jsonObject = dataSet.getJsonObject();
		jsonObject.put(NodeConstant.TABLENAME, criteria.getTableName()); // 表名称
		// 组装数据
		// long currenSize = listGet.size();
		long currenSize = jsonObject.getJSONArray(NodeConstant.DATASET).size();
		jsonObject.put(NodeConstant.SIZE, currenSize); // 当前页条目数

		Long totalSize = esResult.getLong("total");
		jsonObject.put(NodeConstant.TOTALSIZE, totalSize); // 总记录

		if (null != criteria.getPagenation()) {
			jsonObject.put(NodeConstant.PAGENATION, criteria.getPagenation()); // 分页信息
		}
		return dataSet;
	}

	@Override
	public List<Map<String, Object>> query(String sql) {
		return null;
	}
}
