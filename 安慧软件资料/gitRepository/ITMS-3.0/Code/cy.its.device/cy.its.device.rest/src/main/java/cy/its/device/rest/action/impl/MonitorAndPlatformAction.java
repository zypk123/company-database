package cy.its.device.rest.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.SurveySystemCriteria;
import cy.its.device.domain.model.ServerApplication;
import cy.its.device.domain.model.ServerPlatform;
import cy.its.device.domain.model.ServerPlatformAppAsso;
import cy.its.device.domain.model.SurveySys;
import cy.its.device.domain.service.ISurveySystemService;
import cy.its.device.rest.action.IMonitorAndPlatformAction;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.MonitorAndPlatformDto;
import cy.its.device.rest.dto.Results;
import cy.its.device.rest.dto.TreeDto;

@RestController
@RequestMapping("/device/monitorPlatformManage")
@Api(description="监控中心与接入平台的管理", value = "MonitorAndPlatformAction")
public class MonitorAndPlatformAction implements IMonitorAndPlatformAction {
	
	@Autowired
	ISurveySystemService surveySystemService;
	
	/**
	 * 添加接入平台
	 * @return 整形 1表示成功，0表示失败
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/addPlatform",method = RequestMethod.POST)
	@ApiOperation(value="addPlatform",notes="创建接入平台信息",httpMethod="POST")
	public int addPlatform(@ModelAttribute("form") MonitorAndPlatformDto form) throws Exception {
//		int flag = 0;//判断接入平台名称重复的标识
		//判断该监控中心下的接入平台名称是否重复
//		String surveySystemId = form.getSurveySystemId();
//		List<ServerPlatform> list = surveySystemService.platformsOfSurveySystem(surveySystemId);
		
		ServerPlatform serverPlatform = new ServerPlatform();
		ObjectMapUtils.parseObject(serverPlatform, form);
		surveySystemService.createServerPlatform(serverPlatform);//新增接入平台基本信息
		//增加接入平台与服务器组件的关联关系
		String serverAppIdStr = form.getServerAppIdStr();
		if(!StringUtil.isNullOrEmpty(serverAppIdStr)){
			String serverAppId[] = null;
			if(!StringUtil.isNullOrEmpty(serverAppIdStr)){
				serverAppId = serverAppIdStr.split(",");
			}
			for (int i = 0; i < serverAppId.length; i++) {
				ServerPlatformAppAsso asso = new ServerPlatformAppAsso();
				asso.setServerAppId(serverAppId[i]);
				asso.setServerPlatId(serverPlatform.getServerPlatId());
				surveySystemService.createServerPlatAppAsso(asso);
			}
		}
		return 1;
	}

	
	/**
	 * 删除接入平台
	 */
	@Override
	@RequestMapping(value = "/deletePlatform",method = RequestMethod.POST)
	@ApiOperation(value="deletePlatform",notes="删除接入平台",httpMethod="POST")
	public int deletePlatform(@RequestParam(value = "serverPlatId") String serverPlatId) throws Exception {
		int flag = 0;
		//查询接入平台下是否绑定服务器组件
		List<ServerApplication> list = surveySystemService.serverAppsOfServerPlat(serverPlatId);
		if(list == null || list.size() == 0){
			surveySystemService.deleteServerPlatform(serverPlatId);//删除接入平台
			flag = 1;
		}
		return flag;
	}
	
