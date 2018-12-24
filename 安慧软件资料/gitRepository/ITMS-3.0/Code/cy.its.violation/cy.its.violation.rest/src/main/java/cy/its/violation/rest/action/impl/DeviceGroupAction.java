/**
 * @Title: DeviceGroupAction.java
 * @Package cy.its.violation.rest.action.impl
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月9日 下午8:39:11
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.violation.rest.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.service.ISystemService;
import cy.its.syscfg.domain.criteria.UserCriteria;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.violation.domain.criteria.AssoUserDeviceCriteria;
import cy.its.violation.domain.model.config.VioAssoUserDevice;
import cy.its.violation.domain.service.IVioAssoUserDeviceService;
import cy.its.violation.rest.action.IDeviceGroupAction;
import cy.its.violation.rest.dto.DeviceGroupDto;

/**
 * @ClassName: DeviceGroupAction
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月9日 下午8:39:11
 *
 */
@RestController
@RequestMapping("/deviceGroupAction")
public class DeviceGroupAction implements IDeviceGroupAction {

	@Autowired
	ISystemService systemService;
	@Autowired
	IDutyService dutyService;
	@Autowired
	IVioAssoUserDeviceService vioAssoUserDeviceService;
	/*
	 * <p>Title: findDeviceAction</p> <p>Description:查找用户名 </p>
	 * 
	 * @param deviceGroupDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IDeviceGroupAction#searchDeviceAction(cy.its
	 * .violation.rest.dto.DeviceGroupDto)
	 */

	@RequestMapping("/findDeviceAction")
	@Override
	public ArrayList<DeviceGroupDto> findDeviceAction(DeviceGroupDto deviceGroupDto) throws Exception {
		ArrayList<DeviceGroupDto> lstView = new ArrayList<DeviceGroupDto>();
		UserCriteria userCriteria = new UserCriteria();
		// 获得所有的用户信息
		List<User> lst = dutyService.findUsers(userCriteria);
		// 遍历 list获得用户Id，用户名
		for (User user : lst) {
			DeviceGroupDto deviceGroup = new DeviceGroupDto();
			// 把领域获得用户Id和用户名转换为客户端
			deviceGroup.setUserId(user.getUserId());
			deviceGroup.setUserName(user.name);
			ObjectMapUtils.parseObject(deviceGroup, user);
			// 把客户端的数据添加到ArrayList集合
			lstView.add(deviceGroup);
		}

		return lstView;

	}
	/*
	 * <p>Title: searchDeviceList</p> <p>Description: </p>
	 * 
	 * @param deviceGroupDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IDeviceGroupAction#searchDeviceList(cy.its.
	 * violation.rest.dto.DeviceGroupDto)
	 */

	@RequestMapping("/findDeviceList")
	@Override
	public Map findDeviceList(@RequestParam("orgId") String orgId) {

		ArrayList<DeviceGroupDto> lstView = new ArrayList<DeviceGroupDto>();
		SystemCriteria systemCriteria = new SystemCriteria();
		// 获得所有的设备的信息
		List<Sys> lst = systemService.findSystems(systemCriteria);
		// 获取该用户管辖的设备（因为设备的领域中不包含机构的领域，所以要过滤数据权限必须要增加下一步，去系统的领域中查询该用户的下属机构）
		List<Sys> devLst = filterCurrentUserDev(orgId, lst);
		// 遍历该机构下及下机构的所有设备获得设备名和设备编号
		for (Sys list : devLst) {
			DeviceGroupDto device = new DeviceGroupDto();
			device.setDeviceName(list.getDeviceName());
			device.setDeviceSysNbr(list.getDeviceSysNbr());
			lstView.add(device);
		}

		Map map = new HashMap();
		map.put("error", "");
		Map maprows = new HashMap();
		maprows.put("rows", lstView);
		map.put("result", maprows);
		return map;

	}

	/**
	 * FilterCurrentUserDev(根据用户的机构ID获取该用户的机构及其下属机构) TODO(这里描述这个方法适用条件 – 可选)
	 * TODO(这里描述这个方法的执行流程 – 可选) TODO(这里描述这个方法的使用方法 – 可选) TODO(这里描述这个方法的注意事项 –
	 * 可选)
	 *
	 * @Title: FilterCurrentUserDev @Description: TODO @param @param
	 * orgId @param @param lst @param @return 设定文件 @return List<Sys>
	 * 返回类型 @throws
	 */

