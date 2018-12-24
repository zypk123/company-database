package cy.its.device.rest.action.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.com.constant.SysCodeConstant;
import cy.its.com.util.DateUtil;
import cy.its.com.util.ExcelUtil;
import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.CertificationCriteria;
import cy.its.device.domain.criteria.ContractorCriteria;
import cy.its.device.domain.criteria.SiteCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.Certification;
import cy.its.device.domain.model.Contractor;
import cy.its.device.domain.model.DeviceFault;
import cy.its.device.domain.model.DeviceSysCapability;
import cy.its.device.domain.model.Meteorologic;
import cy.its.device.domain.model.Photo;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.TollgateSys;
import cy.its.device.domain.model.VioDevice;
import cy.its.device.domain.model.site.Section;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.IContractService;
import cy.its.device.domain.service.IDevMaintainService;
import cy.its.device.domain.service.ISiteService;
import cy.its.device.domain.service.ISystemAttachService;
import cy.its.device.domain.service.ISystemService;
import cy.its.device.rest.action.IDeviceMoniterAction;
import cy.its.device.rest.dto.CertificationInfoDto;
import cy.its.device.rest.dto.CompanyDto;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.DevTreeForDeviceMoniterDto;
import cy.its.device.rest.dto.DeviceClusterDto;
import cy.its.device.rest.dto.DeviceForMapDto;
import cy.its.device.rest.dto.DeviceQryForMapDto;
import cy.its.device.rest.dto.DeviceRunMonitorDto;
import cy.its.device.rest.dto.DeviceRunMonitorQryDto;
import cy.its.device.rest.dto.MapPointDto;
import cy.its.device.rest.dto.NameAndValueDto;
import cy.its.device.rest.dto.Results;
import cy.its.platform.common.utils.Log4jFactoryProxy;
import cy.its.platform.common.utils.RedisPoolUtil;
import cy.its.road.domain.criteria.RoadCriteria;
import cy.its.road.domain.model.road.Road;
import cy.its.road.domain.service.IRoadService;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.syscfg.domain.service.ISysCodeService;

/**
 * @ClassName: DeviceMoniterAction
 * @Description: 基于地图的设备监控rest服务
 * @author chenzy@cychina.cn
 * @date 2015年10月16日 下午4:29:14
 *
 */
@RestController
@RequestMapping("/device/deviceMoniter")
public class DeviceMoniterAction implements IDeviceMoniterAction {

	// cy.its.common.duplex.mapData.DeviceStatusMapData中load方法，用于存放最新的设备状态缓存
	private static final String DEVICE_STATUS_REDIS_KEY = "deviceStatus";
	@Autowired
	ISystemService systemService;
	@Autowired
	ISiteService siteService;
	@Autowired
	IDutyService dutyService;
	@Autowired
	ISysCodeService sysCodeService;
	@Autowired
	ISystemAttachService systemAttachService;
	@Autowired
	IContractService contractService;

	@Autowired
	IRoadService roadService;
	@Autowired
	IDevMaintainService devMaintainService;

	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected HttpServletRequest request;

	private Log4jFactoryProxy log4jFactoryProxy = Log4jFactoryProxy.getSingleton(DeviceMoniterAction.class);

	/**
	 * 获取机构驻地经纬度信息，格式为：经度值,纬度值
	 * 
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getOrgLonLatByOrgId")
	public String getOrgLonLatByOrgId(String orgId) throws Exception {
		Organization organization = dutyService.organizationOfId(orgId);
		if (organization == null) {
			return "";
		}
		if (organization.orgSeat == null) {
			return "";
		}
		if (organization.orgSeat.orgLongitude != null && organization.orgSeat.orgLatitude != null) {
			if (organization.orgSeat.orgLongitude.equals(0) || organization.orgSeat.orgLatitude.equals(0)) {
				return "";
			}
			return organization.orgSeat.orgLongitude.toString() + "," + organization.orgSeat.orgLatitude.toString();
		} else {
			return "";
		}
	}

	/**
	 * 获取点位经纬度信息，格式为：经度值,纬度值
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSiteLonLatBySiteId")
	public String getSiteLonLatBySiteId(String siteId) throws Exception {
		Site site = siteService.siteOfId(siteId);
		if (site == null) {
			return "";
		}

		if (site.getSiteLongitude() != null && site.getSiteLatitude() != null) {
			if (site.getSiteLongitude().equals(0) || site.getSiteLatitude().equals(0)) {
				return "";
			}
			return site.getSiteLongitude().toString() + "," + site.getSiteLatitude().toString();
		} else {
			return "";
		}
	}

	/**
	 * queryContrator @Title: queryContrator @Description: 查询设备厂商 @param @return
	 * 设定文件 @return List<CompanyDto> 返回类型 @throws
	 */
	@Override
	@RequestMapping(value = "/queryContrator")
	public List<CompanyDto> queryContrator() {
		List<CompanyDto> cmpLst = new ArrayList<CompanyDto>();
		ContractorCriteria criteria = new ContractorCriteria();
		List<Contractor> contractors = contractService.findContractors(criteria);// 查询厂商
		if (contractors != null && contractors.size() > 0) {
			for (int i = 0; i < contractors.size(); i++) {
				CompanyDto company = new CompanyDto();
				company.setContractorId(contractors.get(i).getContractorId());
				company.setContractorName(contractors.get(i).getContractorName());
				cmpLst.add(company);
			}
		}
		return cmpLst;
	}

	@Override
	@RequestMapping(value = "/queryRunDeviceLst")
	public DataGridResult<DeviceRunMonitorDto> queryRunDeviceLst(@ModelAttribute("form") DeviceRunMonitorQryDto form)
			throws Exception {
		DataGridResult<DeviceRunMonitorDto> grdResult = new DataGridResult<DeviceRunMonitorDto>();

		Results<DeviceRunMonitorDto> res = new Results<DeviceRunMonitorDto>();
		res.setTotal(0);
		res.setRows(new ArrayList<DeviceRunMonitorDto>());
		grdResult.setErro("");
		grdResult.setResult(res);

		List<DeviceRunMonitorDto> lstDbRes = new ArrayList<DeviceRunMonitorDto>();

		SystemCriteria criteria = getSystemCriteria(form);
		List<Sys> dbSys = qryDevFromDB(criteria);
		if (dbSys == null || dbSys.size() == 0) {
			return grdResult;
		}
		Map<String, String> cacheSys = null;
		synchronized (DeviceMoniterAction.class) {
			cacheSys = RedisPoolUtil.hgetAll(DEVICE_STATUS_REDIS_KEY);
		}
		List<DeviceFault> allDeviceFaultLst = devMaintainService.findDevLatestFaultsMataince(criteria);
		for (Sys sys : dbSys) {
			String cacheDev = getCacheDev(cacheSys, sys.getDeviceId());
			lstDbRes.add(convertToDeviceRunMonitorDto(sys, cacheDev, allDeviceFaultLst));
		}
		List<DeviceRunMonitorDto> rightLst = filterReaTimeCondition(form, lstDbRes);
		if (rightLst == null || rightLst.size() == 0) {
			return grdResult;
		} else {
			res.setTotal(rightLst.size());
			// if (rightLst.size() > form.getPageSize()) {
			// res.setRows(rightLst.subList(0, form.getPageSize()));
			// } else {
			// res.setRows(rightLst);
			// }
			// 不需要进行翻页，显示所有的设备，总数不会太多
			res.setRows(rightLst);
		}
		return grdResult;
	}

