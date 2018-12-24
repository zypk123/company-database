/**
 * @Title: IMapRoadService.java
 * @Package cy.its.trafficSituation.domain.service
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月13日 上午9:19:38
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.MapRoad;

public interface IMapRoadService {
	List<MapRoad> selectAll(Map map);
	MapRoad selectById(String cyid);
	List<MapRoad> selectByRegionalId(String regionalId);
	int selectRoadCount(Map map);
}
