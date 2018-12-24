/**
 * @Title: RoadSectionRepository.java
 * @Package cy.its.trafficSituation.repository
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月13日 上午9:11:11
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

import cy.its.trafficSituation.domain.model.MapRoad;
import cy.its.trafficSituation.domain.repository.IMapRoadRepository;
import cy.its.trafficSituation.mybatis.client.MapRoadMapper;

@Service
public class MapRoadRepository implements IMapRoadRepository {
	@Autowired 
	MapRoadMapper mapRoadMapper;
	/*
	  * <p>Title: selectAllRoad</p>
	  * <p>Description: </p>
	  * @param map
	  * @return
	  * @see cy.its.trafficSituation.domain.repository.IRoadSectionRepository#selectAllRoad(java.util.Map)
	  */

	@Override
	public List<MapRoad> selectAllRoad(Map map) {
		return mapRoadMapper.selectAllRoad(map);
	}

	/*
	  * <p>Title: selectByPrimaryKey</p>
	  * <p>Description: </p>
	  * @param cyid
	  * @return
	  * @see cy.its.trafficSituation.domain.repository.IMapRoadRepository#selectByPrimaryKey(java.lang.String)
	  */
	
	
	@Override
	public MapRoad selectByPrimaryKey(String cyid) {
		return mapRoadMapper.selectByPrimaryKey(cyid);
	}

	/*
	  * <p>Title: selectByRegionalId</p>
	  * <p>Description: </p>
	  * @param regionalId
	  * @return
	  * @see cy.its.trafficSituation.domain.repository.IMapRoadRepository#selectByRegionalId(java.lang.String)
	  */
	
	
	@Override
	public List<MapRoad> selectByRegionalId(String regionalId) {
		return mapRoadMapper.selectByRegionalId(regionalId);
	}

	/*
	  * <p>Title: selectRoadCount</p>
	  * <p>Description: </p>
	  * @return
	  * @see cy.its.trafficSituation.domain.repository.IMapRoadRepository#selectRoadCount()
	  */
	
	
	@Override
	public int selectRoadCount(Map map) {
		return mapRoadMapper.selectRoadCount(map);
	}

}
