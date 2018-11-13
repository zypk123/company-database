package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmUnit;
import com.huntkey.rx.modeler.common.model.EdmUnitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdmUnitMapper {
    long countByExample(EdmUnitExample example);

    int deleteByExample(EdmUnitExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmUnit record);

    int insertSelective(EdmUnit record);

    List<EdmUnit> selectByExample(EdmUnitExample example);

    EdmUnit selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmUnit record, @Param("example") EdmUnitExample example);

    int updateByExample(@Param("record") EdmUnit record, @Param("example") EdmUnitExample example);

    int updateByPrimaryKeySelective(EdmUnit record);

    int updateByPrimaryKey(EdmUnit record);

    int LogicDeleteByPrimaryKey(String id);

    /**
     * 根据属性id 查找单位列表
     * @param propertyId
     * @return
     */
    List<EdmUnit> selectEdmUnitListByPropertyId(String propertyId);

    /**
     * 批量插入
     * @param edmUnitsAll
     */
    void insertBatch(List<EdmUnit> edmUnitsAll);

}