/**
 * @Title: IMapRoadAction.java
 * @Package cy.its.trafficSituation.rest.action
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月13日 上午9:46:02
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.action;

import java.util.List;

import cy.its.trafficSituation.rest.dto.MapRoadDto;

public interface IMapRoadAction {
	List<MapRoadDto> selectAll() throws Exception;
	MapRoadDto selectById(String cyid);
}
