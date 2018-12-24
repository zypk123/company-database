package ah.its.wrokflow.repository.domain;

import java.util.List;

import ah.its.wrokflow.repository.dao.WfMessage;

public interface WfMessageMapper {
    int deleteByPrimaryKey(String messageId);

    int insert(WfMessage record);

    int insertSelective(WfMessage record);

    WfMessage selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(WfMessage record);

    int updateByPrimaryKey(WfMessage record);
    
    List<WfMessage> queryMessagesByRecord(WfMessage message);
}