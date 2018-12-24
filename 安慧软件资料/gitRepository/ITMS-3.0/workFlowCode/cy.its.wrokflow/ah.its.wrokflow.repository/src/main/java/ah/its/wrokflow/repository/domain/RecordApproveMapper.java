package ah.its.wrokflow.repository.domain;

import ah.its.wrokflow.repository.dao.RecordApprove;

public interface RecordApproveMapper {
    int deleteByPrimaryKey(String recordApproveId);

    int insert(RecordApprove record);

    int insertSelective(RecordApprove record);

    RecordApprove selectByPrimaryKey(String recordApproveId);

    int updateByPrimaryKeySelective(RecordApprove record);

    int updateByPrimaryKey(RecordApprove record);
}