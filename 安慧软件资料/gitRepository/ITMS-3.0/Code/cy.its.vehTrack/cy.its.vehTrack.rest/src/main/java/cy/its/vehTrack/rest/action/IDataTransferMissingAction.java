package cy.its.vehTrack.rest.action;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.rest.dto.DataTransferMissingInputBean;

public interface IDataTransferMissingAction {

	public JSONObject queryDataTransferMissing(DataTransferMissingInputBean inputBean);
	
	public JSONObject queryDataTransferMissingBySysNbr(DataTransferMissingInputBean inputBean);

}
