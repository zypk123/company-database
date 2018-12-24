package ah.its.wrokflow.domain;

import java.util.List;

import ah.its.wrokflow.repository.dao.ApproveChk;

/**
* @Title: ApproveChkServiceI.java 
* @Package ah.its.wrokflow.domain 
* @Description: 审批单操作
* @author lil@cychina.cn
* @date 2016年4月26日 下午6:28:14 
* @version V1.0   
 */
public interface ApproveChkServiceI {
	
	public int saveApproveChkData(ApproveChk record);
	
	public List<ApproveChk> queryApproveChkData(String approveId);
}
