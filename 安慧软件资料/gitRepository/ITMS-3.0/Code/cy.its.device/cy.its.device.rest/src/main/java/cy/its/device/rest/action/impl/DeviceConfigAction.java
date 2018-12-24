package cy.its.device.rest.action.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.Api;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.BaseStation;
import cy.its.device.domain.model.Certification;
import cy.its.device.domain.model.EventDetection;
import cy.its.device.domain.model.FlowSys;
import cy.its.device.domain.model.Meteorologic;
import cy.its.device.domain.model.Photo;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.SysParam;
import cy.its.device.domain.model.TollgateSys;
import cy.its.device.domain.model.Video;
import cy.its.device.domain.model.VioDevice;
import cy.its.device.domain.model.led.Led;
import cy.its.device.domain.model.site.Section;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.ISiteService;
import cy.its.device.domain.service.ISurveySystemService;
import cy.its.device.domain.service.ISystemAttachService;
import cy.its.device.domain.service.ISystemService;
import cy.its.device.rest.action.IDeviceConfigAction;
import cy.its.device.rest.dto.BayonetDeviceDto;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.DeviceConfigQryDto;
import cy.its.device.rest.dto.DeviceInfoDto;
import cy.its.device.rest.dto.DeviceQryConditionDto;
import cy.its.device.rest.dto.Dto;
import cy.its.device.rest.dto.Results;
import cy.its.device.rest.dto.TreeDto;
import cy.its.platform.common.utils.RedisPoolUtil;
import cy.its.road.domain.service.IRoadService;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.service.IDutyService;

@RestController
@RequestMapping("/device/deviceConfig")
@Api(description="设备管理", value = "DeviceConfigAction")
public class DeviceConfigAction implements IDeviceConfigAction {
	
	@Autowired
	ISystemService systemService; 
	
	@Autowired
	ISystemAttachService systemAttachService; 
	
	@Autowired
	ISiteService siteService;
	
	@Autowired
	IDutyService dutyService;
	
	@Autowired
	ISurveySystemService surveySystemService;
	
	@Autowired
	IRoadService roadService;
	
	
	private static final String DEVICE_STATUS_REDIS_KEY = "deviceStatus";

