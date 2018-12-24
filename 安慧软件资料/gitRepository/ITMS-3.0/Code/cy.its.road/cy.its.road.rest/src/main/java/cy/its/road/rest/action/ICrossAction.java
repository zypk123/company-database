/**
 * @Title: ICrossAction.java
 * @Package cy.its.road.rest.action
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月24日 下午2:09:40
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.road.rest.action;

import java.util.Map;

import cy.its.road.rest.dto.CrossDto;

/**
  * @ClassName: ICrossAction
  * @Description: TODO(这里要填写用途)
  * @author wangyf wangyf@cychina.cn
  * @date 2015年11月24日 下午2:09:40
  *
  */

public interface ICrossAction {
	//创建路口
	public String createCross(CrossDto crossDto) throws Exception;
	//查询路口信息
	public Map<String, Object> findCross(CrossDto crossDto) throws Exception;
	//更新路口信息
	public String updateCross(CrossDto crossDto) throws Exception;
	//批量删除路口信息
	public String removeCrossIds(String crossIdStr);
	//单个删除路口
	public String deleteCrossId(String crossId);
	
}
