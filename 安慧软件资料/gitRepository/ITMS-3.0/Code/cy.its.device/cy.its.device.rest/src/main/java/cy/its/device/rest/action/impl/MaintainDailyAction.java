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
import cy.its.device.domain.criteria.DailyMaintainCriteria;
import cy.its.device.domain.model.DailyMaintain;
import cy.its.device.domain.service.IMaintainService;
import cy.its.device.rest.action.IMaintainDailyAction;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.MaintainDailyDto;
import cy.its.device.rest.dto.Results;
import cy.its.road.domain.model.road.Road;
import cy.its.road.domain.service.IRoadService;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.service.IDutyService;

@RestController
@RequestMapping("device/maintainDaily")
public class MaintainDailyAction implements IMaintainDailyAction {
	
	@Autowired
	IMaintainService maintainService; 
	
	@Autowired
	IRoadService roadService;
	
	@Autowired
	IDutyService dutyService;
	
	/**
	 * 添加日常登记信息
	 * @param form 日常登记信息
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/addMaintainDaily")
	public String addMaintainDaily(@ModelAttribute("form") MaintainDailyDto form) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
		DailyMaintain dailyMaintain = new DailyMaintain();
		ObjectMapUtils.parseObject(dailyMaintain, form);
		if(!StringUtil.isNullOrEmpty(form.getRecentlyUploadTime())){
			dailyMaintain.setRecentlyUploadTime(sdf.parse(form.getRecentlyUploadTime()));//最近上传时间
		}
		if(!StringUtil.isNullOrEmpty(form.getMaintenanceDate())){
			dailyMaintain.setMaintenanceDate(sdfs.parse(form.getMaintenanceDate()));//维护时间
		}
		if(!StringUtil.isNullOrEmpty(form.getNextMaintenanceDate())){
			dailyMaintain.setNextMaintenanceDate(sdfs.parse(form.getNextMaintenanceDate()));//下次维护时间
		}
		dailyMaintain.setCreateBy(form.getCurrentUserId());//创建人为当前用户
		dailyMaintain.setCreateTime(new Date());//设置创建时间为当前时间
		maintainService.createDailyMaintain(dailyMaintain);
		return "success";
	}
	
	/**
	 * 删除日常登记信息
	 * @param dailyMaintenanceId 维护记录ID
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/deleteMaintainDaily")
	public String deleteMaintainDaily(@RequestParam("dailyMaintenanceId") String dailyMaintenanceId) throws Exception {
		maintainService.deleteDailyMaintain(dailyMaintenanceId);
		return "success";
	}

	/**
	 * 修改日常登记信息
	 * @param form 日常登记信息
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/editMaintainDaily")
	public String editMaintainDaily(@ModelAttribute("form") MaintainDailyDto form) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
		DailyMaintain dailyMaintain = new DailyMaintain();
		ObjectMapUtils.parseObject(dailyMaintain, form);
		if(!StringUtil.isNullOrEmpty(form.getRecentlyUploadTime())){
			dailyMaintain.setRecentlyUploadTime(sdf.parse(form.getRecentlyUploadTime()));//最近上传时间
		}
		if(!StringUtil.isNullOrEmpty(form.getMaintenanceDate())){
			dailyMaintain.setMaintenanceDate(sdfs.parse(form.getMaintenanceDate()));//维护时间
		}
		if(!StringUtil.isNullOrEmpty(form.getNextMaintenanceDate())){
			dailyMaintain.setNextMaintenanceDate(sdfs.parse(form.getNextMaintenanceDate()));//下次维护时间
		}
		maintainService.updateDailyMaintain(dailyMaintain);
		return "success";
	}
	
	/**
	 * 查询日常登记信息列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/queryMaintainDailyList")
	public DataGridResult<MaintainDailyDto> queryMaintainDailyList(@ModelAttribute("form") MaintainDailyDto form) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<MaintainDailyDto> lst = new ArrayList<MaintainDailyDto>();
		DailyMaintainCriteria criteria = new DailyMaintainCriteria();
		ObjectMapUtils.parseObject(criteria, form);
		criteria.setOrderName(form.getOrderName());
		criteria.setOrderType(form.getOrderType());
		criteria.setPageNum(form.getPageNumber());
		criteria.setPageSize(form.getPageSize());
		if(!StringUtil.isNullOrEmpty(form.getMaintenanceDateFrom())){
			criteria.maintenanceDateFrom = sdf.parse(form.getMaintenanceDateFrom());
		}
		if(!StringUtil.isNullOrEmpty(form.getMaintenanceDateTo())){
			criteria.maintenanceDateTo = sdf.parse(form.getMaintenanceDateTo());
		}
		//根据orgId查orgPrivilegeCode
		if(!StringUtil.isNullOrEmpty(form.getOrgId())){
			Organization org = dutyService.organizationOfId(form.getOrgId());
			criteria.orgPrivilegeCode = org.orgPrivilegeCode;
		}
		criteria.setNeedTotal(true);
		List<DailyMaintain> list = maintainService.findDailyMaintains(criteria);
		for (int i = 0; i < list.size(); i++) {
			MaintainDailyDto maintainDaily = new MaintainDailyDto();
			ObjectMapUtils.parseObject(maintainDaily, list.get(i));
			//根据道路ID查询道路信息，获取道路名称
			if(!StringUtil.isNullOrEmpty(list.get(i).getRoadId())){
				Road road = roadService.roadOfId(list.get(i).getRoadId());
				if(road != null){
					maintainDaily.setRoadName(road.getRoadName());
				}
			}
			if(list.get(i).getMaintenanceDate() != null){
				maintainDaily.setMaintenanceDate(sdf.format(list.get(i).getMaintenanceDate()));
			}
			if(list.get(i).getNextMaintenanceDate() != null){
				maintainDaily.setNextMaintenanceDate(sdf.format(list.get(i).getNextMaintenanceDate()));
			}
			lst.add(maintainDaily);
		}
		long total = criteria.getTotal();
		Results<MaintainDailyDto> res = new Results<MaintainDailyDto>();
		res.setRows(lst);
		res.setTotal(total);
		DataGridResult<MaintainDailyDto> dgr = new DataGridResult<MaintainDailyDto>();
		dgr.setErro("");
		dgr.setResult(res);
		return dgr;
	}
	
	/**
	 * 根据日常维护信息ID查询维护信息
	 * @param dailyMaintenanceId 日常维护信息ID
	 * @return 日常维护对象
	 * @throws Exception 异常
	 */
	@Override
	@RequestMapping(value = "/lookMaintainDaily")
	public MaintainDailyDto lookMaintainDaily(@RequestParam("dailyMaintenanceId") String dailyMaintenanceId) throws Exception {
		SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		MaintainDailyDto maintainDaily = new MaintainDailyDto();
		DailyMaintain dailyMaintain = maintainService.dailyMaintainOfId(dailyMaintenanceId);
		ObjectMapUtils.parseObject(maintainDaily, dailyMaintain);
		if(dailyMaintain.getMaintenanceDate() != null){
			maintainDaily.setMaintenanceDate(sdf.format(dailyMaintain.getMaintenanceDate()));
		}
		if(dailyMaintain.getRecentlyUploadTime() != null){
			maintainDaily.setRecentlyUploadTime(sdfs.format(dailyMaintain.getRecentlyUploadTime()));
		}
		if(dailyMaintain.getNextMaintenanceDate() != null){
			maintainDaily.setNextMaintenanceDate(sdf.format(dailyMaintain.getNextMaintenanceDate()));
		}
		if(dailyMaintain.getCreateTime() != null){
			maintainDaily.setCreateTime(sdf.format(dailyMaintain.getCreateTime()));
		}
		return maintainDaily;
	}

}
