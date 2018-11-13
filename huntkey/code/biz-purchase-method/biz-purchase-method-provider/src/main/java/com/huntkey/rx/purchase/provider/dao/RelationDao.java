package com.huntkey.rx.purchase.provider.dao;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.edm.entity.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by xuyf on 2018/1/4 0004.
 */
public interface RelationDao {

    /**
     * 分页查询伙伴集合
     *
     * @param relaCode
     * @param relaShortName
     * @param relaUscc
     * @param ids
     * @param currentPage
     * @param pageSize
     * @return
     */
    Pagination<RelationEntity> selectRelationPageList(String relaCode, String relaShortName,
                                                      String relaUscc, String[] ids, int currentPage, int pageSize);

    /**
     * 通过唯一性属性条件查询伙伴集合
     *
     * @param exceptRelaId
     * @param relaUscc
     * @param relaCode
     * @param relaShortName
     * @return
     */
    List<RelationEntity> selectRelationUniqueCheck(String exceptRelaId, String relaUscc, String relaCode, String relaShortName);

    /**
     * 根据伙伴id查询伙伴对象
     *
     * @param id
     * @return
     */
    RelationEntity selectRelationentityById(String id);

    /**
     * 根据关键词模糊查询（暂支持code和shortname）
     * @param keyword
     * @return
     */
    List<RelationEntity> selectRelationByKeyword(String keyword);

    /**
     * 条件查询伙伴集合
     * @param relaId
     * @param relaUscc
     * @param relaCode
     * @param relaShortName
     * @return
     */
    List<RelationEntity> selectRelationList(String relaId, String relaUscc, String relaCode, String relaShortName);

    /**
     * 查询伙伴类型集
     *
     * @param relationId
     * @param relaTypes
     * @return
     */
    List<RelaRelaTypesSetaEntity> selectRelaTypesSet(String relationId, String[] relaTypes);

    /**
     * 根据伙伴类型id查询伙伴类型对象
     *
     * @param id
     * @return
     */
    RelaRelaTypesSetaEntity selectRelaTypeEntityById(String id);

    /**
     * 根据伙伴状态和伙伴类型数组（为空查询全部伙伴集），查询伙伴id集合
     *
     * @param relaStatus
     * @param relaTypes
     * @return
     */
    List<String> selectRelaIdByRelaStatus(String relaStatus, String[] relaTypes);

    /**
     * 查询客户数据集
     *
     * @param relationId
     * @param relaStatCusts
     * @param relaServdeptCust
     * @param relaCredlimit
     * @param relaSettWayc
     * @return
     */
    List<RelaRelaCustSetaEntity> selectRelaCustSet(String relationId, String[] relaStatCusts, String relaServdeptCust,
                                                   Integer relaCredlimit, String relaSettWayc);

    /**
     * 根据id查询客户类型信息
     *
     * @param id
     * @return
     */
    RelaRelaCustSetaEntity selectRelaCustById(String id);

    /**
     * 查询供应商数据集
     *
     * @param relationId
     * @param relaSupptype
     * @param relaStatSupps
     * @param relaServdeptSupp
     * @param relaTaxrates
     * @param relaSettWays
     * @return
     */
    List<RelaRelaSupplierSetaEntity> selectRelaSupplierSet(String relationId, String relaSupptype, String[] relaStatSupps, String relaServdeptSupp,
                                                           String[] relaTaxrates, String relaSettWays);

    /**
     * 根据id查询供应商类型信息
     *
     * @param id
     * @return
     */
    RelaRelaSupplierSetaEntity selectRelaSupplierById(String id);

    /**
     * 查询股东数据集
     *
     * @param relationId
     * @param relaHname
     * @param relaHrate
     * @return
     */
    List<RelaRelaHolderSetaEntity> selectRelaHolderSet(String relationId, String relaHname, BigDecimal relaHrate);

    /**
     * 根据id查询股东类型信息
     *
     * @param id
     * @return
     */
    RelaRelaHolderSetaEntity selectRelaHolderById(String id);

    /**
     * 查询体系认证集
     *
     * @param relationId
     * @param relaSname
     * @return
     */
    List<RelaRelaSystemSetaEntity> selectRelaSystemSet(String relationId, String relaSname);


    /**
     * 根据id查询体系认证信息
     *
     * @param id
     * @return
     */
    RelaRelaSystemSetaEntity selectRelaSystemById(String id);


    /**
     * 查询客户联系人集合
     *
     * @param pid
     * @param relaConttypeCust
     * @param relaContnameCust
     * @param relaContsexCust
     * @param relaConttelCust
     * @return
     */
    List<RelaRelaContactCustSetbEntity> selectRelaContactCustSet(String pid, String relaConttypeCust, String relaContnameCust,
                                                                 String relaContsexCust, String relaConttelCust);

