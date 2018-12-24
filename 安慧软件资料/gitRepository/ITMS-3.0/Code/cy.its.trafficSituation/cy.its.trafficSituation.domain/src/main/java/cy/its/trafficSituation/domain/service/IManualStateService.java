/**
 * @Title: IManualStateService.java
 * @Package cy.its.trafficSituation.domain.service
 * @Description: 人工干预道路通行状态领域层接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月18日 上午9:32:16
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.domain.service;

import cy.its.trafficSituation.domain.model.TrafficManualState;

public interface IManualStateService {
	/**
	 * @throws Exception 
	 * 
	  * @Title: selectById
	  * @Description: 查
	  * @param @param id
	  * @param @return    设定文件
	  * @return TrafficManualState    返回类型
	  * @throws
	 */
	TrafficManualState selectById(String id) throws Exception;
	/**
	 * 
	  * @Title: save
	  * @Description: 增
	  * @param @param manualState
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	String save(TrafficManualState manualState);
	/**
	 * 
	  * @Title: delete
	  * @Description: 删
	  * @param @param id
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int delete(String id);
	/**
	 * 
	  * @Title: update
	  * @Description: 改
	  * @param @param manualState
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int update(TrafficManualState manualState);
	/**
	 * 
	  * @Title: selectByRoadId
	  * @Description: 根据roadId查询人工干预状态
	  * @param @param roadId
	  * @param @return    设定文件
	  * @return TrafficManualState    返回类型
	  * @throws
	 */
	TrafficManualState selectByRoadId(String roadId);
}
