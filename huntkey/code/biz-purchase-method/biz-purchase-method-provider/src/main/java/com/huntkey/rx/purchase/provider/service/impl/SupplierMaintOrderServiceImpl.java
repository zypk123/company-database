package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.huntkey.rx.base.BaseEntity;
import com.huntkey.rx.commons.utils.collection.ListUtils;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.purchase.common.constants.*;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.base.CurrentSessionEntity;
import com.huntkey.rx.purchase.common.model.supplier.*;
import com.huntkey.rx.purchase.common.util.JsonUtils;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.service.BizFormService;
import com.huntkey.rx.purchase.provider.service.CommonService;
import com.huntkey.rx.purchase.provider.service.SupplierMaintOrderService;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * 供应商维护单Service接口实现类
 *
 * @author zhangyu
 * @create 2018-01-02 17:44
 **/
@Service
public class SupplierMaintOrderServiceImpl implements SupplierMaintOrderService {

    Logger logger = LoggerFactory.getLogger(SupplierMaintOrderServiceImpl.class);

    @Autowired
    private OrmService ormService;

    @Autowired
    private BizFormService bizFormService;

    @Autowired
    private CommonService commonService;

    @Override
    public Result load(String id) {
        logger.info("供应商维护单加载Service启动...." + id);
        Result result = new Result();
        try {
            // 定义返回数据对象
            SupplierMaintOrderDTO supplierDTO = null;
            // 1. 查询 供应商维护单主表
            SuppliermaintorderEntity suppliermaintorderEntity = ormService.load(SuppliermaintorderEntity.class, id);
            if (null != suppliermaintorderEntity) {
                supplierDTO = JSONObject.parseObject(JSON.toJSONString(suppliermaintorderEntity), SupplierMaintOrderDTO.class);

                // 2. 查询 供应商维护单-体系认证
                List<SumoSumoSystemSetaEntity> systemSetList = selectByPid(supplierDTO.getId(), SumoSumoSystemSetaEntity.class);
                if (null != systemSetList && systemSetList.size() > 0) {
                    List<SumoSystemSetDTO> systemSetDTOList = JSONArray.parseArray(JSON.toJSONString(systemSetList), SumoSystemSetDTO.class);
                    supplierDTO.setSumoSystemSet(systemSetDTOList);
                }

                // 3. 查询 供应商维护单-股东数据集
                List<SumoSumoHolderSetaEntity> holderSetList = selectByPid(supplierDTO.getId(), SumoSumoHolderSetaEntity.class);
                if (null != holderSetList && holderSetList.size() > 0) {
                    List<SumoHolderSetDTO> sumoHolderSetDTOList = JSONArray.parseArray(JSON.toJSONString(holderSetList), SumoHolderSetDTO.class);
                    supplierDTO.setSumoHolderSet(sumoHolderSetDTOList);
                }

                // 4. 查询 供应商维护单-供应商数据集
                List<SumoSumoSupplierSetaEntity> supplierSetList = selectByPid(supplierDTO.getId(), SumoSumoSupplierSetaEntity.class);
                if (null != supplierSetList && supplierSetList.size() > 0) {
                    //  这里由于 供应商维护单和供应商数据集之间是一对一关系，故直接取第一条就行了
                    SumoSumoSupplierSetaEntity supplierSet = supplierSetList.get(0);
                    SumoSupplierSetDTO sumoSupplierSetDTO = JSONObject.parseObject(JSON.toJSONString(supplierSet), SumoSupplierSetDTO.class);
                    String servDeptId = sumoSupplierSetDTO.getSumoServdept();
                    if (!StringUtil.isNullOrEmpty(servDeptId)) {
                        DepttreeEntity deptTree = ormService.load(DepttreeEntity.class, servDeptId);
                        if (!StringUtil.isNullOrEmpty(deptTree)) {
                            // 设置服务部门名称
                            sumoSupplierSetDTO.setSumoServdeptName(deptTree.getMdep_name());
                        }
                    }
                    String settWayId = sumoSupplierSetDTO.getSumoSettWay();
                    if (!StringUtil.isNullOrEmpty(settWayId)) {
                        SettlemenetEntity settlemenet = ormService.load(SettlemenetEntity.class, settWayId);
                        if (!StringUtil.isNullOrEmpty(settlemenet)) {
                            // 设置结算方式描述
                            sumoSupplierSetDTO.setSumoSettWayDesc(settlemenet.getSett_desc());
                        }
                    }
                    // 5. 查询 供应商维护单-供应商数据集-联系人集
                    List<SumoSumoContactSetbEntity> contactSetList = selectByPid(supplierSet.getId(), SumoSumoContactSetbEntity.class);
                    if (null != contactSetList && contactSetList.size() > 0) {
                        List<SumoContactSetDTO> contactSetDTOList = JSONArray.parseArray(JSON.toJSONString(contactSetList), SumoContactSetDTO.class);
                        sumoSupplierSetDTO.setSumoContactSet(contactSetDTOList);
                    }

                    // 6. 查询 供应商维护单-供应商数据集-附件资料集
                    List<SumoSumoAttachSetbEntity> attachSetList = selectByPid(supplierSet.getId(), SumoSumoAttachSetbEntity.class);
                    if (null != attachSetList && attachSetList.size() > 0) {
                        List<SumoAttachSetDTO> attachSetDTOList = JSONArray.parseArray(JSON.toJSONString(attachSetList), SumoAttachSetDTO.class);
                        sumoSupplierSetDTO.setSumoAttachSet(attachSetDTOList);
                    }

                    // 7. 查询 供应商维护单-供应商数据集-服务团队集
                    List<SumoSumoServtSetbEntity> servtSetList = selectByPid(supplierSet.getId(), SumoSumoServtSetbEntity.class);
                    if (null != servtSetList && servtSetList.size() > 0) {
                        List<SumoServtSetDTO> servtSetDTOList = JSONArray.parseArray(JSON.toJSONString(servtSetList), SumoServtSetDTO.class);
                        if (!ListUtils.isNullOrEmpty(servtSetDTOList)) {
                            for (SumoServtSetDTO servtSetDTO : servtSetDTOList) {
                                // 根据员工id到员工类查询
                                String employeeId = servtSetDTO.getSumoStemp();
                                if (!StringUtil.isNullOrEmpty(employeeId)) {
                                    EmployeeEntity employeeEntity = ormService.load(EmployeeEntity.class, employeeId);
                                    if (null != employeeEntity) {
                                        // 姓名
                                        servtSetDTO.setSumoStempName(employeeEntity.getRemp_name());

                                        // 根据部门id到部门类查询
                                        String deptId = employeeEntity.getRemp_dept();
                                        if (!StringUtil.isNullOrEmpty(deptId)) {
                                            DepttreeEntity depttreeEntity = ormService.load(DepttreeEntity.class, deptId);
                                            if (null != depttreeEntity) {
                                                // 部门
                                                servtSetDTO.setSumoDeptName(depttreeEntity.getMdep_name());
                                            }

                                            // 根据岗位id到岗位类查询
                                            String postId = employeeEntity.getRemp_post();
                                            JobpositionEntity jobpositionEntity = ormService.load(JobpositionEntity.class, postId);
                                            if (null != jobpositionEntity) {
                                                // 岗位
                                                servtSetDTO.setSumoPostName(jobpositionEntity.getRpos_name());
                                            }

                                            // 手机号码
                                            servtSetDTO.setSumoTel(employeeEntity.getRemp_tel());

                                            // TODO 办公电话，EDM暂时没有该属性，后续处理
                                        }
                                    }
                                }
                            }
                        }
                        sumoSupplierSetDTO.setSumoServtSet(servtSetDTOList);
                    }

                    // 8. 查询 供应商维护单-供应商数据集-账户管理集
                    List<SumoSumoAccountSetbEntity> accountSetList = selectByPid(supplierSet.getId(), SumoSumoAccountSetbEntity.class);
                    if (null != accountSetList && accountSetList.size() > 0) {
                        List<SumoAccountSetDTO> accountSetDTOList = JSONArray.parseArray(JSON.toJSONString(accountSetList), SumoAccountSetDTO.class);
                        sumoSupplierSetDTO.setSumoAccountSet(accountSetDTOList);
                    }

                    // 9. 查询 供应商维护单-供应商数据集-交货地址集
                    List<SumoSumoDeliSetbEntity> deliSetList = selectByPid(supplierSet.getId(), SumoSumoDeliSetbEntity.class);
                    if (null != deliSetList && deliSetList.size() > 0) {
                        List<SumoDeliSetDTO> deliDTOList = JSONArray.parseArray(JSON.toJSONString(deliSetList), SumoDeliSetDTO.class);
                        sumoSupplierSetDTO.setSumoDeliSet(deliDTOList);
                    }
                    // 这里放最后，保证全部属性以及添加完整
                    supplierDTO.setSumoSupplierSet(sumoSupplierSetDTO);
                }
            }
            result.setData(supplierDTO);
        } catch (Exception e) {
            logger.error("供应商维护单加载方法出现错误....", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result save(SupplierMaintOrderDTO supplierMaintOrderDTO) {
        logger.info("供应商维护单保存Service启用......");
        // 定义返回值
        Result result = new Result();

        // 获取当前session信息
        CurrentSessionEntity currentSessionEntity = bizFormService.getCurrentSessionInfo();

        // 保存必须要先进行登录
        if (null == currentSessionEntity) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("请先登录系统再进行相关操作");
            return result;
        }

        // 单据单号
        String ordeNbr = null;

        // 唯一性校验
//        String message = checkUnique(supplierMaintOrderDTO);
//        if (!message.equals("")) {
//            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
//            result.setErrMsg(message);
//            return result;
//        }

        // DTO转Entity
        SuppliermaintorderEntity suppliermaintorderEntity = JSONObject.parseObject(JSON.toJSONString(supplierMaintOrderDTO), SuppliermaintorderEntity.class);

        // 设置 企业对象
        suppliermaintorderEntity.setEdmd_ente(currentSessionEntity.getEnterpriseId());
        // 设置 对象类
        suppliermaintorderEntity.setEdmd_class(SupplierMaintOrderConstants.SUPPLIERMAINTORDER);
        // 制单时间
        suppliermaintorderEntity.setOrde_date(new Date());
        // 制单人
        suppliermaintorderEntity.setOrde_adduser(currentSessionEntity.getEmployeeId());

        // 获取id，根据是否有id判断是新增还是修改
        String supplierMaintOrderId = suppliermaintorderEntity.getId();
        try {
            if (StringUtil.isNullOrEmpty(supplierMaintOrderId)) {
                // 新增
                // 判断是否 直接提交
                if (supplierMaintOrderDTO.isSubmit()) {
                    // 直接提交，生成正式单号
                    ordeNbr = commonService.getCode(NumberConstants.PREFIX_SUPPLIER_MAINT_ORDER);
                    suppliermaintorderEntity.setOrde_nbr(ordeNbr);
                } else {
                    // 否则，生成临时单号
                    ordeNbr = commonService.getCode(NumberConstants.PREFIX_PU_TEMP_ORDER);
                    suppliermaintorderEntity.setOrde_nbr(ordeNbr);
                }

                // 1. 新增 供应商维护单主表
                suppliermaintorderEntity.setCretime(new Date());
                suppliermaintorderEntity.setCreuser(currentSessionEntity.getEmployeeId());
                suppliermaintorderEntity.setOrde_status(OrderConstants.ORDE_STATUS_1);
                String mainId = ormService.insertSelective(suppliermaintorderEntity).toString();

                // 2. 新增 供应商维护单-体系认证 属性集
                List<SumoSumoSystemSetaEntity> sumoSystemSetList = suppliermaintorderEntity.getSumo_system_set();
                if (!ListUtils.isNullOrEmpty(sumoSystemSetList)) {
                    EdmUtil.setPropertyBaseEntitiesSysColumns(SuppliermaintorderEntity.class, suppliermaintorderEntity,
                            sumoSystemSetList, SQLCurdEnum.INSERT);
                    ormService.insert(sumoSystemSetList);
                }

                // 3. 新增 供应商维护单-股东数据集 属性集
                List<SumoSumoHolderSetaEntity> sumoHolderSet = suppliermaintorderEntity.getSumo_holder_set();
                if (!ListUtils.isNullOrEmpty(sumoHolderSet)) {
                    // 设置前端传来的id为null
                    sumoHolderSet.forEach(item -> item.setId(null));
                    EdmUtil.setPropertyBaseEntitiesSysColumns(SuppliermaintorderEntity.class, suppliermaintorderEntity,
                            sumoHolderSet, SQLCurdEnum.INSERT);
                    ormService.insert(sumoHolderSet);
                }

                // 4. 新增 供应商维护单-供应商数据集 属性集
                List<SumoSumoSupplierSetaEntity> sumoSupplierSetList = suppliermaintorderEntity.getSumo_supplier_set();
                if (!ListUtils.isNullOrEmpty(sumoSupplierSetList)) {
                    EdmUtil.setPropertyBaseEntitiesSysColumns(SuppliermaintorderEntity.class, suppliermaintorderEntity,
                            sumoSupplierSetList, SQLCurdEnum.INSERT);
                    // 返回供应商数据集id
                    String sumoSumoSupplierSetId = ormService.insertSelective(sumoSupplierSetList.get(0)).toString();

                    SumoSumoSupplierSetaEntity sumoSupplierSet = sumoSupplierSetList.get(0);
                    sumoSupplierSet.setId(sumoSumoSupplierSetId);

                    // 5. 新增 供应商维护单-供应商数据集-联系人
                    List<SumoSumoContactSetbEntity> sumoContactSet = sumoSupplierSet.getSumo_contact_set();
                    if (!ListUtils.isNullOrEmpty(sumoContactSet)) {
                        // 设置前端传来的id为null
                        sumoContactSet.forEach(item -> item.setId(null));
                        EdmUtil.setPropertyBaseEntitiesSysColumns(SumoSumoSupplierSetaEntity.class, sumoSupplierSet,
                                sumoContactSet, SQLCurdEnum.INSERT);
                        ormService.insert(sumoContactSet);
                    }

                    // 6. 新增 供应商维护单-供应商数据集-附件资料
                    List<SumoSumoAttachSetbEntity> sumoAttachSet = sumoSupplierSet.getSumo_attach_set();
                    if (!ListUtils.isNullOrEmpty(sumoAttachSet)) {
                        // 设置前端传来的id为null
                        sumoAttachSet.forEach(item -> item.setId(null));
                        EdmUtil.setPropertyBaseEntitiesSysColumns(SumoSumoSupplierSetaEntity.class, sumoSupplierSet,
                                sumoAttachSet, SQLCurdEnum.INSERT);
                        ormService.insert(sumoAttachSet);
                    }

                    // 7. 新增 供应商维护单-供应商数据集-服务团队
                    List<SumoSumoServtSetbEntity> sumoServtSet = sumoSupplierSet.getSumo_servt_set();
                    if (!ListUtils.isNullOrEmpty(sumoServtSet)) {
                        // 设置前端传来的id为null
                        sumoServtSet.forEach(item -> item.setId(null));
                        EdmUtil.setPropertyBaseEntitiesSysColumns(SumoSumoSupplierSetaEntity.class, sumoSupplierSet,
                                sumoServtSet, SQLCurdEnum.INSERT);
                        ormService.insert(sumoServtSet);
                    }

                    // 8. 新增 供应商维护单-供应商数据集-账户管理
                    List<SumoSumoAccountSetbEntity> sumoAccountSet = sumoSupplierSet.getSumo_account_set();
                    if (!ListUtils.isNullOrEmpty(sumoAccountSet)) {
                        // 设置前端传来的id为null
                        sumoAccountSet.forEach(item -> item.setId(null));
                        EdmUtil.setPropertyBaseEntitiesSysColumns(SumoSumoSupplierSetaEntity.class, sumoSupplierSet,
                                sumoAccountSet, SQLCurdEnum.INSERT);
                        ormService.insert(sumoAccountSet);
                    }

                    // 9. 新增 供应商维护单-供应商数据集-交货管理
                    List<SumoSumoDeliSetbEntity> sumoDeliSet = sumoSupplierSet.getSumo_deli_set();
                    if (!ListUtils.isNullOrEmpty(sumoDeliSet)) {
                        // 设置前端传来的id为null
                        sumoDeliSet.forEach(item -> item.setId(null));
                        EdmUtil.setPropertyBaseEntitiesSysColumns(SumoSumoSupplierSetaEntity.class, sumoSupplierSet,
                                sumoDeliSet, SQLCurdEnum.INSERT);
                        ormService.insert(sumoDeliSet);
                    }
                }
                // 封装返回值
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", mainId);
                jsonObject.put("ordeNbr", ordeNbr);
                result.setData(jsonObject);
            } else {
                // 更新
                // 判断是否 直接提交
                if (supplierMaintOrderDTO.isSubmit()) {
                    // 直接提交，生成正式单号
                    ordeNbr = commonService.getCode(NumberConstants.PREFIX_SUPPLIER_MAINT_ORDER);
                    suppliermaintorderEntity.setOrde_nbr(ordeNbr);
                } else {
                    // 这里如果修改临时单，就不要重新生成临时单号了
                    ordeNbr = supplierMaintOrderDTO.getOrdeNbr();
                    suppliermaintorderEntity.setOrde_nbr(ordeNbr);
                }
                // 1. 更新 供应商维护单主表
                suppliermaintorderEntity.setModtime(new Date());
                suppliermaintorderEntity.setModuser(currentSessionEntity.getEmployeeId());
                ormService.update(suppliermaintorderEntity);

                // 2. 先删除 供应商维护单-体系认证属性集，再添加
                deleteByPid(supplierMaintOrderId, SumoSumoSystemSetaEntity.class);
                List<SumoSumoSystemSetaEntity> sumoSystemSet = suppliermaintorderEntity.getSumo_system_set();
                if (!ListUtils.isNullOrEmpty(sumoSystemSet)) {
                    EdmUtil.setPropertyBaseEntitiesSysColumns(SuppliermaintorderEntity.class, suppliermaintorderEntity,
                            sumoSystemSet, SQLCurdEnum.INSERT);
                    ormService.insert(sumoSystemSet);
                }

                // 3. 先删除 供应商维护单-股东数据集属性集，再添加
                deleteByPid(supplierMaintOrderId, SumoSumoHolderSetaEntity.class);
                List<SumoSumoHolderSetaEntity> sumoHolderSet = suppliermaintorderEntity.getSumo_holder_set();
                if (!ListUtils.isNullOrEmpty(sumoHolderSet)) {
                    // 设置前端传来的id为null
                    sumoHolderSet.forEach(item -> item.setId(null));
                    EdmUtil.setPropertyBaseEntitiesSysColumns(SuppliermaintorderEntity.class, suppliermaintorderEntity,
                            sumoHolderSet, SQLCurdEnum.INSERT);
                    ormService.insert(sumoHolderSet);
                }

                // 4. 先删除 供应商维护单-供应商数据集属性集，再添加
                OrmParam sumoSupplierSetParam = new OrmParam();
                sumoSupplierSetParam.setWhereExp(sumoSupplierSetParam.getEqualXML(NodeConstant.PID, supplierMaintOrderId));
                List<SumoSumoSupplierSetaEntity> sumoSupplierSetList = ormService.selectBeanList(SumoSumoSupplierSetaEntity.class, sumoSupplierSetParam);
                if (!ListUtils.isNullOrEmpty(sumoSupplierSetList)) {
                    String sumoSupplierSetOldId = sumoSupplierSetList.get(0).getId();

                    deleteByPid(supplierMaintOrderId, SumoSumoSupplierSetaEntity.class);
                    List<SumoSumoSupplierSetaEntity> sumoSupplierSet = suppliermaintorderEntity.getSumo_supplier_set();
                    if (!ListUtils.isNullOrEmpty(sumoSupplierSet)) {
                        SumoSumoSupplierSetaEntity sumoSumoSupplierSetaEntity = sumoSupplierSet.get(0);
                        EdmUtil.setPropertyBaseEntitiesSysColumns(SumoSumoSupplierSetaEntity.class, suppliermaintorderEntity,
                                sumoSupplierSet, SQLCurdEnum.INSERT);
                        String sumoSupplierSetId = ormService.insertSelective(sumoSumoSupplierSetaEntity).toString();
                        sumoSumoSupplierSetaEntity.setId(sumoSupplierSetId);

                        // 5. 先删除 供应商维护单-供应商数据集-联系人，再添加
                        deleteByPid(sumoSupplierSetOldId, SumoSumoContactSetbEntity.class);
                        List<SumoSumoContactSetbEntity> sumoContactSet = sumoSumoSupplierSetaEntity.getSumo_contact_set();
                        if (!ListUtils.isNullOrEmpty(sumoContactSet)) {
                            // 设置前端传来的id为null
                            sumoContactSet.forEach(item -> item.setId(null));
                            EdmUtil.setPropertyBaseEntitiesSysColumns(SumoSumoSupplierSetaEntity.class, sumoSumoSupplierSetaEntity,
                                    sumoContactSet, SQLCurdEnum.INSERT);
                            ormService.insert(sumoContactSet);
                        }

                        // 6.  先删除 供应商维护单-供应商数据集-附件资料，再添加
                        deleteByPid(sumoSupplierSetOldId, SumoSumoAttachSetbEntity.class);
                        List<SumoSumoAttachSetbEntity> sumoAttachSet = sumoSumoSupplierSetaEntity.getSumo_attach_set();
                        if (!ListUtils.isNullOrEmpty(sumoAttachSet)) {
                            // 设置前端传来的id为null
                            sumoAttachSet.forEach(item -> item.setId(null));
                            EdmUtil.setPropertyBaseEntitiesSysColumns(SumoSumoSupplierSetaEntity.class, sumoSumoSupplierSetaEntity,
                                    sumoAttachSet, SQLCurdEnum.INSERT);
                            ormService.insert(sumoAttachSet);
                        }

                        // 7. 先删除 供应商维护单-供应商数据集-服务团队，再添加
                        deleteByPid(sumoSupplierSetOldId, SumoSumoServtSetbEntity.class);
                        List<SumoSumoServtSetbEntity> sumoServtSet = sumoSumoSupplierSetaEntity.getSumo_servt_set();
                        if (!ListUtils.isNullOrEmpty(sumoServtSet)) {
                            // 设置前端传来的id为null
                            sumoServtSet.forEach(item -> item.setId(null));
                            EdmUtil.setPropertyBaseEntitiesSysColumns(SumoSumoSupplierSetaEntity.class, sumoSumoSupplierSetaEntity,
                                    sumoServtSet, SQLCurdEnum.INSERT);
                            ormService.insert(sumoServtSet);
                        }

                        // 8. 先删除 供应商维护单-供应商数据集-账户管理，再添加
                        deleteByPid(sumoSupplierSetOldId, SumoSumoAccountSetbEntity.class);
                        List<SumoSumoAccountSetbEntity> sumoAccountSet = sumoSumoSupplierSetaEntity.getSumo_account_set();
                        if (!ListUtils.isNullOrEmpty(sumoAccountSet)) {
                            // 设置前端传来的id为null
                            sumoAccountSet.forEach(item -> item.setId(null));
                            EdmUtil.setPropertyBaseEntitiesSysColumns(SumoSumoSupplierSetaEntity.class, sumoSumoSupplierSetaEntity,
                                    sumoAccountSet, SQLCurdEnum.INSERT);
                            ormService.insert(sumoAccountSet);
                        }

                        // 9. 先删除 供应商维护单-供应商数据集-交货管理，再添加
                        deleteByPid(sumoSupplierSetOldId, SumoSumoAccountSetbEntity.class);
                        List<SumoSumoDeliSetbEntity> sumoDeliSet = sumoSumoSupplierSetaEntity.getSumo_deli_set();
                        if (!ListUtils.isNullOrEmpty(sumoDeliSet)) {
                            // 设置前端传来的id为null
                            sumoDeliSet.forEach(item -> item.setId(null));
                            EdmUtil.setPropertyBaseEntitiesSysColumns(SumoSumoSupplierSetaEntity.class, sumoSumoSupplierSetaEntity,
                                    sumoDeliSet, SQLCurdEnum.INSERT);
                            ormService.insert(sumoDeliSet);
                        }
                    }
                }
                // 封装返回值
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", supplierMaintOrderDTO.getId());
                jsonObject.put("ordeNbr", ordeNbr);
                result.setData(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result delete(String id) {
        logger.info("供应商维护单删除Service启用,正在删除......" + id);
        Result result = new Result();
        try {
            // 1. 删除 供应商维护单主表
            int renInt = ormService.delete(SuppliermaintorderEntity.class, id);

            // 2. 删除 供应商维护单-体系认证 属性集
            deleteByPid(id, SumoSumoSystemSetaEntity.class);

            // 3. 删除 供应商维护单-股东数据集 属性集
            deleteByPid(id, SumoSumoHolderSetaEntity.class);

            // 获取供应商维护单-供应商数据集 (通过pid)
            OrmParam supplierSetSearchParam = new OrmParam();
            supplierSetSearchParam.setWhereExp(supplierSetSearchParam.getEqualXML(NodeConstant.PID, id));
            List<SumoSumoSupplierSetaEntity> supplierSetList = ormService.selectBeanList(SumoSumoSupplierSetaEntity.class, supplierSetSearchParam);
            if (!ListUtils.isNullOrEmpty(supplierSetList)) {
                for (SumoSumoSupplierSetaEntity supplierSet : supplierSetList) {
                    // 获取供应商维护单-供应商数据集id
                    String supplierSetId = supplierSet.getId();

                    // 4. 删除 供应商维护单-供应商数据集-联系人
                    deleteByPid(supplierSetId, SumoSumoContactSetbEntity.class);

                    // 5. 删除 供应商维护单-供应商数据集-附件资料
                    deleteByPid(supplierSetId, SumoSumoAttachSetbEntity.class);

                    // 6. 删除 供应商维护单-供应商数据集-服务团队
                    deleteByPid(supplierSetId, SumoSumoServtSetbEntity.class);

                    // 7. 删除 供应商维护单-供应商数据集-账户管理
                    deleteByPid(supplierSetId, SumoSumoAccountSetbEntity.class);

                    // 8. 删除 供应商维护单-供应商数据集-交货管理
                    deleteByPid(supplierSetId, SumoSumoDeliSetbEntity.class);
                }
            }
            // 9. 删除 供应商维护单-供应商数据集 属性集 (这里修改为最后删除供应商维护单-供应商数据集 属性集，是为了删除先删除它的子表)
            deleteByPid(id, SumoSumoSupplierSetaEntity.class);
            result.setData(renInt);
        } catch (Exception e) {
            logger.error("删除供应商维护单接口发生错误:", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }

    @Override
    public Result submit(SupplierMaintOrderDTO supplierMaintOrderDTO) {
        logger.info("供应商维护单提交Service启用..........");
        Result result = new Result();

        // 设置状态位,如果为true，表示直接点提交，没有先点保存，故save方法生成正式单号，反之生成临时单号
        supplierMaintOrderDTO.setSubmit(true);

        // 1.保存供应商维护单，返回id
        Result saveResult = save(supplierMaintOrderDTO);
        JSONObject data = (JSONObject) saveResult.getData();
        // 单据id
        String supplierMaintOrderId = data.getString("id");
        // 单据号
        String ordeNbr = data.getString("ordeNbr");

        // 2. 获取单据定义对象
        String orderDefId = supplierMaintOrderDTO.getOrdeRodeObj();

        // TODO 此处是否需要添加 是否已经提交的校验， 可以参考人资-离职申请单提交方法

        // 3. 调用流程提交方法
        bizFormService.submitWorkFlow(orderDefId, supplierMaintOrderId);

        // 4. 更新单据状态,改为待审状态(2)
        updateSupplierMaintOrderStatus(supplierMaintOrderId, OrderConstants.ORDE_STATUS_2);

        // 5. 封装返回值
        JSONObject obj = new JSONObject();
        obj.put("orderId", supplierMaintOrderId);
        obj.put("ordeNbr", ordeNbr);
        result.setData(obj);

        return result;
    }

    @Override
    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result audit(JSONObject auditSet) {
        Result result = new Result();
        String auditKey = auditSet.getString(WorkFlowConstants.PARAM_AUDITKEY);
        String formState = auditSet.getString(WorkFlowConstants.PARAM_FORMSTATE);
        String actInstanceId = auditSet.getString(WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
        String opinion = NullUtils.valueOf(auditSet.getString(WorkFlowConstants.PARAM_OPINION));
        if (StringUtil.isNullOrEmpty(auditKey)) {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_AUDITKEY);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        } else if (StringUtil.isNullOrEmpty(formState)) {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_FORMSTATE);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        } else if (StringUtil.isNullOrEmpty(actInstanceId)) {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        }
        // 调用流程
        bizFormService.audit(actInstanceId, opinion, auditKey);
        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result approve(String orderInstanceId, String handlerType) {
        logger.info("供应商维护单批准通过服务开始," + orderInstanceId + "------批准方式:" + handlerType);
        Result result = new Result();
        try {
            // 根据请求方式的不同进行不同的处理逻辑
            switch (handlerType) {
                // 通过
                case WFHandlerTypeConstants.PASS: {
                    // 根据sumo_uscc统一社会信用码字段到主表伙伴类中查，如果查不到就是新增，查到就是修改
                    RelationEntity relationEntity = selectBySumoUscc(selectByInstancdId(orderInstanceId).getSumo_uscc());
                    if (null == relationEntity) {
                        // 新增
                        saveSupplier(orderInstanceId);
                    } else {
                        // 修改
                        // 注意：不允许删除主表信息，此处修改需要删除供应商下面所有的属性集表，再次添加
                        updateSupplier(orderInstanceId);
                    }
                    break;
                }
                // 撤销：修改单据状态为临时(1)
                case WFHandlerTypeConstants.REVOKE: {
                    updateSupplierMaintOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_1);
                    break;
                }
                // 退回: 修改单据状态为退回(6)
                case WFHandlerTypeConstants.RETURN_BACK: {
                    updateSupplierMaintOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_6);
                    break;
                }
                default: {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }

    @Override
    public Result checkSupplierUnique(String checkField, String value, String relaId, String supplierMaintOrderId) {
        logger.info("供应商维护单校验Service开始......");

        /**
         * 注意：
         * 1. 只需校验伙伴类以及供应商维护单
         * 2. 统一社会信用码、伙伴编号由于更新时不可编辑，故不做排除自己处理
         */
        Result result = new Result();
        String errMsg = null;
        try {
            // 1. 统一社会信用码
            if (checkField.equals(SupplierMaintOrderConstants.SUMO_USCC_CHECK)) {
                // 伙伴类id
                String relationId = null;
                // 伙伴编号
                String relaCode = null;
                // 单据id
                String orderId = null;
                // 单据单号
                String ordeNbr = null;
                // 流程定义id
                String ordeProcbj = null;

                if (StringUtil.isNullOrEmpty(relaId) && StringUtil.isNullOrEmpty(supplierMaintOrderId)) {
                    // 新增
                    // 查询 伙伴类
                    OrmParam relationParam = new OrmParam();
                    relationParam.setWhereExp(relationParam.getEqualXML(RelationConstants.RELA_USCC, value));
                    List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, relationParam);
                    if (null != relationEntityList && relationEntityList.size() > 0) {
                        relationId = relationEntityList.get(0).getId();
                        relaCode = relationEntityList.get(0).getRela_code();
                    }

                    //  查询 供应商维护单
                    OrmParam supplierParam = new OrmParam();
                    supplierParam.setWhereExp(OrmParam.and(supplierParam.getEqualXML(SupplierMaintOrderConstants.SUMO_USCC, value),
                            OrmParam.or(supplierParam.getIsNull(OrderConstants.ORDE_STATUS), supplierParam.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                    OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                    List<SuppliermaintorderEntity> suppliermaintEntityList = ormService.selectBeanList(SuppliermaintorderEntity.class, supplierParam);
                    if (null != suppliermaintEntityList && suppliermaintEntityList.size() > 0) {
                        orderId = suppliermaintEntityList.get(0).getId();
                        ordeNbr = suppliermaintEntityList.get(0).getOrde_nbr();
                        ordeProcbj = suppliermaintEntityList.get(0).getOrde_procobj();
                    }

                    /**
                     * 分三种情况:
                     * 1. 只存在伙伴 返回伙伴id，伙伴编号
                     * 2. 只存在单据 返回单据id，单据单号，流程定义id
                     * 3. 同时存在 返回伙伴id，伙伴编号，单据id，单据单号，流程定义id
                     * 4. 没有重复数据
                     */
                    JSONObject jsonObject = new JSONObject();

                    boolean onlyRelation = (null != relationEntityList && relationEntityList.size() > 0) && (null == suppliermaintEntityList || suppliermaintEntityList.size() == 0);
                    boolean onlyMaint = (null == relationEntityList || relationEntityList.size() == 0) && (null != suppliermaintEntityList && suppliermaintEntityList.size() > 0);
                    boolean bothHave = (null != relationEntityList && relationEntityList.size() > 0) && (null != suppliermaintEntityList && suppliermaintEntityList.size() > 0);

                    if (onlyRelation) {
                        jsonObject.put("flag", "1");
                        jsonObject.put("relationId", relationId);
                        jsonObject.put("relaCode", relaCode);

                    } else if (onlyMaint) {
                        jsonObject.put("flag", "2");
                        jsonObject.put("orderId", orderId);
                        jsonObject.put("ordeNbr", ordeNbr);
                        jsonObject.put("ordeProcbj", ordeProcbj);

                    } else if (bothHave) {
                        jsonObject.put("flag", "3");
                        jsonObject.put("relationId", relationId);
                        jsonObject.put("relaCode", relaCode);
                        jsonObject.put("orderId", orderId);
                        jsonObject.put("ordeNbr", ordeNbr);
                        jsonObject.put("ordeProcbj", ordeProcbj);
                    } else {
                        jsonObject.put("flag", "4");
                    }

                    result.setData(jsonObject);
                } else {
                    // 更新
                    // 查询 伙伴类
                    OrmParam relationParam = new OrmParam();
                    relationParam.setWhereExp(OrmParam.and(
                            relationParam.getEqualXML(RelationConstants.RELA_USCC, value),
                            relationParam.getUnequalXML(NodeConstant.ID, relaId)
                    ));
                    List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, relationParam);
                    if (null != relationEntityList && relationEntityList.size() > 0) {
                        relationId = relationEntityList.get(0).getId();
                        relaCode = relationEntityList.get(0).getRela_code();
                    }

                    //  查询 供应商维护单
                    OrmParam supplierParam = new OrmParam();
                    supplierParam.setWhereExp(OrmParam.and(supplierParam.getEqualXML(SupplierMaintOrderConstants.SUMO_USCC, value),
                            supplierParam.getUnequalXML(NodeConstant.ID, supplierMaintOrderId),
                            OrmParam.or(supplierParam.getIsNull(OrderConstants.ORDE_STATUS), supplierParam.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                    OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                    List<SuppliermaintorderEntity> suppliermaintEntityList = ormService.selectBeanList(SuppliermaintorderEntity.class, supplierParam);
                    if (null != suppliermaintEntityList && suppliermaintEntityList.size() > 0) {
                        orderId = suppliermaintEntityList.get(0).getId();
                        ordeNbr = suppliermaintEntityList.get(0).getOrde_nbr();
                        ordeProcbj = suppliermaintEntityList.get(0).getOrde_procobj();
                    }

                    /**
                     * 分三种情况:
                     * 1. 只存在伙伴 返回伙伴id，伙伴编号
                     * 2. 只存在单据 返回单据id，单据单号，流程定义id
                     * 3. 同时存在 返回伙伴id，伙伴编号，单据id，单据单号，流程定义id
                     * 4. 没有重复数据
                     */
                    JSONObject jsonObject = new JSONObject();

                    boolean onlyRelation = (null != relationEntityList && relationEntityList.size() > 0) && (null == suppliermaintEntityList || suppliermaintEntityList.size() == 0);
                    boolean onlyMaint = (null == relationEntityList || relationEntityList.size() == 0) && (null != suppliermaintEntityList && suppliermaintEntityList.size() > 0);
                    boolean bothHave = (null != relationEntityList && relationEntityList.size() > 0) && (null != suppliermaintEntityList && suppliermaintEntityList.size() > 0);

                    if (onlyRelation) {
                        jsonObject.put("flag", "1");
                        jsonObject.put("relationId", relationId);
                        jsonObject.put("relaCode", relaCode);

                    } else if (onlyMaint) {
                        jsonObject.put("flag", "2");
                        jsonObject.put("orderId", orderId);
                        jsonObject.put("ordeNbr", ordeNbr);
                        jsonObject.put("ordeProcbj", ordeProcbj);

                    } else if (bothHave) {
                        jsonObject.put("flag", "3");
                        jsonObject.put("relationId", relationId);
                        jsonObject.put("relaCode", relaCode);
                        jsonObject.put("orderId", orderId);
                        jsonObject.put("ordeNbr", ordeNbr);
                        jsonObject.put("ordeProcbj", ordeProcbj);
                    } else {
                        jsonObject.put("flag", "4");
                    }
                    result.setData(jsonObject);
                }
                // 2. 伙伴编号
            } else if (checkField.equals(SupplierMaintOrderConstants.SUMO_CODE_CHECK)) {
                // 根据是否有id判断是新增校验还是更新校验
                if (StringUtil.isNullOrEmpty(relaId) && StringUtil.isNullOrEmpty(supplierMaintOrderId)) {
                    // 新增校验
                    boolean isRepet = false;

                    // 查询 伙伴类
                    OrmParam relationParam = new OrmParam();
                    relationParam.setWhereExp(relationParam.getEqualXML(RelationConstants.RELA_CODE, value));
                    List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, relationParam);
                    if (null != relationEntityList && relationEntityList.size() > 0) {
                        isRepet = true;
                    }

                    //  查询 供应商维护单
                    OrmParam supplierParam = new OrmParam();
                    supplierParam.setWhereExp(OrmParam.and(supplierParam.getEqualXML(SupplierMaintOrderConstants.SUMO_CODE, value),
                            OrmParam.or(supplierParam.getIsNull(OrderConstants.ORDE_STATUS), supplierParam.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                    OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                    List<SuppliermaintorderEntity> suppliermaintEntityList = ormService.selectBeanList(SuppliermaintorderEntity.class, supplierParam);
                    if (null != suppliermaintEntityList && suppliermaintEntityList.size() > 0) {
                        isRepet = true;
                    }

                    if (isRepet) {
                        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                        errMsg = "伙伴编号重复！";
                        result.setErrMsg(errMsg);
                    }
                } else {
                    // 更新校验
                    boolean isRepet = false;

                    // 查询 伙伴类
                    OrmParam relationParam = new OrmParam();
                    relationParam.setWhereExp(OrmParam.and(relationParam.getEqualXML(RelationConstants.RELA_CODE, value),
                            relationParam.getUnequalXML(NodeConstant.ID, relaId)));
                    List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, relationParam);
                    if (null != relationEntityList && relationEntityList.size() > 0) {
                        isRepet = true;
                    }

                    //  查询 供应商维护单
                    OrmParam supplierParam = new OrmParam();
                    supplierParam.setWhereExp(OrmParam.and(supplierParam.getEqualXML(SupplierMaintOrderConstants.SUMO_CODE, value),
                            supplierParam.getUnequalXML(NodeConstant.ID, supplierMaintOrderId),
                            OrmParam.or(supplierParam.getIsNull(OrderConstants.ORDE_STATUS), supplierParam.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                    OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                    List<SuppliermaintorderEntity> suppliermaintEntityList = ormService.selectBeanList(SuppliermaintorderEntity.class, supplierParam);
                    if (null != suppliermaintEntityList && suppliermaintEntityList.size() > 0) {
                        isRepet = true;
                    }

                    if (isRepet) {
                        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                        errMsg = "伙伴编号重复！";
                        result.setErrMsg(errMsg);
                    }

                }
                // 3. 伙伴简称
            } else if (checkField.equals(SupplierMaintOrderConstants.SUMO_SHORT_NAME_CHECK)) {
                // 根据是否有id判断是新增校验还是更新校验
                if (StringUtil.isNullOrEmpty(relaId) && StringUtil.isNullOrEmpty(supplierMaintOrderId)) {
                    // 新增校验
                    boolean isRepet = false;

                    // 查询 伙伴类
                    OrmParam relationParam = new OrmParam();
                    relationParam.setWhereExp(relationParam.getEqualXML(RelationConstants.RELA_SHORT_NAME, value));
                    List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, relationParam);
                    if (null != relationEntityList && relationEntityList.size() > 0) {
                        isRepet = true;
                    }

                    //  查询 供应商维护单
                    OrmParam supplierParam = new OrmParam();
                    supplierParam.setWhereExp(OrmParam.and(supplierParam.getEqualXML(SupplierMaintOrderConstants.SUMO_SHORT_NAME, value),
                            OrmParam.or(supplierParam.getIsNull(OrderConstants.ORDE_STATUS), supplierParam.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                    OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                    List<SuppliermaintorderEntity> suppliermaintEntityList = ormService.selectBeanList(SuppliermaintorderEntity.class, supplierParam);
                    if (null != suppliermaintEntityList && suppliermaintEntityList.size() > 0) {
                        isRepet = true;
                    }

                    if (isRepet) {
                        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                        errMsg = "伙伴简称重复！";
                        result.setErrMsg(errMsg);
                    }
                } else {
                    // 更新校验
                    boolean isRepet = false;

                    // 查询 伙伴类
                    OrmParam relationParam = new OrmParam();
                    relationParam.setWhereExp(OrmParam.and(relationParam.getEqualXML(RelationConstants.RELA_SHORT_NAME, value),
                            relationParam.getUnequalXML(NodeConstant.ID, relaId)));
                    List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, relationParam);
                    if (null != relationEntityList && relationEntityList.size() > 0) {
                        isRepet = true;
                    }

                    //  查询 供应商维护单
                    OrmParam supplierParam = new OrmParam();
                    supplierParam.setWhereExp(OrmParam.and(supplierParam.getEqualXML(SupplierMaintOrderConstants.SUMO_SHORT_NAME, value),
                            supplierParam.getUnequalXML(NodeConstant.ID, supplierMaintOrderId),
                            OrmParam.or(supplierParam.getIsNull(OrderConstants.ORDE_STATUS), supplierParam.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                    OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                    List<SuppliermaintorderEntity> suppliermaintEntityList = ormService.selectBeanList(SuppliermaintorderEntity.class, supplierParam);
                    if (null != suppliermaintEntityList && suppliermaintEntityList.size() > 0) {
                        isRepet = true;
                    }

                    if (isRepet) {
                        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                        errMsg = "伙伴简称重复！";
                        result.setErrMsg(errMsg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, "供应商维护单唯一性校验出现错误:" + e.getMessage());
        }
        return result;
    }

    @Override
    public Result getSupplierMaintOrderByCode(String sumoCode) {
        Result result = new Result();

        try {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML(SuppliermaintorderProperty.SUMO_CODE, sumoCode));

            List<SuppliermaintorderEntity> suppliermaintorderEntityList = ormService.selectBeanList(SuppliermaintorderEntity.class, ormParam);
            if (!ListUtils.isNullOrEmpty(suppliermaintorderEntityList)) {
                SuppliermaintorderEntity suppliermaintorderEntity = suppliermaintorderEntityList.get(0);
                JSONObject jsonObject = (JSONObject) JSON.toJSON(suppliermaintorderEntity);
                // 下划线转驼峰
                JsonUtils.underLine2Camel(jsonObject);
                result.setData(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, "根据伙伴编码查询供应商维护单失败:" + e.getMessage());
        }
        return result;
    }

    @Override
    public Result getRelationByCode(String sumoCode) {
        Result result = new Result();

        try {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML(RelationProperty.RELA_CODE, sumoCode));

            List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, ormParam);
            if (!ListUtils.isNullOrEmpty(relationEntityList)) {
                RelationEntity relationEntity = relationEntityList.get(0);
                JSONObject jsonObject = (JSONObject) JSON.toJSON(relationEntity);
                // 下划线转驼峰
                JsonUtils.underLine2Camel(jsonObject);
                result.setData(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, "根据伙伴编码查询伙伴类失败:" + e.getMessage());
        }
        return result;
    }

    /**
     * 供应商列表查询
     * @param relaCode
     * @param relaShortName
     * @param relaStatus
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Result query(String relaCode, String relaShortName, String relaStatus, int pageNum, int pageSize) {
        Result result = new Result();
        try {
            StringBuffer baseSql = new StringBuffer("select p.rela_code,p.rela_short_name,c.id,c.pid,c.rela_stat_supp,p.rela_remark,c.rela_servdept_supp,c.moduser,c.modtime ");
            baseSql.append(" from rela_rela_supplier_seta c left join  relation p  on p.id = c.pid where p.is_del=0 and c.is_del=0 ");

            if(!StringUtil.isNullOrEmpty(relaCode)){
                baseSql.append(" and p.rela_code like '%").append(relaCode).append("%' ");
            }
            if(!StringUtil.isNullOrEmpty(relaShortName)){
                baseSql.append(" and p.rela_short_name like '%").append(relaShortName).append("%' ");
            }
            if(!StringUtil.isNullOrEmpty(relaStatus)){
                baseSql.append(" and c.rela_stat_supp = '").append(relaStatus).append("' ");
            }

            pageNum = pageNum<1?1:pageNum;

            String limit = " ORDER BY c.cretime desc  LIMIT " + (pageNum - 1) * pageSize + "," + pageSize;

            String querySql = baseSql.append(limit).toString();

            List<Map<String, Object>> mapList = ormService.getDataBySql(querySql);

            if(mapList.isEmpty())
            {
                return this.emptyPageQueryResult(pageNum , pageSize);
            }

            List<Map<String, Object>> count = ormService.getDataBySql(baseSql.toString());

            //组装返回的数据
            JSONObject obj = new JSONObject();
            obj.put("pageNum", pageNum);
            obj.put("pageSize", pageSize);
            obj.put("total", count.size());
            obj.put("list", getSupplierMaintInfo(mapList));
            result.setData(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return getResult(Result.RECODE_ERROR , "查询供应商维护单失败，" + e.getMessage());
        }
        return result;
    }

    private JSONArray getSupplierMaintInfo(List<Map<String , Object>> mapList){
        JSONArray relationArray = new JSONArray();
        for (Map<String, Object> map : mapList) {
            JSONObject jsonObject = new JSONObject();
            //伙伴代码
            jsonObject.put("relaCode" , NullUtils.valueOf(map.get(RelationProperty.RELA_CODE)));
            //伙伴名称
            jsonObject.put("relaShortName" , NullUtils.valueOf(map.get(RelationProperty.RELA_SHORT_NAME)));
            //备注
            jsonObject.put("relaRemark" , NullUtils.valueOf(map.get(RelationProperty.RELA_REMARK)));
            //状态
            jsonObject.put("relaStatus" , NullUtils.valueOf(map.get(RelaRelaSupplierSetaProperty.RELA_STAT_SUPP)));
            //维护时间
            jsonObject.put("modtime" , NullUtils.valueOf(map.get(EdmSysColumn.MODTIME)));
            //供应商ID
            String supplierMaintId = NullUtils.valueOf(map.get(EdmSysColumn.ID));
            //伙伴ID
            String relaId = NullUtils.valueOf(map.get(EdmSysColumn.PID));
            jsonObject.put("id" , relaId);
            //服务部门ID
            String relaServdeptSupp = NullUtils.valueOf(map.get(RelaRelaSupplierSetaProperty.RELA_SERVDEPT_SUPP));
            jsonObject.put("relaServdept" , relaServdeptSupp);
            //维护人ID
            String moduser = NullUtils.valueOf(map.get(EdmSysColumn.MODUSER));
            jsonObject.put("moduser" , moduser);

            HashMap conditionMap = new HashMap();
            conditionMap.put(EdmSysColumn.ID , relaServdeptSupp);
            //服务部门名称
            String servDeptName = queryResultByConditions(DepttreeEntity.class , conditionMap, DepttreeProperty.MDEP_NAME);
            jsonObject.put("relaServdeptName" , servDeptName);
            //维护人名称
            conditionMap.put(EdmSysColumn.ID , moduser);
            String moduserName = queryResultByConditions(EmployeeEntity.class , conditionMap, EmployeeProperty.REMP_NAME);
            jsonObject.put("moduserName" , moduserName);
            /**服务人员名称**/
            //1、先查询服务人员ID
            conditionMap.remove(EdmSysColumn.ID);
            conditionMap.put(EdmSysColumn.PID , supplierMaintId);
            conditionMap.put(RelaRelaServtSuppSetbProperty.RELA_STTYPE_SUPP , RelationConstants.TEAM_TYPE_BUY);
            String ServEmpId = queryResultByConditions(RelaRelaServtSuppSetbEntity.class , conditionMap, RelaRelaServtSuppSetbProperty.RELA_STEMP_SUPP);
            //再根据ID查询名称
            conditionMap.clear();
            conditionMap.put(EdmSysColumn.ID , ServEmpId);
            String servEmpName = queryResultByConditions(EmployeeEntity.class , conditionMap, EmployeeProperty.REMP_NAME);
            jsonObject.put("relaServempName" , servEmpName);
            relationArray.add(jsonObject);
        }
        return relationArray;
    }

    /**
     * 根据条件查询名称
     * @param c
     * @param conditions
     * @param columnName
     * @return
     */
    @Override
    public String queryResultByConditions(Class c , HashMap<String , Object> conditions , String columnName){
        String name = "";
        try {
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(columnName);
            List<String> conditionList = new ArrayList<>();
            for(Map.Entry<String , Object> entry : conditions.entrySet()){
                conditionList.add(ormParam.getEqualXML(entry.getKey() , entry.getValue()));
            }
            ormParam.setWhereExp(OrmParam.and(conditionList));
            List<Map<String , Object>> mapList = ormService.selectMapList(c , ormParam);
            if(!mapList.isEmpty()){
                name = (String)mapList.get(0).get(columnName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return name;
        }
        return name;
    }

    @Override
    public Result getResult(int retCode,String errMsg){
        Result result = new Result();
        result.setRetCode(retCode);
        result.setErrMsg(errMsg);
        return result;
    }

    /**
     * 封装查询单据列表无数据的情况下返回的结果
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Result emptyPageQueryResult(int pageNum , int pageSize){
        Result result = new Result();
        JSONObject obj = new JSONObject();
        obj.put("pageNum", pageNum);
        obj.put("pageSize", pageSize);
        obj.put("total", 0);
        obj.put("list", new ArrayList<>());
        result.setData(obj);
        return result;
    }

    /**
     * 更新供应商维护单单据状态
     *
     * @param orderInstanceId 单据id
     * @param orderStatus     状态码
     */
    private void updateSupplierMaintOrderStatus(String orderInstanceId, String orderStatus) {

        // 1. 准备数据
        SupplierMaintOrderDTO supplierMaintOrderDTO = new SupplierMaintOrderDTO();
        supplierMaintOrderDTO.setId(orderInstanceId);
        supplierMaintOrderDTO.setOrdeStatus(orderStatus);

        // DTO转Entity
        SuppliermaintorderEntity suppliermaintorderEntity = JSONObject.parseObject(JSON.toJSONString(supplierMaintOrderDTO), SuppliermaintorderEntity.class);

        // 2. 更新表
        // 根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity currentSessionEntity = bizFormService.getCurrentSessionInfo();

        // 制单时间
        suppliermaintorderEntity.setOrde_date(new Date());
        // 所属企业对象id
        suppliermaintorderEntity.setEdmd_ente(currentSessionEntity.getEnterpriseId());
        // 修改人
        suppliermaintorderEntity.setModuser(currentSessionEntity.getEmployeeId());

        try {
            ormService.updateSelective(suppliermaintorderEntity);
        } catch (Exception e) {
            logger.error("更新供应商维护单状态出现异常", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, "更新供应商维护单状态出现异常：" + e.getMessage());
        }
    }

    /**
     * 根据单据id查询供应商维护单
     *
     * @param instanceId
     * @return
     */
    private SuppliermaintorderEntity selectByInstancdId(String instanceId) {

        SuppliermaintorderEntity suppliermaintorderEntity = null;
        try {
            suppliermaintorderEntity = ormService.load(SuppliermaintorderEntity.class, instanceId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return suppliermaintorderEntity;
    }

    /**
     * 根据pid查询数据
     *
     * @param pid
     * @param t
     * @param <T>
     * @return
     */
    private <T extends BaseEntity> List<T> selectByPid(String pid, Class<T> t) {

        List<T> list = new ArrayList<>();

        try {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID, pid)));

            list = ormService.selectBeanList(t, ormParam);
        } catch (Exception e) {
            logger.error("根据pid查询出现异常：", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }

        return list;
    }

    /**
     * 根据pid完成修改
     *
     * @param pid
     * @param t
     * @param <T>
     */
    private <T extends BaseEntity> void updateByPid(T t, String pid) {

        try {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, pid));

            ormService.updateSelective(t, ormParam);
        } catch (Exception e) {
            logger.error("根据pid修改出现异常：", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
    }

    /**
     * 根据统一社会信用代码查询伙伴类
     *
     * @param sumoUscc
     * @return
     */
    private RelationEntity selectBySumoUscc(String sumoUscc) {

        try {

            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML(RelationProperty.RELA_USCC, sumoUscc));

            List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, ormParam);

            if (null != relationEntityList && relationEntityList.size() > 0) {
                return relationEntityList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return null;
    }

    /**
     * 批准通过-保存供应商
     *
     * @param orderInstanceId 供应商维护单据id
     */
    private String saveSupplier(String orderInstanceId) {

        // 获取当前session信息
        CurrentSessionEntity currentSessionEntity = bizFormService.getCurrentSessionInfo();

        try {
            // 1. 根据单据id查询到维护单的数据
            SuppliermaintorderEntity suppliermaintorderEntity = selectByInstancdId(orderInstanceId);

            // 2. 封装伙伴类基本信息(主表)，并插入
            RelationEntity relationEntity = new RelationEntity();

            relationEntity.setRela_code(suppliermaintorderEntity.getSumo_code());
            relationEntity.setRela_name(suppliermaintorderEntity.getSumo_name());
            relationEntity.setRela_short_name(suppliermaintorderEntity.getSumo_short_name());
            relationEntity.setRela_remark(suppliermaintorderEntity.getSumo_remark());
            relationEntity.setRela_addrp(suppliermaintorderEntity.getSumo_addrp());
            relationEntity.setRela_addrc(suppliermaintorderEntity.getSumo_addrc());
            relationEntity.setRela_addrl(suppliermaintorderEntity.getSumo_addrl());
            relationEntity.setRela_daddr(suppliermaintorderEntity.getSumo_daddr());
            relationEntity.setRela_pcode(suppliermaintorderEntity.getSumo_pcode());
            relationEntity.setRela_tel(suppliermaintorderEntity.getSumo_tel());
            relationEntity.setRela_fax(suppliermaintorderEntity.getSumo_fax());
            relationEntity.setRela_web(suppliermaintorderEntity.getSumo_web());
            relationEntity.setRela_mprod(suppliermaintorderEntity.getSumo_mprod());
            relationEntity.setRela_character(suppliermaintorderEntity.getSumo_character());
            relationEntity.setRela_listexch(suppliermaintorderEntity.getSumo_listexch());
            relationEntity.setRela_listname(suppliermaintorderEntity.getSumo_listname());
            relationEntity.setRela_listcode(suppliermaintorderEntity.getSumo_listcode());
            relationEntity.setRela_uscc(suppliermaintorderEntity.getSumo_uscc());
            relationEntity.setRela_raddr(suppliermaintorderEntity.getSumo_raddr());
            relationEntity.setRela_rdate(suppliermaintorderEntity.getSumo_rdate());
            relationEntity.setRela_lrep(suppliermaintorderEntity.getSumo_lrep());
            relationEntity.setRela_laddr(suppliermaintorderEntity.getSumo_laddr());
            relationEntity.setRela_rcurr(suppliermaintorderEntity.getSumo_rcurr_curr());
            relationEntity.setRela_rcapital(suppliermaintorderEntity.getSumo_rcapital());

            String relationId = ormService.insertSelective(relationEntity).toString();

            // 3. 封装伙伴类-伙伴类型集，并插入
            RelaRelaTypesSetaEntity relaTypesSetEntity = new RelaRelaTypesSetaEntity();

            relaTypesSetEntity.setPid(relationId);
            relaTypesSetEntity.setClassName(RelationConstants.className);
            relaTypesSetEntity.setCreuser(currentSessionEntity.getEmployeeId());
            relaTypesSetEntity.setRela_type(RelaTypeConstants.RELATION_TYPE_SUPPLIER);

            ormService.insertSelective(relaTypesSetEntity);

            // 4. 封装伙伴类-体系认证集，并插入
            List<RelaRelaSystemSetaEntity> relaSystemSetaEntityList = new ArrayList<>();
            List<SumoSumoSystemSetaEntity> sumoSystemSetaList = selectByPid(orderInstanceId, SumoSumoSystemSetaEntity.class);
            if (!ListUtils.isNullOrEmpty(sumoSystemSetaList)) {
                for (SumoSumoSystemSetaEntity sumoSystemSet : sumoSystemSetaList) {
                    RelaRelaSystemSetaEntity relaSystemSetaEntity = new RelaRelaSystemSetaEntity();

                    relaSystemSetaEntity.setPid(relationId);
                    relaSystemSetaEntity.setClassName(RelationConstants.className);
                    relaSystemSetaEntity.setCreuser(currentSessionEntity.getEmployeeId());
                    relaSystemSetaEntity.setRela_sname(sumoSystemSet.getSumo_sname());

                    relaSystemSetaEntityList.add(relaSystemSetaEntity);
                }
            }
            ormService.insert(relaSystemSetaEntityList);

            // 5. 封装伙伴类-股东集，并插入
            List<RelaRelaHolderSetaEntity> relaHolderSetaEntityList = new ArrayList<>();
            List<SumoSumoHolderSetaEntity> sumoHolderSetParamSetaList = selectByPid(orderInstanceId, SumoSumoHolderSetaEntity.class);
            if (!ListUtils.isNullOrEmpty(sumoHolderSetParamSetaList)) {
                for (SumoSumoHolderSetaEntity sumoHolderSet : sumoHolderSetParamSetaList) {
                    RelaRelaHolderSetaEntity relaHolderSetaEntity = new RelaRelaHolderSetaEntity();

                    relaHolderSetaEntity.setPid(relationId);
                    relaHolderSetaEntity.setClassName(RelationConstants.className);
                    relaHolderSetaEntity.setCreuser(currentSessionEntity.getEmployeeId());
                    relaHolderSetaEntity.setRela_hname(sumoHolderSet.getSumo_hname());
                    relaHolderSetaEntity.setRela_hrate(sumoHolderSet.getSumo_hrate());

                    relaHolderSetaEntityList.add(relaHolderSetaEntity);
                }
            }
            ormService.insert(relaHolderSetaEntityList);

            // 6. 封装伙伴类-供应商数据集，并插入
            // 注意：伙伴类跟供应商数据集之间是一对一关系，故此处不需要批量插入
            List<SumoSumoSupplierSetaEntity> sumoSupplierSetList = selectByPid(orderInstanceId, SumoSumoSupplierSetaEntity.class);
            if (!ListUtils.isNullOrEmpty(sumoSupplierSetList)) {
                SumoSumoSupplierSetaEntity sumoSupplierSet = sumoSupplierSetList.get(0);

                RelaRelaSupplierSetaEntity relaRelaSupplierSetaEntity = new RelaRelaSupplierSetaEntity();

                relaRelaSupplierSetaEntity.setPid(relationId);
                relaRelaSupplierSetaEntity.setClassName(RelationConstants.className);
                relaRelaSupplierSetaEntity.setCreuser(currentSessionEntity.getEmployeeId());
                relaRelaSupplierSetaEntity.setRela_supptype(sumoSupplierSet.getSumo_supptype());
                relaRelaSupplierSetaEntity.setRela_stat_supp(sumoSupplierSet.getSumo_stat());
                relaRelaSupplierSetaEntity.setRela_servdept_supp(sumoSupplierSet.getSumo_servdept());
                relaRelaSupplierSetaEntity.setRela_taxrate(sumoSupplierSet.getSumo_taxrate());
                relaRelaSupplierSetaEntity.setRela_sett_ways(sumoSupplierSet.getSumo_sett_way());

                // 插入伙伴类-供应商数据集表，并返回应商数据集id
                String relaSupplierSetId = ormService.insert(relaRelaSupplierSetaEntity).toString();

                // 查询供应商维护单-供应商数据集的id
                String supplierSetId = sumoSupplierSet.getId();

                // 7. 封装伙伴类-供应商数据集-供应商联系人集,并插入
                List<RelaRelaContactSuppSetbEntity> relaContactSuppSetbEntityList = new ArrayList<>();
                List<SumoSumoContactSetbEntity> sumoContactSetList = selectByPid(supplierSetId, SumoSumoContactSetbEntity.class);
                if (!ListUtils.isNullOrEmpty(sumoContactSetList)) {
                    for (SumoSumoContactSetbEntity sumoContactSet : sumoContactSetList) {
                        RelaRelaContactSuppSetbEntity relaContactSuppSetbEntity = new RelaRelaContactSuppSetbEntity();

                        relaContactSuppSetbEntity.setPid(relaSupplierSetId);
                        relaContactSuppSetbEntity.setClassName(RelationConstants.className);
                        relaContactSuppSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                        relaContactSuppSetbEntity.setRela_conttype_supp(sumoContactSet.getSumo_conttype());
                        relaContactSuppSetbEntity.setRela_contname_supp(sumoContactSet.getSumo_contname());
                        relaContactSuppSetbEntity.setRela_contsex_supp(sumoContactSet.getSumo_contsex());
                        relaContactSuppSetbEntity.setRela_contpost_supp(sumoContactSet.getSumo_contpost());
                        relaContactSuppSetbEntity.setRela_conttel_supp(sumoContactSet.getSumo_conttel());
                        relaContactSuppSetbEntity.setRela_contemail_supp(sumoContactSet.getSumo_contemail());
                        relaContactSuppSetbEntity.setRela_contother_supp(sumoContactSet.getSumo_contother());

                        relaContactSuppSetbEntityList.add(relaContactSuppSetbEntity);
                    }
                }
                ormService.insert(relaContactSuppSetbEntityList);

                // 8. 封装伙伴类-供应商数据集-附件资料集,并插入
                List<RelaRelaAttachSuppSetbEntity> relaAttachSuppSetbEntityList = new ArrayList<>();
                List<SumoSumoAttachSetbEntity> sumoAttachSetList = selectByPid(supplierSetId, SumoSumoAttachSetbEntity.class);
                if (!ListUtils.isNullOrEmpty(sumoAttachSetList)) {
                    for (SumoSumoAttachSetbEntity sumoAttachSet : sumoAttachSetList) {
                        RelaRelaAttachSuppSetbEntity relaAttachSuppSetbEntity = new RelaRelaAttachSuppSetbEntity();

                        relaAttachSuppSetbEntity.setPid(relaSupplierSetId);
                        relaAttachSuppSetbEntity.setClassName(RelationConstants.className);
                        relaAttachSuppSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                        relaAttachSuppSetbEntity.setRela_attatype_supp(sumoAttachSet.getSumo_attatype());
                        relaAttachSuppSetbEntity.setRela_attaaddr_supp(sumoAttachSet.getSumo_attaaddr());
                        relaAttachSuppSetbEntity.setRela_attavalid_supp(sumoAttachSet.getSumo_attavalid());

                        relaAttachSuppSetbEntityList.add(relaAttachSuppSetbEntity);
                    }
                }
                ormService.insert(relaAttachSuppSetbEntityList);

                // 9. 封装伙伴类-供应商数据集-服务团队集,并插入
                List<RelaRelaServtSuppSetbEntity> relaServtSuppSetbEntityList = new ArrayList<>();
                List<SumoSumoServtSetbEntity> sumoServtSetList = selectByPid(supplierSetId, SumoSumoServtSetbEntity.class);
                if (!ListUtils.isNullOrEmpty(sumoServtSetList)) {
                    for (SumoSumoServtSetbEntity sumoServtSet : sumoServtSetList) {
                        RelaRelaServtSuppSetbEntity relaServtSuppSetbEntity = new RelaRelaServtSuppSetbEntity();

                        relaServtSuppSetbEntity.setPid(relaSupplierSetId);
                        relaServtSuppSetbEntity.setClassName(RelationConstants.className);
                        relaServtSuppSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                        relaServtSuppSetbEntity.setRela_sttype_supp(sumoServtSet.getSumo_sttype());
                        relaServtSuppSetbEntity.setRela_stemp_supp(sumoServtSet.getSumo_stemp());

                        relaServtSuppSetbEntityList.add(relaServtSuppSetbEntity);
                    }
                }
                ormService.insert(relaServtSuppSetbEntityList);

                // 10. 封装伙伴类-供应商数据集-账户管理集,并插入
                List<RelaRelaAccountSuppSetbEntity> relaAccountSuppSetbEntityList = new ArrayList<>();
                List<SumoSumoAccountSetbEntity> sumoAccountSetList = selectByPid(supplierSetId, SumoSumoAccountSetbEntity.class);
                if (!ListUtils.isNullOrEmpty(sumoAccountSetList)) {
                    for (SumoSumoAccountSetbEntity sumoSumoAccountSet : sumoAccountSetList) {
                        RelaRelaAccountSuppSetbEntity relaAccountSuppSetbEntity = new RelaRelaAccountSuppSetbEntity();

                        relaAccountSuppSetbEntity.setPid(relaSupplierSetId);
                        relaAccountSuppSetbEntity.setClassName(RelationConstants.className);
                        relaAccountSuppSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                        relaAccountSuppSetbEntity.setRela_acconame_supp(sumoSumoAccountSet.getSumo_acconame());
                        relaAccountSuppSetbEntity.setRela_accobank_supp(sumoSumoAccountSet.getSumo_accobank());
                        relaAccountSuppSetbEntity.setRela_acconum_supp(sumoSumoAccountSet.getSumo_acconum());
                        relaAccountSuppSetbEntity.setRela_accocurr_supp(sumoSumoAccountSet.getSumo_accocurr());
                        relaAccountSuppSetbEntity.setRela_accobeg_supp(sumoSumoAccountSet.getSumo_accobeg());
                        relaAccountSuppSetbEntity.setRela_accoend_supp(sumoSumoAccountSet.getSumo_accoend());
                        relaAccountSuppSetbEntity.setRela_attaacco_supp(sumoSumoAccountSet.getSumo_attaacco());

                        relaAccountSuppSetbEntityList.add(relaAccountSuppSetbEntity);
                    }
                }
                ormService.insert(relaAccountSuppSetbEntityList);

                // 11. 封装伙伴类-供应商数据集-交货地址集,并插入
                List<RelaRelaDeliSuppSetbEntity> relaDeliSuppSetbEntityList = new ArrayList<>();
                List<SumoSumoDeliSetbEntity> sumoDeliSetList = selectByPid(supplierSetId, SumoSumoDeliSetbEntity.class);
                if (!ListUtils.isNullOrEmpty(sumoDeliSetList)) {
                    for (SumoSumoDeliSetbEntity sumoDeliSet : sumoDeliSetList) {
                        RelaRelaDeliSuppSetbEntity relaDeliSuppSetbEntity = new RelaRelaDeliSuppSetbEntity();

                        relaDeliSuppSetbEntity.setPid(relaSupplierSetId);
                        relaDeliSuppSetbEntity.setClassName(RelationConstants.className);
                        relaDeliSuppSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                        relaDeliSuppSetbEntity.setRela_daname_supp(sumoDeliSet.getSumo_daname());
                        relaDeliSuppSetbEntity.setRela_daddrp_supp(sumoDeliSet.getSumo_daddrp());
                        relaDeliSuppSetbEntity.setRela_daddrc_supp(sumoDeliSet.getSumo_daddrc());
                        relaDeliSuppSetbEntity.setRela_daddrl_supp(sumoDeliSet.getSumo_daddrl());
                        relaDeliSuppSetbEntity.setRela_ddaddr_supp(sumoDeliSet.getSumo_ddaddr());
                        relaDeliSuppSetbEntity.setRela_dcontact_supp(sumoDeliSet.getSumo_dcontact());
                        relaDeliSuppSetbEntity.setRela_dcway_supp(sumoDeliSet.getSumo_dcway());

                        relaDeliSuppSetbEntityList.add(relaDeliSuppSetbEntity);
                    }
                }
                ormService.insert(relaDeliSuppSetbEntityList);

                // 12. 修改单据状态为 完成 - 5
                updateSupplierMaintOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_5);
            }
        } catch (Exception e) {
            logger.error("新增批准通过保存供应商出现错误:", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return "";
    }

    /**
     * 批准通过-修改供应商
     *
     * @param orderInstanceId 供应商维护单据id
     */
    private String updateSupplier(String orderInstanceId) {

        // 业务：修改伙伴类-供应商数据集下面的子表之前，需要把子表中的内容全部删除，再添加，以完成修改

        // 获取当前session信息
        CurrentSessionEntity currentSessionEntity = bizFormService.getCurrentSessionInfo();

        try {
            // 1. 修改 伙伴类 主表信息
            SuppliermaintorderEntity suppliermaintorderEntity = selectByInstancdId(orderInstanceId);

            RelationEntity relationEntity = new RelationEntity();

            relationEntity.setRela_code(suppliermaintorderEntity.getSumo_code());
            relationEntity.setRela_name(suppliermaintorderEntity.getSumo_name());
            relationEntity.setRela_short_name(suppliermaintorderEntity.getSumo_short_name());
            relationEntity.setRela_remark(suppliermaintorderEntity.getSumo_remark());
            relationEntity.setRela_addrp(suppliermaintorderEntity.getSumo_addrp());
            relationEntity.setRela_addrc(suppliermaintorderEntity.getSumo_addrc());
            relationEntity.setRela_addrl(suppliermaintorderEntity.getSumo_addrl());
            relationEntity.setRela_daddr(suppliermaintorderEntity.getSumo_daddr());
            relationEntity.setRela_pcode(suppliermaintorderEntity.getSumo_pcode());
            relationEntity.setRela_tel(suppliermaintorderEntity.getSumo_tel());
            relationEntity.setRela_fax(suppliermaintorderEntity.getSumo_fax());
            relationEntity.setRela_web(suppliermaintorderEntity.getSumo_web());
            relationEntity.setRela_mprod(suppliermaintorderEntity.getSumo_mprod());
            relationEntity.setRela_character(suppliermaintorderEntity.getSumo_character());
            relationEntity.setRela_listexch(suppliermaintorderEntity.getSumo_listexch());
            relationEntity.setRela_listname(suppliermaintorderEntity.getSumo_listname());
            relationEntity.setRela_listcode(suppliermaintorderEntity.getSumo_listcode());
            relationEntity.setRela_uscc(suppliermaintorderEntity.getSumo_uscc());
            relationEntity.setRela_raddr(suppliermaintorderEntity.getSumo_raddr());
            relationEntity.setRela_rdate(suppliermaintorderEntity.getSumo_rdate());
            relationEntity.setRela_lrep(suppliermaintorderEntity.getSumo_lrep());
            relationEntity.setRela_laddr(suppliermaintorderEntity.getSumo_laddr());
            relationEntity.setRela_rcurr(suppliermaintorderEntity.getSumo_rcurr_curr());
            relationEntity.setRela_rcapital(suppliermaintorderEntity.getSumo_rcapital());

            OrmParam relaSearchParam = new OrmParam();
            relaSearchParam.setWhereExp(relaSearchParam.getEqualXML(RelationProperty.RELA_CODE, suppliermaintorderEntity.getSumo_code()));
            ormService.updateSelective(relationEntity, relaSearchParam);

            // 通过伙伴编号唯一查找伙伴,获取伙伴类id
            List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, relaSearchParam);
            if (!ListUtils.isNullOrEmpty(relationEntityList)) {
                // 由于伙伴编号唯一，故此处取第一个值
                RelationEntity relation = relationEntityList.get(0);
                // 伙伴类id
                String relationId = relation.getId();

                // 2. 修改 伙伴类-体系认证
                List<SumoSumoSystemSetaEntity> sumoSystemSetList = selectByPid(orderInstanceId, SumoSumoSystemSetaEntity.class);
                if (!ListUtils.isNullOrEmpty(sumoSystemSetList)) {
                    for (SumoSumoSystemSetaEntity sumoSystemSet : sumoSystemSetList) {
                        RelaRelaSystemSetaEntity relaSystemSetaEntity = new RelaRelaSystemSetaEntity();
                        relaSystemSetaEntity.setRela_sname(sumoSystemSet.getSumo_sname());
                        updateByPid(relaSystemSetaEntity, relationId);
                    }
                }

                // 3. 修改 伙伴类-股东数据集
                List<SumoSumoHolderSetaEntity> sumoHolderSetParamSetList = selectByPid(orderInstanceId, SumoSumoHolderSetaEntity.class);
                if (!ListUtils.isNullOrEmpty(sumoHolderSetParamSetList)) {
                    for (SumoSumoHolderSetaEntity sumoHolderSet : sumoHolderSetParamSetList) {
                        RelaRelaHolderSetaEntity relaHolderSetaEntity = new RelaRelaHolderSetaEntity();

                        relaHolderSetaEntity.setRela_hname(sumoHolderSet.getSumo_hname());
                        relaHolderSetaEntity.setRela_hrate(sumoHolderSet.getSumo_hrate());
                        updateByPid(relaHolderSetaEntity, relationId);
                    }
                }

                // 4. 修改 伙伴类-供应商数据集
                List<SumoSumoSupplierSetaEntity> sumoSupplierSetList = selectByPid(orderInstanceId, SumoSumoSupplierSetaEntity.class);

                if (null != sumoSupplierSetList && sumoSupplierSetList.size() > 0) {
                    // 伙伴类跟供应商数据集之间是一对一关系，故此处不需要批量处理
                    SumoSumoSupplierSetaEntity sumoSupplierSet = sumoSupplierSetList.get(0);

                    RelaRelaSupplierSetaEntity relaRelaSupplierSetaEntity = new RelaRelaSupplierSetaEntity();

                    relaRelaSupplierSetaEntity.setRela_supptype(sumoSupplierSet.getSumo_supptype());
                    relaRelaSupplierSetaEntity.setRela_stat_supp(sumoSupplierSet.getSumo_stat());
                    relaRelaSupplierSetaEntity.setRela_servdept_supp(sumoSupplierSet.getSumo_servdept());
                    relaRelaSupplierSetaEntity.setRela_taxrate(sumoSupplierSet.getSumo_taxrate());
                    relaRelaSupplierSetaEntity.setRela_sett_ways(sumoSupplierSet.getSumo_sett_way());

                    updateByPid(relaRelaSupplierSetaEntity, relationId);
                }

                // 5. 删除 伙伴类-供应商数据集 下面的所有属性集
                // 获取伙伴类-供应商数据集的id
                OrmParam relaSupplierSetParam = new OrmParam();
                relaSupplierSetParam.setWhereExp(relaSupplierSetParam.getEqualXML(NodeConstant.PID, relationId));

                List<RelaRelaSupplierSetaEntity> relaSupplierSetList = ormService.selectBeanList(RelaRelaSupplierSetaEntity.class, relaSupplierSetParam);

                if (null != relaSupplierSetList && relaSupplierSetList.size() > 0) {
                    // 伙伴类跟供应商数据集之间是一对一关系，故此处不需要批量处理
                    RelaRelaSupplierSetaEntity relaSupplierSet = relaSupplierSetList.get(0);

                    // 遍历伙伴类-供应商数据集,得到id,作为条件，删除下面的子表
                    OrmParam relaSupplierSetDelParam = new OrmParam();
                    relaSupplierSetDelParam.setWhereExp(relaSupplierSetDelParam.getEqualXML(NodeConstant.PID, relaSupplierSet.getId()));

                    // 删除 伙伴类-供应商数据集-联系人集
                    ormService.delete(RelaRelaContactSuppSetbEntity.class, relaSupplierSetDelParam);
                    // 删除 伙伴类-供应商数据集-附件资料集
                    ormService.delete(RelaRelaAttachSuppSetbEntity.class, relaSupplierSetDelParam);
                    // 删除 伙伴类-供应商数据集-服务团队集
                    ormService.delete(RelaRelaServtSuppSetbEntity.class, relaSupplierSetDelParam);
                    // 删除 伙伴类-供应商数据集-账户管理集
                    ormService.delete(RelaRelaAccountSuppSetbEntity.class, relaSupplierSetDelParam);
                    // 删除 伙伴类-供应商数据集-交货地址集
                    ormService.delete(RelaRelaDeliSuppSetbEntity.class, relaSupplierSetDelParam);

                    // 6. 新增 伙伴类-供应商数据集-联系人集
                    List<SumoSumoSupplierSetaEntity> sumoSupplierSetaList = selectByPid(orderInstanceId, SumoSumoSupplierSetaEntity.class);

                    if (null != sumoSupplierSetaList && sumoSupplierSetaList.size() > 0) {
                        // 供应商维护单跟供应商属性集之间是一对一关系
                        SumoSumoSupplierSetaEntity sumoSupplierSet = sumoSupplierSetaList.get(0);

                        String supplierSetId = sumoSupplierSet.getId();

                        List<RelaRelaContactSuppSetbEntity> relaContactSuppSetbEntityList = new ArrayList<>();

                        List<SumoSumoContactSetbEntity> sumoContactSetList = selectByPid(supplierSetId, SumoSumoContactSetbEntity.class);

                        if (null != sumoContactSetList && sumoContactSetList.size() > 0) {

                            for (SumoSumoContactSetbEntity sumoContactSet : sumoContactSetList) {

                                RelaRelaContactSuppSetbEntity relaContactSuppSetbEntity = new RelaRelaContactSuppSetbEntity();

                                relaContactSuppSetbEntity.setPid(relaSupplierSet.getId());
                                relaContactSuppSetbEntity.setClassName(RelationConstants.className);
                                relaContactSuppSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                                relaContactSuppSetbEntity.setRela_conttype_supp(sumoContactSet.getSumo_conttype());
                                relaContactSuppSetbEntity.setRela_contname_supp(sumoContactSet.getSumo_contname());
                                relaContactSuppSetbEntity.setRela_contsex_supp(sumoContactSet.getSumo_contsex());
                                relaContactSuppSetbEntity.setRela_contpost_supp(sumoContactSet.getSumo_contpost());
                                relaContactSuppSetbEntity.setRela_conttel_supp(sumoContactSet.getSumo_conttel());
                                relaContactSuppSetbEntity.setRela_contemail_supp(sumoContactSet.getSumo_contemail());
                                relaContactSuppSetbEntity.setRela_contother_supp(sumoContactSet.getSumo_contother());

                                relaContactSuppSetbEntityList.add(relaContactSuppSetbEntity);
                            }
                        }
                        ormService.insert(relaContactSuppSetbEntityList);

                        // 7. 新增 伙伴类-供应商数据集-附件资料集
                        List<RelaRelaAttachSuppSetbEntity> relaAttachSuppSetbEntityList = new ArrayList<>();

                        List<SumoSumoAttachSetbEntity> sumoAttachSetList = selectByPid(supplierSetId, SumoSumoAttachSetbEntity.class);

                        if (null != sumoAttachSetList && sumoAttachSetList.size() > 0) {
                            for (SumoSumoAttachSetbEntity sumoAttachSet : sumoAttachSetList) {

                                RelaRelaAttachSuppSetbEntity relaAttachSuppSetbEntity = new RelaRelaAttachSuppSetbEntity();

                                relaAttachSuppSetbEntity.setPid(relaSupplierSet.getId());
                                relaAttachSuppSetbEntity.setClassName(RelationConstants.className);
                                relaAttachSuppSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                                relaAttachSuppSetbEntity.setRela_attatype_supp(sumoAttachSet.getSumo_attatype());
                                relaAttachSuppSetbEntity.setRela_attaaddr_supp(sumoAttachSet.getSumo_attaaddr());
                                relaAttachSuppSetbEntity.setRela_attavalid_supp(sumoAttachSet.getSumo_attavalid());

                                relaAttachSuppSetbEntityList.add(relaAttachSuppSetbEntity);
                            }
                        }

                        ormService.insert(relaAttachSuppSetbEntityList);

                        // 8. 新增 伙伴类-供应商数据集-服务团队集
                        List<RelaRelaServtSuppSetbEntity> relaServtSuppSetbList = new ArrayList<>();

                        List<SumoSumoServtSetbEntity> sumoServtSetbList = selectByPid(supplierSetId, SumoSumoServtSetbEntity.class);

                        if (null != sumoServtSetbList && sumoServtSetbList.size() > 0) {

                            for (SumoSumoServtSetbEntity sumoServtSet : sumoServtSetbList) {

                                RelaRelaServtSuppSetbEntity relaServtSuppSetbEntity = new RelaRelaServtSuppSetbEntity();

                                relaServtSuppSetbEntity.setPid(relaSupplierSet.getId());
                                relaServtSuppSetbEntity.setClassName(RelationConstants.className);
                                relaServtSuppSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                                relaServtSuppSetbEntity.setRela_sttype_supp(sumoServtSet.getSumo_sttype());
                                relaServtSuppSetbEntity.setRela_stemp_supp(sumoServtSet.getSumo_stemp());

                                relaServtSuppSetbList.add(relaServtSuppSetbEntity);
                            }
                        }
                        ormService.insert(relaServtSuppSetbList);

                        // 9. 新增 伙伴类-供应商数据集-账户管理集
                        List<RelaRelaAccountSuppSetbEntity> relaAccountSuppSetbEntityList = new ArrayList<>();

                        List<SumoSumoAccountSetbEntity> sumoAccountSetList = selectByPid(supplierSetId, SumoSumoAccountSetbEntity.class);

                        if (null != sumoAccountSetList && sumoAccountSetList.size() > 0) {

                            for (SumoSumoAccountSetbEntity sumoSumoAccountSet : sumoAccountSetList) {

                                RelaRelaAccountSuppSetbEntity relaAccountSuppSetbEntity = new RelaRelaAccountSuppSetbEntity();

                                relaAccountSuppSetbEntity.setPid(relaSupplierSet.getId());
                                relaAccountSuppSetbEntity.setClassName(RelationConstants.className);
                                relaAccountSuppSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                                relaAccountSuppSetbEntity.setRela_acconame_supp(sumoSumoAccountSet.getSumo_acconame());
                                relaAccountSuppSetbEntity.setRela_accobank_supp(sumoSumoAccountSet.getSumo_accobank());
                                relaAccountSuppSetbEntity.setRela_acconum_supp(sumoSumoAccountSet.getSumo_acconum());
                                relaAccountSuppSetbEntity.setRela_accocurr_supp(sumoSumoAccountSet.getSumo_accocurr());
                                relaAccountSuppSetbEntity.setRela_accobeg_supp(sumoSumoAccountSet.getSumo_accobeg());
                                relaAccountSuppSetbEntity.setRela_accoend_supp(sumoSumoAccountSet.getSumo_accoend());
                                relaAccountSuppSetbEntity.setRela_attaacco_supp(sumoSumoAccountSet.getSumo_attaacco());

                                relaAccountSuppSetbEntityList.add(relaAccountSuppSetbEntity);
                            }
                        }

                        ormService.insert(relaAccountSuppSetbEntityList);

                        // 10.新增 伙伴类-供应商数据集-交货管理集
                        List<RelaRelaDeliSuppSetbEntity> relaDeliSuppSetbEntityList = new ArrayList<>();

                        List<SumoSumoDeliSetbEntity> sumoDeliSetList = selectByPid(supplierSetId, SumoSumoDeliSetbEntity.class);

                        if (null != sumoDeliSetList && sumoDeliSetList.size() > 0) {

                            for (SumoSumoDeliSetbEntity sumoDeliSet : sumoDeliSetList) {

                                RelaRelaDeliSuppSetbEntity relaDeliSuppSetbEntity = new RelaRelaDeliSuppSetbEntity();

                                relaDeliSuppSetbEntity.setPid(relaSupplierSet.getId());
                                relaDeliSuppSetbEntity.setClassName(RelationConstants.className);
                                relaDeliSuppSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                                relaDeliSuppSetbEntity.setRela_daname_supp(sumoDeliSet.getSumo_daname());
                                relaDeliSuppSetbEntity.setRela_daddrp_supp(sumoDeliSet.getSumo_daddrp());
                                relaDeliSuppSetbEntity.setRela_daddrc_supp(sumoDeliSet.getSumo_daddrc());
                                relaDeliSuppSetbEntity.setRela_daddrl_supp(sumoDeliSet.getSumo_daddrl());
                                relaDeliSuppSetbEntity.setRela_ddaddr_supp(sumoDeliSet.getSumo_ddaddr());
                                relaDeliSuppSetbEntity.setRela_dcontact_supp(sumoDeliSet.getSumo_dcontact());
                                relaDeliSuppSetbEntity.setRela_dcway_supp(sumoDeliSet.getSumo_dcway());

                                relaDeliSuppSetbEntityList.add(relaDeliSuppSetbEntity);
                            }
                        }
                        ormService.insert(relaDeliSuppSetbEntityList);

                        // 修改单据状态为 完成 - 5
                        updateSupplierMaintOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_5);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("批准通过-修改保存出错:", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }

        return "";
    }

    /**
     * 校验唯一性
     *
     * @param supplierMaintOrderDTO
     * @return
     */
    public String checkUnique(SupplierMaintOrderDTO supplierMaintOrderDTO) {

        String message = "";
        String supplierMaintOrderId = supplierMaintOrderDTO.getId();

        try {
            // 根据是否传入单据id判断是新增还是修改
            if (StringUtil.isNullOrEmpty(supplierMaintOrderId)) {
                // 新增
                // 伙伴编号、伙伴简称、社会统一信用代码 唯一性校验
                OrmParam relationParam1 = new OrmParam();
                relationParam1.setWhereExp(OrmParam.and(
                        relationParam1.getEqualXML(RelationProperty.RELA_CODE, supplierMaintOrderDTO.getSumoCode()),
                        relationParam1.getUnequalXML(NodeConstant.ID, supplierMaintOrderDTO.getRelaId())
                ));
                List<RelationEntity> relationList1 = ormService.selectBeanList(RelationEntity.class, relationParam1);

                OrmParam supplierMaintOrderParam1 = new OrmParam();
                supplierMaintOrderParam1.setWhereExp(OrmParam.and(supplierMaintOrderParam1.getEqualXML(SupplierMaintOrderConstants.SUMO_CODE, supplierMaintOrderDTO.getSumoCode()),
                        OrmParam.or(supplierMaintOrderParam1.getIsNull(OrderConstants.ORDE_STATUS), supplierMaintOrderParam1.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                List<SuppliermaintorderEntity> supplierMaintOrderList1 = ormService.selectBeanList(SuppliermaintorderEntity.class, supplierMaintOrderParam1);

                if (!ListUtils.isNullOrEmpty(relationList1) || !ListUtils.isNullOrEmpty(supplierMaintOrderList1)) {
                    message = message + "伙伴编号,";
                }

                OrmParam relationParam2 = new OrmParam();
                relationParam2.setWhereExp(OrmParam.and(
                        relationParam2.getEqualXML(RelationProperty.RELA_SHORT_NAME, supplierMaintOrderDTO.getSumoShortName()),
                        relationParam2.getUnequalXML(NodeConstant.ID, supplierMaintOrderDTO.getRelaId())
                ));
                List<RelationEntity> relationList2 = ormService.selectBeanList(RelationEntity.class, relationParam2);

                OrmParam supplierMaintOrderParam2 = new OrmParam();
                supplierMaintOrderParam2.setWhereExp(OrmParam.and(supplierMaintOrderParam2.getEqualXML(SupplierMaintOrderConstants.SUMO_SHORT_NAME, supplierMaintOrderDTO.getSumoShortName()),
                        OrmParam.or(supplierMaintOrderParam2.getIsNull(OrderConstants.ORDE_STATUS), supplierMaintOrderParam2.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                List<SuppliermaintorderEntity> supplierMaintOrderList2 = ormService.selectBeanList(SuppliermaintorderEntity.class, supplierMaintOrderParam2);

                if (!ListUtils.isNullOrEmpty(relationList2) || !ListUtils.isNullOrEmpty(supplierMaintOrderList2)) {
                    message = message + "伙伴简称,";
                }

                OrmParam relationParam3 = new OrmParam();
                relationParam3.setWhereExp(OrmParam.and(
                        relationParam3.getEqualXML(RelationProperty.RELA_USCC, supplierMaintOrderDTO.getSumoUscc()),
                        relationParam3.getUnequalXML(NodeConstant.ID, supplierMaintOrderDTO.getRelaId())
                ));
                List<RelationEntity> relationList3 = ormService.selectBeanList(RelationEntity.class, relationParam3);

                OrmParam supplierMaintOrderParam3 = new OrmParam();
                supplierMaintOrderParam3.setWhereExp(OrmParam.and(supplierMaintOrderParam3.getEqualXML(SupplierMaintOrderConstants.SUMO_USCC, supplierMaintOrderDTO.getSumoUscc()),
                        OrmParam.or(supplierMaintOrderParam3.getIsNull(OrderConstants.ORDE_STATUS), supplierMaintOrderParam3.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                List<SuppliermaintorderEntity> supplierMaintOrderList3 = ormService.selectBeanList(SuppliermaintorderEntity.class, supplierMaintOrderParam3);

                if (!ListUtils.isNullOrEmpty(relationList3) || !ListUtils.isNullOrEmpty(supplierMaintOrderList3)) {
                    message = message + "统一社会信用代码,";
                }

                if (!message.equals("")) {
                    message = message + "存在重复记录，不可提交！";
                }
            } else {
                // 修改校验，排除自身, 如果是已经存在的伙伴类，编辑时需要传入伙伴类的id排除自身
                // 伙伴编号、伙伴简称、社会统一信用代码 唯一性校验
                OrmParam relationParam1 = new OrmParam();
                relationParam1.setWhereExp(OrmParam.and(
                        relationParam1.getEqualXML(RelationProperty.RELA_CODE, supplierMaintOrderDTO.getSumoCode()),
                        relationParam1.getUnequalXML(NodeConstant.ID, supplierMaintOrderDTO.getRelaId())
                ));
                List<RelationEntity> relationList1 = ormService.selectBeanList(RelationEntity.class, relationParam1);

                OrmParam supplierMaintOrderParam1 = new OrmParam();
                supplierMaintOrderParam1.setWhereExp(OrmParam.and(
                        supplierMaintOrderParam1.getEqualXML(SupplierMaintOrderConstants.SUMO_CODE, supplierMaintOrderDTO.getSumoCode()),
                        supplierMaintOrderParam1.getUnequalXML(NodeConstant.ID, supplierMaintOrderDTO.getId())
                ));
                List<SuppliermaintorderEntity> supplierMaintOrderList1 = ormService.selectBeanList(SuppliermaintorderEntity.class, supplierMaintOrderParam1);

                if (!ListUtils.isNullOrEmpty(relationList1) || !ListUtils.isNullOrEmpty(supplierMaintOrderList1)) {
                    message = message + "伙伴编号,";
                }

                OrmParam relationParam2 = new OrmParam();
                relationParam2.setWhereExp(OrmParam.and(
                        relationParam2.getEqualXML(RelationProperty.RELA_SHORT_NAME, supplierMaintOrderDTO.getSumoShortName()),
                        relationParam2.getUnequalXML(NodeConstant.ID, supplierMaintOrderDTO.getRelaId())
                ));
                List<RelationEntity> relationList2 = ormService.selectBeanList(RelationEntity.class, relationParam2);

                OrmParam supplierMaintOrderParam2 = new OrmParam();
                supplierMaintOrderParam2.setWhereExp(OrmParam.and(
                        supplierMaintOrderParam2.getEqualXML(SupplierMaintOrderConstants.SUMO_SHORT_NAME, supplierMaintOrderDTO.getSumoShortName()),
                        supplierMaintOrderParam2.getUnequalXML(NodeConstant.ID, supplierMaintOrderDTO.getId())
                ));
                List<SuppliermaintorderEntity> supplierMaintOrderList2 = ormService.selectBeanList(SuppliermaintorderEntity.class, supplierMaintOrderParam2);

                if (!ListUtils.isNullOrEmpty(relationList2) || !ListUtils.isNullOrEmpty(supplierMaintOrderList2)) {
                    message = message + "伙伴简称,";
                }

                OrmParam relationParam3 = new OrmParam();
                relationParam3.setWhereExp(OrmParam.and(
                        relationParam3.getEqualXML(RelationProperty.RELA_USCC, supplierMaintOrderDTO.getSumoUscc()),
                        relationParam3.getUnequalXML(NodeConstant.ID, supplierMaintOrderDTO.getRelaId())
                ));
                List<RelationEntity> relationList3 = ormService.selectBeanList(RelationEntity.class, relationParam3);

                OrmParam supplierMaintOrderParam3 = new OrmParam();
                supplierMaintOrderParam3.setWhereExp(OrmParam.and(
                        supplierMaintOrderParam2.getEqualXML(SupplierMaintOrderConstants.SUMO_USCC, supplierMaintOrderDTO.getSumoUscc()),
                        supplierMaintOrderParam2.getUnequalXML(NodeConstant.ID, supplierMaintOrderDTO.getId())
                ));
                List<SuppliermaintorderEntity> supplierMaintOrderList3 = ormService.selectBeanList(SuppliermaintorderEntity.class, supplierMaintOrderParam3);

                if (!ListUtils.isNullOrEmpty(relationList3) || !ListUtils.isNullOrEmpty(supplierMaintOrderList3)) {
                    message = message + "统一社会信用代码,";
                }

                if (!message.equals("")) {
                    message = message + "存在重复记录，不可提交！";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return message;
    }

    /**
     * 根据pid删除属性集
     *
     * @param pid
     * @param tClass
     * @param <T>
     * @return
     */
    private <T extends BaseEntity> int deleteByPid(String pid, Class<T> tClass) {
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        int retInt;
        try {
            retInt = ormService.delete(tClass, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "根据pid删除供应商属性集出错:" + e.getMessage());
        }
        return retInt;
    }

}
