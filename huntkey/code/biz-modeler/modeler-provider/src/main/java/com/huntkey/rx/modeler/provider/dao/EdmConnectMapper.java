package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmConnect;
import com.huntkey.rx.modeler.common.model.EdmConnectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdmConnectMapper {
    long countByExample(EdmConnectExample example);

    int deleteByExample(EdmConnectExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmConnect record);

    int insertSelective(EdmConnect record);

    List<EdmConnect> selectByExample(EdmConnectExample example);

    EdmConnect selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmConnect record, @Param("example") EdmConnectExample example);

    int updateByExample(@Param("record") EdmConnect record, @Param("example") EdmConnectExample example);

    int updateByPrimaryKeySelective(EdmConnect record);

    int updateByPrimaryKey(EdmConnect record);

    /**
     * 根据属性id 查找联动属性
     * @param propertyId
     * @return
     */
    List<EdmConnect> selectEdmConnectListByPropertyId(String propertyId);

    void insertBatch(List<EdmConnect> edmConnectsAll);

    /**
     * 根据edcn_edmp_id 来查找联动的属性
     * @param edcnEdmpId
     * @return EdmConnect
     */
    EdmConnect getEdmConnectPropertieByEdmpId(String edcnEdmpId);

    int LogicDeleteByPrimaryKey(String id);

    int LogicDeleteByEdmpId(String id);
}