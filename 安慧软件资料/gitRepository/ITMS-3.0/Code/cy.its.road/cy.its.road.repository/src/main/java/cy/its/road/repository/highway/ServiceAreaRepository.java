package cy.its.road.repository.highway;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.road.domain.criteria.ServiceAreaCriteria;
import cy.its.road.domain.model.highway.ServiceArea;
import cy.its.road.domain.repository.highway.IServiceAreaRepository;
import cy.its.road.mybatis.client.ServiceAreaMapper;

@Service
public class ServiceAreaRepository implements
		IServiceAreaRepository {
	
	@Autowired
	ServiceAreaMapper serviceAreaMapper;
	
	public ServiceArea aggregateOfId(String id) throws Exception {
		return serviceAreaMapper.selectByPrimaryKey(id);
	}

	public String save(ServiceArea obj) {
		obj.setServiceAreaId(StringUtil.generateUUID());
		 serviceAreaMapper.insertSelective(obj);
		 return obj.getServiceAreaId();
	}

	public int delete(String id) {
		return serviceAreaMapper.deleteByPrimaryKey(id);
	}

	public int update(ServiceArea obj) {
		return serviceAreaMapper.updateByPrimaryKeySelective(obj);
	}

	public List<ServiceArea> findHighwayServiceAreas(
			ServiceAreaCriteria serviceAreaCriteria) {
		return serviceAreaMapper.selectServiceAreas(
				ParamUtil.parseParams(serviceAreaCriteria));
	}

	@Override
	public int countHighwayServiceAreas(ServiceAreaCriteria serviceAreaCriteria) {
		return serviceAreaMapper.countServiceAreas(
				ParamUtil.parseParams(serviceAreaCriteria));
	}
	
	@Override
	public int deleteByRoadId(String roadId) {
		return serviceAreaMapper.deleteByRoadId(roadId);
	}


}
