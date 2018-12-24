package cy.its.device.rest.action.impl;

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
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.SysComponent;
import cy.its.device.domain.model.site.Lane;
import cy.its.device.domain.model.site.Section;
import cy.its.device.domain.service.ISystemAttachService;
import cy.its.device.domain.service.ISystemService;
import cy.its.device.rest.action.ICameraInfoManageAction;
import cy.its.device.rest.dto.CameraInfoDto;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.Results;
import cy.its.device.domain.service.ISiteService;


@RestController
@RequestMapping("/device/cameraManage")
public class CameraInfoManageAction implements ICameraInfoManageAction {
	
	@Autowired
	ISystemAttachService systemAttachService; 
	
	@Autowired
	ISiteService siteService;
	
	@Autowired
	ISystemService systemService;
	
	/**
	 * 添加保存相机信息
	 * @param form 相机信息
	 */
	@RequestMapping(value = "/addCameraInfo")
	public String addCameraInfo(@ModelAttribute("form") CameraInfoDto form) throws Exception {
		// TODO Auto-generated method stub
		String flag = "keyError";//判断该deviceKey存在的标识（0表示存在，1表示不存在）
		SysComponent sysComponent = new SysComponent();
		ObjectMapUtils.parseObject(sysComponent, form);
		sysComponent.setOnlineStatus(null);//在线状态
		sysComponent.setStatusUpdateTime(new Date());//设备状态更新时间
		String deviceKey = form.getDeviceKey();
		SysComponent sysComponents = null;
		if(!StringUtil.isNullOrEmpty(deviceKey)){
			sysComponents = systemAttachService.componentOfDeviceKey(deviceKey);
		}
		if(sysComponents == null){
			systemAttachService.createSysComponent(sysComponent);
			String sysComponentId = sysComponent.getSysComponentId();
			flag = sysComponentId;
		}
		return flag;
	}
	
	/**
	 * 查询相机信息列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryCameraInfo")
	public DataGridResult<CameraInfoDto> queryCameraInfo(@RequestParam("deviceId") String deviceId) throws Exception {
		List<SysComponent> list = systemAttachService.componentsOfSystem(deviceId);
		List<CameraInfoDto> lst = new ArrayList<CameraInfoDto>();
		for (int i = 0; i < list.size(); i++) {
			CameraInfoDto cameraInfo = new CameraInfoDto();
			ObjectMapUtils.parseObject(cameraInfo, list.get(i));
			String directionType = list.get(i).getDirectionType();
			//根据方向类型找方向名称
			if(!StringUtil.isNullOrEmpty(directionType)){
				String directionName = directionNameOfId(directionType,deviceId);//根据方向类型查方向名称
				cameraInfo.setDirectionName(directionName);
			}
			lst.add(cameraInfo);
		}
		Results<CameraInfoDto> res = new Results<CameraInfoDto>();
		res.setRows(lst);
		DataGridResult<CameraInfoDto> dgr = new DataGridResult<CameraInfoDto>();
		dgr.setResult(res);
		dgr.setErro("");
		return dgr;
	}
	
	/**
	 * 根据方向类型查方向名称
	 * @param directionType
	 * @return
	 */
	public String directionNameOfId(String directionType,String deviceId) throws Exception{
		String directionName = null;
		Sys sys =new Sys();
		sys = systemService.systemOfId(deviceId);//根据deviceId查设备信息
		String siteId = sys.getSiteId();
		List<Section> list = siteService.sectionsOfSite(siteId);//根据点位ID查断面信息
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getDirectionType().equals(directionType)){
				directionName = list.get(i).getDirectionName();
			}
		}
		return directionName;
	}
	
	/**
	 * 删除相机
	 * @param sysComponentId 组件ID
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/deleteCameraInfo")
	public int deleteCameraInfo(@RequestParam("sysComponentId") String sysComponentId) throws Exception {
		// TODO Auto-generated method stub
		systemAttachService.removeComponents(sysComponentId);
		return 1;
	}
	
	/**
	 * 根据设备ID删除相机
	 * @param deviceId 设备ID
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/deleteCameraByDeviceId")
	public int deleteCameraByDeviceId(@RequestParam("deviceId") String deviceId) throws Exception {
		// TODO Auto-generated method stub sysComponentId
		List<SysComponent> list = systemAttachService.componentsOfSystem(deviceId);
		if(list.size() != 0 && list != null){
			for (SysComponent c : list) {
				systemAttachService.removeComponents(c.getSysComponentId());
			}
		}
		return 1;
	}
	
	/**
	 * 编辑相机信息
	 * @param sysComponentId 组件ID
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/editCameraInfo")
	public int editCameraInfo(@ModelAttribute("form") CameraInfoDto form) throws Exception {
		// TODO Auto-generated method stub
		int flag = 0;//判断该deviceKey存在的标识（0表示存在，1表示不存在）
		SysComponent sysComponent = new SysComponent();
		sysComponent.setStatusUpdateTime(new Date());//设备状态更新时间
		ObjectMapUtils.parseObject(sysComponent, form);
		if(form.getDeviceKey().equals(form.getOldDeviceKey())){
			systemAttachService.updateComponents(sysComponent);
			flag = 1;
		}else{
			String deviceKey = form.getDeviceKey();
			SysComponent sysComponents = null;
			if(!StringUtil.isNullOrEmpty(deviceKey)){
				sysComponents = systemAttachService.componentOfDeviceKey(deviceKey);
			}
			if(sysComponents == null){
				systemAttachService.updateComponents(sysComponent);
				flag = 1;
			}
		}
		return flag;
	}
	
	/**
	 * 根据断面ID查询车道信息
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/getLaneBySectionId")
	public List<Lane> getLaneBySectionId(@RequestParam("sectionId") String sectionId) throws Exception {
		List<Lane> list = siteService.lanesOfSection(sectionId);
		return list;
	} 
	
	/**
	 * 根据deviceKey查相机信息
	 * @param deviceKey 唯一性序列号
	 * @return 整形 0表示存在该相机，1表示不存在该相机
	 * @throws Exception 异常
	 */
	@Override
	@RequestMapping(value = "/queryCameraByKey")
	public int queryCameraByKey(@RequestParam("deviceKey") String deviceKey) throws Exception {
		int flag = 1;
		SysComponent sysComponents = null;
		if(!StringUtil.isNullOrEmpty(deviceKey)){
			sysComponents = systemAttachService.componentOfDeviceKey(deviceKey);
		}
		if(sysComponents != null){
			flag = 0;
		}
		return flag;
	}
}
