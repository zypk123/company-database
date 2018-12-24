/**
 * @Title: ISectionFlowRepository.java
 * @Package cy.its.trafficSituation.domain.repository
 * @Description: 断面流量基础设施层接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月28日 下午6:35:59
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
import cy.its.trafficSituation.domain.model.TrafficSectionFlow;

public interface ISectionFlowRepository extends IRepository<TrafficSectionFlow> {
	/**
	  * @Title: selectRegionFlows
	  * @Description: 根据条件查找断面流量列表
	  * @param @param map
	  * @param @return    设定文件
	  * @return List<TrafficRegionFlow>    返回类型
	  * @throws
	 */	
	List<TrafficSectionFlow> selectSctionFlows(Map<String,Object> map); 
	TrafficSectionFlow selectSectionFlowBySectionId(String sectionId);
	List<TrafficSectionFlow> select2HourSectionFlow(Map<String, Object> map);
}
