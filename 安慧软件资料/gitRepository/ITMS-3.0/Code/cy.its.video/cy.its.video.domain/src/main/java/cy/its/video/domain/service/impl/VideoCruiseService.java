package cy.its.video.domain.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.video.domain.criteria.TrafficPlanLogCriteria;
import cy.its.video.domain.criteria.TrafficVideoGroupCriteria;
import cy.its.video.domain.model.TrafficPlan;
import cy.its.video.domain.model.TrafficPlanLog;
import cy.its.video.domain.model.TrafficPlanVideo;
import cy.its.video.domain.model.TrafficPreset;
import cy.its.video.domain.model.TrafficVideoGroup;
import cy.its.video.domain.model.TrafficVideoLog;
import cy.its.video.domain.repository.ITrafficPlanLogRepository;
import cy.its.video.domain.repository.ITrafficPlanRepository;
import cy.its.video.domain.repository.ITrafficPlanVideoRepository;
import cy.its.video.domain.repository.ITrafficPresetRepository;
import cy.its.video.domain.repository.ITrafficVideoGroupRepository;
import cy.its.video.domain.repository.ITrafficVideoLogRepository;
import cy.its.video.domain.service.IVideoCruiseService;

@Service
public class VideoCruiseService implements IVideoCruiseService {

	@Autowired
	ITrafficPresetRepository iTrafficPresetRepository;

	@Autowired
	ITrafficVideoLogRepository iTrafficVideoLogRepository;

	@Autowired
	ITrafficPlanRepository iTrafficPlanRepository;

	@Autowired
	ITrafficPlanLogRepository iTrafficPlanLogRepository;

	@Autowired
	ITrafficPlanVideoRepository iTrafficPlanVideoRepository;

	@Autowired
	ITrafficVideoGroupRepository iTrafficVideoGroupRepository;

