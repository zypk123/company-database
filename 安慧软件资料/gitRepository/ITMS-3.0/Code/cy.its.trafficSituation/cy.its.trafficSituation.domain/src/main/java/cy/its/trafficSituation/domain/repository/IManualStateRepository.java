/**
 * @Title: IManualStateRepository.java
 * @Package cy.its.trafficSituation.domain.repository
 * @Description: 人工干预道路通行状态基础设施层接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月18日 上午9:19:45
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.domain.repository;

import cy.its.com.domain.IRepository;
import cy.its.trafficSituation.domain.model.TrafficManualState;

public interface IManualStateRepository extends IRepository<TrafficManualState> {
	TrafficManualState selectByRoadId(String roadId);
}
