package cy.its.device.rest.action;

import java.util.Map;

import cy.its.device.rest.dto.LedContentLibDto;
import cy.its.device.rest.dto.LedPublishLogDto;

/**
 * 
 * @Title: LedPublishLogAction.java
 * @Package cy.its.device.rest.action
 * @Description: 发布日志管理接口
 * @author liug@cychina.cn
 * @date  2016年6月24日 上午9:41:45
 * @version V1.0
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016
 */
public interface ILedPublishLogAction {
	
	
	/**
	 * 查询内容库列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findLedPublishLogList(LedPublishLogDto form) throws Exception;
}
