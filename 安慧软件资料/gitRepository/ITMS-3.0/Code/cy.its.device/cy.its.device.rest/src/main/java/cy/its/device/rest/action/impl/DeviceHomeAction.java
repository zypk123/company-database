/**
 * @Title: DeviceHomeAction.java
 * @Package cy.its.device.rest.action.impl
 * @Description: 设备概括首页rest服务类
 * @author  chenzhiying chenzy@cychina.cn
 * @date 2015年12月18日 上午10:23:12
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.rest.action.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cy.its.com.constant.SysCodeConstant;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.service.ISystemService;
import cy.its.device.rest.action.IDeviceHomeAction;
import cy.its.platform.common.model.DeviceCertiStatusModel;
import cy.its.platform.common.model.DeviceGeneralStatusModel;
import cy.its.platform.common.utils.Log4jFactoryProxy;
import cy.its.platform.common.utils.RedisPoolUtil;
import cy.its.syscfg.domain.criteria.OrgCriteria;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.syscfg.domain.service.ISysCodeService;

/**
 * @ClassName: DeviceHomeAction
 * @Description: 设备概括首页rest服务类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年12月18日 上午10:23:12
 *
 */
@RestController
@RequestMapping("/device/deviceHome")
public class DeviceHomeAction implements IDeviceHomeAction {

	private Log4jFactoryProxy log4jFactoryProxy = Log4jFactoryProxy.getSingleton(DeviceHomeAction.class);

	@Autowired
	ISystemService systemService;

	@Autowired
	ISysCodeService sysCodeService;

	@Autowired
	IDutyService dutyService;

	private static final String DEVICE_CERTI_STATUS_REDIS_KEY = "deviceCertiStatus";
	private static final String DEVICE_STATUS_REDIS_KEY = "deviceStatus";

	/**
	 * getDeviceCertiStatusModelsLst @Title:
	 * getDeviceCertiStatusModelsLst @Description: 从缓存中获取设备实时检定信息 @param @return
	 * 设定文件 @return List<DeviceCertiStatusModel> 返回类型 @throws
	 */
	private List<DeviceCertiStatusModel> getDeviceCertiStatusModelsLst() {
		List<String> devCertiCll = null;
		synchronized (DeviceHomeAction.class) {
			devCertiCll = new ArrayList<String>(RedisPoolUtil.hgetAll(DEVICE_CERTI_STATUS_REDIS_KEY).values());
		}
		if (devCertiCll == null || devCertiCll.size() == 0) {
			return null;
		}
		List<DeviceCertiStatusModel> devCertiLst = null;
		try {
			devCertiLst = devCertiCll.stream().map(p -> JSONObject.parseObject(p, DeviceCertiStatusModel.class))
					.collect(Collectors.toList());

		} catch (Exception e) {
			log4jFactoryProxy
					.error("DeviceHomeAction.getDeviceCertiStatusModelsLst方法异常：从redis中获取的jsonObject在转换为DeviceCertiStatusModel对象出错："
							+ e.getMessage());
		}
		return devCertiLst;
	}

	/**
	 * 
	 * @Title: getDeviceGeneralStatusLst @Description: 获取设备概况 @param @return
	 *         设定文件 @return List<DeviceGeneralStatusModel> 返回类型 @throws
	 */
	private List<DeviceGeneralStatusModel> getDeviceGeneralStatusLst() {
		List<String> devCll = null;
		synchronized (DeviceHomeAction.class) {
			devCll = new ArrayList<String>(RedisPoolUtil.hgetAll(DEVICE_STATUS_REDIS_KEY).values());
		}
		if (devCll == null || devCll.size() == 0) {
			return null;
		}
		List<DeviceGeneralStatusModel> devLst = new ArrayList<DeviceGeneralStatusModel>();
		for (int i = 0; i < devCll.size(); i++) {
			JSONObject jsonObject = JSONObject.parseObject(devCll.get(i));
			try {
				devLst.add(convertStatusObj(jsonObject));
			} catch (Exception e) {
				log4jFactoryProxy
						.error("DeviceHomeAction.getDeviceGeneralStatusLst方法异常：从redis中获取的jsonObject在转换为DeviceGeneralStatusModel对象出错："
								+ e.getMessage());
				continue;
			}
		}
		return devLst;
	}

