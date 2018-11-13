package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.RelationProperty;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.purchase.common.constants.*;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.base.CurrentSessionEntity;
import com.huntkey.rx.purchase.common.model.relation.*;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.dao.CustomMaintOrderDao;
import com.huntkey.rx.purchase.provider.dao.RelationDao;
import com.huntkey.rx.purchase.provider.dao.RelationMaintOrderDao;
import com.huntkey.rx.purchase.provider.dao.SupplierMaintOrderDao;
import com.huntkey.rx.purchase.provider.service.BizFormService;
import com.huntkey.rx.purchase.provider.service.CommonService;
import com.huntkey.rx.purchase.provider.service.RelationMaintOrderService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author by xuyf on 2018/1/22 0022.
 */
@Service
public class RelationMaintOrderServiceImpl implements RelationMaintOrderService {
    private static final Logger logger = LoggerFactory.getLogger(RelationMaintOrderServiceImpl.class);

    @Autowired
    RelationMaintOrderDao relationMaintOrderDao;

    @Autowired
    CommonService commonService;

    @Autowired
    BizFormService bizFormService;

    @Autowired
    RelationDao relationDao;

    @Autowired
    SupplierMaintOrderDao supplierMaintOrderDao;

    @Autowired
    CustomMaintOrderDao customMaintOrderDao;

    @Override
    public RelationMaintOrderDTO getRelationMaintOrderByOrderId(String orderId) {

        RelationmaintorderEntity relationmaintorderEntity = relationMaintOrderDao.selectRelationMaintOrderEntityById(orderId);
        if (relationmaintorderEntity == null){
            return null;
        }
        RelationMaintOrderDTO relationMaintOrderDTO = JSONObject.parseObject(JSON.toJSONString(relationmaintorderEntity), RelationMaintOrderDTO.class);

        //单据申请人信息反查
        EmployeeEntity employeeEntity = relationDao.selectEmployeeEntityById(relationMaintOrderDTO.getOrdeAdduser());
        if (employeeEntity != null){
            relationMaintOrderDTO.setOrdeAdduserName(employeeEntity.getRemp_name());
            relationMaintOrderDTO.setOrdeAdduserNo(employeeEntity.getRemp_no());
        }

        DepttreeEntity depttreeEntity = relationDao.selectDepttreeEntityById(relationMaintOrderDTO.getOrdeDept());
        if (depttreeEntity != null){
            relationMaintOrderDTO.setOrdeDeptName(depttreeEntity.getMdep_name());
            relationMaintOrderDTO.setOrdeDeptCode(depttreeEntity.getMdep_code());
        }

        JobpositionEntity jobpositionEntity = relationDao.selectJobpositionEntityById(relationMaintOrderDTO.getOrdeDuty());
        if (jobpositionEntity != null){
            relationMaintOrderDTO.setOrdeDutyName(jobpositionEntity.getRpos_name());
            relationMaintOrderDTO.setOrdeDeptCode(jobpositionEntity.getRpos_code());
        }


        //当为编辑再保存时，获取原伙伴id，用于校验接口
        List<RelationEntity> relationEntityList = relationDao.selectRelationUniqueCheck(null, relationMaintOrderDTO.getRemoUscc(), null,null);
        if (relationEntityList.size() > 0){
            relationMaintOrderDTO.setRelaId(relationEntityList.get(0).getId());
        }

        //体系认证集
        List<RemoRemoSystemSetaEntity> remoSystemSetaEntityList = relationMaintOrderDao.selectRemoSystemSetByPid(orderId);
        List<RemoSystemSetDTO> remoSystemSetDTOList = JSONArray.parseArray(JSON.toJSONString(remoSystemSetaEntityList), RemoSystemSetDTO.class);
        relationMaintOrderDTO.setRemoSystemSet(remoSystemSetDTOList);

        //股东集
        List<RemoRemoHolderSetaEntity> remoHolderSetaEntityList =relationMaintOrderDao.selectRemoHolderSetByPid(orderId);
        List<RemoHolderSetDTO> remoHolderSetDTOList = JSONArray.parseArray(JSON.toJSONString(remoHolderSetaEntityList), RemoHolderSetDTO.class);
        relationMaintOrderDTO.setRemoHolderSet(remoHolderSetDTOList);

        return relationMaintOrderDTO;
    }

