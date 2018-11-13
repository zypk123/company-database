package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmAttachment;
import com.huntkey.rx.modeler.common.model.EdmAttachmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EdmAttachmentMapper {

    long countByExample(EdmAttachmentExample example);

    int deleteByExample(EdmAttachmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmAttachment record);

    int insertSelective(EdmAttachment record);

    List<EdmAttachment> selectByExample(EdmAttachmentExample example);

    EdmAttachment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmAttachment record, @Param("example") EdmAttachmentExample example);

    int updateByExample(@Param("record") EdmAttachment record, @Param("example") EdmAttachmentExample example);

    int updateByPrimaryKeySelective(EdmAttachment record);

    int updateByPrimaryKey(EdmAttachment record);

    /**
     * 根据类id获取所有的附件
     * @param id
     * @return
     */
    List<EdmAttachment> getListByCid(String id);

    int updateIsDelByPrimaryKey(String id);

    List<EdmAttachment> selectEdmAttachmentListByClassId(String classId);

    void updateSeqById(@Param("id")String id, @Param("seq")Integer seq1);

    void insertBatch(List<EdmAttachment> edmAttachments);

    Integer getMaxSeqByEdmcId(String edmcId);

    String getAttachmentNameById(String id);
}