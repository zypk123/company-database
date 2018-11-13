package com.huntkey.rx.hr.provider.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSymbolEnum;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.provider.dao.DeptTreeDao;

@Component
public class DeptTreeDaoImpl implements DeptTreeDao {

    private static final Logger LOG = LoggerFactory.getLogger(DeptTreeDaoImpl.class);

    @Autowired
    OrmService ormService;

    @Autowired
    BizFormService bizFormService;

    @Override
    public DepttreeEntity findById(String id) {
        DepttreeEntity depttreeEntity = null;
        try {
            depttreeEntity = ormService.load(DepttreeEntity.class, id);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门类错误：" + e.getMessage());
        }
        return depttreeEntity;
    }

    @Override
    public DepttreeEntity findByCode(String code) {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(DepttreeProperty.MDEP_CODE, code));
        ormParam.setWhereExp(whereCondition);

        DepttreeEntity depttreeEntity = null;
        try {
            List<DepttreeEntity> depttreeEntityList = ormService.selectBeanList(DepttreeEntity.class, ormParam);

            if (depttreeEntityList == null || depttreeEntityList.isEmpty()){
                return null;
            }

            if(depttreeEntityList.size() > 1){
                throw new ApplicationException(Result.RECODE_ERROR, String.format("根据部门编码%s找到多条部门记录" , code));
            }

            depttreeEntity = depttreeEntityList.get(0);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门类错误：" + e.getMessage());
        }