    @Override
    public JSONObject saveRelationMaintOrder(RelationMaintOrderDTO relationMaintOrderDTO) {
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        //伙伴唯一性校验
        JSONObject checkObj;
        if (StringUtil.isNullOrEmpty(relationMaintOrderDTO.getRelaId())){
            //新增 时通过统一社会信用代码、伙伴编号、伙伴简称 校验
            checkObj = checkRelationMaintOrderUnique(relationMaintOrderDTO.getRelaId(), relationMaintOrderDTO.getId(),
                    relationMaintOrderDTO.getRemoUscc(), relationMaintOrderDTO.getRemoCode(), relationMaintOrderDTO.getRemoShortName());
        } else {
            //根据统一社会信用码在伙伴维护单类中检索，不能存在待审或临时单据
            List<RelationmaintorderEntity> relationmaintorderEntityList =  relationMaintOrderDao.selectRelationMaintOrderUniqueCheck(relationMaintOrderDTO.getId(),
                    relationMaintOrderDTO.getRemoUscc(), null, null);
            if (relationmaintorderEntityList.size() > 0){
                throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "存在待审或临时单据！单据号：" +relationmaintorderEntityList.get(0).getOrde_nbr());
            }

            // 修改时 只通过伙伴简称校验
            checkObj = checkRelationMaintOrderUnique(relationMaintOrderDTO.getRelaId(), relationMaintOrderDTO.getId(),
                    null, null, relationMaintOrderDTO.getRemoShortName());
        }

