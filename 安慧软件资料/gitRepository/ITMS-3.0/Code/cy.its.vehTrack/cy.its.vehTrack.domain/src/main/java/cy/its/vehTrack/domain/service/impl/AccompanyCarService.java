package cy.its.vehTrack.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.domain.repository.IAccompanyCarRepository;
import cy.its.vehTrack.domain.service.IAccompanyCarService;

@Service
public class AccompanyCarService implements IAccompanyCarService {
	@Autowired
	IAccompanyCarRepository repository;

	@Override
	public JSONObject findAccompanyCarList(String query) throws Exception {
		// TODO Auto-generated method stub
		return this.repository.findAccompanyCarList(query);
	}

}
