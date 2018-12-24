package cy.its.vehTrack.repository.impl;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.domain.repository.IDangerAreaRepository;
import cy.its.vehTrack.repository.bigData.Config;
import cy.its.vehTrack.repository.bigData.util.RESTUtil;

@Repository
public class DangerAreaRepository implements IDangerAreaRepository {

	@Override
	public JSONObject findDangerAreaCarList(String query) throws Exception {
		String result = RESTUtil.load(Config.getProperties("danger_area_service"), query);
		JSONObject obj = JSONObject.parseObject(result);
		return obj;
	}

}
