package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmIndexDetail;
import com.huntkey.rx.modeler.common.model.EdmIndexDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdmIndexDetailMapper {
    long countByExample(EdmIndexDetailExample example);

    int deleteByExample(EdmIndexDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmIndexDetail record);

    int insertSelective(EdmIndexDetail record);
    int insertList(List<EdmIndexDetail> recordList);
    int deleteByIndexId(String indexId);

    List<EdmIndexDetail> selectByExample(EdmIndexDetailExample example);

    EdmIndexDetail selectByPrimaryKey(String id);

    List<EdmIndexDetail> selectByIndexId(String indexId);


    int updateByExampleSelective(@Param("record") EdmIndexDetail record, @Param("example") EdmIndexDetailExample example);

    int updateByExample(@Param("record") EdmIndexDetail record, @Param("example") EdmIndexDetailExample example);

    int updateByPrimaryKeySelective(EdmIndexDetail record);

    int updateByPrimaryKey(EdmIndexDetail record);
}