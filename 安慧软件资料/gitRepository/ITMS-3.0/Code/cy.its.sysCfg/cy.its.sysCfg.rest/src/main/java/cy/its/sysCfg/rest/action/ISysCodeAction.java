package cy.its.sysCfg.rest.action;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import cy.its.sysCfg.rest.dto.CodeComboxDto;
import cy.its.sysCfg.rest.dto.DistrictTreeDto;

public interface ISysCodeAction {
	
	public Map<String,List<CodeComboxDto>> initSyscodeData(String codeString)  throws Exception;
	
	public List<DistrictTreeDto> initDistrictData(@RequestParam(value="districtCode",required=true) String districtCode) throws Exception;
	
	public Map<String,String> initPlatePrefixData() throws Exception;
}
