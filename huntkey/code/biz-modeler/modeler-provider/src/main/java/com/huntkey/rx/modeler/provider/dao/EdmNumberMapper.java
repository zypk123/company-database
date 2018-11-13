package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmNumber;
import com.huntkey.rx.modeler.common.model.EdmNumberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface EdmNumberMapper {
    long countByExample(EdmNumberExample example);

    int deleteByExample(EdmNumberExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmNumber record);

    int insertSelective(EdmNumber record);

    List<EdmNumber> selectByExample(EdmNumberExample example);

    EdmNumber selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmNumber record, @Param("example") EdmNumberExample example);

    int updateByExample(@Param("record") EdmNumber record, @Param("example") EdmNumberExample example);

    int updateByPrimaryKeySelective(EdmNumber record);

    int updateByPrimaryKey(EdmNumber record);

    EdmNumber selectedmNumberByTypeEncod(@Param("edmnType") Integer edmnType,@Param("edmnEncode") String edmnEncode);
}