	/**
	 *单个删除的接入平台与服务器组件的关系
	 */
	@Override
	@RequestMapping(value = "/deletePlatformAndServerApp",method = RequestMethod.POST)
	@ApiOperation(value="deletePlatformAndServerApp",notes="单个删除的接入平台与服务器组件的关系",httpMethod="POST")
	public int deletePlatformAndServerApp(@ModelAttribute("form") MonitorAndPlatformDto form) throws Exception {
		ServerPlatformAppAsso asso = new ServerPlatformAppAsso();
		ObjectMapUtils.parseObject(asso, form);
		surveySystemService.deleteServerPlatAppAsso(asso);//删除接入平台与服务器组件的关联关系
	 	return 1;
	}
	 
	 
	/**
	 * 编辑接入平台
	 */
	@Override
	@RequestMapping(value = "/editPlatform",method = RequestMethod.POST)
	@ApiOperation(value="editPlatform",notes="编辑接入平台",httpMethod="POST")
	public int editPlatform(@ModelAttribute("form") MonitorAndPlatformDto form) throws Exception {
		ServerPlatform serverPlatform = new ServerPlatform();
		ObjectMapUtils.parseObject(serverPlatform, form);
		surveySystemService.updateServerPlatform(serverPlatform);//编辑接入平台基础信息
		ServerPlatformAppAsso oldAsso = new ServerPlatformAppAsso();
		ServerPlatformAppAsso newAsso = new ServerPlatformAppAsso();
		oldAsso.setServerPlatId(form.getServerPlatId());
		newAsso.setServerPlatId(form.getServerPlatId());
		String oldServerAppIdStr = form.getOldServerAppIdStr();
		String newServerAppIdStr = form.getServerAppIdStr();
		//该接入平台曾经配过服务器组件,并且也重新配置了
		if(!StringUtil.isNullOrEmpty(oldServerAppIdStr) && !StringUtil.isNullOrEmpty(newServerAppIdStr)){
			if(newServerAppIdStr.equals(oldServerAppIdStr) == false){
				String oldServerAppId [] = oldServerAppIdStr.split(",");
				for (int i = 0; i < oldServerAppId.length; i++) {
					oldAsso.setServerAppId(oldServerAppId[i]);
					surveySystemService.deleteServerPlatAppAsso(oldAsso);//删除旧的关联关系
				}
				
				String newServerAppId [] = newServerAppIdStr.split(",");
				for (int i = 0; i < newServerAppId.length; i++) {
					newAsso.setServerAppId(newServerAppId[i]);
					surveySystemService.createServerPlatAppAsso(newAsso);//新增新的关联关系
				}
			}		
		//该接入平台曾经未配过服务器组件，新增服务器组件接入平台关系
		}else if(StringUtil.isNullOrEmpty(oldServerAppIdStr) && !StringUtil.isNullOrEmpty(newServerAppIdStr)){
			String newServerAppId [] = newServerAppIdStr.split(",");
			for (int i = 0; i < newServerAppId.length; i++) {
				newAsso.setServerAppId(newServerAppId[i]);
				surveySystemService.createServerPlatAppAsso(newAsso);//新增新的关联关系
			}
		//该接入平台曾经配过服务器组件，现在将以前的配置也去除	
		}else if(!StringUtil.isNullOrEmpty(oldServerAppIdStr) && StringUtil.isNullOrEmpty(newServerAppIdStr)){
			String oldServerAppId [] = oldServerAppIdStr.split(",");
			for (int i = 0; i < oldServerAppId.length; i++) {
				oldAsso.setServerAppId(oldServerAppId[i]);
				surveySystemService.deleteServerPlatAppAsso(oldAsso);//删除旧的关联关系
			}
		}
		return 1;
	};
	
