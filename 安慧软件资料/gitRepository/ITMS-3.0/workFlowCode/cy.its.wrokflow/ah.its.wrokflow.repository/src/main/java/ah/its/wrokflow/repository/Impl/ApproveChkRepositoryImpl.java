package ah.its.wrokflow.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.repository.ApproveChkRepositoryI;
import ah.its.wrokflow.repository.dao.ApproveChk;
import ah.its.wrokflow.repository.domain.ApproveChkMapper;

/**
* @Title: ApproveChkRepositoryImpl.java 
* @Package ah.its.wrokflow.repository.Impl 
* @Description: 审批单
* @author lil@cychina.cn
* @date 2016年4月26日 下午6:12:13 
* @version V1.0   
 */
@Service
public class ApproveChkRepositoryImpl implements ApproveChkRepositoryI{
	
	@Autowired
	private ApproveChkMapper approveChkMapper;//用户操作接口
	
	@Override
	public int saveApproveChkData(ApproveChk record) {
		// TODO Auto-generated method stub
		return approveChkMapper.insert(record);
	}

	@Override
	public List<ApproveChk> queryApproveChkData(String approveId) {
		// TODO Auto-generated method stub
		return approveChkMapper.queryDataByApproveId(approveId);
	}
	
}
