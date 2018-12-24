/**
 * @Title: IRoadSectionRepository.java
 * @Package cy.its.trafficSituation.domain.repository
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月13日 上午9:10:18
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.domain.repository;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.MapRoad;

public interface IMapRoadRepository {
	List<MapRoad> selectAllRoad(Map map);
	MapRoad selectByPrimaryKey(String cyid);
	List<MapRoad> selectByRegionalId(String regionalId);
	int selectRoadCount(Map map);
}
