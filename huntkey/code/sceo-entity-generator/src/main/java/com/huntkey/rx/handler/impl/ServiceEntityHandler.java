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

public class ServiceEntityHandler extends BaseHandler<EntityInfo> {

    public ServiceEntityHandler(String ftlName, EntityInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") + File.separator
                + Configuration.getString("entityService.path") + File.separator + info.getClassName()+ Constants.SERVICE_SUFFIX
                + Constants.FILE_SUFFIX_JAVA;

    }

    @Override
    public void combileParams(EntityInfo entityInfo) {
        String packageName = Configuration.getString("entityService.package");
        entityInfo.setEntityPackage(packageName);
        this.param.put("packageStr", entityInfo.getEntityPackage());
        StringBuilder sb = new StringBuilder();
        for (String str : entityInfo.getImports()) {
            sb.append("import ").append(str).append(";\r\n");
        }
        sb.append("import com.huntkey.rx.commons.utils.rest.Result;").append("\r\n");
        sb.append("import com.huntkey.rx.sceo.method.register.plugin.entity.ParamsVo;").append("\r\n");
        sb.append("import com.huntkey.rx.sceo.method.register.plugin.util.ExecUtil;").append("\r\n");
        this.param.put("importStr", sb.toString());
        this.param.put("entityDesc", entityInfo.getEntityDesc());
        this.param.put("className", entityInfo.getClassName());
        this.param.put("extendName", entityInfo.getExtendName());

        // 生成属性，getter,setter方法
        sb = new StringBuilder();
        StringBuilder sbMethods = new StringBuilder();
        StringBuilder classMethods = new StringBuilder();
        Map<String, String> propRemarks = entityInfo.getPropRemarks();
        for (Entry<String, PropertyInfo> entry : entityInfo.getPropTypes().entrySet()) {
            String propName = entry.getKey();
            PropertyInfo propType = entry.getValue();

            // 注释、类型、名称
            sb.append("    /**").append(propRemarks.get(propName)).append("*/\r\n").append("    private ")
                    .append(propType.getType()).append(" ").append(propName).append(";\r\n");

			sbMethods.append("    public ").append(propType.getType()).append(" get")
					.append(propName.substring(0, 1).toUpperCase()).append(propName.substring(1)).append("() {\r\n")
					.append("        return ").append(propName).append(";\r\n").append("    }\r\n")
					.append("    public void set").append(propName.substring(0, 1).toUpperCase())
					.append(propName.substring(1)).append("(").append(propType.getType()).append(" ").append(propName)
					.append(") {\r\n").append("        this.").append(propName).append(" = ").append(propName)
					.append(";\r\n    }\r\n").append("\r\n");

        }

        Map<String, Object> methodMap = entityInfo.getPropMethods();
        List<MethodInfo> methodInfoList = (List<MethodInfo>)methodMap.get(entityInfo.getTableName());
        if(methodInfoList != null && methodInfoList.size()>0) {
            for (MethodInfo methodInfo : methodInfoList) {
                classMethods.append("    public Result ").append(methodInfo.getName()).append("(ParamsVo params) {\r\n")
                        .append("        params.setClassName(\""+entityInfo.getClassName()+"\")").append(";\r\n")
                        .append("        params.setMethodName(\""+methodInfo.getName()+"\")").append(";\r\n")
                        .append("        return ").append("ExecUtil.exec(params)").append(";\r\n").append("    }\r\n")
                        .append("\r\n");
            }
        }

        this.param.put("propertiesStr", sb.toString());
		this.param.put("methodStr", sbMethods.toString());
        this.param.put("serialVersionNum", StringUtil.generate16LongNum());
        this.param.put("classMethod", classMethods.toString());
    }
}