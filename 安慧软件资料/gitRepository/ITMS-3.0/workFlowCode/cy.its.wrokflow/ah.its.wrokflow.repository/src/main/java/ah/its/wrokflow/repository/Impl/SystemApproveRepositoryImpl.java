package ah.its.wrokflow.repository.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.repository.SysApproveRepositoryI;
import ah.its.wrokflow.repository.dao.ApproveChk;
import ah.its.wrokflow.repository.dao.SysApprove;
import ah.its.wrokflow.repository.domain.SysApproveMapper;
@Service
public class SystemApproveRepositoryImpl implements SysApproveRepositoryI {

	@Autowired
	private SysApproveMapper sysApproveMapper;
	@Override
	public int saveSystemApprove(SysApprove record) {
		// TODO Auto-generated method stub
		return sysApproveMapper.insert(record);
	}

	@Override
	public int updateSystemApprove(SysApprove record) {
		// TODO Auto-generated method stub
		return sysApproveMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SysApprove> selectAllData(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return sysApproveMapper.selectAllData(map);
	}

	@Override
	public int deleteSystemApprove(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SysApprove querySystemApproveById(String approveId) {
		// TODO Auto-generated method stub
		return sysApproveMapper.selectByPrimaryKey(approveId);
	}

	@Override
	public List<SysApprove> querySystemApproveHistory(Map map) {
		// TODO Auto-generated method stub
		return sysApproveMapper.querySystemApproveHistory(map);
	}

	@Override
	public List<ApproveChk> querySystemApproveHistoryDetail(String approveId) {
		// TODO Auto-generated method stub
		return sysApproveMapper.querySystemApproveHistoryDetail(approveId);
	}

}
