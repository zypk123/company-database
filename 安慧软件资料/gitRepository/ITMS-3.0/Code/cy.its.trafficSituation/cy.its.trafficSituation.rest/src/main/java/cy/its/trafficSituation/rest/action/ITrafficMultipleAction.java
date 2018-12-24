/**
 * @Title: ITrafficMultipleAction.java
 * @Package cy.its.trafficSituation.rest.action
 * @Description: 综合rest接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月29日 下午4:00:19
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.action;

import java.text.ParseException;
import java.util.List;

import cy.its.trafficSituation.rest.dto.RegionFlowDto;
import cy.its.trafficSituation.rest.dto.RoadStateDto;
import cy.its.trafficSituation.rest.dto.RoadsensorDto;
import cy.its.trafficSituation.rest.dto.SectionFlowDto;
import cy.its.trafficSituation.rest.dto.SiteSectonFlowDto;
import cy.its.trafficSituation.rest.dto.VisibilityDto;
import cy.its.trafficSituation.rest.dto.WeatherDto;

public interface ITrafficMultipleAction {
	/**
	 * @throws Exception @throws ParseException @Title:
	 * selectRegionFlows @Description: 根据条件查询断面流量列表 @param @param
	 * map @param @return 设定文件 @return List<RegionFlowDto> 返回类型 @throws
	 */
	Object selectRegionFlows(RegionFlowDto regionFlowDto) throws ParseException, Exception;

	/**
	 * @throws Exception @throws ParseException @Title:
	 * selectSectionFlows @Description: 根据条件查询断面流量列表 @param @param
	 * map @param @return 设定文件 @return List<RegionFlowDto> 返回类型 @throws
	 */
	Object selectSectionFlows(SectionFlowDto sectionFlowDto) throws ParseException, Exception;

	/**
	 * 
	 * @Title: selectWeathers @Description: 按条件查询气象数据列表 @param @param
	 * weatherDto @param @return @param @throws Exception 设定文件 @return Object
	 * 返回类型 @throws
	 */
	Object selectWeathers(WeatherDto weatherDto) throws Exception;

	/**
	 * 
	 * @Title: selectVisibilitys @Description: 按条件查询能见度列表 @param @param
	 * visibilityDto @param @return @param @throws Exception 设定文件 @return Object
	 * 返回类型 @throws
	 */
	Object selectVisibilitys(VisibilityDto visibilityDto) throws Exception;

	/**
	 * 
	 * @Title: selectRoadsensors @Description: 按条件查询路感数据列表 @param @param
	 * roadsensorDto @param @return @param @throws Exception 设定文件 @return Object
	 * 返回类型 @throws
	 */
	Object selectRoadsensors(RoadsensorDto roadsensorDto) throws Exception;

	/**
	 * 
	 * @Title: selectRoadStates @Description: 查询历史路况数据 @param @param
	 * roadStateDto @param @return @param @throws Exception 设定文件 @return Object
	 * 返回类型 @throws
	 */
	Object selectRoadStates(RoadStateDto roadStateDto) throws Exception;

	/**
	 * 
	 * @Title: selectSiteSections @Description:
	 * 查询最后一次历史断面流量数据 @param @return @param @throws Exception 设定文件 @return
	 * List<SiteSectonFlowDto> 返回类型 @throws
	 */
	List<SiteSectonFlowDto> selectSiteSections(String currentOrgPrivilegeCode) throws Exception;

}
