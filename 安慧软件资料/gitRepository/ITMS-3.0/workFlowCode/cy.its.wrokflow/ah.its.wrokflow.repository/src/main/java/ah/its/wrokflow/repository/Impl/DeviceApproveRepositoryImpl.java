package ah.its.wrokflow.repository.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.repository.DeviceApproveRepositoryI;
import ah.its.wrokflow.repository.dao.ApproveChk;
import ah.its.wrokflow.repository.dao.DeviceApprove;
import ah.its.wrokflow.repository.dao.DeviceApproveResult;
import ah.its.wrokflow.repository.domain.DeviceApproveMapper;

/**
* @Title: DeviceApproveRepositoryImpl.java 
* @Package ah.its.wrokflow.repository.Impl 
* @Description: 设备申请单 仓储类
* @author lil@cychina.cn
* @date 2016年4月19日 下午8:12:26 
* @version V1.0   
 */
@Service
public class DeviceApproveRepositoryImpl implements DeviceApproveRepositoryI {

	
	
	@Autowired
	private DeviceApproveMapper deviceApproveMapper;//用户操作接口
	
	@Override
	public int saveApproveData(DeviceApprove record) {
		// TODO Auto-generated method stub
		return deviceApproveMapper.insert(record);
	}

	@Override
	public int updateApproveData(DeviceApprove record) {
		// TODO Auto-generated method stub
		return deviceApproveMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public List<DeviceApprove> selectAllData(Map map) {
		// TODO Auto-generated method stub
		return  deviceApproveMapper.selectAllData(map);
	}

	@Override
	public int deleteDeviceApprove(Map map) {
		// TODO Auto-generated method stub
		DeviceApprove record = new DeviceApprove();
		record.setDeviceApproveId(map.get("approveId").toString());
		record.setDisabled("1");
		return deviceApproveMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public DeviceApprove queryDeviceApproveById(String approveId) {
		return deviceApproveMapper.selectByPrimaryKey(approveId);
	}

	@Override
	public List<DeviceApprove> queryDeviceApproveHistory(Map map) {
		// TODO Auto-generated method stub
		return deviceApproveMapper.queryDeviceApproveHistory(map);
	}

	@Override
	public List<ApproveChk> queryDeviceApproveHistoryDetail(String approveId) {
		// TODO Auto-generated method stub
		return deviceApproveMapper.queryDeviceApproveHistoryDetail(approveId);
	}

}
