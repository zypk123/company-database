package cy.its.device.rest.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import cy.its.device.rest.dto.BayonetDeviceDto;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.DeviceInfoDto;
import cy.its.device.rest.dto.TollgateQryDto;

/**
 * 卡口管理接口
 * @author STJ
 *
 */
public interface IBayonetDeviceAction {
	
	/**
	 * 查询符合条件的卡口设备信息
	 * @param form 设备查询条件
	 * @return 卡口设备信息列表
	 * @throws Exception 异常
	 */
	public DataGridResult<DeviceInfoDto> queryBayonetDeviceLst(@ModelAttribute("form") TollgateQryDto form) throws Exception;
	
	/**
	 * 查询符合条件的设备使用状态信息
	 * @param form 查询条件
	 * @return 查询结果对象列表
	 * @throws Exception 异常
	 */
	public Map<String, Object> selectSysUseStatusList(TollgateQryDto form) throws Exception;
	
	/**
	 * 新增卡口设备
	 * @param form 卡口信息对象
	 * @return 卡口设备的ID
	 * @throws Exception 异常
	 */
	public String goAddBayonet(BayonetDeviceDto form) throws Exception;
	
	/**
	 * 删除卡口设备
	 * @param deviceId 卡口设备Id
	 * @return 整型 成功返回1
	 * @throws Exception 异常
	 */
	public int goDeleteBayonet(String deviceId) throws Exception;
	
	/**
	 * 修改卡口设备
	 * @param form 卡口信息对象
	 * @return  整型 成功返回1
	 * @throws Exception 异常
	 */
	public int goUpdateBayonet(BayonetDeviceDto form) throws Exception;

	/** 
	 * 根据卡口设备Id查询卡口信息以及参数信息
	 * @param deviceId 卡口设备ID
	 * @return 卡口对象
	 * @throws Exception 异常
	 */
	public BayonetDeviceDto queryBayonetByDeviceId(String deviceId) throws Exception;
	
	/**
	 * 启用设备
	 * @param reason 原因
	 * @param deviceIdStr 多个设备Id字符串
	 * @param recordTypeStr 装备或设备标记(与多个设备Id字符串对应)
	 * @param request 包含currentUserId参数的Http请求
	 * @return 1:全部成功
	 * @throws Exception 异常
	 */
	public int goEnableDevice(String reason, String deviceIdStr, String recordTypeStr, HttpServletRequest request) throws Exception;
		
	/**
	 * 停用设备
	 * @param reason 原因
	 * @param deviceIdStr 多个设备Id字符串
	 * @param recordTypeStr 装备或设备标记(与多个设备Id字符串对应)
	 * @param request 包含currentUserId参数的Http请求
	 * @return 1:全部成功
	 * @throws Exception 异常
	 */
	public int goStopDevice(String reason, String deviceIdStr, String recordTypeStr, HttpServletRequest request) throws Exception;
	
	/**
	 * 报废设备
	 * @param reason 原因
	 * @param deviceIdStr 多个设备Id字符串
	 * @param recordTypeStr 装备或设备标记(与多个设备Id字符串对应)
	 * @param request 包含currentUserId参数的Http请求
	 * @return 1:全部成功
	 * @throws Exception 异常
	 */
	public int goBreakDevice(String reason, String deviceIdStr, String recordTypeStr, HttpServletRequest request) throws Exception;
	
	/**
	 * 
	 * @param deviceId 设备ID
	 * @return success : 删除成功
	 * @throws Exception 异常
	 */
	public String deleteCameraByDeviceId(String deviceId) throws Exception;
	
	
	/**
	 * 根据点位code，查找出所有卡口设备
	 * @param siteCode 点位code
	 * @param directionType 方向类型
	 * @return 所有卡口设备
	 * @throws Exception  异常
	 */
	public List<BayonetDeviceDto> queryBayonetBySiteCode(String siteCode,String directionType) throws Exception;
}
