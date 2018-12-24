package cy.its.device.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.bus.EventBus;
import cy.its.com.constant.ConstValue;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.BackupService;
import cy.its.device.domain.model.DataSheet;
import cy.its.device.domain.model.Server;
import cy.its.device.domain.repository.surveySystem.IServerRepository;
import cy.its.device.mybatis.client.BackupServiceMapper;
import cy.its.device.mybatis.client.DataSheetMapper;
import cy.its.device.mybatis.client.ServerMapper;

@Service
public class ServerRepository implements IServerRepository {
	
	@Autowired
	ServerMapper serverMapper;
	
	@Autowired
	BackupServiceMapper backupServiceMapper;
	
	@Autowired
	DataSheetMapper dataSheetMapper;
	
	@Autowired
	EventBus eventBus;
	
	public Server aggregateOfId(String id) throws Exception {
		return serverMapper.selectByPrimaryKey(id);
	}
	

	public String save(Server obj) {
		obj.setServerId(StringUtil.generateUUID());
		 serverMapper.insertSelective(obj);
		 return obj.getServerId();
	}

	public int delete(String id) {
		return serverMapper.deleteByPrimaryKey(id);
	}

	public int update(Server obj) {
		return serverMapper.updateByPrimaryKeySelective(obj);
	}

	public List<Server> findServers(String surveySystemId) {
		return serverMapper.selectServers(surveySystemId);
	}

	public List<BackupService> backServicsOfServer(String serverId) {
		return backupServiceMapper.selectBackupServices(serverId);
	}

	public List<DataSheet> dataSheetsOfServer(String serverId) {
		return dataSheetMapper.selectDataSheets(serverId);
	}

	public List<Server> selectServerOfIp(String serverIp) {
		return serverMapper.selectByServerIp(serverIp);
	}
	
	public void serverChanged(){
		eventBus.publish(ConstValue.ROUTE_KEY_CACHECHANGE_SERVER, "");
	}
}
