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
import cy.its.device.domain.criteria.DeviceRegionCriteria;
import cy.its.device.domain.model.DeviceRegion;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.IDeviceRegionService;
import cy.its.device.domain.service.ISiteService;
import cy.its.device.rest.action.IIntervalSysAction;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.IntervalSysDto;
import cy.its.device.rest.dto.Results;
import cy.its.platform.common.exception.ItmsAppException;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.service.IDutyService;


@RestController
@RequestMapping("/device/IntervalManage")
public class IntervalSysAction implements IIntervalSysAction {

	@Autowired
	IDeviceRegionService deviceRegionService;
	
	@Autowired
	ISiteService siteService;

	@Autowired
	IDutyService dutyService;
	
	/**
	 * 查询区间系统信息
	 * @param form 查询条件
	 * @return 查询结果对象列表
	 * 
	 */
	@Override
	@RequestMapping(value = "/queryIntervalSys")
	public DataGridResult<IntervalSysDto> queryIntervalSys(@ModelAttribute("form") IntervalSysDto form) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 		DeviceRegionCriteria criteria = new DeviceRegionCriteria();
		criteria.setPageNum(form.getPageNumber());
		criteria.setPageSize(form.getPageSize());
		criteria.setOrderName(form.getOrderName());
		criteria.setOrderType(form.getOrderType());
		criteria.regionalName = form.getRegionalName();
		criteria.orgId = form.getCurrentOrgId();
		criteria.roadId = form.getRoadId();
		criteria.regionalCode = form.getRegionalCode();
		if(!StringUtil.isNullOrEmpty(form.getEnableFlag())){
			criteria.enableFlagArr = form.getEnableFlag().split(",");
		}
		
