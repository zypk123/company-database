package cy.its.device.rest.action;

import java.util.List;
import java.util.Map;

import cy.its.device.rest.dto.DeviceLedSpecDto;
import cy.its.device.rest.dto.TreeDto;

public interface IDeviceLedSpecAction {
	public String saveDeviceLedSpec(DeviceLedSpecDto deviceLedSpecDto) throws Exception;
	
	public void updateDeviceLedSpec(DeviceLedSpecDto deviceLedSpecDto) throws Exception;
	
	public Map<String, Object> findByDeviceLedSpec(DeviceLedSpecDto DeviceLedSpecDto);
	
	public int deleteByDeviceLedSpecId(String ids) throws Exception;

	List<TreeDto> queryDeviceLedSpec() throws Exception;
	
	public DeviceLedSpecDto queryLedSpecInfo(String specId) throws Exception;
}
