package cy.its.vehTrack.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.domain.repository.IDangerAreaRepository;
import cy.its.vehTrack.domain.service.IDangerAreaService;

@Service
public class DangerAreaService implements IDangerAreaService {
	@Autowired
	IDangerAreaRepository repository;

	@Override
	public JSONObject findDangerAreaCarList(String query) throws Exception {
		// TODO Auto-generated method stub
		return this.repository.findDangerAreaCarList(query);
	}

}
