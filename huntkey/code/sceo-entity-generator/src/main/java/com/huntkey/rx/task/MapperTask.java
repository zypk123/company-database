package com.huntkey.rx.task;


import java.util.List;

import com.huntkey.rx.Constants;
import com.huntkey.rx.config.Configuration;
import com.huntkey.rx.framework.AbstractApplicationTask;
import com.huntkey.rx.framework.context.ApplicationContext;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.handler.impl.MapperHandler;
import com.huntkey.rx.model.MapperInfo;

public class MapperTask extends AbstractApplicationTask {

    private static String MAPPER_FTL = Configuration.getString("base.database")
        .equals(Constants.DB_ORACLE) ? "template/Mapper_oracle.ftl" : "template/Mapper.ftl";

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成Mapper");

        List<MapperInfo> list = (List<MapperInfo>) context.getAttribute("mapperInfos");

        BaseHandler<MapperInfo> handler = null;
        for (MapperInfo mapperInfo : list) {
            handler = new MapperHandler(MAPPER_FTL, mapperInfo);
            handler.execute(context);
        }

        logger.info("生成Mapper完成");
        return false;
    }

    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
    }
}
