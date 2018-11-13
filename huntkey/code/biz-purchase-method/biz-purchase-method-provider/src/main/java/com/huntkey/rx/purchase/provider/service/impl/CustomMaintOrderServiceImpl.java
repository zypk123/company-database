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
import com.huntkey.rx.purchase.common.model.custom.*;
import com.huntkey.rx.purchase.common.util.JsonUtils;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.service.BizFormService;
import com.huntkey.rx.purchase.provider.service.CommonService;
import com.huntkey.rx.purchase.provider.service.CustomMaintOrderService;
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
 * 客户维护单Service接口实现类
 *
 * @author zhangyu
 * @create 2018-01-02 17:44
 **/
@Service
public class CustomMaintOrderServiceImpl implements CustomMaintOrderService {

    Logger logger = LoggerFactory.getLogger(CustomMaintOrderServiceImpl.class);

    @Autowired
    private OrmService ormService;

    @Autowired
    private BizFormService bizFormService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SupplierMaintOrderService supplierMaintOrderService;

    /**
     * 客户管理—列表查询
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
            StringBuffer baseSql = new StringBuffer("select p.rela_code,p.rela_short_name,c.id,c.pid,c.rela_stat_cust,p.rela_remark,c.rela_servdept_cust,c.moduser,c.modtime ");
            baseSql.append(" from rela_rela_cust_seta c left join  relation p  on p.id = c.pid where p.is_del=0 and c.is_del=0 ");

            if(!StringUtil.isNullOrEmpty(relaCode)){
                baseSql.append(" and p.rela_code like '%").append(relaCode).append("%' ");
            }
            if(!StringUtil.isNullOrEmpty(relaShortName)){
                baseSql.append(" and p.rela_short_name like '%").append(relaShortName).append("%' ");
            }
            if(!StringUtil.isNullOrEmpty(relaStatus)){
                baseSql.append(" and c.rela_stat_cust = '").append(relaStatus).append("' ");
            }

            pageNum = pageNum<1?1:pageNum;

            String limit = " ORDER BY c.cretime desc  LIMIT " + (pageNum - 1) * pageSize + "," + pageSize;

            String querySql = baseSql.append(limit).toString();

            List<Map<String, Object>> mapList = ormService.getDataBySql(querySql);

            if(mapList.isEmpty())
            {
                return supplierMaintOrderService.emptyPageQueryResult(pageNum , pageSize);
            }

            List<Map<String, Object>> count = ormService.getDataBySql(baseSql.toString());
            //组装返回的数据
            JSONObject obj = new JSONObject();
            obj.put("pageNum", pageNum);
            obj.put("pageSize", pageSize);
            obj.put("total", count.size());
            obj.put("list", getCustomerInfo(mapList));
            result.setData(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return supplierMaintOrderService.getResult(Result.RECODE_ERROR , "查询客户列表失败，" + e.getMessage());
        }
        return result;
    }

    /**
     * 封装客户信息
     * @param list
     * @return
     */
    private JSONArray getCustomerInfo(List<Map<String, Object>> list){
        JSONArray relationArray = new JSONArray();
        for (Map<String, Object> map : list) {
            JSONObject jsonObject = new JSONObject();
            //伙伴代码
            jsonObject.put("relaCode" , NullUtils.valueOf(map.get(RelationProperty.RELA_CODE)));
            //伙伴名称
            jsonObject.put("relaShortName" , NullUtils.valueOf(map.get(RelationProperty.RELA_SHORT_NAME)));
            //备注
            jsonObject.put("relaRemark" , NullUtils.valueOf(map.get(RelationProperty.RELA_REMARK)));
            //状态
            jsonObject.put("relaStatus" , NullUtils.valueOf(map.get(RelaRelaCustSetaProperty.RELA_STAT_CUST)));
            //维护时间
            jsonObject.put("modtime" , NullUtils.valueOf(map.get(EdmSysColumn.MODTIME)));
            //客户ID
            String customMaintId = NullUtils.valueOf(map.get(EdmSysColumn.ID));
            //伙伴ID
            String relaId = NullUtils.valueOf(map.get(EdmSysColumn.PID));
            jsonObject.put("id" , relaId);
            //服务部门ID
            String relaServdeptCust = NullUtils.valueOf(map.get(RelaRelaCustSetaProperty.RELA_SERVDEPT_CUST));
            jsonObject.put("relaServdept" , relaServdeptCust);
            //维护人ID
            String moduser = NullUtils.valueOf(map.get(EdmSysColumn.MODUSER));
            jsonObject.put("moduser" , moduser);
            //组装查询条件
            HashMap conditionMap = new HashMap();
            conditionMap.put(EdmSysColumn.ID , relaServdeptCust);
            //服务部门名称
            String servDeptName = supplierMaintOrderService.queryResultByConditions(DepttreeEntity.class , conditionMap, DepttreeProperty.MDEP_NAME);
            jsonObject.put("relaServdeptName" , servDeptName);
            //维护人名称
            conditionMap.put(EdmSysColumn.ID , moduser);
            String moduserName = supplierMaintOrderService.queryResultByConditions(EmployeeEntity.class , conditionMap, EmployeeProperty.REMP_NAME);
            jsonObject.put("moduserName" , moduserName);
            /**服务人员名称**/
            //1、先查询服务人员ID
            conditionMap.remove(EdmSysColumn.ID);
            conditionMap.put(EdmSysColumn.PID , customMaintId);
            conditionMap.put(RelaRelaServtCustSetbProperty.RELA_STTYPE_CUST , RelationConstants.TEAM_TYPE_SALE);
            String ServEmpId = supplierMaintOrderService.queryResultByConditions(RelaRelaServtCustSetbEntity.class , conditionMap, RelaRelaServtCustSetbProperty.RELA_STEMP_CUST);
            //再根据ID查询名称
            conditionMap.clear();
            conditionMap.put(EdmSysColumn.ID , ServEmpId);
            String servEmpName = supplierMaintOrderService.queryResultByConditions(EmployeeEntity.class , conditionMap, EmployeeProperty.REMP_NAME);
            jsonObject.put("relaServEmpName" , servEmpName);
            relationArray.add(jsonObject);
        }
        return relationArray;
    }