		List<DeviceRegion> list = deviceRegionService.findDeviceRegion(criteria);
		long total = criteria.getTotal();
		List<IntervalSysDto> lst = new ArrayList<IntervalSysDto>();
		if(list.size() != 0){
			for (int i = 0; i < list.size(); i++) {
				IntervalSysDto intervalSysDto = new IntervalSysDto();
				ObjectMapUtils.parseObject(intervalSysDto, list.get(i));
				if(!StringUtil.isNullOrEmpty(list.get(i).getExpireDate())){
					intervalSysDto.setExpireDate(sdf.format(list.get(i).getExpireDate()));
				}					
				String entrySiteId = list.get(i).getEntrySiteId();
				String exitSiteId = list.get(i).getExitSiteId();
				if(!StringUtil.isNullOrEmpty(entrySiteId)){
					if(siteService.siteOfId(entrySiteId) != null){
						String entrySiteName = siteService.siteOfId(entrySiteId).getSiteName();
						intervalSysDto.setEntrySiteName(entrySiteName);
					}
				}
				if(!StringUtil.isNullOrEmpty(exitSiteId)){
					if(siteService.siteOfId(exitSiteId) != null){
						String exitSiteName = siteService.siteOfId(exitSiteId).getSiteName();
						intervalSysDto.setExitSiteName(exitSiteName);
					}
				}
				intervalSysDto.setDistance(String.valueOf(list.get(i).getDistance()));
				lst.add(intervalSysDto);
			}
		}
		Results<IntervalSysDto> res = new Results<IntervalSysDto>();
		res.setRows(lst);
		res.setTotal(total);
		DataGridResult<IntervalSysDto> dgr = new DataGridResult<IntervalSysDto>();
		dgr.setErro("");
		dgr.setResult(res);
		return dgr;
	}
	
	/**
	 * query orgID by siteId
	 * @param siteId
	 * @return orgId
	 */
	@RequestMapping(value = "/queryOrgIdBySiteId")
	public String queryOrgIdBySiteId(String siteId) throws Exception {
		//String siteId = null;
		String orgId;
		Site s = siteService.siteOfId(siteId);
		//Organization o = dutyService.organizationOfId(s.getOrgId());
		orgId = s.getOrgId();
		return orgId;
	}
	 
	/**
	 * 添加区间系统信息
	 * @param form 添加的区间系统信息
	 * @return 整形 1表示成功，0表示失败
	 */
	@Override
	@RequestMapping(value = "/addIntervalSys")
	public String addIntervalSys(@ModelAttribute("form") IntervalSysDto form) throws Exception {
		// TODO Auto-generated method stub
		
		DeviceRegionCriteria deviceRegionCriteria = new DeviceRegionCriteria();
		//设置查询条件
		if(!StringUtil.isNullOrEmpty(form.getRegionalCode())){
			deviceRegionCriteria.regionalCode = form.getRegionalCode();
			List<DeviceRegion> list1 = deviceRegionService.findDeviceRegion(deviceRegionCriteria);
			
			if(!list1.isEmpty()){
				throw new ItmsAppException("区间编号已存在，请重新输入！");
			}
		}
		DeviceRegion deviceRegion = new DeviceRegion();
		ObjectMapUtils.parseObject(deviceRegion, form);
		if(!StringUtil.isNullOrEmpty(form.getDistance())){
			deviceRegion.setDistance(Double.valueOf(form.getDistance()));
		}
		deviceRegion.setCreateTime(new Date());//创建时间
		deviceRegion.setCreateBy(form.getCurrentUserId());//创建人
		deviceRegionService.createDeviceRegion(deviceRegion);
		return "1";
	}
	
	/**
	 * 删除区间系统信息
	 * @param regionalId 区间系统ID
	 * @return 整形 1表示成功，0表示失败
	 */
	@Override
	@RequestMapping(value = "/removeIntervalSys")
	public int removeIntervalSys(@RequestParam("regionalId") String regionalId) throws Exception {
		// TODO Auto-generated method stub
		String regionId = regionalId;
		deviceRegionService.removeDeviceRegion(regionId);
		return 1;
	}
	
	/**
	 * 编辑区间系统信息
	 * @param form 区间系统编辑后信息
	 * @return 整形 1表示成功，0表示失败
	 */
	@Override
	@RequestMapping(value = "/updateIntervalSys")
	public String updateIntervalSys(@ModelAttribute("form") IntervalSysDto form) throws Exception {
		// TODO Auto-generated method stub
		//根据RegionalId返回实例对象数据
		DeviceRegion deviceRegion = deviceRegionService.deviceRegionOfId(form.getRegionalId());
		//如果编号有改变，做唯一性验证
		if(!StringUtil.isNullOrEmpty(form.getRegionalCode())){
			if(!(deviceRegion.getRegionalCode().equals(form.getRegionalCode()))){
				DeviceRegionCriteria deviceRegionCriteria = new DeviceRegionCriteria();
				//设置查询条件
				deviceRegionCriteria.regionalCode = form.getRegionalCode();
				List<DeviceRegion> list1 = deviceRegionService.findDeviceRegion(deviceRegionCriteria);
				
				if(!list1.isEmpty()){
					throw new ItmsAppException("区间编号已存在，请重新输入！");
				} 
			}
		}
		deviceRegion = new DeviceRegion();
		ObjectMapUtils.parseObject(deviceRegion, form);
		if(!StringUtil.isNullOrEmpty(form.getDistance())){
			deviceRegion.setDistance(Double.valueOf(form.getDistance()));
		}
		deviceRegion.setUpdateBy(form.getCurrentUserId());//更新人员
		deviceRegionService.updateDeviceRegion(deviceRegion);
		//System.out.println(deviceRegion.getEnableFlag());
		return "1";
	}
	
	/**
	 * 改变区间系统启用标识
	 * @param devRegionId
	 * @param useStatus
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/changeDeviceRegionUseStauts")
	public int changeDeviceRegionUseStauts(String regionalIdStr,String userName,int optStatus) throws Exception{
			System.out.println(userName+" "+regionalIdStr +" "+optStatus);
			String devRegionIdStrs[] = regionalIdStr.split(",");
			//判断改变类型目标
			if(1 == optStatus){
				//String userName = "";
				for (int i = 0; i < devRegionIdStrs.length; i++) {
					  
					deviceRegionService.enableDeviceRegion(devRegionIdStrs[i], userName);
				}
			}else if(2 == optStatus){
				for (int i = 0; i < devRegionIdStrs.length; i++) {
					deviceRegionService.disableDeviceRegion(devRegionIdStrs[i], userName);
				}
			}else if(3 == optStatus){
				for (int i = 0; i < devRegionIdStrs.length; i++) {
					deviceRegionService.discardDeviceRegion(devRegionIdStrs[i], userName);
				}
			}
			//String userName = "";
//			for (int i = 0; i < devRegionIdStrs.length; i++) {
//				deviceRegionService.enableDeviceRegion(devRegionIdStrs[i], userName);
//			}
		return 1;
	}
}
