package cy.its.road.repository.highway;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.road.domain.criteria.ServiceResourceCriteria;
import cy.its.road.domain.model.highway.ServiceResource;
import cy.its.road.domain.repository.highway.IServiceResourceRepository;
import cy.its.road.mybatis.client.ServiceResourceMapper;

@Service
public class ServiceResourceRepository implements IServiceResourceRepository {
	
	@Autowired
	ServiceResourceMapper serviceResourceMapper;

	public ServiceResource aggregateOfId(String id) throws Exception {
		return serviceResourceMapper.selectByPrimaryKey(id);
	}

	public String save(ServiceResource obj) {
		obj.setServiceResourceId(StringUtil.generateUUID());
		serviceResourceMapper.insertSelective(obj);
		return obj.getServiceResourceId();
	}

	public int delete(String id) {
		return serviceResourceMapper.deleteByPrimaryKey(id);
	}

	public int update(ServiceResource obj) {
		return serviceResourceMapper.updateByPrimaryKeySelective(obj);
	}

	public List<ServiceResource> findServiceResources(
			ServiceResourceCriteria serviceResourceCriteria) {
		return serviceResourceMapper.selectServiceResources(
				ParamUtil.parseParams(serviceResourceCriteria));
	}

	@Override
	public int countServiceResources(ServiceResourceCriteria resourceCriteria) {
		return serviceResourceMapper.countServiceResources(
				ParamUtil.parseParams(resourceCriteria));
	}
}
