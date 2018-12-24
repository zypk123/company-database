package ah.its.wrokflow.repository.domain;

import java.util.List;
import java.util.Map;

import ah.its.wrokflow.repository.dao.ApproveChk;
import ah.its.wrokflow.repository.dao.SysApprove;

public interface SysApproveMapper {
    int deleteByPrimaryKey(String systemApproveId);

    int insert(SysApprove record);

    int insertSelective(SysApprove record);

    SysApprove selectByPrimaryKey(String systemApproveId);

    int updateByPrimaryKeySelective(SysApprove record);

    int updateByPrimaryKey(SysApprove record);
    
    List<SysApprove> selectAllData(@SuppressWarnings("rawtypes") Map map);
    
    /**
	 * 系统审批单历史查询
	 * @param map
	 * @return
	 */
	List<SysApprove> querySystemApproveHistory(Map map);

	/**
	 * 系统审批历史记录详情
	 * @param approveId
	 * @return
	 */
	List<ApproveChk> querySystemApproveHistoryDetail(String systemApproveId);
}