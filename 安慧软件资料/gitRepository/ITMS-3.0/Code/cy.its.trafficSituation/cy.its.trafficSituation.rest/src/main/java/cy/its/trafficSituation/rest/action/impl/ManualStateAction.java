/**
 * @Title: ManualStateAction.java
 * @Package cy.its.trafficSituation.rest.action.impl
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月18日 下午5:15:32
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.action.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cy.its.com.util.StringUtil;
import cy.its.platform.common.utils.RedisPoolUtil;
import cy.its.trafficSituation.domain.model.TrafficManualState;
import cy.its.trafficSituation.domain.service.IManualStateService;
import cy.its.trafficSituation.rest.action.IManualStateAction;
import cy.its.trafficSituation.rest.dto.ManualStateDto;


@RestController
@RequestMapping("/trafficSituation/manualState")
public class ManualStateAction implements IManualStateAction {
	
	@Autowired
	IManualStateService manualStateService;
	/*
	  * <p>Title: update</p>
	  * <p>Description: </p>
	  * @param dto
	  * @return
	  * @see cy.its.trafficSituation.rest.action.IManualStateAction#update(cy.its.trafficSituation.rest.dto.ManualStateDto)
	  */
	
	
	@Override
	@RequestMapping("/update")
	public int update(ManualStateDto dto) throws ParseException {
		if(dto.getStartTime()==null || dto.getEndTime()==null){
			return 0;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String id=dto.getStateControlId();
		//注入redis的键值
		String regionalId="ms:"+dto.getRegionalId();
		//注入redis的内容
		JSONObject   obj = new JSONObject();
		obj.put("trafficState", dto.getControlledState());
		obj.put("startTime", dto.getStartTime());
		obj.put("endTime", dto.getEndTime());
		obj.put("regionalId", dto.getRegionalId());
		//有效时间单位是秒
		int seconds=(int)((sdf.parse(dto.getEndTime()).getTime()-(new Date()).getTime())/1000);
		
		int count=0;
		if(StringUtil.isNullOrEmpty(id)){			
			//插入人工干预路况到redis中
			if(RedisPoolUtil.exists(regionalId)){
				RedisPoolUtil.del(regionalId);
			}			
			RedisPoolUtil.putExpire(regionalId, obj.toJSONString(), seconds);
			//插入人工干预路况到数据库中
			manualStateService.save(dto.parseToTraffic());
			count++;
		}
		else{
			//插入人工干预路况到redis中
			if(RedisPoolUtil.exists(regionalId)){
				RedisPoolUtil.del(regionalId);
				RedisPoolUtil.putExpire(regionalId, obj.toJSONString(), seconds);
			}
			else{
				RedisPoolUtil.putExpire(regionalId, obj.toJSONString(), seconds);
			}
			//更新人工干预路况
			manualStateService.update(dto.parseToTraffic());
			count++;
		}
		return count;
	}

	/*
	  * <p>Title: selectByRoadId</p>
	  * <p>Description: </p>
	  * @param roadId
	  * @return
	  * @see cy.its.trafficSituation.rest.action.IManualStateAction#selectByRoadId(java.lang.String)
	  */
	@Override
	@RequestMapping("/selectByRoadId")
	public ManualStateDto selectByRoadId(String roadId) {
		TrafficManualState state=manualStateService.selectByRoadId(roadId);
		if(state!=null){
			return new ManualStateDto(state);
		}
		else{
			return new ManualStateDto();
		}
	}

}
