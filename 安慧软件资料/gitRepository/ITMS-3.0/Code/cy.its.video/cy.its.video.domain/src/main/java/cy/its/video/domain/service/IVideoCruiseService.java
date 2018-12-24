package cy.its.video.domain.service;

import java.util.List;

import cy.its.video.domain.criteria.TrafficPlanLogCriteria;
import cy.its.video.domain.criteria.TrafficVideoGroupCriteria;
import cy.its.video.domain.model.TrafficPlan;
import cy.its.video.domain.model.TrafficPlanLog;
import cy.its.video.domain.model.TrafficPlanVideo;
import cy.its.video.domain.model.TrafficPreset;
import cy.its.video.domain.model.TrafficVideoGroup;
import cy.its.video.domain.model.TrafficVideoLog;

public interface IVideoCruiseService {

	/**
	 * 获取所有巡航方案列表
	 * 
	 * @return
	 */
	List<TrafficPlan> getTrafficPlanByCondition(String orgId) throws Exception;

	/**
	 * 获取巡航方案
	 * 
	 * @return
	 */
	TrafficPlan getVideoCruisePlanByID(String trafficPlanID) throws Exception;

	/**
	 * 获取巡航方案关联的视频列表
	 * 
	 * @param videoPlanID
	 * @return
	 */
	List<TrafficPlanVideo> getCruisePlanVideo(String videoPlanID) throws Exception;

	/**
	 * 根据巡航方案获取巡航日志
	 * 
	 * @param videoPlanID
	 * @return
	 */
	List<TrafficPlanLog> getVideoCruiseLog(TrafficPlanLogCriteria trafficPlanLogCriteria) throws Exception;

	/**
	 * 根据巡航日志id获取相关的事件记录
	 * 
	 * @param videoPlanLogId
	 * @return
	 */
	List<TrafficVideoLog> getVideoCruiseEvent(String videoPlanLogId) throws Exception;

	/**
	 * 根据视频id获取预置位列表
	 * 
	 * @param videoPlanLogId
	 * @return
	 */
	List<TrafficPreset> getVideoPresetList(String videoDeviceId, String componentId) throws Exception;

	/**
	 * 获取所有视频分组
	 * 
	 * @param videoPlanLogId
	 * @return
	 */
	List<TrafficVideoGroup> getVideoGroupByCondition(TrafficVideoGroupCriteria criteria) throws Exception;

	/**
	 * 创建视频巡航方案
	 * 
	 * @param plan
	 * @return
	 */
	String createVideoCruisePlan(TrafficPlan plan) throws Exception;

	/**
	 * 创建巡航视频
	 * 
	 * @param trafficPlanVideo
	 * @return
	 */
	String createCruiseVideo(TrafficPlanVideo trafficPlanVideo) throws Exception;

	/**
	 * 创建视频巡航日志
	 * 
	 * @param trafficPlanLog
	 * @return
	 */
	TrafficPlanLog createVideoCruiseLog(TrafficPlanLog trafficPlanLog) throws Exception;

	/**
	 * 创建视频巡航事件
	 * 
	 * @param trafficVideoLog
	 * @return
	 */
	String createVideoCruiseEvent(TrafficVideoLog trafficVideoLog) throws Exception;

	/**
	 * 创建视频巡航事件
	 * 
	 * @param trafficVideoLog
	 * @return
	 */
	String createVideoPreset(TrafficPreset trafficPreset) throws Exception;

	/**
	 * 创建用户视频自定义分组
	 * 
	 * @param trafficVideoLog
	 * @return
	 */
	String createVideoGroup(TrafficVideoGroup videoGroup) throws Exception;

	/**
	 * 更新巡航视频信息
	 * 
	 * @param trafficPlanVideo
	 * @return
	 */
	int updateCruiseVideo(TrafficPlanVideo trafficPlanVideo) throws Exception;

	/**
	 * 更新巡航方案
	 * 
	 * @param trafficPlan
	 * @return
	 */
	int updateVideoCruisePlan(TrafficPlan trafficPlan) throws Exception;

	/**
	 * 更新预置位
	 * 
	 * @param TrafficPreset
	 * @return
	 */
	int updateTrafficPreset(TrafficPreset trafficPreset) throws Exception;

	/**
	 * 更新用户分组
	 * 
	 * @param TrafficPreset
	 * @return
	 */
	int updateVideoGroup(TrafficVideoGroup videoGroup) throws Exception;

	/**
	 * 更新巡航日志
	 * 
	 * @param TrafficPlanLog
	 * @return
	 */
	int updateTrafficPlanLog(TrafficPlanLog trafficPlanLog) throws Exception;

	/**
	 * 更新视频事件
	 * 
	 * @param TrafficVideoLog
	 * @return
	 */
	int updateTrafficVideoLog(TrafficVideoLog trafficVideoLog) throws Exception;

	/**
	 * 终止巡航->更新巡航日志
	 * 
	 * @param TrafficPreset
	 * @return
	 */
	int stopVideoCruise(TrafficPlanLog trafficPlanLog) throws Exception;

	/**
	 * 删除巡航视频
	 * 
	 * @param trafficPlanVideo
	 * @return
	 */
	int deleteCruiseVideo(String cruiseVideoID) throws Exception;

	/**
	 * 删除巡航方案
	 * 
	 * @param trafficPlan
	 * @return
	 */
	int deleteVideoCruisePlan(String cruisePlanID) throws Exception;

	/**
	 * 删除视频巡航事件
	 * 
	 * @param trafficVideoLog
	 * @return
	 */
	int deleteVideoCruiseEvent(String videoCruiseEventID) throws Exception;

	/**
	 * 删除视频预置位
	 * 
	 * @param trafficPresetID
	 * @return
	 */
	int deleteTrafficPreset(String trafficPresetID) throws Exception;

	/**
	 * 删除用户自定义视频分组
	 * 
	 * @param trafficPresetID
	 * @return
	 */
	int deleteVideoGroup(String videoGroupID) throws Exception;

}