	private static DeviceGeneralStatusModel convertStatusObj(JSONObject temp) {
		DeviceGeneralStatusModel deviceStatus = new DeviceGeneralStatusModel();
		deviceStatus.setDeviceId(temp.getString("deviceId"));
		String orgPrivilegeCode = temp.getString("orgPrivilegeCode");
		deviceStatus.setOrgPrivilegeCode(orgPrivilegeCode);
		deviceStatus.setDeviceType(temp.getString("deviceType"));
		deviceStatus.setStatusType(temp.getInteger("statusType"));
		String bugType = "10001";// 默认相机故障
		JSONArray arrayStatusDetails = temp.getJSONArray("faultDetails");
		// 如果是工控机设备，该故障有值
		if (arrayStatusDetails != null && arrayStatusDetails.size() > 0) {
			if (arrayStatusDetails.contains("10005"))// 如果故障列表中有电源故障，则为电源故障
			{
				bugType = "10005";
			} else if (arrayStatusDetails.contains("10006"))// 如果故障列表中有网络故障，则为网络故障
			{
				bugType = "10006";
			}
		} else// 如果是智能相机，该故障有值，具体的故障是存在智能相机的组件中的
		{
			arrayStatusDetails = temp.getJSONArray("componentStatusResults");
			if (arrayStatusDetails != null && arrayStatusDetails.size() > 0) {
				for (Object status : arrayStatusDetails) {
					JSONObject tempStatus = (JSONObject) status;
					if (tempStatus == null) {
						continue;
					}
					JSONArray arrayDetails = tempStatus.getJSONArray("faultDetails");
					if (arrayDetails != null && arrayDetails.size() > 0) {
						if (arrayDetails.contains("10005"))// 如果故障列表中有电源故障，则为电源故障
						{
							bugType = "10005";
							break;
						} else if (arrayStatusDetails.contains("10006"))// 如果故障列表中有网络故障，则为网络故障
						{
							bugType = "10006";
							break;
						}
					}
				}
			}
		}
		deviceStatus.setStatusBugType(bugType);
		return deviceStatus;
	}

	@Override
	@RequestMapping(value = "/getDevGenalSituation")
	public Map getDevGenalSituation(String orgPrivilegeCode, String currentOrgId) throws Exception {
		Map mp = new HashMap();
		String orgPriviCode = orgPrivilegeCode;// request.getParameter("orgPrivilegeCode");
		if (StringUtil.isNullOrEmpty(orgPriviCode)) {
			throw new Exception("该用户所在机构权限代码没有配置，查询不到其管辖的设备情况！");
		}
		// 获取当前用户下属一个级别的所有机构
		List<String> sonOrgPriCodeLst = getSonOrg(orgPriviCode);
		// 从缓存中获取当前用户下的所有设备
		List<DeviceGeneralStatusModel> deviceGenalSituations = getUserDevLstByPriviCode(orgPriviCode,
				getDeviceGeneralStatusLst());
		// 获取当前具有设备状态的设备类型有多少种
		Map<String, String> columns = getColums(deviceGenalSituations);
		boolean isNeedCerti = false;
		// 获取当前用户下检定设备
		List<DeviceCertiStatusModel> deviceCertiSituations = getUserCertiDevLstByPriviCode(orgPriviCode,
				getDeviceCertiStatusModelsLst());
		if (deviceCertiSituations != null && deviceCertiSituations.size() > 0) {
			isNeedCerti = true;
		}
		mp.put("hasCerti", isNeedCerti);
		mp.put("forGrdColumn", columns);
		List<String> columnLst = null;
		if (columns != null) {
			columnLst = columns.keySet().stream().collect(Collectors.toList());
		}
		String remark = "query";//判断是刷新还是查询操作的标志
		mp.put("forGrdData", getDevGenalData(deviceCertiSituations, deviceGenalSituations, orgPriviCode, columnLst,
				sonOrgPriCodeLst,remark));
		return mp;
	}

