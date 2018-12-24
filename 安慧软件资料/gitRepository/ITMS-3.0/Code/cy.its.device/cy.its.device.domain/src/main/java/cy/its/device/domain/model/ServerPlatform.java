package cy.its.device.domain.model;

import java.util.List;

import cy.its.com.domain.AggregateRoot;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.ServerPlatformCriteria;
import cy.its.device.domain.repository.surveySystem.IServerPlatformRepository;

public class ServerPlatform extends AggregateRoot {

	private String serverPlatId;

	private String serverPlatName;

	private String remark;

	private String surveySystemId;

	public String getServerPlatId() {
		return serverPlatId;
	}

	public void setServerPlatId(String serverPlatId) {
		this.serverPlatId = serverPlatId;
	}

	public String getServerPlatName() {
		return serverPlatName;
	}

	public void setServerPlatName(String serverPlatName) {
		this.serverPlatName = serverPlatName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSurveySystemId() {
		return surveySystemId;
	}

	public void setSurveySystemId(String surveySystemId) {
		this.surveySystemId = surveySystemId;
	}

	// private List<ServerPlatformAppAsso> serverPlatformAppAssos;
	//
	@Override
	public String getIdentityId() {
		return this.serverPlatId;
	}

	//
	// /**
	// * 添加服务器组件
	// * @param serverAppId
	// */
	// public void addServerApp(String serverAppId){
	// if(serverPlatformAppAssos == null ){
	// serverPlatformAppAssos = new ArrayList<ServerPlatformAppAsso>();
	// }
	//
	// if(serverPlatformAppAssos.stream().anyMatch(a->serverAppId.equals(a.getServerAppId()))){
	// for (ServerPlatformAppAsso asso : serverPlatformAppAssos) {
	// if(serverAppId.equals(asso.getServerAppId())){o}
	// }
	// }else{
	// ServerPlatformAppAsso asso = new ServerPlatformAppAsso();
	// asso.setAccessPlatId(this.accessPlatId);
	// asso.setServerAppId(serverAppId);
	// asso.setEntityState(EntityState.CREATE);
	// serverPlatformAppAssos.add(asso);
	// }
	// }
	//
	// /**
	// * 删除服务器组件
	// * @param serverAppId
	// */
	// public void removeServerApp(String serverAppId){
	// if(serverPlatformAppAssos != null ){
	// serverPlatformAppAssos = new ArrayList<ServerPlatformAppAsso>();
	// for (ServerPlatformAppAsso asso : serverPlatformAppAssos) {
	// asso.getEntit
	// }
	// }
	// }
	//
	// /**
	// * 获取当前接入平台所有的组件
	// * @return
	// */
	// public List<String> allServerApps(){
	// return new ArrayList<String>
	// (serverAppIds.stream().collect(Collectors.toList()));
	// }

	public boolean hasSameNameInSurveySystem(IServerPlatformRepository repository) {
		ServerPlatformCriteria c = new ServerPlatformCriteria();
		c.surveySystemId = this.getSurveySystemId();
		List<ServerPlatform> plats = repository.findServerPlatforms(c);
		if (plats != null && plats.size() > 0) {
			return plats.stream().filter(p -> !p.getServerPlatId().equals(this.serverPlatId))
					.anyMatch(p -> this.serverPlatName.equals(p.getServerPlatName()));
		}

		return false;
	}
}
