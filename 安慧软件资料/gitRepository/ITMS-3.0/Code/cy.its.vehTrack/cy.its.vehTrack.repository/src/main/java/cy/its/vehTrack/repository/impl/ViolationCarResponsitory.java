package cy.its.vehTrack.repository.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cy.its.vehTrack.domain.repository.IViolationCarResponsitory;
import cy.its.vehTrack.repository.bigData.Config;
import cy.its.vehTrack.repository.bigData.util.RESTUtil;

@Repository
public class ViolationCarResponsitory implements IViolationCarResponsitory {

	@Override
	public Map<String, Object> fingViolationCarInfo(String query) throws Exception {
		String result= RESTUtil.load(Config.getProperties(""), query);
		
		return null;
	}

}
