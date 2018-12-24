package cy.its.video.rest.action.impl;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.Server;
import cy.its.device.domain.model.ServerApplication;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.Video;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.ISiteService;
import cy.its.device.domain.service.ISurveySystemService;
import cy.its.device.domain.service.ISystemService;
import cy.its.video.domain.criteria.TrafficPlanLogCriteria;
import cy.its.video.domain.model.TrafficPlan;
import cy.its.video.domain.model.TrafficPlanLog;
import cy.its.video.domain.model.TrafficPlanVideo;
import cy.its.video.domain.model.TrafficPreset;
import cy.its.video.domain.model.TrafficVideoGroup;
import cy.its.video.domain.model.TrafficVideoLog;
import cy.its.video.domain.service.IVideoCruiseService;
import cy.its.video.rest.dto.TrafficPlanDto;
import cy.its.video.rest.dto.TrafficPlanLogDto;
import cy.its.video.rest.dto.TrafficPlanVideoDto;
import cy.its.video.rest.dto.TrafficPresetDto;
import cy.its.video.rest.dto.TrafficVideoGroupDto;
import cy.its.video.rest.dto.TrafficVideoLogConditionDto;
import cy.its.video.rest.dto.TrafficVideoLogDto;

/**
 * @author Jinhb
 *
 */
@RestController
@RequestMapping("/video/cruisePlan")
public class CruisePlanAction {

	@Autowired
	IVideoCruiseService iVideoCruiseService;

	@Autowired
	ISystemService systemService;

	@Autowired
	ISurveySystemService iSurveySystemService;

	@Autowired
	ISiteService iSiteService;

