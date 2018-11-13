package com.huntkey.rx.handler.impl;

import com.huntkey.rx.Constants;
import com.huntkey.rx.config.Configuration;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.util.StringUtil;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ConstantEntityHandler extends BaseHandler<List<Map<String, String>>> {

	public ConstantEntityHandler(String ftlName, List<Map<String, String>> info) {
		this.ftlName = ftlName;
		this.info = info;
		this.savePath = Configuration.getString("base.baseDir") + File.separator
				+ Configuration.getString("entity.path") + File.separator + "Constants"
				+ Constants.FILE_SUFFIX_JAVA;

	}

	@Override
	public void combileParams(List<Map<String, String>> tableList) {
		this.param.put("packageStr", Configuration.getString("entity.package"));
		this.param.put("className", "Constants");

		// 生成属性
		StringBuilder sb = new StringBuilder();
		Set<Map<String, String>> set = new HashSet<Map<String, String>>();
		for(Map<String, String> tableInfo: tableList){
			set.add(tableInfo);
		}

		for(Map<String, String> tableInfo: set){

			for (Entry<String, String> entry : tableInfo.entrySet()) {
				String className = entry.getKey().trim();
				className = StringUtil.convertClassName(className)+Constants.ENTITY_SUFFIX;
				String tableName = entry.getValue();

				// 注释、类型、名称
				sb.append("    /**").append(className).append("*/\r\n").append("    public static String ")
						.append(className).append(" = \"").append(tableName).append("\";\r\n");


			}
		}

		this.param.put("propertiesStr", sb.toString());
	}
}