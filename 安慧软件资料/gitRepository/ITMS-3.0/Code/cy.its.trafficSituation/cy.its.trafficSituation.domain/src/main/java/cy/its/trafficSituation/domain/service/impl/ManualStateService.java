/**
 * @Title: ManualStateService.java
 * @Package cy.its.trafficSituation.domain.service.impl
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月18日 上午9:41:14
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.trafficSituation.domain.model.TrafficManualState;
import cy.its.trafficSituation.domain.repository.IManualStateRepository;
import cy.its.trafficSituation.domain.service.IManualStateService;

@Service
public class ManualStateService implements IManualStateService {
	@Autowired
	IManualStateRepository manualStateRepository;
	/*
	  * <p>Title: selectById</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualStateService#selectById(java.lang.String)
	  */

	@Override
	public TrafficManualState selectById(String id) throws Exception {
		return manualStateRepository.aggregateOfId(id);
	}

	/*
	  * <p>Title: save</p>
	  * <p>Description: </p>
	  * @param manualState
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualStateService#save(cy.its.trafficSituation.domain.model.TrafficManualState)
	  */

	@Override
	public String save(TrafficManualState manualState) {
		return manualStateRepository.save(manualState);
	}

	/*
	  * <p>Title: delete</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualStateService#delete(java.lang.String)
	  */

	@Override
	public int delete(String id) {
		return manualStateRepository.delete(id);
	}

	/*
	  * <p>Title: update</p>
	  * <p>Description: </p>
	  * @param manualState
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualStateService#update(cy.its.trafficSituation.domain.model.TrafficManualState)
	  */

	@Override
	public int update(TrafficManualState manualState) {
		return manualStateRepository.update(manualState);
	}

	/*
	  * <p>Title: selectByRoadId</p>
	  * <p>Description: </p>
	  * @param roadId
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IManualStateService#selectByRoadId(java.lang.String)
	  */
	
	
	@Override
	public TrafficManualState selectByRoadId(String roadId) {
		return manualStateRepository.selectByRoadId(roadId);
	}

}
