package cy.its.device.rest.action;

import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.DeviceDataPathDto;

public interface IDeviceDataPathAction {
	/**
	 * 查询数据轨迹列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	DataGridResult<DeviceDataPathDto> queryDeviceDataPath(DeviceDataPathDto form) throws Exception;
}
