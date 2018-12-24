package ah.its.wrokflow.domain.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.domain.ApproveChkServiceI;
import ah.its.wrokflow.repository.ApproveChkRepositoryI;
import ah.its.wrokflow.repository.dao.ApproveChk;

/**
* @Title: ApproveChkServiceImpl.java 
* @Package ah.its.wrokflow.domain.Impl 
* @Description: 审批单操作
* @author lil@cychina.cn
* @date 2016年4月26日 下午6:28:39 
* @version V1.0   
 */
@Service 
public class ApproveChkServiceImpl implements ApproveChkServiceI{

	@Autowired
	private ApproveChkRepositoryI approveChkRepositoryImpl;//用户操作接口
	@Override
	public int saveApproveChkData(ApproveChk record) {
		// TODO Auto-generated method stub
		return approveChkRepositoryImpl.saveApproveChkData(record);
	}

	@Override
	public List<ApproveChk> queryApproveChkData(String approveId) {
		// TODO Auto-generated method stub
		return approveChkRepositoryImpl.queryApproveChkData(approveId);
	}

}
