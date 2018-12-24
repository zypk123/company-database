package cy.its.vehTrack.rest.action;

import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;

import cy.its.vehTrack.rest.dto.VehtrackPassToVioBean;

public interface IVehtrackPassToVioAction {
	public Map<String, Object> queryVehtrackPassToVio(VehtrackPassToVioBean dto);
	
	public  Map<String, Object> updateVehTraackPassById(String id,String confirmFlag,String confrimDesc,String confrimor);
	public Object exportVioRecordExcel(@ModelAttribute("dto") VehtrackPassToVioBean dto) throws Exception;

}
