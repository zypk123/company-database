package cy.its.device.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.ServerAppCriteria;
import cy.its.device.domain.criteria.ServerPlatformCriteria;
import cy.its.device.domain.criteria.SurveySystemCriteria;
import cy.its.device.domain.model.BackupService;
import cy.its.device.domain.model.DataSheet;
import cy.its.device.domain.model.Server;
import cy.its.device.domain.model.ServerApplication;
import cy.its.device.domain.model.ServerPlatform;
import cy.its.device.domain.model.ServerPlatformAppAsso;
import cy.its.device.domain.model.SurveySys;
import cy.its.device.domain.repository.surveySystem.IServerApplicationRepository;
import cy.its.device.domain.repository.surveySystem.IServerPlatformRepository;
import cy.its.device.domain.repository.surveySystem.IServerRepository;
import cy.its.device.domain.repository.surveySystem.ISurveySysRepository;
import cy.its.device.domain.service.ISurveySystemService;

/**
 * 监控中心服务
 * 
 * @author STJ
 *
 */
@Service
public class SurveySystemService implements ISurveySystemService {

	@Autowired
	IServerRepository serverRepository;

	@Autowired
	ISurveySysRepository surveySysRepository;

	@Autowired
	IServerPlatformRepository serverPlatformRepository;

	@Autowired
	IServerApplicationRepository serverApplicationRepository;

	@Override
	public void createSurveySystem(SurveySys surveySystem) {
		surveySysRepository.save(surveySystem);
	}

	@Override
	public List<SurveySys> findSurveySystems(SurveySystemCriteria criteria) {
		if (criteria.getNeedTotal()) {
			criteria.setTotal(surveySysRepository.countSurveySystems(criteria));
		}
		return surveySysRepository.findSurveySystems(criteria);
	}

	@Override
	public SurveySys surveySystemOfId(String surveySystemId) throws Exception {
		return surveySysRepository.aggregateOfId(surveySystemId);
	}

	@Override
	public void deleteSurveySystem(String surveySystemId) throws Exception {
		List<Server> servers = serverRepository.findServers(surveySystemId);		
		if(servers != null && servers.size() > 0) {
			throw new Exception("当前监控中心存在服务器,禁止删除!");
		}
		
		List<ServerPlatform> plats = platformsOfSurveySystem(surveySystemId);
		if(plats != null && plats.size() > 0) {
			throw new Exception("当前监控中心存在接入平台,禁止删除!");
		}
		
		surveySysRepository.delete(surveySystemId);
	}

	@Override
	public void createServer(Server server) {
		serverRepository.save(server);
	}

	@Override
	public Server serverOfId(String serverId) throws Exception {
		return serverRepository.aggregateOfId(serverId);
	}

	@Override
	public List<Server> serverOfIp(String serverIp) throws Exception {
		return serverRepository.selectServerOfIp(serverIp);
	}

	@Override
	public void deleteServer(String serverId) {
		serverRepository.delete(serverId);
	}

	@Override
	public void updateServer(Server server) {
		serverRepository.update(server);
	}

	@Override
	public List<BackupService> backupServiceOfServer(String serverId) {
		return serverRepository.backServicsOfServer(serverId);
	}

	@Override
	public List<DataSheet> tableStatusOfServer(String serverId) {
		return serverRepository.dataSheetsOfServer(serverId);
	}

	@Override
	public void updateSurveySystem(SurveySys surveySystem) {
		surveySysRepository.update(surveySystem);
	}

	@Override
	public void serverOrSurveyChanged() {
		serverRepository.serverChanged();
	}

	@Override
	public void createServerPlatform(ServerPlatform serverPlatform) throws Exception {
		if (serverPlatform.hasSameNameInSurveySystem(serverPlatformRepository)) {
			throw new Exception("当前监控中心中存在与当前接入平台具有相同名称的平台");
		}

		serverPlatformRepository.save(serverPlatform);
	}

	@Override
	public void updateServerPlatform(ServerPlatform serverPlatform) throws Exception {
		if (serverPlatform.hasSameNameInSurveySystem(serverPlatformRepository)) {
			throw new Exception("当前监控中心中存在与当前接入平台具有相同名称的平台");
		}
		serverPlatformRepository.update(serverPlatform);
	}

