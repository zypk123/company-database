package ah.its.wrokflow.action;

import java.util.Map;

import ah.its.wrokflow.repository.dao.ApproveChk;

/**
* @Title: ApproveChkActionI.java 
* @Package ah.its.wrokflow.action 
* @Description: 审批单操作
* @author lil@cychina.cn
* @date 2016年4月27日 上午9:14:41 
* @version V1.0   
 */
public interface ApproveChkActionI {
	
	/** 
	* @Description: 根据申请单查询审批记录 
	* @param @param approveId
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map queryChkRecord(String approveId);
	
	
	/** 
	* @Description: 审批
	* @param @param form
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map processChkRecord(ApproveChk form);
}
