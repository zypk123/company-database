package cy.its.device.rest.action.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.criteria.VTollgateCriteria;
import cy.its.device.domain.model.DeviceSysUseStatus;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.SysComponent;
import cy.its.device.domain.model.TollgateSys;
import cy.its.device.domain.model.VTollgate;
import cy.its.device.domain.model.site.Section;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.ISiteService;
import cy.its.device.domain.service.ISystemAttachService;
import cy.its.device.domain.service.ISystemService;
import cy.its.device.rest.action.IBayonetDeviceAction;
import cy.its.device.rest.dto.BayonetDeviceDto;
import cy.its.device.rest.dto.CameraInfoDto;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.DeviceInfoDto;
import cy.its.device.rest.dto.DeviceSysUseStatusDto;
import cy.its.device.rest.dto.Results;
import cy.its.device.rest.dto.TollgateQryDto;
import cy.its.road.domain.model.road.Road;
import cy.its.road.domain.service.IRoadService;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.service.IDutyService;


@RestController
@RequestMapping("/device/bayonetManage")
@Api(description="卡口备案管理", value = "BayonetDeviceAction")
public class BayonetDeviceAction implements IBayonetDeviceAction {
	@Autowired
	ISystemService systemService;
	
	@Autowired
	IDutyService dutyService;
	
	@Autowired
	ISystemAttachService systemAttachService;
	
	@Autowired
	ISiteService siteService;
	
	@Autowired
	IRoadService roadService;
	
	/**
	 * 查询符合条件的卡口设备信息
	 * @param form 设备查询条件
	 * @return 卡口设备信息列表
	 * @throws Exception 异常
	 */
	@Override
	@RequestMapping(value = "/queryBayonetDeviceLst",method = RequestMethod.POST)
	@ApiOperation(value="queryBayonetDeviceLst",notes="查询卡口设备列表",httpMethod="POST")
	public DataGridResult<DeviceInfoDto> queryBayonetDeviceLst(@ModelAttribute("form") TollgateQryDto form)
			throws Exception {

		DataGridResult<DeviceInfoDto> grdResult = new DataGridResult<DeviceInfoDto>();
		VTollgateCriteria criteria = new VTollgateCriteria();
		if (!StringUtil.isNullOrEmpty(form.getTollgateType())) {
			List<String> tollgateTypeArray = new ArrayList<String>();
			String[] originTollgateType = form.getTollgateType().split(",");
			for(String tollgate : originTollgateType){
				tollgateTypeArray.add(tollgate);
			}
			criteria.tollgateTypeArray = tollgateTypeArray;
		}
		criteria.deviceName = form.getDeviceName();
		criteria.contractorId = form.getContractorId();
		if (!StringUtil.isNullOrEmpty(form.getUseStatusFlagArray())) {
			List<String> useStatusArray = new ArrayList<String>();
			String[] originUseStatus = form.getUseStatusFlagArray().split(",");
			for(String useStatus : originUseStatus){
				useStatusArray.add(useStatus);
			}
			criteria.useStatusFlagArray = useStatusArray;
		}
		criteria.setOrderName(form.getOrderName());
		criteria.setOrderType(form.getOrderType());
		criteria.setPageNum(form.getPageNumber());
		criteria.setPageSize(form.getPageSize());
		criteria.setNeedTotal(true);
		if (!StringUtil.isNullOrEmpty(form.getUserOrgId())) {
			Organization org = dutyService.organizationOfId(form.getUserOrgId());
			if (!StringUtil.isNullOrEmpty(org.orgPrivilegeCode)) {
				criteria.orgPrivilageCode = org.orgPrivilegeCode;// 机构过滤code
			}
		}
		if (!StringUtil.isNullOrEmpty(form.getRoadType())) {
			criteria.roadType = form.getRoadType();
		}
		if (!StringUtil.isNullOrEmpty(form.getRoadId())) {
			criteria.roadId = form.getRoadId();
		}
		if (!StringUtil.isNullOrEmpty(form.getSiteId())) {
			criteria.siteId = form.getSiteId();
		}
		List<VTollgate> listAll = systemService.findVTollgates(criteria);
		long total = criteria.getTotal();
		List<DeviceInfoDto> lst = new ArrayList<DeviceInfoDto>();
		for (int i = 0; i < listAll.size(); i++) {
			DeviceInfoDto dcDto = new DeviceInfoDto();
			ObjectMapUtils.parseObject(dcDto, listAll.get(i));
			if(listAll.get(i).getEnableUpdateDate()!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dcDto.setStatusUpdateTime(sdf.format(listAll.get(i).getEnableUpdateDate()));
			}
			String roadId = listAll.get(i).getRoadId();
			if (roadId != null) {
				Road road = roadService.roadOfId(roadId);
				String roadName = null;
				if (road != null) {
					roadName = road.getRoadName();
				}
				dcDto.setRoadId(roadName);
			}
			String orgId = listAll.get(i).getOrgId();
			if (orgId != null) {
				Organization org = dutyService.organizationOfId(orgId);
				String orgName = null;
				if (org != null) {
					orgName = org.orgName;
				}
				dcDto.setOrgId(orgName);
			}
			String siteId = listAll.get(i).getSiteId();
			if (siteId != null) {
				Site site = siteService.siteOfId(siteId);
				String siteName = null;
				if (site != null) {
					siteName = site.getSiteName();
				}
				dcDto.setSiteName(siteName);
			}
			String sectionIDs = listAll.get(i).getSectionIdList();
			if(sectionIDs!=null)
			{
			String []sectionIds = sectionIDs.split(",");
			String directionName = "";
			for (int m = 0; m < sectionIds.length; m++) {
				Section section = siteService.sectionOfId(sectionIds[m]);
				if (section != null) {
					directionName += section.getDirectionName() + ",";
				}
			}
			if (directionName != "") {
				directionName = directionName.substring(0, directionName.length() - 1);
			}
			dcDto.setSectionIdList(directionName);
			}
			lst.add(dcDto);
		}
		Results<DeviceInfoDto> res = new Results<DeviceInfoDto>();
		res.setRows(lst);
		res.setTotal(total);
		grdResult.setErro("");
		grdResult.setResult(res);
		return grdResult;
	}
	
