package cy.its.sysCfg.rest.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.syscfg.domain.model.traffic.EnforceStation;
import cy.its.syscfg.domain.service.IEnforceStationService;
import cy.its.sysCfg.rest.action.IEnforceStationAction;
import cy.its.sysCfg.rest.dto.EnforceStationDto;

@RestController
@RequestMapping("/sysCfg/policeAgency/enforceStation")
public class EnforceStationAction implements IEnforceStationAction {
	@Autowired
	IEnforceStationService enforceStationService;
	
	@RequestMapping("/add")
	public String add(@ModelAttribute(value="enforceStationDto")EnforceStationDto enforceStationDto) {
		return enforceStationService.save(enforceStationDto.parseToEnforceStation());
	}
	@RequestMapping("/delete")
	public int delete(@RequestParam(value = "id",required=true)String id) {
		return enforceStationService.delete(id);
	}
	@RequestMapping("/update")
	public int update(@ModelAttribute(value="enforceStationDto")EnforceStationDto enforceStationDto) {
		return enforceStationService.update(enforceStationDto.parseToEnforceStation());
	}
	@RequestMapping("/selectAll")
	public List<EnforceStationDto> selectAll() {
		List<EnforceStationDto> pDtos=new ArrayList<EnforceStationDto>();
		List<EnforceStation> enforceStations=enforceStationService.selectAll(new HashMap());
		for (EnforceStation enforceStation : enforceStations) {
			pDtos.add(new EnforceStationDto(enforceStation));
		}
		return pDtos;
	}
}
