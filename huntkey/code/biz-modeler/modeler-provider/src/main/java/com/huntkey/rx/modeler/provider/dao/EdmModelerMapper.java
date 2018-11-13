package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmModeler;
import com.huntkey.rx.modeler.common.model.EdmModelerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EdmModelerMapper {

    long countByExample(EdmModelerExample example);

    int deleteByExample(EdmModelerExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmModeler record);

    int insertSelective(EdmModeler record);

    List<EdmModeler> selectByExample(EdmModelerExample example);

    EdmModeler selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmModeler record, @Param("example") EdmModelerExample example);

    int updateByExample(@Param("record") EdmModeler record, @Param("example") EdmModelerExample example);

    int updateByPrimaryKeySelective(EdmModeler record);

    int updateByPrimaryKey(EdmModeler record);

    int updateIsDelByPrimaryKey(String id);

    void updateStatusWithPublishedModeler();

    List<String> selectAllVers();

    /**
     * 根据版本获取模型id
     * @param edmdVer
     * @return
     */
    String selectEdmdIdByVer(String edmdVer);


}