	@RequestMapping(value = "/exportRunStatusRecordExcel")
	@ApiOperation(value = "exportRunStatusRecordExcel", notes = "根据传值查询设备运行状态记录信息 生成execl，返回下载路径", httpMethod = "GET")
	public Object exportRunStatusRecordExcel(@ModelAttribute("form") DeviceRunMonitorQryDto form) throws Exception {
		OutputStream outputStream = null;
		JSONObject obj = new JSONObject();
		try {
			DataGridResult<DeviceRunMonitorDto> runStatusList = queryRunDeviceLst(form);
			if (runStatusList != null && runStatusList.getResult().getRows().size() > 0) {
				List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
				for (DeviceRunMonitorDto dic : runStatusList.getResult().getRows()) {
					if (dic == null) {
						continue;
					}
					Map<String, String> map = new HashMap<String, String>();// 将行记录转换为map
					Class<?> cls = dic.getClass();
					Field[] fields = cls.getDeclaredFields();
					for (Field field : fields) {
						String name = field.getName();
						String strGet = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
						Method methodGet = cls.getDeclaredMethod(strGet);
						Object object = methodGet.invoke(dic);
						String value = object != null ? object.toString() : "";
						map.put(name, value);
					}
					mapList.add(map);
				}
				String[] headers = new String[] { "设备编号", "设备类型", "安装地点", "校时", "网络", "供电", "设备故障", "最近上传", "维护信息" };
				String[] fieldNameArr = new String[] { "deviceSysNbr", "deviceTypeName", "address", "correctTime",
						"netStatus", "elcStatus", "devBug", "latestUploadTimeFormat", "matainceInfo" };
				String excelName = "设备运行监控状态记录" + DateUtil.parseFormatDate(new Date(), "yyyyMMddHHmmssS") + ".xlsx";
				File file = new File(request.getServletContext().getRealPath("/FileDir") + "/"
						+ DateUtil.parseFormatDate(new Date(), "yyyyMMdd") + "/" + excelName);
				String ip = getRealIp();
				String basePath = request.getScheme() + "://" + ip + ":" + request.getServerPort();
				Map<String, String> title = getTitleAndParam(form);
				File fileDir = file.getParentFile();
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				outputStream = new FileOutputStream(file);
				obj.put("url", basePath + request.getContextPath() + "/FileDir/"
						+ DateUtil.parseFormatDate(new Date(), "yyyyMMdd") + "/" + excelName);
				ExcelUtil.exportExcel(mapList, fieldNameArr, headers, outputStream, title);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return obj;

	}

	private Map<String, String> getTitleAndParam(DeviceRunMonitorQryDto form) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", "设备运行监控实时情况导出一览");
		DeviceRunMonitorQryDto dto = new DeviceRunMonitorQryDto();
		ObjectMapUtils.parseObject(dto, form);
		String queryParam = "";
		if (dto.getDevType() != "") {
			Code code = sysCodeService.codeOfId(SysCodeConstant.DEVICE_TYPE, dto.getDevType());
			queryParam += "设备类型：" + code.codeName + "，";
		}
		if (dto.getDevName() != "") {
			queryParam += "设备名称：" + dto.getDevName() + "，";
		}
		if (dto.getContractor() != "") {
			Contractor contractor = contractService.getContractorById(dto.getContractor());
			queryParam += "设备厂家：" + contractor.getContractorName() + "，";
		}
		if (dto.getOwnership() != "") {
			Code code = sysCodeService.codeOfId("419", dto.getOwnership());
			queryParam += "建设归属：" + code.codeName + "，";
		}
		if (dto.getBugType() != "") {
			Code code = sysCodeService.codeOfId("442", dto.getBugType());
			queryParam += "故障类型：" + code.codeName + "，";
		}
		if (!dto.getCorrectTime().toString().equals("-1")) {
			queryParam += "校时情况：" + (dto.getCorrectTime() == "0" ? "异常" : "正常") + "，";
		} else {
			queryParam += "校时情况：" + "正常、异常，";
		}
		if (!dto.getNetStatus().toString().equals("-1")) {
			queryParam += "网络状态：" + (dto.getNetStatus() == "0" ? "离线" : "在线") + "，";
		} else {
			queryParam += "网络状态：" + "在线、离线，";
		}
		if (!dto.getElcStatus().toString().equals("-1")) {
			queryParam += "供电状态：" + (dto.getElcStatus() == "0" ? "断电" : "正常") + "，";
		} else {
			queryParam += "供电状态：" + "正常、断电，";
		}
		if (!dto.getDevBug().toString().equals("-1")) {
			queryParam += "设备故障：" + (dto.getDevBug() == "0" ? "无" : "有") + "，";
		} else {
			queryParam += "设备故障：" + "有、无，";
		}
		if (!dto.getMatainceStatus().equals("-1")) {
			queryParam += "维护状态：" + (dto.getMatainceStatus() == "0" ? "未派单" : "已派单") + "，";
		} else {
			queryParam += "维护状态：" + "未派单、已派单，";
		}
		if (queryParam.length() >= 1) {
			queryParam = queryParam.substring(0, queryParam.length() - 1);
		}
		map.put("param", queryParam);
		return map;
	}

	/**
	 * 获取真实的IP地址
	 * 
	 * @return
	 * @throws SocketException
	 */
	public String getRealIp() throws SocketException {
		String localIP = null;// 本地IP，如果没有配置外网IP则返回它
		String netIP = null;// 外网IP

		// 返回此机器上的所有接口
		Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		boolean finded = false;// 是否找到外网IP
		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netIP = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localIP = ip.getHostAddress();
				}
			}
		}

		if (netIP != null && !"".equals(netIP)) {
			return netIP;
		} else {
			return localIP;
		}
	}

	private List<DeviceRunMonitorDto> filterReaTimeCondition(DeviceRunMonitorQryDto form,
			List<DeviceRunMonitorDto> dto) {
		Stream<DeviceRunMonitorDto> temSm = dto.stream();
		if (!form.getCorrectTime().equals("-1")) {
			temSm = temSm.filter(p -> p.getCorrectTimeFlag().equals(form.getCorrectTime()));
		}
		if (!form.getNetStatus().equals("-1")) {
			temSm = temSm.filter(p -> p.getNetStatusFlag().equals(form.getNetStatus()));
		}
		if (!form.getElcStatus().equals("-1")) {
			temSm = temSm.filter(p -> p.getElcStatusFlag().equals(form.getElcStatus()));
		}
		if (!form.getDevBug().equals("-1")) {
			temSm = temSm.filter(p -> p.getDevBugFlag().equals(form.getDevBug()));
		}
		if (!StringUtil.isNullOrEmpty(form.getBugType())) {

			// temSm = temSm.filter(p -> p.getBugType() != null &&
			// p.getBugType().contains(form.getBugType()));
			// 上面注释的是原先的做法，现在改为下面的做法2016.4.22
			temSm = temSm.filter(p -> {
				try {
					List<String> bugLst = p.getBugType().stream().filter(q -> q.startsWith(form.getBugType()))
							.collect(Collectors.toList());
					if (bugLst != null && bugLst.size() > 0) {
						return true;
					}
					return false;
				} catch (Exception e) {
					log4jFactoryProxy
							.error("DeviceMoniterAction.filterReaTimeCondition方法异常：" + p.getDeviceId() + "设备的故障类型为空！");
					return false;
				}
			});

		}
		// 维护信息
		if (!form.getMatainceStatus().equals("-1")) {
			temSm = temSm.filter(p -> p.getMantainceFlag().equals(form.getMatainceStatus()));
		}
		return temSm.collect(Collectors.toList());

	}

	private String getCacheDev(Map<String, String> cacheSys, String devId) {
		if (cacheSys == null || cacheSys.size() == 0) {
			return null;
		}
		return cacheSys.get(devId);
	}

	private DeviceRunMonitorDto convertToDeviceRunMonitorDto(Sys sys, String cacheDevStr,
			List<DeviceFault> allDeviceFaultLst) throws Exception {
		DeviceRunMonitorDto deviceRunMonitorDto = new DeviceRunMonitorDto();
		JSONObject jsonObject = null;
		if (!StringUtil.isNullOrEmpty(cacheDevStr)) {
			jsonObject = JSONObject.parseObject(cacheDevStr);
		}
		deviceRunMonitorDto.setDeviceId(sys.getDeviceId());
		deviceRunMonitorDto.setDeviceSysNbr(sys.getDeviceSysNbr());
		deviceRunMonitorDto.setDeviceType(sys.getDeviceType());
		Code code = sysCodeService.codeOfId(SysCodeConstant.DEVICE_TYPE, sys.getDeviceType());
		if (code != null) {
			deviceRunMonitorDto.setDeviceTypeName(code.codeName);
		}
		String address = "";
		String siteId = sys.getSiteId();
		Site site = siteService.siteOfId(siteId);
		if (site != null) {
			String roadId = site.getRoadId();
			Road road = roadService.roadOfId(roadId);
			if (road != null) {
				address += road.getRoadName();
			}
			address += site.getSiteName();
		}
		deviceRunMonitorDto.setAddress(address);
		deviceRunMonitorDto.setCorrectTime("-");
		deviceRunMonitorDto.setCorrectTimeFlag("-");
		deviceRunMonitorDto.setNetStatus("-");
		deviceRunMonitorDto.setNetStatusFlag("-");
		deviceRunMonitorDto.setElcStatus("-");
		deviceRunMonitorDto.setElcStatusFlag("-");
		deviceRunMonitorDto.setDevBug("-");
		deviceRunMonitorDto.setDevBugFlag("-");
		deviceRunMonitorDto.setLatestHeartTime(null);
		deviceRunMonitorDto.setLatestUploadTime(null);
		deviceRunMonitorDto.setMantaince(null);
		deviceRunMonitorDto.setMatainceInfo("-");
		deviceRunMonitorDto.setBugType(null);
		deviceRunMonitorDto.setMantainceFlag("-");
		deviceRunMonitorDto.setLatestUploadTimeFormat("-");

		if (jsonObject != null) {
			Integer diff = jsonObject.getInteger("timeDiff");
			if (diff != null) {
				if (Math.abs(diff) >= 2) {// 如果时间偏差+-2秒，则显示偏差情况
					deviceRunMonitorDto.setCorrectTime("偏差" + diff + "秒");
					deviceRunMonitorDto.setCorrectTimeFlag("0");
				} else {
					deviceRunMonitorDto.setCorrectTime("正常");
					deviceRunMonitorDto.setCorrectTimeFlag("1");
				}
			}
			Integer status = jsonObject.getInteger("statusType");
			if (status != null) {
				// 2表示离线，3表示故障,1表示正常，4表示异常
				if (status == 2) {// 离线的设备
					deviceRunMonitorDto.setNetStatus("离线");
					deviceRunMonitorDto.setNetStatusFlag("0");
				} else {
					deviceRunMonitorDto.setNetStatus("在线");
					deviceRunMonitorDto.setNetStatusFlag("1");
				}
			}
			// 定义设备的故障列表
			List<String> faultDetailsLst = getFaultDetails(jsonObject);
			// 默认供电正常
			deviceRunMonitorDto.setElcStatus("正常");
			deviceRunMonitorDto.setElcStatusFlag("1");
			// 默认无故障
			deviceRunMonitorDto.setDevBug("无");
			deviceRunMonitorDto.setDevBugFlag("0");
			if (faultDetailsLst != null && faultDetailsLst.size() > 0) {
				// 如果故障列表不为空，则设为有故障
				deviceRunMonitorDto.setDevBug("有");
				deviceRunMonitorDto.setDevBugFlag("1");
				// 以前的做法
				// if (faultDetailsLst.contains("10005")) {
				// deviceRunMonitorDto.setElcStatus("断电");
				// deviceRunMonitorDto.setElcStatusFlag("0");
				// }
				// 13打头的表示电源故障2016.4.22
				List<String> hasElcBugLst = faultDetailsLst.stream().filter(p -> p.startsWith("13"))
						.collect(Collectors.toList());
				if (hasElcBugLst != null && hasElcBugLst.size() > 0) {
					deviceRunMonitorDto.setElcStatus("断电");
					deviceRunMonitorDto.setElcStatusFlag("0");
				}
				deviceRunMonitorDto.setBugType(faultDetailsLst);
				// 维护信息,查询该设备所有的这些故障在故障表中处理状态情况，
				// 根据故障类型、处理状态进行分组，处理状态（0 未处理、1 无需处理、2 未解决、3 已解决）
				List<DeviceFault> faultLst = getOneDeviceFault(allDeviceFaultLst, sys.getDeviceId(), faultDetailsLst);
				if (faultLst != null && faultLst.size() > 0) {
					Map<String, String> matainceMap = new HashMap<String, String>();
					Map<String, List<DeviceFault>> gpMap = faultLst.stream()
							.collect(Collectors.groupingBy(DeviceFault::getFaultType));
					Set<String> faultTypeKeys = gpMap.keySet();
					for (String str : faultTypeKeys) {
						matainceMap.put(str, judgeFaultMatainceStatus(gpMap.get(str)));
					}
					deviceRunMonitorDto.setMantaince(matainceMap);
					if (matainceMap != null && matainceMap.size() > 0) {
						List<String> noMataince = new ArrayList<String>();// 未派单
						List<String> hasMataince = new ArrayList<String>();// 已派单
						List<String> notMataince = new ArrayList<String>();// 无需维护
						for (Map.Entry<String, String> m : matainceMap.entrySet()) {
							String name = sysCodeService.codeOfId("442", m.getKey()).codeName;
							if (name != "") {
								if (m.getValue() == "0") {
									noMataince.add(name);
								} else if (m.getValue() == "1") {
									hasMataince.add(name);
								} else if (m.getValue() == "2") {
									notMataince.add(name);
								}
							}
						}
						String matainceString = "";
						if (noMataince.size() > 0) {
							matainceString = "未派单：" + listToString(noMataince);
						}
						if (hasMataince.size() > 0) {
							matainceString += "已派单：" + listToString(hasMataince);
						}
						if (notMataince.size() > 0) {
							matainceString += "无需维护：" + listToString(notMataince);
						}
						if (matainceString != "") {
							deviceRunMonitorDto.setMatainceInfo(matainceString);
						}
					}
					if (matainceMap.values().contains("0")) {
						deviceRunMonitorDto.setMantainceFlag("0");
					} else if (matainceMap.values().contains("1")) {
						deviceRunMonitorDto.setMantainceFlag("1");
					} else {
						deviceRunMonitorDto.setMantainceFlag("2");// 无需维护
					}
				}
				deviceRunMonitorDto.setLatestHeartTime(jsonObject.getDate("statusUpdateTime"));
				deviceRunMonitorDto.setLatestUploadTime(jsonObject.getDate("lastUploadTime"));
				if (jsonObject.getDate("lastUploadTime") != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					deviceRunMonitorDto.setLatestUploadTimeFormat(sdf.format(jsonObject.getDate("lastUploadTime")));
				}
			}
		}
		return deviceRunMonitorDto;
	}

	private List<DeviceFault> getOneDeviceFault(List<DeviceFault> allDeviceFaultLst, String deviceId,
			List<String> faultTypeLst) {
		if (allDeviceFaultLst == null || allDeviceFaultLst.size() == 0) {
			return null;
		}
		return allDeviceFaultLst.stream()
				.filter(p -> p.getDeviceId().equals(deviceId) && faultTypeLst.contains(p.getFaultSubType()))
				.collect(Collectors.toList());
	}

	private String listToString(List<String> stringList) {
		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append("、");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}

	private String judgeFaultMatainceStatus(List<DeviceFault> faultLst) {
		// 处理状态（0 未处理）则为未派单
		List<DeviceFault> noMaintainceLst = faultLst.stream().filter(p -> p.getProcessState().equals("0"))
				.collect(Collectors.toList());
		if (noMaintainceLst != null && noMaintainceLst.size() > 0) {
			return "0";// 未派单
		} else {
			// 处理状态（2 未解决、3 已解决）则为已派单
			List<DeviceFault> hasMaintainceLst = faultLst.stream()
					.filter(p -> p.getProcessState().equals("2") || p.getProcessState().equals("3"))
					.collect(Collectors.toList());
			if (hasMaintainceLst != null && hasMaintainceLst.size() > 0) {
				return "1";// 已派单
			} else {
				return "2";// 无需维护
			}
		}
	}

	private List<String> getFaultDetails(JSONObject jsonObject) {
		if (jsonObject == null) {
			return null;
		}
		JSONArray arrayFaultDetails = jsonObject.getJSONArray("faultDetails");

		if (arrayFaultDetails != null && arrayFaultDetails.size() > 0) {

		} else// 如果是智能相机，该故障有值，具体的故障是存在智能相机的组件中的
		{
			JSONArray arrayComponentStatusResults = jsonObject.getJSONArray("componentStatusResults");
			if (arrayComponentStatusResults != null && arrayComponentStatusResults.size() > 0) {
				arrayFaultDetails = new JSONArray();
				for (Object cmpt : arrayComponentStatusResults) {
					JSONObject tempCmpt = (JSONObject) cmpt;
					if (tempCmpt == null) {
						continue;
					}
					JSONArray arrayDetails = tempCmpt.getJSONArray("faultDetails");
					if (arrayDetails != null && arrayDetails.size() > 0) {
						arrayFaultDetails.add(arrayDetails);
					}
				}
			}
		}
		if (arrayFaultDetails == null) {
			return null;
		} else {
			return arrayFaultDetails.stream().map(p -> p.toString()).collect(Collectors.toList());
		}
	}

	private List<Sys> qryDevFromDB(SystemCriteria criteria) throws Exception {
		List<Sys> sysLst = systemService.findSys(criteria);
		return sysLst;
	}

	private SystemCriteria getSystemCriteria(DeviceRunMonitorQryDto form) throws Exception {
		SystemCriteria criteria = new SystemCriteria();
		criteria.useStatusFlag = SysCodeConstant.USE_STATUS_USE;// 查询启用的设备
		if (!StringUtil.isNullOrEmpty(form.getDevType())) {
			criteria.deviceType = form.getDevType();
		}
		criteria.deviceName = form.getDevName();
		criteria.contractorId = form.getContractor();
		criteria.ownership = form.getOwnership();
		criteria.orgPrivilegeCode = form.getCurrentOrgPrivilegeCode();
		if (!StringUtil.isNullOrEmpty(form.getNodeType())) {
			if (form.getNodeType().equals("org")) {
				Organization org = dutyService.organizationOfId(form.getNodeId());
				if (!StringUtil.isNullOrEmpty(org.orgPrivilegeCode)) {
					criteria.orgPrivilegeCode = org.orgPrivilegeCode;// 机构过滤code
				}
			} else if (form.getNodeType().equals("roadType") || form.getNodeType().equals("root")) {
				criteria.roadTypeArr = form.getNodeId().split(",");
			} else if (form.getNodeType().equals("road")) {
				criteria.roadId = form.getNodeId();
			} else if (form.getNodeType().equals("site")) {
				criteria.siteId = form.getNodeId();
			}
		}
		criteria.setPageNum(1);
		criteria.setPageSize(Integer.MAX_VALUE);
		criteria.setOrderName(form.getOrderName());
		criteria.setOrderType(form.getOrderType());
		return criteria;
	}

	@RequestMapping(value = "/queryDeviceForMap")
	public List queryDeviceForMap(@ModelAttribute("form") DeviceQryForMapDto form) throws Exception {
		// 从设备领域中获取符合条件的设备
		List<Sys> devLst = queryDeviceFromDevDomain(form.getCurrentOrgPrivilegeCode());

		// 将符合条件的设备进行处理符合地图显示需要
		return getDeviceToMap(form, devLst);
	}

	/**
	 * queryDeviceInit(地图界面加载时候获取该用户下的设备)
	 * 
	 * @Title: queryDeviceInit
	 * @Description:地图界面加载时候获取该用户下的设备
	 * @param
	 * @return Map 返回类型 @throws
	 */
	@Override
	@RequestMapping(value = "/queryDeviceInit")
	public Map queryDeviceInit(String currentOrgPrivilegeCode, String currentOrgId) throws Exception {
		Map mpRes = new HashMap();
		// 获取当前用户所在机构的中心经纬度
		mpRes.put("currentUserOrgLonLat", getOrgLonLatByOrgId(currentOrgId));
		// 从设备领域中获取符合条件的设备
		List<Sys> devLst = queryDeviceFromDevDomain(currentOrgPrivilegeCode);
		if (devLst == null || devLst.size() == 0) {
			return mpRes;
		}
		Map<String, String> cacheSys = null;
		synchronized (DeviceMoniterAction.class) {
			cacheSys = RedisPoolUtil.hgetAll(DEVICE_STATUS_REDIS_KEY);
		}
		// 查询当前用户权限下的点位列表
		List<Site> siteLst = findSite(currentOrgPrivilegeCode);
		// 用户存放设备的map,key:设备类型，
		// value:也是一个map
		// （key:系统ID，value:设备DeviceForMapDto）
		Map mp = new HashMap();
		for (Sys sys : devLst) {
			String cacheDev = getCacheDev(cacheSys, sys.getDeviceId());
			DeviceForMapDto dto = convertToDevForMap(sys, cacheDev, siteLst);
			Map devMp = null;
			if (mp.containsKey(dto.getDevType())) {
				devMp = (Map) mp.get(dto.getDevType());
			} else {
				devMp = new HashMap();
				mp.put(dto.getDevType(), devMp);
			}
			devMp.put(dto.getDevId(), dto);// dto.getDevNbr()
		}
		// 保存用户管辖的设备，用于地图上显示
		mpRes.put("devices", mp);
		// 保存机构设备树
		mpRes.put("orgTreeData", getOrgDevTree(currentOrgPrivilegeCode, devLst, cacheSys));
		// 保存道路设备树
		mpRes.put("roadTreeData", getRoadDevTree(currentOrgPrivilegeCode, devLst, cacheSys, siteLst));
		return mpRes;
	}

	private List<Site> findSite(String currentOrgPrivilegeCode) {
		SiteCriteria siteCriteria = new SiteCriteria();
		siteCriteria.orgPrvCode = currentOrgPrivilegeCode;
		return siteService.findSites(siteCriteria);
	}

	/**
	 * 转换为地图上需要的设备dto
	 * 
	 * @param devSys
	 * @param cacheDevStr
	 * @return
	 * @throws Exception
	 */
	private DeviceForMapDto convertToDevForMap(Sys devSys, String cacheDevStr, List<Site> siteLst) throws Exception {
		DeviceForMapDto deviceForMapDto = new DeviceForMapDto();
		JSONObject jsonObject = null;
		if (!StringUtil.isNullOrEmpty(cacheDevStr)) {
			jsonObject = JSONObject.parseObject(cacheDevStr);
		}
		deviceForMapDto.setDevId(devSys.getDeviceId());
		deviceForMapDto.setDevName(devSys.getDeviceName());
		deviceForMapDto.setDevNbr(devSys.getDeviceSysNbr());
		deviceForMapDto.setDevStatus(devSys.getStatusType());
		deviceForMapDto.setDevType(devSys.getDeviceType());
		deviceForMapDto.setOwnership(devSys.getOwnership());
		deviceForMapDto.setOrgId(devSys.getOrgId());

		deviceForMapDto.setSiteId(devSys.getSiteId());

		Double siteLon = (double) 0;
		Double siteLat = (double) 0;
		String siteName = "";
		String siteCode = "";
		if (siteLst != null && siteLst.size() > 0) {
			// 获取设备点位
			List<Site> sysSiteLst = siteLst.stream().filter(p -> p.getSiteId().equals(devSys.getSiteId()))
					.collect(Collectors.toList());
			if (sysSiteLst != null && sysSiteLst.size() > 0) {
				Site sysSite = sysSiteLst.get(0);
				if (sysSite != null) {
					siteLon = sysSite.getSiteLongitude() == null ? 0 : sysSite.getSiteLongitude().doubleValue();
					siteLat = sysSite.getSiteLatitude() == null ? 0 : sysSite.getSiteLatitude().doubleValue();
					siteName = sysSite.getSiteName();
					siteCode = sysSite.getSiteCode();
				}
			}
		}
		deviceForMapDto.setSiteCode(siteCode);
		deviceForMapDto.setSiteName(siteName);
		deviceForMapDto.setLongitude(siteLon);
		deviceForMapDto.setLatitude(siteLat);
		if (jsonObject != null) {
			deviceForMapDto.setDevStatus(jsonObject.getInteger("statusType").toString());
			deviceForMapDto.setLatestHeartTime(formatDateToString(jsonObject.getDate("statusUpdateTime")));
			deviceForMapDto.setLatestUpDataTime(formatDateToString(jsonObject.getDate("lastUploadTime")));
			Integer diff = jsonObject.getInteger("timeDiff");
			if (diff != null) {
				deviceForMapDto.setTimeDiff(diff.toString());
			}
			deviceForMapDto.setSnapTotal("");// 目前没有提供 todo
			deviceForMapDto.setSnapBackLogPercent("");// 目前没有提供 todo
			deviceForMapDto.setLatestCalibrationTime("");// 最近校时时间目前没有提供 todo
		}
		return deviceForMapDto;
	}

	/**
	 * 获取当前用户机构设备树
	 * 
	 * @param currentOrgPrivilegeCode
	 * @param devLst
	 * @param cacheSys
	 * @return
	 */
	private List<DevTreeForDeviceMoniterDto> getOrgDevTree(String currentOrgPrivilegeCode, List<Sys> devLst,
			Map<String, String> cacheSys) {
		List<DevTreeForDeviceMoniterDto> orgDevTreeLst = new ArrayList<DevTreeForDeviceMoniterDto>();
		// 查询当前用户机构及其下属机构
		List<Organization> orgLst = dutyService.findOrgOfParent(currentOrgPrivilegeCode);
		if (orgLst == null || orgLst.size() == 0) {
			return orgDevTreeLst;
		}
		Organization currentOrg = getOrgByOrgPrivilegeCode(orgLst, currentOrgPrivilegeCode);
		// 建立当前用户所在机构节点
		DevTreeForDeviceMoniterDto rootOrgNode = convertOrgNode(currentOrg);
		getSonOrgDevTreeDtos(rootOrgNode, orgLst, currentOrgPrivilegeCode, devLst, cacheSys);
		orgDevTreeLst.add(rootOrgNode);
		return orgDevTreeLst;
	}

	@RequestMapping(value = "/getOrgDevTreeByOrgPriviCode")
	public List<DevTreeForDeviceMoniterDto> getOrgDevTreeByOrgPriviCode(String currentOrgPrivilegeCode) {

		List<DevTreeForDeviceMoniterDto> orgDevTreeLst = new ArrayList<DevTreeForDeviceMoniterDto>();
		// 从设备领域中获取符合条件的设备
		List<Sys> devLst = queryDeviceFromDevDomain(currentOrgPrivilegeCode);
		if (devLst == null || devLst.size() == 0) {
			return orgDevTreeLst;
		}
		Map<String, String> cacheSys = null;
		synchronized (DeviceMoniterAction.class) {
			cacheSys = RedisPoolUtil.hgetAll(DEVICE_STATUS_REDIS_KEY);
		}
		return getOrgDevTree(currentOrgPrivilegeCode, devLst, cacheSys);
	}

	/**
	 * 构造机构节点
	 * 
	 * @param org
	 * @return
	 */
	private DevTreeForDeviceMoniterDto convertOrgNode(Organization org) {
		DevTreeForDeviceMoniterDto orgNode = new DevTreeForDeviceMoniterDto();
		orgNode.setId(org.getIdentityId());
		orgNode.setText(org.orgName);
		Map<String, Object> attribute = new HashMap<String, Object>();
		attribute.put("nodeType", "org");
		attribute.put("orgPrivCode", org.orgPrivilegeCode);
		orgNode.setAttribute(attribute);
		return orgNode;
	}

	/**
	 * 构造设备类型节点
	 * 
	 * @param devType
	 * @return
	 */
	private DevTreeForDeviceMoniterDto convertDevTypeNode(String devType) {
		DevTreeForDeviceMoniterDto devTypeNode = new DevTreeForDeviceMoniterDto();
		String devTypeName = devType;
		Code code = null;
		try {
			code = sysCodeService.codeOfId(SysCodeConstant.DEVICE_TYPE, devType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (code != null) {
			devTypeName = code.codeName;
		}
		devTypeNode.setId(devType);
		devTypeNode.setText(devTypeName);
		Map<String, Object> attribute = new HashMap<String, Object>();
		attribute.put("nodeType", "devType");
		devTypeNode.setAttribute(attribute);
		return devTypeNode;
	}

	/**
	 * 构造设备节点
	 * 
	 * @param devSys
	 * @param cacheSys
	 * @return
	 */
	private DevTreeForDeviceMoniterDto convertDeviceNode(Sys devSys, Map<String, String> cacheSys) {
		DevTreeForDeviceMoniterDto devNode = new DevTreeForDeviceMoniterDto();
		devNode.setId(devSys.getDeviceId());
		devNode.setText(devSys.getDeviceName());
		String devStatus = getDevCacheStatus(devSys, cacheSys);
		devNode.setIconCls("icon-" + devSys.getDeviceType() + "-" + devStatus);
		Map<String, Object> attribute = new HashMap<String, Object>();
		attribute.put("nodeType", "device");
		attribute.put("devType", devSys.getDeviceType());
		attribute.put("devStatus", devStatus);
		devNode.setAttribute(attribute);
		return devNode;
	}

	/**
	 * 从设备缓存Map中查询出指定设备的状态
	 * 
	 * @param devSys
	 * @param cacheSys
	 * @return
	 */
	private String getDevCacheStatus(Sys devSys, Map<String, String> cacheSys) {
		String devStatus = devSys.getStatusType();
		String cacheDev = getCacheDev(cacheSys, devSys.getDeviceId());
		JSONObject jsonObject = null;
		if (!StringUtil.isNullOrEmpty(cacheDev)) {
			jsonObject = JSONObject.parseObject(cacheDev);
		}
		if (jsonObject != null) {
			devStatus = jsonObject.getInteger("statusType").toString();
		}
		return devStatus;
	}

	/**
	 * 获取指定机构及其下属机构以及设备节点
	 * 
	 * @param parentNode
	 * @param orgLst
	 * @param orgPrivilegeCode
	 * @param devLst
	 * @param cacheSys
	 * @return
	 */
	private List<DevTreeForDeviceMoniterDto> getSonOrgDevTreeDtos(DevTreeForDeviceMoniterDto parentNode,
			List<Organization> orgLst, String orgPrivilegeCode, List<Sys> devLst, Map<String, String> cacheSys) {
		List<DevTreeForDeviceMoniterDto> sonDtoLst = new ArrayList<DevTreeForDeviceMoniterDto>();
		List<Organization> currentOrgLst = orgLst.stream()
				.filter(o -> !StringUtil.isNullOrEmpty(o.orgPrivilegeCode)
						&& o.orgPrivilegeCode.length() == orgPrivilegeCode.length() + 2
						&& o.orgPrivilegeCode.startsWith(orgPrivilegeCode))
				.collect(Collectors.toList());
		// 如果该机构有下属机构
		if (currentOrgLst != null && currentOrgLst.size() > 0) {
			// 遍历每一个下属机构，把有设备的下属机构增加到树节点中
			for (int i = 0; i < currentOrgLst.size(); i++) {
				// 判断该机构下是否有设备，没有设备则该机构不加载
				Organization orgTem = currentOrgLst.get(i);
				List<Sys> sysTemLst = devLst.stream()
						.filter(p -> !StringUtil.isNullOrEmpty(p.getOrgPrivilegeCode())
								&& p.getOrgPrivilegeCode().startsWith(orgTem.orgPrivilegeCode))
						.collect(Collectors.toList());
				if (sysTemLst != null && sysTemLst.size() > 0) {
					DevTreeForDeviceMoniterDto sonDtoTem = convertOrgNode(orgTem);
					getSonOrgDevTreeDtos(sonDtoTem, orgLst, orgTem.orgPrivilegeCode, devLst, cacheSys);
					sonDtoLst.add(sonDtoTem);
				}
			}
		}
		// 判断该机构下是否有设备
		List<Sys> thisOrgSysLst = devLst.stream().filter(p -> !StringUtil.isNullOrEmpty(p.getOrgPrivilegeCode())
				&& p.getOrgPrivilegeCode().equals(orgPrivilegeCode)).collect(Collectors.toList());
		if (thisOrgSysLst != null && thisOrgSysLst.size() > 0) {
			Map<String, List<Sys>> devTypesMp = thisOrgSysLst.stream()
					.collect(Collectors.groupingBy(Sys::getDeviceType));
			Set<String> devTypes = devTypesMp.keySet();
			for (Iterator<String> iterator = devTypes.iterator(); iterator.hasNext();) {
				String devType = (String) iterator.next();
				DevTreeForDeviceMoniterDto devTypeNode = convertDevTypeNode(devType);
				// 加载该设备类型下的设备
				List<Sys> thisTypeDevs = devTypesMp.get(devType);
				List<DevTreeForDeviceMoniterDto> devNodes = new ArrayList<DevTreeForDeviceMoniterDto>();
				for (Sys sys : thisTypeDevs) {
					DevTreeForDeviceMoniterDto devNode = convertDeviceNode(sys, cacheSys);
					devNodes.add(devNode);
				}
				devTypeNode.setChildren(devNodes);
				sonDtoLst.add(devTypeNode);
			}
		}
		// 把下属节点加载到该节点的children属性中
		if (sonDtoLst.size() > 0) {
			parentNode.setChildren(sonDtoLst);
		}
		return sonDtoLst;
	}

	private List<DevTreeForDeviceMoniterDto> getRoadDevTree(String currentOrgPrivilegeCode, List<Sys> devLst,
			Map<String, String> cacheSys, List<Site> siteLst) {
		List<DevTreeForDeviceMoniterDto> roadDevTreeLst = new ArrayList<DevTreeForDeviceMoniterDto>();
		// 建立道路根节点
		DevTreeForDeviceMoniterDto rootNode = new DevTreeForDeviceMoniterDto();
		rootNode.setId("0");
		rootNode.setText("道路");
		// rootNode.setChildren(types);
		Map<String, Object> attribute = new HashMap<String, Object>();
		attribute.put("nodeType", "root");
		rootNode.setAttribute(attribute);
		rootNode.setChildren(createRoadTypesTreeData(currentOrgPrivilegeCode, siteLst, devLst, cacheSys));
		roadDevTreeLst.add(rootNode);
		return roadDevTreeLst;
	}

	private List<DevTreeForDeviceMoniterDto> createRoadTypesTreeData(String currentOrgPrivilegeCode, List<Site> siteLst,
			List<Sys> devSysLst, Map<String, String> cacheSys) {
		List<DevTreeForDeviceMoniterDto> roadTypeTreeLst = new ArrayList<DevTreeForDeviceMoniterDto>();
		List<Road> allRoadLst = getRoadData(currentOrgPrivilegeCode);
		if (allRoadLst == null || allRoadLst.size() == 0) {
			return null;
		}
		// 获得道路类型编码
		List<Code> roadTypeCodeList = null;
		try {
			roadTypeCodeList = sysCodeService.codeListOfType("016");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, List<Road>> roadTypeMp = allRoadLst.stream().collect(Collectors.groupingBy(Road::getRoadType));
		Set<String> roadTypes = roadTypeMp.keySet();
		// 遍历道路类型
		for (Iterator<String> iterator = roadTypes.iterator(); iterator.hasNext();) {
			String roadType = (String) iterator.next();
			// 建立道路类型节点
			DevTreeForDeviceMoniterDto roadTypeNode = convertRoadTypeNode(roadType, roadTypeCodeList);
			// 加载该类型下的道路
			List<Road> thisTypeRoads = roadTypeMp.get(roadType);
			roadTypeNode.setChildren(createRoadTreeData(thisTypeRoads, siteLst, devSysLst, cacheSys));
			roadTypeTreeLst.add(roadTypeNode);
		}
		return roadTypeTreeLst;
	}

	private List<DevTreeForDeviceMoniterDto> createRoadTreeData(List<Road> thisTypeRoads, List<Site> siteLst,
			List<Sys> devSysLst, Map<String, String> cacheSys) {
		List<DevTreeForDeviceMoniterDto> roadTreeLst = new ArrayList<DevTreeForDeviceMoniterDto>();
		for (Road road : thisTypeRoads) {
			String roadId = road.getRoadId();
			// 如果没有点位，则无需继续加载该道路了
			if (siteLst == null || siteLst.size() == 0) {
				continue;
			}
			List<Site> thisRoadSites = siteLst.stream()
					.filter(p -> !StringUtil.isNullOrEmpty(p.getRoadId()) && p.getRoadId().equals(roadId))
					.collect(Collectors.toList());
			// 如果该道路下没有点位，无需加载该道路了
			if (thisRoadSites == null || thisRoadSites.size() == 0) {
				continue;
			}
			List<String> thisRoadSiteIds = thisRoadSites.stream().map(p -> p.getSiteId()).collect(Collectors.toList());
			// 过滤设备中点位包含在thisRoadSiteIds中的设备
			List<Sys> thisRoadDevSysLst = devSysLst.stream()
					.filter(p -> !StringUtil.isNullOrEmpty(p.getSiteId()) && thisRoadSiteIds.contains(p.getSiteId()))
					.collect(Collectors.toList());
			// 如果道路下没有设备，则无需加载该道路了
			if (thisRoadDevSysLst == null || thisRoadDevSysLst.size() == 0) {
				continue;
			}
			DevTreeForDeviceMoniterDto roadNode = convertRoadNode(road);
			// 加载设备类型及设备
			roadNode.setChildren(createDevTreeData(thisRoadDevSysLst, cacheSys));
			roadTreeLst.add(roadNode);
		}
		return roadTreeLst;

	}

	private List<DevTreeForDeviceMoniterDto> createDevTreeData(List<Sys> thisRoadDevSysLst,
			Map<String, String> cacheSys) {
		List<DevTreeForDeviceMoniterDto> devTypeNodes = new ArrayList<DevTreeForDeviceMoniterDto>();
		Map<String, List<Sys>> devTypesMp = thisRoadDevSysLst.stream()
				.collect(Collectors.groupingBy(Sys::getDeviceType));
		Set<String> devTypes = devTypesMp.keySet();
		for (Iterator<String> iterator = devTypes.iterator(); iterator.hasNext();) {
			String devType = (String) iterator.next();
			DevTreeForDeviceMoniterDto devTypeNode = convertDevTypeNode(devType);
			// 加载该设备类型下的设备
			List<Sys> thisTypeDevs = devTypesMp.get(devType);
			List<DevTreeForDeviceMoniterDto> devNodes = new ArrayList<DevTreeForDeviceMoniterDto>();
			for (Sys sys : thisTypeDevs) {
				DevTreeForDeviceMoniterDto devNode = convertDeviceNode(sys, cacheSys);
				devNodes.add(devNode);
			}
			devTypeNode.setChildren(devNodes);
			devTypeNodes.add(devTypeNode);
		}
		return devTypeNodes;
	}

	/**
	 * 构建道路节点
	 * 
	 * @param road
	 * @return
	 */
	private DevTreeForDeviceMoniterDto convertRoadNode(Road road) {
		DevTreeForDeviceMoniterDto roadNode = new DevTreeForDeviceMoniterDto();
		roadNode.setId(road.getRoadId());
		roadNode.setText(road.getRoadName());
		Map<String, Object> attribute = new HashMap<String, Object>();
		attribute.put("nodeType", "road");
		roadNode.setAttribute(attribute);
		return roadNode;
	}

	/**
	 * 构建道路类型节点
	 * 
	 * @param roadType
	 * @param roadTypeCodeList
	 * @return
	 */
	private DevTreeForDeviceMoniterDto convertRoadTypeNode(String roadType, List<Code> roadTypeCodeList) {
		DevTreeForDeviceMoniterDto roadTypeNode = new DevTreeForDeviceMoniterDto();
		String typeName = roadType;
		List<Code> thisRoadTypeCodeObj = roadTypeCodeList.stream().filter(p -> p.codeNo.equals(roadType))
				.collect(Collectors.toList());
		if (thisRoadTypeCodeObj != null && thisRoadTypeCodeObj.size() > 0) {
			typeName = thisRoadTypeCodeObj.get(0).codeName;
		}
		roadTypeNode.setId(roadType);
		roadTypeNode.setText(typeName);
		Map<String, Object> attribute = new HashMap<String, Object>();
		attribute.put("nodeType", "roadType");
		roadTypeNode.setAttribute(attribute);
		return roadTypeNode;
	}

	private List<Road> getRoadData(String currentOrgPrivilegeCode) {
		RoadCriteria roadCriteria = new RoadCriteria();
		roadCriteria.setNoPage();
		roadCriteria.orgPrivilegeCode = currentOrgPrivilegeCode;
		List<Road> roadList = roadService.findRoads(roadCriteria);
		return roadList;
	}

	/**
	 * 根据机构权限代码从机构列表中检索到该机构对象
	 * 
	 * @param orgLst
	 * @param currentOrgPrivilegeCode
	 * @return
	 */
	private Organization getOrgByOrgPrivilegeCode(List<Organization> orgLst, String currentOrgPrivilegeCode) {
		List<Organization> currentOrgLst = orgLst.stream().filter(o -> !StringUtil.isNullOrEmpty(o.orgPrivilegeCode)
				&& o.orgPrivilegeCode.equals(currentOrgPrivilegeCode)).collect(Collectors.toList());
		if (currentOrgLst == null || currentOrgLst.size() == 0) {
			return null;
		}
		return currentOrgLst.get(0);
	}

	/**
	 * 获取指定设备的一些信息，用于地图上点击该设备时候，显示的概要字段信息
	 * 
	 * @param deviceId
	 * @return
	 */
	@Override
	@RequestMapping(value = "/getDeviceForMapTip")
	public Map<String, String> getDeviceForMapTip(String deviceId) {
		Map<String, String> devTipInfoMp = new HashMap<String, String>();

		Sys devSys = null;
		try {
			devSys = systemService.systemOfId(deviceId);
		} catch (Exception e) {
			log4jFactoryProxy.error("DeviceMoniterAction.getDeviceForMapTip方法异常：查询设备id为【" + deviceId + "】出现异常！");
			return devTipInfoMp;
		}

		String orgName = devSys.getOrgId();
		Organization org = null;
		try {
			org = dutyService.organizationOfId(devSys.getOrgId());
		} catch (Exception e) {
			log4jFactoryProxy
					.error("DeviceMoniterAction.getDeviceForMapTip方法异常：查询机构ID为【" + devSys.getOrgId() + "】出现异常！");
		}
		if (org != null) {
			orgName = org.orgName;
		}
		devTipInfoMp.put("orgName", orgName);
		// 查询监控方向
		String directionName = "";
		String sectionIds = devSys.getSectionIdList();
		// 查询设备系统监控断面列表，根据断面列表查询其监控方向
		if (sectionIds != null && !sectionIds.equals("")) {
			String[] sectionIdArr = null;
			if (sectionIds.contains(",")) {
				sectionIdArr = sectionIds.split(",");
			} else {
				sectionIdArr = new String[1];
				sectionIdArr[0] = sectionIds;
			}
			List<Section> sectionLst = siteService.sectionsOfSite(devSys.getSiteId());
			if (sectionLst != null && sectionLst.size() > 0) {
				for (int j = 0; j < sectionIdArr.length; j++) {
					Section section = null;
					String sectionId = sectionIdArr[j];
					try {
						section = sectionLst.stream().filter(p -> p.getSectionId().equals(sectionId))
								.collect(Collectors.toList()).get(0);
					} catch (Exception e) {
						log4jFactoryProxy.error(
								"DeviceMoniterAction.getDeviceForMapTip方法异常：获取断面ID为【" + sectionIdArr[j] + "】出现异常！");
					}
					if (section != null) {
						directionName += section.getDirectionName() + ",";
					}
				}
				if (!directionName.equals("")) {
					directionName = directionName.substring(0, directionName.length() - 1);
				}
			}
		}
		devTipInfoMp.put("directionName", directionName);
		String certificationExpireTime = "";
		Certification certification = null;
		try {
			certification = systemAttachService.findLatesCertByDevSysNbr(devSys.getDeviceSysNbr());
		} catch (Exception e) {
			log4jFactoryProxy
					.error("DeviceMoniterAction.getDeviceForMapTip方法异常：查询设备ID为【" + devSys.getDeviceId() + "】出现异常！");
		}
		if (certification != null) {
			Date ceDate = certification.getExpireDate();
			if (ceDate != null) {
				SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
				certificationExpireTime = sFormat.format(ceDate);
			}
		}
		devTipInfoMp.put("certificationExpireTime", certificationExpireTime);
		String devImgUrl = "";
		List<Photo> photos = systemAttachService.photesOfDevice(devSys.getDeviceId());
		if (photos != null && photos.size() > 0) {
			devImgUrl = photos.get(0).getPhoto();
		}
		devTipInfoMp.put("devImgUrl", devImgUrl);
		// 还需要加上该设备支持的功能
		DeviceSysCapability deviceSysCapability = systemService.deviceSysCapabilityOfId(deviceId);
		if (deviceSysCapability != null) {
			devTipInfoMp.put("isEventSupport", deviceSysCapability.getIsEventSupport());
			devTipInfoMp.put("isRoadsensorSupport", deviceSysCapability.getIsRoadsensorSupport());
			devTipInfoMp.put("isTollgateSupport", deviceSysCapability.getIsTollgateSupport());
			devTipInfoMp.put("isVideoSupport", deviceSysCapability.getIsVideoSupport());
			devTipInfoMp.put("isVioSupport", deviceSysCapability.getIsVioSupport());
			devTipInfoMp.put("isVisibilitySupport", deviceSysCapability.getIsVisibilitySupport());
			devTipInfoMp.put("isWeatherSupport", deviceSysCapability.getIsWeatherSupport());
			devTipInfoMp.put("isFlowSupport", deviceSysCapability.getIsFlowSupport());
		}
		return devTipInfoMp;
	}

	/**
	 * queryDeviceByName(根据设备名称关键字查询设备) @Title: queryDeviceByName @Description:
	 * 根据设备名称关键字查询设备 @param @param devName @param @return @param @throws
	 * Exception 设定文件 @return List<DeviceForMapDto> 返回类型 @throws
	 */
	@Override
	@RequestMapping(value = "/queryDeviceByName")
	public List<DeviceForMapDto> queryDeviceByName(String devName, String currentOrgPrivilegeCode) throws Exception {
		SystemCriteria criteria = new SystemCriteria();
		criteria.deviceName = devName.toUpperCase();
		criteria.useStatusFlag = SysCodeConstant.USE_STATUS_USE;// 启用的设备
		criteria.orgPrivilegeCode = currentOrgPrivilegeCode;
		criteria.setPageNum(1);
		criteria.setPageSize(Integer.MAX_VALUE);
		criteria.setOrderName("siteCode");
		criteria.setOrderType("ASC");
		List<Sys> devLst = systemService.findSys(criteria);
		List<DeviceForMapDto> devForMapLst = new ArrayList<DeviceForMapDto>();
		for (Sys sys : devLst) {
			devForMapLst.add(convertToDevMapDto(sys, null));
		}
		return devForMapLst;
	}

	private String formatDateToString(Date date) {
		if (date != null) {
			SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sFormat.format(date);
		}
		return "";
	}

	// private List<DevTreeForDeviceMoniterDto> convertToDevTree(List<Sys>
	// devLst) throws Exception {
	// if (devLst == null || devLst.size() == 0) {
	// return null;
	// }
	// List<DevTreeForDeviceMoniterDto> list = new
	// ArrayList<DevTreeForDeviceMoniterDto>();
	// // 对查询到的所有设备按照设备类型进行分组
	// Map<String, List<Sys>> devGrp =
	// devLst.stream().collect(Collectors.groupingBy(Sys::getDeviceType));
	// Set<String> devTypes = devGrp.keySet();
	// // 每种设备类型就是一个根节点
	// for (Iterator iterator = devTypes.iterator(); iterator.hasNext();) {
	// String str = (String) iterator.next();
	// // 构建根节点对象
	// DevTreeForDeviceMoniterDto dto = new DevTreeForDeviceMoniterDto();
	// dto.setId(str);
	// Code code = sysCodeService.codeOfId(SysCodeConstant.DEVICE_TYPE, str);
	// if (code == null) {
	// dto.setText(str);
	// } else {
	// dto.setText(code.codeName);
	// }
	// Map<String, Object> attribute = new HashMap<String, Object>();
	// attribute.put("nodeType", "DEVICE_TYPE");
	// dto.setAttribute(attribute);
	// // dto.setIconCls("icon-" + str + "-1");
	// dto.setIconCls("icon-empty");
	// List<Sys> devs = devGrp.get(str);
	// if (devs == null || devs.size() == 0)// 如果该设备类型下无设备，则继续下一个设备类型
	// {
	// continue;
	// }
	// // 否则构建设备类型节点下的具体设备节点
	// List<DevTreeForDeviceMoniterDto> listSon = new
	// ArrayList<DevTreeForDeviceMoniterDto>();
	// for (int i = 0; i < devs.size(); i++) {
	// Sys devSys = devs.get(i);
	//
	// DevTreeForDeviceMoniterDto dtoSon = new DevTreeForDeviceMoniterDto();
	// dtoSon.setId(devSys.getDeviceSysNbr());// 日后要换成device_key todo
	// dtoSon.setText(devSys.getDeviceName());
	// dtoSon.setIconCls("icon-" + str + "-" + devSys.getStatusType());
	//
	// Map<String, Object> attributeSon = new HashMap<String, Object>();
	// attributeSon.put("nodeType", "DEVICE");
	// attributeSon.put("devId", devSys.getDeviceId());
	// attributeSon.put("devNbr", devSys.getDeviceSysNbr());
	// attributeSon.put("devName", devSys.getDeviceName());
	// attributeSon.put("devType", devSys.getDeviceType());
	// attributeSon.put("devStatus", devSys.getStatusType());
	// // 获取设备点位
	// Site sysSite = siteService.siteOfId(devSys.getSiteId());
	// String siteLon = "";
	// String siteLat = "";
	// String siteName = "";
	// if (sysSite != null) {
	// siteLon = sysSite.getSiteLongitude() == null ? "" :
	// sysSite.getSiteLongitude().toString();
	// siteLat = sysSite.getSiteLatitude() == null ? "" :
	// sysSite.getSiteLatitude().toString();
	// siteName = sysSite.getSiteName();
	// }
	// attributeSon.put("siteName", siteName);
	// attributeSon.put("siteLon", siteLon);
	// attributeSon.put("siteLat", siteLat);
	//
	// String orgName = devSys.getOrgId();
	// Organization org = dutyService.organizationOfId(devSys.getOrgId());
	// if (org != null) {
	// orgName = org.orgName;
	// }
	// attributeSon.put("orgName", orgName);
	// // 查询监控方向
	// String directionName = "";
	// String sectionIds = devSys.getSectionIdList();
	// // 查询设备系统监控断面列表，根据断面列表查询其监控方向
	// if (sectionIds != null && !sectionIds.equals("")) {
	// String[] sectionIdArr = null;
	// if (sectionIds.contains(",")) {
	// sectionIdArr = sectionIds.split(",");
	// } else {
	// sectionIdArr = new String[1];
	// sectionIdArr[0] = sectionIds;
	// }
	// for (int j = 0; j < sectionIdArr.length; j++) {
	// Section section = siteService.sectionOfId(sectionIdArr[j]);
	// if (section != null) {
	// directionName += section.getDirectionName() + ",";
	// }
	// }
	// if (!directionName.equals("")) {
	// directionName = directionName.substring(0, directionName.length() - 1);
	// }
	// }
	// attributeSon.put("directionName", directionName);
	// String latestHeartTime = "";
	// if (devSys.getStatusUpdateTime() != null) {
	// SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	// latestHeartTime = sFormat.format(devSys.getStatusUpdateTime());
	// }
	// attributeSon.put("latestHeartTime", latestHeartTime);
	// attributeSon.put("latestCalibrationTime", "");
	// attributeSon.put("latestUpDataTime", "");
	// attributeSon.put("snapTotal", "");
	// attributeSon.put("snapBackLogPercent", "");
	// String certificationExpireTime = "";
	// // Certification certification =
	// // systemAttachService.findLatesCertByDevId(devSys.getDeviceId());
	// Certification certification =
	// systemAttachService.findLatesCertByDevSysNbr(devSys.getDeviceSysNbr());
	// if (certification != null) {
	// Date ceDate = certification.getExpireDate();
	// if (ceDate != null) {
	// SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	// certificationExpireTime = sFormat.format(ceDate);
	// }
	// }
	// attributeSon.put("certificationExpireTime", certificationExpireTime);
	// String devImgUrl = "";
	// List<Photo> photos =
	// systemAttachService.photesOfDevice(devSys.getDeviceId());
	// if (photos != null && photos.size() > 0) {
	// devImgUrl = photos.get(0).getPhoto();
	// }
	// attributeSon.put("devImgUrl", devImgUrl);
	// dtoSon.setAttribute(attributeSon);
	// listSon.add(dtoSon);
	// }
	// dto.setChildren(listSon);
	// list.add(dto);
	// }
	// return list;
	// }

	/**
	 * 从设备领域中获取符合条件的设备
	 * 
	 * @param qyrDto
	 * @return
	 */
	private List<Sys> queryDeviceFromDevDomain(String orgPirvilegeCode) {
		SystemCriteria criteria = new SystemCriteria();
		criteria.useStatusFlag = SysCodeConstant.USE_STATUS_USE;// 启用的设备
		criteria.orgPrivilegeCode = orgPirvilegeCode;
		criteria.setPageNum(1);
		criteria.setPageSize(Integer.MAX_VALUE);
		criteria.setOrderName("siteCode");
		criteria.setOrderType("ASC");
		return systemService.findSys(criteria);
	}

	/**
	 * 将符合条件的设备进行处理符合地图显示需要
	 * 
	 * @param qyrDto
	 * @param devLst
	 * @return
	 * @throws Exception
	 */
	private List getDeviceToMap(DeviceQryForMapDto qyrDto, List<Sys> devLst) throws Exception {
		if (devLst == null || devLst.size() == 0) {
			return null;
		}
		// 如果是总队用户，则按照支队分组；如果是支队用户，则按照大队用户分组;如果是大队用户，则无需聚合，直接加载该范围内的设备;
		String orgPrivilegeCode = qyrDto.getCurrentOrgPrivilegeCode();
		if (orgPrivilegeCode.length() < 6) // 总队、支队用户
		{
			// 如果需要聚合，则不需要根据范围过滤设备，所有符合条件的设备按照机构分组聚合
			if (qyrDto.getIsNeedCluster()) {
				return ClusterDevice(qyrDto.getCurrentOrgId(), devLst);
			}
		}
		return FilterCurrentExtentDevice(qyrDto.getLeft(), qyrDto.getBottom(), qyrDto.getRight(), qyrDto.getTop(),
				devLst);
	}

	/**
	 * 聚合设备
	 * 
	 * @param orgId
	 * @param devLst
	 * @return
	 */
	private List<DeviceClusterDto> ClusterDevice(String orgId, List<Sys> devLst) {
		try {
			List<Organization> lst_top = dutyService.findOrgsOfParent(orgId);
			List<Organization> lst_all = dutyService.findSonOrgsOfParent(orgId);
			List<Organization> lst_topNew = lst_top.stream().filter(o -> o.orgPrivilegeCode != null)
					.collect(Collectors.toList());
			// ooMap的key是机构ID，value是根级机构ID
			Map<String, String> ooMap = lst_all.stream().filter(o -> o.orgPrivilegeCode != null)
					.collect(Collectors.toMap(Organization::getIdentityId, (o) -> {
						for (Organization org : lst_topNew) {
							if (o.orgPrivilegeCode.equals(org.orgPrivilegeCode)
									|| o.orgPrivilegeCode.startsWith(org.orgPrivilegeCode)) {
								return org.getIdentityId();
							}
						}
						return "";
					}));
			// 把设备列表map成只有分类的顶级机构Id的集合，然后对机构ID进行分组
			Map<String, Integer> m = devLst.stream().map(d -> ooMap.get(d.getOrgId()))
					.filter(s -> !StringUtil.isNullOrEmpty(s))
					.collect(Collectors.groupingBy(c -> c, Collectors.summingInt(c -> 1)));

			// 把要分组的顶级机构list转换成key是机构ID，value是机构对象的Map
			Map<String, Organization> mTop = lst_top.stream()
					.collect(Collectors.toMap(Organization::getIdentityId, o -> o));

			// 把设备列表map成DeviceClusterDto列表
			return m.entrySet().stream().map((kv) -> {
				Organization org = mTop.get(kv.getKey());
				DeviceClusterDto dto = new DeviceClusterDto();
				if (org != null) {
					dto.setOrgId(kv.getKey());
					dto.setTotal(String.valueOf(kv.getValue()));
					dto.setLatitude(org.orgSeat != null ? String.valueOf(org.orgSeat.orgLatitude) : "");
					dto.setLongitude(org.orgSeat != null ? String.valueOf(org.orgSeat.orgLongitude) : "");
				}

				return dto;
			}).collect(Collectors.toList());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取当前范围内的设备 原理：设备所在点位的经度落在[left,right]区间中&&纬度落在[bottom,top]区间中
	 * 
	 * @param left
	 * @param bottom
	 * @param right
	 * @param top
	 * @param devLst
	 * @return
	 * @throws Exception
	 */
	private List<DeviceForMapDto> FilterCurrentExtentDevice(Double left, Double bottom, Double right, Double top,
			List<Sys> devLst) throws Exception {

		List<DeviceForMapDto> lst = new ArrayList<DeviceForMapDto>();

		for (Sys s : devLst) {
			Site sysSite = siteService.siteOfId(s.getSiteId());
			if (sysSite == null) {
				continue;
			}
			Double siteLon = sysSite.getSiteLongitude() == null ? 0 : sysSite.getSiteLongitude().doubleValue();
			Double siteLat = sysSite.getSiteLatitude() == null ? 0 : sysSite.getSiteLatitude().doubleValue();
			if (left <= siteLon && siteLon <= right) {
				if (bottom <= siteLat && siteLat <= top) {

					lst.add(convertToDevMapDto(s, sysSite));
				}
			}
		}
		return lst;
	}

	private DeviceForMapDto convertToDevMapDto(Sys s, Site sysSite) throws Exception {
		DeviceForMapDto deviceForMapDto = new DeviceForMapDto();
		deviceForMapDto.setDevId(s.getDeviceId());
		deviceForMapDto.setDevNbr(s.getDeviceSysNbr());
		deviceForMapDto.setDevName(s.getDeviceName());
		deviceForMapDto.setDevStatus(s.getStatusType());// 设备状态
		deviceForMapDto.setDevType(s.getDeviceType());
		if (sysSite == null) {
			sysSite = siteService.siteOfId(s.getSiteId());
		}
		Double siteLon = (double) 0;
		Double siteLat = (double) 0;
		String siteName = "";
		if (sysSite != null) {
			siteLon = sysSite.getSiteLongitude() == null ? 0 : sysSite.getSiteLongitude().doubleValue();
			siteLat = sysSite.getSiteLatitude() == null ? 0 : sysSite.getSiteLatitude().doubleValue();
			siteName = sysSite.getSiteName();
		}
		deviceForMapDto.setLatitude(siteLat);
		deviceForMapDto.setLongitude(siteLon);
		deviceForMapDto.setSiteId(s.getSiteId());
		deviceForMapDto.setSiteName(siteName);
		deviceForMapDto.setOrgId(s.getOrgId());
		deviceForMapDto.setOrgName(s.getOrgId());
		Organization org = dutyService.organizationOfId(s.getOrgId());
		if (org != null) {
			deviceForMapDto.setOrgName(org.orgName);
		}
		return deviceForMapDto;
	}

	/**
	 * 
	 * IsPointInPolygon(判断点是否在普通的多边形内) @Title: IsPointInPolygon @Description:
	 * 原理：求解通过该点的水平线与多边形各边的交点，单边交点为奇数，成立! @param @param p @param @param
	 * plPoints @param @return 设定文件 @return boolean 返回类型 @throws
	 */
	public static boolean IsPointInPolygon(MapPointDto p, List<MapPointDto> plPoints) {
		int nCount = plPoints.size();
		int nCross = 0;
		for (int i = 0; i < nCount; i++) {
			MapPointDto p1 = plPoints.get(i);
			MapPointDto p2 = plPoints.get((i + 1) % nCount);
			// 求解 y=p.y 与 p1p2 的交点
			if (p1.getY() == p2.getY()) // p1p2 与 y=p0.y平行
			{
				continue;
			}
			if (p.getY() < Math.min(p1.getY(), p2.getY())) // 交点在p1p2延长线上
			{
				continue;
			}
			if (p.getY() >= Math.max(p1.getY(), p2.getY())) // 交点在p1p2延长线上
			{
				continue;
			}

			// 求交点的 X 坐标
			// --------------------------------------------------------------
			double x = (double) (p.getY() - p1.getY()) * (double) (p2.getX() - p1.getX())
					/ (double) (p2.getY() - p1.getY()) + p1.getX();
			if (x > p.getX())
				nCross++; // 只统计单边交点
		}

		// 单边交点为偶数，点在多边形之外 ---
		return (nCross % 2 == 1);
	}

	/**
	 * queryDevAssoVideoIds @Title: queryDevAssoVideoIds @Description:
	 * 查询设备关联的视频 @param @param devId @param @param
	 * devType @param @return @param @throws Exception 设定文件 @return List
	 * <String> 返回类型 @throws
	 */
	@RequestMapping(value = "/queryDevAssoVideoIds")
	public List<String> queryDevAssoVideoIds(String devId, String devType) throws Exception {
		List<String> idStrings = new ArrayList<String>();
		String videoIdStrs = "";
		if (devType.equals(SysCodeConstant.DEVICE_TYPE_TOLLGATE)) {
			TollgateSys tSys = systemService.tollgateSysOfId(devId);
			if (tSys != null) {
				String upStr = tSys.getUpRelatedVideoList();
				String downStr = tSys.getDownRelatedVideoList();
				if (upStr != null && !upStr.equals("")) {
					videoIdStrs = upStr;
				}
				if (downStr != null && !downStr.equals("")) {
					if (!videoIdStrs.equals("")) {
						videoIdStrs = videoIdStrs + "," + downStr;
					} else {
						videoIdStrs = upStr;
					}
				}
			}
		} else if (devType.equals(SysCodeConstant.DEVICE_TYPE_MEASURE_SPEED)
				|| devType.equals(SysCodeConstant.DEVICE_TYPE_VIO_STOP)
				|| devType.equals(SysCodeConstant.DEVICE_TYPE_VIO_HOLD)
				|| devType.equals(SysCodeConstant.DEVICE_TYPE_RETROGRADE)) {
			VioDevice vioDevice = systemService.vioDeviceOfId(devId);
			if (vioDevice != null) {
				videoIdStrs = vioDevice.getRelatedVideoList();
			}
		} else if (devType.equals(SysCodeConstant.DEVICE_TYPE_METEOR)) {
			Meteorologic meteorologic = systemService.meteorologicOfId(devId);
			if (meteorologic != null) {
				videoIdStrs = meteorologic.getRelatedVideoId();
			}
		}
		if (!videoIdStrs.equals("")) {
			return Arrays.asList(videoIdStrs.split(","));
		}
		return idStrings;
	}

	/**
	 * delete calcStatus(统计设备的状态情况) @Title: calcStatus @Description:
	 * TODO @param @param devLst @param @return @param @throws Exception
	 * 设定文件 @return Map <String,NameAndValueDto> 返回类型 @throws
	 */
	private Map<String, NameAndValueDto> calcStatus(List<Sys> devLst) throws Exception {
		Map<String, NameAndValueDto> mp = new HashMap<String, NameAndValueDto>();
		mp.put("1", createNameAndValueDto(devLst, "1", "正常"));
		mp.put("2", createNameAndValueDto(devLst, "2", "离线"));
		mp.put("3", createNameAndValueDto(devLst, "3", "故障"));
		mp.put("4", createNameAndValueDto(devLst, "4", "异常"));
		return mp;
	}

	/**
	 * delete createNameAndValueDto(计算某一种状态设备数目) @Title:
	 * createNameAndValueDto @Description: 计算某一种状态设备数目 @param @param
	 * devLst @param @param status @param @param statusName @param @return
	 * 设定文件 @return NameAndValueDto 返回类型 @throws
	 */
	private NameAndValueDto createNameAndValueDto(List<Sys> devLst, String status, String statusName) {
		NameAndValueDto nomalDto = new NameAndValueDto();
		nomalDto.setName(statusName);
		long nomalNum = 0;
		Stream<Sys> devFilter = devLst.stream().filter(p -> p.getStatusType().equals(status));
		if (devFilter != null) {
			nomalNum = devFilter.count();
		}
		nomalDto.setValue((int) nomalNum);
		return nomalDto;
	}

}
