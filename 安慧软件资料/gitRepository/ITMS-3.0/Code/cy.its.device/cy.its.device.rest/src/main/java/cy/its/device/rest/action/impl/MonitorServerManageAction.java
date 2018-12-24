package cy.its.device.rest.action.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.SurveySystemCriteria;
import cy.its.device.domain.model.Server;
import cy.its.device.domain.model.ServerApplication;
import cy.its.device.domain.model.ServerPlatformAppAsso;
import cy.its.device.domain.model.SurveySys;
import cy.its.device.domain.service.ISurveySystemService;
import cy.its.device.rest.action.IMonitorServerManageAction;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.MonitorAndServerDto;
import cy.its.device.rest.dto.TreeDto;
import cy.its.platform.common.utils.RedisPoolUtil;
import cy.its.device.rest.dto.Results;
import cy.its.device.rest.dto.ServerApplicationDto;

@RestController
@RequestMapping("/device/monitorServerManage")
public class MonitorServerManageAction implements IMonitorServerManageAction {
	
	@Autowired
	ISurveySystemService surveySystemService;
	
	/**
	 * 添加监控中心
	 * @return 整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value= "/addMonitor")
	public int addMonitor(@ModelAttribute("form") MonitorAndServerDto form) throws Exception {
		// TODO Auto-generated method stub
		SurveySys surveySystem = new SurveySys();
		//验证监控中心名称是否已存在
		SurveySystemCriteria criteria = new SurveySystemCriteria();
		criteria.surveySystemName = form.getSurveySystemName();
		List<SurveySys> list = surveySystemService.findSurveySystems(criteria);
		int flag = 0;//判断监控中心是否已存在的标志，0不存在，1存在
		if(list == null || list.size() == 0){//不存在
			ObjectMapUtils.parseObject(surveySystem, form);
			surveySystemService.createSurveySystem(surveySystem);
			flag = 1;
		}
		return flag;
	}
	
	/**
	 * 查询所有监控中心以及监控中心下的服务器
	 * @return 对象集合
	 */
	@Override
	@RequestMapping(value= "/queryMonitor")
	public List<TreeDto> queryMonitor(@ModelAttribute("form") MonitorAndServerDto form) {
		SurveySystemCriteria criteria = new SurveySystemCriteria();
		String privilegeCode = form.getCurrentOrgPrivilegeCode();
		criteria.orgPrivilegeCode = privilegeCode;
		ObjectMapUtils.parseObject(criteria, form);
		List<SurveySys> list = surveySystemService.findSurveySystems(criteria);
		List<TreeDto> lst = new ArrayList<TreeDto>();
		for (int i = 0; i < list.size(); i++) {
			TreeDto monitorTree = new TreeDto();
			String surveySystemId = list.get(i).getSurveySystemId();//监控中心ID
			monitorTree.setId(surveySystemId);
			monitorTree.setText(list.get(i).getSurveySystemName());//监控中心名称
			//获取服务器基础信息
			List<Server> lists = surveySystemService.serversOfSurveySystem(surveySystemId);
			List<TreeDto> l = new ArrayList<TreeDto>();//子节点服务器信息
			if(lists.size() > 0 && lists != null){
				for(int j = 0; j < lists.size(); j++){
					TreeDto serverTree = new TreeDto();
					serverTree.setId(lists.get(j).getServerId());//服务器ID
					serverTree.setText("服务器（" + lists.get(j).getServerIp()+ "）");
					Map<String,String> map = new HashMap<String, String>();
					map.put("surveySystemId", lists.get(j).getSurveySystemId());
					map.put("serverIp", lists.get(j).getServerIp());
					map.put("otalDiskResource", lists.get(j).getOtalDiskResource());
					map.put("connUserName", lists.get(j).getConnUserName());
					map.put("connPassword", lists.get(j).getConnPassword());
					serverTree.setAttribute(map);
					l.add(serverTree);
				}
				monitorTree.setChildren(l);
			}else{
				monitorTree.setState("closed");
			}
			lst.add(monitorTree);
		}
		return lst;
	}
	/**
	 * 查询所有监控中心形成列表
	 * @return 对象集合
	 */
	@Override
	@RequestMapping(value= "/queryMonitorOnly")
	public List<TreeDto> queryMonitorOnly(@ModelAttribute("form") MonitorAndServerDto form) {
		SurveySystemCriteria criteria = new SurveySystemCriteria();
		ObjectMapUtils.parseObject(criteria, form);
		List<SurveySys> list = surveySystemService.findSurveySystems(criteria);
		List<TreeDto> lst = new ArrayList<TreeDto>();
		for (int i = 0; i < list.size(); i++) {
			TreeDto monitorTree = new TreeDto();
			String surveySystemId = list.get(i).getSurveySystemId();//监控中心ID
			monitorTree.setId(surveySystemId);
			monitorTree.setText(list.get(i).getSurveySystemName());//监控中心名称
			lst.add(monitorTree);
		}
		return lst;
	}
	
