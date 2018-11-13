package com.huntkey.rx.sceo.formula.provider.related.condition.dao;

import com.huntkey.rx.sceo.formula.common.model.TacConditionRelated;
import com.huntkey.rx.sceo.formula.common.model.TacConditionRelatedExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface TacConditionRelatedMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    int countByExample(TacConditionRelatedExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(TacConditionRelatedExample example);

    /**
     * deleteByPrimaryKey
     * @param cndrId
     * @return
     */
    int deleteByPrimaryKey(String cndrId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(TacConditionRelated record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(TacConditionRelated record);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<TacConditionRelated> selectByExample(TacConditionRelatedExample example);

    /**
     * selectByPrimaryKey
     * @param cndrId
     * @return
     */
    TacConditionRelated selectByPrimaryKey(String cndrId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") TacConditionRelated record, @Param("example") TacConditionRelatedExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") TacConditionRelated record, @Param("example") TacConditionRelatedExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TacConditionRelated record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(TacConditionRelated record);

    /**
     * 删除关联条件的条件接口(逻辑删除)
     *
     * @param cndrId 条件id
     * @return
     */
    int updateIsEnaleByPrimaryKey(String cndrId);

    /**
     * 通过属性关联ID查找条件列表
     *
     * @param prplId
     * @return
     */
    List<TacConditionRelated> getConditionRelatedListByPrplId(String prplId);

    /**
     * 根据关联条件或者触发条件ID删除条件
     *
     * @param prplId
     * @return
     */
    int updateIsEnaleByPropRelatedId(String prplId);

}