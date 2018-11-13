package com.huntkey.rx.sceo.serviceCenter.provider.core.vallidate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.serviceCenter.common.emun.OperatorType;
import com.huntkey.rx.sceo.serviceCenter.common.function.Regex;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.client.EDMClient;
import com.huntkey.rx.sceo.serviceCenter.provider.core.ServiceParse;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.config.PersistanceConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by chenxj on 2017/8/1.
 * mysql 数据库
 */
@Component(value = "mysqlValidate")
public class MysqlDataValidate implements DataValidate {

	private static Logger logger =LoggerFactory.getLogger(MysqlDataValidate.class);

	@Autowired
	private EDMClient EDMClient;

	@Value("${edm.version}")
	private String edmVersion;

	@Autowired
	private ServiceParse serviceParse;

	//mysql
	public final static String DEL_COLUMNS = "is_del";
	/**
	 * 获取表名对应的表名称 (hbase 的，mysql一样吗)
	 * @param edmName
	 * @return
	 */
	public String getTableName(String edmName){
		String tableName = null;
		if (edmName.endsWith(PersistanceConstant.LINK_SUFFIX)){
			tableName = edmName;
		}else{
			tableName = DBUtil.getTableName(edmName,serviceParse.getShortName(edmName));
		}
		return tableName;
	}

	/**
	 * 新增对象的参数校验
	 *
	 * @param serviceData
	 * @return
	 */
	public boolean validate4add(JSONObject serviceData) {
		// 1. 如果是 edmName 是以_link_records 结尾的，跳过参数校验
//		String edmName = serviceData.getString(NodeConstant.EDMNAME);
//		//1.获取表名
//		String tableName = getTableName(edmName);
//		TableType tableType = TableType.getTableType(tableName);
//		if (tableType == TableType.LINK_TABLE) {
//			edmName = edmName.replaceAll(TableType.linkTable, "");
//		}
//		// 2. 校验edmName 是否有效
//		boolean edmFlag = validateEdmName(edmName);
//		if (!edmFlag) {
//			return false;
//		}
//		// 3. 校验dataset的编码是否有效；
//		if (tableType == TableType.LINK_TABLE){
//			return true;
//		}else if(tableType == TableType.MAIN_TABLE || tableType == TableType.SON_TABLE){
//			JSONArray jsonArray = serviceData.getJSONArray(NodeConstant.DATASET);
//			boolean datasetflag = validateDataset4add(jsonArray, edmName);
//			if (!datasetflag) {
//				return false;
//			}
//		}else{
//			String errMsg = "There is unknowed type of the table!";
//			logger.debug(errMsg);
//			throw new IllegalArgumentException(errMsg);
//		}

		return true;
	}

	/**
	 * 修改对象的参数校验
	 *
	 * @param serviceData
	 * @return
	 */
	public boolean validate4update(JSONObject serviceData) {
//		// 1. 如果是 edmName 是以_link_records 结尾的，跳过参数校验
//		String edmName = serviceData.get(NodeConstant.EDMNAME).toString();
//		//1.获取表名
//		String tableName = getTableName(edmName);
//		TableType tableType = TableType.getTableType(tableName);
//		if (tableType == TableType.LINK_TABLE) {
//			edmName = edmName.replaceAll(TableType.linkTable, "");
//		}
//		// 2. 校验edmName 是否有效
//		boolean edmFlag = validateEdmName(edmName);
//		if (!edmFlag) {
//			return false;
//		}
//
//		// 3. 校验dataset的编码是否有效,dataset数据行是否包含id
//		if (tableType == TableType.LINK_TABLE){
//			return true;
//		}else if(tableType == TableType.MAIN_TABLE || tableType == TableType.SON_TABLE) {
//			JSONArray jsonArray = serviceData.getJSONArray(NodeConstant.DATASET);
//			boolean datasetflag = validateDataset4modify(jsonArray, edmName);
//			if (!datasetflag) {
//				return false;
//			}
//		}else {
//			String errMsg = "There is unknowed type of the table!";
//			logger.debug(errMsg);
//			throw new IllegalArgumentException(errMsg);
//		}
		return true;
	}

