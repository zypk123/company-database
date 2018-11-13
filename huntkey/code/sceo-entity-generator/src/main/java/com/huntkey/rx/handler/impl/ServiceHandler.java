package com.huntkey.rx.handler.impl;


import java.io.File;

import com.huntkey.rx.Constants;
import com.huntkey.rx.config.Configuration;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.model.ServiceInfo;

public class ServiceHandler extends BaseHandler<ServiceInfo> {

    public ServiceHandler(String ftlName, ServiceInfo info){
        this.info = info;
        this.ftlName = ftlName;
        this.savePath = Configuration.getString("base.baseDir") + File.separator
                        + Configuration.getString("service.path") + File.separator + info.getClassName()
                        + Constants.FILE_SUFFIX_JAVA;
    }

    @Override
    public void combileParams(ServiceInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("getCommandType", info.getGetCommandType());
        this.param.put("listCommandType", info.getListCommandType());
        this.param.put("batchCommandType", info.getBatchCommandType());
        this.param.put("commandType", info.getCommandType());
        this.param.put("queryCommandType", info.getQueryCommandType());
        this.param.put("voType", info.getVoType());
        this.param.put("entityDesc", info.getEntityDesc());
        this.param.put("className", info.getClassName());
        this.param.put("entityName", info.getEntityName());
        this.param.put("voClassName", info.getVoClassName());
    }

}
