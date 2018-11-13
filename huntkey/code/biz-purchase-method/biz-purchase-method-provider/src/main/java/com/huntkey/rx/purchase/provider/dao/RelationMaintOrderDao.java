package com.huntkey.rx.purchase.provider.dao;

import com.huntkey.rx.edm.entity.RelationEntity;
import com.huntkey.rx.edm.entity.RelationmaintorderEntity;
import com.huntkey.rx.edm.entity.RemoRemoHolderSetaEntity;
import com.huntkey.rx.edm.entity.RemoRemoSystemSetaEntity;

import java.util.List;

/**
 * @author by xuyf on 2018/1/11 0011.
 */
public interface RelationMaintOrderDao {

    /**
     * 通过唯一性属性条件查询伙伴维护单集合
     *
     * @param exceptRemoId
     * @param remoUscc
     * @param remoCode
     * @param remoShortName
     * @return
     */
    List<RelationmaintorderEntity> selectRelationMaintOrderUniqueCheck(String exceptRemoId, String remoUscc, String remoCode, String remoShortName);

    /**
     * 根据伙伴id查询伙伴维护单
     *
     * @param orderId
     * @return
     */
    RelationmaintorderEntity selectRelationMaintOrderEntityById(String orderId);

    /**
     * 根据伙伴维护单id查询体系认证集
     *
     * @param orderId
     * @return
     */
    List<RemoRemoSystemSetaEntity> selectRemoSystemSetByPid(String orderId);

    /**
     * 根据伙伴维护单id查询股东数据集
     *
     * @param orderId
     * @return
     */
    List<RemoRemoHolderSetaEntity> selectRemoHolderSetByPid(String orderId);

    /**
     * 新增伙伴维护单
     *
     * @param relationmaintorderEntity
     * @return
     */
    String insertRelationMaintOrder(RelationmaintorderEntity relationmaintorderEntity);

    /**
     * 更新伙伴维护单
     *
     * @param relationmaintorderEntity
     * @return
     */
    int updateRelationMaintOrder(RelationmaintorderEntity relationmaintorderEntity, boolean isSelective);

    /**
     * 删除伙伴维护单体系认证集
     * @param pid
     * @return
     */
    int deleteRemoSystemSetByPid(String pid);

    /**
     * 删除伙伴维护单股东集
     * @param pid
     * @return
     */
    int deleteRemoHolderSetByPid(String pid);

    /**
     * 删除伙伴维护单
     *
     * @param id
     * @return
     */
    int deleteRelationMaintOrder(String id);

}
