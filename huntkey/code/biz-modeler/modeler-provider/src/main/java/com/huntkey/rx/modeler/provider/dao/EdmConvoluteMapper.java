package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmConvolute;
import com.huntkey.rx.modeler.common.model.EdmConvoluteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdmConvoluteMapper {
    long countByExample(EdmConvoluteExample example);

    int deleteByExample(EdmConvoluteExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmConvolute record);

    int insertSelective(EdmConvolute record);

    List<EdmConvolute> selectByExample(EdmConvoluteExample example);

    EdmConvolute selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmConvolute record, @Param("example") EdmConvoluteExample example);

    int updateByExample(@Param("record") EdmConvolute record, @Param("example") EdmConvoluteExample example);

    int updateByPrimaryKeySelective(EdmConvolute record);

    int updateByPrimaryKey(EdmConvolute record);

    int LogicDeleteByPrimaryKey(String id);

    //export
    String selectEdmpEdmcIdByEdcoEdmpId(String edcoEdmpId);

    String selectEdmcNameByEdmpEdmcId(String edmpEdmcId);

    String selectEdmpNameByEdcoEdmpId(String edcoEdmpId);

    List<String> selectPropertyIdListByClassId(String classId);

    void insertBatch(List<EdmConvolute> edmConvolutesAll);

    /**
     * 根据卷积属性ID查找属性
     * @param id
     * @return
     */
    EdmConvolute selectEdmConvoluteByPropertyId(String id);

    /**
     * 根据属性ID取属性的扩展属性中的卷积公式
     * @param edmpId
     * @return
     */
    String selectConFormulaByEdcoEdmpId(String edmpId);
}