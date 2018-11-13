package com.huntkey.rx.sceo.formula.provider.engine.dao;

import com.huntkey.rx.sceo.formula.common.model.TfmFunctionDefinition;
import com.huntkey.rx.sceo.formula.common.model.TfmFunctionDefinitionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface TfmFunctionDefinitionMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    int countByExample(TfmFunctionDefinitionExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(TfmFunctionDefinitionExample example);

    /**
     * deleteByPrimaryKey
     * @param fundId
     * @return
     */
    int deleteByPrimaryKey(String fundId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(TfmFunctionDefinition record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(TfmFunctionDefinition record);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<TfmFunctionDefinition> selectByExample(TfmFunctionDefinitionExample example);

    /**
     * selectByPrimaryKey
     * @param fundId
     * @return
     */
    TfmFunctionDefinition selectByPrimaryKey(String fundId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") TfmFunctionDefinition record, @Param("example") TfmFunctionDefinitionExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") TfmFunctionDefinition record, @Param("example") TfmFunctionDefinitionExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TfmFunctionDefinition record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(TfmFunctionDefinition record);

    /**
     * 删除函数（逻辑删除）
     *
     * @param fundId
     * @return
     */
    int updateIsDelByPrimaryKey(String fundId);

    /**
     * 通过函数分类ID查找函数定义
     *
     * @param fundFunCatagoryId
     * @return
     */
    List<TfmFunctionDefinition> selectByFundFunCatagoryId(String fundFunCatagoryId);


}