	/**
	 * 删除监控中心
	 * @return 整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/removeMonitor")
	public int removeMonitor(@RequestParam("surveySystemId") String surveySystemId) throws Exception {
		// TODO Auto-generated method stub
		surveySystemService.deleteSurveySystem(surveySystemId);
		return 1;
	}
	
	/**
	 * 查询监控中心
	 * @return 查询结果对象
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/queryMonitorInfo")
	public MonitorAndServerDto queryMonitorInfo(@RequestParam("surveySystemId") String surveySystemId) throws Exception {
		MonitorAndServerDto monitor = new MonitorAndServerDto();
		SurveySys surveySys = surveySystemService.surveySystemOfId(surveySystemId);
		ObjectMapUtils.parseObject(monitor, surveySys);
		return monitor;
	}
	
	/**
	 * 编辑监控中心
	 * @return 整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/editMonitor")
	public int editMonitor(@ModelAttribute("form") MonitorAndServerDto form) throws Exception{
		int flag = 0;//判断监控中心是否已存在的标志，0不存在，1存在
		SurveySys surveySystem = new SurveySys();
		ObjectMapUtils.parseObject(surveySystem, form);
		if(form.getOldSurveySystemName().equals(form.getSurveySystemName())){//监控中心名称未改动
			surveySystemService.updateSurveySystem(surveySystem);
			flag = 1;
		}else{//监控中心名称有改动
			//验证监控中心名称是否已存在
			SurveySystemCriteria criteria = new SurveySystemCriteria();
			criteria.surveySystemName = form.getSurveySystemName();
			List<SurveySys> list = surveySystemService.findSurveySystems(criteria);
			if(list == null || list.size() == 0){//不存在
				surveySystemService.updateSurveySystem(surveySystem);
				flag = 1;
			}
		}
		return flag;
	}
	
	/**
	 * 查询服务器列表
	 * @return 查询结果对象列表
	 */
	@Override
	@RequestMapping(value = "/queryServer")
	public DataGridResult<MonitorAndServerDto> queryServer(@RequestParam(value = "surveySystemId",required = false) String surveySystemId) {
		// TODO Auto-generated method stub
		//获取服务器实时监控的信息
		List<MonitorAndServerDto> lst = new ArrayList<MonitorAndServerDto>();
		//获取服务器基础信息
		List<Server> list = surveySystemService.serversOfSurveySystem(surveySystemId);
		for (int i = 0; i < list.size(); i++) {
			MonitorAndServerDto server = new MonitorAndServerDto();
			ObjectMapUtils.parseObject(server, list.get(i));
			lst.add(server);
		}
		List<String> devCll = null;
		synchronized (MonitorServerManageAction.class) {
			devCll = new ArrayList<String>(RedisPoolUtil.hgetAll("serverStatus").values());
		}
		List<MonitorAndServerDto> l = new ArrayList<MonitorAndServerDto>();
		if (devCll != null && devCll.size() != 0) {
			for (int i = 0; i < lst.size(); i++) {
				String serverId = lst.get(i).getServerId();
				MonitorAndServerDto server = convertStatusObj(devCll,serverId);
				ObjectMapUtils.parseObject(server, lst.get(i));
				l.add(server);
			}
		}
		Results<MonitorAndServerDto> res = new Results<MonitorAndServerDto>();
		if(l != null && l.size() != 0){
			res.setRows(l);
		} else {
			res.setRows(lst);
		}
		DataGridResult<MonitorAndServerDto> dgr= new DataGridResult<MonitorAndServerDto>();
		dgr.setErro("");
		dgr.setResult(res);
		return dgr;
	}
	private static MonitorAndServerDto convertStatusObj(List<String> devCll,String serverIds) {
		MonitorAndServerDto server = new MonitorAndServerDto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < devCll.size(); i++) {
			JSONObject temp = JSONObject.parseObject(devCll.get(i));
			String serverId = temp.getString("serverId");// 服务器ID
			if(serverIds.equals(serverId)){
				if(temp.getInteger("status") != 2){//未收到服务器状态
					server.setTimeResult(String.valueOf(temp.getInteger("timeResult")));//校时结果
					if(!StringUtil.isNullOrEmpty(temp.getString("hostStartTime"))){
						server.setHostStartTime(sdf.format(new Date(Long.valueOf(temp.getString("hostStartTime")))));//服务器启动时间
					}
					if(!StringUtil.isNullOrEmpty(temp.getString("hostCurrTime"))){
						server.setHostCurrTime(sdf.format(new Date(Long.valueOf(temp.getString("hostCurrTime")))));//服务器当前时间
					}
					server.setNetworkUsage(String.valueOf(temp.getInteger("networkUsage")));//网络利用率%
					server.setCpuUsage(String.valueOf(temp.getInteger("cpuUsage")));//网络利用率%
					server.setMemoryTotal(String.valueOf(temp.getLong("memoryTotal")));// 总内存，kb 
					server.setMemoryUsage(String.valueOf(temp.getLong("memoryUsage")));// 已用内存,%
					server.setServerStatus(String.valueOf(temp.getInteger("status")));// 服务器状态
					server.setServerStatusDeacription(temp.getString("statusDeacription"));// 服务器状态描述
				}
				if(temp.getString("updateTime") != null){
					server.setLstServiceStatus(temp.getJSONArray("lstServiceStatus"));//服务器中的服务状态集合
				}
			}
		}
		return server;
	}
	
	/**
	 * 新增服务器
	 * @return 整形 1表示成功，0表示失败	
	 * @throws Exception
	 */

	@Override
	@RequestMapping(value = "/addServer")
	public int goAddServer(@ModelAttribute("form") MonitorAndServerDto form) throws Exception {
		int a = addServer(form);
		if(a == 1){
			surveySystemService.serverOrSurveyChanged();
		}
		return a;
	}
	public int addServer(MonitorAndServerDto form) throws Exception {
		// TODO Auto-generated method stub
		int flag = 0;//添加失败，服务器IP已存在
		//判断测服务器IP是否已存在
	    List<Server> list = surveySystemService.serverOfIp(form.getServerIp());
		if(list == null || list.size() ==0){//不存在
			Server server = new Server();
			ObjectMapUtils.parseObject(server, form);
			surveySystemService.createServer(server);//创建服务器信息
			String appTypelst = form.getAppTypeList();//获取服务器组件
			List<ServerApplicationDto> lst = null;
			if(!StringUtil.isNullOrEmpty(appTypelst)){
				lst = JSON.parseArray(appTypelst,ServerApplicationDto.class);//将字符串转list
			}
			if(lst != null && lst.size() != 0){
				for (int i = 0; i < lst.size(); i++) {
					ServerApplication serverApp = new ServerApplication();
					ObjectMapUtils.parseObject(serverApp, lst.get(i));
					serverApp.setServerId(server.getServerId());//创建服务器下的服务器组件信息
					surveySystemService.createServerApplication(serverApp);
				}
			}
			flag = 1;//添加成功
		}
		return flag;
	}
	
	/**
	 * 删除服务器
	 * @return 整形 1表示成功，0表示失败	
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/removeServer")
	public int goRemoveServer(@RequestParam("serverId") String serverId) throws Exception {
		//查询该服务器下是否有服务器组件
		List<ServerApplication> list = surveySystemService.serverAppsOfServer(serverId);
		int a = 0;//0表示有，1表示无
		if(list == null || list.size() == 0){
			a = removeServer(serverId);			
			surveySystemService.serverOrSurveyChanged();
		}
		return a;
	}
	public int removeServer(String serverId) throws Exception {
		// TODO Auto-generated method stub
		surveySystemService.deleteServer(serverId);
		return 1;
	}
	/**
	 * 批量删除服务器
	 * @return 整形 1表示成功，0表示失败	
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/removeSomeServer")
	public int goRemoveSomeServer(@RequestParam("serverIdStr") String serverIdStr) throws Exception {	
		int a = removeSomeServer(serverIdStr);			
		surveySystemService.serverOrSurveyChanged();
		return a;
	}
	public int removeSomeServer(String serverIdStr) throws Exception {
		// TODO Auto-generated method stub
		String serverId[] = serverIdStr.split(","); 
		for (int i = 0; i < serverId.length; i++) {
			surveySystemService.deleteServer(serverId[i]);
		}
		return 1;
	}
	
	/**
	 * 编辑服务器
	 * @return 整形 1表示成功，0表示失败	
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/editServer")
	public int goEditServer(@ModelAttribute("form") MonitorAndServerDto form) throws Exception {
		int a = editServer(form);	
		if(a == 1){
			surveySystemService.serverOrSurveyChanged();
		}
		return a;
	}
	public int editServer(MonitorAndServerDto form) throws Exception {
		// TODO Auto-generated method stub
		int flag = 0;//编辑失败，服务器IP已存在，不包括该服务器IP
		if(form.getServerIp().equals(form.getOldServerIp())){
			Server server = new Server();
			ObjectMapUtils.parseObject(server, form);
			surveySystemService.updateServer(server);
			flag = 1;//编辑成功
		}else{
			//判断测服务器IP是否已存在
			List<Server> list = surveySystemService.serverOfIp(form.getServerIp());
			if(list == null || list.size() == 0){//不存在
				Server server = new Server();
				ObjectMapUtils.parseObject(server, form);
				surveySystemService.updateServer(server);
				flag = 1;//编辑成功
			}
		}
		return flag;
	}
	/**
	 * 查询服务器组件列表
	 */
	@Override
	@RequestMapping(value = "/queryServerApplicationList")
	public DataGridResult<ServerApplicationDto> queryServerApplicationList(@RequestParam(value = "serverId") String serverId){
		List<ServerApplication> list = surveySystemService.serverAppsOfServer(serverId);
		DataGridResult<ServerApplicationDto> dgr = new DataGridResult<ServerApplicationDto>();
		Results<ServerApplicationDto> res = new Results<ServerApplicationDto>();
		List<ServerApplicationDto> lst = new ArrayList<ServerApplicationDto>();
		for (int i = 0; i < list.size(); i++) {
			ServerApplicationDto serverAppDto = new ServerApplicationDto();
			ObjectMapUtils.parseObject(serverAppDto, list.get(i));
			lst.add(serverAppDto);
		}
		res.setRows(lst);
		dgr.setErro("");
		dgr.setResult(res);
		return dgr;
	};
	
	/**
	 * 添加服务器组件
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/addServerApplication")
	public int addServerApplication(@ModelAttribute("form") ServerApplicationDto form) throws Exception{
		ServerApplication serverApp = new ServerApplication();
		ObjectMapUtils.parseObject(serverApp, form);
		surveySystemService.createServerApplication(serverApp);
		return 1;
	} 
	
	/**
	 * 编辑服务器组件
	 * @param serverAppId
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/editServerApplication")
	public int editServerApplication(@ModelAttribute("form") ServerApplicationDto form) throws Exception {
		ServerApplication serverApp = new ServerApplication();
		ObjectMapUtils.parseObject(serverApp, form);
		surveySystemService.updateServerApplication(serverApp);
		return 1;
	}
	
	/**
	 * 删除服务器组件
	 * @param serverAppId
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/removeServerApplication")
	public int removeServerApplication(@RequestParam("serverAppId") String serverAppId) throws Exception {
		List<ServerPlatformAppAsso> list = surveySystemService.platAppAssoOfApp(serverAppId);
		if(list == null || list.size() == 0){
			surveySystemService.deleteServerApplication(serverAppId);
			return 1;
		}else{
			return 0;
		}
	}
	/**
	 * 根据服务器ID，查看该服务器下是否有服务器组件
	 * @param serverIdStr
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/queryApplicationByServerId")
	public String queryApplicationByServerId(@RequestParam("serverIdStr") String serverIdStr) throws Exception {
		String flag = "";
		String serverId[] = serverIdStr.split(",");
		for (int i = 0; i < serverId.length; i++) {
			//查询该服务器下是否有服务器组件
			List<ServerApplication> list = surveySystemService.serverAppsOfServer(serverId[i]);
			if(list.size() != 0 && list != null){
				flag = serverId[i];
				return flag;
			}
		}
		return flag;
	}
	/**
	 * 获取监控中心下的服务器组件
	 * @param surveySystemId
	 * @return
	 */
	@Override
	@RequestMapping(value = "/serverAppsOfSurveySystem")
	public DataGridResult<MonitorAndServerDto> serverAppsOfSurveySystem(@RequestParam("surveySystemId") String surveySystemId) throws Exception {
		List<ServerApplication> list = surveySystemService.serverAppsOfSurveySystem(surveySystemId);//监控中心下的所有服务器组件
		DataGridResult<MonitorAndServerDto> dgr = new DataGridResult<MonitorAndServerDto>();
		Results<MonitorAndServerDto> res = new Results<MonitorAndServerDto>();
		List<MonitorAndServerDto> lst = new ArrayList<MonitorAndServerDto>();
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				MonitorAndServerDto monitorServerDto = new MonitorAndServerDto();
				String serverId = list.get(i).getServerId();
				Server server = surveySystemService.serverOfId(serverId);//根据服务器ID查询服务器信息
				String serverAppId = list.get(i).getServerAppId();
				ServerApplication serverApp = surveySystemService.serverApplicationOfId(serverAppId);  //根据服务器组件Id查询端口号
				ObjectMapUtils.parseObject(monitorServerDto, list.get(i));
				monitorServerDto.setServerIp(server.getServerIp());
				monitorServerDto.setServerPort(serverApp.getServerPort());
				lst.add(monitorServerDto);
			}
		}
		res.setRows(lst);
		dgr.setErro("");
		dgr.setResult(res);
		return dgr;
	}
	
	/**
	 * 根据ID查找服务器
	 * @param serverId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/queryServerById")
	public Server queryServerById(String serverId) throws Exception{
		return surveySystemService.serverOfId(serverId);
	}
}