	/**
	 * 查询符合条件的设备使用状态信息
	 * @param form 查询条件
	 * @return 查询结果对象列表
	 * @throws Exception 异常
	 */
	@Override
	@RequestMapping(value="/selectSysUseStatusList",method = RequestMethod.POST)
	@ApiOperation(value="selectSysUseStatusList",notes="查询卡口设备使用状态历史记录列表",httpMethod="POST")
	public Map<String, Object> selectSysUseStatusList(@ModelAttribute("form") TollgateQryDto form) throws Exception {
		ArrayList<DeviceSysUseStatusDto> pDtos = new ArrayList<DeviceSysUseStatusDto>();
		String deviceId = form.getDeviceId();
		Date updateTimeFrom = null;
		Date updateTimeTo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(form.getUpdateStartTime()!= ""){
			updateTimeFrom = sdf.parse(form.getUpdateStartTime());
		}
		if(form.getUpdateEndTime()!= ""){
			updateTimeTo = sdf.parse(form.getUpdateEndTime());
		}
		List<DeviceSysUseStatus> sysUseStatusLst = systemService.useStatusesOfDevice(deviceId, updateTimeFrom, updateTimeTo);
		for (DeviceSysUseStatus sysUseStatus : sysUseStatusLst) {
			DeviceSysUseStatusDto dto = new DeviceSysUseStatusDto();
			ObjectMapUtils.parseObject(dto,sysUseStatus);
			String updateBy = sysUseStatus.getUpdateBy();
			if(updateBy != null){
				User user = dutyService.userOfId(updateBy);
				String userName = null;
				if(user!=null){
					userName = user.name;
				}
				dto.setUpdateBy(userName);
			}
			SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(sysUseStatus.getUpdateTime() != null){
				dto.setUpdateTime(sdft.format(sysUseStatus.getUpdateTime()));
			}
			pDtos.add(dto);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "");
		Map<String, Serializable> maprow = new HashMap<String, Serializable>();
		maprow.put("rows", pDtos);
		maprow.put("total", pDtos.size());
		map.put("result", maprow);
		return map;
	}
	
