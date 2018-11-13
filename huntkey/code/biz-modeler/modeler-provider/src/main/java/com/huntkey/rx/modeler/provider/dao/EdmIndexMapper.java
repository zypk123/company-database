package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmIndex;
import com.huntkey.rx.modeler.common.model.EdmIndexExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdmIndexMapper {
    long countByExample(EdmIndexExample example);

    int deleteByExample(EdmIndexExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmIndex record);

    int insertSelective(EdmIndex record);

    List<EdmIndex> selectByExample(EdmIndexExample example);

    List<EdmIndex> selectByEdmIndex(EdmIndex edmIndex);

    EdmIndex selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmIndex record, @Param("example") EdmIndexExample example);

    int updateByExample(@Param("record") EdmIndex record, @Param("example") EdmIndexExample example);

    int updateByPrimaryKeySelective(EdmIndex record);

    int updateByPrimaryKey(EdmIndex record);

    List<EdmIndex> selectIndexsByEdmcId(String edmcId);

    void insertBatch(List<EdmIndex> edmIndexs);
}