	private List<Sys> filterCurrentUserDev(String orgId, List<Sys> lst) {

		if (lst == null || lst.size() == 0) {
			return null;
		}
		if (orgId.equals("")) // 如果当前用户未知，则返回所有的设备
		{
			return lst;
		}
		List<Sys> devs = new ArrayList<Sys>();// 存放当前用户管辖的设备设备
		try {
			// 根据用户的机构ID获取该用户的机构及其下属机构
			List<Organization> sonOrgLst = dutyService.findSonOrgsOfParent(orgId);
			List<String> orgIds = sonOrgLst.stream().map(org -> org.getIdentityId()).collect(Collectors.toList());

			if (orgIds == null || orgIds.size() == 0) {
				orgIds = new ArrayList<String>();
			}
			orgIds.add(orgId);// 把当前用户的机构加入
			for (Sys s : lst) {
				String devOrgId = s.getOrgId();
				if (orgIds.contains(devOrgId)) {
					devs.add(s);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return devs;
	}

	/*
	 * <p>Title: findDGroupAction</p> <p>Description: 查询设备分组信息 </p>
	 * 
	 * @param deviceGroupDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IDeviceGroupAction#searchDGroupAction(cy.its
	 * .violation.rest.dto.DeviceGroupDto)
	 */
	@RequestMapping("/findDGroupAction")
	@Override
	public Map findDGroupAction() throws Exception {
		// 通过设备分组后的用户ID查找用户名,设备编号(分组)
		// 通过 用户ID及设备编号转换用户名，设备名
		ArrayList<DeviceGroupDto> dtoList = new ArrayList<DeviceGroupDto>();
		AssoUserDeviceCriteria assoUserDeviceCriteria = new AssoUserDeviceCriteria();
		assoUserDeviceCriteria.setNoPage();
		List<VioAssoUserDevice> list = vioAssoUserDeviceService.findVioAssoUserDevice(assoUserDeviceCriteria);
		for (VioAssoUserDevice vioAssoUserDevice : list) {
			// 转用户ID为名称
			String username = convertUserIdToName(vioAssoUserDevice.getUserId());
			// 转设备id为名称
			String deviceName = getDeviceNameBySysNbr(vioAssoUserDevice.getDeviceSysNbr());
			// 转程dto
			DeviceGroupDto dto = new DeviceGroupDto();
			dto.setUserId(vioAssoUserDevice.getUserId());
			dto.setUserName(username);
			dto.setDeviceName(deviceName);
			dtoList.add(dto);
		}
		// 按照userId分组，设备编号，设备名称，多个用","好分割
		List<DeviceGroupDto> viewList = groupDeviceGroupByUserId(dtoList);

		Map map = new HashMap();
		map.put("error", "");
		Map maprows = new HashMap();
		maprows.put("rows", viewList);
		map.put("result", maprows);
		return map;

	}

	private List<DeviceGroupDto> groupDeviceGroupByUserId(ArrayList<DeviceGroupDto> dtoList) {
		Map<String, List<DeviceGroupDto>> groupMap = dtoList.stream()
				.collect(Collectors.groupingBy(DeviceGroupDto::getUserId));
		List<DeviceGroupDto> viewList = new ArrayList<DeviceGroupDto>();
		for (Entry<String, List<DeviceGroupDto>> item : groupMap.entrySet()) {
			DeviceGroupDto viewDto = new DeviceGroupDto();
			viewDto.setUserId(item.getKey());
			viewDto.setUserName(item.getValue().get(0).getUserName());
			StringBuilder deviceIds = new StringBuilder();
			StringBuilder deviceNames = new StringBuilder();
			for (DeviceGroupDto dtoItem : item.getValue()) {
				deviceIds.append(dtoItem.getDeviceSysNbr()).append(",");
				deviceNames.append(dtoItem.getDeviceName()).append(",");
			}
			viewDto.setDeviceSysNbrs(deviceIds.substring(0, deviceIds.length() - 1));
			viewDto.setDeviceNames(deviceNames.substring(0, deviceNames.length() - 1));
			viewList.add(viewDto);
		}
		return viewList;
	}

	// 获得设备名
	private String getDeviceNameBySysNbr(String deviceSysNbr) {
		String deviceName = "";
		Sys deviceSys = systemService.selectByNBR(deviceSysNbr);
		if (deviceSys != null) {
			deviceName = deviceSys.getDeviceName();
		}
		return deviceName;
	}

	// 获得用户名
	private String convertUserIdToName(String userId) throws Exception {
		return dutyService.userOfId(userId).name;
	}

	/*
	 * <p>Title: createDGroupAction</p> <p>Description:创建用户设备 </p>
	 * 
	 * @param deviceGroupDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IDeviceGroupAction#saveDGroupAction(cy.its.
	 * violation.rest.dto.DeviceGroupDto)
	 */
	@RequestMapping("/createDGroupAction")
	@Override
	public String createDGroupAction(String deviceSysNbrStr, String userId) {
		String[] deviceSysNbr = deviceSysNbrStr.split(",");

		for (int i = 0; i < deviceSysNbr.length; i++) {
			VioAssoUserDevice vioAssoUserDevice = new VioAssoUserDevice();
			vioAssoUserDevice.setDeviceSysNbr(deviceSysNbr[i]);
			vioAssoUserDevice.setUserId(userId);
			vioAssoUserDeviceService.saveVioAssoUserDevice(vioAssoUserDevice);
		}

		return "success";
	}

	/*
	 * <p>Title: updateDGroupAction</p> <p>Description: </p>
	 * 
	 * @param deviceGroupDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IDeviceGroupAction#updateDGroupAction(cy.its
	 * .violation.rest.dto.DeviceGroupDto)
	 */

	@Override
	public String updateDGroupAction(DeviceGroupDto deviceGroupDto) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 
	 * <p>Title: deleteDGroupAction</p> <p>Description: 批量删除</p>
	 * 
	 * @param userIdStr
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IDeviceGroupAction#deleteDGroupAction(java.
	 * lang.String)
	 */
	@RequestMapping("/deleteDGoupAction")
	@Override
	public String deleteDGroupAction(String userIdStr) {
		String userId[] = userIdStr.split(",");
		int resultIdStr = 0;
		for (int i = 0; i < userId.length; i++) {
			// 获得每一项的Id
			resultIdStr += vioAssoUserDeviceService.removeVioAssoUserDeviceByUserID(userId[i]);

		}
		if (resultIdStr > 0) {
			return "success";
		} else {
			return "false";
		}

	}

	/*
	 * 
	 * <p>Title: removeDGroupAction</p> <p>Description: 单个删除 </p>
	 * 
	 * @param userId
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IDeviceGroupAction#removeDGroupAction(java.
	 * lang.String)
	 */
	@RequestMapping("/removeDGroupAction")
	@Override
	public String removeDGroupAction(@RequestParam("userId") String userId) {
		int resultUserId = vioAssoUserDeviceService.removeVioAssoUserDeviceByUserID(userId);
		if (resultUserId > 0) {
			return "1";
		} else {
			return "0";
		}
	}

}
