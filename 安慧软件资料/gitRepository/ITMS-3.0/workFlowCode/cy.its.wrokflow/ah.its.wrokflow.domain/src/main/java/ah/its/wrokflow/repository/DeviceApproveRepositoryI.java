package ah.its.wrokflow.repository;

import java.util.List;
import java.util.Map;

import ah.its.wrokflow.repository.dao.ApproveChk;
import ah.its.wrokflow.repository.dao.DeviceApprove;
import ah.its.wrokflow.repository.dao.DeviceApproveResult;

/**
* @Title: DeviceApproveRepositoryI.java 
* @Package ah.its.wrokflow.repository 
* @Description: 仓储类
* @author lil@cychina.cn
* @date 2016年4月19日 下午8:06:24 
* @version V1.0   
 */
public interface DeviceApproveRepositoryI {
	
	public  int  saveApproveData(DeviceApprove record);
	
	public  int  updateApproveData(DeviceApprove record);
	
	public List<DeviceApprove> selectAllData(Map map);

	public int deleteDeviceApprove(Map map);

	public DeviceApprove queryDeviceApproveById(String approveId);

	/**
	 * 审批单历史查询
	 * @param map
	 * @return
	 */
	public List<DeviceApprove> queryDeviceApproveHistory(Map map);

	/**
	 * 审批历史记录详情
	 * @param approveId
	 * @return
	 */
	public List<ApproveChk> queryDeviceApproveHistoryDetail(String approveId);

}
