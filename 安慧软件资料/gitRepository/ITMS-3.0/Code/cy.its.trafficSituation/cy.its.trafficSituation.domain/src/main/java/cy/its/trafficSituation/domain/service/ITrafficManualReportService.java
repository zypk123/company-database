/**
 * @Title: ITrafficManualReportService.java
 * @Package cy.its.trafficSituation.domain.service
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月28日 下午3:43:01
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.domain.service;

import cy.its.trafficSituation.domain.model.TrafficManualReport;

/**
  * @ClassName: ITrafficManualReportService
  * @Description: 人工上报交通事件领域层接口
  * @author gyf guanyf@cychina.cn
  * @date 2015年10月28日 下午3:43:01
  *
  */

public interface ITrafficManualReportService {
	/**
	 * @throws Exception 
	 * 
	  * @Title: selectById
	  * @Description: 根据ID查找人工上报交通事件
	  * @param @param id
	  * @param @return    设定文件
	  * @return TrafficManualReport    返回类型
	  * @throws
	 */
	TrafficManualReport selectById(String id) throws Exception;
	
	/**
	  * @Title: save
	  * @Description: 增加人工上报交通事件
	  * @param @param trafficManualReport
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	String save(TrafficManualReport trafficManualReport);
	/**
	  * @Title: delete
	  * @Description: 根据ID删除
	  * @param @param id
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int delete(String id);
	/**
	  * @Title: update
	  * @Description: 更新
	  * @param @param trafficManualReport
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int update(TrafficManualReport trafficManualReport);
}
