package com.huntkey.rx.sceo.formula.provider.variant.dao;

import com.huntkey.rx.sceo.formula.common.model.TvmVariant;
import com.huntkey.rx.sceo.formula.common.model.TvmVariantExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface TvmVariantMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    int countByExample(TvmVariantExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(TvmVariantExample example);

    /**
     * deleteByExample
     * @param vrntId
     * @return
     */
    int deleteByPrimaryKey(String vrntId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(TvmVariant record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(TvmVariant record);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<TvmVariant> selectByExample(TvmVariantExample example);

    /**
     * selectByPrimaryKey
     * @param vrntId
     * @return
     */
    TvmVariant selectByPrimaryKey(String vrntId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") TvmVariant record, @Param("example") TvmVariantExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") TvmVariant record, @Param("example") TvmVariantExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TvmVariant record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(TvmVariant record);

    /**
     * 通过公式ID得到变量ID集合，出去自身
     *
     * @param formulaId
     * @param vrntId
     * @return
     */
    List<String> getVrntIdByFormualIdExceptSelf(@Param("formulaId") String formulaId, @Param("vrntId") String vrntId);

    /**
     * 通过变量ID得到变量名
     *
     * @param vrntId
     * @return
     */
    String getVarNameByVrntId(String vrntId);

    /**
     * 通过公式ID得到变量ID集合
     *
     * @param formulaId
     * @return
     */
    List<String> getVrntIdByFormualId(String formulaId);

    /**
     * 通过变量ID查询该变量所在的公式ID
     *
     * @param vrntId
     * @return
     */
    String getFormualIdByVrntId(String vrntId);

    /**
     * 通过公式ID的到它所有的局部变量的ID集合，除去自身
     *
     * @param formulaId
     * @param vrntId
     * @return
     */
    List<String> getLocalVarIdByFormualIdExceptSelf(@Param("formulaId") String formulaId, @Param("vrntId") String vrntId);

}