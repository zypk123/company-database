package com.huntkey.rx.task;


import com.huntkey.rx.Constants;
import com.huntkey.rx.config.Configuration;
import com.huntkey.rx.framework.AbstractApplicationTask;
import com.huntkey.rx.framework.context.ApplicationContext;
import com.huntkey.rx.handler.BaseHandler;
import com.huntkey.rx.handler.impl.ConstantEntityHandler;
import com.huntkey.rx.handler.impl.EntityHandler;
import com.huntkey.rx.model.DaoInfo;
import com.huntkey.rx.model.EntityInfo;
import com.huntkey.rx.model.VoInfo;
import com.huntkey.rx.util.PropertyUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConstantEntityTask extends AbstractApplicationTask {

    private static String    ENTITY_FTL = "template/ConstantEntity.ftl";

    private List<Map<String, String>> tableList;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成实体");

        // 获取实体信息
        tableList = (List<Map<String, String>>) context.getAttribute("tableInfos");

        BaseHandler<List<Map<String, String>>> handler;
        handler = new ConstantEntityHandler(ENTITY_FTL, tableList);
        handler.execute(context);
        logger.info("生成实体类完成");
        return false;
    }

    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);

        List<DaoInfo> daoList = new ArrayList<DaoInfo>();
        List<VoInfo> voList = new ArrayList<VoInfo>();
        // 组装Dao信息、组装Vo信息
        DaoInfo daoInfo = null;
        VoInfo voInfo = null;
//        for (EntityInfo entityInfo : entityInfos) {
//            daoInfo = new DaoInfo();
//            daoInfo.setClassName(entityInfo.getEntityName() + Constants.DAO_SUFFIX);
//            daoInfo.setEntityInfo(entityInfo);
//            daoInfo.setImportStr("import " + entityInfo.getEntityPackage() + Constants.CHARACTER_POINT
//                                 + entityInfo.getClassName() + Constants.CHARACTER_SPLIT);
//            daoInfo.setPackageStr(Configuration.getString("dao.package"));
//            daoList.add(daoInfo);
//
//            voInfo = new VoInfo();
//            voInfo.setPackageStr(Configuration.getString("vo.package"));
//            voInfo.setClassName(entityInfo.getEntityName() + Constants.VO_SUFFIX);
//            voInfo.setEntityInfo(entityInfo);
//            voList.add(voInfo);
//        }

        context.setAttribute("daoList", daoList);
        context.setAttribute("voList", voList);
    }

}