	/**
	 * @Title: getSonOrg @Description: 获取当前用户下属一个级别的所有机构 @param @param
	 *         orgPriviCode @param @return 设定文件 @return List
	 *         <String> 返回类型 @throws
	 */
	private List<String> getSonOrg(String orgPriviCode) {
		List<String> sonOrgPriCodeLst = new ArrayList<String>();
		// 获取当前用户下属一个级别的所有机构
		int curOrgPriviCodeLen = orgPriviCode.length();
		if (curOrgPriviCodeLen <= 4)// 总队、支队用户
		{
			List<Organization> allSonOrgs = dutyService.findOrgOfParent(orgPriviCode);
			if (allSonOrgs != null && allSonOrgs.size() > 0) {
				List<Organization> sonOrgs = allSonOrgs.stream()
						.filter(p -> !StringUtil.isNullOrEmpty(p.orgPrivilegeCode)
								&& p.orgPrivilegeCode.length() == curOrgPriviCodeLen + 2)
						.collect(Collectors.toList());
				if (sonOrgs != null && sonOrgs.size() > 0) {
					sonOrgPriCodeLst.addAll(sonOrgs.stream().map(p -> p.orgPrivilegeCode).collect(Collectors.toList()));
				}
			}
		}
		return sonOrgPriCodeLst;
	}

	/**
	 * @Title: getUserDevLstByPriviCode @Description: 获取当前用户下的所有设备 @param @param
	 *         orgPriviCode @param @param devLst @param @return 设定文件 @return
	 *         List<DeviceGeneralStatusModel> 返回类型 @throws
	 */
	private List<DeviceGeneralStatusModel> getUserDevLstByPriviCode(String orgPriviCode,
			List<DeviceGeneralStatusModel> devLst) {
		List<DeviceGeneralStatusModel> filterDevLst = null;
		if (devLst != null && devLst.size() > 0) {
			filterDevLst = devLst.stream().filter(p -> {
				try {
					return p.getOrgPrivilegeCode().startsWith(orgPriviCode);
				} catch (Exception e) {
					log4jFactoryProxy.error("DeviceHomeAction.getUserDevLstByPriviCode方法异常：该设备的所属机构权限代码为空！");
					return false;
				}
			}).collect(Collectors.toList());
		}
		return filterDevLst;
	}
	
	private List<DeviceGeneralStatusModel> getUserDevLstByOrgId(String orgPriviCode,
			List<DeviceGeneralStatusModel> devLst) {
		List<DeviceGeneralStatusModel> filterDevLst = null;
		if (devLst != null && devLst.size() > 0) {
			filterDevLst = devLst.stream().filter(p -> {
				try {
					return p.getOrgPrivilegeCode().equals(orgPriviCode);
				} catch (Exception e) {
					log4jFactoryProxy.error("DeviceHomeAction.getUserDevLstByPriviCode方法异常：该设备的所属机构权限代码为空！");
					return false;
				}
			}).collect(Collectors.toList());
		}
		return filterDevLst;
	}

	/**
	 * @Title: getUserCertiDevLstByPriviCode @Description:
	 *         获取当前用户下检定设备 @param @param orgPriviCode @param @param
	 *         deviceCertiLst @param @return 设定文件 @return List
	 *         <DeviceCertiStatusModel> 返回类型 @throws
	 */
	private List<DeviceCertiStatusModel> getUserCertiDevLstByPriviCode(String orgPriviCode,
			List<DeviceCertiStatusModel> deviceCertiLst) {
		List<DeviceCertiStatusModel> filterDeviceCertiLst = null;
		if (deviceCertiLst != null && deviceCertiLst.size() > 0) {
			filterDeviceCertiLst = deviceCertiLst.stream().filter(p -> {
				try {
					return p.getOrgPrivilegeCode().startsWith(orgPriviCode);
				} catch (Exception e) {
					log4jFactoryProxy.error("DeviceHomeAction.getUserCertiDevLstByPriviCode方法异常：该设备的所属机构权限代码为空！");
					return false;
				}
			}).collect(Collectors.toList());
		}
		return filterDeviceCertiLst;
	}

	private List<DeviceCertiStatusModel> getUserCertiDevLstByOrgId(String orgPriviCode,
			List<DeviceCertiStatusModel> deviceCertiLst) {
		List<DeviceCertiStatusModel> filterDeviceCertiLst = null;
		if (deviceCertiLst != null && deviceCertiLst.size() > 0) {
			filterDeviceCertiLst = deviceCertiLst.stream().filter(p -> {
				try {
					return p.getOrgPrivilegeCode().equals(orgPriviCode);
				} catch (Exception e) {
					log4jFactoryProxy.error("DeviceHomeAction.getUserCertiDevLstByPriviCode方法异常：该设备的所属机构权限代码为空！");
					return false;
				}
			}).collect(Collectors.toList());
		}
		return filterDeviceCertiLst;
	}

