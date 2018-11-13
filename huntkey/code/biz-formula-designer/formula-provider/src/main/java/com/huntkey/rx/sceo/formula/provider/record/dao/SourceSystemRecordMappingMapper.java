package com.huntkey.rx.sceo.formula.provider.record.dao;

import com.huntkey.rx.sceo.formula.common.model.SourceSystemRecordMapping;
import com.huntkey.rx.sceo.formula.common.model.SourceSystemRecordMappingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface SourceSystemRecordMappingMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    int countByExample(SourceSystemRecordMappingExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(SourceSystemRecordMappingExample example);

    /**
     * deleteByPrimaryKey
     * @param recdId
     * @return
     */
    int deleteByPrimaryKey(String recdId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(SourceSystemRecordMapping record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(SourceSystemRecordMapping record);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<SourceSystemRecordMapping> selectByExample(SourceSystemRecordMappingExample example);

    /**
     * selectByPrimaryKey
     * @param recdId
     * @return
     */
    SourceSystemRecordMapping selectByPrimaryKey(String recdId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") SourceSystemRecordMapping record, @Param("example") SourceSystemRecordMappingExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") SourceSystemRecordMapping record, @Param("example") SourceSystemRecordMappingExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SourceSystemRecordMapping record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(SourceSystemRecordMapping record);

    /**
     * 根据内部编号删除记录
     *
     * @param insideId
     * @return
     */
    int delRecordMapping(String insideId);
}