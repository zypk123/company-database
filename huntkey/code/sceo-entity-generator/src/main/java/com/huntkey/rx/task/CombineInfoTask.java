package com.huntkey.rx.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.huntkey.rx.Constants;
import com.huntkey.rx.config.Configuration;
import com.huntkey.rx.framework.AbstractApplicationTask;
import com.huntkey.rx.framework.context.ApplicationContext;
import com.huntkey.rx.model.MethodInfo;
import com.huntkey.rx.model.PropertyInfo;
import com.huntkey.rx.model.EntityInfo;
import com.huntkey.rx.model.ClassInfo;
import com.huntkey.rx.util.PropertyUtil;
import com.huntkey.rx.util.StringUtil;

public class CombineInfoTask extends AbstractApplicationTask {

	@SuppressWarnings("unchecked")
	@Override
	protected boolean doInternal(ApplicationContext context) throws Exception {
		logger.info("组装信息");

		// 获取实体相关的配置
		String packageName = Configuration.getString("entity.package");
		// 存放路径
		String path = Configuration.getString("entity.path");

		logger.info("所有实体的包名为{}， 路径为：{}", packageName, path);

		// 获取表和实体的映射集合
		Map<String, ClassInfo> classInfos = (Map<String, ClassInfo>) context.getAttribute("classInfos");

		List<EntityInfo> entityInfos = new ArrayList<EntityInfo>();
		for (Entry<String, ClassInfo> entry : classInfos.entrySet()) {
			EntityInfo entityInfo = new EntityInfo();

			// 表名
			String tableName = entry.getKey();
			// 实体名

			// 表信息
			ClassInfo classInfo = entry.getValue();

			Set<String> imports = new HashSet<String>();
			Map<String, PropertyInfo> propTypes = new LinkedHashMap<String, PropertyInfo>();
			Map<String, String> propRemarks = new LinkedHashMap<String, String>();
			Map<String, String> propJdbcTypes = new LinkedHashMap<String, String>();
			Map<String, String> propName2ColumnNames = new LinkedHashMap<String, String>();
			Map<String, Object> propMethods = new LinkedHashMap<String, Object>();

			entityInfo.setTableName(tableName);
			entityInfo.setEntityName(classInfo.getName().trim());
			entityInfo.setEntityDesc(classInfo.getRemark());
			// java文件的类名，以后再做优化修改，目前直接取
			entityInfo.setClassName(classInfo.getName().trim());
			entityInfo.setEntityPackage(packageName);

			// 添加父类名称
			String parentClassName = classInfo.getParentClassName();
			if (parentClassName == null || parentClassName.equals("")) {
				if(classInfo.isChild() == false){
					parentClassName = "BaseEntity";
					imports.add("com.huntkey.rx.base.BaseEntity");
				}else{
					parentClassName = "PropertyBaseEntity";
					imports.add("com.huntkey.rx.base.PropertyBaseEntity");
				}

			} else {
				parentClassName += "Entity";
			}
			entityInfo.setExtendName(StringUtil.upperFirst(parentClassName));

			// 遍历表字段信息
			List<PropertyInfo> columns = classInfo.getPropertyList();
			if (columns != null && columns.size() > 0) {
				for (PropertyInfo columnInfo : columns) {
					String fieldName = columnInfo.getName();
					String fieldType = columnInfo.getType();

					// 通过字段名生成属性名
					logger.info("-------fieldName={}", fieldName);
//					String propName = StringUtil.convertFieldName2PropName(fieldName);
					String propName = fieldName;


					// 这里为了兼容oracle，number类型，如果小数精度为0，则映射成Long类型
					String propType = null;
					if (Constants.DBTYPE_NUMBER.equals(fieldType) && columnInfo.getPrecision() == 0) {
						propType = Constants.PROPTYPE_LONG;
					} else {
						propType = PropertyUtil.getValueByKey(fieldType);
					}

					columnInfo.setType(propType);

					propTypes.put(propName, columnInfo);
					propRemarks.put(propName, columnInfo.getRemark());
					propJdbcTypes.put(propName, PropertyUtil.getValueByKey(Constants.CHARACTER_BOTTOM_LINE + propType));
					propName2ColumnNames.put(propName, columnInfo.getName().toUpperCase());
				}
			}
			logger.info("属性类型：{}", propTypes);
			logger.info("属性jdbcTypes：{}", propJdbcTypes);

			List<MethodInfo> methodInfoList = classInfo.getMehtodInfoList();
			if(methodInfoList != null && methodInfoList.size() > 0){
				propMethods.put(classInfo.getName().trim(), methodInfoList);
			}
			// 获取此实体所有的类型
			Collection<PropertyInfo> types = propTypes.values();

			for (PropertyInfo type : types) {
				if (!StringUtil.isEmpty(PropertyUtil.getValueByKey(type.getType()))) {
					imports.add(PropertyUtil.getValueByKey(type.getType()));
				}
			}
			logger.info("imports:{}", imports);
			entityInfo.setPropTypes(propTypes);
			entityInfo.setPropRemarks(propRemarks);
			entityInfo.setPropJdbcTypes(propJdbcTypes);
			entityInfo.setPropNameColumnNames(propName2ColumnNames);
			entityInfo.setImports(imports);
			entityInfo.setPackageClassName(
					entityInfo.getEntityPackage() + Constants.CHARACTER_POINT + entityInfo.getClassName());
			entityInfo.setPropMethods(propMethods);
			entityInfos.add(entityInfo);
		}

		context.setAttribute("entityInfos", entityInfos);
		return false;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		List<Long> list = new ArrayList<Long>();
		list.add(1L);
		map1.put("list", list);

		List<Long> list2 = (List<Long>) map1.get("list");
		list2.add(2L);

		System.out.println("list:" + list);
		System.out.println("list:" + map1.get("list"));
	}

}
