package com.huntkey.rx.task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huntkey.rx.Constants;
import com.huntkey.rx.config.Configuration;
import com.huntkey.rx.framework.AbstractApplicationTask;
import com.huntkey.rx.framework.context.ApplicationContext;
import com.huntkey.rx.model.ClassInfo;
import com.huntkey.rx.model.MethodInfo;
import com.huntkey.rx.model.PropertyInfo;
import com.huntkey.rx.model.ServiceEntityInfo;
import com.huntkey.rx.util.DbUtil;
import com.huntkey.rx.util.FileHelper;
import com.huntkey.rx.util.PropertyUtil;
import com.huntkey.rx.util.StringUtil;

public class InitTask extends AbstractApplicationTask {
	private String EDMVersion = "V1.0";

	private Connection conn;
	private ResultSet classRS;
	private ResultSet propertyRS;

	List<Map<String, String>> tableList = new ArrayList<Map<String, String>>();
	Map<String, ClassInfo> classInfos = new HashMap<String, ClassInfo>();
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();

	// 初始化
	private void init(ApplicationContext context) {
		logger.info("初始化任务");

		// 首先清空baseDir下的所有文件
		String baseDir = Configuration.getString("base.baseDir");
		FileHelper.deleteDirectory(baseDir);

		// 加载属性文件
		// 字段类型与属性类型的映射
		if (Configuration.getString("base.database").equals(Constants.DB_ORACLE)) {
			PropertyUtil.loadProp("columnType2PropType_oracle.properties");
		} else {
			PropertyUtil.loadProp("columnType2PropType.properties");
		}

		// 属性类型与包类名的映射
		PropertyUtil.loadProp("propType2Package.properties");

		// 属性类型与jdbc类型的映射，注意这里为了防止与上面冲突，属性类型前加了_
		PropertyUtil.loadProp("propType2JdbcType.properties");

		// 加载基本的7个字段到list
		String baseColumnsStr = Configuration.getString("base.baseColumns");
		String[] baseColumnsArr = baseColumnsStr.split(Constants.CHARACTER_COMMA);
		List<String> baseColumnList = new ArrayList<String>();
		for (String str : baseColumnsArr) {
			baseColumnList.add(str.toUpperCase());
		}
		context.setAttribute("baseColumns", baseColumnList);

		this.conn = DbUtil.getConn();
	}

	// 获取EDM定义的class信息
	private List<Map<String, String>> getEDMClassList() throws Exception {
		String sql = "SELECT (select edmc_name_en from edm_class where id=a.edmc_parent_id ) as parent_name, a.is_entity, a.id, a.edmc_name, a.edmc_name_en, a.tablename FROM edm_class a, edm_modeler b WHERE a.edmc_edmd_id = b.id AND a.is_del = 0 AND b.edmd_ver = ?";

		// 取edm中所有的类，edm_class表
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, EDMVersion);
		this.classRS = ps.executeQuery();

		List<Map<String, String>> classList = new ArrayList<Map<String, String>>();
		while (classRS.next()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("className", classRS.getString("EDMC_NAME_EN"));
			map.put("classRemark", classRS.getString("EDMC_NAME"));
			map.put("edmClassId", classRS.getString("ID"));
			map.put("classType", classRS.getInt("IS_ENTITY") + "");
			map.put("parentClassName", classRS.getString("PARENT_NAME"));
			map.put("TABLENAME", classRS.getString("TABLENAME"));
			classList.add(map);
		}

