/**
 * @Title: ITrafficVisibilityRepository.java
 * @Package cy.its.trafficSituation.domain.repository
 * @Description: 能见度仪基础设施层接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月28日 下午7:40:52
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

import cy.its.com.domain.IRepository;
import cy.its.trafficSituation.domain.model.TrafficVisibility;

public interface ITrafficVisibilityRepository extends IRepository<TrafficVisibility> {
	List<TrafficVisibility> selectVisibilitys(Map<String, Object> map);

	List<TrafficVisibility> select2HourVisibilitys(Map<String, Object> map);
}