    @Override
    public Result load(String id) {
        logger.info("客户维护单 加载Service启动....");
        Result result = new Result();
        try {
            // 定义返回数据对象
            CustomMaintOrderDTO customDTO = null;

            // 1. 查询 客户维护单主表
            CustommaintorderEntity custommaintorderEntity = ormService.load(CustommaintorderEntity.class, id);
            if (null != custommaintorderEntity) {
                customDTO = JSONObject.parseObject(JSON.toJSONString(custommaintorderEntity), CustomMaintOrderDTO.class);

                // 2. 查询 客户维护单-体系认证
                List<CumoCumoSystemSetaEntity> systemSetList = selectByPid(customDTO.getId(), CumoCumoSystemSetaEntity.class);
                if (null != systemSetList && systemSetList.size() > 0) {
                    List<CumoSystemSetDTO> systemSetDTOList = JSONArray.parseArray(JSON.toJSONString(systemSetList), CumoSystemSetDTO.class);
                    customDTO.setCumoSystemSet(systemSetDTOList);
                }

                // 3. 查询 客户维护单-股东数据集
                List<CumoCumoHolderSetaEntity> holderSetList = selectByPid(customDTO.getId(), CumoCumoHolderSetaEntity.class);
                if (null != holderSetList && holderSetList.size() > 0) {
                    List<CumoHolderSetDTO> cumoHolderSetDTOList = JSONArray.parseArray(JSON.toJSONString(holderSetList), CumoHolderSetDTO.class);
                    customDTO.setCumoHolderSet(cumoHolderSetDTOList);
                }

                // 4. 查询 客户维护单-客户数据集
                List<CumoCumoCustSetaEntity> customSetList = selectByPid(customDTO.getId(), CumoCumoCustSetaEntity.class);
                if (null != customSetList && customSetList.size() > 0) {
                    //  这里由于 客户维护单和客户数据集之间是一对一关系，故直接取第一条就行了
                    CumoCumoCustSetaEntity customSet = customSetList.get(0);
                    CumoCustSetDTO cumoCustomSetDTO = JSONObject.parseObject(JSON.toJSONString(customSet), CumoCustSetDTO.class);
                    String servDeptId = cumoCustomSetDTO.getCumoServdept();
                    if (!StringUtil.isNullOrEmpty(servDeptId)) {
                        DepttreeEntity deptTree = ormService.load(DepttreeEntity.class, servDeptId);
                        if (!StringUtil.isNullOrEmpty(deptTree)) {
                            // 设置服务部门名称
                            cumoCustomSetDTO.setCumoServdeptName(deptTree.getMdep_name());
                        }
                    }
                    String settWayId = cumoCustomSetDTO.getCumoSettWay();
                    if (!StringUtil.isNullOrEmpty(settWayId)) {
                        SettlemenetEntity settlemenet = ormService.load(SettlemenetEntity.class, settWayId);
                        if (!StringUtil.isNullOrEmpty(settlemenet)) {
                            // 设置结算方式描述
                            cumoCustomSetDTO.setCumoSettWayDesc(settlemenet.getSett_desc());
                        }
                    }
                    // 5. 查询 客户维护单-客户数据集-联系人集
                    List<CumoCumoContactSetbEntity> contactSetList = selectByPid(customSet.getId(), CumoCumoContactSetbEntity.class);
                    if (null != contactSetList && contactSetList.size() > 0) {
                        List<CumoContactSetDTO> contactSetDTOList = JSONArray.parseArray(JSON.toJSONString(contactSetList), CumoContactSetDTO.class);
                        cumoCustomSetDTO.setCumoContactSet(contactSetDTOList);
                    }

                    // 6. 查询 客户维护单-客户数据集-附件资料集
                    List<CumoCumoAttachSetbEntity> attachSetList = selectByPid(customSet.getId(), CumoCumoAttachSetbEntity.class);
                    if (null != attachSetList && attachSetList.size() > 0) {
                        List<CumoAttachSetDTO> attachSetDTOList = JSONArray.parseArray(JSON.toJSONString(attachSetList), CumoAttachSetDTO.class);
                        cumoCustomSetDTO.setCumoAttachSet(attachSetDTOList);
                    }

                    // 7. 查询 客户维护单-客户数据集-服务团队集
                    List<CumoCumoServtSetbEntity> servtSetList = selectByPid(customSet.getId(), CumoCumoServtSetbEntity.class);
                    if (!ListUtils.isNullOrEmpty(servtSetList)) {
                        List<CumoServtSetDTO> servtSetDTOList = JSONArray.parseArray(JSON.toJSONString(servtSetList), CumoServtSetDTO.class);
                        if (!ListUtils.isNullOrEmpty(servtSetDTOList)) {
                            for (CumoServtSetDTO servtSetDTO : servtSetDTOList) {
                                // 根据员工id到员工类查询
                                String employeeId = servtSetDTO.getCumoStemp();
                                if (!StringUtil.isNullOrEmpty(employeeId)) {
                                    EmployeeEntity employeeEntity = ormService.load(EmployeeEntity.class, employeeId);
                                    if (null != employeeEntity) {
                                        // 姓名
                                        servtSetDTO.setCumoStempName(employeeEntity.getRemp_name());

                                        // 根据部门id到部门类查询
                                        String deptId = employeeEntity.getRemp_dept();
                                        if (!StringUtil.isNullOrEmpty(deptId)) {
                                            DepttreeEntity depttreeEntity = ormService.load(DepttreeEntity.class, deptId);
                                            if (null != depttreeEntity) {
                                                // 部门
                                                servtSetDTO.setCumoDeptName(depttreeEntity.getMdep_name());
                                            }

                                            // 根据岗位id到岗位类查询
                                            String postId = employeeEntity.getRemp_post();
                                            if (!StringUtil.isNullOrEmpty(postId)) {
                                                JobpositionEntity jobpositionEntity = ormService.load(JobpositionEntity.class, postId);
                                                if (null != jobpositionEntity) {
                                                    // 岗位
                                                    servtSetDTO.setCumoPostName(jobpositionEntity.getRpos_name());
                                                }
                                            }
                                            // 手机号码
                                            servtSetDTO.setCumoTel(employeeEntity.getRemp_tel());

                                            // TODO 办公电话，EDM暂时没有该属性，后续处理
                                        }
                                    }
                                }
                            }
                        }
                        cumoCustomSetDTO.setCumoServtSet(servtSetDTOList);
                    }

                    // 8. 查询 客户维护单-客户数据集-账户管理集
                    List<CumoCumoAccountSetbEntity> accountSetList = selectByPid(customSet.getId(), CumoCumoAccountSetbEntity.class);
                    if (null != accountSetList && accountSetList.size() > 0) {
                        List<CumoAccountSetDTO> accountSetDTOList = JSONArray.parseArray(JSON.toJSONString(accountSetList), CumoAccountSetDTO.class);
                        cumoCustomSetDTO.setCumoAccountSet(accountSetDTOList);
                    }

                    // 9. 查询 客户维护单-客户数据集-交货地址集
                    List<CumoCumoDeliSetbEntity> deliSetList = selectByPid(customSet.getId(), CumoCumoDeliSetbEntity.class);
                    if (null != deliSetList && deliSetList.size() > 0) {
                        List<CumoDeliSetDTO> deliDTOList = JSONArray.parseArray(JSON.toJSONString(deliSetList), CumoDeliSetDTO.class);
                        cumoCustomSetDTO.setCumoDeliSet(deliDTOList);
                    }

                    // 这里放最后，保证全部属性以及添加完整
                    customDTO.setCumoCustSet(cumoCustomSetDTO);
                }
            }
            result.setData(customDTO);
        } catch (Exception e) {
            logger.error("客户维护单加载方法出现错误....", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result save(CustomMaintOrderDTO customMaintOrderDTO) {
        logger.info("客户维护单保存接口服务开始......");
        Result result = new Result();

        // 单据单号
        String ordeNbr = null;

        // 获取当前session信息
        CurrentSessionEntity currentSessionEntity = bizFormService.getCurrentSessionInfo();

        // 保存必须要先进行登录
        if (null == currentSessionEntity) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("请先登录系统再进行相关操作");
            return result;
        }

        // 唯一性校验
//        String message = checkUnique(customMaintOrderDTO);
//        if (!message.equals("")) {
//            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
//            result.setErrMsg(message);
//            return result;
//        }

        // DTO转Entity
        CustommaintorderEntity custommaintorderEntity = JSONObject.parseObject(JSON.toJSONString(customMaintOrderDTO), CustommaintorderEntity.class);

        // 设置 企业对象
        custommaintorderEntity.setEdmd_ente(currentSessionEntity.getEnterpriseId());
        // 设置 对象类
        custommaintorderEntity.setEdmd_class(CustomMaintOrderConstants.CUSTOMMAINTORDER);
        // 制单时间
        custommaintorderEntity.setOrde_date(new Date());
        // 制单人
        custommaintorderEntity.setOrde_adduser(currentSessionEntity.getEmployeeId());

        // 获取id，根据是否有id判断是新增还是修改
        String customMaintOrderId = custommaintorderEntity.getId();
        try {
            if (StringUtil.isNullOrEmpty(customMaintOrderId)) {
                // 新增
                // 判断是否 直接提交
                if (customMaintOrderDTO.isSubmit()) {
                    // 直接提交，生成正式单号
                    ordeNbr = commonService.getCode(NumberConstants.PREFIX_CUSTOM_MAINT_ORDER);
                    custommaintorderEntity.setOrde_nbr(ordeNbr);
                } else {
                    // 否则，生成临时单号
                    ordeNbr = commonService.getCode(NumberConstants.PREFIX_PU_TEMP_ORDER);
                    custommaintorderEntity.setOrde_nbr(ordeNbr);
                }

                // 1. 新增 客户维护单主表
                custommaintorderEntity.setCretime(new Date());
                custommaintorderEntity.setCreuser(currentSessionEntity.getEmployeeId());
                custommaintorderEntity.setOrde_status(OrderConstants.ORDE_STATUS_1);
                String mainId = ormService.insertSelective(custommaintorderEntity).toString();

                custommaintorderEntity.setId(mainId);

                // 2. 新增 客户维护单-体系认证 属性集
                List<CumoCumoSystemSetaEntity> cumoSystemSetList = custommaintorderEntity.getCumo_system_set();
                if (!ListUtils.isNullOrEmpty(cumoSystemSetList)) {
                    EdmUtil.setPropertyBaseEntitiesSysColumns(CustommaintorderEntity.class, custommaintorderEntity,
                            cumoSystemSetList, SQLCurdEnum.INSERT);
                    ormService.insert(cumoSystemSetList);
                }

                // 3. 新增 客户维护单-股东数据集 属性集
                List<CumoCumoHolderSetaEntity> cumoHolderSet = custommaintorderEntity.getCumo_holder_set();
                if (!ListUtils.isNullOrEmpty(cumoHolderSet)) {
                    // 设置前端传来的id为null
                    cumoHolderSet.forEach(item -> item.setId(null));
                    EdmUtil.setPropertyBaseEntitiesSysColumns(CustommaintorderEntity.class, custommaintorderEntity,
                            cumoHolderSet, SQLCurdEnum.INSERT);
                    ormService.insert(cumoHolderSet);
                }

                // 4. 新增 客户维护单-客户数据集 属性集
                List<CumoCumoCustSetaEntity> cumoCustSetList = custommaintorderEntity.getCumo_cust_set();
                if (!ListUtils.isNullOrEmpty(cumoCustSetList)) {
                    EdmUtil.setPropertyBaseEntitiesSysColumns(CustommaintorderEntity.class, custommaintorderEntity,
                            cumoCustSetList, SQLCurdEnum.INSERT);
                    // 返回客户数据集id
                    String cumoCumoCustSetId = ormService.insertSelective(cumoCustSetList.get(0)).toString();

                    CumoCumoCustSetaEntity cumoCustSet = cumoCustSetList.get(0);
                    cumoCustSet.setId(cumoCumoCustSetId);

                    // 5. 新增 客户维护单-客户数据集-联系人
                    List<CumoCumoContactSetbEntity> cumoContactSet = cumoCustSet.getCumo_contact_set();
                    if (!ListUtils.isNullOrEmpty(cumoContactSet)) {
                        // 设置前端传来的id为null
                        cumoContactSet.forEach(item -> item.setId(null));
                        EdmUtil.setPropertyBaseEntitiesSysColumns(CumoCumoCustSetaEntity.class, cumoCustSet,
                                cumoContactSet, SQLCurdEnum.INSERT);
                        ormService.insert(cumoContactSet);
                    }

                    // 6. 新增 客户维护单-客户数据集-附件资料
                    List<CumoCumoAttachSetbEntity> cumoAttachSet = cumoCustSet.getCumo_attach_set();
                    if (!ListUtils.isNullOrEmpty(cumoAttachSet)) {
                        // 设置前端传来的id为null
                        cumoAttachSet.forEach(item -> item.setId(null));
                        EdmUtil.setPropertyBaseEntitiesSysColumns(CumoCumoCustSetaEntity.class, cumoCustSet,
                                cumoAttachSet, SQLCurdEnum.INSERT);
                        ormService.insert(cumoAttachSet);
                    }

                    // 7. 新增 客户维护单-客户数据集-服务团队
                    List<CumoCumoServtSetbEntity> cumoServtSet = cumoCustSet.getCumo_servt_set();
                    if (!ListUtils.isNullOrEmpty(cumoServtSet)) {
                        // 设置前端传来的id为null
                        cumoServtSet.forEach(item -> item.setId(null));
                        EdmUtil.setPropertyBaseEntitiesSysColumns(CumoCumoCustSetaEntity.class, cumoCustSet,
                                cumoServtSet, SQLCurdEnum.INSERT);
                        ormService.insert(cumoServtSet);
                    }

                    // 8. 新增 客户维护单-客户数据集-账户管理
                    List<CumoCumoAccountSetbEntity> cumoAccountSet = cumoCustSet.getCumo_account_set();
                    if (!ListUtils.isNullOrEmpty(cumoAccountSet)) {
                        // 设置前端传来的id为null
                        cumoAccountSet.forEach(item -> item.setId(null));
                        EdmUtil.setPropertyBaseEntitiesSysColumns(CumoCumoCustSetaEntity.class, cumoCustSet,
                                cumoAccountSet, SQLCurdEnum.INSERT);
                        ormService.insert(cumoAccountSet);
                    }

                    // 9. 新增 客户维护单-客户数据集-交货管理
                    List<CumoCumoDeliSetbEntity> cumoDeliSet = cumoCustSet.getCumo_deli_set();
                    if (!ListUtils.isNullOrEmpty(cumoDeliSet)) {
                        // 设置前端传来的id为null
                        cumoDeliSet.forEach(item -> item.setId(null));
                        EdmUtil.setPropertyBaseEntitiesSysColumns(CumoCumoCustSetaEntity.class, cumoCustSet,
                                cumoDeliSet, SQLCurdEnum.INSERT);
                        ormService.insert(cumoDeliSet);
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
                if (customMaintOrderDTO.isSubmit()) {
                    // 直接提交，生成正式单号
                    ordeNbr = commonService.getCode(NumberConstants.PREFIX_CUSTOM_MAINT_ORDER);
                    custommaintorderEntity.setOrde_nbr(ordeNbr);
                } else {
                    // 这里如果修改临时单，就不要重新生成临时单号了
                    ordeNbr = customMaintOrderDTO.getOrdeNbr();
                    custommaintorderEntity.setOrde_nbr(ordeNbr);
                }
                // 1. 更新 客户维护单主表
                custommaintorderEntity.setModtime(new Date());
                custommaintorderEntity.setModuser(currentSessionEntity.getEmployeeId());
                ormService.update(custommaintorderEntity);

                // 得到客户维护单主表id，作为子表的外键
                String customMaintorderId = custommaintorderEntity.getId();

                // 2. 先删除 客户维护单-体系认证属性集，再添加
                deleteByPid(customMaintorderId, CumoCumoSystemSetaEntity.class);
                List<CumoCumoSystemSetaEntity> cumoSystemSet = custommaintorderEntity.getCumo_system_set();
                if (!ListUtils.isNullOrEmpty(cumoSystemSet)) {
                    EdmUtil.setPropertyBaseEntitiesSysColumns(CustommaintorderEntity.class, custommaintorderEntity,
                            cumoSystemSet, SQLCurdEnum.INSERT);
                    ormService.insert(cumoSystemSet);
                }

                // 3. 先删除 客户维护单-股东数据集属性集，再添加
                deleteByPid(customMaintorderId, CumoCumoHolderSetaEntity.class);
                List<CumoCumoHolderSetaEntity> cumoHolderSet = custommaintorderEntity.getCumo_holder_set();
                if (!ListUtils.isNullOrEmpty(cumoHolderSet)) {
                    // 设置前端传来的id为null
                    cumoHolderSet.forEach(item -> item.setId(null));
                    EdmUtil.setPropertyBaseEntitiesSysColumns(CustommaintorderEntity.class, custommaintorderEntity,
                            cumoHolderSet, SQLCurdEnum.INSERT);
                    ormService.insert(cumoHolderSet);
                }

                // 4. 先删除 客户维护单-客户数据集属性集，再添加
                // 查询 客户维护单-客户数据集数据，得到客户数据集id
                OrmParam cumoCustSetParam = new OrmParam();
                cumoCustSetParam.setWhereExp(cumoCustSetParam.getEqualXML(NodeConstant.PID, customMaintorderId));
                List<CumoCumoCustSetaEntity> cumoCustSetList = ormService.selectBeanList(CumoCumoCustSetaEntity.class, cumoCustSetParam);
                if (!ListUtils.isNullOrEmpty(cumoCustSetList)) {
                    String cumoCustSetOldId = cumoCustSetList.get(0).getId();

                    deleteByPid(customMaintorderId, CumoCumoCustSetaEntity.class);
                    List<CumoCumoCustSetaEntity> cumoCustSet = custommaintorderEntity.getCumo_cust_set();
                    if (!ListUtils.isNullOrEmpty(cumoCustSet)) {
                        CumoCumoCustSetaEntity cumoCumoCustSetaEntity = cumoCustSet.get(0);
                        EdmUtil.setPropertyBaseEntitiesSysColumns(CustommaintorderEntity.class, custommaintorderEntity,
                                cumoCustSet, SQLCurdEnum.INSERT);
                        String cumoCustSetId = ormService.insertSelective(cumoCumoCustSetaEntity).toString();
                        cumoCumoCustSetaEntity.setId(cumoCustSetId);

                        // 5. 先删除 客户维护单-客户数据集-联系人，再添加
                        deleteByPid(cumoCustSetOldId, CumoCumoContactSetbEntity.class);
                        List<CumoCumoContactSetbEntity> cumoContactSet = cumoCumoCustSetaEntity.getCumo_contact_set();
                        if (!ListUtils.isNullOrEmpty(cumoContactSet)) {
                            // 设置前端传来的id为null
                            cumoContactSet.forEach(item -> item.setId(null));
                            EdmUtil.setPropertyBaseEntitiesSysColumns(CumoCumoCustSetaEntity.class, cumoCumoCustSetaEntity,
                                    cumoContactSet, SQLCurdEnum.INSERT);
                            ormService.insert(cumoContactSet);
                        }

                        // 6.  先删除 客户维护单-客户数据集-附件资料，再添加
                        deleteByPid(cumoCustSetOldId, CumoCumoAttachSetbEntity.class);
                        List<CumoCumoAttachSetbEntity> cumoAttachSet = cumoCumoCustSetaEntity.getCumo_attach_set();
                        if (!ListUtils.isNullOrEmpty(cumoAttachSet)) {
                            // 设置前端传来的id为null
                            cumoAttachSet.forEach(item -> item.setId(null));
                            EdmUtil.setPropertyBaseEntitiesSysColumns(CumoCumoCustSetaEntity.class, cumoCumoCustSetaEntity,
                                    cumoAttachSet, SQLCurdEnum.INSERT);
                            ormService.insert(cumoAttachSet);
                        }

                        // 7. 先删除 客户维护单-客户数据集-服务团队，再添加
                        deleteByPid(cumoCustSetOldId, CumoCumoServtSetbEntity.class);
                        List<CumoCumoServtSetbEntity> cumoServtSet = cumoCumoCustSetaEntity.getCumo_servt_set();
                        if (!ListUtils.isNullOrEmpty(cumoServtSet)) {
                            // 设置前端传来的id为null
                            cumoServtSet.forEach(item -> item.setId(null));
                            EdmUtil.setPropertyBaseEntitiesSysColumns(CumoCumoCustSetaEntity.class, cumoCumoCustSetaEntity,
                                    cumoServtSet, SQLCurdEnum.INSERT);
                            ormService.insert(cumoServtSet);
                        }

                        // 8. 先删除 客户维护单-客户数据集-账户管理，再添加
                        deleteByPid(cumoCustSetOldId, CumoCumoAccountSetbEntity.class);
                        List<CumoCumoAccountSetbEntity> cumoAccountSet = cumoCumoCustSetaEntity.getCumo_account_set();
                        if (!ListUtils.isNullOrEmpty(cumoAccountSet)) {
                            // 设置前端传来的id为null
                            cumoAccountSet.forEach(item -> item.setId(null));
                            EdmUtil.setPropertyBaseEntitiesSysColumns(CumoCumoCustSetaEntity.class, cumoCumoCustSetaEntity,
                                    cumoAccountSet, SQLCurdEnum.INSERT);
                            ormService.insert(cumoAccountSet);
                        }

                        // 9. 先删除 客户维护单-客户数据集-交货管理，再添加
                        deleteByPid(cumoCustSetOldId, CumoCumoAccountSetbEntity.class);
                        List<CumoCumoDeliSetbEntity> cumoDeliSet = cumoCumoCustSetaEntity.getCumo_deli_set();
                        if (!ListUtils.isNullOrEmpty(cumoDeliSet)) {
                            // 设置前端传来的id为null
                            cumoDeliSet.forEach(item -> item.setId(null));
                            EdmUtil.setPropertyBaseEntitiesSysColumns(CumoCumoCustSetaEntity.class, cumoCumoCustSetaEntity,
                                    cumoDeliSet, SQLCurdEnum.INSERT);
                            ormService.insert(cumoDeliSet);
                        }
                    }
                }
                // 封装返回值
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", customMaintOrderDTO.getId());
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
        logger.info("客户维护单删除Service启用-----" + id);
        Result result = new Result();
        try {
            // 1. 删除 客户维护单主表
            int renInt = ormService.delete(CustommaintorderEntity.class, id);

            // 2. 删除 客户维护单-体系认证 属性集
            OrmParam cumoSystemSetDelParam = new OrmParam();
            cumoSystemSetDelParam.setWhereExp(cumoSystemSetDelParam.getEqualXML(NodeConstant.PID, id));
            ormService.delete(CumoCumoSystemSetaEntity.class, cumoSystemSetDelParam);

            // 3. 删除 客户维护单-股东数据集 属性集
            OrmParam cumoHolderSetDelParam = new OrmParam();
            cumoHolderSetDelParam.setWhereExp(cumoHolderSetDelParam.getEqualXML(NodeConstant.PID, id));
            ormService.delete(CumoCumoHolderSetaEntity.class, cumoHolderSetDelParam);

            // 获取客户维护单-客户数据集 (通过pid)
            OrmParam customSetSearchParam = new OrmParam();
            customSetSearchParam.setWhereExp(customSetSearchParam.getEqualXML(NodeConstant.PID, id));
            List<CumoCumoCustSetaEntity> cumoCustSetList = ormService.selectBeanList(CumoCumoCustSetaEntity.class, customSetSearchParam);
            if (!ListUtils.isNullOrEmpty(cumoCustSetList)) {
                for (CumoCumoCustSetaEntity cumoCustSet : cumoCustSetList) {
                    // 获取客户维护单-客户数据集id
                    String cumoCustSetId = cumoCustSet.getId();

                    // 4. 删除 客户维护单-客户数据集-联系人
                    OrmParam cumoContactSetDelParam = new OrmParam();
                    cumoContactSetDelParam.setWhereExp(cumoContactSetDelParam.getEqualXML(NodeConstant.PID, cumoCustSetId));
                    ormService.delete(CumoCumoContactSetbEntity.class, cumoContactSetDelParam);

                    // 5. 删除 客户维护单-客户数据集-附件资料
                    OrmParam cumoAttachSetDelParam = new OrmParam();
                    cumoAttachSetDelParam.setWhereExp(cumoAttachSetDelParam.getEqualXML(NodeConstant.PID, cumoCustSetId));
                    ormService.delete(CumoCumoAttachSetbEntity.class, cumoAttachSetDelParam);

                    // 6. 删除 客户维护单-客户数据集-服务团队
                    OrmParam cumoServtSetDelParam = new OrmParam();
                    cumoServtSetDelParam.setWhereExp(cumoServtSetDelParam.getEqualXML(NodeConstant.PID, cumoCustSetId));
                    ormService.delete(CumoCumoServtSetbEntity.class, cumoAttachSetDelParam);

                    // 7. 删除 客户维护单-客户数据集-账户管理
                    OrmParam cumoAccountSetDelParam = new OrmParam();
                    cumoAccountSetDelParam.setWhereExp(cumoAccountSetDelParam.getEqualXML(NodeConstant.PID, cumoCustSetId));
                    ormService.delete(CumoCumoAccountSetbEntity.class, cumoAccountSetDelParam);

                    // 8. 删除 客户维护单-客户数据集-交货管理
                    OrmParam cumoDeliSetDelParam = new OrmParam();
                    cumoDeliSetDelParam.setWhereExp(cumoDeliSetDelParam.getEqualXML(NodeConstant.PID, cumoCustSetId));
                    ormService.delete(CumoCumoDeliSetbEntity.class, cumoDeliSetDelParam);
                }
            }
            // 9. 删除 客户维护单-客户数据集 属性集 (这里修改为最后删除客户维护单-客户数据集 属性集，是为了删除先删除它的子表)
            OrmParam cumoCustSetDelParam = new OrmParam();
            cumoCustSetDelParam.setWhereExp(cumoCustSetDelParam.getEqualXML(NodeConstant.PID, id));
            ormService.delete(CumoCumoCustSetaEntity.class, cumoCustSetDelParam);

            result.setData(renInt);
        } catch (Exception e) {
            logger.error("删除客户维护单接口发生错误:", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }

    @Override
    public Result submit(CustomMaintOrderDTO customMaintOrderDTO) {
        logger.info("客户维护单提交服务开始..........");
        Result result = new Result();

        // 设置状态位,如果为true，表示直接点提交，没有先点保存，故save方法生成正式单号，反之生成临时单号
        customMaintOrderDTO.setSubmit(true);

        // 1.保存客户维护单
        Result saveResult = save(customMaintOrderDTO);
        JSONObject data = (JSONObject) saveResult.getData();
        // 单据id
        String customMaintOrderId = data.getString("id");
        // 单据号
        String ordeNbr = data.getString("ordeNbr");

        // 2. 获取单据定义对象
        String orderDefId = customMaintOrderDTO.getOrdeRodeObj();

        // TODO 此处是否需要添加 是否已经提交的校验， 可以参考人资-离职申请单提交方法

        // 3. 调用流程提交方法
        bizFormService.submitWorkFlow(orderDefId, customMaintOrderId);

        // 4. 更新单据状态,改为待审状态(2)
        updateCustomMaintOrderStatus(customMaintOrderId, OrderConstants.ORDE_STATUS_2);

        // 5. 封装返回值
        JSONObject obj = new JSONObject();
        obj.put("orderId", customMaintOrderId);
        obj.put("ordeNbr", ordeNbr);
        result.setData(obj);

        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result approve(String orderInstanceId, String handlerType) {
        logger.info("客户维护单批准通过服务开始," + orderInstanceId + "------批准方式:" + handlerType);
        Result result = new Result();
        try {
            // 根据请求方式的不同进行不同的处理逻辑
            switch (handlerType) {
                // 通过：保存客户数据到伙伴类主表
                case WFHandlerTypeConstants.PASS: {
                    // 根据cumo_uscc统一社会信用码字段到主表伙伴类中查，如果查不到就是新增，查到就是修改
                    RelationEntity relationEntity = selectByCumoUscc(selectByInstancdId(orderInstanceId).getCumo_uscc());
                    if (null == relationEntity) {
                        // 新增
                        saveCustom(orderInstanceId);
                    } else {
                        // 修改
                        // 注意：不允许删除主表信息，此处修改需要删除客户下面所有的属性集表，再次添加
                        updateCustom(orderInstanceId);
                    }
                    break;
                }
                // 撤销：修改单据状态为临时(1)
                case WFHandlerTypeConstants.REVOKE: {
                    updateCustomMaintOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_1);
                    break;
                }
                // 退回: 修改单据状态为退回(6)
                case WFHandlerTypeConstants.RETURN_BACK: {
                    updateCustomMaintOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_6);
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
    public Result checkCustomUnique(String checkField, String value, String relaId, String customMaintOrderId) {
        logger.info("客户维护单校验Service开始......");

        /**
         * 注意：
         * 1. 只需校验伙伴类以及客户维护单
         * 2. 统一社会信用码、伙伴编号由于更新时不可编辑，故不做排除自己处理
         */
        Result result = new Result();
        String errMsg = null;
        try {
            // 1. 统一社会信用码
            if (checkField.equals(CustomMaintOrderConstants.CUMO_USCC_CHECK)) {
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

                // 根据是否有id判断是新增校验还是更新校验
                if (StringUtil.isNullOrEmpty(relaId) && StringUtil.isNullOrEmpty(customMaintOrderId)) {
                    // 新增校验
                    // 查询 伙伴类
                    OrmParam relationParam = new OrmParam();
                    relationParam.setWhereExp(relationParam.getEqualXML(RelationConstants.RELA_USCC, value));
                    List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, relationParam);
                    if (null != relationEntityList && relationEntityList.size() > 0) {
                        relationId = relationEntityList.get(0).getId();
                        relaCode = relationEntityList.get(0).getRela_code();
                    }

                    //  查询 客户维护单
                    OrmParam customParam = new OrmParam();
                    customParam.setWhereExp(OrmParam.and(customParam.getEqualXML(CustomMaintOrderConstants.CUMO_USCC, value),
                            OrmParam.or(customParam.getIsNull(OrderConstants.ORDE_STATUS), customParam.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                    OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                    List<CustommaintorderEntity> custommaintEntityList = ormService.selectBeanList(CustommaintorderEntity.class, customParam);
                    if (null != custommaintEntityList && custommaintEntityList.size() > 0) {
                        orderId = custommaintEntityList.get(0).getId();
                        ordeNbr = custommaintEntityList.get(0).getOrde_nbr();
                        ordeProcbj = custommaintEntityList.get(0).getOrde_procobj();
                    }

                    /**
                     * 分三种情况:
                     * 1. 只存在伙伴 返回伙伴id，伙伴编号
                     * 2. 只存在单据 返回单据id，单据单号，流程定义id
                     * 3. 同时存在 返回伙伴id，伙伴编号，单据id，单据单号，流程定义id
                     * 4. 没有重复数据
                     */
                    JSONObject jsonObject = new JSONObject();

                    boolean onlyRelation = (null != relationEntityList && relationEntityList.size() > 0) && (null == custommaintEntityList || custommaintEntityList.size() == 0);
                    boolean onlyMaint = (null == relationEntityList || relationEntityList.size() == 0) && (null != custommaintEntityList && custommaintEntityList.size() > 0);
                    boolean bothHave = (null != relationEntityList && relationEntityList.size() > 0) && (null != custommaintEntityList && custommaintEntityList.size() > 0);

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

                    //  查询 客户维护单
                    OrmParam customParam = new OrmParam();
                    customParam.setWhereExp(OrmParam.and(customParam.getEqualXML(CustomMaintOrderConstants.CUMO_USCC, value),
                            customParam.getUnequalXML(NodeConstant.ID, customMaintOrderId),
                            OrmParam.or(customParam.getIsNull(OrderConstants.ORDE_STATUS), customParam.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                    OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                    List<CustommaintorderEntity> custommaintEntityList = ormService.selectBeanList(CustommaintorderEntity.class, customParam);
                    if (null != custommaintEntityList && custommaintEntityList.size() > 0) {
                        orderId = custommaintEntityList.get(0).getId();
                        ordeNbr = custommaintEntityList.get(0).getOrde_nbr();
                        ordeProcbj = custommaintEntityList.get(0).getOrde_procobj();
                    }

                    /**
                     * 分三种情况:
                     * 1. 只存在伙伴 返回伙伴id，伙伴编号
                     * 2. 只存在单据 返回单据id，单据单号，流程定义id
                     * 3. 同时存在 返回伙伴id，伙伴编号，单据id，单据单号，流程定义id
                     * 4. 没有重复数据
                     */
                    JSONObject jsonObject = new JSONObject();

                    boolean onlyRelation = (null != relationEntityList && relationEntityList.size() > 0) && (null == custommaintEntityList || custommaintEntityList.size() == 0);
                    boolean onlyMaint = (null == relationEntityList || relationEntityList.size() == 0) && (null != custommaintEntityList && custommaintEntityList.size() > 0);
                    boolean bothHave = (null != relationEntityList && relationEntityList.size() > 0) && (null != custommaintEntityList && custommaintEntityList.size() > 0);

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
            } else if (checkField.equals(CustomMaintOrderConstants.CUMO_CODE_CHECK)) {
                // 根据是否有id判断是新增校验还是更新校验
                if (StringUtil.isNullOrEmpty(relaId) && StringUtil.isNullOrEmpty(customMaintOrderId)) {
                    // 新增校验
                    boolean isRepet = false;

                    // 查询 伙伴类
                    OrmParam relationParam = new OrmParam();
                    relationParam.setWhereExp(relationParam.getEqualXML(RelationConstants.RELA_CODE, value));
                    List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, relationParam);
                    if (null != relationEntityList && relationEntityList.size() > 0) {
                        isRepet = true;
                    }

                    //  查询 客户维护单
                    OrmParam customParam = new OrmParam();
                    customParam.setWhereExp(OrmParam.and(customParam.getEqualXML(CustomMaintOrderConstants.CUMO_CODE, value),
                            OrmParam.or(customParam.getIsNull(OrderConstants.ORDE_STATUS), customParam.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                    OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                    List<CustommaintorderEntity> custommaintEntityList = ormService.selectBeanList(CustommaintorderEntity.class, customParam);
                    if (null != custommaintEntityList && custommaintEntityList.size() > 0) {
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

                    //  查询 客户维护单
                    OrmParam customParam = new OrmParam();
                    customParam.setWhereExp(OrmParam.and(customParam.getEqualXML(CustomMaintOrderConstants.CUMO_CODE, value),
                            customParam.getUnequalXML(NodeConstant.ID, customMaintOrderId),
                            OrmParam.or(customParam.getIsNull(OrderConstants.ORDE_STATUS), customParam.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                    OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                    List<CustommaintorderEntity> custommaintEntityList = ormService.selectBeanList(CustommaintorderEntity.class, customParam);
                    if (null != custommaintEntityList && custommaintEntityList.size() > 0) {
                        isRepet = true;
                    }

                    if (isRepet) {
                        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                        errMsg = "伙伴编号重复！";
                        result.setErrMsg(errMsg);
                    }
                }
                // 3. 伙伴简称
            } else if (checkField.equals(CustomMaintOrderConstants.CUMO_SHORT_NAME_CHECK)) {
                // 根据是否有id判断是新增校验还是更新校验
                if (StringUtil.isNullOrEmpty(relaId) && StringUtil.isNullOrEmpty(customMaintOrderId)) {
                    // 新增校验
                    boolean isRepet = false;

                    // 查询 伙伴类
                    OrmParam relationParam = new OrmParam();
                    relationParam.setWhereExp(relationParam.getEqualXML(RelationConstants.RELA_SHORT_NAME, value));
                    List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, relationParam);
                    if (null != relationEntityList && relationEntityList.size() > 0) {
                        isRepet = true;
                    }

                    //  查询 客户维护单
                    OrmParam customParam = new OrmParam();
                    customParam.setWhereExp(OrmParam.and(customParam.getEqualXML(CustomMaintOrderConstants.CUMO_SHORT_NAME, value),
                            OrmParam.or(customParam.getIsNull(OrderConstants.ORDE_STATUS), customParam.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                    OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                    List<CustommaintorderEntity> custommaintEntityList = ormService.selectBeanList(CustommaintorderEntity.class, customParam);
                    if (null != custommaintEntityList && custommaintEntityList.size() > 0) {
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

                    //  查询 客户维护单
                    OrmParam customParam = new OrmParam();
                    customParam.setWhereExp(OrmParam.and(customParam.getEqualXML(CustomMaintOrderConstants.CUMO_SHORT_NAME, value),
                            customParam.getUnequalXML(NodeConstant.ID, customMaintOrderId),
                            OrmParam.or(customParam.getIsNull(OrderConstants.ORDE_STATUS), customParam.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                    OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                    List<CustommaintorderEntity> custommaintEntityList = ormService.selectBeanList(CustommaintorderEntity.class, customParam);
                    if (null != custommaintEntityList && custommaintEntityList.size() > 0) {
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
            throw new ApplicationException(Result.RECODE_ERROR, "客户维护单唯一性校验出现错误:" + e.getMessage());
        }
        return result;
    }

    @Override
    public Result getCustomMaintOrderByCode(String cumoCode) {
        Result result = new Result();
        try {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML(CustommaintorderProperty.CUMO_CODE, cumoCode));

            List<CustommaintorderEntity> custommaintorderEntityList = ormService.selectBeanList(CustommaintorderEntity.class, ormParam);
            if (!ListUtils.isNullOrEmpty(custommaintorderEntityList)) {
                CustommaintorderEntity custommaintorderEntity = custommaintorderEntityList.get(0);
                JSONObject jsonObject = (JSONObject) JSON.toJSON(custommaintorderEntity);
                // 下划线转驼峰
                JsonUtils.underLine2Camel(jsonObject);
                result.setData(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, "根据伙伴编码查询客户维护单失败:" + e.getMessage());
        }
        return result;
    }

    @Override
    public Result getRelationByCode(String cumoCode) {
        Result result = new Result();

        try {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML(RelationProperty.RELA_CODE, cumoCode));

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
     * 更新客户维护单单据状态
     *
     * @param orderInstanceId 单据id
     * @param orderStatus     状态码
     */
    private void updateCustomMaintOrderStatus(String orderInstanceId, String orderStatus) {

        // 1. 准备数据
        CustomMaintOrderDTO customMaintOrderDTO = new CustomMaintOrderDTO();
        customMaintOrderDTO.setId(orderInstanceId);
        customMaintOrderDTO.setOrdeStatus(orderStatus);

        // DTO转Entity
        CustommaintorderEntity custommaintorderEntity = JSONObject.parseObject(JSON.toJSONString(customMaintOrderDTO), CustommaintorderEntity.class);

        // 2. 更新表
        // 根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity currentSessionEntity = bizFormService.getCurrentSessionInfo();
        // 制单时间
        custommaintorderEntity.setOrde_date(new Date());
        // 所属企业对象id
        custommaintorderEntity.setEdmd_ente(currentSessionEntity.getEnterpriseId());
        // 修改人
        custommaintorderEntity.setModuser(currentSessionEntity.getEmployeeId());

        try {
            ormService.updateSelective(custommaintorderEntity);
        } catch (Exception e) {
            logger.error("更新客户维护单状态出现异常", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, "更新客户维护单状态出现异常：" + e.getMessage());
        }
    }

    /**
     * 根据单据id查询客户维护单
     *
     * @param instanceId
     * @return
     */
    private CustommaintorderEntity selectByInstancdId(String instanceId) {
        CustommaintorderEntity custommaintorderEntity = null;
        try {
            custommaintorderEntity = ormService.load(CustommaintorderEntity.class, instanceId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return custommaintorderEntity;
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
            ormParam.setWhereExp((ormParam.getEqualXML(EdmSysColumn.PID, pid)));
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
            logger.error("根据pid修改客户维护单出现异常：", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
    }

    /**
     * 根据统一社会信用代码查询伙伴类
     *
     * @param cumoUscc
     * @return
     */
    private RelationEntity selectByCumoUscc(String cumoUscc) {
        try {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML(RelationProperty.RELA_USCC, cumoUscc));
            List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, ormParam);
            if (!ListUtils.isNullOrEmpty(relationEntityList)) {
                return relationEntityList.get(0);
            }
        } catch (Exception e) {
            logger.error("根据统一社会信用代码查询伙伴类出现异常:", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return null;
    }

    /**
     * 批准通过-保存客户
     *
     * @param orderInstanceId 客户维护单据id
     */
    private String saveCustom(String orderInstanceId) {
        try {

            // 获取当前session信息
            CurrentSessionEntity currentSessionEntity = bizFormService.getCurrentSessionInfo();

            // 1. 根据单据id查询到维护单的数据
            CustommaintorderEntity custommaintorderEntity = selectByInstancdId(orderInstanceId);

            // 2. 封装伙伴类基本信息(主表)，并插入
            RelationEntity relationEntity = new RelationEntity();

            relationEntity.setRela_code(custommaintorderEntity.getCumo_code());
            relationEntity.setRela_name(custommaintorderEntity.getCumo_name());
            relationEntity.setRela_short_name(custommaintorderEntity.getCumo_short_name());
            relationEntity.setRela_remark(custommaintorderEntity.getCumo_remark());
            relationEntity.setRela_addrp(custommaintorderEntity.getCumo_addrp());
            relationEntity.setRela_addrc(custommaintorderEntity.getCumo_addrc());
            relationEntity.setRela_addrl(custommaintorderEntity.getCumo_addrl());
            relationEntity.setRela_daddr(custommaintorderEntity.getCumo_daddr());
            relationEntity.setRela_pcode(custommaintorderEntity.getCumo_pcode());
            relationEntity.setRela_tel(custommaintorderEntity.getCumo_tel());
            relationEntity.setRela_fax(custommaintorderEntity.getCumo_fax());
            relationEntity.setRela_web(custommaintorderEntity.getCumo_web());
            relationEntity.setRela_mprod(custommaintorderEntity.getCumo_mprod());
            relationEntity.setRela_character(custommaintorderEntity.getCumo_character());
            relationEntity.setRela_listexch(custommaintorderEntity.getCumo_listexch());
            relationEntity.setRela_listname(custommaintorderEntity.getCumo_listname());
            relationEntity.setRela_listcode(custommaintorderEntity.getCumo_listcode());
            relationEntity.setRela_uscc(custommaintorderEntity.getCumo_uscc());
            relationEntity.setRela_raddr(custommaintorderEntity.getCumo_raddr());
            relationEntity.setRela_rdate(custommaintorderEntity.getCumo_rdate());
            relationEntity.setRela_lrep(custommaintorderEntity.getCumo_lrep());
            relationEntity.setRela_laddr(custommaintorderEntity.getCumo_laddr());
            relationEntity.setRela_rcurr(custommaintorderEntity.getCumo_rcurr());
            relationEntity.setRela_rcapital(custommaintorderEntity.getCumo_rcapital());

            String relationId = ormService.insertSelective(relationEntity).toString();

            // 3. 封装伙伴类-伙伴类型集，并插入
            RelaRelaTypesSetaEntity relaTypesSetEntity = new RelaRelaTypesSetaEntity();

            relaTypesSetEntity.setPid(relationId);
            relaTypesSetEntity.setClassName(RelationConstants.className);
            relaTypesSetEntity.setCreuser(currentSessionEntity.getEmployeeId());
            relaTypesSetEntity.setRela_type(RelaTypeConstants.RELATION_TYPE_CUSTOM);

            ormService.insertSelective(relaTypesSetEntity);

            // 4. 封装伙伴类-体系认证集，并插入
            List<RelaRelaSystemSetaEntity> relaSystemSetaEntityList = new ArrayList<>();

            List<CumoCumoSystemSetaEntity> cumoSystemSetaList = selectByPid(orderInstanceId, CumoCumoSystemSetaEntity.class);
            if (null != cumoSystemSetaList && cumoSystemSetaList.size() > 0) {
                for (CumoCumoSystemSetaEntity cumoSystemSet : cumoSystemSetaList) {
                    RelaRelaSystemSetaEntity relaSystemSetaEntity = new RelaRelaSystemSetaEntity();
                    relaSystemSetaEntity.setPid(relationId);
                    relaSystemSetaEntity.setClassName(RelationConstants.className);
                    relaSystemSetaEntity.setCreuser(currentSessionEntity.getEmployeeId());
                    relaSystemSetaEntity.setRela_sname(cumoSystemSet.getCumo_sname());

                    relaSystemSetaEntityList.add(relaSystemSetaEntity);
                }
            }
            ormService.insert(relaSystemSetaEntityList);

            // 5. 封装伙伴类-股东集，并插入
            List<RelaRelaHolderSetaEntity> relaHolderSetaEntityList = new ArrayList<>();

            List<CumoCumoHolderSetaEntity> cumoHolderSetParamSetaList = selectByPid(orderInstanceId, CumoCumoHolderSetaEntity.class);

            if (null != cumoHolderSetParamSetaList && cumoHolderSetParamSetaList.size() > 0) {

                for (CumoCumoHolderSetaEntity cumoHolderSet : cumoHolderSetParamSetaList) {

                    RelaRelaHolderSetaEntity relaHolderSetaEntity = new RelaRelaHolderSetaEntity();

                    relaHolderSetaEntity.setPid(relationId);
                    relaHolderSetaEntity.setClassName(RelationConstants.className);
                    relaHolderSetaEntity.setCreuser(currentSessionEntity.getEmployeeId());
                    relaHolderSetaEntity.setRela_hname(cumoHolderSet.getCumo_hname());
                    relaHolderSetaEntity.setRela_hrate(cumoHolderSet.getCumo_hrate());

                    relaHolderSetaEntityList.add(relaHolderSetaEntity);
                }
            }
            ormService.insert(relaHolderSetaEntityList);

            // 6. 封装伙伴类-客户数据集，并插入
            // 注意：伙伴类跟客户数据集之间是一对一关系，故此处不需要批量插入

            List<CumoCumoCustSetaEntity> cumoCustSetList = selectByPid(orderInstanceId, CumoCumoCustSetaEntity.class);

            if (null != cumoCustSetList && cumoCustSetList.size() > 0) {

                CumoCumoCustSetaEntity cumoCustSet = cumoCustSetList.get(0);

                RelaRelaCustSetaEntity relaCustSetaEntity = new RelaRelaCustSetaEntity();

                relaCustSetaEntity.setPid(relationId);
                relaCustSetaEntity.setClassName(RelationConstants.className);
                relaCustSetaEntity.setCreuser(currentSessionEntity.getEmployeeId());
                relaCustSetaEntity.setRela_stat_cust(cumoCustSet.getCumo_stat());
                relaCustSetaEntity.setRela_servdept_cust(cumoCustSet.getCumo_servdept());
                relaCustSetaEntity.setRela_credlimit(cumoCustSet.getCumo_credlimit());
                relaCustSetaEntity.setRela_sett_wayc(cumoCustSet.getCumo_sett_way());

                // 插入伙伴类-客户数据集表，并返回应商数据集id
                String relaCustSetId = ormService.insert(relaCustSetaEntity).toString();

                // 查询客户维护单-客户数据集的id
                String custSetId = cumoCustSet.getId();

                // 7. 封装伙伴类-客户数据集-客户联系人集,并插入
                List<RelaRelaContactCustSetbEntity> relaContactCustSetbEntityList = new ArrayList<>();

                List<CumoCumoContactSetbEntity> cumoContactSetList = selectByPid(custSetId, CumoCumoContactSetbEntity.class);

                if (null != cumoContactSetList && cumoContactSetList.size() > 0) {
                    for (CumoCumoContactSetbEntity cumoContactSet : cumoContactSetList) {

                        RelaRelaContactCustSetbEntity relaContactCustSetbEntity = new RelaRelaContactCustSetbEntity();

                        relaContactCustSetbEntity.setPid(relaCustSetId);
                        relaContactCustSetbEntity.setClassName(RelationConstants.className);
                        relaContactCustSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                        relaContactCustSetbEntity.setRela_conttype_cust(cumoContactSet.getCumo_conttype());
                        relaContactCustSetbEntity.setRela_contname_cust(cumoContactSet.getCumo_contname());
                        relaContactCustSetbEntity.setRela_contsex_cust(cumoContactSet.getCumo_contsex());
                        relaContactCustSetbEntity.setRela_contpost_cust(cumoContactSet.getCumo_contpost());
                        relaContactCustSetbEntity.setRela_conttel_cust(cumoContactSet.getCumo_conttel());
                        relaContactCustSetbEntity.setRela_contemail_cust(cumoContactSet.getCumo_contemail());
                        relaContactCustSetbEntity.setRela_contother_cust(cumoContactSet.getCumo_contother());

                        relaContactCustSetbEntityList.add(relaContactCustSetbEntity);
                    }
                }

                ormService.insert(relaContactCustSetbEntityList);

                // 8. 封装伙伴类-客户数据集-附件资料集,并插入
                List<RelaRelaAttachCustSetbEntity> relaAttachCustSetbEntityList = new ArrayList<>();

                List<CumoCumoAttachSetbEntity> cumoAttachSetList = selectByPid(custSetId, CumoCumoAttachSetbEntity.class);

                if (null != cumoAttachSetList && cumoAttachSetList.size() > 0) {
                    for (CumoCumoAttachSetbEntity cumoAttachSet : cumoAttachSetList) {

                        RelaRelaAttachCustSetbEntity relaAttachCustSetbEntity = new RelaRelaAttachCustSetbEntity();

                        relaAttachCustSetbEntity.setPid(relaCustSetId);
                        relaAttachCustSetbEntity.setClassName(RelationConstants.className);
                        relaAttachCustSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                        relaAttachCustSetbEntity.setRela_attatype_cust(cumoAttachSet.getCumo_attatype());
                        relaAttachCustSetbEntity.setRela_attaaddr_cust(cumoAttachSet.getCumo_attaaddr());
                        relaAttachCustSetbEntity.setRela_attavalid_cust(cumoAttachSet.getCumo_attavalid());

                        relaAttachCustSetbEntityList.add(relaAttachCustSetbEntity);
                    }
                }

                ormService.insert(relaAttachCustSetbEntityList);

                // 9. 封装伙伴类-客户数据集-服务团队集,并插入
                List<RelaRelaServtCustSetbEntity> relaServtCustSetbEntityList = new ArrayList<>();

                List<CumoCumoServtSetbEntity> cumoServtSetList = selectByPid(custSetId, CumoCumoServtSetbEntity.class);

                if (null != cumoServtSetList && cumoServtSetList.size() > 0) {

                    for (CumoCumoServtSetbEntity cumoServtSet : cumoServtSetList) {

                        RelaRelaServtCustSetbEntity relaServtCustSetbEntity = new RelaRelaServtCustSetbEntity();

                        relaServtCustSetbEntity.setPid(relaCustSetId);
                        relaServtCustSetbEntity.setClassName(RelationConstants.className);
                        relaServtCustSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                        relaServtCustSetbEntity.setRela_sttype_cust(cumoServtSet.getCumo_sttype());
                        relaServtCustSetbEntity.setRela_stemp_cust(cumoServtSet.getCumo_stemp());

                        relaServtCustSetbEntityList.add(relaServtCustSetbEntity);
                    }
                }

                ormService.insert(relaServtCustSetbEntityList);

                // 10. 封装伙伴类-客户数据集-账户管理集,并插入
                List<RelaRelaAccountCustSetbEntity> relaAccountCustSetbEntityList = new ArrayList<>();

                List<CumoCumoAccountSetbEntity> cumoAccountSetList = selectByPid(custSetId, CumoCumoAccountSetbEntity.class);

                if (null != cumoAccountSetList && cumoAccountSetList.size() > 0) {

                    for (CumoCumoAccountSetbEntity cumoCumoAccountSet : cumoAccountSetList) {

                        RelaRelaAccountCustSetbEntity relaAccountCustSetbEntity = new RelaRelaAccountCustSetbEntity();

                        relaAccountCustSetbEntity.setPid(relaCustSetId);
                        relaAccountCustSetbEntity.setClassName(RelationConstants.className);
                        relaAccountCustSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                        relaAccountCustSetbEntity.setRela_acconame_cust(cumoCumoAccountSet.getCumo_acconame());
                        relaAccountCustSetbEntity.setRela_accobank_cust(cumoCumoAccountSet.getCumo_accobank());
                        relaAccountCustSetbEntity.setRela_acconum_cust(cumoCumoAccountSet.getCumo_acconum());
                        relaAccountCustSetbEntity.setRela_accocurr_cust(cumoCumoAccountSet.getCumo_accocurr());
                        relaAccountCustSetbEntity.setRela_accobeg_cust(cumoCumoAccountSet.getCumo_accobeg());
                        relaAccountCustSetbEntity.setRela_accoend_cust(cumoCumoAccountSet.getCumo_accoend());
                        relaAccountCustSetbEntity.setRela_attaacco_cust(cumoCumoAccountSet.getCumo_attaacco());

                        relaAccountCustSetbEntityList.add(relaAccountCustSetbEntity);
                    }
                }

                ormService.insert(relaAccountCustSetbEntityList);

                // 11. 封装伙伴类-客户数据集-交货地址集,并插入
                List<RelaRelaDeliCustSetbEntity> relaDeliCustSetbEntityList = new ArrayList<>();

                List<CumoCumoDeliSetbEntity> cumoDeliSetList = selectByPid(custSetId, CumoCumoDeliSetbEntity.class);

                if (null != cumoDeliSetList && cumoDeliSetList.size() > 0) {

                    for (CumoCumoDeliSetbEntity cumoDeliSet : cumoDeliSetList) {

                        RelaRelaDeliCustSetbEntity relaDeliCustSetbEntity = new RelaRelaDeliCustSetbEntity();

                        relaDeliCustSetbEntity.setPid(relaCustSetId);
                        relaDeliCustSetbEntity.setClassName(RelationConstants.className);
                        relaDeliCustSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                        relaDeliCustSetbEntity.setRela_daname_cust(cumoDeliSet.getCumo_daname());
                        relaDeliCustSetbEntity.setRela_daddrp_cust(cumoDeliSet.getCumo_daddrp());
                        relaDeliCustSetbEntity.setRela_daddrc_cust(cumoDeliSet.getCumo_daddrc());
                        relaDeliCustSetbEntity.setRela_daddrl_cust(cumoDeliSet.getCumo_daddrl());
                        relaDeliCustSetbEntity.setRela_ddaddr_cust(cumoDeliSet.getCumo_ddaddr());
                        relaDeliCustSetbEntity.setRela_dcontact_cust(cumoDeliSet.getCumo_dcontact());
                        relaDeliCustSetbEntity.setRela_dcway_cust(cumoDeliSet.getCumo_dcway());

                        relaDeliCustSetbEntityList.add(relaDeliCustSetbEntity);
                    }
                }
                ormService.insert(relaDeliCustSetbEntityList);

                // 修改单据状态为 完成 - 5
                updateCustomMaintOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_5);
            }
        } catch (Exception e) {
            logger.error("新增批准通过保存客户出现错误:", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return "";
    }

    /**
     * 批准通过-修改客户
     *
     * @param orderInstanceId 客户维护单据id
     */
    private String updateCustom(String orderInstanceId) {

        // 业务：修改伙伴类-客户数据集下面的子表之前，需要把子表中的内容全部删除，再添加，以完成修改

        try {

            // 获取当前session信息
            CurrentSessionEntity currentSessionEntity = bizFormService.getCurrentSessionInfo();

            // 1. 修改 伙伴类 主表信息
            CustommaintorderEntity custommaintorderEntity = selectByInstancdId(orderInstanceId);

            RelationEntity relationEntity = new RelationEntity();

            relationEntity.setRela_code(custommaintorderEntity.getCumo_code());
            relationEntity.setRela_name(custommaintorderEntity.getCumo_name());
            relationEntity.setRela_short_name(custommaintorderEntity.getCumo_short_name());
            relationEntity.setRela_remark(custommaintorderEntity.getCumo_remark());
            relationEntity.setRela_addrp(custommaintorderEntity.getCumo_addrp());
            relationEntity.setRela_addrc(custommaintorderEntity.getCumo_addrc());
            relationEntity.setRela_addrl(custommaintorderEntity.getCumo_addrl());
            relationEntity.setRela_daddr(custommaintorderEntity.getCumo_daddr());
            relationEntity.setRela_pcode(custommaintorderEntity.getCumo_pcode());
            relationEntity.setRela_tel(custommaintorderEntity.getCumo_tel());
            relationEntity.setRela_fax(custommaintorderEntity.getCumo_fax());
            relationEntity.setRela_web(custommaintorderEntity.getCumo_web());
            relationEntity.setRela_mprod(custommaintorderEntity.getCumo_mprod());
            relationEntity.setRela_character(custommaintorderEntity.getCumo_character());
            relationEntity.setRela_listexch(custommaintorderEntity.getCumo_listexch());
            relationEntity.setRela_listname(custommaintorderEntity.getCumo_listname());
            relationEntity.setRela_listcode(custommaintorderEntity.getCumo_listcode());
            relationEntity.setRela_uscc(custommaintorderEntity.getCumo_uscc());
            relationEntity.setRela_raddr(custommaintorderEntity.getCumo_raddr());
            relationEntity.setRela_rdate(custommaintorderEntity.getCumo_rdate());
            relationEntity.setRela_lrep(custommaintorderEntity.getCumo_lrep());
            relationEntity.setRela_laddr(custommaintorderEntity.getCumo_laddr());
            relationEntity.setRela_rcurr(custommaintorderEntity.getCumo_rcurr());
            relationEntity.setRela_rcapital(custommaintorderEntity.getCumo_rcapital());

            OrmParam relaSearchParam = new OrmParam();
            relaSearchParam.setWhereExp(relaSearchParam.getEqualXML(RelationProperty.RELA_CODE, custommaintorderEntity.getCumo_code()));

            ormService.updateSelective(relationEntity, relaSearchParam);

            // 通过伙伴编号唯一查找伙伴,获取伙伴类id
            List<RelationEntity> relationEntityList = ormService.selectBeanList(RelationEntity.class, relaSearchParam);
            if (null != relationEntityList && relationEntityList.size() > 0) {

                // 由于伙伴编号唯一，故此处取第一个值
                RelationEntity relation = relationEntityList.get(0);

                // 伙伴类id
                String relationId = relation.getId();

                // 2. 修改 伙伴类-体系认证
                List<CumoCumoSystemSetaEntity> cumoSystemSetList = selectByPid(orderInstanceId, CumoCumoSystemSetaEntity.class);

                if (null != cumoSystemSetList && cumoSystemSetList.size() > 0) {

                    for (CumoCumoSystemSetaEntity cumoSystemSet : cumoSystemSetList) {

                        RelaRelaSystemSetaEntity relaSystemSetaEntity = new RelaRelaSystemSetaEntity();

                        relaSystemSetaEntity.setRela_sname(cumoSystemSet.getCumo_sname());

                        updateByPid(relaSystemSetaEntity, relationId);
                    }
                }

                // 3. 修改 伙伴类-股东数据集
                List<CumoCumoHolderSetaEntity> cumoHolderSetParamSetList = selectByPid(orderInstanceId, CumoCumoHolderSetaEntity.class);

                if (null != cumoHolderSetParamSetList && cumoHolderSetParamSetList.size() > 0) {

                    for (CumoCumoHolderSetaEntity cumoHolderSet : cumoHolderSetParamSetList) {

                        RelaRelaHolderSetaEntity relaHolderSetaEntity = new RelaRelaHolderSetaEntity();

                        relaHolderSetaEntity.setRela_hname(cumoHolderSet.getCumo_hname());
                        relaHolderSetaEntity.setRela_hrate(cumoHolderSet.getCumo_hrate());

                        updateByPid(relaHolderSetaEntity, relationId);
                    }
                }

                // 4. 修改 伙伴类-客户数据集
                List<CumoCumoCustSetaEntity> cumoCustSetList = selectByPid(orderInstanceId, CumoCumoCustSetaEntity.class);

                if (null != cumoCustSetList && cumoCustSetList.size() > 0) {
                    // 伙伴类跟客户数据集之间是一对一关系，故此处不需要批量处理
                    CumoCumoCustSetaEntity cumoCustomSet = cumoCustSetList.get(0);

                    RelaRelaCustSetaEntity relaRelaCustomSetaEntity = new RelaRelaCustSetaEntity();

                    relaRelaCustomSetaEntity.setRela_stat_cust(cumoCustomSet.getCumo_stat());
                    relaRelaCustomSetaEntity.setRela_servdept_cust(cumoCustomSet.getCumo_servdept());
                    relaRelaCustomSetaEntity.setRela_credlimit(cumoCustomSet.getCumo_credlimit());
                    relaRelaCustomSetaEntity.setRela_sett_wayc(cumoCustomSet.getCumo_sett_way());

                    updateByPid(relaRelaCustomSetaEntity, relationId);
                }

                // 5. 删除 伙伴类-客户数据集 下面的所有属性集
                // 获取伙伴类-客户数据集的id
                OrmParam relaCustomSetParam = new OrmParam();
                relaCustomSetParam.setWhereExp(relaCustomSetParam.getEqualXML(NodeConstant.PID, relationId));

                List<RelaRelaCustSetaEntity> relaCustomSetList = ormService.selectBeanList(RelaRelaCustSetaEntity.class, relaCustomSetParam);

                if (null != relaCustomSetList && relaCustomSetList.size() > 0) {
                    // 伙伴类跟客户数据集之间是一对一关系，故此处不需要批量处理
                    RelaRelaCustSetaEntity relaCustomSet = relaCustomSetList.get(0);

                    // 遍历伙伴类-客户数据集,得到id,作为条件，删除下面的子表
                    OrmParam relaCustomSetDelParam = new OrmParam();
                    relaCustomSetDelParam.setWhereExp(relaCustomSetDelParam.getEqualXML(NodeConstant.PID, relaCustomSet.getId()));

                    // 删除 伙伴类-客户数据集-联系人集
                    ormService.delete(RelaRelaContactCustSetbEntity.class, relaCustomSetDelParam);
                    // 删除 伙伴类-客户数据集-附件资料集
                    ormService.delete(RelaRelaAttachCustSetbEntity.class, relaCustomSetDelParam);
                    // 删除 伙伴类-客户数据集-服务团队集
                    ormService.delete(RelaRelaServtCustSetbEntity.class, relaCustomSetDelParam);
                    // 删除 伙伴类-客户数据集-账户管理集
                    ormService.delete(RelaRelaAccountCustSetbEntity.class, relaCustomSetDelParam);
                    // 删除 伙伴类-客户数据集-交货地址集
                    ormService.delete(RelaRelaDeliCustSetbEntity.class, relaCustomSetDelParam);

                    // 6. 新增 伙伴类-客户数据集-联系人集
                    List<CumoCumoCustSetaEntity> cumoCustomSetaList = selectByPid(orderInstanceId, CumoCumoCustSetaEntity.class);

                    if (null != cumoCustomSetaList && cumoCustomSetaList.size() > 0) {
                        // 客户维护单跟客户属性集之间是一对一关系
                        CumoCumoCustSetaEntity cumoCustomSet = cumoCustomSetaList.get(0);

                        String customSetId = cumoCustomSet.getId();

                        List<RelaRelaContactCustSetbEntity> relaContactCustSetbEntityList = new ArrayList<>();

                        List<CumoCumoContactSetbEntity> cumoContactSetList = selectByPid(customSetId, CumoCumoContactSetbEntity.class);

                        if (null != cumoContactSetList && cumoContactSetList.size() > 0) {

                            for (CumoCumoContactSetbEntity cumoContactSet : cumoContactSetList) {

                                RelaRelaContactCustSetbEntity relaContactCustSetbEntity = new RelaRelaContactCustSetbEntity();

                                relaContactCustSetbEntity.setPid(relaCustomSet.getId());
                                relaContactCustSetbEntity.setClassName(RelationConstants.className);
                                relaContactCustSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                                relaContactCustSetbEntity.setRela_conttype_cust(cumoContactSet.getCumo_conttype());
                                relaContactCustSetbEntity.setRela_contname_cust(cumoContactSet.getCumo_contname());
                                relaContactCustSetbEntity.setRela_contsex_cust(cumoContactSet.getCumo_contsex());
                                relaContactCustSetbEntity.setRela_contpost_cust(cumoContactSet.getCumo_contpost());
                                relaContactCustSetbEntity.setRela_conttel_cust(cumoContactSet.getCumo_conttel());
                                relaContactCustSetbEntity.setRela_contemail_cust(cumoContactSet.getCumo_contemail());
                                relaContactCustSetbEntity.setRela_contother_cust(cumoContactSet.getCumo_contother());

                                relaContactCustSetbEntityList.add(relaContactCustSetbEntity);
                            }
                        }
                        ormService.insert(relaContactCustSetbEntityList);

                        // 7. 新增 伙伴类-客户数据集-附件资料集
                        List<RelaRelaAttachCustSetbEntity> relaAttachCustSetbEntityList = new ArrayList<>();

                        List<CumoCumoAttachSetbEntity> cumoAttachSetList = selectByPid(customSetId, CumoCumoAttachSetbEntity.class);

                        if (null != cumoAttachSetList && cumoAttachSetList.size() > 0) {
                            for (CumoCumoAttachSetbEntity cumoAttachSet : cumoAttachSetList) {

                                RelaRelaAttachCustSetbEntity relaAttachCustSetbEntity = new RelaRelaAttachCustSetbEntity();

                                relaAttachCustSetbEntity.setPid(relaCustomSet.getId());
                                relaAttachCustSetbEntity.setClassName(RelationConstants.className);
                                relaAttachCustSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                                relaAttachCustSetbEntity.setRela_attatype_cust(cumoAttachSet.getCumo_attatype());
                                relaAttachCustSetbEntity.setRela_attaaddr_cust(cumoAttachSet.getCumo_attaaddr());
                                relaAttachCustSetbEntity.setRela_attavalid_cust(cumoAttachSet.getCumo_attavalid());

                                relaAttachCustSetbEntityList.add(relaAttachCustSetbEntity);
                            }
                        }

                        ormService.insert(relaAttachCustSetbEntityList);

                        // 8. 新增 伙伴类-客户数据集-服务团队集
                        List<RelaRelaServtCustSetbEntity> relaServtCustSetbList = new ArrayList<>();

                        List<CumoCumoServtSetbEntity> cumoServtSetbList = selectByPid(customSetId, CumoCumoServtSetbEntity.class);

                        if (null != cumoServtSetbList && cumoServtSetbList.size() > 0) {

                            for (CumoCumoServtSetbEntity cumoServtSet : cumoServtSetbList) {

                                RelaRelaServtCustSetbEntity relaServtCustSetbEntity = new RelaRelaServtCustSetbEntity();

                                relaServtCustSetbEntity.setPid(relaCustomSet.getId());
                                relaServtCustSetbEntity.setClassName(RelationConstants.className);
                                relaServtCustSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                                relaServtCustSetbEntity.setRela_sttype_cust(cumoServtSet.getCumo_sttype());
                                relaServtCustSetbEntity.setRela_stemp_cust(cumoServtSet.getCumo_stemp());

                                relaServtCustSetbList.add(relaServtCustSetbEntity);
                            }
                        }
                        ormService.insert(relaServtCustSetbList);

                        // 9. 新增 伙伴类-客户数据集-账户管理集
                        List<RelaRelaAccountCustSetbEntity> relaAccountCustSetbEntityList = new ArrayList<>();

                        List<CumoCumoAccountSetbEntity> cumoAccountSetList = selectByPid(customSetId, CumoCumoAccountSetbEntity.class);

                        if (null != cumoAccountSetList && cumoAccountSetList.size() > 0) {

                            for (CumoCumoAccountSetbEntity cumoCumoAccountSet : cumoAccountSetList) {

                                RelaRelaAccountCustSetbEntity relaAccountCustSetbEntity = new RelaRelaAccountCustSetbEntity();

                                relaAccountCustSetbEntity.setPid(relaCustomSet.getId());
                                relaAccountCustSetbEntity.setClassName(RelationConstants.className);
                                relaAccountCustSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                                relaAccountCustSetbEntity.setRela_acconame_cust(cumoCumoAccountSet.getCumo_acconame());
                                relaAccountCustSetbEntity.setRela_accobank_cust(cumoCumoAccountSet.getCumo_accobank());
                                relaAccountCustSetbEntity.setRela_acconum_cust(cumoCumoAccountSet.getCumo_acconum());
                                relaAccountCustSetbEntity.setRela_accocurr_cust(cumoCumoAccountSet.getCumo_accocurr());
                                relaAccountCustSetbEntity.setRela_accobeg_cust(cumoCumoAccountSet.getCumo_accobeg());
                                relaAccountCustSetbEntity.setRela_accoend_cust(cumoCumoAccountSet.getCumo_accoend());
                                relaAccountCustSetbEntity.setRela_attaacco_cust(cumoCumoAccountSet.getCumo_attaacco());

                                relaAccountCustSetbEntityList.add(relaAccountCustSetbEntity);
                            }
                        }

                        ormService.insert(relaAccountCustSetbEntityList);

                        // 10.新增 伙伴类-客户数据集-交货管理集
                        List<RelaRelaDeliCustSetbEntity> relaDeliCustSetbEntityList = new ArrayList<>();

                        List<CumoCumoDeliSetbEntity> cumoDeliSetList = selectByPid(customSetId, CumoCumoDeliSetbEntity.class);

                        if (null != cumoDeliSetList && cumoDeliSetList.size() > 0) {

                            for (CumoCumoDeliSetbEntity cumoDeliSet : cumoDeliSetList) {

                                RelaRelaDeliCustSetbEntity relaDeliCustSetbEntity = new RelaRelaDeliCustSetbEntity();

                                relaDeliCustSetbEntity.setPid(relaCustomSet.getId());
                                relaDeliCustSetbEntity.setClassName(RelationConstants.className);
                                relaDeliCustSetbEntity.setCreuser(currentSessionEntity.getEmployeeId());
                                relaDeliCustSetbEntity.setRela_daname_cust(cumoDeliSet.getCumo_daname());
                                relaDeliCustSetbEntity.setRela_daddrp_cust(cumoDeliSet.getCumo_daddrp());
                                relaDeliCustSetbEntity.setRela_daddrc_cust(cumoDeliSet.getCumo_daddrc());
                                relaDeliCustSetbEntity.setRela_daddrl_cust(cumoDeliSet.getCumo_daddrl());
                                relaDeliCustSetbEntity.setRela_ddaddr_cust(cumoDeliSet.getCumo_ddaddr());
                                relaDeliCustSetbEntity.setRela_dcontact_cust(cumoDeliSet.getCumo_dcontact());
                                relaDeliCustSetbEntity.setRela_dcway_cust(cumoDeliSet.getCumo_dcway());

                                relaDeliCustSetbEntityList.add(relaDeliCustSetbEntity);
                            }
                        }
                        ormService.insert(relaDeliCustSetbEntityList);

                        // 修改单据状态为 完成 - 5
                        updateCustomMaintOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_5);
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
     * @param customMaintOrderDTO
     * @return
     */
    public String checkUnique(CustomMaintOrderDTO customMaintOrderDTO) {

        String message = "";

        String customMaintOrderId = customMaintOrderDTO.getId();

        try {
            // 根据是否有id判断是新增校验还是修改校验
            if (StringUtil.isNullOrEmpty(customMaintOrderId)) {
                // 新增校验
                // 伙伴编号、伙伴简称、社会统一信用代码 唯一性校验
                OrmParam relationParam1 = new OrmParam();
                relationParam1.setWhereExp(OrmParam.and(
                        relationParam1.getEqualXML(RelationProperty.RELA_CODE, customMaintOrderDTO.getCumoCode()),
                        relationParam1.getUnequalXML(NodeConstant.ID, customMaintOrderDTO.getRelaId())
                ));
                List<RelationEntity> relationList1 = ormService.selectBeanList(RelationEntity.class, relationParam1);

                OrmParam customMaintOrderParam1 = new OrmParam();
                customMaintOrderParam1.setWhereExp(OrmParam.and(customMaintOrderParam1.getEqualXML(CustomMaintOrderConstants.CUMO_USCC, customMaintOrderDTO.getCumoCode()),
                        OrmParam.or(customMaintOrderParam1.getIsNull(OrderConstants.ORDE_STATUS), customMaintOrderParam1.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                List<CustommaintorderEntity> customMaintOrderList1 = ormService.selectBeanList(CustommaintorderEntity.class, customMaintOrderParam1);

                if (!ListUtils.isNullOrEmpty(relationList1) || !ListUtils.isNullOrEmpty(customMaintOrderList1)) {
                    message = message + "伙伴编号,";
                }

                OrmParam relationParam2 = new OrmParam();
                relationParam2.setWhereExp(OrmParam.and(
                        relationParam2.getEqualXML(RelationProperty.RELA_SHORT_NAME, customMaintOrderDTO.getCumoShortName()),
                        relationParam2.getUnequalXML(NodeConstant.ID, customMaintOrderDTO.getRelaId())
                ));
                List<RelationEntity> relationList2 = ormService.selectBeanList(RelationEntity.class, relationParam2);

                OrmParam customMaintOrderParam2 = new OrmParam();
                customMaintOrderParam2.setWhereExp(OrmParam.and(customMaintOrderParam2.getEqualXML(CustomMaintOrderConstants.CUMO_SHORT_NAME, customMaintOrderDTO.getCumoShortName()),
                        OrmParam.or(customMaintOrderParam2.getIsNull(OrderConstants.ORDE_STATUS), customMaintOrderParam2.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                List<CustommaintorderEntity> customMaintOrderList2 = ormService.selectBeanList(CustommaintorderEntity.class, customMaintOrderParam2);

                if (!ListUtils.isNullOrEmpty(relationList2) || !ListUtils.isNullOrEmpty(customMaintOrderList2)) {
                    message = message + "伙伴简称,";
                }

                OrmParam relationParam3 = new OrmParam();
                relationParam3.setWhereExp(OrmParam.and(
                        relationParam3.getEqualXML(RelationProperty.RELA_USCC, customMaintOrderDTO.getCumoUscc()),
                        relationParam3.getUnequalXML(NodeConstant.ID, customMaintOrderDTO.getRelaId())
                ));
                List<RelationEntity> relationList3 = ormService.selectBeanList(RelationEntity.class, relationParam3);

                OrmParam customMaintOrderParam3 = new OrmParam();
                customMaintOrderParam3.setWhereExp(OrmParam.and(customMaintOrderParam3.getEqualXML(CustomMaintOrderConstants.CUMO_USCC, customMaintOrderDTO.getCumoUscc()),
                        OrmParam.or(customMaintOrderParam3.getIsNull(OrderConstants.ORDE_STATUS), customMaintOrderParam3.getInXML(OrderConstants.ORDE_STATUS, new String[]{
                                OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4}))));
                List<CustommaintorderEntity> customMaintOrderList3 = ormService.selectBeanList(CustommaintorderEntity.class, customMaintOrderParam3);

                if (!ListUtils.isNullOrEmpty(relationList3) || !ListUtils.isNullOrEmpty(customMaintOrderList3)) {
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
                        relationParam1.getEqualXML(RelationProperty.RELA_CODE, customMaintOrderDTO.getCumoCode()),
                        relationParam1.getUnequalXML(NodeConstant.ID, customMaintOrderDTO.getRelaId())
                ));
                List<RelationEntity> relationList1 = ormService.selectBeanList(RelationEntity.class, relationParam1);

                OrmParam customMaintOrderParam1 = new OrmParam();
                customMaintOrderParam1.setWhereExp(OrmParam.and(
                        customMaintOrderParam1.getEqualXML(CustomMaintOrderConstants.CUMO_CODE, customMaintOrderDTO.getCumoCode()),
                        customMaintOrderParam1.getUnequalXML(NodeConstant.ID, customMaintOrderDTO.getId())
                ));
                List<CustommaintorderEntity> customMaintOrderList1 = ormService.selectBeanList(CustommaintorderEntity.class, customMaintOrderParam1);

                if (!ListUtils.isNullOrEmpty(relationList1) || !ListUtils.isNullOrEmpty(customMaintOrderList1)) {
                    message = message + "伙伴编号,";
                }

                OrmParam relationParam2 = new OrmParam();
                relationParam2.setWhereExp(OrmParam.and(
                        relationParam2.getEqualXML(RelationProperty.RELA_SHORT_NAME, customMaintOrderDTO.getCumoShortName()),
                        relationParam2.getUnequalXML(NodeConstant.ID, customMaintOrderDTO.getRelaId())
                ));
                List<RelationEntity> relationList2 = ormService.selectBeanList(RelationEntity.class, relationParam2);

                OrmParam customMaintOrderParam2 = new OrmParam();
                customMaintOrderParam2.setWhereExp(OrmParam.and(
                        customMaintOrderParam2.getEqualXML(CustomMaintOrderConstants.CUMO_SHORT_NAME, customMaintOrderDTO.getCumoShortName()),
                        customMaintOrderParam2.getUnequalXML(NodeConstant.ID, customMaintOrderDTO.getId())
                ));
                List<CustommaintorderEntity> customMaintOrderList2 = ormService.selectBeanList(CustommaintorderEntity.class, customMaintOrderParam2);

                if (!ListUtils.isNullOrEmpty(relationList2) || !ListUtils.isNullOrEmpty(customMaintOrderList2)) {
                    message = message + "伙伴简称,";
                }

                OrmParam relationParam3 = new OrmParam();
                relationParam3.setWhereExp(OrmParam.and(
                        relationParam3.getEqualXML(RelationProperty.RELA_USCC, customMaintOrderDTO.getCumoUscc()),
                        relationParam3.getUnequalXML(NodeConstant.ID, customMaintOrderDTO.getRelaId())
                ));
                List<RelationEntity> relationList3 = ormService.selectBeanList(RelationEntity.class, relationParam3);

                OrmParam customMaintOrderParam3 = new OrmParam();
                customMaintOrderParam3.setWhereExp(OrmParam.and(
                        customMaintOrderParam2.getEqualXML(CustomMaintOrderConstants.CUMO_USCC, customMaintOrderDTO.getCumoUscc()),
                        customMaintOrderParam2.getUnequalXML(NodeConstant.ID, customMaintOrderDTO.getId())
                ));
                List<CustommaintorderEntity> customMaintOrderList3 = ormService.selectBeanList(CustommaintorderEntity.class, customMaintOrderParam3);

                if (!ListUtils.isNullOrEmpty(relationList3) || !ListUtils.isNullOrEmpty(customMaintOrderList3)) {
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
            throw new ApplicationException(Result.RECODE_ERROR, "根据pid删除属性集出错:" + e.getMessage());
        }
        return retInt;
    }

}