    /**
     * 查询客户附件资料
     *
     * @param pid
     * @param relaAttatypeCust
     * @param relaAttavalidCust
     * @return
     */
    List<RelaRelaAttachCustSetbEntity> selectRelaAttachCustSet(String pid, String relaAttatypeCust, Date relaAttavalidCust);

    /**
     * 查询客户服务团队集合
     *
     * @param pid
     * @param relaSttypeCusts
     * @param relaStempCust
     * @return
     */
    List<RelaRelaServtCustSetbEntity> selectRelaServtCustSet(String pid, String[] relaSttypeCusts, String relaStempCust);

    /**
     * 查询客户账户管理集合
     *
     * @param pid
     * @param relaAcconameCust
     * @param relaAccobankCust
     * @param relaAcconumCust
     * @param relaAccocurrCust
     * @param accoDate
     * @return
     */
    List<RelaRelaAccountCustSetbEntity> selectRelaAccountCustSet(String pid, String relaAcconameCust,
                                                                 String relaAccobankCust, String relaAcconumCust,
                                                                 String relaAccocurrCust, Date accoDate);

    /**
     * 查询客户交货管理集合
     *
     * @param pid
     * @param relaDanameCust
     * @param relaDaddrpCust
     * @param relaDaddrcCust
     * @param relaDaddrlCust
     * @return
     */
    List<RelaRelaDeliCustSetbEntity> selectRelaDeliCustSet(String pid, String relaDanameCust, String relaDaddrpCust,
                                                           String relaDaddrcCust, String relaDaddrlCust);

    /**
     * 查询供应商联系人集合
     *
     * @param pid
     * @param relaConttypeSupp
     * @param relaContnameSupp
     * @param relaContsexSupp
     * @param relaConttelSupp
     * @return
     */
    List<RelaRelaContactSuppSetbEntity> selectRelaContactSuppSet(String pid, String relaConttypeSupp,
                                                                 String relaContnameSupp, String relaContsexSupp,
                                                                 String relaConttelSupp);

    /**
     * 查询供应商附件资料集合
     *
     * @param pid
     * @param relaAttatypeSupp
     * @param relaAttavalidSupp
     * @return
     */
    List<RelaRelaAttachSuppSetbEntity> selectRelaAttachSuppSet(String pid, String relaAttatypeSupp, Date relaAttavalidSupp);

    /**
     * 查询供应商服务团队集合
     *
     * @param pid
     * @param relaSttypeSupps
     * @param relaStempSupp
     * @return
     */
    List<RelaRelaServtSuppSetbEntity> selectRelaServtSuppSet(String pid, String[] relaSttypeSupps, String relaStempSupp);

    /**
     * 查询供应商账户管理集合
     *
     * @param pid
     * @param relaAcconameSupp
     * @param relaAccobankSupp
     * @param relaAcconumSupp
     * @param relaAccocurrSupp
     * @param accoDate
     * @return
     */
    List<RelaRelaAccountSuppSetbEntity> selectRelaAccountSuppSet(String pid, String relaAcconameSupp,
                                                                 String relaAccobankSupp, String relaAcconumSupp,
                                                                 String relaAccocurrSupp, Date accoDate);

    /**
     * 查询供应商交货管理集合
     *
     * @param pid
     * @param relaDanameSupp
     * @param relaDaddrpSupp
     * @param relaDaddrcSupp
     * @param relaDaddrlSupp
     * @return
     */
    List<RelaRelaDeliSuppSetbEntity> selectRelaDeliSuppSet(String pid, String relaDanameSupp, String relaDaddrpSupp,
                                                           String relaDaddrcSupp, String relaDaddrlSupp);


    /**
     * 新增伙伴信息
     * @param relationEntity
     * @return
     */
    String insertRelationEntity(RelationEntity relationEntity);

    /**
     * 修改伙伴信息
     * @param relationEntity
     * @return
     */
    int updateRelationEntity(RelationEntity relationEntity);

    int deleteRelationEntity(String id);

    int deleteRelaSystemSetaEntityByPid(String relationId);

    int deleteRelaHolderSetaEntityByPid(String relationId);

    /**
     * 根据部门id查询部门对象
     *
     * @param id
     * @return
     */
    DepttreeEntity selectDepttreeEntityById(String id);

    /**
     * 根据员工id查询员工对象
     *
     * @param id
     * @return
     */
    EmployeeEntity selectEmployeeEntityById(String id);

    /**
     * 根据岗位id查询岗位对象
     *
     * @param id
     * @return
     */
    JobpositionEntity selectJobpositionEntityById(String id);

}
