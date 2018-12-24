package ah.its.wrokflow.domain.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.domain.DeviceApproveServiceI;
import ah.its.wrokflow.repository.DeviceApproveRepositoryI;
import ah.its.wrokflow.repository.dao.ApproveChk;
import ah.its.wrokflow.repository.dao.DeviceApprove;
import ah.its.wrokflow.repository.dao.DeviceApproveResult;

/**
* @Title: DeviceApproveServiceImpl.java 
* @Package ah.its.wrokflow.domain.Impl 
* @Description: 申请单接口实现类 
* @author lil@cychina.cn
* @date 2016年4月19日 下午8:03:08 
* @version V1.0   
 */
@Service
public class DeviceApproveServiceImpl implements DeviceApproveServiceI{

	@Autowired
	private DeviceApproveRepositoryI deviceApproveRepositoryImpl;//用户操作接口
	
	@Override
	public int saveDeviceApprove(DeviceApprove record) {
		// TODO Auto-generated method stub
		return deviceApproveRepositoryImpl.saveApproveData(record);
	}

	@Override
	public int updateDeviceApprove(DeviceApprove record) {
		// TODO Auto-generated method stub
		return deviceApproveRepositoryImpl.updateApproveData(record);
	}
	
	@Override
	public List<DeviceApprove>  selectAllData(Map map) {
		// TODO Auto-generated method stub
		return deviceApproveRepositoryImpl.selectAllData(map);
	}

	@Override
	public int deleteDeviceApprove(Map map) {
		
		return deviceApproveRepositoryImpl.deleteDeviceApprove(map);
		
	}

	@Override
	public DeviceApprove queryDeviceApproveById(String approveId) {
		// TODO Auto-generated method stub
		return  deviceApproveRepositoryImpl.queryDeviceApproveById(approveId);
	}

	@Override
	public List<DeviceApprove> queryDeviceApproveHistory(Map map) {
		// TODO Auto-generated method stub
		return deviceApproveRepositoryImpl.queryDeviceApproveHistory(map);
	}

	@Override
	public List<ApproveChk> queryDeviceApproveHistoryDetail(String approveId) {
		// TODO Auto-generated method stub
		return deviceApproveRepositoryImpl.queryDeviceApproveHistoryDetail(approveId);
	}

}
