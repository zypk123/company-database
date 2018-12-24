package cy.its.device.domain.model;

import java.util.List;

import cy.its.device.domain.criteria.ServerAppCriteria;
import cy.its.device.domain.repository.surveySystem.IServerApplicationRepository;

public class ServerPlatformAppAsso {
    private String serverAppId;

    private String serverPlatId;

    public String getServerAppId() {
        return serverAppId;
    }

    public void setServerAppId(String serverAppId) {
        this.serverAppId = serverAppId;
    }

    public String getServerPlatId() {
        return serverPlatId;
    }

    public void setServerPlatId(String serverPlatId) {
        this.serverPlatId = serverPlatId;
    }
    
    
    public void validate(IServerApplicationRepository repository) throws Exception{
    	ServerAppCriteria criteria = new ServerAppCriteria();
		criteria.serverPlatId = this.serverPlatId;
		List<ServerApplication> apps = repository.findServerApps(criteria);		
		
		if (apps != null && apps.size() > 0) {
			ServerApplication newApp = repository.aggregateOfId(this.serverPlatId);
			if (newApp != null) {
				if (apps.stream().anyMatch(a -> a.getServerAppId().equals(newApp.getServerAppId()))) {
					throw new Exception("当前分配的服务器组件在当前接入平台下已存在");
				}

				if (apps.stream().anyMatch(a -> a.getAppType().equals(newApp.getAppType()))) {
					throw new Exception("当前分配的服务器组件类型与当前接入平台下的其它组件类型重复");
				}
			}
		}
    }
}