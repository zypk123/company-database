package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.hr.common.constants.NumberConstants;
import com.huntkey.rx.hr.common.constants.WorkFlowConstants;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.common.util.NullUtils;
import com.huntkey.rx.hr.provider.client.ORMClient;
import com.huntkey.rx.hr.provider.dao.DeptTreeDao;
import com.huntkey.rx.hr.provider.service.BaseService;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.DeptTreeService;
import com.huntkey.rx.hr.provider.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xuyf
 */
@Service
public class DeptTreeServiceImpl extends BaseService implements DeptTreeService {

    private static Logger logger = LoggerFactory.getLogger(DeptTreeServiceImpl.class);

    @Autowired
    DeptTreeDao deptTreeDao;

    @Autowired
    ORMClient ormClient;

    @Autowired
    PostService deptPostService;

    @Autowired
    BizFormService bizFormService;

    @Override
    public DeptTreeDTO getDept(String deptId, Date date) {
        //查询部门基础信息
        List<MdepMdepChagSetaEntity> mdepChagSetaEntityList = deptTreeDao.findChangeSet(deptId, date);
        if (mdepChagSetaEntityList == null || mdepChagSetaEntityList.size() <= 0){
            throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "对应部门不存在");
        }
        MdepMdepChagSetaEntity mdepChagSetaEntity = mdepChagSetaEntityList.get(0);
        DeptTreeDTO dept = JSONObject.parseObject(JSON.toJSONString(mdepChagSetaEntity), DeptTreeDTO.class);
        return dept;
    }

    @Override
    public DeptTreeDTO getDeptTreeList(String deptId, Date beginDate, int level, int complement) {
        //如果deptId为空，默认从顶级部门查询
        if (StringUtil.isNullOrEmpty(deptId)){
            DeptTreeInfoDTO deptTreeInfoDTO = getRootDept();
            deptId = deptTreeInfoDTO.getId();
        }
        List<MdepMdepChagSetaEntity> mdepChagSetaEntityList = deptTreeDao.findChangeSet(deptId, beginDate);
        if (mdepChagSetaEntityList == null || mdepChagSetaEntityList.size() <= 0){
            throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "对应部门不存在");
        }
        MdepMdepChagSetaEntity mdepChagSetaEntity = mdepChagSetaEntityList.get(0);
        DeptTreeDTO dept = JSONObject.parseObject(JSON.toJSONString(mdepChagSetaEntity), DeptTreeDTO.class);

        if (complement == DeptTreeConstants.COMPLEMENT_YES){
            getDeptFieldsName(dept, beginDate);
        }
        getDeptList(dept, beginDate, level, complement);
        return dept;
    }

    @Override
    public DeptTreeInfoDTO getRootDept() {
        List<DepttreeEntity> depttreeEntityList = deptTreeDao.findByParId(null);
        if (depttreeEntityList == null || depttreeEntityList.size() <= 0){
            throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "查询顶级部门失败");
        }
        DepttreeEntity depttreeEntity = depttreeEntityList.get(0);
        DeptTreeInfoDTO deptTreeInfoDTO = JSONObject.parseObject(JSON.toJSONString(depttreeEntity), DeptTreeInfoDTO.class);
        return deptTreeInfoDTO;
    }

    @Override
    public List<DeptTreeDTO> getDeptList(String[] deptIds, Date beginDate, int complement) {
        List<MdepMdepChagSetaEntity> mdepChagSetList = deptTreeDao.findChangeSetByPids(deptIds, beginDate);
        List<DeptTreeDTO> deptList = new ArrayList<>();
        for (MdepMdepChagSetaEntity mdepChagSet : mdepChagSetList){
            DeptTreeDTO deptTreeDTO = JSONObject.parseObject(JSON.toJSONString(mdepChagSet),DeptTreeDTO.class);
            deptList.add(deptTreeDTO);
        }

        if (complement == DeptTreeConstants.COMPLEMENT_YES){
            deptList = deptList.stream().map(dept -> {
                getDeptFieldsName(dept, beginDate);
                return dept;
            }).collect(Collectors.toList());
        }
        return deptList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject saveDeptStuChangeOrder(DeptStuChangeOrderDTO deptStuChangeOrderDTO, String odscType) {
        //单据状态新增时为1—临时
        if (StringUtil.isNullOrEmpty(deptStuChangeOrderDTO.getOrdeStatus())){
            deptStuChangeOrderDTO.setOrdeStatus(OrderConstants.ORDE_STATUS_1);
        }
        //处理生效日期时区问题
        deptStuChangeOrderDTO.setOdscBeg(DateUtil.parseFormatDate(DateUtil.parseFormatDate(deptStuChangeOrderDTO.getOdscBeg())));
        String prefix;
        switch (odscType){
            case DeptStuChangeOrderConstants.ODSC_TYPE_ADD_ID:
                prefix = NumberConstants.PREFIX_DEPT_STU_CHANGE_ORDER_ADD;
                break;
            case DeptStuChangeOrderConstants.ODSC_TYPE_MODIFY_ID:
                prefix = NumberConstants.PREFIX_DEPT_STU_CHANGE_ORDER_MODIFY;
                break;
            case DeptStuChangeOrderConstants.ODSC_TYPE_MOVE_ID:
                prefix = NumberConstants.PREFIX_DEPT_STU_CHANGE_ORDER_MOVE;
                break;
            case DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID:
                prefix = NumberConstants.PREFIX_DEPT_STU_CHANGE_ORDER_CANCEL;
                break;
            default:
                prefix = NumberConstants.PREFIX_DEPT_STU_CHANGE_ORDER_ADD;
                break;
        }

        if (odscType.equals(DeptStuChangeOrderConstants.ODSC_TYPE_ADD_ID)){
            //设置单据类型为新增部门
            deptStuChangeOrderDTO.setOdscType(DeptStuChangeOrderConstants.ODSC_TYPE_ADD_ID);
            // 部门异动列表树转换为异动列表
            List<OdscChagSetDTO> odscChagSetDTOList = new ArrayList<>();
            getOdscChagSetTree(odscChagSetDTOList, deptStuChangeOrderDTO.getOdscChagSetTree(), deptStuChangeOrderDTO.getOdscBeg());
            //为新增的部门创建部门编码
            odscChagSetDTOList = odscChagSetDTOList.stream().map(ocs ->{
                if (ocs.getOdscFlag().equals(OdscChagSetConstants.ODSC_FLAG_ADD) && StringUtil.isNullOrEmpty(ocs.getOdscDeptCode())){
                    String deptCode = getDeptCode();
                    ocs.setOdscDeptCode(deptCode);
                }
                ocs.setId(null);
                return ocs;
            }).collect(Collectors.toList());
            deptStuChangeOrderDTO.setOdscChagSet(odscChagSetDTOList);
            deptStuChangeOrderDTO.setOdscChagSetTree(null);
        }
        if (odscType.equals(DeptStuChangeOrderConstants.ODSC_TYPE_MODIFY_ID)){
            //校验部门是否有效
            checkDeptModifySave(deptStuChangeOrderDTO);
            //设置单据类型为修改部门
            deptStuChangeOrderDTO.setOdscType(DeptStuChangeOrderConstants.ODSC_TYPE_MODIFY_ID);
        }

        String id = deptStuChangeOrderDTO.getId();
        if (!StringUtil.isNullOrEmpty(id)){
            //删除旧的异动列表
            List<OdscOdscChagSetaEntity> oldOdscEntityList = deptTreeDao.findOdscChagSetByPid(deptStuChangeOrderDTO.getId());
            List<OdscChagSetDTO> oldOdscList = JSONArray.parseArray(JSON.toJSONString(oldOdscEntityList), OdscChagSetDTO.class);
            String[] deleteIds = new String[oldOdscList.size()];
            oldOdscList.stream().map(o -> o.getId()).collect(Collectors.toList()).toArray(deleteIds);
            if (deleteIds != null && deleteIds.length >0 ){
                deptTreeDao.deleteOdscChagSetByIds(deleteIds);
            }
            //新增新的异动列表
            List<OdscChagSetDTO> newOdscList = deptStuChangeOrderDTO.getOdscChagSet();
            //校验异动列表有效性
            List<String> flagList = newOdscList.stream()
                    .map(nol -> nol.getOdscFlag())
                    .filter(f -> !f.equals(OdscChagSetConstants.ODSC_FLAG_NONE)).collect(Collectors.toList());
            if (flagList == null || flagList.size() <= 0){
                throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "无效的部门异动单数据");
            }

            List<OdscOdscChagSetaEntity> odscChagSetaEntityList = newOdscList.stream().map(nol -> {
                nol.setId(null);
                return JSONObject.parseObject(JSON.toJSONString(nol), OdscOdscChagSetaEntity.class);
            }).collect(Collectors.toList());

            //更新部门异动单数据
            deptStuChangeOrderDTO.setOdscChagSet(null);
            DeptstuchangeorderEntity updateEntity = JSONObject.parseObject(JSON.toJSONString(deptStuChangeOrderDTO),DeptstuchangeorderEntity.class);
            updateEntity.setOdsc_chag_set(odscChagSetaEntityList);
            deptTreeDao.updateDeptStuChangeOrder(updateEntity);
            //获取更新后的异动单数据
            DeptstuchangeorderEntity deptstuchangeorderEntity = deptTreeDao.findDeptChangeOrderById(deptStuChangeOrderDTO.getId());
            if(deptstuchangeorderEntity == null){
                throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "部门结构异动单不存在");
            }
            deptStuChangeOrderDTO = JSONObject.parseObject(JSON.toJSONString(deptstuchangeorderEntity),DeptStuChangeOrderDTO.class);
        }else{
            //生成单号
            String orderNumber = getOrderNbr(prefix);
            deptStuChangeOrderDTO.setOrdeNbr(orderNumber);
            //新增部门异动单以及异动列表
            deptStuChangeOrderDTO.setId(null);
            DeptstuchangeorderEntity insertEntity = JSONObject.parseObject(JSON.toJSONString(deptStuChangeOrderDTO),DeptstuchangeorderEntity.class);
            id = deptTreeDao.insertDeptStuChangeOrder(insertEntity);
            deptStuChangeOrderDTO.setId(id);
        }
        JSONObject resultObj = new JSONObject();
        resultObj.put("orderId", deptStuChangeOrderDTO.getId());
        resultObj.put("orderNbr", deptStuChangeOrderDTO.getOrdeNbr());
        return resultObj;
    }

    @Override
    public List<DeptStuChangeOrderDTO> checkDeptChangeOrderByAdd(String[] deptCodes, Date date) {
        //新增部门直接上级部门及其上上级部门不能存在注销的待审单据；
        String[] ocscTypes = {DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID};
        //设置待查询的单据状态
        String[] ordeStatus = {OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4};
        //设置数据标志
        String[] odscFlags ={OdscChagSetConstants.ODSC_FLAG_CANCEL};
        List<DeptStuChangeOrderDTO> resultList = getDeptChangeOrder(deptCodes, date, ocscTypes, ordeStatus, odscFlags,
                true, true, Integer.MAX_VALUE, false, 0);

        return resultList;
    }

    @Override
    public List<DeptStuChangeOrderDTO> checkDeptChangeOrderByModify(String[] deptCodes, Date date) {
        //设置待查询的单据状态
        String[] ordeStatus = {OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4};
        //被选择的部门没有[修改/调动]单据
        String[] ocscTypes = {DeptStuChangeOrderConstants.ODSC_TYPE_MODIFY_ID,
                DeptStuChangeOrderConstants.ODSC_TYPE_MOVE_ID};
        //设置数据标志
        String[] odscFlags ={OdscChagSetConstants.ODSC_FLAG_MODIFY, OdscChagSetConstants.ODSC_FLAG_MODIFY_STAFFING,
                OdscChagSetConstants.ODSC_FLAG_MOVE};
        //查询单据列表
        List<DeptStuChangeOrderDTO> resultList = getDeptChangeOrder(deptCodes, date, ocscTypes, ordeStatus, odscFlags,
                true, false, 0, false, 0);

        //被选择的部门以及上级及其上上级不能存在[注销]的待审单据
        String[] ocscTypes2 = {DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID};
        //设置数据标志
        String[] odscFlags2 ={OdscChagSetConstants.ODSC_FLAG_CANCEL};
        List<DeptStuChangeOrderDTO> resultList2 = getDeptChangeOrder(deptCodes, date, ocscTypes2, ordeStatus, odscFlags2,
                true, true, Integer.MAX_VALUE, false, 0);
        if (resultList2 != null && resultList2.size() > 0){
            resultList.addAll(resultList2);
        }
        return resultList;
    }

    @Override
    public List<DeptStuChangeOrderDTO> checkDeptChangeOrderByMove(String[] deptCodes, Date date) {
        String[] ordeStatus = {OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4};

        //被调动的部门以及上级、上上级部门、下级部门及其下下级部门不能存在[注销]的待审单据
        String[] ocscTypes = {DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID};
        //设置数据标志
        String[] odscFlags ={OdscChagSetConstants.ODSC_FLAG_CANCEL};
        List<DeptStuChangeOrderDTO> resultList = getDeptChangeOrder(deptCodes, date, ocscTypes, ordeStatus, odscFlags,
                true, true, Integer.MAX_VALUE, true, Integer.MAX_VALUE);

        //被调动的部门不能存在[修改]待审单据
        String[] ocscTypes2 = {DeptStuChangeOrderConstants.ODSC_TYPE_MODIFY_ID};
        //设置数据标志
        String[] odscFlags2 ={OdscChagSetConstants.ODSC_FLAG_MODIFY,OdscChagSetConstants.ODSC_FLAG_MODIFY_STAFFING};
        List<DeptStuChangeOrderDTO> resultList2 = getDeptChangeOrder(deptCodes, date, ocscTypes2, ordeStatus, odscFlags2,
                true, false, 0, false, 0);
        if (resultList2 != null && resultList2.size() > 0) {
            resultList.addAll(resultList2);
        }

        //被调动的部门以及下级部门及其下下级部门不能存在[调动]的待审单据
        String[] ocscTypes3 = {DeptStuChangeOrderConstants.ODSC_TYPE_MOVE_ID};
        String[] odscFlags3 ={OdscChagSetConstants.ODSC_FLAG_MOVE};
        List<DeptStuChangeOrderDTO> resultList3 = getDeptChangeOrder(deptCodes, date, ocscTypes3, ordeStatus, odscFlags3,
                true, false, 0, true, Integer.MAX_VALUE);
        if (resultList3 != null && resultList3.size() > 0) {
            resultList.addAll(resultList3);
        }

        return resultList;
    }

    @Override
    public List<DeptStuChangeOrderDTO> checkDeptChangeOrderByCancel(String[] deptCodes, Date date) {
        String[] ordeStatus = {OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4};

        //被调动的部门不能存在[修改]待审单据
        String[] ocscTypes = {DeptStuChangeOrderConstants.ODSC_TYPE_MODIFY_ID};
        //设置数据标志
        String[] odscFlags ={OdscChagSetConstants.ODSC_FLAG_CANCEL};
        List<DeptStuChangeOrderDTO> resultList = getDeptChangeOrder(deptCodes, date, ocscTypes, ordeStatus, odscFlags,
                true, false, 0, false, 0);

        //被选择的部门以及上级、上上级部门、下级部门及其下下级部门不能存在[调动/注销]的待审单据
        String[] ocscTypes2 = {DeptStuChangeOrderConstants.ODSC_TYPE_MOVE_ID,
                DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID};
        //设置数据标志
        String[] odscFlags2 ={OdscChagSetConstants.ODSC_FLAG_MOVE, OdscChagSetConstants.ODSC_FLAG_CANCEL};
        List<DeptStuChangeOrderDTO> resultList2 = getDeptChangeOrder(deptCodes, date, ocscTypes2, ordeStatus, odscFlags2,
                true, true, Integer.MAX_VALUE, true, Integer.MAX_VALUE);
        if (resultList2 != null && resultList2.size() > 0) {
            resultList.addAll(resultList2);
        }

        //被调动的部门下级部门及其下下级部门不能存在[新增/修改]的待审单据
        String[] ocscTypes3 = {DeptStuChangeOrderConstants.ODSC_TYPE_ADD_ID,
                DeptStuChangeOrderConstants.ODSC_TYPE_MODIFY_ID};
        String[] odscFlags3 ={OdscChagSetConstants.ODSC_FLAG_ADD, OdscChagSetConstants.ODSC_FLAG_MODIFY,
                OdscChagSetConstants.ODSC_FLAG_MODIFY_STAFFING};
        List<DeptStuChangeOrderDTO> resultList3 = getDeptChangeOrder(deptCodes, date, ocscTypes3, ordeStatus, odscFlags3,
                false, false, 0, true, Integer.MAX_VALUE);
        if (resultList3 != null && resultList3.size() > 0) {
            resultList.addAll(resultList3);
        }
        return resultList;
    }

    @Override
    public List<DeptStuChangeOrderDTO> checkDeptChangeOrderByODCA(String[] deptCodes, Date date) {
        String[] ordeStatus = {OrderConstants.ORDE_STATUS_2, OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4};
        //被选择的部门以及上级、上上级部门、下级部门及其下下级部门不能存在[注销]的待审单据
        String[] ocscTypes = {DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID};
        //设置数据标志
        String[] odscFlags ={OdscChagSetConstants.ODSC_FLAG_CANCEL};
        List<DeptStuChangeOrderDTO> resultList = getDeptChangeOrder(deptCodes, date, ocscTypes, ordeStatus, odscFlags,
                true, true, Integer.MAX_VALUE, true, Integer.MAX_VALUE);
        return resultList;
    }

    @Override
    public DeptStuChangeOrderDTO getDeptChangeOrderById(String orderId) {
        DeptstuchangeorderEntity deptstuchangeorderEntity = deptTreeDao.findDeptChangeOrderById(orderId);
        DeptStuChangeOrderDTO deptStuChangeOrderDTO = JSONObject.parseObject(JSON.toJSONString(deptstuchangeorderEntity),DeptStuChangeOrderDTO.class);

        deptStuChagOrderComple(deptStuChangeOrderDTO);

        List<OdscOdscChagSetaEntity> odscChagSetaEntityList = deptTreeDao.findOdscChagSetByPid(deptStuChangeOrderDTO.getId());
        List<OdscChagSetDTO> odscChagSetDTOList = JSONArray.parseArray(JSON.toJSONString(odscChagSetaEntityList), OdscChagSetDTO.class);

        List<OdscChagSetDTO> newOdscChagSetDTOList =odscChagSetDTOList.stream().map(odsc ->{
            ParkEntity parkEntity = deptTreeDao.findParkById(odsc.getOdscRpak());
            if (parkEntity != null){
                odsc.setOdscRpakName(parkEntity.getRpak_name());
            }
            RelationEntity relationEntity = deptTreeDao.findRelationById(odsc.getOdscMcop());
            if (relationEntity != null) {
                odsc.setOdscMcopName(relationEntity.getRela_short_name());
            }
            return odsc;
        }).collect(Collectors.toList());

        deptStuChangeOrderDTO.setOdscChagSet(newOdscChagSetDTOList);
        return deptStuChangeOrderDTO;
    }

    @Override
    public DeptStuChangeOrderDTO getDeptChangeOrderAddById(String orderId) {
        DeptStuChangeOrderDTO deptStuChangeOrderDTO = getDeptChangeOrderById(orderId);
        List<OdscChagSetDTO> odscChagSetDTOList = deptStuChangeOrderDTO.getOdscChagSet();
        if (odscChagSetDTOList == null || odscChagSetDTOList.size() <= 0){
            throw new ApplicationException(Result.RECODE_VALIDATE_ERROR,
                    "部门结构异动单'"+ deptStuChangeOrderDTO.getOrdeNbr() +"'获取异动列表失败");
        }
        Collections.sort(odscChagSetDTOList);
        //获取根节点层级码
        String rootLvl = odscChagSetDTOList.get(0).getOdscLvl();
        //转换为OdscChagSetTreeDTO对象集合
        List<OdscChagSetTreeDTO> odscChagSetTreeDTOList = odscChagSetDTOList.stream().map(odsc -> {
            OdscChagSetTreeDTO treeDTO = new OdscChagSetTreeDTO();
            BeanUtils.copyProperties(odsc, treeDTO);
            return treeDTO;
        }).collect(Collectors.toList());

        Map<String, OdscChagSetTreeDTO> rMap = new LinkedHashMap<>();
        for (OdscChagSetTreeDTO thisN : odscChagSetTreeDTOList) {
            turnToOdscMap(rMap, thisN, rootLvl);
        }
        OdscChagSetTreeDTO root = new OdscChagSetTreeDTO();
        turnToOdscList(rMap, root);
        deptStuChangeOrderDTO.setOdscChagSetTree(root.getChildList().get(0));
        deptStuChangeOrderDTO.setOdscChagSet(null);
        return deptStuChangeOrderDTO;
    }

    @Override
    public DeptStuChangeOrderDTO getDeptChangeOrderEditById(String orderId) {
        return getDeptChangeOrderById(orderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDeptByIds(String[] deptIds) {
        deptTreeDao.deleteDepttreeByIds(deptIds);
    }

    private void getDeptList(DeptTreeDTO parDept, Date beginDate, int level, int complement){
        MdepMdepChagSetaEntity mdepChagSetaEntitySecEnd = deptTreeDao.findChangeSetMaxEnd(parDept.getId());
        if (mdepChagSetaEntitySecEnd != null){
            parDept.setMdepMaxEnd(mdepChagSetaEntitySecEnd.getMdep_end_his());
        }

        List<MdepMdepChagSetaEntity> mdepChagSetaEntityList = deptTreeDao.findChangeSetByParId(parDept.getId(), beginDate);
        List<DeptTreeDTO> deptList = new ArrayList<>();
        for (MdepMdepChagSetaEntity mdepChagSet : mdepChagSetaEntityList){
            DeptTreeDTO deptTreeDTO = JSONObject.parseObject(JSON.toJSONString(mdepChagSet),DeptTreeDTO.class);
            deptList.add(deptTreeDTO);
        }

        if (null != deptList && deptList.size() > 0 && level > 0) {
            level--;
            List<DeptTreeDTO> newDeptList = new ArrayList<>();
            for (int i = 0; i < deptList.size(); i++) {
                DeptTreeDTO department = deptList.get(i);
                if (complement == DeptTreeConstants.COMPLEMENT_YES){
                    getDeptFieldsName(department, beginDate);
                }
                newDeptList.add(department);
                getDeptList(department, beginDate, level, complement);
            }
            parDept.setChildDeptList(newDeptList);
        }
    }

    private void getDeptFieldsName(DeptTreeDTO deptObj, Date beginDate){
        //法人公司
        RelationEntity relationEntity = deptTreeDao.findRelationById(deptObj.getMdepMcop());
        if (relationEntity != null){
            deptObj.setMdepMcopName(relationEntity.getRela_short_name());
        }

        //办公园区
        ParkEntity parkEntity = deptTreeDao.findParkById(deptObj.getMdepRpak());
        if (parkEntity != null) {
            deptObj.setMdepRpakName(parkEntity.getRpak_name());
        }

        //主管人&协管人
        List<MdepMdepChgrSetaEntity> mdepChgrSetaEntityList = deptTreeDao.findChargeSetByPidAndDate(deptObj.getId(), beginDate);
        List<String> assistantIdList = new ArrayList<>();
        List<String> assistantNameList = new ArrayList<>();
        for (MdepMdepChgrSetaEntity mdepChgrSet : mdepChgrSetaEntityList){
            if (mdepChgrSet.getMdep_chgr_type().equals(MDepChgrSetConstants.MDEP_CHGR_TYPE_LEADER)
                    && StringUtil.isNullOrEmpty(deptObj.getMdepLeader())){
                deptObj.setMdepLeader(mdepChgrSet.getMdep_chgr_emp());
                deptObj.setMdepLeaderPost(mdepChgrSet.getMdep_chgr_post());
                //获取主管人姓名
                EmployeeEntity leader = deptTreeDao.findEmployeeById(mdepChgrSet.getMdep_chgr_emp());
                if (leader != null){
                    deptObj.setMdepLeaderName(leader.getRemp_name()
                            + MDepChgrSetConstants.MDEP_CHGR_SEPARATOR_A + leader.getRemp_no());
                }
                //获取主管人任职方式
                JobpositionEntity leaderPos = deptTreeDao.findJobpositionById(mdepChgrSet.getMdep_chgr_post());
                if (leaderPos != null){
                    deptObj.setMdepLeaderPostName(leaderPos.getRpos_name());
                    deptObj.setMdepLeaderPosDutyType(leaderPos.getRpos_duty_type());
                }
            }else if (mdepChgrSet.getMdep_chgr_type().equals(MDepChgrSetConstants.MDEP_CHGR_TYPE_ASSISTANT)){
                assistantIdList.add(mdepChgrSet.getMdep_chgr_emp());
                EmployeeEntity assistant = deptTreeDao.findEmployeeById(mdepChgrSet.getMdep_chgr_emp());
                if (assistant != null){
                    String assistantName = assistant.getRemp_name()
                            + MDepChgrSetConstants.MDEP_CHGR_SEPARATOR_A + assistant.getRemp_no();
                    assistantNameList.add(assistantName);
                }
            }else{
                continue;
            }
        }
        deptObj.setMdepAssistant(StringUtils.join(assistantIdList.toArray(), MDepChgrSetConstants.MDEP_CHGR_SEPARATOR_B));
        deptObj.setMdepAssistantName(StringUtils.join(assistantNameList.toArray(), MDepChgrSetConstants.MDEP_CHGR_SEPARATOR_B));

        //部门本层编制和下级编制
        DepttreeEntity dept = deptTreeDao.findById(deptObj.getId());
        deptObj.setMdepTlNum(dept.getMdep_tl_num() == null ? 0 : dept.getMdep_tl_num());
        deptObj.setMdepLlNum(dept.getMdep_ll_num() == null ? 0 : dept.getMdep_ll_num());
        //部门编码
        deptObj.setMdepCode(dept.getMdep_code());

        //【部门岗位数：部门岗位数=当前部门&&岗位有效】
        JSONArray jobPositions = null;
        try {
            jobPositions = deptPostService.getPostsByDeptId(deptObj.getId(), "0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(jobPositions != null && jobPositions.size() > 0){
            //设置部门岗位数（包含下级）返回给界面
            deptObj.setPostNum(jobPositions.size());
        }else {
            deptObj.setPostNum(0);
        }

        //【部门在职岗位数：部门在职岗位数=当前部门&&岗位有效&&岗位任职人不为空】
        JSONArray inJobPositions = null;
        try {
            inJobPositions = deptPostService.getPostsEmpByDeptId(deptObj.getId(), "0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(inJobPositions != null && inJobPositions.size() > 0){
            //设置部门含下级部门在编数（包含下级）返回给界面
            deptObj.setInPostNum(inJobPositions.size());
        }else {
            deptObj.setInPostNum(0);
        }

    }


    private List<DeptStuChangeOrderDTO> getDeptChangeOrder(String[] deptCodes, Date date, String[] ocscTypes, String[] ordeStatus,
                                                           String[] odscFlags, boolean isIncludeSelf,
                                                           boolean isIncludeSuperior, int superiorLevel,
                                                           boolean isIncludeLower, int lowerLevel) {
        //查询单据列表
        List<DeptstuchangeorderEntity> deptstuchangeorderEntityList = deptTreeDao.findDeptChangeOrderByTypesAndStatus(ocscTypes, ordeStatus);
        List<DeptStuChangeOrderDTO> deptStuChangeOrderDTOList = JSONArray.parseArray(JSON.toJSONString(deptstuchangeorderEntityList), DeptStuChangeOrderDTO.class);

        if (deptStuChangeOrderDTOList == null || deptStuChangeOrderDTOList.size() <= 0){
            return deptStuChangeOrderDTOList;
        }

        List<String> deptIds = Arrays.stream(deptCodes).map(code -> {
            DepttreeEntity deptObj = deptTreeDao.findByCode(code);
            if (deptObj == null){
                throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "对应部门不存在");
            }
            return deptObj.getId();
        }).collect(Collectors.toList());

        List<String> deptIdList =  new ArrayList<>();
        for (String deptId : deptIds){
            //是否包含自己
            if (isIncludeSelf){
                deptIdList.add(deptId);
            }
            //是否包含上级及上上级
            if (isIncludeSuperior){
                getDeptSuperiorIdList(deptIdList, deptId, date, superiorLevel);
            }
            //是否包含下级及下下级
            if (isIncludeLower){
                getDeptLowerIdList(deptIdList, deptId, date, lowerLevel);
            }
        }
        if (deptIdList.isEmpty()){
            return null;
        }
        List<DepttreeEntity> depttreeEntityList = deptTreeDao.findByIdList(deptIdList);

        List<String> deptCodeList = depttreeEntityList.stream().map(dept -> dept.getMdep_code()).collect(Collectors.toList());

        List<OdscOdscChagSetaEntity> odscChagSetaEntityList = deptTreeDao.findOdscChagSetByDeptCodes(deptCodeList, odscFlags);
        List<OdscChagSetDTO> odscChagSetDTOList = JSONArray.parseArray(JSON.toJSONString(odscChagSetaEntityList), OdscChagSetDTO.class);

        List<DeptStuChangeOrderDTO> resultList = deptStuChangeOrderDTOList.stream().map(dsco -> {
            List<OdscChagSetDTO> newOcsList = new ArrayList<>();
            odscChagSetDTOList.stream().forEach(ocs -> {
                if (dsco.getId().equals(ocs.getPid())){
                    newOcsList.add(ocs);
                }
            });
            dsco.setOdscChagSet(newOcsList);
            return dsco;
        }).filter(newDsco -> newDsco.getOdscChagSet().size() > 0).collect(Collectors.toList());

        return resultList;
    }



    @Override
    public void getDeptSuperiorIdList(List<String> deptIdList, String deptId, Date date, int superiorLevel){
        MdepMdepChagSetaEntity mdepChagSetaEntity = null;
        try {
            List<MdepMdepChagSetaEntity> mdepChagSetaEntityList = deptTreeDao.findChangeSet(deptId, date);
            if (mdepChagSetaEntityList != null && mdepChagSetaEntityList.size() > 0){
                mdepChagSetaEntity = mdepChagSetaEntityList.get(0);
            }
            if ( mdepChagSetaEntity != null && superiorLevel > 0) {
                superiorLevel--;
                if (!StringUtil.isNullOrEmpty(mdepChagSetaEntity.getMdep_par_his())){
                    deptIdList.add(mdepChagSetaEntity.getMdep_par_his());
                }
                getDeptSuperiorIdList(deptIdList, mdepChagSetaEntity.getMdep_par_his(), date, superiorLevel);
            }

        } catch (ApplicationException e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    public void getDeptLowerIdList(List<String> deptIdList, String deptId, Date date, int lowerLevel){
        List<MdepMdepChagSetaEntity> mdepChagSetaEntityList = null;
        try {
            mdepChagSetaEntityList= deptTreeDao.findChangeSetByParId(deptId, date);
            if (null != mdepChagSetaEntityList && mdepChagSetaEntityList.size() > 0 && lowerLevel > 0) {
                lowerLevel--;
                for (int i = 0; i < mdepChagSetaEntityList.size(); i++) {
                    MdepMdepChagSetaEntity department = mdepChagSetaEntityList.get(i);
                    deptIdList.add(department.getPid());
                    getDeptLowerIdList(deptIdList, department.getPid(), date, lowerLevel);
                }
            }
        } catch (ApplicationException e) {
            logger.error(e.getMessage());
        }
    }

    private void getOdscChagSetTree(List<OdscChagSetDTO> odscChagSetDTOList, OdscChagSetTreeDTO odscChagSetTree, Date odscBeg){
        OdscChagSetDTO odscChagSetDTO = new OdscChagSetDTO();
        BeanUtils.copyProperties(odscChagSetTree, odscChagSetDTO);
        odscChagSetDTOList.add(odscChagSetDTO);

        List<OdscChagSetTreeDTO> childList = odscChagSetTree.getChildList();
        if (null != childList && childList.size() > 0){
            Collections.sort(childList);
            List<String> peerDeptNameList = new ArrayList<>();
            for (int i = 0; i < childList.size(); i++){
                OdscChagSetTreeDTO childDto = childList.get(i);
                if (childDto.getOdscFlag().equals(OdscChagSetConstants.ODSC_FLAG_ADD)){
                    //新增部门在同一层中部门名称唯一性校验
                    if (peerDeptNameList.contains(childDto.getOdscName())){
                        String errMsg = "部门名称 " + childDto.getOdscName() + " 已存在";
                        throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, errMsg);
                    }
                    //上级部门id不为空，说明上级部门是已有部门，去上级部门中查询是否有存在有效的相同部门名
                    if (!StringUtil.isNullOrEmpty(childDto.getOdscPdept())){
                        List<MdepMdepChagSetaEntity> chagSetaEntityList = deptTreeDao.checkDeptName(odscChagSetTree.getId(),
                                odscBeg, null, childDto.getOdscName());
                        if (chagSetaEntityList != null && chagSetaEntityList.size() > 0){
                            String errMsg = childDto.getOdscName()+ " 在相同父部门下，已经存在相同的部门名称";
                            throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, errMsg);
                        }
                    }
                    //为新增部门生成层级码
                    String parentLvl= odscChagSetDTO.getOdscLvl();
                    int index = i;
                    if (i > 0){
                        OdscChagSetTreeDTO broDto = childList.get(i - 1);
                        String[] broLvls = broDto.getOdscLvl().split(",");
                        index = Integer.parseInt(broLvls[broLvls.length - 1]);
                    }
                    childDto.setOdscLvl(parentLvl + String.format("%03d", index + 1 ) + ",");
                }
                peerDeptNameList.add(childDto.getOdscName());
                getOdscChagSetTree(odscChagSetDTOList, childDto, odscBeg);
            }
        }
    }

    @Override
    public List<DeptTreeDTO> getParentTreeById(String pid, Date beginDate) {
        
        List<DeptTreeDTO> deptList = new ArrayList<DeptTreeDTO>();
        List<MdepMdepChagSetaEntity> mdepChagSet = deptTreeDao.findChangeSet(pid, beginDate);
        if (mdepChagSet != null && mdepChagSet.size() > 0) {
            MdepMdepChagSetaEntity mdepChag = mdepChagSet.get(0);

            DeptTreeDTO to = JSONObject.parseObject(JSON.toJSONString(mdepChag), DeptTreeDTO.class);

            deptList.add(to.clone());

            while (!StringUtil.isNullOrEmpty(to.getMdepPar())) {
                List<MdepMdepChagSetaEntity> mdepChagSet_ = deptTreeDao.findChangeSet(to.getMdepPar(), beginDate);
                if (mdepChagSet != null && mdepChagSet.size() > 0) {
                    MdepMdepChagSetaEntity mdepChag_ = mdepChagSet_.get(0);
                    to = JSONObject.parseObject(JSON.toJSONString(mdepChag_), DeptTreeDTO.class);
                    deptList.add(to.clone());
                }else {
                    to.setMdepPar(null);
                }
            }
        }
        return deptList;
    }

    /**
     * FIXME 递归查询所有的子部门及下下级部门列表
     * @param deptId  部门对象Id
     * @param deptTreeDTOList  接受部门对象的容器
     */
    @Override
    public void findAllChildDeptNodes(String deptId, List<DeptTreeInfoDTO> deptTreeDTOList)
    {
        List<DepttreeEntity> depttreeEntityList = deptTreeDao.findByParId(deptId);

        if(depttreeEntityList != null && depttreeEntityList.size() > 0)
        {
            List<DeptTreeInfoDTO> childDeptNodeList = JSONArray.parseArray(JSON.toJSONString(depttreeEntityList), DeptTreeInfoDTO.class);

            //叠加所有的子部门节点
            deptTreeDTOList.addAll(childDeptNodeList);

            //递归当前部门下的所有子节点
            for (DeptTreeInfoDTO deptTreeDto : childDeptNodeList)
            {
                //当前部门对象的id，作为查询子节点的上级部门对象Id
                String childDeptId = deptTreeDto.getId();

                findAllChildDeptNodes(childDeptId, deptTreeDTOList);
            }
        }
    }

    @Override
    public JSONObject getOrderType(String code) {
        Result result = ormClient.getEnumsObj("odsc_type", code);
        if (!result.getRetCode().equals(Result.RECODE_SUCCESS)){
            throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "部门结构异动单类型枚举对象查询失败");
        }
        JSONObject resultObject = (JSONObject) JSONObject.toJSON(result.getData());
        return resultObject;
    }

    @Override
    public DeptTreeInfoDTO getDeptByCode(String code) {
        DepttreeEntity depttreeEntity = deptTreeDao.findDeptObj(code, DeptTreeConstants.MDEP_CODE);
        DeptTreeInfoDTO deptTreeInfoDTO = JSONObject.parseObject(JSON.toJSONString(depttreeEntity), DeptTreeInfoDTO.class);
        return deptTreeInfoDTO;
    }

    private void turnToOdscMap(Map<String, OdscChagSetTreeDTO> chagSetTreeDTOMap, OdscChagSetTreeDTO chagSetTreeDTO, String rootLvl){
        String key = null;
        List<String> keyList = new ArrayList<>();
        // 组装code的父级结构
        String lvlcode = chagSetTreeDTO.getOdscLvl().substring(rootLvl.length());
        keyList.add(rootLvl);
        if (lvlcode.length() != 0){
            for (int i = 0; i < lvlcode.length() / 4; i++) {
                key = rootLvl + lvlcode.substring(0, 4 + (i * 4));
                keyList.add(key);
            }
        }
        String thisKey = null;
        OdscChagSetTreeDTO tmpOdsc = null;
        Map<String, OdscChagSetTreeDTO> tmpOdscMap = chagSetTreeDTOMap;
        for (int i = 0; i < keyList.size(); i++) {
            thisKey = keyList.get(i);
            tmpOdsc = tmpOdscMap.get(thisKey);
            if (i + 1 == keyList.size()) {
                tmpOdscMap.put(chagSetTreeDTO.getOdscLvl(), chagSetTreeDTO);// 如果是末级节点，则放入该节点
            } else {
                if (tmpOdsc == null){
                    throw new ApplicationException(Result.RECODE_ERROR, "单据加载失败");
                }
                tmpOdscMap = tmpOdsc.getChildMap();// 如果不是末级节点，则将该节点赋值给临时变量
            }
        }
    }

    private void turnToOdscList(Map<String, OdscChagSetTreeDTO> chagSetTreeDTOMap, OdscChagSetTreeDTO chagSetTreeDTO) {
        Set<Map.Entry<String, OdscChagSetTreeDTO>> eSet = chagSetTreeDTOMap.entrySet();
        Iterator<Map.Entry<String, OdscChagSetTreeDTO>> mIt = eSet.iterator();
        while (mIt.hasNext()) {
            Map.Entry<String, OdscChagSetTreeDTO> entry = mIt.next();
            OdscChagSetTreeDTO node = entry.getValue();
            chagSetTreeDTO.getChildList().add(node);
            chagSetTreeDTO.setChildMap(null);
            turnToOdscList(node.getChildMap(), node);
        }
    }

    private void deptStuChagOrderComple(DeptStuChangeOrderDTO deptStuChangeOrderDTO){
        if(!StringUtil.isNullOrEmpty(deptStuChangeOrderDTO.getOrdeAdduser())) {
            EmployeeEntity employeeEntity = deptTreeDao.findEmployeeById(deptStuChangeOrderDTO.getOrdeAdduser());
            if (employeeEntity != null) {
                deptStuChangeOrderDTO.setOrdeAdduserName(employeeEntity.getRemp_name());
                deptStuChangeOrderDTO.setOrdeAdduserNo(employeeEntity.getRemp_no());
            }
        }

        if(!StringUtil.isNullOrEmpty(deptStuChangeOrderDTO.getOrdeDuty())) {
            JobpositionEntity jobpositionEntity = deptTreeDao.findJobpositionById(deptStuChangeOrderDTO.getOrdeDuty());
            if (jobpositionEntity != null) {
                deptStuChangeOrderDTO.setOrdeDutyName(jobpositionEntity.getRpos_name());
                deptStuChangeOrderDTO.setOrdeDutyCode(jobpositionEntity.getRpos_code());
            }
        }

        if(!StringUtil.isNullOrEmpty(deptStuChangeOrderDTO.getOrdeDept())){
            DepttreeEntity depttreeEntity = deptTreeDao.findById(deptStuChangeOrderDTO.getOrdeDept());
            if (depttreeEntity != null){
                deptStuChangeOrderDTO.setOrdeDeptName(depttreeEntity.getMdep_name());
                deptStuChangeOrderDTO.setOrdeDeptCode(depttreeEntity.getMdep_code());
            }
        }
    }

    private void checkDeptModifySave(DeptStuChangeOrderDTO deptStuChangeOrderDTO){
        Date odscBeg = deptStuChangeOrderDTO.getOdscBeg();
        List<OdscChagSetDTO> odscChagSetDTOList = deptStuChangeOrderDTO.getOdscChagSet();

        //同一个上级部门的部门Map
        Map<String, OdscChagSetDTO> peerDeptMap = new HashMap<>();

        for (OdscChagSetDTO odscChagSetDTO : odscChagSetDTOList){
            DepttreeEntity depttreeEntity = deptTreeDao.findByCode(odscChagSetDTO.getOdscDeptCode());
            if (depttreeEntity == null){
                throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "对应部门不存在");
            }
            //校验单据界面的生效日期，部门是否有效
            List<MdepMdepChagSetaEntity> mdepChagSetaEntityList = deptTreeDao.findChangeSet(depttreeEntity.getId(), odscBeg);
            if (mdepChagSetaEntityList == null || mdepChagSetaEntityList.size() <= 0){
                String errMsg = "生效日期为"+ DateUtil.parseFormatDate(odscBeg)+ "时,"+odscChagSetDTO.getOdscName()+"/"+odscChagSetDTO.getOdscDeptCode()+"无效";
                throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, errMsg);
            }
            //校验是否存在，生效日期>单据界面的生效日期的记录
            List<MdepMdepChagSetaEntity> changeSetBegDateGT = deptTreeDao.findChangeSetBegDateGT(depttreeEntity.getId(), odscBeg);
            if (changeSetBegDateGT != null && changeSetBegDateGT.size() > 0){
                String errMsg = odscChagSetDTO.getOdscDeptCode() + "/" + odscChagSetDTO.getOdscNameOld()+ "，存在"
                        + DateUtil.parseFormatDate(changeSetBegDateGT.get(0).getMdep_beg_his())
                        + "生效的记录，不可修改当前部门信息，请调整生效日期";
                throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, errMsg);
            }
            //部门名称在异动列表中同层级下唯一性校验
            if (peerDeptMap.containsKey(odscChagSetDTO.getOdscPdept())){
                OdscChagSetDTO chagSetDTO = peerDeptMap.get(odscChagSetDTO.getOdscPdept());
                //同一上级部门id的部门，部门名称不能相同
                if (chagSetDTO.getOdscName().equals(odscChagSetDTO.getOdscName())){
                    String errMsg = chagSetDTO.getOdscDeptCode() + "/" + chagSetDTO.getOdscName()+ " 和 "
                            + odscChagSetDTO.getOdscDeptCode() + "/" + odscChagSetDTO.getOdscName() + " 部门名称相同";
                    throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, errMsg);
                }
            }else{
                peerDeptMap.put(odscChagSetDTO.getOdscPdept(), odscChagSetDTO);
            }
        }
        //部门名称在部门变更记录中同层级下唯一性校验
        peerDeptMap.forEach((k, v) -> {
            DepttreeEntity dept = deptTreeDao.findByCode(v.getOdscDeptCode());
            if (dept == null){
                throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "对应部门不存在");
            }
            List<MdepMdepChagSetaEntity> chagSetaEntityList = deptTreeDao.checkDeptName(k, odscBeg, dept.getId() ,v.getOdscName());
            if (chagSetaEntityList != null && chagSetaEntityList.size() > 0){
                String errMsg = v.getOdscDeptCode() + "/" + v.getOdscName()+ " 在相同父部门下，已经存在相同的部门名称";
                throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, errMsg);
            }
        });

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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

}
