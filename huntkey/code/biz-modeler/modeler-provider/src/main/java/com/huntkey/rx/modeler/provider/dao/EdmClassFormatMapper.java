package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmClassFormat;
import com.huntkey.rx.modeler.common.model.EdmClassFormatExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EdmClassFormatMapper {
    long countByExample(EdmClassFormatExample example);

    int deleteByExample(EdmClassFormatExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmClassFormat record);

    int insertSelective(EdmClassFormat record);

    List<EdmClassFormat> selectByExample(EdmClassFormatExample example);

    EdmClassFormat selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmClassFormat record, @Param("example") EdmClassFormatExample example);

    int updateByExample(@Param("record") EdmClassFormat record, @Param("example") EdmClassFormatExample example);

    int updateByPrimaryKeySelective(EdmClassFormat record);

    int updateByPrimaryKey(EdmClassFormat record);

    /**
     * 批量新增对象呈现格式
     *
     * @param edmClassFormatList
     * @return
     */
    int insertBatch(List<EdmClassFormat> edmClassFormatList);

    /**
     * 根据id修改是否删除字段
     *
     * @param id
     * @return
     */
    int updateIsDelByPrimaryKey(String id);

    /**
     * 根据模型版本和模型更新说明查询模型
     * @param edmcId
     * @return
     */
    List<EdmClassFormat> selectClassFormatListByEdmcId(String edmcId);
}