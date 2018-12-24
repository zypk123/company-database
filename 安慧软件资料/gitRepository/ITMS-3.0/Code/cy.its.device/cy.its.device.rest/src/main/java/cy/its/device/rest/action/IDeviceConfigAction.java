package cy.its.device.rest.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cy.its.device.domain.model.Sys;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.DeviceConfigQryDto;
import cy.its.device.rest.dto.DeviceInfoDto;
import cy.its.device.rest.dto.DeviceQryConditionDto;
import cy.its.device.rest.dto.Dto;
import cy.its.device.rest.dto.TollgateQryDto;
import cy.its.device.rest.dto.TreeDto;

/**
 * 设备配置管理接口
 * @author STJ
 *
 */
public interface IDeviceConfigAction {
		
	/**
	 * 查询符合条件的设备信息列表
	 * @param form 设备查询条件
	 * @return 设备信息列表
	 * @throws Exception 异常
	 */
	public DataGridResult<DeviceConfigQryDto> queryDeviceLst(DeviceQryConditionDto form) throws Exception;
	
	
	
	/**
	 * 添加保存设备基本信息
	 * @param form 设备基本信息
	 * @return 设备ID
	 * @throws Exception 异常
	 */
	public String goAddDeviceInfo(DeviceInfoDto form) throws Exception;
	
	/**
	 * 删除设备
	 * @param deviceId 设备ID
	 * @return 整型 成功返回1
	 * @throws Exception 异常
	 */
	public int goDeleteDeviceInfo(String deviceId) throws Exception;
	
	/**
	 * 查看设备基本的信息
	 * @param deviceId 设备ID
	 * @return 设备信息对象
	 * @throws Exception 异常
	 */
	public DeviceInfoDto  lookDeviceInfo(String deviceId) throws Exception;
	
	/**
	 * 编辑设备信息
	 * @param form 设备信息对象
	 * @return  整型 成功返回1
	 * @throws Exception 异常
	 */
	public int goUpdateDeviceInfo(DeviceInfoDto form) throws Exception;
	
	/**
	 * 批量删除设备
	 * @param deviceIdStr 删除设备的ID字符串
	 * @param recordTypeStr	删除设备还是装备标识字符串
	 * @return	 整型 成功返回1
	 * @throws Exception 异常
	 */
	public int goDeleteDeviceInfoAll(String deviceIdStr,String recordTypeStr) throws Exception;

	/**
	 * 根据点位ID查询断面列表
	 * @param siteId 点位ID
	 * @return 断面列表
	 * @throws Exception 异常
	 */
	public List<TreeDto> querySectionBySiteId(String siteId) throws Exception;
	
	
	/**
	 * 启用设备或装备
	 * @param reason 原因
	 * @param deviceIdStr 多个设备Id字符串
	 * @param recordTypeStr 装备或设备标记(与多个设备Id字符串对应)
	 * @param request 包含currentUserId参数的Http请求
	 * @return 1:全部成功
	 * @throws Exception 异常
	 */
	public int goEnableDevice(String reason, String deviceIdStr, String recordTypeStr, HttpServletRequest request) throws Exception;
		
	/**
	 * 停用设备或装备
	 * @param reason 原因
	 * @param deviceIdStr 多个设备Id字符串
	 * @param recordTypeStr 装备或设备标记(与多个设备Id字符串对应)
	 * @param request 包含currentUserId参数的Http请求
	 * @return 1:全部成功
	 * @throws Exception 异常
	 */
	public int goStopDevice(String reason, String deviceIdStr, String recordTypeStr, HttpServletRequest request) throws Exception;
	
	/**
	 * 报废设备或装备
	 * @param reason 原因
	 * @param deviceIdStr 多个设备Id字符串
	 * @param recordTypeStr 装备或设备标记(与多个设备Id字符串对应)
	 * @param request 包含currentUserId参数的Http请求
	 * @return 1:全部成功
	 * @throws Exception 异常
	 */
	public int goBreakDevice(String reason, String deviceIdStr, String recordTypeStr, HttpServletRequest request) throws Exception;

	/**
	 * 查询关联设备
	 * @param orgPrivilegeCode 机构权限过滤代码
	 * @return 设备类型对应的设备ID与设备名称以及设备其他属性组成的树
	 * @throws Exception 异常
	 */
	public Map<String, List<Dto>> queryRelevanceDevice(String orgPrivilegeCode) throws Exception;
	
	/**
	 * 查询所有气象设备
	 * @return 气象设备信息
	 * @throws Exception 异常
	 */
	public List<Sys> queryWeatherDevice() throws Exception;
	
	
	/**
	 * 确认是否存在与指定设备编号相同的设备
	 * @param deviceSysNbr 设备编号
	 * @return 布尔类型  成功返回true表示存在该设备，false 表示不存在
	 * @throws Exception 异常
	 */
	public boolean querySysByDeviceSysNbr(String deviceSysNbr) throws Exception;
	
	/**
	 * 根据机构权限代码查询启用的led设备
	 * @param orgPrivilegeCode
	 * @return led设备集合
	 * @throws Exception 
	 */
	public List<DeviceInfoDto> queryLedDevice(String orgPrivilegeCode) throws Exception;
}


