package cy.its.device.domain.service;

import java.util.List;

import cy.its.device.domain.criteria.LedContentLibCriteria;
import cy.its.device.domain.criteria.LedProgTemplateCriteria;
import cy.its.device.domain.criteria.LedPublishLogCriteria;
import cy.its.device.domain.criteria.LedSpecCriteria;
import cy.its.device.domain.criteria.LedTaskCriteria;
import cy.its.device.domain.model.led.LedContentLib;
import cy.its.device.domain.model.led.LedField;
import cy.its.device.domain.model.led.LedSpec;
import cy.its.device.domain.model.led.LedTask;
import cy.its.device.domain.model.led.LedTaskAuto;
import cy.its.device.domain.model.led.LedTaskManual;
import cy.its.device.domain.model.led.LedPowerGroup;
import cy.its.device.domain.model.led.LedProgTemplate;
import cy.its.device.domain.model.led.LedPublishLog;

public interface ILedService {

	/**
	 * 重新发布任务
	 * @param taskId
	 * @throws Exception
	 */
	public void repubManualTask(String taskId) throws Exception;
	
	/**
	 * 同一设备下任务上下移动
	 * @param deviceId
	 * @param taskId1  移动交换涉及到的任务一
	 * @param taskId2 移动交换涉及到的任务二
	 * @throws Exception
	 */
	public void swapTask(String deviceId, String taskId1, String taskId2) throws Exception;
	
	/**
	 * 同一设备下任务上下移动
	 * @param deviceId
	 * @param taskId1  移动交换涉及到的任务一
	 * @param taskId2 移动交换涉及到的任务二
	 * @throws Exception
	 */
	public void sortTask(String deviceId, List<String> taskId) throws Exception;
	
	/** 
	 * 创建自动任务 √
	 * 
	 * @param ledTask
	 * @throws Exception 
	 */
	 void createAutoTask(LedTaskAuto ledAutoTask) throws Exception ;

	/**
	 * 创建手动任务 √
	 * 
	 * @param ledTask
	 * @throws Exception 
	 */
	 void createManualTask(LedTaskManual ledManualTask) throws Exception ;

	/**
	 * 编辑任务
	 * 
	 * @param ledTask
	 * @throws Exception 
	 */
	 void updateLedTask(LedTask ledTask) throws Exception ;

	/**
	 * 删除任务
	 * 
	 * @param ledTask
	 * @throws Exception 
	 */
	 void removeLedTask(String taskId) throws Exception ;

	/**
	 * 查找符合条件的任务（含自动和人工） √
	 * 
	 * @param criteria
	 * @return
	 */
	 List<LedTask> findLedTask(LedTaskCriteria criteria) ;

	/**
	 * 获取指定设备正在播放的任务（含自动和人工）
	 * 
	 * @param deviceId
	 * @return
	 * @throws Exception 
	 */
	 List<LedTask> playingTasksOfLed(String deviceId) throws Exception;

	/**
	 * 启用自动任务 √
	 * 
	 * @param taskId
	 * @throws Exception 
	 */
	 void enableAutoTask(String taskId) throws Exception ;

	/**
	 * 停用自动任务 √
	 * 
	 * @param taskId
	 * @throws Exception 
	 */
	 void disableAutoTask(String taskId) throws Exception;

	/**
	 * 获取指定的任务明细  √
	 * 
	 * @param taskId
	 * @return
	 * @throws Exception 
	 */
	 LedTask ledTaskOfId(String taskId) throws Exception;
	 
	 
	 
	/**
	 * 创建节目模板 √
	 * 
	 * @param ledProgTemplate
	 * @throws Exception 
	 */
	public void createProgTemplate(LedProgTemplate ledProgTemplate) throws Exception;

	/**
	 * 查询节目模板列表（条件： 规格、无条件等） √
	 * 
	 * @param ledProgTemplate
	 * @return
	 */
	public List<LedProgTemplate> findProgTemplates(LedProgTemplateCriteria criteria);

	/**
	 * 编辑节目模板
	 * 
	 * @param ledProgTemplate
	 * @throws Exception 
	 */
	public void updateProgTemplate(LedProgTemplate ledProgTemplate) throws Exception;

	/**
	 * 删除节目模板
	 * 
	 * @param progTemplateId
	 */
	public void removeProgTemplate(String progTemplateId);
	
	/**
	 * 获取所有动态字段配置
	 * @return
	 */
	public List<LedField> allLedFields();
	
	/**
	 * 查询发布日志记录
	 * @param criteria
	 * @return
	 */
	public List<LedPublishLog> findLedPublishLogs(LedPublishLogCriteria criteria);
	
	/**
	 * 创建发布日志记录
	 * @param ledPublishLog
	 */
	public void saveLedPublishLog(LedPublishLog ledPublishLog);
	
	/**
	 * 手动开关屏
	 * 
	 * @param deviceNo
	 * @param onOrOff
	 * @throws Exception
	 */
	boolean setLedPower(String deviceId, boolean onOrOff) throws Exception;
	
	/**
	 * 定时开关屏
	 * @param deviceId
	 * @param lstPowerGroup
	 * @return
	 * @throws Exception
	 */
	boolean setLedPowerTimed(String deviceId, List<LedPowerGroup> lstPowerGroup) throws Exception;

//	/**
//	 * 调节LED屏亮度
//	 * @param deviceId
//	 * @param brightness 亮度值  在1-16之间
//	 * @return
//	 * @throws Exception
//	 */
//	boolean setLedBrightness(String deviceId, int brightness) throws Exception ;
	
//	/**
//	 * LED设备校时
//	 * @param deviceId
//	 * @return
//	 * @throws Exception
//	 */
//	boolean syncLedTime(String deviceId) throws Exception ;
	
	/**
	 * 增加诱导屏规格
	 * 
	 * @param ledSpec
	 * @throws Exception
	 */
	void createLedSpec(LedSpec ledSpec) throws Exception;

	/**
	 * 修改诱导屏规格
	 * 
	 * @param ledSpec
	 * @throws Exception
	 */
	void updateLedSpec(LedSpec ledSpec) throws Exception;

	/**
	 * 删除诱导屏规格
	 * 
	 * @param specId
	 * @throws Exception
	 */
	void removeLedSpec(String specId) throws Exception;

	/**
	 * 查询指定的诱导屏规格
	 * 
	 * @param specId
	 * @return
	 * @throws Exception
	 */
	LedSpec ledSpecOfId(String specId) throws Exception;

	/**
	 * 查询诱导屏规格列表 √
	 * 
	 * @param criteria
	 * @return
	 */
	List<LedSpec> findLedSpecs(LedSpecCriteria criteria);

	/**
	 * 创建内容库
	 * 
	 * @param contentlib
	 * @param creator
	 * @throws Exception
	 */
	void createContentLib(LedContentLib contentlib, String creator) throws Exception;

	/**
	 * 修改内容库
	 * 
	 * @param contentlib
	 * @param modifier
	 * @throws Exception
	 */
	void updateContentLib(LedContentLib contentlib, String modifier) throws Exception;

	/**
	 * 查询指定的内容库
	 * 
	 * @param contentId
	 * @return
	 * @throws Exception
	 */
	LedContentLib contentLibOfId(String contentId) throws Exception;

	/**
	 * 查询符合条件的内容库
	 * 
	 * @param criteria
	 * @return
	 */
	List<LedContentLib> findContentLibs(LedContentLibCriteria criteria);
}
