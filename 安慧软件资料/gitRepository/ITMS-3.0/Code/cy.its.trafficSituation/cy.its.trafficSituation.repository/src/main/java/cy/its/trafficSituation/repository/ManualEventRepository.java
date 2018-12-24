/**
 * @Title: ManualEventRepository.java
 * @Package cy.its.trafficSituation.repository
 * @Description: 人工登记事件基础设施层实现类
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月10日 下午2:55:14
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.trafficSituation.domain.model.TrafficManualEvent;
import cy.its.trafficSituation.domain.repository.IManualEventRepository;
import cy.its.trafficSituation.mybatis.client.TrafficManualEventMapper;

@Service
public class ManualEventRepository implements IManualEventRepository {

	@Autowired
	TrafficManualEventMapper trafficManualEventMapper;
	/*
	  * <p>Title: aggregateOfId</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @throws Exception
	  * @see cy.its.com.domain.IRepository#aggregateOfId(java.lang.String)
	  */

	@Override
	public TrafficManualEvent aggregateOfId(String id) throws Exception {
		return trafficManualEventMapper.selectByPrimaryKey(id);
	}

	/*
	  * <p>Title: save</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.com.domain.IRepository#save(java.lang.Object)
	  */

	@Override
	public String save(TrafficManualEvent obj) {
		obj.setRoadEventId(StringUtil.generateUUID());
		trafficManualEventMapper.insertSelective(obj);
		return obj.getRoadEventId();
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
		return trafficManualEventMapper.deleteByPrimaryKey(id);
	}

	/*
	  * <p>Title: update</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.com.domain.IRepository#update(java.lang.Object)
	  */

	@Override
	public int update(TrafficManualEvent obj) {
		return trafficManualEventMapper.updateByPrimaryKeySelective(obj);
	}

	/*
	  * <p>Title: selectAll</p>
	  * <p>Description: </p>
	  * @param map
	  * @return
	  * @see cy.its.trafficSituation.domain.repository.IManualEventRepository#selectAll(java.util.Map)
	  */

	@Override
	public List<TrafficManualEvent> selectAll(Map<String, Object> map) {
		return trafficManualEventMapper.selectAll(map);
	}

}
