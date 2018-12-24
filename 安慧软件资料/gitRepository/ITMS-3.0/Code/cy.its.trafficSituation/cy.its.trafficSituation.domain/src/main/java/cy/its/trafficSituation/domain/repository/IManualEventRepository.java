/**
 * @Title: IManualEventRepository.java
 * @Package cy.its.trafficSituation.domain.repository
 * @Description: 人工登记事件基础设施层接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月10日 下午2:47:03
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
import cy.its.trafficSituation.domain.model.TrafficManualEvent;

public interface IManualEventRepository extends IRepository<TrafficManualEvent> {
	/**
	 * 
	  * @Title: selectAll
	  * @Description: 按条件查找人工登记事件列表
	  * @param @param map
	  * @param @return    设定文件
	  * @return List<TrafficManualEvent>    返回类型
	  * @throws
	 */
	List<TrafficManualEvent> selectAll(Map<String, Object> map);
}
