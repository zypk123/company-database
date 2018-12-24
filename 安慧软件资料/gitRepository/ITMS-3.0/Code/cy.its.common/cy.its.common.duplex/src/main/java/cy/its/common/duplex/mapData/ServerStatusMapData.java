/**
 * @Title: ServerStatusMapData.java
 * @Package cy.its.common.duplex.mapData
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年12月28日 下午5:10:47
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.common.duplex.mapData;

import java.util.Collection;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cy.its.platform.common.model.DeviceGeneralStatusModel;
import cy.its.platform.common.utils.RedisPoolUtil;
import cy.its.service.common.rabbitmqClient.MQGateWay;

/**
 * @ClassName: ServerStatusMapData
 * @Description: 服务器缓存接收类
 * @author chenzhiying
 * @date 2015年12月28日 下午5:10:47
 *
 */

public class ServerStatusMapData {
	/*
	 * 发送MQ信息
	 */
	public static void sendMqMessage() {
		MQGateWay.publish("its_query_request.server_status_result", "0");
	}

	public static void load(String objList) {
		JSONArray array = JSONArray.parseArray(objList);
		synchronized (ServerStatusMapData.class) {
			RedisPoolUtil.del("serverStatus");
			for (Object obj : array) {
				try {
					JSONObject temp = (JSONObject) obj;
					RedisPoolUtil.hset("serverStatus", temp.getString("serverId"), JSONObject.toJSONString(temp));
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void updateServerStatus(JSONObject obj) {
		synchronized (ServerStatusMapData.class) {
			// 判断是否已经缓存了该服务器，如果有这更新，没有则丢弃
			if (RedisPoolUtil.hexists("serverStatus", obj.getString("serverId"))) {
				RedisPoolUtil.hset("serverStatus", obj.getString("serverId"), JSONObject.toJSONString(obj));
			}
		}
	}

}
