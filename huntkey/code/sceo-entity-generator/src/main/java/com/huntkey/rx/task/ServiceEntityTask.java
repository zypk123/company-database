package com.huntkey.rx.task;


import com.huntkey.rx.framework.AbstractApplicationTask;
import com.huntkey.rx.framework.context.ApplicationContext;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.handler.impl.ServiceEntityHandler;
import com.huntkey.rx.model.EntityInfo;
import com.huntkey.rx.model.ServiceEntityInfo;

import java.util.List;

public class ServiceEntityTask extends AbstractApplicationTask {

    private static String SERVICEIMPL_FTL = "template/ServiceEntity.ftl";

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成serviceImpl...");
        List<EntityInfo> list = (List<EntityInfo>) context.getAttribute("entityInfos");

        BaseHandler<EntityInfo> baseHandler;
        for (EntityInfo info : list) {
            baseHandler = new ServiceEntityHandler(SERVICEIMPL_FTL, info);
            baseHandler.execute(context);
        }

        logger.info("结束生成serviceImpl。。。");
        return false;
    }

}