package cy.its.sysCfg.rest.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.sysCfg.rest.action.IOverRunCheckPointAction;
import cy.its.sysCfg.rest.dto.OverRunCheckPointDto;
import cy.its.syscfg.domain.model.traffic.OverRunCheckPoint;
import cy.its.syscfg.domain.service.IOverRunCheckPointService;

@RestController
@RequestMapping("/sysCfg/policeAgency/overRun")
public class OverRunCheckPointAction implements IOverRunCheckPointAction {
	
	@Autowired
	IOverRunCheckPointService overRunCheckPointService;
	
	@RequestMapping("/add")
	public String add(@ModelAttribute(value="overRunCheckPointDto")OverRunCheckPointDto overRunCheckPointDto) {
		return overRunCheckPointService.save(overRunCheckPointDto.parseToOverRunCheckPointDto());
	}
	
	@RequestMapping("/delete")
	public int delete(@RequestParam(value = "id",required=true)String id) {
		return overRunCheckPointService.delete(id);
	}
	
	@RequestMapping("/update")	
	public int update(@ModelAttribute(value="overRunCheckPointDto")OverRunCheckPointDto overRunCheckPointDto) {
		return overRunCheckPointService.update(overRunCheckPointDto.parseToOverRunCheckPointDto());
	}
	
	@RequestMapping("/selectAll")
	public List<OverRunCheckPointDto> selectAll() {
		List<OverRunCheckPointDto> pDtos=new ArrayList<OverRunCheckPointDto>();
		List<OverRunCheckPoint> overRunCheckPoints=overRunCheckPointService.selectAll(new HashMap());
		for (OverRunCheckPoint overRunCheckPoint : overRunCheckPoints) {
			pDtos.add(new OverRunCheckPointDto(overRunCheckPoint));
		}
		return pDtos;
	}

}
