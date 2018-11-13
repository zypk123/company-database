/***********************************************************************
 * @author chenxj
 *
 * @email: kaleson@163.com
 *
 * @date : 2017年6月22日 下午7:40:29
 *
 **********************************************************************/
package com.huntkey.rx.sceo.serviceCenter.provider.orm.db.mysql;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.config.PersistanceConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DBHandler;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DataSet;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.dao.FormDataMapper;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.HBaseException;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.model.Criteria;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.MysqlDBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MySQLHandler implements DBHandler {

	private static Logger logger = LoggerFactory.getLogger(MySQLHandler.class);

	@Autowired
	private FormDataMapper formDataMapper;

	/**
	 * @param criteria:条件对象
	 * @return dataset
	 * @throws HBaseException
	 */
	@Override
	public DataSet load(Criteria criteria) throws HBaseException {
		String name = criteria.getTableName();
		if (name == null || name.isEmpty()) {
			String errMsg = "mysql table is null";
			logger.error(errMsg);
			throw new HBaseException(errMsg);
		}
		JSONArray jsonArray = criteria.getIDs();
		if (jsonArray == null) {
			return null;
		}
		List<Map<String, Object>> list = new ArrayList<>();
		List<String> listId = jsonArray.toJavaList(String.class);
		for (String id : listId) {
			Map<String, Object> map = new HashMap<>();
			map.put(PersistanceConstant.TABLE_NAME, name);
			map.put(PersistanceConstant.P_COLUMNS, "*");
			map.put(PersistanceConstant.P_CONDITION, " id = " + "'"+id+"'");
			list.addAll(formDataMapper.select(map));
		}
		DataSet dataSet = new DataSet();
		JSONArray array = new JSONArray();
		for(Map<String, Object> map: list){
			JSONObject object = new JSONObject();
			for(Map.Entry entry: map.entrySet()){
				object.put(entry.getKey().toString(), entry.getValue().toString());
			}
			array.add(object);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(NodeConstant.DATASET, array);
		jsonObject.put(NodeConstant.TABLENAME, name); // 表名称
		// 组装数据
		long size = jsonObject.getJSONArray(NodeConstant.DATASET).size();
		jsonObject.put(NodeConstant.SIZE, size);
		dataSet.setJsonObject(jsonObject);
		return dataSet;
	}

	/**
	 * 表单数据insert到mysql数据库表中方法
	 * @param criteria 入参criteria
	 */
	@Override
	public String merge(Criteria criteria) {
		String id = "";
		List<Map<String, Object>> mapList = MysqlDBUtil.getInsertDatas(criteria);
		for(Map<String, Object> map: mapList){
			formDataMapper.insert(map);
			id +=  map.get(NodeConstant.ID).toString() + ",";
			logger.info("------------id={}", id);
		}

		return id;
	}

	/**
	 * 删除
	 * @param criteria:条件对象
	 */
	@Override
	public void delete(Criteria criteria) {
		Map<String, Object> map = MysqlDBUtil.getDeleteMap(criteria);
		for(Map.Entry entry: map.entrySet()){
			logger.info(entry.getKey()+":"+entry.getValue());
			logger.info("------------");
		}
		formDataMapper.delete(map);
	}

	/**
	 * 查询数据
	 * @param criteria 入参criteria
	 * @return dataset
	 */
	@Override
	public DataSet find(Criteria criteria) {
		//1.组装查询的结果
		Map<String, Object> map = MysqlDBUtil.getSelectMap(criteria);
		JSONObject pagenation = criteria.getPagenation();
		JSONObject jsonObject = new JSONObject();
		if(!StringUtil.isNullOrEmpty(pagenation)){
			int pageNum = Integer.valueOf(pagenation.get(NodeConstant.START_PAGE).toString());
			int pageSize = Integer.valueOf(pagenation.get(NodeConstant.ROWS).toString());
			PageHelper.startPage(pageNum, pageSize);
			jsonObject.put(NodeConstant.PAGENATION, pagenation);
		}
		//2.查询结果
		List<Map<String, Object>> mapList = formDataMapper.select(map);
		//3.对结果进行格式重组
		String tableName = criteria.getTableName();
		JSONArray jsonArray = new JSONArray();
		//获取数据的总数
		jsonObject.put(NodeConstant.SIZE, mapList.size());
		Map<String, String> countMap = new HashMap<>();
		countMap.put(PersistanceConstant.TABLE_NAME, tableName);
		if(map.containsKey(PersistanceConstant.P_CONDITION)){
			Object object = map.get(PersistanceConstant.P_CONDITION);
			if(object != null){
				countMap.put(PersistanceConstant.P_CONDITION,object.toString());
			}
		}

		long totalSize = formDataMapper.count(countMap);
		jsonObject.put(NodeConstant.TOTALSIZE, totalSize); // 根据id来查
		jsonObject.put(PersistanceConstant.TABLE_NAME, tableName);

		for(Map<?,?> m: mapList){
			JSONObject object = new JSONObject();
			if (m == null){
				jsonArray.add(object);
				continue;
			}
			for(Object key: m.keySet()){
				object.put(key.toString(), m.get(key));
			}
			jsonArray.add(object);
		}
		DataSet dataSet = new DataSet();
		jsonObject.put(NodeConstant.DATASET, jsonArray);
		dataSet.setJsonObject(jsonObject);
		return dataSet;
	}


	/**
	 * 修改
	 * @param criteria
	 */
	@Override
	public void update(Criteria criteria) {
		List<Map<String, Object>> list = MysqlDBUtil.getUpdateMap(criteria);
		for(Map<String, Object> map: list){
			formDataMapper.update(map);
		}
	}

	/**
	 * 统计数量
	 * @param criteria
	 * @return
	 */
	@Override
	public long count(Criteria criteria){
		Map<String,String> countMap = MysqlDBUtil.getCountMap(criteria);
		long total = formDataMapper.count(countMap);
		return total;
	}
	/**
	 * 富查询
	 * @param criteria:
	 * 条件对象
	 *
	 * @return
	 * @throws DBException
	 */
	@Override
	public DataSet richfind(Criteria criteria) throws DBException{
		Map<String,String> sqlMap = MysqlDBUtil.getRichFindMap(criteria);
		List<Map<String, Object>> mapList = formDataMapper.richfind(sqlMap);
		//对查询结果进行重组
		//查询count
		String tableName = criteria.getTableName();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject =  new JSONObject();
		jsonObject.put(NodeConstant.SIZE, mapList.size());
		Map<String, String> countMap = new HashMap<>();
		countMap.put(PersistanceConstant.TABLE_NAME, tableName);
		countMap.put(PersistanceConstant.P_CONDITION, sqlMap.get(PersistanceConstant.P_CONDITION));
		long totalSize = formDataMapper.count(countMap);
		jsonObject.put(NodeConstant.TOTALSIZE, totalSize); // 根据id来查
		jsonObject.put(NodeConstant.TABLENAME,tableName);
		//添加 dataset 结果集
		for (Map<?,?> m :mapList){
			JSONObject object = new JSONObject();
			for(Object key:m.keySet()){
				object.put(key.toString(),m.get(key));
			}
			jsonArray.add(object);
		}
		DataSet dataSet = new DataSet();
		jsonObject.put(NodeConstant.DATASET,jsonArray);
		dataSet.setJsonObject(jsonObject);;
		return dataSet ;
	}

	@Override
	public List<Map<String, Object>> query(String sql) {
		return formDataMapper.query(sql);
	}
}