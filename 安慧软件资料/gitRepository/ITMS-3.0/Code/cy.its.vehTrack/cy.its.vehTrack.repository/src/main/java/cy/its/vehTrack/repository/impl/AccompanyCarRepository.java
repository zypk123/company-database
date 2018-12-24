package cy.its.vehTrack.repository.impl;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.domain.repository.IAccompanyCarRepository;
import cy.its.vehTrack.repository.bigData.Config;
import cy.its.vehTrack.repository.bigData.util.RESTUtil;

@Repository
public class AccompanyCarRepository implements IAccompanyCarRepository {

	@Override
	public JSONObject findAccompanyCarList(String query) throws Exception {
		// TODO Auto-generated method stub
		String result = RESTUtil.load(Config.getProperties("accompany_car_service"), query);
		JSONObject obj = JSONObject.parseObject(result);
		return obj;
	}

}
