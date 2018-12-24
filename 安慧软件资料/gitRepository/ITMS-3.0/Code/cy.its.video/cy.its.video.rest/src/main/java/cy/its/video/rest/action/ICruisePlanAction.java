package cy.its.video.rest.action;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import cy.its.video.domain.criteria.TrafficPlanLogCriteria;

public interface ICruisePlanAction {

	/**
	 * 获取所有巡航方案列表
	 * 
	 * @return
	 */
	JSONObject getAllVideoCruisePlan(HttpServletRequest request);

	/**
	 * 获取所有巡航方案列表
	 * 
	 * @return
	 */
	JSONObject getVideoCruisePlanList(HttpServletRequest request);

	/**
	 * 获取巡航方案关联的视频列表
	 * 
	 * @param videoPlanID
	 * @return
	 */
	JSONObject getCruisePlanVideo(HttpServletRequest request);

	/**
	 * 根据巡航方案获取巡航日志
	 * 
	 * @param videoPlanID
	 * @return
	 */
	JSONObject getVideoCruiseLog(HttpServletRequest request, TrafficPlanLogCriteria trafficPlanLogCriteria);

	/**
	 * 根据巡航日志id获取相关的事件记录
	 * 
	 * @param videoPlanLogId
	 * @return
	 * @throws ParseException
	 */
	JSONObject getVideoCruiseEvent(HttpServletRequest request) throws ParseException;

	/**
	 * 根据设备id获取预置位列表
	 * 
	 * @param videoPlanLogId
	 * @return
	 */
	JSONObject getVideoPresetList(HttpServletRequest request);

	/**
	 * 创建视频巡航方案
	 * 
	 * @param plan
	 * @return
	 */
	JSONObject createVideoCruisePlan(HttpServletRequest request);

	/**
	 * 创建视频巡航日志
	 * 
	 * @param trafficPlanLog
	 * @return
	 */
	JSONObject createVideoCruiseLog(HttpServletRequest request);

	/**
	 * 创建视频巡航事件
	 * 
	 * @param trafficVideoLog
	 * @return
	 */
	JSONObject createVideoCruiseEvent(HttpServletRequest request);

	/**
	 * 创建视频预置位
	 * 
	 * @param trafficVideoLog
	 * @return
	 */
	JSONObject createVideoPreset(HttpServletRequest request);

	/**
	 * 创建用户自定义视频组
	 * 
	 * @param trafficVideoLog
	 * @return
	 */
	JSONObject createVideoGroup(HttpServletRequest request) throws Exception;

	/**
	 * 创建巡航视频
	 * 
	 * @param trafficVideoLog
	 * @return
	 */
	JSONObject createCruiseVideo(HttpServletRequest request);

	/**
	 * 更新巡航方案
	 * 
	 * @param trafficPlan
	 * @return
	 */
	JSONObject updateVideoCruisePlan(HttpServletRequest request);

	/**
	 * 更新巡航方案视频
	 * 
	 * @param request
	 * @return
	 */
	JSONObject updateCruiseVideo(HttpServletRequest request);

	/**
	 * 更新用户视频组
	 * 
	 * @param request
	 * @return
	 */
	JSONObject updateVideoGroup(HttpServletRequest request);

	/**
	 * 终止巡航->更新巡航日志
	 * 
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	JSONObject stopCruise(HttpServletRequest request) throws ParseException;

	/**
	 * 删除巡航方案
	 * 
	 * @param trafficPlan
	 * @return
	 */
	JSONObject deleteVideoCruisePlan(HttpServletRequest request);

	/**
	 * 删除视频巡航事件
	 * 
	 * @param trafficVideoLog
	 * @return
	 */
	JSONObject deleteVideoCruiseEvent(HttpServletRequest request);

	/**
	 * 删除视频预置位
	 * 
	 * @param trafficVideoLog
	 * @return
	 */
	JSONObject deleteVideoPreset(HttpServletRequest request);

	/**
	 * 更新视频预置位
	 * 
	 * @param trafficVideoLog
	 * @return
	 */
	JSONObject updateVideoPreset(HttpServletRequest request);

	/**
	 * 删除用户视频组
	 * 
	 * @param trafficVideoLog
	 * @return
	 */
	JSONObject deleteVideoGroup(HttpServletRequest request);

	/**
	 * 删除巡航视频
	 * 
	 * @param trafficVideoLog
	 * @return
	 */
	JSONObject deleteCruiseVideo(HttpServletRequest request);
}
