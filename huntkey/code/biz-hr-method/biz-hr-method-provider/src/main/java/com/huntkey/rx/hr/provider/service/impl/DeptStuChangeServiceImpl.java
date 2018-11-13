/**
 * Project Name:biz-hr-method-provider
 * File Name:DeptStuChangeServiceImpl.java
 * Package Name:com.huntkey.rx.hr.provider.service.impl
 * Date:2017年11月16日下午1:57:37
 * Copyright (c) 2017 嘉源锐信 All Rights Reserved.
 *
*/

package com.huntkey.rx.hr.provider.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.DeptstuchangeorderEntity;
import com.huntkey.rx.edm.entity.DepttreeEntity;
import com.huntkey.rx.edm.entity.MdepMdepChagSetaEntity;
import com.huntkey.rx.edm.entity.OdscOdscChagSetaEntity;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.provider.service.DeptTreeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.provider.dao.DeptStuChangeDao;
import com.huntkey.rx.hr.provider.dao.DeptTreeDao;
import com.huntkey.rx.hr.provider.service.DeptStuChangeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName:DeptStuChangeServiceImpl 部门异动单新增
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 * Date:     2017年11月16日 下午1:57:37
 * @author   lijie
 * @version  
 * @see 	 
 */
@Component
public class DeptStuChangeServiceImpl implements DeptStuChangeService{

    private static Logger logger = LoggerFactory.getLogger(DeptStuChangeServiceImpl.class);

    @Autowired
    DeptStuChangeDao deptStuChangeDao;
    
    @Autowired
    DeptTreeDao deptTreeDao;

    @Autowired
    DeptTreeService deptTreeService;
    
    @Value("${hr.lvl.step}")
    int step;
    
