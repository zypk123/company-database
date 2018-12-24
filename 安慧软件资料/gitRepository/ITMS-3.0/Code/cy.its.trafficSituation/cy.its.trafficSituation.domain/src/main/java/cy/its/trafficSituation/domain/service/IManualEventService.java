/**
 * @Title: IManualEventService.java
 * @Package cy.its.trafficSituation.domain.service
 * @Description: 人工记录事件领域层接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月10日 下午3:11:38
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

import cy.its.trafficSituation.domain.model.TrafficManualEvent;

public interface IManualEventService {
	/**
	 * @throws Exception 
	 * 
	  * @Title: selectById
	  * @Description: 查询
	  * @param @param id
	  * @param @return    设定文件
	  * @return TrafficManualEvent    返回类型
	  * @throws
	 */
	TrafficManualEvent selectById(String id) throws Exception;
	/**
	 * 
	  * @Title: save
	  * @Description: 新增
	  * @param @param manualEvent
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	String save(TrafficManualEvent manualEvent);
	/**
	 * 
	  * @Title: delete
	  * @Description: 删除
	  * @param @param id
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int delete(String id);
	/**
	 * 
	  * @Title: update
	  * @Description: 修改
	  * @param @param manualEvent
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int update(TrafficManualEvent manualEvent);
	/**
	 * 
	  * @Title: selectAll
	  * @Description: 查询
	  * @param @return    设定文件
	  * @return List<TrafficManualEvent>    返回类型
	  * @throws
	 */
	List<TrafficManualEvent> selectAll(Map<String, Object> map);
}
