/**
 * @Title: DeviceHomeMonitorScoket.java
 * @Package cy.its.common.duplex.service
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年12月23日 下午1:50:15
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.common.duplex.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.tools.ant.taskdefs.optional.ccm.Continuus;
import org.springframework.expression.spel.ast.ValueRef.NullValueRef;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cy.its.common.duplex.domain.WebscoketSessionBean;
import cy.its.common.duplex.mapData.DeviceStatusMapData;
import cy.its.common.duplex.rabbitMq.IMqReceiver;
import cy.its.common.duplex.rabbitMq.IWebscoketReceiver;
import cy.its.common.duplex.rabbitMq.MQEntrance;
import cy.its.common.duplex.service.impl.WebscoketServiceImplA;
import cy.its.platform.common.model.DeviceCertiStatusModel;
import cy.its.platform.common.model.DeviceGeneralStatusModel;
import cy.its.platform.common.utils.Log4jFactoryProxy;
import cy.its.platform.common.utils.RedisPoolUtil;

/**
 * @ClassName: DeviceHomeMonitorScoket
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年12月23日 下午1:50:15
 *
 */

public class DeviceHomeMonitorScoket extends WebscoketServiceImplA implements IWebscoketReceiver {

	/*
	 * 日志
	 */
	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(DeviceMonitorScoket.class);

	private final String routingKey = "its_status_result_for_upgrade";

	/**
	 * 两个KEY可以是同一个
	 */
	private final String monitorKey = "deviceHomeMonitor";

	private DeviceHomeMonitorScoket() {
		IMqReceiver receive = MQEntrance.getIreceivermap().get(routingKey);
		receive.addReciveObject(this);
	}
	/*
	 * <p>Title: receive</p> <p>Description: </p>
	 * 
	 * @param jsonObject
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.common.duplex.rabbitMq.IWebscoketReceiver#receive(com.alibaba.
	 * fastjson.JSONObject)
	 */

	@Override
	public Boolean receive(JSONObject jsonObject) {
		// 数据不多可以直接取，不在做缓存
		CopyOnWriteArrayList<WebscoketSessionBean> listbean = maplistbean.get(monitorKey);
		listbean.stream().forEach(wsb -> {
			String userOrgPrivilegeCode = (String) wsb.getParam().get("curOrgPriCode");// wsb.getOrgCode();
			String devOrgPrivilegeCode = jsonObject.getString("orgPrivilegeCode");
			if (devOrgPrivilegeCode.startsWith(userOrgPrivilegeCode)) {// 如果该设备是这个用户所属机构下，则通知客户端更新
				JSONObject obj = new JSONObject();
				obj.put("wbskey", monitorKey);
				// obj.put(monitorKey, jsonObject.toJSONString());
				List<DeviceGeneralStatusModel> deviceGeneralStatusModels = getDeviceGeneralStatusLst();
				List<DeviceCertiStatusModel> deviceCertiStatusModels = getDeviceCertiStatusModelsLst();
				// if (deviceGeneralStatusModels != null &&
				// deviceGeneralStatusModels.size() > 0) {
				List<String> columnArry = (List<String>) wsb.getParam().get("column");
				List<String> orgPriCodeArry = (List<String>) wsb.getParam().get("orgPriCode");
				Map<String, Map<String, Object>> list = getDevGenalData(deviceCertiStatusModels,
						deviceGeneralStatusModels, userOrgPrivilegeCode, columnArry, orgPriCodeArry);
				obj.put(monitorKey, list);
				TextMessage returnMessage = new TextMessage(obj.toJSONString());
				this.sendMessage(wsb, returnMessage);
				// }
			}
		});
		return true;
	}

	private List<DeviceCertiStatusModel> getDeviceCertiStatusModelsLst() {
		List<String> devCertiCll = null;
		synchronized (DeviceHomeMonitorScoket.class) {
			devCertiCll = new ArrayList<String>(RedisPoolUtil.hgetAll("deviceCertiStatus").values());
		}
		if (devCertiCll == null || devCertiCll.size() == 0) {
			return null;
		}
		List<DeviceCertiStatusModel> devCertiLst = devCertiCll.stream()
				.map(p -> JSONObject.parseObject(p, DeviceCertiStatusModel.class)).collect(Collectors.toList());
		return devCertiLst;
	}

