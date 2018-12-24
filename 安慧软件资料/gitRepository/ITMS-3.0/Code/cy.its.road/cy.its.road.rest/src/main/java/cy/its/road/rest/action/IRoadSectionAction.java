/**
 * @Title: ISectionAction.java
 * @Package cy.its.road.rest.action
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年10月29日 下午4:06:41
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.road.rest.action;

import java.util.Map;

import cy.its.road.rest.dto.RoadSectionDto;

/**
  * @ClassName: ISectionAction
  * @Description: TODO(SectionDto对应页面与领域Dto交互)
  * @author wangyf wangyf@cychina.cn
  * @date 2015年10月29日 下午4:06:41
  *
  */

public interface IRoadSectionAction {
	
	//创建路段信息 
	public  int createRoadSection(RoadSectionDto roadSectionDto) throws Exception;
	//查询
	public Map<String,Object> searchRoadSection(RoadSectionDto roadSectionDto) throws Exception;
	//更新
	public int goUpdateRoadSection(RoadSectionDto roadSectionDto) throws Exception;
	//单个删除
	public String goDeleteRoadSection(String roadSectionIdStr);
	//批量删除
	public String goRemoveRoadSection(String roadSectionId);
	//根据路段Id查询断面个数
	public int querySectionByRoadSectionId(String roadSectionId)  throws Exception;
}
