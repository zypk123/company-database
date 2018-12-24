package cy.its.device.rest.action.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.DeviceFaultCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.Contractor;
import cy.its.device.domain.model.DeviceFault;
import cy.its.device.domain.model.DeviceFaultList;
import cy.its.device.domain.model.DeviceMaintainRegister;
import cy.its.device.domain.model.FalutMaintainAsso;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.IContractService;
import cy.its.device.domain.service.IDevMaintainService;
import cy.its.device.domain.service.ISiteService;
import cy.its.device.domain.service.ISystemService;
import cy.its.device.rest.action.IFaultAlarmManageAction;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.FaultAlarmDto;
import cy.its.device.rest.dto.Results;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.service.IDutyService;

@RestController
@RequestMapping("/device/faultAlarmManage")
public class FaultAlarmManageAction implements IFaultAlarmManageAction {
	
	@Autowired
	ISystemService systemService; 
	
	@Autowired
	IDevMaintainService devMaintainService;
	
	@Autowired
	ISiteService siteService;
	
	@Autowired
	IContractService contractService;
	
	@Autowired
	IDutyService dutyService;
	
	/**
	 * 查询故障报警列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value="/queryFaultAlarm")
	public DataGridResult<FaultAlarmDto> queryFaultAlarm(@ModelAttribute("FaultAlarmDto") FaultAlarmDto form) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DeviceFaultCriteria faultCriteria = new DeviceFaultCriteria();
		if(!StringUtil.isNullOrEmpty(form.getOrgId())){
			Organization org = dutyService.organizationOfId(form.getOrgId());
			if(!StringUtil.isNullOrEmpty(org.orgPrivilegeCode)){
				faultCriteria.orgPrivilegeCode = org.orgPrivilegeCode;//机构过滤code
			}
		}else{
			faultCriteria.orgPrivilegeCode = form.getCurrentOrgPrivilegeCode();//登录用户所在机构过滤code
		}
		faultCriteria.deviceType = form.getDeviceType();//设备类型
		faultCriteria.roadId = form.getRoadId();//道路ID
		faultCriteria.faultType = form.getFaultType();//故障类型
		faultCriteria.contractorId = form.getContractorId();//设备厂商ID
		faultCriteria.isValid = form.getIsValidity();//故障有效性
		//faultCriteria.deviceId = form.getDeviceId();//设备Id
		faultCriteria.deviceSysNbr = form.getDeviceSysNbr();//设备编号
		faultCriteria.setOrderName(form.getOrderName());
		faultCriteria.setOrderType(form.getOrderType());
		faultCriteria.setPageNum(form.getPageNumber());
		faultCriteria.setPageSize(form.getPageSize());
		if(!StringUtil.isNullOrEmpty(form.getStartTimeFrom())){
			faultCriteria.startTimeFrom = sdfs.parse(form.getStartTimeFrom() + " 00:00:00");//查询条件发生时间的开始时间
		}
		if(!StringUtil.isNullOrEmpty(form.getStartTimeTo())){
			faultCriteria.startTimeTo = sdfs.parse(form.getStartTimeTo() + " 23:59:59");//查询条件发生时间的结束时间
		}
		List<DeviceFaultList> list = devMaintainService.findFaultsList(faultCriteria);		
		long total = faultCriteria.getTotal();
		List<FaultAlarmDto> lst = new ArrayList<FaultAlarmDto>();
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				FaultAlarmDto faultAlarm = new FaultAlarmDto();
				ObjectMapUtils.parseObject(faultAlarm, list.get(i));
				if(list.get(i).getStartTime() != null){
					faultAlarm.setStartTime(sdf.format(list.get(i).getStartTime()));
				}
				/*if(!StringUtil.isNullOrEmpty(list.get(i).getDeviceId())){
					Sys sys = systemService.systemOfId(list.get(i).getDeviceId());//根据设备ID查询设备信息
					if(sys != null){
						ObjectMapUtils.parseObject(faultAlarm, sys);
						if(!StringUtil.isNullOrEmpty(sys.getSiteId())){
							String siteId = sys.getSiteId();
							Site site = siteService.siteOfId(siteId);
							faultAlarm.setSiteName(site.getSiteName());
						}
						if(!StringUtil.isNullOrEmpty(sys.getContractorId())){
							Contractor contractor = contractService.getContractorById(sys.getContractorId());
							faultAlarm.setContractorName(contractor.getContractorName());
						}
					}
				}*/
				lst.add(faultAlarm);
			}
		}
		Results<FaultAlarmDto> res = new Results<FaultAlarmDto>();
		res.setRows(lst);
		res.setTotal(total);
		DataGridResult<FaultAlarmDto> dgr = new DataGridResult<FaultAlarmDto>();
		dgr.setErro("");
		dgr.setResult(res);
		return dgr;
	}
	
	/**
	 * 添加故障信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value="/addFaultAlarm")
	public String addFaultAlarm(@ModelAttribute("FaultAlarmDto") FaultAlarmDto form) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DeviceFault fault = new DeviceFault();
		ObjectMapUtils.parseObject(fault, form);
		if(!StringUtil.isNullOrEmpty(form.getStartTime())){
			fault.setStartTime(sdf.parse(form.getStartTime()));
		}
		if(!StringUtil.isNullOrEmpty(form.getEndTime())){
			fault.setEndTime(sdf.parse(form.getEndTime()));
		}
		if(!StringUtil.isNullOrEmpty(form.getDeviceId())){
			Sys sys = systemService.systemOfId(form.getDeviceId());
			if(sys != null){
				fault.setOrgPrivilegeCode(sys.getOrgPrivilegeCode());
			}else{
				fault.setOrgPrivilegeCode(form.getCurrentOrgPrivilegeCode());
			}
		}
		fault.setProcessState("0");//新增故障设置默认处理状态为未处理
		fault.setIsValidity("1");//新增故障设置默认有效状态为有效
		fault.setCreateBy(form.getCurrentUserId());//创建人
		fault.setCreateTime(new Date());//创建时间为当前系统时间
		devMaintainService.createDeviceFault(fault);
		return "success";
	}
	
	/**
	 * 设置设备故障的有效性
	 * @param faultId 故障ID
	 * @param validType	有效性标识
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value="/updateValidateFault")
	public String updateValidateFault(@RequestParam("faultId") String faultId,@RequestParam("validType") String validType) throws Exception {
		// TODO Auto-generated method stub
		devMaintainService.validateFault(faultId, validType);
		DeviceFault deviceFault = new DeviceFault();
		deviceFault.setFaultId(faultId);
		deviceFault.setIsValidity(validType);
		if("2".equals(validType)){//如果状态改为无效
			deviceFault.setProcessState("1");//新增故障设置默认处理状态为无需处理
		}else if("1".equals(validType)){//如果状态改为有效
			deviceFault.setProcessState("0");//新增故障设置默认处理状态为未处理
		}
		devMaintainService.updateFault(deviceFault);
		return "success";
	}
	

	/**
	 * 生成维护单
	 * @param faultIdStr 多个故障ID字符串
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value="/addMaintainRegister")
	public String addMaintainRegister(@RequestParam("faultIdStr") String faultIdStr,@RequestParam("orgPrivilegeCode") String orgPrivilegeCode) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		DeviceMaintainRegister deviceMaintainRegister = new DeviceMaintainRegister();//维护单对象
		deviceMaintainRegister.setMaintenanceNbr(sdf.format(new Date()));//设置默认维护单编号为当前时间形成的字符串
		deviceMaintainRegister.setMaintenanceStatus("0");//生成维护单设置默认状态为未分派
		//deviceMaintainRegister.setMaintendanceResult("1");////生成维护单设置默认维护结论为未解决
		deviceMaintainRegister.setOrgPrivilegeCode(orgPrivilegeCode);
		String maintainId = devMaintainService.createMaintainRegister(deviceMaintainRegister);//新增维护单返回维护单ID
		if(!StringUtil.isNullOrEmpty(maintainId)){
			String faultIds[] = faultIdStr.split(",");
			for (int i = 0; i < faultIds.length; i++) {
				FalutMaintainAsso falutMaintainAsso = new FalutMaintainAsso();//故障维护单关系对象
				falutMaintainAsso.setFaultId(faultIds[i]);
				falutMaintainAsso.setMaintainId(maintainId);
				devMaintainService.createMainFaultAsso(falutMaintainAsso);//添加维护单和故障的关联关系
				DeviceFault deviceFault = new DeviceFault();//故障对象
				deviceFault.setFaultId(faultIds[i]);
				deviceFault.setProcessState("2");//生成维护单后设置故障处理状态为未解决
				deviceFault.setProcessTime(new Date());//生成维护单后设置故障处理时间为当前时间
				devMaintainService.updateFault(deviceFault);
			}
		}
		return "success";
	}
	
	
	/**
	 * 根据但前用户所在机构code查询所有设备
	 * @param orgPrivilegeCode
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value="/findDeviceByOrgPrivilegeCode")
	public List<FaultAlarmDto> findDeviceByOrgPrivilegeCode(@RequestParam("orgPrivilegeCode") String orgPrivilegeCode) throws Exception {
		// TODO Auto-generated method stub
		SystemCriteria criteria = new SystemCriteria();
		criteria.orgPrivilegeCode = orgPrivilegeCode;//当前用户所在机构权限过滤code
		List<Sys> list = systemService.findSys(criteria);//查询设备
		List<FaultAlarmDto> lst = new ArrayList<FaultAlarmDto>();
		for (int i = 0; i < list.size(); i++) {
			FaultAlarmDto faultAlarm = new FaultAlarmDto();
			faultAlarm.setDeviceId(list.get(i).getDeviceId());
			faultAlarm.setDeviceName(list.get(i).getDeviceName());
			lst.add(faultAlarm);
		}
		return lst;
	}

}
