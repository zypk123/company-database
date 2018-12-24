/**
 * @Title: ManualEventService.java
 * @Package cy.its.trafficSituation.domain.service.impl
 * @Description: 人工登记事件领域层实现类
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月10日 下午3:22:57
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.trafficSituation.domain.model.TrafficManualEvent;
import cy.its.trafficSituation.domain.repository.IManualEventRepository;
import cy.its.trafficSituation.domain.service.IManualEventService;

@Service
public class ManualEventService implements IManualEventService {
	@Autowired
	IManualEventRepository manualEventRepository;

	/*
	  * <p>Title: selectById</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualEventService#selectById(java.lang.String)
	  */

	@Override
	public TrafficManualEvent selectById(String id) throws Exception {
		return manualEventRepository.aggregateOfId(id);
	}

	/*
	  * <p>Title: save</p>
	  * <p>Description: </p>
	  * @param manualEvent
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualEventService#save(cy.its.trafficSituation.domain.model.TrafficManualEvent)
	  */

	@Override
	public String save(TrafficManualEvent manualEvent) {
		return manualEventRepository.save(manualEvent);
	}

	/*
	  * <p>Title: delete</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualEventService#delete(java.lang.String)
	  */

	@Override
	public int delete(String id) {
		return manualEventRepository.delete(id);
	}

	/*
	  * <p>Title: update</p>
	  * <p>Description: </p>
	  * @param manualEvent
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualEventService#update(cy.its.trafficSituation.domain.model.TrafficManualEvent)
	  */

	@Override
	public int update(TrafficManualEvent manualEvent) {
		return manualEventRepository.update(manualEvent);
	}

	/*
	  * <p>Title: selectAll</p>
	  * <p>Description: </p>
	  * @param map
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualEventService#selectAll(java.util.Map)
	  */

	@Override
	public List<TrafficManualEvent> selectAll(Map<String, Object> map) {
		return manualEventRepository.selectAll(map);
	}

}
