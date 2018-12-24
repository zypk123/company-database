package cy.its.trafficSituation.domain.repository;

import java.util.List;
import java.util.Map;

import org.springframework.aop.IntroductionInterceptor;

import cy.its.com.domain.IRepository;
import cy.its.trafficSituation.domain.model.TrafficControl;

public interface ITrafficControlRepository extends IRepository<TrafficControl>{

	List<TrafficControl> selectAll(Map<String,Object> map);
	List<TrafficControl> selectValid(Map<String,Object> map);
	long selectCount(Map<String,Object> map);
	int updateSelective(TrafficControl trafficControl);
}
