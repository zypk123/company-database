package com.huntkey.rx.task;


import java.util.List;

import com.huntkey.rx.framework.AbstractApplicationTask;
import com.huntkey.rx.framework.context.ApplicationContext;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.handler.impl.ManagerImplHandler;
import com.huntkey.rx.model.ManagerImplInfo;

public class ManagerImplTask extends AbstractApplicationTask {

    private static String MANAGERIMPL_FTL = "template/ManagerImpl.ftl";

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成managerImpl");

        List<ManagerImplInfo> managerInfos = (List<ManagerImplInfo>) context.getAttribute("managerImplInfos");

        BaseHandler<ManagerImplInfo> handler = null;
        for (ManagerImplInfo managerImplInfo : managerInfos) {
            handler = new ManagerImplHandler(MANAGERIMPL_FTL, managerImplInfo);
            handler.execute(context);
        }

        logger.info("生成managerImpl完成");
        return false;
    }

}