	private SimpleDateFormat longTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping("/getAllVideoCruisePlan")
	public JSONObject getAllVideoCruisePlan(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			List<TrafficPlan> serviceResult = iVideoCruiseService
					.getTrafficPlanByCondition(request.getParameter("currentOrgId"));
			List<TrafficPlanDto> returnLst = new ArrayList<TrafficPlanDto>();
			if (serviceResult != null && serviceResult.size() > 0) {

				returnLst = serviceResult.stream().map((obj) -> {
					TrafficPlanDto dto = new TrafficPlanDto();
					ObjectMapUtils.parseObject(dto, obj);
					dto.setCreateTime(longTimeFormat.format(obj.getCreateTime()));
					if (obj.getUpdateTime() != null) {
						dto.setUpdateTime(longTimeFormat.format(obj.getUpdateTime()));
					}
					return dto;
				}).collect(Collectors.toList());
			}
			JSONObject result = new JSONObject();
			result.put("total", returnLst.size());
			result.put("rows", returnLst);
			returnObj.put("result", result);
			returnObj.put("error", null);
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
			returnObj.put("error", e.getMessage());
		}
		return returnObj;
	}

	@RequestMapping("/getVideoCruisePlanList")
	public JSONObject getVideoCruisePlanList(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			List<TrafficPlan> serviceResult;
			serviceResult = iVideoCruiseService.getTrafficPlanByCondition(request.getParameter("currentOrgId"));
			List<TrafficPlanDto> returnLst = new ArrayList<TrafficPlanDto>();
			if (serviceResult != null && serviceResult.size() > 0) {

				returnLst = serviceResult.stream().map((obj) -> {
					TrafficPlanDto dto = new TrafficPlanDto();
					ObjectMapUtils.parseObject(dto, obj);
					dto.setCreateTime(longTimeFormat.format(obj.getCreateTime()));
					if (obj.getUpdateTime() != null) {
						dto.setUpdateTime(longTimeFormat.format(obj.getUpdateTime()));
					}
					return dto;
				}).collect(Collectors.toList());
			}
			returnObj.put("result", returnLst);
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/getCruisePlanVideo")
	public JSONObject getCruisePlanVideo(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String param = request.getParameter("param");
			List<TrafficPlanVideoDto> returnLst = new ArrayList<TrafficPlanVideoDto>();
			if (!StringUtil.isNullOrEmpty(param)) {
				TrafficPlanDto trafficPlan = JSONObject.parseObject(param, TrafficPlanDto.class);
				List<TrafficPlanVideo> serviceResult;
				serviceResult = iVideoCruiseService.getCruisePlanVideo(trafficPlan.getVideoPlanId());

				if (serviceResult != null && serviceResult.size() > 0) {
					returnLst = serviceResult.stream().map((obj) -> {
						TrafficPlanVideoDto dto = new TrafficPlanVideoDto();
						ObjectMapUtils.parseObject(dto, obj);
						// get sys
						try {
							Sys sys = systemService.systemOfId(dto.getDeviceId());
							if (sys != null) {
								dto.setVideoDeviceName(sys.getDeviceName());
								dto.setDeviceIp(sys.getDeviceIp());
								String cameraServerTypeIp = "";
								List<ServerApplication> g1 = iSurveySystemService
										.serverAppsOfServerPlat(sys.getServerPlatId());
								if (g1 != null) {
									for (ServerApplication serverApp : g1) {
										// 视频web服务器
										if ("8".equals(serverApp.getAppType())) {// 436
											// 8
											Server server = iSurveySystemService.serverOfId(serverApp.getServerId());
											cameraServerTypeIp = server.getServerIp();
										}
									}
								}
								dto.setCameraServerTypeIp(cameraServerTypeIp);
								dto.setDeviceSysNbr(sys.getDeviceSysNbr());
								Site site = iSiteService.siteOfId(sys.getSiteId());
								if (site != null) {
									dto.setPlace(site.getSiteName());
								}
								Video video = systemService.videoOfId(dto.getDeviceId());
								if (video != null) {
									dto.setVideoAccessMode(video.getVideoAccessMode());
									dto.setPreViewParam(video.getPreviewParam());
									dto.setPlayBackParam(video.getPlaybackParam());

								}
								List<TrafficPreset> presetLst = iVideoCruiseService
										.getVideoPresetList(dto.getDeviceId(), dto.getSysComponentId()).stream()
										.filter(preset -> {
									return preset.getPresetRecordId().equals(dto.getPresetList());
								}).collect(Collectors.toList());
								if (presetLst != null && presetLst.size() > 0) {
									dto.setPresetDesc(presetLst.get(0).getPresetDesc());
									dto.setPreset(presetLst.get(0).getPreset());
								}

							}
						} catch (Exception e) {

							e.printStackTrace();
						}

						return dto;
					}).collect(Collectors.toList());
				}
			}
			Collections.sort(returnLst, new Comparator<TrafficPlanVideoDto>() {
				public int compare(TrafficPlanVideoDto o1, TrafficPlanVideoDto o2) {
					return o1.getSortIndex() - o2.getSortIndex();
				}
			});
			returnObj.put("result", returnLst);
		} catch (

		Exception e)

		{
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;

	}

	@RequestMapping("/getVideoCruiseLog")
	public JSONObject getVideoCruiseLog(HttpServletRequest request,
			@ModelAttribute("trafficPlanLogCriteria") TrafficVideoLogConditionDto trafficVideoLogConditionDto) {

		JSONObject returnObj = new JSONObject();
		List<TrafficPlanLog> serviceResult;
		try {
			TrafficPlanLogCriteria criteria = new TrafficPlanLogCriteria();
			ObjectMapUtils.parseObject(criteria, trafficVideoLogConditionDto);
			criteria.setPageNum(trafficVideoLogConditionDto.getPageNumber());
			criteria.setPageSize(trafficVideoLogConditionDto.getPageSize());
			criteria.setOrgId(trafficVideoLogConditionDto.getCurrentOrgId());
			criteria.setNeedTotal(true);
			serviceResult = iVideoCruiseService.getVideoCruiseLog(criteria);
			List<TrafficPlanLogDto> returnLst = new ArrayList<TrafficPlanLogDto>();
			if (serviceResult != null && serviceResult.size() > 0) {

				for (TrafficPlanLog trafficPlanLog : serviceResult) {
					TrafficPlanLogDto planLogDto = new TrafficPlanLogDto();
					ObjectMapUtils.parseObject(planLogDto, trafficPlanLog);
					// 获取巡航方案名称

					TrafficPlan plan = iVideoCruiseService.getVideoCruisePlanByID(trafficPlanLog.getVideoPlanId());
					// if (plan != null) {
					// planLogDto.setVideoPlanName(plan.getVideoPlanName());
					// }
					if (trafficPlanLog.getVideoPlanEndTime() != null) {
						planLogDto.setVideoPlanEndTime(longTimeFormat.format(trafficPlanLog.getVideoPlanEndTime()));
					}
					if (trafficPlanLog.getVideoPlanStartTime() != null) {
						planLogDto.setVideoPlanStartTime(longTimeFormat.format(trafficPlanLog.getVideoPlanStartTime()));
					}
					List<TrafficVideoLogDto> eventLst=new ArrayList<TrafficVideoLogDto>();
					List<TrafficVideoLog> eventResult;
					eventResult = iVideoCruiseService.getVideoCruiseEvent(planLogDto.getVideoPlanLogId());
					if (eventResult != null && eventResult.size() > 0) {
						for (TrafficVideoLog videoLog : eventResult) {
							TrafficVideoLogDto videoLogDto = new TrafficVideoLogDto();
							ObjectMapUtils.parseObject(videoLogDto, videoLog);
							if (videoLog.getVideoPlanEndTime() != null) {
								videoLogDto.setVideoPlanEndTime(longTimeFormat.format(videoLog.getVideoPlanEndTime()));
							}
							if (videoLog.getVideoPlanStartTime() != null) {
								videoLogDto.setVideoPlanStartTime(longTimeFormat.format(videoLog.getVideoPlanStartTime()));
							}
							// get sys
							try {
								Sys sys = systemService.systemOfId(videoLogDto.getDeviceId());
								videoLogDto.setVideoDeviceName(sys.getDeviceName());
							} catch (Exception e) {

								e.printStackTrace();
							}
							eventLst.add(videoLogDto);
						}
					}
					planLogDto.setEventLst(eventLst);					
					returnLst.add(planLogDto);
				}
			}

			JSONObject result = new JSONObject();
			result.put("total", criteria.getTotal());
			result.put("rows", returnLst);
			returnObj.put("result", result);
			returnObj.put("error", null);
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/getVideoCruiseEvent")
	public JSONObject getVideoCruiseEvent(HttpServletRequest request) throws ParseException {
		JSONObject returnObj = new JSONObject();
		try {
			String planLogID = request.getParameter("planLogID");
			List<TrafficVideoLogDto> returnLst = new ArrayList<TrafficVideoLogDto>();
			if (!StringUtil.isNullOrEmpty(planLogID)) {
				List<TrafficVideoLog> serviceResult;
				serviceResult = iVideoCruiseService.getVideoCruiseEvent(planLogID);
				if (serviceResult != null && serviceResult.size() > 0) {
					for (TrafficVideoLog videoLog : serviceResult) {
						TrafficVideoLogDto videoLogDto = new TrafficVideoLogDto();
						ObjectMapUtils.parseObject(videoLogDto, videoLog);
						if (videoLog.getVideoPlanEndTime() != null) {
							videoLogDto.setVideoPlanEndTime(longTimeFormat.format(videoLog.getVideoPlanEndTime()));
						}
						if (videoLog.getVideoPlanStartTime() != null) {
							videoLogDto.setVideoPlanStartTime(longTimeFormat.format(videoLog.getVideoPlanStartTime()));
						}
						// get sys
						try {
							Sys sys = systemService.systemOfId(videoLogDto.getDeviceId());
							videoLogDto.setVideoDeviceName(sys.getDeviceName());
						} catch (Exception e) {

							e.printStackTrace();
						}
						returnLst.add(videoLogDto);

					}
				}
			}
			returnObj.put("result", returnLst);
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/getVideoPresetList")
	public JSONObject getVideoPresetList(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String deviceId = request.getParameter("deviceId");
			String sysComponentId = request.getParameter("sysComponentId");
			List<TrafficPresetDto> returnLst = new ArrayList<TrafficPresetDto>();
			List<TrafficPreset> serviceResult;
			serviceResult = iVideoCruiseService.getVideoPresetList(deviceId, sysComponentId);
			if (serviceResult != null && serviceResult.size() > 0) {
				returnLst = serviceResult.stream().map((obj) -> {
					TrafficPresetDto dto = new TrafficPresetDto();
					ObjectMapUtils.parseObject(dto, obj);
					return dto;
				}).sorted(new Comparator<Object>() {
					@Override
					public int compare(Object o1, Object o2) {
						return ((TrafficPresetDto) o1).getPreset() - ((TrafficPresetDto) o2).getPreset();
					}
				}).collect(Collectors.toList());
			}
			returnObj.put("result", returnLst);
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}

		return returnObj;
	}

	@RequestMapping("/stopCruise")
	public JSONObject stopCruise(HttpServletRequest request) throws ParseException {
		JSONObject returnObj = new JSONObject();
		try {
			String param = request.getParameter("param");
			if (!StringUtil.isNullOrEmpty(param)) {
				TrafficPlanLogDto videoLogDto = JSONObject.parseObject(param, TrafficPlanLogDto.class);
				TrafficPlanLog planLog = new TrafficPlanLog();
				ObjectMapUtils.parseObject(planLog, videoLogDto);
				if (videoLogDto.getVideoPlanStartTime() != null) {
					planLog.setVideoPlanStartTime(longTimeFormat.parse(videoLogDto.getVideoPlanStartTime()));
				}
				planLog.setVideoPlanEndTime(new Date());
				iVideoCruiseService.updateTrafficPlanLog(planLog);
				returnObj.put("result", "");
			}
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/createVideoCruiseLog")
	public JSONObject createVideoCruiseLog(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String videoPlanId = request.getParameter("videoPlanId");
			String videoPlanName = request.getParameter("videoPlanName");
			String createUser = request.getParameter("currentUserName");
			String currentOrgId = request.getParameter("currentOrgId");
			// 更新巡航方案
			TrafficPlan trafficPlan = iVideoCruiseService.getVideoCruisePlanByID(videoPlanId);
			trafficPlan.setPlanExecuteTimes(trafficPlan.getPlanExecuteTimes() + 1);
			int updateResult = iVideoCruiseService.updateVideoCruisePlan(trafficPlan);
			if (updateResult > 0) {

				// 生成巡航日志
				TrafficPlanLog log = new TrafficPlanLog();
				TrafficPlanLogDto logDto = new TrafficPlanLogDto();
				if (!StringUtil.isNullOrEmpty(videoPlanId)) {
					log.setVideoPlanId(videoPlanId);
					log.setVideoPlanPerson(createUser);
					log.setVideoPlanName(videoPlanName);
					String ipAddress = null;
					// ipAddress = this.getRequest().getRemoteAddr();
					ipAddress = request.getHeader("x-forwarded-for");
					if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
						ipAddress = request.getHeader("Proxy-Client-IP");
					}
					if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
						ipAddress = request.getHeader("WL-Proxy-Client-IP");
					}
					if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
						ipAddress = request.getRemoteAddr();
						if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
							// 根据网卡取本机配置的IP
							InetAddress inet = null;
							try {
								inet = InetAddress.getLocalHost();
							} catch (Exception e) {
								e.printStackTrace();
							}
							ipAddress = inet.getHostAddress();
						}

					}

					// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
					if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
																		// = 15
						if (ipAddress.indexOf(",") > 0) {
							ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
						}
					}
					log.setComputerIp(ipAddress);
					log.setOrgId(currentOrgId);
					log = iVideoCruiseService.createVideoCruiseLog(log);
					if (log != null) {
						ObjectMapUtils.parseObject(logDto, log);
						logDto.setVideoPlanStartTime(longTimeFormat.format(log.getVideoPlanStartTime()));
					}
				}
				returnObj.put("result", logDto);
			} else {
				returnObj.put("errorMsg", "更新巡航方案总执行次数失败");
			}
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/createVideoCruiseEvent")
	public JSONObject createVideoCruiseEvent(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String param = request.getParameter("param");
			if (!StringUtil.isNullOrEmpty(param)) {
				TrafficVideoLogDto videoLogDto = JSONObject.parseObject(param, TrafficVideoLogDto.class);
				TrafficVideoLog videoLog = new TrafficVideoLog();
				ObjectMapUtils.parseObject(videoLog, videoLogDto);
				if (!StringUtil.isNullOrEmpty(videoLogDto.getVideoPlanStartTime())) {
					videoLog.setVideoPlanStartTime(longTimeFormat.parse(videoLogDto.getVideoPlanStartTime()));
				}
				iVideoCruiseService.createVideoCruiseEvent(videoLog);
			}
			returnObj.put("result", "");
		} catch (ParseException e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/createCruisePlan")
	public JSONObject createCruisePlan(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String param = request.getParameter("param");
			String createUser = request.getParameter("currentUserName");
			String userOrgId = request.getParameter("currentOrgId");
			String videoPlanId = "";
			TrafficPlanDto trafficPlanDto = JSONObject.parseObject(param, TrafficPlanDto.class);
			if (trafficPlanDto != null) {
				TrafficPlan plan = new TrafficPlan();
				ObjectMapUtils.parseObject(plan, trafficPlanDto);
				// 时间转化
				if (!StringUtil.isNullOrEmpty(trafficPlanDto.getCreateTime())) {
					plan.setCreateTime(longTimeFormat.parse(trafficPlanDto.getCreateTime()));
				}
				if (!StringUtil.isNullOrEmpty(trafficPlanDto.getUpdateTime())) {
					plan.setUpdateTime(longTimeFormat.parse(trafficPlanDto.getUpdateTime()));
				}

				plan.setOrgId(userOrgId);
				plan.setCreateBy(createUser);
				plan.setEnableFlag("1");
				plan.setPlanExecuteTimes(0l);
				List<TrafficPlanVideoDto> listVideo = trafficPlanDto.getVideoLst();
				if (listVideo != null && listVideo.size() > 0) {

					short sortIndex = 0;
					// 更新

					Long totalTime = 0l;
					for (TrafficPlanVideoDto dto : listVideo) {
						totalTime += dto.getCruiseTime() == null ? 0l : dto.getCruiseTime();
					}
					plan.setTotalCruiseTimes(totalTime);
					videoPlanId = iVideoCruiseService.createVideoCruisePlan(plan);

					for (TrafficPlanVideoDto dto : listVideo) {

						TrafficPlanVideo trafficPlanVideo = new TrafficPlanVideo();
						ObjectMapUtils.parseObject(trafficPlanVideo, dto);
						// 新视频添加
						// trafficPlanVideo.setSortIndex(sortIndex++);
						// 新记录添加
						trafficPlanVideo.setVideoPlanId(videoPlanId);
						iVideoCruiseService.createCruiseVideo(trafficPlanVideo);
					}
				} else {
					videoPlanId = iVideoCruiseService.createVideoCruisePlan(plan);
				}
				trafficPlanDto.setCreateTime(longTimeFormat.format(plan.getCreateTime()));
				if (plan.getUpdateTime() != null) {
					trafficPlanDto.setUpdateTime(longTimeFormat.format(plan.getUpdateTime()));
				}
				ObjectMapUtils.parseObject(trafficPlanDto, plan);
			}
			returnObj.put("errorMsg", "");
			returnObj.put("result", trafficPlanDto);
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}

		return returnObj;
	}

	@RequestMapping("/createCruiseVideo")
	public JSONObject createCruiseVideo(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String param = request.getParameter("param");
			String relatedVideoId = "";
			if (!StringUtil.isNullOrEmpty(param)) {
				TrafficPlanVideoDto trafficPlanDto = JSONObject.parseObject(param, TrafficPlanVideoDto.class);
				if (trafficPlanDto != null) {
					TrafficPlanVideo trafficPlanVideo = new TrafficPlanVideo();
					ObjectMapUtils.parseObject(trafficPlanVideo, trafficPlanDto);

					// 新记录添加
					relatedVideoId = iVideoCruiseService.createCruiseVideo(trafficPlanVideo);
				}
			}
			returnObj.put("errorMsg", "");
			returnObj.put("result", relatedVideoId);
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/createVideoGroup")
	public JSONObject createVideoGroup(HttpServletRequest request) throws Exception {
		JSONObject returnObj = new JSONObject();
		try {
			String param = request.getParameter("param");
			String createUser = request.getParameter("currentUserName");
			String currentOrgPrivilegeCode = request.getParameter("currentOrgPrivilegeCode");
			String groupID = "";
			if (!StringUtil.isNullOrEmpty(param)) {
				TrafficVideoGroupDto trafficVideoGroupDto = JSONObject.parseObject(param, TrafficVideoGroupDto.class);
				if (trafficVideoGroupDto != null) {
					TrafficVideoGroup videoGroup = new TrafficVideoGroup();
					ObjectMapUtils.parseObject(videoGroup, trafficVideoGroupDto);
					videoGroup.setCreateUser(createUser);
					videoGroup.setCreateTime(new Date());
					videoGroup.setOrgAuthorityCode(currentOrgPrivilegeCode);
					groupID = iVideoCruiseService.createVideoGroup(videoGroup);
				}
			}
			returnObj.put("errorMsg", "");
			returnObj.put("result", groupID);

		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			returnObj.put("result", "");
		}
		return returnObj;
	}

	@RequestMapping("/createVideoPreset")
	public JSONObject createVideoPreset(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String param = request.getParameter("param");
			String createUser = request.getParameter("currentUserName");
			TrafficPresetDto dto = JSONObject.parseObject(param, TrafficPresetDto.class);
			dto.setCreateBy(createUser);
			dto.setCreateTime(new Date());
			String id = "";
			if (dto != null) {
				TrafficPreset preset = new TrafficPreset();
				ObjectMapUtils.parseObject(preset, dto);
				// preset.setCreateBy(createUser);
				// preset.setCreateTime(new Date());
				id = iVideoCruiseService.createVideoPreset(preset);
			}
			dto.setPresetRecordId(id);
			returnObj.put("errorMsg", "");
			returnObj.put("result", dto);
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/updateCruisePlan")
	public JSONObject updateCruisePlan(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String param = request.getParameter("param");
			String createUser = request.getParameter("currentUserName");
			String userOrgId = request.getParameter("currentOrgId");
			String videoPlanId = "";
			TrafficPlanDto trafficPlanDto = JSONObject.parseObject(param, TrafficPlanDto.class);
			TrafficPlanDto returnDto = new TrafficPlanDto();
			if (trafficPlanDto != null) {
				returnDto.setVideoLst(trafficPlanDto.getVideoLst());
				TrafficPlan plan = new TrafficPlan();
				ObjectMapUtils.parseObject(plan, trafficPlanDto);
				// 时间转化
				if (!StringUtil.isNullOrEmpty(trafficPlanDto.getCreateTime())) {
					try {
						plan.setCreateTime(longTimeFormat.parse(trafficPlanDto.getCreateTime()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if (!StringUtil.isNullOrEmpty(trafficPlanDto.getUpdateTime())) {
					try {
						plan.setUpdateTime(longTimeFormat.parse(trafficPlanDto.getUpdateTime()));
					} catch (ParseException e) {

						e.printStackTrace();
					}
				}
				videoPlanId = trafficPlanDto.getVideoPlanId();

				plan.setOrgId(userOrgId);
				plan.setUpdateBy(createUser);

				Long totalTime = 0l;
				List<TrafficPlanVideoDto> listVideo = trafficPlanDto.getVideoLst();
				for (TrafficPlanVideoDto dto : listVideo) {
					totalTime += dto.getCruiseTime();
				}
				plan.setTotalCruiseTimes(totalTime);
				iVideoCruiseService.updateVideoCruisePlan(plan);
				List<String> oldIds = new ArrayList<String>();
				String videoId = null;
				TrafficPlanVideo trafficPlanVideo = new TrafficPlanVideo();

				for (TrafficPlanVideoDto dto : listVideo) {
					videoId = dto.getRelatedVideoId();
					if (!StringUtil.isNullOrEmpty(videoId)) {
						oldIds.add(videoId);
					}
				}
				// 不存在的记录删除
				List<TrafficPlanVideo> serviceResult = iVideoCruiseService.getCruisePlanVideo(videoPlanId);
				for (TrafficPlanVideo dto : serviceResult) {
					if (!oldIds.contains(dto.getRelatedVideoId())) {
						iVideoCruiseService.deleteCruiseVideo(dto.getRelatedVideoId());
					}
				}
				// 更新
				for (TrafficPlanVideoDto dto : listVideo) {
					videoId = dto.getRelatedVideoId();
					trafficPlanVideo = new TrafficPlanVideo();
					ObjectMapUtils.parseObject(trafficPlanVideo, dto);

					if (StringUtil.isNullOrEmpty(videoId)) {
						// 新记录添加
						trafficPlanVideo.setVideoPlanId(videoPlanId);
						iVideoCruiseService.createCruiseVideo(trafficPlanVideo);
					} else {
						// 旧记录更新
						iVideoCruiseService.updateCruiseVideo(trafficPlanVideo);
					}
				}

				ObjectMapUtils.parseObject(returnDto, plan);
				returnDto.setCreateTime(longTimeFormat.format(plan.getCreateTime()));
				if (plan.getUpdateTime() != null) {
					returnDto.setUpdateTime(longTimeFormat.format(plan.getUpdateTime()));
				}
			}
			returnObj.put("errorMsg", "");
			returnObj.put("result", returnDto);
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}

		return returnObj;
	}

	@RequestMapping("/updateCruiseVideo")
	public JSONObject updateCruiseVideo(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String param = request.getParameter("param");
			String relatedVideoId = "";
			if (!StringUtil.isNullOrEmpty(param)) {
				TrafficPlanVideoDto trafficPlanDto = JSONObject.parseObject(param, TrafficPlanVideoDto.class);
				if (trafficPlanDto != null) {
					TrafficPlanVideo trafficPlanVideo = new TrafficPlanVideo();
					ObjectMapUtils.parseObject(trafficPlanVideo, trafficPlanDto);

					// 新记录添加
					iVideoCruiseService.updateCruiseVideo(trafficPlanVideo);
				}
			}
			returnObj.put("result", relatedVideoId);
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/updateVideoCruiseEvent")
	public JSONObject updateVideoCruiseEvent(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String param = request.getParameter("param");
			if (!StringUtil.isNullOrEmpty(param)) {
				TrafficVideoLogDto trafficVideoLogDto = JSONObject.parseObject(param, TrafficVideoLogDto.class);
				if (trafficVideoLogDto != null) {
					TrafficVideoLog trafficVideoLog = new TrafficVideoLog();
					ObjectMapUtils.parseObject(trafficVideoLog, trafficVideoLogDto);
					if (!StringUtil.isNullOrEmpty(trafficVideoLogDto.getVideoPlanStartTime())) {
						trafficVideoLog.setVideoPlanStartTime(
								longTimeFormat.parse(trafficVideoLogDto.getVideoPlanStartTime()));
					}
					// 新记录添加
					int i = iVideoCruiseService.updateTrafficVideoLog(trafficVideoLog);
				}
			}
			returnObj.put("result", "");
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/updateVideoPreset")
	public JSONObject updateVideoPreset(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String param = request.getParameter("param");
			String createUser = request.getParameter("currentUserName");
			TrafficPresetDto dto = JSONObject.parseObject(param, TrafficPresetDto.class);
			if (dto != null) {
				TrafficPreset trafficPreset = new TrafficPreset();
				ObjectMapUtils.parseObject(trafficPreset, dto);
				trafficPreset.setUpdateBy(createUser);
				trafficPreset.setUpdateTime(new Date());
				iVideoCruiseService.updateTrafficPreset(trafficPreset);
			}
			returnObj.put("errorMsg", "");
			returnObj.put("result", "");
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/updateVideoGroup")
	public JSONObject updateVideoGroup(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String param = request.getParameter("param");
			if (!StringUtil.isNullOrEmpty(param)) {
				TrafficVideoGroupDto trafficVideoGroupDto = JSONObject.parseObject(param, TrafficVideoGroupDto.class);
				if (trafficVideoGroupDto != null) {
					TrafficVideoGroup videoGroup = new TrafficVideoGroup();
					ObjectMapUtils.parseObject(videoGroup, trafficVideoGroupDto);
					videoGroup.setUpdateTime(new Date());
					iVideoCruiseService.updateVideoGroup(videoGroup);
					returnObj.put("result", "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnObj.put("errorMsg", e.getMessage());
		}

		return returnObj;
	}

	@RequestMapping("/deleteVideoPreset")
	public JSONObject deleteVideoPreset(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String param = request.getParameter("param");
			TrafficPresetDto dto = JSONObject.parseObject(param, TrafficPresetDto.class);
			int i = 0;
			if (dto != null) {
				i = iVideoCruiseService.deleteTrafficPreset(dto.getPresetRecordId());
			}
			returnObj.put("errorMsg", i > 0 ? null : "失败");
			returnObj.put("result", "");
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/deleteVideoGroup")
	public JSONObject deleteVideoGroup(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String videoGroupID = request.getParameter("param");
			if (!StringUtil.isNullOrEmpty(videoGroupID)) {
				iVideoCruiseService.deleteVideoGroup(videoGroupID);
			}
			returnObj.put("result", "");
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/deleteCruiseVideo")
	public JSONObject deleteCruiseVideo(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String relatedVideoId = request.getParameter("param");
			if (!StringUtil.isNullOrEmpty(relatedVideoId)) {
				iVideoCruiseService.deleteCruiseVideo(relatedVideoId);
			}
			returnObj.put("result", "");
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/deleteVideoCruisePlan")
	public JSONObject deleteVideoCruisePlan(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String videoPlanId = request.getParameter("param");
			List<TrafficPlanVideo> listVideo = iVideoCruiseService.getCruisePlanVideo(videoPlanId);
			if (listVideo != null) {
				for (TrafficPlanVideo video : listVideo) {
					iVideoCruiseService.deleteCruiseVideo(video.getRelatedVideoId());
				}
			}
			iVideoCruiseService.deleteVideoCruisePlan(videoPlanId);

			returnObj.put("result", "");
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}

	@RequestMapping("/deleteVideoCruiseEvent")
	public JSONObject deleteVideoCruiseEvent(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		try {
			String videoLogId = request.getParameter("param");
			int i = iVideoCruiseService.deleteVideoCruiseEvent(videoLogId);

			returnObj.put("result", "");
		} catch (Exception e) {
			returnObj.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return returnObj;
	}
}
