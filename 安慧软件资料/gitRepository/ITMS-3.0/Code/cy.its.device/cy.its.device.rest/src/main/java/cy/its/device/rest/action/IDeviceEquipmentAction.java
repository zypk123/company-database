package cy.its.device.rest.action;

import cy.its.device.rest.dto.DeviceEquipmentDto;

public interface IDeviceEquipmentAction {
	
	/**
	 * 添加装备信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public String addEquipment(DeviceEquipmentDto form) throws Exception;
	
	/**
	 * 删除装备信息
	 * @param equipmentId
	 * @return
	 * @throws Exception
	 */
	public String deleteEquipment(String deviceId) throws Exception;
	
	/**
	 * 编辑装备信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public int updateEquipment(DeviceEquipmentDto form) throws Exception;
	
	/**
	 * 查看装备信息
	 * @param equipmentId
	 * @return
	 * @throws Exception
	 */
	public DeviceEquipmentDto queryEquipment(String deviceId) throws Exception;
	
	
}
