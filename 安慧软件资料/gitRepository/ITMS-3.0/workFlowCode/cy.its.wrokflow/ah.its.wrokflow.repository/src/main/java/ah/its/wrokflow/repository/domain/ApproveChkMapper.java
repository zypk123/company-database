package ah.its.wrokflow.repository.domain;

import java.util.List;

import ah.its.wrokflow.repository.dao.ApproveChk;

public interface ApproveChkMapper {
    int deleteByPrimaryKey(String approvalId);

    int insert(ApproveChk record);

    int insertSelective(ApproveChk record);

    ApproveChk selectByPrimaryKey(String approvalId);

    int updateByPrimaryKeySelective(ApproveChk record);

    int updateByPrimaryKey(ApproveChk record);

	List<ApproveChk> queryDataByApproveId(String approveId);
}