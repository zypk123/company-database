package com.huntkey.rx.sceo.formula.provider.behavior.dao;

import com.huntkey.rx.sceo.formula.common.model.TdfUserBehavior;
import com.huntkey.rx.sceo.formula.common.model.TdfUserBehaviorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface TdfUserBehaviorMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    int countByExample(TdfUserBehaviorExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(TdfUserBehaviorExample example);

    /**
     * deleteByPrimaryKey
     * @param usrbId
     * @return
     */
    int deleteByPrimaryKey(String usrbId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(TdfUserBehavior record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(TdfUserBehavior record);

    /**
     * selectByExampleWithBLOBs
     * @param example
     * @return
     */
    List<TdfUserBehavior> selectByExampleWithBLOBs(TdfUserBehaviorExample example);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<TdfUserBehavior> selectByExample(TdfUserBehaviorExample example);

    /**
     * selectByPrimaryKey
     * @param usrbId
     * @return
     */
    TdfUserBehavior selectByPrimaryKey(String usrbId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") TdfUserBehavior record, @Param("example") TdfUserBehaviorExample example);

    /**
     * updateByExampleWithBLOBs
     * @param record
     * @param example
     * @return
     */
    int updateByExampleWithBLOBs(@Param("record") TdfUserBehavior record, @Param("example") TdfUserBehaviorExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") TdfUserBehavior record, @Param("example") TdfUserBehaviorExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TdfUserBehavior record);

    /**
     * updateByPrimaryKeyWithBLOBs
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(TdfUserBehavior record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(TdfUserBehavior record);
}