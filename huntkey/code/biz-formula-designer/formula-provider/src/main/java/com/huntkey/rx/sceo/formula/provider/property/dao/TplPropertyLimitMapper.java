package com.huntkey.rx.sceo.formula.provider.property.dao;

import com.huntkey.rx.sceo.formula.common.model.TplPropertyLimit;
import com.huntkey.rx.sceo.formula.common.model.TplPropertyLimitExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface TplPropertyLimitMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    int countByExample(TplPropertyLimitExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(TplPropertyLimitExample example);

    /**
     * deleteByPrimaryKey
     * @param prprId
     * @return
     */
    int deleteByPrimaryKey(String prprId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(TplPropertyLimit record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(TplPropertyLimit record);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<TplPropertyLimit> selectByExample(TplPropertyLimitExample example);

    /**
     * selectByPrimaryKey
     * @param prprId
     * @return
     */
    TplPropertyLimit selectByPrimaryKey(String prprId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") TplPropertyLimit record, @Param("example") TplPropertyLimitExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") TplPropertyLimit record, @Param("example") TplPropertyLimitExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TplPropertyLimit record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(TplPropertyLimit record);
}