package com.huntkey.rx.handler.impl;

import com.huntkey.rx.Constants;
import com.huntkey.rx.config.Configuration;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.model.EntityInfo;
import com.huntkey.rx.model.MethodInfo;
import com.huntkey.rx.model.PropertyInfo;
import com.huntkey.rx.util.StringUtil;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class EntityPropertyHandler extends BaseHandler<EntityInfo> {

    public EntityPropertyHandler(String ftlName, EntityInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") + File.separator
                + Configuration.getString("entityProperty.path") + File.separator + info.getClassName()+ Constants.PROPERTY
                + Constants.FILE_SUFFIX_JAVA;

    }

    @Override
    public void combileParams(EntityInfo entityInfo) {
        String packageName = Configuration.getString("entityProperty.package");
        this.param.put("packageStr", packageName);

        StringBuilder sb = new StringBuilder();
        for (String str : entityInfo.getImports()) {
            sb.append("import ").append(str).append(";\r\n");
        }
        this.param.put("importStr", sb.toString());
        this.param.put("entityDesc", entityInfo.getEntityDesc());
        this.param.put("className", entityInfo.getClassName()+ Constants.PROPERTY);
//        this.param.put("className", entityInfo.getClassName()+ Constants.ENTITY_SUFFIX);
//        this.param.put("extendName", entityInfo.getExtendName());

        sb = new StringBuilder();
//        sb.append("    public static final String ID = \"id\"").append(";\r\n")
//                .append("    public static final String CRETIME = \"cretime\"").append(";\r\n")
//                .append("    public static final String CREUSER = \"creuser\"").append(";\r\n")
//                .append("    public static final String MODTIME = \"modtime\"").append(";\r\n")
//                .append("    public static final String MODUSER = \"moduser\"").append(";\r\n");
        Map<String, String> propRemarks = entityInfo.getPropRemarks();
        Set<String> set = new HashSet<String>();

        for (Entry<String, PropertyInfo> entry : entityInfo.getPropTypes().entrySet()) {

            String propName = entry.getKey();
            set.add(propName);

        }
        for(String propName: set){
            sb.append("    //").append(propRemarks.get(propName)).append("\r\n")
                    .append("    public static final String ")
                    .append(propName.toUpperCase())
                    .append(" = ")
                    .append("\""+propName+"\";")
                    .append("\r\n");
        }
        this.param.put("propertiesStr", sb.toString());
    }
}