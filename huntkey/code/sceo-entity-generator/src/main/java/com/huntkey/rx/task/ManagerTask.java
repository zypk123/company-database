package com.huntkey.rx.task;


import java.util.ArrayList;
import java.util.List;

import com.huntkey.rx.Constants;
import com.huntkey.rx.config.Configuration;
import com.huntkey.rx.framework.AbstractApplicationTask;
import com.huntkey.rx.framework.context.ApplicationContext;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.handler.impl.ManagerHandler;
import com.huntkey.rx.model.DaoInfo;
import com.huntkey.rx.model.EntityInfo;
import com.huntkey.rx.model.ManagerImplInfo;
import com.huntkey.rx.model.ManagerInfo;
import com.huntkey.rx.model.VoInfo;

public class ManagerTask extends AbstractApplicationTask {

    private static String     MANAGERIMPL_FTL = "template/Manager.ftl";

    private List<ManagerInfo> managerInfos;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成manager");

        managerInfos = (List<ManagerInfo>) context.getAttribute("managerInfos");

        BaseHandler<ManagerInfo> handler = null;
        for (ManagerInfo managerInfo : managerInfos) {
            handler = new ManagerHandler(MANAGERIMPL_FTL, managerInfo);
            handler.execute(context);
        }

        logger.info("生成manager完成");
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);

        // entitys
        List<EntityInfo> entityInfos = (List<EntityInfo>) context.getAttribute("entityInfos");

        // daos
        List<DaoInfo> daoInfos = (List<DaoInfo>) context.getAttribute("daoList");

        // vos
        List<VoInfo> voList = (List<VoInfo>) context.getAttribute("voList");

        // 组装managerImpl
        List<ManagerImplInfo> managerImplInfos = new ArrayList<ManagerImplInfo>();
        ManagerImplInfo managerImplInfo = null;
        for (int i = 0; i < managerInfos.size(); i++) {
            EntityInfo entityInfo = entityInfos.get(i);
            DaoInfo daoInfo = daoInfos.get(i);
            VoInfo voInfo = voList.get(i);
            ManagerInfo managerInfo = managerInfos.get(i);

            managerImplInfo = new ManagerImplInfo();

            managerImplInfo.setEntityInfo(entityInfo);
            managerImplInfo.setDaoInfo(daoInfo);
            managerImplInfo.setVoInfo(voInfo);
            managerImplInfo.setManagerInfo(managerInfo);

            // 接口类首字母小写
            managerImplInfo.setAnnotationName(
                managerInfo.getClassName().substring(0, 1).toLowerCase() + managerInfo.getClassName().substring(1));
            managerImplInfo.setClassName(entityInfo.getEntityName() + Constants.MANAGER_IMPL_SUFFIX);
            managerImplInfo.setDaoClassName(daoInfo.getClassName());
            managerImplInfo.setDaoType(daoInfo.getPackageStr() + Constants.CHARACTER_POINT + daoInfo.getClassName());
            managerImplInfo
                .setDaoVar(daoInfo.getClassName().substring(0, 1).toLowerCase() + daoInfo.getClassName().substring(1));
            managerImplInfo.setEntityClassName(entityInfo.getClassName());
            managerImplInfo.setEntityType(entityInfo.getPackageClassName());
            managerImplInfo.setManagerClassName(managerInfo.getClassName());
            managerImplInfo
                .setManagerType(managerInfo.getPackageStr() + Constants.CHARACTER_POINT + managerInfo.getClassName());
            managerImplInfo.setPackageStr(Configuration.getString("managerImpl.package"));
            managerImplInfo.setVoClassName(voInfo.getClassName());
            managerImplInfo.setVoType(voInfo.getPackageStr() + Constants.CHARACTER_POINT + voInfo.getClassName());

            managerImplInfos.add(managerImplInfo);
        }

        context.setAttribute("managerImplInfos", managerImplInfos);
    }

}
