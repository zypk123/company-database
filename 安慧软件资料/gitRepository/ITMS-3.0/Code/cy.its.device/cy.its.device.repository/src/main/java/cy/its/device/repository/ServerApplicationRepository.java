package cy.its.device.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.ServerAppCriteria;
import cy.its.device.domain.model.ServerApplication;
import cy.its.device.domain.repository.surveySystem.IServerApplicationRepository;
import cy.its.device.mybatis.client.ServerApplicationMapper;

@Service
public class ServerApplicationRepository implements IServerApplicationRepository {

	@Autowired
	ServerApplicationMapper serverApplicationMapper;

	@Override
	public ServerApplication aggregateOfId(String id) throws Exception {
		return serverApplicationMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(ServerApplication obj) {
		obj.setServerAppId(StringUtil.generateUUID());
		serverApplicationMapper.insertSelective(obj);
		return obj.getServerAppId();
	}

	@Override
	public int delete(String id) {
		return serverApplicationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(ServerApplication obj) {
		return serverApplicationMapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public List<ServerApplication> findServerApps(ServerAppCriteria criteria) {
		return serverApplicationMapper.selectServerApps(ParamUtil.parseParams(criteria));
	}

	@Override
	public int countServerApps(ServerAppCriteria criteria) {
		return serverApplicationMapper.countServerApps(ParamUtil.parseParams(criteria));
	}

}