	@Override
	public List<TrafficPlan> getTrafficPlanByCondition(String orgId) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("orgId", orgId);
		return iTrafficPlanRepository.getTrafficPlanByCondition(map);
	}

	@Override
	public TrafficPlan getVideoCruisePlanByID(String trafficPlanID) throws Exception {
		try {
			return iTrafficPlanRepository.aggregateOfId(trafficPlanID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<TrafficPlanVideo> getCruisePlanVideo(String videoPlanID) throws Exception {
		return iTrafficPlanVideoRepository.getTrafficPlanVideo(videoPlanID);
	}

	@Override
	public List<TrafficPlanLog> getVideoCruiseLog(TrafficPlanLogCriteria trafficPlanLogCriteria) throws Exception {
		String user = trafficPlanLogCriteria.getCruiseUser();
		if (!StringUtil.isNullOrEmpty(user)) {
			trafficPlanLogCriteria.setCruiseUser("%" + user + "%");
		}
		Map<String, Object> map = ParamUtil.parseParams(trafficPlanLogCriteria);
		if (trafficPlanLogCriteria.getNeedTotal()) {
			trafficPlanLogCriteria.setTotal(iTrafficPlanLogRepository.countTrafficPlanLogTotal(map));
		}
		return iTrafficPlanLogRepository.getTrafficPlanLog(map);
	}

	@Override
	public List<TrafficVideoLog> getVideoCruiseEvent(String videoPlanLogId) throws Exception {
		return iTrafficVideoLogRepository.getVideoCruiseEvent(videoPlanLogId);
	}

	@Override
	public List<TrafficPreset> getVideoPresetList(String videoDeviceId, String sysComponentId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtil.isNullOrEmpty(videoDeviceId)) {
			map.put("deviceId", videoDeviceId);
		}
		if (!StringUtil.isNullOrEmpty(sysComponentId)) {
			map.put("sysComponentId", sysComponentId);
		}
		return iTrafficPresetRepository.selectByCondition(map);
	}

	@Override
	public String createVideoCruisePlan(TrafficPlan plan) throws Exception {
		plan.setCreateTime(new Date());
		plan.setUpdateTime(new Date());
		// 名称唯一性约束
		Map<String, String> map = new HashMap<>();
		map.put("videoPlanName", plan.getVideoPlanName());
		map.put("orgId", plan.getOrgId());
		List<TrafficPlan> sameNameLst = iTrafficPlanRepository.getTrafficPlanByCondition(map);
		if (sameNameLst != null && sameNameLst.size() > 0) {
			throw new Exception("已存在名称为" + plan.getVideoPlanName() + "的巡航方案");
		}
		return iTrafficPlanRepository.save(plan);
	}

	@Override
	public TrafficPlanLog createVideoCruiseLog(TrafficPlanLog trafficPlanLog) throws Exception {
		trafficPlanLog.setVideoPlanStartTime(new Date());
		String id = iTrafficPlanLogRepository.save(trafficPlanLog);
		if (!StringUtil.isNullOrEmpty(id)) {
			trafficPlanLog.setVideoPlanLogId(id);
		} else {
			trafficPlanLog = null;
		}
		return trafficPlanLog;
	}

	@Override
	public String createVideoCruiseEvent(TrafficVideoLog trafficVideoLog) throws Exception {
		return iTrafficVideoLogRepository.save(trafficVideoLog);
	}

	@Override
	public String createVideoPreset(TrafficPreset trafficPreset) throws Exception {
		return iTrafficPresetRepository.save(trafficPreset);
	}

	@Override
	public String createCruiseVideo(TrafficPlanVideo trafficPlanVideo) throws Exception {
		return iTrafficPlanVideoRepository.save(trafficPlanVideo);
	}

	@Override
	public int updateCruiseVideo(TrafficPlanVideo trafficPlanVideo) throws Exception {
		return iTrafficPlanVideoRepository.update(trafficPlanVideo);
	}

	@Override
	public int updateVideoCruisePlan(TrafficPlan trafficPlan) throws Exception {
		trafficPlan.setUpdateTime(new Date());
		// 名称唯一性约束
		Map<String, String> map = new HashMap<>();
		map.put("videoPlanName", trafficPlan.getVideoPlanName());
		map.put("orgId", trafficPlan.getOrgId());
		List<TrafficPlan> sameNameLst = iTrafficPlanRepository.getTrafficPlanByCondition(map);
		if (sameNameLst != null && sameNameLst.size() > 0) {
			for (TrafficPlan plan : sameNameLst) {
				if (!plan.getVideoPlanId().equals(trafficPlan.getVideoPlanId())) {
					throw new Exception("已存在名称为" + trafficPlan.getVideoPlanName() + "的巡航方案");
				}
			}
		}

		return iTrafficPlanRepository.update(trafficPlan);
	}

	@Override
	public int updateTrafficPreset(TrafficPreset trafficPreset) throws Exception {
		return iTrafficPresetRepository.update(trafficPreset);
	}

	@Override
	public int updateTrafficPlanLog(TrafficPlanLog trafficPlanLog) throws Exception {
		return iTrafficPlanLogRepository.update(trafficPlanLog);
	}

	@Override
	public int updateTrafficVideoLog(TrafficVideoLog trafficVideoLog) throws Exception {
		return iTrafficVideoLogRepository.update(trafficVideoLog);
	}

	@Override
	public int deleteCruiseVideo(String cruiseVideoID) throws Exception {
		return iTrafficPlanVideoRepository.delete(cruiseVideoID);
	}

	@Override
	public int deleteVideoCruisePlan(String cruisePlanID) throws Exception {
		return iTrafficPlanRepository.delete(cruisePlanID);
	}

	@Override
	public int deleteVideoCruiseEvent(String videoCruiseEventID) throws Exception {
		return iTrafficVideoLogRepository.delete(videoCruiseEventID);
	}

	@Override
	public int deleteTrafficPreset(String trafficPresetID) throws Exception {
		return iTrafficPresetRepository.delete(trafficPresetID);
	}

	@Override
	public int stopVideoCruise(TrafficPlanLog trafficPlanLog) throws Exception {
		trafficPlanLog.setVideoPlanEndTime(new Date());
		return iTrafficPlanLogRepository.update(trafficPlanLog);

	}

	@Override
	public String createVideoGroup(TrafficVideoGroup videoGroup) throws Exception {
		// 数据库约束
		String dataBaseError = DataBaseConstrain(videoGroup);
		if (!StringUtil.isNullOrEmpty(dataBaseError)) {
			throw new Exception(dataBaseError);
		}
		// 名称唯一性约束

		TrafficVideoGroupCriteria criteria = new TrafficVideoGroupCriteria();
		criteria.setGroupName(videoGroup.getGroupName());
		criteria.setOrgAuthorityCode(videoGroup.getOrgAuthorityCode());
		List<TrafficVideoGroup> groupWithSameName = iTrafficVideoGroupRepository.getVideoGroupByCondition(criteria);
		if (groupWithSameName != null && groupWithSameName.size() > 0) {
			throw new Exception("已存在名称为" + videoGroup.getGroupName() + "组");
		}
		return iTrafficVideoGroupRepository.save(videoGroup);
	}

	@Override
	public int updateVideoGroup(TrafficVideoGroup videoGroup) throws Exception {

		// 名称唯一性约束

		TrafficVideoGroupCriteria criteria = new TrafficVideoGroupCriteria();
		criteria.setGroupName(videoGroup.getGroupName());
		criteria.setOrgAuthorityCode(videoGroup.getOrgAuthorityCode());
		List<TrafficVideoGroup> groupWithSameName = iTrafficVideoGroupRepository.getVideoGroupByCondition(criteria);
		if (groupWithSameName != null && groupWithSameName.size() > 0) {
			for (TrafficVideoGroup group : groupWithSameName) {
				if (!group.getGroupId().equals(videoGroup.getGroupId())) {
					throw new Exception("已存在名称为" + videoGroup.getGroupName() + "组");
				}
			}
		}
		return iTrafficVideoGroupRepository.update(videoGroup);
	}

	@Override
	public int deleteVideoGroup(String videoGroupID) throws Exception {
		return iTrafficVideoGroupRepository.delete(videoGroupID);
	}

	@Override
	public List<TrafficVideoGroup> getVideoGroupByCondition(TrafficVideoGroupCriteria criteria) throws Exception {
		return iTrafficVideoGroupRepository.getVideoGroupByCondition(criteria);
	}

	private String DataBaseConstrain(TrafficVideoGroup videoGroup) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(NotNullOrError(videoGroup.getGroupName(), "自定义组名称"));
		sBuilder.append(NotNullOrError(videoGroup.getCreateUser(), "创建人"));
		sBuilder.append(NotNullOrError(videoGroup.getOrgAuthorityCode(), "机构权限代码"));
		sBuilder.append(NotNullOrError(videoGroup.getCreateTime(), "创建时间"));
		sBuilder.append(NotNullOrError(videoGroup.getGroupContent(), "自定义组视频列表"));
		return sBuilder.toString();

	}

	private String NotNullOrError(String checkValue, String fieldName) {
		return StringUtil.isNullOrEmpty(checkValue) ? fieldName + "不能为空!" : "";
	}

	private String NotNullOrError(Date checkValue, String fieldName) {
		return checkValue == null || checkValue == new Date(0) ? fieldName + "不能为空!" : "";
	}

}
