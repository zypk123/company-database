/**
 * @Title: DeviceStatusMapData.java
 * @Package cy.its.common.duplex.mapData
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年12月23日 下午2:12:36
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.common.duplex.mapData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cy.its.platform.common.model.DeviceGeneralStatusModel;
import cy.its.platform.common.utils.RedisPoolUtil;
import cy.its.service.common.rabbitmqClient.MQGateWay;

/**
 * @ClassName: DeviceStatusMapData
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年12月23日 下午2:12:37
 *
 */

public class DeviceStatusMapData {
	/*
	 * 发送MQ信息
	 */
	public static void sendMqMessage() {

		MQGateWay.publish("its_query_request.status_result", "0");

	}

	public static Collection<DeviceGeneralStatusModel> getDeviceGeneralStatus() {
//		return statusMap.values();
		return null;
	}

	public static void load(String objList) {
		JSONArray array = JSONArray.parseArray(objList);
		synchronized (DeviceStatusMapData.class) {
			RedisPoolUtil.del("deviceStatus");
			for (Object obj : array) {
				try {
					JSONObject temp = (JSONObject) obj;
					RedisPoolUtil.hset("deviceStatus", temp.getString("deviceId"), JSONObject.toJSONString(temp));
				} catch (Throwable e) {
					e.printStackTrace();
				}

				// JSONArray.parseArray("", DeviceGeneralStatusModel.class);

				// RedisPoolUtil.hget(key, filed);

				// RedisPoolUtil.hgetAll("").values();

				// RedisPoolUtil.hset("deviceStatus",temp.getString("deviceId"),JSONObject.toJSONString(deviceStatus));
			}
		}
	}
 
	

	private static void convertStatusObj(DeviceGeneralStatusModel deviceStatus, JSONObject temp) {
		
//		deviceStatus.setStatusType(temp.getInteger("statusType"));
//		// 解析当前设备的故障类型todo 李军说状态这块要调整，所以
//		int bugType = 9;// 默认设备故障
//		JSONArray arrayStatusDetails = temp.getJSONArray("statusDetails");
//		// 如果是工控机设备，该故障有值
//		if (arrayStatusDetails != null && arrayStatusDetails.size() > 0) {
//			if (arrayStatusDetails.contains(2))// 如果故障列表中有电源故障，则为电源故障
//			{
//				bugType = 2;
//			} else if (arrayStatusDetails.contains(6))// 如果故障列表中有网络故障，则为网络故障
//			{
//				bugType = 6;
//			}
//		} else// 如果是智能相机，该故障有值，具体的故障是存在智能相机的组件中的
//		{
//			arrayStatusDetails = temp.getJSONArray("componentStatus");
//			if (arrayStatusDetails != null) {
//				for (Object status : arrayStatusDetails) {
//					JSONObject tempStatus = (JSONObject) status;
//					if (tempStatus == null) {
//						continue;
//					}
//					JSONArray arrayDetails = tempStatus.getJSONArray("statusDetails");
//					if (arrayDetails.contains(2))// 如果故障列表中有电源故障，则为电源故障
//					{
//						bugType = 2;
//						break;
//					} else if (arrayStatusDetails.contains(6))// 如果故障列表中有网络故障，则为网络故障
//					{
//						bugType = 6;
//						break;
//					}
//				}
//			}
//		}
//		deviceStatus.setStatusBugType(bugType);
	}

	/**
	 * updateDevStatus(更新单个设备状态缓存) @Title: updateDevStatus @Description:
	 * 更新单个设备状态缓存 @param @param obj 设定文件 @return void 返回类型 @throws
	 */
	public static void updateDevStatus(JSONObject obj) {
		synchronized (DeviceStatusMapData.class) {
			//RedisPoolUtil.hget("deviceStatus", obj.getString("deviceId"))
			//判断是否已经缓存了该设备，如果有这更新，没有则丢弃
			if(RedisPoolUtil.hexists("deviceStatus", obj.getString("deviceId")))
			{
				RedisPoolUtil.hset("deviceStatus", obj.getString("deviceId"), JSONObject.toJSONString(obj));
			}
//			DeviceGeneralStatusModel deviceStatus = statusMap.get(obj.getString("deviceId"));
//			convertStatusObj(deviceStatus, obj);
//			statusMap.put(obj.getString("deviceId"), deviceStatus);
		}
	}
}
