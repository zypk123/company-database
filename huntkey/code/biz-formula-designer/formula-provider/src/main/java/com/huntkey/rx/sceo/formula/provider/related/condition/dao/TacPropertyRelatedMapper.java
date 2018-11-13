package com.huntkey.rx.sceo.formula.provider.related.condition.dao;

import com.huntkey.rx.sceo.formula.common.model.TacPropertyRelated;
import com.huntkey.rx.sceo.formula.common.model.TacPropertyRelatedExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface TacPropertyRelatedMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    int countByExample(TacPropertyRelatedExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(TacPropertyRelatedExample example);

    /**
     * deleteByPrimaryKey
     * @param prplId
     * @return
     */
    int deleteByPrimaryKey(String prplId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(TacPropertyRelated record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(TacPropertyRelated record);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<TacPropertyRelated> selectByExample(TacPropertyRelatedExample example);

    /**
     * selectByPrimaryKey
     * @param prplId
     * @return
     */
    TacPropertyRelated selectByPrimaryKey(String prplId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") TacPropertyRelated record, @Param("example") TacPropertyRelatedExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") TacPropertyRelated record, @Param("example") TacPropertyRelatedExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TacPropertyRelated record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(TacPropertyRelated record);

    /**
     * 根据关联属性ID查找公式
     *
     * @param prplId
     * @return
     */
    String getPrplConditionDescByPrplId(String prplId);

    /**
     * 删除关联条件或者触发条件
     *
     * @param prplId
     * @return
     */
    int delRelCondOrRelTrigger(String prplId);


}