		return classList;
	}

	/**
	 * 组装类的属性
	 *
	 * @param classList
	 * @return
	 * @throws Exception
	 */
	private Map<String, ClassInfo> combineClassInfo(List<Map<String, String>> classList) throws Exception {
		// 遍历
		for (Map<String, String> map : classList) {

			ClassInfo classInfo = new ClassInfo();
			classInfo.setName(StringUtil.convertClassName(map.get("className")));
			logger.info("类名：{}", classInfo.getName());

			// 类描述
			classInfo.setRemark(map.get("classRemark"));
			logger.info("类{} 的描述:{}", classInfo.getName(), classInfo.getRemark());

			// 父类名称
			classInfo.setParentClassName(map.get("parentClassName"));
			logger.info("类{} 的父类名称:{}",  classInfo.getName(), classInfo.getParentClassName());

			// 表类型
			classInfo.setType(map.get("classType"));
			logger.info("类{} 的类型:{}", classInfo.getName(), classInfo.getType());

			//主表
			classInfo.setChild(false);

			//取类对应的表名
			Map<String, String> table = new HashMap<String, String>();
			table.put(classInfo.getName(), map.get("TABLENAME"));
			tableList.add(table);

			String edmClassId = map.get("edmClassId");

			String sql = "SELECT id, edmp_code, edmp_name, edmp_value_type, edmp_data_type, edmp_parent_id, edmp_value_type, edmp_value_size, tablename, edmp_formula, edmp_value_limit FROM edm_property WHERE edmp_edmc_id = '"
					+ edmClassId + "' AND is_del = 0";
			propertyRS = conn.prepareStatement(sql).executeQuery();

			List<Map<String, String>> propertyList = new ArrayList<Map<String, String>>();
			while (propertyRS.next()) {
				Map<String, String> m = new HashMap<String, String>();
				m.put("propertyName", propertyRS.getString("EDMP_CODE"));
				m.put("propertyType", valueConvert(propertyRS.getString("EDMP_DATA_TYPE")));
				m.put("propertyRemark", valueConvert(propertyRS.getString("EDMP_NAME")));

				m.put("propertyValueType", valueConvert(propertyRS.getString("EDMP_VALUE_TYPE")));
				m.put("propertyParentId", valueConvert(propertyRS.getString("EDMP_PARENT_ID")));
				m.put("EDMP_VALUE_SIZE", valueConvert(propertyRS.getString("edmp_value_size")));
				m.put("ID", propertyRS.getString("ID"));
				m.put("TABLENAME", propertyRS.getString("TABLENAME"));
				m.put("edmp_formula", propertyRS.getString("EDMP_FORMULA"));
				m.put("edmp_value_limit", propertyRS.getString("EDMP_VALUE_LIMIT"));
				propertyList.add(m);

			}

			//查询edmclass方法
			sql = "SELECT id, edmm_name, edmm_arithmetic_desc FROM edm_method WHERE edmm_edmc_id = '"+edmClassId+"' AND is_del = 0";
			propertyRS = conn.prepareStatement(sql).executeQuery();
			List<MethodInfo> methodInfoList = new ArrayList<MethodInfo>();
			while (propertyRS.next()) {
				MethodInfo methodInfo = new MethodInfo();
				methodInfo.setId(propertyRS.getString("ID"));
				methodInfo.setName(propertyRS.getString("EDMM_NAME"));
				methodInfo.setEdmmArithmeticDesc(propertyRS.getString("EDMM_ARITHMETIC_DESC"));
				methodInfoList.add(methodInfo);
			}

			// 解析属性类型
			logger.info("*****************************");
			List<PropertyInfo> propertyInfoList = transferPropertyList(propertyList);
			classInfo.setPropertyList(propertyInfoList);

			classInfo.setMehtodInfoList(methodInfoList);

			classInfos.put(classInfo.getName(), classInfo);

		}

		return classInfos;
	}

	/**
	 * 解析类的属性
	 * @param propertyList
	 * @return
	 */
	private List<PropertyInfo> transferPropertyList(List<Map<String, String>> propertyList) throws Exception {
		List<PropertyInfo> propertyInfoList = new ArrayList<PropertyInfo>();
		for (Map<String, String> property : propertyList) {
			String propertyName = property.get("propertyName");
			String propertyValueType = property.get("propertyValueType");
			String propertyType = property.get("propertyType");
			String edmp_formula = property.get("edmp_formula");
			String edmp_value_limit = property.get("edmp_value_limit");
			String propertyParentId = property.get("propertyParentId");
			String classType = "";
			// 如果是属性集, 需要建立VO model
			if (Constants.ASSEMBLE.equals(propertyValueType)) {
				handlePropertySet(property, propertyList);
				propertyType = Constants.LISTOBJECT;
				classType = StringUtil.convertClassName(property.get("TABLENAME"))+ Constants.ENTITY_SUFFIX;
			}
			if(Constants.MEASUREMENT.equals(propertyValueType) 	|| Constants.CONVOLUTION.equals(propertyValueType)){
				propertyType = Constants.Object;
			}else if(Constants.CLASS.equals(propertyValueType) || Constants.CONST.equals(propertyValueType) || Constants.TEXT.equals(propertyValueType)){
				propertyType = Constants.VARCHAR;
			}else if(Constants.Object.equals(propertyValueType) || Constants.OBJECTLINK.equals(propertyValueType) || Constants.ENUM.equals(propertyValueType)){
				//对象，对象链接，生成String类型的属性，加上getXXObject方法
				//获取classType
				if(Constants.ENUM.equals(propertyValueType)){
					classType = "WordlistEntity";
				}else{
					for (Map<String, String> classMap : list) {
						String edmClassId = classMap.get("edmClassId");
						if(edmClassId.equals(propertyType)){
							classType = classMap.get("className");
							classType = StringUtil.upperFirst(classType)+Constants.ENTITY_SUFFIX;
							break;
						}
					}
				}

				propertyType = Constants.VARCHAR;
			}
			String propertyRemark = property.get("propertyRemark");
			logger.info("字段名称：{}, 字段类型：{}, 字段注释：{}", propertyName, propertyType, propertyRemark);
			PropertyInfo propertyInfo = new PropertyInfo();
			if(null == propertyParentId || "".equals(propertyParentId)){
				propertyInfo.setName(propertyName);
				propertyInfo.setType(propertyType);
				propertyInfo.setRemark(propertyRemark);
				propertyInfo.setFomula(edmp_formula);
				propertyInfo.setLimitFomula(edmp_value_limit);
				propertyInfo.setClassName(classType);
				propertyInfo.setFiledName(propertyName);
				propertyInfoList.add(propertyInfo);

			}


		}

		return propertyInfoList;
	}


	/**
	 * 属性集处理
	 */
	private void handlePropertySet(Map<String, String> m, List<Map<String, String>> propertyList){
		String propertyId = m.get("ID");
		// 表名
//		String className = m.get("propertyName");
		String className = m.get("TABLENAME");
		logger.info("数据库表名：{}", className);
		logger.info("*****************************");
		logger.info("className:{}", className);

		ClassInfo classInfo = new ClassInfo();
		classInfo.setName(StringUtil.convertClassName(className));

		// 表注释
		String classRemark = m.get("columnRemark")==null?"":m.get("columnRemark").toString();
		classInfo.setRemark(classInfo.getRemark());
		logger.info("类{}的注释:{}", className, classRemark);

		// 表类型
		String classType = "TABLE";
		classInfo.setType(classType);
		logger.info("类{}的类型:{}", className, classInfo.getType());

		//类对应的表名
		if(null != m.get("TABLENAME")){
			Map<String, String> table = new HashMap<String, String>();
			table.put(m.get("TABLENAME"), m.get("TABLENAME"));
			tableList.add(table);
		}

		classInfo.setChild(true);

		// 循环遍历，查询属性集基本属性，组装column信息
		List<PropertyInfo> attrList = new ArrayList<PropertyInfo>();
		for (Map<String, String> property : propertyList) {
			if (propertyId.equals(property.get("propertyParentId"))) {

				PropertyInfo c = new PropertyInfo();
				String propertyName = property.get("propertyName");
				String propertyType = property.get("propertyType");
				String edmp_formula = property.get("edmp_formula");
				String edmp_value_limit = property.get("edmp_value_limit");
				String clazzName = "";
				if(!Constants.ASSEMBLE.equals(property.get("propertyValueType"))) {
					if (Constants.MEASUREMENT.equals(property.get("propertyValueType")) || Constants.CONVOLUTION.equals(property.get("propertyValueType"))) {
						propertyType = Constants.Object;
					} else if (Constants.CLASS.equals(property.get("propertyValueType")) || Constants.CONST.equals(property.get("propertyValueType"))) {
						propertyType = Constants.VARCHAR;
					} else if (Constants.Object.equals(property.get("propertyValueType")) || Constants.OBJECTLINK.equals(property.get("propertyValueType"))
							|| Constants.ENUM.equals(property.get("propertyValueType"))) {
						//对象，对象链接，生成String类型的属性，加上getXXObject方法
						//获取classType
						if (Constants.ENUM.equals(property.get("propertyValueType"))) {
							clazzName = "WordlistEntity";
						} else {
							for (Map<String, String> classMap : list) {
								String edmClassId = classMap.get("edmClassId");
								if (edmClassId.equals(propertyType)) {
									clazzName = classMap.get("className");
									clazzName = StringUtil.upperFirst(clazzName) + Constants.ENTITY_SUFFIX;
									break;
								}
							}
						}
						propertyType = Constants.VARCHAR;
					}

				}else {
					// todo
					handlePropertySet(property, propertyList);
					propertyType = Constants.LISTOBJECT;
					clazzName = StringUtil.convertClassName(property.get("TABLENAME"))+ Constants.ENTITY_SUFFIX;

				}
				String propertyRemark = property.get("propertyRemark");
				c.setName(propertyName);
				c.setType(propertyType);
				c.setRemark(propertyRemark);
				c.setFomula(edmp_formula);
				c.setLimitFomula(edmp_value_limit);
				c.setClassName(clazzName);
				c.setFiledName(propertyName);
				attrList.add(c);
			}


		}

		logger.info("*****************************");
		classInfo.setPropertyList(attrList);

		classInfos.put(className, classInfo);
	}


	// 把属性值转换为小写字符；
	private String valueConvert(String str) {
		if (str == null) {
			return "";
		} else {
			return str.toLowerCase();
		}
	}

	@Override
	protected boolean doInternal(ApplicationContext context) throws Exception {
		try {
			init(context);

			List<Map<String, String>> classList = getEDMClassList();
			list = classList;
			Map<String, ClassInfo> classInfos = combineClassInfo(classList);

			// 放入上下文
			context.setAttribute("classInfos", classInfos);
			context.setAttribute("tableInfos", tableList);

			if (classInfos.size() == 0) {
				logger.info("在数据库没有匹配到相应的表");
				return true;
			}
		} catch (Exception e) {
			logger.info("初始化任务异常", e);
			e.printStackTrace();
		} finally {
			DbUtil.closeReso(conn, null, classRS);
			DbUtil.closeReso(null, null, propertyRS);
		}

		return false;
	}

}