package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmPropertyGroup;
import com.huntkey.rx.modeler.common.model.EdmPropertyGroupExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EdmPropertyGroupMapper {
    long countByExample(EdmPropertyGroupExample example);

    int deleteByExample(EdmPropertyGroupExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmPropertyGroup record);

    int insertSelective(EdmPropertyGroup record);

    List<EdmPropertyGroup> selectByExample(EdmPropertyGroupExample example);

    EdmPropertyGroup selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmPropertyGroup record, @Param("example") EdmPropertyGroupExample example);

    int updateByExample(@Param("record") EdmPropertyGroup record, @Param("example") EdmPropertyGroupExample example);

    int updateByPrimaryKeySelective(EdmPropertyGroup record);

    int updateByPrimaryKey(EdmPropertyGroup record);

    void insertBatch(List<EdmPropertyGroup> edmPropertyGroups);

    void updateIsDelByGroupId(String groupId);

    void updateBatch(@Param("list") List<EdmPropertyGroup> list);
}