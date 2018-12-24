package cy.its.trafficSituation.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.trafficSituation.domain.model.TrafficControl;
import cy.its.trafficSituation.domain.repository.ITrafficControlRepository;
import cy.its.trafficSituation.domain.service.ITrafficControlService;

@Service
public class TrafficControlService implements ITrafficControlService {
	@Autowired
	ITrafficControlRepository trafficControlRepository;
	public TrafficControl trafficControlOfId(String id) throws Exception {
		return trafficControlRepository.aggregateOfId(id);
	}

	public String save(TrafficControl trafficControl) {
		return trafficControlRepository.save(trafficControl);
	}

	public int delete(String id) {
		return trafficControlRepository.delete(id);
	}

	public int update(TrafficControl trafficControl) {
		return trafficControlRepository.update(trafficControl);
	}

	public List<TrafficControl> selectAll(Map<String,Object> map) {
		return trafficControlRepository.selectAll(map);
	}

	/*
	  * <p>Title: selectValid</p>
	  * <p>Description: </p>
	  * @return
	  * @see cy.its.trafficSituation.domain.service.ITrafficControlService#selectValid()
	  */
	
	
	@Override
	public List<TrafficControl> selectValid(Map<String,Object> map) {
		return trafficControlRepository.selectValid(map);
	}

	/*
	  * <p>Title: selectCount</p>
	  * <p>Description: </p>
	  * @return
	  * @see cy.its.trafficSituation.domain.service.ITrafficControlService#selectCount()
	  */
	
	
	@Override
	public long selectCount(Map<String,Object> map) {
		return trafficControlRepository.selectCount(map);
	}

	/*
	  * <p>Title: updateSelective</p>
	  * <p>Description: </p>
	  * @param trafficControl
	  * @return
	  * @see cy.its.trafficSituation.domain.service.ITrafficControlService#updateSelective(cy.its.trafficSituation.domain.model.TrafficControl)
	  */
	
	
	@Override
	public int updateSelective(TrafficControl trafficControl) {
		// TODO Auto-generated method stub
		return trafficControlRepository.updateSelective(trafficControl);
	}

}
