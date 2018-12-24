package ah.its.wrokflow.domain.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.domain.SysApproveCompleteServiceI;
import ah.its.wrokflow.repository.SysApproveCompleteRepositoryI;
import ah.its.wrokflow.repository.dao.SysApprove;
import ah.its.wrokflow.repository.dao.SysApproveFile;
@Service
public class SysApproveCompleteServiceImpl implements SysApproveCompleteServiceI {

	@Autowired
	private SysApproveCompleteRepositoryI sysApproveCompleteRepositoryImpl;
	@Override
	public int saveSystemApproveComplete(SysApproveFile record) {
		// TODO Auto-generated method stub
		return sysApproveCompleteRepositoryImpl.saveSystemApproveComplete(record);
	}

	@Override
	public int updateSystemApproveComplete(SysApproveFile record) {
		// TODO Auto-generated method stub
		return sysApproveCompleteRepositoryImpl.updateSystemApproveComplete(record);
	}

	@Override
	public List<SysApproveFile> selectAllData(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return sysApproveCompleteRepositoryImpl.selectAllData(map);
	}

	@Override
	public int deleteSystemApproveComplete(@SuppressWarnings("rawtypes") Map map) {
		// TODO Auto-generated method stub
		return sysApproveCompleteRepositoryImpl.deleteSystemApproveComplete(map);
	}

	@Override
	public SysApproveFile querySystemApproveCompleteById(String approveId) {
		// TODO Auto-generated method stub
		return sysApproveCompleteRepositoryImpl.querySystemApproveCompleteById(approveId);
	}

	@Override
	public List<SysApprove> querySystemApproveCompleteHistory(Map map) {
		// TODO Auto-generated method stub
		return sysApproveCompleteRepositoryImpl.querySystemApproveCompleteHistory(map);
	}
}
