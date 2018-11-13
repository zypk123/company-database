package com.huntkey.rx.sceo.common.service.provider.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huntkey.rx.commons.abstracts.BaseService;
import com.huntkey.rx.commons.framework.AppContext;
import com.huntkey.rx.commons.utils.json.JsonUtils;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.sceo.common.service.common.model.Form;
import com.huntkey.rx.sceo.common.service.common.model.TableColumn;
import com.huntkey.rx.sceo.common.service.provider.config.MySqlDataSource;
import com.huntkey.rx.sceo.common.service.provider.dao.FormDao;

@Service
@Transactional(readOnly=true)
public class FormService extends BaseService {
	
	@Autowired
	private FormDao formDao;
	
	/**
	 * 获取某个db下的表名
	 * @param db 数据库名称
	 * @return 表名集合
	 */
	public List<String> getFormTables(String db){
		return formDao.getFormTables(db);
	}   
	/**
	 * 根据数据库名，表名 获取表的字段列表信息
	 * @param tableColumn
	 * @return
	 */
	public List<TableColumn> getFormTableColumns(TableColumn tableColumn){
		return formDao.getFormTableColumns(tableColumn);
	}
	/**
	 * 根据数据库名，表名 获取表的字段列表信息
	 * @param tableColumn
	 * @return
	 */
	public List<TableColumn> getFormTableColumn(String db, String table) {
		TableColumn tableColumn = new TableColumn();
		tableColumn.setTableSchema(db);
		tableColumn.setTableName(table);
		return formDao.getFormTableColumns(tableColumn);
	}
	/**
	 * 获取表单列表或者表单模板列表
	 * 
	 * @param type
	 *            类型：form，temp
	 * @return
	 */
	public List<Form> listByType(Form.FormType type){
		return formDao.listByType(type);
			
	}
	public Form get(String id){
		return formDao.get(id);
	}
	/**
	 * form保存，有ID的更新，没有ID的添加
	 * @param f
	 * @return 
	 */
	@Transactional(readOnly=false)
	public int save(Form f) {
		int res = -1;
		if(StringUtil.isNullOrEmpty(f.getId())){
			f.setId(UuidCreater.uuid());
			res = formDao.insert(f);
		}else{
			res = formDao.update(f);
			if(res == 0){
				res = formDao.insert(f);
			}
		}
		return res;
	}
	@Transactional(readOnly=false)
	public Result formDataSubmit(String formId, String data) {
		//client端接收到的formIdeb299c1060554659bd3f2ee70eb8b6ec
		//client端接收到的json{"test_qingjia.user_name":"董环","test_qingjia.day_count":"3","test_qingjia.start_time":"2017-05-19","test_qingjia.end_time":["3"],"test_qingjia.state":"1"}
		Map<String,List<TableColumn>> map = alanyseFormData(data);
		Result res  = saveTableData(formId,map);
		return res;
	}
	/**
	 * 初始化表数据关系，生成ID值
	 * @param formId
	 * @param map
	 * @return
	 */
	private Result saveTableData(String formId, Map<String, List<TableColumn>> map) {
		Result res = new Result();
		
		if(map == null || map.size() == 0){
			res.setErrMsg("没有可保存的数据");
			res.setRetCode(Result.RECODE_ERROR);
		}
		String dataId = null;
		String mainTable = null;
		for(Map.Entry<String, List<TableColumn>> item : map.entrySet()){
			
			String tableName = item.getKey();
			mainTable = tableName;
			String opType = "add";
			boolean hasId = false;
			//检查ID，判断是添加还是修改
			for(TableColumn tc : item.getValue()){
				if("id".equals(tc.getColumnName())){
					hasId = true;
					if(StringUtil.isNullOrEmpty(tc.getColumnValue())){
						dataId = UuidCreater.uuid();
					}else{
						dataId = tc.getColumnValue().toString();
						opType = "update";
					}
					break;
				}
			}
			
			if(!hasId){
				dataId = UuidCreater.uuid();
				TableColumn idColumn = new TableColumn();
				idColumn.setColumnName("id");
				idColumn.setColumnType("VARCHAR");
				idColumn.setColumnValue(dataId);
				item.getValue().add(idColumn);
			}
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("tableName", tableName);
			param.put("id", dataId);
			param.put("columnList", item.getValue());
			if("add".equals(opType)){
				formDao.insertFormData(param);
			}else{
				formDao.updateFormData(param);
			}
			
		}
		
		Map<String,Object> resDataMap = new HashMap<String, Object>();
		resDataMap.put("mainTable", mainTable);
		resDataMap.put("dataId", dataId);
		resDataMap.put("dataMap", map);
		res.setRetCode(Result.RECODE_SUCCESS);
		res.setData(resDataMap);
		return res;
	}
	/**
	 * 按表名解析数据
	 * @param formData
	 * @return
	 */
	private Map<String,List<TableColumn>> alanyseFormData(String formData){
		
		MySqlDataSource ds = (MySqlDataSource) AppContext.getBean(MySqlDataSource.class);
		String dbName = ds.getDbName();
		
		Map<String,Object> dataMap = JsonUtils.parseJSONMap(formData);
		Map<String,List<TableColumn>> dbMap = new HashMap<String, List<TableColumn>>();
		Map<String,List<TableColumn>> resMap = new HashMap<String, List<TableColumn>>();
		
		if(dataMap != null && dataMap.size() > 0){
			for(Map.Entry<String, Object> item : dataMap.entrySet()){
				Object value = item.getValue();
				if(StringUtil.isNullOrEmpty(value)){
					continue;
				}
				String[] arr = StringUtil.spiltStringToArray(item.getKey(), ".");
				String tableName = arr[0];
				if(!dbMap.containsKey(tableName)){
					TableColumn tc = new TableColumn();
					tc.setTableSchema(dbName);
					tc.setTableName(tableName);
					List<TableColumn> tcList = getFormTableColumns(tc);
					dbMap.put(tableName, tcList);
				}
				
				TableColumn dataColumn = getDataColumn(dbMap.get(tableName),arr[1],value);
				if(dataColumn == null){
					continue;
				}
				
				if(!resMap.containsKey(tableName)){
					resMap.put(tableName, new ArrayList<TableColumn>());
				}
				resMap.get(tableName).add(dataColumn);
				
			}
		}
		
		return resMap;
	}
	/**
	 * 获取字段实体
	 * @param list
	 * @param fieldName
	 * @param value
	 * @return
	 */
	private TableColumn getDataColumn(List<TableColumn> list, String fieldName, Object value) {
		if(list == null || StringUtil.isNullOrEmpty(fieldName)){
			return null;
		}
		TableColumn tc = null;
		for(TableColumn item : list){
			if(fieldName.equals(item.getColumnName())){
				item.setColumnValue(value);
				tc = item;
				break;
			}
		}
		return tc;
	}
}
