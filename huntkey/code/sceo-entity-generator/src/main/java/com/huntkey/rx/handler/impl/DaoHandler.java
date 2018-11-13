package com.huntkey.rx.handler.impl;


import java.io.File;

import com.huntkey.rx.Constants;
import com.huntkey.rx.config.Configuration;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.model.DaoInfo;

public class DaoHandler extends BaseHandler<DaoInfo> {

    public DaoHandler(String ftlName, DaoInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") + File.separator + Configuration.getString("dao.path")
                        + File.separator + info.getClassName() + Constants.FILE_SUFFIX_JAVA;

    }

    @Override
    public void combileParams(DaoInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("importStr", info.getImportStr());
        this.param.put("entityDesc", info.getEntityInfo().getEntityDesc());
        this.param.put("className", info.getClassName());
        this.param.put("entityClassName", info.getEntityInfo().getClassName());
        this.param.put("entityName", info.getEntityInfo().getEntityName());
    }

}