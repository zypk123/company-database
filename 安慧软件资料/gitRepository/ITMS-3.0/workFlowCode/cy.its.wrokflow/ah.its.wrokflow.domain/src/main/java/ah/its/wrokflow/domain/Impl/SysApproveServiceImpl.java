package ah.its.wrokflow.domain.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.domain.SysApproveServiceI;
import ah.its.wrokflow.repository.SysApproveRepositoryI;
import ah.its.wrokflow.repository.dao.ApproveChk;
import ah.its.wrokflow.repository.dao.SysApprove;
@Service
public class SysApproveServiceImpl implements SysApproveServiceI {

	@Autowired
	private SysApproveRepositoryI sysApproveRepositoryImpl;
	@Override
	public int saveSystemApprove(SysApprove record) {
		// TODO Auto-generated method stub
		return sysApproveRepositoryImpl.saveSystemApprove(record);
	}

	@Override
	public int updateSystemApprove(SysApprove record) {
		// TODO Auto-generated method stub
		return sysApproveRepositoryImpl.updateSystemApprove(record);
	}

	@Override
	public List<SysApprove> selectAllData(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return sysApproveRepositoryImpl.selectAllData(map);
	}

	@Override
	public int deleteSystemApprove(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return sysApproveRepositoryImpl.deleteSystemApprove(map);
	}

	@Override
	public SysApprove querySystemApproveById(String approveId) {
		// TODO Auto-generated method stub
		return sysApproveRepositoryImpl.querySystemApproveById(approveId);
	}

	@Override
	public List<SysApprove> querySystemApproveHistory(Map map) {
		// TODO Auto-generated method stub
		return sysApproveRepositoryImpl.querySystemApproveHistory(map);
	}

	@Override
	public List<ApproveChk> querySystemApproveHistoryDetail(String approveId) {
		// TODO Auto-generated method stub
		return sysApproveRepositoryImpl.querySystemApproveHistoryDetail(approveId);
	}

}
