package com.huntkey.rx.task;


import java.util.List;

import com.huntkey.rx.framework.AbstractApplicationTask;
import com.huntkey.rx.framework.context.ApplicationContext;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.handler.impl.ServiceImplHandler;
import com.huntkey.rx.model.ServiceImplInfo;

public class ServiceImplTask extends AbstractApplicationTask {

    private static String SERVICEIMPL_FTL = "template/ServiceImpl.ftl";

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成serviceImpl...");
        List<ServiceImplInfo> list = (List<ServiceImplInfo>) context.getAttribute("serviceImplInfos");

        BaseHandler<ServiceImplInfo> baseHandler = null;
        for (ServiceImplInfo info : list) {
            baseHandler = new ServiceImplHandler(SERVICEIMPL_FTL, info);
            baseHandler.execute(context);
        }

        logger.info("结束生成serviceImpl。。。");
        return false;
    }

}