package com.huntkey.rx.purchase.provider.dao.impl;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.purchase.common.constants.RelationConstants;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.provider.dao.RelationDao;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xuyf on 2018/1/4 0004.
 */
@Component
public class RelationDaoImpl implements RelationDao {

    private static final Logger logger = LoggerFactory.getLogger(RelationDaoImpl.class);

    @Autowired
    OrmService ormService;

    @Override
    public Pagination<RelationEntity> selectRelationPageList(String relaCode, String relaShortName,
                                                             String relaUscc, String[] ids, int currentPage, int pageSize) {
        //查询伙伴类
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(relaCode)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelationProperty.RELA_CODE, relaCode));
        }

        if (!StringUtil.isNullOrEmpty(relaShortName)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelationProperty.RELA_SHORT_NAME, relaShortName));
        }

        if (!StringUtil.isNullOrEmpty(relaUscc)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelationProperty.RELA_USCC, relaUscc));
        }

        if (ids != null && ids.length > 0) {
            conditionList.add(ormParam.getInXML(EdmSysColumn.ID, ids));
        }

        Pagination<RelationEntity> relationEntityPagination;
        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
        }
        ormParam.setOrderExp(SQLSortEnum.ASC, RelationProperty.RELA_CODE);
        try {
            //如果分页参数大于0，进行分页查询
            if (currentPage > 0 && pageSize > 0) {
                ormParam.setPageNo(currentPage);
                ormParam.setPageSize(pageSize);
                relationEntityPagination = ormService.selectPagedBeanList(RelationEntity.class, ormParam);
            } else {
                List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, ormParam);
                relationEntityPagination = new Pagination<>(relationEntityList, 0, 0, relationEntityList.size());
            }
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "分页查询伙伴类错误：" + e.getMessage());
        }
        return relationEntityPagination;
    }

    @Override
    public List<RelationEntity> selectRelationUniqueCheck(String exceptRelaId, String relaUscc, String relaCode, String relaShortName) {
        List<RelationEntity> relationEntityList;
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(relaUscc)) {
            conditionList.add(ormParam.getEqualXML(RelationProperty.RELA_USCC, relaUscc));
        }
        if (!StringUtil.isNullOrEmpty(relaCode)) {
            conditionList.add(ormParam.getEqualXML(RelationProperty.RELA_CODE, relaCode));
        }
        if (!StringUtil.isNullOrEmpty(relaShortName)) {
            conditionList.add(ormParam.getEqualXML(RelationProperty.RELA_SHORT_NAME, relaShortName));
        }

        if (conditionList.size() <= 0){
            return null;
        }

        String whereExp = OrmParam.or(conditionList);

        if (!StringUtil.isNullOrEmpty(exceptRelaId)){
            whereExp = OrmParam.and(ormParam.getUnequalXML(EdmSysColumn.ID, exceptRelaId), whereExp);
        }
        ormParam.setWhereExp(whereExp);

        try {
            relationEntityList = ormService.selectBeanList(RelationEntity.class, ormParam);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询伙伴类错误：" + e.getMessage());
        }

        if (relationEntityList == null || relationEntityList.size() <= 0) {
            relationEntityList = new ArrayList<>();
        }

        return relationEntityList;

    }

    @Override
    public RelationEntity selectRelationentityById(String id) {
        try {
            if (StringUtil.isNullOrEmpty(id)) {
                return null;
            }
            return ormService.load(RelationEntity.class, id);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询伙伴对象错误：" + e.getMessage());
        }
    }

    @Override
    public List<RelationEntity> selectRelationByKeyword(String keyword) {
        List<RelationEntity> relationEntityList;
        OrmParam ormParam = new OrmParam();
        if (!StringUtil.isNullOrEmpty(keyword)){
            ormParam.setWhereExp(OrmParam.or(ormParam.getMatchMiddleXML(RelationProperty.RELA_CODE, keyword),
                    ormParam.getMatchMiddleXML(RelationProperty.RELA_SHORT_NAME, keyword)));
        }
        ormParam.setOrderExp(SQLSortEnum.ASC, RelationProperty.RELA_CODE);
        try {
            relationEntityList = ormService.selectBeanList(RelationEntity.class, ormParam);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询伙伴类错误：" + e.getMessage());
        }
        if (relationEntityList == null){
            relationEntityList = new ArrayList<>();
        }
        return relationEntityList;
    }

    @Override
    public List<RelationEntity> selectRelationList(String relaId, String relaUscc, String relaCode, String relaShortName) {
        List<RelationEntity> relationEntityList = null;
        //查询伙伴类
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(relaId)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.ID, relaId));
        }

        if (!StringUtil.isNullOrEmpty(relaUscc)) {
            conditionList.add(ormParam.getEqualXML(RelationProperty.RELA_USCC, relaUscc));
        }

        if (!StringUtil.isNullOrEmpty(relaCode)) {
            conditionList.add(ormParam.getEqualXML(RelationProperty.RELA_CODE, relaCode));
        }

        if (!StringUtil.isNullOrEmpty(relaShortName)) {
            conditionList.add(ormParam.getEqualXML(RelationProperty.RELA_SHORT_NAME, relaShortName));
        }
        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relationEntityList = ormService.selectBeanList(RelationEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询伙伴类错误：" + e.getMessage());
            }
        }
        if (relationEntityList == null){
            relationEntityList = new ArrayList<>();
        }
        return relationEntityList;
    }

    @Override
    public List<RelaRelaTypesSetaEntity> selectRelaTypesSet(String relationId, String[] relaTypes) {
        List<RelaRelaTypesSetaEntity> relaTypesSetaEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(relationId)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, relationId));
        }

        if (relaTypes != null && relaTypes.length > 0) {
            conditionList.add(ormParam.getInXML(RelaRelaTypesSetaProperty.RELA_TYPE, relaTypes));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            ormParam.setOrderExp(SQLSortEnum.ASC, RelaRelaTypesSetaProperty.RELA_TYPE);
            try {
                relaTypesSetaEntityList = ormService.selectBeanList(RelaRelaTypesSetaEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询伙伴类型集错误：" + e.getMessage());
            }
        }
        return relaTypesSetaEntityList;
    }

    @Override
    public RelaRelaTypesSetaEntity selectRelaTypeEntityById(String id) {
        try {
            if (StringUtil.isNullOrEmpty(id)) {
                return null;
            }
            return ormService.load(RelaRelaTypesSetaEntity.class, id);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询伙伴类型对象错误：" + e.getMessage());
        }
    }

    @Override
    public List<String> selectRelaIdByRelaStatus(String relaStatus, String[] relaTypes) {
        boolean isFindAll = true;

        if (relaTypes != null && relaTypes.length > 0) {
            isFindAll = false;
        }

        List<String> relationIdList = new ArrayList<>();

        //客户
        if (isFindAll == true || ArrayUtils.contains(relaTypes, RelationConstants.RELA_TYPE_CUST)) {
            OrmParam ormParam1 = new OrmParam();
            if (!StringUtil.isNullOrEmpty(relaStatus)){
                String whereExp1 = OrmParam.and(ormParam1.getEqualXML(RelaRelaCustSetaProperty.RELA_STAT_CUST, relaStatus));
                ormParam1.setWhereExp(whereExp1);
            }
            try {
                List<RelaRelaCustSetaEntity> custSetaEntityList = ormService.selectBeanList(RelaRelaCustSetaEntity.class, ormParam1);
                if (custSetaEntityList != null && custSetaEntityList.size() > 0) {
                    custSetaEntityList.forEach(cust -> relationIdList.add(cust.getPid()));
                }
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "根据伙伴状态查询客户数据集错误：" + e.getMessage());
            }
        }
        //供应商
        if (isFindAll == true || ArrayUtils.contains(relaTypes, RelationConstants.RELA_TYPE_SUPPLIER)) {
            OrmParam ormParam2 = new OrmParam();
            if (!StringUtil.isNullOrEmpty(relaStatus)) {
                String whereExp2 = OrmParam.and(ormParam2.getEqualXML(RelaRelaSupplierSetaProperty.RELA_STAT_SUPP, relaStatus));
                ormParam2.setWhereExp(whereExp2);
            }
            try {
                List<RelaRelaSupplierSetaEntity> supplierSetaEntityList = ormService.selectBeanList(RelaRelaSupplierSetaEntity.class, ormParam2);
                if (supplierSetaEntityList != null && supplierSetaEntityList.size() > 0) {
                    supplierSetaEntityList.forEach(supplier -> relationIdList.add(supplier.getPid()));
                }
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "根据伙伴状态查询供应商数据集错误：" + e.getMessage());
            }
        }
        return relationIdList;
    }


    @Override
    public List<RelaRelaCustSetaEntity> selectRelaCustSet(String relationId, String[] relaStatCusts, String relaServdeptCust,
                                                          Integer relaCredlimit, String relaSettWayc) {
        List<RelaRelaCustSetaEntity> relaCustSetaEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(relationId)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, relationId));
        }

        if (relaStatCusts != null && relaStatCusts.length > 0) {
            conditionList.add(ormParam.getInXML(RelaRelaCustSetaProperty.RELA_STAT_CUST, relaStatCusts));
        }

        if (!StringUtil.isNullOrEmpty(relaServdeptCust)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaCustSetaProperty.RELA_SERVDEPT_CUST, relaServdeptCust));
        }

        if (relaCredlimit != null) {
            conditionList.add(ormParam.getLessThanAndEqualXML(RelaRelaCustSetaProperty.RELA_CREDLIMIT, relaCredlimit));
            conditionList.add(ormParam.getGreaterThanAndEqualXML(RelaRelaCustSetaProperty.RELA_CREDLIMIT, relaCredlimit));
        }

        if (!StringUtil.isNullOrEmpty(relaSettWayc)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaCustSetaProperty.RELA_SETT_WAYC, relaSettWayc));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relaCustSetaEntityList = ormService.selectBeanList(RelaRelaCustSetaEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询客户数据集错误：" + e.getMessage());
            }
        }
        return relaCustSetaEntityList;
    }

    @Override
    public RelaRelaCustSetaEntity selectRelaCustById(String id) {
        try {
            if (StringUtil.isNullOrEmpty(id)) {
                return null;
            }
            return ormService.load(RelaRelaCustSetaEntity.class, id);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询客户数据集对象错误：" + e.getMessage());
        }
    }

    @Override
    public List<RelaRelaSupplierSetaEntity> selectRelaSupplierSet(String relationId, String relaSupptype,
                                                                  String[] relaStatSupps, String relaServdeptSupp,
                                                                  String[] relaTaxrates, String relaSettWays) {
        List<RelaRelaSupplierSetaEntity> relaSupplierSetaEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(relationId)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, relationId));
        }

        if (!StringUtil.isNullOrEmpty(relaSupptype)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaSupplierSetaProperty.RELA_SUPPTYPE, relaSupptype));
        }

        if (relaStatSupps != null && relaStatSupps.length > 0) {
            conditionList.add(ormParam.getInXML(RelaRelaSupplierSetaProperty.RELA_STAT_SUPP, relaStatSupps));
        }

        if (!StringUtil.isNullOrEmpty(relaServdeptSupp)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaSupplierSetaProperty.RELA_SERVDEPT_SUPP, relaServdeptSupp));
        }

        if (relaTaxrates != null && relaTaxrates.length > 0) {
            conditionList.add(ormParam.getInXML(RelaRelaSupplierSetaProperty.RELA_TAXRATE, relaTaxrates));
        }

        if (!StringUtil.isNullOrEmpty(relaSettWays)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaSupplierSetaProperty.RELA_SETT_WAYS, relaSettWays));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relaSupplierSetaEntityList = ormService.selectBeanList(RelaRelaSupplierSetaEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询供应商数据集错误：" + e.getMessage());
            }
        }
        return relaSupplierSetaEntityList;
    }

    @Override
    public RelaRelaSupplierSetaEntity selectRelaSupplierById(String id) {
        try {
            if (StringUtil.isNullOrEmpty(id)) {
                return null;
            }
            return ormService.load(RelaRelaSupplierSetaEntity.class, id);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询供应商数据集对象错误：" + e.getMessage());
        }
    }

    @Override
    public List<RelaRelaHolderSetaEntity> selectRelaHolderSet(String relationId, String relaHname, BigDecimal relaHrate) {
        List<RelaRelaHolderSetaEntity> relaHolderSetaEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(relationId)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, relationId));
        }

        if (!StringUtil.isNullOrEmpty(relaHname)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelaRelaHolderSetaProperty.RELA_HNAME, relaHname));
        }

        if (relaHrate != null) {
            conditionList.add(ormParam.getEqualXML(RelaRelaHolderSetaProperty.RELA_HRATE, relaHrate));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            ormParam.setOrderExp(SQLSortEnum.DESC, RelaRelaHolderSetaProperty.RELA_HRATE);
            try {
                relaHolderSetaEntityList = ormService.selectBeanList(RelaRelaHolderSetaEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询股东数据集错误：" + e.getMessage());
            }
        }
        return relaHolderSetaEntityList;
    }

    @Override
    public RelaRelaHolderSetaEntity selectRelaHolderById(String id) {
        try {
            if (StringUtil.isNullOrEmpty(id)) {
                return null;
            }
            return ormService.load(RelaRelaHolderSetaEntity.class, id);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询股东数据集对象错误：" + e.getMessage());
        }
    }

    @Override
    public List<RelaRelaSystemSetaEntity> selectRelaSystemSet(String relationId, String relaSname) {
        List<RelaRelaSystemSetaEntity> relaSystemSetaEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(relationId)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, relationId));
        }

        if (!StringUtil.isNullOrEmpty(relaSname)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelaRelaSystemSetaProperty.RELA_SNAME, relaSname));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relaSystemSetaEntityList = ormService.selectBeanList(RelaRelaSystemSetaEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询体系认证集错误：" + e.getMessage());
            }
        }
        return relaSystemSetaEntityList;
    }

    @Override
    public RelaRelaSystemSetaEntity selectRelaSystemById(String id) {
        try {
            if (StringUtil.isNullOrEmpty(id)) {
                return null;
            }
            return ormService.load(RelaRelaSystemSetaEntity.class, id);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询体系认证对象错误：" + e.getMessage());
        }
    }

    @Override
    public List<RelaRelaContactCustSetbEntity> selectRelaContactCustSet(String pid, String relaConttypeCust,
                                                                        String relaContnameCust, String relaContsexCust,
                                                                        String relaConttelCust) {
        List<RelaRelaContactCustSetbEntity> relaContactCustSetbEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(pid)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        }

        if (!StringUtil.isNullOrEmpty(relaConttypeCust)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaContactCustSetbProperty.RELA_CONTTYPE_CUST, relaConttypeCust));
        }

        if (!StringUtil.isNullOrEmpty(relaContnameCust)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelaRelaContactCustSetbProperty.RELA_CONTNAME_CUST, relaContnameCust));
        }

        if (!StringUtil.isNullOrEmpty(relaContsexCust)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaContactCustSetbProperty.RELA_CONTSEX_CUST, relaContsexCust));
        }

        if (!StringUtil.isNullOrEmpty(relaConttelCust)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelaRelaContactCustSetbProperty.RELA_CONTSEX_CUST, relaConttelCust));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relaContactCustSetbEntityList = ormService.selectBeanList(RelaRelaContactCustSetbEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询客户联系人集错误：" + e.getMessage());
            }
        }
        return relaContactCustSetbEntityList;
    }

    @Override
    public List<RelaRelaAttachCustSetbEntity> selectRelaAttachCustSet(String pid, String relaAttatypeCust,
                                                                      Date relaAttavalidCust) {
        List<RelaRelaAttachCustSetbEntity> relaAttachCustSetbEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(pid)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        }

        if (!StringUtil.isNullOrEmpty(relaAttatypeCust)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaAttachCustSetbProperty.RELA_ATTATYPE_CUST, relaAttatypeCust));
        }

        if (relaAttavalidCust != null) {
            conditionList.add(ormParam.getGreaterThanAndEqualXML(RelaRelaAttachCustSetbProperty.RELA_ATTAVALID_CUST, relaAttavalidCust));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relaAttachCustSetbEntityList = ormService.selectBeanList(RelaRelaAttachCustSetbEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询客户附件资料集错误：" + e.getMessage());
            }
        }
        return relaAttachCustSetbEntityList;
    }

    @Override
    public List<RelaRelaServtCustSetbEntity> selectRelaServtCustSet(String pid, String[] relaSttypeCusts,
                                                                    String relaStempCust) {
        List<RelaRelaServtCustSetbEntity> relaServtCustSetbEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(pid)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        }

        if (relaSttypeCusts != null && relaSttypeCusts.length > 0) {
            conditionList.add(ormParam.getInXML(RelaRelaServtCustSetbProperty.RELA_STTYPE_CUST, relaSttypeCusts));
        }

        if (!StringUtil.isNullOrEmpty(relaStempCust)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaServtCustSetbProperty.RELA_STEMP_CUST, relaStempCust));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relaServtCustSetbEntityList = ormService.selectBeanList(RelaRelaServtCustSetbEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询客户服务团队集错误：" + e.getMessage());
            }
        }
        return relaServtCustSetbEntityList;
    }

    @Override
    public List<RelaRelaAccountCustSetbEntity> selectRelaAccountCustSet(String pid, String relaAcconameCust,
                                                                        String relaAccobankCust, String relaAcconumCust,
                                                                        String relaAccocurrCust, Date accoDate) {
        List<RelaRelaAccountCustSetbEntity> relaAccountCustSetbEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(pid)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        }

        if (!StringUtil.isNullOrEmpty(relaAcconameCust)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelaRelaAccountCustSetbProperty.RELA_ACCONAME_CUST, relaAcconameCust));
        }

        if (!StringUtil.isNullOrEmpty(relaAccobankCust)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelaRelaAccountCustSetbProperty.RELA_ACCOBANK_CUST, relaAccobankCust));
        }

        if (!StringUtil.isNullOrEmpty(relaAcconumCust)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaAccountCustSetbProperty.RELA_ACCONUM_CUST, relaAcconumCust));
        }

        if (!StringUtil.isNullOrEmpty(relaAccocurrCust)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaAccountCustSetbProperty.RELA_ACCOCURR_CUST, relaAccocurrCust));
        }

        if (accoDate != null) {
            conditionList.add(ormParam.getLessThanAndEqualXML(RelaRelaAccountCustSetbProperty.RELA_ACCOBEG_CUST, accoDate));
            conditionList.add(ormParam.getGreaterThanXML(RelaRelaAccountCustSetbProperty.RELA_ACCOEND_CUST, accoDate));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relaAccountCustSetbEntityList = ormService.selectBeanList(RelaRelaAccountCustSetbEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询客户账户管理集错误：" + e.getMessage());
            }
        }
        return relaAccountCustSetbEntityList;
    }

    @Override
    public List<RelaRelaDeliCustSetbEntity> selectRelaDeliCustSet(String pid, String relaDanameCust, String relaDaddrpCust,
                                                                  String relaDaddrcCust, String relaDaddrlCust) {
        List<RelaRelaDeliCustSetbEntity> relaDeliCustSetbEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(pid)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        }

        if (!StringUtil.isNullOrEmpty(relaDanameCust)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelaRelaDeliCustSetbProperty.RELA_DANAME_CUST, relaDanameCust));
        }

        if (!StringUtil.isNullOrEmpty(relaDaddrpCust)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaDeliCustSetbProperty.RELA_DADDRP_CUST, relaDaddrpCust));
        }

        if (!StringUtil.isNullOrEmpty(relaDaddrcCust)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaDeliCustSetbProperty.RELA_DADDRC_CUST, relaDaddrcCust));
        }

        if (!StringUtil.isNullOrEmpty(relaDaddrlCust)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaDeliCustSetbProperty.RELA_DADDRL_CUST, relaDaddrlCust));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relaDeliCustSetbEntityList = ormService.selectBeanList(RelaRelaDeliCustSetbEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询客户交货管理集错误：" + e.getMessage());
            }
        }
        return relaDeliCustSetbEntityList;
    }

    @Override
    public List<RelaRelaContactSuppSetbEntity> selectRelaContactSuppSet(String pid, String relaConttypeSupp,
                                                                        String relaContnameSupp, String relaContsexSupp,
                                                                        String relaConttelSupp) {
        List<RelaRelaContactSuppSetbEntity> relaContactSuppSetbEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(pid)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        }

        if (!StringUtil.isNullOrEmpty(relaConttypeSupp)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaContactSuppSetbProperty.RELA_CONTTYPE_SUPP, relaConttypeSupp));
        }

        if (!StringUtil.isNullOrEmpty(relaContnameSupp)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelaRelaContactSuppSetbProperty.RELA_CONTNAME_SUPP, relaContnameSupp));
        }

        if (!StringUtil.isNullOrEmpty(relaContsexSupp)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaContactSuppSetbProperty.RELA_CONTSEX_SUPP, relaContsexSupp));
        }

        if (!StringUtil.isNullOrEmpty(relaConttelSupp)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelaRelaContactSuppSetbProperty.RELA_CONTTEL_SUPP, relaConttelSupp));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relaContactSuppSetbEntityList = ormService.selectBeanList(RelaRelaContactSuppSetbEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询供应商联系人集错误：" + e.getMessage());
            }
        }
        return relaContactSuppSetbEntityList;
    }

    @Override
    public List<RelaRelaAttachSuppSetbEntity> selectRelaAttachSuppSet(String pid, String relaAttatypeSupp,
                                                                      Date relaAttavalidSupp) {
        List<RelaRelaAttachSuppSetbEntity> relaAttachSuppSetbEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(pid)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        }

        if (!StringUtil.isNullOrEmpty(relaAttatypeSupp)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaAttachSuppSetbProperty.RELA_ATTATYPE_SUPP, relaAttatypeSupp));
        }

        if (relaAttavalidSupp != null) {
            conditionList.add(ormParam.getGreaterThanAndEqualXML(RelaRelaAttachSuppSetbProperty.RELA_ATTAVALID_SUPP, relaAttavalidSupp));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relaAttachSuppSetbEntityList = ormService.selectBeanList(RelaRelaAttachSuppSetbEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询供应商附件资料集错误：" + e.getMessage());
            }
        }
        return relaAttachSuppSetbEntityList;
    }

    @Override
    public List<RelaRelaServtSuppSetbEntity> selectRelaServtSuppSet(String pid, String[] relaSttypeSupps,
                                                                    String relaStempSupp) {
        List<RelaRelaServtSuppSetbEntity> relaServtSuppSetbEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(pid)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        }

        if (relaSttypeSupps != null && relaSttypeSupps.length > 0) {
            conditionList.add(ormParam.getInXML(RelaRelaServtSuppSetbProperty.RELA_STTYPE_SUPP, relaSttypeSupps));
        }

        if (!StringUtil.isNullOrEmpty(relaStempSupp)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaServtSuppSetbProperty.RELA_STEMP_SUPP, relaStempSupp));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relaServtSuppSetbEntityList = ormService.selectBeanList(RelaRelaServtSuppSetbEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询供应商服务团队集错误：" + e.getMessage());
            }
        }
        return relaServtSuppSetbEntityList;
    }

    @Override
    public List<RelaRelaAccountSuppSetbEntity> selectRelaAccountSuppSet(String pid, String relaAcconameSupp,
                                                                        String relaAccobankSupp, String relaAcconumSupp,
                                                                        String relaAccocurrSupp, Date accoDate) {
        List<RelaRelaAccountSuppSetbEntity> relaAccountSuppSetbEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(pid)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        }

        if (!StringUtil.isNullOrEmpty(relaAcconameSupp)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelaRelaAccountSuppSetbProperty.RELA_ACCONAME_SUPP, relaAcconameSupp));
        }

        if (!StringUtil.isNullOrEmpty(relaAccobankSupp)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelaRelaAccountSuppSetbProperty.RELA_ACCOBANK_SUPP, relaAccobankSupp));
        }

        if (!StringUtil.isNullOrEmpty(relaAcconumSupp)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaAccountSuppSetbProperty.RELA_ACCONUM_SUPP, relaAcconumSupp));
        }

        if (!StringUtil.isNullOrEmpty(relaAccocurrSupp)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaAccountSuppSetbProperty.RELA_ACCOCURR_SUPP, relaAccocurrSupp));
        }

        if (accoDate != null) {
            conditionList.add(ormParam.getLessThanAndEqualXML(RelaRelaAccountSuppSetbProperty.RELA_ACCOBEG_SUPP, accoDate));
            conditionList.add(ormParam.getGreaterThanXML(RelaRelaAccountSuppSetbProperty.RELA_ACCOEND_SUPP, accoDate));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relaAccountSuppSetbEntityList = ormService.selectBeanList(RelaRelaAccountSuppSetbEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询供应商服务团队集错误：" + e.getMessage());
            }
        }
        return relaAccountSuppSetbEntityList;
    }

    @Override
    public List<RelaRelaDeliSuppSetbEntity> selectRelaDeliSuppSet(String pid, String relaDanameSupp, String relaDaddrpSupp,
                                                                  String relaDaddrcSupp, String relaDaddrlSupp) {
        List<RelaRelaDeliSuppSetbEntity> relaDeliSuppSetbEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(pid)) {
            conditionList.add(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        }

        if (!StringUtil.isNullOrEmpty(relaDanameSupp)) {
            conditionList.add(ormParam.getMatchMiddleXML(RelaRelaDeliSuppSetbProperty.RELA_DANAME_SUPP, relaDanameSupp));
        }

        if (!StringUtil.isNullOrEmpty(relaDaddrpSupp)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaDeliSuppSetbProperty.RELA_DADDRP_SUPP, relaDaddrpSupp));
        }

        if (!StringUtil.isNullOrEmpty(relaDaddrcSupp)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaDeliSuppSetbProperty.RELA_DADDRC_SUPP, relaDaddrcSupp));
        }

        if (!StringUtil.isNullOrEmpty(relaDaddrlSupp)) {
            conditionList.add(ormParam.getEqualXML(RelaRelaDeliSuppSetbProperty.RELA_DADDRL_SUPP, relaDaddrlSupp));
        }

        if (!conditionList.isEmpty()) {
            ormParam.setWhereExp(OrmParam.and(conditionList));
            try {
                relaDeliSuppSetbEntityList = ormService.selectBeanList(RelaRelaDeliSuppSetbEntity.class, ormParam);
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR, "查询供应商交货管理集错误：" + e.getMessage());
            }
        }
        return relaDeliSuppSetbEntityList;
    }

    @Override
    public String insertRelationEntity(RelationEntity relationEntity) {
        String id;
        try {
            //新增伙伴数据
            id = ormService.insert(relationEntity).toString();
            relationEntity.setId(id);
            //新增体系认证集
            if (relationEntity.getRela_system_set() != null && relationEntity.getRela_system_set().size() > 0){
                EdmUtil.setPropertyBaseEntitiesSysColumns(RelationEntity.class, relationEntity, relationEntity.getRela_system_set(), SQLCurdEnum.INSERT);
                ormService.insert(relationEntity.getRela_system_set());
            }
            //新增股东列表
            if (relationEntity.getRela_holder_set() != null && relationEntity.getRela_holder_set().size() > 0){
                EdmUtil.setPropertyBaseEntitiesSysColumns(RelationEntity.class, relationEntity, relationEntity.getRela_holder_set(), SQLCurdEnum.INSERT);
                ormService.insert(relationEntity.getRela_holder_set());
            }
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "新增伙伴信息错误：" + e.getMessage());
        }
        return id;

    }

    @Override
    public int updateRelationEntity(RelationEntity relationEntity) {
        int resultCount;
        try {
            //更新伙伴数据
            resultCount  = ormService.updateSelective(relationEntity);
            //新增体系认证集
            if (relationEntity.getRela_system_set() != null && relationEntity.getRela_system_set().size() > 0){
                EdmUtil.setPropertyBaseEntitiesSysColumns(RelationEntity.class, relationEntity, relationEntity.getRela_system_set(), SQLCurdEnum.INSERT);
                ormService.insert(relationEntity.getRela_system_set());
            }
            //新增股东列表
            if (relationEntity.getRela_holder_set() != null && relationEntity.getRela_holder_set().size() > 0){
                EdmUtil.setPropertyBaseEntitiesSysColumns(RelationEntity.class, relationEntity, relationEntity.getRela_holder_set(), SQLCurdEnum.INSERT);
                ormService.insert(relationEntity.getRela_holder_set());
            }
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "更新伙伴信息错误：" + e.getMessage());
        }

        return resultCount;
    }

    @Override
    public int deleteRelationEntity(String id) {
        int retInt;
        try {
            retInt = ormService.delete(RelationEntity.class, id);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "删除伙伴错误：" + e.getMessage());
        }
        return retInt;
    }

    @Override
    public int deleteRelaSystemSetaEntityByPid(String relationId) {
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, relationId));
        int retInt;
        try {
            retInt = ormService.delete(RelaRelaSystemSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "删除伙伴体系认证集错误：" + e.getMessage());
        }
        return retInt;
    }

    @Override
    public int deleteRelaHolderSetaEntityByPid(String relationId) {
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, relationId));
        int retInt;
        try {
            retInt = ormService.delete(RelaRelaHolderSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "删除伙伴股东数据集错误：" + e.getMessage());
        }
        return retInt;
    }

    @Override
    public DepttreeEntity selectDepttreeEntityById(String id) {
        try {
            if (StringUtil.isNullOrEmpty(id)) {
                return null;
            }
            return ormService.load(DepttreeEntity.class, id);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门对象错误：" + e.getMessage());
        }
    }

    @Override
    public EmployeeEntity selectEmployeeEntityById(String id) {
        try {
            if (StringUtil.isNullOrEmpty(id)) {
                return null;
            }
            return ormService.load(EmployeeEntity.class, id);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询员工对象错误：" + e.getMessage());
        }
    }

    @Override
    public JobpositionEntity selectJobpositionEntityById(String id) {
        try {
            if (StringUtil.isNullOrEmpty(id)) {
                return null;
            }
            return ormService.load(JobpositionEntity.class, id);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询岗位对象错误：" + e.getMessage());
        }
    }

}
