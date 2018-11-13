package com.huntkey.rx.sceo.formula.provider.function.definefunc.dao;

import com.huntkey.rx.sceo.formula.common.model.TfmFunctionCompiled;
import com.huntkey.rx.sceo.formula.common.model.TfmFunctionCompiledExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface TfmFunctionCompiledMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    int countByExample(TfmFunctionCompiledExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(TfmFunctionCompiledExample example);

    /**
     * deleteByPrimaryKey
     * @param funcId
     * @return
     */
    int deleteByPrimaryKey(String funcId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(TfmFunctionCompiled record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(TfmFunctionCompiled record);

    /**
     * selectByExampleWithBLOBs
     * @param example
     * @return
     */
    List<TfmFunctionCompiled> selectByExampleWithBLOBs(TfmFunctionCompiledExample example);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<TfmFunctionCompiled> selectByExample(TfmFunctionCompiledExample example);

    /**
     * selectByPrimaryKey
     * @param funcId
     * @return
     */
    TfmFunctionCompiled selectByPrimaryKey(String funcId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") TfmFunctionCompiled record, @Param("example") TfmFunctionCompiledExample example);

    /**
     * updateByExampleWithBLOBs
     * @param record
     * @param example
     * @return
     */
    int updateByExampleWithBLOBs(@Param("record") TfmFunctionCompiled record, @Param("example") TfmFunctionCompiledExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") TfmFunctionCompiled record, @Param("example") TfmFunctionCompiledExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TfmFunctionCompiled record);

    /**
     * updateByPrimaryKeyWithBLOBs
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(TfmFunctionCompiled record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(TfmFunctionCompiled record);

    /**
     * 检查修改函数时，函数名是否重复
     *
     * @param fundId
     * @param fundFunName
     * @return
     */
    int checkFuncName(@Param("fundId")String fundId, @Param("fundFunName")String fundFunName);
}