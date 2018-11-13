package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmConvoluteExtend;
import com.huntkey.rx.modeler.common.model.EdmConvoluteExtendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdmConvoluteExtendMapper {
    long countByExample(EdmConvoluteExtendExample example);

    int deleteByExample(EdmConvoluteExtendExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmConvoluteExtend record);

    int insertSelective(EdmConvoluteExtend record);

    List<EdmConvoluteExtend> selectByExample(EdmConvoluteExtendExample example);

    EdmConvoluteExtend selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmConvoluteExtend record, @Param("example") EdmConvoluteExtendExample example);

    int updateByExample(@Param("record") EdmConvoluteExtend record, @Param("example") EdmConvoluteExtendExample example);

    int updateByPrimaryKeySelective(EdmConvoluteExtend record);

    int updateByPrimaryKey(EdmConvoluteExtend record);

    int deleteByEdceEdmpId(String edceEdmpId);

}