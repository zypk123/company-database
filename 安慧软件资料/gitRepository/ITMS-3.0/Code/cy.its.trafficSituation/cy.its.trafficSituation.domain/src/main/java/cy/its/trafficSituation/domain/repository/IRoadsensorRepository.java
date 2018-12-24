/**
 * @Title: IRoadsensorRepository.java
 * @Package cy.its.trafficSituation.domain.repository
 * @Description: 路感repository接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月28日 下午5:14:28
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
import cy.its.trafficSituation.domain.model.TrafficRoadsensor;

public interface IRoadsensorRepository extends IRepository<TrafficRoadsensor> {
	List<TrafficRoadsensor> selectRoadsensors(Map<String,Object> map);

	List<TrafficRoadsensor> select2HourRoadsensors(Map<String, Object> map);
}