	/**
	 * 删除对象的参数校验
	 *
	 * @param serviceData
	 * @return
	 */
	public boolean validate4remove(JSONObject serviceData) {
//		// 1. 如果是 edmName 是以_link_records 结尾的，跳过参数校验
//		String edmName = serviceData.get(NodeConstant.EDMNAME).toString();
//		if (edmName.endsWith(Persistance.PREFIX)) {
//			return true;
//		}
//
//		// 2. 校验edmname是否有效；
//		boolean edmFlag = validateEdmName(edmName);
//		if (!edmFlag) {
//			return false;
//		}
//
//		// 3. 校验conditions的attr编码是否有效；
//		JSONArray conditionAraay = serviceData.getJSONArray(NodeConstant.CONDITIONS);
//		boolean conditionsflag = validateConditions(conditionAraay, edmName);
//		if (!conditionsflag) {
//			return false;
//		}
		return true;
	}

	/**
	 * 查询对象的参数校验
	 *
	 * @param serviceData
	 * @return
	 */
	public boolean validate4search(JSONObject serviceData) {
		return true;
	}


	public boolean validate4richfind(JSONObject serviceData){
		// 1. 如果是 edmName 是以_link_records 结尾的，跳过参数校验

//		// 2. 校验edmName 是否有效
//		boolean edmFlag = validateEdmName(edmName);
//		if (!edmFlag) {
//			return false;
//		}

		//3.校验 where 条件
		String condition = serviceData.getString(NodeConstant.WHERE);
		return validateSearchConditions(condition);
	}

	/**
	 *
	 * @param searchConditions
	 */
	private boolean validateSearchConditions(String searchConditions){
		//校验where 条件是否纯conditions ,防止注入
		//1.先删除字符串的变量
		String valueRegexString = "(\".{0,}\"|'.{0,}')";
		String sentences = searchConditions.replaceAll(valueRegexString,"");
		//2 再判断是否存在需要过滤的sql 关键字,防止注入
		String keyRegex = "(;|^select|^update|^insert|^delete|^drop)";
		return !Regex.isFind(sentences,keyRegex);
	}

	/**
	 * edmName的参数校验
	 *
	 * @param edmName
	 * @return
	 */
	public boolean validateEdmName(String edmName) {
		// edmName判空
		if (edmName == null || " ".equals(edmName)) {
			logger.info("{0} edmName is null", edmName);
			throw new IllegalArgumentException("edmName is null");
		}

		// edmName判断有效
		Result result = EDMClient.getEdmShortName(edmVersion, edmName);
		if (result.getData() == null) {
			logger.info(" edmName:{0} validation failure!", edmName);
			throw new IllegalArgumentException("edmName:" + edmName + " validation failure!");
		}

		return true;
	}

