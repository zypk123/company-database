package cy.its.device.rest.action.impl;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.Equipment;
import cy.its.device.domain.service.ISystemService;
import cy.its.device.rest.action.IDeviceEquipmentAction;
import cy.its.device.rest.dto.DeviceEquipmentDto;
import cy.its.syscfg.domain.model.duty.Organization;

import cy.its.syscfg.domain.service.IDutyService;

@RestController
@RequestMapping("/device/equipment")
public class DeviceEquipmentAction implements IDeviceEquipmentAction {
	
	@Autowired
	ISystemService systemService; 
	
	@Autowired
	IDutyService dutyService;
	
	/**
	 * 添加装备信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/addEquipment")
	public String addEquipment(@ModelAttribute("form") DeviceEquipmentDto form) throws Exception {
		// TODO Auto-generated method stub
		Equipment equipment = systemService.getEquipmentOfNbr(form.getEquipmentNbr());
		
		String equipmentId="nbrError";
		
		if(equipment !=null){
			return equipmentId;
		}else{
			equipmentId = goAddEquipment(form);
		}
			
		return equipmentId;
	}
	private String  goAddEquipment(DeviceEquipmentDto form) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Equipment obj = new Equipment();
		obj.setVerificationMark("1");//检定标识默认为:"1"
		ObjectMapUtils.parseObject(obj, form);
		if(!StringUtil.isNullOrEmpty(form.getInstallDate())){
			obj.setInstallDate(sdf.parse(form.getInstallDate()));//安装日期
		}
		if(!StringUtil.isNullOrEmpty(form.getOrgId())){
			Organization org = dutyService.organizationOfId(form.getOrgId());
			if(!StringUtil.isNullOrEmpty(org.orgPrivilegeCode)){
				obj.setOrgPrivilegeCode(org.orgPrivilegeCode);//机构过滤code
			}else{
				obj.setOrgPrivilegeCode(form.getCurrentOrgPrivilegeCode());//登录用户所在机构过滤code
			}
		}
		obj.setUseStatusFlag("0");//使用状态标识,默认0表示未启用
		obj.setEnableUpdateDate(new Date());//使用状态更新时间
		obj.setCreateBy(form.getCurrentUserId());//创建人
		obj.setCreateTime(new Date());//创建时间
		return systemService.saveEquipment(obj);
	}

	/**
	 * 删除装备信息
	 * @param equipmentId
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/deleteEquipment")
	public String deleteEquipment(@RequestParam("deviceId") String deviceId) throws Exception {
		// TODO Auto-generated method stub
		systemService.deleteEquipment(deviceId);
		return "success";
	}
	
	/**
	 * 编辑装备信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/updateEquipment")
	public int updateEquipment(@ModelAttribute("form") DeviceEquipmentDto form) throws Exception {
		// TODO Auto-generated method stub
		int flag=0;
		if(form.getEquipmentNbr().equals(form.getOldEquipmentNbr())){
			flag=goUpdateEquipment(form);
		}else{
			Equipment equipment = systemService.getEquipmentOfNbr(form.getEquipmentNbr());
			if(equipment !=null){
				return flag;
			}else{
				flag=goUpdateEquipment(form);
			}
		}
		return flag;
	}
		private int goUpdateEquipment(DeviceEquipmentDto form) throws Exception {
			// TODO Auto-generated method stub
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Equipment obj = new Equipment();
			ObjectMapUtils.parseObject(obj, form);
			if(!StringUtil.isNullOrEmpty(form.getInstallDate())){
				obj.setInstallDate(sdf.parse(form.getInstallDate()));//安装日期
			}
			if(!StringUtil.isNullOrEmpty(form.getOrgId())){
				Organization org = dutyService.organizationOfId(form.getOrgId());
				if(!StringUtil.isNullOrEmpty(org.orgPrivilegeCode)){
					obj.setOrgPrivilegeCode(org.orgPrivilegeCode);//机构过滤code
				}else{
					obj.setOrgPrivilegeCode(form.getCurrentOrgPrivilegeCode());//登录用户所在机构过滤code
				}
			}
			obj.setUpdateBy(form.getCurrentUserId());//更新人
			obj.setUpdateTime(new Date());//更新时间
			systemService.updateEquipment(obj);
			
			return 1;
		}
	

	/**
	 * 查看某装备信息
	 * @param equipmentId
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/queryEquipment")
	public DeviceEquipmentDto queryEquipment(@RequestParam("deviceId") String deviceId) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Equipment equipment = systemService.getEquipmentOfId(deviceId);
		
		DeviceEquipmentDto equipmentDto = new DeviceEquipmentDto();
		equipmentDto.setOldEquipmentNbr(equipment.getEquipmentNbr());
		ObjectMapUtils.parseObject(equipmentDto, equipment);
		
		if(equipment.getInstallDate() != null){
			equipmentDto.setInstallDate(sdf.format(equipment.getInstallDate()));//安装日期
		}
		if(equipment.getCreateTime() != null){
			equipmentDto.setCreateTime(sdf.format(equipment.getCreateTime()));//创建日期
		}
		return equipmentDto;
	}
	
}
