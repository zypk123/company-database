/**
 * @Title: ITrafficStateRepository.java
 * @Package cy.its.trafficSituation.domain.repository
 * @Description: 交通状态基础设施层接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月28日 下午7:06:08
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.domain.repository;

import cy.its.com.domain.IRepository;
import cy.its.trafficSituation.domain.model.TrafficState;

public interface ITrafficStateRepository extends IRepository<TrafficState> {
	
	TrafficState selectByRoadSectionId(String id);
}
