/**
 * @Title: MapRoadService.java
 * @Package cy.its.trafficSituation.domain.service.impl
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月13日 上午9:22:18
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

import cy.its.trafficSituation.domain.model.MapRoad;
import cy.its.trafficSituation.domain.repository.IMapRoadRepository;
import cy.its.trafficSituation.domain.service.IMapRoadService;

@Service
public class MapRoadService implements IMapRoadService {
	@Autowired
	IMapRoadRepository mapRoadRepository;
	/*
	  * <p>Title: selectAll</p>
	  * <p>Description: </p>
	  * @param map
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IMapRoadService#selectAll(java.util.Map)
	  */

	@Override
	public List<MapRoad> selectAll(Map map) {
		return mapRoadRepository.selectAllRoad(map);
	}

	/*
	  * <p>Title: selectById</p>
	  * <p>Description: </p>
	  * @param cyid
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IMapRoadService#selectById(java.lang.String)
	  */

	@Override
	public MapRoad selectById(String cyid) {
		return mapRoadRepository.selectByPrimaryKey(cyid);
	}

	/*
	  * <p>Title: selectByRegionalId</p>
	  * <p>Description: </p>
	  * @param regionalId
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IMapRoadService#selectByRegionalId(java.lang.String)
	  */
	
	
	@Override
	public List<MapRoad> selectByRegionalId(String regionalId) {
		return mapRoadRepository.selectByRegionalId(regionalId);
	}

	/*
	  * <p>Title: selectRoadCount</p>
	  * <p>Description: </p>
	  * @return
	  * @see cy.its.trafficSituation.domain.service.IMapRoadService#selectRoadCount()
	  */
	
	
	@Override
	public int selectRoadCount(Map map) {
		return mapRoadRepository.selectRoadCount(map);
	}

}