	@Override
	public void deleteServerPlatform(String serverPlatId) {
		serverPlatformRepository.delete(serverPlatId);
	}

	@Override
	public ServerPlatform serverPlatformOfId(String serverPlatId) throws Exception {
		return serverPlatformRepository.aggregateOfId(serverPlatId);
	}

	@Override
	public List<ServerPlatform> platformsOfSurveySystem(String surveySystemId) {
		ServerPlatformCriteria criteria = new ServerPlatformCriteria();
		criteria.surveySystemId = surveySystemId;
		criteria.setNoPage();
		return serverPlatformRepository.findServerPlatforms(criteria);
	}

	@Override
	public List<ServerApplication> serverAppsOfServerPlat(String serverPlatId) {
		ServerAppCriteria criteria = new ServerAppCriteria();
		criteria.serverPlatId = serverPlatId;
		criteria.setNoPage();
		return serverApplicationRepository.findServerApps(criteria);
	}

	@Override
	public List<ServerApplication> serverAppsOfServer(String serverId) {
		ServerAppCriteria criteria = new ServerAppCriteria();
		criteria.serverId = serverId;
		criteria.setNoPage();
		return serverApplicationRepository.findServerApps(criteria);
	}

	@Override
	public List<ServerApplication> serverAppsOfSurveySystem(String surveySystemId) {
		ServerAppCriteria criteria = new ServerAppCriteria();
		criteria.surveySystemId = surveySystemId;
		return serverApplicationRepository.findServerApps(criteria);
	}

	@Override
	public ServerApplication serverApplicationOfId(String serverAppId) throws Exception {
		return serverApplicationRepository.aggregateOfId(serverAppId);
	}

	@Override
	public void createServerApplication(ServerApplication serverApp) {
		serverApplicationRepository.save(serverApp);
	}

	@Override
	public void updateServerApplication(ServerApplication serverApp) {
		serverApplicationRepository.update(serverApp);
	}

	@Override
	public void deleteServerApplication(String serverAppId) {
		serverApplicationRepository.delete(serverAppId);
	}

	@Override
	public void createServerPlatAppAsso(ServerPlatformAppAsso asso) throws Exception {		
		asso.validate(serverApplicationRepository);
		serverPlatformRepository.savePlatAppAsso(asso);
	}

	@Override
	public void deleteServerPlatAppAsso(ServerPlatformAppAsso asso) {
		serverPlatformRepository.deletePlatAppAsso(asso);
	}

	@Override
	public void updateServerPlatAppAsso(ServerPlatformAppAsso oldAsso, ServerPlatformAppAsso newAsso) {
		serverPlatformRepository.updatePlatAppAsso(oldAsso, newAsso);
	}

	@Override
	public List<Server> serversOfSurveySystem(String surveySystemId) {
		return serverRepository.findServers(surveySystemId);
	}

	@Override
	public List<ServerPlatformAppAsso> platAppAssoOfApp(String serverAppId) {
		return serverPlatformRepository.findPlatAppAsso(null, serverAppId);
	}

	@Override
	public List<ServerPlatformAppAsso> platAppAssoOfPlat(String serverPlatId) {
		return serverPlatformRepository.findPlatAppAsso(serverPlatId, null);
	}
	
	@Override
	public String serverIpOfPlatId(String serverPlatId, String appType) throws Exception {
		ServerAppCriteria c = new ServerAppCriteria();
		c.serverPlatId = serverPlatId;
		List<ServerApplication> apps = serverApplicationRepository.findServerApps(c);
		ServerApplication app = apps.stream().filter(a->appType.equals(a.getAppType())).findFirst().orElse(null);
		if(app == null) {
			throw new Exception("服务器平台下未配置指定类型的服务器组件");
		}
		
		if(StringUtil.isNullOrEmpty(app.getServerId())){
			throw new Exception("服务器平台下指定类型的服务器组件未配置服务器");
		}
		
		Server server = serverRepository.aggregateOfId(app.getServerId());
		if(server == null) {
			throw new Exception("服务器平台下指定类型的服务器组件未配置服务器");
		}
		
		return server.getServerIp();
	}

}
