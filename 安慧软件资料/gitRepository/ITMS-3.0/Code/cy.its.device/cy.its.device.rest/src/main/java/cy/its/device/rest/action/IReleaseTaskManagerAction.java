package cy.its.device.rest.action;

import java.util.Map;

import cy.its.device.domain.model.led.LedTask;
import cy.its.device.rest.dto.LedTaskDto;
import cy.its.device.rest.dto.LedTaskSearchDto;

/**
 *
 * @Title: IReleaseTaskManager.java
 * @Package cy.its.device.rest.action
 * @Description:  信息发布管理接口
 * @author liug@cychina.cn
 * @date  2016年6月20日 上午9:28:48
 * @version V1.0
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016
 */
public interface IReleaseTaskManagerAction {
	/**
	 * 查询信息发布列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findReleaseTaskList(LedTaskSearchDto form)throws Exception;
	/**
	 * 查看信息发布管理详细信息
	 * @param form
	 * @return
	 * @throws Exception 
	 */
	public LedTask findReleaseTaskDetatil(LedTaskSearchDto form) throws Exception;
	/**
	 * 是否启用自动任务
	 * @param form
	 * @return
	 * @throws Exception 
	 */
	public int enableAutoTask(LedTaskSearchDto form) throws Exception;
	/**
	 * 是否停用自动任务
	 * @param form
	 * @return
	 * @throws Exception 
	 */
	public int disableAutoTask(LedTaskSearchDto form) throws Exception;
	
	/**
	 * 重新发布任务
	 * @param taskId
	 * @return 
	 * @throws Exception
	 */
	public int repubManualTask(LedTaskSearchDto form) throws Exception;
	/**
	 * 删除任务
	 * 
	 * @param ledTask
	 * @return 
	 * @throws Exception 
	 */
	 int removeLedTask(LedTaskSearchDto form) throws Exception ;
}
