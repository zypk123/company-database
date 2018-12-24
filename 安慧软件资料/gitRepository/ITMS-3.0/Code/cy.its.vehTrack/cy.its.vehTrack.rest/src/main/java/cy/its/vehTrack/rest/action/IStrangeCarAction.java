package cy.its.vehTrack.rest.action;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.rest.dto.StrangeCarDto;

public interface IStrangeCarAction {
	public JSONObject queryStrangeCarList(StrangeCarDto dto);
}
