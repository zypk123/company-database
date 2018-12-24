package cy.its.device.rest.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cy.its.device.rest.dto.CompanyDto;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.DeviceForMapDto;
import cy.its.device.rest.dto.DeviceQryForMapDto;
import cy.its.device.rest.dto.DeviceRunMonitorDto;
import cy.its.device.rest.dto.DeviceRunMonitorQryDto;

/**
* @Title: IDeviceMoniterAction.java 
* @Package cy.its.device.rest.action 
* @Description: 设备运行监控rest服务接口 
* @author chenzhiying
* @date 2015年10月13日 下午3:24:44  
* @version V3.0 
 */
public interface IDeviceMoniterAction {
	@RequestMapping(value = "/queryDeviceForMap", method = RequestMethod.POST)
	public List queryDeviceForMap(@ModelAttribute("form") DeviceQryForMapDto form)throws Exception;

	Map queryDeviceInit(String currentOrgPrivilegeCode, String currentOrgId) throws Exception;

	DataGridResult<DeviceRunMonitorDto> queryRunDeviceLst(DeviceRunMonitorQryDto form) throws Exception;

	List<CompanyDto> queryContrator();

	Map<String, String> getDeviceForMapTip(String deviceId);

	List<DeviceForMapDto> queryDeviceByName(String devName, String currentOrgPrivilegeCode) throws Exception;
	
	Object exportRunStatusRecordExcel(@ModelAttribute("form") DeviceRunMonitorQryDto form) throws Exception;
	
}
