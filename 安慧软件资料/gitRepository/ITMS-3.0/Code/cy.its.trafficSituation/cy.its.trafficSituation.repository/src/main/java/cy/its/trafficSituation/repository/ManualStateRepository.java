/**
 * @Title: ManualState.java
 * @Package cy.its.trafficSituation.repository
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月18日 上午9:21:41
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.trafficSituation.domain.model.TrafficManualState;
import cy.its.trafficSituation.domain.repository.IManualStateRepository;
import cy.its.trafficSituation.mybatis.client.TrafficManualStateMapper;

@Service
public class ManualStateRepository implements IManualStateRepository {
	@Autowired
	TrafficManualStateMapper trafficManualStateMapper;
	/*
	  * <p>Title: aggregateOfId</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @throws Exception
	  * @see cy.its.com.domain.IRepository#aggregateOfId(java.lang.String)
	  */

	@Override
	public TrafficManualState aggregateOfId(String id) throws Exception {
		return trafficManualStateMapper.selectByPrimaryKey(id);
	}

	/*
	  * <p>Title: save</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.com.domain.IRepository#save(java.lang.Object)
	  */

	@Override
	public String save(TrafficManualState obj) {
		obj.setStateControlId(StringUtil.generateUUID());
		trafficManualStateMapper.insertSelective(obj);
		return obj.getStateControlId();
	}

	/*
	  * <p>Title: delete</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.com.domain.IRepository#delete(java.lang.String)
	  */

	@Override
	public int delete(String id) {
		return trafficManualStateMapper.deleteByPrimaryKey(id);
	}

	/*
	  * <p>Title: update</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.com.domain.IRepository#update(java.lang.Object)
	  */

	@Override
	public int update(TrafficManualState obj) {
		return trafficManualStateMapper.updateByPrimaryKeySelective(obj);
	}

	/*
	  * <p>Title: selectByRoadId</p>
	  * <p>Description:根据页面时间和路段Id查询 </p>
	  * @param roadId
	  * @return
	  * @see cy.its.trafficSituation.domain.repository.IManualStateRepository#selectByRoadId(java.lang.String)
	  */
	@Override
	public TrafficManualState selectByRoadId(String roadId) {
		Map map=new HashMap<>();
		map.put("roadId", roadId);
		map.put("sysTime", new Date());
		return trafficManualStateMapper.selectByRoadId(map);
	}

}
