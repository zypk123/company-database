package com.huntkey.rx.handler.impl;


import java.io.File;

import com.huntkey.rx.Constants;
import com.huntkey.rx.config.Configuration;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.model.ServiceImplInfo;

public class ServiceImplHandler extends BaseHandler<ServiceImplInfo> {

    public ServiceImplHandler(String ftlName, ServiceImplInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") + File.separator
                        + Configuration.getString("serviceImpl.path") + File.separator + info.getClassName()
                        + Constants.FILE_SUFFIX_JAVA;
    }

    @Override
    public void combileParams(ServiceImplInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("managerType", info.getManagerType());
        this.param.put("serviceType", info.getServiceType());
        this.param.put("getCommandType", info.getGetCommandType());
        this.param.put("listCommandType", info.getListCommandType());
        this.param.put("batchCommandType", info.getBatchCommandType());
        this.param.put("commandType", info.getCommandType());
        this.param.put("queryCommand", info.getQueryCommand());
        this.param.put("voType", info.getVoType());
        this.param.put("entityDesc", info.getEntityDesc());
        this.param.put("lowerEntityName", info.getLowerEntityName());
        this.param.put("entityName", info.getEntityName());
    }

}