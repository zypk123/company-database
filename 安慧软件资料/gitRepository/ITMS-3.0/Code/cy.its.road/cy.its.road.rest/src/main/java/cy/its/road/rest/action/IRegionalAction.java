/**
 * @Title: IReglonalAction.java
 * @Package cy.its.road.rest.action
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月21日 上午11:18:37
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.road.rest.action;

import java.util.Map;

import cy.its.road.rest.dto.RegionalDto;
import cy.its.road.rest.dto.RoadDto;

/**
  * @ClassName: IReglonalAction
  * @Description: TODO(这里要填写用途)
  * @author wangyf wangyf@cychina.cn
  * @date 2015年11月21日 上午11:18:37
  *
  */

public interface IRegionalAction {
	//创建区间信息 
	public  String createRegional(RegionalDto regionalDto) throws Exception;
	//查询
	public Map<String, Object> findRegional(RegionalDto regionalDto) throws Exception;
	//更新
	public String goUpdateRegional(RegionalDto regionalDto) throws Exception ;
	//单个删除
	public String goDeleteRegional(String regionalId);
	//批量删除
	public String goRemoveRegional(String regionalIdStr);
		
		
}
