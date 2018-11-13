package com.huntkey.rx.handler.impl;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.huntkey.rx.Constants;
import com.huntkey.rx.config.Configuration;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.model.EntityInfo;
import com.huntkey.rx.model.MethodInfo;
import com.huntkey.rx.model.PropertyInfo;
import com.huntkey.rx.util.StringUtil;

public class EntityHandler extends BaseHandler<EntityInfo> {

	public EntityHandler(String ftlName, EntityInfo info) {
		this.ftlName = ftlName;
		this.info = info;
		this.savePath = Configuration.getString("base.baseDir") + File.separator
				+ Configuration.getString("entity.path") + File.separator + info.getClassName() + Constants.ENTITY_SUFFIX
				+ Constants.FILE_SUFFIX_JAVA;

	}

	@Override
	public void combileParams(EntityInfo entityInfo) {
		this.param.put("packageStr", entityInfo.getEntityPackage());
		StringBuilder sb = new StringBuilder();
		for (String str : entityInfo.getImports()) {
			sb.append("import ").append(str).append(";\r\n");
		}
		sb.append("import com.huntkey.rx.base.PropertyAnnotation;").append("\r\n");
		sb.append("import com.huntkey.rx.util.EntityUtils;").append("\r\n");
		this.param.put("importStr", sb.toString());
		this.param.put("entityDesc", entityInfo.getEntityDesc());
		this.param.put("className", entityInfo.getClassName()+ Constants.ENTITY_SUFFIX);
		this.param.put("extendName", entityInfo.getExtendName());

		// 生成属性，getter,setter方法
		sb = new StringBuilder();
		StringBuilder sbMethods = new StringBuilder();
		StringBuilder classMethods = new StringBuilder();
		Map<String, String> propRemarks = entityInfo.getPropRemarks();
		for (Entry<String, PropertyInfo> entry : entityInfo.getPropTypes().entrySet()) {
			String propName = entry.getKey();
			PropertyInfo propType = entry.getValue();


			String type;
			if(Constants.TYPEVALUE.equals(propType.getType())){

				// 注释、类型、名称
				sb.append("    /**").append(propRemarks.get(propName)).append("*/\r\n")
						.append("    @PropertyAnnotation(fomula=\""+propType.getFomula()+"\", limitFomula=\""+propType.getLimitFomula()+"\", fieldName=\""+propType.getFiledName()+"\", className=\""+propType.getClassName()+"\")").append("\r\n")
						.append("    private ").append("List<"+propType.getClassName()+">").append(" ").append(propName).append(";\r\n");

				type = Constants.LOAD;
				sbMethods.append("    public ").append("List<"+propType.getClassName()+">").append(type)
						.append(propName.substring(0, 1).toUpperCase()).append(propName.substring(1)).append("() {\r\n")
						.append("        String propertyName = \"").append(propName).append("\";\r\n")
						.append("        return ").append("(List<"+propType.getClassName()+">)").append("EntityUtils.getPropertySetObjList(this, propertyName)").append(";\r\n").append("    }\r\n")
						.append("\r\n")
						.append("    public void set").append(propName.substring(0, 1).toUpperCase())
						.append(propName.substring(1)).append("(").append("List<"+propType.getClassName()+">").append(" ").append(propName)
						.append(") {\r\n").append("        this.").append(propName).append(" = ").append(propName)
						.append(";\r\n    }\r\n")
						.append("\r\n")
						.append("    public ").append("List<"+propType.getClassName()+">").append(Constants.GET)
						.append(propName.substring(0, 1).toUpperCase()).append(propName.substring(1)).append("() {\r\n")
						.append("        return ").append(propName).append(";\r\n").append("    }\r\n")
						.append("\r\n");
			}else{

				// 注释、类型、名称
				sb.append("    /**").append(propRemarks.get(propName)).append("*/\r\n")
						.append("    @PropertyAnnotation(fomula=\""+propType.getFomula()+"\", limitFomula=\""+propType.getLimitFomula()+"\", fieldName=\""+propType.getFiledName()+"\", className=\""+propType.getClassName()+"\")").append("\r\n")
						.append("    private ").append(propType.getType()).append(" ").append(propName).append(";\r\n");

				type = Constants.GET;
				sbMethods.append("    public ").append(propType.getType()).append(type)
						.append(propName.substring(0, 1).toUpperCase()).append(propName.substring(1)).append("() {\r\n")
						.append("        return ").append(propName).append(";\r\n").append("    }\r\n")
						.append("\r\n")
						.append("    public void set").append(propName.substring(0, 1).toUpperCase())
						.append(propName.substring(1)).append("(").append(propType.getType()).append(" ").append(propName)
						.append(") {\r\n").append("        this.").append(propName).append(" = ").append(propName)
						.append(";\r\n    }\r\n").append("\r\n");
			}



			if(null != propType.getClassName() && !"".equals(propType.getClassName()) && !Constants.TYPEVALUE.equals(propType.getType())){
				sbMethods.append("    public ").append(propType.getClassName()).append(" load")
						.append(propName.substring(0, 1).toUpperCase()).append(propName.substring(1)).append("() {\r\n")
						.append("        String propertyName = \"").append(propName).append("\";\r\n")
						.append("        return ").append("("+propType.getClassName()+")").append("EntityUtils.getPropertyObj(this, propertyName)").append(";\r\n")
						.append("    }\r\n").append("\r\n");

			}

		}

		Map<String, Object> methodMap = entityInfo.getPropMethods();
		List<MethodInfo> methodInfoList = (List<MethodInfo>)methodMap.get(entityInfo.getTableName());
		if(methodInfoList != null && methodInfoList.size()>0) {
			for (MethodInfo methodInfo : methodInfoList) {
				classMethods.append("    public ResultSet ").append(methodInfo.getName()).append("(String url, Map map) {\r\n")
						.append("        return ").append("execUtil.exec(url, map)").append(";\r\n").append("    }\r\n")
						.append("\r\n");
			}
		}

		this.param.put("propertiesStr", sb.toString());
		this.param.put("methodStr", sbMethods.toString());
		this.param.put("serialVersionNum", StringUtil.generate16LongNum());
//		this.param.put("classMethod", classMethods.toString());
	}
}