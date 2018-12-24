/**
 * @Title: IEventImageRepository.java
 * @Package cy.its.trafficSituation.domain.repository
 * @Description: 交通事件影像领域图层接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月28日 下午8:54:46
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.domain.repository;

import cy.its.com.domain.IRepository;
import cy.its.trafficSituation.domain.model.TrafficEventImage;

public interface IEventImageRepository extends IRepository<TrafficEventImage>{
	/**
	  * @Title: selectEventImageByAlarmId
	  * @Description: 根据事件预警ID查询影像
	  * @param @param id
	  * @param @return    设定文件
	  * @return TrafficEventImage    返回类型
	  * @throws
	 */
	TrafficEventImage selectEventImageByAlarmId(String id);	
}
