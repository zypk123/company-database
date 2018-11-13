package com.huntkey.rx.handler.impl;


import java.io.File;

import com.huntkey.rx.Constants;
import com.huntkey.rx.config.Configuration;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.model.CommandInfo;
import com.huntkey.rx.util.StringUtil;

public class CommandHandler extends BaseHandler<CommandInfo> {

    private String className;

    public CommandHandler(String ftlName, CommandInfo info, String className){
        this.ftlName = ftlName;
        this.className = className;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") + File.separator
                        + Configuration.getString("command.path") + File.separator + this.className
                        + Constants.FILE_SUFFIX_JAVA;
    }

    @Override
    public void combileParams(CommandInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("voType", info.getVoType());
        this.param.put("entityName", info.getEntityName());
        this.param.put("voClassName", info.getVoClassName());
        this.param.put("className", this.className);
        this.param.put("serialVersionNum", StringUtil.generate16LongNum());
    }

}