	/**
	 * 根据查询符合条件的设备信息
	 * 
	 * @param form
	 *            设备查询条件
	 * @return 查询结果对象列表
	 */
	@RequestMapping(value = "/queryDeviceLst")
	public DataGridResult<DeviceConfigQryDto> queryDeviceLst(@ModelAttribute("form") DeviceQryConditionDto form) throws Exception{
		DataGridResult<DeviceConfigQryDto> grdResult = new DataGridResult<DeviceConfigQryDto>();
		SystemCriteria criteria = new SystemCriteria();
		if(!StringUtil.isNullOrEmpty(form.getDeviceType())){
			criteria.deviceTypeArry = form.getDeviceType().split(",");
		}
		criteria.useStatusFlag = form.getUseStatus();
		criteria.deviceName = form.getDeviceName();
		criteria.setPageNum(form.getPageNumber());
		criteria.setPageSize(form.getPageSize());
		criteria.setNeedTotal(true);
		if(!StringUtil.isNullOrEmpty(form.getUserOrgId())){
			Organization org = dutyService.organizationOfId(form.getUserOrgId());
			if(!StringUtil.isNullOrEmpty(org.orgPrivilegeCode)){
				criteria.orgPrivilegeCode = org.orgPrivilegeCode;//机构过滤code
			}
		}
		if(!StringUtil.isNullOrEmpty(form.getRoadType())){
			criteria.roadTypeArr = form.getRoadType().split(",");
		}
		if(!StringUtil.isNullOrEmpty(form.getRoadId())){
			criteria.roadId = form.getRoadId();
		}
		if(!StringUtil.isNullOrEmpty(form.getSiteId())){
			criteria.siteId = form.getSiteId();
		}
		List<Sys> listAll =  systemService.findSystems(criteria);
		long total = criteria.getTotal();
		List<DeviceConfigQryDto> lst = new ArrayList<DeviceConfigQryDto>();
		for (int i = 0; i < listAll.size(); i++) {
			DeviceConfigQryDto dcDto = new DeviceConfigQryDto();
			ObjectMapUtils.parseObject(dcDto, listAll.get(i));
			String deviceId = listAll.get(i).getDeviceId();
			String deviceType = listAll.get(i).getDeviceType();
			if(!StringUtil.isNullOrEmpty(deviceType)){
				if("1".equals(deviceType) || "3".equals(deviceType) || "2".equals(deviceType)){//1,2,3是装备的类型
					dcDto.setRecordType("2");//2表示装备
				}else{
					dcDto.setRecordType("1");//1表示设备
				}
			}
			String expireDate = this.queryExpireDate(listAll.get(i).getDeviceSysNbr());//查询判断最大的年检日期
			if(!StringUtil.isNullOrEmpty(expireDate)){
				dcDto.setExpireDate(expireDate);//证书有效期时间（年检期止）
			}
			String siteId = listAll.get(i).getSiteId();
			if(siteId != null){
				Site site = siteService.siteOfId(siteId);
				String siteName = null;
				if(site != null){
					siteName = site.getSiteName();
				}
				dcDto.setSiteName(siteName);
			}
			//根据电子监控系统ID查图片信息
			List<Photo> l = systemAttachService.photesOfDevice(deviceId);
			if(l.size() > 0){
				dcDto.setImgUrl(l.get(0).getPhoto());//实景图片
			}
			lst.add(dcDto);
		}
		//获取设备运行情况缓存数据
		List<String> devCll = null;
		synchronized (DeviceConfigAction.class) {
			devCll = new ArrayList<String>(RedisPoolUtil.hgetAll(DEVICE_STATUS_REDIS_KEY).values());
		}
		if (devCll != null && devCll.size() != 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < lst.size(); i++) {
				String devId = lst.get(i).getDeviceId();
				for (int j = 0; j < devCll.size(); j++) {
					JSONObject temp = JSONObject.parseObject(devCll.get(j));
					String deviceIds = temp.getString("deviceId");// 设备ID
					if(devId.equals(deviceIds)){
						lst.get(i).setStatusType(temp.getString("statusType"));//设备状态
						if(!StringUtil.isNullOrEmpty(temp.getString("statusUpdateTime"))){
							lst.get(i).setLatestHeartTime(sdf.format(new Date(Long.valueOf(temp.getString("statusUpdateTime")))));//状态更新时间即最近心跳时间
						}else{
							lst.get(i).setLatestHeartTime(null);
						}
						
						lst.get(i).setTimeDiff(temp.getString("timeDiff"));//校时时间
						if(!StringUtil.isNullOrEmpty(temp.getString("lastUploadTime"))){
							lst.get(i).setLatestUploadTime(sdf.format(new Date(Long.valueOf(temp.getString("lastUploadTime")))));//最近上传时间
						}else{
							lst.get(i).setLatestUploadTime(null);
						}
						
					}
				}
			}
		}
		Results<DeviceConfigQryDto> res = new Results<DeviceConfigQryDto>();
		res.setRows(lst);
		res.setTotal(total);
		grdResult.setErro("");
		grdResult.setResult(res);
		return grdResult;
	}
	
	
	
	/**
	 * 判断最大年检时间
	 * @param deviceSysNbr
	 * @return
	 * @throws Exception
	 */
	private String queryExpireDate(String deviceSysNbr) throws Exception{
		String expireDate = null;
		
		List<Certification> list = systemAttachService.certificationsOfSystem(deviceSysNbr);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		long dateC = 0,temp = 0;//dateC年检时间变量、temp最大年检时间
		if(list.size() > 0){
			if(list.get(0).getExpireDate() != null){
				temp = list.get(0).getExpireDate().getTime();
			}
		}
		for (int i = 0; i < list.size()-1; i++) {
			if(list.get(i+1).getExpireDate() != null){
				dateC = list.get(i+1).getExpireDate().getTime();
			}
			if(temp < dateC){
				temp = dateC;
			}
		}
		if(temp != 0){
			expireDate =  sdf.format(new Date(temp));
		}
		return expireDate;
	}

	/**
	 * 添加保存设备基本信息
	 * @param form 设备基本信息信息
	 */
	@RequestMapping(value = "/addDeviceInfo")
	public String goAddDeviceInfo(@ModelAttribute("form") DeviceInfoDto form) throws Exception {
		//根据设备编号查询是否存在该设备
		SystemCriteria criteria = new SystemCriteria();
		criteria.deviceSysNbr = form.getDeviceSysNbr();
		List<Sys> list = systemService.findSystems(criteria);
		String deviceId = "nbrError";
		if(list == null || list.size() == 0){
			deviceId = addDeviceInfo(form);
			if("".equals(deviceId) == false){
				systemService.deviceSysChanged();
			}
		}
		return deviceId;
	}
	
	public String addDeviceInfo(DeviceInfoDto form) throws Exception {
		// TODO Auto-generated method stub
		String deviceId = "";
		/** 基本信息，安装信息，联网与接入 **/
		Sys sys = new Sys(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(form.getIsConnectTrackSys() == null){
			form.setIsConnectTrackSys("0");
		}
		if(form.getInterceptConditions() == null){
			form.setInterceptConditions("0");
		}
		if(form.getIsBackstageRecovery() == null){
			form.setIsBackstageRecovery("0");
		}
		if(form.getEventDetectionList() == null){
			form.setEventDetectionList("0");
		}
		ObjectMapUtils.parseObject(sys, form);//将rest层对象转换成领域层对象
		if(StringUtil.isNullOrEmpty(form.getInstallDate())){
			sys.setInstallDate(null);//安装日期
		}else{
			sys.setInstallDate(sdf.parse(form.getInstallDate()));
		}
		if(StringUtil.isNullOrEmpty(form.getLongitude())){
			sys.setLongitude(null);//经度
		}else{
			sys.setLongitude(new BigDecimal(form.getLongitude()));
		}
		if(StringUtil.isNullOrEmpty(form.getLatitude())){
			sys.setLatitude(null);//纬度
		}else{ 
			sys.setLatitude(new BigDecimal(form.getLatitude()));
		}
		if(StringUtil.isNullOrEmpty(form.getDevicePort())){
			sys.setDevicePort(null);
		}else{ 
			sys.setDevicePort(Long.parseLong(form.getDevicePort()));//设备端口
		}
		if(!StringUtil.isNullOrEmpty(form.getOrgId())){
			Organization org = dutyService.organizationOfId(form.getOrgId());
			if(!StringUtil.isNullOrEmpty(org.orgPrivilegeCode)){
				sys.setOrgPrivilegeCode(org.orgPrivilegeCode);//机构过滤code   
			}else{
				sys.setOrgPrivilegeCode(form.getCurrentOrgPrivilegeCode());//登录用户所在机构过滤code
			}
		}
		sys.setUseStatusFlag("0");//使用状态标识,默认0表示未启用
		sys.setStatusUpdateTime(new Date());//设备状态更新时间设置为系统当前时间
		sys.setCreateTime(new Date());//创建时间设置为系统当前时间
		sys.setCreateBy(form.getCurrentUserId());//设置创建人为当前用户
		sys.setUpdateTime(new Date());//更新时间设置为系统当前时间
		sys.setEnableUpdateDate(null);//使用状态更新时间为系统当前时间
		sys.setUpdateBy(form.getCurrentUserId());//设置更新人为当前用户  
		String deviceType = form.getDeviceType();//获取设备类型 
		if("01".equals(deviceType)){//01表示卡口
			/** 卡口参数 */
			TollgateSys tollgateSys = new TollgateSys(); 
			ObjectMapUtils.parseObject(tollgateSys, form);
			deviceId = systemService.createSystem(sys, tollgateSys);
		}else if("02".equals(deviceType) || "04".equals(deviceType) || "16".equals(deviceType)){//02表示电警，04表示测速，16表示车载
			/** 取证参数 **/
			VioDevice vioDevice = new VioDevice();
			ObjectMapUtils.parseObject(vioDevice, form);
			if(StringUtil.isNullOrEmpty(form.getValidityDate())){
				vioDevice.setValidityDate(null);//有效期止
			}else{
				vioDevice.setValidityDate(sdf.parse(form.getValidityDate()));
			}
			deviceId = systemService.createSystem(sys, vioDevice);
		}else if("03".equals(deviceType)){//03表示视频
			/** 视频监控参数 **/
			Video video = new Video();
			ObjectMapUtils.parseObject(video, form);
			deviceId = systemService.createSystem(sys, video);
		}else if("08".equals(deviceType)){//08表示事件监测
			/** 事件检测功能参数 **/
			EventDetection eventDetection = new EventDetection();
			if(!StringUtil.isNullOrEmpty(form.getEventDetectionListStr())){
				eventDetection.setEventDetectionList(form.getEventDetectionListStr());
			}
			eventDetection.setDeviceId(form.getDeviceId());
			deviceId = systemService.createSystem(sys, eventDetection);
		}else if("05".equals(deviceType)){//05表示气象
			/** 气象参数 **/
			if(form.getIsWeatherSupport() == null){
				form.setIsWeatherSupport("0");
			}
			if(form.getIsVisibilitySupport() == null){
				form.setIsVisibilitySupport("0");
			}
			if(form.getIsRoadsensorSupport() == null){
				form.setIsRoadsensorSupport("0");
			}
			Meteorologic meteorologic = new Meteorologic();
			ObjectMapUtils.parseObject(meteorologic, form);
			deviceId = systemService.createSystem(sys, meteorologic);
		}else if("11".equals(deviceType) || "12".equals(deviceType)){//"11"表示违停,"12"大车占道
		
			/** 违法取证设备*/
			VioDevice vDevice = new VioDevice();
			ObjectMapUtils.parseObject(vDevice, form);
			if(StringUtil.isNullOrEmpty(form.getValidityDate())){
				vDevice.setValidityDate(null);//有效期止
			}else{
				vDevice.setValidityDate(sdf.parse(form.getValidityDate()));
			}
			deviceId = systemService.createSystem(sys, vDevice);
		}else if("09".equals(deviceType)){
			/*** 流量检测设备 ***/
			FlowSys flowSys = new FlowSys();
			ObjectMapUtils.parseObject(flowSys, form);
			deviceId = systemService.createSystem(sys, flowSys);
		}else if("07".equals(deviceType)){
			/*** LED设备 ***/
			Led led = new Led();
			ObjectMapUtils.parseObject(led, form);
			deviceId = systemService.createSystem(sys, led);
		}else if("10".equals(deviceType)){
			/*** 短信基站设备 ***/
			BaseStation baseStation = new BaseStation();
			ObjectMapUtils.parseObject(baseStation, form);
			deviceId = systemService.createSystem(sys, baseStation);
		}else{
			deviceId = systemService.createSystem(sys, null);
		}
		return deviceId ;
	}

	/**
	 * 删除设备
	 * @param deviceId 设备ID
	 */
	@RequestMapping(value = "/deleteDeviceInfo")
	public int goDeleteDeviceInfo(@RequestParam("deviceId") String deviceId) throws Exception {
		int falg = deleteDeviceInfo(deviceId);
		if(falg == 1){
			systemService.deviceSysChanged();
		}
		return falg;
	}
	public int deleteDeviceInfo(String deviceId) throws Exception {
		systemService.removeSystem(deviceId);//删除设备基本信息
		// TODO  删除和设备表关联的一些信息
		return 1;
	}
	
	/**
	 * 批量删除设备（停用）
	 * @param deviceIds 设备ID数组
	 */
	@RequestMapping(value = "/deleteDeviceInfoAll")
	public int goDeleteDeviceInfoAll(@RequestParam("deviceIdStr") String deviceIdStr,@RequestParam("recordTypeStr") String recordTypeStr) throws Exception {
		int flag = deleteDeviceInfoAll(deviceIdStr,recordTypeStr);
		if(flag == 1){
			systemService.deviceSysChanged();
		}
		return flag;
	}
	public int deleteDeviceInfoAll(String deviceIdStr,String recordTypeStr) throws Exception {
		// TODO Auto-generated method stub
		String deviceIdSz[] = deviceIdStr.split(",");
		String recordTypes[] = recordTypeStr.split(",");
		for (int i = 0; i < deviceIdSz.length; i++) {
			String deviceId = deviceIdSz[i];
			String recordType = recordTypes[i];
			if("1".equals(recordType)){
				systemService.removeSystem(deviceId);//删除设备
			}else if("0".equals(recordType)){
				systemService.deleteEquipment(deviceId);//删除装备
			}
		}
		return 1;
	}
	
	/**
	 * 查看设备基本信息
	 */
	@RequestMapping(value = "/lookDeviceInfo")
	public DeviceInfoDto lookDeviceInfo(@RequestParam("deviceId") String deviceId) throws Exception{		
  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
		Sys sys =new Sys();
		sys = systemService.systemOfId(deviceId);
		
		ObjectMapUtils.parseObject(deviceInfoDto, sys);
				
		String siteId = sys.getSiteId();
		
		if(siteId != null){
			if(siteService.siteOfId(siteId) != null){
				String siteName = siteService.siteOfId(siteId).getSiteName();
				String roadId = siteService.siteOfId(siteId).getRoadId();

				deviceInfoDto.setSiteName(siteName);
				if(!StringUtil.isNullOrEmpty(roadId)){
					deviceInfoDto.setRoadId(roadId);
				}
			}
		}	
	

		if(sys.getDevicePort() != null){
			deviceInfoDto.setDevicePort(sys.getDevicePort().toString());//设备端口
		}
		if(sys.getInstallDate() != null){
			deviceInfoDto.setInstallDate(sdf.format(sys.getInstallDate()));
		}
		if(sys.getLatitude() != null){
			deviceInfoDto.setLatitude(sys.getLatitude().toString());
		}
		if(sys.getLongitude() != null){
			deviceInfoDto.setLongitude(sys.getLongitude().toString());
		}
		deviceInfoDto.setCreateTime(sdf.format(sys.getCreateTime()));
		String deviceType = sys.getDeviceType(); 
		if("02".equals(deviceType) || "04".equals(deviceType) || "16".equals(deviceType)){//02表示电警，04表示测速
			/** 取证参数 **/
			VioDevice vioDevice = new VioDevice();
			vioDevice = systemService.vioDeviceOfId(deviceId);
			ObjectMapUtils.parseObject(deviceInfoDto, vioDevice);
			if(vioDevice != null){
			if(vioDevice.getValidityDate() != null && "".equals(vioDevice.getValidityDate()) != true){
				deviceInfoDto.setValidityDate(sdf.format(vioDevice.getValidityDate()));//有效期止
			}
			}
		}else if("03".equals(deviceType)){//03表示视频
			/** 视频监控参数 **/
			Video video = new Video();
			video = systemService.videoOfId(deviceId);
			ObjectMapUtils.parseObject(deviceInfoDto, video);
		}else if("11".equals(deviceType) || "12".equals(deviceType)){//11违停，"12"大车占道
			/** 违法取证参数 **/
			VioDevice vioDev = new VioDevice();
			vioDev = systemService.vioDeviceOfId(deviceId);
			if(vioDev !=null){
				if(vioDev.getValidityDate() !=null && "".equals(vioDev.getValidityDate()) != true){
					deviceInfoDto.setValidityDate(sdf.format(vioDev.getValidityDate()));
				}else{
					deviceInfoDto.setValidityDate(null);
				}
			}
			ObjectMapUtils.parseObject(deviceInfoDto, vioDev);
		}else if("08".equals(deviceType)){//08表示事件监测
			/** 事件检测功能参数 **/
			EventDetection eventDetection = new EventDetection();
			eventDetection = systemService.eventDetectionOfId(deviceId);
			if(eventDetection != null){
				if(!StringUtil.isNullOrEmpty(eventDetection.getEventDetectionList())){
					deviceInfoDto.setEventDetectionListStr(eventDetection.getEventDetectionList());
				}
			}
		}else if("05".equals(deviceType)){//05表示气象
			/** 气象参数 **/
			Meteorologic meteorologic = new Meteorologic();
			meteorologic = systemService.meteorologicOfId(deviceId);
			ObjectMapUtils.parseObject(deviceInfoDto, meteorologic);
		}else if("09".equals(deviceType)){
			/*** 流量检测设备 ***/
			FlowSys flowSys = new FlowSys();
			flowSys = systemService.flowSysOfId(deviceId);
			ObjectMapUtils.parseObject(deviceInfoDto, flowSys);
		}else if("07".equals(deviceType)){
			/*** LED设备 ***/
			Led led = new Led();
			led = systemService.ledOfId(deviceId);
			ObjectMapUtils.parseObject(deviceInfoDto, led);
		}else if("10".equals(deviceType)){
			/*** 短信基站设备 ***/
			BaseStation baseStation = new BaseStation();
			baseStation = systemService.baseStationOfId(deviceId);
			ObjectMapUtils.parseObject(deviceInfoDto, baseStation);
		}
		return deviceInfoDto;
	}
	
	
	/**
	 * 修改设备信息
	 * @param form 修改后的设备信息
	 */
	@RequestMapping(value = "/updateDeviceInfo")
	public int goUpdateDeviceInfo(@ModelAttribute("form") DeviceInfoDto form) throws Exception{
		int flag = 0;//deviceSysNbr重复标识
		if(form.getDeviceSysNbr().equals(form.getOldDeviceSysNbr())){
			flag = updateDeviceInfo(form);
		}else{
			SystemCriteria criteria = new SystemCriteria();
			criteria.deviceSysNbr = form.getDeviceSysNbr();
			List<Sys> list = systemService.findSystems(criteria);
			if(list == null || list.size() == 0){
				flag = updateDeviceInfo(form);
			}
		}
		if(flag == 1){
			systemService.deviceSysChanged();
		}
		return flag;
	}
	public int updateDeviceInfo(DeviceInfoDto form) throws Exception{
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Sys sys = new Sys();
 		ObjectMapUtils.parseObject(sys, form);
 		if(StringUtil.isNullOrEmpty(form.getInstallDate())){
			sys.setInstallDate(null);//安装日期
		}else{
			sys.setInstallDate(sdf.parse(form.getInstallDate()));
		}
		if(StringUtil.isNullOrEmpty(form.getLongitude())){
			sys.setLongitude(null);//经度
		}else{
			sys.setLongitude(new BigDecimal(form.getLongitude()));
		}
		if(StringUtil.isNullOrEmpty(form.getLatitude())){
			sys.setLatitude(null);//纬度
		}else{ 
			sys.setLatitude(new BigDecimal(form.getLatitude()));
		}
 		if(form.getDevicePort() != null && "".equals(form.getDevicePort()) == false){
 			sys.setDevicePort(Long.parseLong(form.getDevicePort()));//设备端口
		}
		if(!StringUtil.isNullOrEmpty(form.getOrgId())){
			Organization org = dutyService.organizationOfId(form.getOrgId());
			if(!StringUtil.isNullOrEmpty(org.orgPrivilegeCode)){
				sys.setOrgPrivilegeCode(org.orgPrivilegeCode);//机构过滤code
			}else{
				sys.setOrgPrivilegeCode(form.getCurrentOrgPrivilegeCode());//登录用户所在机构过滤code
			}
		}
		sys.setUpdateTime(new Date());//更新时间设置为系统当前时间
		sys.setUpdateBy(form.getCurrentUserId());//设置更新人为当前用户
		sys.setStatusUpdateTime(new Date());//设备状态更新时间设置为系统当前时间
		systemService.updateSystem(sys);
		
		/** 事件检测功能参数 **/
		EventDetection eventDetection = new EventDetection();
		if(!StringUtil.isNullOrEmpty(form.getEventDetectionListStr())){
			eventDetection.setEventDetectionList(form.getEventDetectionListStr());
		}
		eventDetection.setDeviceId(form.getDeviceId());
		/** 视频监控参数 **/
		Video video = new Video();
		ObjectMapUtils.parseObject(video, form);
		/** 取证参数 **/
		VioDevice vioDevice = new VioDevice();
		ObjectMapUtils.parseObject(vioDevice, form);
		if(!StringUtil.isNullOrEmpty(form.getValidityDate())){
			vioDevice.setValidityDate(sdf.parse(form.getValidityDate()));
		}else{
			vioDevice.setValidityDate(null);//有效期止
		}
		
		/** 卡口参数 **/
		TollgateSys tollgateSys = new TollgateSys();
		ObjectMapUtils.parseObject(tollgateSys, form);
		/** 气象参数 **/
		Meteorologic meteorologic = new Meteorologic();
		ObjectMapUtils.parseObject(meteorologic, form);
		/*** 流量检测设备 ***/
		FlowSys flowSys = new FlowSys();
		ObjectMapUtils.parseObject(flowSys, form);
		/*** LED设备 ***/
		Led led = new Led();
		ObjectMapUtils.parseObject(led, form);
		/*** 短信基站 ***/
		BaseStation baseStation = new BaseStation();
		ObjectMapUtils.parseObject(baseStation,form);
		
		String deviceType = form.getDeviceType();
		SysParam sysParam = null;
		if("01".equals(deviceType)){//01表示卡口
			sysParam = tollgateSys;
		}else if("02".equals(deviceType) || "04".equals(deviceType) || "16".equals(deviceType)){//02表示电警，04表示测速,16车载（都是取证）
			sysParam = vioDevice;
		}else if("11".equals(deviceType) || "12".equals(deviceType)){//"11"表示违停，"12"大车占道
			/** 违法取证设备 */
			VioDevice vDevice = new VioDevice();
			vDevice.setDeviceId(form.getDeviceId());
			vDevice.setIntegratePlatformNbr(form.getIntegratePlatformNbr());
			vDevice.setCollcetionOrg(form.getCollcetionOrg());
			vDevice.setCollectionPerson(form.getCollectionPerson());
			vDevice.setRelatedVideoList(form.getRelatedVideoList());
			vDevice.setIsFlowSupport(form.getIsFlowSupport());
			if (!StringUtil.isNullOrEmpty(form.getValidityDate())) {
				vDevice.setValidityDate(sdf.parse(form.getValidityDate()));
			} else {
				vDevice.setValidityDate(null);
			}
			sysParam = vDevice;
				
		}else if("03".equals(deviceType)){//03表示视频
			sysParam = video;
		}else if("08".equals(deviceType)){//08表示事件监测
			sysParam = eventDetection;
		}else if("05".equals(deviceType)){//05表示气象
			sysParam = meteorologic;
		}else if("09".equals(deviceType)){//09流量检测
			sysParam = flowSys;
		}else if("07".equals(deviceType)){//07LED
			sysParam = led;
		}else if("10".equals(deviceType)){//07短信基站
			sysParam = baseStation;
		}
		systemService.updateSystem(sysParam);
		return 1;
		
	}
	

	
	/**
	 * 启用设备
	 * @param siteId 点位ID 
	 * @param deviceIdStr	多个设备Id字符串
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/enableDevice")
	public int goEnableDevice(@RequestParam("reason") String reason, @RequestParam("deviceIdStr") String deviceIdStr, @RequestParam("recordTypeStr") String recordTypeStr, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		int flag = enableDevice(reason, deviceIdStr,recordTypeStr,request);
		if(flag == 1){
			systemService.deviceSysChanged();
		}
		return flag;
	}
	public int enableDevice(String reason, String deviceIdStr,String recordTypeStr,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub 
		String deviceId[] = deviceIdStr.split(",");
		String recordTypes[] = recordTypeStr.split(",");
		String userName = request.getParameter("currentUserId");
		for (int i = 0; i < deviceId.length; i++) {
			String devId = deviceId[i];
			String recordType = recordTypes[i];//记录类型字符串，1表示电子监控设备，2表示装备
			if("1".equals(recordType)){
				systemService.enableDevice(devId, reason, userName);
			}else if("2".equals(recordType)){
				systemService.changeEquipmentUseStauts(devId, "1", reason, userName);//1表示启用
			}
		}
		return 1;
	}
	
	/**
	 * 停用设备
	 * @param siteId 点位ID 
	 * @param deviceIdStr	多个设备Id字符串
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/stopDevice")
	public int goStopDevice(@RequestParam("reason") String reason, @RequestParam("deviceIdStr") String deviceIdStr, @RequestParam("recordTypeStr") String recordTypeStr, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		int flag = stopDevice(reason, deviceIdStr,recordTypeStr,request);
		if(flag == 1){
			systemService.deviceSysChanged();
		}
		return flag;
	}
	public int stopDevice(String reason, String deviceIdStr,String recordTypeStr,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String deviceId[] = deviceIdStr.split(",");
		String recordTypes[] = recordTypeStr.split(",");
		String userName = request.getParameter("currentUserId");
		for (int i = 0; i < deviceId.length; i++) {
			String devId = deviceId[i];
			String recordType = recordTypes[i];//记录类型字符串，1表示电子监控设备，0表示装备
			if("1".equals(recordType)){
				systemService.stopDevice(devId, reason, userName);
			}else if("2".equals(recordType)){
				systemService.changeEquipmentUseStauts(devId, "2", reason, userName);//2表示停用
			}
		}
		return 1;
	}
	
	/**
	 * 报废设备
	 * @param siteId 点位ID 
	 * @param deviceIdStr	多个设备Id字符串
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/breakDevice")
	public int goBreakDevice(@RequestParam("reason") String reason, @RequestParam("deviceIdStr") String deviceIdStr, @RequestParam("recordTypeStr") String recordTypeStr, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		int flag = breakDevice(reason, deviceIdStr,recordTypeStr,request);
		if(flag == 1){
			systemService.deviceSysChanged();
		}
		return flag;
	}
	public int breakDevice(String reason, String deviceIdStr,String recordTypeStr,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String deviceId[] = deviceIdStr.split(",");
		String recordTypes[] = recordTypeStr.split(",");
		String userName = request.getParameter("currentUserId");
		for (int i = 0; i < deviceId.length; i++) {
			String devId = deviceId[i];
			String recordType = recordTypes[i];//记录类型字符串，1表示电子监控设备，0表示装备
			if("1".equals(recordType)){
				systemService.brokenDevice(devId, reason, userName);
			}else if("2".equals(recordType)){
				systemService.changeEquipmentUseStauts(devId, "3", reason, userName);//3表示报废
			}
		}
		return 1;
	}
	
	/**
	 * 根据点位ID查询断面列表
	 * @param siteId
	 * @return 断面列表
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/querySectionBySiteId")
	public List<TreeDto> querySectionBySiteId(@RequestParam("siteId") String siteId) throws Exception {
		// TODO Auto-generated method stub
		List<Section> list = siteService.sectionsOfSite(siteId);
		List<TreeDto> lst = new ArrayList<TreeDto>();
		for (int i = 0; i < list.size(); i++) {
			TreeDto treeDto = new TreeDto();
			treeDto.setId(list.get(i).getSectionId());
			treeDto.setText(list.get(i).getDirectionName());
			treeDto.setState(list.get(i).getDirectionType());
			lst.add(treeDto);
		}
		return lst;
	}
	
	/**
	 * 查询关联设备
	 * @param deviceType 设备类型
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/queryRelevanceDevice")
	public Map<String, List<Dto>> queryRelevanceDevice(String orgPrivilegeCode) throws Exception {
		// TODO Auto-generated method stub
		SystemCriteria criteria = new SystemCriteria();
		criteria.setNeedTotal(true);
		criteria.setNoPage();
		criteria.orgPrivilegeCode = orgPrivilegeCode;
		List<Sys> lst = systemService.findSys(criteria);
		Map<String, List<Dto>> returnMap = new HashMap<String, List<Dto>>();
		if(lst != null){
			List<Dto> list = new ArrayList<Dto>();
			for (int i = 0; i < lst.size(); i++) { 
				Dto dto = new Dto();
				dto.setId(lst.get(i).getDeviceId());
				dto.setText(lst.get(i).getDeviceName());
				dto.getAttribute().put("deviceType", lst.get(i).getDeviceType());
				list.add(dto);
			}
			returnMap = list.stream().collect(Collectors.groupingBy(Dto::getDeviceType));
		}
		return returnMap;
	}
	/**
	 * 查询所有气象设备
	 * @return
	 */
	@RequestMapping(value = "/queryWeatherDevice")
	public List<Sys> queryWeatherDevice() throws Exception{
		SystemCriteria criteria = new SystemCriteria();
		criteria.deviceType = "05";//设置设备类型为气象
		List<Sys> list = systemService.findSystems(criteria);//查询符合条件的设备
		return list;
	}
	
	/**
	 * 根据设备编号查询设备
	 * @param deviceSysNbr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/querySysByDeviceSysNbr")
	public boolean querySysByDeviceSysNbr(@RequestParam("deviceSysNbr") String deviceSysNbr) throws Exception{
		Sys sys = new Sys();
		sys = systemService.selectByNBR(deviceSysNbr);
		boolean falg = true;
		if(sys == null){
			falg = false;
		}
		return falg;
	}
	
	/**
	 * 根据点位ID查询设备，看此点位是否已绑定设备
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryDeviceBySiteId")
	public String queryDeviceBySiteId(@RequestParam("siteId") String siteId) throws Exception {
		String flag = "";
		String siteIds[] = siteId.split(",");
		for (int i = 0; i < siteIds.length; i++) {
			SystemCriteria criteria = new SystemCriteria();
			criteria.siteId = siteIds[i];
			List<Sys> list = systemService.findSys(criteria);
			if(list.size() != 0 && list != null){
				flag = siteIds[i];
				return flag;
			}
		}
		return flag;
	}
	
	/**
	 * 根据ID查找设备信息
	 * @param deviceId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/queryDeviceById")
	public Sys queryDeviceById(@RequestParam("deviceId") String deviceId) throws Exception{
		return systemService.systemOfId(deviceId);
	}
	
	/**
	 * 根据机构ID查找设备(按照设备编号从大到小排序,去后四位形成以逗号分隔的字符串)
	 * @param deviceId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/queryDeviceByOrgId")
	public String queryDeviceByOrgId(@RequestParam("orgId") String orgId, @RequestParam("deviceType") String deviceType) throws Exception{
		String deviceSysNbrStr = "";
		SystemCriteria criteria = new SystemCriteria();
		criteria.orgId = orgId;
		criteria.deviceType = deviceType;
		criteria.setOrderName("deviceSysNbr");
		criteria.setOrderType("desc");
		//获取设备信息按设备编号从大到小排序
		List<Sys> list =  systemService.findSys(criteria);
		Set<String> set = new HashSet<String>();//new 一个hashset
		for (int i = 0; i < list.size(); i++) {
			String dsn = list.get(i).getDeviceSysNbr();
			set.add(dsn.substring(dsn.length()-4,dsn.length()));//放入set集合中去除重复的
		}
		List<String> lst = new ArrayList<String>();
		for (Iterator<String> it = set.iterator(); it.hasNext();){
			lst.add((String) it.next());//遍历set 将所有元素键入lst中
        }
        Collections.sort(lst); //对lst进行快速排序(升序排序)
		for (int i = 0; i < lst.size(); i++) {
			if(i > 0){
				deviceSysNbrStr = deviceSysNbrStr + "," + lst.get(i);
			}else{
				deviceSysNbrStr = deviceSysNbrStr + lst.get(i);
			}
		}
		return deviceSysNbrStr;
	}
	
	/**
	 * 根据机构权限代码查询启用的led设备
	 * @param orgPrivilegeCode
	 * @return led设备集合
	 * @throws Exception 
	 */
	@RequestMapping(value = "/queryLedDevice")
	public List<DeviceInfoDto> queryLedDevice(@RequestParam("currentOrgPrivilegeCode") String currentOrgPrivilegeCode) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SystemCriteria criteria = new SystemCriteria();
		criteria.deviceType = "07";//设置设备类型为led设备
		//criteria.useStatusFlag = "1";//启用的设备
		criteria.orgPrivilegeCode = currentOrgPrivilegeCode;//机构权限代码
		List<Sys> lst = systemService.findSystems(criteria);//查询符合条件的设备
		List<DeviceInfoDto> list = new ArrayList<DeviceInfoDto>();
		for (int i = 0; i < lst.size(); i++) {
			DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
			Sys sys = lst.get(i);
			if(sys != null){
				ObjectMapUtils.parseObject(deviceInfoDto, sys);
				if(sys.getDevicePort() != null){
					deviceInfoDto.setDevicePort(sys.getDevicePort().toString());//设备端口
				}
				if(sys.getInstallDate() != null){
					deviceInfoDto.setInstallDate(sdf.format(sys.getInstallDate()));
				}
				if(sys.getEnableUpdateDate() != null){
					deviceInfoDto.setEnableUpdateDate(sdfs.format(sys.getEnableUpdateDate()));
				}
				if(sys.getStatusUpdateTime() != null){
					deviceInfoDto.setStatusUpdateTime(sdfs.format(sys.getStatusUpdateTime()));
				}
				if(sys.getUpdateTime() != null){
					deviceInfoDto.setUpdateTime(sdfs.format(sys.getUpdateTime()));
				}
				if(sys.getLatitude() != null){
					deviceInfoDto.setLatitude(sys.getLatitude().toString());
				}
				if(sys.getLongitude() != null){
					deviceInfoDto.setLongitude(sys.getLongitude().toString());
				}
				if(sys.getSiteId() != null){
					deviceInfoDto.setRoadId(siteService.siteOfId(sys.getSiteId()).getRoadId());
				}
				list.add(deviceInfoDto);
			}
		}
		return list;
	}
}
