package cy.its.device.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.ServerPlatformCriteria;
import cy.its.device.domain.model.ServerPlatform;
import cy.its.device.domain.model.ServerPlatformAppAsso;
import cy.its.device.domain.repository.surveySystem.IServerPlatformRepository;
import cy.its.device.mybatis.client.ServerPlatformAppAssoMapper;
import cy.its.device.mybatis.client.ServerPlatformMapper;

@Service
public class ServerPlatformRepository implements IServerPlatformRepository {

	@Autowired
	ServerPlatformAppAssoMapper serverPlatformAppAssoMapper;

	@Autowired
	ServerPlatformMapper serverPlatformMapper;

	@Override
	public ServerPlatform aggregateOfId(String id) throws Exception {
		return serverPlatformMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(ServerPlatform obj) {
		obj.setServerPlatId(StringUtil.generateUUID());
		serverPlatformMapper.insertSelective(obj);
		return obj.getIdentityId();
	}

	@Override
	public int delete(String id) {
		return serverPlatformMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(ServerPlatform obj) {
		return serverPlatformMapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public List<ServerPlatform> findServerPlatforms(ServerPlatformCriteria criteria) {
		if (criteria.getNeedTotal()) {
			criteria.setTotal(serverPlatformMapper.countServerPlatforms(ParamUtil.parseParams(criteria)));
		}
		return serverPlatformMapper.selectServerPlatforms(ParamUtil.parseParams(criteria));
	}

	@Override
	public void savePlatAppAsso(ServerPlatformAppAsso asso) {
		serverPlatformAppAssoMapper.insert(asso);
	}

	@Override
	public void deletePlatAppAsso(ServerPlatformAppAsso asso) {
		serverPlatformAppAssoMapper.delete(asso);
	}

	@Override
	public void updatePlatAppAsso(ServerPlatformAppAsso oldAsso, ServerPlatformAppAsso newAsso) {
		serverPlatformAppAssoMapper.delete(oldAsso);
		serverPlatformAppAssoMapper.insert(newAsso);
	}

	@Override
	public List<ServerPlatformAppAsso> findPlatAppAsso(String serverPlatId, String serverAppId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serverPlatId", serverPlatId);		
		params.put("serverAppId", serverAppId);
		
		return serverPlatformAppAssoMapper.selectAssos(params);
	}
}
