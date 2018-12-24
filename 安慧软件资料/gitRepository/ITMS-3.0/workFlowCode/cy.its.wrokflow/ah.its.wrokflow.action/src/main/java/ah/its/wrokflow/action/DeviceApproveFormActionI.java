package ah.its.wrokflow.action;

import java.util.Map;

import ah.its.wrokflow.dto.DeviceApproveDto;
import ah.its.wrokflow.repository.dao.DeviceApprove;

/**
* @Title: ApproveFormActionI.java 
* @Package cy.its.wrokflow.approve.action 
* @Description: 主要处理设备申请单 
* @author lil@cychina.cn
* @date 2016年3月28日 下午8:36:52 
* @version V1.0   
 */
public interface DeviceApproveFormActionI {
	
	/** 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param form
	* @param @return  返回 失败0  成功1 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map   saveDeviceApprove(DeviceApprove form);
	
	
	/** 
	* @Description: 更新申请单
	* @param @param form
	* @param @return   返回标志 失败1 成功0 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map   updateDeviceApprove(DeviceApprove form);
	
	/** 
	* @Description: 批量删除申请单
	* @param @param String
	* @param @return   返回值  成功1  失败0 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map   deleteDeviceApprove(String ids);
	
	/** 
	* @Description: 设备DTO接收参数
	* @param @param dto
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map   queryDeviceApprove(DeviceApproveDto dto);
	
	/** 
	* @Description: 提交申请单 
	* @param @param processId
	* @param @param deviceApproveId
	* @param @return 设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map  submitDeviceApprove(String processId,String deviceApproveId);


	/** 
	* @Description: 保存申请单并提交
	* @param @param form
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map saveDeviceApproveSubmit(DeviceApprove form);


	/** 
	* @Description: 通过ID获取申请单数据，同时查询出已经审批过的记录。
	* @param @param approveId
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map queryDeviceApproveDataById(String approveId);


	/** 
	* @Description: 修改数据时，取得所有的数据
	* @param @param approveId
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map queryDeviceApproveById(String approveId);



	/**
	 * 申请历史记录查询
	 * @param dto
	 * @return
	 */
	public Map queryDeviceApproveHistory(DeviceApproveDto dto);

	/**
	 * 审批历史记录详情
	 * @param dto
	 * @return
	 */
	//public Map queryDeviceApproveHistoryDetail(String approveId);
}
