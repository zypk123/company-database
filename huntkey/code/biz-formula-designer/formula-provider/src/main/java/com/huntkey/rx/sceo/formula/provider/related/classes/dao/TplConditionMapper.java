package com.huntkey.rx.sceo.formula.provider.related.classes.dao;

import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.TplConditionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface TplConditionMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    int countByExample(TplConditionExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(TplConditionExample example);

    /**
     * deleteByPrimaryKey
     * @param cndtId
     * @return
     */
    int deleteByPrimaryKey(String cndtId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(TplCondition record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(TplCondition record);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<TplCondition> selectByExample(TplConditionExample example);

    /**
     * selectByPrimaryKey
     * @param cndtId
     * @return
     */
    TplCondition selectByPrimaryKey(String cndtId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") TplCondition record, @Param("example") TplConditionExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") TplCondition record, @Param("example") TplConditionExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TplCondition record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(TplCondition record);

    /**
     * 通过条件id删除条件(逻辑删除)
     *
     * @param cndtId
     * @return
     */
    int updateIsenableByPrimaryKey(String cndtId);

    /**
     * 根据属性或者相关类id查找条件列表
     *
     * @param clssId 公式相关类id
     * @return
     */
    List<TplCondition> selectByClssId(String clssId);

    /**
     * 通过属性或者相关类ID删除条件
     *
     * @param clssId
     * @return
     */
    int updateIsenableByPropClssId(String clssId);


}