package cy.its.device.rest.action;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.MonitorAndPlatformDto;
import cy.its.device.rest.dto.TreeDto;

public interface IMonitorAndPlatformAction {
	//添加接入平台
	public int addPlatform(MonitorAndPlatformDto form) throws Exception;
	
	/**
	 * 查询监控中心与接入平台形成树
	 */
	public List<TreeDto> queryMonitorAndPlatform(MonitorAndPlatformDto form);
	
	//查询接入平台下的服务器组件列表
	public DataGridResult<MonitorAndPlatformDto> queryPlatformServerAppList(MonitorAndPlatformDto form) throws Exception;
	
	//删除接入平台
	public int deletePlatform(String serverPlatId) throws Exception;
	
	//编辑接入平台
	public int editPlatform(MonitorAndPlatformDto form) throws Exception;
	
	//单个删除接入平台与服务器组件的关系
	public int deletePlatformAndServerApp(MonitorAndPlatformDto form) throws Exception;
}