    static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    static final String MAX_TIME = "9999-12-31 23:59:59";
    static final String LNAME_SEP = ".";
    static final String LVL_SEP = ",";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addDept(String orderId) {
        Result result = new Result();
        // 查询新增标识的表单和节点信息
        DeptstuchangeorderEntity deptstuchangeorderEntity = deptStuChangeDao.queryOrder(DeptStuChangeOrderConstants.ODSC_TYPE_ADD_ID, orderId,
                OrderConstants.ORDE_STATUS_4, OdscChagSetConstants.ODSC_FLAG_ADD);

        DeptStuChangeOrderDTO deptStuDTO = JSONObject.parseObject(JSON.toJSONString(deptstuchangeorderEntity), DeptStuChangeOrderDTO.class);
        
        if(deptStuDTO == null) {
            result.setErrMsg("部门异动单数据不存在");
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
        List<OdscChagSetDTO> nodes = deptStuDTO.getOdscChagSet();
        
        if(nodes == null || nodes.isEmpty()) {
            result.setErrMsg("部门异动单数据不存在");
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
        // 下级部门编制累加
        for(OdscChagSetDTO node : nodes) {
            int parNum = 0;

            List<OdscChagSetDTO> childSet = nodes.stream().filter(s -> s.getOdscLvl().startsWith(node.getOdscLvl())
                    && !s.getOdscLvl().equals(node.getOdscLvl())).collect(Collectors.toList());

            for (OdscChagSetDTO child : childSet){
                parNum += child.getOdscTlPnum();
            }
            node.setOdscLlPnum(parNum);
        }
        // 部门全称的拼接
        Map<String,String> names = new HashMap<String,String>();
        Set<OdscChagSetDTO> rootNodes = new HashSet<OdscChagSetDTO>();
        
        for(OdscChagSetDTO node : nodes){
            if(!StringUtil.isNullOrEmpty(names.get(node.getOdscDeptCode()))) {
                continue;
            }
            getNodeName(node,nodes,names,rootNodes);
        }


        List<OdscChagSetDTO> sortRootNodes = rootNodes.stream().sorted().collect(Collectors.toList());
        // 节点入库
        for(OdscChagSetDTO node : sortRootNodes){
            int sumLL = node.getOdscTlPnum() + node.getOdscLlPnum();
            if(sumLL != 0) {
                updateLLNum(node.getOdscPdept(), sumLL);
            }
            storeAddData(node, nodes, names, deptStuDTO.getOdscBeg());
        }
        
        // 修改单据状态为完成 - 应在流程中改状态信息
        updateDeptOrderStatus(deptStuDTO.getId(), OrderConstants.ORDE_STATUS_5);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result modifyDept(String orderId) {
        Result result = new Result();
        // 查询部门异动单据类型为部门修改的详细信息
        DeptstuchangeorderEntity deptstuchangeorderEntity = deptStuChangeDao.queryOrder(DeptStuChangeOrderConstants.ODSC_TYPE_MODIFY_ID, orderId,
                OrderConstants.ORDE_STATUS_4, null);
        if(deptstuchangeorderEntity == null) {
            result.setErrMsg("部门异动单数据不存在");
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
        //获取异动列表
        List<OdscOdscChagSetaEntity> nodes = deptstuchangeorderEntity.getOdsc_chag_set();
        if(nodes == null || nodes.isEmpty()) {
            result.setErrMsg("部门异动单不存在修改的数据");
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }

        //单据生效日期取，yyyy-MM-dd
        Date odscBeg = deptstuchangeorderEntity.getOdsc_beg();
        //批准方法执行的日期（当前日期），yyyy-MM-dd
        Date passDate = new Date();

        //存放新的待新增的变更记录集
        List<MdepMdepChagSetaEntity> newChangeSet = new ArrayList<>();
        for(OdscOdscChagSetaEntity node : nodes) {

            //根据部门code获取部门id
            String deptCode = node.getOdsc_dept_code();

            DepttreeEntity deptObj = deptTreeDao.findByCode(deptCode);
            if (deptObj == null){
                result.setErrMsg("对应部门不存在");
                result.setRetCode(Result.RECODE_ERROR);
                return result;
            }

            String deptId = deptObj.getId();
            int seq = deptObj.getMdep_seq();

            //只修改了编制
            if (node.getOdsc_flag().equals(OdscChagSetConstants.ODSC_FLAG_MODIFY_STAFFING)){
                //只更新变更记录集中的部门编制
                List<MdepMdepChagSetaEntity> changeSet = deptTreeDao.findChangeSetByPids(new String[]{deptId}, deptstuchangeorderEntity.getOdsc_beg());

                if (changeSet != null && changeSet.size() > 0){
                    MdepMdepChagSetaEntity changeObj = changeSet.get(0);
                    //用于更新的对象
                    MdepMdepChagSetaEntity updateObj = new MdepMdepChagSetaEntity();
                    updateObj.setId(changeObj.getId());
                    updateObj.setMdep_tnum_his(node.getOdsc_tl_pnum());
                    deptTreeDao.updateMdepChagSet(updateObj);
                }
            }

            DepttreeEntity depttreeEntity = new DepttreeEntity();
            depttreeEntity.setId(deptId);
            depttreeEntity.setMdep_name(node.getOdsc_name());
            depttreeEntity.setMdep_sname(node.getOdsc_sname());
            depttreeEntity.setMdep_mcop(node.getOdsc_mcop());
            depttreeEntity.setMdep_grade(node.getOdsc_dgrade());
            depttreeEntity.setMdep_rpak(node.getOdsc_rpak());
            depttreeEntity.setMdep_duty(node.getOdsc_function());
            depttreeEntity.setMdep_tl_num(node.getOdsc_tl_pnum());
            depttreeEntity.setMdep_ll_num(node.getOdsc_ll_pnum());
            depttreeEntity.setMdep_beg(deptstuchangeorderEntity.getOdsc_beg());

            if (odscBeg.compareTo(passDate) == -1 || odscBeg.compareTo(passDate) == 0){ //当 生效日期 <= 批准方法执行的日期 立即执行
                //更新depttree部门类
                deptTreeDao.updateDepttree(depttreeEntity);
                //获取部门编制数修改前后的差，用于所有上级部门的下层编制计算
                int differencePnum = node.getOdsc_tl_pnum() - node.getOdsc_tl_old();
                //当修改了部门编制数，更新上级部门编制
                if (differencePnum != 0){
                    //获取所有上级部门id集合
                    List<String> superiorIdList = new ArrayList<>();
                    deptTreeService.getDeptSuperiorIdList(superiorIdList, deptId, new Date(), Integer.MAX_VALUE);
                    //计算所有上级部门的下层编制，返回用于更新的所有上级部门对象集合
                    List<DepttreeEntity> superiorDeptList = superiorIdList.stream().map(sid -> {
                        DepttreeEntity superiorObj = deptTreeDao.findById(sid);
                        DepttreeEntity parDept = new DepttreeEntity();
                        parDept.setId(sid);
                        int newLlNum = superiorObj.getMdep_ll_num() + differencePnum;
                        parDept.setMdep_ll_num(newLlNum);
                        return parDept;
                    }).collect(Collectors.toList());
                    //将计算好了下层编制数的上级部门对象集合，批量更新
                    deptTreeDao.updateDepttree(superiorDeptList);
                }
            }

            if (node.getOdsc_flag().equals(OdscChagSetConstants.ODSC_FLAG_MODIFY)){
                //更新最近的变更记录的失效日期
                List<MdepMdepChagSetaEntity> changeSet = deptTreeDao.findChangeSetByPids(new String[]{deptId}, deptstuchangeorderEntity.getOdsc_beg());
                Date newDeptEnd = DateUtil.parseFormatDate(MAX_TIME,YYYY_MM_DD_HH_MM_SS);
                String deptPar = "";
                String deptLvl = "";
                if (changeSet != null && changeSet.size() > 0){
                    MdepMdepChagSetaEntity changeObj = changeSet.get(0);
                    //用于新增的记录需要取到生效日期时刻的上级部门和部门层级码，例如调动单在修改单之前批准通过
                    deptPar = changeObj.getMdep_par_his();
                    deptLvl = changeObj.getMdep_lvl_his();
                    //用于更新的对象
                    MdepMdepChagSetaEntity updateObj = new MdepMdepChagSetaEntity();
                    newDeptEnd = changeObj.getMdep_end_his();
                    updateObj.setId(changeObj.getId());
                    updateObj.setMdep_end_his(deptstuchangeorderEntity.getOdsc_beg());
                    deptTreeDao.updateMdepChagSet(updateObj);
                }
                //插入变更记录集
                DeptTreeInfoDTO deptTreeInfoDTO = JSONObject.parseObject(JSON.toJSONString(depttreeEntity), DeptTreeInfoDTO.class);
                DeptTreeDTO transfersDept = new DeptTreeDTO();
                BeanUtils.copyProperties(deptTreeInfoDTO, transfersDept);
                MdepMdepChagSetaEntity changeDept = JSONObject.parseObject(JSON.toJSONString(transfersDept), MdepMdepChagSetaEntity.class);
                changeDept.setMdep_end_his(newDeptEnd);
                changeDept.setMdep_seq_his(seq);
                changeDept.setMdep_tnum_his(node.getOdsc_tl_pnum());
                if (!StringUtil.isNullOrEmpty(deptPar)){
                    changeDept.setMdep_par_his(deptPar);
                }
                if (!StringUtil.isNullOrEmpty(deptLvl)){
                    changeDept.setMdep_lvl_his(deptLvl);
                }
                newChangeSet.add(changeDept);
            }
        }
        if (newChangeSet!= null && newChangeSet.size() > 0){
            deptTreeDao.addDeptChagSetRecord(newChangeSet);
        }

        updateDeptOrderStatus(deptstuchangeorderEntity.getId(), OrderConstants.ORDE_STATUS_5);
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updateDeptOrderStatus(String orderId, String orderStatus) {
        // 修改单据状态为完成 - 应在流程中改状态信息
        DeptStuChangeOrderDTO deptStuChangeOrderDTO = new DeptStuChangeOrderDTO();
        deptStuChangeOrderDTO.setId(orderId);
        deptStuChangeOrderDTO.setOrdeStatus(orderStatus);
        DeptstuchangeorderEntity deptstuchangeorderEntity = JSONObject.parseObject(JSON.toJSONString(deptStuChangeOrderDTO),DeptstuchangeorderEntity.class);
        deptTreeDao.updateDeptStuChangeOrder(deptstuchangeorderEntity);
    }

    /**
     * 
     * updateLLNum: 更新祖先节点的下级编制
     * @author lijie
     * @param parentId 父节点id
     * @param sumLL 下级总编制
     */
    private void updateLLNum(String parentId, int sumLL) {
        
        DepttreeEntity depttreeEntity = deptTreeDao.findById(parentId);
        
        List<DepttreeEntity> dp_nodes = new ArrayList<>();
        
        while(depttreeEntity != null){
            DepttreeEntity dept = new DepttreeEntity();
            dept.setId(depttreeEntity.getId());
            dept.setMdep_ll_num(depttreeEntity.getMdep_ll_num() + sumLL);
            dp_nodes.add(dept);
            
            if(StringUtil.isNullOrEmpty(depttreeEntity.getMdep_par())) {
                break;
            }
            depttreeEntity = deptTreeDao.findById(depttreeEntity.getMdep_par());
        }
     
        deptTreeDao.updateDepttree(dp_nodes);
    }

    /**
     * 
     * storeAddData: 节点入库
     * @author lijie
     * @param node 头节点
     * @param nodes 所有需新增的节点
     * @param names 部门全称
     */
    private void storeAddData(OdscChagSetDTO node, List<OdscChagSetDTO> nodes, Map<String, String> names, Date begin) {
        // 查询depttree 父节点信息
        String parentId = node.getOdscPdept();
        DeptTreeInfoDTO parent = JSONObject.parseObject(JSON.toJSONString(deptTreeDao.findById(parentId)),
                DeptTreeInfoDTO.class);
        
        String lvlCode = parent.getMdepLvlCode();
        int maxSeq = deptTreeDao.getMaxSeqByParentId(parentId);
        
        // node修改入库 - 返回id 
        String nodeId = deptTreeDao.add(setValue(node,lvlCode,maxSeq, names, begin));
        
        // 查找node的子节点
        List<OdscChagSetDTO> childs = nodes.stream().filter(s->{
            
            int s_len = s.getOdscLvl().split(LVL_SEP).length;
            int n_len = node.getOdscLvl().split(LVL_SEP).length;
            
            if(s_len - 1 == n_len && s.getOdscLvl().startsWith(node.getOdscLvl()))
                return true;
            
            return false;
        }).collect(Collectors.toList());
        
        if(childs == null || childs.isEmpty()) {
            return;
        }
        for(OdscChagSetDTO to : childs){
            to.setOdscPdept(nodeId);
            storeAddData(to, nodes, names,begin);
        }
    }
    
    /**
     * 
     * setValue:将节点值赋值为部门表字段
     * @author lijie
     * @param node 部门异动单节点
     * @param lvlCode 父节点层级编码
     * @param maxSeq 子节点中的最大序号
     * @param names 部门全称集合
     */
    private DepttreeEntity setValue(OdscChagSetDTO node, String lvlCode, int maxSeq,
                          Map<String, String> names, Date begin) {
        int seq = maxSeq + 1;
        String lvl = lvlCode  +  String.format("%0" + step + "d", seq) + LVL_SEP;

        DepttreeEntity depttreeEntity = new DepttreeEntity();
        depttreeEntity.setMdep_code(node.getOdscDeptCode());
        depttreeEntity.setMdep_name(node.getOdscName());
        depttreeEntity.setMdep_sname(node.getOdscSname());
        depttreeEntity.setMdep_par(node.getOdscPdept());
        depttreeEntity.setMdep_lvl_code(lvl);
        depttreeEntity.setMdep_grade(node.getOdscDgrade());
        depttreeEntity.setMdep_seq(seq);
        //部门全称暂不做
        //depttreeEntity.setMdep_lname(names.get(node.getOdscDeptCode()));
        depttreeEntity.setMdep_rpak(node.getOdscRpak());
        depttreeEntity.setMdep_mcop(node.getOdscMcop());
        depttreeEntity.setMdep_duty(node.getOdscFunction());
        depttreeEntity.setMdep_beg(begin);
        depttreeEntity.setMdep_end(DateUtil.parseFormatDate(MAX_TIME, YYYY_MM_DD_HH_MM_SS));
        depttreeEntity.setMdep_tl_num(node.getOdscTlPnum());
        depttreeEntity.setMdep_ll_num(node.getOdscLlPnum());

        List<MdepMdepChagSetaEntity> mdepChagSetaEntityList = new ArrayList<>();
        MdepMdepChagSetaEntity mdepChagSetaEntity = new MdepMdepChagSetaEntity();
        mdepChagSetaEntity.setMdep_name_his(node.getOdscName());
        mdepChagSetaEntity.setMdep_sname_his(node.getOdscSname());
        mdepChagSetaEntity.setMdep_par_his(node.getOdscPdept());
        mdepChagSetaEntity.setMdep_lvl_his(lvl);
        mdepChagSetaEntity.setMdep_grade_his(node.getOdscDgrade());
        mdepChagSetaEntity.setMdep_seq_his(seq);
        mdepChagSetaEntity.setMdep_rpak_his(node.getOdscRpak());
        mdepChagSetaEntity.setMdep_mcop_his(node.getOdscMcop());
        mdepChagSetaEntity.setMdep_duty_his(node.getOdscFunction());
        mdepChagSetaEntity.setMdep_beg_his(begin);
        mdepChagSetaEntity.setMdep_end_his(DateUtil.parseFormatDate(MAX_TIME, YYYY_MM_DD_HH_MM_SS));
        mdepChagSetaEntity.setMdep_tnum_his(node.getOdscTlPnum());
        mdepChagSetaEntityList.add(mdepChagSetaEntity);

        depttreeEntity.setMdep_chag_set(mdepChagSetaEntityList);
        return depttreeEntity;
    }

    /**
     * 
     * getNodeName:获取节点全称
     * @author lijie
     * @param node 当前节点
     * @param nodes 节点集合
     * @param rootNodes 根节点集合列表
     */
    private void getNodeName(OdscChagSetDTO node, List<OdscChagSetDTO> nodes, Map<String,String> names,
                             Set<OdscChagSetDTO> rootNodes) {
        
        String lvl = node.getOdscLvl();
        String sub_lvl = lvl.substring(0, lvl.lastIndexOf(LVL_SEP));
        String par_lvl = lvl.substring(0, sub_lvl.lastIndexOf(LVL_SEP) + 1);
        
        // 异动单 状态为 1 的数据 至少都是两层 
        if(StringUtil.isNullOrEmpty(par_lvl)) {
            throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "部门异动单数据异常");
        }
        // 查找上级节点层级编码
        List<OdscChagSetDTO> par_nodes = nodes.stream().filter(s->par_lvl.equals(s.getOdscLvl()))
                .collect(Collectors.toList());
        
        // 父节点编码 不在当前列表中  那么在异动单表中找状态为0
        if(par_nodes == null || par_nodes.isEmpty()){

            if(names.containsKey(node.getOdscDeptCode())) {
                return;
            }
            OdscOdscChagSetaEntity odscChagSetaEntity = deptStuChangeDao.queryNode(node.getPid(), par_lvl, OdscChagSetConstants.ODSC_FLAG_NONE);
            OdscChagSetDTO parent = JSONObject.parseObject(JSON.toJSONString(odscChagSetaEntity), OdscChagSetDTO.class);
            if(parent == null) {
                throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "部门异动单数据异常");
            }

            DepttreeEntity deptObj = deptTreeDao.findByCode(parent.getOdscDeptCode());
            if (deptObj == null){
                throw new ApplicationException(Result.RECODE_VALIDATE_ERROR, "对应部门不存在");
            }

            DeptTreeInfoDTO depttreeTo = JSONObject.parseObject(JSON.toJSONString(deptObj), DeptTreeInfoDTO.class);
            
            names.put(node.getOdscDeptCode(), depttreeTo.getMdepLname() + LNAME_SEP + node.getOdscName());
            
            // 头节点赋值上id
            node.setOdscPdept(depttreeTo.getId());
            
            rootNodes.add(node);
            
            return;
        }
            
        getNodeName(par_nodes.get(0), nodes, names, rootNodes);
        
        names.put(node.getOdscDeptCode(), names.get(par_nodes.get(0).getOdscDeptCode()) + LNAME_SEP + node.getOdscName());
    }

}