	/**
	 * 新增卡口信息
	 */
	@Override
	@RequestMapping(value = "/addBayonet",method = RequestMethod.POST)
	@ApiOperation(value="addBayonet",notes="创建卡口设备",httpMethod="POST")
	public String goAddBayonet(@ModelAttribute("form") BayonetDeviceDto form) throws Exception {
		//根据设备编号查询是否存在该设备
		SystemCriteria criteria = new SystemCriteria();
		criteria.deviceSysNbr = form.getDeviceSysNbr();
		List<Sys> list = systemService.findSys(criteria);
		String deviceId = "";
		if(list == null || list.size() == 0){
			deviceId = addBayonet(form);
			if("keyError".equals(deviceId) == false){
				systemService.deviceSysChanged();
			}
		}else{
			deviceId = "nbrError";//设备编号重复标识
		}
		return deviceId;
	}
	
	public String addBayonet(BayonetDeviceDto form) throws Exception {
		String deviceId = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Sys sys = new Sys();//卡口基本信息对象
		ObjectMapUtils.parseObject(sys, form);//将rest层对象转换成领域层对象
		if(!StringUtil.isNullOrEmpty(form.getInstallDate())){
			sys.setInstallDate(sdf.parse(form.getInstallDate()));//安装日期
		}
		if(!StringUtil.isNullOrEmpty(form.getLongitude())){
			sys.setLongitude(new BigDecimal(form.getLongitude()));//经度
		}
		if(!StringUtil.isNullOrEmpty(form.getLatitude())){
			sys.setLatitude(new BigDecimal(form.getLatitude()));//纬度
		}
		if(!StringUtil.isNullOrEmpty(form.getDevicePort())){
			sys.setDevicePort(Long.parseLong(form.getDevicePort()));//设备端口;
		}
		if(!StringUtil.isNullOrEmpty(form.getOrgId())){
			Organization org = dutyService.organizationOfId(form.getOrgId());
			if(!StringUtil.isNullOrEmpty(org.orgPrivilegeCode)){
				sys.setOrgPrivilegeCode(org.orgPrivilegeCode);//机构过滤code   
			}else{
				sys.setOrgPrivilegeCode(form.getCurrentOrgPrivilegeCode());//登录用户所在机构过滤code
			}
		}
		if("1".equals(form.getIsIndustrialControl())){//1表示是工控式卡口
			sys.setArchitecture("1");//设置网络体系结构为主从式
		}else{
			sys.setArchitecture("2");//设置网络体系结构为分布式
		}
		sys.setUseStatusFlag("0");//使用状态标识,默认0表示未启用
		sys.setDeviceType("01");//默认设备类型为卡口
		sys.setCreateTime(new Date());//创建时间设置为系统当前时间
		sys.setCreateBy(form.getCurrentUserId());//设置创建人为当前用户
		//卡口参数对象
		TollgateSys sysParam = new TollgateSys();
		ObjectMapUtils.parseObject(sysParam, form);
		deviceId = systemService.createSystem(sys, sysParam);
		//添加部件信息
		String cameraInfoList = form.getCameraInfoList();//获取服务器组件
		if(!StringUtil.isNullOrEmpty(cameraInfoList) && ("[]").equals(cameraInfoList) == false){
			List<CameraInfoDto> lst = JSON.parseArray(cameraInfoList,CameraInfoDto.class);//将字符串转list	
			for (int i = 0; i < lst.size(); i++) {
				SysComponent sysComponent = new SysComponent();
				ObjectMapUtils.parseObject(sysComponent, lst.get(i));
				sysComponent.setDeviceId(deviceId);
				sysComponent.setOnlineStatus(null);//在线状态
				sysComponent.setStatusUpdateTime(new Date());//设备状态更新时间
				String deviceKey = lst.get(i).getDeviceKey();
				SysComponent sysComponents = new SysComponent();
				if(!StringUtil.isNullOrEmpty(deviceKey)){
					sysComponents = systemAttachService.componentOfDeviceKey(deviceKey);
					if(sysComponents == null){
						systemAttachService.createSysComponent(sysComponent);
					}else{
						throw new Exception(); 
					}
				}else{
					systemAttachService.createSysComponent(sysComponent);
				}
			}
		}
		return deviceId;
	}
	/**
	 * 删除卡口信息
	 */
	@Override
	@RequestMapping(value = "/deleteBayonet",method = RequestMethod.POST)
	@ApiOperation(value="deleteBayonet",notes="删除卡口设备",httpMethod="POST")
	public int goDeleteBayonet(@ApiParam(value="需要删除的卡口ID") @RequestParam("deviceId") String deviceId) throws Exception {
		int flag = deleteBayonet(deviceId);
		if(flag == 1){
			systemService.deviceSysChanged();
		}
		return flag;
	}
	public int deleteBayonet(String deviceId) throws Exception {
		systemService.removeSystem(deviceId);//删除设备基本信息
		return 1;
	}
	