	/**
	 * 增加时dataset的参数校验
	 * 
	 * @param jsonArray
	 * @param edmName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean validateDataset4add(JSONArray jsonArray, String edmName) {
		if (jsonArray.isEmpty()) { // 空数组
			logger.info("{0} dataset is empty!",jsonArray);
			throw new IllegalArgumentException("dataset is empty!");
		}
		return true;
		//获取属性集,比对是否有效属性,mysql 里面视乎不需要
//		Set<String> set = new HashSet<String>();
//		Iterator<Object> iterator = jsonArray.iterator();
//		while (iterator.hasNext()) {
//			Map<String, String> _dataMap = (Map<String, String>) iterator.next();
//
//			// 1. 比较edm属性之前，过滤掉系统属性,属性拼接成 edmName + "." + attr
//			Set<String> _data_set = filterSystemKeys(_dataMap.keySet(), edmName);
//			set.addAll(_data_set);
//		}
//
//		// dataset里面的属性与edm返回的属性进行比对
//		return checkMapFromSet(set, edmName);
	}

	/**
	 * 修改时dataset的参数校验
	 * 
	 * @param jsonArray
	 * @param edmName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean validateDataset4modify(JSONArray jsonArray, String edmName) {
//		if (jsonArray.size() == 0) { // 空数组
//			logger.info("{0} dataset is null", jsonArray);
//			throw new IllegalArgumentException("dataset is null");
//		}
//		Set<String> set = new HashSet<String>();
//
//		// 遍历校验每个dataset数据
//		Iterator<Object> iterator = jsonArray.iterator();
//		while (iterator.hasNext()) {
//			Map<String, String> _dataMap = (Map<String, String>) iterator.next();
//
//			// 1. 检查map中是否包含moduser属性；
//			if (!_dataMap.containsKey(PersistanceConstant.P_MODUSER)) {
//				logger.info("{0} doesn't contain key: moduser", _dataMap);
//				throw new IllegalArgumentException("dataset doesn't contain key: moduser");
//			}
//
//			// 2. 检查map中是否包含modtime属性；
//			if (!_dataMap.containsKey(PersistanceConstant.P_MODTIME)) {
//				logger.info("{0} doesn't contain key: modtime", _dataMap);
//				throw new IllegalArgumentException("dataset doesn't contain key: modtime");
//			}
//
//			// 3. 检查map中是否包含id属性；
//			if (!_dataMap.containsKey(NodeConstant.ID)) {
//				logger.info("{0} doesn't contain key: id", _dataMap);
//				throw new IllegalArgumentException("dataset doesn't contain key: id");
//			}
//			// update 中不能存在adduser
//			if (_dataMap.containsKey(PersistanceConstant.P_ADDUSER)) {
//				_dataMap.remove(PersistanceConstant.P_ADDUSER);
//			//	logger.info("{0} can't contain key: adduser", _dataMap);
//			//	throw new IllegalArgumentException("dataset can't contain key: adduser");
//			}
//			if (_dataMap.containsKey(PersistanceConstant.P_ADDTIME)) {
//				_dataMap.remove(PersistanceConstant.P_ADDTIME);
//			}
//			// 比较edm属性之前，过滤掉系统属性,属性拼接成 edmName + "." + attr
//			Set<String> _data_set = filterSystemKeys(_dataMap.keySet(), edmName);
//			set.addAll(_data_set);
//		}
//
//		// dataset里面的属性与edm返回的属性进行比对
//		return checkMapFromSet(set, edmName);
		return true;
	}

	/**
	 * 过滤掉系统属性，留下普通属性，并且将属性拼接成 edmName + "." + attr， 方便与edm中定义的属性校验；
	 * 
	 * @param keys
	 * @return
	 */
	private Set<String> filterSystemKeys(Collection<String> keys, String edmName) {
		Set<String> result = new HashSet<String>();

		Set<String> _set = filterSystemKeys(keys);
		for (Iterator<String> iterator = _set.iterator(); iterator.hasNext();) {
			String key = iterator.next();
			result.add(edmName + "." + key);
		}

		return result;
	}

	/**
	 * 过滤掉系统属性，留下普通属性；
	 * 
	 * @param keys
	 * @return
	 */
	private Set<String> filterSystemKeys(Collection<String> keys) {
		Set<String> result = new HashSet<String>();
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String _key = iterator.next();

			// 将dataset 中除id和pid的属性拼接成 edmName+"."+attr
			if (NodeConstant.ID.equals(_key) || NodeConstant.PID.equals(_key)|| DEL_COLUMNS.equals(_key)
					|| PersistanceConstant.P_MODTIME.equals(_key) || PersistanceConstant.P_MODUSER.equals(_key)
					|| PersistanceConstant.P_ADDTIME.equals(_key) || PersistanceConstant.P_ADDUSER.equals(_key)
					|| PersistanceConstant.CLASSNAME.equals(_key)) {
				continue;
			}

			result.add(_key);
		}

