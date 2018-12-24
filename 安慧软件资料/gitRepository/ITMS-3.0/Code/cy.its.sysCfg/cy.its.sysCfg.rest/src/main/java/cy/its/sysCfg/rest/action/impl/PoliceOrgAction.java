package cy.its.sysCfg.rest.action.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.sysCfg.rest.action.IPoliceOrgAction;
import cy.its.sysCfg.rest.dto.PoliceOrgDto;
import cy.its.syscfg.domain.criteria.OrgCriteria;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.service.IDutyService;

@RestController
@RequestMapping("/sysCfg/policeAgency/policeOrg")
public class PoliceOrgAction implements IPoliceOrgAction {
	@Autowired
	IDutyService dutyService;
	
	@RequestMapping("/selectAll")
	public List<PoliceOrgDto> selectAll(String currentOrgPrivilegeCodeString){
		List<PoliceOrgDto> pDtos=new ArrayList<PoliceOrgDto>();
		OrgCriteria criteria=new OrgCriteria();
		//criteria.orgPrivCode=currentOrgPrivilegeCodeString;
		List<Organization> organizations=dutyService.findOrganizations(criteria);
		for (Organization organization : organizations) {
			PoliceOrgDto policeOrgDto=new PoliceOrgDto(organization);			
			if(organization.orgSeat.orgLatitude!=null & organization.orgSeat.orgLongitude!=null){
				ObjectMapUtils.parseObject(this, organization);
				policeOrgDto.setOrgLatitude(organization.orgSeat.orgLatitude.toString());			
				policeOrgDto.setOrgLongitude(organization.orgSeat.orgLongitude.toString());
				policeOrgDto.setOrgHeaderName(organization.orgHeader.getOrgHeaderName());
				policeOrgDto.setOrgHeaderPhone(organization.orgHeader.getOrgHeaderPhone());
				policeOrgDto.setAddressDesc(organization.orgSeat.addressDesc);
				policeOrgDto.setOrgLevel(organization.orgLevel);
				pDtos.add(policeOrgDto);
			}
		}		
		return pDtos;
	}
}