	private Map<String, String> getColums(List<DeviceGeneralStatusModel> deviceGenalSituations) throws Exception {
		if (deviceGenalSituations == null || deviceGenalSituations.size() == 0) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		Map<String, List<DeviceGeneralStatusModel>> devTypeGrp = deviceGenalSituations.stream()
				.collect(Collectors.groupingBy(DeviceGeneralStatusModel::getDeviceType));
		if (devTypeGrp != null) {
			Set<String> devTypes = devTypeGrp.keySet();
			for (Iterator<String> iterator = devTypes.iterator(); iterator.hasNext();) {
				String devType = (String) iterator.next();
				String devTypeName = getCodeName(SysCodeConstant.DEVICE_TYPE, devType);
				map.put(devType, devTypeName);
			}
		}
		return map;
	}

	private String getOrganizationByPriCode(String priCode) {
		OrgCriteria orgcriteria = new OrgCriteria();
		orgcriteria.orgPrivCode = priCode;
		List<Organization> orgLst = dutyService.findOrganizations(orgcriteria);
		if (orgLst != null && orgLst.size() > 0) {
			return orgLst.get(0).orgName;
		} else {
			return priCode;
		}
	}

	private List<Map<String, Object>> getDevGenalData(List<DeviceCertiStatusModel> devCertiLst,
			List<DeviceGeneralStatusModel> deviceGenalSituations, String orgPriviCode, List<String> columnArry,
			List<String> orgPriCodeArry,String remark) {
		List<Map<String, Object>> devGenralData = new ArrayList<Map<String, Object>>();
		if (orgPriCodeArry != null && orgPriCodeArry.size() > 0) {
			for (int i = 0; i < orgPriCodeArry.size(); i++) {
				String curOrgPriCode = orgPriCodeArry.get(i);
				// 获取该机构下的所有设备记录
				List<DeviceGeneralStatusModel> devOfOrgLst = getUserDevLstByPriviCode(curOrgPriCode,
						deviceGenalSituations);
				List<DeviceCertiStatusModel> devCertiOfOrgLst = getUserCertiDevLstByPriviCode(curOrgPriCode,
						devCertiLst);
				devGenralData.add(getGenalDataOfOrg(curOrgPriCode, devOfOrgLst, columnArry, devCertiOfOrgLst));
			}
		}
		if (remark != "refresh") {
			// 最后加上当前用户所在机构
			devGenralData.add(getGenalDataOfOrg(orgPriviCode,
					new ArrayList<DeviceGeneralStatusModel>(deviceGenalSituations), columnArry, devCertiLst));
		}

		return devGenralData;
	}

	private Map<String, Object> getGenalDataOfOrg(String orgPriCode, List<DeviceGeneralStatusModel> devOfOrgLst,
			List<String> columns, List<DeviceCertiStatusModel> devCertiLst) {
		// 每个map对应页面上gird一行记录，key是grid的列的id，value是grid列的值
		Map<String, Object> mapOrgRow = new HashMap<String, Object>();
		// 添加机构名称列
		mapOrgRow.put("orgPriviCode", orgPriCode);
		mapOrgRow.put("orgName", getOrganizationByPriCode(orgPriCode));
		getGenalDataOfStatus(mapOrgRow, devOfOrgLst, columns);
		getGenalDataOfCerti(mapOrgRow, devCertiLst);
		return mapOrgRow;
	}

	private void getGenalDataOfCerti(Map<String, Object> mapOrgRow, List<DeviceCertiStatusModel> devCertiLst) {
		if (devCertiLst == null || devCertiLst.size() == 0) {
			return;
		}
		long totalNeedCerti = devCertiLst.size();
		long hasCerti = 0;
		Stream<DeviceCertiStatusModel> devHasCertiSm = devCertiLst.stream()
				.filter(p -> p.getCertiExpireDate() != null && new Date().before(p.getCertiExpireDate()));
		if (devHasCertiSm != null) {
			hasCerti = devHasCertiSm.count();
		}
		Map<String, Long> mapCerti = new HashMap<String, Long>();
		mapCerti.put("total", totalNeedCerti);
		mapCerti.put("normal", hasCerti);
		mapOrgRow.put("certification", mapCerti);// 保存该设备类型对应的总数和正常数
		if (hasCerti == 0 || totalNeedCerti == 0) {
			mapOrgRow.put("certificationPercent", "0%");
		} else {
			mapOrgRow.put("certificationPercent", divide(hasCerti * 100, totalNeedCerti, 2) + "%");// 保存该设备类型对应的总数和正常数
		}
	}

