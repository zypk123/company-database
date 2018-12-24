package cy.its.device.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.criteria.DeviceLedSpecCriteria;
import cy.its.device.domain.model.DeviceLedSpec;

public interface IDeviceLedSpecService {
	//新增
	public String creatDeviceLedSpec(DeviceLedSpec deviceLedSpec);
	//更新
	public void updateDeviceLedSpec(DeviceLedSpec deviceLedSpec);
	//批量删除
	public int removeDeviceLedSpecId(Map<String,Object> ids);
	//查询
	List<DeviceLedSpec> findByDeviceLedSpec(DeviceLedSpecCriteria deviceLedCritera);
}
