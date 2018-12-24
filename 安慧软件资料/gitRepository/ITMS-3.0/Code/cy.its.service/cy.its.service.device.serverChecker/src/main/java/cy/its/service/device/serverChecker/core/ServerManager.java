package cy.its.service.device.serverChecker.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import cy.its.service.common.dataModel.SurveyUpgrade_ServiceStatus;
import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.dataModel.SurveyUpgrade_ServerStatus;
import cy.its.service.common.dataModel.SurveyUpgrade_ServerStatusResult;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.device.serverChecker.ifs.IServerManager;
import cy.its.service.device.serverChecker.model.ServerInfo;

@Export
public class ServerManager implements IServerManager {

	/**
	 * 服务器列表: key 服务器IP
	 */
	Map<String, Server> serverMap = new HashMap<String, Server>();

	@Override
	public synchronized void loadServer(List<ServerInfo> servers) throws Exception {

		Map<String, ServerInfo> newMap = servers.stream().collect(Collectors.groupingBy(ServerInfo::getServerIp))
				.entrySet().stream()
				.collect(Collectors.toMap(Entry<String, List<ServerInfo>>::getKey, kv -> kv.getValue().get(0)));

		synchronized (serverMap) {
			String[] oldKeys = serverMap.keySet().toArray(new String[0]);
			for (String key : oldKeys) {
				if (newMap.containsKey(key)) {
					serverMap.get(key).config(newMap.get(key));
					newMap.remove(key);
				} else {
					serverMap.remove(key);
				}
			}

			for (Entry<String, ServerInfo> kv : newMap.entrySet()) {
				serverMap.put(kv.getKey(), new Server(kv.getValue()));
			}
		}
	}

	@Override
	public void receiveServerStatus(SurveyUpgrade_ServerStatus serverStatus) {
		Server server = getServer(serverStatus.getHostIP());
		if (server != null) {
			server.receiveServerStatus(serverStatus);
		}
	}

	@Override
	public void receiveServiceStatus(SurveyUpgrade_ServiceStatus processStatus) {
		Server server = getServer(processStatus.getHostIp());
		if (server != null) {
			server.receiveProcessStatus(processStatus);
		}
	}

	@Override
	public void publishAllServerStatus() {
		Server[] lst;
		synchronized (serverMap) {
			lst = serverMap.values().toArray(new Server[0]);
		}

		SurveyUpgrade_ServerStatusResult[] rs = new SurveyUpgrade_ServerStatusResult[lst.length];
		for (int i = 0; i < lst.length; i++) {
			rs[i] = lst[i].generateResult();
		}
		
		MQGateWay.publish(ConstValue.ROUTE_KEY_QUERY_RESULT_SERVER_STATUS_RESULT, JsonUtil.serialize(rs));
	}

	private Server getServer(String serverIp) {
		synchronized (serverMap) {
			return serverMap.get(serverIp);
		}
	}

	@Override
	public void periodCheckStatus() {
		Server[] lst;
		synchronized (serverMap) {
			lst = serverMap.values().toArray(new Server[ConstValue.INT_0]);
		}
		
		for (Server s : lst) {
			s.periodCheck();
		}
	}
}