	private void getGenalDataOfStatus(Map<String, Object> mapOrgRow, List<DeviceGeneralStatusModel> devOfOrgLst,
			List<String> columns) {
		if (devOfOrgLst == null || devOfOrgLst.size() == 0) {
			return;
		}
		// 添加设备类型列
		long devTotal = 0;
		long devNormal = 0;// **在线设备数，状态为：正常、故障、异常的总和
		long devBug = 0;// 故障设备数
		for (int i = 0; i < columns.size(); i++)// 统计这个机构下每种设备类型的总数与正常数
		{
			String devTypeField = columns.get(i);
			long total = 0;
			long normal = 0;// **在线设备数，状态为：正常、故障、异常的总和
			// 过滤该设备类型下有多少设备记录
			List<DeviceGeneralStatusModel> devTypeLst = devOfOrgLst.stream()
					.filter(p -> p.getDeviceType().equals(devTypeField)).collect(Collectors.toList());
			if (devTypeLst != null) {
				// 该设备类型下设备总数
				total = devTypeLst.size();
				// 该设备类型下正常设备数:正常设备个数+故障设备个数+异常设备个数
				Stream<DeviceGeneralStatusModel> devNormalSm = devTypeLst.stream().filter(
						p -> p.getStatusType().equals(1) || p.getStatusType().equals(3) || p.getStatusType().equals(4));
				if (devNormalSm != null) {
					// 该设备类型下正常设备总数
					normal = devNormalSm.count();
				}
			}
			Map<String, Long> mapDevType = new HashMap<String, Long>();
			mapDevType.put("total", total);
			mapDevType.put("normal", normal);
			// 保存该设备类型对应的总数和正常数
			mapOrgRow.put(devTypeField, mapDevType);
			devNormal += normal;
			devTotal += total;
		}
		// 查询故障设备
		Stream<DeviceGeneralStatusModel> bugSm = devOfOrgLst.stream().filter(p -> p.getStatusType().equals(3));
		if (bugSm != null) {
			devBug = bugSm.count();
		}
		// 存放设备总数值
		Map<String, Long> mapDevTotal = new HashMap<String, Long>();
		mapDevTotal.put("total", devTotal);
		mapDevTotal.put("normal", devNormal);// 在线设备数，状态为：正常、故障、异常的总和
		mapDevTotal.put("bug", devBug);// 故障设备数
		mapOrgRow.put("devTotal", mapDevTotal);
		// 存放设备在线率（ 指的是在线设备（正常设备个数+异常设备个数+故障设备个数）/设备总数）
		if (devTotal == 0 || devNormal == 0) {
			mapOrgRow.put("devTotalPercent", "0%");
		} else {
			mapOrgRow.put("devTotalPercent", divide(devNormal * 100, devTotal, 2) + "%");
		}
		// 存放设备故障率
		if (devBug == 0 || devBug == 0) {
			mapOrgRow.put("devBugPercent", "0%");
		} else {
			mapOrgRow.put("devBugPercent", divide(devBug * 100, devTotal, 2) + "%");
		}
	}

	@Override
	@RequestMapping(value = "/getDevGenalSituationIntervalTime")
	public List<Map<String, Object>> getDevGenalSituationIntervalTime(String orgPrivCodes, String columns,
			Boolean isNeetCerti, String orgPrivilegeCode) {
		String orgPriviCode = orgPrivilegeCode;// request.getParameter("currentOrgPrivilegeCode");
		
		// List<DeviceGeneralStatusModel> deviceGeneralStatusModels =
		// getDeviceGeneralStatusLst();
		// 从缓存中获取当前用户下的所有设备
		List<DeviceGeneralStatusModel> deviceGeneralStatusModels = getUserDevLstByPriviCode(orgPriviCode,
				getDeviceGeneralStatusLst());

		List<DeviceCertiStatusModel> deviceCertiStatusModels = null;
		if (isNeetCerti) {
			// 获取当前用户下检定设备
			deviceCertiStatusModels = getUserCertiDevLstByPriviCode(orgPriviCode,
					getDeviceCertiStatusModelsLst());
		}
		List<String> orgPrivCodeArry = Arrays.asList(orgPrivCodes.split(","));
		List<String> columnArry = Arrays.asList(columns.split(","));
		String remark = "refresh";//判断是刷新还是查询操作的标志
		return getDevGenalData(deviceCertiStatusModels, deviceGeneralStatusModels, orgPriviCode, columnArry,
				orgPrivCodeArry,remark);
	}
	