	/**
	 * 查询接入平台下的服务器组件列表
	 */
	@Override
	@RequestMapping(value = "/queryPlatformServerAppList",method = RequestMethod.POST)
	@ApiOperation(value="queryPlatformServerAppList",notes="查询接入平台下的服务器组件列表",httpMethod="POST")
	public DataGridResult<MonitorAndPlatformDto> queryPlatformServerAppList(@ModelAttribute("form") MonitorAndPlatformDto form) throws Exception {
		String serverPlatId = form.getServerPlatId();//接入平台ID
		DataGridResult<MonitorAndPlatformDto> dgr = new DataGridResult<MonitorAndPlatformDto>();
		if(!StringUtil.isNullOrEmpty(serverPlatId)){
			List<ServerApplication> list = surveySystemService.serverAppsOfServerPlat(serverPlatId);//通过接入平台查服务器组件	
			ServerPlatform serverPlatform = surveySystemService.serverPlatformOfId(serverPlatId);
			Results<MonitorAndPlatformDto> res = new Results<MonitorAndPlatformDto>();
			List<MonitorAndPlatformDto> lst = new ArrayList<MonitorAndPlatformDto>();
			if(list != null){
				for (int i = 0; i < list.size(); i++) {
					MonitorAndPlatformDto serverAppDto = new MonitorAndPlatformDto();
					ObjectMapUtils.parseObject(serverAppDto, list.get(i));
					serverAppDto.setServerPlatName(serverPlatform.getServerPlatName());
					serverAppDto.setServerPlatId(serverPlatId);
					serverAppDto.setSurveySystemId(form.getSurveySystemId());
					serverAppDto.setSurveySystemName(form.getSurveySystemName());
					String serverAppIdStr = "";
					for (int j = 0; j < list.size(); j++) {
						if(j > 0){
		                    serverAppIdStr = serverAppIdStr + "," + list.get(j).getServerAppId();
		                }else{
		                    serverAppIdStr = serverAppIdStr + list.get(j).getServerAppId();
		                }
						serverAppDto.setServerAppIdStr(serverAppIdStr);
					}
					lst.add(serverAppDto);
				}
			}
			res.setRows(lst);
			dgr.setErro("");
			dgr.setResult(res);
		}
		return dgr;
	}
	
	/**
	 * 查询监控中心与接入平台形成树
	 */
	@Override
	@RequestMapping(value = "/queryMonitorAndPlatform",method = RequestMethod.POST)
	@ApiOperation(value="queryMonitorAndPlatform",notes="查询监控中心与接入平台形成树",httpMethod="POST")
	public List<TreeDto> queryMonitorAndPlatform(@ModelAttribute("form") MonitorAndPlatformDto form) {
		SurveySystemCriteria criteria = new SurveySystemCriteria();
		ObjectMapUtils.parseObject(criteria, form);
		List<SurveySys> list = surveySystemService.findSurveySystems(criteria);
		List<TreeDto> lst = new ArrayList<TreeDto>();
		for (int i = 0; i < list.size(); i++) {
			TreeDto monitorTree = new TreeDto();
			String surveySystemId = list.get(i).getSurveySystemId();//监控中心ID
			monitorTree.setId(surveySystemId);
			monitorTree.setText(list.get(i).getSurveySystemName());//监控中心名称
			Map<String,String> maps = new HashMap<String, String>();
			maps.put("type", "monitor");
			monitorTree.setAttribute(maps);
			//获取接入平台基础信息
			List<ServerPlatform> lists = surveySystemService.platformsOfSurveySystem(surveySystemId);
			List<TreeDto> l = new ArrayList<TreeDto>();//子节点接入平台信息
			if(lists.size() > 0 && lists != null){
				for(int j = 0; j < lists.size(); j++){
					TreeDto platformTree = new TreeDto();
					platformTree.setId(lists.get(j).getServerPlatId());//接入平台ID
					platformTree.setText(lists.get(j).getServerPlatName());//接入平台名称
					Map<String,String> map = new HashMap<String, String>();
					//根据接入平台Id查询相关的服务器组件ID形成字符串
					List<ServerApplication> lsts = surveySystemService.serverAppsOfServerPlat(lists.get(j).getServerPlatId());
					if(lsts != null && lsts.size() != 0){
						String serverAppIdStr = "";
						for (int k = 0; k < lsts.size(); k++) {
							if(k > 0){
			                    serverAppIdStr = serverAppIdStr + "," + lsts.get(k).getServerAppId();
			                }else{
			                    serverAppIdStr = serverAppIdStr + lsts.get(k).getServerAppId();
			                }
						}
						map.put("serverAppIdStr", serverAppIdStr);
						map.put("type", "serverPlat");
						platformTree.setAttribute(map);
					}
					l.add(platformTree);
				}
				monitorTree.setChildren(l);
			}else{
				monitorTree.setState("closed");
			}
				lst.add(monitorTree);
		}
		return lst;
	 }
	
	
	
}
