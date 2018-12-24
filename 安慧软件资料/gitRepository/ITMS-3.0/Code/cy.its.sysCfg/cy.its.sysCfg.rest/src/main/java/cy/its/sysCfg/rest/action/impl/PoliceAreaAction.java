package cy.its.sysCfg.rest.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.sysCfg.rest.action.IPoliceAreaAction;
import cy.its.sysCfg.rest.dto.PoliceAreaDto;
import cy.its.syscfg.domain.model.traffic.PoliceArea;
import cy.its.syscfg.domain.service.IPoliceAreaService;



@RestController
@RequestMapping("/sysCfg/policeAgency/policeArea")
public class PoliceAreaAction implements IPoliceAreaAction {

	@Autowired
	IPoliceAreaService policeAreaService;
	@RequestMapping("/add")
	public String add(@ModelAttribute(value="policeAreaDto")PoliceAreaDto policeAreaDto) {
		return policeAreaService.save(policeAreaDto.parseToPoliceArea());
	}
	@RequestMapping("/delete")
	public int delete(@RequestParam(value = "id",required=true)String id) {
		return policeAreaService.delete(id);
	}
	@RequestMapping("/update")
	public int update(@ModelAttribute(value="policeAreaDto")PoliceAreaDto policeAreaDto) {
		return policeAreaService.update(policeAreaDto.parseToPoliceArea());
	}
	@RequestMapping("/selectAll")
	public List<PoliceAreaDto> selectAll() {
		List<PoliceAreaDto> pDtos=new ArrayList<PoliceAreaDto>();
		List<PoliceArea> policeAreas=policeAreaService.selectAll(new HashMap());
		for (PoliceArea policeArea : policeAreas) {
			pDtos.add(new PoliceAreaDto(policeArea));
		}
		return pDtos;
	}

}