        return depttreeEntity;
    }

    @Override
    public List<DepttreeEntity> findByIdList(List<String> idList) {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getInXML(EdmSysColumn.ID, idList.toArray()));
        ormParam.setWhereExp(whereCondition);
        List<DepttreeEntity> depttreeEntityList;
        try {
            depttreeEntityList = ormService.selectBeanList(DepttreeEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门类错误：" + e.getMessage());
        }
        if (depttreeEntityList == null){
            depttreeEntityList = new ArrayList<>();
        }
        return depttreeEntityList;
    }

    @Override
    public List<DepttreeEntity> findByParId(String parId) {
        //根据session中的当前企业对象
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        String enterpriseId = sessionEntity.getEnterpriseId();

        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(DepttreeProperty.MDEP_PAR, parId), ormParam.getEqualXML(EdmProperty.EDMD_ENTE, enterpriseId));
        if (StringUtil.isNullOrEmpty(parId)){
            whereCondition = OrmParam.and(ormParam.getEqualXML(EdmProperty.EDMD_ENTE, enterpriseId),
                    OrmParam.or(ormParam.getIsNull(DepttreeProperty.MDEP_PAR), ormParam.getEqualXML(DepttreeProperty.MDEP_PAR, "")));
        }
        ormParam.setWhereExp(whereCondition);
        ormParam.setOrderExp(SQLSortEnum.ASC, DepttreeProperty.MDEP_SEQ);

        List<DepttreeEntity> depttreeEntityList = null;
        try {
            depttreeEntityList = ormService.selectBeanList(DepttreeEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门类错误：" + e.getMessage());
        }
        return depttreeEntityList;
    }

    /**
     * 根据部门id或者code查询部门对象
     * @param dept
     * @param type
     * @return
     */
    @Override
    public DepttreeEntity findDeptObj(String dept, String type)
    {
        DepttreeEntity depttreeEntity = null;
        if (EdmSysColumn.ID.equals(type)){
            depttreeEntity = findById(dept);
        }else if (DepttreeProperty.MDEP_CODE.equals(type)){
            depttreeEntity = findByCode(dept);
        }
        return depttreeEntity;
    }

    @Override
    public String add(DepttreeEntity depttreeEntity) {
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        String userId = sessionEntity.getEmployeeId();
        depttreeEntity.setCreuser(userId);
        depttreeEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
        String id = null;
        try {
            id = ormService.insert(depttreeEntity).toString();
            depttreeEntity.setId(id);
            EdmUtil.setPropertyBaseEntitiesSysColumns(DepttreeEntity.class, depttreeEntity, depttreeEntity.getMdep_chag_set(), SQLCurdEnum.INSERT);
            int count = ormService.insert(depttreeEntity.getMdep_chag_set());
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "新增部门错误");
        }
        return id;
    }

    @Override
    public List<MdepMdepChagSetaEntity> findChangeSet(String pid, Date date) {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID, pid),
                ormParam.getLessThanAndEqualXML(MdepMdepChagSetaProperty.MDEP_BEG_HIS, date),
                ormParam.getGreaterThanXML(MdepMdepChagSetaProperty.MDEP_END_HIS, date));
        ormParam.setWhereExp(whereCondition);
        ormParam.setOrderExp(SQLSortEnum.ASC, MdepMdepChagSetaProperty.MDEP_SEQ_HIS);

        List<MdepMdepChagSetaEntity> mdepChagSetaEntityList;
        try {
            mdepChagSetaEntityList = ormService.selectBeanList(MdepMdepChagSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门变更记录错误：" + e.getMessage());
        }
        if (mdepChagSetaEntityList == null){
            mdepChagSetaEntityList = new ArrayList<>();
        }
        return mdepChagSetaEntityList;
    }

    @Override
    public MdepMdepChagSetaEntity findChangeSetMaxEnd(String pid) {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        ormParam.setWhereExp(whereCondition);
        ormParam.setOrderExp(SQLSortEnum.DESC, MdepMdepChagSetaProperty.MDEP_END_HIS);

        List<MdepMdepChagSetaEntity> mdepChagSetaEntityList;
        try {
            mdepChagSetaEntityList = ormService.selectBeanList(MdepMdepChagSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门变更记录错误：" + e.getMessage());
        }
        if (mdepChagSetaEntityList != null && mdepChagSetaEntityList.size() > 0){
            return mdepChagSetaEntityList.get(0);
        }else{
            return null;
        }
    }


    @Override
    public List<MdepMdepChagSetaEntity> findChangeSetBegDateGT(String pid, Date begDate) {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID, pid),
                ormParam.getGreaterThanXML(MdepMdepChagSetaProperty.MDEP_BEG_HIS, begDate));
        ormParam.setWhereExp(whereCondition);
        List<MdepMdepChagSetaEntity> mdepChagSetaEntityList;
        try {
            mdepChagSetaEntityList = ormService.selectBeanList(MdepMdepChagSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门变更记录错误：" + e.getMessage());
        }
        if (mdepChagSetaEntityList == null){
            mdepChagSetaEntityList = new ArrayList<>();
        }
        return mdepChagSetaEntityList;
    }

    @Override
    public List<MdepMdepChagSetaEntity> findChangeSetByParId(String parId, Date date) {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(MdepMdepChagSetaProperty.MDEP_PAR_HIS, parId),
                ormParam.getLessThanAndEqualXML(MdepMdepChagSetaProperty.MDEP_BEG_HIS, date),
                ormParam.getGreaterThanXML(MdepMdepChagSetaProperty.MDEP_END_HIS, date));
        ormParam.setWhereExp(whereCondition);
        ormParam.setOrderExp(SQLSortEnum.ASC, MdepMdepChagSetaProperty.MDEP_SEQ_HIS);
        List<MdepMdepChagSetaEntity> mdepChagSetaEntityList;
        try {
            mdepChagSetaEntityList = ormService.selectBeanList(MdepMdepChagSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门变更记录错误：" + e.getMessage());
        }
        if (mdepChagSetaEntityList == null){
            mdepChagSetaEntityList = new ArrayList<>();
        }
        return mdepChagSetaEntityList;
    }

    @Override
    public List<MdepMdepChagSetaEntity> findChangeSetByPids(String[] pids, Date date) {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getInXML(EdmSysColumn.PID, pids),
                ormParam.getGreaterThanXML(MdepMdepChagSetaProperty.MDEP_END_HIS, date),
                ormParam.getLessThanAndEqualXML(MdepMdepChagSetaProperty.MDEP_BEG_HIS, date));
        ormParam.setWhereExp(whereCondition);
        ormParam.setOrderExp(SQLSortEnum.ASC, MdepMdepChagSetaProperty.MDEP_SEQ_HIS);
        List<MdepMdepChagSetaEntity> mdepChagSetaEntityList;
        try {
            mdepChagSetaEntityList = ormService.selectBeanList(MdepMdepChagSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门变更记录错误：" + e.getMessage());
        }
        if (mdepChagSetaEntityList == null){
            mdepChagSetaEntityList = new ArrayList<>();
        }
        return mdepChagSetaEntityList;
    }

    @Override
    public List<MdepMdepChagSetaEntity> checkDeptName(String parId, Date begDate, String deptId, String deptName) {
        OrmParam ormParam = new OrmParam();
        List<String> conditionList = new ArrayList<>();

        conditionList.add(ormParam.getEqualXML(MdepMdepChagSetaProperty.MDEP_PAR_HIS, parId));
        conditionList.add(ormParam.getGreaterThanXML(MdepMdepChagSetaProperty.MDEP_END_HIS, begDate));
        conditionList.add(ormParam.getEqualXML(MdepMdepChagSetaProperty.MDEP_NAME_HIS,deptName));

        if (!StringUtil.isNullOrEmpty(deptId)) {
            conditionList.add(ormParam.getUnequalXML(EdmSysColumn.PID, deptId));
        }

        String whereCondition = OrmParam.and(conditionList);
        ormParam.setWhereExp(whereCondition);

        List<MdepMdepChagSetaEntity> mdepChagSetaEntityList;
        try {
            mdepChagSetaEntityList = ormService.selectBeanList(MdepMdepChagSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门变更记录错误：" + e.getMessage());
        }
        if (mdepChagSetaEntityList == null){
            mdepChagSetaEntityList = new ArrayList<>();
        }
        return mdepChagSetaEntityList;
    }

    @Override
    public ParkEntity findParkById(String id) {
        ParkEntity parkEntity = null;
        try {
            parkEntity = ormService.load(ParkEntity.class, id);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询园区类错误：" + e.getMessage());
        }
        return parkEntity;
    }

    @Override
    public RelationEntity findRelationById(String id) {
        RelationEntity relationEntity = null;
        try {
            relationEntity = ormService.load(RelationEntity.class, id);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询伙伴类错误：" + e.getMessage());
        }
        return relationEntity;
    }

    @Override
    public EmployeeEntity findEmployeeById(String id) {
        EmployeeEntity employeeEntity = null;
        try {
            employeeEntity = ormService.load(EmployeeEntity.class, id);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询员工类错误：" + e.getMessage());
        }

        return employeeEntity;
    }

    @Override
    public JobpositionEntity findJobpositionById(String id) {
        JobpositionEntity jobpositionEntity = null;
        try {
            jobpositionEntity = ormService.load(JobpositionEntity.class, id);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询岗位类错误：" + e.getMessage());
        }

        return jobpositionEntity;
    }

    @Override
    public List<MdepMdepChgrSetaEntity> findChargeSetByPidAndDate(String pid, Date date) {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID, pid),
                ormParam.getLessThanAndEqualXML(MdepMdepChgrSetaProperty.MDEP_CHGR_BEG, date),
                ormParam.getGreaterThanXML(MdepMdepChgrSetaProperty.MDEP_CHGR_END, date));
        ormParam.setWhereExp(whereCondition);

        List<MdepMdepChgrSetaEntity> mdepChgrSetaEntityList;
        try {
            mdepChgrSetaEntityList = ormService.selectBeanList(MdepMdepChgrSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门负责人集合错误：" + e.getMessage());
        }
        if (mdepChgrSetaEntityList == null){
            mdepChgrSetaEntityList = new ArrayList<>();
        }
        return mdepChgrSetaEntityList;
    }

    @Override
    public List<DeptstuchangeorderEntity> findDeptChangeOrderByTypesAndStatus(String[] ocscTypes, String[] ordeStatus) {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getInXML(DeptstuchangeorderProperty.ODSC_TYPE, ocscTypes),
                ormParam.getInXML(OrderProperty.ORDE_STATUS, ordeStatus));
        ormParam.setWhereExp(whereCondition);

        List<DeptstuchangeorderEntity> deptstuchangeorderEntityList;
        try {
            deptstuchangeorderEntityList = ormService.selectBeanList(DeptstuchangeorderEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门结构异动单错误：" + e.getMessage());
        }
        if (deptstuchangeorderEntityList == null){
            deptstuchangeorderEntityList = new ArrayList<>();
        }
        return deptstuchangeorderEntityList;
    }

    @Override
    public List<OdscOdscChagSetaEntity> findOdscChagSetByDeptCodes(List<String> deptCodes, String[] odscFlags) {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getInXML(OdscOdscChagSetaProperty.ODSC_DEPT_CODE, deptCodes.toArray()),
                ormParam.getInXML(OdscOdscChagSetaProperty.ODSC_FLAG, odscFlags));
        ormParam.setWhereExp(whereCondition);
        List<OdscOdscChagSetaEntity> odscChagSetaEntityList;
        try {
            odscChagSetaEntityList = ormService.selectBeanList(OdscOdscChagSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门结构异动单变更记录错误：" + e.getMessage());
        }
        if (odscChagSetaEntityList == null){
            odscChagSetaEntityList = new ArrayList<>();
        }
        return odscChagSetaEntityList;
    }

    @Override
    public String insertDeptStuChangeOrder(DeptstuchangeorderEntity deptstuchangeorderEntity) {
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        //制单时间
        deptstuchangeorderEntity.setOrde_date(new Date());
        //所属企业对象id
        deptstuchangeorderEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
        String userId = sessionEntity.getEmployeeId();
        deptstuchangeorderEntity.setCreuser(userId);

        String id = null;
        try {
            id = ormService.insert(deptstuchangeorderEntity).toString();
            deptstuchangeorderEntity.setId(id);
            EdmUtil.setPropertyBaseEntitiesSysColumns(DeptstuchangeorderEntity.class,deptstuchangeorderEntity, deptstuchangeorderEntity.getOdsc_chag_set(), SQLCurdEnum.INSERT);
            ormService.insert(deptstuchangeorderEntity.getOdsc_chag_set());
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "新增部门异动单错误：" + e.getMessage());
        }

        return id;
    }

    @Override
    public int updateDeptStuChangeOrder(DeptstuchangeorderEntity deptstuchangeorderEntity){
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        //制单时间
        deptstuchangeorderEntity.setOrde_date(new Date());
        //所属企业对象id
        deptstuchangeorderEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
        String userId = sessionEntity.getEmployeeId();
        deptstuchangeorderEntity.setModuser(userId);
        int resultCount = 0;
        try {
            //新增异动列表属性集
            if (deptstuchangeorderEntity.getOdsc_chag_set() != null || deptstuchangeorderEntity.getOdsc_chag_set().size() > 0) {
                EdmUtil.setPropertyBaseEntitiesSysColumns(DeptstuchangeorderEntity.class, deptstuchangeorderEntity, deptstuchangeorderEntity.getOdsc_chag_set(), SQLCurdEnum.INSERT);
                ormService.insert(deptstuchangeorderEntity.getOdsc_chag_set());
            }
            //更新异动单数据
            resultCount  = ormService.updateSelective(deptstuchangeorderEntity);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "更新部门异动单错误：" + e.getMessage());
        }

        return resultCount;
    }

    @Override
    public int updateDepttree(List<DepttreeEntity> depttreeEntityList) {
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        int resultCount = depttreeEntityList.stream().map(dept ->{
            dept.setEdmd_ente(sessionEntity.getEnterpriseId());
            dept.setModuser(sessionEntity.getEmployeeId());
            try {
                return ormService.updateSelective(dept);
            } catch (Exception e) {
                throw new ApplicationException(Result.RECODE_ERROR, "更新部门类错误：" + e.getMessage());
            }
        }).reduce(0, Integer::sum);

        return resultCount;
    }

    @Override
    public int updateDepttree(DepttreeEntity depttreeEntity)
    {
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        depttreeEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
        depttreeEntity.setModuser(sessionEntity.getEmployeeId());
        int resultCount = 0;
        try {
            resultCount = ormService.updateSelective(depttreeEntity);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "更新部门类错误：" + e.getMessage());
        }
        return resultCount;
    }

    @Override
    public int updateMdepChagSet(MdepMdepChagSetaEntity mdepChagSetaEntity)
    {
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        mdepChagSetaEntity.setModuser(sessionEntity.getEmployeeId());
        int resultCount = 0;
        try {
            resultCount = ormService.updateSelective(mdepChagSetaEntity);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "更新部门类变更记录错误：" + e.getMessage());
        }
        return resultCount;
    }

    /**
     * 部门变更记录集保存接口
     * @param mdepChagSetaEntityList
     * @return
     */
    @Override
    public List<String> addDeptChagSetRecord(List<MdepMdepChagSetaEntity> mdepChagSetaEntityList) {

        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();

        List<String> ids = mdepChagSetaEntityList.stream().map(dept ->{
            dept.setClassName(EdmUtil.getEdmClassName(DepttreeEntity.class));
            dept.setCreuser(sessionEntity.getEmployeeId());
            try {
                return ormService.insert(dept).toString();
            } catch (Exception e) {
                throw new ApplicationException(Result.RECODE_ERROR, "新增部门类变更记录错误：" + e.getMessage());
            }
        }).collect(Collectors.toList());

        return ids;
    }

    @Override
    public int getMaxSeqByParentId(String parId) {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(DepttreeProperty.MDEP_SEQ);
        String whereCondition = OrmParam.and(ormParam.getEqualXML(DepttreeProperty.MDEP_PAR, parId));
        ormParam.setWhereExp(whereCondition);
        ormParam.setOrderExp(SQLSortEnum.DESC, DepttreeProperty.MDEP_SEQ);

        List<DepttreeEntity> depttreeEntityList = null;
        try {
            depttreeEntityList = ormService.selectBeanList(DepttreeEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门类错误：" + e.getMessage());
        }
        if(depttreeEntityList == null || depttreeEntityList.isEmpty()) {
            return 0;
        }

        return depttreeEntityList.get(0).getMdep_seq();
    }


    @Override
    public DeptstuchangeorderEntity findDeptChangeOrderByNbr(String orderNbr) {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(OrderProperty.ORDE_NBR, orderNbr));
        ormParam.setWhereExp(whereCondition);
        DeptstuchangeorderEntity deptstuchangeorderEntity = null;
        try {
            List<DeptstuchangeorderEntity> deptstuchangeorderEntityList = ormService.selectBeanList(DeptstuchangeorderEntity.class, ormParam);
            if(deptstuchangeorderEntityList != null && deptstuchangeorderEntityList.size()>0) {
                deptstuchangeorderEntity = deptstuchangeorderEntityList.get(0);
            }
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门异动单错误：" + e.getMessage());
        }
        return deptstuchangeorderEntity;
    }

    @Override
    public DeptstuchangeorderEntity findDeptChangeOrderById(String id) {
        DeptstuchangeorderEntity deptstuchangeorderEntity = null;
        try {
            deptstuchangeorderEntity = ormService.load(DeptstuchangeorderEntity.class, id);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门异动单错误：" + e.getMessage());
        }

        return deptstuchangeorderEntity;
    }

    @Override
    public List<OdscOdscChagSetaEntity> findOdscChagSetByPid(String pid) {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID, pid));
        ormParam.setWhereExp(whereCondition);
        ormParam.setOrderExp(SQLSortEnum.ASC, OdscOdscChagSetaProperty.ODSC_LVL);

        List<OdscOdscChagSetaEntity> odscChagSetaEntityList;
        try {
            odscChagSetaEntityList = ormService.selectBeanList(OdscOdscChagSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门异动单异动列表错误：" + e.getMessage());
        }
        if (odscChagSetaEntityList == null){
            odscChagSetaEntityList = new ArrayList<>();
        }
        return odscChagSetaEntityList;

    }

    @Override
    public int deleteDepttreeByIds(String[] ids) {
        int id = 0;
        try {
            OrmParam ormParam_1 = new OrmParam();
            ormParam_1.setWhereExp(ormParam_1.getInXML(EdmSysColumn.PID, ids));
            ormService.delete(MdepMdepChagSetaEntity.class, ormParam_1);
            OrmParam ormParam_2 = new OrmParam();
            ormParam_2.setWhereExp(ormParam_2.getInXML(EdmSysColumn.ID, ids));
            id = ormService.delete(DepttreeEntity.class, ormParam_2);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "删除部门错误：" + e.getMessage());
        }
        return id;
    }

    @Override
    public int deleteOdscChagSetByIds(String[] ids) {
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getInXML(EdmSysColumn.ID, ids));
        int id = 0;
        try {
            id = ormService.delete(OdscOdscChagSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "删除部门错误：" + e.getMessage());
        }
        return id;
    }

    /**
     * 根据部门id列表和生效日期查询有效的部门信息
     * @param idList
     * @param mdepBeg
     * @return
     */
    @Override
    public List<DepttreeEntity> findDeptTreeList(List<String> idList , Date mdepBeg){
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(SQLSymbolEnum.ALLCOLUMNS.getSymbol());
        String whereCondition = OrmParam.and(
                ormParam.getInXML(EdmSysColumn.ID, idList.toArray()),
                ormParam.getLessThanAndEqualXML(DepttreeProperty.MDEP_BEG , mdepBeg),
                ormParam.getGreaterThanXML(DepttreeProperty.MDEP_END , mdepBeg)
                );
        ormParam.setWhereExp(whereCondition);
        List<DepttreeEntity> depttreeEntityList;
        try {
            depttreeEntityList = ormService.selectBeanList(DepttreeEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门类错误：" + e.getMessage());
        }
        if (depttreeEntityList == null){
            depttreeEntityList = new ArrayList<>();
        }
        return depttreeEntityList;
    }

}
