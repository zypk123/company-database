/**
 * @Title: IManualEventAction.java
 * @Package cy.its.trafficSituation.rest.action
 * @Description: 人工记录事件rest接口
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月10日 下午3:40:16
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

import cy.its.trafficSituation.rest.dto.ManualEventDto;

public interface IManualEventAction {
	/**
	 * @throws Exception 
	 * @throws ParseException 
	 * 
	  * @Title: add
	  * @Description: 增
	  * @param @param dto
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	String add(ManualEventDto dto) throws ParseException, Exception;
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
	  * @Title: deleteMultiple
	  * @Description: 多删
	  * @param @param ids
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int deleteMultiple(String ids);
	/**
	 * @throws Exception 
	 * 
	  * @Title: update
	  * @Description: 改
	  * @param @param dto
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int update(ManualEventDto dto) throws Exception;
	/**
	 * @throws Exception 
	 * @throws ParseException 
	 * 
	  * @Title: selectAll
	  * @Description: 查
	  * @param @param manualEventDto
	  * @param @return    设定文件
	  * @return Object   返回类型
	  * @throws
	 */
	Object selectAll(ManualEventDto manualEventDto) throws ParseException, Exception;
}