        if (checkObj.get("relationList") != null){
            List<RelationUniqueCheckDTO> relationList = (List<RelationUniqueCheckDTO>)checkObj.get("relationList");
            Set<String> existFiledNameSet = new HashSet<>();
            for (RelationUniqueCheckDTO uniqueCheckDTO : relationList){
                if (!StringUtil.isNullOrEmpty(uniqueCheckDTO.getRelaUscc())){
                    existFiledNameSet.add(RelationConstants.FIELD_NAME_RELA_USCC);
                }
                if (!StringUtil.isNullOrEmpty(uniqueCheckDTO.getRelaCode())){
                    existFiledNameSet.add(RelationConstants.FIELD_NAME_RELA_CODE);
                }
                if (!StringUtil.isNullOrEmpty(uniqueCheckDTO.getRelaShortName())){
                    existFiledNameSet.add(RelationConstants.FIELD_NAME_RELA_SHORT_NAME);
                }
            }
            throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, String.join(",", existFiledNameSet)+ " 存在重复记录！");
        }

        if (checkObj.get("maintOrderList") != null){
            List<RelationOrderUniqueCheckDTO> maintOrderList = (List<RelationOrderUniqueCheckDTO>)checkObj.get("maintOrderList");
            Set<String> existFiledNameSet = new HashSet<>();
            Set<String> orderNbrSet = new HashSet<>();
            for (RelationOrderUniqueCheckDTO orderUniqueCheckDTO : maintOrderList){
                orderNbrSet.add(orderUniqueCheckDTO.getOrderNbr());
                if (!StringUtil.isNullOrEmpty(orderUniqueCheckDTO.getRemoUscc())){
                    existFiledNameSet.add(RelationConstants.FIELD_NAME_RELA_USCC);
                }
                if (!StringUtil.isNullOrEmpty(orderUniqueCheckDTO.getRemoCode())){
                    existFiledNameSet.add(RelationConstants.FIELD_NAME_RELA_CODE);
                }
                if (!StringUtil.isNullOrEmpty(orderUniqueCheckDTO.getRemoShortName())){
                    existFiledNameSet.add(RelationConstants.FIELD_NAME_RELA_SHORT_NAME);
                }
            }
            throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "维护的 " + String.join(",", existFiledNameSet)
                    + " 已用于其它新增伙伴单据！单据号："+String.join(",",orderNbrSet));
        }

        RelationmaintorderEntity relationmaintorderEntity = JSONObject.parseObject(JSON.toJSONString(relationMaintOrderDTO), RelationmaintorderEntity.class);

        //制单时间
        relationmaintorderEntity.setOrde_date(new Date());
        //所属企业对象id
        relationmaintorderEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
        String userId = sessionEntity.getEmployeeId();

        //单据状态新增时为1—临时
        if (StringUtil.isNullOrEmpty(relationmaintorderEntity.getOrde_status())){
            relationmaintorderEntity.setOrde_status(OrderConstants.ORDE_STATUS_1);
        }

        String orderId = relationmaintorderEntity.getId();
        String orderNbr = relationmaintorderEntity.getOrde_nbr();

        //生成单号
        if (relationMaintOrderDTO.isSubmit()){
            if (StringUtil.isNullOrEmpty(orderNbr)
                    || orderNbr.indexOf(NumberConstants.PREFIX_RELATION_MAINT_ORDER) < 0){
                orderNbr = commonService.getCode(NumberConstants.PREFIX_RELATION_MAINT_ORDER);
            }
        } else {
            if (StringUtil.isNullOrEmpty(orderNbr)){
                orderNbr = commonService.getCode(NumberConstants.PREFIX_PU_TEMP_ORDER);
            }
        }
        relationmaintorderEntity.setOrde_nbr(orderNbr);

        if (StringUtil.isNullOrEmpty(orderId)){//新增
            relationmaintorderEntity.setCreuser(userId);
            //制单时间
            relationmaintorderEntity.setOrde_date(new Date());
            //所属企业对象id
            relationmaintorderEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
            relationmaintorderEntity.setCreuser(sessionEntity.getEmployeeId());
            orderId = relationMaintOrderDao.insertRelationMaintOrder(relationmaintorderEntity);
        } else {//更新
            relationmaintorderEntity.setModuser(userId);
            //删除旧的体系认证集
            relationMaintOrderDao.deleteRemoSystemSetByPid(orderId);
            relationmaintorderEntity.getRemo_system_set().forEach(rss -> rss.setId(null));

            //删除旧的股东数据集
            relationMaintOrderDao.deleteRemoHolderSetByPid(orderId);
            relationmaintorderEntity.getRemo_holder_set().forEach(rhs -> rhs.setId(null));
            //制单时间
            relationmaintorderEntity.setOrde_date(new Date());
            //所属企业对象id
            relationmaintorderEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
            relationmaintorderEntity.setModuser(sessionEntity.getEmployeeId());
            //更新伙伴维护单
            relationMaintOrderDao.updateRelationMaintOrder(relationmaintorderEntity, false);
        }
        JSONObject resultObj = new JSONObject();
        resultObj.put("orderId", orderId);
        resultObj.put("orderNbr", orderNbr);
        return resultObj;
    }

    @Override
    public JSONObject submitRelationMaintOrder(RelationMaintOrderDTO relationMaintOrderDTO) {
        relationMaintOrderDTO.setSubmit(true);
        //保存单据
        JSONObject resultObj = saveRelationMaintOrder(relationMaintOrderDTO);
        //调用流程方法
        String orderInstanceId = resultObj.getString("orderId");
        String orderDefId = relationMaintOrderDTO.getOrdeRodeObj();
        bizFormService.submitWorkFlow(orderDefId, orderInstanceId);
        updateRelationMaintOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_2);
        return resultObj;
    }

    @Override
    public JSONObject checkRelationMaintOrderUnique(String exceptRelaId, String exceptRemoId, String remoUscc, String remoCode, String remoShortName) {
        JSONObject resultObj = new JSONObject();
        resultObj.put("relationList", null);
        resultObj.put("maintOrderList", null);

        //校验伙伴类
        List<RelationEntity> relationList = relationDao.selectRelationUniqueCheck(exceptRelaId, remoUscc, remoCode, remoShortName);
        if (relationList.size() > 0){
            List<RelationUniqueCheckDTO> relationUniqueCheckDTOList = new ArrayList<>();
            for (RelationEntity rela : relationList){
                RelationUniqueCheckDTO relationUniqueCheckDTO = new RelationUniqueCheckDTO();
                relationUniqueCheckDTO.setRelationId(rela.getId());
                if (rela.getRela_uscc().equals(remoUscc)){
                    relationUniqueCheckDTO.setRelaUscc(rela.getRela_uscc());
                }
                if (rela.getRela_code().equals(remoCode)){
                    relationUniqueCheckDTO.setRelaCode(rela.getRela_code());
                }
                if (rela.getRela_short_name().equals(remoShortName)){
                    relationUniqueCheckDTO.setRelaShortName(rela.getRela_short_name());
                }
                relationUniqueCheckDTOList.add(relationUniqueCheckDTO);
            }

            resultObj.put("relationList", relationUniqueCheckDTOList);
        }

        //校验伙伴维护单
        List<RelationmaintorderEntity> remoList = relationMaintOrderDao.selectRelationMaintOrderUniqueCheck(exceptRemoId, remoUscc, remoCode, remoShortName);
        if (remoList.size() > 0){
            List<RelationOrderUniqueCheckDTO> maintOrderList = new ArrayList<>();
            for (RelationmaintorderEntity orderEntity : remoList){
                RelationOrderUniqueCheckDTO orderUniqueCheckDTO = new RelationOrderUniqueCheckDTO();
                orderUniqueCheckDTO.setOrderId(orderEntity.getId());
                orderUniqueCheckDTO.setOrderNbr(orderEntity.getOrde_nbr());
                orderUniqueCheckDTO.setOrderStatus(orderEntity.getOrde_status());
                if (orderEntity.getRemo_uscc().equals(remoUscc)){
                    orderUniqueCheckDTO.setRemoUscc(orderEntity.getRemo_uscc());
                }
                if (orderEntity.getRemo_code().equals(remoCode)){
                    orderUniqueCheckDTO.setRemoCode(orderEntity.getRemo_code());
                }
                if (orderEntity.getRemo_short_name().equals(remoShortName)){
                    orderUniqueCheckDTO.setRemoShortName(orderEntity.getRemo_short_name());
                }
                maintOrderList.add(orderUniqueCheckDTO);
            }

            if (resultObj.get("maintOrderList") != null){
                List<RelationOrderUniqueCheckDTO> oldList = (List<RelationOrderUniqueCheckDTO>)resultObj.get("maintOrderList");
                maintOrderList.addAll(oldList);
            }
            resultObj.put("maintOrderList", maintOrderList);
        }

        //校验供应商维护单
        List<SuppliermaintorderEntity> sumoList = supplierMaintOrderDao.selectSupplierMaintOrderUniqueCheck(exceptRemoId, remoUscc, remoCode, remoShortName);
        if (sumoList.size() > 0){
            List<RelationOrderUniqueCheckDTO> maintOrderList = new ArrayList<>();
            for (SuppliermaintorderEntity orderEntity : sumoList){
                RelationOrderUniqueCheckDTO orderUniqueCheckDTO = new RelationOrderUniqueCheckDTO();
                orderUniqueCheckDTO.setOrderId(orderEntity.getId());
                orderUniqueCheckDTO.setOrderNbr(orderEntity.getOrde_nbr());
                orderUniqueCheckDTO.setOrderStatus(orderEntity.getOrde_status());
                if (orderEntity.getSumo_uscc().equals(remoUscc)){
                    orderUniqueCheckDTO.setRemoUscc(orderEntity.getSumo_uscc());
                }
                if (orderEntity.getSumo_code().equals(remoCode)){
                    orderUniqueCheckDTO.setRemoCode(orderEntity.getSumo_code());
                }
                if (orderEntity.getSumo_short_name().equals(remoShortName)){
                    orderUniqueCheckDTO.setRemoShortName(orderEntity.getSumo_short_name());
                }
                maintOrderList.add(orderUniqueCheckDTO);
            }

            if (resultObj.get("maintOrderList") != null){
                List<RelationOrderUniqueCheckDTO> oldList = (List<RelationOrderUniqueCheckDTO>)resultObj.get("maintOrderList");
                maintOrderList.addAll(oldList);
            }
            resultObj.put("maintOrderList", maintOrderList);
        }


        //校验客户维护单
        List<CustommaintorderEntity> cumoList = customMaintOrderDao.selectCustomMaintOrderUniqueCheck(exceptRemoId, remoUscc, remoCode, remoShortName);
        if (cumoList.size() > 0){
            List<RelationOrderUniqueCheckDTO> maintOrderList = new ArrayList<>();
            for (CustommaintorderEntity orderEntity : cumoList){
                RelationOrderUniqueCheckDTO orderUniqueCheckDTO = new RelationOrderUniqueCheckDTO();
                orderUniqueCheckDTO.setOrderId(orderEntity.getId());
                orderUniqueCheckDTO.setOrderNbr(orderEntity.getOrde_nbr());
                orderUniqueCheckDTO.setOrderStatus(orderEntity.getOrde_status());
                if (orderEntity.getCumo_uscc().equals(remoUscc)){
                    orderUniqueCheckDTO.setRemoUscc(orderEntity.getCumo_uscc());
                }
                if (orderEntity.getCumo_code().equals(remoCode)){
                    orderUniqueCheckDTO.setRemoCode(orderEntity.getCumo_code());
                }
                if (orderEntity.getCumo_short_name().equals(remoShortName)){
                    orderUniqueCheckDTO.setRemoShortName(orderEntity.getCumo_short_name());
                }
                maintOrderList.add(orderUniqueCheckDTO);
            }

            if (resultObj.get("maintOrderList") != null){
                List<RelationOrderUniqueCheckDTO> oldList = (List<RelationOrderUniqueCheckDTO>)resultObj.get("maintOrderList");
                maintOrderList.addAll(oldList);
            }
            resultObj.put("maintOrderList", maintOrderList);
        }

        return resultObj;
    }


    @Override
    public int deleteRelation(String id) {
        return relationMaintOrderDao.deleteRelationMaintOrder(id);
    }

    @Override
    public void approve(String orderInstanceId, String handlerType) {
        try {
            // 根据请求方式的不同进行不同的处理逻辑
            switch (handlerType) {
                // 通过
                case WFHandlerTypeConstants.PASS: {
                    // 根据remo_code伙伴编号字段到主表伙伴类中查，如果查不到就是新增，查到就是修改
                    RelationmaintorderEntity relationmaintorderEntity = relationMaintOrderDao.selectRelationMaintOrderEntityById(orderInstanceId);
                    if (relationmaintorderEntity == null){
                        throw new ApplicationException(Result.RECODE_ERROR, "查询伙伴维护单错误 单据id："+ orderInstanceId);
                    }
                    String remoUscc = relationmaintorderEntity.getRemo_uscc();
                    List<RelationEntity> relationEntityList = relationDao.selectRelationUniqueCheck(null, remoUscc, null, null);

                    if (relationEntityList.size() <= 0) {
                        // 新增
                        saveRelation(orderInstanceId);
                    } else {
                        // 修改
                        // 注意：不允许删除主表信息，此处修改需要删除供应商下面所有的属性集表，再次添加
                        updateRelation(orderInstanceId);
                    }

                    break;
                }
                // 撤销：修改单据状态为临时(1)
                case WFHandlerTypeConstants.REVOKE: {
                    updateRelationMaintOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_1);
                    break;
                }
                // 退回: 修改单据状态为退回(6)
                case WFHandlerTypeConstants.RETURN_BACK: {
                    updateRelationMaintOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_6);
                    break;
                }
                default: {
                    break;
                }
            }
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "伙伴维护单批准通过出错:" + e.getMessage());
        }
    }

    private void saveRelation(String orderInstanceId) {
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        // 1. 根据单据id查询到维护单的数据
        RelationmaintorderEntity relationmaintorderEntity = relationMaintOrderDao.selectRelationMaintOrderEntityById(orderInstanceId);
        if (relationmaintorderEntity == null){
            throw new ApplicationException(Result.RECODE_ERROR, "查询伙伴维护单错误 单据id："+ orderInstanceId);
        }
        // 2. 封装伙伴类基本信息(主表)
        RelationEntity relationEntity = new RelationEntity();

        relationEntity.setRela_code(relationmaintorderEntity.getRemo_code());
        relationEntity.setRela_name(relationmaintorderEntity.getRemo_name());
        relationEntity.setRela_short_name(relationmaintorderEntity.getRemo_short_name());
        relationEntity.setRela_remark(relationmaintorderEntity.getRemo_remark());
        relationEntity.setRela_addrp(relationmaintorderEntity.getRemo_addrp());
        relationEntity.setRela_addrc(relationmaintorderEntity.getRemo_addrc());
        relationEntity.setRela_addrl(relationmaintorderEntity.getRemo_addrl());
        relationEntity.setRela_daddr(relationmaintorderEntity.getRemo_daddr());
        relationEntity.setRela_pcode(relationmaintorderEntity.getRemo_pcode());
        relationEntity.setRela_tel(relationmaintorderEntity.getRemo_tel());
        relationEntity.setRela_fax(relationmaintorderEntity.getRemo_fax());
        relationEntity.setRela_web(relationmaintorderEntity.getRemo_web());
        relationEntity.setRela_mprod(relationmaintorderEntity.getRemo_mprod());
        relationEntity.setRela_character(relationmaintorderEntity.getRemo_character());
        relationEntity.setRela_listexch(relationmaintorderEntity.getRemo_listexch());
        relationEntity.setRela_listname(relationmaintorderEntity.getRemo_listname());
        relationEntity.setRela_listcode(relationmaintorderEntity.getRemo_listcode());
        relationEntity.setRela_uscc(relationmaintorderEntity.getRemo_uscc());
        relationEntity.setRela_raddr(relationmaintorderEntity.getRemo_raddr());
        relationEntity.setRela_rdate(relationmaintorderEntity.getRemo_rdate());
        relationEntity.setRela_lrep(relationmaintorderEntity.getRemo_lrep());
        relationEntity.setRela_laddr(relationmaintorderEntity.getRemo_laddr());
        relationEntity.setRela_rcurr(relationmaintorderEntity.getRemo_rcurr());
        relationEntity.setRela_rcapital(relationmaintorderEntity.getRemo_rcapital());

        relationEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
        relationEntity.setCreuser(sessionEntity.getEmployeeId());

        // 3. 封装伙伴类-体系认证集
        List<RelaRelaSystemSetaEntity> relaSystemSetaEntityList = new ArrayList<>();

        List<RemoRemoSystemSetaEntity> remoSystemSetaList = relationMaintOrderDao.selectRemoSystemSetByPid(orderInstanceId);

        for (RemoRemoSystemSetaEntity remoSystemSet : remoSystemSetaList) {
            RelaRelaSystemSetaEntity relaSystemSetaEntity = new RelaRelaSystemSetaEntity();
            relaSystemSetaEntity.setCreuser(sessionEntity.getEmployeeId());
            relaSystemSetaEntity.setRela_sname(remoSystemSet.getRemo_sname());

            relaSystemSetaEntityList.add(relaSystemSetaEntity);
        }
        relationEntity.setRela_system_set(relaSystemSetaEntityList);

        // 4. 封装伙伴类-股东集
        List<RelaRelaHolderSetaEntity> relaHolderSetaEntityList = new ArrayList<>();

        List<RemoRemoHolderSetaEntity> remoHolderSetParamSetaList = relationMaintOrderDao.selectRemoHolderSetByPid(orderInstanceId);

        for (RemoRemoHolderSetaEntity remoHolderSet : remoHolderSetParamSetaList) {

            RelaRelaHolderSetaEntity relaHolderSetaEntity = new RelaRelaHolderSetaEntity();
            relaHolderSetaEntity.setCreuser(sessionEntity.getEmployeeId());
            relaHolderSetaEntity.setRela_hname(remoHolderSet.getRemo_hname());
            relaHolderSetaEntity.setRela_hrate(remoHolderSet.getRemo_hrate());
            relaHolderSetaEntityList.add(relaHolderSetaEntity);
        }
        relationEntity.setRela_holder_set(relaHolderSetaEntityList);

        // 5. 插入伙伴类
        relationDao.insertRelationEntity(relationEntity).toString();

        // 6. 修改单据状态为 完成 - 5
        updateRelationMaintOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_5);
    }

    private void updateRelation(String orderInstanceId) {
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        // 业务：修改伙伴类-供应商数据集下面的子表之前，需要把子表中的内容全部删除，再添加，以完成修改
        // 1. 查询伙伴维护单信息
        RelationmaintorderEntity relationmaintorderEntity = relationMaintOrderDao.selectRelationMaintOrderEntityById(orderInstanceId);

        // 2. 获取原伙伴id;
        List<RelationEntity> oldRelationEntityList = relationDao.selectRelationList(null, relationmaintorderEntity.getRemo_uscc(),
                relationmaintorderEntity.getRemo_code(), null);
        if (oldRelationEntityList.size() != 1){
            throw new ApplicationException(Result.RECODE_ERROR, "查询原伙伴信息错误，请检查数据。relaUscc：" +
                    relationmaintorderEntity.getRemo_uscc() + " relaCode：" + relationmaintorderEntity.getRemo_code());
        }
        RelationEntity oldRelationEntity = oldRelationEntityList.get(0);
        String oldRelationId = oldRelationEntity.getId();

        // 3. 封装修改后的伙伴信息
        RelationEntity relationEntity = new RelationEntity();

        relationEntity.setId(oldRelationId);
        relationEntity.setRela_code(relationmaintorderEntity.getRemo_code());
        relationEntity.setRela_name(relationmaintorderEntity.getRemo_name());
        relationEntity.setRela_short_name(relationmaintorderEntity.getRemo_short_name());
        relationEntity.setRela_remark(relationmaintorderEntity.getRemo_remark());
        relationEntity.setRela_addrp(relationmaintorderEntity.getRemo_addrp());
        relationEntity.setRela_addrc(relationmaintorderEntity.getRemo_addrc());
        relationEntity.setRela_addrl(relationmaintorderEntity.getRemo_addrl());
        relationEntity.setRela_daddr(relationmaintorderEntity.getRemo_daddr());
        relationEntity.setRela_pcode(relationmaintorderEntity.getRemo_pcode());
        relationEntity.setRela_tel(relationmaintorderEntity.getRemo_tel());
        relationEntity.setRela_fax(relationmaintorderEntity.getRemo_fax());
        relationEntity.setRela_web(relationmaintorderEntity.getRemo_web());
        relationEntity.setRela_mprod(relationmaintorderEntity.getRemo_mprod());
        relationEntity.setRela_character(relationmaintorderEntity.getRemo_character());
        relationEntity.setRela_listexch(relationmaintorderEntity.getRemo_listexch());
        relationEntity.setRela_listname(relationmaintorderEntity.getRemo_listname());
        relationEntity.setRela_listcode(relationmaintorderEntity.getRemo_listcode());
        relationEntity.setRela_uscc(relationmaintorderEntity.getRemo_uscc());
        relationEntity.setRela_raddr(relationmaintorderEntity.getRemo_raddr());
        relationEntity.setRela_rdate(relationmaintorderEntity.getRemo_rdate());
        relationEntity.setRela_lrep(relationmaintorderEntity.getRemo_lrep());
        relationEntity.setRela_laddr(relationmaintorderEntity.getRemo_laddr());
        relationEntity.setRela_rcurr(relationmaintorderEntity.getRemo_rcurr());
        relationEntity.setRela_rcapital(relationmaintorderEntity.getRemo_rcapital());

        relationEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
        relationEntity.setCreuser(oldRelationEntity.getCreuser());
        relationEntity.setModuser(sessionEntity.getEmployeeId());

        // 4. 删除原体系认证集
        relationDao.deleteRelaSystemSetaEntityByPid(oldRelationId);
        // 5. 删除原股东集
        relationDao.deleteRelaHolderSetaEntityByPid(oldRelationId);

        // 6. 封装伙伴类-体系认证集
        List<RelaRelaSystemSetaEntity> relaSystemSetaEntityList = new ArrayList<>();

        List<RemoRemoSystemSetaEntity> remoSystemSetaList = relationMaintOrderDao.selectRemoSystemSetByPid(orderInstanceId);

        for (RemoRemoSystemSetaEntity remoSystemSet : remoSystemSetaList) {
            if (StringUtil.isNullOrEmpty(remoSystemSet.getRemo_sname())){
                continue;
            }
            RelaRelaSystemSetaEntity relaSystemSetaEntity = new RelaRelaSystemSetaEntity();
            relaSystemSetaEntity.setCreuser(sessionEntity.getEmployeeId());
            relaSystemSetaEntity.setRela_sname(remoSystemSet.getRemo_sname());
            relaSystemSetaEntityList.add(relaSystemSetaEntity);
        }
        relationEntity.setRela_system_set(relaSystemSetaEntityList);

        // 7. 封装伙伴类-股东集
        List<RelaRelaHolderSetaEntity> relaHolderSetaEntityList = new ArrayList<>();

        List<RemoRemoHolderSetaEntity> remoHolderSetParamSetaList = relationMaintOrderDao.selectRemoHolderSetByPid(orderInstanceId);

        for (RemoRemoHolderSetaEntity remoHolderSet : remoHolderSetParamSetaList) {
            if (StringUtil.isNullOrEmpty(remoHolderSet.getRemo_hname()) ||
                    StringUtil.isNullOrEmpty(remoHolderSet.getRemo_hrate())){
                continue;
            }
            RelaRelaHolderSetaEntity relaHolderSetaEntity = new RelaRelaHolderSetaEntity();
            relaHolderSetaEntity.setCreuser(sessionEntity.getEmployeeId());
            relaHolderSetaEntity.setRela_hname(remoHolderSet.getRemo_hname());
            relaHolderSetaEntity.setRela_hrate(remoHolderSet.getRemo_hrate());
            relaHolderSetaEntityList.add(relaHolderSetaEntity);
        }
        relationEntity.setRela_holder_set(relaHolderSetaEntityList);

        // 8. 修改伙伴信息
        relationDao.updateRelationEntity(relationEntity);

        // 修改单据状态为 完成 - 5
        updateRelationMaintOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_5);
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
    public void updateRelationMaintOrderStatus(String orderInstanceId, String orderStatus) {
        // 根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity currentSessionEntity = bizFormService.getCurrentSessionInfo();
        // 1. 准备数据
        RelationmaintorderEntity relationmaintorderEntity = new RelationmaintorderEntity();
        relationmaintorderEntity.setId(orderInstanceId);
        relationmaintorderEntity.setOrde_status(orderStatus);
        // 制单时间
        relationmaintorderEntity.setOrde_date(new Date());
        // 所属企业对象id
        relationmaintorderEntity.setEdmd_ente(currentSessionEntity.getEnterpriseId());
        // 修改人
        relationmaintorderEntity.setModuser(currentSessionEntity.getEmployeeId());
        // 2. 更新表
        relationMaintOrderDao.updateRelationMaintOrder(relationmaintorderEntity, true);
    }
}
