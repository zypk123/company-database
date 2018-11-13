package com.huntkey.rx.sceo.formula.provider.variant.dao;

import com.huntkey.rx.sceo.formula.common.model.TvmSystemVariantMapping;
import com.huntkey.rx.sceo.formula.common.model.TvmSystemVariantMappingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface TvmSystemVariantMappingMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    int countByExample(TvmSystemVariantMappingExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(TvmSystemVariantMappingExample example);

    /**
     * deleteByPrimaryKey
     * @param systId
     * @return
     */
    int deleteByPrimaryKey(String systId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(TvmSystemVariantMapping record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(TvmSystemVariantMapping record);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<TvmSystemVariantMapping> selectByExample(TvmSystemVariantMappingExample example);

    /**
     * selectByPrimaryKey
     * @param systId
     * @return
     */
    TvmSystemVariantMapping selectByPrimaryKey(String systId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") TvmSystemVariantMapping record, @Param("example") TvmSystemVariantMappingExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") TvmSystemVariantMapping record, @Param("example") TvmSystemVariantMappingExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TvmSystemVariantMapping record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(TvmSystemVariantMapping record);
}