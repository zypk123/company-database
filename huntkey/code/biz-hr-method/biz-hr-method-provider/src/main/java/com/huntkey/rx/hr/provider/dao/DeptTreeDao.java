package com.huntkey.rx.hr.provider.dao;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.hr.common.model.DeptStuChangeOrderDTO;
import com.huntkey.rx.hr.common.model.DeptTreeDTO;
import com.huntkey.rx.hr.common.model.DeptTreeInfoDTO;
import com.huntkey.rx.hr.common.model.OdscChagSetDTO;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchParam;

/**
 * 部门类 数据访问
 */
public interface DeptTreeDao{

    /**
     * 根据主键查询单个对象
     * @param id
     * @return
     */
    DepttreeEntity findById(String id);


    /**
     * 根据主键查询单个对象
     * @param code
     * @return
     */
    DepttreeEntity findByCode(String code);

    /**
     * 根据主键列表查询对象集合
     * @param idList
     * @return
     */
    List<DepttreeEntity> findByIdList(List<String> idList);

    /**
     * 根据上级部门id查询子部门集合
     * @param parId
     * @return
     */
    List<DepttreeEntity> findByParId(String parId);


    /**
     * 查找部门对象
     * @param dept
     * @param type 根据Id或者code查询
     * @return
     */
    DepttreeEntity findDeptObj(String dept, String type);

    /**
     * 新增部门对象
     * @param depttreeEntity
     * @return
     */
    String add(DepttreeEntity depttreeEntity);

    /**
     * 根据部门id和生效日期查询部门变更记录集
     * @param pid
     * @param date
     * @return
     */
    List<MdepMdepChagSetaEntity> findChangeSet(String pid, Date date);

    MdepMdepChagSetaEntity findChangeSetMaxEnd(String pid);

    List<MdepMdepChagSetaEntity> findChangeSetBegDateGT(String pid, Date begDate);

    List<MdepMdepChagSetaEntity> findChangeSetByParId(String parId, Date date);

    List<MdepMdepChagSetaEntity> findChangeSetByPids(String[] pids, Date date);

    List<MdepMdepChagSetaEntity> checkDeptName(String parId, Date begDate, String deptId, String deptName);

    ParkEntity findParkById(String id);

    RelationEntity findRelationById(String id);

    EmployeeEntity findEmployeeById(String id);

    JobpositionEntity findJobpositionById(String id);

    List<MdepMdepChgrSetaEntity> findChargeSetByPidAndDate(String pid, Date date);
    
    int getMaxSeqByParentId(String parId);


    //----------------部门异动单------------------

    List<DeptstuchangeorderEntity> findDeptChangeOrderByTypesAndStatus(String[] ocscTypes, String[] ordeStatus);

    List<OdscOdscChagSetaEntity> findOdscChagSetByDeptCodes(List<String> deptCodes, String[] odscFlags);

    String insertDeptStuChangeOrder(DeptstuchangeorderEntity deptstuchangeorderEntity);

    int updateDeptStuChangeOrder(DeptstuchangeorderEntity deptstuchangeorderEntity);
    /**
     * 更新类记录
     * @param depttreeEntityList
     * @return
     */
    int updateDepttree(List<DepttreeEntity> depttreeEntityList);

    int updateDepttree(DepttreeEntity depttreeEntity);

    /**
     * 修改变更记录集中的一条记录
     * @param mdepChagSetaEntity
     * @return
     */
    int updateMdepChagSet(MdepMdepChagSetaEntity mdepChagSetaEntity);

    /**
     * 部门变更记录集保存接口
     * @param mdepChagSetaEntityList
     * @return
     */
    List<String> addDeptChagSetRecord(List<MdepMdepChagSetaEntity> mdepChagSetaEntityList);

    DeptstuchangeorderEntity findDeptChangeOrderById(String orderId);

    DeptstuchangeorderEntity findDeptChangeOrderByNbr(String orderNbr);

    List<OdscOdscChagSetaEntity> findOdscChagSetByPid(String pid);

    int deleteDepttreeByIds(String[] ids);

    int deleteOdscChagSetByIds(String[] ids);

    /**
     * 根据id列表和生效日期查询部门信息
     * @param idList
     * @param mdepBeg
     * @return
     */
    List<DepttreeEntity> findDeptTreeList(List<String> idList , Date mdepBeg);
}
