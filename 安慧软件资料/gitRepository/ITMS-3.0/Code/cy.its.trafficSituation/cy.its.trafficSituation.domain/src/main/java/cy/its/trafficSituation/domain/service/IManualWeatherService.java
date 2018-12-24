/**
 * @Title: IManualWeatherService.java
 * @Package cy.its.trafficSituation.domain.service
 * @Description: 恶劣天气领域层接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月10日 下午3:19:12
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

import cy.its.trafficSituation.domain.model.TrafficManualWeather;

public interface IManualWeatherService {
	/**
	 * @throws Exception 
	 * 
	  * @Title: selectById
	  * @Description: 查询
	  * @param @param id
	  * @param @return    设定文件
	  * @return TrafficManualWeather    返回类型
	  * @throws
	 */
	TrafficManualWeather selectById(String id) throws Exception;
	/**
	 * 
	  * @Title: save
	  * @Description: 新增
	  * @param @param manualWeather
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	String save(TrafficManualWeather manualWeather);
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
	  * @param @param manualWeather
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int update(TrafficManualWeather manualWeather);
	/**
	 * 
	  * @Title: selectAll
	  * @Description: 查询
	  * @param @return    设定文件
	  * @return List<TrafficManualWeather>    返回类型
	  * @throws
	 */
	List<TrafficManualWeather> selectAll(Map<String, Object> map);
}
