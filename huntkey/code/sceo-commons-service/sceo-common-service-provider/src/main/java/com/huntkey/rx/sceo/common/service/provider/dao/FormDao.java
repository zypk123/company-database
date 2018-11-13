package com.huntkey.rx.sceo.common.service.provider.dao;

import java.util.List;
import java.util.Map;

import com.huntkey.rx.sceo.common.service.common.model.Form;
import com.huntkey.rx.sceo.common.service.common.model.Form.FormType;
import com.huntkey.rx.sceo.common.service.common.model.TableColumn;

public interface FormDao {

	/**
	 * 获取某个db下的表名
	 * 
	 * @param db
	 *            数据库名称
	 * @return 表名集合
	 */
	List<String> getFormTables(String db);
	/**
	 * 根据数据库名，表名 获取表的字段列表信息
	 * @param tableColumn
	 * @return
	 */
	List<TableColumn> getFormTableColumns(TableColumn tableColumn);

	/**
	 * 获取表单列表或者表单模板列表
	 * 
	 * @param type
	 *            类型：form，temp
	 * @return
	 */
	List<Form> listByType(FormType type);

	Form get(String id);

	int insert(Form form);

	int update(Form form);
	
	void insertFormData(Map<String, Object> param);
	void updateFormData(Map<String, Object> param);
}
