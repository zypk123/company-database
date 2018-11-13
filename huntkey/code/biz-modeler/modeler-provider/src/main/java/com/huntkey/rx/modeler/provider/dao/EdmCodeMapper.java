package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmCode;
import com.huntkey.rx.modeler.common.model.EdmCodeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EdmCodeMapper {

    long countByExample(EdmCodeExample example);

    int deleteByExample(EdmCodeExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmCode record);

    int insertSelective(EdmCode record);

    List<EdmCode> selectByExample(EdmCodeExample example);

    EdmCode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmCode record, @Param("example") EdmCodeExample example);

    int updateByExample(@Param("record") EdmCode record, @Param("example") EdmCodeExample example);

    int updateByPrimaryKeySelective(EdmCode record);

    int updateByPrimaryKey(EdmCode record);

    String selectPropertyValueBycodeValue(String edmpValueType);

    List<EdmCode> selectIndustriesByCode();

    EdmCode selectIndustryBycodeValue(String edmcIndustryCode);

    /**
     * 通过codeValue查找类型ParaType的codeName的值
     * @param codeValue
     * @return
     */
    String selectParaTypeCodeNameByCodeValue(String codeValue);

    String selectAttachMentTypeByCodeValue(Byte edmaType);

    /**
     * 通过codeValue查找模型状态的codeName的值
     * @param codeValue
     * @return
     */
    String selectEdmdStatusNameBycodeValue(int codeValue);

    String selectCodeNameByTypeAndValue(@Param("codeType") String codeType,@Param("codeValue") String codeValue);
}