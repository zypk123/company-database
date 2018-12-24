package cy.its.device.rest.action.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.DeviceMaintainCriteria;
import cy.its.device.domain.model.Contractor;
import cy.its.device.domain.model.DeviceFault;
import cy.its.device.domain.model.DeviceMaintainRegister;
import cy.its.device.domain.model.FalutMaintainAsso;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.IContractService;
import cy.its.device.domain.service.IDevMaintainService;
import cy.its.device.domain.service.ISiteService;
import cy.its.device.domain.service.ISystemService;
import cy.its.device.rest.action.IMaintainManageAction;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.FaultAlarmDto;
import cy.its.device.rest.dto.MaintainDto;
import cy.its.device.rest.dto.Results;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.service.IDutyService;

@RestController
@RequestMapping("/device/maintainManage")
public class MaintainManageAction implements IMaintainManageAction {
	
	@Autowired
	IDevMaintainService devMaintainService;
	
	@Autowired
	IContractService contractService;
	
	@Autowired
	ISystemService systemService; 
	
	@Autowired
	ISiteService siteService;
	
	@Autowired
	IDutyService dutyService;
	
	/**
	 * 查询维护单列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/queryMaintain")
	public DataGridResult<MaintainDto> queryMaintain(@ModelAttribute("form") MaintainDto form) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DeviceMaintainCriteria deviceMaintainCriteria = new DeviceMaintainCriteria();
		deviceMaintainCriteria.setPageNum(form.getPageNumber());
		deviceMaintainCriteria.setPageSize(form.getPageSize());
		deviceMaintainCriteria.setOrderName(form.getOrderName());
		deviceMaintainCriteria.setOrderType(form.getOrderType());
		if(!StringUtil.isNullOrEmpty(form.getCreateTimeFrom())){
			deviceMaintainCriteria.createTimeFrom = sdfs.parse(form.getCreateTimeFrom() + " 00:00:00");
		}
		if(!StringUtil.isNullOrEmpty(form.getCreateTimeTo())){
			deviceMaintainCriteria.createTimeTo = sdfs.parse(form.getCreateTimeTo() + " 23:59:59");
		}
		deviceMaintainCriteria.maintenanceCompanyId = form.getMaintenanceCompany();//维护厂商
		deviceMaintainCriteria.maintenanceStatus = form.getMaintenanceStatus();	//维护单状态
		deviceMaintainCriteria.maintendanceResult = form.getMaintendanceResult();//维护结论
		if(!StringUtil.isNullOrEmpty(form.getOrgId())){
			Organization org = dutyService.organizationOfId(form.getOrgId());//根据机构ID找机构信息
			if(!StringUtil.isNullOrEmpty(org.orgPrivilegeCode)){
				deviceMaintainCriteria.orgPrivilegeCode = org.orgPrivilegeCode;//机构过滤code
			}
		}else{
			deviceMaintainCriteria.orgPrivilegeCode = form.getCurrentOrgPrivilegeCode();//登录用户所在机构过滤code
		}
		List<DeviceMaintainRegister> list = devMaintainService.findMaintain(deviceMaintainCriteria);
		List<MaintainDto> lst = new ArrayList<MaintainDto>();
		for (int i = 0; i < list.size(); i++) {
			MaintainDto maintain = maintainToDTO(sdf, list.get(i));
			lst.add(maintain);
		}
		Results<MaintainDto> res = new Results<MaintainDto>();
		long total = deviceMaintainCriteria.getTotal();
		res.setRows(lst);
		res.setTotal(total);
		DataGridResult<MaintainDto> dgr = new DataGridResult<MaintainDto>();
		dgr.setErro("");
		dgr.setResult(res);
		return dgr;
	}

	/**
	 * 根据deviceId查询故障维护单
	 * @throws Exception 
	 */
	@RequestMapping(value = "/queryMaintianByDeviceId")
	public DataGridResult<MaintainDto> queryMaintianByDeviceId(String deviceId) throws Exception {
		List<DeviceMaintainRegister> lst = devMaintainService.maintainsWithOpenFaultOfDevice(deviceId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		List<MaintainDto> lstMt = new ArrayList<MaintainDto>();
		if (lst != null) {
			for (DeviceMaintainRegister r : lst) {
				lstMt.add(maintainToDTO(sdf, r));
			}
		}

		Results<MaintainDto> res = new Results<MaintainDto>();
		res.setRows(lstMt);
		res.setTotal(lstMt != null ? lstMt.size() : 0);
		DataGridResult<MaintainDto> dgr = new DataGridResult<MaintainDto>();
		dgr.setErro("");
		dgr.setResult(res);
		return dgr;
	}

	private MaintainDto maintainToDTO(SimpleDateFormat sdf, DeviceMaintainRegister m) throws Exception {
		MaintainDto maintain = new MaintainDto();
		ObjectMapUtils.parseObject(maintain, m);
		if (m.getAssignTime() != null) {
			maintain.setAssignTime(sdf.format(m.getAssignTime()));
		}
		if (m.getSolutionTime() != null) {
			maintain.setSolutionTime(sdf.format(m.getSolutionTime()));
		}

		if (!StringUtil.isNullOrEmpty(m.getMaintenanceCompany())) {
			Contractor contractor = contractService.getContractorById(m.getMaintenanceCompany());
			if (contractor != null) {
				maintain.setMaintenanceCompanyName(contractor.getContractorName());
			}
		}
		return maintain;
	}
	
	/**
	 * 批量分派维护单
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/updateSomeMaintain")
	public String updateSomeMaintain(@ModelAttribute("form") MaintainDto form) throws Exception {
		// TODO Auto-generated method stub
		String  flag = "";
		String maintenanceIds[] = form.getMaintenanceIdStr().split(",");
		String companyId = form.getMaintenanceCompany();
		String contactor = form.getContactPerson();
		String phone = form.getPhoneNbr();
		String finishTime = form.getFinishTime();
		String dispatchPerson = form.getCurrentUserId();
		//修改厂商信息的联系人和联系方式
		Contractor Contractors = contractService.getContractorById(companyId);//根据id去查厂商信息
		//判断厂商联系人和联系方式是否改动，若改动则更新厂商信息
		if(Contractors.getContractorPerson() == null || Contractors.getContractorTel() == null){
			Contractor contractor = new Contractor();
			contractor.setContractorId(companyId);
			contractor.setContractorName(Contractors.getContractorName());
			contractor.setContractorPerson(contactor);
			contractor.setContractorTel(phone);
			contractService.updateContractor(contractor);
			for (int i = 0; i < maintenanceIds.length; i++) {
				String maintenanceId = maintenanceIds[i];
				devMaintainService.dispatchMaintain(maintenanceId, companyId, contactor, phone, finishTime, dispatchPerson);
			}
			flag = "successCompany";
		}else{
			for (int i = 0; i < maintenanceIds.length; i++) {
				String maintenanceId = maintenanceIds[i];
				devMaintainService.dispatchMaintain(maintenanceId, companyId, contactor, phone, finishTime, dispatchPerson);
			}
			flag = "success";
		}
		return flag;
	}
	
	/**
	 * 单个分派维护单
	 * @param form
	 * @return
	 * @throws Exception gu
	 */
	@Override
	@RequestMapping(value = "/updateMaintain")
	public String updateMaintain(@RequestParam("maintenanceId") String maintenanceId, @RequestParam("companyId") String companyId, 
			@RequestParam("contactor") String contactor, @RequestParam("phone") String phone, @RequestParam("finishTime") String finishTime,
			@RequestParam("dispatchPerson") String dispatchPerson) throws Exception {
		String flag = "";
		//修改厂商信息的联系人和联系方式
		Contractor Contractors = contractService.getContractorById(companyId);//根据id去查厂商信息
		//判断厂商联系人和联系方式是否改动，若改动则更新厂商信息
		if(Contractors.getContractorPerson() == null && Contractors.getContractorTel() == null){
			Contractor contractor = new Contractor();
			contractor.setContractorId(companyId);
			contractor.setContractorName(Contractors.getContractorName());
			contractor.setContractorPerson(contactor);
			contractor.setContractorTel(phone);
			contractService.updateContractor(contractor);
			devMaintainService.dispatchMaintain(maintenanceId, companyId, contactor, phone, finishTime, dispatchPerson);
			flag = "successCompany";
		}else{
			devMaintainService.dispatchMaintain(maintenanceId, companyId, contactor, phone, finishTime, dispatchPerson);
			flag = "success";
		}
		return flag;
	}
	
	/**
	 * 登记维护单
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/updateMaintainResult")
	public String updateMaintainResult(@RequestParam("maintenanceId") String maintenanceId,@RequestParam("mainResult") String mainResult,
			@RequestParam("solution") String solution,@RequestParam("solutionTimes") String solutionTimes,
			@RequestParam("photoUrl") String photoUrl,@RequestParam("remark") String remark,@RequestParam("faultIdStr") String faultIdStr) throws Exception {

		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date solutionTime = null;//解决时间
		if(!StringUtil.isNullOrEmpty(solutionTimes)){
			solutionTime = sdf.parse(solutionTimes);
		}
		devMaintainService.callBackMaintain(maintenanceId, mainResult, solution, solutionTime, photoUrl,remark);
		//根据维修结论改变故障的处理状态
		if("2".equals(mainResult)){//2部分未维修好（1全部未维修好）
			changeFaultStatus(faultIdStr);
		}else if("3".equals(mainResult)){//3全部维修好
			DeviceMaintainRegister deviceMaintainRegister = devMaintainService.selectMaintainAndFaultByKey(maintenanceId);
			List<DeviceFault> list = deviceMaintainRegister.getDeviceFaults();
			for (int i = 0; i < list.size(); i++) {
				changeFaultStatus(list.get(i).getFaultId());
			}
		}
		return "success";
	}
	/**
	 * 根据登记结果改变故障处理状态
	 * @param faultIdStr
	 */
	private void changeFaultStatus(String faultIdStr){
		if(!StringUtil.isNullOrEmpty(faultIdStr)){
			String faultIds[] = faultIdStr.split(",");
			for (int i = 0; i < faultIds.length; i++) {
				DeviceFault deviceFault = new DeviceFault();
				deviceFault.setFaultId(faultIds[i]);
				deviceFault.setProcessState("3");//设置故障处理状态为已解决
				devMaintainService.updateFault(deviceFault);
			}
		}
	}
	
	/**
	 * 删除维护单
	 * @param maintenanceIdStr
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/deleteMaintain")
	public String deleteMaintain(String maintenanceId) throws Exception {
		FalutMaintainAsso falutMaintainAsso = new FalutMaintainAsso();
		falutMaintainAsso.setMaintainId(maintenanceId);
		DeviceMaintainRegister deviceMaintainRegister = devMaintainService.selectMaintainAndFaultByKey(maintenanceId);
		List<DeviceFault> list = deviceMaintainRegister.getDeviceFaults();//维护单中所有故障
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				String faultId = list.get(i).getFaultId();
				String processState = list.get(i).getProcessState();
				if("2".equals(processState)){ //2 未解决故障
					devMaintainService.updateFaultProcessStatus(faultId, "0", new Date());//更新未解决故障处理状态为未处理
				}
			}
		}
		devMaintainService.deleteMainAndFaultAsso(falutMaintainAsso);//删除维护单和故障的关联关系
		devMaintainService.deleteMaintain(maintenanceId);//删除维护单
		return "success";
	}
	
	/**
	 * 删除故障和维护单的关联关系
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/deleteMainFaultAsso")
	public String deleteMainFaultAsso(@RequestParam("faultId") String faultId,@RequestParam("maintenanceId") String maintenanceId) throws Exception {
		FalutMaintainAsso falutMaintainAsso = new FalutMaintainAsso();
		falutMaintainAsso.setFaultId(faultId);
		falutMaintainAsso.setMaintainId(maintenanceId);
		int a = devMaintainService.deleteMainAndFaultAsso(falutMaintainAsso);
		if(a > 0){
			devMaintainService.updateFaultProcessStatus(faultId, "0", new Date());//更新故障处理状态为未处理
		}
		return "success";
	}
	
	
	/**
	 * 根据维护单ID查询维护单及故障列表信息
	 * @param maintenanceId
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/queryMaintainAndFaultById")
	public DataGridResult<FaultAlarmDto> queryMaintainAndFaultById(@ModelAttribute("form") MaintainDto form) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String maintenanceId = form.getMaintenanceId();
		DeviceMaintainRegister deviceMaintainRegister = devMaintainService.selectMaintainAndFaultByKey(maintenanceId);
		List<DeviceFault> list = deviceMaintainRegister.getDeviceFaults();
		List<FaultAlarmDto> lst = new ArrayList<FaultAlarmDto>();
		for (int i = 0; i < list.size(); i++) {
			FaultAlarmDto faultAlarm = new FaultAlarmDto();
			ObjectMapUtils.parseObject(faultAlarm, list.get(i));
			if(list.get(i).getStartTime() != null){
				faultAlarm.setStartTime(sdf.format(list.get(i).getStartTime()));
			}
			if(!StringUtil.isNullOrEmpty(list.get(i).getDeviceId())){
				Sys sys = systemService.systemOfId(list.get(i).getDeviceId());//根据设备ID查询设备信息
				ObjectMapUtils.parseObject(faultAlarm, sys);
				String siteId = sys.getSiteId();
				Site site = siteService.siteOfId(siteId);
				faultAlarm.setSiteName(site.getSiteName());
			}
			lst.add(faultAlarm);
		}
		Results<FaultAlarmDto> res = new Results<FaultAlarmDto>();
		res.setRows(lst);
		DataGridResult<FaultAlarmDto> dgr = new DataGridResult<FaultAlarmDto>();
		dgr.setErro("");
		dgr.setResult(res);
		return dgr;
	}
	 
	
}
