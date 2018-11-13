package com.huntkey.rx.purchase.provider.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.base.BaseEntity;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.OrderProperty;
import com.huntkey.rx.edm.constant.RelationProperty;
import com.huntkey.rx.edm.constant.RelationmaintorderProperty;
import com.huntkey.rx.edm.constant.RemoRemoHolderSetaProperty;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.purchase.common.constants.OrderConstants;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.base.CurrentSessionEntity;
import com.huntkey.rx.purchase.common.model.relation.RelationMaintOrderDTO;
import com.huntkey.rx.purchase.provider.dao.RelationMaintOrderDao;
import com.huntkey.rx.purchase.provider.service.BizFormService;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xuyf on 2018/1/11 0011.
 */
@Repository
public class RelationMaintOrderDaoImpl implements RelationMaintOrderDao {

    private static final Logger logger = LoggerFactory.getLogger(RelationMaintOrderDaoImpl.class);

    @Autowired
    OrmService ormService;

    @Autowired
    BizFormService bizFormService;

    @Override
    public List<RelationmaintorderEntity> selectRelationMaintOrderUniqueCheck(String exceptRemoId, String remoUscc, String remoCode, String remoShortName) {
        List<RelationmaintorderEntity> relationmaintorderEntityList;
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(remoUscc)) {
            conditionList.add(ormParam.getEqualXML(RelationmaintorderProperty.REMO_USCC, remoUscc));
        }
        if (!StringUtil.isNullOrEmpty(remoCode)) {
            conditionList.add(ormParam.getEqualXML(RelationmaintorderProperty.REMO_CODE, remoCode));
        }
        if (!StringUtil.isNullOrEmpty(remoShortName)) {
            conditionList.add(ormParam.getEqualXML(RelationmaintorderProperty.REMO_SHORT_NAME, remoShortName));
        }
        if (conditionList.size() <= 0){
            return null;
        }
        //过滤单据已结束的数据
        String[] overStatus = {OrderConstants.ORDE_STATUS_5, OrderConstants.ORDE_STATUS_6};

        String whereExp = OrmParam.and(ormParam.getNotInXML(OrderProperty.ORDE_STATUS, overStatus), OrmParam.or(conditionList));

        if (!StringUtil.isNullOrEmpty(exceptRemoId)){
            whereExp = OrmParam.and(ormParam.getUnequalXML(EdmSysColumn.ID, exceptRemoId), whereExp);
        }
        ormParam.setWhereExp(whereExp);
        try {
            relationmaintorderEntityList = ormService.selectBeanList(RelationmaintorderEntity.class, ormParam);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询伙伴维护单错误：" + e.getMessage());
        }

        if (relationmaintorderEntityList == null || relationmaintorderEntityList.size() <= 0) {
            relationmaintorderEntityList = new ArrayList<>();
        }

        return relationmaintorderEntityList;
    }

    @Override
    public RelationmaintorderEntity selectRelationMaintOrderEntityById(String orderId) {
        try {
            if (StringUtil.isNullOrEmpty(orderId)) {
                return null;
            }
            return ormService.load(RelationmaintorderEntity.class, orderId);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询伙伴维护单错误：" + e.getMessage());
        }
    }

    @Override
    public List<RemoRemoSystemSetaEntity> selectRemoSystemSetByPid(String orderId) {
        List<RemoRemoSystemSetaEntity> remoSystemSetaEntityList;
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID, orderId)));
        try {
            remoSystemSetaEntityList = ormService.selectBeanList(RemoRemoSystemSetaEntity.class, ormParam);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询伙伴维护单体系认证集错误：" + e.getMessage());
        }
        if (remoSystemSetaEntityList == null) {
            remoSystemSetaEntityList = new ArrayList<>();
        }
        return remoSystemSetaEntityList;
    }

    @Override
    public List<RemoRemoHolderSetaEntity> selectRemoHolderSetByPid(String orderId) {
        List<RemoRemoHolderSetaEntity> remoHolderSetaEntityList;
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID, orderId)));
        ormParam.setOrderExp(SQLSortEnum.DESC, RemoRemoHolderSetaProperty.REMO_HRATE);
        try {
            remoHolderSetaEntityList = ormService.selectBeanList(RemoRemoHolderSetaEntity.class, ormParam);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询伙伴维护单股东数据集错误：" + e.getMessage());
        }
        if (remoHolderSetaEntityList == null) {
            remoHolderSetaEntityList = new ArrayList<>();
        }
        return remoHolderSetaEntityList;
    }

    @Override
    public String insertRelationMaintOrder(RelationmaintorderEntity relationmaintorderEntity) {
        String id;
        try {
            id = ormService.insert(relationmaintorderEntity).toString();
            relationmaintorderEntity.setId(id);
            EdmUtil.setPropertyBaseEntitiesSysColumns(RelationmaintorderEntity.class, relationmaintorderEntity,
                    relationmaintorderEntity.getRemo_system_set(), SQLCurdEnum.INSERT);
            ormService.insert(relationmaintorderEntity.getRemo_system_set());

            EdmUtil.setPropertyBaseEntitiesSysColumns(RelationmaintorderEntity.class, relationmaintorderEntity,
                    relationmaintorderEntity.getRemo_holder_set(), SQLCurdEnum.INSERT);
            ormService.insert(relationmaintorderEntity.getRemo_holder_set());
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "新增伙伴维护单错误：" + e.getMessage());
        }
        return id;
    }

    @Override
    public int updateRelationMaintOrder(RelationmaintorderEntity relationmaintorderEntity, boolean isSelective) {
        int resultCount;
        try {
            //新增体系认证集
            if (relationmaintorderEntity.getRemo_system_set() != null && relationmaintorderEntity.getRemo_system_set().size() > 0) {
                EdmUtil.setPropertyBaseEntitiesSysColumns(RelationmaintorderEntity.class, relationmaintorderEntity,
                        relationmaintorderEntity.getRemo_system_set(), SQLCurdEnum.INSERT);
                ormService.insert(relationmaintorderEntity.getRemo_system_set());
            }

            //新增股东数据集
            if (relationmaintorderEntity.getRemo_holder_set() != null && relationmaintorderEntity.getRemo_holder_set().size() > 0) {
                EdmUtil.setPropertyBaseEntitiesSysColumns(RelationmaintorderEntity.class, relationmaintorderEntity,
                        relationmaintorderEntity.getRemo_holder_set(), SQLCurdEnum.INSERT);
                ormService.insert(relationmaintorderEntity.getRemo_holder_set());
            }

            //更新伙伴维护单据
            if (isSelective){
                resultCount = ormService.updateSelective(relationmaintorderEntity);
            }else{
                resultCount = ormService.update(relationmaintorderEntity);
            }
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "更新伙伴维护单错误：" + e.getMessage());
        }
        return resultCount;
    }

    @Override
    public int deleteRemoSystemSetByPid(String pid) {
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        int retInt;
        try {
            retInt = ormService.delete(RemoRemoSystemSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "删除伙伴维护单体系认证集错误：" + e.getMessage());
        }
        return retInt;
    }

    @Override
    public int deleteRemoHolderSetByPid(String pid) {
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        int retInt;
        try {
            retInt = ormService.delete(RemoRemoHolderSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "删除伙伴维护单股东数据集错误：" + e.getMessage());
        }
        return retInt;
    }

    @Override
    public int deleteRelationMaintOrder(String id) {
        int retInt = 0;
        try {
            retInt = ormService.delete(RelationmaintorderEntity.class, id);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "删除伙伴维护单错误：" + e.getMessage());
        }
        return retInt;
    }

}
