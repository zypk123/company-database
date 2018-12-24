package cy.its.sysCfg.rest.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.sysCfg.rest.action.IPolicePostAction;
import cy.its.sysCfg.rest.dto.PolicePostDto;
import cy.its.syscfg.domain.model.traffic.PolicePost;
import cy.its.syscfg.domain.service.IPolicePostService;


@RestController
@RequestMapping("/sysCfg/policeAgency/policePost")
public class PolicePostAction implements IPolicePostAction {
	@Autowired
	IPolicePostService policePostService;
	
	@RequestMapping(value="/add",method = RequestMethod.POST) 
	public String addPolicePost(@ModelAttribute(value="policePostDto")PolicePostDto policePostDto) {
		return policePostService.savePolicePost(policePostDto.parseToPolicePost());
	}
	@RequestMapping("/delete")
	public int deletePolicePostOfId(@RequestParam(value = "id")String id) {
		return policePostService.deletePolicePostOfId(id);
	}
	
	@RequestMapping("/update")
	public int updatePolicePost(PolicePostDto policePostDto) {
		return policePostService.updatePolice(policePostDto.parseToPolicePost());
	}
	
	@RequestMapping("/selectAll")
	public List<PolicePostDto> selectAll() {
		List<PolicePostDto> policePostDtos=new ArrayList<PolicePostDto>();
		List<PolicePost> policePosts=policePostService.selectAll(new HashMap());		
		for (PolicePost policePost : policePosts) {
			policePostDtos.add(new PolicePostDto(policePost));
		}
		return policePostDtos;
	}
}
