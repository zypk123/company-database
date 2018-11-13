package com.huntkey.rx.sceo.formula.provider.variant.dao;

import com.huntkey.rx.sceo.formula.common.model.TvmLocaleVariantMapping;
import com.huntkey.rx.sceo.formula.common.model.TvmLocaleVariantMappingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface TvmLocaleVariantMappingMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    int countByExample(TvmLocaleVariantMappingExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(TvmLocaleVariantMappingExample example);

    /**
     * deleteByPrimaryKey
     * @param lclvId
     * @return
     */
    int deleteByPrimaryKey(String lclvId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(TvmLocaleVariantMapping record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(TvmLocaleVariantMapping record);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<TvmLocaleVariantMapping> selectByExample(TvmLocaleVariantMappingExample example);

    /**
     * selectByPrimaryKey
     * @param lclvId
     * @return
     */
    TvmLocaleVariantMapping selectByPrimaryKey(String lclvId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") TvmLocaleVariantMapping record, @Param("example") TvmLocaleVariantMappingExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") TvmLocaleVariantMapping record, @Param("example") TvmLocaleVariantMappingExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TvmLocaleVariantMapping record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(TvmLocaleVariantMapping record);

    /**
     * 通过局部变量ID查询它所在的公式ID
     *
     * @param lclvId
     * @return
     */
    String getFormulaIdByLclvId(String lclvId);

    /**
     * 通过变量ID得到局部变量ID
     *
     * @param varId
     * @return
     */
    String getLclvIdByVarId(String varId);

    /**
     * 通过公式ID查找局部变量映射ID
     *
     * @param formulaId
     * @return
     */
    List<String> getlclvVarIdByFormulaId(String formulaId);

}