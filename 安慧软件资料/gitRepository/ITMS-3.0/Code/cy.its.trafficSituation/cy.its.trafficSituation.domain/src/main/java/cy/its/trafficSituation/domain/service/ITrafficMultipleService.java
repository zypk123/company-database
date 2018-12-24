/**
 * @Title: ITrafficMultipleService.java
 * @Package cy.its.trafficSituation.domain.service
 * @Description: 综合领域接口
 * @author gyf  guanyf@cychina.cn
 * @date 2015年10月28日 下午8:05:23
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

import cy.its.trafficSituation.domain.model.TrafficEventImage;
import cy.its.trafficSituation.domain.model.TrafficRegionFlow;
import cy.its.trafficSituation.domain.model.TrafficRoadsensor;
import cy.its.trafficSituation.domain.model.TrafficSectionFlow;
import cy.its.trafficSituation.domain.model.TrafficState;
import cy.its.trafficSituation.domain.model.TrafficVisibility;
import cy.its.trafficSituation.domain.model.TrafficWeather;

public interface ITrafficMultipleService {
	/**
	 * @throws Exception 
	  * @Title: selectStateById
	  * @Description: 根据ID查询交通状态
	  * @param @param id
	  * @param @return    设定文件
	  * @return TrafficState    返回类型
	  * @throws
	 */
	TrafficState selectStateById(String id) throws Exception;
	/**
	  * @Title: selectStates
	  * @Description: 根据条件查询交通状态列表
	  * @param @param map
	  * @param @return    设定文件
	  * @return List<TrafficState>    返回类型
	  * @throws
	 */
	List<TrafficState> selectStates(Map<String,Object> map);
	/**
	 * @throws Exception 
	  * @Title: selectSectionFlowById
	  * @Description: 根据ID查询断面流量
	  * @param @param id
	  * @param @return    设定文件
	  * @return TrafficSectionFlow    返回类型
	  * @throws
	 */
	TrafficSectionFlow selectSectionFlowById(String id) throws Exception;
	/**
	  * @Title: selectSectionFlows
	  * @Description: 根据条件查询断面流量列表
	  * @param @param map
	  * @param @return    设定文件
	  * @return List<TrafficSectionFlow>    返回类型
	  * @throws
	 */
	List<TrafficSectionFlow> selectSectionFlows(Map<String,Object> map);
	/**
	 * 
	  * @Title: selectSectionsBySectionId
	  * @Description: 根据sectionId查询断面流量最近一条时间的流量
	  * @param @param sectionId
	  * @param @return    设定文件
	  * @return List<TrafficSectionFlow>    返回类型
	  * @throws
	 */
	TrafficSectionFlow selectSectionFlowBySectionId(String sectionId);
	/**
	 * @throws Exception 
	  * @Title: selectRegionFlowById
	  * @Description: 根据ID查询区间流量
	  * @param @param id
	  * @param @return    设定文件
	  * @return TrafficRegionFlow    返回类型
	  * @throws
	 */
	TrafficRegionFlow selectRegionFlowById(String id) throws Exception;
	/**
	  * @Title: selectRegionFlows
	  * @Description: 根据条件查询区间流量列表
	  * @param @param map
	  * @param @return    设定文件
	  * @return List<TrafficRegionFlow>    返回类型
	  * @throws
	 */
	List<TrafficRegionFlow> selectRegionFlows(Map<String,Object> map);
	/**
	 * 
	  * @Title: selectByRegionId
	  * @Description: 根据区间ID查询区间流量列表
	  * @param @param regionalId
	  * @param @return    设定文件
	  * @return List<TrafficRegionFlow>    返回类型
	  * @throws
	 */
	List<TrafficRegionFlow> selectByRegionId(String regionalId);
	/**
	 * 
	  * @Title: selectLastFlowByRegionId
	  * @Description: 根据区间ID查询区间流量最近一条
	  * @param @param regionalId
	  * @param @return    设定文件
	  * @return List<TrafficRegionFlow>    返回类型
	  * @throws
	 */
	TrafficRegionFlow selectLastFlowByRegionId(String regionalId);
	/**
	 * @throws Exception 
	  * @Title: selectWeatherById
	  * @Description: 根据ID查询气象仪数据
	  * @param @param id
	  * @param @return    设定文件
	  * @return TrafficWeather    返回类型
	  * @throws
	 */
	TrafficWeather selectWeatherById(String id) throws Exception;
	/**
	 * 
	  * @Title: selectLastByNBR
	  * @Description: 获取最新的气象数据
	  * @param @param deviceNbr
	  * @param @return    设定文件
	  * @return TrafficWeather    返回类型
	  * @throws
	 */
	TrafficWeather getLastWeatherByNBR(String deviceNbr);
	/**
	  * @Title: selectWeathers
	  * @Description: 根据条件查询气象仪数据列表
	  * @param @param map
	  * @param @return    设定文件
	  * @return List<TrafficWeather>    返回类型
	  * @throws
	 */	
	List<TrafficWeather> selectWeathers(Map<String,Object> map);
	
	/**
	 * @throws Exception 
	  * @Title: selectRoadsensorById
	  * @Description: 根据ID查询路感数据
	  * @param @param id
	  * @param @return    设定文件
	  * @return TrafficRoadsensor    返回类型
	  * @throws
	 */
	TrafficRoadsensor selectRoadsensorById(String id) throws Exception;
	/**
	  * @Title: selectRoadsensors
	  * @Description: 根据条件查询路感数据列表
	  * @param @param map
	  * @param @return    设定文件
	  * @return List<TrafficRoadsensor>    返回类型
	  * @throws
	 */
	List<TrafficRoadsensor> selectRoadsensors(Map<String,Object> map);
	
	/**
	 * @throws Exception 
	  * @Title: selectVisibilityById
	  * @Description: 根据ID查询能见度仪数据
	  * @param @param id
	  * @param @return    设定文件
	  * @return TrafficVisibility    返回类型
	  * @throws
	 */
	TrafficVisibility selectVisibilityById(String id) throws Exception;
	/**
	  * @Title: selectVisibilitys
	  * @Description: 根据条件查询能见度仪数据列表
	  * @param @param map
	  * @param @return    设定文件
	  * @return List<TrafficVisibility>    返回类型
	  * @throws
	 */
	List<TrafficVisibility> selectVisibilitys(Map<String,Object> map);
	/**
	 * @throws Exception 
	  * @Title: selectEventImageById
	  * @Description: 根据ID查询事件影像
	  * @param @param id
	  * @param @return    设定文件
	  * @return TrafficEventImage    返回类型
	  * @throws
	 */
	TrafficEventImage selectEventImageById(String id) throws Exception;
	List<TrafficSectionFlow> select2HourSectionFlow(Map<String, Object> map);
	List<TrafficRegionFlow> select2HourRegionFlow(Map<String, Object> map);
	List<TrafficWeather> select2HourWeathers(Map<String, Object> map);
	List<TrafficVisibility> select2HourVisibilitys(Map<String, Object> map);
	List<TrafficRoadsensor> select2HourRoadsensors(Map<String, Object> map);
	
}
