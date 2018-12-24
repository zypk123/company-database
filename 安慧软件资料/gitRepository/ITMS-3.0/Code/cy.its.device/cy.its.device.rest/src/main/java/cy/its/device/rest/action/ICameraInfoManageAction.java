package cy.its.device.rest.action;


import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cy.its.device.domain.model.site.Lane;
import cy.its.device.rest.dto.CameraInfoDto;
import cy.its.device.rest.dto.DataGridResult;

public interface ICameraInfoManageAction {
	
	/**
	 * 添加保存相机信息
	 * @param form 相机信息
	 */
	public String addCameraInfo(CameraInfoDto form) throws Exception;
	
	/**
	 * 查询相机信息列表
	 * @return
	 * @throws Exception
	 */
	public DataGridResult<CameraInfoDto> queryCameraInfo(String deviceId) throws Exception;

	/**
	 * 删除相机
	 * @param sysComponentId 组件ID
	 * @return
	 * @throws Exception
	 */
	public int deleteCameraInfo(String sysComponentId) throws Exception;
	
	/**
	 * 根据设备ID删除相机
	 * @param deviceId 设备ID
	 * @return
	 * @throws Exception
	 */
	public int deleteCameraByDeviceId(String deviceId) throws Exception;
	
	/**
	 * 编辑相机信息
	 * @param sysComponentId 组件ID
	 * @return
	 * @throws Exception
	 */
	public int  editCameraInfo(CameraInfoDto form) throws Exception;
	
	/**
	 * 根据断面ID查询车信息
	 * @param sectionId
	 * @return
	 * @throws Exception
	 */
	public List<Lane> getLaneBySectionId(String sectionId) throws Exception;	
	
	/**
	 * 根据deviceKey查相机信息
	 * @param deviceKey 唯一性序列号
	 * @return 整形 1表示存在该相机，0表示不存在该相机
	 * @throws Exception 异常
	 */
	public int queryCameraByKey(@RequestParam("deviceKey") String deviceKey) throws Exception;
}
