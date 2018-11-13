package com.huntkey.rx.sceo.formula.provider.engine.dao;

import com.huntkey.rx.sceo.formula.common.model.TfdFormula;
import com.huntkey.rx.sceo.formula.common.model.TfdFormulaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface TfdFormulaMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    int countByExample(TfdFormulaExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(TfdFormulaExample example);

    /**
     * deleteByPrimaryKey
     * @param frmuId
     * @return
     */
    int deleteByPrimaryKey(String frmuId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(TfdFormula record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(TfdFormula record);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<TfdFormula> selectByExample(TfdFormulaExample example);

    /**
     * selectByPrimaryKey
     * @param frmuId
     * @return
     */
    TfdFormula selectByPrimaryKey(String frmuId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") TfdFormula record, @Param("example") TfdFormulaExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") TfdFormula record, @Param("example") TfdFormulaExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TfdFormula record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(TfdFormula record);

    /**
     * 通过公式ID得到公式内容
     *
     * @param frmuId
     * @return
     */
    String getFormulaContentByFormulaId(String frmuId);


}