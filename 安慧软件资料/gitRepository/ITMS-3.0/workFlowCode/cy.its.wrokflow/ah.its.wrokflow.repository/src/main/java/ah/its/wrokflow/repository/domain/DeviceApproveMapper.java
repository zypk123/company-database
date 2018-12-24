package ah.its.wrokflow.repository.domain;

import java.util.List;
import java.util.Map;

import ah.its.wrokflow.repository.dao.ApproveChk;
import ah.its.wrokflow.repository.dao.DeviceApprove;

public interface DeviceApproveMapper {
    int deleteByPrimaryKey(String deviceApproveId);

    int insert(DeviceApprove record);

    int insertSelective(DeviceApprove record);

    DeviceApprove selectByPrimaryKey(String deviceApproveId);

    int updateByPrimaryKeySelective(DeviceApprove record);

    int updateByPrimaryKey(DeviceApprove record);

	/** 
	* @Description: 获取用户所在部门下所有的申请单 
	* @param @param map
	* @param @return    设定文件 
	* @return List<DeviceApprove>    返回类型 
	* @throws 
	*/
	List<DeviceApprove> selectAllData(Map map);

	/**
	 * 审批单历史查询
	 * @param map
	 * @return
	 */
	List<DeviceApprove> queryDeviceApproveHistory(Map map);

	/**
	 * 审批历史记录详情
	 * @param approveId
	 * @return
	 */
	List<ApproveChk> queryDeviceApproveHistoryDetail(String deviceApproveId);

}