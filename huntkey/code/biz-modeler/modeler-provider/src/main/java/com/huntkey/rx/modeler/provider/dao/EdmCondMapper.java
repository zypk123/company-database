package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmCond;
import com.huntkey.rx.modeler.common.model.EdmCondExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdmCondMapper {
    long countByExample(EdmCondExample example);

    int deleteByExample(EdmCondExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmCond record);

    int insertSelective(EdmCond record);

    List<EdmCond> selectByExample(EdmCondExample example);

    EdmCond selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmCond record, @Param("example") EdmCondExample example);

    int updateByExample(@Param("record") EdmCond record, @Param("example") EdmCondExample example);

    int updateByPrimaryKeySelective(EdmCond record);

    int updateByPrimaryKey(EdmCond record);

    List<EdmCond> getCondsByPropertyId(String edmpId);

    void insertBatch(List<EdmCond> edmCondAll);

    void logicDeleteById(String id);

    List<EdmCond> selectByEdmpIds(List<String> edmpIdList);

    int deleteByEdcoCondId(String condId);

    void clearCond(String id);
}