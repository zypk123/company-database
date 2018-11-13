package com.huntkey.rx.task;


import java.util.List;

import com.huntkey.rx.framework.AbstractApplicationTask;
import com.huntkey.rx.framework.context.ApplicationContext;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.handler.impl.ServiceTestHandler;
import com.huntkey.rx.model.ServiceTestInfo;

public class ServiceTestTask extends AbstractApplicationTask {

    private static String SERVICETEST_FTL = "template/ServiceTest.ftl";

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成service单元测试类。。。");

        List<ServiceTestInfo> list = (List<ServiceTestInfo>) context.getAttribute("serviceTestInfos");

        BaseHandler<ServiceTestInfo> baseHandler = null;
        for (ServiceTestInfo serviceTestInfo : list) {
            baseHandler = new ServiceTestHandler(SERVICETEST_FTL, serviceTestInfo);
            baseHandler.execute(context);
        }

        logger.info("生成service单元测试类完成。。。");
        return false;
    }

}