	/**
	 * 编辑卡口信息
	 */
	@Override
	@RequestMapping(value = "/updateBayonet",method = RequestMethod.POST)
	@ApiOperation(value="updateBayonet",notes="编辑卡口设备",httpMethod="POST")
	public int goUpdateBayonet(@ModelAttribute("form") BayonetDeviceDto form) throws Exception {
		int flag = 0;//卡口编号重复标识
		if(form.getDeviceSysNbr().equals(form.getOldDeviceSysNbr())){
			flag = updateBayonet(form);
		}else{
			SystemCriteria criteria = new SystemCriteria();
			criteria.deviceSysNbr = form.getDeviceSysNbr();
			List<Sys> list = systemService.findSystems(criteria);
			if(list == null || list.size() == 0){
				flag = updateBayonet(form);
			}
		}
		if(flag == 1){
			systemService.deviceSysChanged();
		}
		return flag;
	}
	public int updateBayonet(BayonetDeviceDto form) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Sys sys = new Sys();
		ObjectMapUtils.parseObject(sys, form);
		if(!StringUtil.isNullOrEmpty(form.getInstallDate())){
			sys.setInstallDate(sdf.parse(form.getInstallDate()));//安装日期
		}
		if(!StringUtil.isNullOrEmpty(form.getLongitude())){
			sys.setLongitude(new BigDecimal(form.getLongitude()));//经度
		}
		if(!StringUtil.isNullOrEmpty(form.getLatitude())){
			sys.setLatitude(new BigDecimal(form.getLatitude()));//纬度
		}
		if(!StringUtil.isNullOrEmpty(form.getDevicePort())){
			sys.setDevicePort(Long.parseLong(form.getDevicePort()));//设备端口;
		}
		if(!StringUtil.isNullOrEmpty(form.getOrgId())){
			Organization org = dutyService.organizationOfId(form.getOrgId());
			if(!StringUtil.isNullOrEmpty(org.orgPrivilegeCode)){
				sys.setOrgPrivilegeCode(org.orgPrivilegeCode);//机构过滤code   
			}else{
				sys.setOrgPrivilegeCode(form.getCurrentOrgPrivilegeCode());//登录用户所在机构过滤code
			}
		}
		if("1".equals(form.getIsIndustrialControl())){//1表示是工控式卡口
			sys.setArchitecture("1");//设置网络体系结构为主从式
		}else{
			sys.setArchitecture("2");//设置网络体系结构为分布式
		}
		sys.setUpdateTime(new Date());//更新时间设置为系统当前时间
		sys.setUpdateBy(form.getCurrentUserId());//设置更新人为当前用户
		sys.setStatusUpdateTime(new Date());//设备状态更新时间设置为系统当前时间
		systemService.updateSystem(sys);
		//编辑参数信息
		TollgateSys sysParam = new TollgateSys();
		ObjectMapUtils.parseObject(sysParam, form);
		systemService.updateSystem(sysParam);
		return 1;
	}

	/**
	 * 根据卡口设备Id查询卡口信息以及参数信息、相机信息
	 * @throws Exception 
	 */
	@Override
	@RequestMapping(value = "/queryBayonetByDeviceId",method = RequestMethod.POST)
	@ApiOperation(value="queryBayonetByDeviceId",notes="根据卡口设备Id查询卡口信息以及参数信息、相机信息",httpMethod="POST")
	public BayonetDeviceDto queryBayonetByDeviceId(@ApiParam(value="查询卡口设备对应卡口ID") @RequestParam("deviceId") String deviceId) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		BayonetDeviceDto bayonet = new BayonetDeviceDto();
		Sys sys =new Sys();
		sys = systemService.systemOfId(deviceId);
		ObjectMapUtils.parseObject(bayonet, sys);
		if("1".equals(sys.getArchitecture())){//网络体系结构1表示是主从式,2表示分布式
			bayonet.setIsIndustrialControl("1");//工控式
		}else if("2".equals(sys.getArchitecture())){
			bayonet.setIsIndustrialControl("2");//非工控式
		}
		String siteId = sys.getSiteId();
		if(!StringUtil.isNullOrEmpty(siteId)){
			String roadId = siteService.siteOfId(siteId).getRoadId();
			if(!StringUtil.isNullOrEmpty(roadId)){
				bayonet.setRoadId(roadId);
			}
		}
		if(sys.getDevicePort() != null){
			bayonet.setDevicePort(sys.getDevicePort().toString());//设备端口
		}
		if(sys.getInstallDate() != null){
			bayonet.setInstallDate(sdf.format(sys.getInstallDate()));
		}
		if(sys.getLatitude() != null){
			bayonet.setLatitude(sys.getLatitude().toString());
		}
		if(sys.getLongitude() != null){
			bayonet.setLongitude(sys.getLongitude().toString());
		}
		/** 卡口参数 **/ 
		TollgateSys tollgateSys = new TollgateSys(); 
		tollgateSys = systemService.tollgateSysOfId(deviceId);
		ObjectMapUtils.parseObject(bayonet, tollgateSys);
		return bayonet;
	}
	
	/**
	 * 启用设备
	 * @param reason 原因
	 * @param deviceIdStr 多个设备Id字符串
	 * @param recordTypeStr 装备或设备标记(与多个设备Id字符串对应)
	 * @param request 包含currentUserId参数的Http请求
	 * @return 1:全部成功
	 * @throws Exception 异常
	 */
	@Override
	@RequestMapping(value = "/enableDevice",method = RequestMethod.POST)
	@ApiOperation(value="goEnableDevice",notes="启用卡口设备",httpMethod="POST")
	public int goEnableDevice(@ApiParam(value="启用卡口设备的原因") @RequestParam("reason") String reason,@ApiParam(value="启用多个设备的ID字符串") @RequestParam("deviceIdStr") String deviceIdStr,@ApiParam(value="装备或设备标记(与多个设备Id字符串对应)") @RequestParam("recordTypeStr") String recordTypeStr,@ApiParam(value="包含currentUserId参数的Http请求")  HttpServletRequest request) throws Exception {
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
	 * @param reason 原因
	 * @param deviceIdStr 多个设备Id字符串
	 * @param recordTypeStr 装备或设备标记(与多个设备Id字符串对应)
	 * @param request 包含currentUserId参数的Http请求
	 * @return 1:全部成功
	 * @throws Exception 异常
	 */
	@Override
	@RequestMapping(value = "/stopDevice",method = RequestMethod.POST)
	@ApiOperation(value="goStopDevice",notes="停用卡口设备",httpMethod="POST")
	public int goStopDevice(@ApiParam(value="停用卡口设备的原因") @RequestParam("reason") String reason,@ApiParam(value="启用多个设备的ID字符串") @RequestParam("deviceIdStr") String deviceIdStr,@ApiParam(value="装备或设备标记(与多个设备Id字符串对应)") @RequestParam("recordTypeStr") String recordTypeStr,@ApiParam(value="包含currentUserId参数的Http请求")  HttpServletRequest request) throws Exception {
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
	 * @param reason 原因
	 * @param deviceIdStr 多个设备Id字符串
	 * @param recordTypeStr 装备或设备标记(与多个设备Id字符串对应)
	 * @param request 包含currentUserId参数的Http请求
	 * @return 1:全部成功
	 * @throws Exception 异常
	 */
	@Override
	@RequestMapping(value = "/breakDevice",method = RequestMethod.POST)
	@ApiOperation(value="goBreakDevice",notes="报废卡口设备",httpMethod="POST")
	public int goBreakDevice(@ApiParam(value="报废卡口设备的原因") @RequestParam("reason") String reason,@ApiParam(value="启用多个设备的ID字符串") @RequestParam("deviceIdStr") String deviceIdStr,@ApiParam(value="装备或设备标记(与多个设备Id字符串对应)") @RequestParam("recordTypeStr") String recordTypeStr,@ApiParam(value="包含currentUserId参数的Http请求")  HttpServletRequest request) throws Exception {
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
	 * 根据设备ID去删除设备下的所有相机信息
	 * @param deviceId 设备ID
	 * @return success : 删除成功
	 * @throws Exception 异常
	 */
	@Override
	@RequestMapping(value = "/deleteCameraByDeviceId",method = RequestMethod.POST)
	@ApiOperation(value="deleteCameraByDeviceId",notes="设备的ID",httpMethod="POST")
	public String deleteCameraByDeviceId(@ApiParam(value="设备的ID") @RequestParam("deviceId") String deviceId)  throws Exception {
		systemAttachService.removeComponentsOfDevice(deviceId);
		return "success";
	}
	
	
	/**
	 * 根据点位code，查找出所有卡口设备
	 * @param siteCode 点位code
	 * @param directionType 方向类型
	 * @return 所有卡口设备
	 * @throws Exception  异常
	 */
	@Override
	@RequestMapping(value = "/queryBayonetBySiteCode",method = RequestMethod.POST)
	@ApiOperation(value="queryBayonetBySiteCode",notes="根据点位code，查找出所有卡口设备",httpMethod="POST")
	public List<BayonetDeviceDto> queryBayonetBySiteCode(@RequestParam("siteCode") String siteCode,@RequestParam("directionType") String directionType) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SystemCriteria criteria = new SystemCriteria();
		criteria.deviceType = "01";//卡口
		//根据点位code查点位信息
		Site site = siteService.siteOfCode(siteCode);
		String sectionId = "";
		if(site != null){
			//根据点位ID查断面信息
			List<Section> l = siteService.sectionsOfSite(site.getSiteId());
			if(l != null && l.size() != 0){
				for (int i = 0; i < l.size(); i++) {
					if(directionType.equals(l.get(i).getDirectionType())){
						sectionId = l.get(i).getSectionId();
					}
				}
			}
			criteria.siteId = site.getSiteId();
		}
		//根据点位ID查询所有卡口
		List<Sys> lst = systemService.findSys(criteria);
		List<BayonetDeviceDto> list = new ArrayList<BayonetDeviceDto>();
		for (int i = 0; i < lst.size(); i++) {
			BayonetDeviceDto bayonet = new BayonetDeviceDto();
			Sys sys = lst.get(i);
			if(sys != null){
				String sectionIds = sys.getSectionIdList();//获取设备关联的断面ID
				if(!StringUtil.isNullOrEmpty(sectionIds)){
					String[] sectionIdsz = sectionIds.split(",");
					List<String> tempList = Arrays.asList(sectionIdsz);//将断面ID字符形成list
					if(tempList.contains(sectionId)){//判断该设备的断面是否包含我所查的断面
						ObjectMapUtils.parseObject(bayonet, sys);
						if("1".equals(sys.getArchitecture())){//网络体系结构1表示是主从式,2表示分布式
							bayonet.setIsIndustrialControl("1");//工控式
						}else if("2".equals(sys.getArchitecture())){
							bayonet.setIsIndustrialControl("2");//非工控式
						}
						if(sys.getDevicePort() != null){
							bayonet.setDevicePort(sys.getDevicePort().toString());//设备端口
						}
						if(sys.getInstallDate() != null){
							bayonet.setInstallDate(sdf.format(sys.getInstallDate()));
						}
						if(sys.getEnableUpdateDate() != null){
							bayonet.setEnableUpdateDate(sdfs.format(sys.getEnableUpdateDate()));
						}
						if(sys.getStatusUpdateTime() != null){
							bayonet.setStatusUpdateTime(sdfs.format(sys.getStatusUpdateTime()));
						}
						if(sys.getUpdateTime() != null){
							bayonet.setUpdateTime(sdfs.format(sys.getUpdateTime()));
						}
						if(sys.getLatitude() != null){
							bayonet.setLatitude(sys.getLatitude().toString());
						}
						if(sys.getLongitude() != null){
							bayonet.setLongitude(sys.getLongitude().toString());
						}
						/** 卡口参数 **/ 
						TollgateSys tollgateSys = new TollgateSys(); 
						tollgateSys = systemService.tollgateSysOfId(sys.getDeviceId());
						ObjectMapUtils.parseObject(bayonet, tollgateSys);
						list.add(bayonet);
					}
				}
			}
		}
		return list;
	}
	
}