	@Override
	@RequestMapping(value = "/getDevGenalSituationForBigTeam")
	public Map getDevGenalSituationForBigTeam(String orgPrivilegeCode) throws Exception {
		Map mp = new HashMap();
		String orgPriviCode = orgPrivilegeCode;// request.getParameter("currentOrgPrivilegeCode");	
		List<Sys> sysLst = getCurrentUserDevFromDB(orgPriviCode);
		// 从缓存中获取当前用户下的直属设备
		List<DeviceGeneralStatusModel> deviceGenalSituations = getUserDevLstByOrgId(orgPriviCode,
				getDeviceGeneralStatusLst());
		boolean isNeedCerti = false;
		// 获取当前用户下检定设备
		List<DeviceCertiStatusModel> deviceCertiSituations = getUserCertiDevLstByOrgId(orgPriviCode,
				getDeviceCertiStatusModelsLst());
		if (deviceCertiSituations != null && deviceCertiSituations.size() > 0) {
			isNeedCerti = true;
		}
		mp.put("hasCerti", isNeedCerti);
		assemblyDevGenalSituationForBigTeamMap(mp, sysLst, deviceCertiSituations, deviceGenalSituations, orgPriviCode);
		return mp;
	}

	@Override
	@RequestMapping(value = "/getDevGenalSituationForBigTeamInterval")
	public Map getDevGenalSituationForBigTeamInterval(Boolean isNeetCerti, String orgPrivilegeCode)
			throws Exception {
		Map mp = new HashMap();
		String orgPriviCode = orgPrivilegeCode;// request.getParameter("currentOrgPrivilegeCode");
		//跟据机构权限编码查询机构信息获取机构ID
		String orgId = "";
		OrgCriteria orgCriteria = new OrgCriteria();
		orgCriteria.orgPrivCode = orgPriviCode;
		List<Organization>  list = dutyService.findOrganizations(orgCriteria);
		if(list != null && list.size() != 0){
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).orgPrivilegeCode.equals(orgPriviCode)){
					orgId = list.get(i).getIdentityId();
				}
			}
		}
		List<Sys> sysLst = getCurrentUserDevFromDB(orgId);
		// 从缓存中获取当前用户下的所有设备
		List<DeviceGeneralStatusModel> deviceGenalSituations = getUserDevLstByPriviCode(orgPriviCode,
				getDeviceGeneralStatusLst());
		List<DeviceCertiStatusModel> deviceCertiSituations = null;
		if (isNeetCerti == true) {
			// 获取当前用户下检定设备
			deviceCertiSituations = getUserCertiDevLstByPriviCode(orgPriviCode, getDeviceCertiStatusModelsLst());
		}
		assemblyDevGenalSituationForBigTeamMap(mp, sysLst, deviceCertiSituations, deviceGenalSituations, orgPriviCode);
		return mp;
	}

	private void assemblyDevGenalSituationForBigTeamMap(Map mp, List<Sys> sysLst,
			List<DeviceCertiStatusModel> devCertiLst, List<DeviceGeneralStatusModel> deviceGenalSituations,
			String orgPriviCode) {
		Map devGenalTempMp = getDevGenalData(sysLst, devCertiLst, deviceGenalSituations, orgPriviCode);
		mp.put("devForTypeNum", devGenalTempMp.get("devForTypeNum"));
		mp.put("forGrdData", devGenalTempMp.get("devGenal"));
		mp.put("onlineNumTotal", devGenalTempMp.get("onlineNumTotal"));
		mp.put("offlineNumTotal", devGenalTempMp.get("offlineNumTotal"));
		mp.put("bugNumTotal", devGenalTempMp.get("bugNumTotal"));
		mp.put("hasCertiNumTotal", devGenalTempMp.get("hasCertiNumTotal"));
		mp.put("notCertiNumTotal", devGenalTempMp.get("notCertiNumTotal"));
	}
	
	//查询该机构下的启用设备
	private List<Sys> getCurrentUserDevFromDB(String orgPriviCode) {
		SystemCriteria criteria = new SystemCriteria();
		criteria.useStatusFlag = SysCodeConstant.USE_STATUS_USE;// 查询启用的设备
		//跟据机构权限编码查询机构信息获取机构ID
		OrgCriteria orgCriteria = new OrgCriteria();
		orgCriteria.orgPrivCode = orgPriviCode;
		List<Organization>  list = dutyService.findOrganizations(orgCriteria);
		if(list != null && list.size() != 0){
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).orgPrivilegeCode.equals(orgPriviCode)){
					criteria.orgId = list.get(i).getIdentityId();
				}
			}
		}
		List<Sys> sysLst = systemService.findSys(criteria);
		return sysLst;
	}

	private Map getDevGenalData(List<Sys> sysLst, List<DeviceCertiStatusModel> devCertiLst,
			List<DeviceGeneralStatusModel> deviceGenalSituations, String orgPriviCode) {

		Map mpRes = new HashMap();
		List<Map<String, Object>> devGenralData = new ArrayList<Map<String, Object>>();
		Map<String, Integer> devForTypeNum = new HashMap<String, Integer>();

		if (deviceGenalSituations == null || deviceGenalSituations.size() == 0) {
			return mpRes;
		}
		// 按照设备类型进行分组
		Map<String, List<DeviceGeneralStatusModel>> devTypeGrp = deviceGenalSituations.stream()
				.collect(Collectors.groupingBy(DeviceGeneralStatusModel::getDeviceType));
		long allTotalNum = deviceGenalSituations.size();
		long onlineNumTotal = 0;
		long offlineNumTotal = 0;
		long bugNumTotal = 0;
		long hasCertiNumTotal = 0;
		long notCertiNumTotal = 0;
		if (devTypeGrp != null) {
			Set<String> devTypes = devTypeGrp.keySet();
			// 遍历每种设备类型
			for (Iterator<String> iterator = devTypes.iterator(); iterator.hasNext();) {
				String devType = (String) iterator.next();
				List<DeviceGeneralStatusModel> devLst = devTypeGrp.get(devType);
				long devTypeNum = devLst.size();
				// 存储每种设备类型有多少个设备，+1表示要增加一行总计行
				devForTypeNum.put(devType, devLst.size() + 1);
				long onlineNum = 0;
				long bugNum = 0;
				long hasCertiNum = 0;
				long notCertiNum = 0;
				long offlineNum = 0;
				// 对该设备类型下的每一个设备按照界面要求进行组装
				for (int i = 0; i < devLst.size(); i++) {
					DeviceGeneralStatusModel devMd = devLst.get(i);
					Sys currentSys = null;
					List<Sys> currentSysTemp = sysLst.stream().filter(p -> p.getDeviceId().equals(devMd.getDeviceId()))
							.collect(Collectors.toList());
					if (currentSysTemp != null && currentSysTemp.size() > 0) {
						currentSys = currentSysTemp.get(0);
					}

					DeviceCertiStatusModel currentDeviceCertiStatusModel = null;
					if (devCertiLst != null) {
						List<DeviceCertiStatusModel> currentDeviceCertiStatusModelTemp = devCertiLst.stream()
								.filter(p -> p.getDeviceId().equals(devMd.getDeviceId())).collect(Collectors.toList());
						if (currentDeviceCertiStatusModelTemp != null && currentDeviceCertiStatusModelTemp.size() > 0) {
							currentDeviceCertiStatusModel = currentDeviceCertiStatusModelTemp.get(0);
						}
					}
					Map<String, Object> devMp = getDeviceGenal(currentSys, devMd, currentDeviceCertiStatusModel);
					devGenralData.add(devMp);
					String online = (String) devMp.get("online");
					if (online.equals("1")) {
						onlineNum++;
					} else {
						offlineNum++;
					}
					String bug = (String) devMp.get("bug");
					if (bug.equals("0")) {
						bugNum++;
					}
					String certi = (String) devMp.get("certi");
					if (certi.equals("1")) {
						hasCertiNum++;
					} else if (certi.equals("0")) {
						notCertiNum++;
					}
				}
				// 对该设备类型增加一行【总计】
				devGenralData.add(getDeviceTypeTotal(devType, devTypeNum, onlineNum, hasCertiNum, notCertiNum, bugNum));
				onlineNumTotal += onlineNum;
				offlineNumTotal += offlineNum;
				bugNumTotal += bugNum;
				hasCertiNumTotal += hasCertiNum;
				notCertiNumTotal += notCertiNum;
			}
			devGenralData.add(getDeviceTypeAllTotal(allTotalNum, onlineNumTotal, hasCertiNumTotal, notCertiNumTotal,
					bugNumTotal));
		}
		mpRes.put("devForTypeNum", devForTypeNum);
		mpRes.put("devGenal", devGenralData);
		mpRes.put("onlineNumTotal", onlineNumTotal);
		mpRes.put("offlineNumTotal", offlineNumTotal);
		mpRes.put("bugNumTotal", bugNumTotal);
		mpRes.put("hasCertiNumTotal", hasCertiNumTotal);
		mpRes.put("notCertiNumTotal", notCertiNumTotal);
		return mpRes;
	}

	private Map<String, Object> getDeviceGenal(Sys sys, DeviceGeneralStatusModel devMd,
			DeviceCertiStatusModel devCertiMd) {
		Map<String, Object> devMp = new HashMap<String, Object>();
		String devType = devMd.getDeviceType();
		devMp.put("devType", devType);
		String devTypeName = getCodeName(SysCodeConstant.DEVICE_TYPE, devType);
		devMp.put("devTypeName", devTypeName);
		String devName = devMd.getDeviceId();
		if (sys != null) {
			devName = sys.getDeviceName();
		}
		devMp.put("devName", devName);
		String bugName = "正常";
		String bug = "1";
		if (devMd.getStatusType().equals(3))// 设备状态3表示故障
		{
			bugName = "故障";
			bug = "0";
		}
		String onlineName = "在线";
		String online = "1";
		if (devMd.getStatusType().equals(2))// 设备状态2表示离线
		{
			onlineName = "离线";
			online = "0";
			// 离线的设备故障情况未知
			bugName = "未知";
			bug = "-1";
		}
		devMp.put("online", online);
		devMp.put("onlineName", onlineName);
		devMp.put("bug", bug);
		devMp.put("bugName", bugName);

		String certiName = "-";
		String certi = "-1";
		if (devCertiMd != null) {
			certiName = "未检定";
			certi = "0";
			Date certiDate = devCertiMd.getCertiExpireDate();
			if (certiDate != null && new Date().before(certiDate)) {
				certiName = "已检定";
				certi = "1";
			}
		}
		devMp.put("certi", certi);
		devMp.put("certiName", certiName);
		return devMp;
	}

	private Map<String, Object> getDeviceTypeAllTotal(long total, long onlineNum, long hasCertiNum, Long notCertiNum,
			long bugNum) {
		Map<String, Object> devMp = new HashMap<String, Object>();
		createTotalRow(devMp, total, onlineNum, hasCertiNum, notCertiNum, bugNum);
		devMp.put("isAllTotalRow", "1");
		return devMp;
	}

	private String getCodeName(String codeType, String codeNo) {
		String codeName = codeNo;
		Code code = null;
		try {
			code = sysCodeService.codeOfId(codeType, codeNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (code != null) {
			codeName = code.codeName;
		}
		return codeName;
	}

	private Map<String, Object> getDeviceTypeTotal(String devType, long total, long onlineNum, long hasCertiNum,
			Long notCertiNum, long bugNum) {
		Map<String, Object> devMp = new HashMap<String, Object>();
		devMp.put("devType", devType);
		String devTypeName = getCodeName(SysCodeConstant.DEVICE_TYPE, devType);
		devMp.put("devTypeName", devTypeName);
		createTotalRow(devMp, total, onlineNum, hasCertiNum, notCertiNum, bugNum);
		devMp.put("isTotalRow", "1");
		return devMp;
	}

	private void createTotalRow(Map devMp, long total, long onlineNum, long hasCertiNum, Long notCertiNum,
			long bugNum) {
		devMp.put("devName", "总计");
		devMp.put("onlineName", divide(onlineNum * 100, total, 2) + "%");
		devMp.put("bugName", divide(bugNum * 100, total, 2) + "%");
		long certiTotal = hasCertiNum + notCertiNum;
		if (certiTotal == 0) {
			devMp.put("certiName", "-");
		} else {
			devMp.put("certiName", divide(hasCertiNum * 100, certiTotal, 2) + "%");
		}
	}

	/**
	 * 
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	 * 
	 * 定精度，以后的数字四舍五入。舍入模式采用BigDecimal.ROUND_HALF_EVEN舍入模式
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @param scale
	 *            表示需要精确到小数点以后几位
	 * 
	 * @param round_mode
	 *            表示用户指定的舍入模式
	 * 
	 * @return 两个参数的商
	 * 
	 */

	public static String divide(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_EVEN).toString();
	}
}
