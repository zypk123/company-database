/**
 * @Title: IManualStateAction.java
 * @Package cy.its.trafficSituation.rest.action
 * @Description: 人工干预道路通行状态rest 接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月18日 下午5:10:29
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.action;

import java.text.ParseException;

import cy.its.trafficSituation.rest.dto.ManualStateDto;

public interface IManualStateAction {
	/**
	 * @throws ParseException 
	 * 
	  * @Title: update
	  * @Description: 更新人工干预
	  * @param @param dto
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int update(ManualStateDto dto) throws ParseException;
	/**
	 * 
	  * @Title: selectByRoadId
	  * @Description: 根据roadId搜索人工干预
	  * @param @param roadId 道路ID
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	ManualStateDto selectByRoadId(String roadId);
	
}
