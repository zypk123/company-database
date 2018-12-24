package ah.its.wrokflow.domain;

import java.util.List;
import java.util.Map;

import ah.its.wrokflow.repository.dao.ApproveChk;
import ah.its.wrokflow.repository.dao.DeviceApprove;
import ah.its.wrokflow.repository.dao.DeviceApproveResult;

/**
* @Title: DeviceApproveServiceI.java 
* @Package ah.its.wrokflow.domain 
* @Description: 申请单业务处理类 
* @author lil@cychina.cn
* @date 2016年4月19日 下午7:59:44 
* @version V1.0   
 */
public interface DeviceApproveServiceI {
	
	/** 
	* @Description: 保存设备申请单 
	* @param @param record
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	public  int  saveDeviceApprove(DeviceApprove record);
	
	
	/** 
	* @Description: 流程实例ID，更新到申请单中
	* @param @param record
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	public  int  updateDeviceApprove(DeviceApprove record);


	/** 
	* @Description:查询申请单
	* @param @param map
	* @param @return    设定文件 
	* @return List<DeviceApprove>    返回类型 
	* @throws 
	*/
	public  List<DeviceApprove> selectAllData(Map map);


	/** 
	* @Description:删除申请单
	* @param @param map    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
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
