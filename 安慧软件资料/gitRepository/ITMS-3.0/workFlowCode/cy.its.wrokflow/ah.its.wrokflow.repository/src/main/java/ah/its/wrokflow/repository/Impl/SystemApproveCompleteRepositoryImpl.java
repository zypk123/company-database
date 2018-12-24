package ah.its.wrokflow.repository.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.repository.SysApproveCompleteRepositoryI;
import ah.its.wrokflow.repository.dao.SysApprove;
import ah.its.wrokflow.repository.dao.SysApproveFile;
import ah.its.wrokflow.repository.domain.SysApproveFileMapper;
@Service
public class SystemApproveCompleteRepositoryImpl implements SysApproveCompleteRepositoryI {
	@Autowired
	private SysApproveFileMapper sysApproveFileMapper;

	@Override
	public int saveSystemApproveComplete(SysApproveFile record) {
		// TODO Auto-generated method stub
		return sysApproveFileMapper.insert(record);
	}

	@Override
	public int updateSystemApproveComplete(SysApproveFile record) {
		// TODO Auto-generated method stub
		return sysApproveFileMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SysApproveFile> selectAllData(Map map) {
		// TODO Auto-generated method stub
		return sysApproveFileMapper.selectAllData(map);
	}

	@Override
	public int deleteSystemApproveComplete(Map map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SysApproveFile querySystemApproveCompleteById(String approveId) {
		// TODO Auto-generated method stub
		return sysApproveFileMapper.selectByPrimaryKey(approveId);
	}

	@Override
	public List<SysApprove> querySystemApproveCompleteHistory(Map map) {
		// TODO Auto-generated method stub
		return sysApproveFileMapper.querySystemApproveHistory(map);
	}
	
}
