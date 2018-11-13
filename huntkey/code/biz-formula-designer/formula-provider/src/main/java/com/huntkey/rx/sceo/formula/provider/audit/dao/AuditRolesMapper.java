package com.huntkey.rx.sceo.formula.provider.audit.dao;


import com.huntkey.rx.sceo.formula.common.model.AuditRoles;
import com.huntkey.rx.sceo.formula.common.model.AuditRolesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/8/12
 */
public interface AuditRolesMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    long countByExample(AuditRolesExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(AuditRolesExample example);

    /**
     * deleteByPrimaryKey
     * @param auditId
     * @return
     */
    int deleteByPrimaryKey(String auditId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(AuditRoles record);

    /**
     * 根据审核角色对象信息
     * 单条记录插入
     * @param record
     * @return
     */
    int insertSelective(AuditRoles record);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<AuditRoles> selectByExample(AuditRolesExample example);
    /**
     * 根据流程单据id
     *
     * @param  classId
     * @return 结果集
     */
    List<AuditRoles>selectByClassId(String classId);

    /**
     * selectByPrimaryKey
     * @param auditId
     * @return
     */
    AuditRoles selectByPrimaryKey(String auditId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") AuditRoles record, @Param("example") AuditRolesExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") AuditRoles record, @Param("example") AuditRolesExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AuditRoles record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(AuditRoles record);

    /**
     * 根据序号进行逻辑删除记录
     *
     * @param formula
     * @return
     */
    int updateIsEnaleByAuditId(String formula);

    /**
     * 批量插入审核角色信息
     *
     * @param records
     * @return
     */
    int insertBatch(List<AuditRoles> records);

}