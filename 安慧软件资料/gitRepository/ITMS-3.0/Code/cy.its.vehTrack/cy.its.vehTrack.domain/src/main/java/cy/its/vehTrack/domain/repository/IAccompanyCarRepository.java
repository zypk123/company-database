package cy.its.vehTrack.domain.repository;

import com.alibaba.fastjson.JSONObject;

public interface IAccompanyCarRepository {
	
	public JSONObject findAccompanyCarList(String query) throws Exception;

}
