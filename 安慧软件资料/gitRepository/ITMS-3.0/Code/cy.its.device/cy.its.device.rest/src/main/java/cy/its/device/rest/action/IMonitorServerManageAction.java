package cy.its.device.rest.action;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.MonitorAndServerDto;
import cy.its.device.rest.dto.ServerApplicationDto;
import cy.its.device.rest.dto.TreeDto;

public interface IMonitorServerManageAction {
	/**
	 * 添加监控中心
	 * @return 整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	public int addMonitor(MonitorAndServerDto form) throws Exception;
	
	/**
	 * 查询所有监控中心
	 * @return 对象集合
	 */
	public List<TreeDto> queryMonitor(MonitorAndServerDto form);
	
	/**
	 * 删除监控中心
	 * @return 整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	public int removeMonitor(String surveySystemId) throws Exception;
	
	/**
	 * 查询监控中心
	 * @return 查询结果对象
	 * @throws Exception
	 */
	public MonitorAndServerDto queryMonitorInfo(String surveySystemId) throws Exception;
	
	/**
	 * 编辑监控中心
	 * @return 整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	public int editMonitor(MonitorAndServerDto form) throws Exception;
	
	/**
	 * 查询服务器列表
	 * @return 查询结果对象列表
	 */
	public DataGridResult<MonitorAndServerDto> queryServer(String surveySystemId);
	
	/**
	 * 新增服务器
	 * @return 整形 1表示成功，0表示失败	
	 * @throws Exception
	 */
	public int goAddServer(MonitorAndServerDto form) throws Exception;
	
	/**
	 * 删除服务器
	 * @return 整形 1表示成功，0表示失败	
	 * @throws Exception
	 */
	public int goRemoveServer(String serverId) throws Exception;
	
	/**
	 * 批量删除服务器
	 * @return 整形 1表示成功，0表示失败	
	 * @throws Exception
	 */
	public int goRemoveSomeServer(String serverIdStr) throws Exception;
	
	/**
	 * 编辑服务器
	 * @return 整形 1表示成功，0表示失败	
	 * @throws Exception
	 */
	public int goEditServer(MonitorAndServerDto form) throws Exception;
	
	//查监控中心形成列表
	public List<TreeDto> queryMonitorOnly(MonitorAndServerDto form);
	
	//根据服务器ID查服务器组件
	public String queryApplicationByServerId(String serverIdStr) throws Exception;
	
	/**
	 * 添加服务器组件
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public int addServerApplication(ServerApplicationDto form) throws Exception;
	
	/**
	 * 编辑服务器组件
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public int editServerApplication(ServerApplicationDto form) throws Exception;
	
	/**
	 * 删除服务器组件
	 * @param serverAppId
	 * @return
	 * @throws Exception
	 */
	public int removeServerApplication(String serverAppId) throws Exception;
	
	/**
	 * 查询服务器组件列表
	 * @param serverId
	 * @return
	 */
	public DataGridResult<ServerApplicationDto> queryServerApplicationList(String serverId);
	
	/**
	 * 获取监控中心下的服务器组件
	 * @param surveySystemId
	 * @return
	 */
	public DataGridResult<MonitorAndServerDto> serverAppsOfSurveySystem(String surveySystemId) throws Exception ;
	
}