		return result;
	}

	/**
	 * 
	 * @param edmName：edm中定义的
	 * @param set: 待校验的属性集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean checkMapFromSet(Set<String> set, String edmName) {
		//mysql 数据库无字段会报错，还有必要比对表字段吗？
		if(edmName.contains(".")){
			edmName = edmName.substring(0,edmName.indexOf("."));
		}

		// 根据类名取所有属性集和普通属性
		Map<String, Object> map = ((Map<String, Object>) EDMClient.getAllEdmCode(edmVersion, edmName, "").getData());
		if (StringUtil.isNullOrEmpty(map)) {
			logger.info("{0} the result of interface EDM.getAllEdmCode is null ",edmName);
			throw new IllegalArgumentException("the result of interface EDM.getAllEdmCode is null ");
		}


		// 将set中的属性与map中的属性比对
		List<String> list = (List<String>) map.get(edmName);
		for (String str : set) {
			// 比对属性attr 是不是物理表中的属性
			if (!list.contains(str)) {
				logger.info("{0} is not physical attribute",str);
				throw new IllegalArgumentException(str + " is not physical attribute");
			}
		}
		return true;
	}

	/**
	 * 列的参数校验
	 *
	 * @param columnArray
	 * @return
	 */
	public boolean validateColumns(JSONArray columnArray, String edmName) {
		if(columnArray == null || columnArray.isEmpty()){
			return true;
		}
		
		List<String> _keys = columnArray.toJavaList(String.class);

		// 1. 比较edm属性之前，过滤掉系统属性, 属性拼接成 edmName + "." + attr
		Set<String> _data_set = filterSystemKeys(_keys, edmName);

		// 2. 将Columns 中的属性与edm获取的物理表属性比对
		return checkMapFromSet(_data_set, edmName);
	}

	/**
	 * conditions条件的参数校验
	 *
	 * @param conditionArray,edmName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean validateConditions(JSONArray conditionArray, String edmName) {
		// 如果conditions是空数组 不需要校验
		if (StringUtil.isNullOrEmpty(conditionArray)) {
			return true;
		}

		// 1. 拿取condition里面的的所有条件属性
		Set<String> keys = new HashSet<String>();
		boolean operatorFlag = false;
		for (int i = 0; i < conditionArray.size(); i++) {
			Map<String, String> _condition = (Map<String, String>) conditionArray.get(i);
			if (_condition.containsKey(NodeConstant.ATTR)) {
				keys.add(_condition.get(NodeConstant.ATTR));
				for(OperatorType operatorType:OperatorType.values()){
					if(operatorType.getValue().equals(_condition.get(NodeConstant.OPERATOR).toString())){
						operatorFlag = true;
					}
				}
			}else{
				//不存在 attr 抛出异常
				throw new IllegalArgumentException("conditions not contain attr!");
			}
		}

		//判断operator的参数是否合法
		if(operatorFlag == false){
			throw new IllegalArgumentException("operator parameter is not legal !");
		}

		// 2.比较edm属性之前，过滤掉系统属性, 属性拼接成 edmName + "." + attr
		Set<String> set = filterSystemKeys(keys, edmName);

		// congdition中的属性与edm类属性比较
		return checkMapFromSet(set, edmName);
	}

	/**
	 * conditions条件的参数是否是索引校验
	 *
	 * @param conditionArray,edmName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean validateConditionsIsIndex(JSONArray conditionArray, String edmName) {
		if(conditionArray == null || conditionArray.isEmpty()){
			return true;
		}
		
		// edm获取物理表中的索引属性
		Map<String, Object> map = (Map<String, Object>) EDMClient.getOrmIndexs(edmVersion, edmName).getData();

		// edm 返回的索引属性判空， EDM返回的类没有索引抛出异常
		if (StringUtil.isNullOrEmpty(map)) {
			logger.info("{0} this class no index",edmName);
			throw new IllegalArgumentException(edmName + "this class no index");
		}

		// 索引列表
		List<String> fieldsList = (List<String>) map.get(edmName);

		// 1. 拿取condition里面的的所有条件属性
		Set<String> keys = new HashSet<String>();
		for (int i = 0; i < conditionArray.size(); i++) {
			Map<String, String> _condition = (Map<String, String>) conditionArray.get(i);
			if (_condition.containsKey(NodeConstant.ATTR)) {
				keys.add(_condition.get(NodeConstant.ATTR));
			}
		}

		// 2.比较edm属性之前，过滤掉系统属性
		Set<String> _indexes = filterSystemKeys(keys);

		// 3. 校验conditions中的编码是否在索引范围之内,如果conditions 中的属性不是索引属性 抛出异常
		for (Iterator<String> iterator = _indexes.iterator(); iterator.hasNext();) {
			String _index = iterator.next();
			if (!fieldsList.contains(_index)) {
				String errMsg = _index + " is not an index attribute for search!";
				logger.info(errMsg);
				throw new IllegalArgumentException(errMsg);
			}
		}

		return true;
	}

	/**
	 * 分页的参数校验
	 *
	 * @param pagenationJson
	 * @return
	 */
	public boolean validatePagenation(JSONObject pagenationJson) {
		// 如果没有分页数据，跳过校验;
		if (StringUtil.isNullOrEmpty(pagenationJson)) {
			return true;
		}

		// pagenation不存在startPage
		if (!pagenationJson.containsKey(NodeConstant.START_PAGE)) {
			logger.info("{0} doesn't contain key: startPage",pagenationJson);
			throw new IllegalArgumentException("doesn't contain key: startPage");
		}

		// pagenation不存在rows
		if (!pagenationJson.containsKey(PersistanceConstant.P_ROWS)) {
			logger.info("{0} doesn't contain key: rows",pagenationJson);
			throw new IllegalArgumentException("doesn't contain key: rows");
		}

		// pagenation 判空
		String startPageStr = pagenationJson.getString(NodeConstant.START_PAGE);
		String rowsStr = pagenationJson.getString(PersistanceConstant.P_ROWS);

		// 判断页数和行数是否都为数字
		if (!isNumber(startPageStr) || !isNumber(rowsStr)) {
			logger.info("{0} is not Number",startPageStr);
			logger.info("{0} is not Number",rowsStr);
			return false;
		}

		return true;
	}

	/**
	 * 字符串是否是数字校验
	 *
	 * @param str
	 * @return
	 */
	private boolean isNumber(String str) {
//		if (!StringUtil.isNullOrEmpty(str)) {
//			// 全数字正则表达式
//			String reg = "^[1-9]\\d*$";
//			return Pattern.compile(reg).matcher(str).find();
//		}
//		return false;
		return true;
	}

	/**
	 * 排序的参数校验
	 *
	 * @param jsonorderby
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean validateOrderBy(JSONArray jsonorderby, String edmName) {
		return true;
//		// 没有排序条件 返回true
//		if (jsonorderby == null || jsonorderby.isEmpty()) {
//			return true;
//		}
//
//		Set<String> set = new HashSet<String>();
//
//		// 将orderby 的attr 封装成edmName+"."+attr 装进set
//		for (Object orderAttr : jsonorderby) {
//			Map<String, String> orderMap = (Map<String, String>) orderAttr;
//			// 判断orderby中attr 是否存在
//			if (!orderMap.containsKey(NodeConstant.ATTR)) {
//				logger.info("{0} doesn't contain key: attr",orderMap);
//				throw new IllegalArgumentException("doesn't contain key: attr");
//			}
//
//			set.add(orderMap.get(NodeConstant.ATTR));
//		}
//
//		Set<String> _keys = this.filterSystemKeys(set, edmName);
//
//		// 将orderby中的属性与物理表中的属性进行比对
////		return checkMapFromSet(_keys, edmName);
//
//		// 判断排序的字段是否是当前查询的表的属性
//		return checkOrderByAttr(_keys, edmName);
	}

	/**
	 *
	 * @param edmName：edm中定义的
	 * @param set: 待校验的属性集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean checkOrderByAttr(Set<String> set, String edmName) {
		String name = edmName;
		if(edmName.contains(".")){
			name = edmName.substring(0,edmName.indexOf("."));
		}
		// 根据类名取所有属性集和普通属性
		Map<String, Object> map = ((Map<String, Object>) EDMClient.getOrmBaseEdmCode(edmVersion, edmName).getData());
		if (StringUtil.isNullOrEmpty(map)) {
			logger.info("{0} the result of interface EDM.getAllEdmCode is null ",edmName);
			throw new IllegalArgumentException("the result of interface EDM.getAllEdmCode is null ");
		}

		// 将set中的属性与map中的属性比对
		List<String> list = (List<String>) map.get(name);
		List<String> tempList = new ArrayList<String>();
		for(String edmCode: list){
			tempList.add(edmName+"."+edmCode);
		}
		for (String str : set) {
			// 比对属性attr 是不是物理表中的属性
			if (!tempList.contains(str)) {
				logger.info("{0} is not physical attribute",str);
				throw new IllegalArgumentException(str + " is not physical attribute");
			}
		}

		return true;
	}

}