	private List<DeviceGeneralStatusModel> getDeviceGeneralStatusLst() {
		List<String> devCll = null;
		synchronized (DeviceHomeMonitorScoket.class) {
			devCll = new ArrayList<String>(RedisPoolUtil.hgetAll("deviceStatus").values());
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
		// if (orgPrivilegeCode.length() >= 4) {
		// deviceStatus.setZhiduiPrivilegeCode(orgPrivilegeCode.substring(0,
		// 4));
		// }
		// if (orgPrivilegeCode.length() >= 6) {
		// deviceStatus.setDaduiPrivilegeCode(orgPrivilegeCode.substring(0, 6));
		// }
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

	private Map<String, Map<String, Object>> getDevGenalData(List<DeviceCertiStatusModel> devCertiLst,
			List<DeviceGeneralStatusModel> deviceGenalSituations, String orgPriviCode, List<String> columnArry,
			List<String> orgPriCodeArry) {
		Map<String, Map<String, Object>> devGenralData = new HashMap<String, Map<String, Object>>();
		if (orgPriCodeArry != null && orgPriCodeArry.size() > 0) {
			for (int i = 0; i < orgPriCodeArry.size(); i++) {
				String curOrgPriCode = orgPriCodeArry.get(i);
				// 获取该机构下的所有设备记录
				List<DeviceGeneralStatusModel> devOfOrgLst = deviceGenalSituations.stream()
						.filter(p -> p.getOrgPrivilegeCode().startsWith(curOrgPriCode)).collect(Collectors.toList());
				List<DeviceCertiStatusModel> devCertiOfOrgLst = devCertiLst.stream()
						.filter(p -> p.getOrgPrivilegeCode().startsWith(curOrgPriCode)).collect(Collectors.toList());
				devGenralData.put(curOrgPriCode,
						getGenalDataOfOrg(curOrgPriCode, devOfOrgLst, columnArry, devCertiOfOrgLst));
			}
		}
		// 最后加上当前用户所在机构
		devGenralData.put(orgPriviCode, getGenalDataOfOrg(orgPriviCode,
				new ArrayList<DeviceGeneralStatusModel>(deviceGenalSituations), columnArry, devCertiLst));
		return devGenralData;
	}

	/**
	 * 获取某一机构的概括信息 getGenalDataOfOrg(获取某一机构的概括信息) @Title:
	 * getGenalDataOfOrg  @param @param
	 * orgPriCode @param @param devOfOrgSm @param @param columns @param @param
	 * needCertification @param @return 设定文件 @return Map
	 * <String,Object> 返回类型 @throws
	 */
	private Map<String, Object> getGenalDataOfOrg(String orgPriCode, List<DeviceGeneralStatusModel> devOfOrgLst,
			List<String> columns, List<DeviceCertiStatusModel> devCertiLst) {
		// 每个map对应页面上gird一行记录，key是grid的列的id，value是grid列的值
		Map<String, Object> mapOrgRow = new HashMap<String, Object>();
		// 添加机构名称列
		mapOrgRow.put("orgPriviCode", orgPriCode);
		getGenalDataOfStatus(mapOrgRow, devOfOrgLst, columns);
		getGenalDataOfCerti(mapOrgRow, devCertiLst);
		// // 添加设备类型列
		// long devTotal = 0;
		// long devNormal = 0;
		// for (int i = 0; i < columns.size(); i++)// 统计这个机构下每种设备类型的总数与正常数
		// {
		// String devTypeField = columns.get(i);
		// long total = 0;
		// long normal = 0;
		// // 过滤该设备类型下有多少设备记录
		// List<DeviceGeneralStatusModel> devTypeLst = devOfOrgLst.stream()
		// .filter(p ->
		// p.getDeviceType().equals(devTypeField)).collect(Collectors.toList());
		// if (devTypeLst != null) {
		// // 该设备类型下设备总数
		// total = devTypeLst.size();
		// // 该设备类型下正常设备数
		// Stream<DeviceGeneralStatusModel> devNormalSm = devTypeLst.stream()
		// .filter(p -> p.getStatusType().equals(1) ||
		// p.getStatusType().equals(4));
		// if (devNormalSm != null) {
		// // 该设备类型下正常设备总数
		// normal = devNormalSm.count();
		// }
		// }
		// Map<String, Long> mapDevType = new HashMap<String, Long>();
		// mapDevType.put("total", total);
		// mapDevType.put("normal", normal);
		// // 保存该设备类型对应的总数和正常数
		// mapOrgRow.put(devTypeField, mapDevType);
		// devNormal += normal;
		// devTotal += total;
		// }
		// // 存放设备总数值
		// Map<String, Long> mapDevTotal = new HashMap<String, Long>();
		// mapDevTotal.put("total", devTotal);
		// mapDevTotal.put("normal", devNormal);
		// mapOrgRow.put("devTotal", mapDevTotal);
		// // 存放设备在线率
		// if (devTotal == 0 || devNormal == 0) {
		// mapOrgRow.put("devTotalPercent", "0%");
		// } else {
		// mapOrgRow.put("devTotalPercent", divide(devNormal * 100, devTotal, 2)
		// + "%");
		// }
		// if (devTotal != devNormal)// 如果正常设备数与总数不一致，说明存在非正常设备
		// {
		// Map<String, Long> unNormalMap = new HashMap<String, Long>();
		// // 查询离线设备
		// Stream<DeviceGeneralStatusModel> offlineSm =
		// devOfOrgLst.stream().filter(p -> p.getStatusType().equals(2));
		// if (offlineSm != null) {
		// long offlineNum = offlineSm.count();
		// if (offlineNum > 0) {
		// unNormalMap.put("离线", offlineNum);
		// }
		// }
		// // 查询故障设备
		// List<DeviceGeneralStatusModel> bugLst = devOfOrgLst.stream().filter(p
		// -> p.getStatusType().equals(3))
		// .collect(Collectors.toList());
		// if (bugLst != null) {
		// long bugNum = bugLst.size();
		// if (bugNum > 0)// 有故障，按照故障分类
		// {
		// Map<Integer, List<DeviceGeneralStatusModel>> bugTypeGrp =
		// bugLst.stream()
		// .collect(Collectors.groupingBy(DeviceGeneralStatusModel::getStatusBugType));
		// Set<Integer> bugTypeGrps = bugTypeGrp.keySet();
		// for (Iterator<Integer> iterator = bugTypeGrps.iterator();
		// iterator.hasNext();) {
		// Integer bugType = (Integer) iterator.next();
		// long bugTypeNum = bugTypeGrp.get(bugType).size();
		// if (bugType == 2) {
		// unNormalMap.put("断电", bugTypeNum);
		// } else if (bugType == 6) {
		// unNormalMap.put("断网", bugTypeNum);
		// } else {
		// unNormalMap.put("相机故障", bugTypeNum);
		// }
		// }
		// }
		// }
		// mapOrgRow.put("unNormal", unNormalMap);
		// } else {
		// mapOrgRow.put("unNormal", "");
		// }
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
		long devNormal = 0;
		for (int i = 0; i < columns.size(); i++)// 统计这个机构下每种设备类型的总数与正常数
		{
			String devTypeField = columns.get(i);
			long total = 0;
			long normal = 0;
			// 过滤该设备类型下有多少设备记录
			List<DeviceGeneralStatusModel> devTypeLst = devOfOrgLst.stream()
					.filter(p -> p.getDeviceType().equals(devTypeField)).collect(Collectors.toList());
			if (devTypeLst != null) {
				// 该设备类型下设备总数
				total = devTypeLst.size();
				// 该设备类型下正常设备数
				Stream<DeviceGeneralStatusModel> devNormalSm = devTypeLst.stream()
						.filter(p -> p.getStatusType().equals(1) || p.getStatusType().equals(4));
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
		// 存放设备总数值
		Map<String, Long> mapDevTotal = new HashMap<String, Long>();
		mapDevTotal.put("total", devTotal);
		mapDevTotal.put("normal", devNormal);
		mapOrgRow.put("devTotal", mapDevTotal);
		// 存放设备在线率
		if (devTotal == 0 || devNormal == 0) {
			mapOrgRow.put("devTotalPercent", "0%");
		} else {
			mapOrgRow.put("devTotalPercent", divide(devNormal * 100, devTotal, 2) + "%");
		}
		if (devTotal != devNormal)// 如果正常设备数与总数不一致，说明存在非正常设备
		{
			Map<String, Long> unNormalMap = new HashMap<String, Long>();
			// 查询离线设备
			Stream<DeviceGeneralStatusModel> offlineSm = devOfOrgLst.stream().filter(p -> p.getStatusType().equals(2));
			if (offlineSm != null) {
				long offlineNum = offlineSm.count();
				if (offlineNum > 0) {
					unNormalMap.put("离线", offlineNum);
				}
			}
			// 查询故障设备
			List<DeviceGeneralStatusModel> bugLst = devOfOrgLst.stream().filter(p -> p.getStatusType().equals(3))
					.collect(Collectors.toList());
			if (bugLst != null) {
				long bugNum = bugLst.size();
				if (bugNum > 0)// 有故障，按照故障分类
				{
					Map<String, List<DeviceGeneralStatusModel>> bugTypeGrp = bugLst.stream()
							.collect(Collectors.groupingBy(DeviceGeneralStatusModel::getStatusBugType));
					Set<String> bugTypeGrps = bugTypeGrp.keySet();
					for (Iterator<String> iterator = bugTypeGrps.iterator(); iterator.hasNext();) {
						String bugType = (String) iterator.next();
						long bugTypeNum = bugTypeGrp.get(bugType).size();
						if (bugType.equals("10005")) {
							unNormalMap.put("断电", bugTypeNum);
						} else if (bugType.equals("10006")) {
							unNormalMap.put("断网", bugTypeNum);
						} else {
							unNormalMap.put("相机故障", bugTypeNum);
						}
					}
				}
			}
			mapOrgRow.put("unNormal", unNormalMap);
		} else {
			mapOrgRow.put("unNormal", "");
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
