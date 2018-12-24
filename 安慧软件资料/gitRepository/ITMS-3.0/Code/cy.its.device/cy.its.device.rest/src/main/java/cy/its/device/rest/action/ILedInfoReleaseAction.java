package cy.its.device.rest.action;

import java.util.List;

import cy.its.device.domain.criteria.LedProgTemplateCriteria;
import cy.its.device.domain.model.led.LedPowerGroup;
import cy.its.device.domain.model.led.LedProgTemplate;
import cy.its.device.domain.model.led.LedTask;
import cy.its.device.rest.dto.LedTaskDto;
import cy.its.device.rest.dto.LedTaskRelaseSearchDto;

/**
 * @Title: ILedProgTemplateCriteriaAction.java
 * @Package cy.its.device.rest.action
 * @Description: 信息发布控制器接口
 * @author liug@cychina.cn
 * @date  2016年6月29日 上午9:59:50
 * @version V1.0
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016 
 */

public interface ILedInfoReleaseAction {
	
	/**
	 * 获取指定设备正在播放的任务（含自动和人工）
	 * 
	 * @param deviceId
	 * @return
	 * @throws Exception 
	 */
	 List<LedTask> playingTasksOfLed(LedTaskRelaseSearchDto form) throws Exception;
	 /**
	  * 手动开关屏
	  * @param deviceId
	  * @param onOrOff
	  * @return
	  * @throws Exception
	  */
	 boolean setLedPower(LedTaskRelaseSearchDto form) throws Exception;
	/**
	* 定时开关屏
	* @param deviceId
	* @param lstPowerGroup
	* @return
	* @throws Exception
	*/
	boolean setLedPowerTimed(String deviceId, List<LedPowerGroup> lstPowerGroup) throws Exception;

	 
	/**
	* 调节LED屏亮度
	* @param deviceId
	* @param brightness
	* @return
	* @throws Exception
	*/
	//boolean setLedBrightness(LedTaskRelaseSearchDto form) throws Exception;
	/**
	 * 查询节目模板列表（条件： 规格、无条件等） √
	 * 
	 * @param ledProgTemplate
	 * @return
	 */
	public List<LedProgTemplate> findProgTemplates(LedProgTemplateCriteria criteria);
	/**
	 * 创建节目模板 √
	 * 
	 * @param ledProgTemplate
	 * @throws Exception 
	 */
	public void createProgTemplate(LedTaskRelaseSearchDto form) throws Exception;
	/**
	 * 编辑节目模板
	 * 
	 * @param ledProgTemplate
	 * @throws Exception 
	 */
	public void updateProgTemplate(LedTaskRelaseSearchDto form) throws Exception;

	/**
	 * 删除节目模板
	 * 
	 * @param progTemplateId
	 * @return 
	 */
	public int removeProgTemplate(LedTaskRelaseSearchDto form) throws Exception;
	/**
	 * 查看视频
	 * 
	 * @param progTemplateId
	 * @return 
	 */
	public String seeVoideo(LedTaskRelaseSearchDto form) throws Exception;
	/**
	 * 节目列表编辑
	 * 
	 * @param progTemplateId
	 * @return 
	 */
	int changeTaskList(LedTaskRelaseSearchDto form) throws Exception;

	/**
	 * 删除任务
	 * 
	 * @param ledTask
	 * @throws Exception 
	 */
	 int removeLedTask(LedTaskRelaseSearchDto form) throws Exception ;
	/**
	* 按编号排序任务
	* 
	* @param deviceId,ledTaskDtoList
	* @throws Exception 
	*/
	int sortTaskList(String deviceId, List<LedTaskDto> ledTaskDtoList) throws Exception;
	
	
	//List<LedTaskDto> sortTaskList(LedTaskRelaseSearchDto form, LedTaskDto ledTaskDto) throws Exception;
}
