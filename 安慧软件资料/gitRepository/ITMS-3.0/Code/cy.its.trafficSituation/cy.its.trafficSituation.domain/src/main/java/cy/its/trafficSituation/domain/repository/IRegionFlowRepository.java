/**
 * @Title: IRegionFlow.java
 * @Package cy.its.trafficSituation.domain.repository
 * @Description: 区间流量repository接口类
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月28日 下午4:26:16
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

import org.apache.ibatis.annotations.Param;

import cy.its.com.domain.IRepository;
import cy.its.trafficSituation.domain.model.TrafficRegionFlow;


public interface IRegionFlowRepository extends IRepository<TrafficRegionFlow> {
	/**
	  * @Title: selectRegionFlows
	  * @Description: 根据条件查找区间流量列表
	  * @param @param map
	  * @param @return    设定文件
	  * @return List<TrafficRegionFlow>    返回类型
	  * @throws
	 */
	List<TrafficRegionFlow> selectRegionFlows(Map<String,Object> map); 
	
	List<TrafficRegionFlow> selectByRegionId(String regionalId);
	
	TrafficRegionFlow selectLastFlowByRegionId(String regionalId);

	List<TrafficRegionFlow> select2HourRegionFlow(Map<String, Object> map);
}
