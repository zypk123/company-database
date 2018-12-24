package cy.its.vehTrack.rest.action;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.rest.dto.VioSnapRateSearchInputBean;

public interface IVioSnapRateAction {

	/**
	 * 违法设备的抓拍率
	 * @param inputBean
	 * @return
	 */
	public JSONObject queryVioSnapRate(VioSnapRateSearchInputBean inputBean);

}
